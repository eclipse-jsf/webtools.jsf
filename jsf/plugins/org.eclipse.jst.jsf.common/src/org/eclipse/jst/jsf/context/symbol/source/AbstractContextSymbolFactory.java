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

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Must be sub-classed by all contextSymbolFactory extension point implementors
 * to create context configured symbols
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public abstract class AbstractContextSymbolFactory
{
    /**
     * @param symbolName -- the symbol name
     * @param scope -- the scope of the symbol
     * @param context -- the context; must be supported (call supports(context))
     * @param problems -- populated with problems found during symbol construction.
     * @return a new ISymbol configured for the name, scope and context or null
     * if the arguments are invalid but some other reason a symbol cannot be created.
     * @throws IllegalArgumentException if this method is called with context
     * for which supports(context) == false or if scope does not conform
     * to exactly one of the ISymbolConstants.SYMBOL_SCOPE_* constants
     * @deprecated Use the new create method instead.
     */
    @Deprecated
    public final ISymbol  create(final String symbolName, final int scope, final IAdaptable context, final List problems)
    {
        if (!supports(context))
        {
            throw new IllegalArgumentException("Unsupported context"); //$NON-NLS-1$
        }
        else if (!ISymbolConstants.isValid(scope))
        {
            throw new IllegalArgumentException("Unsupported symbol constant:"+scope); //$NON-NLS-1$
        }

        try
        {
            return internalCreate(symbolName, scope, context, problems, null);
        }
        catch (final Exception e)
        {
            JSFCommonPlugin.log(e, "During execution of context symbol factory: "+this.getClass().getName()); //$NON-NLS-1$
            return null;
        }
    }

    /**
     * The same as create(symbolName, scope, context, problems), except it calls
     * the internalCreate with additionalInfo.  By default this has the same
     * effect.
     * 
     * @param symbolName
     * @param scope
     * @param context
     * @param problems
     * @param additionalInfo
     * @return the new Symbol
     */
    public final ISymbol  create(final String symbolName, final int scope, final IAdaptable context, final List problems, final IAdditionalContextSymbolInfo additionalInfo)
    {
        if (!supports(context))
        {
            throw new IllegalArgumentException("Unsupported context"); //$NON-NLS-1$
        }
        else if (!ISymbolConstants.isValid(scope))
        {
            throw new IllegalArgumentException("Unsupported symbol constant:"+scope); //$NON-NLS-1$
        }

        try
        {
            return internalCreate(symbolName, scope, context, problems, additionalInfo);
        }
        catch (final Exception e)
        {
            JSFCommonPlugin.log(e, "During execution of context symbol factory: "+this.getClass().getName()); //$NON-NLS-1$
            return null;
        }
    }


    /**
     * Use of this method is DISCOURAGED.  It is preferable to move functionality
     * to the new internalCreate method and have this one call it with null.
     * 
     * This method will eventually be deprecated and removed
     * 
     * @param symbolName
     * @param scope
     * @param context
     * @param problems -- see problems arg on create
     * @return a new ISymbol for the name, scope and context; may return null
     * @deprecated Use the new internalCreate instead.  Ignore the new
     * additionalInfo parameter if you do not need it.
     */
    @Deprecated
    protected ISymbol internalCreate(final String symbolName, final int scope, final IAdaptable context, final List problems)
    {
        // do nothing by default
        return null;
    }

    /**
     * NOTE: when migrating to this method, ensure that the other internalCreate
     * method in your implementation doesn't create inconsistent behaviour.
     * 
     * @param symbolName
     * @param scope
     * @param context Will conform to restrictions you set out in supports().
     * @param problems CURRENTLY IGNORED.
     * @param additionalInfo May be null
     * @since 3.0
     * @return a new ISymbol for the name, scope and context, but with the option
     * to also consult additionalInfo passed by the framework.  may return null.
     */
    protected ISymbol internalCreate(final String symbolName, final int scope, final IAdaptable context, final List problems, final IAdditionalContextSymbolInfo additionalInfo)
    {
        // by default, call the other internalCreate for backward compatibility.
        // implementers can override to do things with the additional info
        return internalCreate(symbolName, scope, context, problems);
    }

    /**
     * @param context
     * @return true if this factory supports the context
     */
    public abstract boolean  supports(IAdaptable context);
}
