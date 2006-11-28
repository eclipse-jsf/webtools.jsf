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
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.wtp.jsf.facesconfig.emf.RenderKitType} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class RenderKitTypeItemProvider extends ItemProviderAdapter implements
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
	public RenderKitTypeItemProvider(AdapterFactory adapterFactory) {
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
			addRenderKitIdPropertyDescriptor(object);
			addRenderKitClassPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Render Kit Id feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRenderKitIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(), getResourceLocator(),
						getString("_UI_RenderKitType_renderKitId_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_RenderKitType_renderKitId_feature",
								"_UI_RenderKitType_type"),
						Literals.RENDER_KIT_TYPE__RENDER_KIT_ID, true, null,
						null, null));
	}

	/**
	 * This adds a property descriptor for the Render Kit Class feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRenderKitClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_RenderKitType_renderKitClass_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_RenderKitType_renderKitClass_feature",
						"_UI_RenderKitType_type"),
				Literals.RENDER_KIT_TYPE__RENDER_KIT_CLASS, true, null, null,
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
				getString("_UI_RenderKitType_id_feature"), getString(
						"_UI_PropertyDescriptor_description",
						"_UI_RenderKitType_id_feature",
						"_UI_RenderKitType_type"),
				Literals.RENDER_KIT_TYPE__ID, true,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
			childrenFeatures.add(Literals.RENDER_KIT_TYPE__DESCRIPTION);
			childrenFeatures.add(Literals.RENDER_KIT_TYPE__DISPLAY_NAME);
			childrenFeatures.add(Literals.RENDER_KIT_TYPE__ICON);
			childrenFeatures.add(Literals.RENDER_KIT_TYPE__RENDERER);
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
	 * This returns RenderKitType.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage(
				"full/obj16/FacesConfig_RenderKit"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getText(Object object) {

		String label = ((RenderKitType) object).getId();

		if (((RenderKitType) object).getRenderKitId() != null
				&& ((RenderKitType) object).getRenderKitId().getTextContent() != null) {
			label = ((RenderKitType) object).getRenderKitId().getTextContent();
		}

		if (label == null || label.length() == 0) {
			if (((RenderKitType) object).getRenderKitClass() != null)
				label = ((RenderKitType) object).getRenderKitClass()
						.getTextContent();
		}

		return label == null || label.length() == 0 ? getString("_UI_RenderKitType_type")
				: getString("_UI_RenderKitType_type") + " " + label;
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

		switch (notification.getFeatureID(RenderKitType.class)) {
		case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID:
		case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS:
		case FacesConfigPackage.RENDER_KIT_TYPE__ID:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), false, true));
			return;
		case FacesConfigPackage.RENDER_KIT_TYPE__DESCRIPTION:
		case FacesConfigPackage.RENDER_KIT_TYPE__DISPLAY_NAME:
		case FacesConfigPackage.RENDER_KIT_TYPE__ICON:
		case FacesConfigPackage.RENDER_KIT_TYPE__RENDERER:
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
				Literals.RENDER_KIT_TYPE__DESCRIPTION,
				FacesConfigFactory.eINSTANCE.createDescriptionType()));

		newChildDescriptors.add(createChildParameter(
				Literals.RENDER_KIT_TYPE__DISPLAY_NAME,
				FacesConfigFactory.eINSTANCE.createDisplayNameType()));

		newChildDescriptors.add(createChildParameter(
				Literals.RENDER_KIT_TYPE__ICON, FacesConfigFactory.eINSTANCE
						.createIconType()));

		newChildDescriptors.add(createChildParameter(
				Literals.RENDER_KIT_TYPE__RENDERER,
				FacesConfigFactory.eINSTANCE.createRendererType()));
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
		RenderKitType renderkit = (RenderKitType) object;
		switch (columnIndex) {
		case 0:
			return getText(object);
		case 1:
			return renderkit.getRenderKitClass() == null ? "" : renderkit
					.getRenderKitClass().getTextContent();
		}
		return null;
	}

}
