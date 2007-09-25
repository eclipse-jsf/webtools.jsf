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
package org.eclipse.jst.pagedesigner.actions.link;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public interface ILinkCreator {

	/**
	 * @param part
	 * @param range
	 * @return the link element
	 */
	public Element makeLinkElement(EditPart part, DesignRange range);

	/**
	 * @return link identifier
	 */
	public String getLinkIdentifier();

	/**
	 * @param range
	 * @return true if can call makeLinkElement
	 */
	public boolean canExecute(DesignRange range);

	/**
	 * @param part
	 * @param range
	 * @return a preview string that approximates the result
	 * of makeLinkElement
	 */
	public String getSourcePreview(EditPart part, DesignRange range);
}
