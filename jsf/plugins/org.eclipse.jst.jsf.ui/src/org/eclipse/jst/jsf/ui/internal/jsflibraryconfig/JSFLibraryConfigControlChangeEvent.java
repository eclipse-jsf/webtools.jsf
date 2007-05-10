/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;

import java.util.EventObject;

/**
 * Event that something has changed in the JSFLibraryConfigControl
 *
 */
public class JSFLibraryConfigControlChangeEvent {
	private EventObject event;

	/**
	 * Constructor
	 * @param ev
	 */
	public JSFLibraryConfigControlChangeEvent(EventObject ev){
		this.event = ev;
	}

	/**
	 * @return java.util.EventObject
	 */
	public EventObject getEvent(){
		return event;
	}
}
