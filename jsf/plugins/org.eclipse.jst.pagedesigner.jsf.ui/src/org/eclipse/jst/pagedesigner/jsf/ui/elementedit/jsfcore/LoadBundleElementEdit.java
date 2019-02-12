/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfcore;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class LoadBundleElementEdit extends AbstractElementEdit
{
    private LoadBundleAction loadBundleAction;

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager, org.w3c.dom.Element)
     */
    public void fillContextMenu(IMenuManager contextMenu, Element ele)
    {
        super.fillContextMenu(contextMenu,ele);

        LoadBundleAction action = getAction();
        action.setBaseName(ele.getAttribute(ICSSPropertyID.ATTR_BASENAME));
        if (ele instanceof IDOMElement)
        {
            action.setProject(StructuredModelUtil.getProjectFor(((IDOMElement) ele).getModel()));
        }
        contextMenu.appendToGroup(PageDesignerActionConstants.GROUP_SPECIAL, action);
    }

    private LoadBundleAction getAction()
    {
        if (loadBundleAction == null)
        {
            loadBundleAction = new LoadBundleAction();
        }
        return loadBundleAction;
    }
}
