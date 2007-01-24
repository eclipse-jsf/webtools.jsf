/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;


public interface IStandardMetaDataSourceInfo {
	/**
	 * @return location of metadata file that the locator class will use 
	 */
	public String getLocation();
	/**
	 * @return bundle id of plugin defining the annotation file
	 */
	public String getBundleId();
	/**
	 * @return class name of the locator that will find the the standard metadata file
	 */
	public String getLocatorClassname();
}
