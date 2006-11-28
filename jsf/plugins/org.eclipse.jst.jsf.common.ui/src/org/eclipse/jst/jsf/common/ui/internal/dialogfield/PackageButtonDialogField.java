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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * This dialog field is used to select the package in the IPackageFragmentRoot
 * 
 * 
 * @author mengbo
 * @version 1.5
 */
public class PackageButtonDialogField extends StringButtonDialogField {
	/** Create the logger for this class */
	private static Logger _log = JSFUICommonPlugin
			.getLogger(PackageButtonDialogField.class);

	/*
	 * package fragment root corresponding to the input type (can be null)
	 */
	private IPackageFragmentRoot _fCurrRoot;

	private IPackageFragment _fCurrPackage;

	/**
	 * @param adapter
	 */
	public PackageButtonDialogField(IPackageFragmentRoot packageRoot) {
		super(null);
		setPackageFragmentRoot(packageRoot);

		setLabelText(DialogFieldResources.getInstance().getString(
				"PackageButtonDialogField.package.label"));

		setButtonLabel(DialogFieldResources.getInstance().getString(
				"PackageButtonDialogField.package.button"));

		setStringButtonAdapter(new IStringButtonAdapter() {
			public void changeControlPressed(DialogField field) {
				packageChangeControlPressed(field);
			}

		});

	}

	/**
	 * @param field
	 */
	protected void packageChangeControlPressed(DialogField field) {
		IPackageFragment pack = choosePackage();
		if (pack != null) {
			setPackageFragment(pack, true);
		}
	}

	/**
	 * Sets the current source folder (model and text field) to the given
	 * package fragment root.
	 * 
	 * @param root
	 *            The new root.
	 * @param canBeModified
	 *            if <code>false</code> the source folder field can not be
	 *            changed by the user. If <code>true</code> the field is
	 *            editable
	 */
	public void setPackageFragmentRoot(IPackageFragmentRoot root) {
		_fCurrRoot = root;
		enableButton(getPackageFragmentRoot() != null);
	}

	/**
	 * Returns the <code>IPackageFragmentRoot</code> that corresponds to the
	 * current value of the source folder field.
	 * 
	 * @return the IPackageFragmentRoot or <code>null</code> if the current
	 *         source folder value is not a valid package fragment root
	 * 
	 */
	public IPackageFragmentRoot getPackageFragmentRoot() {
		return _fCurrRoot;
	}

	/**
	 * selection dialogs
	 */
	private IPackageFragment choosePackage() {
		IPackageFragmentRoot froot = getPackageFragmentRoot();
		IJavaElement[] packages = null;
		try {
			if (froot != null && froot.exists()) {
				packages = froot.getChildren();
			}
		} catch (JavaModelException e) {
			_log.error(e);
		}
		if (packages == null) {
			packages = new IJavaElement[0];
		}

		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				getShell(), new JavaElementLabelProvider(
						JavaElementLabelProvider.SHOW_DEFAULT));
		dialog.setIgnoreCase(false);
		dialog.setTitle(DialogFieldResources.getInstance().getString(
				"PackageButtonDialogField.ChoosePackageDialog.title")); //$NON-NLS-1$
		dialog.setMessage(DialogFieldResources.getInstance().getString(
				"PackageButtonDialogField.ChoosePackageDialog.description")); //$NON-NLS-1$
		dialog
				.setEmptyListMessage(DialogFieldResources
						.getInstance()
						.getString(
								"PackageButtonDialogField.ChoosePackageDialog.empty")); //$NON-NLS-1$
		dialog.setElements(packages);
		IPackageFragment pack = getPackageFragment();
		if (pack != null) {
			dialog.setInitialSelections(new Object[] { pack });
		}

		if (dialog.open() == Window.OK) {
			return (IPackageFragment) dialog.getFirstResult();
		}
		return null;
	}

	/**
	 * Returns the package fragment corresponding to the current input.
	 * 
	 * @return a package fragment or <code>null</code> if the input could not
	 *         be resolved.
	 */
	public IPackageFragment getPackageFragment() {
		return _fCurrPackage;
	}

	/**
	 * Sets the package fragment to the given value. The method updates the
	 * model and the text of the control.
	 * 
	 * @param pack
	 *            the package fragment to be set
	 * @param canBeModified
	 *            if <code>true</code> the package fragment is editable;
	 *            otherwise it is read-only.
	 */
	public void setPackageFragment(IPackageFragment pack, boolean canBeModified) {
		_fCurrPackage = pack;
		String str = (pack == null) ? "" : pack.getElementName(); //$NON-NLS-1$
		setText(str);
		setEnabled(canBeModified);
	}

	/**
	 * Returns the text of the package input field.
	 * 
	 * @return the text of the package input field
	 */
	public String getPackageText() {
		return getText();
	}

	/**
	 * The method validates the package name and returns the status of the
	 * validation. The validation also updates the package fragment model.
	 * 
	 * @return the status of the validation
	 */
	public IStatus getChangedStatus() {
		StatusInfo status = new StatusInfo();

		String packName = getPackageText();
		if (packName.length() > 0) {
			IStatus val = JavaConventions.validatePackageName(packName);
			if (val.getSeverity() == IStatus.ERROR) {
				status
						.setError(DialogFieldResources
								.getInstance()
								.getString(
										"PackageButtonDialogField.error.InvalidPackageName", val.getMessage())); //$NON-NLS-1$
				return status;
			} else if (val.getSeverity() == IStatus.WARNING) {
				status
						.setWarning(DialogFieldResources
								.getInstance()
								.getString(
										"PackageButtonDialogField.warning.DiscouragedPackageName", val.getMessage())); //$NON-NLS-1$
				// continue
			}
		} else {
			status
					.setWarning(DialogFieldResources
							.getInstance()
							.getString(
									"PackageButtonDialogField.warning.DefaultPackageDiscouraged")); //$NON-NLS-1$
		}

		IPackageFragmentRoot root = getPackageFragmentRoot();
		if (root != null) {
			if (root.getJavaProject().exists() && packName.length() > 0) {
				try {
					IPath rootPath = root.getPath();
					IPath outputPath = root.getJavaProject()
							.getOutputLocation();
					if (rootPath.isPrefixOf(outputPath)
							&& !rootPath.equals(outputPath)) {
						// if the bin folder is inside of our root, don't allow
						// to name a package
						// like the bin folder
						IPath packagePath = rootPath.append(packName.replace(
								'.', '/'));
						if (outputPath.isPrefixOf(packagePath)) {
							status
									.setError(DialogFieldResources
											.getInstance()
											.getString(
													"PackageButtonDialogField.error.ClashOutputLocation")); //$NON-NLS-1$
							return status;
						}
					}
				} catch (JavaModelException e) {
					_log.error(e);
					// let pass
				}
			}

			_fCurrPackage = root.getPackageFragment(packName);
		} else {
			status.setError(""); //$NON-NLS-1$
		}
		return status;
	}
}
