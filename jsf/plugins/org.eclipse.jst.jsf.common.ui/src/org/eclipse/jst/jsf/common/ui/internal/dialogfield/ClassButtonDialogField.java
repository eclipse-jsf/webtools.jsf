/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;

/**
 * @author mengbo
 */
public class ClassButtonDialogField extends AbstractClassButtonDialogField {
	/**
	 * @param project
	 */
	public ClassButtonDialogField(IProject project) {
		super(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.AbstractClassButtonDialogField#getImplementInterfaces()
	 */
	protected List getImplementInterfaces() {
		return getInterfacesList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.AbstractClassButtonDialogField#getJavaSearchScope()
	 */
	protected IJavaSearchScope getJavaSearchScope() {
		IJavaSearchScope scope;
		String superType = getSuperClassName();
		List interfaceList = getInterfacesList();
		if (superType == null && interfaceList != null
				&& interfaceList.size() > 0) {
			superType = interfaceList.get(0).toString();
		}
		if (getSuperClassName() != null && interfaceList != null
				&& interfaceList.size() > 0) {
			superType = null;
		}
		if (superType == null) {
			scope = SearchEngine
					.createJavaSearchScope(new IJavaProject[] { JavaCore
							.create(getProject()) });
		} else {
			scope = JavaUIHelper.findSearchScope(getProject(), superType);
		}
		return scope;
	}
}
