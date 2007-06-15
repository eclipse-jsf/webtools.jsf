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

package org.eclipse.jst.jsf.context.symbol.source;

/**
 * Defines global constants for symbols
 * 
 * <p><b>Provisional API - subject to change</b></p> 
 * 
 * @author cbateman
 *
 */
public class ISymbolConstants {
    /**
     * Mask to indicate all request scope symbols for a particular context
     */
    public final static int  SYMBOL_SCOPE_REQUEST = 1;
    
    /**
     * Mask to indicate all session scope symbols for a particular context
     */
    public final static int  SYMBOL_SCOPE_SESSION = 2;
    
    /**
     * Mask to indicate all application scope symbols for a particular context
     */
    public final static int  SYMBOL_SCOPE_APPLICATION = 4;
    
    /**
     * Mask to indicate all 'none' scope symbols for a particular context
     */
    public final static int  SYMBOL_SCOPE_NONE = 8;

    /**
     * All scopes
     */
    public final static int  SYMBOL_SCOPE_ALL = 
                                                SYMBOL_SCOPE_REQUEST 
                                                | SYMBOL_SCOPE_SESSION 
                                                | SYMBOL_SCOPE_APPLICATION 
                                                | SYMBOL_SCOPE_NONE;

    /**
     * the string representation for request scope
     */
    public final static String SYMBOL_SCOPE_REQUEST_STRING = "request"; //$NON-NLS-1$
    
    
    /**
     * the string representation for session scope
     */
    public final static String SYMBOL_SCOPE_SESSION_STRING = "session"; //$NON-NLS-1$

    /**
     * the string representation for application scope
     */
    public final static String SYMBOL_SCOPE_APPLICATION_STRING = "application"; //$NON-NLS-1$

    /**
     * the string representation for none scope
     */
    public final static String  SYMBOL_SCOPE_NONE_STRING = "none"; //$NON-NLS-1$

    /**
     * @param scope
     * @return true iff scope matches exactly one scope value (excluding ALL)
     */
    public static boolean isValid(int scope)
    {
        return scope  == SYMBOL_SCOPE_APPLICATION
                   || scope == SYMBOL_SCOPE_REQUEST
                   || scope == SYMBOL_SCOPE_SESSION
                   || scope == SYMBOL_SCOPE_NONE;
    }
    
    /**
     * @param scopeMask
     * @return the string representation for a scope mask or null if either
     * the mask contains more than one scope or the scope is not valid
     */
    public static String getStringForMask(final int scopeMask)
    {
        if (scopeMask == SYMBOL_SCOPE_REQUEST)
        {
            return SYMBOL_SCOPE_REQUEST_STRING;
        }
        else if (scopeMask == SYMBOL_SCOPE_SESSION)
        {
            return SYMBOL_SCOPE_SESSION_STRING;
        }
        else if (scopeMask == SYMBOL_SCOPE_APPLICATION)
        {
            return SYMBOL_SCOPE_APPLICATION_STRING;
        }
        else if (scopeMask == SYMBOL_SCOPE_NONE)
        {
            return SYMBOL_SCOPE_NONE_STRING;
        }
        
        return null;
    }
    
    /**
     * @param scopeString
     * @return the scope mask for the string or -1 if the string is not
     * one of SYMBOL_SCOPE_*_STRING
     */
    public static int getMaskForString(final String scopeString)
    {
        if (SYMBOL_SCOPE_REQUEST_STRING.equals(scopeString))
        {
            return SYMBOL_SCOPE_REQUEST;
        }
        else if (SYMBOL_SCOPE_SESSION_STRING.equals(scopeString))
        {
            return SYMBOL_SCOPE_SESSION;
        }
        else if (SYMBOL_SCOPE_APPLICATION_STRING.equals(scopeString))
        {
            return SYMBOL_SCOPE_APPLICATION;
        }
        else if (SYMBOL_SCOPE_NONE_STRING.equals(scopeString))
        {
            return SYMBOL_SCOPE_NONE;
        }
        
        return -1;
    }
}
