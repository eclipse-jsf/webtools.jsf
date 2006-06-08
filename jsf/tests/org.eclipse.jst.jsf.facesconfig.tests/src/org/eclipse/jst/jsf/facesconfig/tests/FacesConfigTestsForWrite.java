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
public class FacesConfigTestsForWrite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test FacesConfig - writing");
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteNavigationRule.class,
				"Write navigation rule "));
		//FacesConfigFactoryImplForWriteFactory
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteFactory.class,
				"Write factory "));
		//FacesConfigFactoryImplForWriteApplication
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteApplication.class,
				"Write Application Test"));
		//FacesConfigFactoryImplForWriteLifecycle
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteLifecycle.class,
				"Write Lifectycle Test"));
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteComponent.class,
				"Write Component Test"));
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteValidator.class,
				"Write Validator Test"));		
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteManagedBean.class,
				"Write Managed-bean Test"));
		//FacesConfigFactoryImplForWriteConverter
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteConverter.class,
				"Write converter Test"));
		//FacesConfigFactoryImplForWriteRenderKit
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteRenderKit.class,
				"Write Render-kit Test"));
		//FacesConfigFactoryImplForWriteReferencedBean
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteReferencedBean.class,
				"Write ReferencedBean Test"));
		return suite;
	}
}
