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
package org.eclipse.jst.pagedesigner.commands.single;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class AddSubNodeCommand extends SingleNodeCommand {
	private IDOMElement _parent, _child;

	private String _tagName;

	private String _url;

	private Map _attributes;

	/**
	 * @param label
	 * @param node
	 */
	public AddSubNodeCommand(String label, IDOMElement node, String name,
			String url, Map attributs) {
		super(label, node);
		this._parent = node;
		this._tagName = name;
		this._url = url;
		this._attributes = attributs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		String prefix = JSPUtil.getOrCreatePrefix(_parent.getModel(), _url,
				null);
		_child = (IDOMElement) _parent.getOwnerDocument().createElement(
				prefix + ":" + _tagName);
		for (Iterator iterator = _attributes.keySet().iterator(); iterator
				.hasNext();) {
			String key = (String) iterator.next();
			String value = (String) _attributes.get(key);
			_child.setAttribute(key, value);
		}
		_parent.appendChild(_child);
	}

	public IDOMElement getChildNode() {
		return _child;
	}
}
