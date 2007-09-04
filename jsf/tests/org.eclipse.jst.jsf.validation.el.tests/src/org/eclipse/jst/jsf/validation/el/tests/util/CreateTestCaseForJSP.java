package org.eclipse.jst.jsf.validation.el.tests.util;

import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionCollection;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;

/**
 * Utility class for generating test skeleton for a single JSP test
 * 
 * @author cbateman
 */
public class CreateTestCaseForJSP extends TestCase 
{
    private final static String  jspFile = "greaterThanEq";
    private static IFile  file;
    private static IStructuredModel model;

    private WebProjectTestEnvironment  _testEnv;

    protected void setUp() throws Exception {
        super.setUp();

        _testEnv = new WebProjectTestEnvironment("ELValidationTest_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);    
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        file = 
            (IFile)
            _testEnv.loadResourceInWebRoot(
                ELValidationTestPlugin.getDefault().getBundle(),
                "/testdata/jsps/"+jspFile+".jsp.data", 
                "/WEB-INF/"+jspFile+".jsp");

        model = StructuredModelManager.getModelManager().getModelForRead(file);

    }

    protected void tearDown() throws Exception 
    {
        super.tearDown();
        model.releaseFromRead();
    }
    
    /**
     * Test gen
     */
    public void testDoTestGen()
    {
        System.out.println("    protected void setUp() throws Exception"); 
        System.out.println("    {");
        System.out.println("        _srcFileName = \"/testdata/jsps/"+jspFile+".jsp.data\";");
        System.out.println("        _destFileName = \"/"+jspFile+".jsp\";");
        System.out.println("        super.setUp();");
        System.out.println("    }\n");

        System.out.println("    public void testSanity()");
        System.out.println("    {");
        ELRegionHandler  handler = new ELRegionHandler()
        {
            public void handleRegion(ITextRegionCollection parentRegion, ITextRegion elRegion) {
                final int contentStart = 
                    parentRegion.getStartOffset(elRegion);
                
                final String elTextStr = "\""+parentRegion.getText(elRegion)+ "\"";
                
                System.out.println("        assertEquals("+elTextStr+", getELText(_structuredDocument,"+contentStart+"));");
            }
        };
        processJSP(model.getStructuredDocument(), handler);
        System.out.println("    }");
    }

    /**
     * Process a single JSP files
     * 
     * @param document
     * @param handler
     */
    public static void processJSP(IStructuredDocument document, ELRegionHandler handler)
    {
        IStructuredDocumentRegion curNode = document.getFirstStructuredDocumentRegion();
        while (null != curNode)
        {
            if (curNode.getFirstRegion().getType() == DOMRegionContext.XML_TAG_OPEN ) 
            {
                Iterator<?> regions = curNode.getRegions().iterator();
                ITextRegion region = null;

                while (regions.hasNext()) 
                {
                    region = (ITextRegion) regions.next();

                    if (region instanceof ITextRegionCollection
                            && region.getType() == DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE)
                    {
                        ITextRegionCollection parentRegion = (ITextRegionCollection) region;
                        final ITextRegionList  regionList = parentRegion.getRegions();
                        if (regionList.size() >= 4)
                        {
                            ITextRegion  openQuote = regionList.get(0);
                            ITextRegion  openVBLQuote = regionList.get(1);
        
                            if (    (openQuote.getType() == DOMJSPRegionContexts.XML_TAG_ATTRIBUTE_VALUE_DQUOTE
                                        || openQuote.getType() == DOMJSPRegionContexts.XML_TAG_ATTRIBUTE_VALUE_SQUOTE)
                                        && (openVBLQuote.getType() == DOMJSPRegionContexts.JSP_VBL_OPEN))
                            {
                                // we appear to be inside "#{", so next should be a VBL_CONTENT if there's anything
                                // here to validate
                                final ITextRegion content = regionList.get(2);
                                if (content.getType() == DOMJSPRegionContexts.JSP_VBL_CONTENT)
                                {
                                    handler.handleRegion(parentRegion, content);
                                }
                            }
                        }
                    }
                }             
            }
            curNode = curNode.getNext();
        }
    }
    
    /**
     * Used as a callback interface when an EL region is found in a StructuredModel
     * 
     * @author cbateman
     */
    public interface ELRegionHandler
    {
        /**
         * Called when an EL region is found
         * 
         * @param parentRegion
         * @param elRegion
         */
        public void handleRegion(ITextRegionCollection parentRegion, ITextRegion elRegion);
    }
}