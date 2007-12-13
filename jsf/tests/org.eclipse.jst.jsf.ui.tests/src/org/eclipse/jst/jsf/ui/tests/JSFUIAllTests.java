/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *
 ********************************************************************************/
package org.eclipse.jst.jsf.ui.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.ui.tests.classpath.JSFLibrariesPreferencePageTestCases;
import org.eclipse.jst.jsf.ui.tests.classpath.JSFLibraryWizardTestCases;
import org.eclipse.jst.jsf.ui.tests.jspeditor.TestELHyperlinkDetector;
import org.eclipse.jst.jsf.ui.tests.jspeditor.TestJSFELHover;

public class JSFUIAllTests {

	public static Test suite() {
		final TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.ui.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(JSFLibrariesPreferencePageTestCases.class);
		suite.addTestSuite(JSFLibraryWizardTestCases.class);
		suite.addTestSuite(TestELHyperlinkDetector.class);
		suite.addTestSuite(TestJSFELHover.class);
		//$JUnit-END$
		return suite;
	}

}
