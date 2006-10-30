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
package org.eclipse.jst.pagedesigner.elementedit;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.w3c.dom.Element;

/**
 * IElementEdit support additional edit support to an element
 * 
 * @author mengbo
 * @version 1.5
 */
public interface IElementEdit {
	public void createEditPolicies(ElementEditPart part);

	/**
	 * The element (or its decendent) changed.
	 * 
	 * @param ele
	 * @param part
	 * @return
	 */
	public boolean handleModelChange(Element ele, ElementEditPart part,
			boolean recursive);

	/**
	 * Add special menu items for the particular element to the context menu.
	 * 
	 * @param contextMenu
	 */
	public void fillContextMenu(IMenuManager contextMenu, Element ele);

	/**
	 * This method is called when current selection is inside "ele". And this
	 * method should fill in menu items relating to the "ele" context and the
	 * current "innerSelection". For example, this "ele" could be a table,
	 * "innerSelection" could be something inside a cell. Then could fill in
	 * actions relating to the table and the cell, such as "add row before",
	 * "delete current column", etc.
	 * 
	 * @param contextMenu
	 * @param elePart
	 *            the elementeditpart corresponding to this ElementEdit
	 * @param nodePart
	 *            the smallest part covers the current selection. nodePart will
	 *            always be a decedent of the elePart.
	 * @param innerSelection
	 *            the selection
	 * @return true if added actions.
	 */
	public boolean fillContainerContextMenu(IMenuManager contextMenu,
			ElementEditPart elePart, NodeEditPart nodePart,
			ISelection innerSelection);

	/**
	 * whether the corresponding element support resize. If it does, then the
	 * corresponding policy installed through <code>createEditPolicies</code>
	 * should handle resize.
	 * 
	 * @return
	 */
	public boolean isResizable(Element ele);
}
