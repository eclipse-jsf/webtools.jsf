/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllDeprecatedMetadataTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Tests for org.eclipse.jst.jsf.common.metadata (deprecated)");
		//$JUnit-BEGIN$
		suite.addTestSuite(EmptyResultSetTest.class);
		suite.addTestSuite(MetaDataExceptionTest.class);
		suite.addTestSuite(AbstractMetaDataVisitorTest.class);
		suite.addTestSuite(AbstractEntityQueryVisitorTest.class);
		suite.addTestSuite(AbstractTraitQueryVisitorTest.class);
		suite.addTestSuite(TraitValueHelperTests.class);
		suite.addTestSuite(MetaDataQueryHelperTests.class);
		suite.addTestSuite(ModelProviderAdapterTests.class);
		
		suite.addTestSuite(TraitImplTests.class);
		suite.addTestSuite(IncludeEntityGroupImplTests.class);
		suite.addTestSuite(EntityImplTests.class);
		suite.addTestSuite(ModelImplTests.class);
		suite.addTestSuite(MergeTests.class);
		suite.addTestSuite(TinyTestTests.class);
		
		//$JUnit-END$
		return suite;
	}

}
