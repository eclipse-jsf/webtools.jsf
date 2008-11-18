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
package org.eclipse.jst.pagedesigner.ui.common;

import java.io.File;
import java.util.Arrays;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJarEntryResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jdt.ui.StandardJavaElementContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.utils.JavaUtil;
import org.eclipse.swt.widgets.Shell;

/**
 * This dialog will let client to select resources that located on a
 * IJavaProject classpath, the client can provide a list of files suffixs to
 * filter.
 * 
 * @author mengbo
 */
// TODO: Since many jar files might do nothing to do with client's selection, we
// may need to provides more filter choice
// to clients to exclude unnecessary jar files, such as that are located in JDK
// dir.
public class ResourceOnClasspathDialog extends TreeViewerSelectionDialog {
	// ResourcesOnClasspathDialog.statusMessage = Please select a property file
	private static final String STATUS_MESSAGE = PageDesignerResources
			.getInstance()
			.getString("ResourcesOnClasspathDialog.statusMessage"); //$NON-NLS-1$

	private IJavaProject _javaProject;

	// the suffixs of files that can be selected
	private String _suffixs[]; // =

	// IJMTConstants.DEFAULT_SUFFIX;

	private ResourceOnClasspathFilter _filter;

	// Client doesn't need to know it.
	class ResourceOnClasspathFilter extends ViewerFilter {
		StandardJavaElementContentProvider _javaContentProvider;

		/**
		 * @param contentProvider
		 */
		public ResourceOnClasspathFilter(
				StandardJavaElementContentProvider contentProvider) {
			_javaContentProvider = contentProvider;
		}

		/**
		 * Set the suffixs of files need to be selected.
		 * 
		 * @param suffixs
		 */
		public void setSuffixs(String suffixs[]) {
			_suffixs = suffixs;
		}

		/**
		 * @param contentProvider
		 *            The _javaContentProvider to set.
		 */
		public void setJavaContentProvider(
				StandardJavaElementContentProvider contentProvider) {
			_javaContentProvider = contentProvider;
		}

		/**
		 * @param project
		 *            The _javaProject to set.
		 */
		public void setJavaProject(IJavaProject project) {
			_javaProject = project;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			// TODO: This logic can be improved by add caching mechanism
			if (element instanceof IJavaProject) {
				String currentProjectName = ((IJavaProject) element)
						.getProject().getFullPath().toString().trim();
				String projectName = _javaProject.getProject().getFullPath()
						.toString().trim();
				if (projectName.equalsIgnoreCase(currentProjectName)) {
					return true;
				}
                return false;
			} else if (element instanceof IResource) {
				if (((_javaProject != null) && !_javaProject
						.isOnClasspath((IResource) element))) {
					return false;
				}
				if (element instanceof IFile) {
					if (Arrays.asList(_suffixs).contains(
							((IFile) element).getFileExtension())) {
						return true;
					}
				}
				return false;
			}

			if (element instanceof IJarEntryResource) {
				String ext = ((IJarEntryResource) element).getFullPath()
						.getFileExtension();
				if (ext != null && Arrays.asList(_suffixs).contains(ext)) {
					return true;
				}
                return false;
			}
			if (!(element instanceof IJavaElement)) {
				return false;
			}
			if (((_javaProject != null) && !_javaProject
					.isOnClasspath((IJavaElement) element))) {
				return false;
			}
			IJavaElement javaElement = (IJavaElement) element;
			Object[] children = null;
			switch (javaElement.getElementType()) {
			case IJavaElement.PACKAGE_FRAGMENT_ROOT:
				children = _javaContentProvider.getChildren(javaElement);
				break;
			case IJavaElement.IMPORT_CONTAINER:
				return true;
			case IJavaElement.PACKAGE_FRAGMENT:
				children = _javaContentProvider.getChildren(javaElement);
				break;
			case IJavaElement.CLASS_FILE:
				if (Arrays.asList(_suffixs).contains(
						IFileFolderConstants.EXT_CLASS)) {
					return true;
				} 
				return false;
			case IJavaElement.COMPILATION_UNIT:
				String ext = javaElement.getPath().getFileExtension();
				if (ext != null && Arrays.asList(_suffixs).contains(ext)) {
					return true;
				} 
				return false;
			default:
				return false;
			}

			for (int i = 0; i < children.length; i++) {
				if (select(viewer, javaElement, children[i])) {
					return true;
				}
			}
			// Either the resouce or its children are not for displaying.
			return false;
		}
	}

	/**
	 * Set the suffixs of files need to be selected.
	 * 
	 * @param suffixs
	 */
	public void setSuffixs(String suffixs[]) {
		_suffixs = suffixs;
		_filter.setSuffixs(suffixs);
	}

	/**
	 * @param parentShell
	 * @param project
	 */
	public ResourceOnClasspathDialog(Shell parentShell, IJavaProject project) {
		super(parentShell, STATUS_MESSAGE);
		// set provider and filter
		StandardJavaElementContentProvider contentProvider = new StandardJavaElementContentProvider();
		setContentProvider(contentProvider);
		setLabelProvider(new JavaElementLabelProvider());
		_filter = new ResourceOnClasspathFilter(contentProvider);
		setFilter(_filter);
		// store the project
		_javaProject = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.dialogs.SelectionDialog#getResult()
	 */
	public Object[] getResult() {
		Object[] objects = super.getResult();
		if (objects == null || objects.length == 0) {
			return null;
		}
		IPath path = JavaUtil.getPathOnClasspath(_javaProject, objects[0]);
		String result = null;
		if (path.segmentCount() == 0) {
			return new Object[] { "" }; //$NON-NLS-1$
		}
		path = path.removeFileExtension();
		result = path.toOSString();
		result = result.replace(File.separatorChar, '.');
		return new Object[] { result };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.ui.common.SelectionTreeViewerDialog#isValidSelection()
	 */
	protected boolean isValidSelection(Object selection) {
		String extension = JavaUtil.getPathOnClasspath(_javaProject, selection)
				.getFileExtension();
		return (extension != null && Arrays.asList(_suffixs)
				.contains(extension));
	}

	protected Object findInputElement() {
		Object input = ResourcesPlugin.getWorkspace();
		if (input instanceof IWorkspace) {
			return JavaCore.create(((IWorkspace) input).getRoot());
		} else if (input instanceof IContainer) {
			IJavaElement element = JavaCore.create((IContainer) input);
			if (element != null && element.exists())
				return element;
			return input;
		}
		return JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
	}
}
