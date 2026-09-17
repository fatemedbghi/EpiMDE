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
model_name = "COVID_Age_Stratified"

# ============================================================================
# ### SECTION 2: INITIAL CONDITIONS ###
# LLM fills this section with initial population values
# Parse compartment names from ODE equations and assign initial values
# Use valid Python variable names (replace spaces/special chars with underscores)
# Example:
#   Susceptible_Homosexual_Men = 2446
#   Infectious_Untreated_Homosexual_Men = 79
# ============================================================================

Susceptible_0_17 = 4900000
Susceptible_18_64 = 8800000
Susceptible_65_plus = 1000000
Exposed_0_17 = 1667
Exposed_18_64 = 3000
Exposed_65_plus = 333
Exposed_quarantined_0_17 = 333
Exposed_quarantined_18_64 = 600
Exposed_quarantined_65_plus = 67
Infectious_presymptomatic_0_17 = 1000
Infectious_presymptomatic_18_64 = 1800
Infectious_presymptomatic_65_plus = 200
Infectious_presymptomatic_isolated_0_17 = 333
Infectious_presymptomatic_isolated_18_64 = 600
Infectious_presymptomatic_isolated_65_plus = 67
Infectious_mild_to_moderate_0_17 = 2667
Infectious_mild_to_moderate_18_64 = 4800
Infectious_mild_to_moderate_65_plus = 533
Infectious_mild_to_moderate_isolated_0_17 = 1333
Infectious_mild_to_moderate_isolated_18_64 = 2400
Infectious_mild_to_moderate_isolated_65_plus = 267
Infectious_severe_0_17 = 667
Infectious_severe_18_64 = 1200
Infectious_severe_65_plus = 133
Infectious_severe_isolated_0_17 = 267
Infectious_severe_isolated_18_64 = 480
Infectious_severe_isolated_65_plus = 53
Isolated_0_17 = 2000
Isolated_18_64 = 3600
Isolated_65_plus = 400
Admitted_to_hospital_0_17 = 500
Admitted_to_hospital_18_64 = 900
Admitted_to_hospital_65_plus = 100
ICU_0_17 = 133
ICU_18_64 = 240
ICU_65_plus = 27
Admitted_to_hospital_post_ICU_0_17 = 67
Admitted_to_hospital_post_ICU_18_64 = 120
Admitted_to_hospital_post_ICU_65_plus = 13
Recovered_0_17 = 16667
Recovered_18_64 = 30000
Recovered_65_plus = 3333
COVID_Deaths_0_17 = 33
COVID_Deaths_18_64 = 60
COVID_Deaths_65_plus = 7

# ============================================================================
# ### SECTION 3: HISTORY ARRAYS ###
# LLM fills this section with history tracking lists
# Create one history list per compartment initialized with initial value
# Must match variable names from SECTION 2 exactly
# Example:
#   Susceptible_Homosexual_Men_history = [Susceptible_Homosexual_Men]
#   Infectious_Untreated_Homosexual_Men_history = [Infectious_Untreated_Homosexual_Men]
# ============================================================================

