/**
 */
package compartmentalmodel.provider;


import compartmentalmodel.CompartmentalmodelPackage;
import compartmentalmodel.SupplyFunction;
import compartmentalmodel.SupplyFunctionType;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

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
 * This is the item provider adapter for a {@link compartmentalmodel.SupplyFunction} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SupplyFunctionItemProvider 
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
	public SupplyFunctionItemProvider(AdapterFactory adapterFactory) {
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

			addTypePropertyDescriptor(object);
			addMaxDensityPropertyDescriptor(object);
			addCriticalDensityPropertyDescriptor(object);
			addMaxThroughputPropertyDescriptor(object);
			addMaxDemandPropertyDescriptor(object);
			addIsSourceNodePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SupplyFunction_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SupplyFunction_type_feature", "_UI_SupplyFunction_type"),
				 CompartmentalmodelPackage.Literals.SUPPLY_FUNCTION__TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max Density feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxDensityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SupplyFunction_maxDensity_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SupplyFunction_maxDensity_feature", "_UI_SupplyFunction_type"),
				 CompartmentalmodelPackage.Literals.SUPPLY_FUNCTION__MAX_DENSITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Critical Density feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCriticalDensityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SupplyFunction_criticalDensity_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SupplyFunction_criticalDensity_feature", "_UI_SupplyFunction_type"),
				 CompartmentalmodelPackage.Literals.SUPPLY_FUNCTION__CRITICAL_DENSITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max Throughput feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxThroughputPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SupplyFunction_maxThroughput_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SupplyFunction_maxThroughput_feature", "_UI_SupplyFunction_type"),
				 CompartmentalmodelPackage.Literals.SUPPLY_FUNCTION__MAX_THROUGHPUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max Demand feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxDemandPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SupplyFunction_maxDemand_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SupplyFunction_maxDemand_feature", "_UI_SupplyFunction_type"),
				 CompartmentalmodelPackage.Literals.SUPPLY_FUNCTION__MAX_DEMAND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Source Node feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsSourceNodePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SupplyFunction_isSourceNode_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SupplyFunction_isSourceNode_feature", "_UI_SupplyFunction_type"),
				 CompartmentalmodelPackage.Literals.SUPPLY_FUNCTION__IS_SOURCE_NODE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns SupplyFunction.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SupplyFunction"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		SupplyFunctionType labelValue = ((SupplyFunction)object).getType();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_SupplyFunction_type") :
			getString("_UI_SupplyFunction_type") + " " + label;
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

		switch (notification.getFeatureID(SupplyFunction.class)) {
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__TYPE:
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DENSITY:
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__CRITICAL_DENSITY:
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_THROUGHPUT:
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__MAX_DEMAND:
			case CompartmentalmodelPackage.SUPPLY_FUNCTION__IS_SOURCE_NODE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
