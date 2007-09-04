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

import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class FacesConfigConverterIDTypeTests extends SingleJSPTestCase {

	public FacesConfigConverterIDTypeTests() {
		super(	"/testfiles/jsps/facesConfigConverterIDType.jsp.data/", 
				"/facesConfigConverterIDType.jsp", 
				JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		addJavaFile("MyConverter");
	}

//	public void testSanity() {
//        final IJavaProject javaProject = _jdtTestEnv.getJavaProject(); 
//        assertNotNull(javaProject);
//        assertNotNull(_structuredDocument);
//	}
	
	public void testPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_HTML_URI, "inputText", "converter");		
		assertNotNull(pv);
		
		assertEquals(13, pv.getPossibleValues().size());

		assertPossibleValues(pv.getPossibleValues(),
			new String[]{
				"com.foo.myconverter",
				"javax.faces.BigDecimal", 
				"javax.faces.BigInteger",
				"javax.faces.Byte",
				"javax.faces.Boolean",
				"javax.faces.Character",
				"javax.faces.DateTime",
				"javax.faces.Double",
				"javax.faces.Float",
				"javax.faces.Integer",
				"javax.faces.Number",
				"javax.faces.Long",
				"javax.faces.Short"} );

	}
	public void testGetExpectedRuntimeValue() {
		IValidELValues vv = (IValidELValues)getProcessor(IValidELValues.class, JSF_HTML_URI, "inputText", "converter");		
		assertNotNull(vv);
		
		try {
			assertNotNull(vv.getExpectedRuntimeType());
			assertTrue(vv.getExpectedRuntimeType() instanceof CompositeType);
		} catch (Exception e) {
		}	
	}
	
	public void testValidValues() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_HTML_URI, "inputText", "converter");		
		assertNotNull(vv);
		
		assertTrue(vv.isValidValue("com.foo.myconverter"));
		assertTrue(vv.isValidValue("javax.faces.BigDecimal"));
		assertTrue(vv.isValidValue("javax.faces.BigInteger"));
		assertTrue(vv.isValidValue("javax.faces.Byte"));
		assertTrue(vv.isValidValue("javax.faces.Boolean"));
		assertTrue(vv.isValidValue("javax.faces.Character"));
		assertTrue(vv.isValidValue("javax.faces.DateTime"));
		assertTrue(vv.isValidValue("javax.faces.Double"));
		assertTrue(vv.isValidValue("javax.faces.Float"));
		assertTrue(vv.isValidValue("javax.faces.Integer"));
		assertTrue(vv.isValidValue("javax.faces.Number"));
		assertTrue(vv.isValidValue("javax.faces.Long"));
		assertTrue(vv.isValidValue("javax.faces.Short"));
		
		assertFalse(vv.isValidValue("com.foo.myconvertersubclass1"));
	}
}
