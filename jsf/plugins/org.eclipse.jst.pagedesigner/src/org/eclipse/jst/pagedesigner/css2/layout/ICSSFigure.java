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
package org.eclipse.jst.pagedesigner.css2.layout;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;

/**
 * @author mengbo
 */
public interface ICSSFigure extends IFigure {
	/**
	 * get fragments of this figure. Each item of the list will be a FlowBox.
	 * Note, this method is for read only, caller should not change the returned
	 * list and items in the returned list.
	 * 
	 * @return the list of fragments
	 */
	public List getFragmentsForRead();

	/**
	 * get the CSSStyle of this CSS figure.
	 * 
	 * @return the css style
	 */
	public ICSSStyle getCSSStyle();
}
