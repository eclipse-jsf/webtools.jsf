package org.eclipse.jst.jsf.validation.el.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.test.util.ConfigurableTestSuite;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;
import org.eclipse.jst.jsf.validation.el.tests.base.BaseTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.ResourceBundleTestCase;

public class AllTests_1_2 {
    public static Test suite() 
    {
        TestSuite suite = new TestSuite("JSF EL Validation Testing (1.2 only)");
        
        TestConfiguration  configuration = new TestConfiguration();
        configuration.put(BaseTestCase.PROXY_SETTING_HOST, "www-proxy.uk.oracle.com");
        configuration.put(BaseTestCase.PROXY_SETTING_PORT, "80");

        suite.addTest(getFacesSuite((TestConfiguration) configuration.clone()));
        return suite;
    }
    
    public static Test getFacesSuite(TestConfiguration commonConfig)
    {
        // add JSF 1.2 specific configs to common config
        commonConfig.put(BaseTestCase.JSF_FACET_VERSION, IJSFCoreConstants.FACET_VERSION_1_2);
        commonConfig.put(SingleJSPTestCase.FACES_CONFIG_FILE, SingleJSPTestCase.FACES_CONFIG_FILE_NAME_1_2);

        TestSuite suite = new ConfigurableTestSuite(commonConfig, "JSF 1.2 EL Validation Tests");

        // JSF 1.2 only tests
        suite.addTest(new ConfigurableTestSuite(ResourceBundleTestCase.class));
        CommonTests.getFaces_common_suite(suite);
        
        return suite;
    }

}
