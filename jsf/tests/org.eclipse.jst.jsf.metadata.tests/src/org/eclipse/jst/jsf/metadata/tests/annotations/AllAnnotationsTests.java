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

package org.eclipse.jst.jsf.metadata.tests.annotations;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * CMAnnotatations Metadata framework has been "taken out of service"
 * Please use org.eclipse.jst.jsf.common.metadata
 *
 */
public class AllAnnotationsTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.jst.jsf.metadata.tests.annotations");
		//$JUnit-BEGIN$
//		suite.addTestSuite(AnnotationMapTestCases.class);
//		suite.addTestSuite(DuplicateAnnotationsTestCases.class);
//		suite.addTestSuite(CaseInsensitiveAnnotationsTestCases.class);
//		suite.addTestSuite(AnnotationHelperTestCases.class);
//		suite.addTestSuite(NegativeAnnotationFileTestCases.class);
		//$JUnit-END$
		return suite;
	}

}
