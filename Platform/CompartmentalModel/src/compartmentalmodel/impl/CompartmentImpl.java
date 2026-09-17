/**
 */
package compartmentalmodel.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import compartmentalmodel.Compartment;
import compartmentalmodel.Flow;
import compartmentalmodel.JunctionRuleType;
import compartmentalmodel.Product;
import compartmentalmodel.SupplyFunction;
import compartmentalmodel.CompartmentalmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compartment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.impl.CompartmentImpl#getPrimaryName <em>Primary Name</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentImpl#getPopulation <em>Population</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentImpl#getOutgoingFlows <em>Outgoing Flows</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentImpl#getSecondaryName <em>Secondary Name</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentImpl#getSupplyFunction <em>Supply Function</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentImpl#getJunctionRule <em>Junction Rule</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompartmentImpl extends MinimalEObjectImpl.Container implements Compartment {
	/**
	 * The default value of the '{@link #getPrimaryName() <em>Primary Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryName()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIMARY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrimaryName() <em>Primary Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryName()
	 * @generated
	 * @ordered
	 */
	protected String primaryName = PRIMARY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPopulation() <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPopulation()
	 * @generated
	 * @ordered
	 */
	protected static final double POPULATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPopulation() <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPopulation()
	 * @generated
	 * @ordered
	 */
	protected double population = POPULATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOutgoingFlows() <em>Outgoing Flows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingFlows()
	 * @generated
	 * @ordered
	 */
	protected EList<Flow> outgoingFlows;

	/**
	 * The default value of the '{@link #getSecondaryName() <em>Secondary Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondaryName()
	 * @generated
	 * @ordered
	 */
	protected static final String SECONDARY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecondaryName() <em>Secondary Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondaryName()
	 * @generated
	 * @ordered
	 */
	protected String secondaryName = SECONDARY_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProduct() <em>Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProduct()
	 * @generated
	 * @ordered
	 */
	protected Product product;

	/**
	 * The cached value of the '{@link #getSupplyFunction() <em>Supply Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplyFunction()
	 * @generated
	 * @ordered
	 */
	protected SupplyFunction supplyFunction;

	/**
	 * The default value of the '{@link #getJunctionRule() <em>Junction Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJunctionRule()
	 * @generated
	 * @ordered
	 */
	protected static final JunctionRuleType JUNCTION_RULE_EDEFAULT = JunctionRuleType.NONE;

	/**
	 * The cached value of the '{@link #getJunctionRule() <em>Junction Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJunctionRule()
	 * @generated
	 * @ordered
	 */
	protected JunctionRuleType junctionRule = JUNCTION_RULE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompartmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompartmentalmodelPackage.Literals.COMPARTMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPrimaryName() {
		return primaryName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrimaryName(String newPrimaryName) {
		String oldPrimaryName = primaryName;
		primaryName = newPrimaryName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENT__PRIMARY_NAME, oldPrimaryName, primaryName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPopulation() {
		return population;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPopulation(double newPopulation) {
		double oldPopulation = population;
		population = newPopulation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENT__POPULATION, oldPopulation, population));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Flow> getOutgoingFlows() {
		if (outgoingFlows == null) {
			outgoingFlows = new EObjectContainmentEList<Flow>(Flow.class, this, CompartmentalmodelPackage.COMPARTMENT__OUTGOING_FLOWS);
		}
		return outgoingFlows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSecondaryName() {
		return secondaryName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSecondaryName(String newSecondaryName) {
		String oldSecondaryName = secondaryName;
		secondaryName = newSecondaryName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENT__SECONDARY_NAME, oldSecondaryName, secondaryName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Product getProduct() {
		if (product != null && product.eIsProxy()) {
			InternalEObject oldProduct = (InternalEObject)product;
			product = (Product)eResolveProxy(oldProduct);
			if (product != oldProduct) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.COMPARTMENT__PRODUCT, oldProduct, product));
			}
		}
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product basicGetProduct() {
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProduct(Product newProduct) {
		Product oldProduct = product;
		product = newProduct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENT__PRODUCT, oldProduct, product));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SupplyFunction getSupplyFunction() {
		return supplyFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSupplyFunction(SupplyFunction newSupplyFunction, NotificationChain msgs) {
		SupplyFunction oldSupplyFunction = supplyFunction;
		supplyFunction = newSupplyFunction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION, oldSupplyFunction, newSupplyFunction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupplyFunction(SupplyFunction newSupplyFunction) {
		if (newSupplyFunction != supplyFunction) {
			NotificationChain msgs = null;
			if (supplyFunction != null)
				msgs = ((InternalEObject)supplyFunction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION, null, msgs);
			if (newSupplyFunction != null)
				msgs = ((InternalEObject)newSupplyFunction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION, null, msgs);
			msgs = basicSetSupplyFunction(newSupplyFunction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION, newSupplyFunction, newSupplyFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JunctionRuleType getJunctionRule() {
		return junctionRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJunctionRule(JunctionRuleType newJunctionRule) {
		JunctionRuleType oldJunctionRule = junctionRule;
		junctionRule = newJunctionRule == null ? JUNCTION_RULE_EDEFAULT : newJunctionRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENT__JUNCTION_RULE, oldJunctionRule, junctionRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CompartmentalmodelPackage.COMPARTMENT__OUTGOING_FLOWS:
				return ((InternalEList<?>)getOutgoingFlows()).basicRemove(otherEnd, msgs);
			case CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION:
				return basicSetSupplyFunction(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CompartmentalmodelPackage.COMPARTMENT__PRIMARY_NAME:
				return getPrimaryName();
			case CompartmentalmodelPackage.COMPARTMENT__POPULATION:
				return getPopulation();
			case CompartmentalmodelPackage.COMPARTMENT__OUTGOING_FLOWS:
				return getOutgoingFlows();
			case CompartmentalmodelPackage.COMPARTMENT__SECONDARY_NAME:
				return getSecondaryName();
			case CompartmentalmodelPackage.COMPARTMENT__PRODUCT:
				if (resolve) return getProduct();
				return basicGetProduct();
			case CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION:
				return getSupplyFunction();
			case CompartmentalmodelPackage.COMPARTMENT__JUNCTION_RULE:
				return getJunctionRule();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CompartmentalmodelPackage.COMPARTMENT__PRIMARY_NAME:
				setPrimaryName((String)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__POPULATION:
				setPopulation((Double)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__OUTGOING_FLOWS:
				getOutgoingFlows().clear();
				getOutgoingFlows().addAll((Collection<? extends Flow>)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__SECONDARY_NAME:
				setSecondaryName((String)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__PRODUCT:
				setProduct((Product)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION:
				setSupplyFunction((SupplyFunction)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__JUNCTION_RULE:
				setJunctionRule((JunctionRuleType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CompartmentalmodelPackage.COMPARTMENT__PRIMARY_NAME:
				setPrimaryName(PRIMARY_NAME_EDEFAULT);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__POPULATION:
				setPopulation(POPULATION_EDEFAULT);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__OUTGOING_FLOWS:
				getOutgoingFlows().clear();
				return;
			case CompartmentalmodelPackage.COMPARTMENT__SECONDARY_NAME:
				setSecondaryName(SECONDARY_NAME_EDEFAULT);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__PRODUCT:
				setProduct((Product)null);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION:
				setSupplyFunction((SupplyFunction)null);
				return;
			case CompartmentalmodelPackage.COMPARTMENT__JUNCTION_RULE:
				setJunctionRule(JUNCTION_RULE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CompartmentalmodelPackage.COMPARTMENT__PRIMARY_NAME:
				return PRIMARY_NAME_EDEFAULT == null ? primaryName != null : !PRIMARY_NAME_EDEFAULT.equals(primaryName);
			case CompartmentalmodelPackage.COMPARTMENT__POPULATION:
				return population != POPULATION_EDEFAULT;
			case CompartmentalmodelPackage.COMPARTMENT__OUTGOING_FLOWS:
				return outgoingFlows != null && !outgoingFlows.isEmpty();
			case CompartmentalmodelPackage.COMPARTMENT__SECONDARY_NAME:
				return SECONDARY_NAME_EDEFAULT == null ? secondaryName != null : !SECONDARY_NAME_EDEFAULT.equals(secondaryName);
			case CompartmentalmodelPackage.COMPARTMENT__PRODUCT:
				return product != null;
			case CompartmentalmodelPackage.COMPARTMENT__SUPPLY_FUNCTION:
				return supplyFunction != null;
			case CompartmentalmodelPackage.COMPARTMENT__JUNCTION_RULE:
				return junctionRule != JUNCTION_RULE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (PrimaryName: ");
		result.append(primaryName);
		result.append(", population: ");
		result.append(population);
		result.append(", SecondaryName: ");
		result.append(secondaryName);
		result.append(", junctionRule: ");
		result.append(junctionRule);
		result.append(')');
		return result.toString();
	}

} //CompartmentImpl