Susceptible_0_17_history = [Susceptible_0_17]
Susceptible_18_64_history = [Susceptible_18_64]
Susceptible_65_plus_history = [Susceptible_65_plus]
Exposed_0_17_history = [Exposed_0_17]
Exposed_18_64_history = [Exposed_18_64]
Exposed_65_plus_history = [Exposed_65_plus]
Exposed_quarantined_0_17_history = [Exposed_quarantined_0_17]
Exposed_quarantined_18_64_history = [Exposed_quarantined_18_64]
Exposed_quarantined_65_plus_history = [Exposed_quarantined_65_plus]
Infectious_presymptomatic_0_17_history = [Infectious_presymptomatic_0_17]
Infectious_presymptomatic_18_64_history = [Infectious_presymptomatic_18_64]
Infectious_presymptomatic_65_plus_history = [Infectious_presymptomatic_65_plus]
Infectious_presymptomatic_isolated_0_17_history = [Infectious_presymptomatic_isolated_0_17]
Infectious_presymptomatic_isolated_18_64_history = [Infectious_presymptomatic_isolated_18_64]
Infectious_presymptomatic_isolated_65_plus_history = [Infectious_presymptomatic_isolated_65_plus]
Infectious_mild_to_moderate_0_17_history = [Infectious_mild_to_moderate_0_17]
Infectious_mild_to_moderate_18_64_history = [Infectious_mild_to_moderate_18_64]
Infectious_mild_to_moderate_65_plus_history = [Infectious_mild_to_moderate_65_plus]
Infectious_mild_to_moderate_isolated_0_17_history = [Infectious_mild_to_moderate_isolated_0_17]
Infectious_mild_to_moderate_isolated_18_64_history = [Infectious_mild_to_moderate_isolated_18_64]
Infectious_mild_to_moderate_isolated_65_plus_history = [Infectious_mild_to_moderate_isolated_65_plus]
Infectious_severe_0_17_history = [Infectious_severe_0_17]
Infectious_severe_18_64_history = [Infectious_severe_18_64]
Infectious_severe_65_plus_history = [Infectious_severe_65_plus]
Infectious_severe_isolated_0_17_history = [Infectious_severe_isolated_0_17]
Infectious_severe_isolated_18_64_history = [Infectious_severe_isolated_18_64]
Infectious_severe_isolated_65_plus_history = [Infectious_severe_isolated_65_plus]
Isolated_0_17_history = [Isolated_0_17]
Isolated_18_64_history = [Isolated_18_64]
Isolated_65_plus_history = [Isolated_65_plus]
Admitted_to_hospital_0_17_history = [Admitted_to_hospital_0_17]
Admitted_to_hospital_18_64_history = [Admitted_to_hospital_18_64]
Admitted_to_hospital_65_plus_history = [Admitted_to_hospital_65_plus]
ICU_0_17_history = [ICU_0_17]
ICU_18_64_history = [ICU_18_64]
ICU_65_plus_history = [ICU_65_plus]
Admitted_to_hospital_post_ICU_0_17_history = [Admitted_to_hospital_post_ICU_0_17]
Admitted_to_hospital_post_ICU_18_64_history = [Admitted_to_hospital_post_ICU_18_64]
Admitted_to_hospital_post_ICU_65_plus_history = [Admitted_to_hospital_post_ICU_65_plus]
Recovered_0_17_history = [Recovered_0_17]
Recovered_18_64_history = [Recovered_18_64]
Recovered_65_plus_history = [Recovered_65_plus]
COVID_Deaths_0_17_history = [COVID_Deaths_0_17]
COVID_Deaths_18_64_history = [COVID_Deaths_18_64]
COVID_Deaths_65_plus_history = [COVID_Deaths_65_plus]

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
    
    dSusceptible_0_17_dt = (
        3.0E-5 * 14800000 
        - (0.00001 * 0.7 * Susceptible_0_17 * Infectious_presymptomatic_0_17 / 14800000) 
        - (0.000002 * 0.7 * Susceptible_0_17 * Infectious_presymptomatic_0_17 / 14800000)
    )
    dSusceptible_18_64_dt = (
        - (0.00001 * 1.0 * Susceptible_18_64 * Infectious_presymptomatic_18_64 / 14800000) 
        - (0.000002 * 1.0 * Susceptible_18_64 * Infectious_presymptomatic_18_64 / 14800000)
    )
    dSusceptible_65_plus_dt = (
        - (0.00001 * 1.3 * Susceptible_65_plus * Infectious_presymptomatic_65_plus / 14800000) 
        - (0.000002 * 1.3 * Susceptible_65_plus * Infectious_presymptomatic_65_plus / 14800000)
    )
    dExposed_0_17_dt = (
        (0.00001 * 0.7 * Susceptible_0_17 * Infectious_presymptomatic_0_17 / 14800000) 
        - (0.4 * 1.25 * Exposed_0_17)
    )
    dExposed_18_64_dt = (
        (0.00001 * 1.0 * Susceptible_18_64 * Infectious_presymptomatic_18_64 / 14800000) 
        - (0.4 * 1.0 * Exposed_18_64)
    )
    dExposed_65_plus_dt = (
        (0.00001 * 1.3 * Susceptible_65_plus * Infectious_presymptomatic_65_plus / 14800000) 
        - (0.4 * 0.75 * Exposed_65_plus)
    )
    dExposed_quarantined_0_17_dt = (
        (0.000002 * 0.7 * Susceptible_0_17 * Infectious_presymptomatic_0_17 / 14800000) 
        - (0.4 * 1.25 * Exposed_quarantined_0_17)
    )
    dExposed_quarantined_18_64_dt = (
        (0.000002 * 1.0 * Susceptible_18_64 * Infectious_presymptomatic_18_64 / 14800000) 
        - (0.4 * 1.0 * Exposed_quarantined_18_64)
    )
    dExposed_quarantined_65_plus_dt = (
        (0.000002 * 1.3 * Susceptible_65_plus * Infectious_presymptomatic_65_plus / 14800000) 
        - (0.4 * 0.75 * Exposed_quarantined_65_plus)
    )
    dInfectious_presymptomatic_0_17_dt = (
        (0.4 * 1.25 * Exposed_0_17) 
        - (0.85 * 1.15 * Infectious_presymptomatic_0_17) 
        - (0.15 * 0.07 * Infectious_presymptomatic_0_17)
    )
    dInfectious_presymptomatic_18_64_dt = (
        (0.4 * 1.0 * Exposed_18_64) 
        - (0.85 * 1.0 * Infectious_presymptomatic_18_64) 
        - (0.15 * 1.0 * Infectious_presymptomatic_18_64)
    )
    dInfectious_presymptomatic_65_plus_dt = (
        (0.4 * 0.75 * Exposed_65_plus) 
        - (0.85 * 0.76 * Infectious_presymptomatic_65_plus) 
        - (0.15 * 2.33 * Infectious_presymptomatic_65_plus)
    )
    dInfectious_presymptomatic_isolated_0_17_dt = (
        (0.4 * 1.25 * Exposed_quarantined_0_17) 
        - (0.85 * 1.15 * Infectious_presymptomatic_isolated_0_17) 
        - (0.15 * 0.07 * Infectious_presymptomatic_isolated_0_17)
    )
    dInfectious_presymptomatic_isolated_18_64_dt = (
        (0.4 * 1.0 * Exposed_quarantined_18_64) 
        - (0.85 * 1.0 * Infectious_presymptomatic_isolated_18_64) 
        - (0.15 * 1.0 * Infectious_presymptomatic_isolated_18_64)
    )
    dInfectious_presymptomatic_isolated_65_plus_dt = (
        (0.4 * 0.75 * Exposed_quarantined_65_plus) 
        - (0.85 * 0.76 * Infectious_presymptomatic_isolated_65_plus) 
        - (0.15 * 2.33 * Infectious_presymptomatic_isolated_65_plus)
    )
    dInfectious_mild_to_moderate_0_17_dt = (
        (0.85 * 1.15 * Infectious_presymptomatic_0_17) 
        - 0.5 * Infectious_mild_to_moderate_0_17 
        - 0.167 * Infectious_mild_to_moderate_0_17
    )
    dInfectious_mild_to_moderate_18_64_dt = (
        (0.85 * 1.0 * Infectious_presymptomatic_18_64) 
        - 0.5 * Infectious_mild_to_moderate_18_64 
        - 0.167 * Infectious_mild_to_moderate_18_64
    )
    dInfectious_mild_to_moderate_65_plus_dt = (
        (0.85 * 0.76 * Infectious_presymptomatic_65_plus) 
        - 0.5 * Infectious_mild_to_moderate_65_plus 
        - 0.167 * Infectious_mild_to_moderate_65_plus
    )
    dInfectious_mild_to_moderate_isolated_0_17_dt = (
        (0.85 * 1.15 * Infectious_presymptomatic_isolated_0_17) 
        - 0.0 * Infectious_mild_to_moderate_isolated_0_17 
        - 0.167 * Infectious_mild_to_moderate_isolated_0_17
    )
    dInfectious_mild_to_moderate_isolated_18_64_dt = (
        (0.85 * 1.0 * Infectious_presymptomatic_isolated_18_64) 
        - 0.0 * Infectious_mild_to_moderate_isolated_18_64 
        - 0.167 * Infectious_mild_to_moderate_isolated_18_64
    )
    dInfectious_mild_to_moderate_isolated_65_plus_dt = (
        (0.85 * 0.76 * Infectious_presymptomatic_isolated_65_plus) 
        - 0.0 * Infectious_mild_to_moderate_isolated_65_plus 
        - 0.167 * Infectious_mild_to_moderate_isolated_65_plus
    )
    dInfectious_severe_0_17_dt = (
        (0.15 * 0.07 * Infectious_presymptomatic_0_17) 
        - 0.167 * Infectious_severe_0_17
    )
    dInfectious_severe_18_64_dt = (
        (0.15 * 1.0 * Infectious_presymptomatic_18_64) 
        - 0.167 * Infectious_severe_18_64
    )
    dInfectious_severe_65_plus_dt = (
        (0.15 * 2.33 * Infectious_presymptomatic_65_plus) 
        - 0.167 * Infectious_severe_65_plus
    )
    dInfectious_severe_isolated_0_17_dt = (
        (0.15 * 0.07 * Infectious_presymptomatic_isolated_0_17) 
        - 0.167 * Infectious_severe_isolated_0_17
    )
    dInfectious_severe_isolated_18_64_dt = (
        (0.15 * 1.0 * Infectious_presymptomatic_isolated_18_64) 
        - 0.167 * Infectious_severe_isolated_18_64
    )
    dInfectious_severe_isolated_65_plus_dt = (
        (0.15 * 2.33 * Infectious_presymptomatic_isolated_65_plus) 
        - 0.167 * Infectious_severe_isolated_65_plus
    )
    dIsolated_0_17_dt = (
        0.5 * Infectious_mild_to_moderate_0_17 
        + 0.0 * Infectious_mild_to_moderate_isolated_0_17 
        - 0.167 * Isolated_0_17
    )
    dIsolated_18_64_dt = (
        0.5 * Infectious_mild_to_moderate_18_64 
        + 0.0 * Infectious_mild_to_moderate_isolated_18_64 
        - 0.167 * Isolated_18_64
    )
    dIsolated_65_plus_dt = (
        0.5 * Infectious_mild_to_moderate_65_plus 
        + 0.0 * Infectious_mild_to_moderate_isolated_65_plus 
        - 0.167 * Isolated_65_plus
    )
    dAdmitted_to_hospital_0_17_dt = (
        0.167 * Infectious_severe_0_17 
        + 0.167 * Infectious_severe_isolated_0_17 
        - (0.74 * 1.22 * Admitted_to_hospital_0_17) 
        - 0.26 * Admitted_to_hospital_0_17
    )
    dAdmitted_to_hospital_18_64_dt = (
        0.167 * Infectious_severe_18_64 
        + 0.167 * Infectious_severe_isolated_18_64 
        - (0.74 * 1.08 * Admitted_to_hospital_18_64) 
        - 0.26 * Admitted_to_hospital_18_64
    )
    dAdmitted_to_hospital_65_plus_dt = (
        0.167 * Infectious_severe_65_plus 
        + 0.167 * Infectious_severe_isolated_65_plus 
        - (0.74 * 0.81 * Admitted_to_hospital_65_plus) 
        - 0.26 * Admitted_to_hospital_65_plus
    )
    dICU_0_17_dt = (
        0.26 * Admitted_to_hospital_0_17 
        - (0.6 * 1.42 * ICU_0_17) 
        - (0.4 * 0.0 * ICU_0_17)
    )
    dICU_18_64_dt = (
        0.26 * Admitted_to_hospital_18_64 
        - (0.6 * 1.17 * ICU_18_64) 
        - (0.4 * 0.5 * ICU_18_64)
    )
    dICU_65_plus_dt = (
        0.26 * Admitted_to_hospital_65_plus 
        - (0.6 * 0.75 * ICU_65_plus) 
        - (0.4 * 1.45 * ICU_65_plus)
    )
    dAdmitted_to_hospital_post_ICU_0_17_dt = (
        (0.6 * 1.42 * ICU_0_17) 
        - 0.048 * Admitted_to_hospital_post_ICU_0_17
    )
    dAdmitted_to_hospital_post_ICU_18_64_dt = (
        (0.6 * 1.17 * ICU_18_64) 
        - 0.048 * Admitted_to_hospital_post_ICU_18_64
    )
    dAdmitted_to_hospital_post_ICU_65_plus_dt = (
        (0.6 * 0.75 * ICU_65_plus) 
        - 0.048 * Admitted_to_hospital_post_ICU_65_plus
    )
    dRecovered_0_17_dt = (
        0.167 * Infectious_mild_to_moderate_0_17 
        + 0.167 * Infectious_mild_to_moderate_isolated_0_17 
        + 0.167 * Isolated_0_17 
        + (0.74 * 1.22 * Admitted_to_hospital_0_17) 
        + 0.048 * Admitted_to_hospital_post_ICU_0_17
    )
    dRecovered_18_64_dt = (
        0.167 * Infectious_mild_to_moderate_18_64 
        + 0.167 * Infectious_mild_to_moderate_isolated_18_64 
        + 0.167 * Isolated_18_64 
        + (0.74 * 1.08 * Admitted_to_hospital_18_64) 
        + 0.048 * Admitted_to_hospital_post_ICU_18_64
    )
    dRecovered_65_plus_dt = (
        0.167 * Infectious_mild_to_moderate_65_plus 
        + 0.167 * Infectious_mild_to_moderate_isolated_65_plus 
        + 0.167 * Isolated_65_plus 
        + (0.74 * 0.81 * Admitted_to_hospital_65_plus) 
        + 0.048 * Admitted_to_hospital_post_ICU_65_plus
    )
    dCOVID_Deaths_0_17_dt = (
        (0.4 * 0.0 * ICU_0_17)
    )
    dCOVID_Deaths_18_64_dt = (
        (0.4 * 0.5 * ICU_18_64)
    )
    dCOVID_Deaths_65_plus_dt = (
        (0.4 * 1.45 * ICU_65_plus)
    )
    
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
    
    Susceptible_0_17 += dSusceptible_0_17_dt * dt
    Susceptible_0_17 = max(Susceptible_0_17, 0)
    Susceptible_18_64 += dSusceptible_18_64_dt * dt
    Susceptible_18_64 = max(Susceptible_18_64, 0)
    Susceptible_65_plus += dSusceptible_65_plus_dt * dt
    Susceptible_65_plus = max(Susceptible_65_plus, 0)
    Exposed_0_17 += dExposed_0_17_dt * dt
    Exposed_0_17 = max(Exposed_0_17, 0)
    Exposed_18_64 += dExposed_18_64_dt * dt
    Exposed_18_64 = max(Exposed_18_64, 0)
    Exposed_65_plus += dExposed_65_plus_dt * dt
    Exposed_65_plus = max(Exposed_65_plus, 0)
    Exposed_quarantined_0_17 += dExposed_quarantined_0_17_dt * dt
    Exposed_quarantined_0_17 = max(Exposed_quarantined_0_17, 0)
    Exposed_quarantined_18_64 += dExposed_quarantined_18_64_dt * dt
    Exposed_quarantined_18_64 = max(Exposed_quarantined_18_64, 0)
    Exposed_quarantined_65_plus += dExposed_quarantined_65_plus_dt * dt
    Exposed_quarantined_65_plus = max(Exposed_quarantined_65_plus, 0)
    Infectious_presymptomatic_0_17 += dInfectious_presymptomatic_0_17_dt * dt
    Infectious_presymptomatic_0_17 = max(Infectious_presymptomatic_0_17, 0)
    Infectious_presymptomatic_18_64 += dInfectious_presymptomatic_18_64_dt * dt
    Infectious_presymptomatic_18_64 = max(Infectious_presymptomatic_18_64, 0)
    Infectious_presymptomatic_65_plus += dInfectious_presymptomatic_65_plus_dt * dt
    Infectious_presymptomatic_65_plus = max(Infectious_presymptomatic_65_plus, 0)
    Infectious_presymptomatic_isolated_0_17 += dInfectious_presymptomatic_isolated_0_17_dt * dt
    Infectious_presymptomatic_isolated_0_17 = max(Infectious_presymptomatic_isolated_0_17, 0)
    Infectious_presymptomatic_isolated_18_64 += dInfectious_presymptomatic_isolated_18_64_dt * dt
    Infectious_presymptomatic_isolated_18_64 = max(Infectious_presymptomatic_isolated_18_64, 0)
    Infectious_presymptomatic_isolated_65_plus += dInfectious_presymptomatic_isolated_65_plus_dt * dt
    Infectious_presymptomatic_isolated_65_plus = max(Infectious_presymptomatic_isolated_65_plus, 0)
    Infectious_mild_to_moderate_0_17 += dInfectious_mild_to_moderate_0_17_dt * dt
    Infectious_mild_to_moderate_0_17 = max(Infectious_mild_to_moderate_0_17, 0)
    Infectious_mild_to_moderate_18_64 += dInfectious_mild_to_moderate_18_64_dt * dt
    Infectious_mild_to_moderate_18_64 = max(Infectious_mild_to_moderate_18_64, 0)
    Infectious_mild_to_moderate_65_plus += dInfectious_mild_to_moderate_65_plus_dt * dt
    Infectious_mild_to_moderate_65_plus = max(Infectious_mild_to_moderate_65_plus, 0)
    Infectious_mild_to_moderate_isolated_0_17 += dInfectious_mild_to_moderate_isolated_0_17_dt * dt
    Infectious_mild_to_moderate_isolated_0_17 = max(Infectious_mild_to_moderate_isolated_0_17, 0)
    Infectious_mild_to_moderate_isolated_18_64 += dInfectious_mild_to_moderate_isolated_18_64_dt * dt
    Infectious_mild_to_moderate_isolated_18_64 = max(Infectious_mild_to_moderate_isolated_18_64, 0)
    Infectious_mild_to_moderate_isolated_65_plus += dInfectious_mild_to_moderate_isolated_65_plus_dt * dt
    Infectious_mild_to_moderate_isolated_65_plus = max(Infectious_mild_to_moderate_isolated_65_plus, 0)
    Infectious_severe_0_17 += dInfectious_severe_0_17_dt * dt
    Infectious_severe_0_17 = max(Infectious_severe_0_17, 0)
    Infectious_severe_18_64 += dInfectious_severe_18_64_dt * dt
    Infectious_severe_18_64 = max(Infectious_severe_18_64, 0)
    Infectious_severe_65_plus += dInfectious_severe_65_plus_dt * dt
    Infectious_severe_65_plus = max(Infectious_severe_65_plus, 0)
    Infectious_severe_isolated_0_17 += dInfectious_severe_isolated_0_17_dt * dt
    Infectious_severe_isolated_0_17 = max(Infectious_severe_isolated_0_17, 0)
    Infectious_severe_isolated_18_64 += dInfectious_severe_isolated_18_64_dt * dt
    Infectious_severe_isolated_18_64 = max(Infectious_severe_isolated_18_64, 0)
    Infectious_severe_isolated_65_plus += dInfectious_severe_isolated_65_plus_dt * dt
    Infectious_severe_isolated_65_plus = max(Infectious_severe_isolated_65_plus, 0)
    Isolated_0_17 += dIsolated_0_17_dt * dt
    Isolated_0_17 = max(Isolated_0_17, 0)
    Isolated_18_64 += dIsolated_18_64_dt * dt
    Isolated_18_64 = max(Isolated_18_64, 0)
    Isolated_65_plus += dIsolated_65_plus_dt * dt
    Isolated_65_plus = max(Isolated_65_plus, 0)
    Admitted_to_hospital_0_17 += dAdmitted_to_hospital_0_17_dt * dt
    Admitted_to_hospital_0_17 = max(Admitted_to_hospital_0_17, 0)
    Admitted_to_hospital_18_64 += dAdmitted_to_hospital_18_64_dt * dt
    Admitted_to_hospital_18_64 = max(Admitted_to_hospital_18_64, 0)
    Admitted_to_hospital_65_plus += dAdmitted_to_hospital_65_plus_dt * dt
    Admitted_to_hospital_65_plus = max(Admitted_to_hospital_65_plus, 0)
    ICU_0_17 += dICU_0_17_dt * dt
    ICU_0_17 = max(ICU_0_17, 0)
    ICU_18_64 += dICU_18_64_dt * dt
    ICU_18_64 = max(ICU_18_64, 0)
    ICU_65_plus += dICU_65_plus_dt * dt
    ICU_65_plus = max(ICU_65_plus, 0)
    Admitted_to_hospital_post_ICU_0_17 += dAdmitted_to_hospital_post_ICU_0_17_dt * dt
    Admitted_to_hospital_post_ICU_0_17 = max(Admitted_to_hospital_post_ICU_0_17, 0)
    Admitted_to_hospital_post_ICU_18_64 += dAdmitted_to_hospital_post_ICU_18_64_dt * dt
    Admitted_to_hospital_post_ICU_18_64 = max(Admitted_to_hospital_post_ICU_18_64, 0)
    Admitted_to_hospital_post_ICU_65_plus += dAdmitted_to_hospital_post_ICU_65_plus_dt * dt
    Admitted_to_hospital_post_ICU_65_plus = max(Admitted_to_hospital_post_ICU_65_plus, 0)
    Recovered_0_17 += dRecovered_0_17_dt * dt
    Recovered_0_17 = max(Recovered_0_17, 0)
    Recovered_18_64 += dRecovered_18_64_dt * dt
    Recovered_18_64 = max(Recovered_18_64, 0)
    Recovered_65_plus += dRecovered_65_plus_dt * dt
    Recovered_65_plus = max(Recovered_65_plus, 0)
    COVID_Deaths_0_17 += dCOVID_Deaths_0_17_dt * dt
    COVID_Deaths_0_17 = max(COVID_Deaths_0_17, 0)
    COVID_Deaths_18_64 += dCOVID_Deaths_18_64_dt * dt
    COVID_Deaths_18_64 = max(COVID_Deaths_18_64, 0)
    COVID_Deaths_65_plus += dCOVID_Deaths_65_plus_dt * dt
    COVID_Deaths_65_plus = max(COVID_Deaths_65_plus, 0)
    
    # ========================================================================
    # ### SECTION 6: RECORD HISTORY ###
    # LLM fills this section with history recording
    # Append current value to corresponding history list
    # Must match variable and history names from SECTIONS 2 and 3 exactly
    # Example:
    #   Susceptible_Homosexual_Men_history.append(Susceptible_Homosexual_Men)
    #   Infectious_Untreated_Homosexual_Men_history.append(Infectious_Untreated_Homosexual_Men)
    # ========================================================================
    
    Susceptible_0_17_history.append(Susceptible_0_17)
    Susceptible_18_64_history.append(Susceptible_18_64)
    Susceptible_65_plus_history.append(Susceptible_65_plus)
    Exposed_0_17_history.append(Exposed_0_17)
    Exposed_18_64_history.append(Exposed_18_64)
    Exposed_65_plus_history.append(Exposed_65_plus)
    Exposed_quarantined_0_17_history.append(Exposed_quarantined_0_17)
    Exposed_quarantined_18_64_history.append(Exposed_quarantined_18_64)
    Exposed_quarantined_65_plus_history.append(Exposed_quarantined_65_plus)
    Infectious_presymptomatic_0_17_history.append(Infectious_presymptomatic_0_17)
    Infectious_presymptomatic_18_64_history.append(Infectious_presymptomatic_18_64)
    Infectious_presymptomatic_65_plus_history.append(Infectious_presymptomatic_65_plus)
    Infectious_presymptomatic_isolated_0_17_history.append(Infectious_presymptomatic_isolated_0_17)
    Infectious_presymptomatic_isolated_18_64_history.append(Infectious_presymptomatic_isolated_18_64)
    Infectious_presymptomatic_isolated_65_plus_history.append(Infectious_presymptomatic_isolated_65_plus)
    Infectious_mild_to_moderate_0_17_history.append(Infectious_mild_to_moderate_0_17)
    Infectious_mild_to_moderate_18_64_history.append(Infectious_mild_to_moderate_18_64)
    Infectious_mild_to_moderate_65_plus_history.append(Infectious_mild_to_moderate_65_plus)
    Infectious_mild_to_moderate_isolated_0_17_history.append(Infectious_mild_to_moderate_isolated_0_17)
    Infectious_mild_to_moderate_isolated_18_64_history.append(Infectious_mild_to_moderate_isolated_18_64)
    Infectious_mild_to_moderate_isolated_65_plus_history.append(Infectious_mild_to_moderate_isolated_65_plus)
    Infectious_severe_0_17_history.append(Infectious_severe_0_17)
    Infectious_severe_18_64_history.append(Infectious_severe_18_64)
    Infectious_severe_65_plus_history.append(Infectious_severe_65_plus)
    Infectious_severe_isolated_0_17_history.append(Infectious_severe_isolated_0_17)
    Infectious_severe_isolated_18_64_history.append(Infectious_severe_isolated_18_64)
    Infectious_severe_isolated_65_plus_history.append(Infectious_severe_isolated_65_plus)
    Isolated_0_17_history.append(Isolated_0_17)
    Isolated_18_64_history.append(Isolated_18_64)
    Isolated_65_plus_history.append(Isolated_65_plus)
    Admitted_to_hospital_0_17_history.append(Admitted_to_hospital_0_17)
    Admitted_to_hospital_18_64_history.append(Admitted_to_hospital_18_64)
    Admitted_to_hospital_65_plus_history.append(Admitted_to_hospital_65_plus)
    ICU_0_17_history.append(ICU_0_17)
    ICU_18_64_history.append(ICU_18_64)
    ICU_65_plus_history.append(ICU_65_plus)
    Admitted_to_hospital_post_ICU_0_17_history.append(Admitted_to_hospital_post_ICU_0_17)
    Admitted_to_hospital_post_ICU_18_64_history.append(Admitted_to_hospital_post_ICU_18_64)
    Admitted_to_hospital_post_ICU_65_plus_history.append(Admitted_to_hospital_post_ICU_65_plus)
    Recovered_0_17_history.append(Recovered_0_17)
    Recovered_18_64_history.append(Recovered_18_64)
    Recovered_65_plus_history.append(Recovered_65_plus)
    COVID_Deaths_0_17_history.append(COVID_Deaths_0_17)
    COVID_Deaths_18_64_history.append(COVID_Deaths_18_64)
    COVID_Deaths_65_plus_history.append(COVID_Deaths_65_plus)

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

