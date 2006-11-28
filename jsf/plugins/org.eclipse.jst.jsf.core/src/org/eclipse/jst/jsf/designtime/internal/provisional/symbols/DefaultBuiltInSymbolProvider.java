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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.ISymbolConstants;
import org.eclipse.jst.jsf.designtime.internal.provisional.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.internal.provisional.context.IDTExternalContext;

/**
 * Provides the default built-in JSF symbols
 * 
 * Clients may sub-class
 * 
 * @author cbateman
 *
 */
public class DefaultBuiltInSymbolProvider 
{
    private static DefaultBuiltInSymbolProvider  INSTANCE;
    
    /**
     * @return the singleton instance
     */
    public synchronized static DefaultBuiltInSymbolProvider getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DefaultBuiltInSymbolProvider();
        }
        
        return INSTANCE;
    }
    
    
    private static final String APPLICATION_SCOPE = "applicationScope";
    private static final String SESSION_SCOPE = "sessionScope";
    private static final String REQUEST_SCOPE = "requestScope";
    private static final String COOKIE_IMPLICIT_OBJ = "cookie";
    private static final String FACES_CONTEXT_IMPLICIT_OBJ = "facesContext";
    private static final String HEADER_IMPLICIT_OBJ = "header";
    private static final String HEADER_VALUES_IMPLICIT_OBJ = "headerValues";
    private static final String INIT_PARAM_IMPLICIT_OBJ = "initParam";
    private static final String PARAM_IMPLICIT_OBJ = "param";
    private static final String PARAM_VALUES_IMPLICIT_OBJ = "paramValues";
    private static final String VIEW_IMPLICIT_OBJ = "view";

    private static final String FACES_CONTEXT_FULLY_QUALIFIED_CLASS =
        "javax.faces.context.FacesContext";
    private static final String VIEW_FULLY_QUALIFIED_CLASS =
        "javax.faces.component.UIViewRoot";
    
    /**
     * Now direct instantiation -- use getInstance
     * 
     * Made protected to allow sub-classing
     */
    protected DefaultBuiltInSymbolProvider() {/* empty; do nothing */}
    
    /**
     * @param context
     * @param symbolScopeMask
     * @return all symbols for context in scopes matching symbolScopeMask
     */
    public ISymbol[] getSymbols(IAdaptable context, int symbolScopeMask) 
    {
        final IFile   fileContext = FileContextUtil.deriveIFileFromContext(context);
        return (ISymbol[]) getSymbolsForScope(fileContext, symbolScopeMask).toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }

    /**
     * @param name
     * @param context
     * @param symbolScopeMask 
     * @return the symbol in context matching name or null if not found
     */
    public ISymbol getSymbol(String name, IAdaptable context, int symbolScopeMask)
    {
        return SymbolUtil.
                    findSymbolByName(getSymbols(context, symbolScopeMask), name);
    }
    
    /**
     * @param prefix
     * @param context
     * @param symbolScopeMask
     * @return all implicit symbols for context starting with prefix in
     * scopes matching symbolScopeMask
     */
    public ISymbol[] getSymbols(String prefix, IAdaptable context, int symbolScopeMask) 
    {
        return SymbolUtil.
            filterSymbolsByPrefix(getSymbols(context, symbolScopeMask), prefix);
    }

    private List getSymbolsForScope(final IFile file, final int symbolScopeMask)
    {
        List symbols = new ArrayList();
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_REQUEST) != 0)
        {
            symbols.addAll(getRequestScopeSymbols(file));
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_SESSION) != 0)
        {
            symbols.addAll(getSessionScopeSymbols(file));
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_APPLICATION) != 0)
        {
            symbols.addAll(getApplicationScopeSymbols(file));
        }

        return symbols;
    }
    
    private List getRequestScopeSymbols(final IFile file)
    {
        List symbols = new ArrayList();
        
        symbols.add(createScopeSymbol(file, ISymbolConstants.SYMBOL_SCOPE_REQUEST, REQUEST_SCOPE));
        symbols.add(createUnresolvedMapSymbol(COOKIE_IMPLICIT_OBJ));
        symbols.add(createUnresolvedMapSymbol(HEADER_IMPLICIT_OBJ));
        symbols.add(createUnresolvedMapSymbol(HEADER_VALUES_IMPLICIT_OBJ));
        symbols.add(createUnresolvedMapSymbol(PARAM_IMPLICIT_OBJ));
        symbols.add(createUnresolvedMapSymbol(PARAM_VALUES_IMPLICIT_OBJ));
        // TODO: these aren't maps; need to find way to handle
        symbols.add(createJavaInstanceSymbol(file.getProject(), FACES_CONTEXT_FULLY_QUALIFIED_CLASS, FACES_CONTEXT_IMPLICIT_OBJ));
        symbols.add(createJavaInstanceSymbol(file.getProject(), VIEW_FULLY_QUALIFIED_CLASS, VIEW_IMPLICIT_OBJ));

        return symbols;
    }
    
    private List getSessionScopeSymbols(final IFile file)
    {
        return Collections.
            singletonList
                (createScopeSymbol
                        (file, ISymbolConstants.SYMBOL_SCOPE_SESSION, SESSION_SCOPE));
    }
    
    private List getApplicationScopeSymbols(final IFile file)
    {
        List symbols = new ArrayList();

        // TODO: may be able to resolve this one based on web.xml
        symbols.add(createUnresolvedMapSymbol(INIT_PARAM_IMPLICIT_OBJ));
        symbols.add(createScopeSymbol
                        (file, ISymbolConstants.SYMBOL_SCOPE_APPLICATION, APPLICATION_SCOPE));
        
        return symbols;
    }
    
    private ISymbol createScopeSymbol(final IFile file, final int scopeMask, final String name)
    {
        final Map  mapSource = new ScopeMap(file, 
                scopeMask);
        final IMapTypeDescriptor typeDesc = 
            SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
        typeDesc.setMapSource(mapSource);
        typeDesc.setImmutable(false);  // scope maps are mutable
        final IInstanceSymbol symbol = 
            SymbolFactory.eINSTANCE.createIInstanceSymbol();
        symbol.setName(name);
        symbol.setRuntimeSource(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        symbol.setTypeDescriptor(typeDesc);
        // TODO:symbol.setDetailedDescription("A Map of the application scope attribute values, keyed by attribute name");
        
        return symbol;
    }

    // TODO: this one may be able to be factored up to the symbols plugin
    private ISymbol createUnresolvedMapSymbol(final String name)
    {
        final IMapTypeDescriptor typeDesc = 
            SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
        typeDesc.setMapSource(Collections.EMPTY_MAP);
        final IInstanceSymbol symbol =
            SymbolFactory.eINSTANCE.createIInstanceSymbol();
        symbol.setName(name);
        symbol.setRuntimeSource(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        symbol.setTypeDescriptor(typeDesc);

        return symbol;
    }
    
    private ISymbol createJavaInstanceSymbol(IProject project,
                                             String fullyQualifiedClass,
                                             String symbolName)
    {
        final IJavaProject javaProject = JavaCore.create(project);
        try
        {
            final IType type =
                javaProject.findType(fullyQualifiedClass);
            
            // TODO: this is a high-bred since it consists of a java instance
            // but also has properties we can populate at designtime such as
            // the maps.  Need to add the second part
            if (type != null)
            {
                final IJavaTypeDescriptor2 typeDesc =
                    SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
                typeDesc.setType(type);
                IBeanInstanceSymbol  facesContextVar =
                    SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
                facesContextVar.setTypeDescriptor(typeDesc);
                facesContextVar.setName(symbolName);
                return facesContextVar;
            }
        }
        catch(JavaModelException jme)
        {
            // fall-through and fail with unresolved map
        }
        
        ISymbol symbol = createUnresolvedMapSymbol(symbolName);
        ((IInstanceSymbol)symbol).getTypeDescriptor().
                  setTypeSignatureDelegate(Signature.createTypeSignature(fullyQualifiedClass, true));
        
        return symbol;
    }
    
    private static class ScopeMap extends AbstractMap
    {
        private final IFile              _externalContextKey;
        private final int                _scopeMask;
        
        ScopeMap(   IFile   externalContextKey,
                    int scopeMask)
        {
            _externalContextKey = externalContextKey;
            _scopeMask = scopeMask;
        }
        
        public Set entrySet() 
        {
            final Map  scopeMap = new HashMap(); 
            final DesignTimeApplicationManager  manager =
                DesignTimeApplicationManager.
                    getInstance(_externalContextKey.getProject());

            // do beans first so in case of name collision, beans are hidden
            final DefaultBeanSymbolSourceProvider  beanProvider = 
                DefaultBeanSymbolSourceProvider.getInstance();

            final ISymbol beanSymbols[] = 
                beanProvider.getSymbols(_externalContextKey, _scopeMask);
            
            for (int i = 0; i < beanSymbols.length; i++)
            {
                scopeMap.put(beanSymbols[i].getName(), beanSymbols[i]);
            }

            final IDTExternalContext  externalContext =
                manager.getFacesContext(_externalContextKey).
                    getDTExternalContext(_externalContextKey);

            scopeMap.putAll(externalContext.getMapForScope(_scopeMask));
            
            return scopeMap.entrySet();
        }
    }
}
