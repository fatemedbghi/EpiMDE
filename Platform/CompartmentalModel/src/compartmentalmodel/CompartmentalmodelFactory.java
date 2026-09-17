/**
 */
package compartmentalmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see compartmentalmodel.CompartmentalmodelPackage
 * @generated
 */
public interface CompartmentalmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CompartmentalmodelFactory eINSTANCE = compartmentalmodel.impl.CompartmentalmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Compartment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Compartment</em>'.
	 * @generated
	 */
	Compartment createCompartment();

	/**
	 * Returns a new object of class '<em>Rate Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rate Flow</em>'.
	 * @generated
	 */
	RateFlow createRateFlow();

	/**
	 * Returns a new object of class '<em>Contact Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contact Flow</em>'.
	 * @generated
	 */
	ContactFlow createContactFlow();

	/**
	 * Returns a new object of class '<em>External Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Source</em>'.
	 * @generated
	 */
	ExternalSource createExternalSource();

	/**
	 * Returns a new object of class '<em>External Sink</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Sink</em>'.
	 * @generated
	 */
	ExternalSink createExternalSink();

	/**
	 * Returns a new object of class '<em>Stratum Specific Rate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stratum Specific Rate</em>'.
	 * @generated
	 */
	StratumSpecificRate createStratumSpecificRate();

	/**
	 * Returns a new object of class '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
	Group createGroup();

	/**
	 * Returns a new object of class '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product</em>'.
	 * @generated
	 */
	Product createProduct();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Supply Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Supply Function</em>'.
	 * @generated
	 */
	SupplyFunction createSupplyFunction();

	/**
	 * Returns a new object of class '<em>Compartmental Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Compartmental Model</em>'.
	 * @generated
	 */
	CompartmentalModel createCompartmentalModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CompartmentalmodelPackage getCompartmentalmodelPackage();

} //CompartmentalmodelFactory
