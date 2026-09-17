/**
 */
package compartmentalmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stratum Specific Rate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.StratumSpecificRate#getStratum <em>Stratum</em>}</li>
 *   <li>{@link compartmentalmodel.StratumSpecificRate#getRate <em>Rate</em>}</li>
 *   <li>{@link compartmentalmodel.StratumSpecificRate#getRateParameter <em>Rate Parameter</em>}</li>
 *   <li>{@link compartmentalmodel.StratumSpecificRate#getMultiplier <em>Multiplier</em>}</li>
 *   <li>{@link compartmentalmodel.StratumSpecificRate#getMultiplierParameter <em>Multiplier Parameter</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getStratumSpecificRate()
 * @model
 * @generated
 */
public interface StratumSpecificRate extends EObject {
	/**
	 * Returns the value of the '<em><b>Stratum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * The stratum value from any group (age, location, risk, vaccination, etc.)
	 * Examples: "0-17", "Urban", "High-Risk", "Vaccinated"
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stratum</em>' attribute.
	 * @see #setStratum(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getStratumSpecificRate_Stratum()
	 * @model
	 * @generated
	 */
	String getStratum();

	/**
	 * Sets the value of the '{@link compartmentalmodel.StratumSpecificRate#getStratum <em>Stratum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stratum</em>' attribute.
	 * @see #getStratum()
	 * @generated
	 */
	void setStratum(String value);

	/**
	 * Returns the value of the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * The specific rate for this stratum (contact rate, progression rate, etc.)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rate</em>' attribute.
	 * @see #setRate(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getStratumSpecificRate_Rate()
	 * @model
	 * @generated
	 */
	double getRate();

	/**
	 * Sets the value of the '{@link compartmentalmodel.StratumSpecificRate#getRate <em>Rate</em>}' attribute.
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
	 * @see compartmentalmodel.CompartmentalmodelPackage#getStratumSpecificRate_RateParameter()
	 * @model
	 * @generated
	 */
	Parameter getRateParameter();

	/**
	 * Sets the value of the '{@link compartmentalmodel.StratumSpecificRate#getRateParameter <em>Rate Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rate Parameter</em>' reference.
	 * @see #getRateParameter()
	 * @generated
	 */
	void setRateParameter(Parameter value);

	/**
	 * Returns the value of the '<em><b>Multiplier</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * Additional multiplier for this stratum (susceptibility, severity, etc.)
	 * Default value is 1.0 (no effect). Examples: 0.7 (30% less), 1.3 (30% more)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplier</em>' attribute.
	 * @see #setMultiplier(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getStratumSpecificRate_Multiplier()
	 * @model default="1.0"
	 * @generated
	 */
	double getMultiplier();

	/**
	 * Sets the value of the '{@link compartmentalmodel.StratumSpecificRate#getMultiplier <em>Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplier</em>' attribute.
	 * @see #getMultiplier()
	 * @generated
	 */
	void setMultiplier(double value);

	/**
	 * Returns the value of the '<em><b>Multiplier Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplier Parameter</em>' reference.
	 * @see #setMultiplierParameter(Parameter)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getStratumSpecificRate_MultiplierParameter()
	 * @model
	 * @generated
	 */
	Parameter getMultiplierParameter();

	/**
	 * Sets the value of the '{@link compartmentalmodel.StratumSpecificRate#getMultiplierParameter <em>Multiplier Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplier Parameter</em>' reference.
	 * @see #getMultiplierParameter()
	 * @generated
	 */
	void setMultiplierParameter(Parameter value);

} // StratumSpecificRate