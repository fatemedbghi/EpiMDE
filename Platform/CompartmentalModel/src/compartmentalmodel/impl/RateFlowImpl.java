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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import compartmentalmodel.Parameter;
import compartmentalmodel.RateFlow;
import compartmentalmodel.CompartmentalmodelPackage;
import compartmentalmodel.StratumSpecificRate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rate Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.impl.RateFlowImpl#getRate <em>Rate</em>}</li>
 *   <li>{@link compartmentalmodel.impl.RateFlowImpl#getRateParameter <em>Rate Parameter</em>}</li>
 *   <li>{@link compartmentalmodel.impl.RateFlowImpl#getStratumSpecificRates <em>Stratum Specific Rates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RateFlowImpl extends FlowImpl implements RateFlow {
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
	 * The cached value of the '{@link #getStratumSpecificRates() <em>Stratum Specific Rates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStratumSpecificRates()
	 * @generated
	 * @ordered
	 */
	protected EList<StratumSpecificRate> stratumSpecificRates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RateFlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompartmentalmodelPackage.Literals.RATE_FLOW;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.RATE_FLOW__RATE, oldRate, rate));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.RATE_FLOW__RATE_PARAMETER, oldRateParameter, rateParameter));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.RATE_FLOW__RATE_PARAMETER, oldRateParameter, rateParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<StratumSpecificRate> getStratumSpecificRates() {
		if (stratumSpecificRates == null) {
			stratumSpecificRates = new EObjectContainmentEList<StratumSpecificRate>(StratumSpecificRate.class, this, CompartmentalmodelPackage.RATE_FLOW__STRATUM_SPECIFIC_RATES);
		}
		return stratumSpecificRates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CompartmentalmodelPackage.RATE_FLOW__STRATUM_SPECIFIC_RATES:
				return ((InternalEList<?>)getStratumSpecificRates()).basicRemove(otherEnd, msgs);
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
			case CompartmentalmodelPackage.RATE_FLOW__RATE:
				return getRate();
			case CompartmentalmodelPackage.RATE_FLOW__RATE_PARAMETER:
				if (resolve) return getRateParameter();
				return basicGetRateParameter();
			case CompartmentalmodelPackage.RATE_FLOW__STRATUM_SPECIFIC_RATES:
				return getStratumSpecificRates();
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
			case CompartmentalmodelPackage.RATE_FLOW__RATE:
				setRate((Double)newValue);
				return;
			case CompartmentalmodelPackage.RATE_FLOW__RATE_PARAMETER:
				setRateParameter((Parameter)newValue);
				return;
			case CompartmentalmodelPackage.RATE_FLOW__STRATUM_SPECIFIC_RATES:
				getStratumSpecificRates().clear();
				getStratumSpecificRates().addAll((Collection<? extends StratumSpecificRate>)newValue);
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
			case CompartmentalmodelPackage.RATE_FLOW__RATE:
				setRate(RATE_EDEFAULT);
				return;
			case CompartmentalmodelPackage.RATE_FLOW__RATE_PARAMETER:
				setRateParameter((Parameter)null);
				return;
			case CompartmentalmodelPackage.RATE_FLOW__STRATUM_SPECIFIC_RATES:
				getStratumSpecificRates().clear();
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
			case CompartmentalmodelPackage.RATE_FLOW__RATE:
				return rate != RATE_EDEFAULT;
			case CompartmentalmodelPackage.RATE_FLOW__RATE_PARAMETER:
				return rateParameter != null;
			case CompartmentalmodelPackage.RATE_FLOW__STRATUM_SPECIFIC_RATES:
				return stratumSpecificRates != null && !stratumSpecificRates.isEmpty();
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
		result.append(" (rate: ");
		result.append(rate);
		result.append(')');
		return result.toString();
	}

} //RateFlowImpl
