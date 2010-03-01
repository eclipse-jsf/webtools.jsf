/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessingtests2;


import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.AbstractMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;

public class NoImplPossibleVals extends AbstractMetaDataEnabledFeature implements
		IPossibleValues {

	public NoImplPossibleVals() {
		super();
	}

	public List<?> getPossibleValues() {
		return getPossibleVals();
	}

	private List<?> getPossibleVals() {
		return getTraitValueAsListOfStrings(IPossibleValues.POSSIBLE_VALUES_PROP_NAME);
//		return CMAnnotationHelper.getCMAttributePropertyValues(getCMAnnotationContext().getBundleId(), getCMAnnotationContext().getUri(),
//				getCMAnnotationContext().getElementName(), getCMAnnotationContext().getAttributeName(),
//				IPossibleValues.POSSIBLE_VALUES_PROP_NAME);

	}
	
	

}
