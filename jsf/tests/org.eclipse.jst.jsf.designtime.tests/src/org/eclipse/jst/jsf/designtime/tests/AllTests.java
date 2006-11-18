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
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.validation.el.tests");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestDefaultBeanSymbolSourceProvider.class);
        suite.addTestSuite(TestResourceBundleMapSource.class);
        suite.addTestSuite(TestDefaultPropertyResolver.class);
        //$JUnit-END$
        return suite;
    }
}
