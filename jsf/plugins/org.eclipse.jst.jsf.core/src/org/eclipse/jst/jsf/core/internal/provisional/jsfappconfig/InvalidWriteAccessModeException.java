/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig;

/**
 * InvalidWriteAccessModeException is thrown to indicate a model was attempted to be opened
 * with an access mode (e.g. read, write) that it does not support.
 * 
 * @author Ian Trimble - Oracle
 */
public class InvalidWriteAccessModeException extends Exception {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -530337988461606646L;

	/**
	 * Creates an instance.
	 */
	public InvalidWriteAccessModeException() {
		super();
	}

	/**
	 * Creates an instance with the passed message.
	 * 
	 * @param message Message indicating reason for exception.
	 */
	public InvalidWriteAccessModeException(String message) {
		super(message);
	}

	/**
	 * Creates an instance with the passed message and root cause.
	 * 
	 * @param message Message indicating reason for exception.
	 * @param cause Root cause of exception.
	 */
	public InvalidWriteAccessModeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates an instance with the passed root cause.
	 * 
	 * @param cause Root cause of exception.
	 */
	public InvalidWriteAccessModeException(Throwable cause) {
		super(cause);
	}

}
