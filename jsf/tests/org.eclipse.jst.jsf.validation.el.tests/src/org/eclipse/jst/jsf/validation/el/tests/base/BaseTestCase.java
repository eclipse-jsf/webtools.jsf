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
package org.eclipse.jst.jsf.validation.el.tests.base;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.resolver.AbstractStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.designtime.resolver.CachingSymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
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
    protected final IStructuredDocumentSymbolResolverFactory _symbolResolverFactory
        = new MySymbolResolverFactory();;

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
            ("ELValidationTest_"+this.getClass().getName()+"_"+getName()+"_"+_configuration.getJsfVersion()
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
	    // load enums first, since other classes have dependencies
        TestFileResource resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyEnum1.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyEnum1", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyEnum2.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyEnum2", resource.toString());
        
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MapBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MapBean", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyBeanSettable.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBeanSettable", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyBeanSubClass.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBeanSubClass", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/BeanWithMapProperties.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "BeanWithMapProperties", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/BeanWithListProperties.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "BeanWithListProperties", resource.toString());
        
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/ListBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "ListBean", resource.toString());
        
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/Bundle.properties.data");
        jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()), 
                      "beans", "Bundle.properties");
	}
	
    protected void tearDown() throws Exception
    {
        _testEnv.getTestProject().close(null);
        //_testEnv.getTestProject().delete(true, null);
    }
    
    /**
     * Performs pre-condition and other sanity checks on what was done in setUp()
     */
    public void testSanity()
    {
        final IJavaProject javaProject = _jdtTestEnv.getJavaProject(); 
        assertNotNull(javaProject);
        
        try
        {
            IType type = javaProject.findType("beans.MyBean");
            assertNotNull(type);
            
            type = javaProject.findType("beans.MapBean");
            assertNotNull(type);

            type = javaProject.findType("beans.MyBeanSettable");
            assertNotNull(type);

            type = javaProject.findType("beans.MyBeanSubClass");
            assertNotNull(type);
            
            type = javaProject.findType("beans.BeanWithMapProperties");
            assertNotNull(type);

            type = javaProject.findType("beans.BeanWithListProperties");
            assertNotNull(type);

            type = javaProject.findType("beans.ListBean");
            assertNotNull(type);

            IPackageFragmentRoot srcRoot = _jdtTestEnv.getPackageFragmentRoot("src");
            assertTrue(srcRoot.exists());
            IPackageFragment frag = srcRoot.getPackageFragment("beans");
            assertTrue(frag.exists());
            IFolder res = (IFolder) frag.getResource();
            IFile bundleFile = res.getFile("Bundle.properties");
            assertTrue(bundleFile.exists());
            
            IJSFAppConfigManager  cfgManager = 
                JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(_jdtTestEnv.getProjectEnvironment().getTestProject());
            assertNotNull(cfgManager);
            
            List<ManagedBeanType> mbeans = cfgManager.getManagedBeans();
            Map<String, ManagedBeanType>  nameTest = new HashMap<String, ManagedBeanType>();
            
            for (final ManagedBeanType mbean : mbeans)
            {
            	nameTest.put(mbean.getManagedBeanName().getTextContent(), mbean);
            }

            assertTrue(nameTest.containsKey("myBean"));
            assertTrue(nameTest.containsKey("mapBean"));
            assertTrue(nameTest.containsKey("myBeanSettable"));
            assertTrue(nameTest.containsKey("myBeanSubClass"));
            assertTrue(nameTest.containsKey("beanWithMapProperties"));
            assertTrue(nameTest.containsKey("beanWithListProperties"));
            assertTrue(nameTest.containsKey("listBean"));
        }
        catch(JavaModelException jme)
        {
            assertTrue("JDT error: "+jme.getLocalizedMessage(), false);
        }
        catch (CoreException ce)
        {
            assertTrue("Problem loading bundle: "+ce.getLocalizedMessage(), false);
        }
    }
    
    private static class MySymbolResolverFactory extends AbstractStructuredDocumentSymbolResolverFactory
    {
        private ISymbolContextResolver      _resolver;

        @Override
        public ISymbolContextResolver getSymbolContextResolver(
                IModelContext context)
        {
            if (_resolver == null)
            {
                _resolver = new CachingSymbolContextResolver((IStructuredDocumentContext) context);
            }
            else
            {
                if (!_resolver.hasSameResolution(context))
                {
                    _resolver = new CachingSymbolContextResolver((IStructuredDocumentContext) context);
                }
            }
            return _resolver;
        }
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