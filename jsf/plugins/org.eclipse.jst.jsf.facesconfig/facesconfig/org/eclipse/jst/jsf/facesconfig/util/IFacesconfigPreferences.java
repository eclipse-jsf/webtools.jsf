/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.util;

/**
 * Should NOT be used or extended by clients.
 * 
 * constants for JSF preferences pages
 *
 * @author spaxton
 */
/*package*/ interface IFacesconfigPreferences {
	
	/**
	 * Preference for whether or not to restart the server
	 */
	String PREFSKEY_SERVER_RESTART = "jsf.project.restart"; //$NON-NLS-1$
	
}
