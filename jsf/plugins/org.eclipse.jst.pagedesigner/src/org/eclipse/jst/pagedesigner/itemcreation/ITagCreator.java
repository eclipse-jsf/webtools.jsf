/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation;

import org.w3c.dom.Element;

/**
 * The interface type of all tag creators.
 * 
 * Clients should *not* implement this interface.  Extend AbstractTagCreator instead.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface ITagCreator {
	/**
	 * @param creationData 
	 * @return W3C Element for the  tag at the correct position in the DOM.   May be null if creation cannot occur.
	 */
	public Element createTag(final CreationData creationData);
}
