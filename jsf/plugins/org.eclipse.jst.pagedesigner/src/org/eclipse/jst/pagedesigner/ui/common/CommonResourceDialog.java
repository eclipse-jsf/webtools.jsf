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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * This dialog shows IFile type resources within a IProject domain for
 * selection. The client can prvide the suffixs of files to filter when
 * candidates are shown on the tree,
 * 
 * TODO: dead?
 * 
 * @author mengbo
 */
class CommonResourceDialog extends TreeViewerSelectionDialog {
	private static final int WEBROOT_FOLDER_DEPTH = 2;

	// CommonResourceDialog.statusMessage = Please select an image file
	private static final String STATUS_MESSAGE = PageDesignerResources
			.getInstance().getString("CommonResourceDialog.statusMessage"); //$NON-NLS-1$

	private String _suffixs[] = null;

	private CommonResourceFilter _filter;

	/** Create the logger for this class */
	private static Logger _log = PDPlugin.getLogger(CommonResourceDialog.class);

	// The content provider
	class ProjectFileDialogContentProvider implements ITreeContentProvider {
		/**
		 * The visual part that is using this content provider is about to be
		 * disposed. Deallocate all allocated SWT resources.
		 */
		public void dispose() {
            // do nothing
		}

		/**
		 * @see ITreeContentProvider#getChildren
		 */
		public Object[] getChildren(Object element) {
			if (element instanceof IWorkspace) {
				IWorkspaceRoot root = ((IWorkspace) element).getRoot();
				IProject[] projects = root.getProjects();
				return projects;
			} else if (element instanceof IContainer) {
				if (element instanceof IProject) {
					IContainer container = (IContainer) element;
					if (container.isAccessible()) {
						try {
							IResource[] members = container.members();
							return members;
						} catch (CoreException e) {
							// "Error.CommonResourceDialog.0.1" = "Error in
							// project memeber querying"
							// "Error.CommonResourceDialog.0.2" = "Please refer
							// to the log for details"
							PDPlugin
									.getAlerts()
									.error(
											"Error.CommonResourceDialog.0.1", "Error.CommonResourceDialog.0.2"); //$NON-NLS-1$ //$NON-NLS-2$
							// Error.ProjectFileDialogContentProvider.0 = Core
							// error, you may need to restart the application
							_log
									.error(
											"Error.ProjectFileDialogContentProvider.0", e); //$NON-NLS-1$
						}
					}
				} else if (element instanceof IFolder) {
					// Process the folder container
					IContainer container = (IContainer) element;
					if (container.isAccessible()) {
						try {
							// Filter all the files under the project and only
							// show
							// the folder in the container selection dialog
							List children = new ArrayList();
							IResource[] members = container.members();
							for (int i = 0; i < members.length; i++) {
								if (!members[i].getName().equals(
										IFileFolderConstants.FOLDER_WEBINF)
										&& !members[i]
												.getName()
												.equals(
														IFileFolderConstants.FOLDER_METAINF)) {
									children.add(members[i]);
								}
							}
							return children.toArray();
						} catch (CoreException e) {
							// "Error.CommonResourceDialog.0.1" = "Error in
							// project memeber querying"
							// "Error.CommonResourceDialog.0.2" = "Please refer
							// to the log for details"
							PDPlugin
									.getAlerts()
									.error(
											"Error.CommonResourceDialog.0.1", "Error.CommonResourceDialog.0.2"); //$NON-NLS-1$ //$NON-NLS-2$
							// Error.ProjectFileDialogContentProvider.0 = core
							// error, you may need to restart the application
							_log
									.error(
											"Error.ProjectFileDialogContentProvider.0", e); //$NON-NLS-1$
						}
					}
				}
			}
			return new Object[0];
		}

		/**
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements
		 */
		public Object[] getElements(Object element) {
			return getChildren(element);
		}

		/**
		 * @see ITreeContentProvider#getParent
		 */
		public Object getParent(Object element) {
			if (element instanceof IResource) {
				return ((IResource) element).getParent();
			}
			return null;
		}

		/**
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren
		 */
		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}

		/**
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // do nothing
		}

	}

	// The default resource filter
	static class CommonResourceFilter extends ViewerFilter {
		private String _suffixs[] = { IFileFolderConstants.EXT_PROPERTIES };

		private Logger _mylog = PDPlugin.getLogger(CommonResourceFilter.class);

		private IProject _project;

		/**
		 * @return Returns the _suffixs.
		 */
		public String[] getSuffixs() {
			return _suffixs;
		}

		/**
		 * @param _suffixs
		 *            The _suffixs to set.
		 */
		public void setSuffixs(String[] _suffixs) {
			this._suffixs = _suffixs;
		}

		/**
		 * @param project
		 */
		public CommonResourceFilter(IProject project) {
			_project = project;
		}

