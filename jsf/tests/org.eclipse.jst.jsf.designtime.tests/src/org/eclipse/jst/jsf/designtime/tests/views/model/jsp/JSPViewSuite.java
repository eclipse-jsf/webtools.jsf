/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import junit.framework.Test;
import junit.framework.TestSuite;

public class JSPViewSuite
{
    public static Test suite()
    {
        // $JUnit-BEGIN$
        final TestSuite suite = new TestSuite(
        "Test for org.eclipse.jst.jsf.designtime.tests.JSPViewSuite");

        suite.addTestSuite(TestDefaultJSPTagResolver.class);
        if ("1.1".equals(System.getProperty("jsfRuntimeVersion")))
        {
            suite.addTestSuite(TestTagAnalyzer.class);
        }
        suite.addTestSuite(TestTagIntrospectingStrategy.class);
        suite.addTestSuite(TestTLDNamespace.class);
        suite.addTestSuite(TestTLDTagRegistry.class);

        return suite;
        // $JUnit-END$
    }
}
