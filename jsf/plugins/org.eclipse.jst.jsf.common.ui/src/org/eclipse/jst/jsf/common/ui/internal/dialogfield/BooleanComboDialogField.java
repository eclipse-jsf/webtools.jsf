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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;


/**
 * @author mengbo
 */
public class BooleanComboDialogField extends ComboDialogField {
	final public static int USE_YESNO = 0;

	final public static int USE_TRUEFALSE = 1;

	/**
	 * 
	 * @param type
	 */
	public void setType(int type) {
		if (type == USE_YESNO) {
			setItems(new String[] { "", "yes", "no" });
		} else if (type == USE_TRUEFALSE) {
			setItems(new String[] { "", "true", "false" });
		}
	}

	/**
	 * @param flags
	 */
	public BooleanComboDialogField(int flags) {
		super(flags);
		setType(USE_TRUEFALSE);
	}
}
