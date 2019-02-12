/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
     * @param converterId 
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
        attributes.put("converterId", _converterId); //$NON-NLS-1$
        AddJSFCoreChildCommand command = new AddJSFCoreChildCommand(this.getParentElement(),
            IJSFConstants.TAG_CONVERTER, attributes);
        command.execute();
    }
}
