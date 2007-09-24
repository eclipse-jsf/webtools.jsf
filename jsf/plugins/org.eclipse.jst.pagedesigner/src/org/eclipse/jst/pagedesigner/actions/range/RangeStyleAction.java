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
package org.eclipse.jst.pagedesigner.actions.range;

import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;

/**
 * @author mengbo
 * @version 1.5
 */
public class RangeStyleAction extends Action {
	/**
	 * @param text
	 * @param range 
	 * @param htmlTag 
	 * @param cssProperty 
	 * @param cssValue 
	 */
	public RangeStyleAction(String text, DesignRange range, String htmlTag,
			String cssProperty, String cssValue) {
		super(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		if (isChecked()) {
			// un-apply the style here
		} else {
			// apply the style here.
		}
	}
}
