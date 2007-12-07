/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Yury Kats/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a content (MIME) type.
 * A ContentType is defined as in 4.0 html spec http://www.w3.org/TR/html401/types.html#h-6.7
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */

public class ContentType extends EnumerationType implements IPossibleValues {
	
	/**
	 * List of some of the more common content (MIME) types
	 * See http://www.w3.org/TR/html4/types.html#type-color
	 */
	private final static String[] commontypes = {
		"text/html",  //$NON-NLS-1$
		"text/css",  //$NON-NLS-1$
		"image/jpeg",  //$NON-NLS-1$
		"image/gif",  //$NON-NLS-1$
		"audio/mpeg",  //$NON-NLS-1$
		"video/mpeg", //$NON-NLS-1$
		"video/quicktime",  //$NON-NLS-1$
		"text/javascript" //$NON-NLS-1$
	};

	protected String getReturnType(){ return "java.lang.String";} //$NON-NLS-1$

	public boolean isValidValue(String value) {	
		boolean bValid = true;
		if (value == null || value.trim().length() == 0)
			bValid = false;
		
		if(!bValid) {
			addNewValidationMessage(Messages.ContentType_MIME_not_empty);
		}
		
		return getValidationMessages().isEmpty();
	}

	public List getPossibleValues() {
		List ret = new ArrayList(commontypes.length);
		for (int i=0;i < commontypes.length;i++){
			PossibleValue pv = new PossibleValue(commontypes[i]);
			ret.add(pv);
		}
		return ret;
	}

}
