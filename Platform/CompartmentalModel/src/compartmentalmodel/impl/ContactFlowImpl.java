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
import compartmentalmodel.Compartment;
import compartmentalmodel.ContactFlow;
import compartmentalmodel.Parameter;
import compartmentalmodel.CompartmentalmodelPackage;
import compartmentalmodel.StratumSpecificRate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contact Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.impl.ContactFlowImpl#getContactCompartment <em>Contact Compartment</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ContactFlowImpl#getContactRate <em>Contact Rate</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ContactFlowImpl#getContactRateParameter <em>Contact Rate Parameter</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ContactFlowImpl#getContactParameters <em>Contact Parameters</em>}</li>
 *   <li>{@link compartmentalmodel.impl.ContactFlowImpl#getStratumSpecificRates <em>Stratum Specific Rates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContactFlowImpl extends FlowImpl implements ContactFlow {
	/**
	 * The cached value of the '{@link #getContactCompartment() <em>Contact Compartment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContactCompartment()
	 * @generated
	 * @ordered
	 */
	protected Compartment contactCompartment;

	/**
	 * The default value of the '{@link #getContactRate() <em>Contact Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContactRate()
	 * @generated
	 * @ordered
	 */
	protected static final double CONTACT_RATE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getContactRate() <em>Contact Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContactRate()
	 * @generated
	 * @ordered
	 */
	protected double contactRate = CONTACT_RATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContactRateParameter() <em>Contact Rate Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContactRateParameter()
	 * @generated
	 * @ordered
	 */
	protected Parameter contactRateParameter;

	/**
	 * The default value of the '{@link #getContactParameters() <em>Contact Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContactParameters()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTACT_PARAMETERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContactParameters() <em>Contact Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContactParameters()
	 * @generated
	 * @ordered
	 */
	protected String contactParameters = CONTACT_PARAMETERS_EDEFAULT;

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
	protected ContactFlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompartmentalmodelPackage.Literals.CONTACT_FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Compartment getContactCompartment() {
		if (contactCompartment != null && contactCompartment.eIsProxy()) {
			InternalEObject oldContactCompartment = (InternalEObject)contactCompartment;
			contactCompartment = (Compartment)eResolveProxy(oldContactCompartment);
			if (contactCompartment != oldContactCompartment) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_COMPARTMENT, oldContactCompartment, contactCompartment));
			}
		}
		return contactCompartment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Compartment basicGetContactCompartment() {
		return contactCompartment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContactCompartment(Compartment newContactCompartment) {
		Compartment oldContactCompartment = contactCompartment;
		contactCompartment = newContactCompartment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_COMPARTMENT, oldContactCompartment, contactCompartment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getContactRate() {
		return contactRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContactRate(double newContactRate) {
		double oldContactRate = contactRate;
		contactRate = newContactRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE, oldContactRate, contactRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parameter getContactRateParameter() {
		if (contactRateParameter != null && contactRateParameter.eIsProxy()) {
			InternalEObject oldContactRateParameter = (InternalEObject)contactRateParameter;
			contactRateParameter = (Parameter)eResolveProxy(oldContactRateParameter);
			if (contactRateParameter != oldContactRateParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE_PARAMETER, oldContactRateParameter, contactRateParameter));
			}
		}
		return contactRateParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter basicGetContactRateParameter() {
		return contactRateParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContactRateParameter(Parameter newContactRateParameter) {
		Parameter oldContactRateParameter = contactRateParameter;
		contactRateParameter = newContactRateParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE_PARAMETER, oldContactRateParameter, contactRateParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContactParameters() {
		return contactParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContactParameters(String newContactParameters) {
		String oldContactParameters = contactParameters;
		contactParameters = newContactParameters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_PARAMETERS, oldContactParameters, contactParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<StratumSpecificRate> getStratumSpecificRates() {
		if (stratumSpecificRates == null) {
			stratumSpecificRates = new EObjectContainmentEList<StratumSpecificRate>(StratumSpecificRate.class, this, CompartmentalmodelPackage.CONTACT_FLOW__STRATUM_SPECIFIC_RATES);
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
			case CompartmentalmodelPackage.CONTACT_FLOW__STRATUM_SPECIFIC_RATES:
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
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_COMPARTMENT:
				if (resolve) return getContactCompartment();
				return basicGetContactCompartment();
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE:
				return getContactRate();
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE_PARAMETER:
				if (resolve) return getContactRateParameter();
				return basicGetContactRateParameter();
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_PARAMETERS:
				return getContactParameters();
			case CompartmentalmodelPackage.CONTACT_FLOW__STRATUM_SPECIFIC_RATES:
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
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_COMPARTMENT:
				setContactCompartment((Compartment)newValue);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE:
				setContactRate((Double)newValue);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE_PARAMETER:
				setContactRateParameter((Parameter)newValue);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_PARAMETERS:
				setContactParameters((String)newValue);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__STRATUM_SPECIFIC_RATES:
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
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_COMPARTMENT:
				setContactCompartment((Compartment)null);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE:
				setContactRate(CONTACT_RATE_EDEFAULT);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE_PARAMETER:
				setContactRateParameter((Parameter)null);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_PARAMETERS:
				setContactParameters(CONTACT_PARAMETERS_EDEFAULT);
				return;
			case CompartmentalmodelPackage.CONTACT_FLOW__STRATUM_SPECIFIC_RATES:
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
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_COMPARTMENT:
				return contactCompartment != null;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE:
				return contactRate != CONTACT_RATE_EDEFAULT;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_RATE_PARAMETER:
				return contactRateParameter != null;
			case CompartmentalmodelPackage.CONTACT_FLOW__CONTACT_PARAMETERS:
				return CONTACT_PARAMETERS_EDEFAULT == null ? contactParameters != null : !CONTACT_PARAMETERS_EDEFAULT.equals(contactParameters);
			case CompartmentalmodelPackage.CONTACT_FLOW__STRATUM_SPECIFIC_RATES:
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
		result.append(" (contactRate: ");
		result.append(contactRate);
		result.append(", contactParameters: ");
		result.append(contactParameters);
		result.append(')');
		return result.toString();
	}

} //ContactFlowImpl
