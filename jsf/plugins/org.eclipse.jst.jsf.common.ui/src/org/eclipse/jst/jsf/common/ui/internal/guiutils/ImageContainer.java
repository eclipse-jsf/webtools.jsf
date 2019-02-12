/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.guiutils;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * A general purpose class for displying an image in a composite. There is no
 * eclipse tool for doing this other than CLabl which is bulkly.
 * 
 * This was original written by Karl Reti.
 * 
 * @author mengbo
 */
public class ImageContainer extends Composite {
	private Image _image = null;

	private boolean _bCleanupImage;

	/**
	 * @param parent
	 */
	public ImageContainer(Composite parent) {
		super(parent, SWT.NONE);// SWT.NO_BACKGROUND );//|
		// SWT.NO_REDRAW_RESIZE);
		setBackground(ColorConstants.white);
		// paint the image
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (_image != null) {
					e.gc.drawImage(_image, 0, 0);
				}
			}

		});

		addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				setSize(_image.getBounds().width, _image.getBounds().height);
			}
		});

		// tidy up
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if (_bCleanupImage && _image != null && !_image.isDisposed()) {
					_image.dispose();
				}
			}
		});
	}

	/**
	 * @return Returns the image.
	 */
	public Image getImage() {
		return _image;
	}

	/**
	 * @return should the image be disposed of on cleanup. Set false if the
	 *         image is cached.
	 */
	public boolean isCleanupImage() {
		return _bCleanupImage;
	}

	/**
	 * @param image
	 *            The image to set.
	 */
	public void setImage(Image image) {
		setImage(image, false);
	}

	/**
	 * @param image
	 *            The image to set.
	 * @param bCleanupImage 
	 */
	public void setImage(Image image, boolean bCleanupImage) {
		_image = image;
		_bCleanupImage = bCleanupImage;
		setSize(image.getBounds().width, image.getBounds().height);
	}
}
