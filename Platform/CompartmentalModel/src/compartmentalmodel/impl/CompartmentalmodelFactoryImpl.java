/**
 */
package compartmentalmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import compartmentalmodel.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CompartmentalmodelFactoryImpl extends EFactoryImpl implements CompartmentalmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CompartmentalmodelFactory init() {
		try {
			CompartmentalmodelFactory theCompartmentalmodelFactory = (CompartmentalmodelFactory)EPackage.Registry.INSTANCE.getEFactory(CompartmentalmodelPackage.eNS_URI);
			if (theCompartmentalmodelFactory != null) {
				return theCompartmentalmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CompartmentalmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompartmentalmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CompartmentalmodelPackage.COMPARTMENT: return createCompartment();
			case CompartmentalmodelPackage.RATE_FLOW: return createRateFlow();
			case CompartmentalmodelPackage.CONTACT_FLOW: return createContactFlow();
			case CompartmentalmodelPackage.EXTERNAL_SOURCE: return createExternalSource();
			case CompartmentalmodelPackage.EXTERNAL_SINK: return createExternalSink();
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE: return createStratumSpecificRate();
			case CompartmentalmodelPackage.GROUP: return createGroup();
			case CompartmentalmodelPackage.PRODUCT: return createProduct();
			case CompartmentalmodelPackage.PARAMETER: return createParameter();
			case CompartmentalmodelPackage.SUPPLY_FUNCTION: return createSupplyFunction();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL: return createCompartmentalModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CompartmentalmodelPackage.PARAMETER_TYPE:
				return createParameterTypeFromString(eDataType, initialValue);
			case CompartmentalmodelPackage.SUPPLY_FUNCTION_TYPE:
				return createSupplyFunctionTypeFromString(eDataType, initialValue);
			case CompartmentalmodelPackage.JUNCTION_RULE_TYPE:
				return createJunctionRuleTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CompartmentalmodelPackage.PARAMETER_TYPE:
				return convertParameterTypeToString(eDataType, instanceValue);
			case CompartmentalmodelPackage.SUPPLY_FUNCTION_TYPE:
				return convertSupplyFunctionTypeToString(eDataType, instanceValue);
			case CompartmentalmodelPackage.JUNCTION_RULE_TYPE:
				return convertJunctionRuleTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Compartment createCompartment() {
		CompartmentImpl compartment = new CompartmentImpl();
		return compartment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RateFlow createRateFlow() {
		RateFlowImpl rateFlow = new RateFlowImpl();
		return rateFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContactFlow createContactFlow() {
		ContactFlowImpl contactFlow = new ContactFlowImpl();
		return contactFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExternalSource createExternalSource() {
		ExternalSourceImpl externalSource = new ExternalSourceImpl();
		return externalSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExternalSink createExternalSink() {
		ExternalSinkImpl externalSink = new ExternalSinkImpl();
		return externalSink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StratumSpecificRate createStratumSpecificRate() {
		StratumSpecificRateImpl stratumSpecificRate = new StratumSpecificRateImpl();
		return stratumSpecificRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Product createProduct() {
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SupplyFunction createSupplyFunction() {
		SupplyFunctionImpl supplyFunction = new SupplyFunctionImpl();
		return supplyFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompartmentalModel createCompartmentalModel() {
		CompartmentalModelImpl compartmentalModel = new CompartmentalModelImpl();
		return compartmentalModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterType createParameterTypeFromString(EDataType eDataType, String initialValue) {
		ParameterType result = ParameterType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParameterTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupplyFunctionType createSupplyFunctionTypeFromString(EDataType eDataType, String initialValue) {
		SupplyFunctionType result = SupplyFunctionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSupplyFunctionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JunctionRuleType createJunctionRuleTypeFromString(EDataType eDataType, String initialValue) {
		JunctionRuleType result = JunctionRuleType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJunctionRuleTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompartmentalmodelPackage getCompartmentalmodelPackage() {
		return (CompartmentalmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CompartmentalmodelPackage getPackage() {
		return CompartmentalmodelPackage.eINSTANCE;
	}

} //CompartmentalmodelFactoryImpl
