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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.search.IJavaSearchScope;

/**
 * @author mengbo
 * @version 1.5
 */
public class MultiTypeButtonDialogField extends AbstractClassButtonDialogField {
	IJavaSearchScope _scope;

	/**
	 * @param project
	 */
	public MultiTypeButtonDialogField(IProject project) {
		super(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.AbstractClassButtonDialogField#getJavaSearchScope()
	 */
	protected IJavaSearchScope getJavaSearchScope() {
		if (_scope == null) {
			List list = new ArrayList();
			String superClassName = getSuperClassName();
			if (superClassName != null && !"".equalsIgnoreCase(superClassName)) {
				list.add(getSuperClassName());
			}
			list.addAll(getInterfacesList());
			_scope = new JavaSearchScopeDecorator(this.getProject(), list);
		}
		return _scope;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.AbstractClassButtonDialogField#getImplementInterfaces()
	 */
	protected List getImplementInterfaces() {
		List list = getInterfacesList();
		if (list.size() == 1) {
			return list;
		}
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.AbstractClassButtonDialogField#setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject(IProject project) {
		if (project != getProject()) {
			_scope = null;
		}
		super.setProject(project);
	}
}
