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
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.Alerts;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ abstract class AbstractClassButtonDialogField extends
		StringButtonDialogField {
	private IProject _project;

	private String _superClass;

	private List _interfacesList;

	private IType _type;

	private int _typeFlag = IJavaElementSearchConstants.CONSIDER_ALL_TYPES;

	private boolean _autoOpenResource = true;

	/**
	 * @param autoOpenResource
	 */
	public void setAutoOpenResource(boolean autoOpenResource) {
		_autoOpenResource = autoOpenResource;
	}

	/**
	 * @param project
	 */
	public AbstractClassButtonDialogField(IProject project) {
		super(null);
		this._project = project;

		setHyperLink(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				activeLink();
			}
		});

		setStringButtonAdapter(new IStringButtonAdapter() {
			public void changeControlPressed(DialogField field) {
				browseButtonPressed();
			}
		});
	}

	private void activeLink() {
		String className = getText();
		className = trimNonAlphaChars(className);
		if (className.length() > 0
				&& JavaUIHelper.doesClassExist(_project, className)) {
			JavaUIHelper.doOpenClass(_project, className);
		} else {
			try {
				if (_project == null || !_project.hasNature(JavaCore.NATURE_ID)) {
					ResourceBundle rb = ResourceBundle
							.getBundle("org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldResources");
					Alerts alerts = new Alerts(JSFUICommonPlugin.getDefault(), rb);
					alerts.error("ClassButtonDialogField.Alert.Title",
							"ClassButtonDialogField.Alert.Msg");
					return;
				}
			} catch (CoreException e) {
				e.printStackTrace();
				return;
			}
			JavaClassWizard wizard = new JavaClassWizard(_project, className,
					_superClass, getImplementInterfaces());
			wizard.setAutoOpenResource(_autoOpenResource);
			WizardDialog dialog = new WizardDialog(getShell(), wizard);
			dialog.create();

			setDialogSize(dialog, 400, 500);
			if (dialog.open() == WizardDialog.OK) {
				String newValue = wizard.getClassNameWithArgs();
				if (!newValue.equals(className)) {
					setText(newValue);
				}
			}
		}
	}

	/**
	 * @return the interfaces 
	 * TODO: the contract seems inconsistent
	 * as whether to return null or empty list when none
	 */
	protected abstract List getImplementInterfaces();

	/**
	 * @return the java search scope to be used.  Must not be null
	 */
	protected abstract IJavaSearchScope getJavaSearchScope();

	private void browseButtonPressed() {
		Shell shell = getShell();
		SelectionDialog dialog = JavaUIHelper.openSelectionDialog(shell,
				getJavaSearchScope(), _typeFlag);
		dialog.setTitle(JSFUICommonPlugin
				.getResourceString("DialogField.ClassButton.SelectType"));//$NON-NLS-1$

		if (dialog.open() == SelectionDialog.OK) {
			String oldClassName = getText();
			if (dialog.getResult() != null) {
				_type = (IType) dialog.getResult()[0];
				String newClassName = _type.getFullyQualifiedName();
				if (!oldClassName.equals(newClassName)) {
					setText(newClassName);
				}
			}
		}
	}

	private void setDialogSize(Dialog dialog, int width, int height) {
		Point computedSize = dialog.getShell().computeSize(SWT.DEFAULT,
				SWT.DEFAULT);
		width = Math.max(computedSize.x, width);
		height = Math.max(computedSize.y, height);
		dialog.getShell().setSize(width, height);
	}

	private String trimNonAlphaChars(String className) {
		className = className.trim();
		while (className.length() > 0
				&& !Character.isLetter(className.charAt(0))) {
			className = className.substring(1, className.length());
		}
		int loc = className.indexOf(":"); //$NON-NLS-1$
		if (loc != -1 && loc > 0) {
			className = className.substring(0, loc);
		} else if (loc == 0) {
			className = ""; //$NON-NLS-1$
		}
		return className;
	}

	/**
	 * @return Returns the project.
	 */
	public IProject getProject() {
		return _project;
	}

	/**
	 * @param project
	 *            The project to set.
	 */
	public void setProject(IProject project) {
		this._project = project;
	}

	/**
	 * @return Returns the superClassName.
	 */
	public String getSuperClassName() {
		return _superClass;
	}

	/**
	 * @param superClassName
	 *            The superClassName to set.
	 */
	public void setSuperClassName(String superClassName) {
		this._superClass = superClassName;
	}

	/**
	 * @return Returns the interfacesList.
	 */
	protected List getInterfacesList() {
		return _interfacesList;
	}

	/**
	 * Sets (replaces) the interface list
	 * TODO: this list can have at most one element
	 * @param interfaceName
	 */
	public void setInterface(String interfaceName) {
		_interfacesList = new ArrayList();
		_interfacesList.add(interfaceName);
	}

	/**
	 * @return Returns the _type.
	 */
	public IType getType() {
		return _type;
	}

	/**
	 * @return Returns the typeFalg.
	 */
	public int getTypeFlag() {
		return _typeFlag;
	}

	/**
	 * @param typeFalg
	 *            The typeFalg to set.
	 */
	public void setTypeFlag(int typeFalg) {
		this._typeFlag = typeFalg;
	}
}
