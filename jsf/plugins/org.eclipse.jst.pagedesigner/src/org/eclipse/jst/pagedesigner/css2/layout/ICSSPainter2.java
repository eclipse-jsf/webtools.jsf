/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.css2.layout;

import org.eclipse.draw2d.Graphics;

/**
 * If the layout implements this interface, then it will have chance to paint
 * something to override children effect.
 * 
 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSPainter
 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSFigure
 * 
 * @author mengbo
 * @version 1.5
 */
public interface ICSSPainter2 {
	/**
	 * this method is called after <code>paintClientArea</code>. So it is
	 * called after children. Thus, it could override some children effects.
	 * 
	 * @param g
	 */
	public void paintFigurePostClientArea(Graphics g);
}
