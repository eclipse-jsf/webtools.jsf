package org.eclipse.jst.jsf.designtime.tests;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.el.DefaultDTMethodResolver;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestDefaultDTMethodResolver extends TestCase 
{
    private JDTTestEnvironment              _jdtTestEnvironment;
    private JSFFacetedTestEnvironment       _jsfFactedTestEnvironment;

    private IType                           _methodBeanType;
    
    private final static String     SRC_FOLDER_NAME = "src";
    private final static String     PACKAGE_NAME = "com.test";
    private final static String     TESTBEAN1_NAME = "MethodBean";
    
    @Override
	protected void setUp() throws Exception
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDefaultMethodResolver_"+getName());
        projectTestEnvironment.createProject(false);

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);

        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/MethodBean.java.data", TESTBEAN1_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _methodBeanType = 
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+TESTBEAN1_NAME);
        assertNotNull(_methodBeanType);
	}

	@Override
	protected void tearDown() throws Exception 
	{
		super.tearDown();
		
        _jdtTestEnvironment.getJavaProject().getProject().delete(true, null);
	}

	public void testGetMethod() 
	{
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myBean2");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_methodBeanType);
        symbol.setTypeDescriptor(typeDesc);

        final DefaultDTMethodResolver  methodResolver = new DefaultDTMethodResolver();
        
        IMethodSymbol methodSymbol = methodResolver.getMethod(symbol, "actionMethod");
        assertNotNull(methodSymbol);
        assertEquals("actionMethod", methodSymbol.getName());
        assertEquals("()Ljava.lang.String;", methodSymbol.getSignature());
        
        methodSymbol = methodResolver.getMethod(symbol, "actionMethodWithParam");
        assertNotNull(methodSymbol);
        assertEquals("actionMethodWithParam", methodSymbol.getName());
        assertEquals("(Ljava.lang.String;)Ljava.lang.String;", methodSymbol.getSignature());
        
        // method not found
        methodSymbol = methodResolver.getMethod(symbol, "notAMethod");
        assertNull(methodSymbol);
	}

	public void testGetMethods() 
	{
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myBean2");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_methodBeanType);
        symbol.setTypeDescriptor(typeDesc);

        final DefaultDTMethodResolver  methodResolver = new DefaultDTMethodResolver();

        final ISymbol[] symbols = methodResolver.getMethods(symbol);
        assertNotNull(symbols);
        assertEquals(11, symbols.length);
        assertContains(symbols, "actionMethod", "()Ljava.lang.String;");
        assertContains(symbols, "actionMethodWithParam", "(Ljava.lang.String;)Ljava.lang.String;");
	}

	private void assertContains(final ISymbol[] methods, final String name, final String signature)
	{
		IMethodSymbol methodSymbol = null;
		
		for (final ISymbol symbol : methods)
        {
			if (name.equals(symbol.getName()))
			{
				assertTrue(symbol instanceof IMethodSymbol);
				if (((IMethodSymbol)symbol).getSignature().equals(signature))
				{
					methodSymbol = (IMethodSymbol) symbol;
				}
			}
		}
		
		assertNotNull(methodSymbol);
	}
}
