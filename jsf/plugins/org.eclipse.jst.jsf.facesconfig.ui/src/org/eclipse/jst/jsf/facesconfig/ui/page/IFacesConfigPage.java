/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.page;

import org.eclipse.ui.forms.editor.IFormPage;

/**
 * Implemented by IFormPage's that want to support EditorInput-like
 * model set/get/refresh
 * 
 * @author sfshi
 * 
 */
public interface IFacesConfigPage extends IFormPage {

	/**
	 * Set the input.  Implementer may choose to ignore
	 * 
	 * @param input
	 */
	void setInput(Object input);

	/**
	 * @return the input or null if none set
	 */
	Object getInput();

	/**
	 * signal to a form page that the data in its input has changed
	 * and it should refresh its view.
	 */
	void refresh();

}
