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
package org.eclipse.jst.jsf.facesconfig.tests.read;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.core.internal.provisional.IJSFCoreConstants;
import org.eclipse.jst.jsf.test.util.ConfigurableTestSuite;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase.TestConfiguration;

/**
 * Wrapper suite for all the tests against the .util package.
 * 
 * @author spaxton
 * @author le-ake m. G kristos
 */
public class AllReadTests {

    public static Test suite() 
    {
        TestSuite suite = new TestSuite("FacesConfig Model Read Translation");
        suite.addTest(Faces_1_1_suite());
        suite.addTest(Faces_1_2_suite());
        return suite;
    }
    
    private static Test Faces_1_2_suite()
    {
        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.put(BaseReadTestCase.CONFIG_FILE_KEY, "WEB-INF/faces-config_1_2.xml");
        testConfiguration.put(BaseReadTestCase.FACES_VERSION_KEY, IJSFCoreConstants.JSF_VERSION_1_2);

        TestSuite suite = 
            new ConfigurableTestSuite(testConfiguration, "Faces 1.2 Model Tests");
        suite.addTest(new ConfigurableTestSuite(ReadApplicationTestCase_1_2.class, "Application Test"));
        suite.addTest(new ConfigurableTestSuite(ReadFacesConfigElementsTestCase_1_2.class,
            "Faces-config - Each Element"));
        // Factory
        suite.addTest(new ConfigurableTestSuite(ReadFactoryTestCase_1_2.class,
            "Facesconfig -Factory "));
        // Converter
        suite.addTest(new ConfigurableTestSuite(ReadConverterTestCase_1_2.class,
                "Converter Test - Single"));
        // Lifecycle
        suite.addTest(new ConfigurableTestSuite(ReadLifecycleTestCase_1_2.class,
                "Lifecycle Test - Single"));
        // Navigation
        suite.addTest(new ConfigurableTestSuite(ReadNavigationRuleTestCase_1_2.class,
                "Navigation Rule"));
        // Validator
        suite.addTest(new ConfigurableTestSuite(ReadValidatorTestCase_1_2.class,
                "Validator Test - Single"));
        // Managed Bean
        suite.addTest(new ConfigurableTestSuite(ReadManagedBeanTestCase_1_2.class,
                "Managed-Bean Test"));
        // Render-kit
        suite.addTest(new ConfigurableTestSuite(ReadRenderKitTestCase_1_2.class,
                "Render-kit Test"));

        Faces_base_suite(suite, testConfiguration);
        return suite;
    }
    
    private static Test Faces_1_1_suite()
    {
        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.put(BaseReadTestCase.CONFIG_FILE_KEY, BaseReadTestCase.CONFIG_FILE_DEFAULT);
        testConfiguration.put(BaseReadTestCase.FACES_VERSION_KEY, IJSFCoreConstants.JSF_VERSION_1_1);

        TestSuite suite = 
            new ConfigurableTestSuite(testConfiguration, "Faces 1.1 Model Tests");
        suite.addTest(new ConfigurableTestSuite(ReadFacesConfigElementsTestCase.class,
            "Faces-config - Each Element"));
        // Application
        suite.addTest(new ConfigurableTestSuite(ReadApplicationTestCase.class,
                "Application Test"));
        // Factory
        suite.addTest(new ConfigurableTestSuite(ReadFactoryTestCase.class,
                "Facesconfig -Factory "));
        // Converter
        suite.addTest(new ConfigurableTestSuite(ReadConverterTestCase.class,
                "Converter Test - Single"));
        // Lifecycle
        suite.addTest(new ConfigurableTestSuite(ReadLifecycleTestCase.class,
                "Lifecycle Test - Single"));
        Faces_base_suite(suite, testConfiguration);
        // Navigation
        suite.addTest(new ConfigurableTestSuite(ReadNavigationRuleTestCase.class,
                "Navigation Rule"));
        // Validator
        suite.addTest(new ConfigurableTestSuite(ReadValidatorTestCase.class,
                "Validator Test - Single"));
        // Managed Bean
        suite.addTest(new ConfigurableTestSuite(ReadManagedBeanTestCase.class,
                "Managed-Bean Test"));
        // Render-kit
        suite.addTest(new ConfigurableTestSuite(ReadRenderKitTestCase.class,
                "Render-kit Test"));

        return suite;
    }

    private static void Faces_base_suite(TestSuite suite, TestConfiguration configuration) 
    {
        // Component
        suite.addTest(new ConfigurableTestSuite(ReadComponentTestCase.class,
                "Component Test"));
        suite.addTest(new ConfigurableTestSuite(ReadAttributeComponentTestCase.class,
                "Component-->Attribute Test"));
        suite.addTest(new ConfigurableTestSuite(ReadFacetComponentTestCase.class,
                "Component-->Facet Test"));
        suite.addTest(new ConfigurableTestSuite(ReadPropertyComponentTestCase.class,
                "Component-->Property Test"));
        
        suite.addTest(new ConfigurableTestSuite(ReadAttributeConverterTestCase.class,
                "Converter-->Attribute Test"));
        suite.addTest(new ConfigurableTestSuite(ReadPropertyConverterTestCase.class,
                "Converter-->Property Test"));
        
        // Managed Bean
        suite.addTest(new ConfigurableTestSuite(
                ReadManagedBeanManagedPropertyTestCase.class,
                "Managed-Bean Property"));
        
        // Referenced Bean
        suite.addTest(new ConfigurableTestSuite(ReadReferencedBeanTestCase.class,
                "Referenced-Bean Test"));

        // Render-kit
        suite.addTest(new ConfigurableTestSuite(ReadRendererTestCase.class,
                "Render-kit-->Renderer Test"));
        suite.addTest(new ConfigurableTestSuite(ReadAttributeRendererTestCase.class,
                "Render-kit-->Renderer-->Attribute"));
        suite.addTest(new ConfigurableTestSuite(ReadFacetRendererTestCase.class,
                "Render-kit-->Renderer-->Facet"));
        
        // Validator
        suite.addTest(new ConfigurableTestSuite(ReadAttributeValidatorTestCase.class,
                "Validator-->Attribute Test - Single"));
        suite.addTest(new ConfigurableTestSuite(ReadPropertyValidatorTestCase.class,
                "Validator-->Property Test"));

        // Extended data
        suite.addTest(new ConfigurableTestSuite(ReadExtensionDataTestCase.class,
                "Extension Data Test 1"));
    }
}
