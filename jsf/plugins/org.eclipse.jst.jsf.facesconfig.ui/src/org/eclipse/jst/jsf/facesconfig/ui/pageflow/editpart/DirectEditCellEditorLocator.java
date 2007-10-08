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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

/**
 * CellEditorLocator for Pageflow Node.
 * 
 * @author - Xiaoguang Zhang
 */
/*package*/ class DirectEditCellEditorLocator implements CellEditorLocator {
	/** the source label */
	private Label label;

	/**
	 * Creates a new DirectEditCellEditorLocator for the given Label
	 * 
	 * @param label
	 *            the Label
	 */
	public DirectEditCellEditorLocator(Label label) {
		setLabel(label);
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see CellEditorLocator#relocate(CellEditor)
	 */
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Point pref = text.computeSize(-1, -1);
		// get the label's boundary
		Rectangle rect = label.getTextBounds().getCopy();
		label.translateToAbsolute(rect);

		// because the label can be empty and the size is too small
		// here get the prefered text size according to label's size and default
		// size.
		int width, height;
		if (pref.x > rect.width) {
			width = pref.x;
		} else {
			width = rect.width;
		}

		if (pref.y > rect.height) {
			height = pref.y;
		} else {
			height = rect.height;
		}

		// set the boundary of the text control
		text.setBounds(rect.x - 1, rect.y - 1, width + 1, height + 1);
	}

	/**
	 * Returns the Label figure.
	 * 
	 * @return the Label
	 */
	protected Label getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            The label to set
	 */
	protected void setLabel(Label label) {
		this.label = label;
	}

}
