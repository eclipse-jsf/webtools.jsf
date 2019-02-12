/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
