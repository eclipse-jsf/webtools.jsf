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

/**
 * Represents an annotation on a content model attribute
 * 
 * @author Gerry Kessler - Oracle
 *
 * @see CMElementAnnotation
 * @deprecated see common.metadata package
 */
public class CMAttributeAnnotation extends CMAnnotation {
	private String elementNodeSpec;
	
	/**
	 * Constructor
	 * @param elemNodeSpec
	 * @param name
	 */
	public CMAttributeAnnotation(String elemNodeSpec, String name) {
		super(name);
		elementNodeSpec = elemNodeSpec;
	}

	public String toString(){
		StringBuffer buf = new StringBuffer("AttrAnnotation: "); //$NON-NLS-1$
		buf.append(getName()).append(": ").append(elementNodeSpec).append(": ").append(super.toString()); //$NON-NLS-1$ //$NON-NLS-2$
		return buf.toString();
	}
}
