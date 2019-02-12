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
 * @author bbrodt
 * 
 * Interface definition for listeners of CompoundFigure events. Events are
 * generated when a user presses a different tab (tabChanged event) or presses
 * one of the minimize/maximize/restore buttons (stateChanged event)
 */
/*package*/ interface CompoundFigureListener extends WindowFigureListener {
	/**
	 * @param oldState
	 * @param newState
	 */
	void stateChanged(int oldState, int newState);
}
