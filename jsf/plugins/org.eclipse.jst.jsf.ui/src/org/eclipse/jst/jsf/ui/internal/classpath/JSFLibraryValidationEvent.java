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
package org.eclipse.jst.jsf.ui.internal.classpath;

import org.eclipse.core.runtime.IStatus;

/**
 * Validation event used by JSFLibraryControl to notify containers of updates
 * @deprecated
 */
public class JSFLibraryValidationEvent {
	private String msg;
	private int severity;
	
	/**
	 * Constructor
	 * @param msg
	 * @param severity - IStatus int value
	 */
	public JSFLibraryValidationEvent(String msg, int severity) {
		this.msg = msg;
		this.severity = severity;
	}
	
	/**
	 * Constructs event with severity of IStatus.ERROR
	 * @param msg
	 */
	public JSFLibraryValidationEvent(String msg) {
		this(msg, IStatus.ERROR);
	}
	
	/**
	 * @return validation message
	 */
	public String getMessage(){
		return msg;
	}
	
	/**
	 * @return IStatus int value
	 */
	public int getSeverity(){
		return severity;
	}
}
