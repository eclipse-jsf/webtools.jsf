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

import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.editors.palette.IDropSourceData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IDropCustomizer;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.w3c.dom.Element;

/**
 * IElementEdit support additional edit support to an element.
 *
 * This interface should not be extended by clients.  Extend AbstractElementEdit
 * instead.
 *
 * <p><b>Provisional API - subject to change</b></p>
 *
 * @author mengbo
 * @version 1.5
 */
public interface IElementEdit
{
	/**
	 * override default edit policies on part
	 * @param part
	 */
	public void createEditPolicies(ElementEditPart part);

	/**
	 * The element (or its decendent) changed.
	 *
	 * @param ele
	 * @param part
	 * @param recursive
	 * @return handle model changes on part.
	 */
	public boolean handleModelChange(Element ele, ElementEditPart part,
			boolean recursive);

	/**
	 * Add special menu items for the particular element to the context menu.
	 *
	 * @param contextMenu
	 * @param ele
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
	 * @param ele
	 *
	 * @return true if ele has resize support
	 */
	public boolean isResizable(Element ele);

    /**
     * @param tag the element to return a drop location strategy for.
     * This is the requesting element (being dropped), not the drop target
     *
     * @param viewer is the viewer where the new strategy will optionally
     * add feedback
     *
     * @return the strategy to be used to use to find a drop location when
     * the corresponding element is being dropped (the source part).  May
     * be null signalling that the caller should use its default strategy.
     *
     */
     public IDropLocationStrategy getDropRequestorLocationStrategy(TagIdentifier tag, EditPartViewer viewer);

    /**
     * @param tagId
     * @return the drop customizer for this edit's element.  May return null.
     * Returning null and returning a IDropCustomizer whose runCustomizer always returns
     * OK and getDropCustomizationData always returns null will be considered
     * equivalent by the framework.
     * @deprecated use getDropCustomizer(IDropSourceData) instead
     */
    public IDropCustomizer getDropCustomizer(TagIdentifier tagId);

    /**
     * @param dropSourceData
     * @return the drop customizer for the drop source data or null if none.
     */
    public IDropCustomizer getDropCustomizer(final IDropSourceData dropSourceData);

    /**
     * @param tagId
     * @return a tag creator for the indicated tag or null to indicate that the
     * system should use it's default tag creator
     */
    public ITagCreator getTagCreator(TagIdentifier tagId);
}
