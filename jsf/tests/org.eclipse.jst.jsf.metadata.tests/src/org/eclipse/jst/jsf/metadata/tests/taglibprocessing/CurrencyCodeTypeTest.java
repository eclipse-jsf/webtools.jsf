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

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;

/**
 * 
 *
 */
public class CurrencyCodeTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		IPossibleValues pvs = (IPossibleValues)possibleValueAdapters.get(0);
		Assert.assertEquals(173, pvs.getPossibleValues().size());
	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues vv =(IValidValues)validValuesAdapters.get(0);
		//positive tests
		assertTrue(vv.isValidValue("USD"));
		assertTrue(vv.isValidValue("AOA"));
		//negative tests
		vv.getValidationMessages().clear();
		assertFalse(vv.isValidValue("XXX"));	
	}
	
	public void testPropertyPageDescriptor(){		
		Assert.assertNotNull(propertyPageDescriptorAdapters);
		Assert.assertFalse(propertyPageDescriptorAdapters.isEmpty());
		
		IPropertyPageDescriptor ppd =(IPropertyPageDescriptor)propertyPageDescriptorAdapters.get(0);
		assertEquals(false, ppd.isRequired());

	}
}
