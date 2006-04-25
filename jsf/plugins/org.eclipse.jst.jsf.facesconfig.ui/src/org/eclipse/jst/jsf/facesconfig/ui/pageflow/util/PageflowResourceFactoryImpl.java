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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

/**
 * The <b>Resource Factory</b> associated with the package.
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowResourceFactoryImpl extends XMLResourceFactoryImpl {
	/**
	 * Creates an instance of the resource factory.
	 */
	public PageflowResourceFactoryImpl() {
		super();
	}

	/**
	 * Creates an instance of the resource.
	 */
	public Resource createResource(URI uri) {
		Resource result = new PageflowResourceImpl(uri);
		return result;
	}

}
