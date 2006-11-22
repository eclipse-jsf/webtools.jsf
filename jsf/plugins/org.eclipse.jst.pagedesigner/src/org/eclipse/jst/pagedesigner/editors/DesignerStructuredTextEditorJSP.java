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
package org.eclipse.jst.pagedesigner.editors;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jst.pagedesigner.dnd.internal.DesignerSourceDropTargetListener;
import org.eclipse.ui.texteditor.ITextEditorDropTargetListener;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * @author mengbo
 */
public class DesignerStructuredTextEditorJSP extends StructuredTextEditor {
	protected void initializeDrop(ITextViewer textViewer) {
		// It seems if we don't skip this method, our drag drop listener will
		// can't be enabled.
	}

	public IAction getAction(String actionID) {
		try {
			return super.getAction(actionID);
		} catch (Exception e) {
			return null;
		}
	}

	public Object getAdapter(Class required) {
		if (ITextEditorDropTargetListener.class.equals(required)) {
			DesignerSourceDropTargetListener listener = new DesignerSourceDropTargetListener(
					this);
			return listener;
		}
        return super.getAdapter(required);
	}
}
