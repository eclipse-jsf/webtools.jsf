/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.actions.StackAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.views.palette.PalettePage;
import org.eclipse.gef.ui.views.palette.PaletteViewerPage;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.facesconfig.common.actions.IOpenPage;
import org.eclipse.jst.jsf.facesconfig.edit.provider.FacesConfigItemProviderAdapterFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.ui.page.ComponentsPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.IntroductionPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.ManagedBeanPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OverviewPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.DelegatingZoomManager;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.FacesConfigEditorActionBarContributor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowEditor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DelegatingCommandStack;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.EMFCommandStackGEFAdapter;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.layout.PageflowLayoutManager;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.FC2PFTransformer;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.FacesConfigModelAdapter;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.wst.common.ui.properties.internal.provisional.ITabbedPropertySheetPageContributor;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * 
 * @author sfshi
 * 
 */
public class FacesConfigEditor extends FormEditor implements
		IEditingDomainProvider, ISelectionProvider, IMenuListener,
		IViewerProvider, IGotoMarker, ITabbedPropertySheetPageContributor,
		IOpenPage {
	/**
	 * editing domain that is used to track all changes to the model
	 */
	private AdapterFactoryEditingDomain editingDomain;

	/**
	 * adapter factory used for providing views of the model
	 */
	private ComposedAdapterFactory adapterFactory;

	/** id of the pageflowPage */
	private int pageflowPageID;

	private int managedBeanPageID;

	private int componentsPageID;

	private int othersPageID;

	// private IEditorPart _prevPart;

	/** The pageflow viewer */
	private PageflowEditor pageflowPage;

	/**
	 * model resource
	 */
	// private FacesConfigResourceImpl modelResource;
	private IPropertySheetPage propertySheetPage;

	protected Collection selectionChangedListeners = new ArrayList();

	private FacesConfigArtifactEdit facesConfigAtrifactEdit;

	public static final String EDITOR_ID = "org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor";

	protected ISelection editorSelection = StructuredSelection.EMPTY;

	private IContentOutlinePage outlinePage;

	/**
	 * the index of source page.
	 */
	private int sourcePageId;

	public FacesConfigEditor() {
		initializeEMF();
	}

	/**
	 * This listens for workspace changes. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
		public void resourceChanged(IResourceChangeEvent event) {
			// Only listening to these.
			// if (event.getType() == IResourceDelta.POST_CHANGE)
			{
				IResourceDelta delta = event.getDelta();
				try {
					class ResourceDeltaVisitor implements IResourceDeltaVisitor {
						protected ResourceSet resourceSet = editingDomain
								.getResourceSet();

						protected Collection changedResources = new ArrayList();

						protected Collection removedResources = new ArrayList();

						public boolean visit(IResourceDelta delta) {
							if (delta.getFlags() != IResourceDelta.MARKERS
									&& delta.getResource().getType() == IResource.FILE) {
								if ((delta.getKind() & (IResourceDelta.CHANGED | IResourceDelta.REMOVED)) != 0) {
									Resource resource = resourceSet
											.getResource(URI.createURI(delta
													.getFullPath().toString()),
													false);
									if (resource != null) {
										if ((delta.getKind() & IResourceDelta.REMOVED) != 0) {
											removedResources.add(resource);
										} else {
											changedResources.add(resource);
										}
									}
								}
							}

							return true;
						}

						public Collection getChangedResources() {
							return changedResources;
						}

						public Collection getRemovedResources() {
							return removedResources;
						}
					}

					ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
					delta.accept(visitor);

					if (!visitor.getRemovedResources().isEmpty()) {
						removedResources.addAll(visitor.getRemovedResources());
						if (!isDirty()) {
							getSite().getShell().getDisplay().asyncExec(
									new Runnable() {
										public void run() {
											getSite().getPage().closeEditor(
													FacesConfigEditor.this,
													false);
											FacesConfigEditor.this.dispose();
										}
									});
						}
					}

					if (!visitor.getChangedResources().isEmpty()) {
						changedResources.addAll(visitor.getChangedResources());
					}
				} catch (CoreException exception) {
					// TODO log it.
					// FacesConfigEditorPlugin.INSTANCE.log(exception);
				}
			}
		}
	};

	protected IPartListener partListener = new IPartListener() {
		public void partActivated(IWorkbenchPart p) {
			if (p instanceof ContentOutline) {
				// if (((ContentOutline)p).getCurrentPage() ==
				// contentOutlinePage) {
				// getActionBarContributor().setActiveEditor(FacesConfigEditor.this);
				//
				// setCurrentViewer(contentOutlineViewer);
				// }
			} else if (p instanceof PropertySheet) {
				if (((PropertySheet) p).getCurrentPage() == propertySheetPage) {
					getActionBarContributor().setActiveEditor(
							FacesConfigEditor.this);
					handleActivate();
				}
			} else if (p == FacesConfigEditor.this) {
				handleActivate();
			}
		}

		public void partBroughtToTop(IWorkbenchPart p) {
		}

		public void partClosed(IWorkbenchPart p) {
		}

		public void partDeactivated(IWorkbenchPart p) {
		}

		public void partOpened(IWorkbenchPart p) {
		}
	};

	/**
	 * Resources that have been removed since last activation.
	 * 
	 * @generated
	 */
	Collection removedResources = new ArrayList();

	/**
	 * Resources that have been changed since last activation.
	 * 
	 * @generated
	 */
	Collection changedResources = new ArrayList();

	/**
	 * Resources that have been saved.
	 * 
	 * @generated
	 */
	Collection savedResources = new ArrayList();

	/**
	 * Initializes the EMF support.
	 */
	private void initializeEMF() {
		// create an adapter factory that yields item providers
		List factories = new ArrayList();
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new FacesConfigItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
		adapterFactory = new ComposedAdapterFactory(factories);

		// create the command stack that will notify this editor as commands are
		// executed
		BasicCommandStack commandStack = new BasicCommandStack();
		commandStack
				.addCommandStackListener(new org.eclipse.emf.common.command.CommandStackListener() {
					public void commandStackChanged(final EventObject event) {
						getContainer().getShell().getDisplay().asyncExec(
								new Runnable() {
									public void run() {
										editorDirtyStateChanged();
									}
								});
					}
				});
		// commandStack.addCommandStackListener(this);
		// create the editing domain with a special command stack
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				commandStack, new HashMap());
		// editingDomain.getResourceSet().getResourceFactoryRegistry()
		// .getExtensionToFactoryMap().put(
		// "xml", //$NON-NLS-1$
		// new FacesConfigResourceFactory(
		// EMF2DOMRendererFactoryDefaultHandler.INSTANCE
		// .getDefaultRendererFactory()));
	}

	/*
	 * @see org.eclipse.ui.IEditorPart#init(org.eclipse.ui.IEditorSite,
	 *      org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		setPartName(input.getName());
		if (!isValidInput(input)) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().openEditor(input,
							"org.eclipse.ui.DefaultTextEditor"); //$NON-NLS-1$

			close(false);
			return;
		}

		// ensure the visibility of the palette view and property veiw.
		// IWorkbenchWindow dw = PlatformUI.getWorkbench()
		// .getActiveWorkbenchWindow();
		// IWorkbenchPage page = dw.getActivePage();
		// if (page != null) {
		// page.showView("org.eclipse.gef.ui.palette_view");
		// }
		createActions();

		// we want to listen for our own activation
		// IWorkbenchWindow window = getSite().getWorkbenchWindow();
		// window.getPartService().addPartListener(getPartListener());
		// window.getShell().addShellListener(getPartListener());
		// site.setSelectionProvider(this);
		// site.getPage().addPartListener(partListener);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
	}

	/*
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		IFile inputFile = (IFile) input.getAdapter(IFile.class);
		if (inputFile != null) {
			IProject project = inputFile.getProject();
			IPath inputPath = inputFile.getFullPath();
			// loadModel(inputPath, inputFile.isReadOnly());
			loadModel(project, inputPath);
		}
	}

	/**
	 * Loads the configuration model from the given path.
	 * 
	 */
	private void loadModel(IProject project, IPath modelPath) {

		IFolder webContentFolder = WebrootUtil.getWebContentFolder(project);
		Assert.isTrue(webContentFolder != null && webContentFolder.exists());

		IPath relativePath = modelPath;
		if (webContentFolder.getFullPath().isPrefixOf(modelPath)) {
			relativePath = modelPath.removeFirstSegments(webContentFolder
					.getFullPath().segmentCount());
		}
		facesConfigAtrifactEdit = FacesConfigArtifactEdit
				.getFacesConfigArtifactEditForWrite(project, relativePath
						.toString());
	}

	protected void addPages() {
		try {
			IntroductionPage page1 = new IntroductionPage(this);
			addPage(page1, null);
			
			IFormPage overviewPage = new OverviewPage(this);
			addPage(overviewPage, null);
			
			// Page flow
			createAndAddPageflowPage();
			
			// pages
			IFormPage managedBeanPage = new ManagedBeanPage(this);
			managedBeanPageID = addPage(managedBeanPage, null);
			IFormPage componentsPage = new ComponentsPage(this);
			componentsPageID = addPage(componentsPage, null);
			IFormPage othersPage = new OthersPage(this);
			othersPageID = addPage(othersPage, null);

			sourcePage = new StructuredTextEditor();

			sourcePage.setEditorPart(this);

			sourcePageId = addPage(sourcePage, this.getEditorInput());
			setPageText(sourcePageId,
					NewEditorResourcesNLS.FacesConfigEditor_Source_TabName);
			sourcePage.update();

		} catch (PartInitException e) {
			EditorPlugin.getDefault().getLog().log(
					new Status(IStatus.ERROR, EditorPlugin.getPluginId(),
							IStatus.OK, e.getMessage() == null ? "" : e
									.getMessage(), e));
		}

	}

	/**
	 * Creates the pageflow page of the multi-page editor.
	 */
	protected void createAndAddPageflowPage() throws PartInitException {
		pageflowPage = new PageflowEditor(this);
		pageflowPageID = addPage(pageflowPage, getEditorInput());
		setPageText(pageflowPageID, "Navigation Rule");

		addPageActionRegistry(pageflowPage);

		getFacesConfigAdapter().setPageflowManager(
				pageflowPage.getPageflowManager());
		getFacesConfigAdapter().setFacesConfigEMFModel(getFacesConfig());
		FC2PFTransformer.getInstance().setFacesConfig(getFacesConfig());
		FC2PFTransformer.getInstance().setPageflow(pageflowPage.getPageflow());
		boolean fornew = getFacesConfigAdapter()
				.updatePageflowFromFacesConfig();
		pageflowPage.setGraphicalViewerContents(pageflowPage.getPageflow());
		if (fornew) {
			PageflowLayoutManager.getInstance().layoutPageflow(
					pageflowPage.getPageflow());
		}
		FC2PFTransformer.getInstance().setListenToNotify(true);
		// getFacesConfigAdapter().updatePageflowFromFacesConfig();
	}

	/**
	 * get the action's registry of sub pages.
	 * 
	 */
	protected void addPageActionRegistry(IEditorPart page) {
		if (page != null) {
			ActionRegistry pageActionRegisty = (ActionRegistry) page
					.getAdapter(ActionRegistry.class);
			if (pageActionRegisty != null) {
				for (Iterator iter = pageActionRegisty.getActions(); iter
						.hasNext();) {
					getActionRegistry().registerAction((IAction) iter.next());
				}
			}
		}
	}

	/** the editor's action registry */
	private ActionRegistry actionRegistry = null;

	/**
	 * Returns the action registry of this editor.
	 * 
	 * @return - the action registry
	 */
	protected ActionRegistry getActionRegistry() {
		if (null == actionRegistry) {
			actionRegistry = new ActionRegistry();
		}

		return actionRegistry;
	}

	/** the faces-config model adapter */
	private FacesConfigModelAdapter facesConfigAdapter;

	/**
	 * get the faces config model and pageflow model 's adapter.
	 * 
	 * @return - <code>FacesConfigModelAdapter</code>
	 */
	private FacesConfigModelAdapter getFacesConfigAdapter() {
		if (facesConfigAdapter == null) {
			facesConfigAdapter = new FacesConfigModelAdapter();
		}
		return facesConfigAdapter;
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	/**
	 * Returns the root object of the configuration model.
	 * 
	 * @return the root object
	 */
	public FacesConfigType getFacesConfig() {
		// return modelResource.getFacesConfig();
		FacesConfigType facesConfig = facesConfigAtrifactEdit.getFacesConfig();
		if (!FC2PFTransformer.getInstance().isAdapted(facesConfig)) {
			Iterator contents = facesConfig.getNavigationRule().iterator();
			while (contents.hasNext()) {
				EObject next = ((EObject) contents.next());
				TreeIterator children = next.eAllContents();
				while (children.hasNext()) {
					FC2PFTransformer.getInstance().adapt(
							(EObject) children.next());
				}
				FC2PFTransformer.getInstance().adapt(next);
			}
			FC2PFTransformer.getInstance().adapt(facesConfig);
		}
		return facesConfig;
	}

	/*
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	public boolean isDirty() {
		return ((BasicCommandStack) editingDomain.getCommandStack())
				.isSaveNeeded()
				|| super.isDirty();
	}

	/** The source text editor. */
	private StructuredTextEditor sourcePage;

	/**
	 * update the source editor to sychronize the saved pageflow.
	 * 
	 */
	// private void updateSourcePageFromPageflow() {
	// // System.out
	// // .println("FacesConfigEditor: update source page from page flow");
	// // InputStream inputStreamOfFacesConfig = null;
	// // try {
	// // ISourceEditingTextTools editingTools = (ISourceEditingTextTools)
	// // sourcePage
	// // .getAdapter(ISourceEditingTextTools.class);
	// //
	// // if (editingTools != null) {
	// // inputStreamOfFacesConfig = new ByteArrayInputStream(
	// // editingTools.getDocument().get().getBytes("UTF-8"));//$NON-NLS-1$
	// // }
	// //
	// // } catch (UnsupportedEncodingException e) {
	// // // PageflowEditor.Encoding.Unsupported = Unsupported Encoding.
	// // // log.error("PageflowEditor.Encoding.Unsupported", e);
	// // // //$NON-NLS-1$
	// // }
	// //
	// // if (inputStreamOfFacesConfig == null) {
	// // return;
	// // }
	// // UpdateFacesConfigCommand updateCommand = new
	// // UpdateFacesConfigCommand(
	// // getFacesConfigAdapter(), inputStreamOfFacesConfig);
	// //
	// // updateCommand.execute();
		// }
	/**
	 * This class listens for command stack changes of the pages contained in
	 * this editor and decides if the editor is dirty or not.
	 */
	private class MultiPageCommandStackListener implements CommandStackListener {

		/** the observed command stacks */
		private List commandStacks = new ArrayList(2);

		/** to get the editorpart from command stack */
		private HashMap mapEditorCommandStack = new HashMap();

		private boolean saveLocation = false;

		/**
		 * Adds a <code>CommandStack</code> to observe.
		 * 
		 * @param commandStack
		 */
		public void addCommandStack(CommandStack commandStack,
				IEditorPart editor) {
			if (commandStack == null) {
				return;
			}
			if (mapEditorCommandStack.get(commandStack) == editor) {
				return;
			}
			commandStacks.add(commandStack);
			commandStack.addCommandStackListener(this);
			mapEditorCommandStack.put(commandStack, editor);
		}

		/**
		 * set the dirty status for the models of different editor
		 * 
		 * @param editor -
		 *            editor, e.g., pageflow or databinding page.
		 * @param dirty -
		 *            true or false
		 */
		private void setEditorDirty(IEditorPart editor, boolean dirty) {
			if (editor != null) {
				if (editor == pageflowPage) {
					getFacesConfigAdapter().setPageflowDirty(dirty);
				}
			}
		}

		/** the list of action ids that are to CommandStack actions */
		private List stackActionIDs = new ArrayList();

		/**
		 * Updates the specified actions.
		 * 
		 * @param actionIds -
		 *            the list of ids of actions to update
		 */
		private void updateActions(List actionIds) {
			for (Iterator ids = actionIds.iterator(); ids.hasNext();) {
				IAction action = getActionRegistry().getAction(ids.next());
				if (null != action && action instanceof UpdateAction) {
					((UpdateAction) action).update();
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see CommandStackListener#commandStackChanged(java.util.EventObject)
		 */
		public void commandStackChanged(EventObject event) {
			// enable or disable the actions
			updateActions(stackActionIDs);
			getFacesConfigAdapter().setFacesConfigDirty(true);
			if (((CommandStack) event.getSource()).isDirty()) {
				// set the editor's model dirty status
				setEditorDirty((IEditorPart) mapEditorCommandStack
						.get(((CommandStack) event.getSource())), true);
				// at least one command stack is dirty,
				// so the multi page editor is dirty too
				setDirty(true);
			} else {
				// set the editor's model dirty status, if it is from not save
				// location.
				if (!saveLocation) {
					setEditorDirty((IEditorPart) mapEditorCommandStack
							.get(((CommandStack) event.getSource())), true);
					setDirty(true);
				} else {
					setDirty(false);
				}
			}
		}

		/** the pageflow page editor's dirty state */
		private boolean isDirty = false;

		/**
		 * Changes the dirty state.
		 * 
		 * @param dirty -
		 *            dirty state
		 */
		public void setDirty(boolean dirty) {
			if (isDirty != dirty) {
				isDirty = dirty;
				firePropertyChange(IEditorPart.PROP_DIRTY);
			}
		}

		/**
		 * Disposed the listener
		 */
		public void dispose() {
			for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
				((CommandStack) stacks.next()).removeCommandStackListener(this);
			}
			commandStacks.clear();
		}

		/**
		 * Marks every observed command stack beeing saved. This method should
		 * be called whenever the editor/model was saved.
		 */
		public void markSaveLocations() {
			saveLocation = true;
			for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
				CommandStack stack = (CommandStack) stacks.next();
				stack.markSaveLocation();
			}
			saveLocation = false;
		}

		/**
		 * Flushes every observed command stack and resets the save location to
		 * zero.
		 */
		public void flush() {
			for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
				CommandStack stack = (CommandStack) stacks.next();
				stack.flush();
			}
		}
	}

	public String getContributorId() {
		return EDITOR_ID;
	}

	/**
	 * Update the pageflow editor to sychronize with the source editor of
	 * faces-config.
	 * 
	 */
	public void updatePageflowPage(boolean addToCommandStack, boolean layout) {
		// InputStream inputStreamOfFacesConfig = null;
		// try {
		// ISourceEditingTextTools editingTools = (ISourceEditingTextTools)
		// sourcePage
		// .getAdapter(ISourceEditingTextTools.class);
		//
		// if (editingTools != null) {
		// inputStreamOfFacesConfig = new ByteArrayInputStream(
		// editingTools.getDocument().get().getBytes("UTF-8"));//$NON-NLS-1$
		// }
		// } catch (UnsupportedEncodingException e) {
		// // PageflowEditor.Encoding.Unsupported = Unsupported Encoding.
		// log.error("PageflowEditor.Encoding.Unsupported", e); //$NON-NLS-1$
		// }
		//
		// if (inputStreamOfFacesConfig == null) {
		// return;
		// }

		// Pageflow pageflowFromFacesConfig = null;

		try {

			Assert.isTrue(getFacesConfigAdapter().getPageflowFromFacesConfig(
					layout) != null);// getPageflowFromFacesConfig();
		} catch (IOException e) {
			// PageflowEditor.Transform.Error.GetPageflowFromFacesConfig =
			// Failed to get pageflow model from faces-config'a navigation rule.
			// log
			// .error(
			// "PageflowEditor.Transform.Error.GetPageflowFromFacesConfig",
			// e);//$NON-NLS-1$
		}

		// if (null != pageflowFromFacesConfig) {
		// UpdatePageflowCommand updateCommand = new UpdatePageflowCommand();
		// // updateCommand.setPageflowUpdateDelta(pageflowPage.getPageflow(),
		// // pageflowFromFacesConfig);
		// if (addToCommandStack) {
		// pageflowPage.getCommandStack().execute(updateCommand);
		// } else {
		// updateCommand.execute();
		// }
		// }
		getFacesConfigAdapter().setPageflowSynchronizeState(true);
	}

	/** the <code>CommandStackListener</code> */
	private MultiPageCommandStackListener multiPageCommandStackListener = null;

	/**
	 * Returns the global command stack listener.
	 * 
	 * @return the <code>CommandStackListener</code>
	 */
	protected MultiPageCommandStackListener getMultiPageCommandStackListener() {
		if (null == multiPageCommandStackListener) {
			multiPageCommandStackListener = new MultiPageCommandStackListener();
		}
		return multiPageCommandStackListener;
	}

	/*
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		// do the work within an operation because this is a long running
		// activity that modifies the workbench
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			public void execute(IProgressMonitor monitor) {
				try {
					// modelResource.save(Collections.EMPTY_MAP);
					facesConfigAtrifactEdit.getDeploymentDescriptorResource()
							.save(Collections.EMPTY_MAP);
					// if (getActiveEditor() == sourcePage) {
					// if (getFacesConfigAdapter().isFacesConfigDirty()) {
					// // if the faces-config file is updated in the source
					// // page,
					// // the pageflow should be updated!
					// updatePageflowPage(false, false);
					// }
					// }
					// else if (getActiveEditor() == pageflowPage) {
					// if (getFacesConfigAdapter().isPageflowDirty()) {
					// // if the pageflow is modified in the pageflow page,
					// // the source editor should be updated.
					// updateSourcePageFromPageflow();
					// }
					// }
					IFile file = ((IFileEditorInput) getEditorInput())
							.getFile();
					pageflowPage.doSave(file, monitor);
					sourcePage.doSave(monitor);
					getMultiPageCommandStackListener().markSaveLocations();
				} catch (Exception e) {
					EditorPlugin.getDefault().getLog().log(
							new Status(IStatus.ERROR, EditorPlugin
									.getPluginId(), IStatus.OK,
									e.getMessage() == null ? "" : e
											.getMessage(), e));
				}
			}
		};
		try {
			// commit all pending changes in form pages
			for (Iterator iter = pages.iterator(); iter.hasNext();) {
				Object obj = iter.next();
				if (obj instanceof FormPage) {
					((FormPage) obj).doSave(monitor);
				}
				// else if (obj instanceof PageflowEditor) {
				// ((PageflowEditor) obj).doSave(monitor);
				// }

			}
			operation.run(null);// .run(true, false,
			// operation;
			// runs the operation, and shows progress
			// new ProgressMonitorDialog();

			// refresh the necessary state
			((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();

			editorDirtyStateChanged();
		} catch (Exception e) {
			EditorPlugin.getDefault().getLog().log(
					new Status(IStatus.ERROR, EditorPlugin.getPluginId(),
							IStatus.OK, e.getMessage(), e));
		}
	}

	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				doSaveAs(URI.createPlatformResourceURI(file.getFullPath()
						.toString()), new FileEditorInput(file));
			}
		}
	}

	protected void doSaveAs(URI uri, IEditorInput editorInput) {
		((Resource) editingDomain.getResourceSet().getResources().get(0))
				.setURI(uri);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		IProgressMonitor progressMonitor = getActionBars()
				.getStatusLineManager() != null ? getActionBars()
				.getStatusLineManager().getProgressMonitor()
				: new NullProgressMonitor();
		doSave(progressMonitor);
	}

	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return true;
	}

	/** the delegating PaletteViewPage */
	private PaletteViewerPage paletteViewerPage = null;

	/**
	 * Returns the <code>DelegatingPaletteViewPage</code> for this editor.
	 * 
	 * @return - the <code>DelegatingPaletteViewPage</code>
	 */
	protected PaletteViewerPage getPaletteViewerPage() {
		if (null == paletteViewerPage) {
			if (pageflowPage != null) {
				EditDomain domain = pageflowPage.getEditDomain();
				PaletteViewerProvider pvProvider = new PaletteViewerProvider(
						domain) {
					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.gef.ui.palette.PaletteViewerProvider#configurePaletteViewer(org.eclipse.gef.ui.palette.PaletteViewer)
					 */
					protected void configurePaletteViewer(PaletteViewer viewer) {
						super.configurePaletteViewer(viewer);

						// enable the palette as source for drag operations
						viewer
								.addDragSourceListener(new TemplateTransferDragSourceListener(
										viewer));
					}
				};
				paletteViewerPage = new PaletteViewerPage(pvProvider);
			}
		}
		return paletteViewerPage;
	}

	/** the undoable <code>IPropertySheetPage</code> */
	private TabbedPropertySheetPage tabbedPropertySheetPage = null;

	/**
	 * Returns the <code>TabbedPropertySheetPage</code> for this editor.
	 * 
	 * @return - the <code>TabbedPropertySheetPage</code>
	 */
	protected IPropertySheetPage getPropertySheetPage() {
		if (null == tabbedPropertySheetPage) {
			tabbedPropertySheetPage = new TabbedPropertySheetPage(this);
		}

		return tabbedPropertySheetPage;

	}

	/** the delegating ZoomManager */
	private DelegatingZoomManager delegatingZoomManager = null;

	/**
	 * check whether the input is related with IFile.
	 * 
	 * @param input
	 * @return
	 */
	private boolean isValidInput(IEditorInput input) {
		if (input != null) {
			IFile file = (IFile) input.getAdapter(IResource.class);
			if (file != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the <code>DelegatingZoomManager</code> for this editor.
	 * 
	 * @return - the <code>DelegatingZoomManager</code>
	 */
	protected DelegatingZoomManager getDelegatingZoomManager() {
		if (!isValidInput(getEditorInput())) {
			return null;
		}
		if (null == delegatingZoomManager) {
			delegatingZoomManager = new DelegatingZoomManager();
			delegatingZoomManager
					.setCurrentZoomManager((ZoomManager) pageflowPage
							.getAdapter(ZoomManager.class));
		}
		return delegatingZoomManager;
	}

	/** the delegating CommandStack */
	private DelegatingCommandStack delegatingCommandStack = null;

	/**
	 * Returns the <code>CommandStack</code> for this editor.
	 * 
	 * @return - the <code>CommandStack</code>
	 */
	public DelegatingCommandStack getDelegatingCommandStack() {
		if (null == delegatingCommandStack) {
			delegatingCommandStack = new DelegatingCommandStack();
		}
		return delegatingCommandStack;
	}

	/*
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		if (adapter == IEditingDomainProvider.class) {
			return new IEditingDomainProvider() {
				public EditingDomain getEditingDomain() {
					return editingDomain;
				}
			};
		}
		if (adapter == EditingDomain.class) {
			return editingDomain;
		}
		if (adapter == AdapterFactory.class) {
			return adapterFactory;
		}
		if (adapter == IEditorPart.class) {
			return getActiveEditor();
		}

		if (adapter == CommandStack.class) {
			return getDelegatingCommandStack();
		}
		if (adapter == ZoomManager.class) {
			return getDelegatingZoomManager();
		}
		if (adapter == PalettePage.class) {
			return getPaletteViewerPage();
		}
		if (adapter == ActionRegistry.class) {
			return getActionRegistry();
		}
		if (adapter == IGotoMarker.class) {
			return getGotoMarker();
		}
		// if (adapter == StructuredTextEditor.class) {
		// return sourcePage;
		// }

		if (adapter == IContentOutlinePage.class) {
			return getOutlinePage();
		}

		if (adapter == IPropertySheetPage.class) {
			return getPropertySheetPage();
		}

		if (adapter == IProject.class) {
			return getProject();
		}

		if (adapter == CTabFolder.class) {
			return getContainer();
		}
		return super.getAdapter(adapter);
	}

	private EMFCommandStackGEFAdapter sourceCommandStack;

	//
	// /**
	// * get or create the source page's GEF command stack based on its EMF
	// * command stack.
	// *
	// * @return
	// */
	private CommandStack getSourcePageCommandStack() {
		if (sourceCommandStack == null) {
			sourceCommandStack = new EMFCommandStackGEFAdapter(
					(BasicCommandStack) sourcePage.getModel().getUndoManager()
							.getCommandStack());
		}
		return sourceCommandStack;
	}

	/** the list of action ids that are to CommandStack actions */
	private List stackActionIDs = new ArrayList();

	/**
	 * Adds an <code>CommandStack</code> action to this editor.
	 * <p>
	 * <code>CommandStack</code> actions are actions that depend and work on
	 * the <code>CommandStack</code>.
	 * 
	 * @param action -
	 *            the <code>CommandStack</code> action
	 */
	protected void addStackAction(StackAction action) {
		getActionRegistry().registerAction(action);
		stackActionIDs.add(action.getId());
	}

	/** the list of action ids that are editor actions */
	private List editorActionIDs = new ArrayList();

	/**
	 * Adds an editor action to this editor.
	 * <p>
	 * Editor actions are actions that depend and work on the editor.
	 * 
	 * @param action -
	 *            the editor action
	 */
	protected void addEditorAction(EditorPartAction action) {
		getActionRegistry().registerAction(action);
		editorActionIDs.add(action.getId());
	}

	/**
	 * Creates different kinds of actions and registers them to the
	 * ActionRegistry.
	 */
	protected void createActions() {
		// register undo/redo action
		addStackAction(new UndoAction(this));
		addStackAction(new RedoAction(this));

		// register save action
		addEditorAction(new SaveAction(this));
	}

	/**
	 * Updates the specified actions.
	 * 
	 * @param actionIds -
	 *            the list of ids of actions to update
	 */
	private void updateActions(List actionIds) {
		for (Iterator ids = actionIds.iterator(); ids.hasNext();) {
			IAction action = getActionRegistry().getAction(ids.next());
			if (null != action && action instanceof UpdateAction) {
				((UpdateAction) action).update();
			}
		}
	}

	/**
	 * Indicates that the current page has changed.
	 * <p>
	 * We update the DelegatingCommandStack, OutlineViewer and other things
	 * here. //
	 */
	protected void currentPageChanged() {
		IEditorPart activeEditor = getActiveEditor();
		if (activeEditor == null) {
			return;
		}

		// update command stack
		CommandStack cmdStack = null;

		// if (activeEditor == this.overviewPage) {
		// overviewPage.refreshAll();
		// // }
		// if (activeEditor == pageflowPage && _prevPart == sourcePage) {
		// if (getFacesConfigAdapter().isFacesConfigDirty()) {
		// // if the faces-config file is updated in the source page,
		// // the pageflow editor and databinding page should be updated!
		// updatePageflowPage(true, true);
		// }
		// } else if (activeEditor == sourcePage) {
		// if (getFacesConfigAdapter().isPageflowDirty()) {
		// // if the pageflow is modified in the pageflow page,
		// // the source editor should be updated.
		// updateSourcePageFromPageflow();
		// }
		// }

		if (activeEditor == pageflowPage) {
			cmdStack = (CommandStack) activeEditor
					.getAdapter(CommandStack.class);
		} else if (activeEditor == sourcePage)// other page will delegate the
		// GEF command stack to source
		// page's.
		{
			cmdStack = this.getSourcePageCommandStack();
		}

		// Add command stacks
		getMultiPageCommandStackListener().addCommandStack(cmdStack,
				activeEditor);
		getDelegatingCommandStack().setCurrentCommandStack(cmdStack);

		// enable or disable the actions
		updateActions(stackActionIDs);

		// update zoom actions
		ZoomManager zoomManager = null;
		zoomManager = (ZoomManager) activeEditor.getAdapter(ZoomManager.class);

		if (zoomManager != null) {
			getDelegatingZoomManager().setCurrentZoomManager(zoomManager);
		}

		IEditorActionBarContributor contributor = getEditorSite()
				.getActionBarContributor();
		if (contributor != null
				&& contributor instanceof FacesConfigEditorActionBarContributor) {
			((FacesConfigEditorActionBarContributor) contributor)
					.setActivePage(activeEditor);
		}
		// _prevPart = activeEditor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see MultiPageEditorPart#pageChange(int)
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);

		// refresh content depending on current page
		currentPageChanged();
	}

	/**
	 * get the goto marker based on active editor
	 * 
	 * @return
	 */
	private IGotoMarker getGotoMarker() {
		IGotoMarker gotoMarker = null;
		setActivePage(sourcePageId);

		IEditorPart activeEditor = getActiveEditor();

		if (activeEditor != null) {
			if (activeEditor instanceof IGotoMarker) {
				gotoMarker = (IGotoMarker) activeEditor;
			} else {
				gotoMarker = (IGotoMarker) activeEditor
						.getAdapter(IGotoMarker.class);
			}
		}
		return gotoMarker;
	}

	/**
	 * This accesses a cached version of the property sheet. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	// public IPropertySheetPage getPropertySheetPage() {
	// if (propertySheetPage == null) {
	// propertySheetPage = new ExtendedPropertySheetPage(editingDomain) {
	// public void setSelectionToViewer(List selection) {
	// // FacesConfigEditor.this.setSelectionToViewer(selection);
	// // FacesConfigEditor.this.setFocus();
	// }
	//
	// public void setActionBars(IActionBars actionBars) {
	// super.setActionBars(actionBars);
	// getActionBarContributor().shareGlobalActions(this,
	// actionBars);
	// }
	// };
	// propertySheetPage
	// .setPropertySourceProvider(new AdapterFactoryContentProvider(
	// adapterFactory));
	// }
	//
	// return propertySheetPage;
	// }
	public void dispose() {
		FC2PFTransformer.getInstance().dispose();
//		Iterator contents = getFacesConfig().getNavigationRule().iterator();
//		while (contents.hasNext()) {
//			EObject next = ((EObject) contents.next());
//			TreeIterator children = next.eAllContents();
//			while (children.hasNext()) {
//				FC2PFTransformer.getInstance().unAdapt(
//						(EObject) children.next());
//			}
//			FC2PFTransformer.getInstance().unAdapt((EObject) next);
//		}
		// FC2PFTransformer.getInstance().unAdapt(getFacesConfig());

		ResourcesPlugin.getWorkspace().removeResourceChangeListener(
				resourceChangeListener);

		// getSite().getPage().removePartListener(partListener);

		adapterFactory.dispose();

		if (getActionBarContributor().getActiveEditor() == this) {
			getActionBarContributor().setActiveEditor(null);
		}

		if (propertySheetPage != null) {
			propertySheetPage.dispose();
		}

		if (this.outlinePage != null) {
			outlinePage.dispose();
		}

		if (facesConfigAtrifactEdit != null)
			facesConfigAtrifactEdit.dispose();
		super.dispose();
	}

	private IProject currentProject;

	/**
	 * get the project of the faces config file that the editor is working on.
	 * 
	 * @return
	 */
	public IProject getProject() {
		if (currentProject == null) {
			if (facesConfigAtrifactEdit != null) {
				IFile file = facesConfigAtrifactEdit.getFile();
				if (file != null)
					currentProject = file.getProject();
			}
		}
		return currentProject;
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * Returns the <code>IContentOutlinePage</code> for this editor.
	 * 
	 * @return - the <code>IContentOutlinePage</code>
	 */
	protected IContentOutlinePage getOutlinePage() {
		if (null == outlinePage) {
			outlinePage = new MultiPageEditorOutlinePage();
		}
		return outlinePage;
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);

	}

	public ISelection getSelection() {
		return editorSelection;
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		editorSelection = selection;
		for (Iterator listeners = selectionChangedListeners.iterator(); listeners
				.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listeners
					.next();
			listener
					.selectionChanged(new SelectionChangedEvent(this, selection));
		}
	}

	public void menuAboutToShow(IMenuManager manager) {
		((IMenuListener) getEditorSite().getActionBarContributor())
				.menuAboutToShow(manager);
	}

	public Viewer getViewer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void gotoMarker(IMarker marker) {
		// TODO Auto-generated method stub
		// setActivePage(sourcePageId);
		// IDE.gotoMarker(fTextEditor, marker);
	}

	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite()
				.getActionBarContributor();
	}

	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}

	/**
	 * Handles activation of the editor or it's associated views.
	 * 
	 * @generated
	 */
	protected void handleActivate() {
		// Recompute the read only state.
		//
		if (editingDomain.getResourceToReadOnlyMap() != null) {
			editingDomain.getResourceToReadOnlyMap().clear();

			// Refresh any actions that may become enabled or disabled.
			//
			setSelection(getSelection());
		}

		if (!removedResources.isEmpty()) {
			if (handleDirtyConflict()) {
				getSite().getPage().closeEditor(FacesConfigEditor.this, false);
				FacesConfigEditor.this.dispose();
			} else {
				removedResources.clear();
				changedResources.clear();
				savedResources.clear();
			}
		} else if (!changedResources.isEmpty()) {
			changedResources.removeAll(savedResources);
			handleChangedResources();
			changedResources.clear();
			savedResources.clear();
		}
	}

	/**
	 * Shows a dialog that asks if conflicting changes should be discarded.
	 * 
	 * @generated
	 */
	protected boolean handleDirtyConflict() {
		return MessageDialog
				.openQuestion(
						getSite().getShell(),
						"File Conflict",
						" There are unsaved changes that conflict with changes made outside the editor.  Do you wish to discard this editor's changes?");
	}

	/**
	 * Handles what to do with changed resources on activation.
	 * 
	 * @generated
	 */
	protected void handleChangedResources() {
		if (!changedResources.isEmpty()
				&& (!isDirty() || handleDirtyConflict())) {
			editingDomain.getCommandStack().flush();

			for (Iterator i = changedResources.iterator(); i.hasNext();) {
				Resource resource = (Resource) i.next();
				if (resource.isLoaded()) {
					resource.unload();
					try {
						resource.load(Collections.EMPTY_MAP);
					} catch (IOException exception) {
						// TODO log it.
						// FacesConfigEditorPlugin.INSTANCE.log(exception);
					}
				}
			}
		}
	}

	public void setActiveEditorPage(String pageID) {
		if (pageID.equals(PageflowEditor.PAGE_ID)) {
			setActivePage(pageflowPageID);
		} else if (pageID.equals(ManagedBeanPage.PAGE_ID)) {
			setActivePage(managedBeanPageID);
		} else if (pageID.equals(ComponentsPage.PAGE_ID)) {
			setActivePage(componentsPageID);
		} else if (pageID.equals(OthersPage.PAGE_ID)) {
			setActivePage(othersPageID);
		}
	}
}
