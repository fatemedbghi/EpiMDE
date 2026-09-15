# SEIR Model Generator with Llama.cpp

Automated generation of epidemiological SEIR models in XML format and Python simulations using local LLM (GPT-OSS 20B) via Llama.cpp.

## Prerequisites

### 1. Download Llama.cpp
- Download from: https://github.com/ggerganov/llama.cpp
- Extract to your preferred location
- Note the path to the `main` executable (or `main.exe` on Windows)

### 2. Download GPT-OSS 20B Model
- Download the GGUF model file for GPT-OSS 20B
- Place it in a `models` folder or your preferred location
- Note the full path to the `.gguf` file

## Configuration

### Update Paths in `llm_backends.py`

Open `llm_backends.py` and modify these lines at the top:

```python
LLAMA_CPP_PATH = "./llama.cpp/main"              # Update with your llama.cpp path
MODEL_PATH = "./models/gpt-oss-20b.gguf"         # Update with your model path
```

**Examples:**
- Windows: `LLAMA_CPP_PATH = "C:/llama.cpp/main.exe"`
- Linux/Mac: `LLAMA_CPP_PATH = "/home/user/llama.cpp/main"`

### Optional: Adjust Generation Parameters

If you need to change token limits or sampling parameters:

```python
GENERATION_PARAMS = {
    "n_predict": 4096,      # Maximum tokens to generate (increase for larger models)
    "temp": 0.7,            # Temperature (0.1-1.0, lower = more deterministic)
    "top_k": 40,            # Top-k sampling
    "top_p": 0.9,           # Top-p sampling
    "repeat_penalty": 1.1,  # Repetition penalty
    "ctx_size": 8192,       # Context size (must not exceed model's limit)
}
```

**Tip:** If GPU is struggling, increase `BREAK_TIME` to give it more rest between generations.

## Required Files

Ensure these files exist in your project directory:
- `prompts.json` - Contains LLM prompts
- `metamodel.json` - SEIR model metamodel specification
- `models.json` - Model input definitions
- `ode.json` - ODE equations for simulation (if running simulations)
- `simulation_skeleton.txt` - Python simulation template

## Usage

### Run the Generator

```bash
# Stage 1 & 2: SEIR model generation (skeleton + .seirmodel) with the local GPT-OSS model
python run_pipeline.py --provider gptoss --model all

# Stage 3: Simulation scripts from ODE equations
python run_stage3.py --provider gptoss --model all
```

The scripts will:
1. Generate the SEIR XML skeleton (Stage 1) and the final `.seirmodel` (Stage 2) for each model
2. Generate Python simulation scripts (Stage 3A & 3B)
3. Save outputs to the provider's `prompt_sample/` folder and `simulation_scripts/`

### Enable/Disable Models

Use the `--model` argument to select which models to run (`hiv`, `covid`, `sir`, `malaria`, `ebola`, or `all`):

```bash
# Only HIV
python run_pipeline.py --provider gptoss --model hiv

# Only HIV + COVID simulations
python run_stage3.py --provider gptoss --model hiv covid
```

## Output

- **SEIR skeletons:** `prompt_sample/{model}_skeleton.xml`
- **SEIR models:** `prompt_sample/{model}.seirmodel`
- **Simulations:** `simulation_scripts/{model}_simulation.py`
- **Graphs:** `simulation_{model}.png` (generated when simulation runs)

## Troubleshooting

### "ERROR: 'llama.cpp/main' not found"
**Solution:** Update `LLAMA_CPP_PATH` with the correct path to your llama.cpp executable.

### "ERROR: Model file not found"
**Solution:** Update `MODEL_PATH` with the correct path to your `.gguf` model file.

### "Generation timed out after 5 minutes"
**Causes:**
- Model is too slow on your hardware
- Prompt is too large for the context window
- GPU/CPU is overloaded

**Solutions:**
- Increase timeout in `call_llama_cpp()` function
- Reduce `ctx_size` in `GENERATION_PARAMS`
- Increase `BREAK_TIME` between generations
- Use a smaller model or faster hardware

### "Invalid JSON" errors
**Causes:**
- `prompts.json`, `models.json`, or `ode.json` has syntax errors
- Files are missing or corrupted

**Solutions:**
- Validate JSON files using an online validator
- Check for missing commas, brackets, or quotes
- Ensure files use UTF-8 encoding

### LLM generates code with markdown blocks (```python)
**Cause:** LLM not following instructions to output raw code

**Solutions:**
- Prompts already instruct against this, but some models ignore it
- Post-process output to strip markdown blocks:
  ```python
  output = output.replace("```python", "").replace("```", "")
  ```

### GPU out of memory
**Solutions:**
- Reduce `ctx_size` in `GENERATION_PARAMS`
- Reduce `n_predict` (max tokens)
- Increase `BREAK_TIME` to let GPU cool down
- Use CPU-only mode in llama.cpp (slower but uses RAM instead)

### Simulation script has syntax errors
**Causes:**
- LLM made mistakes in variable naming or equation conversion
- Compartment names have special characters

**Solutions:**
- Check `simulation_scripts/*.py` for obvious errors
- Run `python -m py_compile simulation_scripts/hiv_simulation.py` to validate syntax
- Manually fix variable names if needed
- Adjust prompts in `prompts.json` for stricter output

### "FileNotFoundError: simulation_skeleton.txt"
**Solution:** Ensure `simulation_skeleton.txt` (or `.py`) exists in the project root directory.

## Performance Notes

- **First run:** Llama.cpp loads the model into memory (takes time)
- **Subsequent calls:** Much faster as model stays loaded
- **Break time:** Default 10 seconds prevents GPU overheating
- **Memory usage:** 20B model requires ~12-16GB RAM/VRAM

## Tips

1. **Start small:** Test with one model first (HIV is enabled by default)
2. **Check outputs:** Validate XML structure before running simulations
3. **Monitor resources:** Watch GPU/CPU usage during generation
4. **Adjust parameters:** If quality is poor, try lowering temperature or adjusting top_k/top_p
5. **Save prompts:** If you modify prompts, keep backups of what works

## Support

If issues persist:
1. Check llama.cpp documentation: https://github.com/ggerganov/llama.cpp
2. Verify your GGUF model is compatible with your llama.cpp version
3. Test llama.cpp directly from command line to rule out Python issues
4. Check that all JSON files are valid and complete

---

**Note:** This tool uses local LLMs. Output quality depends on your model choice and hardware capabilities. Results may vary from run to run due to the probabilistic nature of LLMs.
