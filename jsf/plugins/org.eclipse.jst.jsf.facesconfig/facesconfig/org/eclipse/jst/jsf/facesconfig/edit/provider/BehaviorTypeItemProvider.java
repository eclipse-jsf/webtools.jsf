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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.jst.jsf.facesconfig.FacesConfigPlugin;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BehaviorTypeItemProvider
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
	public BehaviorTypeItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__DESCRIPTION);
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__DISPLAY_NAME);
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__ICON);
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__BEHAVIOR_ID);
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__BEHAVIOR_CLASS);
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__ATTRIBUTE);
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__PROPERTY);
			childrenFeatures.add(FacesConfigPackage.Literals.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION);
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
	 * This returns BehaviorType.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/BehaviorType")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getText(Object object) {
		return getString("_UI_BehaviorType_type"); //$NON-NLS-1$
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

		switch (notification.getFeatureID(BehaviorType.class)) {
			case FacesConfigPackage.BEHAVIOR_TYPE__DESCRIPTION:
			case FacesConfigPackage.BEHAVIOR_TYPE__DISPLAY_NAME:
			case FacesConfigPackage.BEHAVIOR_TYPE__ICON:
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID:
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS:
			case FacesConfigPackage.BEHAVIOR_TYPE__ATTRIBUTE:
			case FacesConfigPackage.BEHAVIOR_TYPE__PROPERTY:
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION:
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
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__DESCRIPTION,
				 FacesConfigFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__DISPLAY_NAME,
				 FacesConfigFactory.eINSTANCE.createDisplayNameType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__ICON,
				 FacesConfigFactory.eINSTANCE.createIconType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__BEHAVIOR_ID,
				 FacesConfigFactory.eINSTANCE.createBehaviorIdType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__BEHAVIOR_CLASS,
				 FacesConfigFactory.eINSTANCE.createBehaviorClassType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__ATTRIBUTE,
				 FacesConfigFactory.eINSTANCE.createAttributeType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__PROPERTY,
				 FacesConfigFactory.eINSTANCE.createPropertyType()));

		newChildDescriptors.add
			(createChildParameter
				(FacesConfigPackage.Literals.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION,
				 FacesConfigFactory.eINSTANCE.createBehaviorExtensionType()));
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
