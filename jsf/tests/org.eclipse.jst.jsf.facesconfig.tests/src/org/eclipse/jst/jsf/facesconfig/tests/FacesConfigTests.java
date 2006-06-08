/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Wrapper suite for all the tests against the .util package.
 * 
 * @author spaxton
 * @author le-ake m. G kristos
 */
public class FacesConfigTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test FacesConfig model");
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingeNavigationRule.class,
				"Non-Empty Navigation - Rule"));
		// This is to test config-files with no attributes
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadEmptyNavigationRule.class,
				"Empty Navigation rule"));
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadApplication.class,
				"Applicaiton Test"));

		suite.addTest(new TestSuite(MultipleFacesConfigFilesTest.class,
				"Multiple Facesconfig Files Support"));

		// FacesConfigElementsTest
		suite.addTest(new TestSuite(FacesConfigElementsTest.class,
				"Faces-config - Each Element"));

		// FacesConfigFactoryTest
		suite.addTest(new TestSuite(FacesConfigFactoryTest.class,
				"Facesconfig -Factory "));

		// FacesConfigFactoryImplForReadSingeNavigationCase
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingeNavigationCase.class,
				"Navigation-case Test"));

		// FacesConfigFactoryImplForReadSingleComponent
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleComponent.class,
				"Component Test - Single"));

		// FacesConfigFactoryImplForReadSingleConverter
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleConverter.class,
				"Converter Test - Single"));

		// FacesConfigFactoryImplForReadSingleManagedBean
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleManagedBean.class,
				"Managed-Bean Test - Single"));

		// FacesConfigFactoryImplForReadSingleReferencedBean
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleReferencedBean.class,
				"Referenced-Bean Test - Single"));

		// FacesConfigFactoryImplForReadSingleRenderKit
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleRenderKit.class,
				"Render-kit Test - Single"));

		// FacesConfigFactoryImplForReadSingleLifecycle
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleLifecycle.class,
				"Lifecycle Test - Single"));

		// FacesConfigFactoryImplForReadSingleValidator
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleValidator.class,
				"Validator Test - Single"));

		// FacesConfigFactoryImplForReadRenderer
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadRenderer.class,
				"Renderer Test - Single"));

		// FacesConfigFactoryImplForReadAttributeComponent
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadAttributeComponent.class,
				"Component-->Attribute Test - Single"));

		// FacesConfigFactoryImplForReadAttributeConverter
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadAttributeConverter.class,
				"Converter-->Attribute Test - Single"));

		// FacesConfigFactoryImplForReadAttributeValidator
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadAttributeValidator.class,
				"Validator-->Attribute Test - Single"));

		// FacesConfigFactoryImplForReadSingleFactory
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadSingleFactory.class,
				"Factory Test - Single"));

		// FacesConfigFactoryImplForReadManagedBeanManagedProperty
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForReadManagedBeanManagedProperty.class,
				"Managed-property - Single"));
	
		
		//FacecConfigFactoryImplForWriteRenderKi
		return suite;
	}
}
