/**
 */
package compartmentalmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compartment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a population compartment in the model. For epidemiological models, this is a disease state (Susceptible, Infected, etc.). For traffic models, this is a road link with vehicle density. The optional supplyFunction and junctionRule attributes enable traffic network modeling without affecting disease models.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.Compartment#getPrimaryName <em>Primary Name</em>}</li>
 *   <li>{@link compartmentalmodel.Compartment#getPopulation <em>Population</em>}</li>
 *   <li>{@link compartmentalmodel.Compartment#getOutgoingFlows <em>Outgoing Flows</em>}</li>
 *   <li>{@link compartmentalmodel.Compartment#getSecondaryName <em>Secondary Name</em>}</li>
 *   <li>{@link compartmentalmodel.Compartment#getProduct <em>Product</em>}</li>
 *   <li>{@link compartmentalmodel.Compartment#getSupplyFunction <em>Supply Function</em>}</li>
 *   <li>{@link compartmentalmodel.Compartment#getJunctionRule <em>Junction Rule</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment()
 * @model
 * @generated
 */
public interface Compartment extends EObject {
	/**
	 * Returns the value of the '<em><b>Primary Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Name</em>' attribute.
	 * @see #setPrimaryName(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment_PrimaryName()
	 * @model ordered="false"
	 * @generated
	 */
	String getPrimaryName();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Compartment#getPrimaryName <em>Primary Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Name</em>' attribute.
	 * @see #getPrimaryName()
	 * @generated
	 */
	void setPrimaryName(String value);

	/**
	 * Returns the value of the '<em><b>Population</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Population</em>' attribute.
	 * @see #setPopulation(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment_Population()
	 * @model
	 * @generated
	 */
	double getPopulation();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Compartment#getPopulation <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Population</em>' attribute.
	 * @see #getPopulation()
	 * @generated
	 */
	void setPopulation(double value);

	/**
	 * Returns the value of the '<em><b>Outgoing Flows</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.Flow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Flows</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment_OutgoingFlows()
	 * @model containment="true"
	 * @generated
	 */
	EList<Flow> getOutgoingFlows();

	/**
	 * Returns the value of the '<em><b>Secondary Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secondary Name</em>' attribute.
	 * @see #setSecondaryName(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment_SecondaryName()
	 * @model ordered="false"
	 * @generated
	 */
	String getSecondaryName();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Compartment#getSecondaryName <em>Secondary Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secondary Name</em>' attribute.
	 * @see #getSecondaryName()
	 * @generated
	 */
	void setSecondaryName(String value);

	/**
	 * Returns the value of the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' reference.
	 * @see #setProduct(Product)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment_Product()
	 * @model
	 * @generated
	 */
	Product getProduct();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Compartment#getProduct <em>Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(Product value);

	/**
	 * Returns the value of the '<em><b>Supply Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional supply function for traffic network modeling. When present, indicates this compartment represents a road link with capacity constraints (supply-demand dynamics). Disease models leave this null and use standard rate-based flows.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Supply Function</em>' containment reference.
	 * @see #setSupplyFunction(SupplyFunction)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment_SupplyFunction()
	 * @model containment="true"
	 * @generated
	 */
	SupplyFunction getSupplyFunction();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Compartment#getSupplyFunction <em>Supply Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supply Function</em>' containment reference.
	 * @see #getSupplyFunction()
	 * @generated
	 */
	void setSupplyFunction(SupplyFunction value);

	/**
	 * Returns the value of the '<em><b>Junction Rule</b></em>' attribute.
	 * The literals are from the enumeration {@link compartmentalmodel.JunctionRuleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional junction rule for traffic network modeling. Specifies how flows are computed at network junctions (NONE for disease models, PPFIFO for traffic). Default is NONE.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Junction Rule</em>' attribute.
	 * @see compartmentalmodel.JunctionRuleType
	 * @see #setJunctionRule(JunctionRuleType)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartment_JunctionRule()
	 * @model
	 * @generated
	 */
	JunctionRuleType getJunctionRule();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Compartment#getJunctionRule <em>Junction Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Junction Rule</em>' attribute.
	 * @see compartmentalmodel.JunctionRuleType
	 * @see #getJunctionRule()
	 * @generated
	 */
	void setJunctionRule(JunctionRuleType value);

} // Compartment
