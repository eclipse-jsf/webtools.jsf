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
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Brings up a simple text window.
 * 
 * @author mengbo
 */
public class TextWindow extends ApplicationWindow {
	// this browser will be cleaned up when they close the window.
	private Text _text;

	private String _content;

	private Point _size = new Point(600, 600);

	/**
	 * empty for passing from events.
	 */
	public TextWindow(Shell parentShell, String content) {
		super(parentShell);
		// addStatusLine();
		_content = content;
		// addCoolBar(SWT.BORDER);
		// addMenuBar();
		// addToolBar(SWT.BORDER);
	}

	public Text getTextWidget() {
		return _text;
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

	public void setStartSize(Point pp) {
		_size = pp;
	}

	protected Point getInitialSize() {
		return _size;
	}
}
