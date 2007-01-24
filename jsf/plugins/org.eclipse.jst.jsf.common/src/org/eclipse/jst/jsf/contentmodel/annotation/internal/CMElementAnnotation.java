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

import java.util.Hashtable;
import java.util.Map;

/**
 * Represents an annotation on a content model element.   
 * The element annotation object holds a map it's attribute annotations.
 * 
 * @author Gerry Kessler - Oracle
 * 
 * @see CMAttributeAnnotation
 * @deprecated see common.metadata package
 */
public class CMElementAnnotation extends CMAnnotation {
	protected Hashtable attributes = new Hashtable();

	/**
	 * Constructor
	 * @param name
	 */
	public CMElementAnnotation(String name) {
		super(name);
	}

	/**
	 * Return this element annotations attribute annotations.
	 * Will NOT be null but may be empty. 
	 * @return Map
	 */
	public Map getAttributeAnnotations() {
		return attributes;
	}

	/**
	 * Add an attribute annotation to this element's attribute annotations map
	 * 
	 * @param attribute annotation
	 */
	public void addCMAttributeAnnotation(CMAttributeAnnotation annotation){
		attributes.put(annotation.getName(), annotation);		
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer("ElementAnnotation: "); //$NON-NLS-1$
		buf.append(getName()).append(": ").append(super.toString()); //$NON-NLS-1$
		return buf.toString();
	}
}
