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
package org.eclipse.jst.pagedesigner.properties.attrgroup;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * This is a special interface for those special dialog field that need context
 * information.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface IElementContextable {
	/**
	 * 
	 * @param ancester
	 *            an non null element, could be the element being edited, or an
	 *            ancester element when we are creating new element.
	 * @param element
	 *            if not null, then is the element being edited, could be used
	 *            to retrive initial data.
	 * 
	 */
	public void setElementContext(IDOMNode ancester, IDOMElement element);
}
