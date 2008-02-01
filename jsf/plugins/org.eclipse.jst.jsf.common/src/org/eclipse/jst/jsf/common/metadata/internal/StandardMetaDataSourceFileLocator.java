/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Used to supply an InputStream to the parser of an annotation 
 * meta-data file and the URL to the resource bundle for a properties file, if any.
 * 
 * Implementors must provide a zero-argument constructor.
 * 
 * CURRENTLY INTERNAL... WILL BE MADE API AT SOME POINT
 *
 */ 
public abstract class StandardMetaDataSourceFileLocator {
	/**
	 * metadata file to locate
	 */
	protected IStandardMetaDataSourceInfo fileInfo;
	
	/**
	 * Set the <code>IStandardMetaDataSourceInfo</code> for this locator
	 * @param fileInfo
	 */
	public final void setFileInfo(IStandardMetaDataSourceInfo fileInfo){
		this.fileInfo = fileInfo;
	}
	
	/**
	 * @return the <code>IStandardMetaDataSourceInfo</code> for this locator
	 */
	protected final IStandardMetaDataSourceInfo getFileInfo(){
		return fileInfo;
	}
	/**
	 * Return InputStream to the meta-data source file.  
	 * Callers are responsible for closing the stream.
	 * @return InputStream
	 * @throws IOException
	 */
	public abstract InputStream getInputStream() throws IOException;
	
	/**
	 * Return URL to the meta-data source file.  
	 * Must not be null.
	 * @return URL
	 */
	public abstract URL getURL();
	
	/**
	 * Return ResourceBundle for the property files if there are any.  Return null if not.
	 * 
	 * @return java.util.ResourceBundle
	 * @throws IOException
	 * @throws MissingResourceException
	 */
	public abstract ResourceBundle getResourceBundle() throws IOException, MissingResourceException;
}
