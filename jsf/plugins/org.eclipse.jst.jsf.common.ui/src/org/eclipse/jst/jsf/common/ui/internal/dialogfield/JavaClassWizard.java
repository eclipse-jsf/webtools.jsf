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

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

/**
 * @author mengbo
 */
/*package*/ class JavaClassWizard extends Wizard {
	private static String STORE_SECTION = "JavaClassWizard"; //$NON-NLS-1$

	private JavaClassWizardPage _mainPage;

	private String _className, _classArgs;

	private IProject _project;

	private String _superClass;

	private List _interfaceList;

	private boolean _autoOpenResource = true;

	/**
	 * @param autoOpenResource
	 */
	public void setAutoOpenResource(boolean autoOpenResource) {
		_autoOpenResource = autoOpenResource;
	}

	/**
	 * @param project
	 * @param className
	 */
	public JavaClassWizard(IProject project, String className) {
		this(project, className, null, null);
	}

	/**
	 * @param project
	 * @param className
	 * @param superClass
	 * @param superInterfaces
	 */
	public JavaClassWizard(IProject project, String className,
			String superClass, List superInterfaces) {
		this._project = project;
		this._className = className;
		this._superClass = superClass;
		this._interfaceList = superInterfaces;
		IDialogSettings masterSettings = JSFUICommonPlugin.getDefault()
				.getDialogSettings();
		setDialogSettings(getSettingsSection(masterSettings));
		setWindowTitle(JSFUICommonPlugin
				.getResourceString("DialogField.JavaClassWizard.NewClass"));//$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		_mainPage = new JavaClassWizardPage(_project, _className, _superClass,
				_interfaceList);
		addPage(_mainPage);
		_mainPage.init();
	}

	private IDialogSettings getSettingsSection(IDialogSettings master) {
		IDialogSettings setting = master.getSection(STORE_SECTION);
		if (setting == null) {
			setting = master.addNewSection(STORE_SECTION);
		}
		return setting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		if (_mainPage.getPackageText() != null
				&& _mainPage.getPackageText().length() > 0) {
			StringBuffer buffer = new StringBuffer(_mainPage.getPackageText());
			buffer.append(".");//$NON-NLS-1$
			buffer.append(_mainPage.getTypeName());
			_className = buffer.toString();
		} else {
			_className = _mainPage.getTypeName();
		}
		_classArgs = _mainPage.getClassArgs();
		IRunnableWithProgress op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				_mainPage.createType(monitor);
				IResource resource = _mainPage.getModifiedResource();
				if (resource != null && _autoOpenResource) {
					selectAndReveal(resource);
					if (_project.hasNature(JavaCore.NATURE_ID)) {
						IJavaProject jProject = JavaCore.create(_project);
						IJavaElement jElement = jProject.findElement(resource
								.getProjectRelativePath()
								.removeFirstSegments(1));
						if (jElement != null) {
							JavaUI.openInEditor(jElement);
						}
					} else if (resource instanceof IFile) {
						IWorkbenchPage page = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage();
						IDE.openEditor(page, (IFile) resource, true);
					}
				}
			}

		};
		try {
			getContainer().run(false, true, op);
		} catch (InvocationTargetException e) {
			e.printStackTrace(); // PDEPlugin.logException(e);
		} catch (InterruptedException e) {
			e.printStackTrace();// PDEPlugin.logException(e);
		}
		return true;
	}

	private void selectAndReveal(IResource newResource) {
		BasicNewResourceWizard.selectAndReveal(newResource,
				getWorkbenchWindow());
	}

	private IWorkbenchWindow getWorkbenchWindow() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}

	private String getClassName() {
		return _className;
	}

	/**
	 * @return the class name including args
	 */
	public String getClassNameWithArgs() {
		if (_classArgs != null && _classArgs.length() > 0) {
			StringBuffer buffer = new StringBuffer(_className);
			buffer.append(":");//$NON-NLS-1$
			buffer.append(_classArgs);
			return buffer.toString();
		}
		return getClassName();
	}
}
