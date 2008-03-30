package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for built-in (implicit) symbol resolution
 * 
 * @author cbateman
 *
 */
public class BuiltInSymbolsTestCase extends SingleJSPTestCase
{
    public BuiltInSymbolsTestCase()
    {
        super("/testdata/jsps/builtinSymbols.jsp.data", "/builtinSymbols.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("applicationScope", getELText(_structuredDocument,975));
        assertEquals("sessionScope", getELText(_structuredDocument,1024));
        assertEquals("requestScope", getELText(_structuredDocument,1069));
        assertEquals("cookie", getELText(_structuredDocument,1114));
        assertEquals("facesContext", getELText(_structuredDocument,1153));
        assertEquals("header", getELText(_structuredDocument,1198));
        assertEquals("headerValues", getELText(_structuredDocument,1237));
        assertEquals("initParam", getELText(_structuredDocument,1282));
        assertEquals("param", getELText(_structuredDocument,1324));
        assertEquals("paramValues", getELText(_structuredDocument,1362));
        assertEquals("view", getELText(_structuredDocument,1406));
        assertEquals("applicationScope.mapBean", getELText(_structuredDocument,1492));
        assertEquals("sessionScope.myBean", getELText(_structuredDocument,1596));
        assertEquals("sessionScope.mapBean1", getELText(_structuredDocument,1645));
        assertEquals("sessionScope.myBeanSettable", getELText(_structuredDocument,1696));
        assertEquals("requestScope.myBeanSubClass", getELText(_structuredDocument,1803));
        assertEquals("requestScope.hiddenBean", getELText(_structuredDocument,1860));
        assertEquals("requestScope.bundle", getELText(_structuredDocument,1913));
        assertEquals("requestScope.bundle2", getELText(_structuredDocument,1962));
        assertEquals("empty cookie", getELText(_structuredDocument,2096));
        assertEquals("empty header", getELText(_structuredDocument,2138));
        assertEquals("empty headerValues", getELText(_structuredDocument,2180));
        assertEquals("empty param", getELText(_structuredDocument,2228));
        assertEquals("empty paramValues", getELText(_structuredDocument,2269));
        assertEquals("facesContext.application", getELText(_structuredDocument,2367));
        assertEquals("facesContext.clientIdsWithMessages", getELText(_structuredDocument,2421));
        assertEquals("facesContext.externalContext", getELText(_structuredDocument,2485));
        assertEquals("facesContext.maximumSeverity", getELText(_structuredDocument,2543));
        assertEquals("facesContext.messages", getELText(_structuredDocument,2601));
        assertEquals("facesContext.renderKit", getELText(_structuredDocument,2652));
        assertEquals("facesContext.renderResponse", getELText(_structuredDocument,2704));
        assertEquals("facesContext.responseComplete", getELText(_structuredDocument,2761));
        assertEquals("facesContext.responseStream", getELText(_structuredDocument,2820));
        assertEquals("facesContext.responseWriter", getELText(_structuredDocument,2877));
        assertEquals("facesContext.viewRoot", getELText(_structuredDocument,2934));
        assertEquals("view.viewId", getELText(_structuredDocument,3030));
        assertEquals("view.family", getELText(_structuredDocument,3071));
        assertEquals("view.locale", getELText(_structuredDocument,3112));
        assertEquals("view.renderKitId", getELText(_structuredDocument,3153));
        assertEquals("view.viewId", getELText(_structuredDocument,3199));
        assertEquals("sessionScope.myBean.integerProperty", getELText(_structuredDocument,3295));
        assertEquals("requestScope.bundle.bundleProp2", getELText(_structuredDocument,3363));
        assertEquals("3 + sessionScope.myBean.integerProperty", getELText(_structuredDocument,3478));

        assertEquals("applicationScope.notAMember", getELText(_structuredDocument,3572));
        assertEquals("sessionScope.notAMember", getELText(_structuredDocument,3632));
        assertEquals("requestScope.notAMember", getELText(_structuredDocument,3688));
        assertEquals("cookie.notAMember", getELText(_structuredDocument,3744));
        assertEquals("facesContext.notAMember", getELText(_structuredDocument,3794));
        assertEquals("header.notAMember", getELText(_structuredDocument,3850));
        assertEquals("headerValues.notAMember", getELText(_structuredDocument,3900));
        assertEquals("initParam.notAMember", getELText(_structuredDocument,3956));
        assertEquals("param.notAMember", getELText(_structuredDocument,4009));
        assertEquals("paramValues.notAMember", getELText(_structuredDocument,4058));
        assertEquals("view.notAMember", getELText(_structuredDocument,4113));
        assertEquals("applicationScope.myBean_none", getELText(_structuredDocument,4209));
        assertEquals("sessionScope.myBean_none", getELText(_structuredDocument,4270));
        assertEquals("requestScope.myBean_none", getELText(_structuredDocument,4327));

        assertEquals("!initParam", getELText(_structuredDocument,4400));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(975, TypeConstants.TYPE_MAP);
        assertNoError(1024, TypeConstants.TYPE_MAP);
        assertNoError(1069, TypeConstants.TYPE_MAP);
        assertNoError(1114, TypeConstants.TYPE_MAP);
        assertNoError(1153, "Ljavax.faces.context.FacesContext;");
        assertNoError(1198, TypeConstants.TYPE_MAP);
        assertNoError(1237, TypeConstants.TYPE_MAP);
        assertNoError(1282, TypeConstants.TYPE_MAP);
        assertNoError(1324, TypeConstants.TYPE_MAP);
        assertNoError(1362, TypeConstants.TYPE_MAP);
        assertNoError(1406, "Ljavax.faces.component.UIViewRoot;");
        assertNoError(1492, "Lbeans.MapBean;");
        assertNoError(1596, "Lbeans.MyBean;");
        assertNoError(1645, "Lbeans.MapBean;");
        assertNoError(1696, "Lbeans.MyBeanSettable;");
        assertNoError(1803, "Lbeans.MyBeanSubClass;");
        assertNoError(1860, "Lbeans.MyBean;");

        assertNoError(1913, TypeConstants.TYPE_MAP);
        assertNoError(1962, TypeConstants.TYPE_MAP);

        assertNoError(2096, Signature.SIG_BOOLEAN);
        assertNoError(2138, Signature.SIG_BOOLEAN);
        assertNoError(2180, Signature.SIG_BOOLEAN);
        assertNoError(2228, Signature.SIG_BOOLEAN);
        assertNoError(2269, Signature.SIG_BOOLEAN);
        // TODO: can't check these until we can import the required API jars
        // into the project
        //        assertEquals("facesContext.application", getELText(_structuredDocument,2367));
        //        assertEquals("facesContext.clientIdsWithMessages", getELText(_structuredDocument,2421));
        //        assertEquals("facesContext.externalContext", getELText(_structuredDocument,2485));
        //        assertEquals("facesContext.maximumSeverity", getELText(_structuredDocument,2543));
        //        assertEquals("facesContext.messages", getELText(_structuredDocument,2601));
        //        assertEquals("facesContext.renderKit", getELText(_structuredDocument,2652));
        //        assertEquals("facesContext.renderResponse", getELText(_structuredDocument,2704));
        //        assertEquals("facesContext.responseComplete", getELText(_structuredDocument,2761));
        //        assertEquals("facesContext.responseStream", getELText(_structuredDocument,2820));
        //        assertEquals("facesContext.responseWriter", getELText(_structuredDocument,2877));
        //        assertEquals("facesContext.viewRoot", getELText(_structuredDocument,2934));
        //        assertEquals("view.viewId", getELText(_structuredDocument,3030));
        //        assertEquals("view.family", getELText(_structuredDocument,3071));
        //        assertEquals("view.locale", getELText(_structuredDocument,3112));
        //        assertEquals("view.renderKitId", getELText(_structuredDocument,3153));
        //        assertEquals("view.viewId", getELText(_structuredDocument,3199));
        assertNoError(3295, Signature.SIG_INT);
        assertNoError(3363, TypeConstants.TYPE_STRING);
        assertNoError(3478, Signature.SIG_LONG);
        
        // can't know what's actually in the scope map.  so anything unresolved is just
        // no error Java object.
        assertNoError(3572, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3632,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3688,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3744,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3794,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3850,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3900,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3956,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4009,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4058,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4113,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4209,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4270,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4327,TypeConstants.TYPE_JAVAOBJECT);
    }
    @Override
    public void testWarningExprs()
    {
        // no warnings.  The issues marked warnings in the jsp test data
        // are no longer, since we realized that maps can contain anything...
        // the  only exceptino is FacesContext which is resovled to a map 
        // at test time since the  API  is not in the classpath.  This
        // should be changed if start using jsf api/impl in these tests TODO
    }

    @Override
    public void testErrorExprs()
    {
        final List<ReportedProblem> list = assertSemanticError(4400,null,1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }
}
