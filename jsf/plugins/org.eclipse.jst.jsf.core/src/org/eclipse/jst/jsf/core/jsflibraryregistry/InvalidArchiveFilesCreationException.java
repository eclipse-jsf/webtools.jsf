/*******************************************************************************
 * Copyright (c) 2005, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsflibraryregistry;

/**
 * Exception class thrown by PluginProvidedJSFLibraryCreationHelper.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @deprecated
 */
public class InvalidArchiveFilesCreationException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an instance with the specified message.
	 * @param msg This instance's message
	 */
	public InvalidArchiveFilesCreationException(String msg) {
		super(msg);
	}
}
