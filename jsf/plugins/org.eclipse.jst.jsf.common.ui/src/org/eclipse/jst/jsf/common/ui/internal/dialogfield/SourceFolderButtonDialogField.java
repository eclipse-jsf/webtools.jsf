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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jdt.ui.JavaElementSorter;
import org.eclipse.jdt.ui.StandardJavaElementContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

/**
 * This class provides a source folder selection DialogFiled. There are two
 * conditions: 1. If you set the IProject instance, the source folder selection
 * will only be limited in the specified project 2. If you set null, the source
 * folder selection is allowed in all workspaces
 * 
 * 
 * @author mengbo
 * @version 1.5
 */
public class SourceFolderButtonDialogField extends StringButtonDialogField {
	/** Create the logger for this class */
	private static Logger _log = JSFUICommonPlugin
			.getLogger(SourceFolderButtonDialogField.class);

	/**
	 * package fragment root corresponding to the input type (can be null)
	 */
	private IPackageFragmentRoot _fCurrRoot;

	private IProject _project;

	/**
	 * @param adapter
	 */
	public SourceFolderButtonDialogField(IProject project) {
		super(null);

		_project = project;

		setLabelText(DialogFieldResources.getInstance().getString(
				"SourceFolderButtonDialogField.container.label"));

		setButtonLabel(DialogFieldResources.getInstance().getString(
				"SourceFolderButtonDialogField.container.button"));

		setStringButtonAdapter(new IStringButtonAdapter() {
			public void changeControlPressed(DialogField field) {
				containerChangeControlPressed(field);
			}

		});
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
	 * @param field
	 */
	protected void containerChangeControlPressed(DialogField field) {
		// take the current jproject as init element of the dialog
		IPackageFragmentRoot root = getPackageFragmentRoot();
		root = chooseSourceContainer(root);
		if (root != null) {
			setPackageFragmentRoot(root, true);
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
	public void setPackageFragmentRoot(IPackageFragmentRoot root,
			boolean canBeModified) {
		_fCurrRoot = root;
		String str = (root == null) ? "" : root.getPath().makeRelative().toString(); //$NON-NLS-1$
		setText(str);
		setEnabled(canBeModified);
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
	 * Returns the current text of source folder text field.
	 * 
	 * @return the text of the source folder text field
	 */
	public String getPackageFragmentRootText() {
		return getText();
	}

	/**
	 * choose source container dialog
	 * 
	 * @param initElement
	 * @return
	 */
	private IPackageFragmentRoot chooseSourceContainer(IJavaElement initElement) {
		Class[] acceptedClasses = new Class[] { IPackageFragmentRoot.class,
				IJavaProject.class, };

		TypedElementSelectionValidator validator = new TypedElementSelectionValidator(
				acceptedClasses, false) {
            // TODO: never used
//			public boolean isSelectedValid(Object element) {
//				try {
//					if (element instanceof IJavaProject) {
//						IJavaProject jproject = (IJavaProject) element;
//						IPath path = jproject.getProject().getFullPath();
//						return (jproject.findPackageFragmentRoot(path) != null);
//					} else if (element instanceof IPackageFragmentRoot) {
//						return (((IPackageFragmentRoot) element).getKind() == IPackageFragmentRoot.K_SOURCE);
//					}
//					return true;
//				} catch (JavaModelException e) {
//					_log.error("JavaModelException"); // just log, no ui in
//					// validation
//				}
//				return false;
//			}
		};

		acceptedClasses = new Class[] { IJavaModel.class,
				IPackageFragmentRoot.class, IJavaProject.class, };
		ViewerFilter filter = new TypedViewerFilter(acceptedClasses) {
			public boolean select(Viewer viewer, Object parent, Object element) {
				if (element instanceof IPackageFragmentRoot) {
					try {
						return (((IPackageFragmentRoot) element).getKind() == IPackageFragmentRoot.K_SOURCE);
					} catch (JavaModelException e) {
						_log.error(e); // just log, no ui
						// in validation
						return false;
					}
				}
				return super.select(viewer, parent, element);
			}
		};

		StandardJavaElementContentProvider provider = new StandardJavaElementContentProvider();
		ILabelProvider labelProvider = new JavaElementLabelProvider(
				JavaElementLabelProvider.SHOW_DEFAULT);
		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				getShell(), labelProvider, provider);
		dialog.setValidator(validator);
		dialog.setSorter(new JavaElementSorter());
		// HibernateWizardPage.ChooseSourceContainerDialog.Title = Folder
		// Selection
		dialog
				.setTitle(DialogFieldResources
						.getInstance()
						.getString(
								"SourceFolderButtonDialogField.ChooseSourceContainerDialog.Title")); //$NON-NLS-1$
		// HibernateWizardPage.ChooseSourceContainerDialog.Description = Choose
		// a folder:
		dialog
				.setMessage(DialogFieldResources
						.getInstance()
						.getString(
								"SourceFolderButtonDialogField.ChooseSourceContainerDialog.Description")); //$NON-NLS-1$
		dialog.addFilter(filter);
		if (_project != null) {
			dialog.setInput(JavaCore.create(_project));
		} else {
			dialog.setInput(JavaCore.create(ResourcesPlugin.getWorkspace()
					.getRoot()));
		}
		dialog.setInitialSelection(initElement);

		if (dialog.open() == Window.OK) {
			Object element = dialog.getFirstResult();
			if (element instanceof IJavaProject) {
				IJavaProject jproject = (IJavaProject) element;
				return jproject.getPackageFragmentRoot(jproject.getProject());
			} else if (element instanceof IPackageFragmentRoot) {
				return (IPackageFragmentRoot) element;
			}
			return null;
		}
		return null;
	}

	/**
	 * This method updates the model and returns an error status. The underlying
	 * model is only valid if the returned status is OK.
	 * 
	 * @return the model's error status
	 */
	public IStatus getChangedStatus() {
		StatusInfo status = new StatusInfo();

		_fCurrRoot = null;
		String str = getPackageFragmentRootText();
		if (str.length() == 0) {
			// SourceFolderButtonDialogField.error.EnterContainerName = Folder
			// name is empty.
			status.setError(DialogFieldResources.getInstance().getString(
					"SourceFolderButtonDialogField.error.EnterContainerName")); //$NON-NLS-1$
			return status;
		}
		IPath path = new Path(str);
		IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(
				path);
		if (res != null) {
			int resType = res.getType();
			if (resType == IResource.PROJECT || resType == IResource.FOLDER) {
				IProject proj = res.getProject();
				if (!proj.isOpen()) {
					status
							.setError(DialogFieldResources
									.getInstance()
									.getString(
											"SourceFolderButtonDialogField.error.ProjectClosed", proj.getFullPath().toString())); //$NON-NLS-1$
					return status;
				}
				if (_project != null && proj != _project) {
					// HibernateWizardPage.error.NotSameProject = The project
					// should be \''{0}\''.
					status
							.setError(DialogFieldResources
									.getInstance()
									.getString(
											"SourceFolderButtonDialogField.error.NotSameProject", _project.getName())); //$NON-NLS-1$
					return status;
				}
				IJavaProject jproject = JavaCore.create(proj);
				_fCurrRoot = jproject.getPackageFragmentRoot(res);
				if (res.exists()) {
					try {
						if (!proj.hasNature(JavaCore.NATURE_ID)) {
							if (resType == IResource.PROJECT) {
								status
										.setError(DialogFieldResources
												.getInstance()
												.getString(
														"SourceFolderButtonDialogField.warning.NotAJavaProject", proj.getName())); //$NON-NLS-1$
							} else {
								status
										.setWarning(DialogFieldResources
												.getInstance()
												.getString(
														"SourceFolderButtonDialogField.warning.NotInAJavaProject", proj.getName())); //$NON-NLS-1$
							}
							return status;
						}
					} catch (CoreException e) {
						status
								.setWarning(DialogFieldResources
										.getInstance()
										.getString(
												"SourceFolderButtonDialogField.warning.NotAJavaProject", proj.getName())); //$NON-NLS-1$
					}
					if (!jproject.isOnClasspath(_fCurrRoot)) {
						status
								.setWarning(DialogFieldResources
										.getInstance()
										.getString(
												"SourceFolderButtonDialogField.warning.NotOnClassPath", str)); //$NON-NLS-1$
					}
					if (_fCurrRoot.isArchive()) {
						status
								.setError(DialogFieldResources
										.getInstance()
										.getString(
												"SourceFolderButtonDialogField.error.ContainerIsBinary", str)); //$NON-NLS-1$
						return status;
					}
				}
				return status;
			}
            status.setError(DialogFieldResources.getInstance().getString(
                    "SourceFolderButtonDialogField.error.NotAFolder", str)); //$NON-NLS-1$
            return status;
		}
        status
                 .setError(DialogFieldResources
                        .getInstance()
                        .getString(
                                "SourceFolderButtonDialogField.error.ContainerDoesNotExist", str)); //$NON-NLS-1$
        return status;
	}

}
