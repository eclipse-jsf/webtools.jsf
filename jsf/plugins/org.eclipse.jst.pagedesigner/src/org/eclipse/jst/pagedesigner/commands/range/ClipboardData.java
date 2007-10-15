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
package org.eclipse.jst.pagedesigner.commands.range;

import java.util.Vector;

import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class ClipboardData implements IInputSourceProvider {

	private Control _control;

	/**
	 * @param control 
	 */
	public ClipboardData(Control control) {
		super();
		_control = control;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.IInputSourceProvider#getNodes()
	 */
	public Node[] getNodes() {
		Object data = getClipboardData();
		if (data instanceof Vector && ((Vector) data).size() > 0) {
			return (Node[]) ((Vector) data).toArray(new Node[] {});
		} else if (data instanceof Node[]) {
			return (Node[]) data;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.IInputSourceProvider#getStringData()
	 */
	public String getStringData() {
		Object data = getClipboardData();
		if (data instanceof String) {
			return (String) data;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.IInputSourceProvider#getCharacterData()
	 */
	public Character getCharacterData() {
		return null;
	}

	/**
	 * @return the clip board data
	 */
	public Object getClipboardData() {
		Clipboard clipboard = new Clipboard(_control.getDisplay());

		final Object cuted = clipboard.getContents(TemplateTransfer.getInstance());
		if (cuted instanceof Node[] || cuted instanceof Vector) {
			return cuted;
		}
        return clipboard.getContents(TextTransfer.getInstance());
	}
}
