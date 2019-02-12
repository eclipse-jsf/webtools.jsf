/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
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

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class ComponentIDTypeTests extends SingleJSPTestCase {
	
	private final String tagName = "commandButton";
	private final String attrName = "id";
	
	public ComponentIDTypeTests() {
		super(	"/testfiles/jsps/facesConfigValidatorIDType.jsp.data/", 
				"/facesConfigValidatorIDType.jsp", 
				JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	}
	protected void setUp() throws Exception {
		super.setUp();
		
//		addJavaFile("MyBean");
	}

	public void testGetExpectedRuntimeType() {
		IValidELValues vv = (IValidELValues)getProcessor(IValidELValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(vv);
		
		try {
			assertNotNull(vv.getExpectedRuntimeType());
			fail("Expected ELIsNotValidException");
		} catch (ELIsNotValidException e) {			
		}
		
	}

	public void testPossibleValues() {
		IPossibleValues vv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNull(vv); //possible value is not supported!

	}
	
	public void testIsValidValue() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(vv);
//		
//		assertTrue(vv.isValidValue("every non zero length string is valid!"));
//		assertNotNull(vv.getValidationMessages());
//		assertEquals(0, vv.getValidationMessages().size());
//		assertFalse(vv.isValidValue(""));

		//positive tests
		Assert.assertTrue(vv.isValidValue("A"));
		Assert.assertTrue(vv.isValidValue("Aa"));
		Assert.assertTrue(vv.isValidValue("z.abc"));
		Assert.assertTrue(vv.isValidValue("A1"));
		Assert.assertTrue(vv.isValidValue("A-1"));
		Assert.assertTrue(vv.isValidValue("A_a"));
		Assert.assertTrue(vv.isValidValue("A:a"));
		Assert.assertTrue(vv.isValidValue("A_"));
		Assert.assertTrue(vv.isValidValue("a."));
		Assert.assertTrue(vv.isValidValue("Aa."));		
		Assert.assertTrue(vv.isValidValue("_Aa"));	
		
		//negative tests
		Assert.assertFalse(vv.isValidValue("  "));	
		vv.getValidationMessages().clear();
		
		//will fail when we have proper regex validation
//		Assert.assertFalse(vv.isValidValue("1Aa"));	
//		vv.getValidationMessages().clear();
//		Assert.assertFalse(vv.isValidValue(":Aa"));	
//		vv.getValidationMessages().clear();
//		Assert.assertFalse(vv.isValidValue("-Aa"));	
//		vv.getValidationMessages().clear();
//		Assert.assertFalse(vv.isValidValue(".Aa"));	
//		vv.getValidationMessages().clear();	
		
		// FIXME the below is currently failing and is commented out.   
//		Assert.assertFalse(vv.isValidValue("A$!a"));
//		vv.getValidationMessages().clear();
//		Assert.assertFalse(vv.isValidValue("A:!a"));					
//		vv.getValidationMessages().clear();
//		Assert.assertFalse(vv.isValidValue("A(a"));	
//		vv.getValidationMessages().clear();
	}

}
