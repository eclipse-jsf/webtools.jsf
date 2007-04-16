package org.eclipse.jst.jsf.core.tests.tagmatcher;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.core.tagmatcher.XPathMatchingAlgorithm;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class TestXPathTagMatcher extends BaseTagMatcherTestCase {

    protected void setUp() throws Exception {
        _srcFileName = "/testfiles/jsps/testdata1.jsp.data";
        _destFileName = "/testdata1.jsp";
        super.setUp();
    }

    public void testSimpleMatches()
    {
        // get the view tag
        Document doc = ((IDOMModel)_structuredModel).getDocument();
        System.out.println(System.currentTimeMillis());
        XPathMatchingAlgorithm viewMatcher = new XPathMatchingAlgorithm("/view");
        AxiomaticSet  set = viewMatcher.evaluate(doc);
        System.out.println(System.currentTimeMillis());
        
        // get an input nested along a form path
        assertEquals(1, set.size());
        Node node = (Node) set.getFirstElement();
        XPathMatchingAlgorithm  matcher = new XPathMatchingAlgorithm("html/body/form/panelGrid/inputText");
        set = matcher.evaluate(node);
        System.out.println(System.currentTimeMillis());
        assertEquals(1, set.size());
        
        // get all of the ancestors of the inputText
        final Node inputText = (Node) set.getFirstElement();
        matcher = new XPathMatchingAlgorithm("ancestor::*");
        set = matcher.evaluate(inputText);
        assertEquals(5,set.size());
        
        for (final Iterator it = set.iterator(); it.hasNext();)
        {
            System.out.println(it.next());
        }
    }
}
