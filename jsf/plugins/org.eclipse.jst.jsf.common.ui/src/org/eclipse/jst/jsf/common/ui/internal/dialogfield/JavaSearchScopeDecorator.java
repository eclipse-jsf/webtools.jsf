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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.search.IJavaSearchScope;

/**
 * @author mengbo
 * @version 1.5
 */
public class JavaSearchScopeDecorator implements IJavaSearchScope {
	JavaSearchScope[] _scopes = new JavaSearchScope[0];

	public JavaSearchScopeDecorator(IProject project, List superTypes) {
		List scopeList = new ArrayList();
		if (superTypes != null) {
			for (int i = 0; i < superTypes.size(); i++) {
				scopeList.add(new JavaSearchScope(project, superTypes.get(i)
						.toString()));
			}
		}
		_scopes = (JavaSearchScope[]) scopeList
				.toArray(new JavaSearchScope[scopeList.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#encloses(java.lang.String)
	 */
	public boolean encloses(String resourcePath) {
		for (int i = 0; i < _scopes.length; i++) {
			if (_scopes[i].encloses(resourcePath) == true) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#encloses(org.eclipse.jdt.core.IJavaElement)
	 */
	public boolean encloses(IJavaElement element) {
		for (int i = 0; i < _scopes.length; i++) {
			if (_scopes[i].encloses(element) == true) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#enclosingProjectsAndJars()
	 */
	public IPath[] enclosingProjectsAndJars() {
		Set set = new HashSet();
		for (int i = 0; i < _scopes.length; i++) {
			set.addAll(Arrays.asList(_scopes[i].enclosingProjectsAndJars()));
		}
		return (IPath[]) set.toArray(new IPath[set.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#includesBinaries()
	 */
	public boolean includesBinaries() {
		for (int i = 0; i < _scopes.length; i++) {
			if (_scopes[i].includesBinaries() == true) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#includesClasspaths()
	 */
	public boolean includesClasspaths() {
		for (int i = 0; i < _scopes.length; i++) {
			if (_scopes[i].includesClasspaths() == true) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#setIncludesBinaries(boolean)
	 */
	public void setIncludesBinaries(boolean includesBinaries) {
		for (int i = 0; i < _scopes.length; i++) {
			_scopes[i].setIncludesBinaries(includesBinaries);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#setIncludesClasspaths(boolean)
	 */
	public void setIncludesClasspaths(boolean includesClasspaths) {
		for (int i = 0; i < _scopes.length; i++) {
			_scopes[i].setIncludesClasspaths(includesClasspaths);
		}
	}

}
