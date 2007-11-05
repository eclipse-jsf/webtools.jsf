package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleSymbolSourceProvider;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class TestResourceBundleSymbolSourceProvider extends TestCase 
{
	private IFile _testJSP;
	private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;
    private WebProjectTestEnvironment _projectTestEnvironment;

	@Override
	protected void setUp() throws Exception 
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        _projectTestEnvironment = 
            new WebProjectTestEnvironment(
                    "TestResourceBundleSymbolSourceProvider"+getName()
                    , JavaFacetUtils.JAVA_50
                    , ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.5")
                    );
        _projectTestEnvironment.createProject(false);

        final JDTTestEnvironment jdtTestEnvironment = 
        	new JDTTestEnvironment(_projectTestEnvironment);

        TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle()
                ,"/testdata/bundle1.resources.data");
        jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "beans", "bundle.properties");

        input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle()
                ,"/testdata/bundle2.resources.data");
        jdtTestEnvironment.addResourceFile("src"
                , new ByteArrayInputStream(input.toBytes())
                , "beans", "bundle2.properties");

        /*IFile facesConfigFile = (IFile) */
        _projectTestEnvironment
        	.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle(),
        							"/testdata/faces-config_1_2.xml.data", 
        							"/WEB-INF/faces-config.xml");
        
        final IResource res = 
        	_projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP = (IFile) res;

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(_projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_2);
        
        //_structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        //_structuredDocument = _structuredModel.getStructuredDocument();
	}

	@SuppressWarnings("unchecked")
    public void testSanity()
	{
        final JSFAppConfigManager appconfigMgr = JSFAppConfigManager.getInstance(_projectTestEnvironment.getTestProject());
        final List resourceBundles = appconfigMgr.getResourceBundles();
        assertEquals(2, resourceBundles.size());
	}
	
	public void testGetSymbolsIAdaptableInt()
	{
		final ResourceBundleSymbolSourceProvider  sourceProvider =
			new ResourceBundleSymbolSourceProvider();
		
		ISymbol[]  symbols = 
			sourceProvider.getSymbols(_testJSP, ISymbolConstants.SYMBOL_SCOPE_ALL);
		assertEquals(2, symbols.length);
		
		List<String>  testProps = new ArrayList<String>();
		testProps.add("prop1");
		// these two are dotted and so only the first segment is used in the base property
        testProps.add("one");
        testProps.add("two");
		
		assertContains(symbols, "resBundleProp1", testProps);
		
		testProps.clear();
		testProps.add("x_prop1");
        // these two are dotted and so only the first segment is used in the base property
		testProps.add("x_one");
		testProps.add("x_two");

		assertContains(symbols, "resBundleProp2", testProps);
	}

	@SuppressWarnings({ "unchecked"})
    private void assertContains(ISymbol[] symbols, final String varName,List<String> properties)
	{
	    List<String>  copyProperties = new ArrayList<String>(properties);
	    List<String>  propsNotFound = new ArrayList<String>();
	    
	    boolean  foundSymbol = false;
	    
	    for (ISymbol symbol : symbols)
	    {
	        if (varName.equals(symbol.getName()))
	        {
	            foundSymbol = true;
	            assertTrue(symbol instanceof IInstanceSymbol);
	            IInstanceSymbol varSymbol = (IInstanceSymbol) symbol;
	            List<IPropertySymbol> props = 
	                varSymbol.getTypeDescriptor().getProperties();

	            for (Iterator it = props.iterator(); it.hasNext();)
	            {
	                IPropertySymbol prop = (IPropertySymbol) it.next();
	                if (copyProperties.contains(prop.getName()))
	                {
	                    copyProperties.remove(prop.getName());
	                }
	                else
	                {
	                    propsNotFound.add(prop.getName());
	                }
	            }
	        }
	    }
	    
	    assertTrue("Symbol not found", foundSymbol);
	    assertTrue(String.format("%d properties not found, %s not found, not matched %s"
	                                , copyProperties.size()
	                                , Arrays.toString(copyProperties.toArray(new String[0]))
	                                , Arrays.toString(propsNotFound.toArray(new String[0])))
	               ,copyProperties.isEmpty());
	}
//	public void testIsProvider() 
//	{
//		fail("Not yet implemented");
//	}
}
