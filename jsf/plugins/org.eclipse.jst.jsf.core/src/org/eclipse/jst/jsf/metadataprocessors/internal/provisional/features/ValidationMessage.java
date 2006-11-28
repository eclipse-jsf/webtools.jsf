/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features;

import org.eclipse.core.runtime.IStatus;

public class ValidationMessage implements IValidationMessage {

	private String code;
	private String msg;
	private int severity = IStatus.WARNING;

	public ValidationMessage(String msg){
		this.msg = msg;
	}
	
	public ValidationMessage(String msg, String code, int severity){
		this.msg = msg;
		this.code = code;
		this.severity = severity;
	}
	
	public String getMessage() {
		return msg;
	}

	public String getCode() {
		return code;
	}

	public int getSeverity() {
		return severity;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

}
