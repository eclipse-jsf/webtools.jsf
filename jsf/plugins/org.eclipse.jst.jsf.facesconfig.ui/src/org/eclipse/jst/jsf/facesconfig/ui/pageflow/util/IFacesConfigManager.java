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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

/**
 * @author Yang Liu
 * @version
 */
public interface IFacesConfigManager // extends ILifecycle
{
	public IProject getProject();

	/**
	 * 
	 * @return the array of the faces config files, it will not return null
	 */
	public IFile[] getFacesConfigs();

	public void addFacesConfigChangeListener(IFacesConfigChangeListener listener);

	public void removeFacesConfigChangeListener(
			IFacesConfigChangeListener listener);

	/**
	 * if the clients wants to change any of the faces-config file, should call
	 * this method.
	 * 
	 */
	// public void beginModify();
	//
	// /**
	// * after the client finish modify should call this method. The config
	// manager will save the
	// * config files and fire out change event to other clients.
	// *
	// */
	// public void endModify();
}
