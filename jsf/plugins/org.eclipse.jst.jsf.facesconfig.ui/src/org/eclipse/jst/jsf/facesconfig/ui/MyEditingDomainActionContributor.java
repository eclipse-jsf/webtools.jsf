package org.eclipse.jst.jsf.facesconfig.ui;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;

/**
 * The action contributor for form based page.
 * 
 * @author hmeng
 * 
 */
public class MyEditingDomainActionContributor extends
		EditingDomainActionBarContributor implements INestedActionContributor {
	public MyEditingDomainActionContributor() {
		super();
	}

	public void setActiveEditor(IEditorPart part) {
		if (getActiveEditor() != null) {
			deactivate();
		}
		super.setActiveEditor(part);
		IActionBars actionBars = getActionBars();
		actionBars.clearGlobalActionHandlers();
		if (part instanceof IEditingDomainProvider) {
			actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					deleteAction);
			actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
					undoAction);
			actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
					redoAction);
			// actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
			// cutAction);
			// actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
			// copyAction);
			// actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
			// pasteAction);
			activate();
		}
	}

	public void update() {
		if (getActiveEditor() instanceof IEditingDomainProvider) {
			super.update();
		}
	}
}
