package compartmental.equationgenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import compartmentalmodel.StratumSpecificRate;
import compartmentalmodel.ExternalSource;
import compartmentalmodel.Compartment;
import compartmentalmodel.ContactFlow;
import compartmentalmodel.ExternalSink;
import compartmentalmodel.Flow;
import compartmentalmodel.Group;
import compartmentalmodel.Parameter;
import compartmentalmodel.Product;
import compartmentalmodel.RateFlow;
import compartmentalmodel.CompartmentalModel;
import compartmentalmodel.CompartmentalmodelPackage;

public class CompartmentalEquationGenerator {
    public static void main(String[] args) {
        initializeEMF();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the .compmodel file (e.g. covid.compmodel): ");
        String fileName = scanner.nextLine().trim();
        scanner.close();

        String modelPath = System.getProperty("user.dir") + "/" + fileName;

        CompartmentalModel model = loadCompartmentalModel(modelPath);
        if (model == null) {
            System.out.println("❌ Failed to load compartmental model.");
            return;
        }

        // Detect model type
        boolean isTRM = isTRMModel(model);
        boolean hasTraffic = hasTrafficFeatures(model);

        if (isTRM) {
            // Generate TRM equations
            System.out.println("🚗 Traffic Reaction Model (TRM) detected!");
            String outputFileName = fileName.replace(".compmodel", "_equations.txt");
            generateTRMEquations(model, outputFileName);
        } else if (hasTraffic) {
            // Generate classical traffic equations (if implemented)
            System.out.println("🚗 Classical Traffic Network detected!");
            System.out.println("⚠️  Classical traffic equation generation not yet fully implemented in Java.");
            System.out.println("   Use Python simulation: python3 traffic_network.py");
            System.out.println("   For now, generating basic disease-model-style equations...\n");

            // Fall through to disease model generation for now
            Map<String, String> equations = generateEquations(model);
            System.out.println("✅ Generated Equations:");
            equations.forEach((compartment, equation) -> System.out.println(equation));
            String outputFileName = fileName.replace(".compmodel", ".txt");
            saveEquationsToFile(equations, outputFileName);
        } else {
            // Generate disease model equations (existing logic)
            Map<String, String> equations = generateEquations(model);

            // Also generate stratified equations if products exist
            if (!model.getProducts().isEmpty()) {
                System.out.println("\n🔍 Stratification detected! Generating expanded equations...");
                Map<String, String> stratifiedEquations = generateStratifiedEquations(model);
                System.out.println("✅ Generated Stratified Compartmental Model Equations:");
                stratifiedEquations.forEach((compartment, equation) -> System.out.println(equation));

                String stratifiedOutputFileName = fileName.replace(".compmodel", "_stratified.txt");
                saveEquationsToFile(stratifiedEquations, stratifiedOutputFileName);
            }

            System.out.println("✅ Generated Compartmental Model Equations:");
            equations.forEach((compartment, equation) -> System.out.println(equation));

            String outputFileName = fileName.replace(".compmodel", ".txt");
            saveEquationsToFile(equations, outputFileName);
        }
    }

    private static void initializeEMF() {
        try {
            Class.forName("org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl");
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("compmodel", new XMIResourceFactoryImpl());
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Unable to load EMF XMI Factory.");
            e.printStackTrace();
        }
    }

