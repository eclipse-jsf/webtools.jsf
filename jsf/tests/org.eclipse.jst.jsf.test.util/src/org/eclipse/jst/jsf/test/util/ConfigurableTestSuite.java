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
package org.eclipse.jst.jsf.test.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;

public class ConfigurableTestSuite extends TestSuite 
{
    protected TestConfiguration    _configuration;
    
    
    /**
     * Use this constructor when adding to parent ConfigurableTestSuite
     * @param theClass
     * @param name
     */
    public ConfigurableTestSuite(Class<? extends TestCase> theClass, String name) {
        super(theClass, name);
    }

    public ConfigurableTestSuite(Class<?> theClass)
    {
        super(theClass);
    }
    
    public ConfigurableTestSuite(TestConfiguration configuration, String name)
    {
        super(name);
        _configuration = configuration;
    }
    
    public void runTest(Test test, TestResult result) 
    {
        if (test instanceof ConfigurableTestCase)
        {
            ((ConfigurableTestCase)test)
                .setConfiguration(_configuration);
        }
        else if (test instanceof ConfigurableTestSuite)
        {
            ((ConfigurableTestSuite)test)._configuration = 
                  this._configuration;
        }
        super.runTest(test, result);
    }

}
