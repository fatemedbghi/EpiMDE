/**
 */
package compartmentalmodel.impl;

import compartmentalmodel.CompartmentalmodelPackage;
import compartmentalmodel.SupplyFunction;
import compartmentalmodel.SupplyFunctionType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Supply Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.impl.SupplyFunctionImpl#getType <em>Type</em>}</li>
 *   <li>{@link compartmentalmodel.impl.SupplyFunctionImpl#getMaxDensity <em>Max Density</em>}</li>
 *   <li>{@link compartmentalmodel.impl.SupplyFunctionImpl#getCriticalDensity <em>Critical Density</em>}</li>
 *   <li>{@link compartmentalmodel.impl.SupplyFunctionImpl#getMaxThroughput <em>Max Throughput</em>}</li>
 *   <li>{@link compartmentalmodel.impl.SupplyFunctionImpl#getMaxDemand <em>Max Demand</em>}</li>
 *   <li>{@link compartmentalmodel.impl.SupplyFunctionImpl#isIsSourceNode <em>Is Source Node</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SupplyFunctionImpl extends MinimalEObjectImpl.Container implements SupplyFunction {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final SupplyFunctionType TYPE_EDEFAULT = SupplyFunctionType.NONE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected SupplyFunctionType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxDensity() <em>Max Density</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDensity()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_DENSITY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxDensity() <em>Max Density</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDensity()
	 * @generated
	 * @ordered
	 */
	protected double maxDensity = MAX_DENSITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCriticalDensity() <em>Critical Density</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCriticalDensity()
	 * @generated
	 * @ordered
	 */
	protected static final double CRITICAL_DENSITY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCriticalDensity() <em>Critical Density</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCriticalDensity()
	 * @generated
	 * @ordered
	 */
	protected double criticalDensity = CRITICAL_DENSITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxThroughput() <em>Max Throughput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxThroughput()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_THROUGHPUT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxThroughput() <em>Max Throughput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxThroughput()
	 * @generated
	 * @ordered
	 */
	protected double maxThroughput = MAX_THROUGHPUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxDemand() <em>Max Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDemand()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_DEMAND_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxDemand() <em>Max Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDemand()
	 * @generated
	 * @ordered
	 */
	protected double maxDemand = MAX_DEMAND_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsSourceNode() <em>Is Source Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSourceNode()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SOURCE_NODE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSourceNode() <em>Is Source Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSourceNode()
	 * @generated
	 * @ordered
	 */
	protected boolean isSourceNode = IS_SOURCE_NODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SupplyFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompartmentalmodelPackage.Literals.SUPPLY_FUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SupplyFunctionType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(SupplyFunctionType newType) {
		SupplyFunctionType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.SUPPLY_FUNCTION__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMaxDensity() {
		return maxDensity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxDensity(double newMaxDensity) {
		double oldMaxDensity = maxDensity;
		maxDensity = newMaxDensity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DENSITY, oldMaxDensity, maxDensity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getCriticalDensity() {
		return criticalDensity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCriticalDensity(double newCriticalDensity) {
		double oldCriticalDensity = criticalDensity;
		criticalDensity = newCriticalDensity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.SUPPLY_FUNCTION__CRITICAL_DENSITY, oldCriticalDensity, criticalDensity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMaxThroughput() {
		return maxThroughput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxThroughput(double newMaxThroughput) {
		double oldMaxThroughput = maxThroughput;
		maxThroughput = newMaxThroughput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_THROUGHPUT, oldMaxThroughput, maxThroughput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMaxDemand() {
		return maxDemand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxDemand(double newMaxDemand) {
		double oldMaxDemand = maxDemand;
		maxDemand = newMaxDemand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DEMAND, oldMaxDemand, maxDemand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsSourceNode() {
		return isSourceNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsSourceNode(boolean newIsSourceNode) {
		boolean oldIsSourceNode = isSourceNode;
		isSourceNode = newIsSourceNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.SUPPLY_FUNCTION__IS_SOURCE_NODE, oldIsSourceNode, isSourceNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__TYPE:
				return getType();
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DENSITY:
				return getMaxDensity();
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__CRITICAL_DENSITY:
				return getCriticalDensity();
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_THROUGHPUT:
				return getMaxThroughput();
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DEMAND:
				return getMaxDemand();
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__IS_SOURCE_NODE:
				return isIsSourceNode();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__TYPE:
				setType((SupplyFunctionType)newValue);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DENSITY:
				setMaxDensity((Double)newValue);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__CRITICAL_DENSITY:
				setCriticalDensity((Double)newValue);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_THROUGHPUT:
				setMaxThroughput((Double)newValue);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DEMAND:
				setMaxDemand((Double)newValue);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__IS_SOURCE_NODE:
				setIsSourceNode((Boolean)newValue);
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
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DENSITY:
				setMaxDensity(MAX_DENSITY_EDEFAULT);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__CRITICAL_DENSITY:
				setCriticalDensity(CRITICAL_DENSITY_EDEFAULT);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_THROUGHPUT:
				setMaxThroughput(MAX_THROUGHPUT_EDEFAULT);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DEMAND:
				setMaxDemand(MAX_DEMAND_EDEFAULT);
				return;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__IS_SOURCE_NODE:
				setIsSourceNode(IS_SOURCE_NODE_EDEFAULT);
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
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__TYPE:
				return type != TYPE_EDEFAULT;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DENSITY:
				return maxDensity != MAX_DENSITY_EDEFAULT;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__CRITICAL_DENSITY:
				return criticalDensity != CRITICAL_DENSITY_EDEFAULT;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_THROUGHPUT:
				return maxThroughput != MAX_THROUGHPUT_EDEFAULT;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DEMAND:
				return maxDemand != MAX_DEMAND_EDEFAULT;
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__IS_SOURCE_NODE:
				return isSourceNode != IS_SOURCE_NODE_EDEFAULT;
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
		result.append(" (type: ");
		result.append(type);
		result.append(", maxDensity: ");
		result.append(maxDensity);
		result.append(", criticalDensity: ");
		result.append(criticalDensity);
		result.append(", maxThroughput: ");
		result.append(maxThroughput);
		result.append(", maxDemand: ");
		result.append(maxDemand);
		result.append(", isSourceNode: ");
		result.append(isSourceNode);
		result.append(')');
		return result.toString();
	}

} //SupplyFunctionImpl
