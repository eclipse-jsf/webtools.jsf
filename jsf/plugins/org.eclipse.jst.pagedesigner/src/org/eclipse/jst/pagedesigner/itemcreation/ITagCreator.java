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

import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * Interface to be implemented by tag creator factories
 * currently NOT to be implemented by clients 
 */
public interface ITagCreator {
	/**
	 * @param tagToolPaletteEntry
	 * @param model
	 * @param domPosition
	 * @return W3C Element for the  tag at the correct position in the DOM.   May be null if creation cannot occur.
	 */
	public Element createTag(TagToolPaletteEntry tagToolPaletteEntry, IDOMModel model, IDOMPosition domPosition);
}
