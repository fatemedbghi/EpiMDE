import os
import time
from dotenv import load_dotenv
import json
import google.generativeai as genai

# --- Configuration ---
BREAK_TIME = 10  # 10 seconds break between each execution

# --- Load configuration files ---
def load_json_file(filename: str) -> dict:
    """Load and return JSON file contents."""
    try:
        with open(filename, "r", encoding="utf-8") as f:
            return json.load(f)
    except FileNotFoundError:
        print(f"ERROR: '{filename}' not found.")
        raise
    except json.JSONDecodeError:
        print(f"ERROR: '{filename}' is not valid JSON.")
        raise


# Load prompts and models
prompts = load_json_file("prompts.json")
LLM1_PROMPT = prompts["smart_LLM1_PROMPT"]
LLM2_PROMPT = prompts["smart_LLM2_PROMPT"]
LLM3A_PROMPT = prompts["smart_LLM3A_PROMPT"]
LLM3B_PROMPT = prompts["smart_LLM3B_PROMPT"]

METAMODEL_FILENAME = "compartmental_metamodel.json"
SIMULATION_SKELETON_FILE = "simulation_skeleton.txt"

models = load_json_file("models.json")
hivModel = models["smartHIVInput"]
covidModel = models["smartCovidInput"]
simpleModel = models["smartSIRInput"]
malariaModel = models["smartMalariaInput"]
ebolaModel = models["smartEbolaInput"]

ode = load_json_file("ode.json")
hiv_ode = ode["hivModel"]
covid_ode = ode["covidModel"]
simple_ode = ode["simpleModel"]
malaria_ode = ode["malariaModel"]

# Extracting python code from simulation_skeleton.txt file
SIMULATION_SKELETON = ""
try:
    with open(SIMULATION_SKELETON_FILE, 'r', encoding='utf-8') as f:
        SIMULATION_SKELETON = f.read()
except FileNotFoundError:
    print(f"Error: {SIMULATION_SKELETON_FILE} not found.")
except Exception as e:
    print(f"An error occurred: {e}")


# --- Initialize Gemini API ---
try:
    env_path = os.path.join(os.path.dirname(__file__), ".env")
    load_dotenv(env_path)
    genai.configure(api_key=os.environ["GEMINI_API_KEY"])
except KeyError:
    print("FATAL ERROR: 'GEMINI_API_KEY' environment variable not set.")
    print("Please set it before running the script.")
    exit(1)

# Use the Gemini model
model = genai.GenerativeModel('gemini-2.5-pro')


def call_gemini(prompt: str) -> str:
    """
    Call Gemini API with the given prompt and return the generated text.
    
    Args:
        prompt: The input prompt
        
    Returns:
        str: Generated text from the model
    """
    try:
        print(f"Calling Gemini API with prompt length: {len(prompt)} characters")
        
        response = model.generate_content(prompt.strip())
        output = response.text.strip()
        
        return output
        
    except Exception as e:
        return f"ERROR: {str(e)}"


def generate_seirmodel(
    user_input: str, 
    output_fileName: str
) -> str:
    """
    Generates a SEIR model in XML format using a two-stage LLM process.
    
    Args:
        user_input: Text containing tabular data (compartments, flows, variables).
        output_fileName: The desired name for the output file (e.g., "hiv_model.txt").

    Returns:
        str: A success message with the output file path, or an error message.
    """
    try:
        # Load metamodel specifications
        with open(METAMODEL_FILENAME, "r", encoding="utf-8") as f:
            lang_specs_json = json.load(f)
        lang_specs = json.dumps(lang_specs_json, indent=2)
        
        print(f"'{METAMODEL_FILENAME}' loaded successfully.")

    except FileNotFoundError as err:
        error_msg = f"Error: File not found - {err}"
        print(error_msg)
        return error_msg
    except Exception as err:
        error_msg = f"Unexpected error reading files: {err}"
        print(error_msg)
        return error_msg

    # --- Stage 1: Structural generation ---
    separator = "\n" + "*" * 80 + "\n"
    
    llm1_input = (
        f"{separator}"
        f"PROMPT: \n{LLM1_PROMPT.strip()}\n"
        f"{separator}"
        f"METAMODEL: \n{lang_specs.strip()}\n"
        f"{separator}"
        f"USER_INPUT: \n{user_input.strip()}\n"
        f"{separator}"
        f"Generate the SEIR model in XML format based on the above information:"
    )

    print("Generating LLM1 response...")
    llm1 = call_gemini(llm1_input)
    
    if llm1.startswith("ERROR:"):
        return llm1
    
    print("LLM1 response generated successfully.")

    # --- Stage 2: Refinement ---
    llm2_input = (
        f"{separator}"
        f"PROMPT:\n{LLM2_PROMPT.strip()}\n"
        f"{separator}"
        f"USER INPUT:\n{user_input.strip()}\n"
        f"{separator}"
        f"STRUCTURALLY CORRECT SEIRMODEL FILE:\n{llm1.strip()}\n"
        f"{separator}"
    )

    print("Generating LLM2 response...")
    llm2 = call_gemini(llm2_input)
    
    if llm2.startswith("ERROR:"):
        return llm2
    
    print("LLM2 response generated successfully.")
    
    # --- Format and save the output ---
    output_content = (
        f"LLM1 PROMPT:\n{LLM1_PROMPT.strip()}"
        f"{separator}"
        f"METAMODEL:\n{lang_specs.strip()}"
        f"{separator}"
        f"User Input:\n{user_input.strip()}"
        f"{separator}"
        f"LLM1 RESPONSE:\n{llm1}"
        f"{separator}"
        f"{separator}"
        f"LLM2 PROMPT:\n{LLM2_PROMPT.strip()}"
        f"{separator}"
        f"USER INPUT:\n{user_input}"
        f"{separator}"
        f"LLM1'S RESPONSE:\n{llm1}"
        f"{separator}"
        f"LLM2'S RESPONSE:\n{llm2}"
    )

    try:
        # Ensure the output directory exists
        output_dir = "ChatGPT_prompt_sample"
        os.makedirs(output_dir, exist_ok=True)
        output_file = os.path.join(output_dir, output_fileName)
        
        with open(output_file, "w", encoding="utf-8") as tf:
            tf.write(output_content)
        
        success_msg = f"SEIR model successfully written to {output_file}"
        print(success_msg)
        return success_msg
        
    except IOError as e:
        error_msg = f"Error writing to output file '{output_file}': {e}"
        print(error_msg)
        return error_msg


