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

package org.eclipse.jst.jsf.designtime.internal.provisional.resolver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.internal.provisional.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.designtime.internal.provisional.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.internal.provisional.context.DTFacesContext;

/**
 * A symbol context resolver
 * 
 * @author cbateman
 *
 */
/*package*/ class SymbolContextResolver implements ISymbolContextResolver 
{
    private final IStructuredDocumentContext		_context;
    private IWorkspaceContextResolver               _wkspResolver; // = null; lazy created through getWorkspaceResolver
	
	/*package*/ SymbolContextResolver(IStructuredDocumentContext context)
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
            
            final DTFacesContext facesContext = manager.getFacesContext(file);
    
            return manager.getVariableResolver().
                resolveVariable(facesContext, name, file);
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
            
            final DTFacesContext facesContext = manager.getFacesContext(file);
            
            if (facesContext != null)
            {
                return manager.getVariableResolver().
                        getAllVariables(facesContext, file);
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
            return manager.getPropertyResolver().getProperty(symbol, propertyName);
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
            return manager.getPropertyResolver().getAllProperties(symbol);
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
            return manager.getMethodResolver().getMethod(base, methodName);
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
            return manager.getMethodResolver().getMethods(base);
        }
        
        return new IMethodSymbol[0];
    }

    private IFile getFile()
    {
        final IWorkspaceContextResolver  resolver = getWorkspaceResolver();
        
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
    
	private IWorkspaceContextResolver  getWorkspaceResolver()
    {
        if (_wkspResolver == null)
        {
            _wkspResolver = IStructuredDocumentContextResolverFactory.
                                INSTANCE.getWorkspaceContextResolver(_context);
        }
        
        return _wkspResolver;
    }
    
	public boolean canResolveContext(IModelContext modelContext)
    {
		return modelContext.getAdapter(IStructuredDocumentContext.class) != null;
	}

}
