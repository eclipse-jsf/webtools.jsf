/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager;

import org.w3c.dom.Element;

/**
 * Factory that provides IDTInfo instances.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public interface IDTInfoFactory {

	/**
	 * Gets an IDTInfo instance for the specified Element.
	 * 
	 * @param element Element instance for which to locate and return IDTInfo
	 * instance.
	 * @return An IDTInfo instance for the specified Element.
	 */
	public IDTInfo getDTInfo(Element element);

}
