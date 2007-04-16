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
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;


public class StringTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertFalse(possibleValueAdapters.isEmpty());
		
		IPossibleValues pv =(IPossibleValues)possibleValueAdapters.get(0);
		Assert.assertTrue(pv.getPossibleValues().size() == 4);
		
		IPossibleValue val = (IPossibleValue)pv.getPossibleValues().get(0);
		Assert.assertFalse(val.getDisplayValue().equals(val.getValue()));

		Assert.assertNotNull(val.getIcon());
		Assert.assertFalse(val.getIcon().getClass().getName().equals("org.eclipse.jface.resource.MissingImageDescriptor"));// equals("/testfiles/icons/attr_val.gif"));
		Assert.assertTrue(val.getIcon().getClass().getName().equals("org.eclipse.jface.resource.URLImageDescriptor"));// equals("/icons/foo.gif"));

		Assert.assertTrue(val.isDefaultValue());
		
		val = (IPossibleValue)pv.getPossibleValues().get(1);
		Assert.assertFalse(val.getDisplayValue().equals(val.getValue()));
		Assert.assertFalse(val.isDefaultValue());
	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues vv =(IValidValues)possibleValueAdapters.get(0);
		Assert.assertTrue(vv.isValidValue("A"));
		Assert.assertTrue(vv.isValidValue("B"));
		Assert.assertFalse(vv.isValidValue("a"));

	}
	
	public void testDefaultValues(){		
		Assert.assertNotNull(defaultValueAdapters);
		Assert.assertFalse(defaultValueAdapters.isEmpty());
		
		IDefaultValue dv =(IDefaultValue)possibleValueAdapters.get(0);
		Assert.assertNotNull(dv.getDefaultValue());

	}
	
	public void testCreateValues(){		
		Assert.assertNotNull(createValuesAdapters);
		Assert.assertTrue(createValuesAdapters.isEmpty());
	}
	
}
