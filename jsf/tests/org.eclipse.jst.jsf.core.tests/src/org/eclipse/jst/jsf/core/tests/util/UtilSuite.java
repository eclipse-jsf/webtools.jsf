/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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
