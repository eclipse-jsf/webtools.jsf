/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.tagmatcher;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.set.mapping.ElementToTagIdentifierMapping;
import org.eclipse.jst.jsf.core.tagmatcher.XPathMatchingAlgorithm;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		IStructuredDocumentContext context =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_structuredDocument, 529);
		IDOMContextResolver resolver = 
			IStructuredDocumentContextResolverFactory.INSTANCE
				.getDOMContextResolver(context);

        Node node = resolver.getNode();//(Node) set.getFirstElement();
        assertEquals("f:view", node.getNodeName());
        System.out.println(CMUtil.getElementNamespaceURI((Element) node));
        XPathMatchingAlgorithm  matcher = new XPathMatchingAlgorithm("html/body/form/panelGrid/inputText");
        set = matcher.evaluate(node);
        System.out.println(System.currentTimeMillis());
        assertEquals(1, set.size());
        
        // get all of the ancestors of the inputText
        final Node inputText = (Node) set.getFirstElement();
        System.out.println(CMUtil.getElementNamespaceURI((Element) inputText));
        matcher = new XPathMatchingAlgorithm("ancestor::*");
        set = matcher.evaluate(inputText);
        assertEquals(5,set.size());

        set = new ElementToTagIdentifierMapping().map(set);

        for (final Iterator<?> it = set.iterator(); it.hasNext();)
        {
        	TagIdentifier tagId = (TagIdentifier) it.next();
        	System.out.println(tagId.getUri());
            System.out.println(tagId.getTagName());
        }
    }
}
