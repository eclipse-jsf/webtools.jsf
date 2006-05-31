package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

/**
 * Delete a connection.
 * 
 * @author hmeng
 * 
 */
public class DeleteConnectionCommand extends ConnectionCommand {

	public boolean canExecute() {
		return link != null;
	}

	protected void doExecute() {
		// It is a delete connection command
		if (link != null) {
			link.setSource(null);
			link.setTarget(null);
			oldSource.getPageflow().getLinks().remove(link);
			link.eAdapters().clear();
		}
	}

	public void undo() {
		// It is a delete connection command
		if (canExecute()) {
			link.setSource(oldSource);
			link.setTarget(oldTarget);
			oldSource.getPageflow().getLinks().add(link);
		}
	}

}
