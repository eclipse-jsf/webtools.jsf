/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a Link type.
 * 
 * Possible values are only common types; not all
 * Valid values just checks for a non-empty value, and not against known link types
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class LinkType extends EnumerationType implements IPossibleValues{
	//common link types
	private String[] _linkTypes = new String[]{
			"Alternate", //$NON-NLS-1$
			"Stylesheet", //$NON-NLS-1$
			"Start", //$NON-NLS-1$
			"Next", //$NON-NLS-1$
			"Prev", //$NON-NLS-1$
			"Contents", //$NON-NLS-1$
			"Index", //$NON-NLS-1$
			"Glossary", //$NON-NLS-1$
			"Copyright", //$NON-NLS-1$
			"Chapter", //$NON-NLS-1$
			"Section", //$NON-NLS-1$
			"Subsection", //$NON-NLS-1$
			"Appendix", //$NON-NLS-1$
			"Help", //$NON-NLS-1$
			"Bookmark" //$NON-NLS-1$
		};
	
	
	@Override
	protected String getReturnType() {
		return "java.lang.String"; //$NON-NLS-1$
	}

	public List getPossibleValues() {
		List ret = new ArrayList();		
		for(int i=0;i<_linkTypes.length;i++){
			PossibleValue pv = new PossibleValue(_linkTypes[i].toString(), _linkTypes[i].toString());
			ret.add(pv);
		}
		return ret;
	}

	public boolean isValidValue(String value) {
		if (value == null || value.trim().length() == 0){
			addNewValidationMessage(Messages.LinkType_16);
			return false;
		}
		return true;
	}

}
