import argparse
import os
import time

import llm_backends
from llm_backends import extract_code_block, load_json_file

# --- Configuration ---
BREAK_TIME = 10  # Seconds break between each execution

PROMPTS_FILE = "prompts.json"
ODE_FILE = "ode.json"
SIMULATION_SKELETON_FILE = "simulation_skeleton.txt"

# Model name (CLI arg) -> (key in ode.json, output Python filename)
ODE_MODELS = {
    "hiv": ("hivModel", "hiv_simulation.py"),
    "covid": ("covidModel", "covid_simulation.py"),
    "sir": ("simpleModel", "simple_simulation.py"),
    "malaria": ("malariaModel", "malaria_simulation.py"),
}

SEPARATOR = "\n" + "*" * 80 + "\n"

# --- Load prompts ---
prompts = load_json_file(PROMPTS_FILE)
LLM3A_PROMPT = prompts["smart_LLM3A_PROMPT"]
LLM3B_PROMPT = prompts["smart_LLM3B_PROMPT"]

ode = load_json_file(ODE_FILE)

# Extract python code from simulation_skeleton.txt
SIMULATION_SKELETON = ""
try:
    with open(SIMULATION_SKELETON_FILE, "r", encoding="utf-8") as f:
        SIMULATION_SKELETON = f.read()
except FileNotFoundError:
    print(f"Error: {SIMULATION_SKELETON_FILE} not found.")
except Exception as e:
    print(f"An error occurred: {e}")


def stage3_generate_simulation(
    ode_equations: str,
    model_name: str,
    provider: str,
    outdir: str = "simulation_scripts",
    max_tokens: int = None,
):
    """
    Stage 3: Generate an executable Python simulation script from ODE equations.

    Stage 3A fills the setup sections of the simulation skeleton (model name,
    initial conditions, history arrays). Stage 3B fills the logic sections
    (ODE equations, state updates, history recording, plot lines).

    Args:
        ode_equations: ODE equations and initial populations for the model.
        model_name: Short model identifier used for output filenames (e.g. "hiv").
        provider: LLM backend to use.
        outdir: Directory where the simulation script and log are written.
        max_tokens: Optional token override (used by the gptoss backend).

    Returns:
        (simulation_script, script_path) on success, or (None, error_message).
    """
    # --- Stage 3A: Fill the setup sections ---
    stage3a_input = (
        f"{SEPARATOR}"
        f"PROMPT:\n{LLM3A_PROMPT.strip()}\n"
        f"{SEPARATOR}"
        f"ODE_EQUATIONS:\n{ode_equations.strip()}\n"
        f"{SEPARATOR}"
        f"Simulation python skeleton file:\n{SIMULATION_SKELETON.strip()}"
    )

    print("Stage 3A: Filling simulation setup sections...")
    partial_script = llm_backends.call_llm(stage3a_input, provider, max_tokens)

    if partial_script.startswith("ERROR:"):
        return None, partial_script

    print("Stage 3A simulation script generated successfully.")

    # --- Stage 3B: Fill the simulation logic sections ---
    stage3b_input = (
        f"{SEPARATOR}"
        f"PROMPT:\n{LLM3B_PROMPT.strip()}\n"
        f"{SEPARATOR}"
        f"ODE_EQUATIONS:\n{ode_equations.strip()}\n"
        f"{SEPARATOR}"
        f"Simulation python partially build file:\n{partial_script.strip()}"
    )

    print("Stage 3B: Filling simulation logic sections...")
    final_script = llm_backends.call_llm(stage3b_input, provider, max_tokens)

    if final_script.startswith("ERROR:"):
        return None, final_script

    print("Stage 3B simulation script generated successfully.")

    simulation_script = extract_code_block(final_script)

    os.makedirs(outdir, exist_ok=True)
    script_path = os.path.join(outdir, f"{model_name}_simulation.py")
    partial_path = os.path.join(outdir, f"{model_name}_stage3a_partial.py")
    log_path = os.path.join(outdir, f"{model_name}_stage3_log.txt")

    with open(script_path, "w", encoding="utf-8") as f:
        f.write(simulation_script)

    with open(partial_path, "w", encoding="utf-8") as f:
        f.write(partial_script)

    with open(log_path, "w", encoding="utf-8") as f:
        f.write(
            f"LLM3A PROMPT:\n{LLM3A_PROMPT.strip()}"
            f"{SEPARATOR}"
            f"ODE_EQUATIONS:\n{ode_equations.strip()}"
            f"{SEPARATOR}"
            f"Simulation python skeleton file:\n{SIMULATION_SKELETON.strip()}"
            f"{SEPARATOR}"
            f"LLM3A RESPONSE:\n{partial_script}"
            f"{SEPARATOR}"
            f"{SEPARATOR}"
            f"LLM3B PROMPT:\n{LLM3B_PROMPT.strip()}"
            f"{SEPARATOR}"
            f"ODE_EQUATIONS:\n{ode_equations.strip()}"
            f"{SEPARATOR}"
            f"Simulation python partially build file:\n{partial_script.strip()}"
            f"{SEPARATOR}"
            f"LLM3B RESPONSE:\n{final_script}"
        )

    print(f"Simulation script successfully written to {script_path}")
    print(f"Stage 3 log written to {log_path}")
    return simulation_script, script_path


def main():
    """Main execution function."""
    parser = argparse.ArgumentParser(
        description="Run Stage 3 of the pipeline: generate Python simulation scripts from ODE equations."
    )
    parser.add_argument(
        "--provider",
        choices=llm_backends.VALID_PROVIDERS,
        default="gemini",
        help="LLM backend to use (default: gemini).",
    )
    parser.add_argument(
        "--model",
        nargs="*",
        default=["all"],
        help="Epidemiological models to simulate. Choices: "
             + ", ".join(list(ODE_MODELS.keys()) + ["all"]) + " (default: all).",
    )
    parser.add_argument(
        "--outdir",
        default="simulation_scripts",
        help="Output directory (default: simulation_scripts).",
    )
    parser.add_argument(
        "--max-tokens",
        type=int,
        default=None,
        help="Optional max token override (used by the gptoss backend).",
    )
    args = parser.parse_args()

    llm_backends.configure_provider(args.provider)

    selected = args.model
    if "all" in selected:
        selected = list(ODE_MODELS.keys())

    print("\n" + "=" * 80)
    print(f"Using provider: {args.provider.upper()}")
    print(f"Output directory: {args.outdir}")
    print("=" * 80)

    for name in selected:
        if name not in ODE_MODELS:
            print(f"Skipping unknown model '{name}'.")
            continue

        ode_key, script_filename = ODE_MODELS[name]
        ode_equations = ode[ode_key]

        print("\n" + "=" * 80)
        print(f"Generating {name.upper()} simulation...")
        print("=" * 80)

        script, result = stage3_generate_simulation(
            ode_equations, name, args.provider, args.outdir, args.max_tokens
        )
        if script is None:
            print(f"Stage 3 failed for {name}: {result}")

        if name != selected[-1]:
            time.sleep(BREAK_TIME)

    print("\n" + "=" * 80)
    print("All tasks completed!")
    print("=" * 80)


if __name__ == "__main__":
    main()