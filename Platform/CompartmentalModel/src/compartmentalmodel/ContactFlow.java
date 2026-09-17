/**
 */
package compartmentalmodel;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contact Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Contact-based transmission between compartments, where infection rate depends on contact with individuals in another compartment (e.g., susceptibles becoming exposed through contact with infectious individuals). Models frequency-dependent or density-dependent transmission. Supports both numeric and parametric modeling.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.ContactFlow#getContactCompartment <em>Contact Compartment</em>}</li>
 *   <li>{@link compartmentalmodel.ContactFlow#getContactRate <em>Contact Rate</em>}</li>
 *   <li>{@link compartmentalmodel.ContactFlow#getContactRateParameter <em>Contact Rate Parameter</em>}</li>
 *   <li>{@link compartmentalmodel.ContactFlow#getContactParameters <em>Contact Parameters</em>}</li>
 *   <li>{@link compartmentalmodel.ContactFlow#getStratumSpecificRates <em>Stratum Specific Rates</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getContactFlow()
 * @model
 * @generated
 */
public interface ContactFlow extends Flow {
	/**
	 * Returns the value of the '<em><b>Contact Compartment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The compartment whose population influences transmission (e.g., Infectious compartment for disease transmission, or Infected Mosquitoes for vector-borne diseases).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact Compartment</em>' reference.
	 * @see #setContactCompartment(Compartment)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getContactFlow_ContactCompartment()
	 * @model
	 * @generated
	 */
	Compartment getContactCompartment();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ContactFlow#getContactCompartment <em>Contact Compartment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contact Compartment</em>' reference.
	 * @see #getContactCompartment()
	 * @generated
	 */
	void setContactCompartment(Compartment value);

	/**
	 * Returns the value of the '<em><b>Contact Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Numeric contact/transmission rate. Used for numeric models or as default when contactRateParameter is not set. For parametric models, set this to 0.0 and use contactRateParameter instead.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact Rate</em>' attribute.
	 * @see #setContactRate(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getContactFlow_ContactRate()
	 * @model
	 * @generated
	 */
	double getContactRate();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ContactFlow#getContactRate <em>Contact Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contact Rate</em>' attribute.
	 * @see #getContactRate()
	 * @generated
	 */
	void setContactRate(double value);

	/**
	 * Returns the value of the '<em><b>Contact Rate Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to a Parameter defining this flow's contact rate. When set, this takes precedence over the numeric 'contactRate' attribute. Enables symbolic/parametric modeling (e.g., β₁a(T) for temperature-dependent mosquito biting rate).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact Rate Parameter</em>' reference.
	 * @see #setContactRateParameter(Parameter)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getContactFlow_ContactRateParameter()
	 * @model
	 * @generated
	 */
	Parameter getContactRateParameter();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ContactFlow#getContactRateParameter <em>Contact Rate Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contact Rate Parameter</em>' reference.
	 * @see #getContactRateParameter()
	 * @generated
	 */
	void setContactRateParameter(Parameter value);

	/**
	 * Returns the value of the '<em><b>Contact Parameters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Legacy string-based parameter specification. Deprecated in favor of contactRateParameter for better type safety and tool support.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact Parameters</em>' attribute.
	 * @see #setContactParameters(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getContactFlow_ContactParameters()
	 * @model
	 * @generated
	 */
	String getContactParameters();

	/**
	 * Sets the value of the '{@link compartmentalmodel.ContactFlow#getContactParameters <em>Contact Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contact Parameters</em>' attribute.
	 * @see #getContactParameters()
	 * @generated
	 */
	void setContactParameters(String value);

	/**
	 * Returns the value of the '<em><b>Stratum Specific Rates</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.StratumSpecificRate}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * Defines rates for specific strata (age groups, locations, risk levels, etc.).
	 * Each rate applies to compartments matching the stratum value.
	 * Examples: stratum="0-17" for age, stratum="Urban" for location, stratum="High-Risk" for risk level.
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional stratum-specific contact rates for population stratification. Allows different transmission rates between different population segments (e.g., age-specific contact matrices).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stratum Specific Rates</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getContactFlow_StratumSpecificRates()
	 * @model containment="true"
	 * @generated
	 */
	EList<StratumSpecificRate> getStratumSpecificRates();

} // ContactFlow
