/**
 */
package compartmentalmodel.provider;


import compartmentalmodel.CompartmentalModel;
import compartmentalmodel.CompartmentalmodelFactory;
import compartmentalmodel.CompartmentalmodelPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link compartmentalmodel.CompartmentalModel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CompartmentalModelItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompartmentalModelItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addTotalPopulationPropertyDescriptor(object);
			addGlobalBirthRatePropertyDescriptor(object);
			addGlobalDeathRatePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Total Population feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTotalPopulationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CompartmentalModel_totalPopulation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CompartmentalModel_totalPopulation_feature", "_UI_CompartmentalModel_type"),
				 CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__TOTAL_POPULATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Global Birth Rate feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGlobalBirthRatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CompartmentalModel_globalBirthRate_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CompartmentalModel_globalBirthRate_feature", "_UI_CompartmentalModel_type"),
				 CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Global Death Rate feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGlobalDeathRatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CompartmentalModel_globalDeathRate_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CompartmentalModel_globalDeathRate_feature", "_UI_CompartmentalModel_type"),
				 CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__COMPARTMENTS);
			childrenFeatures.add(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES);
			childrenFeatures.add(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__EXTERNAL_SINKS);
			childrenFeatures.add(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__GROUPS);
			childrenFeatures.add(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__PRODUCTS);
			childrenFeatures.add(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__PARAMETERS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns CompartmentalModel.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CompartmentalModel"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		CompartmentalModel compartmentalModel = (CompartmentalModel)object;
		return getString("_UI_CompartmentalModel_type") + " " + compartmentalModel.getTotalPopulation();
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(CompartmentalModel.class)) {
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__TOTAL_POPULATION:
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_BIRTH_RATE:
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GLOBAL_DEATH_RATE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__COMPARTMENTS:
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES:
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__EXTERNAL_SINKS:
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__GROUPS:
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PRODUCTS:
			case CompartmentalmodelPackage.COMPARTMENTAL_MODEL__PARAMETERS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__COMPARTMENTS,
				 CompartmentalmodelFactory.eINSTANCE.createCompartment()));

		newChildDescriptors.add
			(createChildParameter
				(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__EXTERNAL_SOURCES,
				 CompartmentalmodelFactory.eINSTANCE.createExternalSource()));

		newChildDescriptors.add
			(createChildParameter
				(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__EXTERNAL_SINKS,
				 CompartmentalmodelFactory.eINSTANCE.createExternalSink()));

		newChildDescriptors.add
			(createChildParameter
				(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__GROUPS,
				 CompartmentalmodelFactory.eINSTANCE.createGroup()));

		newChildDescriptors.add
			(createChildParameter
				(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__PRODUCTS,
				 CompartmentalmodelFactory.eINSTANCE.createProduct()));

		newChildDescriptors.add
			(createChildParameter
				(CompartmentalmodelPackage.Literals.COMPARTMENTAL_MODEL__PARAMETERS,
				 CompartmentalmodelFactory.eINSTANCE.createParameter()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return CompartmentalEditPlugin.INSTANCE;
	}

}
