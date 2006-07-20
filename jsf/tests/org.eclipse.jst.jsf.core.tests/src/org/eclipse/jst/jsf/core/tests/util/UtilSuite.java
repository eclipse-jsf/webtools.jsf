package org.eclipse.jst.jsf.core.tests.util;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Runs all util package tests as a suite
 * 
 * @author cbateman
 *
 */
public class UtilSuite {

    /**
     * @return the test suite
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Tests for org.eclipse.jst.jsf.core.tests.util");
        //$JUnit-BEGIN$
        
        suite.addTestSuite(TestJDTBeanPropertyWorkingCopy.class);
        suite.addTestSuite(TestJDTBeanIntrospector.class);

        //$JUnit-END$
        return suite;
    }
}
