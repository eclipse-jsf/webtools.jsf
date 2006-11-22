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
package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.jst.pagedesigner.actions.link.LinkRequest;
import org.eclipse.jst.pagedesigner.actions.link.MakeLinkCommand;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 * @version 1.5
 */
public class LinkEditPolicy extends GraphicalEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
		EditPart part = this.getHost();
		IHTMLGraphicalViewer viewer = null;
		if (part instanceof TextEditPart) {
			//TODO: ?? part = (TextEditPart) part;
			viewer = (IHTMLGraphicalViewer) part.getViewer();
		}
		if (request instanceof LinkRequest) {
			LinkRequest req = (LinkRequest) request;
			String identifier = req.getIdentifier();
			DesignRange range = req.getDesignRange();
            // TODO: when part !instancof TextEditPart, viewer == null
			Command command = new MakeLinkCommand(identifier, viewer, part,
					range);
			return command;
		}
		return super.getCommand(request);
	}
}
