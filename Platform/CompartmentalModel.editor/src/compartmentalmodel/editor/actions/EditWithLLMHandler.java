package compartmentalmodel.editor.actions;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

import java.util.Collection;
import java.util.ArrayList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.handlers.HandlerUtil;

public class EditWithLLMHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = HandlerUtil.getActiveShell(event);
        ISelection selection = HandlerUtil.getCurrentSelection(event);

        String selectionInfo = "Nothing selected";
        Object domainElement = null;

        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();

            if (firstElement != null) {
                Object current = firstElement;
                
                // 1. Try to unwrap GMF EditPart
                try {
                    java.lang.reflect.Method method = current.getClass().getMethod("resolveSemanticElement");
                    Object sem = method.invoke(current);
                    if (sem != null) current = sem;
                } catch (Exception e) {}
                
                // 2. Try to unwrap generic Element wrapper
                try {
                    java.lang.reflect.Method method = current.getClass().getMethod("getElement");
                    Object elem = method.invoke(current);
                    if (elem != null) current = elem;
                } catch (Exception e) {}
                
                // 3. Try to unwrap Sirius DSemanticDecorator (DNode, DEdge, etc.)
                try {
                    java.lang.reflect.Method method = current.getClass().getMethod("getTarget");
                    Object target = method.invoke(current);
                    if (target != null) current = target;
                } catch (Exception e) {}

                if (current instanceof EObject) {
                    domainElement = current;
                    
                    // Format a nice display string
                    try {
                        java.lang.reflect.Method getName = current.getClass().getMethod("getPrimaryName");
                        selectionInfo = current.getClass().getSimpleName() + " (" + getName.invoke(current) + ")";
                    } catch (Exception e) {
                        try {
                            java.lang.reflect.Method getName = current.getClass().getMethod("getName");
                            selectionInfo = current.getClass().getSimpleName() + " (" + getName.invoke(current) + ")";
                        } catch (Exception e2) {
                            selectionInfo = current.toString();
                        }
                    }
                }
            }
        }

        if (!(domainElement instanceof EObject)) {
            MessageDialog.openInformation(shell, "Edit with LLM", "Please select a valid EMF model element.");
            return null;
        }
        
        EObject targetEObject = (EObject) domainElement;

        Path repoRoot = findRepoRoot();
        if (repoRoot == null) {
            MessageDialog.openError(shell, "Error", "Could not locate repository root.");
            return null;
        }

        Path keysDir = repoRoot.resolve(".keys");
        keysDir.toFile().mkdirs();

        EditWithLLMDialog dialog = new EditWithLLMDialog(shell, keysDir, repoRoot, selectionInfo);
        if (dialog.open() != Window.OK) return null;

        String llmProvider = dialog.getLlmProvider();
        String apiKey = dialog.getApiKey();
        String userPrompt = dialog.getUserPrompt();
        String historyFilePath = dialog.getSelectedHistoryFile();

        if (userPrompt.isEmpty()) {
            MessageDialog.openError(shell, "Error", "Prompt cannot be empty.");
            return null;
        }

        // Save the API key
        Path apiKeyFile = keysDir.resolve(llmProvider + ".key");
        try {
            Files.writeString(apiKeyFile, apiKey, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {}

        Path pipelineRoot = repoRoot.resolve("AI-ASSISTED MODEL-DRIVEN EPIDEMIOLOGY");
        Path runPromptScript = pipelineRoot.resolve("run_llm_json.py");

        Path compmodelFile = null;
        String baseName = "model";
        try {
            Resource resource = targetEObject.eResource();
            if (resource != null) {
                URI uri = resource.getURI();
                if (uri != null && uri.isPlatformResource()) {
                    String workspacePath = uri.toPlatformString(true);
                    IResource iRes = ResourcesPlugin.getWorkspace().getRoot().findMember(workspacePath);
                    if (iRes != null) {
                        compmodelFile = iRes.getLocation().toFile().toPath();
                        String fn = compmodelFile.getFileName().toString();
                        if (fn.endsWith(".compmodel")) {
                            baseName = fn.substring(0, fn.length() - ".compmodel".length());
                        }
                    }
                }
            }
        } catch (Exception e) {}

        if (compmodelFile == null || !compmodelFile.toFile().exists()) {
            MessageDialog.openError(shell, "Error", "Could not resolve the physical .compmodel file.");
            return null;
        }

        Path promptFilePath = keysDir.resolve("edit_prompt.txt");
        String fullPrompt = "The user has selected the following element in the editor:\n" +
                            selectionInfo + "\n\n" +
                            "User's edit request for this element:\n" +
                            userPrompt + "\n\n";

        if (historyFilePath != null) {
            fullPrompt += "NOTE: You have been provided with the CONTEXT HISTORY (attached file) of how this model was originally generated. " +
                          "The current model state may contain errors from your previous generation. Use the context history to understand the original source material and fix the error in the current model.\n\n";
        }
        
        fullPrompt += "Please locate this element in the XML and apply the requested edit. Ensure the output is the fully updated XML.";
        try {
            Files.writeString(promptFilePath, fullPrompt, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            MessageDialog.openError(shell, "Error", "Failed to prepare prompt: " + e.getMessage());
            return null;
        }

        Path pythonBin = PipelineRunner.detectPython(pipelineRoot);
        
        final Path finalCompmodelFile = compmodelFile;
        final String finalBaseName = baseName;

        Job job = new Job("Editing Model with LLM") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                monitor.beginTask("Requesting LLM edit...", IProgressMonitor.UNKNOWN);
                try {
                    java.util.List<String> commandList = new java.util.ArrayList<>();
                    commandList.add(pythonBin.toString());
                    commandList.add(runPromptScript.toString());
                    commandList.add("--prompt");
                    commandList.add(promptFilePath.toString());
                    commandList.add("--context");
                    commandList.add(finalCompmodelFile.toString());
                    commandList.add("--provider");
                    commandList.add(llmProvider);
                    commandList.add("--log-name");
                    commandList.add("Edit_" + finalBaseName);
                    
                    if (historyFilePath != null) {
                        commandList.add("--attach");
                        commandList.add("ContextHistory::" + historyFilePath);
                    }

                    ProcessBuilder pb = new ProcessBuilder(commandList);
                    pb.directory(pipelineRoot.toFile());
                    pb.redirectErrorStream(true);
                    
                    Process process = pb.start();
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            output.append(line).append("\n");
                            System.out.println("[EditWithLLM] " + line);
                        }
                    }
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        throw new Exception("Script failed with exit code " + exitCode + "\nOutput: " + output);
                    }

                    // Parse JSON output manually to avoid Gson dependency issues
                    String jsonStr = output.toString().trim();
                    if (jsonStr.contains("\"error\"")) {
                        throw new Exception("LLM returned error: " + jsonStr);
                    }
                    
                    // Extract model_xml from JSON {"model_xml": "..."}
                    int xmlStart = jsonStr.indexOf("\"model_xml\":");
                    if (xmlStart == -1) throw new Exception("Invalid JSON returned: missing model_xml");
                    
                    int firstQuote = jsonStr.indexOf("\"", xmlStart + 12);
                    int lastQuote = jsonStr.lastIndexOf("\"");
                    String xmlContent = jsonStr.substring(firstQuote + 1, lastQuote);
                    
                    // Unescape JSON string
                    xmlContent = xmlContent.replace("\\n", "\n").replace("\\\"", "\"").replace("\\\\", "\\");

                    // EMF Transaction
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(targetEObject);
                    final String finalXml = xmlContent;
                    
                    if (domain != null) {
                        Display.getDefault().syncExec(() -> {
                            Session session = null;
                            try {
                                session = SessionManager.INSTANCE.getSession(targetEObject.eResource());
                            } catch (Exception err) {
                                // Sirius might not be available or no session exists
                            }
                            
                            final Session fSession = session;
                            
                            if (fSession != null) {
                                EObject root = targetEObject.eResource().getContents().isEmpty() ? targetEObject : targetEObject.eResource().getContents().get(0);
                                Collection<DRepresentation> existingReps = new ArrayList<>(DialectManager.INSTANCE.getRepresentations(root, fSession));
                                Collection<DRepresentationDescriptor> existingDescriptors = new ArrayList<>();
                                
                                String repNameToUse = "Diagram";
                                RepresentationDescription repDescToUse = null;
                                
                                for (DRepresentation rep : existingReps) {
                                    repNameToUse = rep.getName();
                                    
                                    DRepresentationDescriptor desc = new DRepresentationQuery(rep).getRepresentationDescriptor();
                                    if (desc != null) {
                                        existingDescriptors.add(desc);
                                        repDescToUse = desc.getDescription();
                                    }
                                    
                                    org.eclipse.ui.IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                                    if (window != null) {
                                        IWorkbenchPage page = window.getActivePage();
                                        if (page != null) {
                                            IEditorPart activeEditor = page.getActiveEditor();
                                            if (activeEditor != null && activeEditor.getClass().getName().contains("sirius")) {
                                                page.closeEditor(activeEditor, false);
                                            }
                                        }
                                    }
                                }
                                
                                if (repDescToUse == null) {
                                    for (Viewpoint vp : fSession.getSelectedViewpoints(false)) {
                                        for (RepresentationDescription rd : vp.getOwnedRepresentations()) {
                                            if ("Diagram".equals(rd.getName())) {
                                                repDescToUse = rd;
                                                break;
                                            }
                                        }
                                    }
                                }
                                
                                final String finalRepName = repNameToUse;
                                final RepresentationDescription finalRepDesc = repDescToUse;
                                
                                domain.getCommandStack().execute(new RecordingCommand(domain) {
                                    @Override
                                    protected void doExecute() {
                                        try {
                                            for (DRepresentationDescriptor desc : existingDescriptors) {
                                                DialectManager.INSTANCE.deleteRepresentation(desc, fSession);
                                            }
                                            
                                            ResourceSet rset = new ResourceSetImpl();
                                            Resource tempRes = rset.createResource(URI.createURI("temp.compmodel"));
                                            tempRes.load(new ByteArrayInputStream(finalXml.getBytes("UTF-8")), null);
                                            
                                            Resource targetRes = targetEObject.eResource();
                                            targetRes.getContents().clear();
                                            targetRes.getContents().addAll(tempRes.getContents());
                                            
                                            if (finalRepDesc != null && !targetRes.getContents().isEmpty()) {
                                                EObject newRoot = targetRes.getContents().get(0);
                                                DRepresentation newRep = DialectManager.INSTANCE.createRepresentation(finalRepName, newRoot, finalRepDesc, fSession, new NullProgressMonitor());
                                                
                                                Display.getDefault().asyncExec(() -> {
                                                    DialectUIManager.INSTANCE.openEditor(fSession, newRep, new NullProgressMonitor());
                                                    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Success", "Model and diagram have been automatically updated by LLM!");
                                                });
                                            } else {
                                                Display.getDefault().asyncExec(() -> {
                                                    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Success", "Model has been updated by LLM!");
                                                });
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                domain.getCommandStack().execute(new RecordingCommand(domain) {
                                    @Override
                                    protected void doExecute() {
                                        try {
                                            ResourceSet rset = new ResourceSetImpl();
                                            Resource tempRes = rset.createResource(URI.createURI("temp.compmodel"));
                                            tempRes.load(new ByteArrayInputStream(finalXml.getBytes("UTF-8")), null);
                                            
                                            Resource targetRes = targetEObject.eResource();
                                            targetRes.getContents().clear();
                                            targetRes.getContents().addAll(tempRes.getContents());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                Display.getDefault().asyncExec(() -> {
                                    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Success", "Model has been updated by LLM via RecordingCommand!");
                                });
                            }
                        });
                    } else {
                        // Fallback: Write directly to the file on disk if we aren't in a strict EMF Transaction context
                        try {
                            Files.writeString(finalCompmodelFile, finalXml, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                            
                            // Refresh Eclipse workspace so it picks up the disk change
                            IResource iRes = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(
                                    new org.eclipse.core.runtime.Path(finalCompmodelFile.toAbsolutePath().toString()));
                            if (iRes != null) {
                                iRes.refreshLocal(IResource.DEPTH_INFINITE, monitor);
                                
                                Display.getDefault().asyncExec(() -> {
                                    try {
                                        org.eclipse.ui.IWorkbenchWindow window = org.eclipse.ui.PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                                        if (window != null) {
                                            org.eclipse.ui.IWorkbenchPage page = window.getActivePage();
                                            if (page != null) {
                                                org.eclipse.ui.IEditorInput input = new org.eclipse.ui.part.FileEditorInput((org.eclipse.core.resources.IFile) iRes);
                                                org.eclipse.ui.IEditorPart editor = page.findEditor(input);
                                                if (editor != null) {
                                                    page.closeEditor(editor, false);
                                                }
                                                page.openEditor(input, "compartmentalmodel.presentation.CompartmentalmodelEditorID");
                                            }
                                        }
                                        MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Success", "Model has been successfully updated by LLM!");
                                    } catch (Exception e) {
                                        MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Success", "Model has been updated by LLM on disk.\n\n" + e.getMessage());
                                    }
                                });
                            }
                        } catch (Exception e) {
                            throw new Exception("Failed to write updated XML to file: " + e.getMessage());
                        }
                    }
                    
                    monitor.done();
                    return Status.OK_STATUS;
                } catch (Exception e) {
                    Display.getDefault().asyncExec(() -> {
                        MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", "LLM Edit failed:\n" + e.getMessage());
                    });
                    monitor.done();
                    return new Status(IStatus.ERROR, "CompartmentalModel.editor", "LLM Edit failed", e);
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

    private static class EditWithLLMDialog extends Dialog {
        private String llmProvider = "gemini";
        private String apiKey = "";
        private String userPrompt = "";
        
        private Combo providerCombo;
        private Text apiKeyText;
        private Text promptText;
        
        private Path keysDir;
        private Path repoRoot;
        private String selectionInfo;
        
        private Button contextAwareBtn;
        private Combo historyCombo;
        private String selectedHistoryFile = null;
        
        public EditWithLLMDialog(Shell parentShell, Path keysDir, Path repoRoot, String selectionInfo) {
            super(parentShell);
            this.keysDir = keysDir;
            this.repoRoot = repoRoot;
            this.selectionInfo = selectionInfo;
        }
        
        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText("EpiMDE: Edit with LLM");
            newShell.setMinimumSize(500, 350);
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
            layout.marginWidth = 15;
            layout.marginHeight = 15;
            layout.verticalSpacing = 15;
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
            
            // 2. Edit Group
            Group editGroup = new Group(container, SWT.NONE);
            editGroup.setText("Edit Instructions");
            editGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            editGroup.setLayout(new GridLayout(1, false));
            
            Label targetLabel = new Label(editGroup, SWT.WRAP);
            targetLabel.setText("Selected Element: " + selectionInfo);
            targetLabel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
            
            promptText = new Text(editGroup, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
            GridData gdPrompt = new GridData(SWT.FILL, SWT.FILL, true, true);
            gdPrompt.heightHint = 80;
            promptText.setLayoutData(gdPrompt);
            promptText.setToolTipText("Describe exactly what to change about this element");

            contextAwareBtn = new Button(editGroup, SWT.CHECK);
            contextAwareBtn.setText("Context Aware (Include Model Generation History)");
            
            historyCombo = new Combo(editGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
            historyCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            historyCombo.setEnabled(false);
            
            contextAwareBtn.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    historyCombo.setEnabled(contextAwareBtn.getSelection());
                }
            });

            // Populate historyCombo with files from LLM_Samples
            File samplesDir = repoRoot.resolve("LLM_Samples").toFile();
            if (samplesDir.exists() && samplesDir.isDirectory()) {
                File[] files = samplesDir.listFiles((d, name) -> name.endsWith(".txt"));
                if (files != null && files.length > 0) {
                    Arrays.sort(files, (a, b) -> b.getName().compareTo(a.getName())); // latest first
                    for (File f : files) {
                        historyCombo.add(f.getName());
                    }
                    historyCombo.select(0);
                } else {
                    contextAwareBtn.setEnabled(false);
                    contextAwareBtn.setText("Context Aware (No history found)");
                }
            } else {
                contextAwareBtn.setEnabled(false);
                contextAwareBtn.setText("Context Aware (No history found)");
            }

            // Listeners
            providerCombo.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    loadApiKey();
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
            
            if (contextAwareBtn.getSelection() && historyCombo.getSelectionIndex() >= 0) {
                selectedHistoryFile = repoRoot.resolve("LLM_Samples").resolve(historyCombo.getText()).toString();
            } else {
                selectedHistoryFile = null;
            }
            
            super.okPressed();
        }
        
        public String getLlmProvider() { return llmProvider; }
        public String getApiKey() { return apiKey; }
        public String getUserPrompt() { return userPrompt; }
        public String getSelectedHistoryFile() { return selectedHistoryFile; }
    }
}
