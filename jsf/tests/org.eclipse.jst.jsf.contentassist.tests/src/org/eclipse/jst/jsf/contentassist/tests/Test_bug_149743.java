/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.contentassist.tests;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * Test a situation where no expected return type information is 
 * available for a tag.
 * 
 * @author cbateman
 *
 */
public class Test_bug_149743 extends TestCase 
{
    private WebProjectTestEnvironment       _testEnv;
    
    protected void setUp() throws Exception 
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        
        _testEnv = new WebProjectTestEnvironment("Test_bug_149743_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());
        
        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/faces-config_bug149743.xml.data", 
                                      "/WEB-INF/faces-config.xml");
        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/bug_149743.jsp.data",
                                      "/bug_149743.jsp");
        
        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
        TestFileResource resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(), 
                      "/testdata/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
    }

    /**
     * Sanity check
     */
    public void testSanity()
    {
        ContextWrapper wrapper = null;
        
        try
        {
            wrapper = getDocumentContext();
            final IStructuredDocumentContext context = wrapper.getContext();
            final IDOMContextResolver resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getDOMContextResolver(context);
            Node node = resolver.getNode();
            assertTrue(node instanceof Attr);
            assertEquals("value", ((Attr)node).getNodeName());
            assertEquals("#{myBean.property}", ((Attr)node).getNodeValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
        finally
        {
            if (wrapper != null)
            {
                wrapper.dispose();
            }
        }
    }
    
    /**
     * Checks the scenario for Test_bug_149743
     */
    public void testCompletionProposalsForId() throws Exception
    {
        ContextWrapper wrapper = null;
        
        try
        {
            wrapper = getDocumentContext();
            ITextRegionContextResolver  resolver = 
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getTextRegionResolver(wrapper.getContext());
    
            final String elText = resolver.getRegionText().trim();
            assertNotNull(elText);

            final ContentAssistStrategy strategy = 
                ContentAssistParser.getPrefix
                    (wrapper.getContext().getDocumentPosition() 
                            - resolver.getStartOffset() + 1, elText);

            List proposals = strategy.getProposals(wrapper.getContext());
            assertEquals(2, proposals.size());
            final ICompletionProposal proposal = (ICompletionProposal) proposals.get(0);
            assertEquals("property", proposal.getDisplayString());
        }
        finally
        {
            if (wrapper != null)
            {
                wrapper.dispose();
            }
        }
    }
    
    private ContextWrapper getDocumentContext() throws Exception
    {
        IProject project = _testEnv.getTestProject();
        IFile jspFile = project.getFile(new Path("/WebContent/bug_149743.jsp"));
        assertTrue(jspFile.exists());
        
        final IModelManager modelManager = 
            StructuredModelManager.getModelManager();

        IStructuredModel model = null;
        
        model = modelManager.getModelForRead(jspFile);
        assertTrue(model instanceof DOMModelForJSP);
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.
                getContext(model.getStructuredDocument(), 548);
        return new ContextWrapper(context, model);
    }

    private static class ContextWrapper
    {
        private final IStructuredDocumentContext _context;
        private final IStructuredModel  _model;
        
        ContextWrapper(final IStructuredDocumentContext context, final IStructuredModel model) {
            super();
            _context = context;
            _model = model;
        }
        IStructuredDocumentContext getContext() {
            return _context;
        }
        IStructuredModel getModel() {
            return _model;
        }
        void dispose()
        {
            _model.releaseFromRead();
        }
    }
}
