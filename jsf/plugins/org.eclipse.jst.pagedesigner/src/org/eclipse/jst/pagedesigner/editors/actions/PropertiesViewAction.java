/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.ui.IPageLayout;

/**
 * @author mengbo
 */
public class PropertiesViewAction extends ShowViewAction {
	/**
	 * the id
	 */
	public final static String ID = "org.eclipse.jst.pagedesigner.editors.actions.PropertiesViewAction"; //$NON-NLS-1$

	/**
	 * Default constructor
	 */
	public PropertiesViewAction() 
    {
		super(ActionsMessages
				.getString("PropertiesViewAction.Menu.Properties") //$NON-NLS-1$
              , IPageLayout.ID_PROP_SHEET);
	}
}
