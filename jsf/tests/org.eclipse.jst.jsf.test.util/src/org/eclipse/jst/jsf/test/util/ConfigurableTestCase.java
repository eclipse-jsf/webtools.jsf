package org.eclipse.jst.jsf.test.util;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Set;

import junit.framework.TestCase;

public class ConfigurableTestCase extends TestCase 
{
    protected TestConfiguration     _testConfiguration;
    
    
    protected ConfigurableTestCase() {
        super();
    }

    public ConfigurableTestCase(String name)
    {
        super(name);
    }
    
    
    protected void setUp() throws Exception {
        super.setUp();
        
        if (_testConfiguration == null)
        {
            doStandaloneSetup();
        }
        else
        {
            doTestSuiteSetup();
        }
    }

    /**
     * sub-classes should override to do test configuration for 
     * when the test is run stand-alone. 
     * 
     * Subs should always call super.doStandalonSetup to ensure all setup is performed
     */
    protected void doStandaloneSetup()
    {
        // no default behavior
    }
    
    /**
     * sub-classes should override to handle the case where
     * the testcase is run under a ConfigurableTestSuite that sets
     * the configuration automatically.
     * 
     * Subs should always call super.doStandalonSetup to ensure all setup is performed
     */
    protected void doTestSuiteSetup()
    {
        // no default behavior
    }
    
    public void setConfiguration(TestConfiguration configuration)
    {
        _testConfiguration = configuration;
    }
    
    public static class TestConfiguration extends AbstractMap<String, String>
    {
        private HashMap<String, String>   backingMap = new HashMap<String, String>();
        
        public Set<Entry<String,String>> entrySet() 
        {
            return backingMap.entrySet();
        }

        public String put(String key, String value) {
            return backingMap.put(key, value);
        }

        /**
         * Produces a new TestConfiguration with a shallow copy of the backing map
         */
        @SuppressWarnings("unchecked")
        public Object clone() 
        {
            TestConfiguration clone = new TestConfiguration();
            HashMap<String, String>  newBackingMap = (HashMap<String, String>) backingMap.clone();
            clone.backingMap = newBackingMap;
            return clone;
        }
        
        
    }
}
