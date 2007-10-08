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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.CreateBendpointCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DeleteBendpointCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.MoveBendpointCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;

/**
 * Customize the bendpoint edit policy fro pageflow link connections
 *
 */
public class PFLinkBendpointEditPolicy extends BendpointEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getCreateBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
	 */
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		CreateBendpointCommand createCmd = new CreateBendpointCommand();
		Point p = request.getLocation();
		Connection conn = getConnection();

		conn.translateToRelative(p);

		createCmd.setLocation(p);
		Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
		Point ref2 = getConnection().getTargetAnchor().getReferencePoint();

		conn.translateToRelative(ref1);
		conn.translateToRelative(ref2);

		createCmd.setRelativeDimensions(p.getDifference(ref1), p
				.getDifference(ref2));
		createCmd.setPFLink((PageflowLink) request.getSource().getModel());
		createCmd.setIndex(request.getIndex());
		return createCmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getDeleteBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
	 */
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		DeleteBendpointCommand deleteCom = new DeleteBendpointCommand();
		Point p = request.getLocation();
		deleteCom.setLocation(p);
		deleteCom.setPFLink((PageflowLink) request.getSource().getModel());
		deleteCom.setIndex(request.getIndex());
		return deleteCom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getMoveBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
	 */
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		MoveBendpointCommand moveCmd = new MoveBendpointCommand();
		Point p = request.getLocation();
		Connection conn = getConnection();

		conn.translateToRelative(p);

		moveCmd.setLocation(p);

		Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
		Point ref2 = getConnection().getTargetAnchor().getReferencePoint();

		conn.translateToRelative(ref1);
		conn.translateToRelative(ref2);

		moveCmd.setRelativeDimensions(p.getDifference(ref1), p
				.getDifference(ref2));
		moveCmd.setPFLink((PageflowLink) request.getSource().getModel());
		moveCmd.setIndex(request.getIndex());
		return moveCmd;
	}

}
