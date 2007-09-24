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

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This is for those tags that don't convert to HTML. So they will not display
 * anything in preview, and will display a small icon in designer.
 * 
 * @author mengbo
 * @version 1.5
 */
public class HiddenTagConverter implements ITagConverter {
	private Element _hostElement;

    private ILabelProvider         _labelProvider;

	private int _mode;

	/**
	 * @param host 
	 * @param labelProvider 
	 * 
	 */
	public HiddenTagConverter(Element host, ILabelProvider labelProvider) {
		_hostElement = host;
		//_image = image;
        _labelProvider = labelProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#setDestDocument(org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument)
	 */
	public void setDestDocument(IDOMDocument document) {
        // do nothing?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#convertRefresh(java.lang.Object)
	 */
	public void convertRefresh(Object context) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getHostElement()
	 */
	public Element getHostElement() {
		return _hostElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isVisualByHTML()
	 */
	public boolean isVisualByHTML() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getVisualImage()
	 */
	public Image getVisualImage() {
        // defer the creation of the image until it is needed
		return _labelProvider.getImage(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getResultElement()
	 */
	public Element getResultElement() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getChildModeList()
	 */
	public List getChildModeList() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getChildVisualPosition(org.w3c.dom.Node)
	 */
	public ConvertPosition getChildVisualPosition(Node childModel) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isWidget()
	 */
	public boolean isWidget() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#dispose()
	 */
	public void dispose() {
        // do nothing
        // TODO: dispose of image or Element here?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#setMode(int)
	 */
	public void setMode(int mode) {
		this._mode = mode;
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return _mode;
	}

    public List getNonVisualChildren() {
        return Collections.EMPTY_LIST;
    }
}
