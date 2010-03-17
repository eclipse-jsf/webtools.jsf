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
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType} object.
 * <!-- begin-user-doc --> 
 * @extends ITableItemLabelProvider
 * <!-- end-user-doc -->
 * @generated
 */
@SuppressWarnings("nls")
public class ConverterTypeItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider {
    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	public ConverterTypeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
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
     * @param object 
     * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConverterType_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConverterType_id_feature", "_UI_ConverterType_type"),
				 FacesConfigPackage.Literals.CONVERTER_TYPE__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__DESCRIPTION);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__DISPLAY_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__ICON);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_FOR_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__ATTRIBUTE);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__PROPERTY);
			childrenFeatures.add(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_EXTENSION);
		}
		return childrenFeatures;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ConverterType.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ConverterType"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getText(Object object) {
		String label = null;
		
		if (((ConverterType) object).getDisplayName() != null
				&& ((ConverterType) object).getDisplayName().size() > 0) {
			label = ((DisplayNameType) ((ConverterType) object)
					.getDisplayName().get(0)).getTextContent();
		}
		
		if (label == null && ((ConverterType) object).getConverterId() != null) {
			label = ((ConverterType) object).getConverterId().getTextContent();
		}

		if (label == null || label.length() == 0) {
			if (((ConverterType) object).getConverterClass() != null)
				label = ((ConverterType) object).getConverterClass()
						.getTextContent();
		}
		if (label == null || label.length() ==0) {
			label = ((ConverterType) object).getId();
		}

		return label == null || label.length() == 0 ? getString("_UI_ConverterType_type") 
				: label;
//				: getString("_UI_ConverterType_type") + " " + label;
	}

    /**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ConverterType.class)) {
			case FacesConfigPackage.CONVERTER_TYPE__ID:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case FacesConfigPackage.CONVERTER_TYPE__DESCRIPTION:
			case FacesConfigPackage.CONVERTER_TYPE__DISPLAY_NAME:
			case FacesConfigPackage.CONVERTER_TYPE__ICON:
			case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID:
			case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS:
			case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS:
			case FacesConfigPackage.CONVERTER_TYPE__ATTRIBUTE:
			case FacesConfigPackage.CONVERTER_TYPE__PROPERTY:
			case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_EXTENSION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

    /**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__DESCRIPTION,
				 FacesConfigFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__DISPLAY_NAME,
				 FacesConfigFactory.eINSTANCE.createDisplayNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__ICON,
				 FacesConfigFactory.eINSTANCE.createIconType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_ID,
				 FacesConfigFactory.eINSTANCE.createConverterIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_FOR_CLASS,
				 FacesConfigFactory.eINSTANCE.createConverterForClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_CLASS,
				 FacesConfigFactory.eINSTANCE.createConverterClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__ATTRIBUTE,
				 FacesConfigFactory.eINSTANCE.createAttributeType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__PROPERTY,
				 FacesConfigFactory.eINSTANCE.createPropertyType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.CONVERTER_TYPE__CONVERTER_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createConverterExtensionType()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return FacesConfigPlugin.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		if(columnIndex ==0)
			return getImage(object);
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object object, int columnIndex) {
		switch (columnIndex) {

		case 0:
			return getText(object);
		case 1:
			return getString("_UI_ConverterType_type"); //$NON-NLS-1$
		}
		return null;
	}

}
