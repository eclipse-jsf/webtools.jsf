/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.ui.dialogs;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * @author mengbo
 * @version 1.5
 */
public class StyleCombo extends Combo {

	/**
	 * @param parent
	 * @param style
	 */
	public StyleCombo(Composite parent, int style) {
		super(parent, style);
		this.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Combo combo = (Combo) e.widget;
				combo.setSelection(new Point(0, 0));
			}
		});
	}

	protected void checkSubclass() {
        // override subclass validation, otherwise Widget.isValidSubclass
        // will throw an error
	}
}
