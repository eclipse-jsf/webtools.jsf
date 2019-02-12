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
