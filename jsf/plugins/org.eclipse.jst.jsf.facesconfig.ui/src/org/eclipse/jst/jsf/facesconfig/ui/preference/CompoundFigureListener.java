/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
