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
package org.eclipse.jst.pagedesigner.ui.common.sash;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;

/**
 * This contributor should be used when a SashEditor is inside a multipage
 * editor.
 * 
 * @author mengbo
 * @version 1.5
 */
public abstract class NestedEditorActionBarContributor extends
		MultiPageEditorActionBarContributor {
	/**
	 * Child class should not override this method.
	 */
	public final void setActivePage(IEditorPart activeEditor) {
		if (activeEditor instanceof SashEditorPart) {
			activeEditor = ((SashEditorPart) activeEditor).getActiveEditor();
		}

		setInnerActivePage(activeEditor);
	}

	/**
	 * Child class should override this method
	 * 
	 * @param activeEditor
	 */
	public abstract void setInnerActivePage(IEditorPart activeEditor);
}
