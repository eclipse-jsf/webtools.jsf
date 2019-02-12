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
package org.eclipse.jst.jsf.common.metadata.internal;


/**
 * Represents the metadata file defined by the StandardMetaDataFile extension-point
 * Not intended to be implemented by clients
 */
public interface IStandardMetaDataSourceInfo {
	/**
	 * @return location of metadata file that the locator class will use 
	 */
	public String getLocation();
	/**
	 * @return bundle id of plugin defining the metadata file
	 */
	public String getBundleId();
	/**
	 * @return class name of the locator that will find the the standard metadata file
	 */
	public String getLocatorClassname();
}
