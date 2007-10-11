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

import org.eclipse.draw2d.LayoutManager;

/**
 * There are several kinds of layout involved. 1. the layout need let the child
 * figures do certain layouting of themselves first, then decide the final
 * result based on child information. 2. the layout could decide the size
 * information of this figure without child information.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface ICSSLayout extends LayoutManager {
	/**
	 * Each ICSSLayout is dedicated to a single CSSFigure.
	 * 
	 * @return the figure
	 */
	public ICSSFigure getICSSFigure();

	/**
	 * 
	 * @return
	 */
	// public List getFragmentsForRead();
	/**
	 * postValidate the child figures of this CSSFigure. Normally layout fall
	 * into the first category need implement this method.
	 */
	// public void postValidate();
	/**
	 * setBounds is called on the CSSFigure. Normally layout fall into the
	 * second category need implement this method.
	 * 
	 * @param rect
	 * @param invalidate
	 */
	// public void setBoundsCalled(Rectangle rect, boolean invalidate);
	/**
	 * @return
	 */
	// public boolean useLocalCoordinates();
}
