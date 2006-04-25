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
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;

public class PageflowTreePartFactory implements EditPartFactory {
	/*
	 * (non-Javadoc)
	 * 
	 * @see EditPartFactory#createEditPart()
	 */
	public EditPart createEditPart(EditPart context, Object model) {

		if (model instanceof Pageflow) {
			return new PageflowTreeEditPart((Pageflow) model);

		} else if (model instanceof PageflowElement) {
			return new PageflowElementTreeEditPart((PageflowElement) model);
		}

		return null;
	}
}
