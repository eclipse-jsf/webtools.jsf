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

import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

/**
 * This is copied from the xmleditor plugin. Represents the design viewer that
 * need can be used to show the design page.
 * 
 * @author mengbo
 */
public interface IDesignViewer {
	/**
	 * @return the title
	 */
	String getTitle();

	/**
	 * @param model
	 */
	void setModel(IStructuredModel model);

	// void setViewerSelectionManager(ViewerSelectionManager
	// viewerSelectionManager);

	/**
	 * @return the graphical viewer
	 */
	public IHTMLGraphicalViewer getGraphicViewer();
}
