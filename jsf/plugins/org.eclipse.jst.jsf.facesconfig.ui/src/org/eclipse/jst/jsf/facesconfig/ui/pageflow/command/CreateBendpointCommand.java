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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * Create a new bend point command
 * 
 */
public class CreateBendpointCommand extends BendpointCommand {
	/**
	 * Default constructor
	 */
	public CreateBendpointCommand() {
		// Pageflow.Commands.CreateBendpointCommand.Label = Create Bendpoint
		super(PageflowMessages.Pageflow_Commands_CreateBendpointCommand_Label);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void execute() {
		Map registry = EPackage.Registry.INSTANCE;
		String pageflowURI = PageflowPackage.eNS_URI;
		PageflowPackage pageflowPackage = (PageflowPackage) registry
				.get(pageflowURI);
		PageflowFactory factory = pageflowPackage.getPageflowFactory();
		PageflowLinkBendpoint _newBendpoint = factory.createPFLinkBendpoint();

		_newBendpoint.setRelativeDimensions(getFirstRelativeDimension(),
				getSecondRelativeDimension());
		getPFLink().insertBendpoint(getIndex(), _newBendpoint);
		super.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void undo() {
		super.undo();
		getPFLink().removeBendpoint(getIndex());
	}

}
