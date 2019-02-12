/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.model;

/**
 * A simplified representation of a "selectItem" tag's most useful properties.
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectItem {

	private String label;
	private String value;

	/**
	 * Creates an instance.
	 * 
	 * @param label The label.
	 * @param value The value.
	 */
	public SelectItem(String label, String value) {
		setLabel(label);
		setValue(value);
	}

	/**
	 * Sets the label of this instance.
	 * 
	 * @param label The new label.
	 */
	public void setLabel(String label) {
		if (label != null) {
			this.label = label;
		} else {
			this.label = ""; //$NON-NLS-1$
		}
	}

	/**
	 * Gets the label of this instance.
	 * 
	 * @return The current label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the value of this instance.
	 * 
	 * @param value The new value.
	 */
	public void setValue(String value) {
		if (value != null) {
			this.value = value;
		} else {
			this.value = ""; //$NON-NLS-1$
		}
	}

	/**
	 * Gets the value of this instance.
	 * 
	 * @return The current value.
	 */
	public String getValue() {
		return value;
	}

}
