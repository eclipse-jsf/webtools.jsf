package org.eclipse.jst.jsf.test.util;

import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class ConfigurableTestSuite extends TestSuite 
{
    protected TestConfiguration    _configuration;
    
    
    /**
     * Use this constructor when adding to parent ConfigurableTestSuite
     * @param theClass
     * @param name
     */
    public ConfigurableTestSuite(Class theClass, String name) {
        super(theClass, name);
    }

    public ConfigurableTestSuite(Class theClass)
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
