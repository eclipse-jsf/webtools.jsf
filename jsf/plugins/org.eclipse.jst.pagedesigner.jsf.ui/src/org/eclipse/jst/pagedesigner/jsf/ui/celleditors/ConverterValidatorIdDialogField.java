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

import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ComboDialogField;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.swt.SWT;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated
 */
public class ConverterValidatorIdDialogField extends ComboDialogField implements IElementContextable
{
    private String _elementId = ""; //$NON-NLS-1$

    /**
     * The default constructor
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
        setItems(new String[0]);
    }

    /**
     * @return the element id
     */
    public String getElementId()
    {
        return _elementId;
    }

    /**
     * Set the element id
     * 
     * @param elementId
     */
    public void setElementId(String elementId)
    {
        this._elementId = elementId;
    }
}
