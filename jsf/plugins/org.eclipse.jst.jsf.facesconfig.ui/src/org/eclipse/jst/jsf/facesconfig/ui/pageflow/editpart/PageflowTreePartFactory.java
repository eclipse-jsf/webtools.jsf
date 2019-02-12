/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;

/**
 * Edit Part Factory for creation pageflow tree parts
 *
 */
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
