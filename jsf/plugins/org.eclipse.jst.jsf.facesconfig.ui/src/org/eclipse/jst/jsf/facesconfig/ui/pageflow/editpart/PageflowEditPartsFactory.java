/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * 
 * Edit part factory to create different pageflow elements.
 * 
 * 
 */
public class PageflowEditPartsFactory implements EditPartFactory {
	/*
	 * (non-Javadoc)
	 * 
	 * @see EditPartFactory#createEditPart(EditPart, Object)
	 */
	public EditPart createEditPart(EditPart context, Object obj) {
		if (obj instanceof Pageflow) {
			return new PageflowEditPart((Pageflow) obj);
		} else if (obj instanceof PageflowNode) {
			return new PageflowNodeEditPart((PageflowNode) obj);
		} else if (obj instanceof PageflowLink) {
			return new PageflowLinkEditPart((PageflowLink) obj);
		}
		return null;
	}
}
