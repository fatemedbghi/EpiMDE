/**
 */
package compartmentalmodel.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import compartmentalmodel.Parameter;
import compartmentalmodel.CompartmentalmodelPackage;
import compartmentalmodel.StratumSpecificRate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stratum Specific Rate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.impl.StratumSpecificRateImpl#getStratum <em>Stratum</em>}</li>
 *   <li>{@link compartmentalmodel.impl.StratumSpecificRateImpl#getRate <em>Rate</em>}</li>
 *   <li>{@link compartmentalmodel.impl.StratumSpecificRateImpl#getRateParameter <em>Rate Parameter</em>}</li>
 *   <li>{@link compartmentalmodel.impl.StratumSpecificRateImpl#getMultiplier <em>Multiplier</em>}</li>
 *   <li>{@link compartmentalmodel.impl.StratumSpecificRateImpl#getMultiplierParameter <em>Multiplier Parameter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StratumSpecificRateImpl extends MinimalEObjectImpl.Container implements StratumSpecificRate {
	/**
	 * The default value of the '{@link #getStratum() <em>Stratum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStratum()
	 * @generated
	 * @ordered
	 */
	protected static final String STRATUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStratum() <em>Stratum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStratum()
	 * @generated
	 * @ordered
	 */
	protected String stratum = STRATUM_EDEFAULT;

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
	 * The default value of the '{@link #getMultiplier() <em>Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplier()
	 * @generated
	 * @ordered
	 */
	protected static final double MULTIPLIER_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getMultiplier() <em>Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplier()
	 * @generated
	 * @ordered
	 */
	protected double multiplier = MULTIPLIER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMultiplierParameter() <em>Multiplier Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplierParameter()
	 * @generated
	 * @ordered
	 */
	protected Parameter multiplierParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StratumSpecificRateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompartmentalmodelPackage.Literals.STRATUM_SPECIFIC_RATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStratum() {
		return stratum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStratum(String newStratum) {
		String oldStratum = stratum;
		stratum = newStratum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__STRATUM, oldStratum, stratum));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE, oldRate, rate));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE_PARAMETER, oldRateParameter, rateParameter));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE_PARAMETER, oldRateParameter, rateParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMultiplier() {
		return multiplier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMultiplier(double newMultiplier) {
		double oldMultiplier = multiplier;
		multiplier = newMultiplier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER, oldMultiplier, multiplier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parameter getMultiplierParameter() {
		if (multiplierParameter != null && multiplierParameter.eIsProxy()) {
			InternalEObject oldMultiplierParameter = (InternalEObject)multiplierParameter;
			multiplierParameter = (Parameter)eResolveProxy(oldMultiplierParameter);
			if (multiplierParameter != oldMultiplierParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER, oldMultiplierParameter, multiplierParameter));
			}
		}
		return multiplierParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter basicGetMultiplierParameter() {
		return multiplierParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMultiplierParameter(Parameter newMultiplierParameter) {
		Parameter oldMultiplierParameter = multiplierParameter;
		multiplierParameter = newMultiplierParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER, oldMultiplierParameter, multiplierParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__STRATUM:
				return getStratum();
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE:
				return getRate();
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE_PARAMETER:
				if (resolve) return getRateParameter();
				return basicGetRateParameter();
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER:
				return getMultiplier();
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER:
				if (resolve) return getMultiplierParameter();
				return basicGetMultiplierParameter();
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
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__STRATUM:
				setStratum((String)newValue);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE:
				setRate((Double)newValue);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE_PARAMETER:
				setRateParameter((Parameter)newValue);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER:
				setMultiplier((Double)newValue);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER:
				setMultiplierParameter((Parameter)newValue);
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
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__STRATUM:
				setStratum(STRATUM_EDEFAULT);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE:
				setRate(RATE_EDEFAULT);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE_PARAMETER:
				setRateParameter((Parameter)null);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER:
				setMultiplier(MULTIPLIER_EDEFAULT);
				return;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER:
				setMultiplierParameter((Parameter)null);
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
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__STRATUM:
				return STRATUM_EDEFAULT == null ? stratum != null : !STRATUM_EDEFAULT.equals(stratum);
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE:
				return rate != RATE_EDEFAULT;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__RATE_PARAMETER:
				return rateParameter != null;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER:
				return multiplier != MULTIPLIER_EDEFAULT;
			case CompartmentalmodelPackage.STRATUM_SPECIFIC_RATE__MULTIPLIER_PARAMETER:
				return multiplierParameter != null;
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
		result.append(" (stratum: ");
		result.append(stratum);
		result.append(", rate: ");
		result.append(rate);
		result.append(", multiplier: ");
		result.append(multiplier);
		result.append(')');
		return result.toString();
	}

} //StratumSpecificRateImpl
