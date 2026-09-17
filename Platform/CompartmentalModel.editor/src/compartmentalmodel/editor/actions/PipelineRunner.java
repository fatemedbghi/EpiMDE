package compartmentalmodel.editor.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Runs the Python LLM pipeline (run_full_pipeline.py) as an external process.
 */
public class PipelineRunner {

    private final Path pythonExecutable;
    private final Path scriptPath;
    private final Path pipelineRoot;

    /**
     * @param pythonExecutable path to python3 binary (e.g., from venv)
     * @param scriptPath       path to run_full_pipeline.py
     * @param pipelineRoot     working directory for the pipeline (AI-ASSISTED... root)
     */
    public PipelineRunner(Path pythonExecutable, Path scriptPath, Path pipelineRoot) {
        this.pythonExecutable = pythonExecutable;
        this.scriptPath = scriptPath;
        this.pipelineRoot = pipelineRoot;
    }

    /**
     * Runs the full pipeline (Phase 2 + Phase 3) on a PDF.
     *
     * @param pdfPath      absolute path to the input PDF
     * @param llmProvider  "gemini" or "openai"
     * @param outputDir    directory where the final .compmodel should be placed
     * @param modelName    base name for the output .compmodel file
     * @param monitor      Eclipse progress monitor for cancellation/feedback
     * @return absolute path to the generated .compmodel file
     * @throws IOException          if process fails to start
     * @throws InterruptedException if process is interrupted
     */
    public Path run(Path pdfPath, String llmProvider, Path outputDir, String modelName,
                    IProgressMonitor monitor) throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder(
                pythonExecutable.toString(),
                scriptPath.toString(),
                "--pdf", pdfPath.toString(),
                "--llm-provider", llmProvider,
                "--output", outputDir.toString(),
                "--model-name", modelName,
                "--repair"
        );

        pb.directory(pipelineRoot.toFile());
        pb.redirectErrorStream(true);

        monitor.setTaskName("Running LLM pipeline (Phase 2 + 3)...");

        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("OUTPUT_PATH:")) {
                    String pathStr = line.substring("OUTPUT_PATH:".length()).trim();
                    Path result = Path.of(pathStr);
                    if (result.toFile().exists()) {
                        return result;
                    }
                }
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Pipeline failed with exit code " + exitCode
                    + ". Check Eclipse Console for details.");
        }

        // If we didn't catch OUTPUT_PATH in stdout, try to find the file
        Path fallback = outputDir.resolve(modelName + ".compmodel");
        if (fallback.toFile().exists()) {
            return fallback;
        }

        throw new IOException("Pipeline completed but .compmodel not found at " + fallback);
    }

    /**
     * Auto-detect the best Python binary to use.
     * Checks for venv first, then falls back to system python3.
     */
    public static Path detectPython(Path pipelineRoot) {
        Path venvPython = pipelineRoot.resolve(".venv/bin/python3");
        if (venvPython.toFile().exists()) {
            return venvPython;
        }
        // Try to find python3 on PATH
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return Path.of("python");
        }
        return Path.of("python3");
    }

    /**
     * Resolve the pipeline root relative to the Compartmental workspace project.
     * Workspace is typically at the repo root, so AI-ASSISTED... is a sibling of Compartmental/.
     */
    public static Path resolvePipelineRoot(Path workspaceProjectPath) {
        // Compartmental/CompartmentalModel.editor/... -> go up to repo root
        Path repoRoot = workspaceProjectPath;
        while (repoRoot != null && !repoRoot.resolve("Compartmental").toFile().exists()) {
            repoRoot = repoRoot.getParent();
        }
        if (repoRoot == null) {
            // Fallback: assume workspace is the repo root
            repoRoot = Path.of(".").toAbsolutePath().normalize();
        }
        return repoRoot.resolve("AI-ASSISTED MODEL-DRIVEN EPIDEMIOLOGY");
    }
}
