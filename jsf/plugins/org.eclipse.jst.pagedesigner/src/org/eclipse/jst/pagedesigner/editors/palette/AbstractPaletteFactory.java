/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.views.palette.PaletteViewerPage;
import org.eclipse.ui.IEditorInput;

/**
 * Abstract class that all extenders must use to provide alternate palettes
 * to the Web Page Editor.  See also {@link IPaletteFactory}
 */
public abstract class AbstractPaletteFactory implements IPaletteFactory {

	public PaletteViewerPage createPaletteViewerPage(PaletteViewerProvider provider) {return null;}
	public PaletteViewerProvider createPaletteViewerProvider(EditDomain domain) {return null;}
	public PaletteRoot createPaletteRoot(IEditorInput editorInput){return null;}
}
