/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.symbols;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.designtime.symbols.AbstractDataModelVariableFactory;

/**
 * Used to expose protected final members of AbstractDataModelVariableFactory
 * for testing purposes.  May change without notice, should *never* be used in
 * production code.
 * 
 * @author cbateman
 *
 */
public final class DataModelVariableTestFacade extends AbstractDataModelVariableFactory 
{
    /**
     * Test facade for createArraySymbol
     * 
     * @param symbolName
     * @param signature
     * @param javaProject
     * @return super.createArraySymbol()
     */
    public final ISymbol testCreateArraySymbol(final String symbolName, final String signature, final IJavaProject javaProject)
    {
        return getSymbolFactory().createArraySymbol(symbolName, signature, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, javaProject);
    }
    
    /**
     * Test facade for createFromList
     * 
     * @param symbolName
     * @param valueType
     * @param javaProject
     * @return super.createFromList()
     */
    public final ISymbol testCreateFromList(String symbolName, ValueType valueType, IJavaProject javaProject)
    {
        return getSymbolFactory().createFromList(symbolName, valueType, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, null, javaProject);
    }
    
    /**
     * @param symbolName
     * @param signature
     * @param javaProject
     * @return a symbol assuming a scalar type wrapping
     */
    public ISymbol testCreateScalarSymbol(String symbolName, String signature, IJavaProject javaProject)
    {
        return getSymbolFactory().createScalarSymbol(symbolName, signature, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, javaProject);
    }

    
    /**
     * @param symbolName
     * @param valueType
     * @param javaProject
     * @return a symbol from a proxied call to  super.createFromType
     */
    public ISymbol testCreateFromType(String symbolName, ValueType valueType,
            IJavaProject javaProject) {
        return super.createFromType(symbolName, valueType, javaProject);
    }

    @Override
    protected final String getVariableSourceName() 
    {
        return "testFacade -- SHOULD NOT BE USED IN PRODUCTION CODE"; //$NON-NLS-1$
    }
}
