/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.ModelCreationFactory;

/**
 * This is the drop listener for template transfers from the palette. Only need
 * to implement the <code>getFactory</code> method.
 */
public class PageflowTemplateTransferDropTargetListener extends
		TemplateTransferDropTargetListener {
	/**
	 * Creates a new PageflowTemplateTransferDropTargetListener instance.
	 * 
	 * @param viewer -
	 *            target pageflow editor view.
	 */
	public PageflowTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see TemplateTransferDropTargetListener#getFactory(Object)
	 */
	protected CreationFactory getFactory(Object template) {
		if (template instanceof Class) {
			return new ModelCreationFactory((Class) template);
		}
		return null;
	}

}
