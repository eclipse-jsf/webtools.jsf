package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.types.TypeConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

public class ArithmeticMinusTestCase extends SingleJSPTestCase
{
    protected void setUp() throws Exception 
    {
        _srcFileName = "/testdata/jsps/arithmeticMinus.jsp.data";
        _destFileName = "/WEB-INF/arithmeticMinus.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.integerProperty - 3", getELText(_structuredDocument,852));
        assertEquals("myBean.stringProperty - 3", getELText(_structuredDocument,912));
        assertEquals("myBean.integerProperty - myBean.integerProperty", getELText(_structuredDocument,971));
        assertEquals("myBean.bigIntegerProperty - 4", getELText(_structuredDocument,1052));
        assertEquals("myBean.bigIntegerProperty - 5.5", getELText(_structuredDocument,1115));
        assertEquals("myBean.bigDoubleProperty - 5.5", getELText(_structuredDocument,1180));
        assertEquals("myBean.doubleProperty - 5", getELText(_structuredDocument,1244));

        assertEquals("5 - 3", getELText(_structuredDocument,1336));
        assertEquals("5.5 - 4", getELText(_structuredDocument,1372));
        assertEquals("5.5 - null", getELText(_structuredDocument,1410));
        assertEquals("'5' - '4'", getELText(_structuredDocument,1454));
        assertEquals("null - null", getELText(_structuredDocument,1497));
        assertEquals("5.5 - 3.5", getELText(_structuredDocument,1543));

        assertEquals("5 - true", getELText(_structuredDocument,1606));
        assertEquals("myBean.integerProperty - myBean.booleanProperty", getELText(_structuredDocument,1645));
        assertEquals("myBean.stringArrayProperty - myBean.booleanProperty", getELText(_structuredDocument,1723));
        assertEquals("myBean.integerProperty - true ", getELText(_structuredDocument,1808));
        assertEquals("'a' - 'b'", getELText(_structuredDocument,1872));
        assertEquals("myBean.bigIntegerProperty - true", getELText(_structuredDocument,1915));
    }
    
    public void testNoErrorExprs()
    {
        assertNoError(852, Signature.SIG_LONG);
        assertNoError(912, Signature.SIG_LONG);
        assertNoError(971, Signature.SIG_LONG);
        assertNoError(1052, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1115, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1180, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1244, Signature.SIG_DOUBLE);
    }
    
    public void testWarningExprs()
    {
        List list = assertSemanticWarning(1336, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1372, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1410, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1454, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1497, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);
        
        list = assertSemanticWarning(1543, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }
    
    public void testErrorExprs()
    {
        List list = assertSemanticError(1606, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1645, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1723, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1808, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1872, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID);
        
        list = assertSemanticError(1915, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
