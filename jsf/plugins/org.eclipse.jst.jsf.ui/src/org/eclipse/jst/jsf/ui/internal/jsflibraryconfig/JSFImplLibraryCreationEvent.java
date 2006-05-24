/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Justin Chen - development check in
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;

import java.util.EventObject;

/**
 * JSF Implementation library creation event.
 * 
 * @author Justin Chen - Oracle
 *
 */
public class JSFImplLibraryCreationEvent extends EventObject {

	private static final long serialVersionUID = 6390319185522362453L;
	private boolean isLibCreated; 
	
	public JSFImplLibraryCreationEvent(Object source, boolean okClicked) {
		super(source);
		this.isLibCreated = okClicked;
	}

	/**
	 * Ok button pressed.
	 * 
	 * @return boolean
	 */
	public boolean isLibraryCreated() {
		return isLibCreated;
	}
	
}
