/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.util;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Base class for all JSP test cases in this plugin
 * 
 * @author cbateman
 *
 */
public abstract class BaseTestCase extends ConfigurableTestCase 
{
    public static final String      PROXY_SETTING_HOST = "proxySettings_Host";
    public static final String      PROXY_SETTING_PORT = "proxySettings_Port";
    public static final String      JSF_FACET_VERSION  = "jsfFacetVersion";    
    
    private final JSFVersion            _defaultJSFVersion;
    
    /**
     * Default constructor
     */
    public BaseTestCase(JSFVersion defaultJSFVersion)
    {
        super();
        _defaultJSFVersion = defaultJSFVersion;
    }
    
	/**
	 * @param name
	 */
	public BaseTestCase(String name, JSFVersion defaultJSFVersion) {
        super(name);
        _defaultJSFVersion = defaultJSFVersion;
    }

    /**
	 * The dynamic web project test environment
	 */
	protected WebProjectTestEnvironment  _testEnv;
	/**
	 * A handle to the Java project test environment
	 */
	protected JDTTestEnvironment         _jdtTestEnv;
    
	private MyConfiguration              _configuration;

	
	protected void doStandaloneSetup() 
	{
	    super.doStandaloneSetup();
	    _configuration = new MyConfiguration("www-proxy.uk.oracle.com","80",_defaultJSFVersion);
    }

    protected void doTestSuiteSetup() 
    {
        super.doTestSuiteSetup();
        _configuration = new MyConfiguration(_testConfiguration);
    }

    protected void setUp() throws Exception    
	{
		super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        
        if (_configuration.isProxyEnabled())
        {
            JSFTestUtil.setInternetProxyPreferences
                (true, _configuration.getProxyHostName()
                        , _configuration.getProxyPort());
        }

        // if JSF 1.1, use web facet 2.4, if higher then use 2.5
        final String webProjVersion = 
            (_configuration.getJsfVersion() == JSFVersion.V1_0
                    || _configuration.getJsfVersion() == JSFVersion.V1_1)
                ? "2.4" : "2.5";
        
        _testEnv = new WebProjectTestEnvironment
            ("MetaDataTest_"+this.getClass().getName()+"_"+getName()+"_"+_configuration.getJsfVersion()
                    , JavaFacet.VERSION_1_5
                    , ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion(webProjVersion));
        _testEnv.createProject(false);
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        // sub-classes may custom their JSF env; primarily to decide what version
        // of JSF
        configureJSFEnvironment();
        
        _jdtTestEnv = new JDTTestEnvironment(_testEnv);
        configureJDTTestEnvironment(_jdtTestEnv);
	}
    
	/**
	 * Used to configure the JSF environment. After successful 
	 * return, sub-classes must ensure that their JSF facet is
	 * installed and that there is at least one faces-config (in WEB-INF)
	 * installed
	 * 
	 * @throws Exception
	 */
	protected abstract JSFFacetedTestEnvironment configureJSFEnvironment() throws Exception;
	
	/**
	 * Add all Java and property file resources to the Java source path
	 * needed for testing.  Sub-classes may override.
	 * 
	 * @param jdtTestEnv
	 * @throws Exception
	 */
	protected void configureJDTTestEnvironment(JDTTestEnvironment jdtTestEnv) throws Exception
	{
	}
	
    protected void tearDown() throws Exception
    {
        _testEnv.getTestProject().close(null);
        //_testEnv.getTestProject().delete(true, null);
    }
    

    protected void addJavaFile(String file) throws IOException,
			JavaModelException, CoreException {
				TestFileResource resource = new TestFileResource();
				resource.load(MetadataTestsPlugin.getDefault().getBundle(), "/testfiles/java/"+file+".java.data");
			    _jdtTestEnv.addSourceFile("src", "com.foo", file, resource.toString());
			}


	private static class MyConfiguration
    {
        private final String  _proxyHostName;
        private final String  _proxyPort;
        private final JSFVersion  _jsfVersion;
        
        MyConfiguration(final String proxyHostName, final String proxyPort, final JSFVersion jsfVersion)
        {
            _proxyHostName = proxyHostName;
            _proxyPort = proxyPort;
            _jsfVersion = jsfVersion;
        }
        
        MyConfiguration(TestConfiguration  configuration)
        {
            _proxyHostName = configuration.get(BaseTestCase.PROXY_SETTING_HOST);
            _proxyPort = configuration.get(BaseTestCase.PROXY_SETTING_PORT);
            _jsfVersion = JSFVersion.valueOfString(configuration.get(BaseTestCase.JSF_FACET_VERSION));
        }
        
        public boolean isProxyEnabled()
        {
            return _proxyHostName != null && _proxyPort != null;
        }

        public String getProxyHostName() {
            return _proxyHostName;
        }

        public String getProxyPort() {
            return _proxyPort;
        }

        public JSFVersion getJsfVersion() {
            return _jsfVersion;
        }
        
    }
}