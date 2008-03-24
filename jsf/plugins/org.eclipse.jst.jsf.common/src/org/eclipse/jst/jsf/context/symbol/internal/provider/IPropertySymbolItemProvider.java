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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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
import org.eclipse.jst.jsf.context.symbol.IDescribedInDetail;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.ui.internal.contentassist.CustomCompletionProposal;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.IPropertySymbol} object.
 * <!-- begin-user-doc -->
 * @extends IContentProposalProvider
 * <!-- end-user-doc -->
 * @generated
 */
public class IPropertySymbolItemProvider
    extends ItemProviderAdapter
    implements	
        IEditingDomainItemProvider,	
        IStructuredItemContentProvider,	
        ITreeItemContentProvider,	
        IItemLabelProvider,	
        IItemPropertySource,
        IContentProposalProvider{
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
     * @param adapterFactory 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IPropertySymbolItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the property descriptors 
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
			addIntermediatePropertyDescriptor(object);
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
				 getString("_UI_ISymbol_name_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ISymbol_name_feature", "_UI_ISymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
				 getString("_UI_IObjectSymbol_typeDescriptor_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_IObjectSymbol_typeDescriptor_feature", "_UI_IObjectSymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
				 getString("_UI_IObjectSymbol_readable_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_IObjectSymbol_readable_feature", "_UI_IObjectSymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
				 getString("_UI_IObjectSymbol_writable_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_IObjectSymbol_writable_feature", "_UI_IObjectSymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SymbolPackage.Literals.IOBJECT_SYMBOL__WRITABLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Intermediate feature.
	 * <!-- begin-user-doc -->
     * @param object 
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addIntermediatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IPropertySymbol_intermediate_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_IPropertySymbol_intermediate_feature", "_UI_IPropertySymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SymbolPackage.Literals.IPROPERTY_SYMBOL__INTERMEDIATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns IPropertySymbol.gif.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the image rep of object 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IPropertySymbol")); //$NON-NLS-1$
	}

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the text representation of the object 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getText(Object object) {
        String label = ((IPropertySymbol)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_IPropertySymbol_type") : //$NON-NLS-1$
            label;
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

		switch (notification.getFeatureID(IPropertySymbol.class)) {
			case SymbolPackage.IPROPERTY_SYMBOL__NAME:
			case SymbolPackage.IPROPERTY_SYMBOL__READABLE:
			case SymbolPackage.IPROPERTY_SYMBOL__WRITABLE:
			case SymbolPackage.IPROPERTY_SYMBOL__INTERMEDIATE:
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

    /**
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
     */
    public Collection getChildren(Object object) 
    {
        final List  childSymbols = new ArrayList();
        childSymbols.addAll(((IPropertySymbol)object).getTypeDescriptor().getProperties());
        return Collections.unmodifiableList(childSymbols);
    }

    /**
     * @see org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider#getProposals(Object, org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider.IProposalCreationFactory)
     */
    public ICompletionProposal[] getProposals(Object target1, IProposalCreationFactory proposalFactory) 
    {
        return getProposalsInternal((IPropertySymbol) target1, proposalFactory, ""); //$NON-NLS-1$
    }
    
    private ICompletionProposal[] getProposalsInternal(IPropertySymbol symbol, IProposalCreationFactory proposalFactory,  String intermediatePrefix)
    {
        final List            completions = new ArrayList();
        final Image displayImage = 
            ExtendedImageRegistry.getInstance().getImage(getImage(symbol));
            
        if (symbol.isIntermediate())
        {
            // see if there's something using this as intermediate completion
            List props = symbol.getTypeDescriptor().getProperties();
            
            for (final Iterator it = props.iterator(); it.hasNext();)
            {
                final IPropertySymbol propSymbol = (IPropertySymbol) it.next();

                if (!propSymbol.isIntermediate())
                {
                    final String replacementText = "['" + intermediatePrefix + symbol.getName() + "." + propSymbol.getName() + "']"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    completions.add(proposalFactory.createProposal(                          
                                        replacementText, 
                                        replacementText, 
                                        null, 
                                        displayImage, symbol));
                }
                else
                {
                    completions.addAll(Arrays.asList(getProposalsInternal(propSymbol, proposalFactory, intermediatePrefix+symbol.getName()+"."))); //$NON-NLS-1$
                }
            }
        }
        else
        {
            final String replacementText = intermediatePrefix + symbol.getName();
            final String displayText = symbol.getName();

            completions.add(createProposal(symbol, replacementText, displayText, displayImage,
					proposalFactory));
        }
        
        return (ICompletionProposal[]) completions.toArray(new CustomCompletionProposal[0]);

    }

	private ICompletionProposal createProposal(IPropertySymbol symbol,
			final String replacementText, final String displayText,
			final Image displayImage, IProposalCreationFactory proposalFactory) {
		String additionalText = null; // TODO: put property value here where possible?
        if (symbol instanceof IDescribedInDetail) {
            additionalText = ((IDescribedInDetail) symbol).getDetailedDescription();
        }
		return proposalFactory.createProposal(
		                                    replacementText, 
		                                    displayText, 
		                                    additionalText,
		                                    displayImage, symbol);
	}
}
