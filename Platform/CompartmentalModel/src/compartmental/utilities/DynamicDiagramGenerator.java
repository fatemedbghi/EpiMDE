package compartmental.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import compartmentalmodel.ExternalSource;
import compartmentalmodel.Compartment;
import compartmentalmodel.ContactFlow;
import compartmentalmodel.ExternalSink;
import compartmentalmodel.Flow;
import compartmentalmodel.Group;
import compartmentalmodel.RateFlow;
import compartmentalmodel.CompartmentalModel;
import compartmentalmodel.StratumSpecificRate;

/**
 * Completely dynamic model splitter that works with any groups.
 * Automatically detects all group values and creates separate models.
 */
public class DynamicDiagramGenerator {
    
    public static void main(String[] args) {
        String modelFile;
        
        if (args.length == 0) {
            // No arguments provided - look for covid.compmodel in current directory
        	Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of the .compmodel file (e.g. covid.compmodel): ");
            String fileName = scanner.nextLine().trim();
            scanner.close();

            modelFile = System.getProperty("user.dir") + "/" + fileName;
            System.out.println("No arguments provided. Looking for covid.compmodel in current directory...");
        } else if (args.length == 1) {
            // Use provided argument
            modelFile = args[0];
        } else {
            System.out.println("Usage: java DynamicDiagramGenerator [model-file.compmodel]");
            System.out.println("Examples:");
            System.out.println("  java DynamicDiagramGenerator                    (looks for covid.compmodel)");
            System.out.println("  java DynamicDiagramGenerator covid.compmodel    (uses specified file)");
            System.out.println("");
            System.out.println("This utility automatically:");
            System.out.println("  1. Detects all groups in your model");
            System.out.println("  2. Finds all group values dynamically");
            System.out.println("  3. Creates separate model files for each group value");
            System.out.println("  4. Preserves group-specific rates and flows");
            return;
        }
        
        generateDynamicModels(modelFile);
    }
    
