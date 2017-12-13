/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.el.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.test.util.ConfigurableTestSuite;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;
import org.eclipse.jst.jsf.validation.el.tests.base.BaseTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.JSPTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.ResourceBundleTestCase;

public class AllTests_1_2 {
    public static Test suite()
    {
        final TestSuite suite = new TestSuite("JSF EL Validation Testing (1.2 only)");

        final TestConfiguration  configuration = new TestConfiguration();
        configuration.put(BaseTestCase.PROXY_SETTING_HOST, "www-proxy.us.oracle.com");
        configuration.put(BaseTestCase.PROXY_SETTING_PORT, "80");

        suite.addTest(getFacesSuite((TestConfiguration) configuration.clone()));
        return suite;
    }

    public static Test getFacesSuite(final TestConfiguration commonConfig)
    {
        // add JSF 1.2 specific configs to common config
        commonConfig.put(BaseTestCase.JSF_FACET_VERSION, IJSFCoreConstants.FACET_VERSION_1_2);
        commonConfig.put(JSPTestCase.FACES_CONFIG_FILE, SingleJSPTestCase.FACES_CONFIG_FILE_NAME_1_2);

        final TestSuite suite = new ConfigurableTestSuite(commonConfig, "JSF 1.2 EL Validation Tests");

        // JSF 1.2 only tests
        suite.addTest(new ConfigurableTestSuite(ResourceBundleTestCase.class));
        CommonTests.getFaces_common_suite(suite);

        return suite;
    }

}
