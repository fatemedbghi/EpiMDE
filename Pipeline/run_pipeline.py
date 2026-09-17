import argparse
import json
import os
import time

import llm_backends
from llm_backends import extract_code_block, load_json_file

# --- Configuration ---
BREAK_TIME = 10  # Seconds break between each execution

PROMPTS_FILE = "prompts.json"
MODELS_FILE = "models.json"
DEFAULT_METAMODEL_FILENAME = "compartmental_metamodel.json"

# Model name (CLI arg) -> key in models.json for the user input
MODELS = {
    "hiv": "smartHIVInput",
    "covid": "smartCovidInput",
    "sir": "smartSIRInput",
    "malaria": "smartMalariaInput",
    "ebola": "smartEbolaInput",
}

# Default output directory per provider
DEFAULT_OUTDIRS = {
    "gptoss": "prompt_sample",
    "gemini": "Gemini_prompt_sample",
    "chatgpt": "chatgpt_prompt_sample",
}

SEPARATOR = "\n" + "*" * 80 + "\n"

# --- Load prompts ---
prompts = load_json_file(PROMPTS_FILE)
LLM1_PROMPT = prompts["smart_LLM1_PROMPT"]
LLM2_PROMPT = prompts["smart_LLM2_PROMPT"]

models = load_json_file(MODELS_FILE)


def _load_lang_specs(metamodel_filename: str) -> str:
    with open(metamodel_filename, "r", encoding="utf-8") as f:
        lang_specs_json = json.load(f)
    print(f"'{metamodel_filename}' loaded successfully.")
    return json.dumps(lang_specs_json, indent=2)


def stage1_generate_skeleton(
    user_input: str,
    model_name: str,
    provider: str,
    metamodel_filename: str = DEFAULT_METAMODEL_FILENAME,
    outdir: str = "prompt_sample",
    max_tokens: int = None,
):
    """
    Stage 1: Generate the structural SEIR skeleton (all rates left as [[rate_missing]]).

    Args:
        user_input: Text containing tabular data (compartments, flows, variables).
        model_name: Short model identifier used for output filenames (e.g. "hiv").
        provider: LLM backend to use.
        metamodel_filename: Metamodel JSON used to constrain the output structure.
        outdir: Directory where the skeleton and log are written.
        max_tokens: Optional token override (used by the gptoss backend).

    Returns:
        (skeleton_xml, skeleton_path) on success, or (None, error_message).
    """
    lang_specs = _load_lang_specs(metamodel_filename)

    llm1_input = (
        f"{SEPARATOR}"
        f"PROMPT: \n{LLM1_PROMPT.strip()}\n"
        f"{SEPARATOR}"
        f"METAMODEL: \n{lang_specs.strip()}\n"
        f"{SEPARATOR}"
        f"USER_INPUT: \n{user_input.strip()}\n"
        f"{SEPARATOR}"
        f"Generate the SEIR model in XML format based on the above information:"
    )

    print("Stage 1: Generating structural skeleton (LLM1)...")
    llm1 = llm_backends.call_llm(llm1_input, provider, max_tokens)

    if llm1.startswith("ERROR:"):
        return None, llm1

    print("Stage 1 response generated successfully.")
    skeleton = extract_code_block(llm1)

    os.makedirs(outdir, exist_ok=True)
    base = os.path.join(outdir, model_name)
    skeleton_path = f"{base}_skeleton.xml"
    log_path = f"{base}_stage1_log.txt"

    with open(skeleton_path, "w", encoding="utf-8") as f:
        f.write(skeleton)

    with open(log_path, "w", encoding="utf-8") as f:
        f.write(
            f"LLM1 PROMPT:\n{LLM1_PROMPT.strip()}"
            f"{SEPARATOR}"
            f"METAMODEL:\n{lang_specs.strip()}"
            f"{SEPARATOR}"
            f"User Input:\n{user_input.strip()}"
            f"{SEPARATOR}"
            f"LLM1 RESPONSE:\n{llm1}"
        )

    print(f"Stage 1 skeleton written to {skeleton_path}")
    print(f"Stage 1 log written to {log_path}")
    return skeleton, skeleton_path


