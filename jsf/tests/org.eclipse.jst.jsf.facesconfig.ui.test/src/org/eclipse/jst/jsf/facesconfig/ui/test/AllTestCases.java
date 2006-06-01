/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.facesconfig.ui.section.ComponentsPageTest;
import org.eclipse.jst.jsf.facesconfig.ui.wizard.NewManagedBeanWizardTest;

/**
 * The test suite for faces config editor. Before run it, make sure that this
 * plugin is in the same folder with "org.eclipse.jst.jsf.facesconfig.ui"
 * plugin.
 * 
 * @author sfshi
 * 
 */
public class AllTestCases {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test Suite for FacesConfig Editor");

		suite.addTestSuite(OpenADFDemoFacesConfigTest.class);

		suite.addTestSuite(ComponentsPageTest.class);

		suite.addTestSuite(WebrootUtilTest.class);

		suite.addTestSuite(ManagedBeanUtilTest.class);

		suite.addTestSuite(JSPUtilTest.class);

		suite.addTestSuite(GEMPreferencesTest.class);

		suite.addTestSuite(NewManagedBeanWizardTest.class);

		suite.addTestSuite(PageflowEditorTest.class);
		return suite;
	}
}
