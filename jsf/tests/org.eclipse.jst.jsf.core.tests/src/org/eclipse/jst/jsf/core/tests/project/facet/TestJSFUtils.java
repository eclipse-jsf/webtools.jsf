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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtilFactory;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockDataModel;
import org.eclipse.jst.jsf.test.util.mock.MockModelProvider;
import org.eclipse.jst.jsf.test.util.mock.MockDataModel.MockPropertyHolder;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestJSFUtils extends TestCase
{
    // private WebProjectTestEnvironment _webProjectTestEnv;
    private TestableJSFUtils _jsfUtils;
    private Object _modelObject;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        // for these tests, it doesn't matter which version is created.
        _modelObject = new Object();
        _jsfUtils = new TestableJSFUtils(new JSFUtilFactory()
            .create(JSFVersion.V2_0, null), new MockModelProvider(_modelObject));
    }

    public void testIsJavaEE()
    {
        final org.eclipse.jst.javaee.web.WebApp javaEEWebApp = WebFactory.eINSTANCE
                .createWebApp();
        assertEquals(WebAppVersionType._25_LITERAL, javaEEWebApp.getVersion());
        assertTrue(_jsfUtils.isJavaEE(javaEEWebApp));
        javaEEWebApp.setVersion(WebAppVersionType._30_LITERAL);
        assertTrue(_jsfUtils.isJavaEE(javaEEWebApp));

        final org.eclipse.jst.j2ee.webapplication.WebApp J2EEWebApp = org.eclipse.jst.j2ee.webapplication.WebapplicationFactory.eINSTANCE
                .createWebApp();
        assertFalse(_jsfUtils.isJavaEE(J2EEWebApp));
    }
    
    public void testGetModelProvider()
    {
        IModelProvider modelProvider = _jsfUtils.getModelProvider();
        assertNotNull(modelProvider);
        assertEquals(_modelObject, modelProvider.getModelObject());
        
        _jsfUtils = new TestableJSFUtils(new JSFUtilFactory()
        .create(JSFVersion.V2_0, null), new MockModelProvider(null));
        // returns null when the model provider has a null model object.
        assertNull(_jsfUtils.getModelProvider());
    }
    
    public void testGetDisplayName()
    {
        Map<String, MockPropertyHolder>  propsMap = new HashMap<String, MockPropertyHolder>();
        MockDataModel model = new MockDataModel("TestID", propsMap);
        // when the property is not present, use the default.
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, _jsfUtils.getDisplayName_testable(model));
        
        // if property is present but empty or only whitespace, we also use
        // the default.
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, "");
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, _jsfUtils.getDisplayName_testable(model));
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, "");
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, _jsfUtils.getDisplayName_testable(model));

        // otherwise, we should get back the value
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, "foobar");
        assertEquals("foobar", _jsfUtils.getDisplayName_testable(model));
    }

    public void testGetServletClassName()
    {
        Map<String, MockPropertyHolder>  propsMap = new HashMap<String, MockPropertyHolder>();
        MockDataModel model = new MockDataModel("TestID", propsMap);
        // when the property is not present, use the default.
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, _jsfUtils.getServletClassname_testable(model));
        
        // if property is present but empty or only whitespace, we also use
        // the default.
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME, "");
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, _jsfUtils.getServletClassname_testable(model));
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME, "");
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, _jsfUtils.getServletClassname_testable(model));

        // otherwise, we should get back the value
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME, "foobar");
        assertEquals("foobar", _jsfUtils.getServletClassname_testable(model));
    }
    
    public void testGetServletMappings()
    {
        Map<String, MockPropertyHolder>  propsMap = new HashMap<String, MockPropertyHolder>();
        MockDataModel model = new MockDataModel("TestID", propsMap);

        // empty array should beget empty list
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS, new String[]{});
        assertTrue(_jsfUtils.getServletMappings_testable(model).isEmpty());
        
        // non-empty list should beget the same list back
        String[]  values1 = new String[] {"foo"};
        String[]  valuesMany = new String[] {"foo", "bar", "foobar"};
        
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS, values1);
        assertEquals(Arrays.asList(values1), _jsfUtils.getServletMappings_testable(model));
        
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS, valuesMany);
        assertEquals(Arrays.asList(valuesMany), _jsfUtils.getServletMappings_testable(model));
    }
    
    public void testIsJSFPage()
    {
        assertTrue(_jsfUtils.isJSFPage_testable(null));
    }
    
    public void testIsValidKnownExtension()
    {
        assertTrue(_jsfUtils.isValidKnownExtension_testable("jsp"));
        assertTrue(_jsfUtils.isValidKnownExtension_testable("jspx"));  //$NON-NLS-1$
        assertTrue(_jsfUtils.isValidKnownExtension_testable("jsf")); //$NON-NLS-1$
        assertTrue(_jsfUtils.isValidKnownExtension_testable("xhtml")); //$NON-NLS-1$

        assertFalse(_jsfUtils.isValidKnownExtension_testable("html")); //$NON-NLS-1$)
        assertFalse(_jsfUtils.isValidKnownExtension_testable("dtd")); //$NON-NLS-1$)
        assertFalse(_jsfUtils.isValidKnownExtension_testable("jspf")); //$NON-NLS-1$)
        assertFalse(_jsfUtils.isValidKnownExtension_testable("php")); //$NON-NLS-1$)
        
        
        assertFalse(_jsfUtils.isValidKnownExtension_testable("")); //$NON-NLS-1$)
        assertFalse(_jsfUtils.isValidKnownExtension_testable(null)); //$NON-NLS-1$)
    }
    // @Override
    // protected void tearDown() throws Exception {
    // super.tearDown();
    // _webProjectTestEnv.getTestProject().close(null);
    // }

    // public void testSearchServletMappings_NoPrefixMappings() {
    // final List<String> mappings = new ArrayList<String>();
    // // prefix mappings must end in "*"
    // mappings.add("/notaprefixmapping/");
    // mappings.add("*.faces");
    // mappings.add("*.html");
    // mappings.add("*.jsp");
    //
    // // if no preference, then the first one should be found
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertTrue(result.getExtensionMapping().equals("*.faces"));
    // assertNull(result.getPrefixMapping());
    //
    // // selecting the first one as the preferred should yield the same result
    // result = searchServletMappings(mappings, "*.faces", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getExtensionMapping(), "*.faces");
    // assertNull(result.getPrefixMapping());
    //
    // result = searchServletMappings(mappings, "*.html", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getExtensionMapping(), "*.html");
    // assertNull(result.getPrefixMapping());
    //
    // result = searchServletMappings(mappings, "*.jsp", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getExtensionMapping(), "*.jsp");
    // assertNull(result.getPrefixMapping());
    //
    // // an extension that doesn't exist
    // result = searchServletMappings(mappings, "*.xhtml", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // // if preferred not present, then should be the first one
    // assertEquals(result.getExtensionMapping(), "*.faces");
    // assertNull(result.getPrefixMapping());
    // }
    //
    // public void testSearchServletMappings_NoExtensionMappings() {
    // final List<String> mappings = new ArrayList<String>();
    // // prefix mappings must end in "*"
    // mappings.add("/notaprefixMapping/");
    // mappings.add("/faces/*");
    // mappings.add("/foo/*");
    // mappings.add("/bar/*");
    //
    // // if no preference, then the first one should be found
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/faces/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/foo/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/foo/*");
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/bar/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/bar/*");
    // assertNull(result.getExtensionMapping());
    //
    // // if a preference is given that is not present, then first one
    // // one should be picked
    // result = searchServletMappings(mappings, null, "/notfound/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertNull(result.getExtensionMapping());
    // }
    //
    // public void testSearchServletMappings_BothKindsOfMappings() {
    // final List<String> mappings = new ArrayList<String>();
    // // prefix mappings must end in "*"
    // mappings.add("/notaprefixmapping/");
    // mappings.add("*.faces");
    // mappings.add("/faces/*");
    // mappings.add("*.html");
    // mappings.add("/foo/*");
    // mappings.add("*.jsp");
    // mappings.add("/bar/*");
    //
    // // if no preference, then the first one should be found
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    //
    // result = searchServletMappings(mappings, "*.faces", "/faces/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    //
    // result = searchServletMappings(mappings, "*.html", "/foo/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/foo/*");
    // assertEquals(result.getExtensionMapping(), "*.html");
    //
    // result = searchServletMappings(mappings, "*.jsp", "/bar/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/bar/*");
    // assertEquals(result.getExtensionMapping(), "*.jsp");
    //
    // // one pref found, the other not: the first in is picked
    // result = searchServletMappings(mappings, "*.jsp", "/bar2/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.jsp");
    //
    // // one pref found, the other not: the first in is picked
    // result = searchServletMappings(mappings, "*.jspx", "/bar/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/bar/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    //
    // // neither found, so first of each
    // result = searchServletMappings(mappings, "*.jspx", "/bar2/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    // }
    //
    // public void testSearchServletMappings_NoMatches() {
    // final List<String> mappings = new ArrayList<String>();
    // // none of these are either prefix or extension mappings
    // mappings.add("/notaprefixmapping/");
    // mappings.add("/alsoNotAMatch/");
    // mappings.add("/");
    // mappings.add("file.jsp");
    // mappings.add("test.html");
    // mappings.add("foo.jspx");
    //
    // // should not find any matches
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, "*.faces", null);
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/faces/*");
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, "*.faces", "/faces/*");
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    // }
    //
    // /**
    // * Search the list of servlet-mappings for the first extension and prefix
    // mappings. The contents
    // * of mappings is assumed to be all url-pattern's.
    // *
    // * If prefExtMapping is not null, it is an extension mapping and
    // * it is in mappings, then it is returned. Otherwise, the first extension
    // * mapping in mappings is returned. Returns null if mappings does not
    // * contain an extension mapping. The same algorithm holds for
    // prefPrefixMapping and
    // * corresponding prefix mapping.
    // *
    // * See isExtensionMapping and isPrefixMapping for more information on url
    // patterns.
    // *
    // * @param mappings
    // * @param prefExtMapping
    // * @param prefPrefixMapping
    // * @return the result
    // */
    // public final MappingSearchResult searchServletMappings(
    // final List<String> mappings, String prefExtMapping,
    // String prefPrefixMapping) {
    // String firstExtFound = null;
    // String firstPrefixFound = null;
    // boolean foundExtMapping = false;
    // boolean foundPrefixMapping = false;
    //
    // // if the caller has no preferredMapping, then
    // // set it to something guaranteed to be non-null
    // // and which is guaranteed not to match anything
    // // that pass isExtensionMapping
    // if (prefExtMapping == null) {
    //            prefExtMapping = "NOTANEXTENSIONMAPPING"; //$NON-NLS-1$
    // }
    //
    // // similarly, guarantee that if the caller has no
    // // preferred prefix mapping, that we set a non-null
    // // comp mapping
    // if (prefPrefixMapping == null) {
    //            prefPrefixMapping = "NOTAPREFIXMAPPING"; //$NON-NLS-1$
    // }
    //
    // SEARCH_LOOP: for (String mapping : mappings) {
    // if (isExtensionMapping(mapping)) {
    // // can assum that mapping is non-null since
    // // it is an ext mapping
    // if (prefExtMapping.equals(mapping.trim())) {
    // firstExtFound = prefExtMapping;
    // continue;
    // }
    //
    // if (firstExtFound == null) {
    // firstExtFound = mapping.trim();
    // }
    // } else if (isPrefixMapping(mapping)) {
    // if (prefPrefixMapping.equals(mapping.trim())) {
    // firstPrefixFound = prefPrefixMapping;
    // continue;
    // }
    //
    // if (firstPrefixFound == null) {
    // firstPrefixFound = mapping.trim();
    // }
    // }
    //
    // if (foundExtMapping && foundPrefixMapping) {
    // break SEARCH_LOOP;
    // }
    // }
    //
    // return new MappingSearchResult(firstExtFound, firstPrefixFound);
    // }
    //
    // /**
    // * Servlet 2.3_SRV.11.2: a string that begins with a "/" and ends
    // * with "/*" is a prefix mapping
    // *
    // * @param mapping
    // * @return true if the mapping string represents a prefix mapping
    // */
    // public final boolean isPrefixMapping(final String mapping)
    // {
    // if (mapping == null || mapping.length() < 4)
    // {
    // return false;
    // }
    //
    //        return mapping.charAt(0) == '/' && mapping.endsWith("/*"); //$NON-NLS-1$
    // }
    //
    // /**
    // * Servlet 2.3_SRV.11.2: a string that begins with "*."
    // * is an extension mapping
    // *
    // * @param mapping
    // * @return true if mapping is an extension mapping
    // */
    // public final boolean isExtensionMapping(final String mapping)
    // {
    // if (mapping == null)
    // {
    // return false;
    // }
    //
    //        return mapping.startsWith("*."); //$NON-NLS-1$
    // }
}
