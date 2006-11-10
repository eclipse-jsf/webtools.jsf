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

package org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional;

/**
 * The interface used to access information about the annotationFile extension
 * 
 * Not intended to be implemented by clients.
 * @author Gerry Kessler - Oracle
 *
 */
public interface ICMAnnotationSourceFileInfo {
	/**
	 * @return location of annotation file that the locator class will use 
	 */
	public String getAnnotationFileLocation();
	/**
	 * @return bundle id of plugin defining the annotation file
	 */
	public String getBundleId();
	/**
	 * @return class name of parser to use on the annotation file
	 */
	public String getParserClassName();
	/**
	 * @return class name of the file locator
	 */
	public String getAnnotationFileLocatorClassname();
}
