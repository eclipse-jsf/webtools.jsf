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


package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;

/**
 * Change the end of a link.
 * 
 * @author hmeng
 * 
 */
public class ReconnectConnectionCommand extends ConnectionCommand {

	/**
	 * Default constructor
	 */
	public ReconnectConnectionCommand() {
		super();
	}

	public boolean canExecute() {
		// if user didn't set PFLink object beforehand, this command can't be
		// executed.
		if (link == null || link.eContainer() == null) {
			return false;
		}

		// Reconnect both source and target
		if (oldSource != null && source != null && oldTarget != null
				&& target != null) {
			if (!PageflowValidation.getInstance().isValidLinkForCreation(
					source, target)) {
				return false;
			}
		}

		// Reconnect source
		if (oldSource != null && source != null) {
			if (!PageflowValidation.getInstance().isValidLinkForCreation(
					source, oldTarget)) {
				return false;
			}
		}
		// Reconnect target
		if (oldTarget != null && target != null) {
			if (!PageflowValidation.getInstance().isValidLinkForCreation(
					oldSource, target)) {
				return false;
			}
		}

		return true;

	}

	public void doExecute() {
		String outcome = null, action = null, largeIcon = null, smallIcon = null;
		boolean isRedirect = false;
		outcome = link.getOutcome();
		action = link.getFromaction();
		isRedirect = link.isRedirect();
		largeIcon = link.getLargeicon();
		smallIcon = link.getSmallicon();
		// It is a reconnect source command
		if (oldSource != null && source != null) {
			link.setSource(source);
		}
		// It is a reconnect target command
		if (oldTarget != null && target != null) {
			link.setTarget(target);
		}
		link.setOutcome(outcome);
		link.setFromaction(action);
		link.setLargeicon(largeIcon);
		link.setSmallicon(smallIcon);
		link.setRedirect(isRedirect);
	}

	public void undo() {
		if (canExecute()) {
			// It was a reconnect source command
			if (oldSource != null && source != null) {
				// The link source must be replaced by the oldSource
				if (link.getSource() != null) {
					link.getSource().getOutlinks().remove(link);
				}
				source.getOutlinks().remove(link);
				link.setSource(oldSource);
			}
			// It was a reconnect target command
			if (oldTarget != null && target != null) {
				// The link target must be replaced by the oldTarget
				if (link.getTarget() != null) {
					link.getTarget().getInlinks().remove(link);
				}
				target.getInlinks().remove(link);
				link.setTarget(oldTarget);
			}
		}
	}

}
