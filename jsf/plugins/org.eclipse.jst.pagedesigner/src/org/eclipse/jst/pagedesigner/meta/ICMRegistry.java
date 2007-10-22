/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.meta;

/**
 * 
 * @author mengbo
 */
public interface ICMRegistry {
	/**
	 * get the URI supported by this registry.
	 * 
	 * @return null if this is the global registry that support all the URI.
	 */
	public String getSupportedURI();

	/**
	 * get element descriptor by URI and tagname.
	 * 
	 * @param uri
	 * @param tagname
	 * @return the element descriptor
	 */
	public IElementDescriptor getElementDescriptor(String uri, String tagname);
}
