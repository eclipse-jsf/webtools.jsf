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
package org.eclipse.jst.pagedesigner.figurehandler;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class AbstractFigureHandler implements IFigureHandler {
	private CSSFigure _figure;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return (type == IFigureHandler.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
        // TODO: anything?
	}

	/**
	 * @param node
	 * @return the css style on the node
	 */
	protected ICSSStyle getCSSStyle(Element node) {
		ICSSStyle style = null;
		if (node instanceof IDOMElement) {
			style = (ICSSStyle) ((IDOMElement) node)
					.getAdapterFor(ICSSStyle.class);
		}
		if (style == null) {
			style = DefaultStyle.getInstance();
		} 
        
        return style;
	}

	/**
	 * @param oldFigure
	 */
	protected void setCurrentFigure(CSSFigure oldFigure) {
		this._figure = oldFigure;
	}

	public CSSFigure getFigure() {
		return this._figure;
	}

	/**
	 * child class could override this method
	 */
	public void dispose() {
        // TODO: anything to dispose?
	}
}
