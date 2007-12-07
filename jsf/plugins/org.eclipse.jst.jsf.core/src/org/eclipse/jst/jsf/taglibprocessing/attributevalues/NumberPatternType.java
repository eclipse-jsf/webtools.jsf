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

import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a numeric pattern type.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class NumberPatternType extends AbstractRootTypeDescriptor implements IPossibleValues{
	//should we ever decide to validate the patterns, extend EnumerationType
	final private static String[] PATTERNS = {					
			"0.00", //$NON-NLS-1$
			"#,##0", //$NON-NLS-1$
			"#,##0.00", //$NON-NLS-1$
			"#,##0;(#,##0)", //$NON-NLS-1$
			"#,##0.00;(#,##0.00)", //$NON-NLS-1$
			"0.##E0", //$NON-NLS-1$
			"0%", //$NON-NLS-1$
			"0.00%" //$NON-NLS-1$
	};

	public List getPossibleValues() {
		List ret = new ArrayList();		
		for (int i=0;i<PATTERNS.length;i++){
			ret.add(new PossibleValue(PATTERNS[i]));
		}
		return ret;
	}


}
