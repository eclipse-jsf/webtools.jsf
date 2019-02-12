/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.contentassist.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All tests suite for content assist
 *
 * @author cbateman
 *
 */
public class AllTests {
    /**
     * @return the test suite
     */
    public static Test suite()
    {
        final TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.contentassist");
        //$JUnit-BEGIN$
        suite.addTestSuite(CompletionPrefixTest.class);
        suite.addTestSuite(Test_bug_149224.class);
        suite.addTestSuite(Test_bug_149743.class);
        suite.addTestSuite(TestIdCompletionStrategy.class);
        suite.addTestSuite(TestFunctionCompletionStrategy.class);
        suite.addTestSuite(TestContentAssistParser.class);
        //$JUnit-END$
        return suite;
    }

}
