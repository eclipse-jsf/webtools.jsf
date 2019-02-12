/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.taglibprocessing.attributevalues.MultiSignatureEnumerationType;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;

/**
 * Tests {@link LocaleTypeTest} and {@link MultiSignatureEnumerationType} *
 */
public class LocaleTypeTest extends TaglibProcessingTestCase {
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
		assertTrue(vv.isValidValue("en"));
		//negative tests
		vv.getValidationMessages().clear();
		assertFalse(vv.isValidValue("xxx"));	
	}
	
	public void testPropertyPageDescriptor(){		
		Assert.assertNotNull(propertyPageDescriptorAdapters);
		Assert.assertFalse(propertyPageDescriptorAdapters.isEmpty());
		
		IPropertyPageDescriptor ppd =(IPropertyPageDescriptor)propertyPageDescriptorAdapters.get(0);
		//positive test
		assertEquals("ACategory", ppd.getCategory());
		assertEquals(false, ppd.isRequired());

	}
	
	public void testELValidValues() {
		//value can be String (non-EL) or an EL expression pointing at Locale
		Assert.assertNotNull(validELValuesAdapters);
		Assert.assertFalse(validELValuesAdapters.isEmpty());
		
		IValidELValues vv =(IValidELValues)validELValuesAdapters.get(0);
		try {
			String[] validSigs = vv.getExpectedRuntimeType().getSignatures();
			assertEquals(2, validSigs.length);			
			assertEquals("Ljava.util.Locale;", validSigs[0]);
			assertEquals("Ljava.lang.String;", validSigs[1]);
		} catch (ELIsNotValidException e) {		
			e.printStackTrace();
		}
	}
}
