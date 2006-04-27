package org.eclipse.jst.jsf.ui.tests;

import org.eclipse.jst.jsf.ui.tests.classpath.JSFLibrariesPreferencePageTestCases;
import org.eclipse.jst.jsf.ui.tests.classpath.JSFLibraryWizardTestCases;

import junit.framework.Test;
import junit.framework.TestSuite;

public class JSFUIAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.ui.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(JSFLibrariesPreferencePageTestCases.class);
		suite.addTestSuite(JSFLibraryWizardTestCases.class);
				
		//$JUnit-END$
		return suite;
	}	
	
}
