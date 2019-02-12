/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.tagmatcher;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tagmatcher.XPathMatchingAlgorithm;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;



public class BaseTagMatcherTestCase extends TestCase {
    /**
     * The dynamic web project test environment
     */
    protected WebProjectTestEnvironment  _testEnv;
    /**
     * A handle to the Java project test environment
     */
    protected JDTTestEnvironment         _jdtTestEnv;
    /**
     * Name of the test data file containing the JSP source for this test
     */
    protected String                  _srcFileName;
    /**
     * Name of the file and path where the JSP source should be put in the
     * test project
     */
    protected String                  _destFileName;
    
    /**
     * The file handle to the JSP in the workspace
     */
    protected IFile                   _testJSP;
    /**
     * The SSE structured model for the JSP
     */
    protected IStructuredModel        _structuredModel;
    /**
     * The SSE structured document for the JSP
     */
    protected IStructuredDocument     _structuredDocument;

    protected void setUp() throws Exception    
    {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");
        
        _testEnv = new WebProjectTestEnvironment("ELValidationTest_"+this.getClass().getName()+"_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        // load a dummy tld for core
        // NOTE: the TLDs seem to need to be loaded BEFORE any JSPs that use them.
        // probably adding the jsp kicks off something that searches for TLD's that doesn't
        // get kicked if the TLDs are added after.
        _testEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
    			, "/testfiles/myfaces_core.tld.data", "META-INF/myfaces_core.tld");

        _testEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
    			, "/testfiles/myfaces_html.tld.data", "META-INF/myfaces_html.tld");

        _testJSP = (IFile) _testEnv.loadResourceInWebRoot
            (TestsPlugin.getDefault().getBundle(),
                    _srcFileName, _destFileName);

        _structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        _structuredDocument = _structuredModel.getStructuredDocument();
        
        // 	initialize test case for faces 1.1
        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

    }
    
    protected void tearDown() throws Exception 
    {
        super.tearDown();
        
        if (_structuredModel != null)
        {
            _structuredModel.releaseFromRead();
        }
        _testEnv.getTestProject().close(null);
    }
    
    protected final AxiomaticSet getAncestorsOf(String xpathToChild, int expectedAncestors)
    {
        Document doc = ((IDOMModel)_structuredModel).getDocument();
        XPathMatchingAlgorithm matcher = new XPathMatchingAlgorithm(xpathToChild);
        AxiomaticSet  set = matcher.evaluate(doc);
        assertEquals(1, set.size());
     
        // get all of the ancestors of the inputText
        final Node inputText = (Node) set.getFirstElement();
        matcher = new XPathMatchingAlgorithm("ancestor::*");
        AxiomaticSet result =  matcher.evaluate(inputText);

        assertNotNull(result);

        if (expectedAncestors >= 0)
        {
        	assertEquals(expectedAncestors, result.size());
        }

        return result;
    }
}
