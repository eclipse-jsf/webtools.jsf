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

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
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
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

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
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__APPLICATION);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__FACTORY);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__COMPONENT);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__CONVERTER);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__MANAGED_BEAN);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__NAVIGATION_RULE);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__REFERENCED_BEAN);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__RENDER_KIT);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__LIFECYCLE);
            childrenFeatures.add(FacesConfigPackage.Literals.FACES_CONFIG_TYPE__VALIDATOR);
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
     * This handles model notifications by calling {@link #updateChildren(Notification)} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged(Notification)}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(FacesConfigType.class)) {
            case FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS:
            case FacesConfigPackage.FACES_CONFIG_TYPE__ID:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION:
            case FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY:
            case FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT:
            case FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER:
            case FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN:
            case FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE:
            case FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN:
            case FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT:
            case FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE:
            case FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR:
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
                (FacesConfigPackage.Literals.FACES_CONFIG_TYPE__APPLICATION,
                 FacesConfigFactory.eINSTANCE.createApplicationType()));

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
