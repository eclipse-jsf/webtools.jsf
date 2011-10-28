package org.eclipse.jst.jsf.designtime.tests;


import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.el.DefaultDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.DefaultDTVariableResolver;
import org.eclipse.jst.jsf.designtime.symbols.DefaultBuiltInSymbolProvider;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.junit.Before;

/**
 * Tests the implicit JSF2.0 variables excercising {@link DefaultBuiltInSymbolProvider}, {@link DefaultDTVariableResolver}, and {@link DefaultDTPropertyResolver}
 *
 */
public class TestJSF20ImplicitVariables extends TestCase
{
    private IType                       _testBean1Type;
	private JSFFacetedTestEnvironment 	_jsfFactedTestEnvironment;
	private JDTTestEnvironment 			_jdtTestEnvironment;
	private IFile						_testJSP1;
	
    private final static String     SRC_FOLDER_NAME = "src";
    private final static String     PACKAGE_NAME = "com.test";
    private final static String     TESTBEAN1_NAME = "TestBean1";
	private static final String 	ATTRS_SYMBOL_NAME = "attrs";
	
	@Before
	public void setUp() throws Exception {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestJSF20ImplicitVariables_"+getName(), JavaFacet.VERSION_1_5, ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.5"));       
        projectTestEnvironment.createProject(true);

        final IResource res = projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP1 = (IFile) res;
        
        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_2_0);
        
        final IProject project = projectTestEnvironment.getTestProject();
        
        FacesConfigArtifactEdit edit = null;
        
        try
        {
        	edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(project, null);
        
        	final FacesConfigType model = edit.getFacesConfig();
        	final ManagedBeanClassType beanClass = FacesConfigFactory.eINSTANCE.createManagedBeanClassType();
        	beanClass.setTextContent("com.test.TestBean1");

        	final ManagedBeanNameType beanName = FacesConfigFactory.eINSTANCE.createManagedBeanNameType();
        	beanName.setTextContent("testBean1");
        	
        	final ManagedBeanScopeType beanScope = FacesConfigFactory.eINSTANCE.createManagedBeanScopeType();
        	beanScope.setTextContent("session");
        	
        	final ManagedBeanType bean = FacesConfigFactory.eINSTANCE.createManagedBeanType();
        	bean.setManagedBeanClass(beanClass);
        	bean.setManagedBeanName(beanName);
        	bean.setManagedBeanScope(beanScope);
        	
        	model.getManagedBean().add(bean);
        	
        	edit.save(null);
        }
        finally
        {
        	if (edit != null)
        	{
        		edit.dispose();
        	}
        }
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
        
        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        _jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");

        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/TestBean1.java.data", TESTBEAN1_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBean1Type = _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+TESTBEAN1_NAME);
        assertNotNull(_testBean1Type);
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
    
	}
	
	public void testCCSymbol() {
		DefaultDTVariableResolver resolver = new DefaultDTVariableResolver();
		DTFacesContext context = DesignTimeApplicationManager.getInstance(_jdtTestEnvironment.getJavaProject().getProject()).getFacesContext(_testJSP1);
		ISymbol symbol = resolver.resolveVariable(context, "cc", _testJSP1);
		assertNotNull(symbol);
		assertTrue(symbol instanceof IInstanceSymbol);
		assertEquals(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL, ((IInstanceSymbol)symbol).getRuntimeSource());
		
		DefaultDTPropertyResolver propresolver = new DefaultDTPropertyResolver();
		propresolver.setProject(_jdtTestEnvironment.getProjectEnvironment().getTestProject());
		ISymbol[] props = propresolver.getAllProperties(symbol);
		assertContainsVariable(props, ATTRS_SYMBOL_NAME);
//		assertContainsVariable(props, "attributes"); //need real jars on cp
		
		//tests "attrs"
		ISymbol propSymbol = propresolver.getProperty(symbol, ATTRS_SYMBOL_NAME);
		assertNotNull(propSymbol);
		assertTrue(propSymbol instanceof IInstanceSymbol);
		ITypeDescriptor typeDesc = ((IInstanceSymbol)propSymbol).getTypeDescriptor();
		assertTrue(typeDesc instanceof IBoundedMapTypeDescriptor);
		
//		assertNull(propresolver.getProperty(symbol, "unknown"));
	}
	
	public void testComponentSymbol() {
		DefaultDTVariableResolver resolver = new DefaultDTVariableResolver();
		DTFacesContext context = DesignTimeApplicationManager.getInstance(_jdtTestEnvironment.getJavaProject().getProject()).getFacesContext(_testJSP1);
		ISymbol symbol = resolver.resolveVariable(context, "component", _testJSP1);
		assertNotNull(symbol);
		assertTrue(symbol instanceof IInstanceSymbol);
		assertEquals(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL, ((IInstanceSymbol)symbol).getRuntimeSource());
		
		DefaultDTPropertyResolver propresolver = new DefaultDTPropertyResolver();
		propresolver.setProject(_jdtTestEnvironment.getProjectEnvironment().getTestProject());
		ISymbol[] props = propresolver.getAllProperties(symbol);
		assertContainsVariable(props, ATTRS_SYMBOL_NAME);
//		assertContainsVariable(props, "attributes");		//need real jars on cp
		
		//tests "attrs" for component
		ISymbol propSymbol = propresolver.getProperty(symbol, ATTRS_SYMBOL_NAME);
		assertNotNull(propSymbol);
		assertTrue(propSymbol instanceof IInstanceSymbol);
		ITypeDescriptor typeDesc = ((IInstanceSymbol)propSymbol).getTypeDescriptor();
		assertTrue(typeDesc instanceof IBoundedMapTypeDescriptor);
		
//		assertNull(propresolver.getProperty(symbol, "unknown"));
		
	}
	
	public void testResourceSymbol() {
		DefaultDTVariableResolver resolver = new DefaultDTVariableResolver();
		DTFacesContext context = DesignTimeApplicationManager.getInstance(_jdtTestEnvironment.getJavaProject().getProject()).getFacesContext(_testJSP1);
		ISymbol symbol = resolver.resolveVariable(context, "resource", _testJSP1);
		assertNotNull(symbol);
		assertTrue(symbol instanceof IInstanceSymbol);
		assertEquals(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL, ((IInstanceSymbol)symbol).getRuntimeSource());
		ITypeDescriptor typeDesc = ((IInstanceSymbol)symbol).getTypeDescriptor();
		assertTrue(typeDesc instanceof IBoundedMapTypeDescriptor);
	}
	
	public void testViewScopeSymbol() {
		DefaultDTVariableResolver resolver = new DefaultDTVariableResolver();
		DTFacesContext context = DesignTimeApplicationManager.getInstance(_jdtTestEnvironment.getJavaProject().getProject()).getFacesContext(_testJSP1);
		ISymbol symbol = resolver.resolveVariable(context, "viewScope", _testJSP1);
		assertNotNull(symbol);
		assertTrue(symbol instanceof IInstanceSymbol);
		assertEquals(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL, ((IInstanceSymbol)symbol).getRuntimeSource());
		ITypeDescriptor typeDesc = ((IInstanceSymbol)symbol).getTypeDescriptor();
		assertTrue(typeDesc instanceof IBoundedMapTypeDescriptor);

	}
	
	public void testFlashScopeSymbol() {
		DefaultDTVariableResolver resolver = new DefaultDTVariableResolver();
		DTFacesContext context = DesignTimeApplicationManager.getInstance(_jdtTestEnvironment.getJavaProject().getProject()).getFacesContext(_testJSP1);
		ISymbol symbol = resolver.resolveVariable(context, "flash", _testJSP1);
		assertNotNull(symbol);
		assertTrue(symbol instanceof IInstanceSymbol);
		assertEquals(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL, ((IInstanceSymbol)symbol).getRuntimeSource());
		ITypeDescriptor typeDesc = ((IInstanceSymbol)symbol).getTypeDescriptor();
		assertTrue(typeDesc instanceof IBoundedMapTypeDescriptor);
	}
	
	public void testGetAllVariables() 
	{
        final DesignTimeApplicationManager manager =
        	DesignTimeApplicationManager.getInstance
        		(_jdtTestEnvironment.getProjectEnvironment().getTestProject());

		final DefaultDTVariableResolver  variableResolver = new DefaultDTVariableResolver();
		
		final ISymbol[] variables = variableResolver.getAllVariables
			(manager.getFacesContext(_testJSP1), _testJSP1);
		
		assertEquals(19, variables.length);
		
		//jsf1.x - implicits
		assertContainsVariable(variables, "applicationScope");
		assertContainsVariable(variables, "sessionScope");
		assertContainsVariable(variables, "requestScope");
		assertContainsVariable(variables, "cookie");
		assertContainsVariable(variables, "facesContext");
		assertContainsVariable(variables, "header");
		assertContainsVariable(variables, "headerValues");
		assertContainsVariable(variables, "initParam");
		assertContainsVariable(variables, "param");
		assertContainsVariable(variables, "paramValues");
		assertContainsVariable(variables, "view");
		
		//jsf2.0 - implicits
		assertContainsVariable(variables, "viewScope");
		assertContainsVariable(variables, "flash");
		assertContainsVariable(variables, "cc");
		assertContainsVariable(variables, "component");
		assertContainsVariable(variables, "resource");
		
		//external
		assertContainsVariable(variables, "testBean1");
		assertContainsVariable(variables, "bundle");
		assertContainsVariable(variables, "row");
		
	}

	private void assertContainsVariable(final ISymbol[] variables, final String name)
	{
		for (final ISymbol variable : variables)
		{
			if (name.equals(variable.getName()))
			{
				assertTrue(variable instanceof IInstanceSymbol);
				return;
			}
		}
		
		fail("Expected variable not found: "+name);
	}
}
