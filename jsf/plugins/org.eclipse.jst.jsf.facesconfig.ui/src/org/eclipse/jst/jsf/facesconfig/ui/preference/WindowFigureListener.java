/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.preference;

/**
 * @author Bob Brodt
 * 
 * Defines an interface for notifying listeners of page switches on a tabbed
 * window figure.
 */
/*package*/ interface WindowFigureListener {
	/**
	 * @param oldIndex
	 * @param newIndex
	 */
	void tabChanged(int oldIndex, int newIndex);
}
