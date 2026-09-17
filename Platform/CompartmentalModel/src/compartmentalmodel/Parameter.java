/**
 */
package compartmentalmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a named parameter that can be referenced by flows, birth sources, and death sinks. Supports symbolic modeling with three types: CONSTANT (fixed named values like pi=0.012), VARIABLE (simulation-time inputs like temperature), and EXPRESSION (computed values like beta2=eta_S*IM). Enables Greek symbols (β, μ, γ) and subscripts in names for professional mathematical notation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.Parameter#getName <em>Name</em>}</li>
 *   <li>{@link compartmentalmodel.Parameter#getType <em>Type</em>}</li>
 *   <li>{@link compartmentalmodel.Parameter#getExpression <em>Expression</em>}</li>
 *   <li>{@link compartmentalmodel.Parameter#getDescription <em>Description</em>}</li>
 *   <li>{@link compartmentalmodel.Parameter#getUnit <em>Unit</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parameter name, displayed in diagrams and equations. Supports Unicode characters including Greek letters (α, β, γ, δ, μ, ρ, θ, λ) and subscripts (ₕ, ᵥ, ₁, ₂). Example: 'β₁', 'μₕ', 'a(T)'.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getParameter_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Parameter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link compartmentalmodel.ParameterType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parameter type: CONSTANT (fixed value with name), VARIABLE (placeholder for runtime input), or EXPRESSION (computed from other parameters/compartments). Defaults to CONSTANT if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see compartmentalmodel.ParameterType
	 * @see #setType(ParameterType)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getParameter_Type()
	 * @model
	 * @generated
	 */
	ParameterType getType();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Parameter#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see compartmentalmodel.ParameterType
	 * @see #getType()
	 * @generated
	 */
	void setType(ParameterType value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For CONSTANT/VARIABLE: numeric value or formula. For EXPRESSION: mathematical expression referencing other parameters and compartments (e.g., 'eta_S * IM' or 'μₕ + 0.017325').
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getParameter_Expression()
	 * @model
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Parameter#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Human-readable description of what this parameter represents (e.g., 'Natural mortality rate of human population').
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getParameter_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Parameter#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Physical unit of measurement (e.g., 'per day', '1/time', 'dimensionless', '°C'). Supports Unicode symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getParameter_Unit()
	 * @model
	 * @generated
	 */
	String getUnit();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Parameter#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(String value);

} // Parameter
