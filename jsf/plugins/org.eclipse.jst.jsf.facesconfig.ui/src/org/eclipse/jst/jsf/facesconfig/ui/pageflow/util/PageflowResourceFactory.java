/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;

/**
 * 
 * This is the Page Flow Resource Factory for resource drag and drop
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowResourceFactory implements CreationFactory {
	/** resource */
	private IResource resource;

	/**
	 * @param resource
	 */
	public PageflowResourceFactory(IResource resource) {
		this.resource = resource;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see Factory#getNewObject()
	 */
	public Object getNewObject() {
		if (null == resource) {
			return null;
		}

		String strWebPath = WebrootUtil.getWebPath(resource.getFullPath());
		if (strWebPath.length() > 0) {
			PageflowFactory factory = PageflowModelManager.getFactory();
			Object result = factory.createPFPage();
			PageflowPage page = (PageflowPage) result;
			page.setPath(strWebPath);
			page.setName(WebrootUtil.getPageNameFromWebPath(strWebPath));
			return page;
		}
        
        EditorPlugin
        		.getAlerts()
        		.error(
        				"Pageflow.Error.DNDResourceTitle", //$NON-NLS-1$
        				EditorPlugin
        						.getResourceString("Pageflow.Error.InvalidResource") //$NON-NLS-1$
        						+ resource.getName());

		return null;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see Factory#getObjectType()
	 */
	public Object getObjectType() {
		return resource;
	}
}
