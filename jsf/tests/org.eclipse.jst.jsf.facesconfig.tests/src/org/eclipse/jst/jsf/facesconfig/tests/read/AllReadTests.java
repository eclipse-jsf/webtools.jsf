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

/**
 * Wrapper suite for all the tests against the .util package.
 * 
 * @author spaxton
 * @author le-ake m. G kristos
 */
public class AllReadTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test FacesConfig model");

        suite.addTest(new TestSuite(ReadFacesConfigElementsTestCase.class,
                "Faces-config - Each Element"));

        // Application
        suite.addTest(new TestSuite(ReadApplicationTestCase.class,
                "Application Test"));
        
        // Component
        suite.addTest(new TestSuite(ReadComponentTestCase.class,
                "Component Test"));
        suite.addTest(new TestSuite(ReadAttributeComponentTestCase.class,
                "Component-->Attribute Test"));
        suite.addTest(new TestSuite(ReadFacetComponentTestCase.class,
                "Component-->Facet Test"));
        suite.addTest(new TestSuite(ReadPropertyComponentTestCase.class,
                "Component-->Property Test"));
        
        // Converter
        suite.addTest(new TestSuite(ReadConverterTestCase.class,
                "Converter Test - Single"));
        suite.addTest(new TestSuite(ReadAttributeConverterTestCase.class,
                "Converter-->Attribute Test"));
        suite.addTest(new TestSuite(ReadPropertyConverterTestCase.class,
                "Converter-->Property Test"));
        
        // Factory
        suite.addTest(new TestSuite(ReadFactoryTestCase.class,
                "Facesconfig -Factory "));
        
        // Lifecycle
        suite.addTest(new TestSuite(ReadLifecycleTestCase.class,
                "Lifecycle Test - Single"));
        
        // Managed Bean
        suite.addTest(new TestSuite(ReadManagedBeanTestCase.class,
                "Managed-Bean Test"));
        suite.addTest(new TestSuite(
                ReadManagedBeanManagedPropertyTestCase.class,
                "Managed-Bean Property"));
        
        // Navigation
        suite.addTest(new TestSuite(ReadNavigationRuleTestCase.class,
                "Navigation Rule"));
        
        // Referenced Bean
        suite.addTest(new TestSuite(ReadReferencedBeanTestCase.class,
                "Referenced-Bean Test"));

        // Render-kit
        suite.addTest(new TestSuite(ReadRenderKitTestCase.class,
                "Render-kit Test"));
        suite.addTest(new TestSuite(ReadRendererTestCase.class,
                "Render-kit-->Renderer Test"));
        suite.addTest(new TestSuite(ReadAttributeRendererTestCase.class,
                "Render-kit-->Renderer-->Attribute"));
        suite.addTest(new TestSuite(ReadFacetRendererTestCase.class,
                "Render-kit-->Renderer-->Facet"));
        
        // Validator
        suite.addTest(new TestSuite(ReadValidatorTestCase.class,
                "Validator Test - Single"));
        suite.addTest(new TestSuite(ReadAttributeValidatorTestCase.class,
                "Validator-->Attribute Test - Single"));
        suite.addTest(new TestSuite(ReadPropertyValidatorTestCase.class,
                "Validator-->Property Test"));

        // Extended data
        suite.addTest(new TestSuite(ReadExtensionDataTestCase.class,
                "Extension Data Test 1"));
        
        return suite;
    }
}
