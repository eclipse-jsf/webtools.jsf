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
package org.eclipse.jst.pagedesigner.tableedit;

import java.util.List;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableResizableEditPolicy extends ElementResizableEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#createSelectionHandles()
	 */
	protected List createSelectionHandles() {
		List list = super.createSelectionHandles();

		// CR402770-1. Add a check whether it is table. If is not, will not
		// create the handles. Thus, the handles could assume
		// TableEditHelper.getTableEditAdatper()
		// will always return non null.
		ITableEditAdapter adapter = TableEditHelper
				.getTableEditAdapter((GraphicalEditPart) getHost());
		if (adapter != null) {
			TableHandleKit.addHandles((GraphicalEditPart) getHost(), list);
		}
		return list;
	}
}
