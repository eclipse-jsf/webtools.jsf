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
package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.editors.SimpleGraphicalEditor;
import org.eclipse.jst.pagedesigner.ui.common.sash.NestedEditorActionBarContributor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.sse.ui.internal.ExtendedEditorActionBuilder;
import org.eclipse.wst.sse.ui.internal.IExtendedContributor;
import org.eclipse.wst.sse.ui.internal.ISourceViewerActionBarContributor;

/**
 * This is the actionbar contributor for HTML Editor. As HTMLEditor is
 * multipaged, so this contributor will also handle on which page currently is
 * activated.
 * 
 * @author mengbo
 */
public class PageDesignerActionBarContributor2 extends
		NestedEditorActionBarContributor implements IExtendedContributor {

	private DesignPageActionContributor _designViewerActionBarContributor = null;

	private ISourceViewerActionBarContributor _sourceViewerActionContributor = null;

	private HTMLEditor _htmlEditor = null;

	// EditorExtension
	private static final String EDITOR_ID = IJMTConstants.EDITORID_HTML;

	private IExtendedContributor _extendedContributor;

	private DesignerStyleActionGroup _group = new DesignerStyleActionGroup();

	/**
	 * Default constructor
	 */
	public PageDesignerActionBarContributor2() {
		super();

		_sourceViewerActionContributor = new SourcePageActionContributor();
		_designViewerActionBarContributor = new DesignPageActionContributor();

		// Read action extensions.
		ExtendedEditorActionBuilder builder = new ExtendedEditorActionBuilder();
		_extendedContributor = builder.readActionExtensions(EDITOR_ID);
	}

	public void init(IActionBars actionBars) {
		super.init(actionBars);

		if (actionBars != null) {
			initCommonActionBarContributor(actionBars);
			actionBars.getToolBarManager().add(new ManageSkinsAction());
			actionBars.getToolBarManager().add(new Separator());
			initDesignViewerActionBarContributor(actionBars);
			initSourceViewerActionContributor(actionBars);
		}
	}

	/**
	 * @param actionBars
	 */
	private void initCommonActionBarContributor(IActionBars actionBars) {
		_group.fillActionBars(actionBars);
	}

	/**
	 * @param actionBars
	 */
	protected void initDesignViewerActionBarContributor(IActionBars actionBars) {
		if (_designViewerActionBarContributor != null)
			_designViewerActionBarContributor.init(actionBars, getPage());
	}

	/**
	 * @param actionBars
	 */
	protected void initSourceViewerActionContributor(IActionBars actionBars) {
		if (_sourceViewerActionContributor != null)
			_sourceViewerActionContributor.init(actionBars, getPage());
	}

	public void dispose() {
		super.dispose();
		if (_designViewerActionBarContributor != null) {
			_designViewerActionBarContributor.dispose();
		}
		if (_sourceViewerActionContributor != null) {
			_sourceViewerActionContributor.dispose();
		}
		if (_extendedContributor != null) {
			_extendedContributor.dispose();
		}
		if (_group != null) {
			_group.dispose();
		}
	}

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(IMenuManager)
	 */
	public final void contributeToMenu(IMenuManager menu) {
		super.contributeToMenu(menu);
		addToMenu(menu);
		if (_extendedContributor != null)
			_extendedContributor.contributeToMenu(menu);
	}

	private void addToMenu(IMenuManager menu) {
		// IMenuManager menuMgr = new MenuManager(PD_EDITOR_MENU_LABEL,
		// IJMTConstants.PD_EDITOR_MENU_ID);
		// menu.insertBefore(IWorkbenchActionConstants.M_NAVIGATE, menuMgr);
		//
		// menuMgr.add(action);
		// menuMgr.setRemoveAllWhenShown(true);
		//
		// menuMgr.addMenuListener(new IMenuListener()
		// {
		// public void menuAboutToShow(IMenuManager menuMgr)
		// {
		// PageDesignerActionConstants.addStandardActionGroups(menuMgr);
		// RelatedViewActionGroup viewMenu = new RelatedViewActionGroup();
		// viewMenu.fillContextMenu(menuMgr);
		// updateEditorMenu(menuMgr);
		// }
		// });
	}

	/**
	 * @see IExtendedContributor#contributeToPopupMenu(IMenuManager)
	 */
	public final void contributeToPopupMenu(IMenuManager menu) {
		// TODO: this method is empty addToPopupMenu(menu);
		if (_extendedContributor != null)
			_extendedContributor.contributeToPopupMenu(menu);
	}

//	protected void addToPopupMenu(IMenuManager menu) {
//        // do nothing
//	}

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(IToolBarManager)
	 */
	public final void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);
		// TODO: this method is empty addToToolBar(toolBarManager);
		if (_extendedContributor != null)
			_extendedContributor.contributeToToolBar(toolBarManager);
	}

//	protected void addToToolBar(IToolBarManager toolBarManager) {
//	}

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToStatusLine(IStatusLineManager)
	 */
	public final void contributeToStatusLine(IStatusLineManager manager) {
		super.contributeToStatusLine(manager);
		// TODO: this method does nothing addToStatusLine(manager);
		if (_extendedContributor != null)
			_extendedContributor.contributeToStatusLine(manager);
	}

