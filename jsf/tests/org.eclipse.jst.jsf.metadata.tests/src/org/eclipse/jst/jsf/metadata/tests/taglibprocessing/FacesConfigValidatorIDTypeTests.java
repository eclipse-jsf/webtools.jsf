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
		
		assertEquals(4, pv.getPossibleValues().size());

		assertPossibleValues(pv.getPossibleValues(),
			new String[]{
				"com.foo.myvalidator",
				"javax.faces.DoubleRange", 
				"javax.faces.Length",
				"javax.faces.LongRange"} );

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
