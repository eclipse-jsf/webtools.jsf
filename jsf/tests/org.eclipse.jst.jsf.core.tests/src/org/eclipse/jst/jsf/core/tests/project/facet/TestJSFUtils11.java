package org.eclipse.jst.jsf.core.tests.project.facet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtilFactory;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockDataModel;
import org.eclipse.jst.jsf.test.util.mock.MockModelProvider;
import org.eclipse.jst.jsf.test.util.mock.MockResource;
import org.eclipse.jst.jsf.test.util.mock.MockDataModel.MockPropertyHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestJSFUtils11
{
    // private WebProjectTestEnvironment _webProjectTestEnv;
    private JSFUtils _jsfUtils;
    private Object _modelObject;
    private WebapplicationFactory _factory;

    @Before
    public void setUp() throws Exception
    {
        // for these tests, it doesn't matter which version is created.
        _modelObject = new Object();
        _jsfUtils = new TestableJSFUtils(new JSFUtilFactory().create(
                JSFVersion.V1_1, null), new MockModelProvider(_modelObject));
        assertEquals(JSFVersion.V1_1, _jsfUtils.getVersion());
        _factory = WebapplicationFactory.eINSTANCE;
    }

    @Test
    public void testUpdateWebApp_NewServlet_2_3()
    {
        testUpdateWebApp_NewServlet("2.3");
    }

    @Test
    public void testUpdateWebApp_NewServlet_2_4()
    {
        testUpdateWebApp_NewServlet("2.4");
    }

    private void testUpdateWebApp_NewServlet(final String version)
    {
        WebApp webApp = _factory.createWebApp();
        webApp.setVersion(version);

        Map<String, MockPropertyHolder> configProps = new HashMap<String, MockPropertyHolder>();
        final MockDataModel config = new MockDataModel("testModel", configProps);

        _jsfUtils.updateWebApp(webApp, config);
        Servlet defaultServlet = webApp
                .getServletNamed(JSFUtils.JSF_DEFAULT_SERVLET_NAME);
        assertNotNull(defaultServlet);
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, defaultServlet
                .getServletName());

        WebType webType = defaultServlet.getWebType();
        assertTrue(webType.isServletType());
        ServletType servletType = (ServletType) defaultServlet.getWebType();
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, servletType.getClassName());
        assertEquals(Integer.valueOf(1), defaultServlet.getLoadOnStartup());
    }
    @Test
    public void testUpdateWebApp_ExistingServlet_2_3()
    {
        testUpdateWebApp_ExistingServlet("2.3");
    }
    @Test
    public void testUpdateWebApp_ExistingServlet_2_4()
    {
        testUpdateWebApp_ExistingServlet("2.4");
    }
    @SuppressWarnings("unchecked")
    private void testUpdateWebApp_ExistingServlet(String version)
    {
        WebApp webApp = _factory.createWebApp();
        webApp.setVersion(version);
        Servlet servlet = _factory.createServlet();
        servlet.setServletName("Foobar");
        servlet.setWebType(_factory.createServletType());
        ((ServletType)servlet.getWebType()).setClassName(JSFUtils.JSF_SERVLET_CLASS);
        webApp.getServlets().add(servlet);
        Map<String, MockPropertyHolder> configProps = new HashMap<String, MockPropertyHolder>();
        final MockDataModel config = new MockDataModel("testModel", configProps);

        _jsfUtils.updateWebApp(webApp, config);
        Servlet defaultServlet = webApp
                .getServletNamed(JSFUtils.JSF_DEFAULT_SERVLET_NAME);
        assertNotNull(defaultServlet);
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, defaultServlet
                .getServletName());

        WebType webType = defaultServlet.getWebType();
        assertTrue(webType.isServletType());
        ServletType servletType = (ServletType) defaultServlet.getWebType();
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, servletType.getClassName());
        assertEquals(Integer.valueOf(1), defaultServlet.getLoadOnStartup());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetFileUrlPath_NonNullCases()
    {
        // a web app with valid servlet and extension mapping
        WebApp webApp = _factory.createWebApp();
        Servlet servlet = _factory.createServlet();
        servlet.setWebType(_factory.createServletType());
        ((ServletType) servlet.getWebType())
                .setClassName(JSFUtils.JSF_SERVLET_CLASS);
        webApp.getServlets().add(servlet);
        ServletMapping mapping = _factory.createServletMapping();
        mapping.setServlet(servlet);
        mapping.setUrlPattern(".faces");
        mapping.setWebApp(webApp);
        webApp.getServletMappings().add(mapping);
        IPath fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/test.faces", fileUrlPath.toString());

        // web app with valid servlet and non-default extension mapping
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
                "/test.xhtml"));
        assertEquals("/test.faces", fileUrlPath.toString());

        // web app with valid servlet and path-based mapping
        mapping.setUrlPattern("/faces");
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/faces/test.jsp", fileUrlPath.toString());

        // web app with valid servlet and path-based mapping match *
        mapping.setUrlPattern("/faces/*");
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/faces/test.jsp", fileUrlPath.toString());

        // web app with valid servlet and extension mapping based on a
        // non-default, default extension
        ContextParam param = _factory.createContextParam();
        mapping.setUrlPattern(".jspx");
        param.setParamName(JSFUtils.JSF_DEFAULT_SUFFIX_CONTEXT_PARAM);
        param.setParamValue(".xhtml");
        webApp.getContexts().add(param);
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
                "/test.xhtml"));
        assertEquals("/test.jspx", fileUrlPath.toString());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetFileUrlPath_NullCases()
    {
        // a null webApp object produces a null path
        Path existingURL = new Path("foobar");
        verifyNull(existingURL, null);

        // a non-null value that is not instanceof WebApp returns a null path
        verifyNull(existingURL, new Object());
        // a web app with no servlet produces a null path

        WebApp webApp = _factory.createWebApp();
        verifyNull(existingURL, webApp);

        // a web app with no meaningful servlet returns null
        Servlet servlet = _factory.createServlet();
        webApp.getServlets().add(servlet);
        verifyNull(existingURL, webApp);

        // the servlet has a jsp type returns null
        servlet.setWebType(_factory.createJSPType());
        verifyNull(existingURL, webApp);

        // the servlet has a servlet type but null class returns null
        servlet.setWebType(_factory.createServletType());
        verifyNull(existingURL, webApp);

        // wrong servlet class name
        ((ServletType) servlet.getWebType()).setClassName("com.wrong.Servlet");
        verifyNull(existingURL, webApp);

        // multiple wrong class names
        servlet = _factory.createServlet();
        servlet.setWebType(_factory.createServletType());
        ((ServletType) servlet.getWebType())
                .setClassName("com.AnotherWrong.Servlet");
        webApp.getServlets().add(servlet);

        assertEquals(2, webApp.getServlets().size());
        verifyNull(existingURL, webApp);

        // valid servlet class, but no mappings
        ((ServletType) servlet.getWebType())
                .setClassName(JSFUtils.JSF_SERVLET_CLASS);
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath")));
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));

        // has mapping but it's empty
        ServletMapping mapping = _factory.createServletMapping();
        webApp.getServletMappings().add(mapping);
        assertEquals(1, webApp.getServletMappings().size());
        assertEquals(0, servlet.getMappings().size());
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));

        // empty mapping that matches to servlet
        mapping.setServlet(servlet);
        assertEquals(1, webApp.getServletMappings().size());
        // mapping should be matched
        assertEquals(1, servlet.getMappings().size());
        assertTrue(servlet.getMappings().contains(mapping));
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));
    }

    private void verifyNull(Path existingURL, Object webApp, IResource res)
    {
        assertNull(_jsfUtils.getFileUrlPath(webApp, res, null));
        assertNull(_jsfUtils.getFileUrlPath(webApp, res, existingURL));
    }

    private void verifyNull(Path existingURL, Object webApp)
    {
        verifyNull(existingURL, webApp, null);
    }
}
