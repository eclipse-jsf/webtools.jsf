/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core.tests.region;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.dom.AttrDOMAdapter;
import org.eclipse.jst.jsf.common.dom.AttributeIdentifier;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.region.Region2AttrAdapter;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Node;

public class TestRegion2ElementAdapter extends TestCase
{

    private final static int                 OFFSET_OUTPUT_TEXT_WITH_EL       =
                                                                                883;
    private final static int                 OFFSET_PANELGRID_WITH_NO_EL      =
                                                                                958;
    private final static int                 OFFSET_OUTPUTLABEL_WITH_NO_EL    =
                                                                                990;
    private final static int                 OFFSET_INPUTTEXT_WITH_BOTH       =
                                                                                1044;
    private final static int                 OFFSET_COMMANDBUTTON_WITH_TWO_EL =
                                                                                1255;

    private final static Map<String, String> OUTPUTLABEL_ATTRIBUTES;
    private final static Map<String, String> INPUTTEXT_ATTRIBUTES;
    private final static Map<String, String> COMMANDBUTTON_ATTRIBUTES;

    private WebProjectTestEnvironment        _webProjectTestEnv;
    private IResource                        _res1;
    private IStructuredModel                 _model;
    private IStructuredDocument              _sdoc;

    static
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("for", "userId");
        map.put("value", "Username:");
        OUTPUTLABEL_ATTRIBUTES = Collections.unmodifiableMap(map);

        map = new HashMap<String, String>();
        map.put("id", "userId");
        map.put("value", "#{loginRequest.id}");
        INPUTTEXT_ATTRIBUTES = Collections.unmodifiableMap(map);

        map = new HashMap<String, String>();
        map.put("action", "#{appController.loginActions}");
        map.put("value", "#{bundle['login.button.value']}");
        COMMANDBUTTON_ATTRIBUTES = Collections.unmodifiableMap(map);

    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _webProjectTestEnv =
                             new WebProjectTestEnvironment(getClass().getName()
                                     + "_" + getName());
        _webProjectTestEnv.createProject(false);
        assertNotNull(_webProjectTestEnv);
        assertNotNull(_webProjectTestEnv.getTestProject());
        assertTrue(_webProjectTestEnv.getTestProject().isAccessible());

