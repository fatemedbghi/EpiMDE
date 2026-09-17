/**
 */
package compartmentalmodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import compartmentalmodel.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see compartmentalmodel.CompartmentalmodelPackage
 * @generated
 */
public class CompartmentalmodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CompartmentalmodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompartmentalmodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = CompartmentalmodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompartmentalmodelSwitch<Adapter> modelSwitch =
		new CompartmentalmodelSwitch<Adapter>() {
			@Override
			public Adapter caseCompartment(Compartment object) {
				return createCompartmentAdapter();
			}
			@Override
			public Adapter caseFlow(Flow object) {
				return createFlowAdapter();
			}
			@Override
			public Adapter caseRateFlow(RateFlow object) {
				return createRateFlowAdapter();
			}
			@Override
			public Adapter caseContactFlow(ContactFlow object) {
				return createContactFlowAdapter();
			}
			@Override
			public Adapter caseExternalSource(ExternalSource object) {
				return createExternalSourceAdapter();
			}
			@Override
			public Adapter caseExternalSink(ExternalSink object) {
				return createExternalSinkAdapter();
			}
			@Override
			public Adapter caseStratumSpecificRate(StratumSpecificRate object) {
				return createStratumSpecificRateAdapter();
			}
			@Override
			public Adapter caseGroup(Group object) {
				return createGroupAdapter();
			}
			@Override
			public Adapter caseProduct(Product object) {
				return createProductAdapter();
			}
			@Override
			public Adapter caseParameter(Parameter object) {
				return createParameterAdapter();
			}
			@Override
			public Adapter caseSupplyFunction(SupplyFunction object) {
				return createSupplyFunctionAdapter();
			}
			@Override
			public Adapter caseCompartmentalModel(CompartmentalModel object) {
				return createCompartmentalModelAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.Compartment <em>Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.Compartment
	 * @generated
	 */
	public Adapter createCompartmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.Flow <em>Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.Flow
	 * @generated
	 */
	public Adapter createFlowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.RateFlow <em>Rate Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.RateFlow
	 * @generated
	 */
	public Adapter createRateFlowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.ContactFlow <em>Contact Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.ContactFlow
	 * @generated
	 */
	public Adapter createContactFlowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.ExternalSource <em>External Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.ExternalSource
	 * @generated
	 */
	public Adapter createExternalSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.ExternalSink <em>External Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.ExternalSink
	 * @generated
	 */
	public Adapter createExternalSinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.StratumSpecificRate <em>Stratum Specific Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.StratumSpecificRate
	 * @generated
	 */
	public Adapter createStratumSpecificRateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.Group
	 * @generated
	 */
	public Adapter createGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.Product
	 * @generated
	 */
	public Adapter createProductAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.SupplyFunction <em>Supply Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.SupplyFunction
	 * @generated
	 */
	public Adapter createSupplyFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link compartmentalmodel.CompartmentalModel <em>Compartmental Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see compartmentalmodel.CompartmentalModel
	 * @generated
	 */
	public Adapter createCompartmentalModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //CompartmentalmodelAdapterFactory
