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
