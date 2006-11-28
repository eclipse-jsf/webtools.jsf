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

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The text input only allows numbers
 * 
 * @author mengbo
 * @version 1.5
 */
public class NumberDialogField extends StringDialogField {
	public Control[] doFillIntoGrid(FormToolkit kit, Composite parent,
			int nColumns) {
		Control[] controls = super.doFillIntoGrid(kit, parent, nColumns);

		getTextControl(kit, parent).addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String str = ((Text) e.getSource()).getText();
				String newString = str.substring(0, e.start) + e.text
						+ str.substring(e.end, str.length());
				String regExpression = "^[1-9][0-9]*";
				e.doit = (newString.length() == 0 || newString
						.matches(regExpression));
			}
		});

		return controls;
	}
}
