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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.commands.single.ChangeStyleCommand;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class ChangeStylePropertyAction extends Action {
	private IDOMElement _ele;

	private String _cssProperty;

	private String _cssValue;

	/**
	 * @param text
	 * @param ele 
	 * @param cssProperty 
	 * @param cssValue 
	 */
	public ChangeStylePropertyAction(String text, IDOMElement ele,
			String cssProperty, String cssValue) {
		super(text);
		this._ele = ele;
		this._cssProperty = cssProperty;
		this._cssValue = cssValue;
	}

	public void run() {
		if (isChecked()) {
			return;
		}

		Map map = new HashMap();
		map.put(_cssProperty, _cssValue);
		ChangeStyleCommand command = new ChangeStyleCommand(_ele, map);
		command.execute();
	}
}
