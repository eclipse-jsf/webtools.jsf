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

import java.util.Vector;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LabelAnchor;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPreferences;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * The figure for pageflow node, used by pageflow node edit part.
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowNodeFigure extends Figure {
	/** Same connection anchors for source and target */
	protected Vector connectionAnchors = new Vector(1);

	/** label of the pageflow node figure */
	private NodeLabel label;

	/** the foreground color of Label */
	private Color labelFgColor;

	/** the background color of Label */
	private Color labelBgColor;

	/** the text placement for the label */
	private int textPlacement = PositionConstants.SOUTH;

	/** the label's font */
	private Font labelFont;

	public PageflowNodeFigure() {
		setLayoutManager(new FlowLayout());

		// initializeConnectionAnchors();
	}

	/**
	 * get the source(output) anchor according to the input point.
	 * 
	 * @param p -
	 *            the input point
	 * @return - Source ConnectionAnchor
	 */
	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		if (getSourceConnectionAnchors().size() == 0) {
			return null;
		}
		return (ConnectionAnchor) getSourceConnectionAnchors().get(0);
	}

	/**
	 * get the default source anchor
	 * 
	 * @return - Source ConnectionAnchor
	 */
	public ConnectionAnchor getSourceConnectionAnchor() {
		if (getSourceConnectionAnchors().size() == 0) {
			return null;
		}
		return (ConnectionAnchor) getSourceConnectionAnchors().get(0);
	}

	/**
	 * Get the source connection anchors vector
	 * 
	 * @return - the vector
	 */
	private Vector getSourceConnectionAnchors() {
		return connectionAnchors;
	}

	/**
	 * get the target (input) connection Anchor according the input point
	 * 
	 * @param p -
	 *            the input Point
	 * @return - ConnectionAnchor
	 */
	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		if (getTargetConnectionAnchors().size() == 0) {
			return null;
		}
		return (ConnectionAnchor) getTargetConnectionAnchors().get(0);
	}

	/**
	 * get the default target (input) connection Anchor
	 * 
	 * @return - ConnectionAnchor
	 */
	public ConnectionAnchor getTargetConnectionAnchor() {
		if (getTargetConnectionAnchors().size() == 0) {
			return null;
		}
		return (ConnectionAnchor) getTargetConnectionAnchors().get(0);
	}

	/**
	 * Get the target connection anchors vector
	 * 
	 * @return - the vector
	 */
	private Vector getTargetConnectionAnchors() {
		return connectionAnchors;
	}

	/**
	 * update the anchors
	 * 
	 */
	public void update() {
		initializeConnectionAnchors();
	}

	/**
	 * Initialize the connection anchors
	 * 
	 */
	private void initializeConnectionAnchors() {
		connectionAnchors.removeAllElements();
		ChopboxAnchor inputConnectionAnchor = new LabelAnchor(label);
		connectionAnchors.addElement(inputConnectionAnchor);
	}

	/**
	 * get the text placement from preference.
	 * 
	 */
	private int getTextPlacement() {
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		String s = store.getString(EditorPreferences.LABEL_PLACEMENT);
		if (EditorPreferences.LABEL_PLACEMENT_TOP.equals(s)) {
			textPlacement = PositionConstants.NORTH;
		}
		if (EditorPreferences.LABEL_PLACEMENT_BOTTOM.equals(s)) {
			textPlacement = PositionConstants.SOUTH;
		}
		if (EditorPreferences.LABEL_PLACEMENT_LEFT.equals(s)) {
			textPlacement = PositionConstants.WEST;
		}
		if (EditorPreferences.LABEL_PLACEMENT_RIGHT.equals(s)) {
			textPlacement = PositionConstants.EAST;
		}
		return textPlacement;
	}

	/**
	 * get the label's background color from preference.
	 * 
	 * @return
	 */
	private Color getLabelBackgroundColor() {
		return labelBgColor;
	}

	/**
	 * get the label's foreground color from preference.
	 * 
	 * @return
	 */
	private Color getLabelForegroundColor() {
		if (labelFgColor == null) {
			IPreferenceStore store = EditorPlugin.getDefault()
					.getPreferenceStore();
			labelFgColor = EditorPreferences.getColor(store,
					EditorPreferences.FIGURE_LABEL_FONT_COLOR);
		}
		return labelFgColor;
	}

	/**
	 * get the labe's font from preference.
	 * 
	 * @return
	 */
	private Font getLabelFont() {
		if (labelFont == null) {
			IPreferenceStore store = EditorPlugin.getDefault()
					.getPreferenceStore();
			labelFont = new Font(null, PreferenceConverter.getFontData(store,
					EditorPreferences.FIGURE_LABEL_FONT));
		}
		return labelFont;
	}

	/**
	 * set the image and text of the figure
	 * 
	 * @param image -
	 *            the image
	 * @param str -
	 *            the label
	 */
	public void setImageText(Image image, String str) {
		if (image == null) {
			setText(str);
		} else if (str == null) {
			setImage(image);
		} else {
			if (label == null) {
				label = new NodeLabel(str, image);
				label.setTextPlacement(getTextPlacement());
				label.setForegroundColor(getLabelForegroundColor());
				label.setFont(getLabelFont());
				add(label);
				initializeConnectionAnchors();
			} else {
				label.setIcon(image);
				label.setText(str);
			}
		}
	}

	/**
	 * set the image of the figure
	 * 
	 * @param image -
	 *            the image
	 */
	public void setImage(Image image) {
		if (image == null) {
			return;
		}
		if (label == null) {
			label = new NodeLabel(image);
			label.setTextPlacement(getTextPlacement());
			label.setForegroundColor(getLabelForegroundColor());
			label.setFont(getLabelFont());
			add(label);
			initializeConnectionAnchors();
		} else {
			label.setIcon(image);
		}
	}

	/**
	 * set the text of the figure
	 * 
	 * @param str -
	 *            the text
	 */
	public void setText(String str) {
		if (str == null) {
			return;
		}
		if (label == null) {
			label = new NodeLabel(str);
			label.setTextPlacement(getTextPlacement());
			label.setForegroundColor(getLabelForegroundColor());
			label.setFont(getLabelFont());
			add(label);
			initializeConnectionAnchors();
		} else {
			label.setText(str);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
	 */
	protected boolean useLocalCoordinates() {
		return true;
	}

	/**
	 * Returns the imageBounds.
	 * 
	 * @return - Rectangle of image
	 */
	public Rectangle getImageBounds() {
		return label.getIconBounds();
	}

	/**
	 * Returns the textBounds.
	 * 
	 * @return - Rectangle of text field
	 */
	public Rectangle getTextBounds() {
		return label.getTextBounds();
	}

	/**
	 * Returns the label.
	 * 
	 * @return - Label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * set the label's backaground
	 */
	public void setBackgroundColor(Color bg) {
		labelBgColor = bg;
		if (label != null) {
			label.setBackgroundColor(bg);
		}
	}

	/**
	 * set the label's foreground
	 */
	public void setForegroundColor(Color fg) {
		labelFgColor = fg;

		if (label != null) {
			label.setForegroundColor(fg);
		}
	}

	/**
	 * Set the label's font
	 */
	public void setFont(Font f) {
		if (label != null) {
			label.setFont(f);
		}
		super.setFont(f);
	}

	/**
	 * Set the text placement for the label
	 * 
	 * @param where
	 */
	public void setTextPlacement(int where) {
		textPlacement = where;
		if (label != null) {
			label.setTextPlacement(where);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setToolTipText()
	 */
	public void setToolTipText(String text) {
		Label toolTip = null;

		if (text != null && text.length() > 0) {
			toolTip = new Label(text);
			toolTip.setBorder(new MarginBorder(3));
		}

		super.setToolTip(toolTip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getToolTipText()
	 */
	public String getToolTipText() {
		if (getToolTip() != null) {
			return ((Label) getToolTip()).getText();
		}
		return null;
	}
}
