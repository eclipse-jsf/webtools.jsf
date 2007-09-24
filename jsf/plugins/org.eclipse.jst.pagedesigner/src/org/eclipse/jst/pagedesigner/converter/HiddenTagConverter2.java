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

import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;

/**
 * HiddenTagConverter2 is similiar to HiddenTagConverter, with the following
 * difference:
 * 
 * <ol>
 * <li>HiddenTagConverter2 will copy the DOM sub tree to the destination
 * document.</li>
 * </ol>
 * 
 * TODO: this the wrong way to do this.  This should sub-class HiddenTagConverter
 * 
 * @author mengbo
 * @version 1.5
 */
public class HiddenTagConverter2 extends AbstractTagConverter {

	private Image _image;

	/**
	 * @param host
	 * @param image
	 */
	public HiddenTagConverter2(Element host, Image image) {
		super(host);
		this._image = image;
	}

	protected Element doConvertRefresh() {
		return (Element) DOMUtil.cloneNodeDeepIgnoreError(getDestDocument(),
				getHostElement());
	}

	public boolean isVisualByHTML() {
		return false;
	}

	public Image getVisualImage() {
		return _image;
	}

	public boolean isMultiLevel() {
		return true;
	}

	public boolean isWidget() {
		return true;
	}

}
