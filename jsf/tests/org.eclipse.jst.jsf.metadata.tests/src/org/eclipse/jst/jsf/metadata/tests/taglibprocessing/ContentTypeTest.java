/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class ContentTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues() {
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertFalse(possibleValueAdapters.isEmpty());

		IPossibleValues pvs = (IPossibleValues) possibleValueAdapters.get(0);
		Assert.assertTrue(pvs.getPossibleValues().size() >= 8);
	}

	public void testValidValuesValues() {
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues pvs = (IValidValues) validValuesAdapters.get(0);
		Assert.assertTrue(pvs.isValidValue("a"));
		Assert.assertTrue(pvs.isValidValue("foo_bar"));
		
		//negative
		Assert.assertFalse(pvs.isValidValue(""));
		pvs.getValidationMessages().clear();
		Assert.assertFalse(pvs.isValidValue("          "));
		pvs.getValidationMessages().clear();
		Assert.assertFalse(pvs.isValidValue(null));
		pvs.getValidationMessages().clear();
	}
}
