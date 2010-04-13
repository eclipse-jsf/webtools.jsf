package org.eclipse.jst.jsf.core.tests.appconfig;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.CompositeJSFAppConfigLocatorProviderStrategy;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigLocatorProvider;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.junit.Test;

public class CompositeJSFAppConfigLocatorProviderStrategyTests extends TestCase {
		WebProjectTestEnvironment projectTestEnvironment;
		JDTTestEnvironment jdtTestEnv;
		
		protected void setUp() throws Exception {
			super.setUp();
			
	        JSFTestUtil.setValidationEnabled(false);
	        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
	        
	        projectTestEnvironment = 
	            new WebProjectTestEnvironment("CompositeJSFAppConfigLocatorProviderStrategyTests"+"_"+getName());
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
		
//test ext-pt removed as it causes other tests to fail
//	@Test
//	public void testExtPtLocatorProvider() {
//		//uses the TestLocatorProvider that was loaded using ext-pt (currently)
//		CompositeLocatorProviderStrategy strategy = new CompositeLocatorProviderStrategy(projectTestEnvironment.getTestProject(), null);
//		assertNotNull(strategy.getLocators());
//		assertEquals(5, strategy.getLocators().size());//expecting FakeProvider + default set	
//		assertTrue(strategy.getLocators().get(0).getClass().getSimpleName().equals("FakeLocator"));
//	
//		IJSFAppConfigManager mgr = JSFAppConfigManagerFactory.getInstance(projectTestEnvironment.getTestProject());
//		assertNotNull(mgr.getManagedBeans());
//		assertEquals(TestLocatorProvider.MANAGED_BEAN_COUNT, mgr.getManagedBeans().size());
//		ManagedBeanType bean = (ManagedBeanType)mgr.getManagedBeans().get(0);
//		assertTrue( bean.getManagedBeanName().getTextContent().contains(TestLocatorProvider.MANAGED_BEAN_NAME_PREFIX));
//		assertTrue( bean.getManagedBeanClass().getTextContent().contains(TestLocatorProvider.MANAGED_BEAN_CLASS_PREFIX));
//	}
	
	@Test
	public void testTestableLocatorProvider() throws Exception {
		//uses the TestLocatorProvider
		int EXPECTED_BEAN_COUNT = 3;
		IJSFAppConfigLocatorProvider testLocatorProvider = new TestLocatorProvider(EXPECTED_BEAN_COUNT);
		projectTestEnvironment.getTestProject().setSessionProperty(CompositeJSFAppConfigLocatorProviderStrategy.TESTABLE_FACTORY_SESSION_KEY, testLocatorProvider);
		CompositeJSFAppConfigLocatorProviderStrategy strategy = new CompositeJSFAppConfigLocatorProviderStrategy(projectTestEnvironment.getTestProject());
		assertNotNull(strategy.getLocators());
		assertEquals(5, strategy.getLocators().size());//expecting FakeProvider + default set	
		assertTrue(strategy.getLocators().get(0).getClass().getSimpleName().equals("FakeLocator"));
	}
	
	public void testDefaultLocatorProvider() {
		//since we are not using an extension, if no testable is provided, must be using default
		CompositeJSFAppConfigLocatorProviderStrategy strategy = new CompositeJSFAppConfigLocatorProviderStrategy(projectTestEnvironment.getTestProject());
		assertNotNull(strategy.getLocators());
		assertEquals(4, strategy.getLocators().size());//expecting default set only	
		assertTrue(strategy.getLocators().get(0).getClass().getSimpleName().equals("ImplicitRuntimeJSFAppConfigLocater"));

	}

}
