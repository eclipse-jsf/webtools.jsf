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
package org.eclipse.jst.pagedesigner.meta.internal;

import java.util.List;

import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.IElementDescriptor;

/**
 * @author mengbo
 */
public class ElementDescriptor implements IElementDescriptor {
	String _tagName;

	String _namespaceURI;

	IAttributeDescriptor[] _attrs;

	private String _helpContextID;

	/**
	 * 
	 */
	public ElementDescriptor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IElementDescriptor#getTagName()
	 */
	public String getTagName() {
		return _tagName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IElementDescriptor#getNamespaceURI()
	 */
	public String getNamespaceURI() {
		return _namespaceURI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IElementDescriptor#getAttributeDescriptors()
	 */
	public IAttributeDescriptor[] getAttributeDescriptors() {
		return _attrs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IElementDescriptor#getReference()
	 */
	public IElementDescriptor getReference() {
		return null;
	}

	/**
	 * @param namespaceURI
	 */
	public void setNamespaceURI(String namespaceURI) {
		this._namespaceURI = namespaceURI;
	}

	/**
	 * @param tagName
	 */
	public void setTagName(String tagName) {
		this._tagName = tagName;
	}

	/**
	 * @param attrs
	 */
	public void setAttributeDescriptors(List attrs) {
		if (attrs == null || attrs.isEmpty()) {
			_attrs = new IAttributeDescriptor[0];
		} else {
			_attrs = new IAttributeDescriptor[attrs.size()];
			attrs.toArray(_attrs);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IElementDescriptor#getAttributeDescriptor(java.lang.String)
	 */
	public IAttributeDescriptor getAttributeDescriptor(String attributeName) {
		if (_attrs != null) {
			for (int i = 0; i < _attrs.length; i++) {
				IAttributeDescriptor descriptor = _attrs[i];
				if (descriptor.getAttributeName().equalsIgnoreCase(
						attributeName)) {
					return descriptor;
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IElementDescriptor#getHelpContextID()
	 */
	public String getHelpContextID() {
		return _helpContextID;
	}

	/**
	 * @param contextid
	 */
	public void setHelpContextID(String contextid) {
		_helpContextID = contextid;
	}
}
