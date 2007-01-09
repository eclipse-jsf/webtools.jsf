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
package org.eclipse.jst.pagedesigner.css2.style;

import java.util.Map;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.font.CSSFontManager;
import org.eclipse.jst.pagedesigner.css2.font.ICSSFont;
import org.eclipse.jst.pagedesigner.css2.list.ICounterValueGenerator;
import org.eclipse.jst.pagedesigner.css2.property.CSSMetaRegistry;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;

/**
 * @author mengbo
 */
public class DefaultStyle implements ICSSStyle {

	private static final Insets EMPTY_INSETS = new Insets();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#reset()
	 */
	public void reset() {
        // TODO: dead?        
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getStyleProperty(java.lang.String)
	 */
	public Object getStyleProperty(String property) {
		ICSSPropertyMeta meta = CSSMetaRegistry.getInstance().getMeta(property);
		if (meta == null) {
			return ICSSPropertyMeta.NOT_SPECIFIED;
		}
        return meta.getInitialValue(property, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getMarginInsets()
	 */
	public Insets getMarginInsets() {
		//return EMPTY_INSETS;
        return new Insets(ARTIFICIAL_MARGIN_OFFSET, ARTIFICIAL_MARGIN_OFFSET, ARTIFICIAL_MARGIN_OFFSET,ARTIFICIAL_MARGIN_OFFSET);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getBorderInsets()
	 */
	public Insets getBorderInsets() {
		return EMPTY_INSETS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getPaddingInsets()
	 */
	public Insets getPaddingInsets() {
		return EMPTY_INSETS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#isSizeIncludeBorderPadding()
	 */
	public boolean isSizeIncludeBorderPadding() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#dispose()
	 */
	public void dispose() {
        // TODO: anything to dispose
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.sse.model.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return type == ICSSStyle.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.sse.model.INodeAdapter#notifyChanged(com.ibm.sse.model.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
        // TODO: do nothing?
	}

	private ICSSFont defaultFont = CSSFontManager.getInstance().createDefaultFont();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getCSSFont()
	 */
	public ICSSFont getCSSFont() {
		return defaultFont;
	}

	/**
	 * @return
	 */
	public static ICSSStyle getInstance() {
		if (_instance == null) {
			_instance = new DefaultStyle();
		}
		return _instance;
	}

	static DefaultStyle _instance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getParentStyle()
	 */
	public ICSSStyle getParentStyle() {
		// return this;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getBackgroundColor()
	 */
	public Object getBackgroundColor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getColor()
	 */
	public Object getColor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getDisplay()
	 */
	public String getDisplay() {
		return "inline";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getListStyle()
	 */
	public Map getCounters() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getCounter(java.lang.String)
	 */
	public ICounterValueGenerator findCounter(String name, boolean must) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getColSpan()
	 */
	public int getColSpan() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getRowSpan()
	 */
	public int getRowSpan() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#isInSelection()
	 */
	public boolean isInSelection() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#resetCounters()
	 */
	public void processCounters() {
		// do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.ICSSStyle#getHTMLelementValue(java.lang.String)
	 */
	public Object getHTMLelementInitValue(String propertyName) {
		ICSSPropertyMeta meta = CSSMetaRegistry.getInstance().getMeta(
				propertyName);
		if (meta == null) {
			return ICSSPropertyMeta.NOT_SPECIFIED;
		}
        return meta.getInitialValue(propertyName, this);
	}
}
