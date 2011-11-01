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

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;

/**
 * @author Xiao-guang Zhang, sfshi
 * @version
 */
public class ManagedBeanScopeTreeItem {

	/**
	 * Possible values for bean scope
	 */
	public static final String[] scopeItems = {
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_APPLICATION,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_SESSION,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_REQUEST,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_VIEW,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_NONE};

	/**
	 * Possible values for bean scope if we can determine that we are in a pre-JSF 2.0 context
	 */
	public static final String[] scopeItemsPreJSF2 = {
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_APPLICATION,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_SESSION,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_REQUEST,
			IFacesConfigConstants.MANAGED_BEAN_SCOPE_NONE};

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

	/**
	 * @return the parent object
	 */
	public Object getParent() {
		return facesConfig;
	}

	/**
	 * @return true if this node has children
	 */
	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @return the list of children.  List may be empty.
	 */
	public List getChildren() {
		List result = new ArrayList();
		List managedBeans = facesConfig.getManagedBean();
		for (Iterator it = managedBeans.iterator(); it.hasNext();) {
			ManagedBeanType managedBean = (ManagedBeanType) it.next();

			if (managedBean.getManagedBeanScope() != null) {
				if (scope.equals(managedBean.getManagedBeanScope()
						.getTextContent())) {
					result.add(managedBean);
				}
			}
		}

		return result;
	}
}
