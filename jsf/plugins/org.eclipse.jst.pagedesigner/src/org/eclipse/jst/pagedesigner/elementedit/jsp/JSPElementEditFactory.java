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
package org.eclipse.jst.pagedesigner.elementedit.jsp;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory;
import org.eclipse.jst.pagedesigner.jsp.core.IJSPCoreConstants;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSPElementEditFactory implements IElementEditFactory 
{
    final static TagIdentifier TAG_DIRECTIVE_TAGLIB_TAG_IDENTIFIER =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSP, IJSPCoreConstants.TAG_DIRECTIVE_TAGLIB);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.w3c.dom.Element)
	 */
	public IElementEdit createElementEdit(TagIdentifier tagIdentifier) {
		if (TAG_DIRECTIVE_TAGLIB_TAG_IDENTIFIER.isSameTagType(tagIdentifier)) {
			return new TaglibElementEdit();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#getSupportedURI()
	 */
	public String getSupportedURI() {
		return ITLDConstants.URI_JSP;
	}

}
