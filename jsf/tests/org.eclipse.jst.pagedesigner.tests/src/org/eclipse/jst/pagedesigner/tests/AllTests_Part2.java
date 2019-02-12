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
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolForJSFCore;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolForJSFHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolHTML;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestItemCreationToolJSP;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestOverrideableCreationData;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestUserCustomizedTagCreatorForJSFHTML_AttributeCustomization;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestUserCustomizedTagCreatorForJSFHTML_ChildCustomization;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestUserCustomizedTagCreatorForJSFHTML_GeneralCustomization;
import org.eclipse.jst.pagedesigner.tests.tagcreator.TestUserCustomizedTagCreatorForJSFHTML_ParentCustomization;

/**
 * All tests suite for Web Page Editor. Part 2
 * 
 * Split into multiple suites since combined they eventually grind to a halt
 * 
 * @author Ian Trimble - Oracle
 * 
 */
public class AllTests_Part2
{
    /**
     * @return the test suite
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Tests for org.eclipse.jst.pagedesigner - Part 2");
        // $JUnit-BEGIN$

        // Add UserCustomizedTagCreator tests
        addTestRequiringJSFRuntime(suite, TestUserCustomizedTagCreatorForJSFHTML_AttributeCustomization.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestUserCustomizedTagCreatorForJSFHTML_ChildCustomization.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestUserCustomizedTagCreatorForJSFHTML_ParentCustomization.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestUserCustomizedTagCreatorForJSFHTML_GeneralCustomization.class, JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestOverrideableCreationData.class, JSFVersion.V1_1);
        
        // TODO: could merge the item creation and default tag creators into a
        // common testsuite
        addTestRequiringJSFRuntime(suite, TestItemCreationToolForJSFCore.class,
                JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestItemCreationToolForJSFHTML.class,
                JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestItemCreationToolHTML.class,
                JSFVersion.V1_1);
        addTestRequiringJSFRuntime(suite, TestItemCreationToolJSP.class,
                JSFVersion.V1_1);

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
