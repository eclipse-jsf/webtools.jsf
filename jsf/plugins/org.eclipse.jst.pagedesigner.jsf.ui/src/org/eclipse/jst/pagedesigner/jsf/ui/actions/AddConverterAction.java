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
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.AddJSFCoreChildCommand;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;


/**
 * @author mengbo
 * @version 1.5
 */
public class AddConverterAction extends JSFAddChildAction
{
    String	_converterId;

    /**
     * @param text
     * @param parentNode
     */
    public AddConverterAction(String converterId, IDOMElement parentNode)
    {
        super(converterId, parentNode);
        this._converterId = converterId;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        Map attributes = new HashMap();
        attributes.put("converterId", _converterId);
        AddJSFCoreChildCommand command = new AddJSFCoreChildCommand(this.getParentElement(),
            IJSFConstants.TAG_CONVERTER, attributes);
        command.execute();
    }
}
