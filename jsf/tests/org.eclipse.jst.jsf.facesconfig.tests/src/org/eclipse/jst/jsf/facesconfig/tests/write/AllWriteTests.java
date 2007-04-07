/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.internal.provisional.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.test.util.ConfigurableTestSuite;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;

/**
 * Wrapper suite for all the tests against the .util package.
 * 
 * @author spaxton
 * @author le-ake m. G kristos
 */
public class AllWriteTests {

    public static Test suite() 
    {
        TestSuite suite = new TestSuite("FacesConfig Model Write/Read Translation");
        suite.addTest(Faces_1_1_suite());
        suite.addTest(Faces_1_2_suite());
        return suite;
    }
    
    private static Test Faces_1_2_suite()
    {
        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.put(BaseWriteTestCase.CONFIG_FILE_KEY, "WEB-INF/faces-config2.xml");
        testConfiguration.put(BaseWriteTestCase.FACES_VERSION_KEY, IJSFCoreConstants.JSF_VERSION_1_2);

        TestSuite suite = 
            new ConfigurableTestSuite(testConfiguration, "Faces 1.2 Model Tests");
        suite.addTest(new ConfigurableTestSuite(
                WriteApplicationTestCase_1_2.class,
                "Write application"));
        suite.addTest(new ConfigurableTestSuite(
                WriteConverterTestCase_1_2.class,
                "Write converter Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteFactoryTestCase_1_2.class,
                "Write factory "));
        suite.addTest(new ConfigurableTestSuite(
                WriteLifecycleTestCase_1_2.class,
                "Write Lifecycle Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteManagedBeanTestCase_1_2.class,
                "Write Managed-bean Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteNavigationRuleTestCase_1_2.class,
                "Write navigation rule"));
        suite.addTest(new ConfigurableTestSuite(
                WriteRenderKitTestCase_1_2.class,
                "Write Render-kit Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteValidatorTestCase_1_2.class,
                "Write Validator Test"));
        Faces_common_suite(suite, testConfiguration);
        
        suite.addTest(new ConfigurableTestSuite(
                FacesConfigExtensionTestCase.class,
                "Write Facesconfig Extension Test"));
        
        return suite;
    }
    
    private static Test Faces_1_1_suite()
    {
        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.put(BaseWriteTestCase.CONFIG_FILE_KEY, "WEB-INF/faces-config1.xml");
        testConfiguration.put(BaseWriteTestCase.FACES_VERSION_KEY, IJSFCoreConstants.JSF_VERSION_1_1);

        TestSuite suite = 
            new ConfigurableTestSuite(testConfiguration, "Faces 1.1 Model Tests");

        suite.addTest(new ConfigurableTestSuite(
                WriteApplicationTestCase.class,
                "Write application"));
        suite.addTest(new ConfigurableTestSuite(
                WriteConverterTestCase.class,
                "Write converter Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteFactoryTestCase.class,
                "Write factory "));
        suite.addTest(new ConfigurableTestSuite(
                WriteLifecycleTestCase.class,
                "Write Lifecycle Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteManagedBeanTestCase.class,
                "Write Managed-bean Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteNavigationRuleTestCase.class,
                "Write navigation rule "));
        suite.addTest(new ConfigurableTestSuite(
                WriteRenderKitTestCase.class,
                "Write Render-kit Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteValidatorTestCase.class,
                "Write Validator Test"));   
        Faces_common_suite(suite, testConfiguration);
        
        return suite;
    }
    
    private static void Faces_common_suite(TestSuite suite, TestConfiguration configuration)
    {
        suite.addTest(new ConfigurableTestSuite(
                CommonStructuresUtil.class,
                "Sanity Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteComponentTestCase.class,
                "Write Component Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteReferencedBeanTestCase.class,
                "Write ReferencedBean Test"));
        suite.addTest(new ConfigurableTestSuite(
                WriteComponentExtensionDataTestCase.class,
                "Write Component Extension Data"));
	}
}
