/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query;

/**
 * Exception intended to be superclass of all exceptions thrown while handling metadata during query
 * <p><b>Provisional API - subject to change</b></p>
 */
public class MetaDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public MetaDataException(){
		super();
	}
	/**
	 * Constructor
	 * @param msg
	 */
	public MetaDataException(String msg){
		super(msg);
	}
	
	/**
	 * Constructor
	 * @param msg
	 * @param cause 
	 */
	public MetaDataException(String msg, Throwable cause){
		super(msg, cause);
	}
	
}
