/**
 */
package compartmentalmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.Product#getName <em>Name</em>}</li>
 *   <li>{@link compartmentalmodel.Product#getDescription <em>Description</em>}</li>
 *   <li>{@link compartmentalmodel.Product#getGroups <em>Groups</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getProduct()
 * @model
 * @generated
 */
public interface Product extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getProduct_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Product#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getProduct_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link compartmentalmodel.Product#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' reference list.
	 * The list contents are of type {@link compartmentalmodel.Group}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getProduct_Groups()
	 * @model
	 * @generated
	 */
	EList<Group> getGroups();

} // Product
