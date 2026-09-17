/**
 */
package compartmentalmodel.impl;

import compartmentalmodel.Compartment;
import compartmentalmodel.CompartmentalmodelPackage;
import compartmentalmodel.ExternalSink;
import compartmentalmodel.Parameter;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Sink</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.impl.ExternalSinkImpl#getName <em>Name</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ExternalSinkImpl#getRate <em>Rate</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ExternalSinkImpl#getRateParameter <em>Rate Parameter</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ExternalSinkImpl#getSourceCompartment <em>Source Compartment</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ExternalSinkImpl#getSourceStratum <em>Source Stratum</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExternalSinkImpl extends MinimalEObjectImpl.Container implements ExternalSink {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRate() <em>Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRate()
	 * @generated
	 * @ordered
	 */
	protected static final double RATE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRate() <em>Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRate()
	 * @generated
	 * @ordered
	 */
	protected double rate = RATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRateParameter() <em>Rate Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRateParameter()
	 * @generated
	 * @ordered
	 */
	protected Parameter rateParameter;

	/**
	 * The cached value of the '{@link #getSourceCompartment() <em>Source Compartment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceCompartment()
	 * @generated
	 * @ordered
	 */
	protected Compartment sourceCompartment;

	/**
	 * The default value of the '{@link #getSourceStratum() <em>Source Stratum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceStratum()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_STRATUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceStratum() <em>Source Stratum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceStratum()
	 * @generated
	 * @ordered
	 */
	protected String sourceStratum = SOURCE_STRATUM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalSinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompartmentalmodelPackage.Literals.EXTERNAL_SINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.EXTERNAL_SINK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getRate() {
		return rate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRate(double newRate) {
		double oldRate = rate;
		rate = newRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.EXTERNAL_SINK__RATE, oldRate, rate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parameter getRateParameter() {
		if (rateParameter != null && rateParameter.eIsProxy()) {
			InternalEObject oldRateParameter = (InternalEObject)rateParameter;
			rateParameter = (Parameter)eResolveProxy(oldRateParameter);
			if (rateParameter != oldRateParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.EXTERNAL_SINK__RATE_PARAMETER, oldRateParameter, rateParameter));
			}
		}
		return rateParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter basicGetRateParameter() {
		return rateParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRateParameter(Parameter newRateParameter) {
		Parameter oldRateParameter = rateParameter;
		rateParameter = newRateParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.EXTERNAL_SINK__RATE_PARAMETER, oldRateParameter, rateParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Compartment getSourceCompartment() {
		if (sourceCompartment != null && sourceCompartment.eIsProxy()) {
			InternalEObject oldSourceCompartment = (InternalEObject)sourceCompartment;
			sourceCompartment = (Compartment)eResolveProxy(oldSourceCompartment);
			if (sourceCompartment != oldSourceCompartment) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_COMPARTMENT, oldSourceCompartment, sourceCompartment));
			}
		}
		return sourceCompartment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Compartment basicGetSourceCompartment() {
		return sourceCompartment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourceCompartment(Compartment newSourceCompartment) {
		Compartment oldSourceCompartment = sourceCompartment;
		sourceCompartment = newSourceCompartment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_COMPARTMENT, oldSourceCompartment, sourceCompartment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSourceStratum() {
		return sourceStratum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourceStratum(String newSourceStratum) {
		String oldSourceStratum = sourceStratum;
		sourceStratum = newSourceStratum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_STRATUM, oldSourceStratum, sourceStratum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CompartmentalmodelPackage.EXTERNAL_SINK__NAME:
				return getName();
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE:
				return getRate();
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE_PARAMETER:
				if (resolve) return getRateParameter();
				return basicGetRateParameter();
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_COMPARTMENT:
				if (resolve) return getSourceCompartment();
				return basicGetSourceCompartment();
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_STRATUM:
				return getSourceStratum();
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
			case CompartmentalmodelPackage.EXTERNAL_SINK__NAME:
				setName((String)newValue);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE:
				setRate((Double)newValue);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE_PARAMETER:
				setRateParameter((Parameter)newValue);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_COMPARTMENT:
				setSourceCompartment((Compartment)newValue);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_STRATUM:
				setSourceStratum((String)newValue);
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
			case CompartmentalmodelPackage.EXTERNAL_SINK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE:
				setRate(RATE_EDEFAULT);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE_PARAMETER:
				setRateParameter((Parameter)null);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_COMPARTMENT:
				setSourceCompartment((Compartment)null);
				return;
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_STRATUM:
				setSourceStratum(SOURCE_STRATUM_EDEFAULT);
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
			case CompartmentalmodelPackage.EXTERNAL_SINK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE:
				return rate != RATE_EDEFAULT;
			case CompartmentalmodelPackage.EXTERNAL_SINK__RATE_PARAMETER:
				return rateParameter != null;
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_COMPARTMENT:
				return sourceCompartment != null;
			case CompartmentalmodelPackage.EXTERNAL_SINK__SOURCE_STRATUM:
				return SOURCE_STRATUM_EDEFAULT == null ? sourceStratum != null : !SOURCE_STRATUM_EDEFAULT.equals(sourceStratum);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", rate: ");
		result.append(rate);
		result.append(", sourceStratum: ");
		result.append(sourceStratum);
		result.append(')');
		return result.toString();
	}

} //ExternalSinkImpl
