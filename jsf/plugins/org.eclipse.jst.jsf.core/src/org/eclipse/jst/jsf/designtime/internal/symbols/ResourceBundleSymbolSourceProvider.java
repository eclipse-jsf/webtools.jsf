package org.eclipse.jst.jsf.designtime.internal.symbols;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.AbstractSymbolSourceProviderFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.ISymbolSourceProvider;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.provisional.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.designtime.internal.provisional.symbols.FileContextUtil;
import org.eclipse.jst.jsf.designtime.internal.provisional.symbols.SymbolUtil;
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
           
           final JSFAppConfigManager appconfigMgr = JSFAppConfigManager.getInstance(project);
           final List resourceBundles = appconfigMgr.getResourceBundles();
           
           for (final Iterator it = resourceBundles.iterator(); it.hasNext();)
           {
               ResourceBundleType  resBundle = (ResourceBundleType) it.next();
               final String  basename = getBaseName(resBundle);
               final String  name = getVarName(resBundle);
               
               if (basename != null && name != null)
               {
                   try 
                   {
                       symbols.add(createSymbolForResourceBundle(project, name, basename));
                   } catch (JavaModelException e) {
                       JSFCorePlugin.log(e, "Error creating base name for: "+basename);
                    } catch (IOException e) {
                        JSFCorePlugin.log(e, "Error creating base name for: "+basename);
                    } catch (CoreException e) {
                        JSFCorePlugin.log(e, "Error creating base name for: "+basename);
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
        symbol.setDetailedDescription("Resource bundle map for bundle <i>"+basename+"</i>");
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
