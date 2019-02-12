/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.edit.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.jst.jsf.facesconfig.FacesConfigPlugin;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
@SuppressWarnings("nls")
public class FacesConfigTypeItemProvider
	extends ItemProviderAdapter
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
     * @param adapterFactory 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacesConfigTypeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

    /**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addFacesConfigExtensionPropertyDescriptor(object);
			addXmlnsPropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addMetadataCompletePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

    /**
	 * This adds a property descriptor for the Faces Config Extension feature.
	 * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addFacesConfigExtensionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FacesConfigType_facesConfigExtension_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FacesConfigType_facesConfigExtension_feature", "_UI_FacesConfigType_type"),
				 FacesConfigPackage.Literals.FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Xmlns feature.
	 * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addXmlnsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FacesConfigType_xmlns_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FacesConfigType_xmlns_feature", "_UI_FacesConfigType_type"),
				 FacesConfigPackage.Literals.FACES_CONFIG_TYPE__XMLNS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FacesConfigType_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FacesConfigType_id_feature", "_UI_FacesConfigType_type"),
				 FacesConfigPackage.Literals.FACES_CONFIG_TYPE__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Metadata Complete feature.
	 * <!-- begin-user-doc -->
	 * @param object
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMetadataCompletePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FacesConfigType_metadataComplete_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FacesConfigType_metadataComplete_feature", "_UI_FacesConfigType_type"),
				 FacesConfigPackage.Literals.FACES_CONFIG_TYPE__METADATA_COMPLETE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__APPLICATION);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__ORDERING);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__COMPONENT);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__CONVERTER);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__MANAGED_BEAN);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__NAVIGATION_RULE);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__REFERENCED_BEAN);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__RENDER_KIT);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__LIFECYCLE);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__VALIDATOR);
			childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__BEHAVIOR);
		}
		return childrenFeatures;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

    /**
	 * This returns FacesConfigType.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FacesConfigType"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((FacesConfigType)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_FacesConfigType_type") :
			getString("_UI_FacesConfigType_type") + " " + label;
	}

    /**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(FacesConfigType.class)) {
			case FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS:
			case FacesConfigPackage.FACES_CONFIG_TYPE__ID:
			case FacesConfigPackage.FACES_CONFIG_TYPE__METADATA_COMPLETE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION:
			case FacesConfigPackage.FACES_CONFIG_TYPE__ORDERING:
			case FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING:
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY:
			case FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT:
			case FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER:
			case FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN:
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAME:
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE:
			case FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN:
			case FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT:
			case FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE:
			case FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR:
			case FacesConfigPackage.FACES_CONFIG_TYPE__BEHAVIOR:
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
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__APPLICATION,
				 FacesConfigFactory.eINSTANCE.createApplicationType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__ORDERING,
				 FacesConfigFactory.eINSTANCE.createOrderingType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING,
				 FacesConfigFactory.eINSTANCE.createAbsoluteOrderingType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__FACTORY,
				 FacesConfigFactory.eINSTANCE.createFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__COMPONENT,
				 FacesConfigFactory.eINSTANCE.createComponentType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__CONVERTER,
				 FacesConfigFactory.eINSTANCE.createConverterType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__MANAGED_BEAN,
				 FacesConfigFactory.eINSTANCE.createManagedBeanType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__NAME,
				 FacesConfigFactory.eINSTANCE.createNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__NAVIGATION_RULE,
				 FacesConfigFactory.eINSTANCE.createNavigationRuleType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__REFERENCED_BEAN,
				 FacesConfigFactory.eINSTANCE.createReferencedBeanType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__RENDER_KIT,
				 FacesConfigFactory.eINSTANCE.createRenderKitType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__LIFECYCLE,
				 FacesConfigFactory.eINSTANCE.createLifecycleType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__VALIDATOR,
				 FacesConfigFactory.eINSTANCE.createValidatorType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__BEHAVIOR,
				 FacesConfigFactory.eINSTANCE.createBehaviorType()));
	}

    /**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return FacesConfigPlugin.INSTANCE;
	}

}
