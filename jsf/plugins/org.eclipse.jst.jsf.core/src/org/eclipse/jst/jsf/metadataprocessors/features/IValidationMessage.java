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

/**
 * Interface for validation messages for metadata features
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface IValidationMessage {
	/**
	 * @return localized string desribing validation issue
	 * Should not be null;
	 */
	public String getMessage();

	/**
	 * @return String representing a validation code associated with validation issue 
	 * Can return null
	 */
	public String getCode();
	
	/**
	 * @return integer value representing severity. 
	 * Valid values are 0 (IStatus.INFO), 1 (IStatus.WARNING) and 4 (IStatus.ERROR)
	 */
	public int getSeverity();
}
