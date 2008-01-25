/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.designtime.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * The test suite for all tests on jsf.designtime
 * 
 * @author cbateman
 * 
 */
public class AllTests {
//    private static boolean _inited;
//    private static boolean _jsfRuntimePresentV11;
//    private static boolean _jsfRuntimePresentV12;

    /**
     * @return the all tests suite
     */
    public static Test suite() {
        // tests tied to runtimes
//        if (!_inited) {
//            _jsfRuntimePresentV11 = JSFCoreUtilHelper
//                    .isJSFRuntimeJarsDirectoryPropertySet(JSFVersion.V1_1);
//            _jsfRuntimePresentV12 = JSFCoreUtilHelper
//                    .isJSFRuntimeJarsDirectoryPropertySet(JSFVersion.V1_2);
//            final boolean oneIsPresent = 
//                (_jsfRuntimePresentV11 || _jsfRuntimePresentV12);
//            final boolean bothArePresent = 
//                (_jsfRuntimePresentV11 && _jsfRuntimePresentV12);
//
//            if (!oneIsPresent) {
//                final TestSuite suite = new TestSuite(
//                        "Error: JSF runtimes not found");
//
//                suite.addTestSuite(ErrorTestCase.class);
//
//                return suite;
//            }
//
//            if (!bothArePresent) {
//                System.err.println
//                  ("Warning: only one of JSF 1.1 and 1.2 runtimes are present");
//            }
//            _inited = true;
//        }

        final TestSuite suite = new TestSuite(
                "Test for org.eclipse.jst.jsf.designtime.tests");
        // $JUnit-BEGIN$
        suite.addTestSuite(TestDefaultBeanSymbolSourceProvider.class);
        suite.addTestSuite(TestResourceBundleMapSource.class);
        suite.addTestSuite(TestDefaultPropertyResolver.class);
        suite.addTestSuite(TestAbstractDataModelVariableFactory.class);
        suite.addTestSuite(TestDefaultDTMethodResolver.class);
        suite.addTestSuite(TestDefaultDTVariableResolver.class);
        suite.addTestSuite(TestDTJSPExternalContext.class);
        suite.addTestSuite(TestJSPDefaultSymbolFactory.class);
        suite.addTestSuite(TestJSPModelProcessor.class);
        suite.addTestSuite(TestStartupHandler.class);
        suite.addTestSuite(TestDesignTimeApplicationManager.class);

        //addTestSuite(suite, JSPViewSuite.class);

        // $JUnit-END$
        return suite;
    }

//    private static void addTestSuite(final TestSuite suite, final Class<?> klass) {
//        if (_jsfRuntimePresentV11) {
//            suite.addTestSuite(klass);
//        }
//        if (_jsfRuntimePresentV12) {
//            suite.addTestSuite(klass);
//        }
//    }
}