        _res1 =
                _webProjectTestEnv.loadResourceInWebRoot(TestsPlugin
                        .getDefault().getBundle(),
                        "/testfiles/jsps/testdata1.jsp.data", "/testdata1.jsp");
        _model =
                 StructuredModelManager.getModelManager().getModelForRead(
                         (IFile) _res1);
        assert (_model instanceof DOMModelForJSP);
        _sdoc = _model.getStructuredDocument();
    }

    @Override
    protected void tearDown() throws Exception
    {
        try
        {
            super.tearDown();
        }
        finally
        {
            _model.releaseFromRead();
        }

    }

    public void testSanity()
    {
        assertExpectedTag(OFFSET_OUTPUT_TEXT_WITH_EL, "h", "outputText",
                ITLDConstants.URI_JSF_HTML);
        final Map<String, String> expectedMap = new HashMap<String, String>();
        expectedMap.put("value", "#{bundle['page.header']}");
        assertExpectedAttributes(OFFSET_OUTPUT_TEXT_WITH_EL, expectedMap);

        assertExpectedTag(OFFSET_PANELGRID_WITH_NO_EL, "h", "panelGrid",
                ITLDConstants.URI_JSF_HTML);
        expectedMap.clear();
        expectedMap.put("columns", "2");
        assertExpectedAttributes(OFFSET_PANELGRID_WITH_NO_EL, expectedMap);

        assertExpectedTag(OFFSET_OUTPUTLABEL_WITH_NO_EL, "h", "outputLabel",
                ITLDConstants.URI_JSF_HTML);
        assertExpectedAttributes(OFFSET_OUTPUTLABEL_WITH_NO_EL,
                OUTPUTLABEL_ATTRIBUTES);

        assertExpectedTag(OFFSET_INPUTTEXT_WITH_BOTH, "h", "inputText",
                ITLDConstants.URI_JSF_HTML);
        assertExpectedAttributes(OFFSET_INPUTTEXT_WITH_BOTH,
                INPUTTEXT_ATTRIBUTES);

        assertExpectedTag(OFFSET_COMMANDBUTTON_WITH_TWO_EL, "h",
                "commandButton", ITLDConstants.URI_JSF_HTML);
        assertExpectedAttributes(OFFSET_COMMANDBUTTON_WITH_TWO_EL,
                COMMANDBUTTON_ATTRIBUTES);

    }

    /**
     * Single attribute, EL only
     * 
     * @throws Exception
     */
    public void testOutputTextWithEL() throws Exception
    {
        final IndexedRegion region =
                                     JSFTestUtil.getIndexedRegion(_sdoc,
                                             OFFSET_OUTPUT_TEXT_WITH_EL);
        final IDOMNode domNode = (IDOMNode) region;

        final ITextRegion textRegion =
                                       domNode
                                               .getFirstStructuredDocumentRegion();
        final Region2ElementAdapter adapter =
                                              new Region2ElementAdapter(
                                                      textRegion);

        // verify element
        assertEquals("h:outputText", adapter.getNodeName());
        assertEquals("outputText", adapter.getLocalName());
        assertEquals("h", adapter.getPrefix());
        assertEquals(ITLDConstants.URI_JSF_HTML, adapter.getNamespace());
        assertEquals(1, adapter.getAttributes().size());

        // verify the TagIdentifier
        final TagIdentifier tagId = adapter.getTagId();
        assertNotNull(tagId);
        assertEquals(ITLDConstants.URI_JSF_HTML, tagId.getUri());
        assertEquals(IJSFConstants.TAG_OUTPUTTEXT, tagId.getTagName());
        assertEquals(IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT, tagId);

        // verify only attribute
        assertTrue(adapter.getAttributes().containsKey("value"));
        final AttrDOMAdapter attr = adapter.getAttributes().get("value");
        assertEquals("value", attr.getLocalName());
        assertEquals("value", attr.getNodeName());
        assertEquals("#{bundle['page.header']}", attr.getValue());

        // verify the AttributeIdentifier
        final AttributeIdentifier attrId = attr.getAttributeIdentifier();
        assertNotNull(attrId);
        assertEquals(tagId, attrId.getTagIdentifier());
        assertEquals(attr.getNodeName(), attrId.getName());
    }

    /**
     * Single attribute No EL
     * 
     * @throws Exception
     */
    public void testPanelGridWithNoEL() throws Exception
    {
        final IndexedRegion region =
                                     JSFTestUtil.getIndexedRegion(_sdoc,
                                             OFFSET_PANELGRID_WITH_NO_EL);
        final IDOMNode domNode = (IDOMNode) region;

        final ITextRegion textRegion =
                                       domNode
                                               .getFirstStructuredDocumentRegion();
        final Region2ElementAdapter adapter =
                                              new Region2ElementAdapter(
                                                      textRegion);

        // verify element
        assertEquals("h:panelGrid", adapter.getNodeName());
        assertEquals("panelGrid", adapter.getLocalName());
        assertEquals("h", adapter.getPrefix());
        assertEquals(ITLDConstants.URI_JSF_HTML, adapter.getNamespace());
        assertEquals(1, adapter.getAttributes().size());

        // verify the TagIdentifier
        final TagIdentifier tagId = adapter.getTagId();
        assertNotNull(tagId);
        assertEquals(ITLDConstants.URI_JSF_HTML, tagId.getUri());
        assertEquals(IJSFConstants.TAG_PANELGRID, tagId.getTagName());
        assertEquals(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID, tagId);

        // verify only attribute
        assertTrue(adapter.getAttributes().containsKey("columns"));
        final AttrDOMAdapter attr = adapter.getAttributes().get("columns");
        assertEquals("columns", attr.getLocalName());
        assertEquals("columns", attr.getNodeName());
        assertEquals("2", attr.getValue());

        // verify the AttributeIdentifier
        final AttributeIdentifier attrId = attr.getAttributeIdentifier();
        assertNotNull(attrId);
        assertEquals(tagId, attrId.getTagIdentifier());
        assertEquals(attr.getNodeName(), attrId.getName());
    }

    /**
     * Two non-EL attributes
     * 
     * @throws Exception
     */
    public void testOutputLabelWithNoEL() throws Exception
    {
        final IndexedRegion region =
                                     JSFTestUtil.getIndexedRegion(_sdoc,
                                             OFFSET_OUTPUTLABEL_WITH_NO_EL);
        final IDOMNode domNode = (IDOMNode) region;

        final ITextRegion textRegion =
                                       domNode
                                               .getFirstStructuredDocumentRegion();
        final Region2ElementAdapter adapter =
                                              new Region2ElementAdapter(
                                                      textRegion);

        // verify element
        assertEquals("h:outputLabel", adapter.getNodeName());
        assertEquals("outputLabel", adapter.getLocalName());
        assertEquals("h", adapter.getPrefix());
        assertEquals(ITLDConstants.URI_JSF_HTML, adapter.getNamespace());

        // verify the TagIdentifier
        final TagIdentifier tagId = adapter.getTagId();
        assertNotNull(tagId);
        assertEquals(ITLDConstants.URI_JSF_HTML, tagId.getUri());
        assertEquals(IJSFConstants.TAG_OUTPUTLABEL, tagId.getTagName());
        assertEquals(IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL, tagId);

        assertEquals(OUTPUTLABEL_ATTRIBUTES.size(), adapter.getAttributes()
                .size());
        for (final Map.Entry<String, String> entry : OUTPUTLABEL_ATTRIBUTES
                .entrySet())
        {
            AttrDOMAdapter attrAdapter =
                                         adapter.getAttributes().get(
                                                 entry.getKey());
            assertNotNull(attrAdapter);
            assertEquals(entry.getKey(), attrAdapter.getNodeName());
            assertEquals(entry.getValue(), attrAdapter.getValue());
            // verify the AttributeIdentifier
            final AttributeIdentifier attrId =
                                               attrAdapter
                                                       .getAttributeIdentifier();
            assertNotNull(attrId);
            assertEquals(tagId, attrId.getTagIdentifier());
            assertEquals(attrAdapter.getNodeName(), attrId.getName());
        }

    }

    /**
     * One non-EL attribute, One EL attribute
     * 
     * @throws Exception
     */
    public void testInputTextWithBoth() throws Exception
    {
        final IndexedRegion region =
                                     JSFTestUtil.getIndexedRegion(_sdoc,
                                             OFFSET_INPUTTEXT_WITH_BOTH);
        final IDOMNode domNode = (IDOMNode) region;

        final ITextRegion textRegion =
                                       domNode
                                               .getFirstStructuredDocumentRegion();
        final Region2ElementAdapter adapter =
                                              new Region2ElementAdapter(
                                                      textRegion);

        // verify element
        assertEquals("h:inputText", adapter.getNodeName());
        assertEquals("inputText", adapter.getLocalName());
        assertEquals("h", adapter.getPrefix());
        assertEquals(ITLDConstants.URI_JSF_HTML, adapter.getNamespace());

        // verify the TagIdentifier
        final TagIdentifier tagId = adapter.getTagId();
        assertNotNull(tagId);
        assertEquals(ITLDConstants.URI_JSF_HTML, tagId.getUri());
        assertEquals(IJSFConstants.TAG_INPUTTEXT, tagId.getTagName());
        assertEquals(IJSFConstants.TAG_IDENTIFIER_INPUTTEXT, tagId);

        assertEquals(INPUTTEXT_ATTRIBUTES.size(), adapter.getAttributes()
                .size());
        for (final Map.Entry<String, String> entry : INPUTTEXT_ATTRIBUTES
                .entrySet())
        {
            AttrDOMAdapter attrAdapter =
                                         adapter.getAttributes().get(
                                                 entry.getKey());
            assertNotNull(attrAdapter);
            assertEquals(entry.getKey(), attrAdapter.getNodeName());
            assertEquals(entry.getValue(), attrAdapter.getValue());
            // verify the AttributeIdentifier
            final AttributeIdentifier attrId =
                                               attrAdapter
                                                       .getAttributeIdentifier();
            assertNotNull(attrId);
            assertEquals(tagId, attrId.getTagIdentifier());
            assertEquals(attrAdapter.getNodeName(), attrId.getName());
        }

    }

    /**
     * Two EL, no non-EL
     * 
     * @throws Exception
     */
    public void testCommandButtonWithTwoEL() throws Exception
    {
        final IndexedRegion region =
                                     JSFTestUtil.getIndexedRegion(_sdoc,
                                             OFFSET_COMMANDBUTTON_WITH_TWO_EL);
        final IDOMNode domNode = (IDOMNode) region;

        final ITextRegion textRegion =
                                       domNode
                                               .getFirstStructuredDocumentRegion();
        final Region2ElementAdapter adapter =
                                              new Region2ElementAdapter(
                                                      textRegion);

        // verify element
        assertEquals("h:commandButton", adapter.getNodeName());
        assertEquals("commandButton", adapter.getLocalName());
        assertEquals("h", adapter.getPrefix());
        assertEquals(ITLDConstants.URI_JSF_HTML, adapter.getNamespace());

        // verify the TagIdentifier
        final TagIdentifier tagId = adapter.getTagId();
        assertNotNull(tagId);
        assertEquals(ITLDConstants.URI_JSF_HTML, tagId.getUri());
        assertEquals(IJSFConstants.TAG_COMMANDBUTTON, tagId.getTagName());
        assertEquals(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, tagId);

        assertEquals(COMMANDBUTTON_ATTRIBUTES.size(), adapter.getAttributes()
                .size());
        for (final Map.Entry<String, String> entry : COMMANDBUTTON_ATTRIBUTES
                .entrySet())
        {
            AttrDOMAdapter attrAdapter =
                                         adapter.getAttributes().get(
                                                 entry.getKey());
            assertNotNull(attrAdapter);
            assertEquals(entry.getKey(), attrAdapter.getNodeName());
            assertEquals(entry.getValue(), attrAdapter.getValue());
            // verify the AttributeIdentifier
            final AttributeIdentifier attrId =
                                               attrAdapter
                                                       .getAttributeIdentifier();
            assertNotNull(attrId);
            assertEquals(tagId, attrId.getTagIdentifier());
            assertEquals(attrAdapter.getNodeName(), attrId.getName());
        }
    }

    /**
     * Ensure that we can pass the element or attribute adapter back and get the
     * corresponding region
     */
    public void testAdapterToRegionMappings() throws Exception
    {
        final IndexedRegion region =
                                     JSFTestUtil.getIndexedRegion(_sdoc,
                                             OFFSET_INPUTTEXT_WITH_BOTH);
        final IDOMNode domNode = (IDOMNode) region;

        final ITextRegion textRegion =
                                       domNode
                                               .getFirstStructuredDocumentRegion();
        final Region2ElementAdapter adapter =
                                              new Region2ElementAdapter(
                                                      textRegion);

        assertEquals(OFFSET_INPUTTEXT_WITH_BOTH, adapter.getTextRegion()
                .getStart());

        assertEquals(textRegion, adapter.getTextRegion());

        Region2AttrAdapter attrAdapter = adapter.getAttributes().get("id");
        assertEquals(OFFSET_INPUTTEXT_WITH_BOTH+13, attrAdapter.getStartOffset());
        assertExpectedAttributeName(attrAdapter, 13, 2);
        // length is 9 instead of 8 because of trailing whitespace
        assertExpectedAttributeValue(attrAdapter, 16, 9);

        attrAdapter = adapter.getAttributes().get("value");
        assertEquals(OFFSET_INPUTTEXT_WITH_BOTH+25, attrAdapter.getStartOffset());
        assertExpectedAttributeName(attrAdapter, 25, 5);
        // length is 9 instead of 8 because of trailing whitespace
        assertExpectedAttributeValue(attrAdapter, 31, 20);
    }

    private void assertExpectedAttributeName(
            final Region2AttrAdapter attrAdapter, final int expectedStart,
            final int expectedLength)
    {
        final ITextRegion nameRegion = attrAdapter.getAttributeNameRegion();
        assertEquals(expectedStart, nameRegion.getStart());
        assertEquals(expectedLength, nameRegion.getLength());
    }

    private void assertExpectedAttributeValue(
            final Region2AttrAdapter attrAdapter, final int expectedStart,
            final int expectedLength)
    {
        final ITextRegion valueRegion = attrAdapter.getAttributeValueRegion();
        assertEquals(expectedStart, valueRegion.getStart());
        assertEquals(expectedLength, valueRegion.getLength());

    }

    private void assertExpectedTag(final int offset, final String prefix,
            final String tagName, final String uri)
    {
        final IndexedRegion region =
                                     JSFTestUtil
                                             .getIndexedRegion(_sdoc, offset);
        assertTrue(region instanceof IDOMElement);

        assertEquals(prefix + ":" + tagName, ((IDOMElement) region)
                .getNodeName());
        assertEquals(tagName, ((IDOMElement) region).getLocalName());
        assertEquals(prefix, ((IDOMElement) region).getPrefix());
    }

    private void assertExpectedAttributes(final int offset,
            final Map<String, String> attributes)
    {
        final IndexedRegion region =
                                     JSFTestUtil
                                             .getIndexedRegion(_sdoc, offset);
        assertTrue(region instanceof IDOMElement);

        final IDOMElement domElement = (IDOMElement) region;

        assertExpectedAttributes(new Iterator<Node>()
        {
            int curElement = 0;

            public boolean hasNext()
            {
                return (curElement < domElement.getAttributes().getLength());
            }

            public Node next()
            {
                return domElement.getAttributes().item(curElement++);
            }

            public void remove()
            {
                throw new UnsupportedOperationException("");

            }

        }, attributes);
    }

    private void assertExpectedAttributes(final Iterator<Node> check,
            final Map<String, String> expectedAttributes)
    {
        int count = 0;
        for (; check.hasNext();)
        {
            Node node = check.next();
            assertTrue(expectedAttributes.containsKey(node.getNodeName()));
            assertEquals(expectedAttributes.get(node.getNodeName()), node
                    .getNodeValue());
            count++;
        }
        assertEquals(expectedAttributes.size(), count);
    }
}
