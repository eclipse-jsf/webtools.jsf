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
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.editors.palette.IDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.AbstractDropCustomizer;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IDropCustomizer;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.w3c.dom.Element;

/**
 * <p><b>Provisional API - subject to change</b></p>
 * @author mengbo
 * @version 1.5
 */
// TODO: should this be abstract?
public class AbstractElementEdit implements IElementEdit {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#createEditPolicies(org.eclipse.jst.pagedesigner.parts.ElementEditPart)
	 */
	public void createEditPolicies(ElementEditPart part) {
        // do nothing; sub-classes should override to add edit policies a part
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#handleModelChange(org.w3c.dom.Element,
	 *      org.eclipse.jst.pagedesigner.parts.ElementEditPart)
	 */
	public boolean handleModelChange(Element ele, ElementEditPart part,
			boolean recursive) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager,
	 *      org.w3c.dom.Element)
	 */
	public void fillContextMenu(IMenuManager contextMenu, Element ele) {
		// default do nothing, child class could override.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContainerContextMenu(org.eclipse.jface.action.IMenuManager,
	 *      org.eclipse.jst.pagedesigner.parts.ElementEditPart,
	 *      org.eclipse.jst.pagedesigner.parts.NodeEditPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public boolean fillContainerContextMenu(IMenuManager contextMenu,
			ElementEditPart elePart, NodeEditPart nodePart,
			ISelection innerSelection) {
		return false;
	}

	/**
	 * Child class should override this method if they have different way for
	 * resizing. e.g. DataWindow use "width/height" attribute, not "style".
	 * Also, the default ResizeCommand will adjust "style", so if child class
	 * override this method, they should also use different command.
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy#getResizeCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
	 */
	public boolean isResizable(Element ele) {
		CMElementDeclaration decl = CMUtil.getElementDeclaration(ele);
		if (decl != null) {
			// XXX: default implementation, if this element support "style"
			// attribute,
			// then we think it support resize.
			return decl.getAttributes().getNamedItem("style") != null; //$NON-NLS-1$
		}
		return true;
	}

    /** 
     * By default, return null to signal caller should use its default strategy
     */
    public IDropLocationStrategy getDropRequestorLocationStrategy(TagIdentifier tag, EditPartViewer viewer) {
        return null;
    }

    private final static IDropCustomizer   DEFAULT_DROP_CUSTOMIZER = 
        new AbstractDropCustomizer(){/* do nothing; use defaults */};
    
    /** (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#getDropCustomizer(org.eclipse.jst.jsf.common.dom.TagIdentifier)
     * @deprecated use getDropCustomizer(IDropSourceData) instead.
     */
    public IDropCustomizer getDropCustomizer(TagIdentifier tagId) 
    {
        return DEFAULT_DROP_CUSTOMIZER;
    }

    public IDropCustomizer getDropCustomizer(IDropSourceData dropSourceData)
    {
        if (dropSourceData instanceof ITagDropSourceData)
        {
            final String uri = ((ITagDropSourceData)dropSourceData).getNamespace();
            final String tagName = ((ITagDropSourceData)dropSourceData).getTagName();
            final TagIdentifier tagId = TagIdentifierFactory.createJSPTagWrapper(uri, tagName);
            return getDropCustomizer(tagId);
        }
        return DEFAULT_DROP_CUSTOMIZER;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#getTagCreator(org.eclipse.jst.jsf.common.dom.TagIdentifier)
     */
    public ITagCreator getTagCreator(TagIdentifier tagId) 
    {
        return null;
    }
}
