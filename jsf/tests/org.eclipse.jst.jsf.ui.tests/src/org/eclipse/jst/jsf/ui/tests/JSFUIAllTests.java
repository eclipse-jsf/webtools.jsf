/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *
 ********************************************************************************/
package org.eclipse.jst.jsf.ui.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.ui.tests.jspeditor.TestELHyperlinkDetector;
import org.eclipse.jst.jsf.ui.tests.jspeditor.TestJSFELHover;

public class JSFUIAllTests {

	public static Test suite() {
		final TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.ui.tests");
		//$JUnit-BEGIN$
//		suite.addTestSuite(JSFLibrariesPreferencePageTestCases.class);
//		suite.addTestSuite(JSFLibraryWizardTestCases.class);
		suite.addTestSuite(TestELHyperlinkDetector.class);
		suite.addTestSuite(TestJSFELHover.class);
		//$JUnit-END$
		return suite;
	}

}
