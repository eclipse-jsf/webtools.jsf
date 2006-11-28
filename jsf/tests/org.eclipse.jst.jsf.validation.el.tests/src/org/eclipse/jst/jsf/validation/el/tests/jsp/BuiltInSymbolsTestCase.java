package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
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
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/builtinSymbols.jsp.data";
        _destFileName = "/builtinSymbols.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("applicationScope", getELText(_structuredDocument,1003));
        assertEquals("sessionScope", getELText(_structuredDocument,1053));
        assertEquals("requestScope", getELText(_structuredDocument,1099));
        assertEquals("cookie", getELText(_structuredDocument,1145));
        assertEquals("facesContext", getELText(_structuredDocument,1185));
        assertEquals("header", getELText(_structuredDocument,1231));
        assertEquals("headerValues", getELText(_structuredDocument,1271));
        assertEquals("initParam", getELText(_structuredDocument,1317));
        assertEquals("param", getELText(_structuredDocument,1360));
        assertEquals("paramValues", getELText(_structuredDocument,1399));
        assertEquals("view", getELText(_structuredDocument,1444));
        assertEquals("applicationScope.mapBean", getELText(_structuredDocument,1533));
        assertEquals("sessionScope.myBean", getELText(_structuredDocument,1640));
        assertEquals("sessionScope.mapBean1", getELText(_structuredDocument,1690));
        assertEquals("sessionScope.myBeanSettable", getELText(_structuredDocument,1742));
        assertEquals("requestScope.myBeanSubClass", getELText(_structuredDocument,1852));
        assertEquals("requestScope.hiddenBean", getELText(_structuredDocument,1910));
        assertEquals("requestScope.bundle", getELText(_structuredDocument,1964));
        assertEquals("requestScope.bundle2", getELText(_structuredDocument,2014));
        assertEquals("empty cookie", getELText(_structuredDocument,2151));
        assertEquals("empty header", getELText(_structuredDocument,2194));
        assertEquals("empty headerValues", getELText(_structuredDocument,2237));
        assertEquals("empty param", getELText(_structuredDocument,2286));
        assertEquals("empty paramValues", getELText(_structuredDocument,2328));
        assertEquals("facesContext.application", getELText(_structuredDocument,2429));
        assertEquals("facesContext.clientIdsWithMessages", getELText(_structuredDocument,2484));
        assertEquals("facesContext.externalContext", getELText(_structuredDocument,2549));
        assertEquals("facesContext.maximumSeverity", getELText(_structuredDocument,2608));
        assertEquals("facesContext.messages", getELText(_structuredDocument,2667));
        assertEquals("facesContext.renderKit", getELText(_structuredDocument,2719));
        assertEquals("facesContext.renderResponse", getELText(_structuredDocument,2772));
        assertEquals("facesContext.responseComplete", getELText(_structuredDocument,2830));
        assertEquals("facesContext.responseStream", getELText(_structuredDocument,2890));
        assertEquals("facesContext.responseWriter", getELText(_structuredDocument,2948));
        assertEquals("facesContext.viewRoot", getELText(_structuredDocument,3006));
        assertEquals("view.viewId", getELText(_structuredDocument,3105));
        assertEquals("view.family", getELText(_structuredDocument,3147));
        assertEquals("view.locale", getELText(_structuredDocument,3189));
        assertEquals("view.renderKitId", getELText(_structuredDocument,3231));
        assertEquals("view.viewId", getELText(_structuredDocument,3278));
        assertEquals("sessionScope.myBean.integerProperty", getELText(_structuredDocument,3377));
        assertEquals("requestScope.bundle.bundleProp2", getELText(_structuredDocument,3446));
        assertEquals("3 + sessionScope.myBean.integerProperty", getELText(_structuredDocument,3564));

        assertEquals("applicationScope.notAMember", getELText(_structuredDocument,3661));
        assertEquals("sessionScope.notAMember", getELText(_structuredDocument,3722));
        assertEquals("requestScope.notAMember", getELText(_structuredDocument,3779));
        assertEquals("cookie.notAMember", getELText(_structuredDocument,3836));
        assertEquals("facesContext.notAMember", getELText(_structuredDocument,3887));
        assertEquals("header.notAMember", getELText(_structuredDocument,3944));
        assertEquals("headerValues.notAMember", getELText(_structuredDocument,3995));
        assertEquals("initParam.notAMember", getELText(_structuredDocument,4052));
        assertEquals("param.notAMember", getELText(_structuredDocument,4106));
        assertEquals("paramValues.notAMember", getELText(_structuredDocument,4156));
        assertEquals("view.notAMember", getELText(_structuredDocument,4212));
        assertEquals("applicationScope.myBean_none", getELText(_structuredDocument,4311));
        assertEquals("sessionScope.myBean_none", getELText(_structuredDocument,4373));
        assertEquals("requestScope.myBean_none", getELText(_structuredDocument,4431));

        assertEquals("!initParam", getELText(_structuredDocument,4507));    
    }

    public void testNoErrorExprs() 
    {
        assertNoError(1003, TypeConstants.TYPE_MAP);
        assertNoError(1053, TypeConstants.TYPE_MAP);
        assertNoError(1099, TypeConstants.TYPE_MAP);
        assertNoError(1145, TypeConstants.TYPE_MAP);
        assertNoError(1185, "Ljavax.faces.context.FacesContext;");
        assertNoError(1232, TypeConstants.TYPE_MAP);
        assertNoError(1272, TypeConstants.TYPE_MAP);
        assertNoError(1317, TypeConstants.TYPE_MAP);
        assertNoError(1360, TypeConstants.TYPE_MAP);
        assertNoError(1399, TypeConstants.TYPE_MAP);
        assertNoError(1444, "Ljavax.faces.component.UIViewRoot;");
        assertNoError(1533, "Lbeans.MapBean;");
        assertNoError(1640, "Lbeans.MyBean;");
        assertNoError(1690, "Lbeans.MapBean;");
        assertNoError(1742, "Lbeans.MyBeanSettable;");
        assertNoError(1852, "Lbeans.MyBeanSubClass;");
        assertNoError(1910, "Lbeans.MyBean;");

        assertNoError(1964, TypeConstants.TYPE_MAP);
        assertNoError(2014, TypeConstants.TYPE_MAP);
        
        assertNoError(2151, Signature.SIG_BOOLEAN);
        assertNoError(2194, Signature.SIG_BOOLEAN);
        assertNoError(2237, Signature.SIG_BOOLEAN);
        assertNoError(2286, Signature.SIG_BOOLEAN);
        assertNoError(2328, Signature.SIG_BOOLEAN);
        // TODO: can't check these until we can import the required API jars
        // into the project
//        assertEquals("facesContext.application", getELText(_structuredDocument,2854));
//        assertEquals("facesContext.clientIdsWithMessages", getELText(_structuredDocument,2909));
//        assertEquals("facesContext.externalContext", getELText(_structuredDocument,2974));
//        assertEquals("facesContext.maximumSeverity", getELText(_structuredDocument,3033));
//        assertEquals("facesContext.messages", getELText(_structuredDocument,3092));
//        assertEquals("facesContext.renderKit", getELText(_structuredDocument,3144));
//        assertEquals("facesContext.renderResponse", getELText(_structuredDocument,3197));
//        assertEquals("facesContext.responseComplete", getELText(_structuredDocument,3255));
//        assertEquals("facesContext.responseStream", getELText(_structuredDocument,3315));
//        assertEquals("facesContext.responseWriter", getELText(_structuredDocument,3373));
//        assertEquals("facesContext.viewRoot", getELText(_structuredDocument,3431));
//        assertEquals("view.viewId", getELText(_structuredDocument,3530));
//        assertEquals("view.family", getELText(_structuredDocument,3572));
//        assertEquals("view.locale", getELText(_structuredDocument,3614));
//        assertEquals("view.renderKitId", getELText(_structuredDocument,3656));
//        assertEquals("view.viewId", getELText(_structuredDocument,3703));
        assertNoError(3377, Signature.SIG_INT);
        assertNoError(3446, TypeConstants.TYPE_STRING);
        assertNoError(3564, Signature.SIG_LONG);
    }
    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(3661,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3722,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3779,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3836,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3887,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3944,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3995,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(4052,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(4106,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(4156,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(4212,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(4311,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(4373,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(4431,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }
    public void testErrorExprs() 
    {
        List list = assertSemanticError(4507,null,1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }
}
