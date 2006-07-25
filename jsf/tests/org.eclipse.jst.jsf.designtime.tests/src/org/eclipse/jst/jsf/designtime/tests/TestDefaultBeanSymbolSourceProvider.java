/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.designtime.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.provisional.symbols.DefaultBeanSymbolSourceProvider;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.osgi.framework.Bundle;

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
    
    protected void setUp() throws Exception 
    {
        super.setUp();
        
        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDefaultBeanSymbolSourceProvider_"+getName());
        projectTestEnvironment.createProject();
        _facesConfigFile = (IFile) projectTestEnvironment.
            loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle(),
                                  "/testdata/faces-config.xml.data", 
                                  "/WEB-INF/faces-config.xml");

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize();
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);

        loadSourceClass(TestsPlugin.getDefault().getBundle(), "/testfiles/TestBean1.java.data", "TestBean1");
    }

    protected void tearDown() throws Exception 
    {
        super.tearDown();
        _jsfFactedTestEnvironment.dispose();
        _jdtTestEnvironment.getProjectEnvironment().deleteProject();
    }

    private void loadSourceClass(final Bundle bundle, final String fileName, final String beanClassName) throws Exception
    {
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(bundle, fileName);
        String code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(SRC_FOLDER_NAME, PACKAGE_NAME, beanClassName, code);
    }

    /**
     * Test the external sanity of the suite (i.e. that the test data is in sync)
     */
    public void testSanity()
    {
        DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();
        
        ISymbol[] symbol =
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

    private void testScopeBeans(int scope, int expectedSize, String[] expectedNames)
    {
        DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();
        
        ISymbol[] symbols =
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
        DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();
        
        ISymbol[] symbols =
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
        DefaultBeanSymbolSourceProvider  provider =
            DefaultBeanSymbolSourceProvider.getInstance();

        ISymbol symbol = 
            provider.getSymbol(expectedName, _facesConfigFile, 
                                ISymbolConstants.SYMBOL_SCOPE_ALL);
        
        assertNotNull(symbol);
        assertEquals(expectedName, symbol.getName());
    }
}
