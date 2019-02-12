/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.ide.IDE;

/**
 * @author mengbo
 */
public final class JavaUIHelper {
	/**
	 * @param project
	 * @param className
	 */
	static void doOpenClass(IProject project, String className) {
		String path = className.replace('.', '/') + ".java"; //$NON-NLS-1$
		try {
			if (project.hasNature(JavaCore.NATURE_ID)) {
				IJavaProject javaProject = JavaCore.create(project);
				IJavaElement result = javaProject.findElement(new Path(path));
				JavaUI.openInEditor(result);
			} else {
				IResource resource = project.findMember(new Path(path));
				if (resource instanceof IFile) {
					IWorkbenchPage page = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();
					IDE.openEditor(page, (IFile) resource, true);
				}
			}
		} catch (PartInitException e) {
			e.printStackTrace();// PDEPlugin.logException(e);
		} catch (JavaModelException e) {
			e.printStackTrace();// Display.getCurrent().beep();
		} catch (CoreException e) {
			e.printStackTrace();// PDEPlugin.logException(e);
		}
	}

	/**
	 * @param project
	 * @param className
	 * @return true if the class exists in project
	 */
	static boolean doesClassExist(IProject project, String className) {
		String path = className.replace('.', '/') + ".java"; //$NON-NLS-1$
		try {
			if (project.hasNature(JavaCore.NATURE_ID)) {
				IJavaProject javaProject = JavaCore.create(project);

				IJavaElement result = javaProject.findElement(new Path(path));
				return result != null;
			}
            IResource resource = project.findMember(new Path(path));
            return resource != null;
		} catch (JavaModelException e) {
			return false;
		} catch (CoreException e) {
			return false;
		}
	}

	/**
	 * @param shell
	 * @param searchScope
	 * @param typeFlag
	 * @return the selection dialog
	 */
	static SelectionDialog openSelectionDialog(Shell shell,
			IJavaSearchScope searchScope, int typeFlag) {
		try {
			return JavaUI.createTypeDialog(shell, new ProgressMonitorDialog(
					shell), searchScope, typeFlag, false);
		} catch (JavaModelException e) {
            JSFUICommonPlugin.getLogger(JavaUIHelper.class).error(e);
			return null;
		}
	}

	/**
	 * @param shell
	 * @param project
	 * @param superType
	 * @param typeFlag
	 * @return the selection  dialog
	 */
	public static SelectionDialog openSelectionDialog(Shell shell,
			IProject project, String superType, int typeFlag) {
		IJavaSearchScope searchScope = findSearchScope(project, superType);
		return openSelectionDialog(shell, searchScope, typeFlag);
	}

	/**
	 * @param project
	 * @param superType
	 * @return the search scope
	 */
	static IJavaSearchScope findSearchScope(IProject project,
			String superType) {
		if (project != null) {
			if (superType == null || "".equals(superType)) { //$NON-NLS-1$
				superType = "java.lang.Object";//$NON-NLS-1$
			}
			return new JavaSearchScope(project, superType);
		}
		return SearchEngine.createWorkspaceScope();
	}
	
	private JavaUIHelper()
	{
		// static helper, no instantiation
	}
}
