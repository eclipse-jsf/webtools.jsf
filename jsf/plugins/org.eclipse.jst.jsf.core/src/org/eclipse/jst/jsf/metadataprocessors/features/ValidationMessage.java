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

package org.eclipse.jst.jsf.metadataprocessors.features;

import org.eclipse.core.runtime.IStatus;

/**
 * Metadata Validation Message implementation 
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */
public class ValidationMessage implements IValidationMessage {

	private String code;
	private String msg;
	private int severity = IStatus.WARNING;

	/**
	 * Constructor
	 * @param msg
	 */
	public ValidationMessage(String msg){
		this.msg = msg;
	}
	
	/**
	 * Constructor
	 * @param msg
	 * @param code
	 * @param severity
	 */
	public ValidationMessage(String msg, String code, int severity){
		this.msg = msg;
		this.code = code;
		this.severity = severity;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage#getMessage()
	 */
	public String getMessage() {
		return msg;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage#getCode()
	 */
	public String getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage#getSeverity()
	 */
	public int getSeverity() {
		return severity;
	}

	/**
	 * @param msg
	 */
	public void setMessage(String msg) {
		this.msg = msg;
	}

}
