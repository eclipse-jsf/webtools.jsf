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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Translatable;

/**
 * @author mengbo
 * @version 1.5
 */
public  final class FigureUtil {
	/**
	 * @param figure
	 * @param t
	 */
    // XXX:
    // seemed Figure.translateToRelative is bug?
	public static final void translateToRelative(IFigure figure, Translatable t) {
		if (figure.getParent() != null) {
			translateToRelative(figure.getParent(), t);
			// figure.getParent().translateToRelative(t);
			figure.translateFromParent(t);
		}
	}

	/**
	 * @param figure
	 * @param t
	 */
    // XXX:
    // seemed Figure.translateToAbsolute is bug?
	public static final void translateToAbsolute(IFigure figure, Translatable t) {
		if (figure.getParent() != null) {
			figure.translateToParent(t);
			translateToAbsolute(figure.getParent(), t);
			// figure.getParent().translateToAbsolute(t);
		}

	}
	
	private FigureUtil()
	{
	    // util class.  No instantiation
	}
}
