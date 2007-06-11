/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.validation.el.tests.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;

public class JSPTestCase extends BaseTestCase 
{
    public static final String   FACES_CONFIG_FILE = "facesConfigFile";

    /**
     * Test config
     */
    private MyConfiguration                 _myConfig;
    private final       String              _defaultJSFVersion;
    private  final      String              _defaultFacesConfigFile; 
    
    public JSPTestCase(final String defaultJSFVersion, final String defaultFacesConfigFile) 
    {
        super(defaultJSFVersion);
        _defaultJSFVersion = defaultJSFVersion;
        _defaultFacesConfigFile = defaultFacesConfigFile;
    }

    protected void doStandaloneSetup() 
    {
        super.doStandaloneSetup();
        
        // NOTE: defaults to 1.1 tests for standalone testing
        _myConfig = new MyConfiguration
            (_defaultJSFVersion
                    , _defaultFacesConfigFile);
    }

    protected void doTestSuiteSetup() 
    {
        super.doTestSuiteSetup();
        
        _myConfig = new MyConfiguration(_testConfiguration);
    }
    
    @Override
    protected JSFFacetedTestEnvironment configureJSFEnvironment() throws Exception
    {
        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(_myConfig.getFacetVersion());

        _testEnv.loadResourceInWebRoot(ELValidationTestPlugin.getDefault().getBundle(),
                                      _myConfig.getFacesConfigFile(), 
                                      "/WEB-INF/faces-config.xml");
        return jsfFacedEnv;
    }
    
    protected IFile loadJSP(final String srcFileName, final String destFileName) throws Exception
    {
        return (IFile) _testEnv.loadResourceInWebRoot
            (ELValidationTestPlugin.getDefault().getBundle(),
                    srcFileName, destFileName);
    }
    
    protected static class MyConfiguration
    {
        private final String        _facetVersion;
        private final String        _facesConfigFile;
        
        MyConfiguration(String facetVersion, String facesConfigFile) 
        {
            super();
            _facetVersion = facetVersion;
            _facesConfigFile = facesConfigFile;
        }
        
        MyConfiguration(TestConfiguration testConfiguration)
        {
            _facetVersion = testConfiguration.get(BaseTestCase.JSF_FACET_VERSION);
            _facesConfigFile = testConfiguration.get(FACES_CONFIG_FILE);
        }

        public String getFacetVersion() {
            return _facetVersion;
        }

        public String getFacesConfigFile() {
            return _facesConfigFile;
        }
    }
}
