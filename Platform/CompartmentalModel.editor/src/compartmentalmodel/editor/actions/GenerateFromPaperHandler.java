package compartmentalmodel.editor.actions;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import java.util.List;
import java.util.ArrayList;

public class GenerateFromPaperHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = HandlerUtil.getActiveShell(event);
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        if (shell == null || window == null) return null;

        // Step 1: Resolve repo root
        Path repoRoot = findRepoRoot();
        if (repoRoot == null) {
            MessageDialog.openError(shell, "Error", "Could not locate repository root.");
            return null;
        }
        Path keysDir = repoRoot.resolve(".keys");
        keysDir.toFile().mkdirs();

        // Step 2: Show unified dialog
        BuildWithLLMDialog dialog = new BuildWithLLMDialog(shell, keysDir);
        if (dialog.open() != Window.OK) return null;

        String llmProvider = dialog.getLlmProvider();
        String apiKey = dialog.getApiKey();
        String userPrompt = dialog.getUserPrompt();

        if (userPrompt.isEmpty()) {
            MessageDialog.openError(shell, "Error", "Prompt cannot be empty.");
            return null;
        }
        if (apiKey.isEmpty()) {
            MessageDialog.openError(shell, "Error", "API Key cannot be empty.");
            return null;
        }

        // Save the API key
        Path apiKeyFile = keysDir.resolve(llmProvider + ".key");
        try {
            Files.writeString(apiKeyFile, apiKey, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            MessageDialog.openError(shell, "Error", "Failed to save API key: " + e.getMessage());
            return null;
        }

        Path pipelineRoot = repoRoot.resolve("AI-ASSISTED MODEL-DRIVEN EPIDEMIOLOGY");

        // Step 3: Setup Workspace Project
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("CompartmentalModel");
        try {
            if (project == null || !project.exists()) {
                project.create(null);
                project.open(null);
            } else if (!project.isOpen()) {
                project.open(null);
            }
        } catch (CoreException e) {
            MessageDialog.openError(shell, "Error", "Failed to create project: " + e.getMessage());
            return null;
        }

        // Detect python and script
        Path pythonBin = PipelineRunner.detectPython(pipelineRoot);
        Path pipelineScript = pipelineRoot.resolve("run_llm_json.py");

        String modelName = dialog.getModelName();
        if (modelName.isEmpty()) modelName = "model_" + llmProvider + "_" + java.time.LocalDateTime.now().toString().replace(":", "-").replace("T", "_");
        final String finalName = modelName;

        IFile newFile = project.getFile(finalName + ".compmodel");
        if (newFile.exists()) {
            MessageDialog.openError(shell, "Error", "A model named '" + finalName + "' already exists! Please choose a different name to prevent overwriting.");
            return null;
        }
        
        // Write prompt to file
        Path systemPromptFile = keysDir.resolve("build_prompt.txt");
        try {
            Files.writeString(systemPromptFile, userPrompt, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            MessageDialog.openError(shell, "Error", "Failed to write prompt: " + e.getMessage());
            return null;
        }
        
        // We don't read files in Java anymore, we let Python handle it.
        // Create an empty context file to pass as base context
        Path contextFile = keysDir.resolve("empty_context.xml");
        String emptyContext = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                              "<compartmentalmodel:CompartmentalModel xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:compartmentalmodel=\"http://example.com/compartmentalmodel\"/>\n";
        try {
            Files.writeString(contextFile, emptyContext, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {}
        final Path finalContextFile = contextFile;
        
        // Collect attachment arguments
        List<String> commandList = new ArrayList<>();
        commandList.add(pythonBin.toString());
        commandList.add(pipelineScript.toString());
        commandList.add("--prompt");
        commandList.add(systemPromptFile.toString());
        commandList.add("--context");
        commandList.add(finalContextFile.toString());
        commandList.add("--provider");
        commandList.add(llmProvider);
        commandList.add("--log-name");
        commandList.add("Build_" + finalName);
        
        if (!dialog.getAttachedFiles().isEmpty()) {
            for (AttachedFile f : dialog.getAttachedFiles()) {
                commandList.add("--attach");
                commandList.add(f.alias + "::" + f.path);
            }
        }

        // Step 4: Run LLM JSON Builder
        Job job = new Job("Building Model with LLM") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                monitor.beginTask("Requesting model generation...", IProgressMonitor.UNKNOWN);
                try {
                    ProcessBuilder pb = new ProcessBuilder(commandList);
                    pb.directory(pipelineRoot.toFile());
                    pb.redirectErrorStream(true);
                    
                    Process process = pb.start();
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            output.append(line).append("\n");
                            System.out.println("[BuildWithLLM] " + line);
                        }
                    }
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        throw new Exception("Script failed with exit code " + exitCode + "\nOutput:\n" + output);
                    }

                    String jsonStr = output.toString().trim();
                    if (jsonStr.contains("\"error\"")) {
                        throw new Exception("LLM returned error: " + jsonStr);
                    }
                    
                    int xmlStart = jsonStr.indexOf("\"model_xml\":");
                    if (xmlStart == -1) throw new Exception("Invalid JSON returned: missing model_xml");
                    
                    int firstQuote = jsonStr.indexOf("\"", xmlStart + 12);
                    int lastQuote = jsonStr.lastIndexOf("\"");
                    String xmlContent = jsonStr.substring(firstQuote + 1, lastQuote);
                    
                    // Unescape JSON string
                    xmlContent = xmlContent.replace("\\n", "\n").replace("\\\"", "\"").replace("\\\\", "\\");

                    // Save XML to an EMF Resource using Workspace URI
                    IFile newFile = project.getFile(finalName + ".compmodel");
                    URI uri = URI.createPlatformResourceURI(newFile.getFullPath().toString(), true);
                    
                    ResourceSet rset = new ResourceSetImpl();
                    Resource res = rset.createResource(uri);
                    
                    // Use a temporary resource to parse the XML, then move contents
                    Resource tempRes = rset.createResource(URI.createURI("temp.compmodel"));
                    tempRes.load(new ByteArrayInputStream(xmlContent.getBytes("UTF-8")), null);
                    
                    res.getContents().addAll(tempRes.getContents());
                    res.save(null);
                    
                    project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

                    Display.getDefault().asyncExec(() -> {
                        if (newFile.exists()) {
                            try {
                                IWorkbenchPage page = window.getActivePage();
                                if (page != null) {
                                    page.openEditor(new FileEditorInput(newFile), "compartmentalmodel.presentation.CompartmentalmodelEditorID");
                                }
                                MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Success", "Model successfully built and loaded via JSON!");
                            } catch (PartInitException e) {}
                        }
                    });

                    monitor.done();
                    return Status.OK_STATUS;
                } catch (Exception e) {
                    Display.getDefault().asyncExec(() -> {
                        MessageDialog.openError(Display.getDefault().getActiveShell(), "Pipeline Error", "Model generation failed:\n" + e.getMessage());
                    });
                    monitor.done();
                    return new Status(IStatus.ERROR, "CompartmentalModel.editor", "Pipeline failed", e);
                }
            }
        };
        job.setUser(true);
        job.schedule();

        return null;
    }

    private static Path findRepoRoot() {
        try {
            URL bundleUrl = Platform.getBundle("CompartmentalModel.editor").getEntry("/");
            URL fileUrl = FileLocator.toFileURL(bundleUrl);
            Path path = Paths.get(fileUrl.toURI());
            while (path != null && !path.resolve("Compartmental").toFile().exists()) {
                path = path.getParent();
            }
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    public static class AttachedFile {
        public String alias;
        public String path;
        public AttachedFile(String alias, String path) { this.alias = alias; this.path = path; }
    }

    private static class BuildWithLLMDialog extends Dialog {
        private String llmProvider = "gemini";
        private String apiKey = "";
        private String userPrompt = "";
        private String modelName = "";
        private List<AttachedFile> attachedFiles = new ArrayList<>();
        
        private Combo providerCombo;
        private Text apiKeyText;
        private Text promptText;
        private Text modelNameText;
        private Table fileTable;
        
        private Path keysDir;
        
        public BuildWithLLMDialog(Shell parentShell, Path keysDir) {
            super(parentShell);
            this.keysDir = keysDir;
        }
        
        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText("EpiMDE: LLM Model Generator");
            newShell.setMinimumSize(700, 650);
        }
        
        @Override
        protected boolean isResizable() {
            return true;
        }
        
        @Override
        protected Control createDialogArea(Composite parent) {
            Composite area = (Composite) super.createDialogArea(parent);
            Composite container = new Composite(area, SWT.NONE);
            container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            GridLayout layout = new GridLayout(1, false);
            layout.marginWidth = 20;
            layout.marginHeight = 20;
            layout.verticalSpacing = 20;
            container.setLayout(layout);
            
            // 1. LLM Authentication Group
            Group authGroup = new Group(container, SWT.NONE);
            authGroup.setText("LLM Configuration");
            authGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            authGroup.setLayout(new GridLayout(2, false));
            
            Label providerLabel = new Label(authGroup, SWT.NONE);
            providerLabel.setText("Provider:");
            providerCombo = new Combo(authGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
            providerCombo.setItems(new String[]{"gemini", "openai"});
            providerCombo.select(0);
            providerCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            
            Label apiKeyLabel = new Label(authGroup, SWT.NONE);
            apiKeyLabel.setText("API Key:");
            apiKeyText = new Text(authGroup, SWT.BORDER | SWT.PASSWORD);
            apiKeyText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            Label nameLabel = new Label(authGroup, SWT.NONE);
            nameLabel.setText("Model Name:");
            modelNameText = new Text(authGroup, SWT.BORDER);
            modelNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            
            // 2. Reference Files Group
            Group attachGroup = new Group(container, SWT.NONE);
            attachGroup.setText("Context Files (Reference material)");
            attachGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            attachGroup.setLayout(new GridLayout(2, false));
            
            fileTable = new Table(attachGroup, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
            fileTable.setHeaderVisible(true);
            fileTable.setLinesVisible(true);
            GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
            tableData.heightHint = 120;
            fileTable.setLayoutData(tableData);
            
            TableColumn nameCol = new TableColumn(fileTable, SWT.NONE);
            nameCol.setText("Alias");
            nameCol.setWidth(150);
            
            TableColumn pathCol = new TableColumn(fileTable, SWT.NONE);
            pathCol.setText("File Path");
            pathCol.setWidth(350);
            
            Composite btnComp = new Composite(attachGroup, SWT.NONE);
            btnComp.setLayout(new GridLayout(1, false));
            btnComp.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
            
            Button btnAdd = new Button(btnComp, SWT.PUSH);
            btnAdd.setText("Add File...");
            btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
            
            Button btnRemove = new Button(btnComp, SWT.PUSH);
            btnRemove.setText("Remove");
            btnRemove.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

            Button btnRename = new Button(btnComp, SWT.PUSH);
            btnRename.setText("Rename Alias");
            btnRename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
            
            // 3. Prompt Group
            Group promptGroup = new Group(container, SWT.NONE);
            promptGroup.setText("Model Instructions (Prompt)");
            promptGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            promptGroup.setLayout(new GridLayout(1, false));
            
            promptText = new Text(promptGroup, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
            GridData gdPrompt = new GridData(SWT.FILL, SWT.FILL, true, true);
            gdPrompt.heightHint = 200;
            promptText.setLayoutData(gdPrompt);

            // Listeners
            providerCombo.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    loadApiKey();
                }
            });
            
            btnAdd.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
                    String path = fd.open();
                    if (path != null) {
                        String defaultAlias = new java.io.File(path).getName();
                        TableItem item = new TableItem(fileTable, SWT.NONE);
                        item.setText(0, defaultAlias);
                        item.setText(1, path);
                    }
                }
            });
            
            btnRemove.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    int[] selection = fileTable.getSelectionIndices();
                    fileTable.remove(selection);
                }
            });
            
            btnRename.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    int idx = fileTable.getSelectionIndex();
                    if (idx >= 0) {
                        TableItem item = fileTable.getItem(idx);
                        InputDialog aliasDialog = new InputDialog(getShell(), "Rename Alias", "Enter new alias for this file:", item.getText(0), null);
                        if (aliasDialog.open() == Window.OK) {
                            item.setText(0, aliasDialog.getValue());
                        }
                    }
                }
            });
            
            loadApiKey();
            return area;
        }
        
        private void loadApiKey() {
            String provider = providerCombo.getText();
            Path keyFile = keysDir.resolve(provider + ".key");
            if (keyFile.toFile().exists()) {
                try {
                    String existingKey = Files.readString(keyFile).trim();
                    apiKeyText.setText(existingKey);
                } catch (Exception e) {
                    apiKeyText.setText("");
                }
            } else {
                apiKeyText.setText("");
            }
        }
        
        @Override
        protected void okPressed() {
            llmProvider = providerCombo.getText();
            apiKey = apiKeyText.getText().trim();
            userPrompt = promptText.getText().trim();
            modelName = modelNameText.getText().trim();
            
            attachedFiles.clear();
            for (TableItem item : fileTable.getItems()) {
                attachedFiles.add(new AttachedFile(item.getText(0), item.getText(1)));
            }
            super.okPressed();
        }
        
        public String getLlmProvider() { return llmProvider; }
        public String getApiKey() { return apiKey; }
        public String getUserPrompt() { return userPrompt; }
        public String getModelName() { return modelName; }
        public List<AttachedFile> getAttachedFiles() { return attachedFiles; }
    }
}
