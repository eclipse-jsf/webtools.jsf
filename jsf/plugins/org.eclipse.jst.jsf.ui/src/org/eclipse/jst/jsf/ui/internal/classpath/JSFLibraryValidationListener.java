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

/**
 * Listeners of  {@link JSFLibraryValidationEvent}s should implement
 * @deprecated
 */
public interface JSFLibraryValidationListener {
	/**
	 * Callback 
	 * @param e
	 */
	public void notifyValidation(JSFLibraryValidationEvent e);
}
