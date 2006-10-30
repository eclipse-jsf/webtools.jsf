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

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.pagedesigner.editors.DesignerStructuredTextEditorJSP;
import org.eclipse.ui.texteditor.ITextEditorDropTargetListener;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * @author mengbo
 */
public class TextEditorDropTargetListenerFactory implements IAdapterFactory {

	/**
	 * 
	 */
	public TextEditorDropTargetListenerFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (ITextEditorDropTargetListener.class.equals(adapterType)) {
			if (adaptableObject instanceof DesignerStructuredTextEditorJSP) {
				DesignerSourceDropTargetListener listener = new DesignerSourceDropTargetListener(
						(StructuredTextEditor) adaptableObject);
				return listener;
			}

		}
		return null;

	}

	public Class[] getAdapterList() {
		Class[] classes = new Class[1];
		classes[0] = ITextEditorDropTargetListener.class;
		return classes;
	}
}
