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

package org.eclipse.jst.jsf.validation.internal.el;


class ValueExpressionTracker
{
    private int                         _curPropertySymbolOffset;
    private int                         _curPropertySymbolLength;

    /**
     * @param offset 
     * @param length 
     */
    public void setCurMemberSymbol(int offset, int length) 
    {
        _curPropertySymbolOffset = offset;
        _curPropertySymbolLength = length;
    }
    
    /**
     * @return the offset of the current property symbol or 0 if no current property symbol
     */
    public int getCurPropertySymbolOffset()
    {
        return _curPropertySymbolOffset;
    }
    
    /**
     * @return the length of the current property symbol or 0 if no current property symbol
     */
    public int getCurPropertySymbolLength()
    {
        return _curPropertySymbolLength;
    }
}