    private static CompartmentalModel loadCompartmentalModel(String filePath) {
        File modelFile = new File(filePath);
        if (!modelFile.exists()) {
            System.err.println("❌ Error: Model file not found at " + filePath);
            return null;
        }

        try {
            EPackage.Registry.INSTANCE.put(CompartmentalmodelPackage.eNS_URI, CompartmentalmodelPackage.eINSTANCE);
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.getResource(URI.createFileURI(modelFile.getAbsolutePath()), true);
            resource.load(null);
            return (CompartmentalModel) resource.getContents().get(0);
        } catch (IOException | IndexOutOfBoundsException e) {
            System.err.println("❌ Error: Failed to load compartmental model.");
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, String> generateEquations(CompartmentalModel model) {
        Map<String, String> equations = new HashMap<>();

        for (Compartment compartment : model.getCompartments()) {
            String primaryName = compartment.getPrimaryName();
            String secondaryName = compartment.getSecondaryName() != null ? compartment.getSecondaryName() : "";
            String displayName = primaryName + (secondaryName.isEmpty() ? "" : " (" + secondaryName + ")");
            StringBuilder equation = new StringBuilder("d" + displayName + "/dt = ");

            // Incoming flows to this compartment
            for (Compartment source : model.getCompartments()) {
                for (Flow flow : source.getOutgoingFlows()) {
                    if (flow.getTarget() == compartment) {
                        String sourceName = source.getPrimaryName();
                        String sourceSecondary = source.getSecondaryName();
                        String sourceDisplay = sourceName + (sourceSecondary != null && !sourceSecondary.isEmpty() ? " (" + sourceSecondary + ")" : "");
                        
                        if (flow instanceof ContactFlow) {
                            ContactFlow contactFlow = (ContactFlow) flow;
                            Compartment contactCompartment = contactFlow.getContactCompartment();
                            String contactName = contactCompartment.getPrimaryName();
                            String contactSecondary = contactCompartment.getSecondaryName();
                            String contactDisplay = contactName + (contactSecondary != null && !contactSecondary.isEmpty() ? " (" + contactSecondary + ")" : "");

                            String contactRateExpr = getContactRateExpression(contactFlow, "");
                            equation.append("+ (").append(contactRateExpr)
                                    .append(" * ").append(sourceDisplay)
                                    .append(" * ").append(contactDisplay)
                                    .append(" / ").append(model.getTotalPopulation()).append(") ");
                        } else if (flow instanceof RateFlow) {
                            RateFlow rateFlow = (RateFlow) flow;
                            String rateExpr = getRateExpression(rateFlow, "");
                            equation.append("+ ").append(rateExpr).append(" * ").append(sourceDisplay).append(" ");
                        }
                    }
                }
            }

            // Birth sources flowing into this compartment
            for (ExternalSource externalSource : model.getExternalSources()) {
                if (externalSource.getTargetCompartment() == compartment) {
                    String birthRateExpr = getExternalSourceRateExpression(externalSource);
                    if (isFixedRateExternalSource(externalSource)) {
                        equation.append("+ ").append(birthRateExpr).append(" ");
                    } else {
                        equation.append("+ ").append(birthRateExpr).append(" * ").append(model.getTotalPopulation()).append(" ");
                    }
                }
            }

            // Outgoing flows from this compartment
            for (Flow flow : compartment.getOutgoingFlows()) {
                if (flow instanceof ContactFlow) {
                    ContactFlow contactFlow = (ContactFlow) flow;
                    Compartment contactCompartment = contactFlow.getContactCompartment();
                    String contactName = contactCompartment.getPrimaryName();
                    String contactSecondary = contactCompartment.getSecondaryName();
                    String contactDisplay = contactName + (contactSecondary != null && !contactSecondary.isEmpty() ? " (" + contactSecondary + ")" : "");

                    String contactRateExpr = getContactRateExpression(contactFlow, "");
                    equation.append("- (").append(contactRateExpr)
                            .append(" * ").append(displayName)
                            .append(" * ").append(contactDisplay)
                            .append(" / ").append(model.getTotalPopulation()).append(") ");
                } else if (flow instanceof RateFlow) {
                    RateFlow rateFlow = (RateFlow) flow;
                    String rateExpr = getRateExpression(rateFlow, "");
                    equation.append("- ").append(rateExpr).append(" * ").append(displayName).append(" ");
                }
            }

            // Death sinks flowing out of this compartment
            for (ExternalSink externalSink : model.getExternalSinks()) {
                if (externalSink.getSourceCompartment() == compartment) {
                    String deathRateExpr = getExternalSinkRateExpression(externalSink);
                    equation.append("- ").append(deathRateExpr).append(" * ").append(displayName).append(" ");
                }
            }

            equations.put(displayName, equation.toString());
        }

        return equations;
    }

    private static Map<String, String> generateStratifiedEquations(CompartmentalModel model) {
        Map<String, String> stratifiedEquations = new HashMap<>();
        
        for (Compartment compartment : model.getCompartments()) {
            Product product = compartment.getProduct();
            if (product == null) {
                // Non-stratified compartment - generate single equation
                String equation = generateCompartmentEquation(compartment, model, "");
                stratifiedEquations.put(getCompartmentDisplayName(compartment, ""), equation);
            } else {
                // Stratified compartment - expand into all product combinations
                List<String> strataCombinations = generateStrataCombinations(product);
                for (String stratum : strataCombinations) {
                    String equation = generateCompartmentEquation(compartment, model, stratum);
                    stratifiedEquations.put(getCompartmentDisplayName(compartment, stratum), equation);
                }
            }
        }
        
        return stratifiedEquations;
    }

    private static List<String> generateStrataCombinations(Product product) {
        List<String> combinations = new ArrayList<>();
        combinations.add(""); // Start with empty combination

        for (Group group : product.getGroups()) {
            List<String> newCombinations = new ArrayList<>();
            for (String combination : combinations) {
                for (String value : group.getValues()) {
                    // Use comma as separator to match model format (e.g., "0-17,Male")
                    String newCombination = combination.isEmpty() ? value : combination + "," + value;
                    newCombinations.add(newCombination);
                }
            }
            combinations = newCombinations;
        }

        return combinations;
    }

    private static String getCompartmentDisplayName(Compartment compartment, String stratum) {
        String primaryName = compartment.getPrimaryName();
        String secondaryName = compartment.getSecondaryName() != null ? compartment.getSecondaryName() : "";
        String baseName = primaryName + (secondaryName.isEmpty() ? "" : " (" + secondaryName + ")");
        if (stratum.isEmpty()) {
            return baseName;
        }
        // Replace commas with underscores for display/file naming
        String displayStratum = stratum.replace(",", "_");
        return baseName + "_" + displayStratum;
    }

    private static String generateCompartmentEquation(Compartment compartment, CompartmentalModel model, String stratum) {
        String displayName = getCompartmentDisplayName(compartment, stratum);
        StringBuilder equation = new StringBuilder("d" + displayName + "/dt = ");

        // Incoming flows to this compartment (only from matching strata)
        for (Compartment source : model.getCompartments()) {
            for (Flow flow : source.getOutgoingFlows()) {
                if (flow.getTarget() == compartment) {
                    // Only include flows if source and target have compatible stratification
                    if (areCompatibleStrata(source, compartment, stratum)) {
                        String sourceDisplay = getCompartmentDisplayName(source, stratum);
                        
                        if (flow instanceof ContactFlow) {
                            ContactFlow contactFlow = (ContactFlow) flow;
                            Compartment contactCompartment = contactFlow.getContactCompartment();
                            
                            // CRITICAL: Only include if this flow applies to the current stratum
                            if (isFlowApplicableToStratum(contactFlow, stratum) && areCompatibleStrata(contactCompartment, compartment, stratum)) {
                                String contactDisplay = getCompartmentDisplayName(contactCompartment, stratum);

                                // Get stratum-specific rate expression or default rate expression
                                String contactRateExpr = getContactRateExpression(contactFlow, stratum);
                                String multiplierExpr = getMultiplierExpression(contactFlow, stratum);

                                if (!multiplierExpr.equals("1.0") && !multiplierExpr.equals("1")) {
                                    equation.append("+ (").append(contactRateExpr).append(" * ").append(multiplierExpr)
                                            .append(" * ").append(sourceDisplay)
                                            .append(" * ").append(contactDisplay)
                                            .append(" / ").append(model.getTotalPopulation()).append(") ");
                                } else {
                                    equation.append("+ (").append(contactRateExpr)
                                            .append(" * ").append(sourceDisplay)
                                            .append(" * ").append(contactDisplay)
                                            .append(" / ").append(model.getTotalPopulation()).append(") ");
                                }
                            }
                        } else if (flow instanceof RateFlow) {
                            RateFlow rateFlow = (RateFlow) flow;
                            // Get stratum-specific rate expression or default rate expression
                            String rateExpr = getRateExpression(rateFlow, stratum);
                            equation.append("+ ").append(rateExpr).append(" * ").append(sourceDisplay).append(" ");
                        }
                    }
                }
            }
        }

        // Birth sources flowing into this compartment (only for matching strata)
        for (ExternalSource externalSource : model.getExternalSources()) {
            if (externalSource.getTargetCompartment() == compartment) {
                // Check if this birth source applies to the current stratum
                if (isExternalSourceApplicableToStratum(externalSource, stratum)) {
                    String birthRateExpr = getExternalSourceRateExpression(externalSource);

                    // Check if this is a fixed rate or population-based rate
                    if (isFixedRateExternalSource(externalSource)) {
                        // Fixed rate - don't multiply by population
                        equation.append("+ ").append(birthRateExpr).append(" ");
                    } else {
                        // Population-based rate - multiply by total population (legacy behavior)
                        equation.append("+ ").append(birthRateExpr).append(" * ").append(model.getTotalPopulation()).append(" ");
                    }
                }
            }
        }

        // Outgoing flows from this compartment
        for (Flow flow : compartment.getOutgoingFlows()) {
            if (flow instanceof ContactFlow) {
                ContactFlow contactFlow = (ContactFlow) flow;

                // CRITICAL: Only include if this flow applies to the current stratum
                if (isFlowApplicableToStratum(contactFlow, stratum)) {
                    Compartment contactCompartment = contactFlow.getContactCompartment();
                    String contactDisplay = getCompartmentDisplayName(contactCompartment, stratum);

                    // Get stratum-specific rate expression or default rate expression
                    String contactRateExpr = getContactRateExpression(contactFlow, stratum);
                    String multiplierExpr = getMultiplierExpression(contactFlow, stratum);

                    if (!multiplierExpr.equals("1.0") && !multiplierExpr.equals("1")) {
                        equation.append("- (").append(contactRateExpr).append(" * ").append(multiplierExpr)
                                .append(" * ").append(displayName)
                                .append(" * ").append(contactDisplay)
                                .append(" / ").append(model.getTotalPopulation()).append(") ");
                    } else {
                        equation.append("- (").append(contactRateExpr)
                                .append(" * ").append(displayName)
                                .append(" * ").append(contactDisplay)
                                .append(" / ").append(model.getTotalPopulation()).append(") ");
                    }
                }
            } else if (flow instanceof RateFlow) {
                RateFlow rateFlow = (RateFlow) flow;

                // For RateFlow, check if it has stratum-specific rates and if they apply
                if (isFlowApplicableToStratum(rateFlow, stratum)) {
                    // Get stratum-specific rate expression or default rate expression
                    String rateExpr = getRateExpression(rateFlow, stratum);
                    equation.append("- ").append(rateExpr).append(" * ").append(displayName).append(" ");
                }
            }
        }

        // Death sinks flowing out of this compartment (with stratum matching)
        for (ExternalSink externalSink : model.getExternalSinks()) {
            if (externalSink.getSourceCompartment() == compartment) {
                // Check if death sink applies to this stratum
                if (isExternalSinkApplicableToStratum(externalSink, stratum)) {
                    String deathRateExpr = getExternalSinkRateExpression(externalSink);
                    equation.append("- ").append(deathRateExpr).append(" * ").append(displayName).append(" ");
                }
            }
        }

        return equation.toString();
    }

    private static double getStratumSpecificRate(ContactFlow contactFlow, String stratum, double defaultRate) {
        if (stratum.isEmpty() || contactFlow.getStratumSpecificRates().isEmpty()) {
            return defaultRate;
        }

        // Try exact match first (for Cartesian products like "0-17,Male")
        for (StratumSpecificRate stratumRate : contactFlow.getStratumSpecificRates()) {
            if (stratum.equals(stratumRate.getStratum())) {
                // If rate is 0, use multiplier instead
                if (stratumRate.getRate() == 0.0 && stratumRate.getMultiplier() != 0.0) {
                    return defaultRate * stratumRate.getMultiplier();
                }
                return stratumRate.getRate();
            }
        }

        return defaultRate;
    }

    private static double getStratumSpecificRate(RateFlow rateFlow, String stratum, double defaultRate) {
        if (stratum.isEmpty() || rateFlow.getStratumSpecificRates().isEmpty()) {
            return defaultRate;
        }

        // Try exact match first (for Cartesian products like "0-17,Male")
        for (StratumSpecificRate stratumRate : rateFlow.getStratumSpecificRates()) {
            if (stratum.equals(stratumRate.getStratum())) {
                // If rate is 0, use multiplier instead
                if (stratumRate.getRate() == 0.0 && stratumRate.getMultiplier() != 0.0) {
                    return defaultRate * stratumRate.getMultiplier();
                }
                return stratumRate.getRate();
            }
        }

        return defaultRate;
    }

    private static double getStratumSpecificMultiplier(ContactFlow contactFlow, String stratum) {
        if (stratum.isEmpty() || contactFlow.getStratumSpecificRates().isEmpty()) {
            return 1.0; // Default multiplier (no effect)
        }

        // Try exact match (for Cartesian products like "0-17,Male")
        for (StratumSpecificRate stratumRate : contactFlow.getStratumSpecificRates()) {
            if (stratum.equals(stratumRate.getStratum())) {
                return stratumRate.getMultiplier();
            }
        }

        return 1.0; // Default multiplier (no effect)
    }

    /**
     * Check if two compartments have compatible stratification for the given stratum.
     * Compatible means:
     * 1. Both are non-stratified (product == null)
     * 2. Both have the same product (same stratification scheme)
     * 3. One is stratified and the other is not (mixed interaction)
     */
    private static boolean areCompatibleStrata(Compartment source, Compartment target, String stratum) {
        Product sourceProduct = source.getProduct();
        Product targetProduct = target.getProduct();
        
        // If stratum is empty, we're generating non-stratified equations
        if (stratum.isEmpty()) {
            return sourceProduct == null && targetProduct == null;
        }
        
        // For stratified equations, only include if both compartments have the same stratification
        // or if one is stratified and the other is not (for mixed interactions)
        if (sourceProduct != null && targetProduct != null) {
            return sourceProduct == targetProduct; // Same stratification scheme
        } else if (sourceProduct == null && targetProduct != null) {
            return false; // Non-stratified source to stratified target (skip to avoid duplication)
        } else if (sourceProduct != null && targetProduct == null) {
            return false; // Stratified source to non-stratified target (skip to avoid duplication)
        } else {
            return true; // Both non-stratified
        }
    }

    /**
     * Check if a flow applies to the given stratum.
     * A flow applies if:
     * 1. It has no stratum-specific rates (applies to all)
     * 2. It has stratum-specific rates and one matches the current stratum
     */
    private static boolean isFlowApplicableToStratum(ContactFlow contactFlow, String stratum) {
        // If no stratum specified, apply to all non-stratified
        if (stratum.isEmpty()) {
            return true;
        }

        // If flow has no stratum-specific rates, it applies to all
        if (contactFlow.getStratumSpecificRates().isEmpty()) {
            return true;
        }

        // Check if any stratum-specific rate exactly matches the current stratum (e.g., "0-17,Male")
        for (StratumSpecificRate stratumRate : contactFlow.getStratumSpecificRates()) {
            if (stratum.equals(stratumRate.getStratum())) {
                return true;
            }
        }

        // Flow has stratum-specific rates but none match - doesn't apply
        return false;
    }

    /**
     * Check if a RateFlow applies to the given stratum.
     */
    private static boolean isFlowApplicableToStratum(RateFlow rateFlow, String stratum) {
        // If no stratum specified, apply to all non-stratified
        if (stratum.isEmpty()) {
            return true;
        }

        // If flow has no stratum-specific rates, it applies to all strata
        if (rateFlow.getStratumSpecificRates().isEmpty()) {
            return true;
        }

        // Check if any stratum-specific rate exactly matches the current stratum (e.g., "0-17,Male")
        for (StratumSpecificRate stratumRate : rateFlow.getStratumSpecificRates()) {
            if (stratum.equals(stratumRate.getStratum())) {
                return true;
            }
        }

        // Flow has stratum-specific rates but none match - doesn't apply
        return false;
    }

    /**
     * Check if a birth source applies to the given stratum.
     * Uses the targetStratum attribute if available, otherwise applies to all.
     */
    private static boolean isExternalSourceApplicableToStratum(ExternalSource externalSource, String stratum) {
        // If no stratum specified, birth source applies to non-stratified compartments
        if (stratum.isEmpty()) {
            return true;
        }

        // Extract targetStratum from EMF object using reflection
        String targetStratum = externalSource.getTargetStratum();

        if (targetStratum != null && !targetStratum.isEmpty()) {
            // Exact match for Cartesian products (e.g., "0-17,Male")
            return targetStratum.equals(stratum);
        }

        // If no targetStratum specified, don't apply to stratified compartments (avoid duplication)
        return false;
    }

    /**
     * Check if a death sink applies to the given stratum.
     * Uses the sourceStratum attribute if available, otherwise applies to all.
     */
    private static boolean isExternalSinkApplicableToStratum(ExternalSink externalSink, String stratum) {
        // If no stratum specified, death sink applies to non-stratified compartments
        if (stratum.isEmpty()) {
            return true;
        }

        // Extract sourceStratum from death sink
        String sourceStratum = externalSink.getSourceStratum();

        if (sourceStratum != null && !sourceStratum.isEmpty()) {
            // Exact match for Cartesian products (e.g., "0-17,Male")
            return sourceStratum.equals(stratum);
        }

        // If no sourceStratum specified, don't apply to stratified compartments (avoid duplication)
        return false;
    }


    /**
     * Check if a birth source uses fixed rate (not population-based)
     * Tries to extract fixedRate attribute from EMF object
     */
    private static boolean isFixedRateExternalSource(ExternalSource externalSource) {
        try {
            // Try reflection to get the fixedRate attribute
            Object eObject = externalSource;
            if (eObject instanceof org.eclipse.emf.ecore.EObject) {
                org.eclipse.emf.ecore.EObject eo = (org.eclipse.emf.ecore.EObject) eObject;
                org.eclipse.emf.ecore.EClass eClass = eo.eClass();
                
                // Look for fixedRate attribute
                for (org.eclipse.emf.ecore.EAttribute attr : eClass.getEAllAttributes()) {
                    if ("fixedRate".equals(attr.getName())) {
                        Object value = eo.eGet(attr);
                        if (value instanceof Boolean) {
                            return (Boolean) value;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Could not extract fixedRate from birth source '" + 
                             externalSource.getName() + "'. Consider regenerating EMF classes.");
        }
        
        // Default to population-based (legacy behavior) if no fixedRate attribute found
        return false;
    }

    private static void saveEquationsToFile(Map<String, String> equations, String outputFileName) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            for (Map.Entry<String, String> entry : equations.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            System.out.println("✅ Equations saved to " + outputFileName);
        } catch (IOException e) {
            System.err.println("❌ Error: Failed to save equations.");
            e.printStackTrace();
        }
    }

    /**
     * Get contact rate as either a symbolic parameter name or numeric value.
     * Prioritizes parameter reference over numeric attribute.
     *
     * For CONSTANT parameters: Returns the parameter NAME (e.g., "mu1")
     * For EXPRESSION parameters: Returns the expression (e.g., "eta_S * IM")
     * For numeric attributes: Returns the numeric value (e.g., "0.5")
     */
    private static String getContactRateExpression(ContactFlow flow, String stratum) {
        // Check for stratum-specific parameter first
        if (!stratum.isEmpty() && !flow.getStratumSpecificRates().isEmpty()) {
            for (StratumSpecificRate ssr : flow.getStratumSpecificRates()) {
                if (stratum.equals(ssr.getStratum())) {
                    Parameter ssrParam = ssr.getRateParameter();
                    if (ssrParam != null) {
                        // Return parameter name for CONSTANT/VARIABLE, expression for EXPRESSION
                        return getParameterRepresentation(ssrParam);
                    } else if (ssr.getRate() != 0.0) {
                        return String.valueOf(ssr.getRate());
                    }
                }
            }
        }

        // Fall back to flow-level parameter or numeric value
        Parameter param = flow.getContactRateParameter();
        if (param != null) {
            return getParameterRepresentation(param);
        } else if (flow.getContactRate() != 0.0) {
            return String.valueOf(flow.getContactRate());
        } else {
            return "UNKNOWN_CONTACT_RATE";
        }
    }

    /**
     * Get rate for RateFlow as symbolic parameter name or numeric value.
     */
    private static String getRateExpression(RateFlow flow, String stratum) {
        // Check for stratum-specific parameter first
        if (!stratum.isEmpty() && !flow.getStratumSpecificRates().isEmpty()) {
            for (StratumSpecificRate ssr : flow.getStratumSpecificRates()) {
                if (stratum.equals(ssr.getStratum())) {
                    Parameter ssrParam = ssr.getRateParameter();
                    if (ssrParam != null) {
                        return getParameterRepresentation(ssrParam);
                    } else if (ssr.getRate() != 0.0) {
                        return String.valueOf(ssr.getRate());
                    }
                }
            }
        }

        // Fall back to flow-level parameter or numeric value
        Parameter param = flow.getRateParameter();
        if (param != null) {
            return getParameterRepresentation(param);
        } else if (flow.getRate() != 0.0) {
            return String.valueOf(flow.getRate());
        } else {
            return "UNKNOWN_RATE";
        }
    }

    /**
     * Get birth rate as symbolic parameter name or numeric value.
     */
    private static String getExternalSourceRateExpression(ExternalSource externalSource) {
        Parameter param = externalSource.getRateParameter();
        if (param != null) {
            return getParameterRepresentation(param);
        } else if (externalSource.getRate() != 0.0) {
            return String.valueOf(externalSource.getRate());
        } else {
            return "UNKNOWN_BIRTH_RATE";
        }
    }

    /**
     * Get death rate as symbolic parameter name or numeric value.
     */
    private static String getExternalSinkRateExpression(ExternalSink externalSink) {
        Parameter param = externalSink.getRateParameter();
        if (param != null) {
            return getParameterRepresentation(param);
        } else if (externalSink.getRate() != 0.0) {
            return String.valueOf(externalSink.getRate());
        } else {
            return "UNKNOWN_DEATH_RATE";
        }
    }

    /**
     * Get multiplier as symbolic parameter name or numeric value.
     */
    private static String getMultiplierExpression(ContactFlow flow, String stratum) {
        if (stratum.isEmpty() || flow.getStratumSpecificRates().isEmpty()) {
            return "1.0";
        }

        for (StratumSpecificRate ssr : flow.getStratumSpecificRates()) {
            if (stratum.equals(ssr.getStratum())) {
                Parameter multiplierParam = ssr.getMultiplierParameter();
                if (multiplierParam != null) {
                    return getParameterRepresentation(multiplierParam);
                } else if (ssr.getMultiplier() != 0.0 && ssr.getMultiplier() != 1.0) {
                    return String.valueOf(ssr.getMultiplier());
                }
            }
        }

        return "1.0";
    }

    /**
     * Get the appropriate representation for a parameter:
     * - For CONSTANT or VARIABLE: Return the parameter NAME (e.g., "pi", "mu1")
     * - For EXPRESSION: Return the expression itself (e.g., "eta_S * IM")
     */
    private static String getParameterRepresentation(Parameter param) {
        if (param == null) {
            return "UNKNOWN_PARAM";
        }

        // Check parameter type
        String typeName = param.getType() != null ? param.getType().toString() : "";

        if ("EXPRESSION".equals(typeName)) {
            // For EXPRESSION type, return the full expression
            String expr = param.getExpression();
            return (expr != null && !expr.isEmpty()) ? expr : param.getName();
        } else {
            // For CONSTANT or VARIABLE, return just the parameter name
            String name = param.getName();
            return (name != null && !name.isEmpty()) ? name : param.getExpression();
        }
    }

    /**
     * Find parameter by name (supports alternative names)
     * Used to look up TRM parameters like "ω" (or "omega"), "C" (or "capacityDropFactor")
     */
    private static Parameter findParameter(CompartmentalModel model, String... names) {
        for (Parameter param : model.getParameters()) {
            String paramName = param.getName();
            for (String name : names) {
                if (name.equals(paramName)) {
                    return param;
                }
            }
        }
        return null;
    }

    /**
     * Check if model uses Traffic Reaction Model (TRM) features
     * Returns true if any compartment uses TRM_MAK or TRM_CAPACITATED supply functions
     *
     * NOTE: Requires EMF regeneration from .ecore to access TRM_MAK and TRM_CAPACITATED enums
     */
    private static boolean isTRMModel(CompartmentalModel model) {
        for (Compartment comp : model.getCompartments()) {
            if (comp.getSupplyFunction() != null) {
                String typeName = comp.getSupplyFunction().getType().toString();
                if ("TRM_MAK".equals(typeName) || "TRM_CAPACITATED".equals(typeName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if model has traffic features (supply functions)
     */
    private static boolean hasTrafficFeatures(CompartmentalModel model) {
        for (Compartment comp : model.getCompartments()) {
            if (comp.getSupplyFunction() != null) {
                String typeName = comp.getSupplyFunction().getType().toString();
                if (!"NONE".equals(typeName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Generate TRM equations with flux decomposition
     */
    private static void generateTRMEquations(CompartmentalModel model, String outputFileName) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write("======================================================================\n");
            writer.write("Differential Equations Generated from: " + outputFileName.replace("_equations.txt", ".compmodel") + "\n");
            writer.write("======================================================================\n\n");
            writer.write("MODEL TYPE: Traffic Reaction Model (TRM) - Kinetic Compartmental Approach\n\n");

            // Extract TRM parameters
            Parameter omega = findParameter(model, "ω", "omega");
            Parameter capacityDrop = findParameter(model, "C", "capacityDropFactor");
            Parameter rhoMax = findParameter(model, "ρ_max", "rho_max", "maxDensity");
            Parameter rhoCrit = findParameter(model, "ρ_crit", "rho_crit", "criticalDensity");
            Parameter phiMax = findParameter(model, "Φ_max", "phi_max", "maxThroughput");
            Parameter deltaX = findParameter(model, "Δx", "delta_x", "cellLength");

            writer.write("Traffic Reaction Model (TRM) Parameters:\n");
            if (deltaX != null) writer.write("  Δx = " + deltaX.getExpression() + "\n");
            if (rhoMax != null) writer.write("  ρ_max = " + rhoMax.getExpression() + "\n");
            if (rhoCrit != null) writer.write("  ρ_crit = " + rhoCrit.getExpression() + "\n");
            if (phiMax != null) writer.write("  Φ_max = " + phiMax.getExpression() + "\n");
            if (omega != null) writer.write("  ω = " + omega.getExpression() + "\n");
            if (capacityDrop != null) writer.write("  C = " + capacityDrop.getExpression() + "\n");
            writer.write("\n");

            writer.write("TRM Decomposition Types and Flux Functions:\n\n");

            // Generate compartment descriptions
            for (Compartment comp : model.getCompartments()) {
                String primaryName = comp.getPrimaryName();
                writer.write(primaryName + ":\n");

                if (comp.getSupplyFunction() != null) {
                    String typeName = comp.getSupplyFunction().getType().toString();
                    writer.write("  Type: " + typeName + "\n");

                    if (comp.getSupplyFunction().isIsSourceNode()) {
                        writer.write("  Node Type: Source Node\n");
                    } else {
                        writer.write("  Node Type: Constrained (Internal)\n");
                    }

                    double rhoMaxVal = comp.getSupplyFunction().getMaxDensity();
                    double rhoCritVal = comp.getSupplyFunction().getCriticalDensity();
                    double phiMaxVal = comp.getSupplyFunction().getMaxThroughput();

                    writer.write("  ρ^max = " + rhoMaxVal + "\n");
                    writer.write("  ρ^crit = " + rhoCritVal + "\n");
                    writer.write("  Φ^max = " + phiMaxVal + "\n");

                    if (capacityDrop != null) {
                        writer.write("  C(t) = " + capacityDrop.getExpression() + " (Extended TRM with capacity drop)\n");
                    }

                    // Type-specific flux decomposition
                    if ("TRM_MAK".equals(typeName)) {
                        if (omega != null) {
                            writer.write("  ω = " + omega.getExpression() + " (reaction rate constant from parameters)\n");
                        }
                        writer.write("  Flux Decomposition (MAK): g(ρ, ν) = ω·ρ·ν\n");
                        writer.write("  where ν = ρ^max - ρ (free space density)\n");
                    } else if ("TRM_CAPACITATED".equals(typeName)) {
                        writer.write("  Flux Decomposition (Capacitated): g(ρ, ν) = D(ρ)·Q(ρ^max - ν)/Φ^max\n");
                        writer.write("  D(ρ) = min((Φ^max/ρ^crit)·ρ, Φ^max) (demand function)\n");
                        writer.write("  Q(ν) = min((Φ^max/(ρ^max-ρ^crit))·ν, Φ^max) (supply function)\n");
                    } else if ("TRIANGULAR".equals(typeName)) {
                        writer.write("  Flux: g(ρ, ν) = min(D(ρ), Q(ρ^max - ν))\n");
                        writer.write("  Note: TRIANGULAR = TRM Godunov = Cell Transmission Model\n");
                    }

                    writer.write("  Dual Variable Tracking: Available\n");
                    writer.write("    ρ(t) = N(t)/Δx (occupied space density)\n");
                    writer.write("    ν(t) = S(t)/Δx = ρ^max - ρ(t) (free space density)\n");
                    writer.write("    Conservation: N + S = ρ^max·Δx\n");
                }

                writer.write("\n");
            }

            writer.write("\nKinetic Interpretation (for dual variable models):\n");
            writer.write("  Each road segment is a compartment with two species:\n");
            writer.write("    N_i(t) = occupied space (vehicles)\n");
            writer.write("    S_i(t) = free space (available capacity)\n");
            writer.write("  Chemical reaction: N_{i-1} + S_i →^{κ_{i-1,i}} N_i + S_{i-1}\n");
            writer.write("  Reaction rate: κ_{i-1,i}(t) = (1/Δx)·g(ρ_{i-1}, ν_i)\n\n");

            writer.write("Differential Equations:\n\n");
            writer.write("NOTE: TRM requires numerical flux computation F(u,v) = g(u, ρ^max - v)\n");
            writer.write("See trm_simulation.py for full implementation.\n\n");

            // Generate equations
            for (Compartment comp : model.getCompartments()) {
                String primaryName = comp.getPrimaryName();
                writer.write("dρ_" + primaryName + "/dt = (1/Δx)[F(ρ_upstream, ρ_" + primaryName + ") - F(ρ_" + primaryName + ", ρ_downstream)]\n");
                writer.write("dν_" + primaryName + "/dt = -dρ_" + primaryName + "/dt (conservation)\n\n");
            }

            writer.write("\nNumerical Flux Computation:\n");
            writer.write("  F(ρ_i, ρ_{i+1}) = g(ρ_i, ρ^max - ρ_{i+1})\n");
            writer.write("  This couples upstream density ρ_i with downstream free space ν_{i+1} = ρ^max - ρ_{i+1}\n\n");

            writer.write("TRM Properties:\n");
            writer.write("  - Persistence: All trajectories remain in (0, ρ^max)\n");
            writer.write("  - Monotonicity: g increasing in ρ, decreasing in ρ^max - ν\n");
            writer.write("  - Lipschitz Continuity: g is Lipschitz continuous\n");
            writer.write("  - CTM Equivalence: TRIANGULAR ≡ Cell Transmission Model\n");

            System.out.println("✅ TRM equations saved to " + outputFileName);
        } catch (IOException e) {
            System.err.println("❌ Error: Failed to save TRM equations.");
            e.printStackTrace();
        }
    }
}
