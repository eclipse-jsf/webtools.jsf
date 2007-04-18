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
