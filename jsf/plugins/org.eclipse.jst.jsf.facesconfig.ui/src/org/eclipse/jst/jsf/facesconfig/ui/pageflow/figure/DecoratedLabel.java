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
import org.eclipse.jdt.internal.ui.viewsupport.ImageImageDescriptor;
import org.eclipse.jdt.ui.JavaElementImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * 
 * The decorated label is the simple implementation of label with image and text
 * decoration.
 * 
 * @author Xiao-guang Zhang
 * 
 */
public class DecoratedLabel extends Label implements ILabelDecorator {
	/**
	 * 
	 */
	public DecoratedLabel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param s
	 */
	public DecoratedLabel(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param i
	 */
	public DecoratedLabel(Image i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param s
	 * @param i
	 */
	public DecoratedLabel(String s, Image i) {
		super(s, i);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.figures.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image,
	 *      java.lang.Object)
	 */
	public Image decorateImage(Image image, Object element) {
		int adornmentFlags = computeAdornmentFlags(element);
		if (adornmentFlags != 0) {
			ImageDescriptor baseImage = new ImageImageDescriptor(image);
			Rectangle bounds = image.getBounds();
			Image newImage = (new JavaElementImageDescriptor(baseImage,
					adornmentFlags, new Point(bounds.width, bounds.height)))
					.createImage();
			return newImage;
		}
		return image;
	}

	/**
	 * Note: This method is for internal use only. Clients should not call this
	 * method.
	 */
	protected int computeAdornmentFlags(Object obj) {
		return JavaElementImageDescriptor.WARNING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.figures.ILabelDecorator#decorateText(java.lang.String,
	 *      java.lang.Object)
	 */
	public String decorateText(String text, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
