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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.provider.DimensionInfo;
import org.eclipse.swt.graphics.Image;

/**
 * @author mengbo
 */
public class ImageWidgetProvider extends AbstractWidgetProvider {
	private static final String NOPIC_IMAGE_NAME = "PD_nopic.jpg"; //$NON-NLS-1$

	private static Image _noPicImage;

	private static int _noPicWidth;

	private static int _noPicHeight;

	/**
	 * the image
	 */
	protected Image _image;

	/**
	 * image width
	 */
	protected int _imageWidth;

	/**
	 * image height
	 */
	protected int _imageHeight;

	/**
	 * @param image
	 * @param style
	 */
	public ImageWidgetProvider(Image image, ICSSStyle style) {
		super(style);

		// set up image and image width/height
		org.eclipse.swt.graphics.Rectangle rect = null;
		if (image != null) {
			rect = image.getBounds();
			if (rect.width <= 0 || rect.height <= 0) {
				useNoPicImage();
			} else {
				_image = image;
				_imageWidth = rect.width;
				_imageHeight = rect.height;
			}
		} else {
			useNoPicImage();
		}
	}

	/**
	 * 
	 */
	private void useNoPicImage() {
		if (_noPicImage == null || _noPicImage.isDisposed()) {
			ImageDescriptor noPicImageDesc = PDPlugin.getDefault()
					.getImageDescriptor(NOPIC_IMAGE_NAME);
			_noPicImage = noPicImageDesc.createImage();
			org.eclipse.swt.graphics.Rectangle rect = _noPicImage.getBounds();
			_noPicWidth = rect.width;
			_noPicHeight = rect.height;
		}
		_image = _noPicImage;
		_imageWidth = _noPicWidth;
		_imageHeight = _noPicHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#getPreferredDimension(int,
	 *      int)
	 */
	public DimensionInfo getPreferredDimension(int width, int height) {
		if (width <= 0 && height <= 0) {
			// we are free, so use image size
			org.eclipse.swt.graphics.Rectangle a = _image.getBounds();
			return new DimensionInfo(a.width, a.height, -1);
		} else if (width > 0 && height > 0) {
			return new DimensionInfo(width, height, -1);
		} else if (height > 0) {
			width = (int) ((double) _imageWidth * height / _imageHeight);
			return new DimensionInfo(width, height, -1);
		} else {
			height = (int) ((double) _imageHeight * width / _imageWidth);
			return new DimensionInfo(width, height, -1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#paintFigure(org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void paintFigure(Graphics g, Rectangle rect) {
		if (_image != null) {
			g.drawImage(_image, 0, 0, _imageWidth, _imageHeight, rect.x,
					rect.y, rect.width, rect.height);
		}
	}
}
