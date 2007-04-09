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
package org.eclipse.jst.pagedesigner.actions.single;

import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class ChangeAttributeAction extends Action {
	IDOMElement _ele;

	String _attrValue;

	private String _attrName;

	/**
	 * @param label 
	 * @param ele
	 * @param attrName 
	 * @param attrValue 
	 */
	public ChangeAttributeAction(String label, IDOMElement ele,
			String attrName, String attrValue) {
		super(label);
		this._ele = ele;
		this._attrName = attrName;
		this._attrValue = attrValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		if (this.isChecked()) {
			return;
		}

		ChangeAttributeCommand c = new ChangeAttributeCommand(
				PDPlugin
						.getResourceString("ChangeAttributeAction.CommandLabel.ChangeStyleClass"), _ele, _attrName, _attrValue); //$NON-NLS-1$
		c.execute();
	}
}
