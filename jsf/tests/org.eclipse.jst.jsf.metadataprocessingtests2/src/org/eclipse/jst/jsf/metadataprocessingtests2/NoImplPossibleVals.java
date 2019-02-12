/*******************************************************************************
 * Copyright (c) 2006, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
