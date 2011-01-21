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

package org.eclipse.jst.jsf.designtime.symbols;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;

/**
 * Self-factory provider of bean symbol information for a particular project.
 * 
 * Context is determined by querying all known managed beans declared in a 
 * web project.
 * 
 * Client may sub-class
 * 
 * @author cbateman
 *
 */
public class DefaultBeanSymbolSourceProvider
{
    private static DefaultBeanSymbolSourceProvider  INSTANCE;

    /**
     * @return the singleton instance of the provider
     */
    public static DefaultBeanSymbolSourceProvider getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DefaultBeanSymbolSourceProvider();
        }

        return INSTANCE;
    }

    /**
     * No external instantiation
     * 
     * Left protected to allow sub-classing
     */
    protected DefaultBeanSymbolSourceProvider() {/* empty */}
    
    /**
     * @param context
     * @param symbolScopeMask
     * @return all bean symbols for the context at scopes matching symbolScopeMask
     */
    public ISymbol[] getSymbols(IAdaptable context, int symbolScopeMask) 
    {
        final IFile file = FileContextUtil.deriveIFileFromContext(context);
        if (file != null)
        {
            final IProject  myProject = file.getProject();
            List  managedBeans = getManagedBeanSymbols(myProject, symbolScopeMask);
            return (ISymbol[]) managedBeans.toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
        }
        
        return ISymbol.EMPTY_SYMBOL_ARRAY;
    }

    /**
     * @param name
     * @param context
     * @param symbolScopeMask
     * @return the symbol called name, in context/symbolScope
     */
    public ISymbol getSymbol(final String name, final IAdaptable context, 
                             final int symbolScopeMask)
    {
        return SymbolUtil.
                findSymbolByName(getSymbols(context, symbolScopeMask), name);
    }
    
    /**
     * @param prefix
     * @param context
     * @param symbolScopeMask
     * @return all symbols for context and symbolScopeMask that start with
     * prefix
     */
    public ISymbol[] getSymbols(String prefix, IAdaptable context,
            int symbolScopeMask) {
        return SymbolUtil.
            filterSymbolsByPrefix(getSymbols(context, symbolScopeMask), prefix);
    }

    private List getManagedBeanSymbols(IProject iProject, int symbolScopeMask)
    {
        List   symbols = new ArrayList();
        
        final IJSFAppConfigManager  configManager = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(iProject);
        

        if (configManager != null)
        {
            for (final Iterator aIt = configManager.getManagedBeans().iterator(); aIt.hasNext();)
            {
                ManagedBeanType  bean = (ManagedBeanType) aIt.next();
                
                // only bother with all this if we care about the scope of this bean
                if (isBeanScopeInMask(bean.getManagedBeanScope(), symbolScopeMask))
                {
                    final String name = bean.getManagedBeanName().getTextContent();
                    final String detailedDescription = createAdditionalProposalInfo(bean);
                    final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
                    symbol.setName(name);
                    if (detailedDescription.length() > 0)
                    symbol.setDetailedDescription(detailedDescription);
                    symbol.setRuntimeSource(ERuntimeSource.MANAGED_BEAN_SYMBOL_LITERAL);
                    try
                    {
                        IJavaProject javaProject = JavaCore.create(iProject);
                        final String typeName = bean.getManagedBeanClass() != null?
                            bean.getManagedBeanClass().getTextContent() : ""; //$NON-NLS-1$
                        final IType type = javaProject.findType(typeName);

                        // don't bother setting a type descriptor if we
                        // can't find a type
                        if (type != null)
                        {
                            IJavaTypeDescriptor2 javaTypeDescriptor = 
                                SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
                            javaTypeDescriptor.setType(type);
                            symbol.setJavaTypeDescriptor(javaTypeDescriptor);
                        }
                    }
                    catch (JavaModelException t)
                    {
                        // do nothing; skip type info for this bean
                    }
                    symbols.add(symbol);
                }
            }
        }
            
        return symbols;
    }
    
    private String createAdditionalProposalInfo(ManagedBeanType beanType)
    {
        final String beanName = beanType.getManagedBeanName() != null ?
                                    beanType.getManagedBeanName().getTextContent() : null;

        StringBuffer additionalInfo = new StringBuffer();
        if (beanName != null)
        {
            additionalInfo.append("<p><b>"+Messages.getString("DefaultBeanSymbolSourceProvider.AdditionalInformation.Name")); //$NON-NLS-1$ //$NON-NLS-2$
            additionalInfo.append(" </b>"); //$NON-NLS-1$
            additionalInfo.append(beanName);
            additionalInfo.append("</p>"); //$NON-NLS-1$
        }

        final String beanClass = beanType.getManagedBeanClass() != null ?
                beanType.getManagedBeanClass().getTextContent() : null;
        if (beanClass != null)
        {
            additionalInfo.append("<p><b>"+Messages.getString("DefaultBeanSymbolSourceProvider.AdditionalInformation.Type")); //$NON-NLS-1$ //$NON-NLS-2$
            additionalInfo.append(" </b>"); //$NON-NLS-1$
            additionalInfo.append(beanClass);
            additionalInfo.append("</p>"); //$NON-NLS-1$
        }
        
        final String beanScope = beanType.getManagedBeanScope() != null ?
                beanType.getManagedBeanScope().getTextContent() : null;
        if (beanScope != null)
        {
            additionalInfo.append("<p><b>"+Messages.getString("DefaultBeanSymbolSourceProvider.AdditionalInformation.Scope")); //$NON-NLS-1$ //$NON-NLS-2$
            additionalInfo.append(" </b>"); //$NON-NLS-1$
            additionalInfo.append(beanScope);
            additionalInfo.append("</p>"); //$NON-NLS-1$
        }
        StringBuffer descBuffer = new StringBuffer();
        
        for (final Iterator it = beanType.getDescription().iterator(); it.hasNext();)
        {
            final DescriptionType descType = (DescriptionType) it.next();
            final String appendString = descType.getTextContent();
            if (appendString != null)
            {
                descBuffer.append(appendString);
            }
        }
        
        if (descBuffer.length() > 0)
        {
            additionalInfo.append("<p><b>"); //$NON-NLS-1$
            additionalInfo.append(Messages.getString("DefaultBeanSymbolSourceProvider.AdditionalInformation.Description")); //$NON-NLS-1$
            additionalInfo.append("</b>"); //$NON-NLS-1$
            additionalInfo.append(descBuffer);
            additionalInfo.append("</p>"); //$NON-NLS-1$
        }
        
        return additionalInfo.toString();
    }

   
    private boolean isBeanScopeInMask(final ManagedBeanScopeType scope, final int scopeMask)
    {
        final int testScope =
        	scope != null ? ISymbolConstants.getMaskForString(scope.getTextContent()) : -1;
        
        if (testScope != -1)
        {
            return (scopeMask & testScope) != 0;
        }
        
        // no other cases supported
        return false;
    }
}
