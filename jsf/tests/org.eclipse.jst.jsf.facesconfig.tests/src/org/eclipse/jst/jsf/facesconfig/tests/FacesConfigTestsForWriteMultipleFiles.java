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
public class FacesConfigTestsForWriteMultipleFiles {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test FacesConfig - writing to MULTIPLE FILes");
		
		//add all the test suits here
		
		//FacesConfigFactoryImplForWriteRenderKitTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteRenderKitTwoFiles.class,
				"Write TWO files Render-kit Test"));
		
		//FacesConfigFactoryImplForWriteLifecycleTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteLifecycleTwoFiles.class,
				"Write TWO files Lifecycle Test"));
		
		//FacesConfigFactoryImplForWriteReferencedBeanTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteReferencedBeanTwoFiles.class,
				"Write TWO files Referenced Bean Test"));
		
		//FacesConfigFactoryImplForWriteValidatorTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteValidatorTwoFiles.class,
				"Write TWO files Validator Test"));
		
		//FacesConfigFactoryImplForWriteApplicationTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteApplicationTwoFiles.class,
				"Write TWO files Application Test"));
		//FacesConfigFactoryImplForWriteFactoryTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteFactoryTwoFiles.class,
				"Write TWO files config factory Test"));
		//FacesConfigFactoryImplForWriteComponentTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteComponentTwoFiles.class,
				"Write TWO files Component Test"));		
		
		//FacesConfigFactoryImplForWriteConverterTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteConverterTwoFiles.class,
				"Write TWO files Converter Test"));		
		
		//FacesConfigFactoryImplForWriteNavigationRuleTwoFiles
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteNavigationRuleTwoFiles.class,
				"Write TWO files Nav - Rules Test"));		
		//testWriteManagedBeanToFileOne
		suite.addTest(new TestSuite(
				FacesConfigFactoryImplForWriteManagedBeanTwoFiles.class,
				"Write TWO files Managed - Bean Test"));		
		return suite;
	}
}
