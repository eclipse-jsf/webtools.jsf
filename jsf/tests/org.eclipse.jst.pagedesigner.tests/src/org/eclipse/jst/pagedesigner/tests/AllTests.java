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
		addTestRequiringJSFRuntime(suite, Test_DTManager.class, "1.1");
		addTestRequiringJSFRuntime(suite, Test_TransformOperations.class, "1.1");

		//$JUnit-END$
		return suite;
	}

	private static void addTestRequiringJSFRuntime(TestSuite suite, Class testClass, String jsfVersion) {
		if (Utils.isJSFRuntimeJarsDirectoryValid(jsfVersion)) {
			suite.addTestSuite(testClass);
		} else {
			System.err.println(Utils.getTestRequiresJSFRuntimeMessage(testClass, jsfVersion));
		}
	}

}
