/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.actions;

/**
 * Editors should implement this if they can handle reseting to a page based on
 * the class defined for that page.
 * 
 * @author collinsc,jchoi
 */
public interface IOpenPage {
	/**
	 * Sets the currently active page from the id of the page.
	 * 
	 * @param pageID
	 */
	public void setActiveEditorPage(String pageID);

}
