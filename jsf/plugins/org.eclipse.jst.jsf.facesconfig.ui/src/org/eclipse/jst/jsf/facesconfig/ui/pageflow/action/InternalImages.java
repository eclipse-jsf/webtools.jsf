/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.action;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Supplies image descriptors, intended for internal use only.
 * 
 * @author Ian Trimble - Oracle
 */
public class InternalImages {

	/**
	 * ImageDescriptor instance for center alignment image.
	 */
	public static final ImageDescriptor DESC_HORZ_ALIGN_CENTER;

	/**
	 * ImageDescriptor instance for left alignment image.
	 */
	public static final ImageDescriptor DESC_HORZ_ALIGN_LEFT;

	/**
	 * ImageDescriptor instance for right alignment image.
	 */
	public static final ImageDescriptor DESC_HORZ_ALIGN_RIGHT;

	/**
	 * ImageDescriptor instance for middle alignment image.
	 */
	public static final ImageDescriptor DESC_VERT_ALIGN_MIDDLE;

	/**
	 * ImageDescriptor instance for top alignment image.
	 */
	public static final ImageDescriptor DESC_VERT_ALIGN_TOP;

	/**
	 * ImageDescriptor instance for bottom alignment image.
	 */
	public static final ImageDescriptor DESC_VERT_ALIGN_BOTTOM;

	/**
	 * ImageDescriptor instance for center alignment image (disabled).
	 */
	public static final ImageDescriptor DESC_HORZ_ALIGN_CENTER_DIS;

	/**
	 * ImageDescriptor instance for left alignment image (disabled).
	 */
	public static final ImageDescriptor DESC_HORZ_ALIGN_LEFT_DIS;

	/**
	 * ImageDescriptor instance for right alignment image (disabled).
	 */
	public static final ImageDescriptor DESC_HORZ_ALIGN_RIGHT_DIS;

	/**
	 * ImageDescriptor instance for middle alignment image (disabled).
	 */
	public static final ImageDescriptor DESC_VERT_ALIGN_MIDDLE_DIS;

	/**
	 * ImageDescriptor instance for top alignment image (disabled).
	 */
	public static final ImageDescriptor DESC_VERT_ALIGN_TOP_DIS;

	/**
	 * ImageDescriptor instance for bottom alignment image (disabled).
	 */
	public static final ImageDescriptor DESC_VERT_ALIGN_BOTTOM_DIS;

	static {
		DESC_VERT_ALIGN_BOTTOM = createDescriptor("icons/alignbottom.gif"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_CENTER = createDescriptor("icons/aligncenter.gif"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_LEFT = createDescriptor("icons/alignleft.gif"); //$NON-NLS-1$
		DESC_VERT_ALIGN_MIDDLE = createDescriptor("icons/alignmid.gif"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_RIGHT = createDescriptor("icons/alignright.gif"); //$NON-NLS-1$
		DESC_VERT_ALIGN_TOP = createDescriptor("icons/aligntop.gif"); //$NON-NLS-1$
		DESC_VERT_ALIGN_BOTTOM_DIS = createDescriptor("icons/alignbottom_d.gif"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_CENTER_DIS = createDescriptor("icons/aligncenter_d.gif"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_LEFT_DIS = createDescriptor("icons/alignleft_d.gif"); //$NON-NLS-1$
		DESC_VERT_ALIGN_MIDDLE_DIS = createDescriptor("icons/alignmid_d.gif"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_RIGHT_DIS = createDescriptor("icons/alignright_d.gif"); //$NON-NLS-1$
		DESC_VERT_ALIGN_TOP_DIS = createDescriptor("icons/aligntop_d.gif"); //$NON-NLS-1$
	}

	private static ImageDescriptor createDescriptor(String filename) {
		return ImageDescriptor.createFromFile(InternalImages.class, filename);
	}

}
