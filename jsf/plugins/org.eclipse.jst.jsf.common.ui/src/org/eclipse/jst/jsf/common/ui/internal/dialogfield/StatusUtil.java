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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.DialogPage;

/**
 * copied from org.eclipse.jdt.internal.dialogs. Enhanced to support null status
 * as parameter
 * 
 * A utility class to work with IStatus.
 */
public class StatusUtil {

	/**
	 * Compares two instances of <code>IStatus</code>. The more severe is
	 * returned: An error is more severe than a warning, and a warning is more
	 * severe than ok. If the two stati have the same severity, the second is
	 * returned.
	 * @param s1 
	 * @param s2 
	 * 
	 * @return Could be null.
	 */
	public static IStatus getMoreSevere(IStatus s1, IStatus s2) {
		if (s1 == null && s2 == null) {
			return null;
		} else if (s1 == null) {
			return s2;
		} else if (s2 == null) {
			return s1;
		} else if (s1.getSeverity() > s2.getSeverity()) {
			return s1;
		} else {
			return s2;
		}
	}

	/**
	 * Finds the most severe status from a array of stati. An error is more
	 * severe than a warning, and a warning is more severe than ok.
	 * @param status 
	 * 
	 * @return could be null
	 */
	public static IStatus getMostSevere(IStatus[] status) {
		if (status == null)
			return null;
		IStatus max = null;
		for (int i = 0; i < status.length; i++) {
			IStatus curr = status[i];
			if (curr == null)
				continue;

			if (curr.matches(IStatus.ERROR)) {
				return curr;
			}
			if (max == null || curr.getSeverity() > max.getSeverity()) {
				max = curr;
			}
		}
		if (max != null)
        {
			return max;
        }

        return null;
	}

	/**
	 * Applies the status to the status line of a dialog page.
	 * @param page 
	 * @param status 
	 */
	public static void applyToStatusLine(DialogPage page, IStatus status) {
		if (status == null) {
			page.setMessage(null);
			page.setErrorMessage(null);
		} else {
			String message = status.getMessage();
			switch (status.getSeverity()) {
			case IStatus.OK:
				page.setMessage(message, DialogPage.NONE);
				page.setErrorMessage(null);
				break;
			case IStatus.WARNING:
				page.setMessage(message, DialogPage.WARNING);
				page.setErrorMessage(null);
				break;
			case IStatus.INFO:
				page.setMessage(message, DialogPage.INFORMATION);
				page.setErrorMessage(null);
				break;
			default:
				if (message.length() == 0) {
					message = null;
				}
				page.setMessage(null);
				page.setErrorMessage(message);
				break;
			}
		}
	}
}
