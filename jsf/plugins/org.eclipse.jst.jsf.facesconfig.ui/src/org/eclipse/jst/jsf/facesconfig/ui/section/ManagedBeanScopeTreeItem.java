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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.wtp.jsf.facesconfig.emf.ManagedBeanType;

/**
 * @author Xiao-guang Zhang, sfshi
 * @version
 */
public class ManagedBeanScopeTreeItem {

	public static final String[] scopeItems = {
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_SESSION,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_REQUEST,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_APPLICATION,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_NONE };

	private String scope;

	private FacesConfigType facesConfig;

	/**
	 * 
	 * @param managedBeanScope
	 * @param facesConfig
	 */
	public ManagedBeanScopeTreeItem(String managedBeanScope,
			FacesConfigType facesConfig) {
		super();
		this.scope = managedBeanScope;

		this.facesConfig = facesConfig;
	}

	public Object getParent() {
		return facesConfig;
	}

	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}

	public String getScope() {
		return scope;
	}

	public List getChildren() {
		List result = new ArrayList();
		List managedBeans = facesConfig.getManagedBean();
		for (Iterator it = managedBeans.iterator(); it.hasNext();) {
			ManagedBeanType managedBean = (ManagedBeanType) it.next();

			if (scope
					.equals(managedBean.getManagedBeanScope().getTextContent())) {
				result.add(managedBean);
			}
		}

		return result;
	}
}
