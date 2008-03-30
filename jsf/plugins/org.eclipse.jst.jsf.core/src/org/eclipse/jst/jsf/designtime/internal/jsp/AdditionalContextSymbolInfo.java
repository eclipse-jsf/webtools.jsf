/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
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

import org.eclipse.jst.jsf.context.symbol.source.IAdditionalContextSymbolInfo;

/**
 * 
 * NOT API.  Use IAdditionalContextSymbolInfo.
 * 
 * @author cbateman
 *
 */
/*package*/ final class AdditionalContextSymbolInfo implements IAdditionalContextSymbolInfo
{
    private final String    _symbolTypeSignature;
    private final String    _valueExpressionAttributeName;

    /**
     * @param symbolTypeSignature
     */
    /*package*/ AdditionalContextSymbolInfo(final String symbolTypeSignature,
            String valueExpressionAttributeName)
    {
        super();
        this._symbolTypeSignature = symbolTypeSignature;
        this._valueExpressionAttributeName = valueExpressionAttributeName;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.context.symbol.source.IAdditionalContextSymbolInfo#getSymbolTypeSignature()
     */
    public String getSymbolTypeSignature()
    {
        return _symbolTypeSignature;
    }

    public String getValueExpressionAttributeName()
    {
        return _valueExpressionAttributeName;
    }
}
