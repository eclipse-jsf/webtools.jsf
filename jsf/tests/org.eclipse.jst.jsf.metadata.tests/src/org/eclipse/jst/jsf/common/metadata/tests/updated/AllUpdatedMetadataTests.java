/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests.updated;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllUpdatedMetadataTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for updated org.eclipse.jst.jsf.common.metadata.tests");
		//$JUnit-BEGIN$
		//model
		suite.addTestSuite(ModelImplTests.class);
		suite.addTestSuite(EntityImplTests.class);
		suite.addTestSuite(TraitImplTests.class);
		suite.addTestSuite(IncludeEntityGroupImplTests.class);

		suite.addTestSuite(MergeTests.class);
		suite.addTestSuite(TinyTestTests.class);

		suite.addTestSuite(MetaDataModelManagerFactoryTests.class);
		suite.addTestSuite(ModelProviderAdapterTests.class);
		
		//query
		suite.addTestSuite(EmptyResultSetTests.class);
		suite.addTestSuite(MetaDataExceptionTests.class);
		suite.addTestSuite(AbstractMetaDataVisitorTests.class);
		suite.addTestSuite(AbstractEntityQueryVisitorTests.class);
		suite.addTestSuite(AbstractTraitQueryVisitorTests.class);
		suite.addTestSuite(TraitValueHelperTests.class);
		suite.addTestSuite(MetaDataQueryFactoryTests.class);
		suite.addTestSuite(MetaDataQueryContextFactoryTests.class);
		suite.addTestSuite(MetaDataQueryHelperTests.class);
		
		suite.addTestSuite(MissingMDExtensionModelTests.class);
		
		//taglib query
		suite.addTestSuite(TaglibMetaDataQueryTests.class);
		
		//$JUnit-END$
		return suite;
	}

}
