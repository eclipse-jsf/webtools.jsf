/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.metadata.tests.util.IJSFRuntimeRequiredV11;
import org.eclipse.jst.jsf.metadata.tests.util.IJSFRuntimeRequiredV12;

public class AllTests {

	private static boolean _inited;
	private static boolean _jsfRuntimePresentV11;
	private static boolean _jsfRuntimePresentV12;
	
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.jst.jsf.metadata.tests.taglibprocessing");
		//$JUnit-BEGIN$
		//Tests NOT requiring JSF Implementation
		addTestSuite(suite, StringTypeTest.class);
		addTestSuite(suite, BooleanTypeTest.class);
		addTestSuite(suite, IntegerTypeTest.class);
		addTestSuite(suite, EnumeratedIntegerTypeTest.class);
		addTestSuite(suite, LongTypeTest.class);
		addTestSuite(suite, EnumeratedLongTypeTest.class);
		addTestSuite(suite, DoubleTypeTest.class);
		addTestSuite(suite, EnumeratedDoubleTypeTest.class);
		
		addTestSuite(suite, ColorTypeTest.class);
		addTestSuite(suite, LanguageCodeTypeTest.class);
		addTestSuite(suite, LocaleTypeTest.class);
		addTestSuite(suite, CurrencyCodeTypeTest.class);
		addTestSuite(suite, TimeZoneTypeTest.class);
		addTestSuite(suite, LengthTypeTest.class);
		addTestSuite(suite, NumberPatternTypeTest.class);
//		addTestSuite(suite, RelativePathTypeTest.class);
//		addTestSuite(suite, WebPathTypeTest.class);
		addTestSuite(suite, CharacterTypeTest.class);
//		addTestSuite(suite, ScriptTypeTest.class);
		addTestSuite(suite, LinkTypeTest.class);
		addTestSuite(suite, ContentTypeTest.class);
//		addTestSuite(suite, URITypeTest.class);
		
		//Tests requiring JSF Implementation
		addTestSuite(suite, JavaClassTypeTest.class);
		addTestSuite(suite, FacesConfigConverterIDTypeTests.class);
		addTestSuite(suite, FacesConfigValidatorIDTypeTests.class);
		addTestSuite(suite, MethodBindingTypeTests.class);
		addTestSuite(suite, ComponentIDTypeTests.class);
		addTestSuite(suite, ComponentBindingTypeTests.class);
		addTestSuite(suite, ValueBindingTypeTests.class);
		addTestSuite(suite, ValueTypeTests.class);
		addTestSuite(suite, ActionTypeTests.class);
		
		addTestSuite(suite, CSSClassTypeTests.class);
		addTestSuite(suite, CSSStyleTypeTests.class);			
		
		addTestSuite(suite,JSFHTMLTestCase.class);
		
		//$JUnit-END$
		return suite;
	}
	private static void addTestSuite(TestSuite suite, Class<? extends TestCase> klass) {
		if (!_inited) {
			_jsfRuntimePresentV11 = JSFCoreUtilHelper.isJSFRuntimeJarsDirectoryPropertySet(JSFVersion.V1_1);
			_jsfRuntimePresentV12 = JSFCoreUtilHelper.isJSFRuntimeJarsDirectoryPropertySet(JSFVersion.V1_2);
			_inited = true;
		}
		if (IJSFRuntimeRequiredV11.class.isAssignableFrom(klass)){
			if (_jsfRuntimePresentV11)
				suite.addTestSuite(klass);
			else 
				System.err.println("Warning: JSF Runtime v1.1 is required and not present - "+klass.getName()+" test was skipped.");
		} else if (IJSFRuntimeRequiredV12.class.isAssignableFrom(klass)){
			if (_jsfRuntimePresentV12)
				suite.addTestSuite(klass);
			else 
				System.err.println("Warning: JSF Runtime v1.2 is required and not present - "+klass.getName()+" test was skipped.");
		} else {
			suite.addTestSuite(klass);
		}
	}
}
