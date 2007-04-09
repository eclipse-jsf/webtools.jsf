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
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Image;

/**
 * This derived label has simple tooltip support
 * 
 * @author Xiao-guang Zhang
 * 
 */
public class NodeLabel extends Label {
	/**
	 * 
	 */
	public NodeLabel() {
		super();
	}

	/**
	 * @param s
	 */
	public NodeLabel(String s) {
		super(s);
	}

	/**
	 * @param i
	 */
	public NodeLabel(Image i) {
		super(i);
	}

	/**
	 * @param s
	 * @param i
	 */
	public NodeLabel(String s, Image i) {
		super(s, i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Label#setTextPlacement(int)
	 */
	public void setTextPlacement(int where) {
		super.setTextPlacement(where);
		layout();
		invalidate();
	}

	/**
	 * set tooltip text
	 * 
	 * @param text
	 */
	public void setToolTipText(String text) {
		Label toolTipLabel = null;

		if (text != null && text.length() > 0) {
			toolTipLabel = new Label(text);
			toolTipLabel.setBorder(new MarginBorder(3));
		}

		super.setToolTip(toolTipLabel);
	}

	/**
	 * get tooltip's text
	 * 
	 * @return the tool tip text string
	 */
	public String getToolTipText() {
		if (getToolTip() != null)
			return ((Label) getToolTip()).getText();
		return null;
	}
}
