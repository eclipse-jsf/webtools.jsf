/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.commands.range;

import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public interface IInputSourceProvider {
	/**
	 * @return Returns the _data.
	 */
	public Node[] getNodes();

	/**
	 * @return the string data
	 */
	public String getStringData();

	/**
	 * @return the character data
	 */
	public Character getCharacterData();
}
