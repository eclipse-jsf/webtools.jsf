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
 * Create a palette parts to replace the default palette  in
 * the Web Page Editor.
 * 
 * If any of the create methods return null, the default WPE behaviour will be used.
 */
public interface IPaletteFactory {
	/**
	 * @param provider
	 * @return {@link PaletteViewerPage} - MAY return null. If null, the default palette viewer page will be used. 
	 */
	public PaletteViewerPage createPaletteViewerPage(PaletteViewerProvider provider) ;
	
	/**
	 * @param domain
	 * @return {@link PaletteViewerProvider} - may return null. If null, the default paletteViewerProvider will be used. 
	 */
	public PaletteViewerProvider createPaletteViewerProvider(EditDomain domain) ;

	/**
	 * @param editorInput
	 * @return PaletteRoot  - may return null.   If null, the default palette root will be used. 
	 */
	public PaletteRoot createPaletteRoot(IEditorInput editorInput);
}
