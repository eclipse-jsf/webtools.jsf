package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;

/**
 * Change the end of a link.
 * 
 * @author hmeng
 * 
 */
public class ReconnectConnectionCommand extends ConnectionCommand {

	public ReconnectConnectionCommand() {
		// TODO Auto-generated constructor stub
	}

	public boolean canExecute() {
		// if user don't set PFLink object before, this command can't be
		// executed.
		if (link == null || link.eContainer() == null) {
			return false;
		}

		// It is a reconnect both of source and target command
		if (oldSource != null && pageflowNode != null && oldTarget != null
				&& target != null) {
			if (!PageflowValidation.getInstance().isValidLinkForCreation(
					pageflowNode, target)) {
				return false;
			}
		}

		// It is a reconnect only source command
		if (oldSource != null && pageflowNode != null) {
			if (!PageflowValidation.getInstance().isValidLinkForCreation(
					pageflowNode, oldTarget)) {
				return false;
			}
		}
		// It is a reconnect only target command
		if (oldTarget != null && target != null) {
			if (!PageflowValidation.getInstance().isValidLinkForCreation(
					oldSource, target)) {
				return false;
			}
		}

		return true;

	}

	public void doExecute() {
		// It is a reconnect source command
		if (oldSource != null && pageflowNode != null) {
			link.setSource(pageflowNode);
		}
		// It is a reconnect target command
		if (oldTarget != null && target != null) {
			link.setTarget(target);
		}
	}

	public void undo() {
		if (canExecute()) {
			// It was a reconnect source command
			if (oldSource != null && pageflowNode != null) {
				// The link source must be replaced by the oldSource
				if (link.getSource() != null) {
					link.getSource().getOutlinks().remove(link);
				}
				// Source should not know link anymore
				pageflowNode.getOutlinks().remove(link);
				// Re-link with oldSource
				// No containment link between link and input and output
				// Two add method need to be called
				link.setSource(oldSource);
			}
			// It was a reconnect target command
			if (oldTarget != null && target != null) {
				// The link target must be replaced by the oldTarget
				if (link.getTarget() != null) {
					link.getTarget().getInlinks().remove(link);
				}
				// Target should not know link anymore
				target.getInlinks().remove(link);
				// Re-link with oldTarget
				// No containment link between link and input and output port
				// Two add method need to be called
				link.setTarget(oldTarget);
			}
		}
	}

}
