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

import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class FacesConfigValidatorIDTypeTests extends SingleJSPTestCase {
	private final String tagName = "validator";
	private final String attrName = "validatorId";
	
	public FacesConfigValidatorIDTypeTests() {
		super(	"/testfiles/jsps/facesConfigValidatorIDType.jsp.data/", 
				"/facesConfigValidatorIDType.jsp", 
				JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		addJavaFile("MyValidator");
	}

//	public void testSanity() {
//        final IJavaProject javaProject = _jdtTestEnv.getJavaProject(); 
//        assertNotNull(javaProject);
//        assertNotNull(_structuredDocument);
//	}
	
	public void testPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_CORE_URI, tagName, attrName);		
		assertNotNull(pv);
		
		assertEquals(7, pv.getPossibleValues().size());

		assertPossibleValues(pv.getPossibleValues(),
			new String[]{
				"com.foo.myvalidator",
				"javax.faces.DoubleRange", 
				"javax.faces.Length",
				"javax.faces.LongRange",
				"javax.faces.Bean",
				"javax.faces.RegularExpression",
				"javax.faces.Required"} );

	}
	
	public void testGetExpectedRuntimeValue() {
		IValidELValues vv = (IValidELValues)getProcessor(IValidELValues.class, JSF_CORE_URI, tagName, attrName);	
		assertNotNull(vv);
		
		try {
			assertNotNull(vv.getExpectedRuntimeType());
			assertTrue(vv.getExpectedRuntimeType() instanceof CompositeType);
		} catch (Exception e) {
		}	
	}
	
	public void testValidValues() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_CORE_URI, tagName, attrName);		
		assertNotNull(vv);
		
		assertTrue(vv.isValidValue("com.foo.myvalidator"));
		assertTrue(vv.isValidValue("javax.faces.DoubleRange"));
		assertTrue(vv.isValidValue("javax.faces.Length"));
		assertTrue(vv.isValidValue("javax.faces.LongRange"));
		
		assertFalse(vv.isValidValue("com.foo.myconverter"));
	}
}
