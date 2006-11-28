/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.celleditors;

import java.util.Arrays;

import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ComboDialogField;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.swt.SWT;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 */
public class ConverterValidatorIdDialogField extends ComboDialogField implements IElementContextable
{
    private String _elementId = "";

    /**
     * @param flags
     */
    public ConverterValidatorIdDialogField()
    {
        super(SWT.NONE);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable#setElementContext(org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode, org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement)
     */
    public void setElementContext(IDOMNode ancester, IDOMElement element)
    {
        String[] results = null;
        if ("validator-id".equalsIgnoreCase(_elementId))
        {
            //results = FacesUtil.getRegisteredConverterIds(project);
        	
        }
        else
        {
            // results = FacesUtil.getRegisteredValidatorIds(project);
        }
        if (results != null)
        {
            Arrays.sort(results);
            setItems(results);
        }
        else
        {
            setItems(new String[0]);
        }
    }

    public String getElementId()
    {
        return _elementId;
    }

    public void setElementId(String elementId)
    {
        this._elementId = elementId;
    }
}
