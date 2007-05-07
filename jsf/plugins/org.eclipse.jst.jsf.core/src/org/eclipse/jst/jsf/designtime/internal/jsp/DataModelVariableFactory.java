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
package org.eclipse.jst.jsf.designtime.internal.jsp;

import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.designtime.symbols.AbstractDataModelVariableFactory;
import org.eclipse.jst.jsf.designtime.symbols.DefaultDataTableSymbolFactory;
import org.w3c.dom.Element;

/**
 * Constructs an EL IObjectSymbol for a particular data table row access variable
 * based on the type of the bound data (the value attribute)
 *  
 * 
 * @author cbateman
 *
 */
class DataModelVariableFactory extends DefaultDataTableSymbolFactory
{
    private static DataModelVariableFactory INSTANCE;
    
    /**
     * @return an instance of the factory
     */
    public synchronized static DataModelVariableFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DataModelVariableFactory();
        }
        
        return INSTANCE;
    }
    
    @Override
    public ISymbol createSymbolForDataTableValue(String symbolName,
            Element dataTableElement, IStructuredDocumentContext context) 
    {
        if (!IJSFConstants.TAG_DATATABLE.equals(dataTableElement.getLocalName()))
        {
            throw new AssertionError("dataTableElement must be a dataTable"); //$NON-NLS-1$
        }

        return super.createSymbolForDataTableValue(symbolName, dataTableElement, context);
    }

    //private String getELText(IStructuredDocumentConte)
    private DataModelVariableFactory()
    {
        // no external instantiation
        super(new MyDataModelVariableFactory());
    }
    
    private static class MyDataModelVariableFactory extends AbstractDataModelVariableFactory
    {
        @Override
        protected String getVariableSourceName() {
            return "dataTable"; //$NON-NLS-1$
        }
    }
}
