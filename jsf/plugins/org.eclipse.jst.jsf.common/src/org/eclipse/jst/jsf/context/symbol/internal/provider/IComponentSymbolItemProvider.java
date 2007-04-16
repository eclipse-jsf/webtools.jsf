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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.IComponentSymbol} object.
 * <!-- begin-user-doc -->
 * @extends IContentProposalProvider
 * <!-- end-user-doc -->
 * @generated
 */
public class IComponentSymbolItemProvider
	extends IInstanceSymbolItemProvider
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource,
        IContentProposalProvider
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
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IComponentSymbolItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the property descriptor list
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
	 * This returns IComponentSymbol.gif.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the image associated with object or null if not found 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IComponentSymbol"));
	}

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the text label for object or null if not found 
	 * <!-- end-user-doc -->
     * @generated NOT
     */
	public String getText(Object object) {
        String label = ((IComponentSymbol)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_IComponentSymbol_type") :
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
        IComponentSymbol  symbol = (IComponentSymbol) target_;

        final String replacementText = symbol.getName();
        final String displayText = getText(symbol);
        final String additionalText = symbol.getDetailedDescription();
        final Image displayImage = 
            ExtendedImageRegistry.getInstance().getImage(getImage(symbol));

        return new ICompletionProposal[]
        {
            proposalFactory.createProposal(replacementText, 
                                           displayText, 
                                           additionalText, 
                                           displayImage,
                                           target_)
        };
    }
}
