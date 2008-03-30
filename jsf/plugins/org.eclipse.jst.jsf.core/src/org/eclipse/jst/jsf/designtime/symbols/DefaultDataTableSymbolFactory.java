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
package org.eclipse.jst.jsf.designtime.symbols;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.w3c.dom.Element;

/**
 * The default factory for creating DataModel symbols based on an value
 * expression in a dataTable.
 * 
 * Clients may sub-class.
 * 
 * @author cbateman
 * 
 */
public class DefaultDataTableSymbolFactory
{
    private final AbstractDataModelVariableFactory _dataModelSymbolFactory;

    /**
     * @param dataModelSymbolFactory
     */
    public DefaultDataTableSymbolFactory(
            final AbstractDataModelVariableFactory dataModelSymbolFactory)
    {
        assert dataModelSymbolFactory != null;
        _dataModelSymbolFactory = dataModelSymbolFactory;
    }

    /**
     * @param symbolName
     *            the name of the symbol to be created. Must not be null.
     * @param dataTableElement
     *            the DOM element representing the dataTable tag.
     * @param context
     *            the document context pointing to dataTableElement
     * @return a symbol named symbolName based on the variable declared by
     *         dataTableElement
     */
    public ISymbol createSymbolForDataTableValue(final String symbolName,
            final Element dataTableElement,
            final IStructuredDocumentContext context)
    {
        final String elText = AbstractDataModelVariableFactory
        .getELText(dataTableElement);
        final IFile file = FileContextUtil.deriveIFileFromContext(context);

        if (elText != null && file != null)
        {
            final IStructuredDocumentContext elContext = IStructuredDocumentContextFactory.INSTANCE
                .getContext(context.getStructuredDocument(),
                    dataTableElement.getAttributeNode("value")); //$NON-NLS-1$

            final IJavaProject javaProject = JavaCore.create(file.getProject());

            final ValueType valueExpr = new JSFSymbolFactory()
                    .getValueTypeFromEL(elText, elContext, file);

            if (valueExpr != null)
            {
                // otherwise, we have to try to resolve the base type and see
                // if it's an instanceof any of the supported implicit or
                // explict types
                return _dataModelSymbolFactory.createFromType(symbolName,
                        valueExpr, javaProject);
            }
        }

        // by default create a default
        return _dataModelSymbolFactory.getSymbolFactory().createDefaultSymbol(
                symbolName, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL,
                null);
    }

}
