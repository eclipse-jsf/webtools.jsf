/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.ui.IPageLayout;

/**
 * A show view action that shows the outline view
 *
 */
public class OutlineViewAction extends ShowViewAction 
{
    /**
     * the action id
     */
    public final static String ID = "org.eclipse.jst.pagedesigner.editors.actions.OutlineViewAction"; //$NON-NLS-1$

    /**
     * Default constructor
     */
    public OutlineViewAction() 
    {
        super(ActionsMessages
                .getString("OutlineViewAction.Menu.OutlineView") //$NON-NLS-1$
              , IPageLayout.ID_OUTLINE);
    }
}
