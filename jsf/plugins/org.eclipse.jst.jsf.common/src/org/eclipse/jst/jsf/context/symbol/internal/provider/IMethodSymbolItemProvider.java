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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IMethodSymbolItemProvider
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
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * @param adapterFactory 
     * <!-- end-user-doc -->
     * @generated
     */
    public IMethodSymbolItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the property sheet descriptors
     * <!-- end-user-doc -->
     * @generated
     */
    public List getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addNamePropertyDescriptor(object);
            addSignaturePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ISymbol_name_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ISymbol_name_feature", "_UI_ISymbol_type"),
                 SymbolPackage.Literals.ISYMBOL__NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Signature feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSignaturePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IMethodSymbol_signature_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IMethodSymbol_signature_feature", "_UI_IMethodSymbol_type"),
                 SymbolPackage.Literals.IMETHOD_SYMBOL__SIGNATURE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This returns IMethodSymbol.gif.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the image representatino of object
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/IMethodSymbol"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the text represention of object 
     * <!-- end-user-doc -->
     * @generated
     */
    public String getText(Object object) {
        String label = ((IMethodSymbol)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_IMethodSymbol_type") :
            getString("_UI_IMethodSymbol_type") + " " + label;
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

        switch (notification.getFeatureID(IMethodSymbol.class)) {
            case SymbolPackage.IMETHOD_SYMBOL__NAME:
            case SymbolPackage.IMETHOD_SYMBOL__SIGNATURE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
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
     * @return the EMF resource locator 
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceLocator getResourceLocator() {
        return JSFCommonPlugin.INSTANCE;
    }

}
