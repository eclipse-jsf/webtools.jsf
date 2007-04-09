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
package org.eclipse.jst.pagedesigner.converter;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ITagConverter is used to convert a custom tag to a HTML tag. Each
 * ITagConverter instance will be dedicated to a single custom tag element.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface ITagConverter {
	/**
	 * Set the target document where the converted node should belong to.
	 * 
	 * @param document
	 */
	public void setDestDocument(IDOMDocument document);

	/**
	 * refresh the internal state of this ITagConverter. This method normally is
	 * called when the host element change.
	 * 
	 * @param context
	 */
	public void convertRefresh(Object context);

	/**
	 * @return The host element being converted.
	 */
	public Element getHostElement();

	/**
	 * for some tags, they don't convert to HTML. In that case, this method
	 * should return false for them. And if this method return false, then
	 * should return an image in <code>getVisualImage()</code> for displaying
	 * in the designer.
	 * 
	 * @return true if the tag represents something that will be rendered visually at runtime
	 */
	public boolean isVisualByHTML();

	/**
	 * if isVisualByHTML() return false, then this method should return an image
	 * to be displayed in designer.
	 * 
	 * Normally this image will be a shared image for those hidden elements. It
	 * is this class's responsibility to dispose the image if the image is not a
	 * shared one.
	 * 
	 * @return the placeholder image for non-visual tags
	 */
	public Image getVisualImage();

	/**
	 * @return the result element after conversion
	 */
	public Element getResultElement();

	/**
	 * @return the list of children that should be continuely converted.
	 */
	public List getChildModeList();
    
    /**
     * @return a list of Element tags that map to non-visual children
     * Type should be always be Element.
     */
    public List getNonVisualChildren();

	/**
	 * For child nodes that need further convert, return their position in the
	 * converted DOM tree.
	 * 
	 * @param childModel
	 * @return the position of child nodes
	 */
	public ConvertPosition getChildVisualPosition(Node childModel);

	/**
	 * When the convert result in multi-level element. If this method return
	 * false, then the caller should not use child nodes of
	 * <code>getHostElement()</code>
	 * 
	 * @return true if the host element has convertable children
	 */
	public boolean isMultiLevel();

	/**
	 * 
	 * @return true if the host element is a widget
	 */
	public boolean isWidget();

	/**
	 * API contract needed here
	 * 
	 */
	public void dispose();

	/**
	 * @param mode
	 */
	public void setMode(int mode);
}
