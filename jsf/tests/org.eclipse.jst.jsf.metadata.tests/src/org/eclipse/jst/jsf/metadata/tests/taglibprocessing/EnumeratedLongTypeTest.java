/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
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
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;

public class EnumeratedLongTypeTest extends TaglibProcessingTestCase {
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues vv =(IValidValues)validValuesAdapters.get(0);
		Assert.assertTrue(vv.isValidValue("0"));
		Assert.assertTrue(vv.isValidValue("3"));
		Assert.assertTrue(vv.isValidValue("5"));
		Assert.assertTrue(vv.getValidationMessages().size()==0);
		vv.getValidationMessages().clear();
		Assert.assertFalse(vv.isValidValue("6"));
		Assert.assertFalse(vv.getValidationMessages().size()==0);
		IValidationMessage msg = (IValidationMessage)vv.getValidationMessages().get(0);
		Assert.assertEquals("bogus validation message", msg.getMessage());
		Assert.assertEquals(99, msg.getSeverity());
		Assert.assertEquals("X99", msg.getCode());
	}
	
	public void testDefaultValues(){		
		Assert.assertNotNull(defaultValueAdapters);
		Assert.assertFalse(defaultValueAdapters.isEmpty());
		
		IDefaultValue dv =(IDefaultValue)defaultValueAdapters.get(0);
		Assert.assertTrue(dv.getDefaultValue().equals("3"));

	}
	
}
