/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Xiaonan Jiang/IBM  -- extracted from https://bugs.eclipse.org/bugs/show_bug.cgi?id=206514
 *    Cameron Bateman/Oracle - integrated.
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.internal.symbols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver;
import org.eclipse.jst.jsf.designtime.el.DefaultDTVariableResolver;
import org.eclipse.jst.jsf.designtime.el.IDecorativeResolver;

/**
 * A design time proxy for the runtime VariableResolver.
 * 
 * @author xnjiang
 * 
 */
public class ConfigBasedDTVariableResolver extends DefaultDTVariableResolver
// implements IFacesConfigChangeListener
{
    /**
     * Constructor
     */
    public ConfigBasedDTVariableResolver()
    {
        super();

    }

    @Override
    public ISymbol[] getAllVariables(final DTFacesContext facesContext,
            final IAdaptable externalContextKey)
    {
        //final long curTime = System.nanoTime();
        final Map<String, ISymbol> allSymbols = new HashMap<String, ISymbol>();
        final ISymbol[] superSymbols = super.getAllVariables(facesContext,
                externalContextKey);
        for (final ISymbol superSymbol : superSymbols)
        {
            allSymbols.put(superSymbol.getName(), superSymbol);
        }

        if (JSFCorePlugin.hasDecorativeVariableResolvers())
        {
            final IProject project = getProject(facesContext);

            final List<AbstractDTVariableResolver> resolvers = retrieveDecorativeVariableResolvers(project);
            for (final AbstractDTVariableResolver resolver : resolvers)
            {
                final ISymbol[] otherSymbols = resolver.getAllVariables(
                        facesContext, externalContextKey);
                for (final ISymbol otherSymbol : otherSymbols)
                {
                    // allow subsequent resolvers to replace symbols by name
                    // added previously
                    allSymbols.put(otherSymbol.getName(), otherSymbol);
                }
            }
        }
        //System.out.printf("totalAllSymbols: %d\n", Long.valueOf(System.nanoTime()- curTime));
        return allSymbols.values().toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }

    @Override
    public ISymbol resolveVariable(final DTFacesContext context,
            final String name, final IAdaptable externalContextKey)
    {
        //long curTime = System.nanoTime();
        if (JSFCorePlugin.hasDecorativeVariableResolvers())
        {
            final IProject project = getProject(context);

            final List<AbstractDTVariableResolver> resolvers = retrieveDecorativeVariableResolvers(project);

            // we need to iterate backwards through the resolvers, since
            // the we take the first answer we receive.
            for (int i = resolvers.size() - 1; i >= 0; i--)
            {
                final ISymbol symbol = resolvers.get(i).resolveVariable(
                        context, name, externalContextKey);
                if (symbol != null)
                {
                    return symbol;
                }
            }
        }
        ISymbol symbol = super.resolveVariable(context, name, externalContextKey);
        //System.out.printf("totalSymbol: %d\n", Long.valueOf(System.nanoTime()- curTime));
        return symbol;
    }

    private List<AbstractDTVariableResolver> retrieveDecorativeVariableResolvers(
            final IProject project)
    {
        //final long  curTime = System.nanoTime();
        final IJSFAppConfigManager manager = JSFAppConfigManagerFactory
                .getJSFAppConfigManagerInstance(project);
        final List<String> variableResolvers = manager.getVariableResolvers();
        final List<AbstractDTVariableResolver> resolvers = new ArrayList<AbstractDTVariableResolver>();
        for (final String variableResolver : variableResolvers)
        {
            final List<String> ids = JSFCorePlugin
                    .getVariableResolversForName(variableResolver);

            for (final String id : ids)
            {
                final AbstractDTVariableResolver resolver = JSFCorePlugin
                        .getVariableResolvers(id).getInstance(project);
                if (resolver instanceof IDecorativeResolver)
                {
                    resolvers.add(resolver);
                }
                else
                {
                    JSFCorePlugin.log(new Exception(), String.format("resolver %s must implement the IDecorativeResolver interface to be used", id)); //$NON-NLS-1$
                }
            }
        }
        //System.out.printf("retrieveDecorativeVariable: %d\n", Long.valueOf(System.nanoTime()- curTime));
        return resolvers;
    }

    private IProject getProject(final DTFacesContext facesContext)
    {
        final IResource res = facesContext.adaptContextObject();

        if (res != null)
        {
            return res.getProject();
        }
        return null;
    }

    // private void checkFacesConfigForVariableResolver(final IProject project)
    // {
    // resolvers = new ArrayList<AbstractDTVariableResolver>();
    // String str = "";//$NON-NLS-1$
    // boolean bFirst = true;
    // final List facesConfigs =
    // JSFAppConfigManager.getInstance(project).getFacesConfigModels();
    // final Iterator itFacesConfigs = facesConfigs.iterator();
    // while (itFacesConfigs.hasNext()) {
    // final FacesConfigType facesConfig =
    // (FacesConfigType)itFacesConfigs.next();
    // final EList applications = facesConfig.getApplication();
    // for (int i=0;i<applications.size();i++) {
    // final EList resolvers2 =
    // ((ApplicationType)applications.get(i)).getVariableResolver();
    // for (int j=0;j<resolvers2.size();j++) {
    // final VariableResolverType resolverType =
    // (VariableResolverType)resolvers2.get(i);
    // final AbstractDTVariableResolver resolver =
    // JSFCorePlugin.getVariableResolvers().get(resolverType.getTextContent());
    // if (resolver != null) {
    // resolvers.add(resolver);
    // if (bFirst) {
    // bFirst = false;
    // } else {
    // str += ",";//$NON-NLS-1$
    // }
    // str += resolverType.getTextContent();
    // }
    // }
    // }
    // }
    // try {
    // if (!str.equals("")) {//$NON-NLS-1$
    // project.setPersistentProperty(PERSIST_PROPERTY_KEY_VARIABLE_RESOLVERS,
    // str);
    // } else {
    // project.setPersistentProperty(PERSIST_PROPERTY_KEY_VARIABLE_RESOLVERS,
    // null);
    // }
    // } catch (final CoreException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // public void notifyChanged(final Notification notification) {
    // checkFacesConfigForVariableResolver();
    // }

}
