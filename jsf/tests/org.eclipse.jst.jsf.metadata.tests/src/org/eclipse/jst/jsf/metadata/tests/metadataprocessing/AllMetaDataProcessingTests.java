package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllMetaDataProcessingTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test suite for org.eclipse.jst.jsf.metadata.tests.metadataprocessing");
		//$JUnit-BEGIN$
		suite.addTestSuite(AttributeValueRuntimeTypesRegistryTests.class);
		suite.addTestSuite(AttributeValueRuntimeTypeFactoryTests.class);
		suite.addTestSuite(MetaDataProcessorsFactoryTests.class);
		suite.addTestSuite(AttributeValueRuntimeTypeExtensionsTests.class);
		//$JUnit-END$
		return suite;
	}

}
