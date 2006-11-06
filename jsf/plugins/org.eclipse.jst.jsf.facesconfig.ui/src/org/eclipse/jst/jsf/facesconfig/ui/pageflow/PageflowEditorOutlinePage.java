/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowTreePartFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * This is an implementation of an outline page showing an overview figure and
 * an tree outline of the main graphical viewer provided by the current active
 * page of an multi page editor.
 * 
 */
public class PageflowEditorOutlinePage extends Page implements
		IContentOutlinePage {
	/** the pageflow editor */
	private final PageflowEditor editor;

	/** the outlineContent for the tree viewer */
	private PageflowElement outlineContent = null;

	/** the control of the overview */
	private Canvas overview = null;

	/** the root edit part (outlineContent for the thumbnail) */
	private RootEditPart overviewContent = null;

	/** the thumbnail */
	private ScrollableThumbnail thumbnail = null;

	/** the tree viewer */
	private TreeViewer treeViewer = null;

	/** the control of the tree view */
	private Control outline = null;

	/** the pagebook */
	private PageBook pageBook = null;

	/** action for showing the tree page */
	private IAction showOutlineAction = null;

	/** action for showing the overview page */
	private IAction showOverviewAction = null;

	/** the LightweightSystem */
	private LightweightSystem lws = null;

	/** the edit domain */
	private EditDomain editDomain = null;

	/**
	 * Creates a new PageflowEditorOutlinePage instance.
	 * 
	 * @param editor -
	 *            Pageflow Editor
	 */
	public PageflowEditorOutlinePage(EditorPart editor) {
		super();
		this.editor = (PageflowEditor) editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISelectionProvider#addSelectionChangedListener(ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		getTreeViewer().addSelectionChangedListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		// pagebook
		pageBook = new PageBook(parent, SWT.NONE);

		// tree viewer control
		outline = getTreeViewer().createControl(pageBook);
		configureEditPartViewer(getTreeViewer());

		// overview canvas
		overview = new Canvas(pageBook, SWT.NONE);
		lws = new LightweightSystem(overview);

		// create actions
		IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
		showOutlineAction = new Action() {
			public void run() {
				showPage(outline);
			}
		};
		showOutlineAction.setImageDescriptor(EditorPlugin.getDefault()
				.getImageDescriptor("facesconfig/Pageflow_Outline.gif")); //$NON-NLS-1$
		tbm.add(showOutlineAction);
		showOverviewAction = new Action() {
			public void run() {
				showPage(overview);
			}
		};
		showOverviewAction.setImageDescriptor(EditorPlugin.getDefault()
				.getImageDescriptor("facesconfig/Pageflow_Outline_Overview.gif")); //$NON-NLS-1$
		tbm.add(showOverviewAction);

		// initialize outline page
		initializeOutlineViewer();

		// initialize overview
		initializeOverview();

		// initialize pagebook
		showPage(outline);
	}

	/**
	 * Configures the outline viewer
	 */
	private void initializeOutlineViewer() {
		if (null != getEditDomain()) {
			getEditDomain().addViewer(getTreeViewer());
		}
		// getTreeViewer().setEditPartFactory(new PageflowTreePartFactory());

		// synchronize selections
		editor.getSelectionSynchronizer().addViewer(getTreeViewer());

		// add content
		getTreeViewer().setContents(getOutlineContent());
	}

	/**
	 * Shows the spcified page.
	 * 
	 * @param id -
	 *            control id of pagebook in outline page. it is can be _outline
	 *            or _overview
	 */
	protected void showPage(Control id) {
		if (id == outline) {
			showOutlineAction.setChecked(true);
			showOverviewAction.setChecked(false);
			pageBook.showPage(outline);
			if (thumbnail != null) {
				thumbnail.setVisible(false);
			}
		} else if (id == overview) {
			showOutlineAction.setChecked(false);
			showOverviewAction.setChecked(true);
			pageBook.showPage(overview);
			if (thumbnail != null) {
				thumbnail.setVisible(true);
			}
		}
	}

	/**
	 * Returns the current edit domain.
	 * 
	 * @return - the edit domain
	 */
	public EditDomain getEditDomain() {
		return editDomain;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPage#dispose()
	 */
	public void dispose() {
		editor.getSelectionSynchronizer().removeViewer(getTreeViewer());

		if (null != thumbnail) {
			thumbnail.deactivate();
		}

		super.dispose();
	}

	/**
	 * initialize Overview.
	 * 
	 * @return - the edit domain
	 */
	private void initializeOverview() {
		// check if control was created
		if (null == lws) {
			return;
		}

		// deactivate old thumbnail
		if (null != thumbnail) {
			thumbnail.deactivate();
		}

		// create empty thumbnail
		thumbnail = new ScrollableThumbnail();
		thumbnail.setBorder(new MarginBorder(3));
		lws.setContents(thumbnail);

		// initialize thumbnail
		if (null != getOverviewContent()) {
			Viewport viewport = null;
			IFigure source = null;
			if (getOverviewContent() instanceof ScalableFreeformRootEditPart) {
				viewport = (Viewport) ((ScalableFreeformRootEditPart) getOverviewContent())
						.getFigure();
				source = ((ScalableFreeformRootEditPart) getOverviewContent())
						.getLayer(LayerConstants.PRINTABLE_LAYERS);
			}
			if (getOverviewContent() instanceof ScalableRootEditPart) {
				viewport = (Viewport) ((ScalableRootEditPart) getOverviewContent())
						.getFigure();
				source = ((ScalableRootEditPart) getOverviewContent())
						.getLayer(LayerConstants.PRINTABLE_LAYERS);
			}

			if (null != viewport && null != source) {
				thumbnail.setViewport(viewport);
				thumbnail.setSource(source);
			}
		}
	}

	/**
	 * Returns the outlineContent.
	 * 
	 * @return - the outlineContent
	 */
	public PageflowElement getOutlineContent() {
		return outlineContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPage#getControl()
	 */
	public Control getControl() {
		return pageBook;
	}

	/**
	 * Returns the root edit part
	 * 
	 * @return - the root edit part
	 */
	public RootEditPart getOverviewContent() {
		return overviewContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return getTreeViewer().getSelection();
	}

	/**
	 * Returns the tree viewer.
	 * 
	 * @return - the tree viewer
	 */
	public TreeViewer getTreeViewer() {
		if (null == treeViewer) {
			treeViewer = new TreeViewer();
			treeViewer.setEditPartFactory(new PageflowTreePartFactory());
		}

		return treeViewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISelectionProvider#removeSelectionChangedListener(ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		getTreeViewer().removeSelectionChangedListener(listener);
	}

	/**
	 * Sets the outlineContent.
	 * 
	 * @param element -
	 *            Pageflow Element
	 */
	public void setOutlineContent(PageflowElement element) {
		if (outlineContent != element) {
			outlineContent = element;

			// refresh viewer
			if (null != getTreeViewer().getControl()) {
				getTreeViewer().setContents(element);

				// bugfix: something is overwriting visibility
				if (null != pageBook) {
					if (showOverviewAction.isChecked()) {
						showPage(outline);
						showPage(overview);
					} else {
						showPage(outline);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPage#setFocus()
	 */
	public void setFocus() {
		if (getControl() != null) {
			getControl().setFocus();
		}
	}

	/**
	 * Sets the root edit part.
	 * 
	 * @param part -
	 *            root edit part for pageflow model
	 */
	public void setOverviewContent(RootEditPart part) {
		if (overviewContent != part) {
			overviewContent = part;
			// reinitialize thumbnail
			// if (null != thumbnail && thumbnail.isVisible())
			initializeOverview();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISelectionProvider#setSelection(ISelection)
	 */
	public void setSelection(ISelection selection) {
		getTreeViewer().setSelection(selection);
	}

	/**
	 * Initializes this outline page from a given editor page.
	 * 
	 * @param newPage -
	 *            Pageflow EditorPage
	 */
	public void initialize(PageflowEditor newPage) {
		if (null != newPage.getGraphicalViewer()) {
			setOverviewContent(newPage.getGraphicalViewer().getRootEditPart());
			EditPart currentContent = newPage.getGraphicalViewer()
					.getContents();
			setOutlineContent(null != currentContent ? (PageflowElement) currentContent
					.getModel()
					: null);
			setEditDomain(newPage.getEditDomain());
			getViewerContentListener().setViewer(newPage.getGraphicalViewer());
		} else {
			setOverviewContent(null);
			setOutlineContent(null);
			setEditDomain(null);
		}
	}

	/**
	 * Sets the edit domain.
	 * 
	 * @param domain -
	 *            editor's edit domain
	 */
	public void setEditDomain(EditDomain domain) {
		if (editDomain != domain) {
			// refresh viewer
			if (null != getTreeViewer()) {
				if (null != editDomain) {
					editDomain.removeViewer(getTreeViewer());
				}
				if (null != domain) {
					domain.addViewer(getTreeViewer());
				}
			}
			editDomain = domain;
		}
	}

	/**
	 * the current viewer content listener
	 */
	private final ViewerContentListener viewerContentListener = new ViewerContentListener();

	/**
	 * This class listens for changes of the content of an EditPartViewer. If
	 * the content changed, it will update the outline viewer.
	 * <p>
	 * Important: this class has to be disposed with
	 * <code>setViewer(null)</code>.
	 * 
	 * @author Gunnar Wagenknecht
	 */
	private class ViewerContentListener implements EditPartListener {
		/* the viewer */
		private EditPartViewer _viewer;

		/**
		 * Sets a new viewer. <code>null</code> is allowed to dispose.
		 * 
		 * @param viewer
		 */
		public void setViewer(EditPartViewer viewer) {
			if (null != this._viewer)
				this._viewer.getRootEditPart().removeEditPartListener(this);

			this._viewer = viewer;

			if (null != this._viewer)
				this._viewer.getRootEditPart().addEditPartListener(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.gef.EditPartListener#childAdded(org.eclipse.gef.EditPart,
		 *      int)
		 */
		public void childAdded(EditPart child, int index) {
			// this is out event, update the viewers
			if (child.getModel() instanceof PageflowElement)
				setOutlineContent((PageflowElement) child.getModel());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.gef.EditPartListener#partActivated(org.eclipse.gef.EditPart)
		 */
		public void partActivated(EditPart editpart) {
            // do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.gef.EditPartListener#partDeactivated(org.eclipse.gef.EditPart)
		 */
		public void partDeactivated(EditPart editpart) {
            // do nothing
        }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.gef.EditPartListener#removingChild(org.eclipse.gef.EditPart,
		 *      int)
		 */
		public void removingChild(EditPart child, int index) {
            // do nothing
        }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.gef.EditPartListener#selectedStateChanged(org.eclipse.gef.EditPart)
		 */
		public void selectedStateChanged(EditPart editpart) {
            // do nothing
		}
	}

	/**
	 * Returns the viewer content listener.
	 * 
	 * @return the viewer content listener
	 */
	private ViewerContentListener getViewerContentListener() {
		return viewerContentListener;
	}

	/**
	 * Configures the specified <code>EditPartViewer</code>.
	 * 
	 * @param viewer
	 */
	protected void configureEditPartViewer(EditPartViewer viewer) {
		// configure the shared key handler
		if (viewer.getKeyHandler() != null) {
			viewer.getKeyHandler().setParent(editor.getSharedKeyHandler());
		}

		// configure the context menu
		ContextMenuProvider provider = new PageflowEditorContextMenuProvider(
				viewer, editor.getActionRegistry());
		viewer.setContextMenu(provider);
		getSite().registerContextMenu(
				EditorPlugin.getPluginId()
						+ ".pageflow.editor.outlineview.contextmenu", provider,
				getSite().getSelectionProvider()); //$NON-NLS-1$
	}

}
