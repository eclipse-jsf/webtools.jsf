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

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class RemoveSubNodeCommand extends SingleNodeCommand {
	private IDOMElement _parent, _child;

	/**
	 * @param label
	 * @param parent
	 * @param child 
	 */
	public RemoveSubNodeCommand(String label, IDOMElement parent,
			IDOMElement child) {
		super(label, parent);
		_parent = parent;
		_child = child;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		_parent.removeChild(_child);
	}
}
