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
package org.eclipse.jst.pagedesigner.css2.widget;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class AbstractWidgetProvider implements ICSSWidgetProvider {
	final static int BORDERTHICK = 2;

	private final ICSSStyle _style;

	/**
	 * @param style
	 */
	public AbstractWidgetProvider(ICSSStyle style) {
		_style = style;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#getCSSStyle()
	 */
	public ICSSStyle getCSSStyle() {
		return _style;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#isHandlingBorder()
	 */
	public boolean isHandlingBorder() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#isInline()
	 */
	public boolean isInline() {
		return true;
	}

}
