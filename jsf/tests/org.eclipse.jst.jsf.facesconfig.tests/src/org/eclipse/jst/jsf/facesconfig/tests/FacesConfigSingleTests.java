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
 */
public class FacesConfigSingleTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Single Navigation Rule tests");
		
		//The following tests for single elements of the navigation-rule
		//(description, display-name, icon, from-action, to-view-id, redirect)
		suite.addTest(new TestSuite(FacesConfigFactoryImplForReadSingeNavigationRule.class));

		return suite;
	}
}