		/**
		 * @param project
		 *            The _project to set.
		 */
		public void setProject(IProject project) {
			this._project = project;
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
			if (element instanceof IWorkspace) {
				return true;
			} else if (element instanceof IFile) {
				if (Arrays.asList(_suffixs).contains(
						((IFile) element).getFileExtension())) {
					return true;
				}
			} else if (element instanceof IContainer) {
				if (!((IContainer) element).isAccessible()) {
					return false;
				}
				if (element instanceof IProject) {
					IProject container = (IProject) element;
					if (isWebAppProject(container)
							&& this.isSameProject(container, _project)) {
						return true;
					}
                    return false;
				} else if (element instanceof IFolder) {
					IContainer container = (IContainer) element;
					try {
						if (container.getName().equals(
								IFileFolderConstants.FOLDER_WEBINF)
								|| container.getName().equals(
										IFileFolderConstants.FOLDER_METAINF)) {
							return false;
						}
						IResource[] members = container.members();
						for (int i = 0; i < members.length; i++) {
							{
								if (select(viewer, members[i].getParent(),
										members[i])) {
									return true;
								}
							}
						}
					} catch (CoreException e) {
						// "Error.ImgFileFilter.0" = "Error in filtering the
						// tree", "Error.ImgFileFilter.2 = ""CoreException is
						// thrown, please refer to error log for details"
						// "Error.ProjectFileDialogContentProvider.0" = core
						// error, show alert dialog to user.
						PDPlugin
								.getAlerts()
								.detailError(
										"Error.ImgFileFilter.0", "Error.ImgFileFilter.2");  //$NON-NLS-1$//$NON-NLS-2$
						_mylog.error(
								"Error.ProjectFileDialogContentProvider.0", e); //$NON-NLS-1$
						return false;
					}
				}
			}
			// we don't select any other types of resources.
			return false;
		}

		/**
		 * Determines if a project is a Web Application project by the presence
		 * of an associated Web Application Nature.
		 * 
		 * @return boolean - True, when project is a Web Application project
		 */
		private boolean isWebAppProject(IProject project) {
			return WebrootUtil.isValidWebProject(project);
		}

		private boolean isSameProject(IProject orig, IProject dst) {
			String currentProjectName = orig.getFullPath()
					.toString().trim();
			String projectName = dst.getFullPath().toString().trim();
			return projectName.equalsIgnoreCase(currentProjectName);
		}
	}

	/**
	 * This is a dialog for common resource selection, the resouce supported
	 * include IFolder, IProject, IFile, user can provide
	 * 
	 * @param parentShell
	 * @param project
	 */
	public CommonResourceDialog(Shell parentShell, IProject project) {
		super(parentShell, STATUS_MESSAGE);
		setContentProvider(new ProjectFileDialogContentProvider());
		setLabelProvider(WorkbenchLabelProvider
				.getDecoratingWorkbenchLabelProvider());
		_filter = new CommonResourceFilter(project);
		setFilter(_filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.ui.common.SelectionTreeViewerDialog#findInputElement()
	 */
	protected Object findInputElement() {
		return ResourcesPlugin.getWorkspace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.ui.common.SelectionTreeViewerDialog#isValidSelection(java.lang.Object)
	 */
	protected boolean isValidSelection(Object selection) {
		if (getContainerFullPath(selection) == null) {
			return false;
		}
        int depth = getContainerFullPath(selection).segmentCount();
        // The location is within WEBROOT PATH?
        if ((selection instanceof IFile) && depth >= WEBROOT_FOLDER_DEPTH) {
        	// Null means no filter is set
        	if (_suffixs == null) {
        		return true;
        	}
        	// The extension is supported?
        	else if (_suffixs != null
        			&& Arrays.asList(_suffixs).contains(
        					((IFile) selection).getFileExtension())) {
        		return true;
        	}
        }
        // None of above conditions, invalid.
        return false;
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
		// Only one element is set by us.
		IPath returnValue = getWebRelatedPath(objects[0]);
		if (returnValue != null) {
			if (!(returnValue.toString().startsWith(
					IFileFolderConstants.PATH_SEPARATOR) || returnValue
					.toString().startsWith("\\"))) { //$NON-NLS-1$
				Path tempPath = new Path(IFileFolderConstants.PATH_SEPARATOR
						+ returnValue.toString());
				returnValue = tempPath;
			}
		}
		return new Object[] { returnValue };
	}

	private IPath getContainerFullPath(Object _selectedElement) {
		if (_selectedElement == null) {
			return null;
		} else if (_selectedElement instanceof IContainer) {
			return ((IContainer) _selectedElement).getFullPath();
		} else if (_selectedElement instanceof IFile) {
			return ((IFile) _selectedElement).getFullPath();
		}
		return null;
	}

	private IPath getWebRelatedPath(Object _selectedElement) {
		IPath result = null;
		if (_selectedElement == null) {
			return null;
		} else if (_selectedElement instanceof IContainer) {
			result = ((IContainer) _selectedElement).getFullPath();
		} else if (_selectedElement instanceof IFile) {
			result = ((IFile) _selectedElement).getFullPath();
		}
		if (result != null) {
			if (result.segmentCount() > 2) {
				result = result.removeFirstSegments(2);
			}
		}
		return result;
	}
}
