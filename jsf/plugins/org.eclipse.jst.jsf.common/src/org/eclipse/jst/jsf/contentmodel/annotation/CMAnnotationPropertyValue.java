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

package org.eclipse.jst.jsf.contentmodel.annotation;

import java.util.List;

/**
 * The class used to return result of a query on the annotation model.   
 * 
 * @author Gerry Kessler - Oracle
 *
 * @deprecated see <code>org.eclipse.jst.jsf.common.metadata</code>  package
 */
public class CMAnnotationPropertyValue {
	private ICMAnnotationSourceFileInfo fileInfo;
	private List propertyValues;
	
	/**
	 * Constructor
	 * @param fileInfo
	 * @param propertyValues
	 */
	public CMAnnotationPropertyValue(ICMAnnotationSourceFileInfo fileInfo, List propertyValues){
		this.fileInfo = fileInfo;
		this.propertyValues = propertyValues;
	}

	/**
	 * Convenience method for accessing the bundleid
	 * @return String
	 */
	public String getBundleId(){
		return fileInfo.getBundleId();
	}
	
	/**
	 * @return ICMAnnotationSourceFileInfo used to define this property
	 */
	public ICMAnnotationSourceFileInfo getFileInfo(){
		return fileInfo;
	}
	
	/**
	 * Return first string property value.   
	 * If caller expects more values, they should use getPropertyValues().
	 * @return String  
	 */
	public String getPropertyValue(){
		return ((String)propertyValues.get(0)).trim();
	}
	
	/**
	 * Return list of property values as strings for the given property
	 * @return java.util.List
	 */
	public List getPropertyValues(){
		return propertyValues;
	}
}
