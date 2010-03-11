package org.eclipse.jst.jsf.core.tests.project.facet;

import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.jsf.core.JSFVersion;

//@Category(NoPluginEnvironment.class)
public class TestJSFUtils11 extends TestJSFUtils
{
    @Override
//    @Before
    public void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    protected WebAppAccessor createWebAccessor()
    {
        WebapplicationFactory factory = WebapplicationFactory.eINSTANCE;
        return new WebAppTestAccessor(factory);
    }

    //@Test
    public void testUpdateWebApp_ExistingServlet_2_3()
    {
        testUpdateWebApp_ExistingServlet("2.3");
    }

    //@Test
    public void testUpdateWebApp_ExistingServlet_2_4()
    {
        testUpdateWebApp_ExistingServlet("2.4");
    }

    //@Test
    public void testUpdateWebApp_NewServlet_2_3()
    {
        testUpdateWebApp_NewServlet("2.3");
    }

    //@Test
    public void testUpdateWebApp_NewServlet_2_4()
    {
        testUpdateWebApp_NewServlet("2.4");
    }

    //@Test
    public void testRollbackWebApp_2_3()
    {
        super.testRollbackWebApp("2.3");
    }

    //@Test
    public void testRollbackWebApp_2_4()
    {
        super.testRollbackWebApp("2.4");
    }

    //@Test
    public void testGetFileUrlPath_NonNullCases_2_3()
    {
        super.testGetFileUrlPath_NonNullCases("2.3");
    }

    //@Test
    public void testGetFileUrlPath_NonNullCases_2_4()
    {
        super.testGetFileUrlPath_NonNullCases("2.4");
    }

    //@Test
    public void testGetFileUrlPath_NullCases_2_3()
    {
        super.testGetFileUrlPath_NullCases("2.3");
    }

    //@Test
    public void testGetFileUrlPath_NullCases_2_4()
    {
        super.testGetFileUrlPath_NullCases("2.4");
    }

    @Override
    protected void doPre25SpecificOrNoop(Path existingURL, Object webApp,
            Object servlet)
    {
        WebapplicationFactory factory = WebapplicationFactory.eINSTANCE;
        // the servlet has a jsp type returns null
        ((Servlet) servlet).setWebType(factory.createJSPType());
        verifyNull(existingURL, webApp);

        // the servlet has a servlet type but null class returns null
        ((Servlet) servlet).setWebType(factory.createServletType());
        verifyNull(existingURL, webApp);
    }

    @Override
    protected JSFVersion getVersionToTestIn()
    {
        return JSFVersion.V1_1;
    }

    private static class WebAppTestAccessor extends WebAppAccessor
    {
        private WebapplicationFactory _factory;

        public WebAppTestAccessor(WebapplicationFactory factory)
        {
            _factory = factory;
        }

        @Override
        @SuppressWarnings("rawtypes")
        protected List getServletMappings_Servlet(final Object webApp,
                Object servlet)
        {
            return ((Servlet) servlet).getMappings();
        }

        @Override
        @SuppressWarnings("rawtypes")
        protected EList getServletMappings_WebApp(final Object webApp)
        {
            return ((WebApp) webApp).getServletMappings();
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void addMappingToWebApp(final Object webApp,
                final Object mapping)
        {
            getServletMappings_WebApp(webApp).add((ServletMapping) mapping);
        }

        @Override
        @SuppressWarnings("rawtypes")
        protected EList getServlets(final Object webApp)
        {
            return ((WebApp) webApp).getServlets();
        }

        @Override
        protected void setServletClass(Object servlet, String name)
        {
            ((ServletType) ((Servlet) servlet).getWebType()).setClassName(name);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void setContextParam(final Object webApp, final Object param, final String version)
        {
            if ("2.3".equals(version))
            {
                ((WebApp) webApp).getContexts().add(param);
            }
            else
            {
                ((WebApp) webApp).getContextParams().add(param);
            }
        }

        @Override
        protected Object createContextParam(final String name,
                final String value, final String version)
        {
            if ("2.3".equals(version))
            {
                final ContextParam param = _factory.createContextParam();
                param.setParamName(name);
                param.setParamValue(value);
                return param;
            }
            final ParamValue param = CommonFactory.eINSTANCE.createParamValue();
            param.setName(name);
            param.setValue(value);
            return param;
        }

        @Override
        protected void setUrlPattern(final Object mapping,
                final List<String> patterns)
        {
            assertEquals(
                    "Webapp 2.4 and before support only one pattern per mapping",
                    1, patterns.size());
            ((ServletMapping) mapping).setUrlPattern(patterns.get(0));
        }

        @Override
        protected void setMappingData(final Object webApp,
                final List<String> urlPatterns, final Object servlet,
                final Object mapping)
        {
            ((ServletMapping) mapping).setServlet((Servlet) servlet);
            for (final String pattern : urlPatterns)
            {
                ((ServletMapping) mapping).setUrlPattern(pattern);
            }
            ((ServletMapping) mapping).setWebApp((WebApp) webApp);
        }

        @Override
        protected ServletMapping createServletMapping()
        {
            return _factory.createServletMapping();
        }

        @Override
        protected void verifyLoadOnStartup(final Object defaultServlet,
                final Integer expectedValue)
        {
            assertEquals(expectedValue, ((Servlet) defaultServlet)
                    .getLoadOnStartup());
        }

        @Override
        protected void verifyServlet(final Object defaultServlet,
                final String className)
        {
            final WebType webType = ((Servlet) defaultServlet).getWebType();
            assertTrue(webType.isServletType());
            final ServletType servletType = (ServletType) ((Servlet) defaultServlet)
                    .getWebType();
            assertEquals(className, servletType.getClassName());
        }

        @Override
        protected WebApp createWebApp(final String version)
        {
            final WebApp webApp = _factory.createWebApp();
            webApp.setVersion(version);
            return webApp;
        }

        @Override
        protected Servlet getServlet(final Object webApp,
                final String servletName)
        {
            final Servlet defaultServlet = ((WebApp) webApp)
                    .getServletNamed(servletName);
            if (defaultServlet != null)
            {
                assertEquals(servletName, defaultServlet.getServletName());
            }
            return defaultServlet;
        }

        @Override
        protected Servlet createServlet(final String servletName,
                final String servletClass)
        {
            final Servlet servlet = _factory.createServlet();
            servlet.setServletName(servletName);
            servlet.setWebType(_factory.createServletType());
            ((ServletType) servlet.getWebType()).setClassName(servletClass);
            return servlet;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void addServletToWebApp(Object webApp, Object servlet)
        {
            getServlets(webApp).add(servlet);
        }

        @Override
        protected void setServletOnMapping(Object mapping, Object servlet)
        {
            ((ServletMapping) mapping).setServlet((Servlet) servlet);
        }

        @Override
        protected void verifyMapping(Object mapping,
                String jsfDefaultServletName, String pattern)
        {
            assertEquals(jsfDefaultServletName, ((ServletMapping) mapping)
                    .getServlet().getServletName());
            assertEquals(pattern, ((ServletMapping) mapping).getUrlPattern());
        }

        @SuppressWarnings("rawtypes")
        @Override
        protected List getContextParams(Object webApp, String version)
        {
            if ("2.3".equals(version))
            {
                return ((WebApp)webApp).getContexts();
            }
            return ((WebApp)webApp).getContextParams();
        }

        @Override
        protected String getContextParamValue(Object param, String version)
        {
            if ("2.3".equals(version))
            {
                return ((ContextParam)param).getParamValue();
            }
            return ((ParamValue)param).getValue();
        }

    }
}
