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
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.provider.IContentProposalProvider;
import org.eclipse.swt.graphics.Image;




/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol} object.
 * <!-- begin-user-doc -->
 * @extends IContentProposalProvider
 * <!-- end-user-doc -->
 * @generated
 */
public class IInstanceSymbolItemProvider
	extends ItemProviderAdapter
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource,
        IContentProposalProvider {
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
	public IInstanceSymbolItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the EMF property descriptors  
	 * <!-- end-user-doc -->
     * @generated
     */
	public List getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addNamePropertyDescriptor(object);
            addTypeDescriptorPropertyDescriptor(object);
            addReadablePropertyDescriptor(object);
            addWritablePropertyDescriptor(object);
            addTypeResolvedPropertyDescriptor(object);
            addRuntimeSourcePropertyDescriptor(object);
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
     * This adds a property descriptor for the Type Descriptor feature.
     * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addTypeDescriptorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IObjectSymbol_typeDescriptor_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IObjectSymbol_typeDescriptor_feature", "_UI_IObjectSymbol_type"),
                 SymbolPackage.Literals.IOBJECT_SYMBOL__TYPE_DESCRIPTOR,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Readable feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addReadablePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IObjectSymbol_readable_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IObjectSymbol_readable_feature", "_UI_IObjectSymbol_type"),
                 SymbolPackage.Literals.IOBJECT_SYMBOL__READABLE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Writable feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addWritablePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IObjectSymbol_writable_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IObjectSymbol_writable_feature", "_UI_IObjectSymbol_type"),
                 SymbolPackage.Literals.IOBJECT_SYMBOL__WRITABLE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Type Resolved feature.
     * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addTypeResolvedPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IInstanceSymbol_typeResolved_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IInstanceSymbol_typeResolved_feature", "_UI_IInstanceSymbol_type"),
                 SymbolPackage.Literals.IINSTANCE_SYMBOL__TYPE_RESOLVED,
                 false,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Runtime Source feature.
     * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addRuntimeSourcePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IInstanceSymbol_runtimeSource_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IInstanceSymbol_runtimeSource_feature", "_UI_IInstanceSymbol_type"),
                 SymbolPackage.Literals.IINSTANCE_SYMBOL__RUNTIME_SOURCE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This returns IInstanceSymbol.gif.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the image adaption for object
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/IInstanceSymbol"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the text representation of object 
	 * <!-- end-user-doc -->
     * @generated NOT
     */
	public String getText(Object object) {
        String label = ((IInstanceSymbol)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_IInstanceSymbol_type") :
            label;
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

        switch (notification.getFeatureID(IInstanceSymbol.class)) {
            case SymbolPackage.IINSTANCE_SYMBOL__NAME:
            case SymbolPackage.IINSTANCE_SYMBOL__READABLE:
            case SymbolPackage.IINSTANCE_SYMBOL__WRITABLE:
            case SymbolPackage.IINSTANCE_SYMBOL__TYPE_RESOLVED:
            case SymbolPackage.IINSTANCE_SYMBOL__RUNTIME_SOURCE:
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
     * @return the resource locator 
	 * <!-- end-user-doc -->
     * @generated
     */
	public ResourceLocator getResourceLocator() {
        return JSFCommonPlugin.INSTANCE;
    }

    public ICompletionProposal[] getProposals(Object target_, 
                                    IProposalCreationFactory proposalFactory) 
    {
        IInstanceSymbol  symbol = (IInstanceSymbol) target_;

        final String replacementText = symbol.getName();
        final String displayText = getText(symbol);
        //final String additionalText = symbol.getDetailedDescription();
        final Image displayImage = 
            ExtendedImageRegistry.getInstance().getImage(getImage(symbol));

        return new ICompletionProposal[]
        {
            proposalFactory.createProposal(replacementText, 
                                           displayText, 
                                           null, 
                                           displayImage,
                                           target_)
        };
    }
}
