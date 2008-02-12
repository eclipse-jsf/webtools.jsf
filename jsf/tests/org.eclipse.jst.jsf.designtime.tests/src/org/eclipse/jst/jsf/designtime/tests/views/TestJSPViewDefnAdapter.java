package org.eclipse.jst.jsf.designtime.tests.views;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter.DTELExpression;
import org.eclipse.jst.jsf.designtime.tests.DesignTimeTestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

public class TestJSPViewDefnAdapter extends TestCase
{
    private final String              ENV_JSF_VERSION   = "jsfRuntimeVersion";

    private JSFVersion                _jsfVersion;

    private WebProjectTestEnvironment _webProjectTestEnv;

    private IFile                     _jspFile;

    private IStructuredModel          _structuredModel;

    private final static ELData       EMPTY_EXPR        =
                                                                new ELData(
                                                                        "   ",
                                                                        566);
    private final static ELData       VAR_EXPR          =
                                                                new ELData(
                                                                        "myBean",
                                                                        599);
    private final static ELData       BEAN_PROP_EXPR    =
                                                                new ELData(
                                                                        "myBean.property",
                                                                        635);
    private final static ELData       BUILTIN_PROP_EXPR =
                                                                new ELData(
                                                                        "paramValues.foo",
                                                                        680);
    private final static ELData       METHOD_EXPR       =
                                                                new ELData(
                                                                        "myBean.actionMethod",
                                                                        729);

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        final String version = System.getProperty(ENV_JSF_VERSION);

        if (version == null)
        {
            _jsfVersion = JSFVersion.V1_1;
        }
        else
        {
            _jsfVersion = JSFVersion.valueOfString(version);
        }
        // only 1.1 and 1.2 are supported
        assertTrue(_jsfVersion == JSFVersion.V1_1
                || _jsfVersion == JSFVersion.V1_2);

        // assertNotNull(JSFCoreUtilHelper.getJSFRuntimeJarsDirectory(_jsfVersion));
        //
        final String jst_web_version =
                (_jsfVersion == JSFVersion.V1_1) ? "2.4"
                        : ((_jsfVersion == JSFVersion.V1_2) ? "2.5" : null);
        assertNotNull(jst_web_version);

        final String jst_jsf_version = _jsfVersion.toString();

        _webProjectTestEnv =
                new WebProjectTestEnvironment(getClass().getName() + "_"
                        + getName(), JavaFacetUtils.JAVA_50,
                        ProjectFacetsManager.getProjectFacet("jst.web")
                                .getVersion(jst_web_version));

        _webProjectTestEnv.createProject(false);
        assertNotNull(_webProjectTestEnv);
        assertNotNull(_webProjectTestEnv.getTestProject());
        assertTrue(_webProjectTestEnv.getTestProject().isAccessible());

        final JSFFacetedTestEnvironment jsfFacetedTestEnv =
                new JSFFacetedTestEnvironment(_webProjectTestEnv);
        jsfFacetedTestEnv.initialize(jst_jsf_version);

        _jspFile =
                (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                        DesignTimeTestsPlugin.getDefault().getBundle(),
                        "/testdata/testdata2.jsp.data", "testdata2.jsp");

        _structuredModel =
                StructuredModelManager.getModelManager().getModelForRead(
                        _jspFile);

    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();

        _structuredModel.releaseFromRead();
    }

    public final void testSanity()
    {
        final IStructuredDocument sdoc =
                _structuredModel.getStructuredDocument();
        assertNotNull(sdoc);

        EMPTY_EXPR.assertELText(sdoc);
        VAR_EXPR.assertELText(sdoc);
        BEAN_PROP_EXPR.assertELText(sdoc);
        BUILTIN_PROP_EXPR.assertELText(sdoc);
        METHOD_EXPR.assertELText(sdoc);
    }

    public final void testGetELExpressionIModelContext() throws Exception
    {
        assertTrue(DesignTimeApplicationManager
                .hasJSFDesignTime(_webProjectTestEnv.getTestProject()));

        final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(_webProjectTestEnv
                        .getTestProject());
        assertNotNull(manager);

        IDTViewHandler handler = manager.getViewHandler();
        DTFacesContext facesContext = manager.getFacesContext(_jspFile);
        assertNotNull(facesContext);
        assertTrue(handler.supportsViewDefinition(_jspFile));

        XMLViewDefnAdapter adapter =
                (XMLViewDefnAdapter) handler.getViewMetadataAdapterFactory(
                        facesContext).createAdapter(facesContext, "");
        final IStructuredDocument sdoc = _structuredModel.getStructuredDocument();
        
        checkELExpression(adapter, sdoc, EMPTY_EXPR);
        checkELExpression(adapter, sdoc, VAR_EXPR);
        checkELExpression(adapter, sdoc, BEAN_PROP_EXPR);
        checkELExpression(adapter, sdoc, BUILTIN_PROP_EXPR);
        checkELExpression(adapter, sdoc, METHOD_EXPR);
    }

    private void checkELExpression(final XMLViewDefnAdapter adapter,
            final IStructuredDocument sdoc, final ELData elData)
            throws Exception
    {
        final String expectedText = elData._expectedText;
        final int offset = elData._offset;

        // ensure that at ever valid offset in the EL expression, we get a
        // valid el text back with it's sdoc's offset set to the start offset
        // of the region.
        for (int i = 0; i < expectedText.length(); i++)
        {
            final IStructuredDocumentContext context =
                IStructuredDocumentContextFactory.INSTANCE.getContext(sdoc,offset+i);
            final DTELExpression elExpr = adapter.getELExpression(context);
            
            assertEquals(expectedText.trim(), elExpr.getText().trim());
            assertEquals(offset, elExpr.getDocumentContext().getDocumentPosition());
        }
    }

    private static class ELData
    {
        private final String _expectedText;
        private final int    _offset;

        ELData(final String expectedText, int offset)
        {
            _expectedText = expectedText;
            _offset = offset;
        }

        IStructuredDocumentContext getContext(IStructuredDocument sdoc)
        {
            final IStructuredDocumentContext context =
                    IStructuredDocumentContextFactory.INSTANCE.getContext(sdoc,
                            _offset);
            assertNotNull(context);
            return context;
        }

        String getELText(IStructuredDocument sdoc)
        {
            final ITextRegionContextResolver resolver =
                    IStructuredDocumentContextResolverFactory.INSTANCE
                            .getTextRegionResolver(getContext(sdoc));

            assertEquals(DOMJSPRegionContexts.JSP_VBL_CONTENT, resolver
                    .getRegionType());
            return resolver.getRegionText();
        }

        void assertELText(IStructuredDocument sdoc)
        {
            assertEquals(_expectedText, getELText(sdoc));
        }
    }
}
