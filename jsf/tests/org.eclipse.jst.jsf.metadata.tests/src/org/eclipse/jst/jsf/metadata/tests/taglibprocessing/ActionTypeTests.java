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

public class ActionTypeTests extends SingleJSPTestCase {
	private final String tagName = "commandButton";
	private final String attrName = "action";
	
	public ActionTypeTests(){
		super(	"/testfiles/jsps/actionTypeTest.jsp.data/", 
				"/actionTypeTest.jsp", 
				IJSFCoreConstants.FACET_VERSION_1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	}

	public void testGetPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(pv);		
		
		assertEquals(2, pv.getPossibleValues().size());
		assertPossibleValues(pv.getPossibleValues(), new String[] {
			"gotoWelcome",
			"gotoSomeplaceElse"});
			
	}

	public void testIsValidValue() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(vv); 		

		assertTrue(vv.isValidValue("gotoWelcome"));
		assertTrue(vv.isValidValue("gotoSomeplaceElse"));
		assertFalse(vv.isValidValue("gotoSomeplace")); 
		assertEquals(1, vv.getValidationMessages().size());
		IValidationMessage msg = (IValidationMessage)vv.getValidationMessages().get(0);
		assertEquals(IStatus.WARNING, msg.getSeverity());
		assertEquals("The action value does not match a navigation case outcome.", msg.getMessage());
		vv.getValidationMessages().clear();
		assertFalse(vv.isValidValue(""));
		msg = (IValidationMessage)vv.getValidationMessages().get(0);
		assertEquals(IStatus.WARNING, msg.getSeverity());
		assertEquals("The action attribute must be a non-zero length String or a method binding matching a navigation case outcome.", msg.getMessage());
		
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


}
