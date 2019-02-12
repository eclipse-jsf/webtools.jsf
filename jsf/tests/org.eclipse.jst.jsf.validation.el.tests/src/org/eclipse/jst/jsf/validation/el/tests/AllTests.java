package org.eclipse.jst.jsf.validation.el.tests;
/*******************************************************************************
 * Copyright (c) 2005, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;
import org.eclipse.jst.jsf.validation.el.tests.base.BaseTestCase;


/**
 * The full test suite for core.tests
 * 
 * @author cbateman
 *
 */
public class AllTests 
{
    public static Test suite() 
    {
        TestSuite suite = new TestSuite("EL Validation Testing");
        
        TestConfiguration  configuration = new TestConfiguration();
        configuration.put(BaseTestCase.PROXY_SETTING_HOST, "www-proxy.uk.oracle.com");
        configuration.put(BaseTestCase.PROXY_SETTING_PORT, "80");

        suite.addTest(AllTests_1_1.getFacesSuite((TestConfiguration) configuration.clone()));
        suite.addTest(AllTests_1_2.getFacesSuite((TestConfiguration) configuration.clone()));
        return suite;
    }
}