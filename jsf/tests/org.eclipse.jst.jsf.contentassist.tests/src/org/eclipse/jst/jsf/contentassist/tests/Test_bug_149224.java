package org.eclipse.jst.jsf.contentassist.tests;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
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
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * Regression test for 149224 -- replace '.' on completion with [] style of
 * map
 * 
 * @author cbateman
 *
 */
public class Test_bug_149224 extends TestCase 
{
    private WebProjectTestEnvironment       _testEnv;
    
    protected void setUp() throws Exception 
    {
        super.setUp();

        _testEnv = new WebProjectTestEnvironment("Test_bug_149224_"+getName());
        _testEnv.createProject();
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());
        
        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/bug_149224_1.jsp.data",
                                      "/bug_149224.jsp");
        
        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
        TestFileResource resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(), 
                      "/testdata/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
        
        resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
                      "/testdata/bug_149224.properties.data");
        jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()),
                                   "bundles", "bundle1.properties");
    }

    /**
     * Sanity check
     */
    public void testSanity()
    {
        ContextWrapper wrapper = null;
        
        try
        {
            wrapper = getDocumentContext("/WebContent/bug_149224.jsp", 609);
            IStructuredDocumentContext context = wrapper.getContext();
            IDOMContextResolver resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getDOMContextResolver(context);
            Node node = resolver.getNode();
            assertTrue(node instanceof Attr);
            assertEquals("value", ((Attr)node).getNodeName());
            assertEquals("#{bundle1.}", ((Attr)node).getNodeValue());
            wrapper.dispose();
            
            wrapper = getDocumentContext("/WebContent/bug_149224.jsp", 650);
            context = wrapper.getContext();
            resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getDOMContextResolver(context);
            node = resolver.getNode();
            assertTrue(node instanceof Attr);
            assertEquals("value", ((Attr)node).getNodeName());
            assertEquals("#{bundle1.x}", ((Attr)node).getNodeValue());
            
            ISymbolContextResolver symbolResolver = 
                StructuredDocumentSymbolResolverFactory.getInstance().
                    getSymbolContextResolver(context);
            ISymbol bundleVar = symbolResolver.getVariable("bundle1");
            assertNotNull(bundleVar);
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
     * Test the completion:
     * 
     *      # { b u n d l e 1 . }
     *                         ^ 
     */
    public void testCompletionAtCloseBrace()
    {
        ContextWrapper wrapper = null;
        
        try
        {
            wrapper = getDocumentContext("/WebContent/bug_149224.jsp", 609);
            final IStructuredDocumentContext context = wrapper.getContext();
            ITextRegionContextResolver resolver = 
                IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);
            final ContentAssistStrategy strategy = 
                ContentAssistParser.getPrefix(9, "bundle1.");
            
            assertNotNull(strategy);
            List proposals = strategy.getProposals(context);
            
            ICompletionProposal proposal = null;
            
            FIND_ARRAY_PROPOSAL: 
                for (final Iterator it = proposals.iterator(); it.hasNext();)
            {
                proposal = (ICompletionProposal) it.next();
                // TODO: this is a bit of a hack.  Would rather be able 
                // to query for the actual replacement text
                if (proposal.getDisplayString().startsWith("['"))
                {
                    break FIND_ARRAY_PROPOSAL;
                }
            }
            
            assertNotNull(proposal);
            
            proposal.apply(wrapper.getContext().getStructuredDocument());
            
            String newELText = resolver.getRegionText();
            assertEquals("bundle1['prop.with.dots_x']", newELText);
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
     * Test the completion:
     * 
     *      # { b u n d l e 1 . x }
     *                         ^ 
     */
    public void testCompletionAtProperty()
    {
        ContextWrapper wrapper = null;
        
        try
        {
            wrapper = getDocumentContext("/WebContent/bug_149224.jsp", 609);
            final IStructuredDocumentContext context = wrapper.getContext();
            ITextRegionContextResolver resolver = 
                IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);
            final ContentAssistStrategy strategy = 
                ContentAssistParser.getPrefix(9, "bundle1.x");
            
            assertNotNull(strategy);
            List proposals = strategy.getProposals(context);
            
            ICompletionProposal proposal = null;
            
            FIND_ARRAY_PROPOSAL: 
                for (final Iterator it = proposals.iterator(); it.hasNext();)
            {
                proposal = (ICompletionProposal) it.next();
                // TODO: this is a bit of a hack.  Would rather be able 
                // to query for the actual replacement text
                if (proposal.getDisplayString().startsWith("['"))
                {
                    break FIND_ARRAY_PROPOSAL;
                }
            }

            assertNotNull(proposal);

            proposal.apply(wrapper.getContext().getStructuredDocument());

            String newELText = resolver.getRegionText();
            assertEquals("bundle1['prop.with.dots_x']", newELText);
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


    private ContextWrapper getDocumentContext(String path, int offset) throws Exception
    {
        IProject project = _testEnv.getTestProject();
        IFile jspFile = project.getFile(new Path(path));
        assertTrue(jspFile.exists());
        
        final IModelManager modelManager = 
            StructuredModelManager.getModelManager();

        IStructuredModel model = null;
        
        model = modelManager.getModelForRead(jspFile);
        assertTrue(model instanceof DOMModelForJSP);
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.
                getContext(model.getStructuredDocument(), offset);
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
