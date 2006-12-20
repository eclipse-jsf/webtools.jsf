/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;


import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Wrapper suite for all the tests against the .util package.
 * 
 * @author spaxton
 * @author le-ake m. G kristos
 */
public class AllWriteTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test FacesConfig - writing");

        suite.addTest(new TestSuite(
                CommonStructuresUtil.class,
                "Sanity Test"));
        suite.addTest(new TestSuite(
                WriteApplicationTestCase.class,
                "Write application"));
        suite.addTest(new TestSuite(
                WriteComponentTestCase.class,
                "Write Component Test"));
        suite.addTest(new TestSuite(
                WriteConverterTestCase.class,
                "Write converter Test"));
        suite.addTest(new TestSuite(
                WriteFactoryTestCase.class,
                "Write factory "));
        suite.addTest(new TestSuite(
                WriteLifecycleTestCase.class,
                "Write Lifecycle Test"));        
        suite.addTest(new TestSuite(
                WriteManagedBeanTestCase.class,
                "Write Managed-bean Test"));
        suite.addTest(new TestSuite(
				WriteNavigationRuleTestCase.class,
				"Write navigation rule "));
        suite.addTest(new TestSuite(
                WriteRenderKitTestCase.class,
                "Write Render-kit Test"));
        suite.addTest(new TestSuite(
                WriteReferencedBeanTestCase.class,
                "Write ReferencedBean Test"));
		suite.addTest(new TestSuite(
				WriteValidatorTestCase.class,
				"Write Validator Test"));		

		return suite;
	}
}
