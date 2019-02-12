/***************************************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.util;


import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;


/**
 * Should not be used or extended by clients.
 * 
 * @author gjohnsto
 * @version $Id$
 */
/*package*/ interface IFacesConfigResource extends TranslatorResource {
	
	/**
	 * @return the root faces config object
	 */
	public FacesConfigType getFacesConfig();
}
