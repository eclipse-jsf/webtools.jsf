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
package org.eclipse.jst.pagedesigner.editors;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.actions.container.ContainerActionGroup;
import org.eclipse.jst.pagedesigner.actions.menuextension.CustomedContextMenuActionGroup;
import org.eclipse.jst.pagedesigner.actions.menuextension.RunAction;
import org.eclipse.jst.pagedesigner.actions.range.RangeActionGroup;
import org.eclipse.jst.pagedesigner.actions.single.SingleElementActionGroup;
import org.eclipse.jst.pagedesigner.commands.CopyAction;
import org.eclipse.jst.pagedesigner.commands.CutAction;
import org.eclipse.jst.pagedesigner.commands.DeleteAction;
import org.eclipse.jst.pagedesigner.commands.PasteAction;
import org.eclipse.jst.pagedesigner.dnd.internal.DesignerTemplateTransferDragSourceListener;
import org.eclipse.jst.pagedesigner.dnd.internal.LocalSelectionDropTargetListener;
import org.eclipse.jst.pagedesigner.dnd.internal.PDTemplateTransferDropTargetListener;
import org.eclipse.jst.pagedesigner.dnd.internal.ResouceDropTargetListener;
import org.eclipse.jst.pagedesigner.editors.actions.DesignerUndoRedoAction;
import org.eclipse.jst.pagedesigner.editors.actions.RelatedViewActionGroup;
import org.eclipse.jst.pagedesigner.editors.palette.DesignerPaletteCustomizer;
import org.eclipse.jst.pagedesigner.editors.palette.DesignerPaletteViewerProvider;
import org.eclipse.jst.pagedesigner.editors.palette.HTMLEditorPaletteFactory;
import org.eclipse.jst.pagedesigner.jsp.core.internal.pagevar.DocumentPageVariableAdapter;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.PageVariableAdapterFactory;
import org.eclipse.jst.pagedesigner.parts.CSSStyleAdapterFactory;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.parts.HTMLEditPartsFactory;
import org.eclipse.jst.pagedesigner.parts.RefresherFactory;
import org.eclipse.jst.pagedesigner.utils.SelectionHelper;
import org.eclipse.jst.pagedesigner.viewer.HTMLGraphicalViewer;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;
import org.eclipse.wst.sse.core.internal.PropagatingAdapter;
import org.eclipse.wst.sse.core.internal.provisional.IModelStateListener;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapterFactory;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.undo.IDocumentSelectionMediator;
import org.eclipse.wst.sse.core.internal.undo.UndoDocumentEvent;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * @author mengbo
 */
