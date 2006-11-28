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
public interface IDialogFieldApplyListener {
	/**
	 * when the DialogField believe it should apply its value in UI, will fire
	 * this event.
	 * 
	 * Normally, for text input, when user is typing, will only fire
	 * dialogFieldChanged(), when lose focus will fire dialogFieldApplied().
	 * 
	 * For other control, such as readonly combo box, whenever user change
	 * selection, will fire both dialogFieldChanged() and dialogFieldApplied()
	 * 
	 * @param field
	 */
	void dialogFieldApplied(DialogField field);
}
