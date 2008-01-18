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

import org.eclipse.jst.jsf.core.tests.appconfig.validation.AppConfigValidationUtilTestCase;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryConfigDialogSettingDataTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryConfigModelTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFLibraryRegistryUtilTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfig.JSFProjectLibraryReferenceTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfiguration.JSFLibraryReferenceTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryconfiguration.JSFLibraryServerSuppliedReferenceTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.ArchiveFileTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryRegistryPackageTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryRegistryTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.JSFLibraryTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.MaintainDefaultImplementationAdapterTestCases;
import org.eclipse.jst.jsf.core.tests.jsflibraryregistry.migration.MigrationV1toV2Test;
import org.eclipse.jst.jsf.core.tests.project.facet.JSFFacetInstallDataModelProviderTestCases;
import org.eclipse.jst.jsf.core.tests.set.ConcreteAxiomaticSetTest;
import org.eclipse.jst.jsf.core.tests.set.NodeSetTest;
import org.eclipse.jst.jsf.core.tests.set.TestMemberConstraint;
import org.eclipse.jst.jsf.core.tests.set.TestXPathValidation;
import org.eclipse.jst.jsf.core.tests.tagmatcher.TestXPathTagMatcher;
import org.eclipse.jst.jsf.core.tests.types.TypeComparatorTests;
import org.eclipse.jst.jsf.core.tests.types.TypeTransformerTests;
import org.eclipse.jst.jsf.core.tests.util.TestJDTBeanIntrospector;
import org.eclipse.jst.jsf.core.tests.util.TestJDTBeanPropertyWorkingCopy;
import org.eclipse.jst.jsf.core.tests.util.TestTypeUtil;
import org.eclipse.jst.jsf.core.tests.validation.TestJSPSemanticsValidator;

/**
 * The full test suite for core.tests
 * @author cbateman
 *
 */
public class AllTests 
{
	/**
	 * @return the test suite
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.core.tests");
		//$JUnit-BEGIN$
        suite.addTestSuite(TestJDTBeanPropertyWorkingCopy.class);
        suite.addTestSuite(TestJDTBeanIntrospector.class);
        suite.addTestSuite(TestTypeUtil.class);

		suite.addTestSuite(TypeComparatorTests.class);
		suite.addTestSuite(TypeTransformerTests.class);

		suite.addTestSuite(JSFLibraryRegistryTestCases.class);
		suite.addTestSuite(JSFLibraryTestCases.class);
		suite.addTestSuite(ArchiveFileTestCases.class);
		suite.addTestSuite(MaintainDefaultImplementationAdapterTestCases.class);
		suite.addTestSuite(JSFLibraryRegistryPackageTestCases.class);

		// comment out temporarily.  See https://bugs.eclipse.org/bugs/show_bug.cgi?id=214417
		//suite.addTestSuite(TestLifecycleListener.class);

		suite.addTestSuite(JSFFacetInstallDataModelProviderTestCases.class);

		// test the jar file appconfig provider
		// TODO C.B: add this back suite.addTestSuite(TestJARFileJSFAppConfigProvider.class);
		
		suite.addTestSuite(JSFLibraryConfigDialogSettingDataTestCases.class);
		suite.addTestSuite(JSFProjectLibraryReferenceTestCases.class);
		suite.addTestSuite(JSFLibraryConfigModelTestCases.class);
		suite.addTestSuite(JSFLibraryRegistryUtilTestCases.class);

		suite.addTestSuite(TestJSPSemanticsValidator.class);
		suite.addTestSuite(AppConfigValidationUtilTestCase.class);
		suite.addTestSuite(TestMemberConstraint.class);
		suite.addTestSuite(TestXPathValidation.class);
		suite.addTestSuite(TestXPathTagMatcher.class);
		suite.addTestSuite(ConcreteAxiomaticSetTest.class);
		suite.addTestSuite(NodeSetTest.class);
		
        // NOTE: migration tests affect workspace meta-data files, but they
        // should play nice with others
		// BUT, to be on the safe side, always run them LAST.
        suite.addTestSuite(MigrationV1toV2Test.class);

        suite.addTestSuite(JSFLibraryReferenceTestCases.class);
        suite.addTestSuite(JSFLibraryServerSuppliedReferenceTestCases.class);
        //
		//$JUnit-END$
		return suite;
	}
}
