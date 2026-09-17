import os



def parse_equations(file_path):
    with open(file_path, 'r') as f:
        lines = f.readlines()
    print("The compartments in the model are:")
    for line in lines:
        if ':' in line and '=' in line:
            name = line.split(':')[0].strip()
            eq = line.split('=')[1].strip()
            compartments.append({"name": name, "population": 0, "equation_string": eq})
            print(f"{name}")

def get_selected_compartments():
    print("\nChoose compartments to simulate:")
    # Create a list of compartments to remove
    to_remove = []
    for comp in compartments:
        while True:
            choice = input(f"Include '{comp['name']}'? (y/n): ").strip().lower()
            if choice in ['y', 'n']:
                if choice == 'n':
                    to_remove.append(comp)
                break
            print("Invalid choice, please enter 'y' or 'n'.")
    
    # Remove unwanted compartments after the iteration
    for comp in to_remove:
        compartments.remove(comp)
    

def get_initial_values():
    print("\nProvide initial population values (non-negative):")
    for comp in compartments:
        while True:
            try:
                val = float(input(f"Initial value for '{comp['name']}': "))
                if val < 0:
                    raise ValueError("Value must be non-negative.")
                comp['population'] = val
                break
            except ValueError as e:
                print(e)
    

def simulate(years):
    data = ""
    dt = 0.01
    for step in range(int((years+1) * 100)):
        for comp in compartments:
            eq = comp['equation_string']
            parts = []
            current = eq[0]
            bracket_count = 0
            
            #NOTE: this for loop takes care of contact-based equation
            for char in eq[1:]:
                if char == '(':
                    bracket_count += 1
                    current += char
                elif char == ')':
                    bracket_count -= 1
                    current += char
                elif char in '+-' and bracket_count == 0 and (not current or current[-1] != 'E'):
                    if current.strip():
                        parts.append(current.strip())
                    current = char 
                else:
                    current += char
            
            if current.strip():
                parts.append(current.strip())

            # NOTE: this loop doesnt handle contact-based equations only batch and rate-based equations
            population_change = 0.0
            for part in parts:
                if '*' in part:
                    factors = part.split('*')
                else:
                    factors = [part]

                result = 1.0
                found = False
                for factor in factors:
                    factor = factor.strip()
                    try:
                        result *= float(factor[1:] if factor[0] in ['-', '+'] else factor)
                    except ValueError:
                        for c in compartments:
                            if c['name'].strip() == factor:
                                result *= c['population']
                                found = True
                                break
                if not found:
                    result *= 1.0  # Default to 1.0 if not found

                # Apply sign after full multiplication
                if part.strip().startswith('-'):
                    population_change -= result
                else:
                    population_change += result
            comp['population'] += population_change * dt
        
        if step % 100 == 0:
            # Add headers if this is the first step
            if step == 0:
                data = "Year," + ",".join(f'"{comp['name']}"' for comp in compartments) + "\n"
            data += f"{step * dt:.2f}," + ",".join(f"{comp['population']:.2f}" for comp in compartments) + "\n"
    return data

filename = input("Enter .txt file name (e.g. HIV or HIV.txt): ").strip()
if not filename.endswith('.txt'):
    filename += '.txt'
try:
    #step 1: Get the txt file  
    script_dir = os.path.dirname(__file__) 
    file_path = os.path.join(script_dir, "..", filename)    

    compartments = []  # List to hold compartment data

    parse_equations(file_path)
    get_selected_compartments()
    get_initial_values()
    years = float(input("Simulate how many years?: "))
    data = simulate(years)
    csv_filename = os.path.splitext(filename)[0] + '.csv'
    csv_path = os.path.join(script_dir, "..", csv_filename)
    
    with open(csv_path, 'w') as f:
        f.write(data)
    print(f"Results saved to {csv_filename}")
except FileNotFoundError:
    print("❌ File not found.")
except Exception as ex:
    print(f"❌ Error occurred: {ex}")
