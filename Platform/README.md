# EpiMDE: Epidemiological Model-Driven Engineering Framework

**Compartmental workspace** — This folder is the Eclipse/EMF implementation: a single guide for **epidemiological** compartmental models (`compartmental.ecore`), the **`sample.compmodel`** reference, **EMF + Sirius** workflows, generators, and utilities.

EpiMDE applies **Model-Driven Engineering** to epidemic modeling: a structured **CompartmentalModel** (compartments, flows, stratification, parameters) drives **diagrams**, **equation generation**, and optional **numerical simulation**, so model structure can stay aligned with papers and be reused under different parameter sets.

### Key features

| Area | What you get |
|------|----------------|
| **Symbolic / parametric modeling** | **`Parameter`** with types **`CONSTANT`**, **`VARIABLE`**, **`EXPRESSION`**; flows can use **`rateParameter`**, **`contactRateParameter`**, and stratum **`rateParameter`** / **`multiplierParameter`** instead of bare numbers. |
| **Graphical and textual editing** | Sirius diagrams (**`CompartmentalModel.design`**), EMF tree editor, raw XMI in **`.compmodel`**. |
| **Population stratification** | **`Group`**, **`Product`** (Cartesian products), **`StratumSpecificRate`**, stratified **`externalSources`** / **`externalSinks`**. |
| **Equation generation** | **`CompartmentalEquationGenerator.java`** produces ODE-style text from **`.compmodel`** (aggregate and, when stratified, a `_stratified.txt` file). |
| **Python simulation** | **`CompartmentalModel/simulation/simulation.py`** — interactive numerical integration from a generated equation file (see limitations below). |
| **Model splitting** | **`DynamicDiagramGenerator.java`** emits per-stratum **`.compmodel`** files; run from Eclipse (see [§13](#13-model-splitting-utilities)). |

---

## Table of contents

1. [Scope](#1-scope)
2. [Role in the repository](#2-role-in-the-repository)
3. [Metamodel essentials (epidemiology)](#3-metamodel-essentials-epidemiology)
4. [Eclipse workspace layout](#4-eclipse-workspace-layout)
5. [Model files and naming](#5-model-files-and-naming)
6. [Reference model: `sample.compmodel`](#6-reference-model-samplecompmodel)
7. [Modeling in Eclipse](#7-modeling-in-eclipse)
8. [Other bundled disease examples](#8-other-bundled-disease-examples)
9. [Parameter system (symbolic and parametric)](#9-parameter-system-symbolic-and-parametric)
10. [Setup and requirements](#10-setup-and-requirements)
11. [Equation generation (Java)](#11-equation-generation-java)
12. [Python simulation](#12-python-simulation)
13. [Model splitting utilities](#13-model-splitting-utilities)
14. [Troubleshooting and diagrams](#14-troubleshooting-and-diagrams)

---

## 1. Scope

This folder is the **EpiMDE** Eclipse/EMF workspace: **compartmental epidemic models** (SIR/SEIR/variants, stratification, parameters), visual and tree editors, and example **`.compmodel`** files.

For **disease models**, use ordinary compartments and flows only:

- Do **not** attach **`SupplyFunction`** to compartments.
- Keep **`JunctionRuleType`** at **`NONE`** (default).

---

## 2. Role in the repository

| Location | Purpose |
|----------|---------|
| **`Compartmental/`** | EMF **metamodel** (`compartmental.ecore`), editors, Sirius diagrams, hand-authored or IDE-edited **`.compmodel`**. |
| **`AI-ASSISTED MODEL-DRIVEN EPIDEMIOLOGY/`** | Python pipeline: papers → draft **`.compmodel`** → gap fill → Phase 4 UQ. Same **conceptual** elements (compartments, flows, parameters). |

You do **not** need Eclipse to run the AI-assisted pipeline; you **do** need this metamodel (or compatible XMI) for IDE editing, symbolic parameters, or external generators that consume the same format.

---

## 3. Metamodel essentials (epidemiology)

Root element: **`CompartmentalModel`** (`CompartmentalModel/compartmental.ecore`).

| Concept | Epidemic role |
|---------|----------------|
| **`Compartment`** | Disease state (e.g. Susceptible, Exposed, Infectious, Recovered, Death). **`population`**: initial count; optional **`product`** for stratification. |
| **`RateFlow`** | Transitions proportional to **source** only (incubation, recovery, progression). ~`rate × source`; optional **`rateParameter`**; **`stratumSpecificRates`**. |
| **`ContactFlow`** | Transmission: depends on **source** and **`contactCompartment`** (e.g. infectious). Mass-action style: ~`contactRate × S × I / N` (details depend on the simulator). |
| **`ExternalSource`** | Inflow (e.g. births into Susceptible). **`targetCompartment`**, optional **`targetStratum`**. In Eclipse the tree may label these “Birth Sources”. |
| **`ExternalSink`** | Outflow (e.g. natural death). **`sourceCompartment`**, optional **`sourceStratum`**. Tree may say “Death Sinks”. |
| **`Group`** | One stratification dimension; **`values`**: category labels. |
| **`Product`** | Cartesian product of referenced **`Group`**s → strata like `0-17,Male`. |
| **`Parameter`** | **`CONSTANT`**, **`VARIABLE`**, or **`EXPRESSION`** — symbolic names, units, Greek labels. |
| **Root** | **`totalPopulation`**, optional **`globalBirthRate`** / **`globalDeathRate`**. |

Supported epidemic patterns: **SIR**, **SEIR**, extra compartments (hospitalized, quarantined), **vector-borne** structures (multiple compartments + contact flows), **stratified** demography.

---

## 4. Eclipse workspace layout

| Project | Role |
|---------|------|
| **`CompartmentalModel/`** | `compartmental.ecore`, `compartmental.genmodel`, generated Java API, **`*.compmodel`** examples. |
| **`CompartmentalModel.edit`** | Item providers (properties / labels). |
| **`CompartmentalModel.editor`** | EMF tree editor plugin. |
| **`CompartmentalModel.design`** | Sirius **`.odesign`** — graphical diagrams. |
| **`CompartmentalModel.tests`** | JUnit tests. |
| **`org.compartmental.targetplatform`** | Target platform. |

---

## 5. Model files and naming

- **`.compmodel`** — Used in this repo for examples and the AI-assisted pipeline (e.g. `sample.compmodel`).
- **`.compartmentalmodel`** — Same EMF/XMI; often used when creating models via the Eclipse **Example EMF Model Creation** wizard.

Both refer to the same **`CompartmentalModel`** root type. The **namespace** in XML is `http://example.com/compartmentalmodel` with prefix commonly **`compartmental:`** (see `sample.compmodel`).

---

## 6. Reference model: `sample.compmodel`

**Path:** [`CompartmentalModel/sample.compmodel`](CompartmentalModel/sample.compmodel)

This is the **canonical teaching example**: a **age × gender** stratified **SEIR**-style model with disease death, recovery, births, and natural mortality. Use it as a template for stratified epidemic XML.

### 6.1 Overview

- **Flow of disease states:** Susceptible → Exposed → Infectious → **Recovered** or **Death** (disease-related).
- **Strata:** Age `{0-17, 18-65, 66+}` × Gender `{Male, Female}` → **6 strata** per compartment.
- **Total population:** 10,000 (see root **`totalPopulation`**).
- **Global rates** on root: `globalBirthRate`, `globalDeathRate` (supplemented by explicit **`externalSources`** / **`externalSinks`** for stratum detail).

### 6.2 Groups and products

- **`Group`**: one dimension (e.g. `Age` with three **`values`**).
- **`Product`**: references **`groups`** and forms the **Cartesian product** — e.g. two groups with 3 and 2 values → 6 combinations.
- **`Compartment`** with **`product`**: population is subdivided into those strata; equations track each stratum separately.

You can define **multiple** `Product`s over the same `Group`s (e.g. age-only vs age×gender) for different compartments if needed (see [§6.8](#68-advanced-mixing-between-strata-multiple-products)).

### 6.3 ContactFlow vs RateFlow

| | **ContactFlow** | **RateFlow** |
|--|-----------------|--------------|
| **Use** | Transmission (contact with infectious / vector) | Incubation, recovery, progression, disease death to `Death` |
| **Depends on** | Source compartment × **`contactCompartment`** | Source compartment only |
| **Typical term** | ~β × S × I / N | ~rate × compartment |

**ContactFlow** on Susceptible points **`target`** to Exposed and **`contactCompartment`** to Infectious.

### 6.4 Stratum-specific rates

- **Stratum key:** comma-separated, **same order as groups in the product**, e.g. `0-17,Male`.
- **Absolute `rate`:** explicit value per stratum (good when data are per group).
- **`multiplier`:** scales the flow’s base **`contactRate`** or base **`rate`** (good for “children have 1.67× contacts”). In `sample.compmodel`, transmission uses **multiplier-only** stratum lines for clarity.

Prefer **one style per flow** (multipliers *or* explicit stratum rates) to avoid confusion.

### 6.5 Walkthrough (structure)

Order in the file (mirrors how you might build it):

1. **Groups** — Age, Gender.
2. **Product** — `AgeAndGenderStratification` linking both groups.
3. **Compartments** — Susceptible (with **ContactFlow** to Exposed; multipliers per stratum); Exposed (**RateFlow** to Infectious; age-specific incubation); Infectious (**RateFlow** to Recovered; **RateFlow** to Death with stratum-specific disease mortality); Recovered; Death (terminal).
4. **`externalSources`** — births into Susceptible, split by **`targetStratum`** (e.g. `0-17,Male` / `0-17,Female`).
5. **`externalSinks`** — natural death from Susceptible, Exposed, Infectious, Recovered; each stratum has its own sink (**`sourceStratum`**).

Terminal compartments (**Recovered**, **Death**) have **no outgoing flows**; they still use **`product`** so initial conditions can be tracked by stratum.

### 6.6 Birth, natural death, and disease death

| Mechanism | Metamodel | Meaning |
|-----------|-----------|---------|
| **Births** | **`ExternalSource`** | New individuals enter **`targetCompartment`** (Susceptible), optional **`targetStratum`**. |
| **Natural death** | **`ExternalSink`** | Removes individuals from **`sourceCompartment`** / **`sourceStratum`** without a “disease death” tally. |
| **Disease death** | **`RateFlow`** from Infectious → **Death** compartment | Counted in **Death**; interpret as disease-specific mortality. |

### 6.7 How tools process this model

Downstream tooling (outside this README) may:

1. **Flatten strata** — e.g. emit one **unstratified** SEIR file per stratum, resolving multipliers into a single numeric **`contactRate`** / **`rate`** and keeping only matching birth/death for that stratum.
2. **Emit equations** — **aggregate** (lumped) and/or **stratified** ODEs; stratified lines use exact stratum names and typically **within-stratum** mixing (e.g. `0-17,Male` infects only the same stratum unless you add cross-stratum flows).

Example stratified term for Susceptible `0-17,Male`:

```text
dS_0_17_Male/dt = +births − β_eff·S·I/N − natural_death
```

(Exact formulas depend on the generator.)

### 6.8 Advanced: mixing between strata, multiple products

- **Within-stratum mixing only** in the sample: each stratum’s infectious pool couples to the same stratum’s susceptibles. **Cross-stratum** mixing requires additional **ContactFlow**s or mixing-matrix style links (more verbose).
- **Multiple products:** e.g. Susceptible stratified by age only, Hospitalized by age×gender — powerful but harder to wire flows consistently.
- **Dynamic stratification** (aging, vaccination switching strata) is **not** in the sample; would need extra flows between strata or external tooling.

### 6.9 Patterns and best practices (summary)

| Pattern | Idea |
|---------|------|
| Age-only SEIR | One group (e.g. 3–5 age bands), strata `0-17`, `18-65`, … |
| Risk groups (HIV / STIs) | Group `High` / `Medium` / `Low` |
| Age × gender | As in **`sample.compmodel`** |
| Multi-dimensional | e.g. age × urban/rural × vaccine — **combinations explode**; add dimensions only with data and interpretability |

**Stratum naming examples:**

| Groups | Example stratum |
|--------|-----------------|
| Age | `18-65` |
| Risk | `High` |
| Age + Gender | `66+,Female` |
| Age + Location + Vaccine | `0-17,Urban,Vaccinated` |

### 6.10 XML quick reference (skeleton)

```xml
<compartmental:CompartmentalModel xmlns:compartmental="http://example.com/compartmentalmodel"
    totalPopulation="10000" globalBirthRate="0.00003" globalDeathRate="0.00002">

  <groups name="Age">...</groups>
  <groups name="Gender">...</groups>
  <products name="AgeAndGenderStratification" groups="//@groups.0 //@groups.1"/>

  <compartments PrimaryName="Susceptible" population="9500" product="//@products.0">
    <outgoingFlows xsi:type="compartmental:ContactFlow"
        contactRate="0.000003" contactCompartment="//@compartments.2"
        target="//@compartments.1">
      <stratumSpecificRates stratum="0-17,Male" multiplier="1.67"/>
      <!-- ... other strata ... -->
    </outgoingFlows>
  </compartments>

  <externalSources name="Natural births (Male)" rate="0.000015"
      targetCompartment="//@compartments.0" targetStratum="0-17,Male"/>

  <externalSinks name="Natural death (Susceptible - 0-17,Male)" rate="0.000012"
      sourceCompartment="//@compartments.0" sourceStratum="0-17,Male"/>

</compartmental:CompartmentalModel>
```

---

## 7. Modeling in Eclipse

### 7.1 Prerequisites

- **Eclipse Modeling Tools** (recommended distribution)
- **EMF**
- **Sirius** (graphical diagrams)

Workspace projects: **`CompartmentalModel`**, **`.edit`**, **`.editor`**, **`.design`**.

### 7.2 Editors and files

| Editor | Role |
|--------|------|
| **Tree (EMF)** | Hierarchy, precise numbers, stratum-specific rates, validation. |
| **Sirius diagram** | Drag compartments, draw flows, visual layout. |

Model data: **`.compmodel`** or **`.compartmentalmodel`** (XML). Diagram layout: **`.aird`** (commit with the model if you use diagrams).

### 7.3 Creating a model

1. **New → Other → Example EMF Model Creation Wizards** — choose the compartmental / SEIR wizard if present (**CompartmentalModel** root).
2. Name the file (e.g. `my_flu_model.compartmentalmodel`).
3. Open with **tree editor** or create **New → Representation → Diagram**.

### 7.4 Tree editor (typical operations)

Under **`CompartmentalModel`**:

- **New Child → Compartments** — set **Primary Name**, **Population**, **Product** if stratified.
- Under a compartment: **Outgoing Flows → Contact Flow** or **Rate Flow** — set rates, **Target**, and for Contact flow **Contact Compartment**.
- **New Child → Groups** / **Values**; **New Child → Products** — link groups.
- **Birth / death** — **New Child** entries that correspond to **`externalSources`** and **`externalSinks`** (labels may say “Birth Sources” / “Death Sinks”).
- On flows: **Stratum Specific Rates** — **Stratum**, **Rate** and/or **Multiplier**.

**Properties** view: **Window → Show View → Properties**. References use **...** to browse. **Ctrl+S** to save.

### 7.5 Sirius diagram (typical operations)

- **Palette**: Compartment nodes, flow edges, birth source nodes, death sink nodes, edges connecting them.
- **Flow edge** → set type **Contact Flow** vs **Rate Flow** in properties.
- Compartment colors often follow first letter (S blue, E orange, I red, R green, etc.).
- **Format → Arrange All** for layout; diagram saves to **`.aird`**.

### 7.6 Simple SEIR flu (minimal checklist)

**Compartments:** Susceptible, Exposed, Infectious, Recovered.

**Flows:**

- Susceptible → Exposed: **ContactFlow**; **Contact Compartment** = Infectious.
- Exposed → Infectious; Infectious → Recovered: **RateFlow**s.

**Demography (optional):** **`externalSources`** to Susceptible; **`externalSinks`** from each living compartment.

Set root **Total Population** and initial compartment populations consistently.

### 7.7 Age-stratified model (checklist)

1. **Groups** + **Values** (e.g. Children / Adults / Elderly).
2. **Product** over the group(s).
3. Assign **Product** on each stratified compartment.
4. Add **stratum-specific rates** on relevant flows.
5. Stratify **externalSources** / **externalSinks** with **targetStratum** / **sourceStratum** as needed.

### 7.8 Tips, validation, troubleshooting

- Use **diagram** for structure, **tree** for stratum and numeric detail.
- Watch for **error markers** on elements; fix missing **Target**, rates, or broken references.
- **Diagram empty / viewpoint**: **Viewpoints Selection** on the model file; enable the project viewpoint; **Refresh** the diagram.
- **Properties empty**: select an element in tree or diagram.
- **Cannot set reference**: create the target compartment or group first.
- **Help**: Eclipse **Sirius User Manual**, **EMF** help; tooltips on properties where available.

**Example models in-repo:** `covid.compmodel`, `HIV.compmodel`, `HIV_Women.compmodel`, `malaria.compmodel`, `zika.compmodel`, `tuberculosis.compmodel`.

---

## 8. Other bundled disease examples

Under **`CompartmentalModel/`**, explore disease-specific **`*.compmodel`** files for structures beyond the tutorial sample (e.g. **COVID**, **HIV** variants, **malaria**, **Zika**, **TB**).

---

## 9. Parameter system (symbolic and parametric)

The metamodel supports **named parameters** so you can match paper notation, separate **structure** from **numbers**, and use **expressions** (e.g. force of infection built from other symbols).

### Types (`ParameterType`)

| Type | Role | Typical use |
|------|------|-------------|
| **CONSTANT** | Named value (often with **`expression`** holding the numeric literal) | `μ`, `γ`, fixed transmission scalars |
| **VARIABLE** | Placeholder for values supplied at simulation time | temperature, seasonality, intervention level |
| **EXPRESSION** | Derived from other parameters / compartment names in **`expression`** | `eta_S * IM`, combined forces of infection |

Each **`Parameter`** can have **`name`**, **`description`**, **`unit`**, and **`expression`** (meaning depends on type; see `compartmental.ecore`).

### Wiring parameters to flows

- **`RateFlow`**: **`rate`** (numeric) and/or **`rateParameter`**
- **`ContactFlow`**: **`contactRate`** and/or **`contactRateParameter`**
- **`StratumSpecificRate`**: **`rate`**, **`rateParameter`**, **`multiplier`**, **`multiplierParameter`**
- **`ExternalSource`** / **`ExternalSink`**: **`rate`** and/or **`rateParameter`**

**Precedence:** When both a numeric attribute and a parameter reference are present, tooling (e.g. the equation generator) should treat the **parameter reference as authoritative** — keep models unambiguous by setting numeric rates to `0` when using parameters only.

### Backward compatibility

Older **`.compmodel`** files without **`parameters`** continue to work: flows use numeric **`rate`** / **`contactRate`** only. Mixed numeric + parameter models should follow one clear convention per flow.

### XML examples (namespace `compartmental:`)

**Numeric (legacy style):** use **`rate="0.2"`** on a **`RateFlow`**.

**Parametric:** define **`parameters`** at the root, then reference them:

```xml
<parameters name="gamma" type="CONSTANT" expression="0.1" description="Recovery rate" unit="per day"/>
<!-- ... -->
<outgoingFlows xsi:type="compartmental:RateFlow" rateParameter="//@parameters.0" target="//@compartments.2"/>
```

Index **`//@parameters.N`** is the Nth parameter in document order (0-based **N** in the path).

---

## 10. Setup and requirements

### Eclipse

- **Eclipse Modeling** distribution (or install **EMF SDK** + **Sirius** manually).
- **Java:** use the JDK version expected by your Eclipse release (often **17+** for current modeling packages).
- **Target platform:** open **`org.compartmental.targetplatform/compartment.target`**, then **Set as Active Target Platform** so EMF, Sirius (**7.4.x** line in this repo’s target), and dependencies resolve consistently.

### Importing the workspace

Use **File → Import → Existing Projects into Workspace** and import at least:

- `CompartmentalModel`, `CompartmentalModel.edit`, `CompartmentalModel.editor`, `CompartmentalModel.design`, `CompartmentalModel.tests`, `org.compartmental.targetplatform`

### Running Java tools from the IDE

- **`CompartmentalEquationGenerator`**: run as **Java Application** with working directory **`CompartmentalModel`** (see [§11](#11-equation-generation-java)).
- **`DynamicDiagramGenerator`**: same project, working directory **`CompartmentalModel`** ([§13](#13-model-splitting-utilities)).

---

## 11. Equation generation (Java)

**Class:** [`CompartmentalModel/src/compartmental/equationgenerator/CompartmentalEquationGenerator.java`](CompartmentalModel/src/compartmental/equationgenerator/CompartmentalEquationGenerator.java)

1. Run **`main`** as a **Java Application** (Eclipse: **Run As → Java Application**).
2. When prompted, enter a **`.compmodel`** filename (e.g. `covid.compmodel`) relative to the **working directory** (use **`CompartmentalModel`** as cwd so the file is found next to the generator).
3. **Outputs (typical)** for **epidemiological** **`.compmodel`** files:
   - A file named like **`covid.txt`** (same base name as the model, extension **`.txt`**) — aggregate equations.
   - If the model has **`products`** / stratification: also a **`_stratified.txt`** sibling (e.g. **`covid_stratified.txt`**) with expanded stratum equations.

**Symbolic output:** when flows use **`Parameter`** references, equations should show parameter **names** (e.g. `gamma * Infectious`) rather than only numeric literals, depending on the implementation in `getParameterRepresentation` and related helpers.

---

## 12. Python simulation

**Script:** [`CompartmentalModel/simulation/simulation.py`](CompartmentalModel/simulation/simulation.py)

1. Generate an equation **`.txt`** file with **`CompartmentalEquationGenerator`** (or compatible format).
2. From a shell, **`cd`** to **`CompartmentalModel/simulation/`** (or pass paths accordingly).
3. Run **`python3 simulation.py`** and follow prompts: equation file name, which compartments to include, initial values, horizon in **years**.

**Behavior:** uses a fixed step (**`dt = 0.01`**) and simple parsing of the equation string. The source notes **limitations** parsing **contact-based** terms; validate against your equation file format before relying on results for research.

---

## 13. Model splitting utilities

**Class:** [`CompartmentalModel/src/compartmental/utilities/DynamicDiagramGenerator.java`](CompartmentalModel/src/compartmental/utilities/DynamicDiagramGenerator.java)

- **Purpose:** “Group-aware” splitting of a stratified **`.compmodel`**: detects **`groups`** / **`values`**, emits **separate** **`.compmodel`** files per stratum (or per combination, depending on logic), resolves **`stratumSpecificRates`**, and filters **`externalSources`** / **`externalSinks`** by stratum.
- **Run (recommended):** **Run As → Java Application** from Eclipse on **`DynamicDiagramGenerator`**, with working directory **`CompartmentalModel`** and a prompt/argument for the **`.compmodel`** path (see **`main`** usage text in the source).
- **Shell helpers:** [`run_splitter.sh`](CompartmentalModel/run_splitter.sh) / [`run_splitter.bat`](CompartmentalModel/run_splitter.bat) may still reference an older **`seir`** package layout; if they fail, run the class from Eclipse or update the script classpath to **`compartmental.utilities.DynamicDiagramGenerator`** and **`.compmodel`**.

Use this when you want **one flat model per demographic slice** for visualization, testing, or external tools that do not read stratified products directly.

---

## 14. Troubleshooting and diagrams

### Flows or labels missing in Sirius

- Ensure **Flow** mappings in **`.odesign`** target the metamodel’s **`Flow`** subtypes (**`RateFlow`**, **`ContactFlow`**) and **`target`** references.
- **Semantic candidates** often use something like **`aql:self.compartments.outgoingFlows`** from the root or compartment node (inspect **`CompartmentalModel.design`** for the live definition).

### Edge label shows literal `self.rate` / AQL text

- Sirius **label expressions** should use **AQL** where configured; in **Preferences → Sirius**, confirm the **AQL** interpreter.
- For parametric models, labels may need expressions that read **`rateParameter`** when **`rate`** is unset.

### Viewpoint missing

- **Right-click** the **`.compmodel`** / representation → **Viewpoints Selection** → enable the project’s viewpoint.
- Ensure **`CompartmentalModel.design`** plugin is in the launch configuration if using a **runtime workbench**.

### Export diagram as image

- Use the diagram toolbar **Export as image** (PNG/SVG), or **right-click** the diagram surface → export options (depends on Eclipse/Sirius version).

---

