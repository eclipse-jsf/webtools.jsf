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
package org.eclipse.jst.jsf.facesconfig.ui.section;


/**
 * Section interface to build relation between model and adapter
 * 
 * @author sfshi
 * 
 */
public interface IFacesConfigSection {
	/**
	 * set the input of this section
	 * 
	 * @param newInput
	 */
	void setInput(Object newInput);

	/**
	 * get the input of this section
	 * 
	 * @return
	 */
	Object getInput();
	
	/**
	 * initialize current section based on the input
	 * 
	 */
	void initialize();

	/**
	 * clear all section's contents.
	 * 
	 */
	void clearAll();

	/**
	 * refresh the needed parts of the section
	 * 
	 */
	void refresh();

	/**
	 * refresh all parts of the section
	 * 
	 */
	void refreshAll();

}
