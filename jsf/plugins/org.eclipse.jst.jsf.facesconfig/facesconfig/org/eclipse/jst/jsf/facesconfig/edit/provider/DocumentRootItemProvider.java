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
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

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
            childrenFeatures.add(FacesConfigPackage.Literals.DOCUMENT_ROOT__MIXED);
        }
        return childrenFeatures;
    }

    /**
     * This returns DocumentRoot.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DocumentRoot"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getText(Object object) {
        return getString("_UI_DocumentRoot_type");
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

        switch (notification.getFeatureID(DocumentRoot.class)) {
            case FacesConfigPackage.DOCUMENT_ROOT__MIXED:
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
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION,
                 FacesConfigFactory.eINSTANCE.createAttributeExtensionType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_NAME,
                 FacesConfigFactory.eINSTANCE.createAttributeNameType()));

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
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__COMPONENT_TYPE,
                 FacesConfigFactory.eINSTANCE.createComponentTypeType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__CONVERTER,
                 FacesConfigFactory.eINSTANCE.createConverterType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__DEFAULT_LOCALE,
                 FacesConfigFactory.eINSTANCE.createDefaultLocaleType()));

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
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__FACES_CONFIG,
                 FacesConfigFactory.eINSTANCE.createFacesConfigType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY,
                 FacesConfigFactory.eINSTANCE.createFacesContextFactoryType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__FACTORY,
                 FacesConfigFactory.eINSTANCE.createFactoryType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__ICON,
                 FacesConfigFactory.eINSTANCE.createIconType()));

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
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__PHASE_LISTENER,
                 FacesConfigFactory.eINSTANCE.createPhaseListenerType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY,
                 FacesConfigFactory.eINSTANCE.createPropertyType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__PROPERTY_EXTENSION,
                 FacesConfigFactory.eINSTANCE.createPropertyExtensionType()));

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
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__REFERENCED_BEAN,
                 FacesConfigFactory.eINSTANCE.createReferencedBeanType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER,
                 FacesConfigFactory.eINSTANCE.createRendererType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDERER_EXTENSION,
                 FacesConfigFactory.eINSTANCE.createRendererExtensionType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT,
                 FacesConfigFactory.eINSTANCE.createRenderKitType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__RENDER_KIT_FACTORY,
                 FacesConfigFactory.eINSTANCE.createRenderKitFactoryType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__STATE_MANAGER,
                 FacesConfigFactory.eINSTANCE.createStateManagerType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__SUPPORTED_LOCALE,
                 FacesConfigFactory.eINSTANCE.createSupportedLocaleType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__VALIDATOR,
                 FacesConfigFactory.eINSTANCE.createValidatorType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__VALUE,
                 FacesConfigFactory.eINSTANCE.createValueType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__VARIABLE_RESOLVER,
                 FacesConfigFactory.eINSTANCE.createVariableResolverType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.DOCUMENT_ROOT__VIEW_HANDLER,
                 FacesConfigFactory.eINSTANCE.createViewHandlerType()));
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
