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

package org.eclipse.jst.jsf.designtime.context;

import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolSourceProvider;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolSourceProviderFactory;


/**
 * A designtime proxy for the Faces ExternalContext
 * 
 * Clients may sub-class.
 * 
 * @author cbateman
 *
 */
public class DTJSPExternalContext implements IDTExternalContext 
{
    /**
     * 
     */
    private final IFile     _jspFile;
    
    /**
     * Construct a default external context
     * 
     * @param jspFile
     */
    DTJSPExternalContext(IAdaptable  jspFile)
    {
        if (jspFile instanceof IFile)
        {
            _jspFile = (IFile) jspFile;
        }
        else if (jspFile != null)
        {
            IFile file = (IFile) jspFile.getAdapter(IFile.class);
            
            if (file != null)
            {
                _jspFile = file;
            }
            else
            {
                _jspFile = null;
                throw new AssertionError("jspFile must be adapable to an IFile");
            }
        }
        else
        {
            _jspFile = null;
            throw new AssertionError("jspFile must be adapable to an IFile");
        }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getMapForScope(int)
     */
    public Map getMapForScope(int scopeMask)
    {
        final Map  map = new HashMap();
        
        for (final Iterator it = JSFCommonPlugin.getSymbolSourceProviders().iterator(); it.hasNext();)
        {
            final ISymbolSourceProviderFactory  factory = (ISymbolSourceProviderFactory) it.next();
            final ISymbolSourceProvider provider = factory.createInstance(_jspFile.getProject());
            
            final ISymbol[] symbols = provider.getSymbols(_jspFile, scopeMask);
            
            for (int i = 0; i < symbols.length; i++)
            {
                map.put(symbols[i].getName(), symbols[i]);
            }
        }
        
        return Collections.unmodifiableMap(map);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getRequestMap()
     */
    public Map getRequestMap()
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getSessionMap()
     */
    public Map getSessionMap()
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_SESSION);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getApplicationMap()
     */
    public Map getApplicationMap()
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_APPLICATION);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getNoneMap()
     */
    public Map getNoneMap() 
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_NONE);
    }
    
    /**
     * @param stream
     */
    public void trace(PrintStream stream)
    {
        String[]  scopeNames = {ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING, 
                                ISymbolConstants.SYMBOL_SCOPE_SESSION_STRING, 
                                ISymbolConstants.SYMBOL_SCOPE_APPLICATION_STRING,
                                ISymbolConstants.SYMBOL_SCOPE_NONE_STRING
                                };
        Map[]     symbolMaps = {getRequestMap(), getSessionMap(), getApplicationMap()};

        for (int i = 0; i < scopeNames.length; i++)
        {
            stream.println("--------------");
            stream.println(scopeNames[i]+" Scope:");
            stream.println("--------------");

            for (final Iterator it = symbolMaps[i].values().iterator(); it.hasNext();)
            {
                ISymbol symbol = (ISymbol) it.next();
                System.out.println(symbol.getName());
            }
        }
    }
}
