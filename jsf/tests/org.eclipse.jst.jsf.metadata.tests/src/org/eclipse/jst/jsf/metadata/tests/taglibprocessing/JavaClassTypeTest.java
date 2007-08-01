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

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.metadata.tests.util.IJSFRuntimeRequiredV11;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class JavaClassTypeTest extends SingleJSPTestCase implements IJSFRuntimeRequiredV11 {

	public JavaClassTypeTest() {
		super("/testfiles/jsps/javaClassType.jsp.data",
				"/javaClassType.jsp",
				IJSFCoreConstants.FACET_VERSION_1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
		

	}
	
	public void setUp() throws Exception {
		super.setUp();
		
		//add source files
		addJavaFile("MyConverter");
		addJavaFile("MyConverterSubclass1");
		addJavaFile("MyConverterSubclass2");
		addJavaFile("MyConverterSubclass3");
		addJavaFile("MySubclassOfMyConverterSubclass1");
		
	}
	
//	public void testSanity() {
//        final IJavaProject javaProject = _jdtTestEnv.getJavaProject(); 
//        assertNotNull(javaProject);
//        assertNotNull(_structuredDocument);
//	}

	public void testPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_CORE_URI, "actionListener", "type");		
		assertNotNull(pv);
		
		assertEquals(1, pv.getPossibleValues().size());
		assertPossibleValues(pv.getPossibleValues(),
				new String[]{
					"com.sun.faces.application.ActionListenerImpl"});
		
		pv = (IPossibleValues)getProcessor(IPossibleValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType1");
		assertNotNull(pv.getPossibleValues());
		assertTrue(pv.getPossibleValues().size() == 3);
		// all classes implement 
		assertPossibleValues(pv.getPossibleValues(),
			new String[]{
				"com.foo.MyConverterSubclass1",
				"com.foo.MyConverterSubclass2", 
				"com.foo.MySubclassOfMyConverterSubclass1"} );
	//Below is not currently working ... need to fix results with valid-superclass set
//		pv = (IPossibleValues)getProcessor(IPossibleValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType2");
//		assertNotNull(pv.getPossibleValues());
//		assertTrue(pv.getPossibleValues().size() == 5);
//		assertPossibleValues(pv.getPossibleValues(),
//			new String[]{
//				"com.foo.MyConverter",
//				"com.foo.MyConverterSubclass1",
//				"com.foo.MyConverterSubclass2", 
//				"com.foo.MyConverterSubclass3",
//				"com.foo.MySubclassOfMyConverterSubclass1"} );
		
		pv = (IPossibleValues)getProcessor(IPossibleValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType3");
		assertNotNull(pv.getPossibleValues());
		assertTrue(pv.getPossibleValues().size() == 3);
		assertPossibleValues(pv.getPossibleValues(),
			new String[]{
				"com.foo.MyConverterSubclass2",
				"com.foo.MyConverterSubclass3", 
				"com.foo.MySubclassOfMyConverterSubclass1"} );
		
	//Below is not currently working... need to fix results with multiple valid-interfaces set
//		pv = (IPossibleValues)getProcessor(IPossibleValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType4");
//		assertNotNull(pv.getPossibleValues());
//		assertTrue(pv.getPossibleValues().size() == 2);
//		assertPossibleValues(pv.getPossibleValues(),
//			new String[]{
//				"com.foo.MyConverterSubclass2", 
//				"com.foo.MySubclassOfMyConverterSubclass1"} );
	}
	


	public void testValidValues() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_CORE_URI, "actionListener", "type");
				
		assertNotNull(vv);
		assertFalse(vv.isValidValue("foobar"));
		assertTrue(vv.isValidValue("com.sun.faces.application.ActionListenerImpl"));
		
		//single interface
		vv = (IValidValues)getProcessor(IValidValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType1");
		assertNotNull(vv);
		assertFalse(vv.isValidValue("com.foo.MyConverter"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass1"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass2"));
		assertFalse(vv.isValidValue("com.foo.MyConverterSubclass3"));
		assertTrue(vv.isValidValue("com.foo.MySubclassOfMyConverterSubclass1"));
		
		//superclass test
		vv = (IValidValues)getProcessor(IValidValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType2");
		assertNotNull(vv);
		assertTrue(vv.isValidValue("com.foo.MyConverter"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass1"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass2"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass3"));
		assertTrue(vv.isValidValue("com.foo.MySubclassOfMyConverterSubclass1"));
		
		//superclass + single interface test
		vv = (IValidValues)getProcessor(IValidValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType3");
		assertNotNull(vv);
		assertFalse(vv.isValidValue("com.foo.MyConverter"));
		assertFalse(vv.isValidValue("com.foo.MyConverterSubclass1"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass2"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass3"));
		assertTrue(vv.isValidValue("com.foo.MySubclassOfMyConverterSubclass1"));
		
		//superclass + multiple interface test
		vv = (IValidValues)getProcessor(IValidValues.class, TAG_TEST_URI, TAG_TEST_TAG, "JavaClassType4");
		assertNotNull(vv);
		assertFalse(vv.isValidValue("com.foo.MyConverter"));
		assertFalse(vv.isValidValue("com.foo.MyConverterSubclass1"));
		assertTrue(vv.isValidValue("com.foo.MyConverterSubclass2"));
		assertFalse(vv.isValidValue("com.foo.MyConverterSubclass3"));
		assertTrue(vv.isValidValue("com.foo.MySubclassOfMyConverterSubclass1"));
		
	}
	
}
