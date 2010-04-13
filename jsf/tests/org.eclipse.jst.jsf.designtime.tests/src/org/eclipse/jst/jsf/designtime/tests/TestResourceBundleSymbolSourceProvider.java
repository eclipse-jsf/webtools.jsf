/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleSymbolSourceProvider;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType;
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
                    "TestResourceBundleSymbolSourceProvider_"+getName()
                    , JavaFacet.VERSION_1_5
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

	public void testSanity()
	{
        final IJSFAppConfigManager appconfigMgr = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(_projectTestEnvironment.getTestProject());
        final List<ResourceBundleType> resourceBundles = appconfigMgr.getResourceBundles();
        assertEquals(2, resourceBundles.size());
	}
	
	public void testGetSymbolsIAdaptableInt()
	{
		final ResourceBundleSymbolSourceProvider  sourceProvider =
			new ResourceBundleSymbolSourceProvider();
		
		final ISymbol[]  symbols = 
			sourceProvider.getSymbols(_testJSP, ISymbolConstants.SYMBOL_SCOPE_ALL);
		assertEquals(2, symbols.length);
		
		final List<String>  testProps = new ArrayList<String>();
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
    private void assertContains(final ISymbol[] symbols, final String varName,final List<String> properties)
	{
	    final List<String>  copyProperties = new ArrayList<String>(properties);
	    final List<String>  propsNotFound = new ArrayList<String>();
	    
	    boolean  foundSymbol = false;
	    
	    for (final ISymbol symbol : symbols)
	    {
	        if (varName.equals(symbol.getName()))
	        {
	            foundSymbol = true;
	            assertTrue(symbol instanceof IInstanceSymbol);
	            final IInstanceSymbol varSymbol = (IInstanceSymbol) symbol;
	            final List<IPropertySymbol> props = 
	                varSymbol.getTypeDescriptor().getProperties();

	            for (final Object element : props)
                {
	                final IPropertySymbol prop = (IPropertySymbol) element;
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
