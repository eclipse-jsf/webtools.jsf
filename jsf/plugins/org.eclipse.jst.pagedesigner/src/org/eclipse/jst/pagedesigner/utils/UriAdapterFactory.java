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
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.wst.sse.core.internal.util.URIResolver;

/**
 * @author mengbo comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class UriAdapterFactory implements IAdapterFactory {
	/** Create the logger for this class */
	//private static Logger _log = PDPlugin.getLogger(UriAdapterFactory.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object,
	 *      java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (URIResolver.class.equals(adapterType)) {
			IProject project = (IProject) adaptableObject;
			URIResolver fProjectResolver = new ProjectResolver(project);
			return fProjectResolver;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		Class[] classes = new Class[1];
		classes[0] = URIResolver.class;
		return classes;
	}

}
