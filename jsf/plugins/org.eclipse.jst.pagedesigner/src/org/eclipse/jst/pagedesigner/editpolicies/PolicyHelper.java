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
package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class PolicyHelper {
	private static Logger _log = PDPlugin.getLogger(PolicyHelper.class);

	/**
	 * @param part
	 * @return the current viewer's status line manager or null
	 */
	public static IStatusLineManager getStatusLineManager(EditPart part) {
		EditPartViewer v = part.getViewer();
		if (v instanceof IHTMLGraphicalViewer) {
			IHTMLGraphicalViewer htmlviewer = (IHTMLGraphicalViewer) v;
			IStatusLineManager m = htmlviewer.getStatusLineManager();
			if (m == null) {
				_log.info("Warn.PolicyHelper.0", (String) null); //$NON-NLS-1$
			}
			return m;
		}
		_log.info("Warn.PolicyHelper.1", (String)null); //$NON-NLS-1$
		return null;
	}
}
