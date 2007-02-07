package org.eclipse.jst.jsf.common.metadata.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllMetadataTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.jst.jsf.common.metadata.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(TraitImplTests.class);
		suite.addTestSuite(IncludeEntityGroupImplTests.class);
		suite.addTestSuite(EntityImplTests.class);
		suite.addTestSuite(MetaDataQueryHelperTests.class);
		suite.addTestSuite(ModelImplTests.class);
		//$JUnit-END$
		return suite;
	}

}
