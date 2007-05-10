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
