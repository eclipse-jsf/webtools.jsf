/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.provider.IContentProposalProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol} object.
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
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

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
                 getString("_UI_IBeanPropertySymbol_owner_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IBeanPropertySymbol_owner_feature", "_UI_IBeanPropertySymbol_type"),
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
     * @generated
     */
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/IBeanPropertySymbol"));
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
            getString("_UI_IBeanPropertySymbol_type") :
            getString("_UI_IBeanPropertySymbol_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
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
