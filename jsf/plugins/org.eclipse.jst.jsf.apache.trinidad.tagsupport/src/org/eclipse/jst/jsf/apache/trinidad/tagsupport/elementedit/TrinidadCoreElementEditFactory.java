/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.elementedit;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory;

/**
 * IElementEditFactory implementation for Trinidad core tag library.
 * 
 * @author Ian Trimble - Oracle
 */
public class TrinidadCoreElementEditFactory implements IElementEditFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.eclipse.jst.jsf.common.dom.TagIdentifier)
	 */
	public IElementEdit createElementEdit(final TagIdentifier tagIdentifier) {
		if (ITrinidadConstants.TAG_IDENTIFIER_PANELTABBED.isSameTagType(tagIdentifier)) {
			return new PanelTabbedElementEdit();
		} else {
			return new DefaultTrinidadCoreElementEdit();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#getSupportedURI()
	 */
	public String getSupportedURI() {
		return ITrinidadConstants.URI_CORE;
	}

}
