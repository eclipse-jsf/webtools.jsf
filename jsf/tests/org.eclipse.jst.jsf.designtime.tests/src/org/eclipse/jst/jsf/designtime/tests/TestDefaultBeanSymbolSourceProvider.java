/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
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
package org.eclipse.jst.jsf.designtime.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.symbols.DefaultBeanSymbolSourceProvider;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * JUnit tests for org.eclipse.jst.jsf.designtime.DefaultBeanSymbolSourceProvider
 * 
 * @author cbateman
 *
 */
public class TestDefaultBeanSymbolSourceProvider extends TestCase 
{
    private JDTTestEnvironment              _jdtTestEnvironment;
    private JSFFacetedTestEnvironment       _jsfFactedTestEnvironment;
    private IFile                           _facesConfigFile;
    
    private final static String     SRC_FOLDER_NAME = "src";
    private final static String     PACKAGE_NAME = "com.test";
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDefaultBeanSymbolSourceProvider_"+getName());
        projectTestEnvironment.createProject(false);
        _facesConfigFile = (IFile) projectTestEnvironment.
            loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle(),
                                  "/testdata/faces-config.xml.data", 
                                  "/WEB-INF/faces-config.xml");

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);

        JSFTestUtil.loadSourceClass(
                TestsPlugin.getDefault().getBundle(), 
                    "/testfiles/TestBean1.java.data", "TestBean1", SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
    }

    @Override
    protected void tearDown() throws Exception 
    {
        super.tearDown();
    }

    /**
     * Test the external sanity of the suite (i.e. that the test data is in sync)
     */
    public void testSanity()
    {
        final DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();
        
        final ISymbol[] symbol =
            provider.getSymbols(_facesConfigFile, ISymbolConstants.SYMBOL_SCOPE_ALL);
        
        assertNotNull(symbol);
        assertEquals("Check that test suite is in sync with setup()", symbol.length, 4);
    }
    
    /**
     * 
     */
    public void testAllScopes()
    {
        final String[]  names = new String[]{"myBean_request", "myBean_session", "myBean_application", "myBean_none"};
        testScopeBeans(ISymbolConstants.SYMBOL_SCOPE_ALL, names.length, names);
    }
    
    /**
     * Check mask by request scope
     */
    public void testOnlyRequestBeans()
    {
        final String[]  names = new String[]{"myBean_request"};
        testScopeBeans(ISymbolConstants.SYMBOL_SCOPE_REQUEST, names.length, names);
    }
    
    /**
     * Check mask by Session scope
     */
    public void testOnlySessionBeans()
    {
        final String[]  names = new String[]{"myBean_session"};
        testScopeBeans(ISymbolConstants.SYMBOL_SCOPE_SESSION, names.length, names);
    }
    
    /**
     * Check mask by Application scope
     */
    public void testOnlyApplicationBeans()
    {
        final String[]  names = new String[]{"myBean_application"};
        testScopeBeans(ISymbolConstants.SYMBOL_SCOPE_APPLICATION, names.length, names);
    }
    /**
     * Check mask by None scope
     */
    public void testOnlyNoneBeans()
    {
        final String[]  names = new String[]{"myBean_none"};
        testScopeBeans(ISymbolConstants.SYMBOL_SCOPE_NONE, names.length, names);
    }

    private void testScopeBeans(final int scope, final int expectedSize, final String[] expectedNames)
    {
        final DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();
        
        final ISymbol[] symbols =
            provider.getSymbols(_facesConfigFile, scope);
        
        assertEquals(expectedSize, symbols.length);
        
        for (int j = 0; j < expectedNames.length; j++)
        {
            final String expectedName = expectedNames[j];
            assertNotNull(expectedName);
            boolean found = false;
            
            FIND_IN_SYMBOLS:
            for (int i = 0; i < symbols.length; i++)
            {
                if (expectedName.equals(symbols[i].getName()))
                {
                    found = true;
                    break FIND_IN_SYMBOLS;
                }
            }
            
            assertTrue(found);
        }
    }
    
    /**
     * Test the get symbol by prefix
     */
    public void testPrefixSearch()
    {
        final DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();
        
        final ISymbol[] symbols =
            provider.getSymbols("myBean_n", _facesConfigFile, ISymbolConstants.SYMBOL_SCOPE_ALL);

        assertEquals(1, symbols.length);
        assertEquals("myBean_none", symbols[0].getName());
    }
    
    /**
     * Test getting a symbol it's name
     */
    public void testGetSymbolByName()
    {
        testGetSymbolByName("myBean_none");
        testGetSymbolByName("myBean_application");
        testGetSymbolByName("myBean_session");
        testGetSymbolByName("myBean_request");
    }
    
    private void testGetSymbolByName(final String expectedName)
    {
        final DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();

        final ISymbol symbol = 
            provider.getSymbol(expectedName, _facesConfigFile, 
                                ISymbolConstants.SYMBOL_SCOPE_ALL);
        
        assertNotNull(symbol);
        assertEquals(expectedName, symbol.getName());
    }
}
