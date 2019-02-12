/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;

/**
 * @author mengbo
 */
/*package*/ class JavaClassWizardPage extends NewClassWizardPage {
	private String _className;

	private final IProject _project;

	private final InitialClassProperties _initialValues;

	private IJavaProject _javaProject;

	private IStatus _fClassNameStatus, _fPackageNameStatus;

	private final static String  SOURCE_COMPLIANCE_1_3 = JavaCore.VERSION_1_3;
	
	static class InitialClassProperties {
		// populate new wizard page
		private String superClassName;

		private List interfacesName;

		private String className;

		private String classArgs;

		private String packageName;

		private IPackageFragmentRoot packageFragmentRoot;

		private IPackageFragment packageFragment;

		InitialClassProperties() {
			this.superClassName = ""; //$NON-NLS-1$
			this.interfacesName = null;
			this.className = null;
			this.classArgs = null;
			this.packageName = null;
			this.packageFragment = null;
			this.packageFragmentRoot = null;
		}
	}

	/**
	 * @param project
	 * @param className
	 * @param superClassName
	 * @param interfacesName
	 */
	public JavaClassWizardPage(IProject project, String className,
			String superClassName, List interfacesName) {
		super();
		this._className = className;
		this._project = project;
		try {
			if (project != null && project.hasNature(JavaCore.NATURE_ID)) {
				this._javaProject = JavaCore.create(project);
			} else {
				this._javaProject = null;
			}
		} catch (CoreException e) {
			e.printStackTrace();// PDEPlugin.logException(e);
		}
		_initialValues = new InitialClassProperties();
		_initialValues.className = className;
		_initialValues.superClassName = superClassName;
		_initialValues.interfacesName = interfacesName;
	}

	/**
	 * @param project
	 * @param className
	 */
	public JavaClassWizardPage(IProject project, String className) {
		this(project, className, null, null);
	}

	/**
	 * Call when page is added to wizard to initialize
	 */
	public void init() {
		initializeExpectedValues();
		initializeWizardPage();
	}

	private void initializeExpectedValues() {
		// source folder name, package name, class name
		int loc = _className.indexOf(":"); //$NON-NLS-1$
		if (loc != -1) {
			if (loc < _className.length()) {
				_initialValues.classArgs = _className.substring(loc + 1,
						_className.length());
				_className = _className.substring(0, loc);
			}
			if (loc > 0) {
				_initialValues.className = _className.substring(0, loc);
			} else if (loc == 0) {
				_initialValues.className = ""; //$NON-NLS-1$
			}
		}
		_fClassNameStatus = JavaConventions
		    .validateJavaTypeName(_initialValues.className, SOURCE_COMPLIANCE_1_3,SOURCE_COMPLIANCE_1_3);

		loc = _className.lastIndexOf('.');
		if (loc != -1) {
			_initialValues.packageName = _className.substring(0, loc);
			_initialValues.className = _className.substring(loc + 1);
			_fPackageNameStatus = JavaConventions
                    .validateJavaTypeName(_initialValues.packageName, SOURCE_COMPLIANCE_1_3,SOURCE_COMPLIANCE_1_3);
			_fClassNameStatus = JavaConventions
                    .validateJavaTypeName(_initialValues.className, SOURCE_COMPLIANCE_1_3,SOURCE_COMPLIANCE_1_3);
		}
		if (_javaProject == null) {
			return;
		}
		try {
			if (_initialValues.packageFragmentRoot == null) {
				IPackageFragmentRoot srcEntryDft = null;
				IPackageFragmentRoot[] roots = _javaProject
						.getPackageFragmentRoots();
				for (int i = 0; i < roots.length; i++) {
					if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE) {
						srcEntryDft = roots[i];
						break;
					}
				}
				if (srcEntryDft != null) {
					_initialValues.packageFragmentRoot = srcEntryDft;
				} else {
					_initialValues.packageFragmentRoot = _javaProject
							.getPackageFragmentRoot(_javaProject.getResource());
				}
				if (_initialValues.packageFragment == null
						&& _initialValues.packageFragmentRoot != null
						&& _initialValues.packageName != null
						&& _initialValues.packageName.length() > 0) {
					IFolder packageFolder = _project
							.getFolder(_initialValues.packageName);
					_initialValues.packageFragment = _initialValues.packageFragmentRoot
							.getPackageFragment(packageFolder
									.getProjectRelativePath().toOSString());
				}
			}
			// superclass and interface
			if (_initialValues.superClassName == null) {
				_initialValues.superClassName = "java.lang.Object"; //$NON-NLS-1$
			}
//			_initialValues.superClassType = findTypeForName(_initialValues.superClassName);
		} catch (JavaModelException e) {
			e.printStackTrace();// PDEPlugin.logException(e);
		}
	}

	/**
	 * initialize the wizard page
	 */
	protected void initializeWizardPage() {
		setPackageFragmentRoot(_initialValues.packageFragmentRoot, true);
		setPackageFragment(_initialValues.packageFragment, true);
		setEnclosingType(null, true);
		setEnclosingTypeSelection(false, true);
		setTypeName(_initialValues.className, true);
		setSuperClass(_initialValues.superClassName, true);
		if (_initialValues.interfacesName != null) {
			setSuperInterfaces(_initialValues.interfacesName, true);
		}
		boolean hasSuperClass = _initialValues.superClassName != null
				&& _initialValues.superClassName.length() > 0;
		boolean hasInterface = _initialValues.interfacesName != null
				&& _initialValues.interfacesName.size() > 0;
		setMethodStubSelection(false, hasSuperClass, hasInterface
				|| hasSuperClass, true);
	}

//	private IType findTypeForName(String typeName) throws JavaModelException {
//		if (typeName == null || typeName.length() == 0) {
//			return null;
//		}
//		IType type = null;
//		String fileName = typeName.replace('.', '/') + ".java"; //$NON-NLS-1$
//		IJavaElement element = _javaProject.findElement(new Path(fileName));
//		if (element == null) {
//			return null;
//		}
//		if (element instanceof IClassFile) {
//			type = ((IClassFile) element).getType();
//		} else if (element instanceof ICompilationUnit) {
//			IType[] types = ((ICompilationUnit) element).getTypes();
//			type = types[0];
//		}
//		return type;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.ui.wizards.NewClassWizardPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		// policy: wizards are not allowed to come up with an error message;
		// in this wizard, some fields may need initial validation and thus,
		// potentially start with an error message.
		if (_fClassNameStatus != null && !_fClassNameStatus.isOK()) {
			updateStatus(_fClassNameStatus);
		}
		if (_fPackageNameStatus != null && !_fPackageNameStatus.isOK()) {
			updateStatus(_fPackageNameStatus);
		}
	}

	/**
	 * @return the class arguments or "" if not set
	 */
	public String getClassArgs() {
		if (_initialValues.classArgs == null) {
			return ""; //$NON-NLS-1$
		}
		return _initialValues.classArgs;
	}

}
