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
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Brings up a simple text window.
 * 
 * @author mengbo
 */
public class TextWindow extends ApplicationWindow {
	private String _content;

	private Point _size = new Point(600, 600);

	/**
	 * empty for passing from events.
	 * @param parentShell 
	 * @param content 
	 */
	public TextWindow(Shell parentShell, String content) {
		super(parentShell);
		// addStatusLine();
		_content = content;
		// addCoolBar(SWT.BORDER);
		// addMenuBar();
		// addToolBar(SWT.BORDER);
	}

	protected Control createContents(Composite parent) {
		// _text = new Text(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		// _text.setText(_content);
		// return _text;

		TextViewer tview = new TextViewer(parent, SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		tview.setDocument(new Document(_content));

		return tview.getControl();
	}


	protected Point getInitialSize() {
		return _size;
	}
}
