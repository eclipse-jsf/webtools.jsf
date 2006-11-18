package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for logical not (not, !)
 * 
 * @author cbateman
 */
public class LogicalNotTestCase extends SingleJSPTestCase {

    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/logicalNOT.jsp.data";
        _destFileName = "/logicalNOT.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("!myBean.booleanProperty", getELText(_structuredDocument,853));
        assertEquals("not myBean.booleanProperty", getELText(_structuredDocument,910));
        assertEquals("!myBean.stringProperty", getELText(_structuredDocument,967));
        assertEquals("not myBean.stringProperty", getELText(_structuredDocument,1020));

        assertEquals("!true", getELText(_structuredDocument,1105));
        assertEquals("not true", getELText(_structuredDocument,1144));
        assertEquals("!false", getELText(_structuredDocument,1186));
        assertEquals("not false", getELText(_structuredDocument,1226));
        assertEquals("! 'true'", getELText(_structuredDocument,1269));
        assertEquals("not 'true'", getELText(_structuredDocument,1311));
        assertEquals("! 'notTrue'", getELText(_structuredDocument,1355));
        assertEquals("not 'notTrue'", getELText(_structuredDocument,1400));

        assertEquals("!5", getELText(_structuredDocument,1470));
        assertEquals("not 5", getELText(_structuredDocument,1503));
        assertEquals("!myBean.integerProperty", getELText(_structuredDocument,1539));
        assertEquals("not myBean.integerProperty", getELText(_structuredDocument,1593));
        assertEquals("!myBean.collectionProperty", getELText(_structuredDocument,1650));
        assertEquals("not myBean.collectionProperty", getELText(_structuredDocument,1707));
        assertEquals("!myBean.stringArrayProperty", getELText(_structuredDocument,1767));
        assertEquals("not myBean.stringArrayProperty", getELText(_structuredDocument,1825));
        assertEquals("!myBean.mapProperty", getELText(_structuredDocument,1886));
        assertEquals("not myBean.mapProperty", getELText(_structuredDocument,1936));
    }

    public void testNoErrorExprs() 
    {
        assertNoError(853, Signature.SIG_BOOLEAN);
        assertNoError(910, Signature.SIG_BOOLEAN);
        assertNoError(967, Signature.SIG_BOOLEAN);
        assertNoError(1020, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1105, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1144, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1186, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1226, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1269, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1311, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1355, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1400, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);
    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(1470, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
        
        list = assertSemanticError(1503, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1539, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1593, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1650, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1707, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1767, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1825, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1886, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1936, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }

}
