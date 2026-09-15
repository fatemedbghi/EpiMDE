# LLM-Driven Epidemiological Model Generation (Supplementary Material)

This repository contains the source code for the LLM-driven pipeline designed for generating epidemiological models in XML format, as described in the accompanying research paper. It serves as supplementary material to allow reviewers and researchers to reproduce the experimental results and explore the methodology.

## 1. Project Overview

This project implements a three-stage Large Language Model (LLM) pipeline that takes structured textual input (tables of compartments, flows, parameters, and initial populations) and transforms it into a standardized SEIR model XML format:

* **Stage 1** — structural generation: produces a `_skeleton.xml` file (no numerical values; rates are placeholders `[[rate_missing]]`).
* **Stage 2** — value mapping and verification: copies numeric values explicitly provided in the user input into the `.seirmodel` file. As an important design constraint, the LLM performs **no calculations** (see Section 4.4.1 of the paper) — all mathematical evaluation happens during user input preparation, so the same textual specification always produces the same model (reproducibility, Section 4.4.2).
* **Stage 3** — simulation: generates an executable Python simulation script from the ODE equations in `ode.json`.

The pipeline leverages an LLM (Google's Gemini 2.5 Pro, OpenAI's GPT-4o, or a local GPT-OSS model via Llama.cpp) for structural generation and deterministic value mapping, guided by a formal metamodel.

For a detailed understanding of the methodology, evaluation, and experimental results, please refer to the main research paper.

## 2. Repository Contents

* `run_pipeline.py`: The core script that runs **Stage 1** (skeleton generation, no numerical values) and **Stage 2** (.seirmodel generation) for all three LLM backends.
* `run_stage3.py`: Runs **Stage 3** — generates Python simulation scripts from the ODE equations in `ode.json`.
* `llm_backends.py`: Shared module with the ChatGPT / Gemini / GPT-OSS (Llama.cpp) backends and output helpers.
* `archive/`: Previous single-file scripts (`runGPT.py`, `runChatGPT.py`, `runGemini.py`) and the `diagrams/` folder (paper diagrams) kept for reference.
* `metamodel.json` / `compartmental_metamodel.json`: Define the strict XML structure and validation rules for the SEIR models.
* `prompt_sample/`: Stores the output XML/skeleton/`.seirmodel` files and the full LLM interaction logs.
* `requirements.txt`: Lists all Python dependencies required to run the project.

## 3. Setup and Installation

To get started with the project, follow these steps:

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/YourAnonymousRepo/EpiMDE-LLM-Project.git
    cd EpiMDE-LLM-Project
    ```
    *(Note: Replace `YourAnonymousRepo/EpiMDE-LLM-Project` with the actual anonymous repository URL)*

2.  **Install Python Dependencies:**
    It is highly recommended to use a virtual environment.
    ```bash
    python3 -m venv venv
    source venv/bin/activate
    pip install -r requirements.txt
    ```
    The `requirements.txt` file is expected to contain:
    ```
    google-generativeai
    openai
    python-dotenv
    matplotlib
    pandas
    ```

3.  **Set Up API Keys:**
    * Create a file named `.env` in the root directory of your cloned repository (the same directory as the pipeline scripts).
    * For Gemini, add your key from [Google AI Studio](https://aistudio.google.com/app/apikey):
        ```
        GEMINI_API_KEY="your_google_api_key_here"
        ```
    * For ChatGPT, add your key from the [OpenAI platform](https://platform.openai.com/api-keys):
        ```
        OPENAI_API_KEY="your_openai_api_key_here"
        ```
    * For the local GPT-OSS backend (Llama.cpp), edit `LLAMA_CPP_PATH` and `MODEL_PATH` at the top of `llm_backends.py` — no API key is needed.
    * **Important:** Do not commit your `.env` file to the repository. It's already in `.gitignore`.

## 4. How to Run

Both scripts select the LLM backend with `--provider {gemini,chatgpt,gptoss}` (default: `gemini`).

### Stage 1 & 2: Generate the SEIR model (skeleton + .seirmodel)

```bash
# Run Stage 1 and Stage 2 for all models with Gemini
python run_pipeline.py --provider gemini

# Run for specific models
python run_pipeline.py --provider gemini --model hiv covid ebola

# Run a single stage
python run_pipeline.py --provider chatgpt --model hiv --stage 1   # skeleton only
python run_pipeline.py --provider chatgpt --model hiv --stage 2   # .seirmodel from existing skeleton
```

Outputs (per provider: `Gemini_prompt_sample/`, `chatgpt_prompt_sample/`, or `prompt_sample/` for GPT-OSS):
- `{model}_skeleton.xml` — Stage 1 structural model (`[[rate_missing]]` placeholders).
- `{model}_stage1_log.txt` — full Stage 1 prompt/response log.
- `{model}.seirmodel` — Stage 2 final model with values mapped from the user input.
- `{model}_stage2_log.txt` — full Stage 2 prompt/response log.

### Stage 3: Generate simulation scripts from ODE equations

```bash
python run_stage3.py --provider gemini                     # all models
python run_stage3.py --provider chatgpt --model hiv covid  # specific models
```

Outputs in `simulation_scripts/`:
- `{model}_simulation.py` — executable Python simulation script.
- `{model}_stage3a_partial.py` — Stage 3A partial output (setup sections).
- `{model}_stage3_log.txt` — full Stage 3 prompt/response log.

## 5. Anonymity Note for Reviewers

This repository has been anonymized for peer review. No identifying information about the authors or institutions is included. Please refer to the main research paper for full author details.

---