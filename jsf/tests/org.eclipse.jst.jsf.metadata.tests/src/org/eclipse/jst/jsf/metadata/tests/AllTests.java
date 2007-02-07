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
package org.eclipse.jst.jsf.metadata.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.common.metadata.tests.AllMetadataTests;
import org.eclipse.jst.jsf.common.metadata.tests.EntityImplTests;
import org.eclipse.jst.jsf.common.metadata.tests.IncludeEntityGroupImplTests;
import org.eclipse.jst.jsf.common.metadata.tests.MergeTests;
import org.eclipse.jst.jsf.common.metadata.tests.MetaDataQueryHelperTests;
import org.eclipse.jst.jsf.common.metadata.tests.ModelImplTests;
import org.eclipse.jst.jsf.common.metadata.tests.TraitImplTests;
import org.eclipse.jst.jsf.metadata.tests.annotations.AnnotationHelperTestCases;
import org.eclipse.jst.jsf.metadata.tests.annotations.AnnotationMapTestCases;
import org.eclipse.jst.jsf.metadata.tests.annotations.CaseInsensitiveAnnotationsTestCases;
import org.eclipse.jst.jsf.metadata.tests.annotations.DisableCMAnnotationFilesAPITest;
import org.eclipse.jst.jsf.metadata.tests.annotations.DuplicateAnnotationsTestCases;
import org.eclipse.jst.jsf.metadata.tests.annotations.NegativeAnnotationFileTestCases;
import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.AllMetaDataProcessingTests;
import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.AttributeValueRuntimeTypeExtensionsTests;
import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.AttributeValueRuntimeTypeFactoryTests;
import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.AttributeValueRuntimeTypesRegistryTests;
import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.MetaDataProcessorsFactoryTests;
import org.eclipse.jst.jsf.metadata.tests.taglibprocessing.BooleanTypeTest;
import org.eclipse.jst.jsf.metadata.tests.taglibprocessing.DoubleTypeTest;
import org.eclipse.jst.jsf.metadata.tests.taglibprocessing.IntegerTypeTest;
import org.eclipse.jst.jsf.metadata.tests.taglibprocessing.JavaClassTypeTest;
import org.eclipse.jst.jsf.metadata.tests.taglibprocessing.LongTypeTest;
import org.eclipse.jst.jsf.metadata.tests.taglibprocessing.StringTypeTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test Suite for org.eclipse.jst.jsf.metadata.tests");
		//$JUnit-BEGIN$
		
		//disablement of CMAnnotationFiles
		suite.addTestSuite(DisableCMAnnotationFilesAPITest.class);
		
		//model 
		suite.addTestSuite(ModelImplTests.class);
		suite.addTestSuite(EntityImplTests.class);
		suite.addTestSuite(TraitImplTests.class);
		suite.addTestSuite(IncludeEntityGroupImplTests.class);

//		//query - Main API
		suite.addTestSuite(MetaDataQueryHelperTests.class);
		
		//model merging
		suite.addTestSuite(MergeTests.class);
		
//		//metadataprocessing
		suite.addTestSuite(MetaDataProcessorsFactoryTests.class);
		suite.addTestSuite(AttributeValueRuntimeTypeExtensionsTests.class);
		suite.addTestSuite(AttributeValueRuntimeTypesRegistryTests.class);
		suite.addTestSuite(AttributeValueRuntimeTypeFactoryTests.class);
		
//		//taglib processing
		suite.addTestSuite(StringTypeTest.class);
		suite.addTestSuite(BooleanTypeTest.class);
		suite.addTestSuite(IntegerTypeTest.class);
		suite.addTestSuite(LongTypeTest.class);
		suite.addTestSuite(DoubleTypeTest.class);
		suite.addTestSuite(JavaClassTypeTest.class);

		//$JUnit-END$
		return suite;
	}

	/**
	 * Old tests that no longer need to run since moveing to ne metadata system
	 */
	public void Oldsuite() {
//		TestSuite suite = new TestSuite(
//				"Test for org.eclipse.jst.jsf.metadata.tests");
//		//$JUnit-BEGIN$
//		
//		//annotations 
//		suite.addTestSuite(AnnotationMapTestCases.class);
//		suite.addTestSuite(AnnotationHelperTestCases.class);
//		suite.addTestSuite(DuplicateAnnotationsTestCases.class);
//		suite.addTestSuite(CaseInsensitiveAnnotationsTestCases.class);
//		suite.addTestSuite(NegativeAnnotationFileTestCases.class);
//
//		//metadataprocessing
//		suite.addTestSuite(MetaDataProcessorsFactoryTests.class);
//		suite.addTestSuite(AttributeValueRuntimeTypeExtensionsTests.class);
//		suite.addTestSuite(AttributeValueRuntimeTypesRegistryTests.class);
//		suite.addTestSuite(AttributeValueRuntimeTypeFactoryTests.class);
//		
//		//taglib processing
//		suite.addTestSuite(StringTypeTest.class);
//		suite.addTestSuite(BooleanTypeTest.class);
//		suite.addTestSuite(IntegerTypeTest.class);
//		suite.addTestSuite(LongTypeTest.class);
//		suite.addTestSuite(DoubleTypeTest.class);
//		suite.addTestSuite(JavaClassTypeTest.class);

		//$JUnit-END$
//		return suite;
	}

}
