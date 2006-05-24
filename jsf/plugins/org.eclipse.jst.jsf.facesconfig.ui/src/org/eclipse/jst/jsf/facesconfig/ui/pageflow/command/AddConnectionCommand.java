package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.layout.PageflowLayoutManager;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;

/**
 * Add a new connection
 * 
 * @author hmeng
 * 
 */
public class AddConnectionCommand extends ConnectionCommand {

	public boolean canExecute() {
		// if user don't set PFLink object before, this command can't be
		// executed.
		if (link == null) {
			return false;
		}

		// It is a connection create command
		if (oldSource == null && oldTarget == null) {
			// It is a connection create command
			// Source and target must be pointing to some
			// real connection point
			if (pageflowNode == null || target == null) {
				return false;
			}

			if (!PageflowValidation.getInstance().isValidLinkForCreation(
					pageflowNode, target)) {
				return false;
			}
		}
		return true;
	}

	public void doExecute() {
		// It is a connection create command
		if (canExecute() && oldSource == null && oldTarget == null) {
			Pageflow pageflow = null;
			// Get a reference to the pageflow
			if (pageflowNode != null) {
				pageflow = pageflowNode.getPageflow();
			} else if (target != null) {
				pageflow = target.getPageflow();
			}
			pageflow.connect(pageflowNode, target, link);

			// self loop link
			if (pageflowNode == target) {
				PageflowLayoutManager.updateSelfLoopLink(link);
			}
		}
	}

	public void redo() {
		// TODO Auto-generated method stub
		super.redo();
	}

	public void undo() {
		if (canExecute()) {
			// It was a connection create command
			if (oldSource == null && oldTarget == null) {

				Pageflow pageflow = null;
				// Get a reference to the pageflow
				if (pageflowNode != null) {
					pageflow = pageflowNode.getPageflow();
				} else if (target != null) {
					pageflow = target.getPageflow();
				}

				// Ensure that link knows nothing about nodes anymore.
				link.setSource(null);
				link.setTarget(null);
				// Remove link from pageflow
				pageflow.getLinks().remove(link);
			}
		}
	}

}
