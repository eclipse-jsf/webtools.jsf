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

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory;

/**
 * @author mengbo
 * @version 1.5
 */
public class HTMLElementEditFactory implements IElementEditFactory 
{
    final static TagIdentifier HTMLHEAD_TAG_IDENTIFIER =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_HTML, IHTMLConstants.TAG_HEAD);
    final static TagIdentifier HTMLTABLE_TAG_IDENTIFIER =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_HTML, IHTMLConstants.TAG_TABLE);
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.w3c.dom.Element)
	 */
	public IElementEdit createElementEdit(TagIdentifier tagIdentifier) {
		if (HTMLTABLE_TAG_IDENTIFIER.isSameTagType(tagIdentifier)) {
			return new TableElementEdit();
		} else if (HTMLHEAD_TAG_IDENTIFIER.isSameTagType(tagIdentifier)) {
			return new HeadElementEdit();
		} else if (tagIdentifier != null) {
			// No need to check the URI, it was checked to get this factory.
			// Just check the tag name, ignoring case in the HTML tag names.
			// The HTML tag meta data uses upper case and the programming
			// constants are in lower case.
			if (IHTMLConstants.TAG_LINK.equalsIgnoreCase(tagIdentifier.getTagName())) {
				return new StylesheetLinkElementEdit();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#getSupportedURI()
	 */
	public String getSupportedURI() {
		return ITLDConstants.URI_HTML;
	}
}