def simulate(ode_equations: str, output_fileName: str) -> str:
    """
    Generates a Python simulation script for an ODE model using a two-stage process.

    Args:
        ode_equations: ODE equations 
        output_fileName: The desired name for the output Python file (e.g., "simulation.py").

    Returns:
        str: A success message with the output file path, or an error message.
    """
    
    # --- Stage 3A: Generate simulation script ---
    separator = "\n" + "*" * 80 + "\n"
    
    simulation_stage3a = (
        f"{separator}"
        f"PROMPT:\n{LLM3A_PROMPT.strip()}\n"
        f"{separator}"
        f"ODE_EQUATIONS:\n{ode_equations.strip()}\n"
        f"{separator}"
        f"Simulation python skeleton file:\n{SIMULATION_SKELETON.strip()}"
    )

    print("Generating simulation stage3a script...")
    simulation_script_3a = call_gemini(simulation_stage3a)
    
    if simulation_script_3a.startswith("ERROR:"):
        return simulation_script_3a
    
    print("Stage 3A simulation script generated successfully.")
    
    # --- Stage 3B: Refine simulation script ---
    simulation_stage3b = (
        f"{separator}"
        f"PROMPT:\n{LLM3B_PROMPT.strip()}\n"
        f"{separator}"
        f"ODE_EQUATIONS:\n{ode_equations.strip()}\n"
        f"{separator}"
        f"Simulation python partially build file:\n{simulation_script_3a.strip()}"
    )

    print("Generating final simulation stage3b script...")
    simulation_script = call_gemini(simulation_stage3b)
    
    if simulation_script.startswith("ERROR:"):
        return simulation_script
    
    print("Stage 3B simulation script generated successfully.")
    
    # --- Save the output ---
    try:
        output_dir = "simulation_scripts"
        os.makedirs(output_dir, exist_ok=True)
        output_file = os.path.join(output_dir, output_fileName)
        
        with open(output_file, "w", encoding="utf-8") as tf:
            tf.write(simulation_script)
        
        success_msg = f"Simulation script successfully written to {output_file}"
        print(success_msg)
        return success_msg
        
    except IOError as e:
        error_msg = f"Error writing to output file '{output_file}': {e}"
        print(error_msg)
        return error_msg


def main():
    """Main execution function."""
    
    print("\n" + "="*80)
    print("Using Gemini API")
    print("="*80)
    
    # Generate SEIR models
    # print("\n" + "="*80)
    # print("Generating HIV Model...")
    # print("="*80)
    # generate_seirmodel(hivModel, "finalHivModel.txt")
    
    # time.sleep(BREAK_TIME)

    # print("\n" + "="*80)
    # print("Generating COVID Model...")
    # print("="*80)
    # generate_seirmodel(covidModel, "finalCovidModel.txt")

    print("\n" + "="*80)
    print("Generating Ebola Model...")
    print("="*80)
    generate_seirmodel(ebolaModel, "finalEbolaModel.txt")
    
    # time.sleep(BREAK_TIME)

    # print("\n" + "="*80)
    # print("Generating Simple SIR Model...")
    # print("="*80)
    # generate_seirmodel(simpleModel, "finalSimpleModel.txt")

    # time.sleep(BREAK_TIME)

    # print("\n" + "="*80)
    # print("Generating Malaria Model...")
    # print("="*80)
    # generate_seirmodel(malariaModel, "finalMalariaModel.txt")
    
    # NOTE: Only run if the ODE equations are ready in the ode.json
    # time.sleep(BREAK_TIME)
    # print("\n" + "="*80)
    # print("Generating HIV Simulation...")
    # print("="*80)
    # simulate(hiv_ode, "hiv_simulation.py")
    
    # time.sleep(BREAK_TIME)
    # print("\n" + "="*80)
    # print("Generating COVID Simulation...")
    # print("="*80)
    # simulate(covid_ode, "covid_simulation.py")
    
    # time.sleep(BREAK_TIME)
    # simulate(simple_ode, "simple_simulation.py")
    # time.sleep(BREAK_TIME)
    # simulate(malaria_ode, "malaria_simulation.py")

    print("\n" + "="*80)
    print("All tasks completed!")
    print("="*80)


if __name__ == "__main__":
    main()
