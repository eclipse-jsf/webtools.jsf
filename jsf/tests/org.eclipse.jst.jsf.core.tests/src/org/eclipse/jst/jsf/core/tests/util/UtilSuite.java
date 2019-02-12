/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
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

        suite.addTestSuite(TestCMUtil.class);
        
        //$JUnit-END$
        return suite;
    }
}
