package compartmentalmodel.editor.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
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

public class ExplainElementHandler extends AbstractHandler {

    protected boolean isContextAware() {
        return false;
    }

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
                try { java.lang.reflect.Method method = current.getClass().getMethod("resolveSemanticElement"); Object sem = method.invoke(current); if (sem != null) current = sem; } catch (Exception e) {}
                try { java.lang.reflect.Method method = current.getClass().getMethod("getElement"); Object elem = method.invoke(current); if (elem != null) current = elem; } catch (Exception e) {}
                try { java.lang.reflect.Method method = current.getClass().getMethod("getTarget"); Object target = method.invoke(current); if (target != null) current = target; } catch (Exception e) {}

                if (current instanceof EObject) {
                    domainElement = current;
                    try { java.lang.reflect.Method getName = current.getClass().getMethod("getPrimaryName"); selectionInfo = current.getClass().getSimpleName() + " (" + getName.invoke(current) + ")"; } 
                    catch (Exception e) {
                        try { java.lang.reflect.Method getName = current.getClass().getMethod("getName"); selectionInfo = current.getClass().getSimpleName() + " (" + getName.invoke(current) + ")"; } 
                        catch (Exception e2) { selectionInfo = current.toString(); }
                    }
                }
            }
        }

        if (!(domainElement instanceof EObject)) {
            MessageDialog.openInformation(shell, "Explain Element", "Please select a valid EMF model element.");
            return null;
        }

        Path repoRoot = findRepoRoot();
        if (repoRoot == null) {
            MessageDialog.openError(shell, "Error", "Could not locate repository root.");
            return null;
        }

        Path keysDir = repoRoot.resolve(".keys");
        keysDir.toFile().mkdirs();

        ExplainElementDialog dialog = new ExplainElementDialog(shell, keysDir, repoRoot, selectionInfo, isContextAware());
        if (dialog.open() != Window.OK) return null;

        String llmProvider = dialog.getLlmProvider();
        String apiKey = dialog.getApiKey();
        String historyFilePath = dialog.getSelectedHistoryFile();

        Path apiKeyFile = keysDir.resolve(llmProvider + ".key");
        try { Files.writeString(apiKeyFile, apiKey, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING); } catch (Exception e) {}

        Path pipelineRoot = repoRoot.resolve("AI-ASSISTED MODEL-DRIVEN EPIDEMIOLOGY");
        Path runPromptScript = pipelineRoot.resolve("run_explain_element.py");

        Path promptFilePath = keysDir.resolve("explain_prompt.txt");
        String fullPrompt = "The user has selected the following element in the editor:\n" + selectionInfo + "\n\n";
        
        try { Files.writeString(promptFilePath, fullPrompt, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING); } 
        catch (Exception e) { MessageDialog.openError(shell, "Error", "Failed to prepare prompt: " + e.getMessage()); return null; }

        Path pythonBin = PipelineRunner.detectPython(pipelineRoot);

        final String finalSelectionInfo = selectionInfo;

        Job job = new Job("Explaining Element with LLM") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                monitor.beginTask("Requesting explanation...", IProgressMonitor.UNKNOWN);
                
                String explanation = runLLMExplanation(repoRoot, finalSelectionInfo, historyFilePath, llmProvider, "");

                Display.getDefault().asyncExec(() -> {
                    if (explanation.startsWith("Error:") && explanation.split("\n").length <= 2) {
                        MessageDialog.openError(shell, "Pipeline Error", explanation);
                    } else {
                        ElementExplanationChatDialog chatDialog = new ElementExplanationChatDialog(shell, repoRoot, finalSelectionInfo, historyFilePath, llmProvider, explanation);
                        chatDialog.open();
                    }
                });
                
                monitor.done();
                return Status.OK_STATUS;
            }
        };
        job.setUser(true);
        job.schedule();

        return null;
    }

    private static String runLLMExplanation(Path repoRoot, String selectionInfo, String historyFilePath, String llmProvider, String followUp) {
        try {
            Path pipelineRoot = repoRoot.resolve("AI-ASSISTED MODEL-DRIVEN EPIDEMIOLOGY");
            Path pythonBin = PipelineRunner.detectPython(pipelineRoot);
            Path runPromptScript = pipelineRoot.resolve("run_explain_element.py");
            
            Path keysDir = repoRoot.resolve(".keys");
            keysDir.toFile().mkdirs();
            Path promptFilePath = keysDir.resolve("explain_prompt.txt");
            
            String fullPrompt = "The user has selected the following element in the editor:\n" + selectionInfo + "\n\n";
            if (!followUp.isEmpty()) {
                fullPrompt += "\n\nFollow-up question:\n" + followUp;
            }
            
            Files.writeString(promptFilePath, fullPrompt, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            
            java.util.List<String> commandList = new java.util.ArrayList<>();
            commandList.add(pythonBin.toString());
            commandList.add(runPromptScript.toString());
            commandList.add("--prompt");
            commandList.add(promptFilePath.toString());
            commandList.add("--provider");
            commandList.add(llmProvider);
            
            if (historyFilePath != null && !historyFilePath.isEmpty()) {
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
                }
            }
            process.waitFor();
            return output.toString().trim();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static class ElementExplanationChatDialog extends Dialog {
        private String selectionInfo;
        private String explanation;
        private Path repoRoot;
        private String historyFilePath;
        private String llmProvider;
        private String conversationHtml = "";
        
        private Browser browser;
        private Text followUpText;

        public ElementExplanationChatDialog(Shell parentShell, Path repoRoot, String selectionInfo, String historyFilePath, String llmProvider, String explanation) {
            super(parentShell);
            this.repoRoot = repoRoot;
            this.selectionInfo = selectionInfo;
            this.historyFilePath = historyFilePath;
            this.llmProvider = llmProvider;
            this.explanation = explanation;
            this.conversationHtml = "<div class='ai-msg'><h3>AI Assistant</h3>" + explanation + "</div>";
        }

        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText("AI Element Explanation");
            newShell.setMinimumSize(700, 600);
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
            container.setLayout(new GridLayout(1, false));

            Label label = new Label(container, SWT.NONE);
            label.setText("Explanation for: " + selectionInfo);

            browser = new Browser(container, SWT.BORDER);
            GridData gd1 = new GridData(SWT.FILL, SWT.FILL, true, true);
            gd1.heightHint = 300;
            browser.setLayoutData(gd1);
            updateBrowser();

            Label followUpLabel = new Label(container, SWT.NONE);
            followUpLabel.setText("Follow-up Question:");

            followUpText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
            GridData gd2 = new GridData(SWT.FILL, SWT.FILL, true, false);
            gd2.heightHint = 60;
            followUpText.setLayoutData(gd2);

            Button askBtn = new Button(container, SWT.PUSH);
            askBtn.setText("Ask");
            askBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
            askBtn.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    String question = followUpText.getText().trim();
                    if (question.isEmpty()) return;
                    
                    String previousConvo = conversationHtml;
                    
                    conversationHtml += "<div class='user-msg'><h3>You</h3><p>" + question.replace("\n", "<br>") + "</p></div>";
                    updateBrowser();
                    
                    followUpText.setText("");
                    askBtn.setEnabled(false);
                    
                    new Thread(() -> {
                        String newContext = "Previous conversation:\n" + previousConvo;
                        String response = runLLMExplanation(repoRoot, selectionInfo, historyFilePath, llmProvider, newContext + "\n\nFollow-up:\n" + question);
                        Display.getDefault().asyncExec(() -> {
                            if (!browser.isDisposed()) {
                                conversationHtml += "<div class='ai-msg'><h3>AI Assistant</h3>" + response + "</div>";
                                updateBrowser();
                            }
                            if (!askBtn.isDisposed()) {
                                askBtn.setEnabled(true);
                            }
                        });
                    }).start();
                }
            });

            return area;
        }

        private void updateBrowser() {
            String html = "<html><head><style>" +
                "body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 1.6; color: #333; padding: 15px; margin: 0; }" +
                ".user-msg { background-color: #e3f2fd; padding: 10px 15px; border-radius: 8px; margin-bottom: 15px; border-left: 4px solid #1976d2; }" +
                ".ai-msg { background-color: #ffffff; padding: 10px 15px; border-radius: 8px; margin-bottom: 15px; border: 1px solid #ddd; }" +
                "h3 { margin-top: 0; margin-bottom: 5px; font-size: 14px; color: #555; }" +
                "p { margin-top: 0; margin-bottom: 10px; }" +
                "p:last-child { margin-bottom: 0; }" +
                "pre { background: #f4f4f5; padding: 10px; border-radius: 5px; overflow-x: auto; font-family: Consolas, monospace; font-size: 13px; border: 1px solid #e0e0e0; }" +
                "code { font-family: Consolas, monospace; background: #f4f4f5; padding: 2px 4px; border-radius: 3px; font-size: 13px; }" +
                "ul, ol { margin-top: 0; margin-bottom: 10px; padding-left: 20px; }" +
                "</style></head><body>" +
                conversationHtml +
                "<script>window.scrollTo(0, document.body.scrollHeight);</script>" +
                "</body></html>";
            browser.setText(html);
        }

        @Override
        protected void createButtonsForButtonBar(Composite parent) {
            createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
        }
    }

    private static Path findRepoRoot() {
        try {
            URL bundleUrl = Platform.getBundle("CompartmentalModel.editor").getEntry("/");
            URL fileUrl = FileLocator.toFileURL(bundleUrl);
            Path path = Paths.get(fileUrl.toURI());
            while (path != null && !path.resolve("Compartmental").toFile().exists()) { path = path.getParent(); }
            return path;
        } catch (Exception e) { return null; }
    }

    private static class ExplainElementDialog extends Dialog {
        private String llmProvider = "gemini";
        private String apiKey = "";
        
        private Combo providerCombo;
        private Text apiKeyText;
        private Combo historyCombo;
        
        private Path keysDir;
        private Path repoRoot;
        private String selectionInfo;
        private boolean isContextAware;
        private String selectedHistoryFile = null;
        
        public ExplainElementDialog(Shell parentShell, Path keysDir, Path repoRoot, String selectionInfo, boolean isContextAware) {
            super(parentShell);
            this.keysDir = keysDir;
            this.repoRoot = repoRoot;
            this.selectionInfo = selectionInfo;
            this.isContextAware = isContextAware;
        }
        
        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText(isContextAware ? "Explain Element (Context-Aware)" : "Explain Element (Fast)");
            newShell.setMinimumSize(400, 250);
        }
        
        @Override
        protected boolean isResizable() { return true; }
        
        @Override
        protected Control createDialogArea(Composite parent) {
            Composite area = (Composite) super.createDialogArea(parent);
            Composite container = new Composite(area, SWT.NONE);
            container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            GridLayout layout = new GridLayout(1, false);
            container.setLayout(layout);
            
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
            
            Label targetLabel = new Label(container, SWT.WRAP);
            targetLabel.setText("Selected Element: " + selectionInfo);
            targetLabel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
            
            if (isContextAware) {
                Label histLabel = new Label(container, SWT.NONE);
                histLabel.setText("Select Context History:");
                historyCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
                historyCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
                
                java.io.File samplesDir = repoRoot.resolve("LLM_Samples").toFile();
                if (samplesDir.exists() && samplesDir.isDirectory()) {
                    java.io.File[] files = samplesDir.listFiles((d, name) -> name.endsWith(".txt"));
                    if (files != null && files.length > 0) {
                        java.util.Arrays.sort(files, (a, b) -> b.getName().compareTo(a.getName()));
                        for (java.io.File f : files) { historyCombo.add(f.getName()); }
                        historyCombo.select(0);
                    }
                }
            }

            providerCombo.addSelectionListener(new SelectionAdapter() {
                @Override public void widgetSelected(SelectionEvent e) { loadApiKey(); }
            });
            
            loadApiKey();
            return area;
        }
        
        private void loadApiKey() {
            String provider = providerCombo.getText();
            Path keyFile = keysDir.resolve(provider + ".key");
            if (keyFile.toFile().exists()) {
                try { apiKeyText.setText(Files.readString(keyFile).trim()); } catch (Exception e) { apiKeyText.setText(""); }
            } else { apiKeyText.setText(""); }
        }
        
        @Override
        protected void okPressed() {
            llmProvider = providerCombo.getText();
            apiKey = apiKeyText.getText().trim();
            if (isContextAware && historyCombo != null && historyCombo.getSelectionIndex() >= 0) {
                selectedHistoryFile = repoRoot.resolve("LLM_Samples").resolve(historyCombo.getText()).toString();
            }
            super.okPressed();
        }
        
        public String getLlmProvider() { return llmProvider; }
        public String getApiKey() { return apiKey; }
        public String getSelectedHistoryFile() { return selectedHistoryFile; }
    }
}
