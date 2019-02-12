/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
		} else if (
				ITrinidadConstants.TAG_IDENTIFIER_INPUTCOLOR.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_INPUTDATE.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_INPUTFILE.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_INPUTLISTOFVALUES.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_INPUTNUMBERSPINBOX.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_INPUTTEXT.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_SELECTBOOLEANRADIO.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_SELECTONECHOICE.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_SELECTONELISTBOX.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_SELECTONERADIO.isSameTagType(tagIdentifier) ||
				ITrinidadConstants.TAG_IDENTIFIER_PANELLABELANDMESSAGE.isSameTagType(tagIdentifier)
				) {
			return new PanelFormLayoutChildElementEdit();
		} else {
			return new DefaultTrinidadCoreElementEdit();
		}
	}

}
