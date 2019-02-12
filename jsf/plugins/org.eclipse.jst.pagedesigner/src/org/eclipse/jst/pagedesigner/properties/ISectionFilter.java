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
package org.eclipse.jst.pagedesigner.properties;

import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated  unused
 */
public interface ISectionFilter {

	/**
	 * @param node
	 * @return true if node applies
	 */
	boolean appliesTo(Element node);

}
