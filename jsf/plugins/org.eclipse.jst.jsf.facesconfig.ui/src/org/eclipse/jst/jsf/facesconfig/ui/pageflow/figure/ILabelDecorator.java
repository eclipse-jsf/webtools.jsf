/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.figure;

import org.eclipse.swt.graphics.Image;

/**
 * A label decorator decorates the label text and image for some element. The
 * original label text and image are obtained by some other means,
 * 
 * 
 * @author Xiao-guang Zhang
 */
public interface ILabelDecorator {
	/**
	 * Returns an image that is based on the given image, but decorated with
	 * additional information relating to the state of the provided element.
	 * 
	 * @param image
	 *            the input image to decorate, or <code>null</code> if the
	 *            element has no image
	 * @param element
	 *            the element whose image is being decorated
	 * @return the decorated image, or <code>null</code> if no decoration is
	 *         to be applied
	 * 
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor
	 */
	public Image decorateImage(Image image, Object element);

	/**
	 * Returns a text label that is based on the given text label, but decorated
	 * with additional information relating to the state of the provided
	 * element.
	 * 
	 * 
	 * @param text
	 *            the input text label to decorate
	 * @param element
	 *            the element whose image is being decorated
	 * @return the decorated text label, or <code>null</code> if no decoration
	 *         is to be applied
	 */
	public String decorateText(String text, Object element);

}
