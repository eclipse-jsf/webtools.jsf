/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.commands.single;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class InsertSubNodeCommand extends SingleNodeCommand {
	private IDOMElement _parent, _child, _refchild;

	/**
	 * @param label
	 * @param parent
	 * @param child 
	 * @param refchild 
	 */
	public InsertSubNodeCommand(String label, IDOMElement parent,
			IDOMElement child, IDOMElement refchild) {
		super(label, parent);
		this._parent = parent;
		this._child = child;
		this._refchild = refchild;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		_parent.insertBefore(_child, _refchild);
	}
}
