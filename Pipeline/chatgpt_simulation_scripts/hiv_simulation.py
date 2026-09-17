```python
"""
SEIR Model Simulation Skeleton
This template provides the fixed structure for epidemiological simulations.
LLM will fill in model-specific sections marked with ### SECTION X ###
"""

import numpy as np
import matplotlib.pyplot as plt

# ============================================================================
# SIMULATION CONFIGURATION (Pre-written - Universal)
# ============================================================================
max_time = float(input("Enter simulation time (e.g., 10 for 10 years): "))
dt = 0.1
time_steps = int(max_time / dt)
time = np.arange(0, max_time, dt)

# ============================================================================
# ### SECTION 1: MODEL NAME ###
# LLM fills this with a descriptive model name for the output file
# Example: model_name = "HIV_Sexual_Behavior_Stratification"
# ============================================================================
model_name = "HIV_Sexual_Behavior_Stratified"

# ============================================================================
# ### SECTION 2: INITIAL CONDITIONS ###
# LLM fills this section with initial population values
# Parse compartment names from ODE equations and assign initial values
# Use valid Python variable names (replace spaces/special chars with underscores)
# Example:
#   Susceptible_Homosexual_Men = 2446
#   Infectious_Untreated_Homosexual_Men = 79
#   Susceptible_Women = 189994
# ============================================================================

HIV_Deaths_Homosexual_Men = 0  # Initial value not provided, assumed 0
Infectious_living_with_AIDS_Heterosexual_Men = 0  # Initial value not provided, assumed 0
Infectious_living_with_AIDS_Women = 0  # Initial value not provided, assumed 0
Infectious_Untreated_Infected_Women = 6
Infectious_living_with_AIDS_Homosexual_Men = 0  # Initial value not provided, assumed 0
Infectious_Untreated_Infected_Homosexual_Men = 79
Treated_with_ART_Homosexual_Men = 0  # Initial value not provided, assumed 0
HIV_Deaths_Women = 0  # Initial value not provided, assumed 0
Susceptible_Women = 189994
Infectious_Untreated_Infected_Heterosexual_Men = 29
Susceptible_Heterosexual_Men = 171173
Treated_with_ART_Women = 0  # Initial value not provided, assumed 0
Treated_with_ART_Heterosexual_Men = 0  # Initial value not provided, assumed 0
Susceptible_Homosexual_Men = 2446
HIV_Deaths_Heterosexual_Men = 0  # Initial value not provided, assumed 0

# ============================================================================
# ### SECTION 3: HISTORY ARRAYS ###
# LLM fills this section with history tracking lists
# Create one history list per compartment initialized with initial value
# Must match variable names from SECTION 2 exactly
# Example:
#   Susceptible_Homosexual_Men_history = [Susceptible_Homosexual_Men]
#   Infectious_Untreated_Homosexual_Men_history = [Infectious_Untreated_Homosexual_Men]
# ============================================================================

HIV_Deaths_Homosexual_Men_history = [HIV_Deaths_Homosexual_Men]
Infectious_living_with_AIDS_Heterosexual_Men_history = [Infectious_living_with_AIDS_Heterosexual_Men]
Infectious_living_with_AIDS_Women_history = [Infectious_living_with_AIDS_Women]
Infectious_Untreated_Infected_Women_history = [Infectious_Untreated_Infected_Women]
Infectious_living_with_AIDS_Homosexual_Men_history = [Infectious_living_with_AIDS_Homosexual_Men]
Infectious_Untreated_Infected_Homosexual_Men_history = [Infectious_Untreated_Infected_Homosexual_Men]
Treated_with_ART_Homosexual_Men_history = [Treated_with_ART_Homosexual_Men]
HIV_Deaths_Women_history = [HIV_Deaths_Women]
Susceptible_Women_history = [Susceptible_Women]
Infectious_Untreated_Infected_Heterosexual_Men_history = [Infectious_Untreated_Infected_Heterosexual_Men]
Susceptible_Heterosexual_Men_history = [Susceptible_Heterosexual_Men]
Treated_with_ART_Women_history = [Treated_with_ART_Women]
Treated_with_ART_Heterosexual_Men_history = [Treated_with_ART_Heterosexual_Men]
Susceptible_Homosexual_Men_history = [Susceptible_Homosexual_Men]
HIV_Deaths_Heterosexual_Men_history = [HIV_Deaths_Heterosexual_Men]

# ============================================================================
# SIMULATION LOOP (Pre-written - Universal)
# ============================================================================
for step in range(time_steps):
    
    # ========================================================================
    # ### SECTION 4: ODE EQUATIONS ###
    # LLM fills this section with differential equations
    # Convert each ODE equation to valid Python syntax
    # Each equation computes dVariableName_dt
    # Preserve exact mathematical expressions from input
    # Example:
    #   dSusceptible_Homosexual_Men_dt = (
    #       12.7872 * 362796 
    #       - (0.09636435643564358 * Susceptible_Homosexual_Men * Infectious_Untreated_Homosexual_Men / 362796)
    #       - 0.0129 * Susceptible_Homosexual_Men
    #   )
    # ========================================================================
    
    dHIV_Deaths_Homosexual_Men_dt = (0.3333 * Infectious_living_with_AIDS_Homosexual_Men)
    dInfectious_living_with_AIDS_Heterosexual_Men_dt = (0.03333 * Infectious_Untreated_Infected_Heterosexual_Men + 0.018 * Treated_with_ART_Heterosexual_Men - 0.3333 * Infectious_living_with_AIDS_Heterosexual_Men - 0.0129 * Infectious_living_with_AIDS_Heterosexual_Men)
    dInfectious_living_with_AIDS_Women_dt = (0.03333 * Infectious_Untreated_Infected_Women + 0.018 * Treated_with_ART_Women - 0.3333 * Infectious_living_with_AIDS_Women - 0.0129 * Infectious_living_with_AIDS_Women)
    dInfectious_Untreated_Infected_Women_dt = ((0.0011263366336633665 * Susceptible_Women * Infectious_Untreated_Infected_Women / 362796) + (2.526315789473684E-6 * Susceptible_Women * Infectious_Untreated_Infected_Women / 362796) + (1.355124355907057E-5 * Susceptible_Women * Infectious_Untreated_Infected_Women / 362796) - 0.29997 * Infectious_Untreated_Infected_Women - 0.03333 * Infectious_Untreated_Infected_Women - 0.0129 * Infectious_Untreated_Infected_Women)
    dInfectious_living_with_AIDS_Homosexual_Men_dt = (0.03333 * Infectious_Untreated_Infected_Homosexual_Men + 0.018 * Treated_with_ART_Homosexual_Men - 0.3333 * Infectious_living_with_AIDS_Homosexual_Men - 0.0129 * Infectious_living_with_AIDS_Homosexual_Men)
    dInfectious_Untreated_Infected_Homosexual_Men_dt = ((0.09636435643564358 * Susceptible_Homosexual_Men * Infectious_Untreated_Infected_Homosexual_Men / 362796) + (2.526315789473684E-6 * Susceptible_Homosexual_Men * Infectious_Untreated_Infected_Homosexual_Men / 362796) + (1.355124355907057E-5 * Susceptible_Homosexual_Men * Infectious_Untreated_Infected_Homosexual_Men / 362796) - 0.29997 * Infectious_Untreated_Infected_Homosexual_Men - 0.03333 * Infectious_Untreated_Infected_Homosexual_Men - 0.0129 * Infectious_Untreated_Infected_Homosexual_Men)
    dTreated_with_ART_Homosexual_Men_dt = (0.29997 * Infectious_Untreated_Infected_Homosexual_Men - 0.018 * Treated_with_ART_Homosexual_Men - 0.0129 * Treated_with_ART_Homosexual_Men)
    dHIV_Deaths_Women_dt = (0.3333 * Infectious_living_with_AIDS_Women)
    dSusceptible_Women_dt = (173.16 * 362796 - (0.0011263366336633665 * Susceptible_Women * Infectious_Untreated_Infected_Women / 362796) - (2.526315789473684E-6 * Susceptible_Women * Infectious_Untreated_Infected_Women / 362796) - (1.355124355907057E-5 * Susceptible_Women * Infectious_Untreated_Infected_Women / 362796) - 0.0129 * Susceptible_Women)
    dInfectious_Untreated_Infected_Heterosexual_Men_dt = ((0.007821782178217822 * Susceptible_Heterosexual_Men * Infectious_Untreated_Infected_Heterosexual_Men / 362796) + (2.526315789473684E-6 * Susceptible_Heterosexual_Men * Infectious_Untreated_Infected_Heterosexual_Men / 362796) + (1.355124355907057E-5 * Susceptible_Heterosexual_Men * Infectious_Untreated_Infected_Heterosexual_Men / 362796) - 0.29997 * Infectious_Untreated_Infected_Heterosexual_Men - 0.03333 * Infectious_Untreated_Infected_Heterosexual_Men - 0.0129 * Infectious_Untreated_Infected_Heterosexual_Men)
    dSusceptible_Heterosexual_Men_dt = (147.0528 * 362796 - (0.007821782178217822 * Susceptible_Heterosexual_Men * Infectious_Untreated_Infected_Heterosexual_Men / 362796) - (2.526315789473684E-6 * Susceptible_Heterosexual_Men * Infectious_Untreated_Infected_Heterosexual_Men / 362796) - (1.355124355907057E-5 * Susceptible_Heterosexual_Men * Infectious_Untreated_Infected_Heterosexual_Men / 362796) - 0.0129 * Susceptible_Heterosexual_Men)
    dTreated_with_ART_Women_dt = (0.29997 * Infectious_Untreated_Infected_Women - 0.018 * Treated_with_ART_Women - 0.0129 * Treated_with_ART_Women)
    dTreated_with_ART_Heterosexual_Men_dt = (0.29997 * Infectious_Untreated_Infected_Heterosexual_Men - 0.018 * Treated_with_ART_Heterosexual_Men - 0.0129 * Treated_with_ART_Heterosexual_Men)
    dSusceptible_Homosexual_Men_dt = (12.7872 * 362796 - (0.09636435643564358 * Susceptible_Homosexual_Men * Infectious_Untreated_Infected_Homosexual_Men / 362796) - (2.526315789473684E-6 * Susceptible_Homosexual_Men * Infectious_Untreated_Infected_Homosexual_Men / 362796) - (1.355124355907057E-5 * Susceptible_Homosexual_Men * Infectious_Untreated_Infected_Homosexual_Men / 362796) - 0.0129 * Susceptible_Homosexual_Men)
    dHIV_Deaths_Heterosexual_Men_dt = (0.3333 * Infectious_living_with_AIDS_Heterosexual_Men)

    # ========================================================================
    # ### SECTION 5: STATE UPDATES ###
    # LLM fills this section with state variable updates
    # For each compartment: update using Euler method and enforce non-negativity
    # Must match variable names from SECTION 2 exactly
    # Format:
    #   VariableName += dVariableName_dt * dt
    #   VariableName = max(VariableName, 0)
    # Example:
    #   Susceptible_Homosexual_Men += dSusceptible_Homosexual_Men_dt * dt
    #   Susceptible_Homosexual_Men = max(Susceptible_Homosexual_Men, 0)
    # ========================================================================
    
    HIV_Deaths_Homosexual_Men += dHIV_Deaths_Homosexual_Men_dt * dt
    HIV_Deaths_Homosexual_Men = max(HIV_Deaths_Homosexual_Men, 0)
    
    Infectious_living_with_AIDS_Heterosexual_Men += dInfectious_living_with_AIDS_Heterosexual_Men_dt * dt
    Infectious_living_with_AIDS_Heterosexual_Men = max(Infectious_living_with_AIDS_Heterosexual_Men, 0)
    
    Infectious_living_with_AIDS_Women += dInfectious_living_with_AIDS_Women_dt * dt
    Infectious_living_with_AIDS_Women = max(Infectious_living_with_AIDS_Women, 0)
    
    Infectious_Untreated_Infected_Women += dInfectious_Untreated_Infected_Women_dt * dt
    Infectious_Untreated_Infected_Women = max(Infectious_Untreated_Infected_Women, 0)
    
    Infectious_living_with_AIDS_Homosexual_Men += dInfectious_living_with_AIDS_Homosexual_Men_dt * dt
    Infectious_living_with_AIDS_Homosexual_Men = max(Infectious_living_with_AIDS_Homosexual_Men, 0)
    
    Infectious_Untreated_Infected_Homosexual_Men += dInfectious_Untreated_Infected_Homosexual_Men_dt * dt
    Infectious_Untreated_Infected_Homosexual_Men = max(Infectious_Untreated_Infected_Homosexual_Men, 0)
    
    Treated_with_ART_Homosexual_Men += dTreated_with_ART_Homosexual_Men_dt * dt
    Treated_with_ART_Homosexual_Men = max(Treated_with_ART_Homosexual_Men, 0)
    
    HIV_Deaths_Women += dHIV_Deaths_Women_dt * dt
    HIV_Deaths_Women = max(HIV_Deaths_Women, 0)
    
    Susceptible_Women += dSusceptible_Women_dt * dt
    Susceptible_Women = max(Susceptible_Women, 0)
    
    Infectious_Untreated_Infected_Heterosexual_Men += dInfectious_Untreated_Infected_Heterosexual_Men_dt * dt
    Infectious_Untreated_Infected_Heterosexual_Men = max(Infectious_Untreated_Infected_Heterosexual_Men, 0)
    
    Susceptible_Heterosexual_Men += dSusceptible_Heterosexual_Men_dt * dt
    Susceptible_Heterosexual_Men = max(Susceptible_Heterosexual_Men, 0)
    
    Treated_with_ART_Women += dTreated_with_ART_Women_dt * dt
    Treated_with_ART_Women = max(Treated_with_ART_Women, 0)
    
    Treated_with_ART_Heterosexual_Men += dTreated_with_ART_Heterosexual_Men_dt * dt
    Treated_with_ART_Heterosexual_Men = max(Treated_with_ART_Heterosexual_Men, 0)
    
    Susceptible_Homosexual_Men += dSusceptible_Homosexual_Men_dt * dt
    Susceptible_Homosexual_Men = max(Susceptible_Homosexual_Men, 0)
    
    HIV_Deaths_Heterosexual_Men += dHIV_Deaths_Heterosexual_Men_dt * dt
    HIV_Deaths_Heterosexual_Men = max(HIV_Deaths_Heterosexual_Men, 0)
    
    # ========================================================================
    # ### SECTION 6: RECORD HISTORY ###
    # LLM fills this section with history recording
    # Append current value to corresponding history list
    # Must match variable and history names from SECTIONS 2 and 3 exactly
    # Example:
    #   Susceptible_Homosexual_Men_history.append(Susceptible_Homosexual_Men)
    #   Infectious_Untreated_Homosexual_Men_history.append(Infectious_Untreated_Homosexual_Men)
    # ========================================================================
    
    HIV_Deaths_Homosexual_Men_history.append(HIV_Deaths_Homosexual_Men)
    Infectious_living_with_AIDS_Heterosexual_Men_history.append(Infectious_living_with_AIDS_Heterosexual_Men)
    Infectious_living_with_AIDS_Women_history.append(Infectious_living_with_AIDS_Women)
    Infectious_Untreated_Infected_Women_history.append(Infectious_Untreated_Infected_Women)
    Infectious_living_with_AIDS_Homosexual_Men_history.append(Infectious_living_with_AIDS_Homosexual_Men)
    Infectious_Untreated_Infected_Homosexual_Men_history.append(Infectious_Untreated_Infected_Homosexual_Men)
    Treated_with_ART_Homosexual_Men_history.append(Treated_with_ART_Homosexual_Men)
    HIV_Deaths_Women_history.append(HIV_Deaths_Women)
    Susceptible_Women_history.append(Susceptible_Women)
    Infectious_Untreated_Infected_Heterosexual_Men_history.append(Infectious_Untreated_Infected_Heterosexual_Men)
    Susceptible_Heterosexual_Men_history.append(Susceptible_Heterosexual_Men)
    Treated_with_ART_Women_history.append(Treated_with_ART_Women)
    Treated_with_ART_Heterosexual_Men_history.append(Treated_with_ART_Heterosexual_Men)
    Susceptible_Homosexual_Men_history.append(Susceptible_Homosexual_Men)
    HIV_Deaths_Heterosexual_Men_history.append(HIV_Deaths_Heterosexual_Men)

# ============================================================================
# PLOTTING SETUP (Pre-written - Universal)
# ============================================================================
plt.figure(figsize=(12, 8))

# ============================================================================
# ### SECTION 7: PLOT LINES ###
# LLM fills this section with plot commands
# Create one plt.plot() line per compartment with readable label
# Use history arrays from SECTION 3
# Format: plt.plot(time, VariableName_history, label='Readable Label')
# Example:
#   plt.plot(time, Susceptible_Homosexual_Men_history, label='Susceptible (Homosexual Men)')
#   plt.plot(time, Infectious_Untreated_Homosexual_Men_history, label='Infectious Untreated (Homosexual Men)')
# ============================================================================

plt.plot(time, HIV_Deaths_Homosexual_Men_history, label='HIV Deaths (Homosexual Men)')
plt.plot(time, Infectious_living_with_AIDS_Heterosexual_Men_history, label='Infectious Living with AIDS (Heterosexual Men)')
plt.plot(time, Infectious_living_with_AIDS_Women_history, label='Infectious Living with AIDS (Women)')
plt.plot(time, Infectious_Untreated_Infected_Women_history, label='Infectious Untreated Infected (Women)')
plt.plot(time, Infectious_living_with_AIDS_Homosexual_Men_history, label='Infectious Living with AIDS (Homosexual Men)')
plt.plot(time, Infectious_Untreated_Infected_Homosexual_Men_history, label='Infectious Untreated Infected (Homosexual Men)')
plt.plot(time, Treated_with_ART_Homosexual_Men_history, label='Treated with ART (Homosexual Men)')
plt.plot(time, HIV_Deaths_Women_history, label='HIV Deaths (Women)')
plt.plot(time, Susceptible_Women_history, label='Susceptible (Women)')
plt.plot(time, Infectious_Untreated_Infected_Heterosexual_Men_history, label='Infectious Untreated Infected (Heterosexual Men)')
plt.plot(time, Susceptible_Heterosexual_Men_history, label='Susceptible (Heterosexual Men)')
plt.plot(time, Treated_with_ART_Women_history, label='Treated with ART (Women)')
plt.plot(time, Treated_with_ART_Heterosexual_Men_history, label='Treated with ART (Heterosexual Men)')
plt.plot(time, Susceptible_Homosexual_Men_history, label='Susceptible (Homosexual Men)')
plt.plot(time, HIV_Deaths_Heterosexual_Men_history, label='HIV Deaths (Heterosexual Men)')

# ============================================================================
# PLOT FINALIZATION (Pre-written - Universal)
# ============================================================================
plt.xlabel('Time')
plt.ylabel('Population')
plt.title(f'{model_name} - SEIR Model Simulation')
plt.legend(loc='best', fontsize=8, ncol=2)
plt.grid(True, alpha=0.3)

# Generate unique output filename using model name
output_filename = f'simulation_{model_name}.png'
plt.savefig(output_filename, dpi=300, bbox_inches='tight')
print(f"Simulation complete! Graph saved as '{output_filename}'")
plt.close()
```