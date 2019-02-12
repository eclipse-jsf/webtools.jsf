/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.parts;

import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class SubNodeEditPart extends NodeEditPart {
	/**
	 * 
	 * @return could be null
	 */
	public abstract Node getNodeForFigure();
}