def stage2_generate_seirmodel(
    user_input: str,
    skeleton_xml: str,
    model_name: str,
    provider: str,
    outdir: str = "prompt_sample",
    max_tokens: int = None,
):
    """
    Stage 2: Fill the rate/parameter placeholders to produce the final .seirmodel file.

    Args:
        user_input: Text containing tabular data (compartments, flows, variables).
        skeleton_xml: Structurally correct skeleton produced by Stage 1.
        model_name: Short model identifier used for output filenames (e.g. "hiv").
        provider: LLM backend to use.
        outdir: Directory where the .seirmodel and log are written.
        max_tokens: Optional token override (used by the gptoss backend).

    Returns:
        (seirmodel_xml, seirmodel_path) on success, or (None, error_message).
    """
    llm2_input = (
        f"{SEPARATOR}"
        f"PROMPT:\n{LLM2_PROMPT.strip()}\n"
        f"{SEPARATOR}"
        f"USER INPUT:\n{user_input.strip()}\n"
        f"{SEPARATOR}"
        f"STRUCTURALLY CORRECT SEIRMODEL FILE:\n{skeleton_xml.strip()}\n"
        f"{SEPARATOR}"
    )

    print("Stage 2: Filling rates/parameters to produce .seirmodel (LLM2)...")
    llm2 = llm_backends.call_llm(llm2_input, provider, max_tokens)

    if llm2.startswith("ERROR:"):
        return None, llm2

    print("Stage 2 response generated successfully.")
    seirmodel = extract_code_block(llm2)

    os.makedirs(outdir, exist_ok=True)
    base = os.path.join(outdir, model_name)
    seirmodel_path = f"{base}.seirmodel"
    log_path = f"{base}_stage2_log.txt"

    with open(seirmodel_path, "w", encoding="utf-8") as f:
        f.write(seirmodel)

    with open(log_path, "w", encoding="utf-8") as f:
        f.write(
            f"LLM2 PROMPT:\n{LLM2_PROMPT.strip()}"
            f"{SEPARATOR}"
            f"USER INPUT:\n{user_input.strip()}"
            f"{SEPARATOR}"
            f"LLM1'S RESPONSE (SHELLETON):\n{skeleton_xml.strip()}"
            f"{SEPARATOR}"
            f"LLM2'S RESPONSE:\n{llm2}"
        )

    print(f"Stage 2 .seirmodel written to {seirmodel_path}")
    print(f"Stage 2 log written to {log_path}")
    return seirmodel, seirmodel_path


def generate_seirmodel(
    user_input: str,
    model_name: str,
    provider: str,
    metamodel_filename: str = DEFAULT_METAMODEL_FILENAME,
    outdir: str = "prompt_sample",
    max_tokens: int = None,
):
    """
    Run the full two-stage SEIR model generation (Stage 1 skeleton -> Stage 2 .seirmodel).
    """
    skeleton, err = stage1_generate_skeleton(
        user_input, model_name, provider, metamodel_filename, outdir, max_tokens
    )
    if skeleton is None:
        return None, err

    time.sleep(BREAK_TIME)

    seirmodel, err = stage2_generate_seirmodel(
        user_input, skeleton, model_name, provider, outdir, max_tokens
    )
    if seirmodel is None:
        return None, err

    return seirmodel, os.path.join(outdir, f"{model_name}.seirmodel")


def main():
    """Main execution function."""
    parser = argparse.ArgumentParser(
        description="Run the SEIR model generation pipeline (Stage 1 skeleton + Stage 2 .seirmodel)."
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
        help="Epidemiological models to generate. Choices: "
             + ", ".join(list(MODELS.keys()) + ["all"]) + " (default: all).",
    )
    parser.add_argument(
        "--stage",
        choices=["all", "1", "2"],
        default="all",
        help="Run only Stage 1 (skeleton), only Stage 2 (.seirmodel), or all (default: all).",
    )
    parser.add_argument(
        "--metamodel",
        default=DEFAULT_METAMODEL_FILENAME,
        help=f"Metamodel JSON file (default: {DEFAULT_METAMODEL_FILENAME}).",
    )
    parser.add_argument(
        "--outdir",
        default=None,
        help="Output directory (default depends on provider).",
    )
    parser.add_argument(
        "--max-tokens",
        type=int,
        default=None,
        help="Optional max token override (used by the gptoss backend).",
    )
    args = parser.parse_args()

    llm_backends.configure_provider(args.provider)
    if args.outdir is None:
        args.outdir = DEFAULT_OUTDIRS[args.provider]

    selected = args.model
    if "all" in selected:
        selected = list(MODELS.keys())

    print("\n" + "=" * 80)
    print(f"Using provider: {args.provider.upper()}")
    print(f"Output directory: {args.outdir}")
    print("=" * 80)

    for name in selected:
        if name not in MODELS:
            print(f"Skipping unknown model '{name}'.")
            continue

        user_input = models[MODELS[name]]
        print("\n" + "=" * 80)
        print(f"Generating {name.upper()} model...")
        print("=" * 80)

        if args.stage == "1":
            skeleton, result = stage1_generate_skeleton(
                user_input, name, args.provider, args.metamodel, args.outdir, args.max_tokens
            )
            if skeleton is None:
                print(f"Stage 1 failed for {name}: {result}")
        elif args.stage == "2":
            skeleton_path = os.path.join(args.outdir, f"{name}_skeleton.xml")
            if not os.path.exists(skeleton_path):
                print(f"Stage 2 failed for {name}: skeleton not found at {skeleton_path}. Run Stage 1 first.")
                continue
            with open(skeleton_path, "r", encoding="utf-8") as f:
                skeleton = f.read()
            seirmodel, result = stage2_generate_seirmodel(
                user_input, skeleton, name, args.provider, args.outdir, args.max_tokens
            )
            if seirmodel is None:
                print(f"Stage 2 failed for {name}: {result}")
        else:
            seirmodel, result = generate_seirmodel(
                user_input, name, args.provider, args.metamodel, args.outdir, args.max_tokens
            )
            if seirmodel is None:
                print(f"Generation failed for {name}: {result}")

        if name != selected[-1]:
            time.sleep(BREAK_TIME)

    print("\n" + "=" * 80)
    print("All tasks completed!")
    print("=" * 80)


if __name__ == "__main__":
    main()