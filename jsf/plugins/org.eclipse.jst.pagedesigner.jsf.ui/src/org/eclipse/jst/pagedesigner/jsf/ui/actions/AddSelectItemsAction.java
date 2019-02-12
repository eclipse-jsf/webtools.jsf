/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.properties.attrgroup.DialogUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;


/**
 * @author mengbo
 * @version 1.5
 */
public class AddSelectItemsAction extends JSFAddChildAction
{

    /**
     * @param parentNode
     */
    public AddSelectItemsAction(IDOMElement parentNode)
    {
        super(ActionsResources.getString("AddSelectItemsAction.ActionLabel.SelectItems"), parentNode); //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    { 
//        AddJSFCoreChildCommand command = new AddJSFCoreChildCommand(this.getParentElement(),
//            IJSFConstants.TAG_SELECTITEMS, attributes);
//        command.execute();
        DialogUtil.createSubElement(getShell(), getParentElement(), ITLDConstants.URI_JSF_CORE,
                IJSFConstants.TAG_SELECTITEMS, new String[] { IJSFConstants.ATTR_ID, IJSFConstants.ATTR_BINDING, IJSFConstants.ATTR_VALUE});

    }
}
