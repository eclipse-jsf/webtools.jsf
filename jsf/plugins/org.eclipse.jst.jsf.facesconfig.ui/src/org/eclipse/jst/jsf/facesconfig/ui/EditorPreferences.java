/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;

public class EditorPreferences {
	// appearance
	public final static String USE_SYSTEM_COLORS = "UseSystemColors"; //$NON-NLS-1$

	public final static String CANVAS_COLOR = "CanvasColor"; //$NON-NLS-1$

	public final static String FIGURE_LABEL_FONT = "FigureLabelFont"; //$NON-NLS-1$

	public final static String FIGURE_LABEL_FONT_COLOR = "FigureLabelFontColor"; //$NON-NLS-1$

	public final static String LABEL_PLACEMENT = "LabelPlacement";

	public final static String INPUT_PORT_COLOR = "InputPortColor";

	public final static String OUTPUT_PORT_COLOR = "OutputPortColor";

	public final static String SHOW_LINE_LABELS = "ShowLineLabels";

	public final static String LINE_LABEL_FONT = "LineLabelFont"; //$NON-NLS-1$

	public final static String LINE_LABEL_FONT_COLOR = "LineLabelFontColor"; //$NON-NLS-1$

	public final static String LINE_LABEL_COLOR = "LineLabelColor"; //$NON-NLS-1$

	public final static String LINE_WIDTH = "LineWidth"; //$NON-NLS-1$

	public final static String LINE_COLOR = "LineColor"; //$NON-NLS-1$

	public final static String LINE_ROUTING = "LineRouting"; //$NON-NLS-1$

	public final static String SNAP_TO_GRID = "SnapToGrid"; //$NON-NLS-1$

	public final static String SNAP_TO_GEOMETRY = "SnapToGeometry"; //$NON-NLS-1$

	public final static String GRID_WIDTH = "GridWidth"; //$NON-NLS-1$

	public final static String GRID_HEIGHT = "GridHeight"; //$NON-NLS-1$

	public final static String GRID_COLOR = "GridColor"; //$NON-NLS-1$

	public final static String LABEL_PLACEMENT_TOP = "Top";

	public final static String LABEL_PLACEMENT_BOTTOM = "Bottom";

	public final static String LABEL_PLACEMENT_LEFT = "Left";

	public final static String LABEL_PLACEMENT_RIGHT = "Right";

	// "Direct" routing was intended for connections lines without bendpoints;
	// this has been removed because it is unnecessary.
	// public final static String LINE_ROUTING_DIRECT = "Direct";
	// "Manhattan" line routing creates orthogonal lines
	public final static String LINE_ROUTING_MANHATTAN = "Manhattan";

	// "Manual" routing allows user to create bendpoints
	public final static String LINE_ROUTING_MANUAL = "Manaul";

	public static Color getColor(IPreferenceStore store, String property) {
		boolean useSystemColors = store.getBoolean(USE_SYSTEM_COLORS);

		Color c = ColorConstants.black;
		if (useSystemColors) {
			if (GRID_COLOR.equals(property))
				c = ColorConstants.button;
			;
			if (LINE_COLOR.equals(property))
				c = ColorConstants.listForeground;
			if (LINE_LABEL_FONT_COLOR.equals(property))
				c = ColorConstants.listForeground;
			if (LINE_LABEL_COLOR.equals(property))
				c = ColorConstants.listBackground;
			if (CANVAS_COLOR.equals(property))
				c = ColorConstants.listBackground;
			if (INPUT_PORT_COLOR.equals(property))
				c = ColorConstants.listForeground;
			if (OUTPUT_PORT_COLOR.equals(property))
				c = ColorConstants.listForeground;
			if (FIGURE_LABEL_FONT_COLOR.equals(property))
				c = ColorConstants.listForeground;
		}

		return c;
	}
}
