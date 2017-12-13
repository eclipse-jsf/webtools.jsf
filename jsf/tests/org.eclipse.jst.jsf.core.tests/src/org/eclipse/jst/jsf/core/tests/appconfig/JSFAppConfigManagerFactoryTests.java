package org.eclipse.jst.jsf.core.tests.appconfig;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class JSFAppConfigManagerFactoryTests extends TestCase {
	WebProjectTestEnvironment projectTestEnvironment;
	JDTTestEnvironment jdtTestEnv;
	
	protected void setUp() throws Exception {
		super.setUp();
		
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
        
        projectTestEnvironment = 
            new WebProjectTestEnvironment("JSFAppConfigManagerFactoryTests"+"_"+getName());
        boolean created = projectTestEnvironment.createProject(true);

        assertNotNull(projectTestEnvironment);       
        assertNotNull(projectTestEnvironment.getTestProject());
        assertTrue(projectTestEnvironment.getTestProject().isAccessible());
        
        if (created) {
	        // initialize test case for faces 1.1
	        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(projectTestEnvironment);
	        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
	        
        }
	}

	public void testDefaultJSFAppConfigManagerSetup() {
		//test extension must be removed for this to work
		IJSFAppConfigManager mgr = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(projectTestEnvironment.getTestProject());
		assertNotNull(mgr);
		assertEquals("org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager", mgr.getClass().getName());
		
		//verify getting same instance when called the second time
		IJSFAppConfigManager mgr2 = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(projectTestEnvironment.getTestProject());
		assertNotNull(mgr2);		
		assertEquals(mgr, mgr2);
		
		//verify that the deprecated call results in same instance
		JSFAppConfigManager mgr3 = JSFAppConfigManager.getInstance(projectTestEnvironment.getTestProject());
		assertNotNull(mgr3);		
		assertEquals(mgr, mgr3);
		assertNotNull(mgr3.getManagedBeans());
	}
	
// test extension disabled becuz it screws up tests	
//	public void testExtPtJSFAppConfigManagerSetup() {
//		IJSFAppConfigManager mgr = JSFAppConfigManagerFactory.getInstance(projectTestEnvironment.getTestProject());
//		assertNotNull(mgr);
//		assertEquals("TestJSFAppConfigManager", mgr.getClass().getSimpleName());
//		assertNotNull(mgr.getManagedBeans());
//		assertEquals(TestLocatorProvider.MANAGED_BEAN_COUNT, mgr.getManagedBeans().size());
//		ManagedBeanType bean = (ManagedBeanType)mgr.getManagedBeans().get(0);
//		assertTrue( bean.getManagedBeanName().getTextContent().contains(TestLocatorProvider.MANAGED_BEAN_NAME_PREFIX));
//		assertTrue( bean.getManagedBeanClass().getTextContent().contains(TestLocatorProvider.MANAGED_BEAN_CLASS_PREFIX));
//	}
	
	public void testTestableJSFAppConfigManagerSetup() throws Exception {
		projectTestEnvironment.getTestProject().setSessionProperty(JSFAppConfigManagerFactory.TESTABLE_FACTORY_SESSION_KEY, new TestJSFAppConfigManagerFactory());
		IJSFAppConfigManager mgr = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(projectTestEnvironment.getTestProject());
		assertNotNull(mgr);
		assertEquals("TestJSFAppConfigManager", mgr.getClass().getSimpleName());
		assertNotNull(mgr.getManagedBeans());
		assertEquals(TestLocatorProvider.MANAGED_BEAN_COUNT, mgr.getManagedBeans().size());
		ManagedBeanType bean = (ManagedBeanType)mgr.getManagedBeans().get(0);
		assertTrue( bean.getManagedBeanName().getTextContent().contains(TestLocatorProvider.MANAGED_BEAN_NAME_PREFIX));
		assertTrue( bean.getManagedBeanClass().getTextContent().contains(TestLocatorProvider.MANAGED_BEAN_CLASS_PREFIX));
		
		IJSFAppConfigManager mgr2 = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(projectTestEnvironment.getTestProject());
		assertNotNull(mgr2);
		assertEquals(mgr, mgr2);
	}
}
