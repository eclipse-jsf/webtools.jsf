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
 *  * @deprecated
 */
public interface IElementDescriptor {
	/**
	 * get the tagname
	 * 
	 * @return the tag name
	 */
	public String getTagName();

	/**
	 * get the namespace URI
	 * 
	 * @return te namespace
	 */
	public String getNamespaceURI();

	/**
	 * get all attribute descriptors
	 * 
	 * @return the attribute descriptors
	 */
	public IAttributeDescriptor[] getAttributeDescriptors();

	/**
	 * @param attributeName
	 * @return the attribute by name
	 */
	public IAttributeDescriptor getAttributeDescriptor(String attributeName);

	/**
	 * get attribute descriptor by name
	 * 
	 * @param attrname
	 * @return
	 */
	// public IAttributeDescriptor getAttributeDescriptor(String attrname);
	/**
	 * get reference. The ElementDescriptor being referenced may provide more
	 * information. For example, &lt;h:inputText&gt; may reference
	 * &lt;input&gt;. So those attribute descriptor not provided by
	 * &lt;h:inputText&gt; could still be provided by &lt;input&gt;.
	 * 
	 * @return the reference
	 */
	public IElementDescriptor getReference();

	/**
	 * if this element have eclipse help topic, then could use this method to
	 * return a context id.
	 * 
	 * @return could be null
	 */
	public String getHelpContextID();
}
