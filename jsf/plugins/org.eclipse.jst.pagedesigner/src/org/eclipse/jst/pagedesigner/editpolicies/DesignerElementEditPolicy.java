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

import org.eclipse.gef.editpolicies.ComponentEditPolicy;

/**
 * @author mengbo
 */
public class DesignerElementEditPolicy extends ComponentEditPolicy {

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	// */
	// protected Command createDeleteCommand(GroupRequest deleteRequest)
	// {
	// // Object model = getHost().getModel();
	// // ;
	// // Node parent = null;
	// // if (model instanceof Node)
	// // {
	// // parent = ((Node) model).getParentNode();
	// // }
	// // EditDomain domain = getHost().getViewer().getEditDomain();
	// // IEditorPart editor = null;
	// // //FIXME: must do this cast?
	// // if (domain instanceof DefaultEditDomain)
	// // {
	// // editor = ((DefaultEditDomain) domain).getEditorPart();
	// // }
	// // if (editor instanceof HTMLEditor)
	// // {
	// // DeleteNodeCommand deleteCmd = new DeleteNodeCommand(((HTMLEditor)
	// editor).getTextEditor().getTextViewer());
	// // deleteCmd.setParent(parent);
	// // deleteCmd.setChild((Node) getHost().getModel());
	// // return deleteCmd;
	// // }
	// // else
	// // {
	// // return null;
	// // }
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
	// */
	// public Command getCommand(Request request)
	// {
	// if (request.getType() == DesignCutAction.CUT_TYPE && request instanceof
	// GroupRequest)
	// {
	// return createCutCommand((GroupRequest) request);
	// }
	// else if (request.getType() == DesignPasteAction.PASTE_TYPE && request
	// instanceof GroupRequest)
	// {
	// return createPasteCommand((GroupRequest) request);
	// }
	// return super.getCommand(request);
	// }
	//
	// protected Command createCutCommand(GroupRequest cutRequest)
	// {
	// Object model = getHost().getModel();
	// ;
	// Node parent = null;
	// if (model instanceof Node)
	// {
	// parent = ((Node) model).getParentNode();
	// }
	// EditDomain domain = getHost().getViewer().getEditDomain();
	// IEditorPart editor = null;
	// //FIXME: must do this cast?
	// if (domain instanceof DefaultEditDomain)
	// {
	// editor = ((DefaultEditDomain) domain).getEditorPart();
	// }
	// if (editor instanceof HTMLEditor)
	// {
	// CutNodeCommand cutCmd = new CutNodeCommand(((HTMLEditor)
	// editor).getTextEditor().getTextViewer());
	// cutCmd.setParent(parent);
	// cutCmd.setChild((Node) getHost().getModel());
	// return cutCmd;
	// }
	// else
	// {
	// return null;
	// }
	// }
	//
	// protected Command createPasteCommand(GroupRequest cutRequest)
	// {
	// Object model = getHost().getModel();
	// ;
	// Node parent = null;
	// if (model instanceof Node)
	// {
	// parent = ((Node) model).getParentNode();
	// }
	// EditDomain domain = getHost().getViewer().getEditDomain();
	// IEditorPart editor = null;
	// //FIXME: must do this cast?
	// if (domain instanceof DefaultEditDomain)
	// {
	// editor = ((DefaultEditDomain) domain).getEditorPart();
	// }
	// if (editor instanceof HTMLEditor)
	// {
	// PasteNodeCommand pasteCmd = new PasteNodeCommand(((HTMLEditor)
	// editor).getTextEditor().getTextViewer());
	// pasteCmd.setParent(parent);
	// pasteCmd.setChild((Node) getHost().getModel());
	// return pasteCmd;
	// }
	// else
	// {
	// return null;
	// }
	// }

}
