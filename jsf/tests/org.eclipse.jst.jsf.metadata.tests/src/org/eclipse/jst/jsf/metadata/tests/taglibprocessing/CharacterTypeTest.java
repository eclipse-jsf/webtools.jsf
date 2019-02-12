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
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class CharacterTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues() {
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertTrue(possibleValueAdapters.isEmpty());

	}

	public void testValidValuesValues() {
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());

		IValidValues pvs = (IValidValues) validValuesAdapters.get(0);
		Assert.assertTrue(pvs.isValidValue("a"));
		
		
		//negative 
		Assert.assertFalse(pvs.isValidValue("ab"));
		pvs.getValidationMessages().clear();
		Assert.assertFalse(pvs.isValidValue("_a"));
		pvs.getValidationMessages().clear();
		Assert.assertFalse(pvs.isValidValue(""));
		pvs.getValidationMessages().clear();
		Assert.assertFalse(pvs.isValidValue(" a "));
		pvs.getValidationMessages().clear();
	}
}
