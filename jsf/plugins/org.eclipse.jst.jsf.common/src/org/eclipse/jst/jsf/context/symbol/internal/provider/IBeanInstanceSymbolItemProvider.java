/*******************************************************************************
 * Copyright (c) 2006, 2013 Oracle Corporation.
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
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol} object.
 * <!-- begin-user-doc -->
 * @extends IContentProposalProvider
 * <!-- end-user-doc -->
 * @generated
 */
public class IBeanInstanceSymbolItemProvider
	extends IInstanceSymbolItemProvider
	implements	
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IContentProposalProvider 
{
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
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IBeanInstanceSymbolItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * @param object 
	 * @return 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addPropertiesPropertyDescriptor(object);
			addMethodsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Properties feature.
	 * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPropertiesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IBeanInstanceSymbol_properties_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_IBeanInstanceSymbol_properties_feature", "_UI_IBeanInstanceSymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SymbolPackage.Literals.IBEAN_INSTANCE_SYMBOL__PROPERTIES,
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Methods feature.
	 * <!-- begin-user-doc -->
     * @param object 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMethodsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IBeanInstanceSymbol_methods_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_IBeanInstanceSymbol_methods_feature", "_UI_IBeanInstanceSymbol_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SymbolPackage.Literals.IBEAN_INSTANCE_SYMBOL__METHODS,
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns IBeanInstanceSymbol.gif.
	 * <!-- begin-user-doc -->
     * @param object 
     * @return the image associated with object or null if not found 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IBeanInstanceSymbol")); //$NON-NLS-1$
	}

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object 
     * @return the text label associated with object or null if not found
	 * <!-- end-user-doc -->
     * @generated NOT
     */
	public String getText(Object object) {
        String label = ((IBeanInstanceSymbol)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_IBeanInstanceSymbol_type") : //$NON-NLS-1$
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

	/**
	 * @param target_
	 * @param proposalFactory 
	 * @return a completion proposal for target at offset
	 */
	public ICompletionProposal[] getProposals(Object target_, 
                                          IProposalCreationFactory proposalFactory) 
	{
		IBeanInstanceSymbol  symbol = (IBeanInstanceSymbol) target_;

		final String replacementText = symbol.getName();
		final String displayText = getText(symbol);
		final String additionalText = symbol.getDetailedDescription();
		// for running headless, we need to avoid hitting the image registry
        // because it accesses the Display object that won't exist
        final Image displayImage = Display.getCurrent() != null ? 
            ExtendedImageRegistry.getInstance().getImage(getImage(symbol))
            : null;
		
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
