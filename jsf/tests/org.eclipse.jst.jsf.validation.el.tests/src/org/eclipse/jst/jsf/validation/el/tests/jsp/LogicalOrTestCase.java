package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

public class LogicalOrTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/logicalOR.jsp.data";
        _destFileName = "/logicalOR.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("myBean.booleanProperty or myBean.booleanProperty", getELText(_structuredDocument,852));
        assertEquals("myBean.booleanProperty || myBean.booleanProperty", getELText(_structuredDocument,934));
        assertEquals("false || myBean.booleanProperty", getELText(_structuredDocument,1016));
        assertEquals("false or myBean.booleanProperty", getELText(_structuredDocument,1081));
        assertEquals("null || myBean.booleanProperty ", getELText(_structuredDocument,1146));
        assertEquals("null or myBean.booleanProperty ", getELText(_structuredDocument,1211));
        assertEquals("'notTrue' || myBean.booleanProperty", getELText(_structuredDocument,1276));
        assertEquals("myBean.booleanProperty || false", getELText(_structuredDocument,1352));
        assertEquals("myBean.booleanProperty or false", getELText(_structuredDocument,1417));
        assertEquals("myBean.booleanProperty || null ", getELText(_structuredDocument,1482));
        assertEquals("myBean.booleanProperty or null", getELText(_structuredDocument,1547));
        assertEquals("myBean.booleanProperty || 'notTrue'", getELText(_structuredDocument,1611));
        assertEquals("myBean.booleanProperty or 'notTrue'", getELText(_structuredDocument,1682));

        assertEquals("'true' or myBean.booleanProperty", getELText(_structuredDocument,1777));
        assertEquals("'true' || myBean.booleanProperty", getELText(_structuredDocument,1844));
        assertEquals("myBean.booleanProperty or true ", getELText(_structuredDocument,1908));
        assertEquals("myBean.booleanProperty || true ", getELText(_structuredDocument,1970));
        assertEquals("myBean.booleanProperty || 'true'", getELText(_structuredDocument,2032));
        assertEquals("false || true", getELText(_structuredDocument,2095));
        assertEquals("null || true", getELText(_structuredDocument,2139));

        assertEquals("myBean.integerProperty || false", getELText(_structuredDocument,2209));
        assertEquals("false || myBean.integerProperty", getELText(_structuredDocument,2274));
        assertEquals("4 || false", getELText(_structuredDocument,2339));
    }

    public void testNoErrorExprs() 
    {
        assertNoError(852, Signature.SIG_BOOLEAN);
        assertNoError(934, Signature.SIG_BOOLEAN);
        assertNoError(1016, Signature.SIG_BOOLEAN);
        assertNoError(1081, Signature.SIG_BOOLEAN);
        assertNoError(1146, Signature.SIG_BOOLEAN);
        assertNoError(1211, Signature.SIG_BOOLEAN);
        assertNoError(1276, Signature.SIG_BOOLEAN);
        assertNoError(1352, Signature.SIG_BOOLEAN);
        assertNoError(1417, Signature.SIG_BOOLEAN);
        assertNoError(1482, Signature.SIG_BOOLEAN);
        assertNoError(1547, Signature.SIG_BOOLEAN);
        assertNoError(1611, Signature.SIG_BOOLEAN);
        assertNoError(1682, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1777, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1844, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1908, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1970, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2032, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(2095, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2139, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);
    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(2209, null,1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        assertSemanticError(2274, null,1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        assertSemanticError(2339, null,1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }
}
