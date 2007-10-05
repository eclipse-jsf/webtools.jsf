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
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;

/**
 * @author mengbo
 */
/*package*/ class JavaSearchScope implements IJavaSearchScope {
	private final IProject _project;

	private final String _superType;

	private HashSet _allowedTypeSet;

	private IPath[] _enclosingProjectsAndJars;

	private IProject[] _relativeProjects;

	/**
	 * @param project
	 * @param superType
	 */
	public JavaSearchScope(IProject project, String superType) {
		this._project = project;
		this._superType = superType;
		computeRelativeProjects();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#enclosingProjectsAndJars()
	 */
	public IPath[] enclosingProjectsAndJars() {
		if (_enclosingProjectsAndJars == null) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < _relativeProjects.length; i++) {
				try {
					if (_relativeProjects[i].hasNature(JavaCore.NATURE_ID)) {
						IJavaProject javaProject = JavaCore
								.create(_relativeProjects[i]);
						IClasspathEntry[] classpath = javaProject
								.getResolvedClasspath(true);
						for (int j = 0; j < classpath.length; j++) {
							list.add(classpath[j].getPath());
						}
						list.add(javaProject.getPath());
					}
				} catch (CoreException e)// NOPMD
				{
					// skip the project.
				}
			}
			_enclosingProjectsAndJars = (IPath[]) list.toArray(new IPath[(list
					.size())]);
		}
		return _enclosingProjectsAndJars;
	}

	private void computeRelativeProjects() {
		try {
			IProject[] referencedProjects = _project.getReferencedProjects();
			_relativeProjects = new IProject[referencedProjects.length + 1];
			System.arraycopy(referencedProjects, 0, _relativeProjects, 1,
					referencedProjects.length);
			_relativeProjects[0] = _project;
		} catch (CoreException e) {
			_relativeProjects = new IProject[] { _project };
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#encloses(java.lang.String)
	 */
	public boolean encloses(String resourcePath) {
		if (_allowedTypeSet == null) {
			try {
				_allowedTypeSet = findAllowedTypes(_superType);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		if (_allowedTypeSet == null) {
			_allowedTypeSet = new HashSet();
		}
		int separatorIndex = resourcePath.indexOf(JAR_FILE_ENTRY_SEPARATOR);
		if (separatorIndex != -1) {
			String className = resourcePath.substring(separatorIndex + 1,
					resourcePath.length() - 6).replace('/', '.');
			if (_allowedTypeSet.contains(className)) {
				return true;
			}
		} else if (_allowedTypeSet.contains(resourcePath)) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#encloses(org.eclipse.jdt.core.IJavaElement)
	 */
	public boolean encloses(IJavaElement element) {
		return encloses(element.getPath().toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#includesBinaries()
	 */
	public boolean includesBinaries() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#includesClasspaths()
	 */
	public boolean includesClasspaths() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#setIncludesBinaries(boolean)
	 */
	public void setIncludesBinaries(boolean includesBinaries) {
	    //    do nothing, includeBinaries always true
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.search.IJavaSearchScope#setIncludesClasspaths(boolean)
	 */
	public void setIncludesClasspaths(boolean includesClasspaths) {
        // do nothing, includeClasspaths always trues
	}

	private HashSet findAllowedTypes(String superType) throws CoreException {
		HashSet set = new HashSet();

		IProject[] projects = _relativeProjects;

		for (int i = 0; i < projects.length; i++) {
			IType type = null;
			if (projects[i].hasNature(JavaCore.NATURE_ID)) {
				IJavaProject javaProject = JavaCore.create(projects[i]);
				if (superType != null) {
					try {
						type = javaProject.findType(superType);
						if (type != null) {
							ITypeHierarchy typeHierarchy = type
									.newTypeHierarchy(javaProject, null);
							IType[] subtypes = typeHierarchy
									.getAllSubtypes(type);
							for (int j = 0; j < subtypes.length; j++) {
								if (!subtypes[j].isBinary()) {
									set.add(subtypes[j].getPath().toString());
								} else {
									String path = subtypes[j].getPath()
											.toString();
									if (path != null
											&& path
													.endsWith(IFileFolderConstants.DOT
															+ IFileFolderConstants.EXT_JAR)) {
										set.add(subtypes[j]
												.getFullyQualifiedName());
									} else {
										set.add(path);
									}
								}
							}
						}
					} catch (JavaModelException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return set;
	}

	/**
	 * @return Returns the superType.
	 */
	public String getSuperType() {
		return _superType;
	}
}
