package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.internal.Workbench;

/**
 * Action to change the current DT skin.
 * 
 * @author Ian Trimble - Oracle
 */
public class ManageSkinsAction extends Action {

	/**
	 * Constructs an instance.
	 */
	public ManageSkinsAction() {
		super(ActionsMessages.getString("ManageSkinsAction.Text"), AS_DROP_DOWN_MENU); //$NON-NLS-1$
		setImageDescriptor(PDPlugin.getDefault().getImageDescriptor("skin.png")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		IEditorPart editorPart = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editorPart instanceof HTMLEditor) {
			IEditorInput editorInput = editorPart.getEditorInput();
			if (editorInput instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput)editorInput).getFile();
				IProject project = file.getProject();
				if (project != null) {
					SelectionProvider selectionProvider = new SelectionProvider();
					selectionProvider.setSelection(new StructuredSelection(project));
			        PropertyDialogAction propertyDialogAction = new PropertyDialogAction(editorPart.getEditorSite(), selectionProvider);
			        PreferenceDialog dialog = propertyDialogAction.createDialog();
			        if (dialog != null) {
			        	dialog.setSelectedNode("org.eclipse.jst.pagedesigner.WPEPropertyPage"); //$NON-NLS-1$
			        	//yes, we create AGAIN - cannot find another way to get the selected node to stick
			        	dialog = propertyDialogAction.createDialog();
			        	if (dialog != null) {
			        		dialog.open();
			        	}
			        }
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#getMenuCreator()
	 */
	@Override
	public IMenuCreator getMenuCreator() {
		return new MenuCreator();
	}



	/**
	 * Simple selection provider for creation of the property dialog.
	 */
	private class SelectionProvider implements ISelectionProvider {
		private ISelection selection;
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
		 */
		public void addSelectionChangedListener(ISelectionChangedListener listener) {
			//do nothing - we don't care here
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
		 */
		public void removeSelectionChangedListener(ISelectionChangedListener listener) {
			//do nothing - we don't care here
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
		 */
		public void setSelection(ISelection selection) {
			this.selection = selection;
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
		 */
		public ISelection getSelection() {
			return selection;
		}
	}



	/**
	 * Store last created menu so we can dispose on next creation.
	 */
	private static Menu lastMenu;
	/**
	 * Menu creator for the drop-down button.
	 */
	private class MenuCreator implements IMenuCreator {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.action.IMenuCreator#dispose()
		 */
		public void dispose() {
			if (lastMenu != null) {
				lastMenu.dispose();
			}
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
		 */
		public Menu getMenu(Control control) {
			dispose();
			lastMenu = new Menu(control);
			buildMenu(lastMenu);
			return lastMenu;
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
		 */
		public Menu getMenu(Menu menu) {
			dispose();
			lastMenu = new Menu(menu);
			buildMenu(lastMenu);
			return lastMenu;
		}
		private void buildMenu(Menu menu) {
			MenuItem menuItem = new MenuItem(lastMenu, SWT.PUSH);
			menuItem.setText(ActionsMessages.getString("ManageSkinsAction.Text")); //$NON-NLS-1$
			menuItem.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					run();
				}
				public void widgetDefaultSelected(SelectionEvent event) {
					widgetSelected(event);
				}
			});
			IEditorPart editorPart = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editorPart instanceof HTMLEditor) {
				IEditorInput editorInput = editorPart.getEditorInput();
				if (editorInput instanceof IFileEditorInput) {
					IFile file = ((IFileEditorInput)editorInput).getFile();
					IProject project = file.getProject();
					if (project != null) {
						SkinsMenuItemBuilder skinsMenuItemBuilder =
							new SkinsMenuItemBuilder(project);
						skinsMenuItemBuilder.buildMenuItems(menu);
					}
				}
			}
		}
	}

}
