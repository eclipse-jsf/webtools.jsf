/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
