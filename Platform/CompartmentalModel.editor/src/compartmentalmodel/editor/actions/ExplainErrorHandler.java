package compartmentalmodel.editor.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExplainErrorHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = HandlerUtil.getActiveShell(event);
        if (shell == null) return null;

        boolean isFast = event.getCommand() != null && event.getCommand().getId().contains("quickExplainError");

        Path workspaceRoot = Paths.get(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
        Path logPath = workspaceRoot.resolve(".metadata").resolve(".log");

        if (!Files.exists(logPath)) {
            MessageDialog.openInformation(shell, "Explain Error", "No error found in logs.");
            return null;
        }

        String lastError = "";
        try {
            List<String> lines = Files.readAllLines(logPath);
            int lastEntryIdx = -1;
            // Find the last !ENTRY in the last 500 lines to isolate the most recent event
            for (int i = lines.size() - 1; i >= Math.max(0, lines.size() - 500); i--) {
                if (lines.get(i).startsWith("!ENTRY")) {
                    lastEntryIdx = i;
                    break;
                }
            }
            
            if (lastEntryIdx != -1) {
                StringBuilder sb = new StringBuilder();
                boolean hasError = false;
                for (int i = lastEntryIdx; i < lines.size(); i++) {
                    String l = lines.get(i);
                    sb.append(l).append("\n");
                    if (l.contains("Exception") || l.contains("Error") || l.contains("!STACK") || l.contains("!MESSAGE")) {
                        hasError = true;
                    }
                }
                lastError = sb.toString();
                
                // Truncate massive stack traces by keeping the top and bottom (where Caused by: usually is)
                if (lastError.length() > 4000) {
                    lastError = lastError.substring(0, 2000) + "\n\n... [TRUNCATED MASSIVE STACK TRACE] ...\n\n" + lastError.substring(lastError.length() - 2000);
                }
                
                if (!hasError) {
                    MessageDialog.openInformation(shell, "Explain Error", "No error found in recent logs.");
                    return null;
                }
            } else {
                MessageDialog.openInformation(shell, "Explain Error", "No recent log entries found.");
                return null;
            }
        } catch (Exception e) {
            MessageDialog.openError(shell, "Error", "Could not read log file: " + e.getMessage());
            return null;
        }

        Path repoRoot = findRepoRoot();
        if (repoRoot == null) return null;
        
        final String errorStr = lastError;

        Job job = new Job("Explaining Error with AI") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                monitor.beginTask("Asking LLM...", IProgressMonitor.UNKNOWN);
                String explanation = runLLMExplanation(repoRoot, errorStr, "", isFast);
                
                Display.getDefault().asyncExec(() -> {
                    if (explanation.startsWith("Error:") && explanation.split("\n").length == 1) {
                        MessageDialog.openError(shell, "Pipeline Error", explanation);
                    } else {
                        ExplainErrorDialog dialog = new ExplainErrorDialog(shell, repoRoot, errorStr, explanation, isFast);
                        dialog.open();
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

    private static String runLLMExplanation(Path repoRoot, String errorStr, String followUp, boolean isFast) {
        try {
            Path pipelineRoot = repoRoot.resolve("AI-ASSISTED MODEL-DRIVEN EPIDEMIOLOGY");
            Path pythonBin = PipelineRunner.detectPython(pipelineRoot);
            Path script = pipelineRoot.resolve("run_explain_error.py");
            
            Path keysDir = repoRoot.resolve(".keys");
            keysDir.toFile().mkdirs();
            Path errorFile = keysDir.resolve("current_error.txt");
            
            String content = "Error:\n" + errorStr;
            if (!followUp.isEmpty()) {
                content += "\n\nFollow-up question:\n" + followUp;
            }
            
            Files.writeString(errorFile, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            
            List<String> cmd = new ArrayList<>();
            cmd.add(pythonBin.toString());
            cmd.add(script.toString());
            cmd.add("--error");
            cmd.add(errorFile.toString());
            if (isFast) {
                cmd.add("--fast");
            }
            
            ProcessBuilder pb = new ProcessBuilder(cmd);
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

    private static class ExplainErrorDialog extends Dialog {
        private String errorStr;
        private String explanation;
        private Path repoRoot;
        private String conversationHtml = "";
        
        private Browser browser;
        private Text followUpText;
        private boolean isFast;

        public ExplainErrorDialog(Shell parentShell, Path repoRoot, String errorStr, String explanation, boolean isFast) {
            super(parentShell);
            this.repoRoot = repoRoot;
            this.errorStr = errorStr;
            this.explanation = explanation;
            this.isFast = isFast;
            this.conversationHtml = "<div class='ai-msg'><h3>AI Assistant</h3>" + explanation + "</div>";
        }

        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText("AI Error Explanation");
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
            label.setText("Explanation:");

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
                        String newContext = errorStr + "\n\nPrevious conversation:\n" + previousConvo;
                        String response = runLLMExplanation(repoRoot, newContext, question, isFast);
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
}
