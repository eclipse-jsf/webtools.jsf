/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Exception for use during JSF Facet installation/un-installation
 *
 */
public class JSFFacetException extends CoreException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param msg
	 */
	public JSFFacetException(String msg) {
		super(new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, msg));
	}

}
