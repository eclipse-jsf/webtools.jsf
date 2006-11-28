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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.wtp.jsf.facesconfig.emf.ValidatorType} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ValidatorTypeItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ValidatorTypeItemProvider(AdapterFactory adapterFactory) {
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
			addValidatorIdPropertyDescriptor(object);
			addValidatorClassPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Validator Id feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addValidatorIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ValidatorType_validatorId_feature"), getString(
						"_UI_PropertyDescriptor_description",
						"_UI_ValidatorType_validatorId_feature",
						"_UI_ValidatorType_type"),
				Literals.VALIDATOR_TYPE__VALIDATOR_ID, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Validator Class feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addValidatorClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ValidatorType_validatorClass_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_ValidatorType_validatorClass_feature",
						"_UI_ValidatorType_type"),
				Literals.VALIDATOR_TYPE__VALIDATOR_CLASS, true, null, null,
				null));
	}

	/**
	 * This adds a property descriptor for the Id feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ValidatorType_id_feature"), getString(
						"_UI_PropertyDescriptor_description",
						"_UI_ValidatorType_id_feature",
						"_UI_ValidatorType_type"), Literals.VALIDATOR_TYPE__ID,
				true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(Literals.VALIDATOR_TYPE__DESCRIPTION);
			childrenFeatures.add(Literals.VALIDATOR_TYPE__DISPLAY_NAME);
			childrenFeatures.add(Literals.VALIDATOR_TYPE__ICON);
			childrenFeatures.add(Literals.VALIDATOR_TYPE__ATTRIBUTE);
			childrenFeatures.add(Literals.VALIDATOR_TYPE__PROPERTY);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper
		// feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ValidatorType.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage(
				"full/obj16/FacesConfig_Validator"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ValidatorType) object).getId();

		if (((ValidatorType) object).getValidatorId() != null
				&& ((ValidatorType) object).getValidatorId().getTextContent() != null) {
			label = ((ValidatorType) object).getValidatorId().getTextContent();
		}

		if (label == null || label.length() == 0) {
			if (((ValidatorType) object).getValidatorClass() != null)
				label = ((ValidatorType) object).getValidatorClass()
						.getTextContent();
		}

		return label == null || label.length() == 0 ? getString("_UI_ValidatorType_type")
				: getString("_UI_ValidatorType_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ValidatorType.class)) {
		case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID:
		case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS:
		case FacesConfigPackage.VALIDATOR_TYPE__ID:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), false, true));
			return;
		case FacesConfigPackage.VALIDATOR_TYPE__DESCRIPTION:
		case FacesConfigPackage.VALIDATOR_TYPE__DISPLAY_NAME:
		case FacesConfigPackage.VALIDATOR_TYPE__ICON:
		case FacesConfigPackage.VALIDATOR_TYPE__ATTRIBUTE:
		case FacesConfigPackage.VALIDATOR_TYPE__PROPERTY:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of
	 * {@link org.eclipse.emf.edit.command.CommandParameter}s describing all of
	 * the children that can be created under this object. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				Literals.VALIDATOR_TYPE__DESCRIPTION,
				FacesConfigFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add(createChildParameter(
				Literals.VALIDATOR_TYPE__DISPLAY_NAME,
				FacesConfigFactory.eINSTANCE.createDisplayNameType()));

		newChildDescriptors.add(createChildParameter(
				Literals.VALIDATOR_TYPE__ICON, FacesConfigFactory.eINSTANCE
						.createIconType()));

		newChildDescriptors.add(createChildParameter(
				Literals.VALIDATOR_TYPE__ATTRIBUTE,
				FacesConfigFactory.eINSTANCE.createAttributeType()));

		newChildDescriptors.add(createChildParameter(
				Literals.VALIDATOR_TYPE__PROPERTY, FacesConfigFactory.eINSTANCE
						.createPropertyType()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return FacesConfigEditPlugin.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex ==0)
			return getImage(object);
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object object, int columnIndex) {
		ValidatorType validator = (ValidatorType) object;
		switch (columnIndex) {
		case 0:
			return getText(object);
		case 1:
			return validator.getValidatorClass() == null ? "" : validator
					.getValidatorClass().getTextContent();
		}
		return null;
	}

}
