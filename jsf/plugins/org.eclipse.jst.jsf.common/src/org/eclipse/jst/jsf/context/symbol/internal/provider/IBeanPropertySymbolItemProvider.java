/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.provider;


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
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol} object.
 * <!-- begin-user-doc -->
 * @extends IContentProposalProvider
 * <!-- end-user-doc -->
 * @generated
 */
public class IBeanPropertySymbolItemProvider
	extends IPropertySymbolItemProvider
	implements	
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IContentProposalProvider
{
	private static final String PROPERTY_SYMBOL_WO = "full/obj16/IBeanPropertySymbol_wo"; //$NON-NLS-1$
	private static final String PROPERTY_SYMBOL_RO = "full/obj16/IBeanPropertySymbol_ro"; //$NON-NLS-1$
	private static final String PROPERTY_SYMBOL_RW = "full/obj16/IBeanPropertySymbol_rw"; //$NON-NLS-1$
	private static final String PROPERTY_SYMBOL_WO_INDEXABLE = "full/obj16/IBeanPropertySymbol_wo_indexable"; //$NON-NLS-1$
	private static final String PROPERTY_SYMBOL_RO_INDEXABLE = "full/obj16/IBeanPropertySymbol_ro_indexable"; //$NON-NLS-1$
	private static final String PROPERTY_SYMBOL_RW_INDEXABLE = "full/obj16/IBeanPropertySymbol_rw_indexable"; //$NON-NLS-1$
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("hiding")
	public static final String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
     * @param adapterFactory 
     * @extends IContentProposalProvider
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IBeanPropertySymbolItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the list of property descriptors 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addOwnerPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Owner feature.
	 * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOwnerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IBeanPropertySymbol_owner_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_IBeanPropertySymbol_owner_feature", "_UI_IBeanPropertySymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SymbolPackage.Literals.IBEAN_PROPERTY_SYMBOL__OWNER,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns IBeanPropertySymbol.gif.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the image associated with object or null if not found 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object getImage(Object object) {
		IBeanPropertySymbol propertySymbol = (IBeanPropertySymbol) object;
		String imageFile = null;
		ITypeDescriptor typeDescriptor = propertySymbol.getTypeDescriptor();
		if (typeDescriptor != null && (typeDescriptor.isArray() || typeDescriptor.instanceOf("Ljava.util.List;") || typeDescriptor.instanceOf("Ljava.util.Map;"))) { //$NON-NLS-1$ //$NON-NLS-2$
			// property is somehow "indexable" by int/String:
			if (propertySymbol.isReadable() && propertySymbol.isWritable()) {
				imageFile = PROPERTY_SYMBOL_RW_INDEXABLE;
			} else if (propertySymbol.isReadable()) {
				imageFile = PROPERTY_SYMBOL_RO_INDEXABLE;
			} else if (propertySymbol.isWritable()) {
				imageFile = PROPERTY_SYMBOL_WO_INDEXABLE;
			}
		} else {
			if (propertySymbol.isReadable() && propertySymbol.isWritable()) {
				imageFile = PROPERTY_SYMBOL_RW;
			} else if (propertySymbol.isReadable()) {
				imageFile = PROPERTY_SYMBOL_RO;
			} else if (propertySymbol.isWritable()) {
				imageFile = PROPERTY_SYMBOL_WO;
			}
		}
		return imageFile == null? null : overlayImage(object, getResourceLocator().getImage(imageFile));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the text label associated with object
 	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((IBeanPropertySymbol)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_IBeanPropertySymbol_type") : //$NON-NLS-1$
			getString("_UI_IBeanPropertySymbol_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren(Notification)} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged(Notification)}.
	 * <!-- begin-user-doc -->
     * @param notification 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc -->
     * @param newChildDescriptors 
     * @param object 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
     * @return the resource locator 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return JSFCommonPlugin.INSTANCE;
	}

}
