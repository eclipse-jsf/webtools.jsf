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

import java.util.List;

import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class MoveSupport {
	/**
	 * Check whether the move operation only drags a single node.
	 * 
	 * @param request
	 * @return true if the request affects a single node
	 */
	public static boolean isSingleNode(ChangeBoundsRequest request) {
		List parts = request.getEditParts();
		if (parts == null || parts.size() != 1
				|| !(parts.get(0) instanceof NodeEditPart)) {
			return false;
		}
        return true;
	}

	/**
	 * this method must be called after isSingleNode
	 * 
	 * @param request
	 * @return the dragged part
	 */
	public static NodeEditPart getDraggedPart(ChangeBoundsRequest request) {
		List parts = request.getEditParts();
		NodeEditPart part = (NodeEditPart) parts.get(0);
		return part;
	}

	/**
	 * this method must be called after isSingleNode
	 * 
	 * @param request
	 * @return the dragged node
	 */
	public static Node getDraggedNode(ChangeBoundsRequest request) {
		return getDraggedPart(request).getIDOMNode();
	}
}
