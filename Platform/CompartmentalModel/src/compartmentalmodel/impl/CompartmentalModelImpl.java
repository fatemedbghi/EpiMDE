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
import compartmentalmodel.ExternalSink;
import compartmentalmodel.ExternalSource;
import compartmentalmodel.Group;
import compartmentalmodel.Parameter;
import compartmentalmodel.Product;
import compartmentalmodel.CompartmentalModel;
import compartmentalmodel.CompartmentalmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SEIR Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getCompartments <em>Compartments</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getExternalSources <em>External Sources</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getExternalSinks <em>External Sinks</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getProducts <em>Products</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getTotalPopulation <em>Total Population</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getGlobalBirthRate <em>Global Birth Rate</em>}</li>
 *   <li>{@link compartmentalmodel.impl.CompartmentalModelImpl#getGlobalDeathRate <em>Global Death Rate</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompartmentalModelImpl extends MinimalEObjectImpl.Container implements CompartmentalModel {
	/**
	 * The cached value of the '{@link #getCompartments() <em>Compartments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompartments()
	 * @generated
	 * @ordered
	 */
	protected EList<Compartment> compartments;

	/**
	 * The cached value of the '{@link #getExternalSources() <em>External Sources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalSources()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalSource> externalSources;

	/**
	 * The cached value of the '{@link #getExternalSinks() <em>External Sinks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalSinks()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalSink> externalSinks;

	/**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<Group> groups;

	/**
	 * The cached value of the '{@link #getProducts() <em>Products</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Product> products;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameters;

	/**
	 * The default value of the '{@link #getTotalPopulation() <em>Total Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalPopulation()
	 * @generated
	 * @ordered
	 */
	protected static final double TOTAL_POPULATION_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getTotalPopulation() <em>Total Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalPopulation()
	 * @generated
	 * @ordered
	 */
	protected double totalPopulation = TOTAL_POPULATION_EDEFAULT;
	/**
	 * The default value of the '{@link #getGlobalBirthRate() <em>Global Birth Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalBirthRate()
	 * @generated
	 * @ordered
	 */
	protected static final double GLOBAL_BIRTH_RATE_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getGlobalBirthRate() <em>Global Birth Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalBirthRate()
	 * @generated
	 * @ordered
	 */
	protected double globalBirthRate = GLOBAL_BIRTH_RATE_EDEFAULT;
	/**
	 * The default value of the '{@link #getGlobalDeathRate() <em>Global Death Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalDeathRate()
	 * @generated
	 * @ordered
	 */
	protected static final double GLOBAL_DEATH_RATE_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getGlobalDeathRate() <em>Global Death Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalDeathRate()
	 * @generated
	 * @ordered
	 */
	protected double globalDeathRate = GLOBAL_DEATH_RATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompartmentalModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Compartment> getCompartments() {
		if (compartments == null) {
			compartments = new EObjectContainmentEList<Compartment>(Compartment.class, this, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__COMPARTMENTS);
		}
		return compartments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExternalSource> getExternalSources() {
		if (externalSources == null) {
			externalSources = new EObjectContainmentEList<ExternalSource>(ExternalSource.class, this, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES);
		}
		return externalSources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExternalSink> getExternalSinks() {
		if (externalSinks == null) {
			externalSinks = new EObjectContainmentEList<ExternalSink>(ExternalSink.class, this, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SINKS);
		}
		return externalSinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Group> getGroups() {
		if (groups == null) {
			groups = new EObjectContainmentEList<Group>(Group.class, this, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GROUPS);
		}
		return groups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Product> getProducts() {
		if (products == null) {
			products = new EObjectContainmentEList<Product>(Product.class, this, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PRODUCTS);
		}
		return products;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTotalPopulation() {
		return totalPopulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTotalPopulation(double newTotalPopulation) {
		double oldTotalPopulation = totalPopulation;
		totalPopulation = newTotalPopulation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__TOTAL_POPULATION, oldTotalPopulation, totalPopulation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getGlobalBirthRate() {
		return globalBirthRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGlobalBirthRate(double newGlobalBirthRate) {
		double oldGlobalBirthRate = globalBirthRate;
		globalBirthRate = newGlobalBirthRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE, oldGlobalBirthRate, globalBirthRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getGlobalDeathRate() {
		return globalDeathRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGlobalDeathRate(double newGlobalDeathRate) {
		double oldGlobalDeathRate = globalDeathRate;
		globalDeathRate = newGlobalDeathRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE, oldGlobalDeathRate, globalDeathRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__COMPARTMENTS:
				return ((InternalEList<?>)getCompartments()).basicRemove(otherEnd, msgs);
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES:
				return ((InternalEList<?>)getExternalSources()).basicRemove(otherEnd, msgs);
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SINKS:
				return ((InternalEList<?>)getExternalSinks()).basicRemove(otherEnd, msgs);
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GROUPS:
				return ((InternalEList<?>)getGroups()).basicRemove(otherEnd, msgs);
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PRODUCTS:
				return ((InternalEList<?>)getProducts()).basicRemove(otherEnd, msgs);
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__COMPARTMENTS:
				return getCompartments();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES:
				return getExternalSources();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SINKS:
				return getExternalSinks();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GROUPS:
				return getGroups();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PRODUCTS:
				return getProducts();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PARAMETERS:
				return getParameters();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__TOTAL_POPULATION:
				return getTotalPopulation();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE:
				return getGlobalBirthRate();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE:
				return getGlobalDeathRate();
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
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__COMPARTMENTS:
				getCompartments().clear();
				getCompartments().addAll((Collection<? extends Compartment>)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES:
				getExternalSources().clear();
				getExternalSources().addAll((Collection<? extends ExternalSource>)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SINKS:
				getExternalSinks().clear();
				getExternalSinks().addAll((Collection<? extends ExternalSink>)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GROUPS:
				getGroups().clear();
				getGroups().addAll((Collection<? extends Group>)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PRODUCTS:
				getProducts().clear();
				getProducts().addAll((Collection<? extends Product>)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__TOTAL_POPULATION:
				setTotalPopulation((Double)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE:
				setGlobalBirthRate((Double)newValue);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE:
				setGlobalDeathRate((Double)newValue);
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
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__COMPARTMENTS:
				getCompartments().clear();
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES:
				getExternalSources().clear();
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SINKS:
				getExternalSinks().clear();
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GROUPS:
				getGroups().clear();
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PRODUCTS:
				getProducts().clear();
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PARAMETERS:
				getParameters().clear();
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__TOTAL_POPULATION:
				setTotalPopulation(TOTAL_POPULATION_EDEFAULT);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE:
				setGlobalBirthRate(GLOBAL_BIRTH_RATE_EDEFAULT);
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE:
				setGlobalDeathRate(GLOBAL_DEATH_RATE_EDEFAULT);
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
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__COMPARTMENTS:
				return compartments != null && !compartments.isEmpty();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES:
				return externalSources != null && !externalSources.isEmpty();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SINKS:
				return externalSinks != null && !externalSinks.isEmpty();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GROUPS:
				return groups != null && !groups.isEmpty();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PRODUCTS:
				return products != null && !products.isEmpty();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__TOTAL_POPULATION:
				return totalPopulation != TOTAL_POPULATION_EDEFAULT;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE:
				return globalBirthRate != GLOBAL_BIRTH_RATE_EDEFAULT;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE:
				return globalDeathRate != GLOBAL_DEATH_RATE_EDEFAULT;
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
		result.append(" (totalPopulation: ");
		result.append(totalPopulation);
		result.append(", globalBirthRate: ");
		result.append(globalBirthRate);
		result.append(", globalDeathRate: ");
		result.append(globalDeathRate);
		result.append(')');
		return result.toString();
	}

} //CompartmentalModelImpl
