/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/*import org.eclipse.jst.jsf.core.tests.annotations.AnnotationHelperTestCases;
import org.eclipse.jst.jsf.core.tests.annotations.AnnotationMapTestCases;
import org.eclipse.jst.jsf.core.tests.annotations.CaseInsensitiveAnnotationsTestCases;
import org.eclipse.jst.jsf.core.tests.annotations.DuplicateAnnotationsTestCases;
import org.eclipse.jst.jsf.core.tests.annotations.NegativeAnnotationFileTestCases;
*/
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryConfigModelAdapterTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryDecoratorTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.ArchiveFileTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryRegistryPackageTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryRegistryTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.MaintainDefaultImplementationAdapterTestCases;
import org.eclipse.jst.jsf.core.tests.project.facet.JSFFacetInstallDataModelProviderTestCases;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.core.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(JSFLibraryRegistryTestCases.class);
		suite.addTestSuite(JSFLibraryTestCases.class);
		suite.addTestSuite(ArchiveFileTestCases.class);
		suite.addTestSuite(MaintainDefaultImplementationAdapterTestCases.class);
		suite.addTestSuite(JSFLibraryRegistryPackageTestCases.class);
		
		suite.addTestSuite(JSFFacetInstallDataModelProviderTestCases.class);
		
		suite.addTestSuite(JSFLibraryConfigModelAdapterTestCases.class);
		suite.addTestSuite(JSFLibraryDecoratorTestCases.class);
		
		//annotations 
/*		suite.addTestSuite(AnnotationMapTestCases.class);
		suite.addTestSuite(AnnotationHelperTestCases.class);
		suite.addTestSuite(DuplicateAnnotationsTestCases.class);
		suite.addTestSuite(CaseInsensitiveAnnotationsTestCases.class);
		suite.addTestSuite(NegativeAnnotationFileTestCases.class);
*/
		//$JUnit-END$
		return suite;
	}
	
}
