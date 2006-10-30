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
package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.common.logging.Logger;
import org.eclipse.jst.pagedesigner.viewer.HTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class PolicyHelper {
	private static Logger _log = PDPlugin.getLogger(PolicyHelper.class);

	public static IStatusLineManager getStatusLineManager(EditPart part) {
		EditPartViewer v = part.getViewer();
		if (v instanceof HTMLGraphicalViewer) {
			HTMLGraphicalViewer htmlviewer = (HTMLGraphicalViewer) v;
			IStatusLineManager m = htmlviewer.getStatusLineManager();
			if (m == null) {
				_log.info("Warn.PolicyHelper.0", null); //$NON-NLS-1$
			}
			return m;
		}
		_log.info("Warn.PolicyHelper.1", null); //$NON-NLS-1$
		return null;
	}
}
