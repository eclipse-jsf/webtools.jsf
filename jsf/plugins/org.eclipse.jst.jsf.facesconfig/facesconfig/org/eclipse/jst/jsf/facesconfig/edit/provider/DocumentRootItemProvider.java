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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.jst.jsf.facesconfig.FacesConfigPlugin;
import org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentRootItemProvider
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
	public DocumentRootItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ABSOLUTE_ORDERING);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ACTION_LISTENER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__APPLICATION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__APPLICATION_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR_EXTENSION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_EXTENSION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_FAMILY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_TYPE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER_FOR_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_LOCALE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_VALIDATORS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_VALUE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__DESCRIPTION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__DISPLAY_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__EXTERNAL_CONTEXT_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACES_CONFIG);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACET);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACET_EXTENSION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACET_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FROM_ACTION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FROM_OUTCOME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__FROM_VIEW_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ICON);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__IF);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__KEY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__KEY_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__LARGE_ICON);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__LIFECYCLE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__LIFECYCLE_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__LIST_ENTRIES);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__LOCALE_CONFIG);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_PROPERTY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MAP_ENTRIES);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MAP_ENTRY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MESSAGE_BUNDLE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAVIGATION_CASE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAVIGATION_HANDLER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAVIGATION_RULE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__NULL_VALUE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ORDERING);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__ORDERING_ORDERING);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__OTHERS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__PARTIAL_VIEW_CONTEXT_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__PHASE_LISTENER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_EXTENSION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_RESOLVER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__REDIRECT);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__REDIRECT_VIEW_PARAM);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__REFERENCED_BEAN);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__REFERENCED_BEAN_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER_EXTENSION);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER_TYPE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__RESOURCE_HANDLER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__SMALL_ICON);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__SOURCE_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__STATE_MANAGER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__SUGGESTED_VALUE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__SUPPORTED_LOCALE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__SYSTEM_EVENT_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__TAG_HANDLER_DELEGATE_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__TO_VIEW_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALIDATOR);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALIDATOR_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALIDATOR_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALUE);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALUE_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VARIABLE_RESOLVER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VIEW_DECLARATION_LANGUAGE_FACTORY);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VIEW_HANDLER);
			childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__VISIT_CONTEXT_FACTORY);
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
	 * This returns DocumentRoot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DocumentRoot")); //$NON-NLS-1$
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getText(Object object) {
		return getString("_UI_DocumentRoot_type"); //$NON-NLS-1$
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

		switch (notification.getFeatureID(DocumentRoot.class)) {
			case FacesConfigPackage.DOCUMENT_ROOT__ABSOLUTE_ORDERING:
			case FacesConfigPackage.DOCUMENT_ROOT__ACTION_LISTENER:
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION:
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE:
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION:
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_NAME:
			case FacesConfigPackage.DOCUMENT_ROOT__BEHAVIOR:
			case FacesConfigPackage.DOCUMENT_ROOT__BEHAVIOR_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__BEHAVIOR_ID:
			case FacesConfigPackage.DOCUMENT_ROOT__BEHAVIOR_EXTENSION:
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT:
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_EXTENSION:
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_FAMILY:
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_TYPE:
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER:
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_FOR_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_ID:
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_LOCALE:
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID:
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_VALIDATORS:
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_VALUE:
			case FacesConfigPackage.DOCUMENT_ROOT__DESCRIPTION:
			case FacesConfigPackage.DOCUMENT_ROOT__DISPLAY_NAME:
			case FacesConfigPackage.DOCUMENT_ROOT__EXCEPTION_HANDLER_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__EXTERNAL_CONTEXT_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONFIG:
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__FACET:
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_EXTENSION:
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_NAME:
			case FacesConfigPackage.DOCUMENT_ROOT__FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_ACTION:
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_OUTCOME:
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_VIEW_ID:
			case FacesConfigPackage.DOCUMENT_ROOT__ICON:
			case FacesConfigPackage.DOCUMENT_ROOT__IF:
			case FacesConfigPackage.DOCUMENT_ROOT__KEY:
			case FacesConfigPackage.DOCUMENT_ROOT__KEY_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__LARGE_ICON:
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE:
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__LIST_ENTRIES:
			case FacesConfigPackage.DOCUMENT_ROOT__LOCALE_CONFIG:
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN:
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_NAME:
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE:
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_PROPERTY:
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRIES:
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRY:
			case FacesConfigPackage.DOCUMENT_ROOT__MESSAGE_BUNDLE:
			case FacesConfigPackage.DOCUMENT_ROOT__NAME:
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_CASE:
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_HANDLER:
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_RULE:
			case FacesConfigPackage.DOCUMENT_ROOT__NULL_VALUE:
			case FacesConfigPackage.DOCUMENT_ROOT__ORDERING:
			case FacesConfigPackage.DOCUMENT_ROOT__ORDERING_ORDERING:
			case FacesConfigPackage.DOCUMENT_ROOT__OTHERS:
			case FacesConfigPackage.DOCUMENT_ROOT__PARTIAL_VIEW_CONTEXT_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__PHASE_LISTENER:
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY:
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_EXTENSION:
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_NAME:
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_RESOLVER:
			case FacesConfigPackage.DOCUMENT_ROOT__REDIRECT:
			case FacesConfigPackage.DOCUMENT_ROOT__REDIRECT_VIEW_PARAM:
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN:
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_NAME:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_EXTENSION:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_TYPE:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_ID:
			case FacesConfigPackage.DOCUMENT_ROOT__RESOURCE_HANDLER:
			case FacesConfigPackage.DOCUMENT_ROOT__SMALL_ICON:
			case FacesConfigPackage.DOCUMENT_ROOT__SOURCE_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__STATE_MANAGER:
			case FacesConfigPackage.DOCUMENT_ROOT__SUGGESTED_VALUE:
			case FacesConfigPackage.DOCUMENT_ROOT__SUPPORTED_LOCALE:
			case FacesConfigPackage.DOCUMENT_ROOT__SYSTEM_EVENT_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER:
			case FacesConfigPackage.DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__TAG_HANDLER_DELEGATE_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__TO_VIEW_ID:
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR:
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_ID:
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE:
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE_CLASS:
			case FacesConfigPackage.DOCUMENT_ROOT__VARIABLE_RESOLVER:
			case FacesConfigPackage.DOCUMENT_ROOT__VIEW_DECLARATION_LANGUAGE_FACTORY:
			case FacesConfigPackage.DOCUMENT_ROOT__VIEW_HANDLER:
			case FacesConfigPackage.DOCUMENT_ROOT__VISIT_CONTEXT_FACTORY:
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
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ABSOLUTE_ORDERING,
				 FacesConfigFactory.eINSTANCE.createAbsoluteOrderingType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ACTION_LISTENER,
				 FacesConfigFactory.eINSTANCE.createActionListenerType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__APPLICATION,
				 FacesConfigFactory.eINSTANCE.createApplicationType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__APPLICATION_FACTORY,
				 FacesConfigFactory.eINSTANCE.createApplicationFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE,
				 FacesConfigFactory.eINSTANCE.createAttributeType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_CLASS,
				 FacesConfigFactory.eINSTANCE.createAttributeClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createAttributeExtensionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_NAME,
				 FacesConfigFactory.eINSTANCE.createAttributeNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR,
				 FacesConfigFactory.eINSTANCE.createBehaviorType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR_CLASS,
				 FacesConfigFactory.eINSTANCE.createBehaviorClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR_ID,
				 FacesConfigFactory.eINSTANCE.createBehaviorIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__BEHAVIOR_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createBehaviorExtensionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT,
				 FacesConfigFactory.eINSTANCE.createComponentType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS,
				 FacesConfigFactory.eINSTANCE.createComponentClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createComponentExtensionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_FAMILY,
				 FacesConfigFactory.eINSTANCE.createComponentFamilyType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_TYPE,
				 FacesConfigFactory.eINSTANCE.createComponentTypeType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER,
				 FacesConfigFactory.eINSTANCE.createConverterType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER_CLASS,
				 FacesConfigFactory.eINSTANCE.createConverterClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER_FOR_CLASS,
				 FacesConfigFactory.eINSTANCE.createConverterForClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER_ID,
				 FacesConfigFactory.eINSTANCE.createConverterIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_LOCALE,
				 FacesConfigFactory.eINSTANCE.createDefaultLocaleType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID,
				 FacesConfigFactory.eINSTANCE.createDefaultRenderKitIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_VALIDATORS,
				 FacesConfigFactory.eINSTANCE.createDefaultValidatorsType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_VALUE,
				 FacesConfigFactory.eINSTANCE.createDefaultValueType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__DESCRIPTION,
				 FacesConfigFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__DISPLAY_NAME,
				 FacesConfigFactory.eINSTANCE.createDisplayNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER_FACTORY,
				 FacesConfigFactory.eINSTANCE.createExceptionHandlerFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__EXTERNAL_CONTEXT_FACTORY,
				 FacesConfigFactory.eINSTANCE.createExternalContextFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACES_CONFIG,
				 FacesConfigFactory.eINSTANCE.createFacesConfigType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY,
				 FacesConfigFactory.eINSTANCE.createFacesContextFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACET,
				 FacesConfigFactory.eINSTANCE.createFacetType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACET_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createFacetExtensionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACET_NAME,
				 FacesConfigFactory.eINSTANCE.createFacetNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FACTORY,
				 FacesConfigFactory.eINSTANCE.createFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FROM_ACTION,
				 FacesConfigFactory.eINSTANCE.createFromActionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FROM_OUTCOME,
				 FacesConfigFactory.eINSTANCE.createFromOutcomeType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__FROM_VIEW_ID,
				 FacesConfigFactory.eINSTANCE.createFromViewIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ICON,
				 FacesConfigFactory.eINSTANCE.createIconType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__IF,
				 FacesConfigFactory.eINSTANCE.createIfType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__KEY,
				 FacesConfigFactory.eINSTANCE.createKeyType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__KEY_CLASS,
				 FacesConfigFactory.eINSTANCE.createKeyClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__LARGE_ICON,
				 FacesConfigFactory.eINSTANCE.createLargeIconType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__LIFECYCLE,
				 FacesConfigFactory.eINSTANCE.createLifecycleType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__LIFECYCLE_FACTORY,
				 FacesConfigFactory.eINSTANCE.createLifecycleFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__LIST_ENTRIES,
				 FacesConfigFactory.eINSTANCE.createListEntriesType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__LOCALE_CONFIG,
				 FacesConfigFactory.eINSTANCE.createLocaleConfigType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN,
				 FacesConfigFactory.eINSTANCE.createManagedBeanType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN_CLASS,
				 FacesConfigFactory.eINSTANCE.createManagedBeanClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN_NAME,
				 FacesConfigFactory.eINSTANCE.createManagedBeanNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE,
				 FacesConfigFactory.eINSTANCE.createManagedBeanScopeType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MANAGED_PROPERTY,
				 FacesConfigFactory.eINSTANCE.createManagedPropertyType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MAP_ENTRIES,
				 FacesConfigFactory.eINSTANCE.createMapEntriesType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MAP_ENTRY,
				 FacesConfigFactory.eINSTANCE.createMapEntryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__MESSAGE_BUNDLE,
				 FacesConfigFactory.eINSTANCE.createMessageBundleType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAME,
				 FacesConfigFactory.eINSTANCE.createNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAVIGATION_CASE,
				 FacesConfigFactory.eINSTANCE.createNavigationCaseType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAVIGATION_HANDLER,
				 FacesConfigFactory.eINSTANCE.createNavigationHandlerType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__NAVIGATION_RULE,
				 FacesConfigFactory.eINSTANCE.createNavigationRuleType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__NULL_VALUE,
				 FacesConfigFactory.eINSTANCE.createNullValueType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ORDERING,
				 FacesConfigFactory.eINSTANCE.createOrderingType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__ORDERING_ORDERING,
				 FacesConfigFactory.eINSTANCE.createOrderingOrderingType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__OTHERS,
				 FacesConfigFactory.eINSTANCE.createOrderingOthersType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__PARTIAL_VIEW_CONTEXT_FACTORY,
				 FacesConfigFactory.eINSTANCE.createPartialViewContextFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__PHASE_LISTENER,
				 FacesConfigFactory.eINSTANCE.createPhaseListenerType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY,
				 FacesConfigFactory.eINSTANCE.createPropertyType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_CLASS,
				 FacesConfigFactory.eINSTANCE.createPropertyClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createPropertyExtensionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_NAME,
				 FacesConfigFactory.eINSTANCE.createPropertyNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_RESOLVER,
				 FacesConfigFactory.eINSTANCE.createPropertyResolverType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__REDIRECT,
				 FacesConfigFactory.eINSTANCE.createRedirectType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__REDIRECT_VIEW_PARAM,
				 FacesConfigFactory.eINSTANCE.createRedirectViewParamType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__REFERENCED_BEAN,
				 FacesConfigFactory.eINSTANCE.createReferencedBeanType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS,
				 FacesConfigFactory.eINSTANCE.createReferencedBeanClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__REFERENCED_BEAN_NAME,
				 FacesConfigFactory.eINSTANCE.createReferencedBeanNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER,
				 FacesConfigFactory.eINSTANCE.createRendererType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER_CLASS,
				 FacesConfigFactory.eINSTANCE.createRendererClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createRendererExtensionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER_TYPE,
				 FacesConfigFactory.eINSTANCE.createRendererTypeType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT,
				 FacesConfigFactory.eINSTANCE.createRenderKitType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT_CLASS,
				 FacesConfigFactory.eINSTANCE.createRenderKitClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT_FACTORY,
				 FacesConfigFactory.eINSTANCE.createRenderKitFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT_ID,
				 FacesConfigFactory.eINSTANCE.createRenderKitIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__RESOURCE_HANDLER,
				 FacesConfigFactory.eINSTANCE.createResourceHandlerType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__SMALL_ICON,
				 FacesConfigFactory.eINSTANCE.createSmallIconType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__SOURCE_CLASS,
				 FacesConfigFactory.eINSTANCE.createSourceClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__STATE_MANAGER,
				 FacesConfigFactory.eINSTANCE.createStateManagerType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__SUGGESTED_VALUE,
				 FacesConfigFactory.eINSTANCE.createSuggestedValueType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__SUPPORTED_LOCALE,
				 FacesConfigFactory.eINSTANCE.createSupportedLocaleType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__SYSTEM_EVENT_CLASS,
				 FacesConfigFactory.eINSTANCE.createSystemEventClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER,
				 FacesConfigFactory.eINSTANCE.createSystemEventListenerType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER_CLASS,
				 FacesConfigFactory.eINSTANCE.createSystemEventListenerClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__TAG_HANDLER_DELEGATE_FACTORY,
				 FacesConfigFactory.eINSTANCE.createTagHandlerDelegateFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__TO_VIEW_ID,
				 FacesConfigFactory.eINSTANCE.createToViewIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALIDATOR,
				 FacesConfigFactory.eINSTANCE.createValidatorType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALIDATOR_CLASS,
				 FacesConfigFactory.eINSTANCE.createValidatorClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALIDATOR_ID,
				 FacesConfigFactory.eINSTANCE.createValidatorIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALUE,
				 FacesConfigFactory.eINSTANCE.createValueType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VALUE_CLASS,
				 FacesConfigFactory.eINSTANCE.createValueClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VARIABLE_RESOLVER,
				 FacesConfigFactory.eINSTANCE.createVariableResolverType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VIEW_DECLARATION_LANGUAGE_FACTORY,
				 FacesConfigFactory.eINSTANCE.createViewDeclarationLanguageFactoryType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VIEW_HANDLER,
				 FacesConfigFactory.eINSTANCE.createViewHandlerType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.DOCUMENT_ROOT__VISIT_CONTEXT_FACTORY,
				 FacesConfigFactory.eINSTANCE.createVisitContextFactoryType()));
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
