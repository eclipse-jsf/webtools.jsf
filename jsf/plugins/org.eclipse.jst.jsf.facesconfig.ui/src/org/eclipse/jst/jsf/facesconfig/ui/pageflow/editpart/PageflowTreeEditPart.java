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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;

/**
 * The <code>TreeEditPart</code> implementation for a pageflow.
 * 
 * 
 */
/*package*/ class PageflowTreeEditPart extends PageflowElementTreeEditPart {

	/**
	 * Creates a new PageflowTreeEditPart instance.
	 * 
	 * @param pageflow
	 */
	public PageflowTreeEditPart(Pageflow pageflow) {
		super(pageflow);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {
		List children_ = getPageflow().getNodes();

		return new ArrayList(children_);
	}

	/**
	 * Returns the model as <code>Pageflow</code>.
	 * 
	 * @return the model as <code>Pageflow</code>
	 */
	public Pageflow getPageflow() {
		return (Pageflow) getModel();
	}

}
