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
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEditFactory;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;

/**
 * AbstractElementEditFactory extension for Trinidad core tag library.
 * 
 * @author Ian Trimble - Oracle
 */
public class TrinidadCoreElementEditFactory extends AbstractElementEditFactory {

	/**
	 * Instantiates an instance, with ITrinidadConstants.URI_CORE as the
	 * supported URI.
	 */
	public TrinidadCoreElementEditFactory() {
		super(ITrinidadConstants.URI_CORE);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEditFactory#createElementEdit(org.eclipse.jst.jsf.common.dom.TagIdentifier)
	 */
	public IElementEdit createElementEdit(final TagIdentifier tagIdentifier) {
		if (ITrinidadConstants.TAG_IDENTIFIER_PANELTABBED.isSameTagType(tagIdentifier)) {
			return new PanelTabbedElementEdit();
		} else if (ITrinidadConstants.TAG_IDENTIFIER_SHOWDETAILITEM.isSameTagType(tagIdentifier)) {
			return new ShowDetailItemElementEdit();
		} else {
			return new DefaultTrinidadCoreElementEdit();
		}
	}

}