//	protected void addToStatusLine(IStatusLineManager manager) {
//	}

	/**
	 * @see IExtendedContributor#updateToolbarActions()
	 */
	public void updateToolbarActions() {
		if (_extendedContributor != null) {
			_extendedContributor.updateToolbarActions();
		}
		_group.setHTMLEditor(_htmlEditor);
	}

	public void setActiveEditor(IEditorPart targetEditor) {
		if (targetEditor instanceof HTMLEditor) {
			_htmlEditor = (HTMLEditor) targetEditor;
			//StructuredTextEditor textEditor = _htmlEditor.getTextEditor();
			// TODO: never read this._model = textEditor.getModel();
		}
		super.setActiveEditor(targetEditor);
		updateToolbarActions();
		if (_extendedContributor != null)
			_extendedContributor.setActiveEditor(targetEditor);
	}

	public void setInnerActivePage(IEditorPart activeEditor) {
		// This contributor is designed for StructuredTextMultiPageEditorPart.
		// To safe-guard this from problems caused by unexpected usage by
		// other editors, the following
		// check is added.
		if (_htmlEditor != null) {
			if (activeEditor instanceof StructuredTextEditor) {
				activateSourcePage((StructuredTextEditor) activeEditor);
			} else if (activeEditor instanceof SimpleGraphicalEditor) {
				//SimpleGraphicalEditor graphEditor = (SimpleGraphicalEditor) activeEditor;
				activateDesignPage((SimpleGraphicalEditor) activeEditor);
				// TODO: never read this._viewer = graphEditor.getGraphicViewer();
			} else {
				// currently we don't have special action for preview.
				deactivateSourceAndDesignPage(activeEditor);
				// TODO: never read this._viewer = null;
			}
		}

		updateToolbarActions();

		IActionBars actionBars = getActionBars();
		if (actionBars != null) {
			// update menu bar and tool bar
			actionBars.updateActionBars();
		}
	}

	/**
	 * @param activeEditor 
	 * 
	 */
	protected void deactivateSourceAndDesignPage(IEditorPart activeEditor) {
		if (_designViewerActionBarContributor != null) {
			_designViewerActionBarContributor.setActiveEditor(_htmlEditor);
			_designViewerActionBarContributor
					.setViewerSpecificContributionsEnabled(false);
		}
		if (_sourceViewerActionContributor != null) {
			_sourceViewerActionContributor.setActiveEditor(_htmlEditor);
			_sourceViewerActionContributor
					.setViewerSpecificContributionsEnabled(false);
		}
	}

	/**
	 * @param activeEditor
	 */
	protected void activateDesignPage(SimpleGraphicalEditor activeEditor) {

		if (_sourceViewerActionContributor != null /*
													 * &&
													 * _sourceViewerActionContributor
													 * instanceof
													 * ISourceViewerActionBarContributor
													 */) {
			// previously I was trying setActiveEditor(null) here. But as in the
			// super class will
			// compare the editor with original one, if same then directly
			// return. So will not disable
			// those actions. (lium)
			_sourceViewerActionContributor.setActiveEditor(_htmlEditor);
			_sourceViewerActionContributor
					.setViewerSpecificContributionsEnabled(false);
		}

		if (_designViewerActionBarContributor != null) {
			_designViewerActionBarContributor.setActiveEditor(activeEditor);
			_designViewerActionBarContributor
					.setViewerSpecificContributionsEnabled(true);
		}
	}

	/**
	 * @param activeEditor
	 */
	protected void activateSourcePage(StructuredTextEditor activeEditor) {
		if (_designViewerActionBarContributor != null /*
														 * &&
														 * _designViewerActionBarContributor
														 * instanceof
														 * IDesignViewerActionBarContributor
														 */) {
			// _designViewerActionBarContributor only recogonize HTMLEditor and
			// its own GraphicEditor. so not setting source editor to it.
			_designViewerActionBarContributor.setActiveEditor(_htmlEditor);
			_designViewerActionBarContributor
					.setViewerSpecificContributionsEnabled(false);
		}

		if (_sourceViewerActionContributor != null /*
													 * &&
													 * _sourceViewerActionContributor
													 * instanceof
													 * ISourceViewerActionBarContributor
													 */) {
			_sourceViewerActionContributor.setActiveEditor(activeEditor);
			_sourceViewerActionContributor.setViewerSpecificContributionsEnabled(true);
		}
	}

    // TODO: dead?
//	private void updateEditorMenu(IMenuManager menuMgr) {
//		if (this._viewer == null) {
//			return;
//		} else {
//			if (menuMgr != null) {
//				ContainerActionGroup containerActionGroup = new ContainerActionGroup();
//				ActionContext context = new ActionContext(this._viewer
//						.getSelection());
//				context.setInput(this._viewer);
//				containerActionGroup.setContext(context);
//				containerActionGroup.fillContextMenu(menuMgr);
//				containerActionGroup.setContext(null);
//
//				RangeActionGroup rangeActionGroup = new RangeActionGroup();
//				context = new ActionContext(this._viewer.getSelection());
//				context.setInput(this._viewer);
//				rangeActionGroup.setContext(context);
//				rangeActionGroup.fillContextMenu(menuMgr);
//				rangeActionGroup.setContext(null);
//
//				SingleElementActionGroup singleActionGroup = new SingleElementActionGroup();
//				singleActionGroup.setContext(new ActionContext(this._viewer
//						.getSelection()));
//				singleActionGroup.fillContextMenu(menuMgr);
//				singleActionGroup.setContext(null);
//
//				if (this._model != null) {
//					CustomedContextMenuActionGroup customedMenu = new CustomedContextMenuActionGroup();
//					customedMenu.setContext(new ActionContext(_viewer
//							.getSelection()));
//					customedMenu.setModel(_model);
//					customedMenu.setParentControl(_viewer.getControl());
//					customedMenu.fillContextMenu(menuMgr);
//					customedMenu.setContext(null);
//					customedMenu.setParentControl(null);
//					customedMenu.setModel(null);
//				}
//			}
//		}
//	}
}
