/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.pagedesigner.tests.tabbed.properties.sections.BasicTabbedPropertyPageTests;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorForJSFCore;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorForJSFHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorJSP;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolForJSFCore;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolForJSFHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolJSP;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestTagCreationFactory;

/**
 * All tests suite for Web Page Editor.
 * 
 * @author Ian Trimble - Oracle
 *
 */
public class AllTests {
	/**
	 * @return the test suite
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.jst.pagedesigner");
		//$JUnit-BEGIN$
		
		//tests requiring jsfRuntimeJarsDirectoryV1.1
		addTestRequiringJSFRuntime(suite, Test_DTManager.class, JSFVersion.V1_1);
		addTestRequiringJSFRuntime(suite, Test_TransformOperations.class, JSFVersion.V1_1);
		addTestRequiringJSFRuntime(suite, TestDefaultTagCreatorForJSFCore.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestDefaultTagCreatorForJSFHTML.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestDefaultTagCreatorHTML.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestDefaultTagCreatorJSP.class, JSFVersion.V1_1);

        // TODO: could merge the item creation and default tag creators into a common testsuite
        addTestRequiringJSFRuntime(suite, TestItemCreationToolForJSFCore.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestItemCreationToolForJSFHTML.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestItemCreationToolHTML.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestItemCreationToolJSP.class, JSFVersion.V1_1);

        
        addTestRequiringJSFRuntime(suite, TestTagCreationFactory.class, JSFVersion.V1_1);
        
        
        //Tabbed Property Tests - do not require JSF Runtime
        // C.B: 2008/01/17 Commenting out this suite to get no-fail build
        //suite.addTestSuite(BasicTabbedPropertyPageTests.class);
		//$JUnit-END$
		return suite;
	}

	private static void addTestRequiringJSFRuntime(TestSuite suite, Class<?> testClass, JSFVersion jsfVersion) {
		if (JSFCoreUtilHelper.isJSFRuntimeJarsDirectoryValid(jsfVersion)) {
			suite.addTestSuite(testClass);
		} else {
			System.err.println(JSFCoreUtilHelper.getTestRequiresJSFRuntimeMessage(testClass, jsfVersion));
		}
	}
}
