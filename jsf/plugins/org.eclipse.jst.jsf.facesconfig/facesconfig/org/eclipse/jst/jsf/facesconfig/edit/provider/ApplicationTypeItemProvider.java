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
import org.eclipse.jst.jsf.facesconfig.FacesConfigPlugin;

import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType} object.
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
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * @param adapterFactory 
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
            addELResolverPropertyDescriptor(object);
            addResourceBundlePropertyDescriptor(object);
            addApplicationExtensionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
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
                 getString("_UI_ApplicationType_id_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationType_id_feature", "_UI_ApplicationType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.APPLICATION_TYPE__ID,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the EL Resolver feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addELResolverPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ApplicationType_eLResolver_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationType_eLResolver_feature", "_UI_ApplicationType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.APPLICATION_TYPE__EL_RESOLVER,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Resource Bundle feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addResourceBundlePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ApplicationType_resourceBundle_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationType_resourceBundle_feature", "_UI_ApplicationType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.APPLICATION_TYPE__RESOURCE_BUNDLE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Application Extension feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addApplicationExtensionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ApplicationType_applicationExtension_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationType_applicationExtension_feature", "_UI_ApplicationType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.APPLICATION_TYPE__APPLICATION_EXTENSION,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This specifies how to implement {@link #getChildren(Object)} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand(Object, org.eclipse.emf.edit.domain.EditingDomain, Class, org.eclipse.emf.edit.command.CommandParameter)}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Collection getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__ACTION_LISTENER);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__MESSAGE_BUNDLE);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__NAVIGATION_HANDLER);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__VIEW_HANDLER);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__STATE_MANAGER);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__PROPERTY_RESOLVER);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__VARIABLE_RESOLVER);
            childrenFeatures.add(FacesConfigPackage.Literals.APPLICATION_TYPE__LOCALE_CONFIG);
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
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ApplicationType")); //$NON-NLS-1$
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
            getString("_UI_ApplicationType_type") : //$NON-NLS-1$
            getString("_UI_ApplicationType_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren(Notification)} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged(Notification)}.
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
                (FacesConfigPackage.Literals.APPLICATION_TYPE__ACTION_LISTENER,
                 FacesConfigFactory.eINSTANCE.createActionListenerType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID,
                 FacesConfigFactory.eINSTANCE.createDefaultRenderKitIdType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__MESSAGE_BUNDLE,
                 FacesConfigFactory.eINSTANCE.createMessageBundleType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__NAVIGATION_HANDLER,
                 FacesConfigFactory.eINSTANCE.createNavigationHandlerType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__VIEW_HANDLER,
                 FacesConfigFactory.eINSTANCE.createViewHandlerType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__STATE_MANAGER,
                 FacesConfigFactory.eINSTANCE.createStateManagerType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__PROPERTY_RESOLVER,
                 FacesConfigFactory.eINSTANCE.createPropertyResolverType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__VARIABLE_RESOLVER,
                 FacesConfigFactory.eINSTANCE.createVariableResolverType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.APPLICATION_TYPE__LOCALE_CONFIG,
                 FacesConfigFactory.eINSTANCE.createLocaleConfigType()));
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
