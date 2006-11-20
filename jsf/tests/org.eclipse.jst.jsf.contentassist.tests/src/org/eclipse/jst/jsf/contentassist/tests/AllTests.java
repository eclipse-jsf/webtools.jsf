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
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.contentassist");
        //$JUnit-BEGIN$
        suite.addTestSuite(CompletionPrefixTest.class);
        suite.addTestSuite(Test_bug_149224.class);
        suite.addTestSuite(Test_bug_149743.class);
        //$JUnit-END$
        return suite;
    }

}
