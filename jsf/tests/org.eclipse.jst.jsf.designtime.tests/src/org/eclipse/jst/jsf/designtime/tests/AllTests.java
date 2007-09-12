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
public class AllTests 
{
    /**
     * @return the all tests suite
     */
    public static Test suite() 
    {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.designtime.tests");
        //$JUnit-BEGIN$
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
        //$JUnit-END$
        return suite;
    }
}
