package org.eclipse.jst.jsf.test.util;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Set;

import junit.framework.TestCase;

public class ConfigurableTestCase extends TestCase 
{
    protected TestConfiguration     _testConfiguration;
    
    
    public ConfigurableTestCase(String name)
    {
        super(name);
    }
    
    public void setConfiguration(TestConfiguration configuration)
    {
        _testConfiguration = configuration;
    }
    
    public static class TestConfiguration extends AbstractMap
    {
        private HashMap   backingMap = new HashMap();
        
        public Set entrySet() 
        {
            return backingMap.entrySet();
        }

        public Object put(Object key, Object value) {
            return backingMap.put(key, value);
        }
    }
}
