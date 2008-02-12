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

package org.eclipse.jst.jsf.designtime.resolver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * A symbol context resolver
 * Clients may NOT sub-class.
 * 
 * @author cbateman
 *
 */
/*package*/ final class SymbolContextResolver extends AbstractSymbolContextResolver 
{
    private final IStructuredDocumentContext        _context;
    private IWorkspaceContextResolver               _wkspResolver; // = null; lazy created through getWorkspaceResolver

	/**
     * @param context
     */
    /* package */SymbolContextResolver(IStructuredDocumentContext context)
    {
        _context = context;
    }

    public ISymbol getVariable(String name) 
    {
        final IFile file = getFile();
        
        if (file != null)
        {
            final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(file.getProject());
            
            if (manager != null)
            {
                final DTFacesContext facesContext = manager.getFacesContext(file);
        
                return manager.getVariableResolver().
                    resolveVariable(facesContext, name, file);
            }
        }
        
        return null;
    }

    public ISymbol[] getAllVariables() 
    {
        final IFile file = getFile();
        
        if (file != null)
        {
            final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(file.getProject());
            
            if (manager != null)
            {
                final DTFacesContext facesContext = manager.getFacesContext(file);
                
                if (facesContext != null)
                {
                    return manager.getVariableResolver().
                            getAllVariables(facesContext, file);
                }
            }
        }
        
        return ISymbol.EMPTY_SYMBOL_ARRAY;
    }
    
    public ISymbol getProperty(ISymbol symbol, Object propertyName) 
    {
        final IFile file = getFile();
        
        if (file != null)
        {
            final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(file.getProject());
            
            if (manager != null)
            {
                return manager.getPropertyResolver().getProperty(symbol, propertyName);
            }
        }
        
        return null;
    }

    public ISymbol[] getProperties(ISymbol symbol) 
    {
        final IFile file = getFile();
        
        if (file != null)
        {
            final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(file.getProject());
            
            if (manager != null)
            {
                return manager.getPropertyResolver().getAllProperties(symbol);
            }
        }
        return ISymbol.EMPTY_SYMBOL_ARRAY;
    }

    public IMethodSymbol getMethod(IObjectSymbol base, Object methodName)
    {
        final IFile file = getFile();
        
        if (file != null)
        {
            final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(file.getProject());
             
            if (manager != null)
            {
                return manager.getMethodResolver().getMethod(base, methodName);
            }
        }
        
        return null;
    }
    
    public ISymbol[] getMethods(IObjectSymbol base) 
    {
        final IFile file = getFile();
        
        if (file != null)
        {
            final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(file.getProject());
            
            if (manager != null)
            {
                return manager.getMethodResolver().getMethods(base);
            }
        }
        
        return new IMethodSymbol[0];
    }

    
	public boolean canResolveContext(IModelContext modelContext)
    {
		return modelContext.getAdapter(IStructuredDocumentContext.class) != null;
	}
	
    /**
     * @return a lazily loaded workspace resolver for this resolver's context
     */
    protected final IWorkspaceContextResolver  getWorkspaceResolver()
    {
        if (_wkspResolver == null)
        {
            _wkspResolver = getWorkspaceResolver(_context);
        }
        
        return _wkspResolver;
    }
    
    private IWorkspaceContextResolver getWorkspaceResolver(final IStructuredDocumentContext context)
    {
        return IStructuredDocumentContextResolverFactory.INSTANCE
                .getWorkspaceContextResolver(context);
    }

    /**
     * @return the underlying IFile for my context or null if can't be determined
     */
    protected final IFile getFile()
    {
        return getFile(getWorkspaceResolver());
    }

    private IFile getFile(final IWorkspaceContextResolver  resolver)
    {
        
        if (resolver != null)
        {
            IResource curRes = resolver.getResource();
            
            if (curRes instanceof IFile)
            {
                return (IFile) curRes;
            }
        }
        
        return null;
        
    }

    @Override
    public boolean hasSameResolution(IModelContext modelContext)
    {
        if (canResolveContext(modelContext))
        {
            return getFile().equals(getFile(getWorkspaceResolver((IStructuredDocumentContext) modelContext)));
        }

        // if we can't even resolve the context, then we certainly don't have
        // the same resolution
        return false;
    }
}
