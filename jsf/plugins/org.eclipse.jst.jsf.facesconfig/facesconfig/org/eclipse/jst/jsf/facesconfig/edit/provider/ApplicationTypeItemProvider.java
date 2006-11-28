/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.wtp.jsf.facesconfig.emf.ApplicationType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ApplicationTypeItemProvider
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
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationTypeItemProvider(AdapterFactory adapterFactory) {
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

			addIdPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ApplicationType_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationType_id_feature", "_UI_ApplicationType_type"),
				 Literals.APPLICATION_TYPE__ID,
				 true,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(Literals.APPLICATION_TYPE__ACTION_LISTENER);
			childrenFeatures.add(Literals.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID);
			childrenFeatures.add(Literals.APPLICATION_TYPE__MESSAGE_BUNDLE);
			childrenFeatures.add(Literals.APPLICATION_TYPE__NAVIGATION_HANDLER);
			childrenFeatures.add(Literals.APPLICATION_TYPE__VIEW_HANDLER);
			childrenFeatures.add(Literals.APPLICATION_TYPE__STATE_MANAGER);
			childrenFeatures.add(Literals.APPLICATION_TYPE__PROPERTY_RESOLVER);
			childrenFeatures.add(Literals.APPLICATION_TYPE__VARIABLE_RESOLVER);
			childrenFeatures.add(Literals.APPLICATION_TYPE__LOCALE_CONFIG);
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
	 * This returns ApplicationType.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FacesConfig_Application"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ApplicationType)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_ApplicationType_type") :
			getString("_UI_ApplicationType_type") + " " + label;
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

		switch (notification.getFeatureID(ApplicationType.class)) {
			case FacesConfigPackage.APPLICATION_TYPE__ID:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case FacesConfigPackage.APPLICATION_TYPE__ACTION_LISTENER:
			case FacesConfigPackage.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID:
			case FacesConfigPackage.APPLICATION_TYPE__MESSAGE_BUNDLE:
			case FacesConfigPackage.APPLICATION_TYPE__NAVIGATION_HANDLER:
			case FacesConfigPackage.APPLICATION_TYPE__VIEW_HANDLER:
			case FacesConfigPackage.APPLICATION_TYPE__STATE_MANAGER:
			case FacesConfigPackage.APPLICATION_TYPE__PROPERTY_RESOLVER:
			case FacesConfigPackage.APPLICATION_TYPE__VARIABLE_RESOLVER:
			case FacesConfigPackage.APPLICATION_TYPE__LOCALE_CONFIG:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__ACTION_LISTENER,
				 FacesConfigFactory.eINSTANCE.createActionListenerType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID,
				 FacesConfigFactory.eINSTANCE.createDefaultRenderKitIdType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__MESSAGE_BUNDLE,
				 FacesConfigFactory.eINSTANCE.createMessageBundleType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__NAVIGATION_HANDLER,
				 FacesConfigFactory.eINSTANCE.createNavigationHandlerType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__VIEW_HANDLER,
				 FacesConfigFactory.eINSTANCE.createViewHandlerType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__STATE_MANAGER,
				 FacesConfigFactory.eINSTANCE.createStateManagerType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__PROPERTY_RESOLVER,
				 FacesConfigFactory.eINSTANCE.createPropertyResolverType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__VARIABLE_RESOLVER,
				 FacesConfigFactory.eINSTANCE.createVariableResolverType()));

		newChildDescriptors.add
			(createChildParameter
				(Literals.APPLICATION_TYPE__LOCALE_CONFIG,
				 FacesConfigFactory.eINSTANCE.createLocaleConfigType()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return FacesConfigEditPlugin.INSTANCE;
	}

}