public class SimpleGraphicalEditor extends GraphicalEditor implements
		IDesignViewer, IDocumentSelectionMediator {
	private HTMLEditor _delegate;

	private HTMLGraphicalViewer _viewer;

	private IStructuredModel _model;

	/** Palette component, holding the tools and shapes. */
	private PaletteRoot _palette;

	private SelectionSynchronizer _synchronizer = new SelectionSynchronizer(
			this);

	private IModelStateListener _internalModelListener = new IModelStateListener() {
		public void modelAboutToBeChanged(IStructuredModel model) {
		}

		public void modelChanged(IStructuredModel model) {
			updateActionsWhenModelChange();
		}

		public void modelDirtyStateChanged(IStructuredModel model,
				boolean isDirty) {
		}

		public void modelResourceDeleted(IStructuredModel model) {
		}

		public void modelResourceMoved(IStructuredModel oldModel,
				IStructuredModel newModel) {
		}

		public void modelAboutToBeReinitialized(IStructuredModel structuredModel) {
		}

		public void modelReinitialized(IStructuredModel structuredModel) {
		}
	};

	public SimpleGraphicalEditor(HTMLEditor delegate,
			DefaultEditDomain editdomain) {
		_delegate = delegate;
		this.setEditDomain(editdomain);
	}

	protected void createGraphicalViewer(Composite parent) {
		_viewer = new HTMLGraphicalViewer(this);
		Control control = _viewer.createControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(control,
				PDPlugin.getResourceString("SimpleGraphicalEditor.help.id"));
		setGraphicalViewer(_viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
		initializeContextMenu();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#dispose()
	 */
	public void dispose() {
		if (_model != null) {
			_model.getUndoManager().disconnect(this);
		}

		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#initializeGraphicalViewer()
	 */
	protected void initializeGraphicalViewer() {
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		_viewer.setRootEditPart(rootEditPart);

		_viewer.getViewport().setContentsTracksWidth(true);

		_viewer.setKeyHandler(new GraphicalViewerKeyHandler(_viewer));

		// initialize the viewer with input
		// IStructuredModel sModel =
		// StructuredModelManager.getModelManager().createUnManagedStructuredModelFor(ContentTypeIdForHTML.ContentTypeID_HTML);
		// IDOMDocument designDoc = ((IDOMModel)sModel).getDocument();
		// HTMLEditPartsFactory factory = new HTMLEditPartsFactory(designDoc);
		HTMLEditPartsFactory factory = new HTMLEditPartsFactory(null);

		_viewer.setEditPartFactory(factory);

		// for sync with source view.

		_viewer.addDropTargetListener(new LocalSelectionDropTargetListener(
				_viewer));
		_viewer.addDropTargetListener(new PDTemplateTransferDropTargetListener(
				_viewer));
		_viewer.addDropTargetListener(new ResouceDropTargetListener(_viewer));

		// add double click support.
		_viewer.getControl().addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				try {
					getSite().getPage().showView(IPageLayout.ID_PROP_SHEET);
				} catch (PartInitException e1) {
					// ignore
				}
			}
		});
	}

	protected void initializeContextMenu() {
		Control gviewer = _viewer.getControl();
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		Menu menu = menuMgr.createContextMenu(gviewer);
		gviewer.setMenu(menu);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager menuMgr1) {
				PageDesignerActionConstants.addStandardActionGroups(menuMgr1);

				menuMgr1.add(new RunAction(SimpleGraphicalEditor.this._delegate,
						RunAction.LAUNCH_MODE_RUN));
				menuMgr1.add(new RunAction(SimpleGraphicalEditor.this._delegate,
						RunAction.LAUNCH_MODE_DEBUG));
				// FIXME: for UNDO/REDO, maybe need also wrap them in
				// DesignerCommand.
				// otherwise don't have validate() called after the source
				// change.
				menuMgr1.appendToGroup(PageDesignerActionConstants.GROUP_UNDO,
						getAction(IWorkbenchActionDefinitionIds.UNDO));
				menuMgr1.appendToGroup(PageDesignerActionConstants.GROUP_UNDO,
						getAction(IWorkbenchActionDefinitionIds.REDO));

				menuMgr1.appendToGroup(PageDesignerActionConstants.GROUP_EDIT,
						getAction(IWorkbenchActionDefinitionIds.CUT));
				menuMgr1.appendToGroup(PageDesignerActionConstants.GROUP_EDIT,
						getAction(IWorkbenchActionDefinitionIds.COPY));
				menuMgr1.appendToGroup(PageDesignerActionConstants.GROUP_EDIT,
						getAction(IWorkbenchActionDefinitionIds.PASTE));
				menuMgr1.appendToGroup(PageDesignerActionConstants.GROUP_EDIT,
						getAction(IWorkbenchActionDefinitionIds.DELETE));

				ContainerActionGroup containerActionGroup = new ContainerActionGroup();
				ActionContext context = new ActionContext(_viewer
						.getSelection());
				context.setInput(_viewer);
				containerActionGroup.setContext(context);
				containerActionGroup.fillContextMenu(menuMgr1);
				containerActionGroup.setContext(null);

				// TableActionGroup tableActionGroup = new TableActionGroup();
				// tableActionGroup.setContext(new
				// ActionContext(_viewer.getSelection()));
				// tableActionGroup.fillContextMenu(menuMgr);
				// tableActionGroup.setContext(null);

				RangeActionGroup rangeActionGroup = new RangeActionGroup();
				context = new ActionContext(_viewer.getSelection());
				context.setInput(_viewer);
				rangeActionGroup.setContext(context);
				rangeActionGroup.fillContextMenu(menuMgr1);
				rangeActionGroup.setContext(null);

				SingleElementActionGroup singleActionGroup = new SingleElementActionGroup();
				singleActionGroup.setContext(new ActionContext(_viewer
						.getSelection()));
				singleActionGroup.fillContextMenu(menuMgr1);
				singleActionGroup.setContext(null);

				// IAction customize =
				// graphicalActionRegistry.getAction(CustomizeJavaBeanAction.ACTION_ID);
				// if (customize.isEnabled())
				// menuMgr.appendToGroup(GEFActionConstants.GROUP_EDIT,
				// customize);

				RelatedViewActionGroup viewMenu = new RelatedViewActionGroup();
				viewMenu.fillContextMenu(menuMgr1);

				CustomedContextMenuActionGroup customedMenu = new CustomedContextMenuActionGroup();
				customedMenu.setContext(new ActionContext(_viewer
						.getSelection()));
				customedMenu.setModel(_model);
				customedMenu.setParentControl(_viewer.getControl());
				customedMenu.fillContextMenu(menuMgr1);
				customedMenu.setContext(null);
				customedMenu.setParentControl(null);
				customedMenu.setModel(null);
			}
		});
		getSite().registerContextMenu(
				"HTMLVisualEditor.contextMenu", menuMgr, _viewer); //$NON-NLS-1$
	}

	private void updateActionsWhenModelChange() {
		// update undo/redo action
		IAction action = this.getAction(IWorkbenchActionDefinitionIds.UNDO);
		((UpdateAction) action).update();

		action = this.getAction(IWorkbenchActionDefinitionIds.REDO);
		((UpdateAction) action).update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
	 */
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();

		IAction action;

		action = new DesignerUndoRedoAction(true, this);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO_DISABLED));
		action.setActionDefinitionId(IWorkbenchActionDefinitionIds.UNDO);
		action.setId(IWorkbenchActionDefinitionIds.UNDO);
		getSite().getKeyBindingService().registerAction(action);
		registry.registerAction(action);

		action = new DesignerUndoRedoAction(false, this);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_REDO_DISABLED));
		action.setActionDefinitionId(IWorkbenchActionDefinitionIds.REDO);
		action.setId(IWorkbenchActionDefinitionIds.REDO);
		getSite().getKeyBindingService().registerAction(action);
		registry.registerAction(action);

		action = new DeleteAction(this);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		action.setActionDefinitionId(IWorkbenchActionDefinitionIds.DELETE);
		action.setId(IWorkbenchActionDefinitionIds.DELETE);
		getSite().getKeyBindingService().registerAction(action);
		this.getSelectionActions().add(action.getId());
		registry.registerAction(action);

		action = new CopyAction(this);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		action.setActionDefinitionId(IWorkbenchActionDefinitionIds.COPY);
		action.setId(IWorkbenchActionDefinitionIds.COPY);
		getSite().getKeyBindingService().registerAction(action);
		this.getSelectionActions().add(action.getId());
		registry.registerAction(action);

		action = new CutAction(this);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		action.setActionDefinitionId(IWorkbenchActionDefinitionIds.CUT);
		action.setId(IWorkbenchActionDefinitionIds.CUT);
		getSite().getKeyBindingService().registerAction(action);
		this.getSelectionActions().add(action.getId());
		registry.registerAction(action);

		action = new PasteAction(this);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		action.setActionDefinitionId(IWorkbenchActionDefinitionIds.PASTE);
		action.setId(IWorkbenchActionDefinitionIds.PASTE);
		getSite().getKeyBindingService().registerAction(action);
		this.getSelectionActions().add(action.getId());
		registry.registerAction(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		if (_delegate != null) {
			_delegate.doSave(monitor);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
		if (_delegate != null) {
			_delegate.doSaveAs();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	public boolean isDirty() {
		if (_delegate != null) {
			return _delegate.isDirty();
		}
        return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		if (_delegate != null) {
			return _delegate.isSaveAsAllowed();
		}
        return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.html.editor.IDesignViewer#setModel(com.ibm.sse.model.IStructuredModel)
	 */
	public void setModel(IStructuredModel model) {
		if (_model != null) {
			if (_model.getUndoManager() != null)
				_model.getUndoManager().disconnect(this);
			_model.removeModelStateListener(_internalModelListener);
		}

		this._model = model;

		if (_model != null) {
			_model.addModelStateListener(_internalModelListener);
			if (_model.getUndoManager() != null) {
				_model.getUndoManager().connect(this);
				updateActionsWhenModelChange();
			}
		}

		if (model instanceof IDOMModel) {
			IDOMDocument doc = ((IDOMModel) model).getDocument();
			PropagatingAdapter adapter = (PropagatingAdapter) doc
					.getAdapterFor(PropagatingAdapter.class);
			if (adapter != null) {
				INodeAdapterFactory factory = RefresherFactory.getInstance();
				adapter.addAdaptOnCreateFactory(factory);
				adapter.initializeForFactory(factory, doc);
				// CSSStyleAdapterFactory fac2 =
				// CSSStyleAdapterFactory.getInstance();
				// adapter.addAdaptOnCreateFactory(fac2);
				// adapter.initializeForFactory(fac2, doc);
			}
			((IDOMModel) model).getFactoryRegistry().addFactory(
					CSSStyleAdapterFactory.getInstance());

			// _viewer.getDestDocumentForDesign().getModel().getFactoryRegistry().addFactory(CSSStyleAdapterFactory.getInstance());
			((IDOMModel) model).getFactoryRegistry().addFactory(
					new PageVariableAdapterFactory());
			doc.addAdapter(new DocumentPageVariableAdapter(doc));
			_viewer.setContents(((IDOMModel) model).getDocument());
		} else {
			_viewer.setContents((EditPart) null);
		}
	}

	protected SelectionSynchronizer getSynchronizer() {
		return _synchronizer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.sse.model.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#updateActions(java.util.List)
	 */
	protected void updateActions(List actionIds) {
		super.updateActions(actionIds);
	}

	public IAction getAction(Object id) {
		// lium: following lines commented out, see comments in
		// DesignerUndoRedoAction
		// if (ITextEditorActionConstants.UNDO.equals(id) ||
		// ITextEditorActionConstants.REDO.equals(id))
		// {
		// return _delegate.getTextEditor().getAction((String) id);
		// }
		return getActionRegistry().getAction(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
	 */
	protected FlyoutPreferences getPalettePreferences() {
		return HTMLEditorPaletteFactory.createPalettePreferences();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
	 */
	protected PaletteRoot getPaletteRoot() {
		if (_palette == null) {
			_palette = HTMLEditorPaletteFactory
					.createPalette(getCurrentProject(_delegate.getEditorInput()));
		}
		return _palette;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
	 */
	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new DesignerPaletteViewerProvider(getEditDomain()) {
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.setCustomizer(new DesignerPaletteCustomizer());

				// create a drag source listener for this palette viewer
				// together with an appropriate transfer drop target listener,
				// this will enable
				// model element creation by dragging a
				// CombinatedTemplateCreationEntries
				// from the palette into the editor
				// @see ShapesEditor#createTransferDropTargetListener()
				viewer
						.addDragSourceListener(new DesignerTemplateTransferDragSourceListener(
								viewer));
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.IDesignViewer#getGraphicViewer()
	 */
	public IHTMLGraphicalViewer getGraphicViewer() {
		return _viewer;
	}

	public HTMLEditor getHTMLEditor() {
		return _delegate;
	}

	private IProject getCurrentProject(IEditorInput input) {
		IProject curProject = null;
		IFile inputFile = null;
		if (input instanceof IFileEditorInput) {
			inputFile = ((IFileEditorInput) input).getFile();
			curProject = inputFile.getProject();
		}
		return curProject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (_viewer != null) {
			GraphicalViewer viewerViewer = getGraphicalViewer();
			if (viewerViewer != null && viewerViewer.getControl() != null
					&& viewerViewer.getControl().isFocusControl()) {
				updateActions(getSelectionActions());
				if (selection instanceof IStructuredSelection && //
						!(((IStructuredSelection) selection).getFirstElement() instanceof DocumentEditPart)) {
					((HTMLGraphicalViewer) viewerViewer)
							.updateRangeSelection(selection);
				}
			}
		} else {
			super.selectionChanged(part, selection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.undo.IDocumentSelectionMediator#getDocument()
	 */
	public IDocument getDocument() {
		if (_model != null) {
			return _model.getStructuredDocument();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.undo.IDocumentSelectionMediator#undoOperationSelectionChanged(org.eclipse.wst.sse.core.internal.undo.UndoDocumentEvent)
	 */
	public void undoOperationSelectionChanged(UndoDocumentEvent event) {
		IDocumentSelectionMediator requester = event.getRequester();
		if (this == requester) {
			// ok, the undo/redo operation is initialized by designer page.
			// we should set selection in designer.
			// However, when this method is called, the modelChanged event is
			// not fired yet, so the
			// editpart hasn't refreshed yet. So we register a
			// modelStateListener, and do the selection
			// in modelChangedEvent. (lium)
			final int offset = event.getOffset();
			final int length = event.getLength();

			_model.addModelStateListener(new IModelStateListener() {
				public void modelAboutToBeChanged(IStructuredModel model) {
                    // nothing to do
				}

				public void modelChanged(IStructuredModel model) {
					_model.removeModelStateListener(this);
					ISelection sel = SelectionHelper
							.convertToDesignerSelection(getGraphicViewer(),
									offset, length);
					if (sel != null) {
						getGraphicViewer().setSelection(sel);
					}
				}

				public void modelDirtyStateChanged(IStructuredModel model,
						boolean isDirty) {
                    // do  nothing
				}

				public void modelResourceDeleted(IStructuredModel model) {
                    // do nothign
				}

				public void modelResourceMoved(IStructuredModel oldModel,
						IStructuredModel newModel) {
                    // do nothing
				}

				public void modelAboutToBeReinitialized(
						IStructuredModel structuredModel) {
                    // do nothing
				}

				public void modelReinitialized(IStructuredModel structuredModel) {
                    // do nothing
				}
			});
		}
	}
}
