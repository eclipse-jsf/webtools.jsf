/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.symbols;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.AbstractSymbolSourceProviderFactory;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolSourceProvider;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.tld.LoadBundleUtil;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.designtime.symbols.FileContextUtil;
import org.eclipse.jst.jsf.designtime.symbols.SymbolUtil;
import org.eclipse.jst.jsf.facesconfig.emf.BaseNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.VarType;

/**
 * WARNING: this is an interim solution to supporting Faces 1.2 resource
 * bundle variables.  This class will become obsolete by design once
 * the dt framework for Unified EL symbol resolution is added
 * 
 * DO NOT USE THIS CLASS EXTERNALLY, IT WILL BE REMOVED WITHOUT WARNING IN THE FUTURE
 * WITH NO MIGRATION PATH
 * 
 * Self-factory for symbol source providers that contribute symbols for
 * the resource bundle variables declared in Faces 1.2 and greater 
 * app configuration files.
 * 
 * @author cbateman
 *
 */
public final class ResourceBundleSymbolSourceProvider extends
        AbstractSymbolSourceProviderFactory implements ISymbolSourceProvider {

    protected final ISymbolSourceProvider create(IProject project) {
        return this;
    }

    public final ISymbol[] getSymbols(IAdaptable context, int symbolScopeMask) 
    {
       if (isProvider(context))
       {
           final List symbols = new ArrayList();
           final IFile   fileContext = FileContextUtil.deriveIFileFromContext(context);
           final IProject project = fileContext.getProject();
           
           final IJSFAppConfigManager appconfigMgr = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(project);
           final List<ResourceBundleType> resourceBundles = appconfigMgr.getResourceBundles();
           
           for (final ResourceBundleType  resBundle : resourceBundles)
           {            
               final String  basename = getBaseName(resBundle);
               final String  name = getVarName(resBundle);
               
               if (basename != null && name != null)
               {
                   try 
                   {
                       symbols.add(createSymbolForResourceBundle(project, name, basename));
                   } catch (JavaModelException e) {
                       JSFCorePlugin.log(e, "Error creating base name for: "+basename); //$NON-NLS-1$
                    } catch (IOException e) {
                    	//Bug 306811 - Invalid error "messages not found in classpath for project"
                    	ISymbol symbol = createSymbolForResourceBundleInJAR(project, name, basename);
                    	if (symbol != null) {
                    		symbols.add(symbol);
                    	} else {
                    		JSFCorePlugin.log(e, "Error creating base name for: "+basename); //$NON-NLS-1$
                    	}
                    } catch (CoreException e) {
                        JSFCorePlugin.log(e, "Error creating base name for: "+basename); //$NON-NLS-1$
                    }
               }
           }     
           return (ISymbol[]) symbols.toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
       }
       return ISymbol.EMPTY_SYMBOL_ARRAY;
    }

    private ISymbol createSymbolForResourceBundle(IProject project, 
                                                  final String name,
                                                  final String basename) throws JavaModelException, IOException, CoreException
    {
        // TODO: push down into ResourceBundleMapSourceFactory and share
        // with loadBundle
        final Map mapSource = ResourceBundleMapSourceFactory
            .getResourceBundleMapSource(project, basename);
        final IMapTypeDescriptor typeDesc = 
            SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
        typeDesc.setMapSource(mapSource);
        final IComponentSymbol symbol = 
            SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName(name);
        symbol.setTypeDescriptor(typeDesc);
        symbol.setDetailedDescription(Messages.getString("ResourceBundleSymbolSourceProvider.DetailedDescription")+basename+"</i>");  //$NON-NLS-1$//$NON-NLS-2$
        return symbol;
    }

    //Bug 306811 - Invalid error "messages not found in classpath for project"
    private ISymbol createSymbolForResourceBundleInJAR(IProject project, final String name, final String basename) {
    	IComponentSymbol symbol = null;
    	InputStream in = null;
    	try {
    		final IStorage storage = LoadBundleUtil.getLoadBundleResource(project, basename);
    		if (storage != null) {
	    		in = storage.getContents();
	    		Properties props = new Properties();
	    		props.load(in);
	    		final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
	    		typeDesc.setMapSource(props);
	    		symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
	    		symbol.setName(name);
	    		symbol.setTypeDescriptor(typeDesc);
	    		symbol.setDetailedDescription(Messages.getString("ResourceBundleSymbolSourceProvider.DetailedDescription") + basename + "</i>"); //$NON-NLS-1$ //$NON-NLS-2$
    		}
    	} catch(CoreException cex) {
    		//fall through with null symbol
    	} catch(IOException ioex) {
    		//fall through with null symbol
    	} finally {
    		if (in != null) {
    			try {
    				in.close();
    			} catch(IOException ignored) {
    				//do nothing
    			}
    		}
    	}
    	return symbol;
    }

    private String getBaseName(ResourceBundleType resBundle)
    {
        final BaseNameType  baseNameType = resBundle.getBaseName();
        if (baseNameType != null)
        {
            return baseNameType.getTextContent();
        }
        return null;
    }

    private String getVarName(ResourceBundleType resBundle)
    {
        final VarType  varName = resBundle.getVar();
        if (varName != null)
        {
            return varName.getTextContent();
        }
        return null;
    }
    
    public final ISymbol[] getSymbols(String prefix, IAdaptable context,
            int symbolScopeMask) {
        return SymbolUtil.
            filterSymbolsByPrefix(getSymbols(context, symbolScopeMask), prefix);
    }

    public final boolean isProvider(IAdaptable context) {
        IFile file = FileContextUtil.deriveIFileFromContext(context);
        
        if (file != null)
        {
            final IProject project = file.getProject();
            
            if (project != null && project.isAccessible())
            {
                // to be valid, the jsf project must be at least version 1.2
                return JSFAppConfigUtils.isValidJSFProject(project, IJSFCoreConstants.FACET_VERSION_1_2);
            }
        }
        
        return false;
    }
}
