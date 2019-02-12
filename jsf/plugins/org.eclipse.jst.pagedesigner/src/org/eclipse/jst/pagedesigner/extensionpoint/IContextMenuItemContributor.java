/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.extensionpoint;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

/**
 * @author mengbo
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */

public interface IContextMenuItemContributor {
	/**
	 * @param uri
	 */
	void setURI(String uri);

	/**
	 * @return the uri
	 */
	String getURI();

	/**
	 * @param manager
	 * @param selection
	 * @param model
	 * @param parentUI
	 */
	void fillContextMenu(IMenuManager manager, ISelection selection,
			IStructuredModel model, Control parentUI);
}
