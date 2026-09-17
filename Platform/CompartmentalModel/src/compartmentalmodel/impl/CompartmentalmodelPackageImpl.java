/**
 */
package compartmentalmodel.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import compartmentalmodel.Compartment;
import compartmentalmodel.ContactFlow;
import compartmentalmodel.ExternalSink;
import compartmentalmodel.ExternalSource;
import compartmentalmodel.Flow;
import compartmentalmodel.Group;
import compartmentalmodel.JunctionRuleType;
import compartmentalmodel.Parameter;
import compartmentalmodel.ParameterType;
import compartmentalmodel.Product;
import compartmentalmodel.RateFlow;
import compartmentalmodel.CompartmentalModel;
import compartmentalmodel.CompartmentalmodelFactory;
import compartmentalmodel.CompartmentalmodelPackage;
import compartmentalmodel.StratumSpecificRate;
import compartmentalmodel.SupplyFunction;
import compartmentalmodel.SupplyFunctionType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CompartmentalmodelPackageImpl extends EPackageImpl implements CompartmentalmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compartmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rateFlowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contactFlowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalSinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stratumSpecificRateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass supplyFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compartmentalModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parameterTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum supplyFunctionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum junctionRuleTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see compartmentalmodel.CompartmentalmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CompartmentalmodelPackageImpl() {
		super(eNS_URI, CompartmentalmodelFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link CompartmentalmodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CompartmentalmodelPackage init() {
		if (isInited) return (CompartmentalmodelPackage)EPackage.Registry.INSTANCE.getEPackage(CompartmentalmodelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCompartmentalmodelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CompartmentalmodelPackageImpl theCompartmentalmodelPackage = registeredCompartmentalmodelPackage instanceof CompartmentalmodelPackageImpl ? (CompartmentalmodelPackageImpl)registeredCompartmentalmodelPackage : new CompartmentalmodelPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theCompartmentalmodelPackage.createPackageContents();

		// Initialize created meta-data
		theCompartmentalmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCompartmentalmodelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CompartmentalmodelPackage.eNS_URI, theCompartmentalmodelPackage);
		return theCompartmentalmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompartment() {
		return compartmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartment_PrimaryName() {
		return (EAttribute)compartmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartment_Population() {
		return (EAttribute)compartmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartment_OutgoingFlows() {
		return (EReference)compartmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartment_SecondaryName() {
		return (EAttribute)compartmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartment_Product() {
		return (EReference)compartmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartment_SupplyFunction() {
		return (EReference)compartmentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartment_JunctionRule() {
		return (EAttribute)compartmentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFlow() {
		return flowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlow_Description() {
		return (EAttribute)flowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlow_Target() {
		return (EReference)flowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRateFlow() {
		return rateFlowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRateFlow_Rate() {
		return (EAttribute)rateFlowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRateFlow_RateParameter() {
		return (EReference)rateFlowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRateFlow_StratumSpecificRates() {
		return (EReference)rateFlowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContactFlow() {
		return contactFlowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContactFlow_ContactCompartment() {
		return (EReference)contactFlowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContactFlow_ContactRate() {
		return (EAttribute)contactFlowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContactFlow_ContactRateParameter() {
		return (EReference)contactFlowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContactFlow_ContactParameters() {
		return (EAttribute)contactFlowEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContactFlow_StratumSpecificRates() {
		return (EReference)contactFlowEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExternalSource() {
		return externalSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalSource_Name() {
		return (EAttribute)externalSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalSource_Rate() {
		return (EAttribute)externalSourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExternalSource_RateParameter() {
		return (EReference)externalSourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExternalSource_TargetCompartment() {
		return (EReference)externalSourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalSource_TargetStratum() {
		return (EAttribute)externalSourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalSource_FixedRate() {
		return (EAttribute)externalSourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExternalSink() {
		return externalSinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalSink_Name() {
		return (EAttribute)externalSinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalSink_Rate() {
		return (EAttribute)externalSinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExternalSink_RateParameter() {
		return (EReference)externalSinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExternalSink_SourceCompartment() {
		return (EReference)externalSinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalSink_SourceStratum() {
		return (EAttribute)externalSinkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStratumSpecificRate() {
		return stratumSpecificRateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStratumSpecificRate_Stratum() {
		return (EAttribute)stratumSpecificRateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStratumSpecificRate_Rate() {
		return (EAttribute)stratumSpecificRateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStratumSpecificRate_RateParameter() {
		return (EReference)stratumSpecificRateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStratumSpecificRate_Multiplier() {
		return (EAttribute)stratumSpecificRateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStratumSpecificRate_MultiplierParameter() {
		return (EReference)stratumSpecificRateEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGroup() {
		return groupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGroup_Name() {
		return (EAttribute)groupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGroup_Description() {
		return (EAttribute)groupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGroup_Values() {
		return (EAttribute)groupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProduct() {
		return productEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProduct_Name() {
		return (EAttribute)productEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProduct_Description() {
		return (EAttribute)productEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProduct_Groups() {
		return (EReference)productEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Name() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Type() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Expression() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Description() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Unit() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSupplyFunction() {
		return supplyFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSupplyFunction_Type() {
		return (EAttribute)supplyFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSupplyFunction_MaxDensity() {
		return (EAttribute)supplyFunctionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSupplyFunction_CriticalDensity() {
		return (EAttribute)supplyFunctionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSupplyFunction_MaxThroughput() {
		return (EAttribute)supplyFunctionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSupplyFunction_MaxDemand() {
		return (EAttribute)supplyFunctionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSupplyFunction_IsSourceNode() {
		return (EAttribute)supplyFunctionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompartmentalModel() {
		return compartmentalModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentalModel_Compartments() {
		return (EReference)compartmentalModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentalModel_ExternalSources() {
		return (EReference)compartmentalModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentalModel_ExternalSinks() {
		return (EReference)compartmentalModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentalModel_Groups() {
		return (EReference)compartmentalModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentalModel_Products() {
		return (EReference)compartmentalModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentalModel_Parameters() {
		return (EReference)compartmentalModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartmentalModel_TotalPopulation() {
		return (EAttribute)compartmentalModelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartmentalModel_GlobalBirthRate() {
		return (EAttribute)compartmentalModelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartmentalModel_GlobalDeathRate() {
		return (EAttribute)compartmentalModelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getParameterType() {
		return parameterTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSupplyFunctionType() {
		return supplyFunctionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getJunctionRuleType() {
		return junctionRuleTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompartmentalmodelFactory getCompartmentalmodelFactory() {
		return (CompartmentalmodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		compartmentEClass = createEClass(COMPARTMENT);
		createEAttribute(compartmentEClass, COMPARTMENT__PRIMARY_NAME);
		createEAttribute(compartmentEClass, COMPARTMENT__POPULATION);
		createEReference(compartmentEClass, COMPARTMENT__OUTGOING_FLOWS);
		createEAttribute(compartmentEClass, COMPARTMENT__SECONDARY_NAME);
		createEReference(compartmentEClass, COMPARTMENT__PRODUCT);
		createEReference(compartmentEClass, COMPARTMENT__SUPPLY_FUNCTION);
		createEAttribute(compartmentEClass, COMPARTMENT__JUNCTION_RULE);

		flowEClass = createEClass(FLOW);
		createEAttribute(flowEClass, FLOW__DESCRIPTION);
		createEReference(flowEClass, FLOW__TARGET);

		rateFlowEClass = createEClass(RATE_FLOW);
		createEAttribute(rateFlowEClass, RATE_FLOW__RATE);
		createEReference(rateFlowEClass, RATE_FLOW__RATE_PARAMETER);
		createEReference(rateFlowEClass, RATE_FLOW__STRATUM_SPECIFIC_RATES);

		contactFlowEClass = createEClass(CONTACT_FLOW);
		createEReference(contactFlowEClass, CONTACT_FLOW__CONTACT_COMPARTMENT);
		createEAttribute(contactFlowEClass, CONTACT_FLOW__CONTACT_RATE);
		createEReference(contactFlowEClass, CONTACT_FLOW__CONTACT_RATE_PARAMETER);
		createEAttribute(contactFlowEClass, CONTACT_FLOW__CONTACT_PARAMETERS);
		createEReference(contactFlowEClass, CONTACT_FLOW__STRATUM_SPECIFIC_RATES);

		externalSourceEClass = createEClass(EXTERNAL_SOURCE);
		createEAttribute(externalSourceEClass, EXTERNAL_SOURCE__NAME);
		createEAttribute(externalSourceEClass, EXTERNAL_SOURCE__RATE);
		createEReference(externalSourceEClass, EXTERNAL_SOURCE__RATE_PARAMETER);
		createEReference(externalSourceEClass, EXTERNAL_SOURCE__TARGET_COMPARTMENT);
		createEAttribute(externalSourceEClass, EXTERNAL_SOURCE__TARGET_STRATUM);
		createEAttribute(externalSourceEClass, EXTERNAL_SOURCE__FIXED_RATE);

		externalSinkEClass = createEClass(EXTERNAL_SINK);
		createEAttribute(externalSinkEClass, EXTERNAL_SINK__NAME);
		createEAttribute(externalSinkEClass, EXTERNAL_SINK__RATE);
		createEReference(externalSinkEClass, EXTERNAL_SINK__RATE_PARAMETER);
		createEReference(externalSinkEClass, EXTERNAL_SINK__SOURCE_COMPARTMENT);
		createEAttribute(externalSinkEClass, EXTERNAL_SINK__SOURCE_STRATUM);

		stratumSpecificRateEClass = createEClass(STRATUM_SPECIFIC_RATE);
		createEAttribute(stratumSpecificRateEClass, STRATUM_SPECIFIC_RATE__STRATUM);
		createEAttribute(stratumSpecificRateEClass, STRATUM_SPECIFIC_RATE__RATE);
		createEReference(stratumSpecificRateEClass, STRATUM_SPECIFIC_RATE__RATE_PARAMETER);
		createEAttribute(stratumSpecificRateEClass, STRATUM_SPECIFIC_RATE__MULTIPLIER);
		createEReference(stratumSpecificRateEClass, STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER);

		groupEClass = createEClass(GROUP);
		createEAttribute(groupEClass, GROUP__NAME);
		createEAttribute(groupEClass, GROUP__DESCRIPTION);
		createEAttribute(groupEClass, GROUP__VALUES);

		productEClass = createEClass(PRODUCT);
		createEAttribute(productEClass, PRODUCT__NAME);
		createEAttribute(productEClass, PRODUCT__DESCRIPTION);
		createEReference(productEClass, PRODUCT__GROUPS);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__NAME);
		createEAttribute(parameterEClass, PARAMETER__TYPE);
		createEAttribute(parameterEClass, PARAMETER__EXPRESSION);
		createEAttribute(parameterEClass, PARAMETER__DESCRIPTION);
		createEAttribute(parameterEClass, PARAMETER__UNIT);

		supplyFunctionEClass = createEClass(SUPPLY_FUNCTION);
		createEAttribute(supplyFunctionEClass, SUPPLY_FUNCTION__TYPE);
		createEAttribute(supplyFunctionEClass, SUPPLY_FUNCTION__MAX_DENSITY);
		createEAttribute(supplyFunctionEClass, SUPPLY_FUNCTION__CRITICAL_DENSITY);
		createEAttribute(supplyFunctionEClass, SUPPLY_FUNCTION__MAX_THROUGHPUT);
		createEAttribute(supplyFunctionEClass, SUPPLY_FUNCTION__MAX_DEMAND);
		createEAttribute(supplyFunctionEClass, SUPPLY_FUNCTION__IS_SOURCE_NODE);

		compartmentalModelEClass = createEClass(COMPARTMENTAL_MODEL);
		createEReference(compartmentalModelEClass, COMPARTMENTAL_MODEL__COMPARTMENTS);
		createEReference(compartmentalModelEClass, COMPARTMENTAL_MODEL__EXTERNAL_SOURCES);
		createEReference(compartmentalModelEClass, COMPARTMENTAL_MODEL__EXTERNAL_SINKS);
		createEReference(compartmentalModelEClass, COMPARTMENTAL_MODEL__GROUPS);
		createEReference(compartmentalModelEClass, COMPARTMENTAL_MODEL__PRODUCTS);
		createEReference(compartmentalModelEClass, COMPARTMENTAL_MODEL__PARAMETERS);
		createEAttribute(compartmentalModelEClass, COMPARTMENTAL_MODEL__TOTAL_POPULATION);
		createEAttribute(compartmentalModelEClass, COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE);
		createEAttribute(compartmentalModelEClass, COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE);

		// Create enums
		parameterTypeEEnum = createEEnum(PARAMETER_TYPE);
		supplyFunctionTypeEEnum = createEEnum(SUPPLY_FUNCTION_TYPE);
		junctionRuleTypeEEnum = createEEnum(JUNCTION_RULE_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		rateFlowEClass.getESuperTypes().add(this.getFlow());
		contactFlowEClass.getESuperTypes().add(this.getFlow());

		// Initialize classes, features, and operations; add parameters
		initEClass(compartmentEClass, Compartment.class, "Compartment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCompartment_PrimaryName(), ecorePackage.getEString(), "PrimaryName", null, 0, 1, Compartment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCompartment_Population(), ecorePackage.getEDouble(), "population", null, 0, 1, Compartment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompartment_OutgoingFlows(), this.getFlow(), null, "outgoingFlows", null, 0, -1, Compartment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompartment_SecondaryName(), ecorePackage.getEString(), "SecondaryName", null, 0, 1, Compartment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCompartment_Product(), this.getProduct(), null, "product", null, 0, 1, Compartment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompartment_SupplyFunction(), this.getSupplyFunction(), null, "supplyFunction", null, 0, 1, Compartment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompartment_JunctionRule(), this.getJunctionRuleType(), "junctionRule", null, 0, 1, Compartment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowEClass, Flow.class, "Flow", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFlow_Description(), ecorePackage.getEString(), "description", null, 0, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_Target(), this.getCompartment(), null, "target", null, 0, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rateFlowEClass, RateFlow.class, "RateFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRateFlow_Rate(), ecorePackage.getEDouble(), "rate", null, 0, 1, RateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRateFlow_RateParameter(), this.getParameter(), null, "rateParameter", null, 0, 1, RateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRateFlow_StratumSpecificRates(), this.getStratumSpecificRate(), null, "stratumSpecificRates", null, 0, -1, RateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contactFlowEClass, ContactFlow.class, "ContactFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContactFlow_ContactCompartment(), this.getCompartment(), null, "contactCompartment", null, 0, 1, ContactFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContactFlow_ContactRate(), ecorePackage.getEDouble(), "contactRate", null, 0, 1, ContactFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContactFlow_ContactRateParameter(), this.getParameter(), null, "contactRateParameter", null, 0, 1, ContactFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContactFlow_ContactParameters(), ecorePackage.getEString(), "contactParameters", null, 0, 1, ContactFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContactFlow_StratumSpecificRates(), this.getStratumSpecificRate(), null, "stratumSpecificRates", null, 0, -1, ContactFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalSourceEClass, ExternalSource.class, "ExternalSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExternalSource_Name(), ecorePackage.getEString(), "name", null, 0, 1, ExternalSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalSource_Rate(), ecorePackage.getEDouble(), "rate", null, 0, 1, ExternalSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExternalSource_RateParameter(), this.getParameter(), null, "rateParameter", null, 0, 1, ExternalSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExternalSource_TargetCompartment(), this.getCompartment(), null, "targetCompartment", null, 0, 1, ExternalSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalSource_TargetStratum(), ecorePackage.getEString(), "targetStratum", null, 0, 1, ExternalSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalSource_FixedRate(), ecorePackage.getEBoolean(), "fixedRate", "false", 0, 1, ExternalSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalSinkEClass, ExternalSink.class, "ExternalSink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExternalSink_Name(), ecorePackage.getEString(), "name", null, 0, 1, ExternalSink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalSink_Rate(), ecorePackage.getEDouble(), "rate", null, 0, 1, ExternalSink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExternalSink_RateParameter(), this.getParameter(), null, "rateParameter", null, 0, 1, ExternalSink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExternalSink_SourceCompartment(), this.getCompartment(), null, "sourceCompartment", null, 0, 1, ExternalSink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalSink_SourceStratum(), ecorePackage.getEString(), "sourceStratum", null, 0, 1, ExternalSink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stratumSpecificRateEClass, StratumSpecificRate.class, "StratumSpecificRate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStratumSpecificRate_Stratum(), ecorePackage.getEString(), "stratum", null, 0, 1, StratumSpecificRate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStratumSpecificRate_Rate(), ecorePackage.getEDouble(), "rate", null, 0, 1, StratumSpecificRate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStratumSpecificRate_RateParameter(), this.getParameter(), null, "rateParameter", null, 0, 1, StratumSpecificRate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStratumSpecificRate_Multiplier(), ecorePackage.getEDouble(), "multiplier", "1.0", 0, 1, StratumSpecificRate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStratumSpecificRate_MultiplierParameter(), this.getParameter(), null, "multiplierParameter", null, 0, 1, StratumSpecificRate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroup_Name(), ecorePackage.getEString(), "name", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroup_Description(), ecorePackage.getEString(), "description", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroup_Values(), ecorePackage.getEString(), "values", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProduct_Name(), ecorePackage.getEString(), "name", null, 0, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProduct_Description(), ecorePackage.getEString(), "description", null, 0, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_Groups(), this.getGroup(), null, "groups", null, 0, -1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Type(), this.getParameterType(), "type", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Description(), ecorePackage.getEString(), "description", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Unit(), ecorePackage.getEString(), "unit", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(supplyFunctionEClass, SupplyFunction.class, "SupplyFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSupplyFunction_Type(), this.getSupplyFunctionType(), "type", null, 0, 1, SupplyFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSupplyFunction_MaxDensity(), ecorePackage.getEDouble(), "maxDensity", null, 0, 1, SupplyFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSupplyFunction_CriticalDensity(), ecorePackage.getEDouble(), "criticalDensity", null, 0, 1, SupplyFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSupplyFunction_MaxThroughput(), ecorePackage.getEDouble(), "maxThroughput", null, 0, 1, SupplyFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSupplyFunction_MaxDemand(), ecorePackage.getEDouble(), "maxDemand", null, 0, 1, SupplyFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSupplyFunction_IsSourceNode(), ecorePackage.getEBoolean(), "isSourceNode", "false", 0, 1, SupplyFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compartmentalModelEClass, CompartmentalModel.class, "CompartmentalModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompartmentalModel_Compartments(), this.getCompartment(), null, "compartments", null, 0, -1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompartmentalModel_ExternalSources(), this.getExternalSource(), null, "externalSources", null, 0, -1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompartmentalModel_ExternalSinks(), this.getExternalSink(), null, "externalSinks", null, 0, -1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompartmentalModel_Groups(), this.getGroup(), null, "groups", null, 0, -1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompartmentalModel_Products(), this.getProduct(), null, "products", null, 0, -1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompartmentalModel_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompartmentalModel_TotalPopulation(), ecorePackage.getEDouble(), "totalPopulation", null, 0, 1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompartmentalModel_GlobalBirthRate(), ecorePackage.getEDouble(), "globalBirthRate", null, 0, 1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompartmentalModel_GlobalDeathRate(), ecorePackage.getEDouble(), "globalDeathRate", null, 0, 1, CompartmentalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(parameterTypeEEnum, ParameterType.class, "ParameterType");
		addEEnumLiteral(parameterTypeEEnum, ParameterType.CONSTANT);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.VARIABLE);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.EXPRESSION);

		initEEnum(supplyFunctionTypeEEnum, SupplyFunctionType.class, "SupplyFunctionType");
		addEEnumLiteral(supplyFunctionTypeEEnum, SupplyFunctionType.NONE);
		addEEnumLiteral(supplyFunctionTypeEEnum, SupplyFunctionType.TRIANGULAR);
		addEEnumLiteral(supplyFunctionTypeEEnum, SupplyFunctionType.LINEAR);
		addEEnumLiteral(supplyFunctionTypeEEnum, SupplyFunctionType.CUSTOM);
		addEEnumLiteral(supplyFunctionTypeEEnum, SupplyFunctionType.TRM_MAK);
		addEEnumLiteral(supplyFunctionTypeEEnum, SupplyFunctionType.TRM_CAPACITATED);

		initEEnum(junctionRuleTypeEEnum, JunctionRuleType.class, "JunctionRuleType");
		addEEnumLiteral(junctionRuleTypeEEnum, JunctionRuleType.NONE);
		addEEnumLiteral(junctionRuleTypeEEnum, JunctionRuleType.PPFIFO);
		addEEnumLiteral(junctionRuleTypeEEnum, JunctionRuleType.CUSTOM);

		// Create resource
		createResource(eNS_URI);
	}

} //CompartmentalmodelPackageImpl
