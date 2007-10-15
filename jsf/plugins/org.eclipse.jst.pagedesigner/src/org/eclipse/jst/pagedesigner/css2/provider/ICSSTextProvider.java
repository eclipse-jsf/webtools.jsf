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
package org.eclipse.jst.pagedesigner.css2.provider;

/**
 * This class behave as the information provider of CSSTextFigure.
 * 
 * @author mengbo
 */
public interface ICSSTextProvider {
	/**
	 * get the style.
	 * 
	 * @return can't be null
	 */
	// public ICSSStyle getCSSStyle();
	/**
	 * this is the final data to be displayed. There is no need for the
	 * CSSTextFigure to normalize it.
	 * 
	 * @return the text data
	 */
	public String getTextData();

	/**
	 * get the selected information.
	 * 
	 * @return the selected range
	 */
	public int[] getSelectedRange();
}
