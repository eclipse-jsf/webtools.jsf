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
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.jst.jsf.facesconfig.FacesConfigPlugin;

import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 */
public class AttributeTypeItemProvider
	extends ItemProviderAdapter
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource,
		ITableItemLabelProvider{
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
	public AttributeTypeItemProvider(AdapterFactory adapterFactory) {
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

            addAttributeClassPropertyDescriptor(object);
            addDefaultValuePropertyDescriptor(object);
            addSuggestedValuePropertyDescriptor(object);
            addIdPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Attribute Class feature.
     * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addAttributeClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AttributeType_attributeClass_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AttributeType_attributeClass_feature", "_UI_AttributeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Default Value feature.
     * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addDefaultValuePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AttributeType_defaultValue_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AttributeType_defaultValue_feature", "_UI_AttributeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.ATTRIBUTE_TYPE__DEFAULT_VALUE,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Suggested Value feature.
     * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addSuggestedValuePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AttributeType_suggestedValue_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AttributeType_suggestedValue_feature", "_UI_AttributeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.ATTRIBUTE_TYPE__SUGGESTED_VALUE,
                 true,
                 false,
                 false,
                 null,
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
                 getString("_UI_AttributeType_id_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AttributeType_id_feature", "_UI_AttributeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ID,
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
            childrenFeatures.add(FacesConfigPackage.Literals.ATTRIBUTE_TYPE__DESCRIPTION);
            childrenFeatures.add(FacesConfigPackage.Literals.ATTRIBUTE_TYPE__DISPLAY_NAME);
            childrenFeatures.add(FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ICON);
            childrenFeatures.add(FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ATTRIBUTE_NAME);
            childrenFeatures.add(FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION);
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
     * This returns AttributeType.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/AttributeType")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getText(Object object) {
        String label = ((AttributeType)object).getId();
        return label == null || label.length() == 0 ?
            getString("_UI_AttributeType_type") : //$NON-NLS-1$
            getString("_UI_AttributeType_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(AttributeType.class)) {
            case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS:
            case FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE:
            case FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE:
            case FacesConfigPackage.ATTRIBUTE_TYPE__ID:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case FacesConfigPackage.ATTRIBUTE_TYPE__DESCRIPTION:
            case FacesConfigPackage.ATTRIBUTE_TYPE__DISPLAY_NAME:
            case FacesConfigPackage.ATTRIBUTE_TYPE__ICON:
            case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME:
            case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION:
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
                (FacesConfigPackage.Literals.ATTRIBUTE_TYPE__DESCRIPTION,
                 FacesConfigFactory.eINSTANCE.createDescriptionType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.ATTRIBUTE_TYPE__DISPLAY_NAME,
                 FacesConfigFactory.eINSTANCE.createDisplayNameType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ICON,
                 FacesConfigFactory.eINSTANCE.createIconType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ATTRIBUTE_NAME,
                 FacesConfigFactory.eINSTANCE.createAttributeNameType()));

        newChildDescriptors.add
            (createChildParameter
                (FacesConfigPackage.Literals.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION,
                 FacesConfigFactory.eINSTANCE.createAttributeExtensionType()));
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

	public String getColumnText(Object object, int columnIndex) {
		AttributeType attribute = (AttributeType) object;
		switch (columnIndex) {

		case 0:
			return attribute.getAttributeName() == null ? "" : attribute //$NON-NLS-1$
					.getAttributeName().getTextContent();
		case 1:
			return attribute.getAttributeClass() == null ? "" : attribute //$NON-NLS-1$
					.getAttributeClass().getTextContent();
		case 2:
			return attribute.getDefaultValue() == null ? "" : attribute //$NON-NLS-1$
					.getDefaultValue().getTextContent();
		case 3:
			return attribute.getSuggestedValue() == null ? "" : attribute //$NON-NLS-1$
					.getSuggestedValue().getTextContent();
		}
		return null;
	}

	public Object getColumnImage(Object object, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
