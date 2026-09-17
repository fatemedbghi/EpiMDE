/**
 */
package compartmentalmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SEIR Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Root container for epidemiological compartmental models. Supports both numeric and parametric (symbolic) modeling approaches. Contains compartments, flows, parameters, stratification groups, and demographic sources/sinks. Can represent SEIR, SIR, SEIRS, vector-borne, and other compartmental model variants.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getCompartments <em>Compartments</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getExternalSources <em>External Sources</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getExternalSinks <em>External Sinks</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getGroups <em>Groups</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getProducts <em>Products</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getParameters <em>Parameters</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getTotalPopulation <em>Total Population</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getGlobalBirthRate <em>Global Birth Rate</em>}</li>
 *   <li>{@link compartmentalmodel.CompartmentalModel#getGlobalDeathRate <em>Global Death Rate</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel()
 * @model
 * @generated
 */
public interface CompartmentalModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Compartments</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.Compartment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Population compartments representing disease states (e.g., Susceptible, Exposed, Infectious, Recovered) or vector states (e.g., Susceptible Mosquitoes, Infected Mosquitoes).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Compartments</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_Compartments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Compartment> getCompartments();

	/**
	 * Returns the value of the '<em><b>External Sources</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.ExternalSource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * External inflows into compartments. Examples: births/recruitment (disease), onramps (traffic), material input (manufacturing), packet generators (networks).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Sources</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_ExternalSources()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalSource> getExternalSources();

	/**
	 * Returns the value of the '<em><b>External Sinks</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.ExternalSink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * External outflows from compartments. Examples: deaths (disease), offramps (traffic), waste output (manufacturing), packet drops (networks).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Sinks</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_ExternalSinks()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalSink> getExternalSinks();

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.Group}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Population stratification groups (age, geographic location, risk level, vaccination status, etc.) used to segment the population into subgroups with potentially different dynamics.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Groups</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_Groups()
	 * @model containment="true"
	 * @generated
	 */
	EList<Group> getGroups();

	/**
	 * Returns the value of the '<em><b>Products</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.Product}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cartesian products of groups for multi-dimensional stratification (e.g., crossing age groups with vaccination status to create age-vaccinated strata).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Products</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_Products()
	 * @model containment="true"
	 * @generated
	 */
	EList<Product> getProducts();

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link compartmentalmodel.Parameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Named parameters (CONSTANT, VARIABLE, EXPRESSION) that can be referenced by flows, birth sources, and death sinks. Enables symbolic/parametric modeling where model structure is separated from parameter values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Total Population</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total population size used for normalization in contact-based transmission (frequency-dependent vs density-dependent). Sum of all compartment populations should typically equal this value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Total Population</em>' attribute.
	 * @see #setTotalPopulation(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_TotalPopulation()
	 * @model
	 * @generated
	 */
	double getTotalPopulation();

	/**
	 * Sets the value of the '{@link compartmentalmodel.CompartmentalModel#getTotalPopulation <em>Total Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Population</em>' attribute.
	 * @see #getTotalPopulation()
	 * @generated
	 */
	void setTotalPopulation(double value);

	/**
	 * Returns the value of the '<em><b>Global Birth Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional global birth rate applied uniformly across the population. Can be overridden by compartment-specific birth sources.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Global Birth Rate</em>' attribute.
	 * @see #setGlobalBirthRate(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_GlobalBirthRate()
	 * @model
	 * @generated
	 */
	double getGlobalBirthRate();

	/**
	 * Sets the value of the '{@link compartmentalmodel.CompartmentalModel#getGlobalBirthRate <em>Global Birth Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Birth Rate</em>' attribute.
	 * @see #getGlobalBirthRate()
	 * @generated
	 */
	void setGlobalBirthRate(double value);

	/**
	 * Returns the value of the '<em><b>Global Death Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional global death rate applied uniformly across the population. Can be overridden by compartment-specific death sinks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Global Death Rate</em>' attribute.
	 * @see #setGlobalDeathRate(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getCompartmentalModel_GlobalDeathRate()
	 * @model
	 * @generated
	 */
	double getGlobalDeathRate();

	/**
	 * Sets the value of the '{@link compartmentalmodel.CompartmentalModel#getGlobalDeathRate <em>Global Death Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Death Rate</em>' attribute.
	 * @see #getGlobalDeathRate()
	 * @generated
	 */
	void setGlobalDeathRate(double value);

} // CompartmentalModel
