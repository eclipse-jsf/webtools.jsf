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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;

/**
 * The holder of faces config graphical editor configuration.
 * 
 * @author sfshi
 * 
 */
public class EditorPreferenceStore implements IPreferenceStore {

	public void addPropertyChangeListener(IPropertyChangeListener listener) {

	}

	public boolean contains(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public void firePropertyChangeEvent(String name, Object oldValue,
			Object newValue) {
		// TODO Auto-generated method stub

	}

	public boolean getBoolean(String name) {
		if (EditorPreferences.SNAP_TO_GEOMETRY.equals(name)) {
			return true;
		} else if (EditorPreferences.SNAP_TO_GRID.equals(name)) {
			return true;
		} else if (EditorPreferences.USE_SYSTEM_COLORS.equals(name)) {
			return true;
		} else if (EditorPreferences.SHOW_LINE_LABELS.equals(name)) {
			return true;
		}

		return false;
	}

	public boolean getDefaultBoolean(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public double getDefaultDouble(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getDefaultFloat(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDefaultInt(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getDefaultLong(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getDefaultString(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getDouble(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getFloat(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getInt(String name) {
		if (EditorPreferences.LINE_WIDTH.equals(name)) {
			return 1;
		} else if (EditorPreferences.GRID_WIDTH.equals(name)) {
			return 12;
		} else if (EditorPreferences.GRID_HEIGHT.equals(name)) {
			return 12;
		}
		return 0;
	}

	public long getLong(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getString(String name) {
		if (EditorPreferences.LINE_ROUTING.equals(name)) {
			// defaultly use manual, another value
			// is GEMPreferences.LINE_ROUTING_MANHATTAN
			return EditorPreferences.LINE_ROUTING_MANUAL;
		} else if (EditorPreferences.LABEL_PLACEMENT.equals(name)) {
			return EditorPreferences.LABEL_PLACEMENT_BOTTOM;
		} else if (EditorPreferences.LINE_LABEL_FONT.equals(name)) {
			return "1|Tahoma|8|0|WINDOWS|1|-11|0|0|0|400|0|0|0|1|0|0|0|0|Tahoma;";
		} else if (EditorPreferences.FIGURE_LABEL_FONT.equals(name)) {

			return "1|Tahoma|8|0|WINDOWS|1|-11|0|0|0|400|0|0|0|1|0|0|0|0|Tahoma;";
		}

		return null;
	}

	public boolean isDefault(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean needsSaving() {
		// TODO Auto-generated method stub
		return false;
	}

	public void putValue(String name, String value) {
		// TODO Auto-generated method stub

	}

	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	public void setDefault(String name, double value) {
		// TODO Auto-generated method stub

	}

	public void setDefault(String name, float value) {
		// TODO Auto-generated method stub

	}

	public void setDefault(String name, int value) {
		// TODO Auto-generated method stub

	}

	public void setDefault(String name, long value) {
		// TODO Auto-generated method stub

	}

	public void setDefault(String name, String defaultObject) {
		// TODO Auto-generated method stub

	}

	public void setDefault(String name, boolean value) {
		// TODO Auto-generated method stub

	}

	public void setToDefault(String name) {
		// TODO Auto-generated method stub

	}

	public void setValue(String name, double value) {
		// TODO Auto-generated method stub

	}

	public void setValue(String name, float value) {
		// TODO Auto-generated method stub

	}

	public void setValue(String name, int value) {
		// TODO Auto-generated method stub

	}

	public void setValue(String name, long value) {
		// TODO Auto-generated method stub

	}

	public void setValue(String name, String value) {
		// TODO Auto-generated method stub

	}

	public void setValue(String name, boolean value) {
		// TODO Auto-generated method stub

	}

}
