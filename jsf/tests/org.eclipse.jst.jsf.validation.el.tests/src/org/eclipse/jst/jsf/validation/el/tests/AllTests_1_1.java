package org.eclipse.jst.jsf.validation.el.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.test.util.ConfigurableTestSuite;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;
import org.eclipse.jst.jsf.validation.el.tests.base.BaseTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.JSPTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

public class AllTests_1_1
{
    public static Test suite()
    {
        final TestSuite suite = new TestSuite("EL Validation Testing  (1.1 only)");

        final TestConfiguration  configuration = new TestConfiguration();
        configuration.put(BaseTestCase.PROXY_SETTING_HOST, "www-proxy.uk.oracle.com");
        configuration.put(BaseTestCase.PROXY_SETTING_PORT, "80");

        suite.addTest(getFacesSuite((TestConfiguration) configuration.clone()));
        return suite;
    }

    /**
     * @return the test suite
     */
    public static Test getFacesSuite(final TestConfiguration commonConfig)
    {
        // add JSF 1.1 specific configs to common config
        commonConfig.put(BaseTestCase.JSF_FACET_VERSION, IJSFCoreConstants.FACET_VERSION_1_1);
        commonConfig.put(JSPTestCase.FACES_CONFIG_FILE, SingleJSPTestCase.FACES_CONFIG_FILE_NAME_1_1);

        final TestSuite suite = new ConfigurableTestSuite(commonConfig, "JSF 1.1 EL Validation Tests");

        CommonTests.getFaces_common_suite(suite);

        return suite;
    }

}
