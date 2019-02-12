/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;

public class LanguageCodeTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		IPossibleValues pvs = (IPossibleValues)possibleValueAdapters.get(0);
		Assert.assertEquals(58, pvs.getPossibleValues().size());
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
		//positive tests
		assertEquals("ACategory", ppd.getCategory());
		assertEquals(false, ppd.isRequired());

	}
}
