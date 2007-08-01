/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
 
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class ComponentIDTypeTests extends SingleJSPTestCase {
	private final String tagName = "inputText";
	private final String attrName = "id";
	
	public ComponentIDTypeTests() {
		super(	"/testfiles/jsps/facesConfigValidatorIDType.jsp.data/", 
				"/facesConfigValidatorIDType.jsp", 
				IJSFCoreConstants.FACET_VERSION_1_1,
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
		
		assertTrue(vv.isValidValue("every non zero length string is valid!"));
		assertNotNull(vv.getValidationMessages());
		assertEquals(0, vv.getValidationMessages().size());
		assertFalse(vv.isValidValue(""));

	}

}
