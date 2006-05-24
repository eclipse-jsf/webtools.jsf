package org.eclipse.jst.jsf.facesconfig.ui;

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage;
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
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		if (getActiveEditor() != null) {
			deactivate();
		}
		IActionBars actionBars = getActionBars();
		actionBars.clearGlobalActionHandlers();

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
		if (part instanceof FacesConfigMasterDetailPage) {
			activate();
		}
	}

	public void update() {
		if (getActiveEditor() instanceof FacesConfigMasterDetailPage) {
			super.update();
		}
	}
}
