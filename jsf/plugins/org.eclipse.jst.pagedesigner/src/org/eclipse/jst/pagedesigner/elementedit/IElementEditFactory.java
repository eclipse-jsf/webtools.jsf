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
package org.eclipse.jst.pagedesigner.elementedit;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;


/**
 * @author mengbo
 * @version 1.5
 * 
 * Clients should not implement this interface.  Extend AbstractElementEditFactory instead.
 */
public interface IElementEditFactory {
	/**
	 * 
	 * @param tag
	 * @return null if this factory don't support this element
	 */
	public IElementEdit createElementEdit(TagIdentifier tag);

	/**
	 * get the URI namespace that this factory support. "null" means this
	 * factory can be used as default factory.
	 * 
	 * @return null if this factory don't have a specific URI to support.
	 */
	public String getSupportedURI();
}
