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
 * An internal class implementing ICMAnnotationAdvisor that allows us to decouple the parser
 * from the internal data model (CMAnnotationMap).
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public final class CMAnnotationAdvisor implements ICMAnnotationAdvisor {

	private CMAnnotationMap map;

	public CMAnnotationAdvisor(CMAnnotationMap map) {
		this.map = map;
		setCaseSensitive(true);// default to case sensitive
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.ICMAnnotationAdvisor#addElementAnnotation(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addElementAnnotation(String elementName, String propertyName, String propertyValue) {

		CMElementAnnotation elem = getElementAnnotation(getStringValueForCaseSensitivity(elementName));
		elem.setProperty(getStringValueForCaseSensitivity(propertyName), propertyValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.ICMAnnotationAdvisor#addAttributeAnnotation(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addAttributeAnnotation(String elementName, String attributeName,
			String propertyName, String propertyValue) {

		CMElementAnnotation elem = getElementAnnotation(getStringValueForCaseSensitivity(elementName));
		CMAttributeAnnotation attr = getAttributeAnnotation(elem,
				getStringValueForCaseSensitivity(attributeName));
		attr.setProperty(getStringValueForCaseSensitivity(propertyName), propertyValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.ICMAnnotationAdvisor#getFileInfo()
	 */
	public ICMAnnotationSourceFileInfo getFileInfo() {
		return this.map.getFileInfo();
	}

	/**
	 * This implementation is case sensitive by default
	 * 
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.ICMAnnotationAdvisor#setCaseSensitive(boolean)
	 */
	public void setCaseSensitive(boolean val) {
		map.setCaseSensitive(val);
	}

	private CMElementAnnotation getElementAnnotation(String elementName) {
		CMElementAnnotation elem = map.getElementAnnotation(elementName);
		if (elem == null) {
			elem = new CMElementAnnotation(elementName);
			map.addCMElementAnnotation(elem);
		}
		return elem;
	}

	private CMAttributeAnnotation getAttributeAnnotation(CMElementAnnotation elem,
			String attributeName) {
		// attrAnnotations map will always be initialized so do not need to check for null
		CMAttributeAnnotation attr = (CMAttributeAnnotation) elem.getAttributeAnnotations().get(
				attributeName);
		if (attr == null) {
			attr = new CMAttributeAnnotation(elem.getName(), attributeName);
			elem.addCMAttributeAnnotation(attr);
		}
		return attr;
	}

	private String getStringValueForCaseSensitivity(String val) {
		return map.getStringValueForCaseSensitivity(val);
	}

}
