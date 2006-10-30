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

import org.eclipse.draw2d.Graphics;

/**
 * @author mengbo
 */
public interface ICSSPainter {
	/**
	 * this method is called in the figure's <code>paintFigure</code> method,
	 * before <code>paintClientArea</code>. So it is called before children.
	 * Thus, children may override its effects.
	 * 
	 * @param g
	 */
	public void paintFigure(Graphics g);

}
