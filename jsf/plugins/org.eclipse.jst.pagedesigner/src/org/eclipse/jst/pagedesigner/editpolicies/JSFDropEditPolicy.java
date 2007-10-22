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
import org.eclipse.jst.pagedesigner.commands.PDDropRequest;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class JSFDropEditPolicy extends GraphicalEditPolicy {
	String _attrName;

	/**
	 * @param attrname
	 */
	public JSFDropEditPolicy(String attrname) {
		_attrName = attrname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof PDDropRequest) {
			return getHost();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
		if (request instanceof PDDropRequest) {
			PDDropRequest r = (PDDropRequest) request;
			final String s = (String) r.getCurrentEvent().data;
			return new Command() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.gef.commands.Command#execute()
				 */
				public void execute() {
					// XXX: should check whether alreayd set the attribute,
					// maybe
					// should also popup dialog etc.
					((Element) getHost().getModel()).setAttribute(_attrName, s);
				}
			};
		}

		return super.getCommand(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	public void eraseTargetFeedback(Request request) {
		// 
		super.eraseTargetFeedback(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public void showTargetFeedback(Request request) {
		super.showTargetFeedback(request);
	}
}
