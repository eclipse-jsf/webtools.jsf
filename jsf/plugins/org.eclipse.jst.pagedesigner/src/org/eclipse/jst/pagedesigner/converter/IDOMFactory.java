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
package org.eclipse.jst.pagedesigner.converter;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Factory interface. AbstractTagConverter will implement it.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface IDOMFactory {
	/**
	 * create element
	 * 
	 * @param tag
	 * @return a new element using tag as its name
	 */
	public Element createElement(String tag);

	/**
	 * create text node
	 * 
	 * @param content
	 * @return a new text node using content as its body
	 */
	public Text createText(String content);
}
