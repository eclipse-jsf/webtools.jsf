/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.pagedesigner.tests.tabbed.properties.sections.BasicTabbedPropertyPageTests;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorForJSFCore;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorForJSFHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestDefaultTagCreatorJSP;

/**
 * All tests suite for Web Page Editor. Part 1
 * 
 * Split into multiple suites since combined they eventually grind to a halt
 * 
 * @author Ian Trimble - Oracle
 * 
 */
public class AllTests_Part1
{
    /**
     * @return the test suite
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Tests for org.eclipse.jst.pagedesigner - Part 1");
        // $JUnit-BEGIN$

        // tests requiring jsfRuntimeJarsDirectoryV1.1

        addTestRequiringJSFRuntime(suite, Test_DTManager.class, JSFVersion.V1_1);
        
        addTestRequiringJSFRuntime(suite, Test_TransformOperations.class,
                JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite,
                TestDefaultTagCreatorForJSFCore.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite,
                TestDefaultTagCreatorForJSFHTML.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestDefaultTagCreatorHTML.class,
                JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestDefaultTagCreatorJSP.class,
                JSFVersion.V1_1);

        // C.B: 2008/01/18 temporary comment out: see
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=215849
        // addTestRequiringJSFRuntime(suite, TestTagCreationFactory.class,
        // JSFVersion.V1_1);

        // Tabbed Property Tests - do not require JSF Runtime
         suite.addTestSuite(BasicTabbedPropertyPageTests.class);
        // $JUnit-END$
        return suite;
    }

    private static void addTestRequiringJSFRuntime(TestSuite suite,
            Class<? extends TestCase> testClass, JSFVersion jsfVersion)
    {
        if (JSFCoreUtilHelper.isJSFRuntimeJarsDirectoryValid(jsfVersion))
        {
            suite.addTestSuite(testClass);
        }
        else
        {
            System.err.println(JSFCoreUtilHelper
                    .getTestRequiresJSFRuntimeMessage(testClass, jsfVersion));
        }
    }
}
