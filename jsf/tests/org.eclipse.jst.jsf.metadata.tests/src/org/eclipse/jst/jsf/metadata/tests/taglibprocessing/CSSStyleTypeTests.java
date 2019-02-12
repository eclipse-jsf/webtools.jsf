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

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class CSSStyleTypeTests extends SingleJSPTestCase {
	private final String tagName = "commandButton";
	private final String attrName = "class";
	
	public CSSStyleTypeTests(){
		super(	"/testfiles/jsps/cssTypesTest.jsp.data/", 
				"/cssTypesTest.jsp", 
				JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	}

	public void testGetPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(pv);		
			
//		TODO: fill me in
	}

	public void testIsValidValue() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNull(vv); 		

		
	}
	


}
