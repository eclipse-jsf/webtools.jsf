package org.eclipse.jst.jsf.core.tests.project.facet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jsf.core.JSFVersion;

//@Category(NoPluginEnvironment.class)
public class TestJSFUtils12 extends TestJSFUtils
{
//    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        // _factory = WebFactory.eINSTANCE;
    }

    @Override
    protected JSFVersion getVersionToTestIn()
    {
        return JSFVersion.V1_2;
    }

    @Override
    protected WebAppAccessor createWebAccessor()
    {
        return new WebAppTestAccessor();
    }

    //@Test
    public void testUpdateWebApp_ExistingServlet_2_5()
    {
        testUpdateWebApp_ExistingServlet("2.5");
    }

    //@Test
    public void testUpdateWebApp_NewServlet_2_5()
    {
        testUpdateWebApp_NewServlet("2.5");
    }

    //@Test
    public void testRollbackWebApp_2_5()
    {
        super.testRollbackWebApp("2.5");
    }

    //@Test
    public void testGetFileUrlPath_NonNullCases_2_5()
    {
        super.testGetFileUrlPath_NonNullCases("2.5");
    }

    //@Test
    public void testGetFileUrlPath_NullCases_2_5()
    {
        super.testGetFileUrlPath_NullCases("2.5");
    }

    protected static class WebAppTestAccessor extends WebAppAccessor
    {
        private WebFactory _factory;

        public WebAppTestAccessor()
        {
            _factory = WebFactory.eINSTANCE;
        }
        @Override
        protected WebApp createWebApp(String version)
        {
            WebApp webApp = _factory.createWebApp();
            webApp.setVersion(WebAppVersionType.get(version));
            return webApp;
        }

        @Override
        protected Servlet createServlet(String servletName, String servletClass)
        {
            final Servlet servlet = _factory.createServlet();
            servlet.setServletName(servletName);
            servlet.setServletClass(servletClass);
            return servlet;
        }

        @Override
        protected Object createServletMapping()
        {
            return _factory.createServletMapping();
        }

        @Override
        protected void setServletClass(Object servlet, String name)
        {
            ((Servlet) servlet).setServletClass(name);
        }

        @SuppressWarnings("rawtypes")
        @Override
        protected List getServlets(Object webApp)
        {
            return ((WebApp) webApp).getServlets();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void addMappingToWebApp(Object webApp, Object mapping)
        {
            getServletMappings_WebApp(webApp).add((ServletMapping) mapping);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void addServletToWebApp(Object webApp, Object servlet)
        {
            getServlets(webApp).add(servlet);
        }

        @SuppressWarnings("rawtypes")
        @Override
        protected List getServletMappings_WebApp(Object webApp)
        {
            return ((WebApp) webApp).getServletMappings();
        }

        @SuppressWarnings("rawtypes")
        @Override
        protected List getServletMappings_Servlet(final Object webApp,Object servlet)
        {
            List<ServletMapping> mappings = new ArrayList<ServletMapping>();
            for (final ServletMapping mapping : ((WebApp)webApp).getServletMappings())
            {
                if (mapping.getServletName() != null &&
                        mapping.getServletName().equals(((Servlet)servlet).getServletName()))
                {
                    mappings.add(mapping);
                }
            }
            return mappings;
        }

        @Override
        protected void setMappingData(Object webApp, List<String> urlPatterns,
                Object servlet, Object mapping)
        {
            ((ServletMapping) mapping).setServletName(((Servlet) servlet).getServletName());
            setUrlPattern(mapping, urlPatterns);
//            ((ServletMapping) mapping).setWebApp((WebApp) webApp);
        }

        @Override
        protected void setUrlPattern(Object mapping, List<String> patterns)
        {
            ((ServletMapping) mapping).getUrlPatterns().clear();
            for (final String pattern : patterns)
            {
                UrlPatternType newPattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
                newPattern.setValue(pattern);
                ((ServletMapping) mapping).getUrlPatterns().add(newPattern);
            }
        }

        @Override
        protected Object createContextParam(String name, String value, final String version)
        {
            ParamValue paramValue = 
                JavaeeFactory.eINSTANCE.createParamValue();
            paramValue.setParamName(name);
            paramValue.setParamValue(value);
            return paramValue;
        }

        @Override
        protected void setContextParam(Object webApp, Object param, final String version)
        {
            ((WebApp)webApp).getContextParams().add((ParamValue) param);
        }

        @Override
        protected Servlet getServlet(Object webApp, String servletName)
        {
            List<Servlet> servlets = ((WebApp)webApp).getServlets();
            for (final Servlet servlet : servlets)
            {
                if (servlet.getServletName().equals(servletName))
                {
                    return servlet;
                }
            }
            return null;
        }

        @Override
        protected void verifyServlet(Object defaultServlet, String className)
        {
            assertEquals(className, ((Servlet) defaultServlet).getServletClass());
        }

        @Override
        protected void verifyLoadOnStartup(Object defaultServlet,
                Integer expectedValue)
        {
            assertEquals(expectedValue, ((Servlet)defaultServlet).getLoadOnStartup());
        }

        @Override
        protected void setServletOnMapping(Object mapping, Object servlet)
        {
            ((ServletMapping)mapping).setServletName(((Servlet)servlet).getServletName());
        }
        @Override
        protected void verifyMapping(Object mapping,
                String jsfDefaultServletName, String pattern)
        {
            assertEquals(jsfDefaultServletName, ((ServletMapping)mapping).getServletName());
            boolean foundPattern = false;
            for (final UrlPatternType urlPattern : ((ServletMapping)mapping).getUrlPatterns())
            {
                if (pattern.equals(urlPattern.getValue()))
                {
                    foundPattern = true;
                    break;
                }
            }
            assertTrue(foundPattern);
        }
        @SuppressWarnings("rawtypes")
        @Override
        protected List getContextParams(Object webApp, String version)
        {
            return ((WebApp)webApp).getContextParams();
        }
        @Override
        protected String getContextParamValue(Object param, String version)
        {
            return ((ParamValue)param).getParamValue();
        }
        
    }
    //
    // //@Test
    // public void testGetFileUrlPath_NonNullCases()
    // {
    // // a web app with valid servlet and extension mapping
    // WebApp webApp = _factory.createWebApp();
    // Servlet servlet = _factory.createServlet();
    // servlet.setServletClass(JSFUtils.JSF_SERVLET_CLASS);
    // servlet.setServletName("JSF Servlet");
    // webApp.getServlets().add(servlet);
    // ServletMapping mapping = _factory.createServletMapping();
    // mapping.setServletName("JSF Servlet");
    // UrlPatternType pattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
    // pattern.setValue(".faces");
    // mapping.getUrlPatterns().add(pattern);
    // webApp.getServletMappings().add(mapping);
    // IPath fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
    // IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
    // "/test.jsp"));
    // assertEquals("/test.faces", fileUrlPath.toString());
    //
    // // web app with valid servlet and non-default extension mapping
    // fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
    // IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
    // "/test.xhtml"));
    // assertEquals("/test.faces", fileUrlPath.toString());
    //
    // // web app with valid servlet and path-based mapping
    // pattern.setValue("/faces");
    // fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
    // IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
    // "/test.jsp"));
    // assertEquals("/faces/test.jsp", fileUrlPath.toString());
    //
    // // web app with valid servlet and path-based mapping match *
    // pattern.setValue("/faces/*");
    // fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
    // IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
    // "/test.jsp"));
    // assertEquals("/faces/test.jsp", fileUrlPath.toString());
    //
    // // web app with valid servlet and extension mapping based on a
    // // non-default, default extension
    // pattern.setValue(".jspx");
    // ParamValue param = JavaeeFactory.eINSTANCE.createParamValue();
    // param.setParamName(JSFUtils.JSF_DEFAULT_SUFFIX_CONTEXT_PARAM);
    // param.setParamValue(".xhtml");
    // webApp.getContextParams().add(param);
    // fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
    // IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
    // "/test.xhtml"));
    // assertEquals("/test.jspx", fileUrlPath.toString());
    // }
    //
    // //@Test
    // public void testGetFileUrlPath_NullCases()
    // {
    // // a null webApp object produces a null path
    // Path existingURL = new Path("foobar");
    // verifyNull(existingURL, null);
    //
    // // a non-null value that is not instanceof WebApp returns a null path
    // verifyNull(existingURL, new Object());
    // // a web app with no servlet produces a null path
    //
    // WebApp webApp = _factory.createWebApp();
    // verifyNull(existingURL, webApp);
    //
    // // a web app with no meaningful servlet returns null
    // Servlet servlet = _factory.createServlet();
    // webApp.getServlets().add(servlet);
    // verifyNull(existingURL, webApp);
    //
    // // wrong servlet class name
    // servlet.setServletClass("com.wrong.Servlet");
    // verifyNull(existingURL, webApp);
    //
    // // multiple wrong class names
    // servlet = _factory.createServlet();
    // servlet.setServletClass("com.AnotherWrong.Servlet");
    // webApp.getServlets().add(servlet);
    //
    // assertEquals(2, webApp.getServlets().size());
    // verifyNull(existingURL, webApp);
    //
    // // valid servlet class, but no mappings
    // servlet.setServletClass(JSFUtils.JSF_SERVLET_CLASS);
    // verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
    // new Path("/somepath")));
    // verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
    // new Path("/somepath.xhtml")));
    //
    // // has mapping but it's empty
    // ServletMapping mapping = _factory.createServletMapping();
    // webApp.getServletMappings().add(mapping);
    // assertEquals(1, webApp.getServletMappings().size());
    // verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
    // new Path("/somepath.xhtml")));
    //
    // // empty mapping that matches to servlet
    // mapping.setServletName(servlet.getServletName());
    // assertEquals(1, webApp.getServletMappings().size());
    // // assertTrue(servlet.getMappings().contains(mapping));
    // verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
    // new Path("/somepath.xhtml")));
    // }
    //
    // private void verifyNull(Path existingURL, Object webApp, IResource res)
    // {
    // assertNull(_jsfUtils.getFileUrlPath(webApp, res, null));
    // assertNull(_jsfUtils.getFileUrlPath(webApp, res, existingURL));
    // }
    //
    // private void verifyNull(Path existingURL, Object webApp)
    // {
    // verifyNull(existingURL, webApp, null);
    // }
    //
    // protected ServletMapping createServletMapping()
    // {
    // return null;
    // }
    //
    // protected void setServletClass(Servlet servlet, String name)
    // {
    // }
    //
    // protected EList getServlets(final WebApp webApp)
    // {
    // return null;
    // }
    //
    // protected void addToWebApp(final WebApp webApp, final ServletMapping
    // mapping)
    // {
    // }
    //
    // protected EList getServletMappings(final WebApp webApp)
    // {
    // return null;
    // }
    //
    // protected List getServletMappings(Servlet servlet)
    // {
    // return null;
    // }
    //
    // protected void setMappingData(final WebApp webApp, final List<String>
    // urlPatterns, final Servlet servlet,
    // final ServletMapping mapping)
    // {
    // }
    //
    // protected void setUrlPattern(final ServletMapping mapping, final
    // List<String> patterns)
    // {
    // }
    //
    // protected ContextParam createContextParam(final String name, final String
    // value)
    // {
    // return null;
    // }
    //
    // protected void setContextParam(final WebApp webApp, final ContextParam
    // param)
    // {
    // }
    //
    // protected void addToWebApp(final WebApp webApp, final Servlet servlet)
    // {
    // }
    //
    // protected Servlet getServlet(final WebApp webApp, final String
    // servletName)
    // {
    // return null;
    // }
    //
    // protected void verifyServlet(final Servlet defaultServlet, final String
    // className)
    // {
    // }
    //
    // protected void verifyLoadOnStartup(final Servlet defaultServlet, final
    // Integer expectedValue)
    // {
    // }
}
