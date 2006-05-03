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
		//$JUnit-END$
		return suite;
	}

}
