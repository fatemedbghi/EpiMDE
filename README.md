# EpiMDE: A Model-Driven Engineering Platform for Reusable and Reproducible Epidemiological Models

EpiMDE is a model-driven engineering (MDE) platform for building, reusing, and reproducing compartmental epidemiological models. Model structure (compartments, flows, parameters, stratification) is captured in a formal metamodel, then used to generate diagrams, equations, and simulations.

The repository has two parts:

- **Platform** — an Eclipse/EMF + Sirius workspace for editing `.compmodel` files, generating ODEs, and exploring example disease models.
- **Pipeline** — a three-stage LLM pipeline that turns structured textual specifications into SEIR-style models and Python simulation scripts.

## Repository layout

```text
EpiMDE/
├── Platform/     # Eclipse metamodel, editors, example .compmodel files
└── Pipeline/     # LLM pipeline (Stages 1–3), prompts, metamodels, samples
```

## Platform

The Platform folder is the Eclipse modeling workspace. A `CompartmentalModel` (compartments, rate/contact flows, groups, products, parameters) drives diagrams, equation generation, and optional numerical simulation.

**Features**

- Graphical (Sirius) and tree (EMF) editors for `.compmodel` files
- Symbolic parameters (`CONSTANT`, `VARIABLE`, `EXPRESSION`)
- Population stratification (`Group`, `Product`, stratum-specific rates)
- ODE-style equation generation from a model file
- Example models: COVID-19, HIV, Ebola, malaria, Zika, tuberculosis, influenza, and others

**Requirements**

- Eclipse Modeling Tools
- EMF and Sirius
- Java 17+ (or the JDK expected by your Eclipse release)

Import the projects under `Platform/` into Eclipse (`CompartmentalModel`, `.edit`, `.editor`, `.design`, `.tests`, and the target platform). See [`Platform/README.md`](Platform/README.md) for metamodel details, editor workflows, and equation generation.

## Pipeline

The Pipeline folder implements a three-stage LLM workflow guided by a formal metamodel:

1. **Stage 1 — structure.** Generate a skeleton XML model. Rates are placeholders (`[[rate_missing]]`); no numbers are invented.
2. **Stage 2 — values.** Map numbers from the user input into a `.seirmodel` file. The LLM does not calculate; it only copies provided values so the same specification always yields the same model.
3. **Stage 3 — simulation.** Generate an executable Python script from the ODE equations in `ode.json`.

Supported backends: Gemini, ChatGPT (GPT-4o), and a local GPT-OSS model via llama.cpp.

### Setup

```bash
cd Pipeline
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt
```

Create a `.env` file in `Pipeline/`:

```
GEMINI_API_KEY="your_google_api_key"
OPENAI_API_KEY="your_openai_api_key"
```

For the local GPT-OSS backend, set `LLAMA_CPP_PATH` and `MODEL_PATH` at the top of `llm_backends.py`. No API key is required.

### Run

```bash
# Stages 1 and 2 (skeleton + .seirmodel)
python run_pipeline.py --provider gemini
python run_pipeline.py --provider chatgpt --model hiv covid ebola
python run_pipeline.py --provider gemini --model hiv --stage 1

# Stage 3 (Python simulation scripts)
python run_stage3.py --provider gemini
python run_stage3.py --provider chatgpt --model hiv covid
```

`--provider` can be `gemini`, `chatgpt`, or `gptoss`. `--model` can be `hiv`, `covid`, `sir`, `malaria`, `ebola`, or omitted to run all available models.

Full pipeline usage, outputs, and llama.cpp notes are in [`Pipeline/readme.md`](Pipeline/readme.md) and [`Pipeline/readTHIS.md`](Pipeline/readTHIS.md).

## Reproducibility

The pipeline separates structure from numbers. Stage 1 never fills rates; Stage 2 only copies values that already appear in the input. Mathematical evaluation happens before the LLM runs, so the same textual specification produces the same model.

## License

Add a license file if you want to specify reuse terms.
