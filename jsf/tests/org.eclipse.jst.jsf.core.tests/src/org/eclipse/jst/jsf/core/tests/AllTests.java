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

import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.J2EEModuleDependencyDelegateTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryConfigDialogSettingDataTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryConfigModelTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryRegistryUtilTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFProjectLibraryReferenceTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.ArchiveFileTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryRegistryPackageTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryRegistryTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.MaintainDefaultImplementationAdapterTestCases;
import org.eclipse.jst.jsf.core.tests.project.facet.JSFFacetInstallDataModelProviderTestCases;
import org.eclipse.jst.jsf.core.tests.types.TypeComparatorTests;
import org.eclipse.jst.jsf.core.tests.types.TypeTransformerTests;
import org.eclipse.jst.jsf.core.tests.util.TestJDTBeanIntrospector;
import org.eclipse.jst.jsf.core.tests.util.TestJDTBeanPropertyWorkingCopy;

/**
 * The full test suite for core.tests
 * @author cbateman
 *
 */
public class AllTests {

	/**
	 * @return the test suite
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.core.tests");
		//$JUnit-BEGIN$
        suite.addTestSuite(TestJDTBeanPropertyWorkingCopy.class);
        suite.addTestSuite(TestJDTBeanIntrospector.class);

		suite.addTestSuite(TypeComparatorTests.class);
		suite.addTestSuite(TypeTransformerTests.class);

		suite.addTestSuite(JSFLibraryRegistryTestCases.class);
		suite.addTestSuite(JSFLibraryTestCases.class);
		suite.addTestSuite(ArchiveFileTestCases.class);
		suite.addTestSuite(MaintainDefaultImplementationAdapterTestCases.class);
		suite.addTestSuite(JSFLibraryRegistryPackageTestCases.class);
		
		suite.addTestSuite(JSFFacetInstallDataModelProviderTestCases.class);
		
		suite.addTestSuite(J2EEModuleDependencyDelegateTestCases.class);
		suite.addTestSuite(JSFLibraryConfigDialogSettingDataTestCases.class);
		suite.addTestSuite(JSFProjectLibraryReferenceTestCases.class);
		suite.addTestSuite(JSFLibraryConfigModelTestCases.class);
		suite.addTestSuite(JSFLibraryRegistryUtilTestCases.class);
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
