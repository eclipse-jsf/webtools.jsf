/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Justin Chen - development check in
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;

/**
 * Listener interface when a new JSF implementation library is created.
 * 
 * @author Justin Chen - Oracle
 * @deprecated
 */
public interface IJSFImplLibraryCreationListener extends java.util.EventListener {
	/**
	 * Ok button is pressed in JSF Library dialog.
	 * 
	 * @param event
	 */
	public void okClicked(JSFImplLibraryCreationEvent event);
}
