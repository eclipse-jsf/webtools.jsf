package org.eclipse.jst.jsf.core.tests.project.facet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtilFactory;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockModelProvider;
import org.eclipse.jst.jsf.test.util.mock.MockResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestJSFUtils12
{
    private JSFUtils _jsfUtils;
    private Object _modelObject;
    private WebFactory _factory;

    @Before
    public void setUp() throws Exception
    {
        // for these tests, it doesn't matter which version is created.
        _modelObject = new Object();
        _jsfUtils = new TestableJSFUtils(new JSFUtilFactory().create(
                JSFVersion.V1_2, null), new MockModelProvider(_modelObject));
        assertEquals(JSFVersion.V1_2, _jsfUtils.getVersion());
        _factory = WebFactory.eINSTANCE;
    }

    @Test
    public void testGetFileUrlPath_NonNullCases()
    {
        // a web app with valid servlet and extension mapping
        WebApp webApp = _factory.createWebApp();
        Servlet servlet = _factory.createServlet();
        servlet.setServletClass(JSFUtils.JSF_SERVLET_CLASS);
        servlet.setServletName("JSF Servlet");
        webApp.getServlets().add(servlet);
        ServletMapping mapping = _factory.createServletMapping();
        mapping.setServletName("JSF Servlet");
        UrlPatternType pattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
        pattern.setValue(".faces");
        mapping.getUrlPatterns().add(pattern);
        webApp.getServletMappings().add(mapping);
        IPath fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/test.faces", fileUrlPath.toString());

        // web app with valid servlet and non-default extension mapping
        fileUrlPath =  _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
                "/test.xhtml"));
        assertEquals("/test.faces", fileUrlPath.toString());

        // web app with valid servlet and path-based mapping
        pattern.setValue("/faces");
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/faces/test.jsp", fileUrlPath.toString());

        // web app with valid servlet and path-based mapping match *
        pattern.setValue("/faces/*");
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/faces/test.jsp", fileUrlPath.toString());

        // web app with valid servlet and extension mapping based on a 
        // non-default, default extension
        pattern.setValue(".jspx");
        ParamValue param = JavaeeFactory.eINSTANCE.createParamValue();
        param.setParamName(JSFUtils.JSF_DEFAULT_SUFFIX_CONTEXT_PARAM);
        param.setParamValue(".xhtml");
        webApp.getContextParams().add(param);
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
                "/test.xhtml"));
        assertEquals("/test.jspx", fileUrlPath.toString());
    }

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

        // wrong servlet class name
        servlet.setServletClass("com.wrong.Servlet");
        verifyNull(existingURL, webApp);

        // multiple wrong class names
        servlet = _factory.createServlet();
        servlet.setServletClass("com.AnotherWrong.Servlet");
        webApp.getServlets().add(servlet);

        assertEquals(2, webApp.getServlets().size());
        verifyNull(existingURL, webApp);

        // valid servlet class, but no mappings
        servlet.setServletClass(JSFUtils.JSF_SERVLET_CLASS);
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath")));
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));

        // has mapping but it's empty
        ServletMapping mapping = _factory.createServletMapping();
        webApp.getServletMappings().add(mapping);
        assertEquals(1, webApp.getServletMappings().size());
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));

        // empty mapping that matches to servlet
        mapping.setServletName(servlet.getServletName());
        assertEquals(1, webApp.getServletMappings().size());
//        assertTrue(servlet.getMappings().contains(mapping));
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
