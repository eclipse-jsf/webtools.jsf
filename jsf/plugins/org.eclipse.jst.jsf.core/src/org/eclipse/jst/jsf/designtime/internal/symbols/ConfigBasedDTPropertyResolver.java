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
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.designtime.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.DefaultDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.IDecorativeResolver;
import org.eclipse.jst.jsf.designtime.el.IInstancePerProjectResolver;

/**
 * A design time proxy for the runtime ProperyResolver.
 * 
 * @author xnjiang
 * 
 */
public class ConfigBasedDTPropertyResolver extends DefaultDTPropertyResolver
        implements IInstancePerProjectResolver
{
    private IProject _project;

    /**
     * Constructor
     * 
     */
    public ConfigBasedDTPropertyResolver()
    {
        super();
    }

    @Override
    public ISymbol[] getAllProperties(final ISymbol base)
    {
        final Map<String, ISymbol> allSymbols = new HashMap<String, ISymbol>();
        final ISymbol[] superSymbols = super.getAllProperties(base);
        for (final ISymbol superSymbol : superSymbols)
        {
            allSymbols.put(superSymbol.getName(), superSymbol);
        }

        if (JSFCorePlugin.hasDecorativePropertyResolvers())
        {
            final List<AbstractDTPropertyResolver> resolvers = retrieveDecorativePropertyResolvers(_project);
            for (final AbstractDTPropertyResolver resolver : resolvers)
            {
                final ISymbol[] otherSymbols = resolver.getAllProperties(base);
                for (final ISymbol otherSymbol : otherSymbols)
                {
                    // allow subsequent resolvers to replace symbols by name
                    // added previously
                    allSymbols.put(otherSymbol.getName(), otherSymbol);
                }
            }
        }
        return allSymbols.values().toArray(ISymbol.EMPTY_SYMBOL_ARRAY);

    }

    @Override
    public ISymbol getProperty(final ISymbol base, final int offset)
    {
        if (JSFCorePlugin.hasDecorativePropertyResolvers())
        {
            final List<AbstractDTPropertyResolver> resolvers = retrieveDecorativePropertyResolvers(_project);

            // we need to iterate backwards through the resolvers, since
            // the we take the first answer we receive.
            for (int i = resolvers.size() - 1; i >= 0; i--)
            {
                final ISymbol symbol = resolvers.get(i).getProperty(base,
                        offset);
                if (symbol != null)
                {
                    return symbol;
                }
            }
        }
        return super.getProperty(base, offset);
    }

    @Override
    public ISymbol getProperty(final ISymbol base, final Object propertyId)
    {
        if (JSFCorePlugin.hasDecorativePropertyResolvers())
        {
            final List<AbstractDTPropertyResolver> resolvers = retrieveDecorativePropertyResolvers(_project);

            // we need to iterate backwards through the resolvers, since
            // the we take the first answer we receive.
            for (int i = resolvers.size() - 1; i >= 0; i--)
            {
                final ISymbol symbol = resolvers.get(i).getProperty(base,
                        propertyId);
                if (symbol != null)
                {
                    return symbol;
                }
            }
        }
        return super.getProperty(base, propertyId);
    }

    private List<AbstractDTPropertyResolver> retrieveDecorativePropertyResolvers(
            final IProject project)
    {
        final IJSFAppConfigManager manager = JSFAppConfigManagerFactory
                .getJSFAppConfigManagerInstance(project);
        final List<String> propertyResolvers = manager.getPropertyResolvers();
        final List<String> elResolvers = manager.getELResolvers();
        propertyResolvers.addAll(elResolvers);
        final List<AbstractDTPropertyResolver> resolvers = new ArrayList<AbstractDTPropertyResolver>();
        for (final String propertyResolver : propertyResolvers)
        {
            final List<String> ids = JSFCorePlugin
                    .getPropertyResolversForName(propertyResolver);

            for (final String id : ids)
            {
                final AbstractDTPropertyResolver resolver = JSFCorePlugin
                        .getPropertyResolver(id).getInstance(_project);
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
        return resolvers;
    }

    public void setProject(final IProject project)
    {
        _project = project;
    }

    // private void retrievePropertyResolver() {
    // try {
    // String str =
    // project.getPersistentProperty(PERSIST_PROPERTY_KEY_PROPERTY_RESOLVERS);
    // if (str != null) {
    // resolvers = new ArrayList<AbstractDTPropertyResolver>();
    // StringTokenizer tokenizer = new StringTokenizer(str, ",");//$NON-NLS-1$
    // while (tokenizer.hasMoreTokens()) {
    // AbstractDTPropertyResolver resolver =
    // JSFCorePlugin.getPropertyResolvers().get(tokenizer.nextToken());
    // if (resolver != null) {
    // resolvers.add(resolver);
    // }
    // }
    // } else {
    // checkFacesConfigForPropertyResolver();
    // }
    // } catch (CoreException e) {
    // checkFacesConfigForPropertyResolver();
    // }
    // }
    //
    // private void checkFacesConfigForPropertyResolver() {
    // resolvers = new ArrayList<AbstractDTPropertyResolver>();
    // String str = "";//$NON-NLS-1$
    // boolean bFirst = true;
    // List facesConfigs =
    // JSFAppConfigManager.getInstance(project).getFacesConfigModels();
    // Iterator itFacesConfigs = facesConfigs.iterator();
    // while (itFacesConfigs.hasNext()) {
    // FacesConfigType facesConfig = (FacesConfigType)itFacesConfigs.next();
    // EList applications = facesConfig.getApplication();
    // for (int i=0;i<applications.size();i++) {
    // EList resolvers2 =
    // ((ApplicationType)applications.get(i)).getPropertyResolver();
    // for (int j=0;j<resolvers2.size();j++) {
    // PropertyResolverType resolverType =
    // (PropertyResolverType)resolvers2.get(i);
    // AbstractDTPropertyResolver resolver =
    // JSFCorePlugin.getPropertyResolvers().get(resolverType.getTextContent());
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
    // project.setPersistentProperty(PERSIST_PROPERTY_KEY_PROPERTY_RESOLVERS,
    // str);
    // } else {
    // project.setPersistentProperty(PERSIST_PROPERTY_KEY_PROPERTY_RESOLVERS,
    // null);
    // }
    // } catch (CoreException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // /**
    // * Called when a change in an application configuration model for which
    // * this listener has been registered occurs.
    // *
    // * @param notification EMF {@link Notification} instance that describes
    // the
    // * model change.
    // */
    // public void notifyChanged(Notification notification) {
    // checkFacesConfigForPropertyResolver();
    // }
}
