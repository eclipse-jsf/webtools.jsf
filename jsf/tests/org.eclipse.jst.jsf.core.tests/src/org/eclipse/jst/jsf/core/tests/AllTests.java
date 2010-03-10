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

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.tests.appconfig.validation.AppConfigValidationUtilTestCase;
import org.eclipse.jst.jsf.core.tests.facet.JsfLibraryProviderTests;
import org.eclipse.jst.jsf.core.tests.facet.JsfLibraryValidatorTest;
import org.eclipse.jst.jsf.core.tests.facet.VendorSpecificWebXmlConfigurationForJ2EETest;
import org.eclipse.jst.jsf.core.tests.facet.VendorSpecificWebXmlConfigurationForJavaEETest;
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
import org.eclipse.jst.jsf.core.tests.validation.TestJSPSemanticsValidator_AttributeValues;
import org.eclipse.jst.jsf.core.tests.validation.TestJSPSemanticsValidator_Containment;

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
        suite.addTest(new JUnit4TestAdapter(TestJDTBeanPropertyWorkingCopy.class));
        suite.addTest(new JUnit4TestAdapter(TestJDTBeanIntrospector.class));
        suite.addTest(new JUnit4TestAdapter(TestTypeUtil.class));

		suite.addTest(new JUnit4TestAdapter(TypeComparatorTests.class));
		suite.addTest(new JUnit4TestAdapter(TypeTransformerTests.class));

		suite.addTest(new JUnit4TestAdapter(JSFLibraryRegistryTestCases.class));
		suite.addTest(new JUnit4TestAdapter(JSFLibraryTestCases.class));
		suite.addTest(new JUnit4TestAdapter(ArchiveFileTestCases.class));
		suite.addTest(new JUnit4TestAdapter(MaintainDefaultImplementationAdapterTestCases.class));
		suite.addTest(new JUnit4TestAdapter(JSFLibraryRegistryPackageTestCases.class));

		// comment out temporarily.  See https://bugs.eclipse.org/bugs/show_bug.cgi?id=214417
		//suite.addTest(new JUnit4TestAdapter(TestLifecycleListener.class));

		suite.addTest(new JUnit4TestAdapter(JSFFacetInstallDataModelProviderTestCases.class));

		// test the jar file appconfig provider
		// TODO C.B: add this back suite.addTest(TestJARFileJSFAppConfigProvider.class);
		
//		suite.addTest(JSFLibraryConfigDialogSettingDataTestCases.class);
		suite.addTest(new JUnit4TestAdapter(JSFProjectLibraryReferenceTestCases.class));
//		suite.addTest(JSFLibraryConfigModelTestCases.class);
		suite.addTest(new JUnit4TestAdapter(JSFLibraryRegistryUtilTestCases.class));
		suite.addTest(new JUnit4TestAdapter(JsfLibraryValidatorTest.class));

		suite.addTest(new JUnit4TestAdapter(TestJSPSemanticsValidator_Containment.class));
        suite.addTest(new JUnit4TestAdapter(TestJSPSemanticsValidator_AttributeValues.class));
		suite.addTest(new JUnit4TestAdapter(AppConfigValidationUtilTestCase.class));
		suite.addTest(new JUnit4TestAdapter(TestMemberConstraint.class));
		suite.addTest(new JUnit4TestAdapter(TestXPathValidation.class));
		suite.addTest(new JUnit4TestAdapter(TestXPathTagMatcher.class));
		suite.addTest(new JUnit4TestAdapter(ConcreteAxiomaticSetTest.class));
		suite.addTest(new JUnit4TestAdapter(NodeSetTest.class));
		
        // NOTE: migration tests affect workspace meta-data files, but they
        // should play nice with others
		// BUT, to be on the safe side, always run them LAST.
        suite.addTest(new JUnit4TestAdapter(MigrationV1toV2Test.class));

        suite.addTest(new JUnit4TestAdapter(JSFLibraryReferenceTestCases.class));
        suite.addTest(new JUnit4TestAdapter(JSFLibraryServerSuppliedReferenceTestCases.class));
        
        // Vendor-specific web.xml configuration tests
        suite.addTest(new JUnit4TestAdapter(VendorSpecificWebXmlConfigurationForJavaEETest.class));
        suite.addTest(new JUnit4TestAdapter(VendorSpecificWebXmlConfigurationForJ2EETest.class));

        // Jsf library provider tests
        suite.addTest(new JUnit4TestAdapter(JsfLibraryProviderTests.class));

		//$JUnit-END$
		return suite;
	}
}
