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

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.jst.jsf.metadata.tests.taglibprocessing");
		//$JUnit-BEGIN$
		suite.addTestSuite(StringTypeTest.class);
		suite.addTestSuite(BooleanTypeTest.class);
		suite.addTestSuite(IntegerTypeTest.class);
		suite.addTestSuite(LongTypeTest.class);
		suite.addTestSuite(DoubleTypeTest.class);
		suite.addTestSuite(JavaClassTypeTest.class);
		suite.addTestSuite(JSFHTMLTestCase.class);
		//$JUnit-END$
		return suite;
	}

}
