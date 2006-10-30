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
	public static final String LINK_IDENTIFIER = "linkIdentifier";

	public Element makeLinkElement(EditPart part, DesignRange range);

	public String getLinkIdentifier();

	public boolean canExecute(DesignRange range);

	public String getSourcePreview(EditPart part, DesignRange range);
}
