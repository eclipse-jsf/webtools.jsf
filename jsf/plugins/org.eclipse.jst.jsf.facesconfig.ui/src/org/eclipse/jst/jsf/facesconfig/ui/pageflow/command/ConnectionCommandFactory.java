/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

/**
 * The factory helps to create connection commands.
 * 
 * @author hmeng
 * 
 */
public class ConnectionCommandFactory {
	/**
	 * @param request
	 * @return the connection command for  the request
	 */
	public static ConnectionCommand createCommand(Request request) {
		ConnectionCommand command = null;
		if (request.getType() == RequestConstants.REQ_CONNECTION_START
				|| request.getType() == RequestConstants.REQ_CONNECTION_END) {
			command = new AddConnectionCommand();
		} else if (request.getType() == RequestConstants.REQ_RECONNECT_SOURCE
				|| request.getType() == RequestConstants.REQ_RECONNECT_TARGET) {
			command = new ReconnectConnectionCommand();
		} else if (request.getType() == RequestConstants.REQ_DELETE) {
			command = new DeleteConnectionCommand();
		}
		return command;
	}
}
