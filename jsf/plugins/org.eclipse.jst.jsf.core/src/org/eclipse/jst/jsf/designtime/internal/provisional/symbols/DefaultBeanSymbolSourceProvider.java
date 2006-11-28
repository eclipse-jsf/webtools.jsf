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

package org.eclipse.jst.jsf.designtime.internal.provisional.symbols;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager;
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
        
        final JSFAppConfigManager  configManager = JSFAppConfigManager.getInstance(iProject);
        

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
                    IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
                    symbol.setName(name);
                    symbol.setDetailedDescription(detailedDescription);
                    symbol.setRuntimeSource(ERuntimeSource.MANAGED_BEAN_SYMBOL_LITERAL);
                    try
                    {
                        IJavaProject javaProject = JavaCore.create(iProject);
                        IType type = javaProject.findType(bean.getManagedBeanClass().getTextContent());
                        
                        // don't bother setting a type descriptor if we
                        // can't find a type
                        if (type != null)
                        {
                            IJavaTypeDescriptor2 javaTypeDescriptor = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
                            javaTypeDescriptor.setType(type);
                            symbol.setJavaTypeDescriptor(javaTypeDescriptor);
                        }
                    }
                    catch (Exception t)
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
        StringBuffer additionalInfo = new StringBuffer("<p><b>");
        additionalInfo.append("Name: </b>");
        additionalInfo.append(beanType.getManagedBeanName().getTextContent());
        additionalInfo.append("</p>");
        additionalInfo.append("<p><b>Type: </b>");
        additionalInfo.append(beanType.getManagedBeanClass().getTextContent());
        additionalInfo.append("</p>");
        additionalInfo.append("<p><b>Scope: </b>");
        additionalInfo.append(beanType.getManagedBeanScope().getTextContent());
        additionalInfo.append("</p>");
        
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
            additionalInfo.append("<p><b>Description: </b>");
            additionalInfo.append(descBuffer);
            additionalInfo.append("</p>");
        }
        
        return additionalInfo.toString();
    }

   
    private boolean isBeanScopeInMask(final ManagedBeanScopeType scope, final int scopeMask)
    {
        final int testScope = ISymbolConstants.getMaskForString(scope.getTextContent());
        
        if (testScope != -1)
        {
            return (scopeMask & testScope) != 0;
        }
        
        // no other cases supported
        return false;
    }
}
