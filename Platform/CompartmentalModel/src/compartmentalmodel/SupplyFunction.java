/**
 */
package compartmentalmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Supply Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines supply and demand functions for flow networks based on capacity constraints and flow characteristics. Originally designed for traffic networks (triangular fundamental diagram) but generalizable to other flow systems. When a Compartment has a SupplyFunction, it represents a flow-constrained node rather than a simple state (e.g., road link in traffic, pipeline in fluids, queue in systems). Disease models do not use supply functions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link compartmentalmodel.SupplyFunction#getType <em>Type</em>}</li>
 *   <li>{@link compartmentalmodel.SupplyFunction#getMaxDensity <em>Max Density</em>}</li>
 *   <li>{@link compartmentalmodel.SupplyFunction#getCriticalDensity <em>Critical Density</em>}</li>
 *   <li>{@link compartmentalmodel.SupplyFunction#getMaxThroughput <em>Max Throughput</em>}</li>
 *   <li>{@link compartmentalmodel.SupplyFunction#getMaxDemand <em>Max Demand</em>}</li>
 *   <li>{@link compartmentalmodel.SupplyFunction#isIsSourceNode <em>Is Source Node</em>}</li>
 * </ul>
 *
 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunction()
 * @model
 * @generated
 */
public interface SupplyFunction extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link compartmentalmodel.SupplyFunctionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Type of supply/demand function: TRIANGULAR (piecewise linear with critical point), LINEAR (proportional), or CUSTOM (user-defined). TRIANGULAR follows flow network models like Coogan & Arcak (2015) for traffic.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see compartmentalmodel.SupplyFunctionType
	 * @see #setType(SupplyFunctionType)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunction_Type()
	 * @model
	 * @generated
	 */
	SupplyFunctionType getType();

	/**
	 * Sets the value of the '{@link compartmentalmodel.SupplyFunction#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see compartmentalmodel.SupplyFunctionType
	 * @see #getType()
	 * @generated
	 */
	void setType(SupplyFunctionType value);

	/**
	 * Returns the value of the '<em><b>Max Density</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum density/capacity: the saturation point where no more entities can be stored (e.g., ρ^jam in traffic - vehicles per length when stopped, max queue size in systems, storage capacity in networks). For constrained nodes only; source nodes may have unbounded capacity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Density</em>' attribute.
	 * @see #setMaxDensity(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunction_MaxDensity()
	 * @model
	 * @generated
	 */
	double getMaxDensity();

	/**
	 * Sets the value of the '{@link compartmentalmodel.SupplyFunction#getMaxDensity <em>Max Density</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Density</em>' attribute.
	 * @see #getMaxDensity()
	 * @generated
	 */
	void setMaxDensity(double value);

	/**
	 * Returns the value of the '<em><b>Critical Density</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Critical density/threshold: the density at which maximum throughput occurs (e.g., ρ^crit in traffic). Below this is efficient/free-flow operation, above is congested/constrained regime. Typically criticalDensity < maxDensity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Critical Density</em>' attribute.
	 * @see #setCriticalDensity(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunction_CriticalDensity()
	 * @model
	 * @generated
	 */
	double getCriticalDensity();

	/**
	 * Sets the value of the '{@link compartmentalmodel.SupplyFunction#getCriticalDensity <em>Critical Density</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Critical Density</em>' attribute.
	 * @see #getCriticalDensity()
	 * @generated
	 */
	void setCriticalDensity(double value);

	/**
	 * Returns the value of the '<em><b>Max Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum throughput/capacity: the maximum flow rate that can pass through this node (e.g., Φ^crit in traffic - vehicles per time, max processing rate in systems, bandwidth in networks). Achieved at criticalDensity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Throughput</em>' attribute.
	 * @see #setMaxThroughput(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunction_MaxThroughput()
	 * @model
	 * @generated
	 */
	double getMaxThroughput();

	/**
	 * Sets the value of the '{@link compartmentalmodel.SupplyFunction#getMaxThroughput <em>Max Throughput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Throughput</em>' attribute.
	 * @see #getMaxThroughput()
	 * @generated
	 */
	void setMaxThroughput(double value);

	/**
	 * Returns the value of the '<em><b>Max Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum demand/output rate: upper bound on outflow for source nodes (e.g., Φ^max for onramps in traffic, max generation rate for sources, service rate for queues). For constrained nodes, leave at 0 (not used). Source nodes act as unbounded storage with demand bounded by maxDemand.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Demand</em>' attribute.
	 * @see #setMaxDemand(double)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunction_MaxDemand()
	 * @model
	 * @generated
	 */
	double getMaxDemand();

	/**
	 * Sets the value of the '{@link compartmentalmodel.SupplyFunction#getMaxDemand <em>Max Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Demand</em>' attribute.
	 * @see #getMaxDemand()
	 * @generated
	 */
	void setMaxDemand(double value);

	/**
	 * Returns the value of the '<em><b>Is Source Node</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if this is a source/entry node with external input (e.g., onramp in traffic, generator in systems, entry queue in networks), false for internal constrained nodes. Source nodes typically have unbounded storage capacity and use only demand function; constrained nodes have both supply and demand functions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Source Node</em>' attribute.
	 * @see #setIsSourceNode(boolean)
	 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunction_IsSourceNode()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsSourceNode();

	/**
	 * Sets the value of the '{@link compartmentalmodel.SupplyFunction#isIsSourceNode <em>Is Source Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Source Node</em>' attribute.
	 * @see #isIsSourceNode()
	 * @generated
	 */
	void setIsSourceNode(boolean value);

} // SupplyFunction
