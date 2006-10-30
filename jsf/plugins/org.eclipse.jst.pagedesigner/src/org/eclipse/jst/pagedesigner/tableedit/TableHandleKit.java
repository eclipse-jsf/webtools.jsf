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

/**
 * @author mengbo
 * @version 1.5
 */
public class TableHandleKit {

	/**
	 * @param tableHost
	 * @param handles
	 */
	public static void addHandles(GraphicalEditPart tableHost, List handles) {
		addColumnHandles(tableHost, handles);
		addRowHandles(tableHost, handles);
	}

	/**
	 * @param tableHost
	 * @param handles
	 */
	private static void addColumnHandles(GraphicalEditPart tableHost,
			List handles) {
		TableColumnHandle handle = new TableColumnHandle(tableHost);
		handles.add(handle);

	}

	/**
	 * @param tableHost
	 * @param handles
	 */
	private static void addRowHandles(GraphicalEditPart tableHost, List handles) {
		TableRowHandle handle = new TableRowHandle(tableHost);
		handles.add(handle);
	}

}
