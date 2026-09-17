/**
 */
package compartmentalmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * EpiMDE Metamodel - A comprehensive framework for epidemiological compartmental models with support for symbolic/parametric modeling, population stratification, and automatic equation generation.
 * <!-- end-model-doc -->
 * @see compartmentalmodel.CompartmentalmodelFactory
 * @model kind="package"
 * @generated
 */
public interface CompartmentalmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "compartmentalmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://example.com/compartmentalmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "compartmental";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CompartmentalmodelPackage eINSTANCE = compartmentalmodel.impl.CompartmentalmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.CompartmentImpl <em>Compartment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.CompartmentImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getCompartment()
	 * @generated
	 */
	int COMPARTMENT = 0;

	/**
	 * The feature id for the '<em><b>Primary Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__PRIMARY_NAME = 0;

	/**
	 * The feature id for the '<em><b>Population</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__POPULATION = 1;

	/**
	 * The feature id for the '<em><b>Outgoing Flows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__OUTGOING_FLOWS = 2;

	/**
	 * The feature id for the '<em><b>Secondary Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__SECONDARY_NAME = 3;

	/**
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__PRODUCT = 4;

	/**
	 * The feature id for the '<em><b>Supply Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__SUPPLY_FUNCTION = 5;

	/**
	 * The feature id for the '<em><b>Junction Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__JUNCTION_RULE = 6;

	/**
	 * The number of structural features of the '<em>Compartment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Compartment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.FlowImpl <em>Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.FlowImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getFlow()
	 * @generated
	 */
	int FLOW = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.RateFlowImpl <em>Rate Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.RateFlowImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getRateFlow()
	 * @generated
	 */
	int RATE_FLOW = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FLOW__DESCRIPTION = FLOW__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FLOW__TARGET = FLOW__TARGET;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FLOW__RATE = FLOW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rate Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FLOW__RATE_PARAMETER = FLOW_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Stratum Specific Rates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FLOW__STRATUM_SPECIFIC_RATES = FLOW_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Rate Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FLOW_FEATURE_COUNT = FLOW_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Rate Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FLOW_OPERATION_COUNT = FLOW_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.ContactFlowImpl <em>Contact Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.ContactFlowImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getContactFlow()
	 * @generated
	 */
	int CONTACT_FLOW = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW__DESCRIPTION = FLOW__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW__TARGET = FLOW__TARGET;

	/**
	 * The feature id for the '<em><b>Contact Compartment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW__CONTACT_COMPARTMENT = FLOW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contact Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW__CONTACT_RATE = FLOW_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Contact Rate Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW__CONTACT_RATE_PARAMETER = FLOW_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contact Parameters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW__CONTACT_PARAMETERS = FLOW_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Stratum Specific Rates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW__STRATUM_SPECIFIC_RATES = FLOW_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Contact Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW_FEATURE_COUNT = FLOW_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Contact Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FLOW_OPERATION_COUNT = FLOW_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.ExternalSourceImpl <em>External Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.ExternalSourceImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getExternalSource()
	 * @generated
	 */
	int EXTERNAL_SOURCE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE__RATE = 1;

	/**
	 * The feature id for the '<em><b>Rate Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE__RATE_PARAMETER = 2;

	/**
	 * The feature id for the '<em><b>Target Compartment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE__TARGET_COMPARTMENT = 3;

	/**
	 * The feature id for the '<em><b>Target Stratum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE__TARGET_STRATUM = 4;

	/**
	 * The feature id for the '<em><b>Fixed Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE__FIXED_RATE = 5;

	/**
	 * The number of structural features of the '<em>External Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>External Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.ExternalSinkImpl <em>External Sink</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.ExternalSinkImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getExternalSink()
	 * @generated
	 */
	int EXTERNAL_SINK = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SINK__NAME = 0;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SINK__RATE = 1;

	/**
	 * The feature id for the '<em><b>Rate Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SINK__RATE_PARAMETER = 2;

	/**
	 * The feature id for the '<em><b>Source Compartment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SINK__SOURCE_COMPARTMENT = 3;

	/**
	 * The feature id for the '<em><b>Source Stratum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SINK__SOURCE_STRATUM = 4;

	/**
	 * The number of structural features of the '<em>External Sink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SINK_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>External Sink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.StratumSpecificRateImpl <em>Stratum Specific Rate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.StratumSpecificRateImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getStratumSpecificRate()
	 * @generated
	 */
	int STRATUM_SPECIFIC_RATE = 6;

	/**
	 * The feature id for the '<em><b>Stratum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATUM_SPECIFIC_RATE__STRATUM = 0;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATUM_SPECIFIC_RATE__RATE = 1;

	/**
	 * The feature id for the '<em><b>Rate Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATUM_SPECIFIC_RATE__RATE_PARAMETER = 2;

	/**
	 * The feature id for the '<em><b>Multiplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATUM_SPECIFIC_RATE__MULTIPLIER = 3;

	/**
	 * The feature id for the '<em><b>Multiplier Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER = 4;

	/**
	 * The number of structural features of the '<em>Stratum Specific Rate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATUM_SPECIFIC_RATE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Stratum Specific Rate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATUM_SPECIFIC_RATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.GroupImpl <em>Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.GroupImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getGroup()
	 * @generated
	 */
	int GROUP = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__VALUES = 2;

	/**
	 * The number of structural features of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.ProductImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__GROUPS = 2;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.ParameterImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__EXPRESSION = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__UNIT = 4;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.SupplyFunctionImpl <em>Supply Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.SupplyFunctionImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getSupplyFunction()
	 * @generated
	 */
	int SUPPLY_FUNCTION = 10;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Max Density</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION__MAX_DENSITY = 1;

	/**
	 * The feature id for the '<em><b>Critical Density</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION__CRITICAL_DENSITY = 2;

	/**
	 * The feature id for the '<em><b>Max Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION__MAX_THROUGHPUT = 3;

	/**
	 * The feature id for the '<em><b>Max Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION__MAX_DEMAND = 4;

	/**
	 * The feature id for the '<em><b>Is Source Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION__IS_SOURCE_NODE = 5;

	/**
	 * The number of structural features of the '<em>Supply Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Supply Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLY_FUNCTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.impl.CompartmentalModelImpl <em>Compartmental Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.impl.CompartmentalModelImpl
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getCompartmentalModel()
	 * @generated
	 */
	int COMPARTMENTAL_MODEL = 11;

	/**
	 * The feature id for the '<em><b>Compartments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__COMPARTMENTS = 0;

	/**
	 * The feature id for the '<em><b>External Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__EXTERNAL_SOURCES = 1;

	/**
	 * The feature id for the '<em><b>External Sinks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__EXTERNAL_SINKS = 2;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__GROUPS = 3;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__PRODUCTS = 4;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__PARAMETERS = 5;

	/**
	 * The feature id for the '<em><b>Total Population</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__TOTAL_POPULATION = 6;

	/**
	 * The feature id for the '<em><b>Global Birth Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE = 7;

	/**
	 * The feature id for the '<em><b>Global Death Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE = 8;

	/**
	 * The number of structural features of the '<em>Compartmental Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Compartmental Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENTAL_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link compartmentalmodel.ParameterType <em>Parameter Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.ParameterType
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getParameterType()
	 * @generated
	 */
	int PARAMETER_TYPE = 12;


	/**
	 * The meta object id for the '{@link compartmentalmodel.SupplyFunctionType <em>Supply Function Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.SupplyFunctionType
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getSupplyFunctionType()
	 * @generated
	 */
	int SUPPLY_FUNCTION_TYPE = 13;

	/**
	 * The meta object id for the '{@link compartmentalmodel.JunctionRuleType <em>Junction Rule Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see compartmentalmodel.JunctionRuleType
	 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getJunctionRuleType()
	 * @generated
	 */
	int JUNCTION_RULE_TYPE = 14;


	/**
	 * Returns the meta object for class '{@link compartmentalmodel.Compartment <em>Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compartment</em>'.
	 * @see compartmentalmodel.Compartment
	 * @generated
	 */
	EClass getCompartment();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Compartment#getPrimaryName <em>Primary Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary Name</em>'.
	 * @see compartmentalmodel.Compartment#getPrimaryName()
	 * @see #getCompartment()
	 * @generated
	 */
	EAttribute getCompartment_PrimaryName();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Compartment#getPopulation <em>Population</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Population</em>'.
	 * @see compartmentalmodel.Compartment#getPopulation()
	 * @see #getCompartment()
	 * @generated
	 */
	EAttribute getCompartment_Population();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.Compartment#getOutgoingFlows <em>Outgoing Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Flows</em>'.
	 * @see compartmentalmodel.Compartment#getOutgoingFlows()
	 * @see #getCompartment()
	 * @generated
	 */
	EReference getCompartment_OutgoingFlows();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Compartment#getSecondaryName <em>Secondary Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secondary Name</em>'.
	 * @see compartmentalmodel.Compartment#getSecondaryName()
	 * @see #getCompartment()
	 * @generated
	 */
	EAttribute getCompartment_SecondaryName();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.Compartment#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see compartmentalmodel.Compartment#getProduct()
	 * @see #getCompartment()
	 * @generated
	 */
	EReference getCompartment_Product();

	/**
	 * Returns the meta object for the containment reference '{@link compartmentalmodel.Compartment#getSupplyFunction <em>Supply Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Supply Function</em>'.
	 * @see compartmentalmodel.Compartment#getSupplyFunction()
	 * @see #getCompartment()
	 * @generated
	 */
	EReference getCompartment_SupplyFunction();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Compartment#getJunctionRule <em>Junction Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Junction Rule</em>'.
	 * @see compartmentalmodel.Compartment#getJunctionRule()
	 * @see #getCompartment()
	 * @generated
	 */
	EAttribute getCompartment_JunctionRule();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.Flow <em>Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flow</em>'.
	 * @see compartmentalmodel.Flow
	 * @generated
	 */
	EClass getFlow();


	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Flow#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see compartmentalmodel.Flow#getDescription()
	 * @see #getFlow()
	 * @generated
	 */
	EAttribute getFlow_Description();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.Flow#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see compartmentalmodel.Flow#getTarget()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Target();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.RateFlow <em>Rate Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rate Flow</em>'.
	 * @see compartmentalmodel.RateFlow
	 * @generated
	 */
	EClass getRateFlow();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.RateFlow#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate</em>'.
	 * @see compartmentalmodel.RateFlow#getRate()
	 * @see #getRateFlow()
	 * @generated
	 */
	EAttribute getRateFlow_Rate();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.RateFlow#getRateParameter <em>Rate Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rate Parameter</em>'.
	 * @see compartmentalmodel.RateFlow#getRateParameter()
	 * @see #getRateFlow()
	 * @generated
	 */
	EReference getRateFlow_RateParameter();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.RateFlow#getStratumSpecificRates <em>Stratum Specific Rates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stratum Specific Rates</em>'.
	 * @see compartmentalmodel.RateFlow#getStratumSpecificRates()
	 * @see #getRateFlow()
	 * @generated
	 */
	EReference getRateFlow_StratumSpecificRates();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.ContactFlow <em>Contact Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contact Flow</em>'.
	 * @see compartmentalmodel.ContactFlow
	 * @generated
	 */
	EClass getContactFlow();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.ContactFlow#getContactCompartment <em>Contact Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contact Compartment</em>'.
	 * @see compartmentalmodel.ContactFlow#getContactCompartment()
	 * @see #getContactFlow()
	 * @generated
	 */
	EReference getContactFlow_ContactCompartment();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ContactFlow#getContactRate <em>Contact Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contact Rate</em>'.
	 * @see compartmentalmodel.ContactFlow#getContactRate()
	 * @see #getContactFlow()
	 * @generated
	 */
	EAttribute getContactFlow_ContactRate();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.ContactFlow#getContactRateParameter <em>Contact Rate Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contact Rate Parameter</em>'.
	 * @see compartmentalmodel.ContactFlow#getContactRateParameter()
	 * @see #getContactFlow()
	 * @generated
	 */
	EReference getContactFlow_ContactRateParameter();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ContactFlow#getContactParameters <em>Contact Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contact Parameters</em>'.
	 * @see compartmentalmodel.ContactFlow#getContactParameters()
	 * @see #getContactFlow()
	 * @generated
	 */
	EAttribute getContactFlow_ContactParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.ContactFlow#getStratumSpecificRates <em>Stratum Specific Rates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stratum Specific Rates</em>'.
	 * @see compartmentalmodel.ContactFlow#getStratumSpecificRates()
	 * @see #getContactFlow()
	 * @generated
	 */
	EReference getContactFlow_StratumSpecificRates();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.ExternalSource <em>External Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Source</em>'.
	 * @see compartmentalmodel.ExternalSource
	 * @generated
	 */
	EClass getExternalSource();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ExternalSource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see compartmentalmodel.ExternalSource#getName()
	 * @see #getExternalSource()
	 * @generated
	 */
	EAttribute getExternalSource_Name();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ExternalSource#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate</em>'.
	 * @see compartmentalmodel.ExternalSource#getRate()
	 * @see #getExternalSource()
	 * @generated
	 */
	EAttribute getExternalSource_Rate();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.ExternalSource#getRateParameter <em>Rate Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rate Parameter</em>'.
	 * @see compartmentalmodel.ExternalSource#getRateParameter()
	 * @see #getExternalSource()
	 * @generated
	 */
	EReference getExternalSource_RateParameter();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.ExternalSource#getTargetCompartment <em>Target Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Compartment</em>'.
	 * @see compartmentalmodel.ExternalSource#getTargetCompartment()
	 * @see #getExternalSource()
	 * @generated
	 */
	EReference getExternalSource_TargetCompartment();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ExternalSource#getTargetStratum <em>Target Stratum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Stratum</em>'.
	 * @see compartmentalmodel.ExternalSource#getTargetStratum()
	 * @see #getExternalSource()
	 * @generated
	 */
	EAttribute getExternalSource_TargetStratum();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ExternalSource#isFixedRate <em>Fixed Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed Rate</em>'.
	 * @see compartmentalmodel.ExternalSource#isFixedRate()
	 * @see #getExternalSource()
	 * @generated
	 */
	EAttribute getExternalSource_FixedRate();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.ExternalSink <em>External Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Sink</em>'.
	 * @see compartmentalmodel.ExternalSink
	 * @generated
	 */
	EClass getExternalSink();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ExternalSink#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see compartmentalmodel.ExternalSink#getName()
	 * @see #getExternalSink()
	 * @generated
	 */
	EAttribute getExternalSink_Name();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ExternalSink#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate</em>'.
	 * @see compartmentalmodel.ExternalSink#getRate()
	 * @see #getExternalSink()
	 * @generated
	 */
	EAttribute getExternalSink_Rate();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.ExternalSink#getRateParameter <em>Rate Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rate Parameter</em>'.
	 * @see compartmentalmodel.ExternalSink#getRateParameter()
	 * @see #getExternalSink()
	 * @generated
	 */
	EReference getExternalSink_RateParameter();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.ExternalSink#getSourceCompartment <em>Source Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Compartment</em>'.
	 * @see compartmentalmodel.ExternalSink#getSourceCompartment()
	 * @see #getExternalSink()
	 * @generated
	 */
	EReference getExternalSink_SourceCompartment();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.ExternalSink#getSourceStratum <em>Source Stratum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Stratum</em>'.
	 * @see compartmentalmodel.ExternalSink#getSourceStratum()
	 * @see #getExternalSink()
	 * @generated
	 */
	EAttribute getExternalSink_SourceStratum();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.StratumSpecificRate <em>Stratum Specific Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stratum Specific Rate</em>'.
	 * @see compartmentalmodel.StratumSpecificRate
	 * @generated
	 */
	EClass getStratumSpecificRate();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.StratumSpecificRate#getStratum <em>Stratum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stratum</em>'.
	 * @see compartmentalmodel.StratumSpecificRate#getStratum()
	 * @see #getStratumSpecificRate()
	 * @generated
	 */
	EAttribute getStratumSpecificRate_Stratum();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.StratumSpecificRate#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate</em>'.
	 * @see compartmentalmodel.StratumSpecificRate#getRate()
	 * @see #getStratumSpecificRate()
	 * @generated
	 */
	EAttribute getStratumSpecificRate_Rate();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.StratumSpecificRate#getRateParameter <em>Rate Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rate Parameter</em>'.
	 * @see compartmentalmodel.StratumSpecificRate#getRateParameter()
	 * @see #getStratumSpecificRate()
	 * @generated
	 */
	EReference getStratumSpecificRate_RateParameter();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.StratumSpecificRate#getMultiplier <em>Multiplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplier</em>'.
	 * @see compartmentalmodel.StratumSpecificRate#getMultiplier()
	 * @see #getStratumSpecificRate()
	 * @generated
	 */
	EAttribute getStratumSpecificRate_Multiplier();

	/**
	 * Returns the meta object for the reference '{@link compartmentalmodel.StratumSpecificRate#getMultiplierParameter <em>Multiplier Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Multiplier Parameter</em>'.
	 * @see compartmentalmodel.StratumSpecificRate#getMultiplierParameter()
	 * @see #getStratumSpecificRate()
	 * @generated
	 */
	EReference getStratumSpecificRate_MultiplierParameter();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.CompartmentalModel <em>Compartmental Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compartmental Model</em>'.
	 * @see compartmentalmodel.CompartmentalModel
	 * @generated
	 */
	EClass getCompartmentalModel();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.CompartmentalModel#getCompartments <em>Compartments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Compartments</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getCompartments()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EReference getCompartmentalModel_Compartments();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.CompartmentalModel#getExternalSources <em>External Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Sources</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getExternalSources()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EReference getCompartmentalModel_ExternalSources();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.CompartmentalModel#getExternalSinks <em>External Sinks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Sinks</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getExternalSinks()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EReference getCompartmentalModel_ExternalSinks();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.CompartmentalModel#getTotalPopulation <em>Total Population</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Population</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getTotalPopulation()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EAttribute getCompartmentalModel_TotalPopulation();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.CompartmentalModel#getGlobalBirthRate <em>Global Birth Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Global Birth Rate</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getGlobalBirthRate()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EAttribute getCompartmentalModel_GlobalBirthRate();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.CompartmentalModel#getGlobalDeathRate <em>Global Death Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Global Death Rate</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getGlobalDeathRate()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EAttribute getCompartmentalModel_GlobalDeathRate();

	/**
	 * Returns the meta object for enum '{@link compartmentalmodel.ParameterType <em>Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parameter Type</em>'.
	 * @see compartmentalmodel.ParameterType
	 * @generated
	 */
	EEnum getParameterType();

	/**
	 * Returns the meta object for enum '{@link compartmentalmodel.SupplyFunctionType <em>Supply Function Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Supply Function Type</em>'.
	 * @see compartmentalmodel.SupplyFunctionType
	 * @generated
	 */
	EEnum getSupplyFunctionType();

	/**
	 * Returns the meta object for enum '{@link compartmentalmodel.JunctionRuleType <em>Junction Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Junction Rule Type</em>'.
	 * @see compartmentalmodel.JunctionRuleType
	 * @generated
	 */
	EEnum getJunctionRuleType();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.CompartmentalModel#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Groups</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getGroups()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EReference getCompartmentalModel_Groups();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.CompartmentalModel#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Products</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getProducts()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EReference getCompartmentalModel_Products();

	/**
	 * Returns the meta object for the containment reference list '{@link compartmentalmodel.CompartmentalModel#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see compartmentalmodel.CompartmentalModel#getParameters()
	 * @see #getCompartmentalModel()
	 * @generated
	 */
	EReference getCompartmentalModel_Parameters();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group</em>'.
	 * @see compartmentalmodel.Group
	 * @generated
	 */
	EClass getGroup();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Group#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see compartmentalmodel.Group#getName()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Name();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Group#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see compartmentalmodel.Group#getDescription()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Description();

	/**
	 * Returns the meta object for the attribute list '{@link compartmentalmodel.Group#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see compartmentalmodel.Group#getValues()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Values();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see compartmentalmodel.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Product#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see compartmentalmodel.Product#getName()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Name();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Product#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see compartmentalmodel.Product#getDescription()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Description();

	/**
	 * Returns the meta object for the reference list '{@link compartmentalmodel.Product#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Groups</em>'.
	 * @see compartmentalmodel.Product#getGroups()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Groups();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see compartmentalmodel.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Parameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see compartmentalmodel.Parameter#getName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see compartmentalmodel.Parameter#getType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Type();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Parameter#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see compartmentalmodel.Parameter#getExpression()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Expression();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Parameter#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see compartmentalmodel.Parameter#getDescription()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Description();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.Parameter#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see compartmentalmodel.Parameter#getUnit()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Unit();

	/**
	 * Returns the meta object for class '{@link compartmentalmodel.SupplyFunction <em>Supply Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Supply Function</em>'.
	 * @see compartmentalmodel.SupplyFunction
	 * @generated
	 */
	EClass getSupplyFunction();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.SupplyFunction#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see compartmentalmodel.SupplyFunction#getType()
	 * @see #getSupplyFunction()
	 * @generated
	 */
	EAttribute getSupplyFunction_Type();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.SupplyFunction#getMaxDensity <em>Max Density</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Density</em>'.
	 * @see compartmentalmodel.SupplyFunction#getMaxDensity()
	 * @see #getSupplyFunction()
	 * @generated
	 */
	EAttribute getSupplyFunction_MaxDensity();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.SupplyFunction#getCriticalDensity <em>Critical Density</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Critical Density</em>'.
	 * @see compartmentalmodel.SupplyFunction#getCriticalDensity()
	 * @see #getSupplyFunction()
	 * @generated
	 */
	EAttribute getSupplyFunction_CriticalDensity();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.SupplyFunction#getMaxThroughput <em>Max Throughput</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Throughput</em>'.
	 * @see compartmentalmodel.SupplyFunction#getMaxThroughput()
	 * @see #getSupplyFunction()
	 * @generated
	 */
	EAttribute getSupplyFunction_MaxThroughput();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.SupplyFunction#getMaxDemand <em>Max Demand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Demand</em>'.
	 * @see compartmentalmodel.SupplyFunction#getMaxDemand()
	 * @see #getSupplyFunction()
	 * @generated
	 */
	EAttribute getSupplyFunction_MaxDemand();

	/**
	 * Returns the meta object for the attribute '{@link compartmentalmodel.SupplyFunction#isIsSourceNode <em>Is Source Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Source Node</em>'.
	 * @see compartmentalmodel.SupplyFunction#isIsSourceNode()
	 * @see #getSupplyFunction()
	 * @generated
	 */
	EAttribute getSupplyFunction_IsSourceNode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CompartmentalmodelFactory getCompartmentalmodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.CompartmentImpl <em>Compartment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.CompartmentImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getCompartment()
		 * @generated
		 */
		EClass COMPARTMENT = eINSTANCE.getCompartment();

		/**
		 * The meta object literal for the '<em><b>Primary Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENT__PRIMARY_NAME = eINSTANCE.getCompartment_PrimaryName();

		/**
		 * The meta object literal for the '<em><b>Population</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENT__POPULATION = eINSTANCE.getCompartment_Population();

		/**
		 * The meta object literal for the '<em><b>Outgoing Flows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENT__OUTGOING_FLOWS = eINSTANCE.getCompartment_OutgoingFlows();

		/**
		 * The meta object literal for the '<em><b>Secondary Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENT__SECONDARY_NAME = eINSTANCE.getCompartment_SecondaryName();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENT__PRODUCT = eINSTANCE.getCompartment_Product();

		/**
		 * The meta object literal for the '<em><b>Supply Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENT__SUPPLY_FUNCTION = eINSTANCE.getCompartment_SupplyFunction();

		/**
		 * The meta object literal for the '<em><b>Junction Rule</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENT__JUNCTION_RULE = eINSTANCE.getCompartment_JunctionRule();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.FlowImpl <em>Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.FlowImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getFlow()
		 * @generated
		 */
		EClass FLOW = eINSTANCE.getFlow();


		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW__DESCRIPTION = eINSTANCE.getFlow_Description();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__TARGET = eINSTANCE.getFlow_Target();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.RateFlowImpl <em>Rate Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.RateFlowImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getRateFlow()
		 * @generated
		 */
		EClass RATE_FLOW = eINSTANCE.getRateFlow();

		/**
		 * The meta object literal for the '<em><b>Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RATE_FLOW__RATE = eINSTANCE.getRateFlow_Rate();

		/**
		 * The meta object literal for the '<em><b>Rate Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RATE_FLOW__RATE_PARAMETER = eINSTANCE.getRateFlow_RateParameter();

		/**
		 * The meta object literal for the '<em><b>Stratum Specific Rates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RATE_FLOW__STRATUM_SPECIFIC_RATES = eINSTANCE.getRateFlow_StratumSpecificRates();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.ContactFlowImpl <em>Contact Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.ContactFlowImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getContactFlow()
		 * @generated
		 */
		EClass CONTACT_FLOW = eINSTANCE.getContactFlow();

		/**
		 * The meta object literal for the '<em><b>Contact Compartment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTACT_FLOW__CONTACT_COMPARTMENT = eINSTANCE.getContactFlow_ContactCompartment();

		/**
		 * The meta object literal for the '<em><b>Contact Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTACT_FLOW__CONTACT_RATE = eINSTANCE.getContactFlow_ContactRate();

		/**
		 * The meta object literal for the '<em><b>Contact Rate Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTACT_FLOW__CONTACT_RATE_PARAMETER = eINSTANCE.getContactFlow_ContactRateParameter();

		/**
		 * The meta object literal for the '<em><b>Contact Parameters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTACT_FLOW__CONTACT_PARAMETERS = eINSTANCE.getContactFlow_ContactParameters();

		/**
		 * The meta object literal for the '<em><b>Stratum Specific Rates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTACT_FLOW__STRATUM_SPECIFIC_RATES = eINSTANCE.getContactFlow_StratumSpecificRates();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.ExternalSourceImpl <em>External Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.ExternalSourceImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getExternalSource()
		 * @generated
		 */
		EClass EXTERNAL_SOURCE = eINSTANCE.getExternalSource();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_SOURCE__NAME = eINSTANCE.getExternalSource_Name();

		/**
		 * The meta object literal for the '<em><b>Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_SOURCE__RATE = eINSTANCE.getExternalSource_Rate();

		/**
		 * The meta object literal for the '<em><b>Rate Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_SOURCE__RATE_PARAMETER = eINSTANCE.getExternalSource_RateParameter();

		/**
		 * The meta object literal for the '<em><b>Target Compartment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_SOURCE__TARGET_COMPARTMENT = eINSTANCE.getExternalSource_TargetCompartment();

		/**
		 * The meta object literal for the '<em><b>Target Stratum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_SOURCE__TARGET_STRATUM = eINSTANCE.getExternalSource_TargetStratum();

		/**
		 * The meta object literal for the '<em><b>Fixed Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_SOURCE__FIXED_RATE = eINSTANCE.getExternalSource_FixedRate();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.ExternalSinkImpl <em>External Sink</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.ExternalSinkImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getExternalSink()
		 * @generated
		 */
		EClass EXTERNAL_SINK = eINSTANCE.getExternalSink();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_SINK__NAME = eINSTANCE.getExternalSink_Name();

		/**
		 * The meta object literal for the '<em><b>Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_SINK__RATE = eINSTANCE.getExternalSink_Rate();

		/**
		 * The meta object literal for the '<em><b>Rate Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_SINK__RATE_PARAMETER = eINSTANCE.getExternalSink_RateParameter();

		/**
		 * The meta object literal for the '<em><b>Source Compartment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_SINK__SOURCE_COMPARTMENT = eINSTANCE.getExternalSink_SourceCompartment();

		/**
		 * The meta object literal for the '<em><b>Source Stratum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_SINK__SOURCE_STRATUM = eINSTANCE.getExternalSink_SourceStratum();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.StratumSpecificRateImpl <em>Stratum Specific Rate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.StratumSpecificRateImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getStratumSpecificRate()
		 * @generated
		 */
		EClass STRATUM_SPECIFIC_RATE = eINSTANCE.getStratumSpecificRate();

		/**
		 * The meta object literal for the '<em><b>Stratum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRATUM_SPECIFIC_RATE__STRATUM = eINSTANCE.getStratumSpecificRate_Stratum();

		/**
		 * The meta object literal for the '<em><b>Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRATUM_SPECIFIC_RATE__RATE = eINSTANCE.getStratumSpecificRate_Rate();

		/**
		 * The meta object literal for the '<em><b>Rate Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATUM_SPECIFIC_RATE__RATE_PARAMETER = eINSTANCE.getStratumSpecificRate_RateParameter();

		/**
		 * The meta object literal for the '<em><b>Multiplier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRATUM_SPECIFIC_RATE__MULTIPLIER = eINSTANCE.getStratumSpecificRate_Multiplier();

		/**
		 * The meta object literal for the '<em><b>Multiplier Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER = eINSTANCE.getStratumSpecificRate_MultiplierParameter();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.ParameterType <em>Parameter Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.ParameterType
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getParameterType()
		 * @generated
		 */
		EEnum PARAMETER_TYPE = eINSTANCE.getParameterType();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.SupplyFunctionType <em>Supply Function Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.SupplyFunctionType
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getSupplyFunctionType()
		 * @generated
		 */
		EEnum SUPPLY_FUNCTION_TYPE = eINSTANCE.getSupplyFunctionType();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.JunctionRuleType <em>Junction Rule Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.JunctionRuleType
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getJunctionRuleType()
		 * @generated
		 */
		EEnum JUNCTION_RULE_TYPE = eINSTANCE.getJunctionRuleType();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.GroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.GroupImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getGroup()
		 * @generated
		 */
		EClass GROUP = eINSTANCE.getGroup();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP__NAME = eINSTANCE.getGroup_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP__DESCRIPTION = eINSTANCE.getGroup_Description();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP__VALUES = eINSTANCE.getGroup_Values();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.ProductImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__NAME = eINSTANCE.getProduct_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__DESCRIPTION = eINSTANCE.getProduct_Description();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__GROUPS = eINSTANCE.getProduct_Groups();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.ParameterImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__EXPRESSION = eINSTANCE.getParameter_Expression();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__DESCRIPTION = eINSTANCE.getParameter_Description();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__UNIT = eINSTANCE.getParameter_Unit();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.SupplyFunctionImpl <em>Supply Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.SupplyFunctionImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getSupplyFunction()
		 * @generated
		 */
		EClass SUPPLY_FUNCTION = eINSTANCE.getSupplyFunction();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPPLY_FUNCTION__TYPE = eINSTANCE.getSupplyFunction_Type();

		/**
		 * The meta object literal for the '<em><b>Max Density</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPPLY_FUNCTION__MAX_DENSITY = eINSTANCE.getSupplyFunction_MaxDensity();

		/**
		 * The meta object literal for the '<em><b>Critical Density</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPPLY_FUNCTION__CRITICAL_DENSITY = eINSTANCE.getSupplyFunction_CriticalDensity();

		/**
		 * The meta object literal for the '<em><b>Max Throughput</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPPLY_FUNCTION__MAX_THROUGHPUT = eINSTANCE.getSupplyFunction_MaxThroughput();

		/**
		 * The meta object literal for the '<em><b>Max Demand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPPLY_FUNCTION__MAX_DEMAND = eINSTANCE.getSupplyFunction_MaxDemand();

		/**
		 * The meta object literal for the '<em><b>Is Source Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPPLY_FUNCTION__IS_SOURCE_NODE = eINSTANCE.getSupplyFunction_IsSourceNode();

		/**
		 * The meta object literal for the '{@link compartmentalmodel.impl.CompartmentalModelImpl <em>Compartmental Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see compartmentalmodel.impl.CompartmentalModelImpl
		 * @see compartmentalmodel.impl.CompartmentalmodelPackageImpl#getCompartmentalModel()
		 * @generated
		 */
		EClass COMPARTMENTAL_MODEL = eINSTANCE.getCompartmentalModel();

		/**
		 * The meta object literal for the '<em><b>Compartments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENTAL_MODEL__COMPARTMENTS = eINSTANCE.getCompartmentalModel_Compartments();

		/**
		 * The meta object literal for the '<em><b>External Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENTAL_MODEL__EXTERNAL_SOURCES = eINSTANCE.getCompartmentalModel_ExternalSources();

		/**
		 * The meta object literal for the '<em><b>External Sinks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENTAL_MODEL__EXTERNAL_SINKS = eINSTANCE.getCompartmentalModel_ExternalSinks();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENTAL_MODEL__GROUPS = eINSTANCE.getCompartmentalModel_Groups();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENTAL_MODEL__PRODUCTS = eINSTANCE.getCompartmentalModel_Products();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENTAL_MODEL__PARAMETERS = eINSTANCE.getCompartmentalModel_Parameters();

		/**
		 * The meta object literal for the '<em><b>Total Population</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENTAL_MODEL__TOTAL_POPULATION = eINSTANCE.getCompartmentalModel_TotalPopulation();

		/**
		 * The meta object literal for the '<em><b>Global Birth Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE = eINSTANCE.getCompartmentalModel_GlobalBirthRate();

		/**
		 * The meta object literal for the '<em><b>Global Death Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE = eINSTANCE.getCompartmentalModel_GlobalDeathRate();

	}

} //CompartmentalmodelPackage
