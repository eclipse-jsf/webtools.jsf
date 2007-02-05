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
package org.eclipse.jst.pagedesigner.elementedit.html;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory;

/**
 * @author mengbo
 * @version 1.5
 */
public class HTMLElementEditFactory implements IElementEditFactory 
{
    final static TagIdentifier HTMLTABLE_TAG_IDENTIFIER =
        TagIdentifierFactory.createJSPTagWrapper(IJMTConstants.URI_HTML, IHTMLConstants.TAG_TABLE);
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.w3c.dom.Element)
	 */
	public IElementEdit createElementEdit(TagIdentifier tagIdentifier) {
		if (HTMLTABLE_TAG_IDENTIFIER.isSameTagType(tagIdentifier)) {
			return new TableElementEdit();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#getSupportedURI()
	 */
	public String getSupportedURI() {
		return IJMTConstants.URI_HTML;
	}
}
