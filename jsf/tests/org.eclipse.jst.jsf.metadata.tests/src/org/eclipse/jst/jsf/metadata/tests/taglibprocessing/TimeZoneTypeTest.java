/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import java.util.Locale;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class TimeZoneTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		IPossibleValues pvs = (IPossibleValues)possibleValueAdapters.get(0);
		Assert.assertTrue( pvs.getPossibleValues().size() > 50);//there are a bunch and it depends on the env.
	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues vv =(IValidValues)validValuesAdapters.get(0);
		//positive tests
		if (Locale.getDefault().getDisplayLanguage().equals(Locale.ENGLISH.getDisplayLanguage()))
			assertTrue(vv.isValidValue("America/Edmonton"));
		//negative tests
		vv.getValidationMessages().clear();
		assertFalse(vv.isValidValue("xxx"));	
	}

}
