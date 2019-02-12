/*******************************************************************************
 * Copyright (c) 2007, 2008 Oracle Corporation and others.
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
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class CSSClassTypeTests extends SingleJSPTestCase {
	private final String tagName = "inputText";
	private final String attrName = "styleClass";
	
	public CSSClassTypeTests(){
		super(	"/testfiles/jsps/cssTypesTest.jsp.data/", 
				"/cssTypesTest.jsp", 
				JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	}

	public void setUp() throws Exception {
		super.setUp();
		_testEnv.loadResourceInWebRoot(MetadataTestsPlugin.getDefault().getBundle(),
                "/testfiles/jsps/mystyle.css.data", 
                "mystyle.css");
		
		_testEnv.loadResourceInWebRoot(MetadataTestsPlugin.getDefault().getBundle(),
                "/testfiles/jsps/foo.css.data", 
                "foo.css");
	}
	
	public void testGetPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNotNull(pv);		
		assertEquals(12, pv.getPossibleValues().size());		
	}

	public void testIsValidValue() {
		//valid values not currently supported
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_HTML_URI, tagName, attrName);		
		assertNull(vv); 		

	}
	


}
