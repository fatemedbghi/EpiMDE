import json
import os
import subprocess

VALID_PROVIDERS = ("gptoss", "gemini", "chatgpt")

# --- GPT-OSS / Llama.cpp configuration ---
LLAMA_CPP_PATH = "./llama.cpp/main"          # Path to llama.cpp executable
MODEL_PATH = "./models/gpt-oss-20b.gguf"      # Path to your GGUF model file
GENERATION_PARAMS = {
    "n_predict": 4096,      # Maximum tokens to generate
    "temp": 0.7,            # Temperature
    "top_k": 40,            # Top-k sampling
    "top_p": 0.9,           # Top-p sampling
    "repeat_penalty": 1.1,  # Repetition penalty
    "ctx_size": 8192,       # Context size
}

DEFAULT_CHATGPT_MODEL = "gpt-4o"
DEFAULT_GEMINI_MODEL = "gemini-2.5-pro"

GEMINI_MODEL = None
OPENAI_CLIENT = None


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


def _load_env():
    """Load the .env file from the repo root, if present."""
    try:
        from dotenv import load_dotenv
    except ImportError:
        return
    env_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), ".env")
    if os.path.exists(env_path):
        load_dotenv(env_path)


def configure_provider(provider: str) -> str:
    """
    Initialize the selected LLM backend.

    Args:
        provider: One of 'gptoss', 'gemini', 'chatgpt'.

    Returns:
        The normalized provider name.
    """
    global GEMINI_MODEL, OPENAI_CLIENT

    provider = provider.lower()
    if provider not in VALID_PROVIDERS:
        raise ValueError(f"Unknown provider '{provider}'. Choose from {', '.join(VALID_PROVIDERS)}.")

    if provider == "gemini":
        _load_env()
        api_key = os.environ.get("GEMINI_API_KEY")
        if not api_key:
            raise RuntimeError("FATAL ERROR: 'GEMINI_API_KEY' environment variable not set. Add it to .env or export it.")
        import google.generativeai as genai
        genai.configure(api_key=api_key)
        GEMINI_MODEL = genai.GenerativeModel(DEFAULT_GEMINI_MODEL)

    elif provider == "chatgpt":
        _load_env()
        api_key = os.environ.get("OPENAI_API_KEY")
        if not api_key:
            raise RuntimeError("FATAL ERROR: 'OPENAI_API_KEY' environment variable not set. Add it to .env or export it.")
        from openai import OpenAI
        OPENAI_CLIENT = OpenAI(api_key=api_key)

    return provider


def call_gemini(prompt: str) -> str:
    """Call the Gemini API with the given prompt and return the generated text."""
    if GEMINI_MODEL is None:
        raise RuntimeError("Provider 'gemini' is not configured. Call configure_provider('gemini') first.")
    print(f"Calling Gemini API with prompt length: {len(prompt)} characters")
    try:
        response = GEMINI_MODEL.generate_content(prompt.strip())
        return response.text.strip()
    except Exception as e:
        return f"ERROR: {str(e)}"


def call_chatgpt(prompt: str, model: str = DEFAULT_CHATGPT_MODEL) -> str:
    """Call the ChatGPT API with the given prompt and return the generated text."""
    if OPENAI_CLIENT is None:
        raise RuntimeError("Provider 'chatgpt' is not configured. Call configure_provider('chatgpt') first.")
    print(f"Calling ChatGPT API with prompt length: {len(prompt)} characters")
    try:
        completion = OPENAI_CLIENT.chat.completions.create(
            model=model,
            messages=[{"role": "user", "content": prompt.strip()}],
        )
        return completion.choices[0].message.content.strip()
    except Exception as e:
        return f"ERROR: {str(e)}"


def call_llama_cpp(prompt: str, max_tokens: int = None, timeout: int = 300) -> str:
    """
    Call Llama.cpp with the given prompt and return the generated text.

    Args:
        prompt: The input prompt.
        max_tokens: Override default max tokens if specified.
        timeout: Seconds to wait before timing out.

    Returns:
        Generated text from the model.
    """
    cmd = [
        LLAMA_CPP_PATH,
        "-m", MODEL_PATH,
        "-p", prompt,
        "-n", str(max_tokens or GENERATION_PARAMS["n_predict"]),
        "--temp", str(GENERATION_PARAMS["temp"]),
        "--top-k", str(GENERATION_PARAMS["top_k"]),
        "--top-p", str(GENERATION_PARAMS["top_p"]),
        "--repeat-penalty", str(GENERATION_PARAMS["repeat_penalty"]),
        "-c", str(GENERATION_PARAMS["ctx_size"]),
        "--log-disable",
    ]

    try:
        print(f"Calling Llama.cpp with prompt length: {len(prompt)} characters")
        result = subprocess.run(cmd, capture_output=True, text=True, timeout=timeout)

        if result.returncode != 0:
            return f"ERROR: Llama.cpp error: {result.stderr}"

        output = result.stdout.strip()

        # Remove the prompt from the output if it's echoed back
        if output.startswith(prompt):
            output = output[len(prompt):].strip()

        return output

    except subprocess.TimeoutExpired:
        return "ERROR: Generation timed out after 5 minutes"
    except Exception as e:
        return f"ERROR: {str(e)}"


def call_llm(prompt: str, provider: str, max_tokens: int = None) -> str:
    """Dispatch a prompt to the configured LLM backend."""
    if provider == "gemini":
        return call_gemini(prompt)
    if provider == "chatgpt":
        return call_chatgpt(prompt)
    if provider == "gptoss":
        return call_llama_cpp(prompt, max_tokens)
    raise ValueError(f"Unknown provider '{provider}'. Choose from {', '.join(VALID_PROVIDERS)}.")


def extract_code_block(text: str) -> str:
    """
    Strip markdown code fences (e.g. ```xml ... ``` / ```python ... ```) if present.
    Returns the raw text unchanged when no code fences are found.
    """
    text = text.strip()
    start = text.find("```")
    if start == -1:
        return text
    end = text.rfind("```")
    if end <= start:
        return text
    inner = text[start + 3:end]
    newline = inner.find("\n")
    if newline != -1:
        inner = inner[newline + 1:]
    return inner.strip()