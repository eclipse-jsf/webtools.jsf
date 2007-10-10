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
package org.eclipse.jst.pagedesigner.dnd.internal;

import org.eclipse.jst.pagedesigner.commands.SourceViewerCommand;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * @author mengbo
 */
public class SourceViewLocalDropCommand extends SourceViewerCommand {
	/**
	 * the map from feedback to ILocalDropHandler
	 */
	private int _location;

	private StructuredTextEditor _textEditor;

	private Object _data;

	/**
	 * @param textEditor 
	 * @param data 
	 * @param location 
	 * 
	 */
	public SourceViewLocalDropCommand(StructuredTextEditor textEditor,
			Object data, int location) {
		super(PageDesignerResources.getInstance().getString(
				"SourceViewLocalDropCommand.Label.InsertSyntax"), textEditor); //$NON-NLS-1$
		_textEditor = textEditor;
		_data = data;
		_location = location;
	}

	public void doExecute() {
		_textEditor.getTextViewer().getTextWidget().insert((String) _data);
	}

	public void setSelection() {
		_textEditor.getTextViewer().setSelectedRange(_location,
				((String) _data).length());
	}
}
