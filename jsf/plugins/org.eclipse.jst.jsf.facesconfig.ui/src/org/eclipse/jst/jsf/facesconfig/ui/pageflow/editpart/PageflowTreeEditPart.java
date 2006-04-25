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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;

/**
 * The <code>TreeEditPart</code> implementation for a pageflow.
 * 
 * 
 */
public class PageflowTreeEditPart extends PageflowElementTreeEditPart {

	/**
	 * Creates a new PageflowTreeEditPart instance.
	 * 
	 * @param pageflow
	 * @param showOnlyCompoundTasks
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
		List children = getPageflow().getNodes();

		return new ArrayList(children);
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