plt.plot(time, Susceptible_0_17_history, label='Susceptible (0-17)')
plt.plot(time, Susceptible_18_64_history, label='Susceptible (18-64)')
plt.plot(time, Susceptible_65_plus_history, label='Susceptible (65+)')
plt.plot(time, Exposed_0_17_history, label='Exposed (0-17)')
plt.plot(time, Exposed_18_64_history, label='Exposed (18-64)')
plt.plot(time, Exposed_65_plus_history, label='Exposed (65+)')
plt.plot(time, Exposed_quarantined_0_17_history, label='Exposed Quarantined (0-17)')
plt.plot(time, Exposed_quarantined_18_64_history, label='Exposed Quarantined (18-64)')
plt.plot(time, Exposed_quarantined_65_plus_history, label='Exposed Quarantined (65+)')
plt.plot(time, Infectious_presymptomatic_0_17_history, label='Infectious Presymptomatic (0-17)')
plt.plot(time, Infectious_presymptomatic_18_64_history, label='Infectious Presymptomatic (18-64)')
plt.plot(time, Infectious_presymptomatic_65_plus_history, label='Infectious Presymptomatic (65+)')
plt.plot(time, Infectious_presymptomatic_isolated_0_17_history, label='Infectious Presymptomatic Isolated (0-17)')
plt.plot(time, Infectious_presymptomatic_isolated_18_64_history, label='Infectious Presymptomatic Isolated (18-64)')
plt.plot(time, Infectious_presymptomatic_isolated_65_plus_history, label='Infectious Presymptomatic Isolated (65+)')
plt.plot(time, Infectious_mild_to_moderate_0_17_history, label='Infectious Mild to Moderate (0-17)')
plt.plot(time, Infectious_mild_to_moderate_18_64_history, label='Infectious Mild to Moderate (18-64)')
plt.plot(time, Infectious_mild_to_moderate_65_plus_history, label='Infectious Mild to Moderate (65+)')
plt.plot(time, Infectious_mild_to_moderate_isolated_0_17_history, label='Infectious Mild to Moderate Isolated (0-17)')
plt.plot(time, Infectious_mild_to_moderate_isolated_18_64_history, label='Infectious Mild to Moderate Isolated (18-64)')
plt.plot(time, Infectious_mild_to_moderate_isolated_65_plus_history, label='Infectious Mild to Moderate Isolated (65+)')
plt.plot(time, Infectious_severe_0_17_history, label='Infectious Severe (0-17)')
plt.plot(time, Infectious_severe_18_64_history, label='Infectious Severe (18-64)')
plt.plot(time, Infectious_severe_65_plus_history, label='Infectious Severe (65+)')
plt.plot(time, Infectious_severe_isolated_0_17_history, label='Infectious Severe Isolated (0-17)')
plt.plot(time, Infectious_severe_isolated_18_64_history, label='Infectious Severe Isolated (18-64)')
plt.plot(time, Infectious_severe_isolated_65_plus_history, label='Infectious Severe Isolated (65+)')
plt.plot(time, Isolated_0_17_history, label='Isolated (0-17)')
plt.plot(time, Isolated_18_64_history, label='Isolated (18-64)')
plt.plot(time, Isolated_65_plus_history, label='Isolated (65+)')
plt.plot(time, Admitted_to_hospital_0_17_history, label='Admitted to Hospital (0-17)')
plt.plot(time, Admitted_to_hospital_18_64_history, label='Admitted to Hospital (18-64)')
plt.plot(time, Admitted_to_hospital_65_plus_history, label='Admitted to Hospital (65+)')
plt.plot(time, ICU_0_17_history, label='ICU (0-17)')
plt.plot(time, ICU_18_64_history, label='ICU (18-64)')
plt.plot(time, ICU_65_plus_history, label='ICU (65+)')
plt.plot(time, Admitted_to_hospital_post_ICU_0_17_history, label='Admitted to Hospital Post ICU (0-17)')
plt.plot(time, Admitted_to_hospital_post_ICU_18_64_history, label='Admitted to Hospital Post ICU (18-64)')
plt.plot(time, Admitted_to_hospital_post_ICU_65_plus_history, label='Admitted to Hospital Post ICU (65+)')
plt.plot(time, Recovered_0_17_history, label='Recovered (0-17)')
plt.plot(time, Recovered_18_64_history, label='Recovered (18-64)')
plt.plot(time, Recovered_65_plus_history, label='Recovered (65+)')
plt.plot(time, COVID_Deaths_0_17_history, label='COVID Deaths (0-17)')
plt.plot(time, COVID_Deaths_18_64_history, label='COVID Deaths (18-64)')
plt.plot(time, COVID_Deaths_65_plus_history, label='COVID Deaths (65+)')

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