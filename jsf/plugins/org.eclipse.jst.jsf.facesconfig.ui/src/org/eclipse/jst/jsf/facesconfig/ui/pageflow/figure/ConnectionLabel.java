/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.figure;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * This label is used in connection figure, which has a border.
 * 
 * @author Xiao-guang Zhang
 * 
 */
public class ConnectionLabel extends Label {
	/**
	 * 
	 */
	public ConnectionLabel() {
		this(null, null);
	}

	/**
	 * @param s
	 */
	public ConnectionLabel(String s) {
		this(s, null);
	}

	/**
	 * @param i
	 */
	public ConnectionLabel(Image i) {
		this(null, i);
	}

	/**
	 * @param s
	 * @param i
	 */
	public ConnectionLabel(String s, Image i) {
		super(s, i);

		// setBorder(new LineBorder());
		setOpaque(true);
	}

	/**
	 * set the label's border's width
	 * 
	 * @param w
	 */
	public void setBorderWidth(int w) {
		if (getBorder() != null) {
			((LineBorder) getBorder()).setWidth(w);
		}
	}

	/**
	 * set the label's border's color
	 * 
	 * @param c
	 */
	public void setBorderColor(Color c) {
		if (getBorder() != null) {
			((LineBorder) getBorder()).setColor(c);
		}
	}
}