    public static void generateDynamicModels(String inputFile) {
        try {
            System.out.println("=== Dynamic Compartmental Model Splitter ===");
            System.out.println("Loading: " + inputFile);
            
            CompartmentalModel originalModel = loadModel(inputFile);
            
            // Dynamic group detection
            List<GroupInfo> allGroups = analyzeGroups(originalModel);
            
            if (allGroups.isEmpty()) {
                System.out.println("❌ No groups found in model. Nothing to split.");
                System.out.println("Make sure your model has <groups> with <values> defined.");
                return;
            }
            
            System.out.println("✓ Found " + allGroups.size() + " group(s):");
            for (GroupInfo groupInfo : allGroups) {
                System.out.println("  📁 " + groupInfo.groupName + ": " + groupInfo.values.size() + " values");
                for (String value : groupInfo.values) {
                    System.out.println("    - " + value);
                }
            }
            
            // Generate Cartesian product of all group values
            List<String> cartesianProduct = generateCartesianProduct(allGroups);

            System.out.println("\n🔄 Creating " + cartesianProduct.size() + " group-specific models...");
            System.out.println("   (Cartesian product of " + allGroups.size() + " group(s))");

            int count = 0;
            for (String groupValue : cartesianProduct) {
                count++;
                System.out.println("\n[" + count + "/" + cartesianProduct.size() + "] Processing: " + groupValue);

                // Create complete copy
                CompartmentalModel groupModel = (CompartmentalModel) EcoreUtil.copy(originalModel);

                // Customize for this group
                customizeModelForGroup(groupModel, groupValue);

                // Generate filename
                String outputFile = generateFileName(inputFile, groupValue);

                // Save
                saveModel(groupModel, outputFile);

                // Report
                int compartments = groupModel.getCompartments().size();
                int stratifiedCompartments = countStratifiedCompartments(groupModel);

                System.out.println("  ✓ Saved: " + outputFile);
                System.out.println("    📊 " + compartments + " compartments (" + stratifiedCompartments + " stratified)");
                System.out.println("    🔄 " + countFlows(groupModel) + " flows");
                System.out.println("    📈 " + groupModel.getExternalSources().size() + " external sources");
                System.out.println("    📉 " + groupModel.getExternalSinks().size() + " external sinks");
            }

            System.out.println("\n🎉 SUCCESS! Generated " + cartesianProduct.size() + " group-specific models.");
            System.out.println("💡 You can now create diagrams from each file in Eclipse:");
            for (String groupValue : cartesianProduct) {
                System.out.println("  📋 " + generateFileName(inputFile, groupValue));
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static class GroupInfo {
        String groupName;
        String description;
        List<String> values;
        
        GroupInfo(String name, String desc) {
            this.groupName = name;
            this.description = desc;
            this.values = new ArrayList<String>();
        }
    }
    
    private static List<GroupInfo> analyzeGroups(CompartmentalModel model) {
        List<GroupInfo> groups = new ArrayList<GroupInfo>();

        for (int i = 0; i < model.getGroups().size(); i++) {
            Group group = model.getGroups().get(i);
            GroupInfo info = new GroupInfo(group.getName(), group.getDescription());

            // Collect all values
            for (int j = 0; j < group.getValues().size(); j++) {
                info.values.add(group.getValues().get(j));
            }

            if (!info.values.isEmpty()) {
                groups.add(info);
            }
        }

        return groups;
    }

    /**
     * Generate Cartesian product of all group values.
     * For example, if groups are:
     *   Age: [0-17, 18-65, 66+]
     *   Gender: [Male, Female]
     * This returns: [0-17,Male, 0-17,Female, 18-65,Male, 18-65,Female, 66+,Male, 66+,Female]
     */
    private static List<String> generateCartesianProduct(List<GroupInfo> groups) {
        List<String> result = new ArrayList<String>();

        if (groups.isEmpty()) {
            return result;
        }

        // Start with first group's values
        for (String value : groups.get(0).values) {
            result.add(value);
        }

        // For each additional group, expand the product
        for (int i = 1; i < groups.size(); i++) {
            List<String> newResult = new ArrayList<String>();
            GroupInfo currentGroup = groups.get(i);

            for (String existingCombo : result) {
                for (String newValue : currentGroup.values) {
                    newResult.add(existingCombo + "," + newValue);
                }
            }

            result = newResult;
        }

        return result;
    }
    
    private static void customizeModelForGroup(CompartmentalModel model, String targetGroupValue) {
        System.out.println("    🔧 Customizing flows for group: " + targetGroupValue);
        
        // Process all flows to use group-specific rates
        int flowsProcessed = 0;
        int ratesReplaced = 0;
        
        for (int i = 0; i < model.getCompartments().size(); i++) {
            Compartment comp = model.getCompartments().get(i);
            
            for (int j = 0; j < comp.getOutgoingFlows().size(); j++) {
                Flow flow = comp.getOutgoingFlows().get(j);
                flowsProcessed++;
                
                if (processFlow(flow, targetGroupValue)) {
                    ratesReplaced++;
                }
            }
        }
        
        System.out.println("    📊 Processed " + flowsProcessed + " flows, replaced " + ratesReplaced + " rates");
        
        // Update labels
        updateLabels(model, targetGroupValue);
    }
    
    private static boolean processFlow(Flow flow, String targetGroup) {
        boolean rateReplaced = false;

        if (flow instanceof RateFlow) {
            RateFlow rateFlow = (RateFlow) flow;

            // Look for group-specific rate (exact match for Cartesian product)
            for (int k = 0; k < rateFlow.getStratumSpecificRates().size(); k++) {
                StratumSpecificRate stratumRate = rateFlow.getStratumSpecificRates().get(k);
                if (targetGroup.equals(stratumRate.getStratum())) {
                    // Use the stratum-specific rate if provided, otherwise calculate from multiplier
                    double finalRate = stratumRate.getRate();
                    if (finalRate == 0.0 && stratumRate.getMultiplier() != 0.0) {
                        finalRate = rateFlow.getRate() * stratumRate.getMultiplier();
                    }
                    rateFlow.setRate(finalRate);
                    rateReplaced = true;
                    break;
                }
            }

            // Clear stratum-specific rates (no longer needed)
            rateFlow.getStratumSpecificRates().clear();

        } else if (flow instanceof ContactFlow) {
            ContactFlow contactFlow = (ContactFlow) flow;

            // Look for group-specific contact rate (exact match for Cartesian product)
            for (int k = 0; k < contactFlow.getStratumSpecificRates().size(); k++) {
                StratumSpecificRate stratumRate = contactFlow.getStratumSpecificRates().get(k);
                if (targetGroup.equals(stratumRate.getStratum())) {
                    // Use the stratum-specific rate if provided, otherwise calculate from multiplier
                    double finalRate = stratumRate.getRate();
                    if (finalRate == 0.0 && stratumRate.getMultiplier() != 0.0) {
                        finalRate = contactFlow.getContactRate() * stratumRate.getMultiplier();
                    }
                    contactFlow.setContactRate(finalRate);
                    rateReplaced = true;
                    break;
                }
            }

            // Clear stratum-specific rates
            contactFlow.getStratumSpecificRates().clear();
        }

        return rateReplaced;
    }
    
    private static void updateLabels(CompartmentalModel model, String groupValue) {
        // Filter external sources to only include those relevant to this group combination
        List<ExternalSource> relevantExternalSources = new ArrayList<ExternalSource>();
        for (int i = 0; i < model.getExternalSources().size(); i++) {
            ExternalSource source = model.getExternalSources().get(i);
            // Keep external source if it belongs to this group combination or has no stratum specified
            if (source.getTargetStratum() == null ||
                groupValue.equals(source.getTargetStratum()) ||
                isStratumMatch(groupValue, source.getTargetStratum())) {
                relevantExternalSources.add(source);
            }
        }
        model.getExternalSources().clear();
        model.getExternalSources().addAll(relevantExternalSources);

        // Filter external sinks to only include those relevant to this group combination
        List<ExternalSink> relevantExternalSinks = new ArrayList<ExternalSink>();
        for (int i = 0; i < model.getExternalSinks().size(); i++) {
            ExternalSink sink = model.getExternalSinks().get(i);
            // Keep external sink if it belongs to this group combination or has no stratum specified
            if (sink.getSourceStratum() == null ||
                groupValue.equals(sink.getSourceStratum()) ||
                isStratumMatch(groupValue, sink.getSourceStratum())) {
                relevantExternalSinks.add(sink);
            }
        }
        model.getExternalSinks().clear();
        model.getExternalSinks().addAll(relevantExternalSinks);
    }

    /**
     * Check if a target stratum matches the current group value.
     * Handles both exact matches and partial matches for Cartesian products.
     * For example: "0-17,Male" matches "0-17,Male" exactly
     */
    private static boolean isStratumMatch(String groupValue, String stratum) {
        if (groupValue == null || stratum == null) {
            return false;
        }
        return groupValue.equals(stratum);
    }
    
    private static String generateFileName(String inputFile, String groupValue) {
        // Create safe filename
        String safeGroupValue = groupValue.replaceAll("[^a-zA-Z0-9]", "_");
        return inputFile.replace(".compmodel", "_" + safeGroupValue + ".compmodel");
    }
    
    private static int countStratifiedCompartments(CompartmentalModel model) {
        int count = 0;
        for (int i = 0; i < model.getCompartments().size(); i++) {
            if (model.getCompartments().get(i).getProduct() != null) {
                count++;
            }
        }
        return count;
    }
    
    private static int countFlows(CompartmentalModel model) {
        int count = 0;
        for (int i = 0; i < model.getCompartments().size(); i++) {
            count += model.getCompartments().get(i).getOutgoingFlows().size();
        }
        return count;
    }
    
    private static CompartmentalModel loadModel(String filePath) throws IOException {
        // Register the Compartmental model package
        compartmentalmodel.CompartmentalmodelPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
            .put("compmodel", new XMIResourceFactoryImpl());
        
        // Register the package URI
        resourceSet.getPackageRegistry().put(
            compartmentalmodel.CompartmentalmodelPackage.eNS_URI, 
            compartmentalmodel.CompartmentalmodelPackage.eINSTANCE
        );
        
        URI uri = URI.createFileURI(filePath);
        Resource resource = resourceSet.getResource(uri, true);
        
        if (resource.getContents().isEmpty()) {
            throw new IOException("Model file is empty or invalid");
        }
        
        return (CompartmentalModel) resource.getContents().get(0);
    }
    
    private static void saveModel(CompartmentalModel model, String filePath) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
            .put("compmodel", new XMIResourceFactoryImpl());
        
        URI uri = URI.createFileURI(filePath);
        Resource resource = resourceSet.createResource(uri);
        resource.getContents().add(model);
        
        resource.save(null);
    }
}