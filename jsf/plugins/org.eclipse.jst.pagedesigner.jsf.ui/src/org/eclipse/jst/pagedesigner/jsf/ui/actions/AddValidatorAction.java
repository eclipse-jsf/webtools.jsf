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
public class AddValidatorAction extends JSFAddChildAction
{
    String	_validatorId;

    /**
     * @param validatorId
     * @param parentNode
     */
    public AddValidatorAction(String validatorId, IDOMElement parentNode)
    {
        super(validatorId, parentNode);
        this._validatorId = validatorId;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        Map attributes = new HashMap();
        attributes.put("validatorId", _validatorId); //$NON-NLS-1$
        AddJSFCoreChildCommand command = new AddJSFCoreChildCommand(this.getParentElement(),
            IJSFConstants.TAG_VALIDATOR, attributes);
        command.execute();
    }
}
