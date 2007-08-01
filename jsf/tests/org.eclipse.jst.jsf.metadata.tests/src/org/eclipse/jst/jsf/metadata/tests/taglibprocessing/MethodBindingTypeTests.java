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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;

public class MethodBindingTypeTests extends SingleJSPTestCase {
	private final String tagName = "inputText";
	private final String attrName = "validator";
	
	public MethodBindingTypeTests() {
		super(	"/testfiles/jsps/facesConfigValidatorIDType.jsp.data/", 
				"/facesConfigValidatorIDType.jsp", 
				IJSFCoreConstants.FACET_VERSION_1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	}
	protected void setUp() throws Exception {
		super.setUp();
		
		addJavaFile("MyBean");
	}

	public void testGetExpectedRuntimeType() {
		IValidELValues vv = (IValidELValues)getProcessor(IValidELValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(vv);
		
		try {
			assertNotNull(vv.getExpectedRuntimeType());
			assertTrue(vv.getExpectedRuntimeType() instanceof CompositeType);
		} catch (Exception e) {
		}
		
	}

	public void testPossibleValues() {
		IPossibleValues vv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNull(vv); //possible value is not supported!

	}
	public void testIsValidValue() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(vv);
		
		assertFalse(vv.isValidValue("everything is invalid!"));
		assertNotNull(vv.getValidationMessages());
		assertEquals(1, vv.getValidationMessages().size());
		//throw in some coverage on IValidationMessage
		IValidationMessage msg = (IValidationMessage)vv.getValidationMessages().get(0);
		assertEquals("MethodBinding attribute values must be EL expressions.", msg.getMessage());
		assertNull(msg.getCode());
		assertEquals(IStatus.WARNING, msg.getSeverity());
		
		assertFalse(vv.isValidValue(""));
		

	}

}
