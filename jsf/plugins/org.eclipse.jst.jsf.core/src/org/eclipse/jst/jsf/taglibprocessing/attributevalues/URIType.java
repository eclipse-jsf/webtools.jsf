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
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;

/**
 * URI Type: attribute values are Uniform Resource Identifiers (URIs), as defined in RFC 2396.
 * URI attribute values may include full URIs such as http://www.foobar.com/ as well as relative URIs such as foo.html and ../foo/.
 * EXPERIMENTAL - will change or dissappear
 */
public class URIType extends PathType implements IMetaDataEnabledFeature{
	//TODO: add validation... refactor or WebPath, RelativePath, etc.
	
	/**
	 * Constructor
	 */
	public URIType() {
		//
	}

}
