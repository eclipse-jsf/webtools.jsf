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
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowElementEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * The figure for pflink, used by pflink edit part, which is a polyline with a
 * label control on it, and with tooltip support.
 * 
 * @author Xiao-guang Zhang
 */
public class PFLinkFigure extends PolylineConnection {
	/** the label attached to the connection */
	private ConnectionLabel textLabel = null;

	//private ConnectionLabel iconLabel = null;

	/** The label's background color */
	private Color labelBgColor;

	/** The label's foreground color */
	private Color labelFgColor;

	/** The label's visiblity */
	private boolean bLabelVisible = false;

	/** The connection's line width */
	private int lineWidth = 0;

	/** the polygon decoration for connection line */
	private PolygonDecoration arrow;

	/**
	 * 
	 */
	public PFLinkFigure() {
		arrow = new PolygonDecoration();
		arrow.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		arrow.setScale(10, getLineWidth() * 2);
		setTargetDecoration(arrow);
		setForegroundColor(getForegroundColor());
	}

	/**
	 * set the connection's label string
	 * 
	 * @param strLabel
	 */
	public void setLabel(String strLabel) {
		if (null != strLabel) {
			if (textLabel == null) {
				textLabel = new ConnectionLabel();
				textLabel.setFont(getLabelFont());
				textLabel.setBackgroundColor(getLabelBackgroundColor());
				textLabel.setForegroundColor(getLabelForegroundColor());
				setLineWidth(getLineWidth());
				setLabelVisible(getLabelVisible());
				setForegroundColor(getForegroundColor());
				add(textLabel, new MidpointLocator(this, 1));
			}
			// textLabel.setVisible(true);
			textLabel.setText(strLabel);
		}
	}

	public void clearIcon() {
		setImage(null);
	}

	public void clearOutcome() {
		setLabel("");
	}

	/**
	 * set the connection's label string
	 * 
	 * @param strLabel
	 */
	public void setImage(Image image) {
		if (textLabel == null) {
			textLabel = new ConnectionLabel();
			textLabel.setFont(getLabelFont());
			textLabel.setBackgroundColor(getLabelBackgroundColor());
			textLabel.setForegroundColor(getLabelForegroundColor());
			setLineWidth(getLineWidth());
			setLabelVisible(getLabelVisible());
			setForegroundColor(getForegroundColor());
			add(textLabel, new MidpointLocator(this, 0));
		}
		textLabel.setIcon(image);
	}

	public void setActionImage() {
		ImageDescriptor imageDescriptor = PageflowElementEditPart.IMG_ACTION;
		Image image = EditorPlugin.getDefault().getImageRegistry().get(
				imageDescriptor.toString());
		if (null == image) {
			EditorPlugin.getDefault().getImageRegistry().put(
					imageDescriptor.toString(), imageDescriptor);
			image = EditorPlugin.getDefault().getImageRegistry().get(
					imageDescriptor.toString());
		}
		setImage(image);
	}

	public Image getImage() {
		if (textLabel != null)
        {
			return textLabel.getIcon();
        }
        return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setToolTipText()
	 */
	public void setToolTipText(String text) {
		Label toolTipLabel = null;

		if (text != null && text.length() > 0) {
			toolTipLabel = new Label(text);
			toolTipLabel.setBorder(new MarginBorder(3));
		}

		super.setToolTip(toolTipLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getToolTipText()
	 */
	public String getToolTipText() {
		if (getToolTip() != null)
			return ((Label) getToolTip()).getText();
		return null;
	}

	/**
	 * set the font including label's
	 * 
	 */
	public void setFont(Font f) {
		if (textLabel != null)
			textLabel.setFont(f);
		super.setFont(f);
	}

	/**
	 * get the label's font from preference
	 * 
	 */
	private Font getLabelFont() {
		FontRegistry registry = JFaceResources.getFontRegistry();
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		FontData fontData = PreferenceConverter.getFontData(store,
				GEMPreferences.LINE_LABEL_FONT);
		if (!registry.get(fontData.toString()).equals(registry.defaultFont()))
			return registry.get(fontData.toString());
		
		registry.put(fontData.toString(), new FontData[] {fontData});
		return registry.get(fontData.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Shape#setLineWidth(int)
	 */
	public void setLineWidth(int w) {
		if (textLabel != null)
			textLabel.setBorderWidth(w);
		arrow.setScale(10, getLineWidth() * 2);
		super.setLineWidth(w);
	}

	/**
	 * get the connection line width from preference.
	 */
	public int getLineWidth() {
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		lineWidth = store.getInt(GEMPreferences.LINE_WIDTH);
		return lineWidth;
	}

	/**
	 * set label's visibility
	 * 
	 * @param flag -
	 *            visible or not.
	 */
	public void setLabelVisible(boolean flag) {
		if (textLabel != null)
			textLabel.setVisible(flag);
	}

	/**
	 * get the label's visiblity from preference
	 * 
	 */
	private boolean getLabelVisible() {
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		bLabelVisible = store.getBoolean(GEMPreferences.SHOW_LINE_LABELS);
		return bLabelVisible;
	}

	/**
	 * set the label's foreground color
	 * 
	 * @param c
	 */
	public void setLabelForegroundColor(Color c) {
		labelFgColor = c;
		if (textLabel != null)
			textLabel.setForegroundColor(c);
	}

	/**
	 * get the label's foreground color from preference
	 * 
	 */
	private Color getLabelForegroundColor() {
		if (labelFgColor == null) {
			IPreferenceStore store = EditorPlugin.getDefault()
					.getPreferenceStore();
			labelFgColor = GEMPreferences.getColor(store,
					GEMPreferences.LINE_LABEL_FONT_COLOR);
		}
		return labelFgColor;
	}

	/**
	 * set the label's background color
	 * 
	 * @param c
	 */
	public void setLabelBackgroundColor(Color c) {
		labelBgColor = c;
		if (textLabel != null)
			textLabel.setBackgroundColor(c);
	}

	/**
	 * get the label's background color from preference
	 * 
	 */
	private Color getLabelBackgroundColor() {
		if (labelBgColor == null) {
			IPreferenceStore store = EditorPlugin.getDefault()
					.getPreferenceStore();
			labelBgColor = GEMPreferences.getColor(store,
					GEMPreferences.LINE_LABEL_COLOR);
		}
		return labelBgColor;
	}

	/**
	 * get the foreground color from preference
	 * 
	 */
	public Color getForegroundColor() {
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		final Color newFgColor = GEMPreferences.getColor(store, GEMPreferences.LINE_COLOR);
        setForegroundColor(newFgColor);
		return newFgColor;
	}

	/**
	 * set the figure's foreground, which will also update the label's border's
	 * color.
	 */
	public void setForegroundColor(Color c) {
		if (textLabel != null)
			textLabel.setBorderColor(c);
		super.setForegroundColor(c);
	}
}
