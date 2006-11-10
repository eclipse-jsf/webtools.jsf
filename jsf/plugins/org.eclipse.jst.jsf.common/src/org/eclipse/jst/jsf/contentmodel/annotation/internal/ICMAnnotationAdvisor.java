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

package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationSourceFileInfo;


/**
 * Interface used to build internal data model of annotations.   
 * Decouples the data model from the parser so that model can be changed in the future.
 * 
 * Not intended to be implemented by others.
 * 
 * @author Gerry Kessler - Oracle
 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAnnotationAdvisor
 * 
 */
public interface ICMAnnotationAdvisor {
	/**
	 * Returns annotationFile extension information
	 * @return ICMAnnotationSourceFileInfo 
	 */
	public ICMAnnotationSourceFileInfo getFileInfo();
	/**
	 * Set whether or not elements, attributes and property name querying is case sensitive or not.
	 * @param boolean
	 */
	public void setCaseSensitive(boolean val);
	/**
	 * Method to be used by a ICMAnnotationFileParser to assign a property and a value 
	 * to an element in a particular content model defined by the ICMAnnotationSourceFileInfo.
	 * 
	 * @param elementName
	 * @param propertyName
	 * @param propertyValue
	 * 
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationFileParser
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationSourceFileInfo
	 */
	public void addElementAnnotation(String elementName, String propertyName, String propertyValue);
	/**
	 * Method to be used by a ICMAnnotationFileParser to assign a property and a value 
	 * to an attribute of an element in a particular content model defined by the ICMAnnotationSourceFileInfo.
	 * 
	 * @param elementName
	 * @param propertyName
	 * @param propertyValue
	 * 
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationFileParser
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationSourceFileInfo
	 */
	public void addAttributeAnnotation(String elementName, String attributeName, String propertyName, String propertyValue);
}
