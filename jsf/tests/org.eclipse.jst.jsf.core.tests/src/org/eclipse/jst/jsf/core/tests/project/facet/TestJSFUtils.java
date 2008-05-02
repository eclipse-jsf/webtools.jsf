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
package org.eclipse.jst.jsf.core.tests.project.facet;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils.MappingSearchResult;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestJSFUtils extends TestCase {
    private WebProjectTestEnvironment _webProjectTestEnv;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _webProjectTestEnv = new WebProjectTestEnvironment(getClass().getName()
                + "_" + getName());
        _webProjectTestEnv.createProject(false);
        assertNotNull(_webProjectTestEnv);
        assertNotNull(_webProjectTestEnv.getTestProject());
        assertTrue(_webProjectTestEnv.getTestProject().isAccessible());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        _webProjectTestEnv.getTestProject().close(null);
    }

    public void testSearchServletMappings_NoPrefixMappings() {
        final List<String> mappings = new ArrayList<String>();
        // prefix mappings must end in "*"
        mappings.add("/notaprefixmapping/");
        mappings.add("*.faces");
        mappings.add("*.html");
        mappings.add("*.jsp");

        // if no preference, then the first one should be found
        MappingSearchResult result = JSFUtils.searchServletMappings(mappings,
                null, null);
        assertNotNull(result);
        assertTrue(result.isResult());
        assertTrue(result.getExtensionMapping().equals("*.faces"));
        assertNull(result.getPrefixMapping());

        // selecting the first one as the preferred should yield the same result
        result = JSFUtils.searchServletMappings(mappings, "*.faces", null);
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getExtensionMapping(), "*.faces");
        assertNull(result.getPrefixMapping());

        result = JSFUtils.searchServletMappings(mappings, "*.html", null);
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getExtensionMapping(), "*.html");
        assertNull(result.getPrefixMapping());

        result = JSFUtils.searchServletMappings(mappings, "*.jsp", null);
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getExtensionMapping(), "*.jsp");
        assertNull(result.getPrefixMapping());

        // an extension that doesn't exist
        result = JSFUtils.searchServletMappings(mappings, "*.xhtml", null);
        assertNotNull(result);
        assertTrue(result.isResult());
        // if preferred not present, then should be the first one
        assertEquals(result.getExtensionMapping(), "*.faces");
        assertNull(result.getPrefixMapping());
    }

    public void testSearchServletMappings_NoExtensionMappings() {
        final List<String> mappings = new ArrayList<String>();
        // prefix mappings must end in "*"
        mappings.add("/notaprefixMapping/");
        mappings.add("/faces/*");
        mappings.add("/foo/*");
        mappings.add("/bar/*");

        // if no preference, then the first one should be found
        MappingSearchResult result = JSFUtils.searchServletMappings(mappings,
                null, null);
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/faces/*");
        assertNull(result.getExtensionMapping());

        result = JSFUtils.searchServletMappings(mappings, null, "/faces/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/faces/*");
        assertNull(result.getExtensionMapping());

        result = JSFUtils.searchServletMappings(mappings, null, "/foo/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/foo/*");
        assertNull(result.getExtensionMapping());

        result = JSFUtils.searchServletMappings(mappings, null, "/bar/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/bar/*");
        assertNull(result.getExtensionMapping());

        // if a preference is given that is not present, then first one
        // one should be picked
        result = JSFUtils.searchServletMappings(mappings, null, "/notfound/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/faces/*");
        assertNull(result.getExtensionMapping());
    }

    public void testSearchServletMappings_BothKindsOfMappings() {
        final List<String> mappings = new ArrayList<String>();
        // prefix mappings must end in "*"
        mappings.add("/notaprefixmapping/");
        mappings.add("*.faces");
        mappings.add("/faces/*");
        mappings.add("*.html");
        mappings.add("/foo/*");
        mappings.add("*.jsp");
        mappings.add("/bar/*");

        // if no preference, then the first one should be found
        MappingSearchResult result = JSFUtils.searchServletMappings(mappings,
                null, null);
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/faces/*");
        assertEquals(result.getExtensionMapping(), "*.faces");

        result = JSFUtils
                .searchServletMappings(mappings, "*.faces", "/faces/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/faces/*");
        assertEquals(result.getExtensionMapping(), "*.faces");

        result = JSFUtils.searchServletMappings(mappings, "*.html", "/foo/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/foo/*");
        assertEquals(result.getExtensionMapping(), "*.html");

        result = JSFUtils.searchServletMappings(mappings, "*.jsp", "/bar/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/bar/*");
        assertEquals(result.getExtensionMapping(), "*.jsp");

        // one pref found, the other not: the first in is picked
        result = JSFUtils.searchServletMappings(mappings, "*.jsp", "/bar2/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/faces/*");
        assertEquals(result.getExtensionMapping(), "*.jsp");

        // one pref found, the other not: the first in is picked
        result = JSFUtils.searchServletMappings(mappings, "*.jspx", "/bar/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/bar/*");
        assertEquals(result.getExtensionMapping(), "*.faces");

        // neither found, so first of each
        result = JSFUtils.searchServletMappings(mappings, "*.jspx", "/bar2/*");
        assertNotNull(result);
        assertTrue(result.isResult());
        assertEquals(result.getPrefixMapping(), "/faces/*");
        assertEquals(result.getExtensionMapping(), "*.faces");
    }

    public void testSearchServletMappings_NoMatches() {
        final List<String> mappings = new ArrayList<String>();
        // none of these are either prefix or extension mappings
        mappings.add("/notaprefixmapping/");
        mappings.add("/alsoNotAMatch/");
        mappings.add("/");
        mappings.add("file.jsp");
        mappings.add("test.html");
        mappings.add("foo.jspx");

        // should not find any matches
        MappingSearchResult result = JSFUtils.searchServletMappings(mappings,
                null, null);
        assertNotNull(result);
        assertFalse(result.isResult());
        assertNull(result.getPrefixMapping());
        assertNull(result.getExtensionMapping());

        result = JSFUtils.searchServletMappings(mappings, "*.faces", null);
        assertNotNull(result);
        assertFalse(result.isResult());
        assertNull(result.getPrefixMapping());
        assertNull(result.getExtensionMapping());

        result = JSFUtils.searchServletMappings(mappings, null, "/faces/*");
        assertNotNull(result);
        assertFalse(result.isResult());
        assertNull(result.getPrefixMapping());
        assertNull(result.getExtensionMapping());

        result = JSFUtils
                .searchServletMappings(mappings, "*.faces", "/faces/*");
        assertNotNull(result);
        assertFalse(result.isResult());
        assertNull(result.getPrefixMapping());
        assertNull(result.getExtensionMapping());
    }
}
