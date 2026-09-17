/**
 */
package compartmentalmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * External inflow into a compartment. Examples: births (disease), onramps (traffic), material input (manufacturing), packet generators (networks).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.ExternalSource#getName <em>Name</em>}</li>
 *   <li>{@link compartmentalmodel.ExternalSource#getRate <em>Rate</em>}</li>
 *   <li>{@link compartmentalmodel.ExternalSource#getRateParameter <em>Rate Parameter</em>}</li>
 *   <li>{@link compartmentalmodel.ExternalSource#getTargetCompartment <em>Target Compartment</em>}</li>
 *   <li>{@link compartmentalmodel.ExternalSource#getTargetStratum <em>Target Stratum</em>}</li>
 *   <li>{@link compartmentalmodel.ExternalSource#isFixedRate <em>Fixed Rate</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getExternalSource()
 * @model
 * @generated
 */
public interface ExternalSource extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getExternalSource_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ExternalSource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rate</em>' attribute.
	 * @see #setRate(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getExternalSource_Rate()
	 * @model
	 * @generated
	 */
	double getRate();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ExternalSource#getRate <em>Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rate</em>' attribute.
	 * @see #getRate()
	 * @generated
	 */
	void setRate(double value);

	/**
	 * Returns the value of the '<em><b>Rate Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rate Parameter</em>' reference.
	 * @see #setRateParameter(Parameter)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getExternalSource_RateParameter()
	 * @model
	 * @generated
	 */
	Parameter getRateParameter();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ExternalSource#getRateParameter <em>Rate Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rate Parameter</em>' reference.
	 * @see #getRateParameter()
	 * @generated
	 */
	void setRateParameter(Parameter value);

	/**
	 * Returns the value of the '<em><b>Target Compartment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Compartment</em>' reference.
	 * @see #setTargetCompartment(Compartment)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getExternalSource_TargetCompartment()
	 * @model
	 * @generated
	 */
	Compartment getTargetCompartment();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ExternalSource#getTargetCompartment <em>Target Compartment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Compartment</em>' reference.
	 * @see #getTargetCompartment()
	 * @generated
	 */
	void setTargetCompartment(Compartment value);

	/**
	 * Returns the value of the '<em><b>Target Stratum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Stratum</em>' attribute.
	 * @see #setTargetStratum(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getExternalSource_TargetStratum()
	 * @model
	 * @generated
	 */
	String getTargetStratum();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ExternalSource#getTargetStratum <em>Target Stratum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Stratum</em>' attribute.
	 * @see #getTargetStratum()
	 * @generated
	 */
	void setTargetStratum(String value);

	/**
	 * Returns the value of the '<em><b>Fixed Rate</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fixed Rate</em>' attribute.
	 * @see #setFixedRate(boolean)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getExternalSource_FixedRate()
	 * @model default="false"
	 * @generated
	 */
	boolean isFixedRate();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ExternalSource#isFixedRate <em>Fixed Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed Rate</em>' attribute.
	 * @see #isFixedRate()
	 * @generated
	 */
	void setFixedRate(boolean value);

} // ExternalSource
