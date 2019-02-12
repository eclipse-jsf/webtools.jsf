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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType} object.
 * <!-- begin-user-doc -->
 * @extends ITableItemLabelProvider 
 * <!-- end-user-doc -->
 * @generated
 */
@SuppressWarnings("nls")
public class RendererTypeTypeItemProvider extends ItemProviderAdapter implements
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
	public RendererTypeTypeItemProvider(AdapterFactory adapterFactory) {
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

			addTextContentPropertyDescriptor(object);
			addIdPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Text Content feature.
	 * <!-- begin-user-doc --> 
	 * @param object
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTextContentPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RendererTypeType_textContent_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RendererTypeType_textContent_feature", "_UI_RendererTypeType_type"),
				 FacesConfigPackage.Literals.RENDERER_TYPE_TYPE__TEXT_CONTENT,
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
				 getString("_UI_RendererTypeType_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RendererTypeType_id_feature", "_UI_RendererTypeType_type"),
				 FacesConfigPackage.Literals.RENDERER_TYPE_TYPE__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This returns RendererTypeType.gif.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/RendererTypeType"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((RendererTypeType)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_RendererTypeType_type") :
			getString("_UI_RendererTypeType_type") + " " + label;
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

		switch (notification.getFeatureID(RendererTypeType.class)) {
			case FacesConfigPackage.RENDERER_TYPE_TYPE__TEXT_CONTENT:
			case FacesConfigPackage.RENDERER_TYPE_TYPE__ID:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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

	public String getColumnText(Object object, int columnIndex) {
		RendererType renderer = (RendererType) object;
		switch (columnIndex) {

		case 0:
			return renderer.getDisplayName().get(0) == null ? "" //$NON-NLS-1$
					: (String) renderer.getDisplayName().get(0);
		case 1:
			return renderer.getRendererClass() == null ? "" : renderer //$NON-NLS-1$
					.getRendererClass().getTextContent();
		case 2:
			return renderer.getComponentFamily().getTextContent() == null ? "" //$NON-NLS-1$
					: renderer.getComponentFamily().getTextContent();
		case 3:
			return renderer.getRendererType().getTextContent() == null ? "" //$NON-NLS-1$
					: renderer.getRendererType().getTextContent();
		}
		return null;
	}

	public Object getColumnImage(Object object, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
