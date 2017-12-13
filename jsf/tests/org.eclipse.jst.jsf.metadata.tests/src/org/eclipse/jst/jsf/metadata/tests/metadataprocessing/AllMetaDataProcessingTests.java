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
