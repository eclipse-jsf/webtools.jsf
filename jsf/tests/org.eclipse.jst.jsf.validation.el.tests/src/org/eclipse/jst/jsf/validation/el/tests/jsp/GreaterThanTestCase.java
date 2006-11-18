package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for greater than (gt, >)
 * 
 * @author cbateman
 */
public class GreaterThanTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception 
    {
        _srcFileName = "/testdata/jsps/greaterThan.jsp.data";
        _destFileName = "/greaterThan.jsp";

        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.stringProperty > '3'", getELText(_structuredDocument,852));
        assertEquals("myBean.stringProperty gt '3'", getELText(_structuredDocument,913));
        assertEquals("myBean.integerProperty > 3", getELText(_structuredDocument,975));
        assertEquals("myBean.integerProperty gt 3", getELText(_structuredDocument,1035));
        assertEquals("myBean.integerProperty > '4' ", getELText(_structuredDocument,1096));
        assertEquals("myBean.integerProperty gt '4' ", getELText(_structuredDocument,1159));
        assertEquals("myBean.comparableProperty > myBean.collectionProperty", getELText(_structuredDocument,1224));
        assertEquals("myBean.comparableProperty gt myBean.collectionProperty", getELText(_structuredDocument,1311));
        assertEquals("myBean.integerProperty > -3", getELText(_structuredDocument,1399));
        assertEquals("myBean.doubleProperty > 5", getELText(_structuredDocument,1460));
        assertEquals("5 gt myBean.bigIntegerProperty", getELText(_structuredDocument,1519));
        assertEquals("myBean.bigDoubleProperty > myBean.bigIntegerProperty", getELText(_structuredDocument,1583));

        assertEquals("5 > 3", getELText(_structuredDocument,1695));
        assertEquals("5 gt 3", getELText(_structuredDocument,1731));
        assertEquals("'4' > '34'", getELText(_structuredDocument,1768));
        assertEquals("'4' gt '34'", getELText(_structuredDocument,1809));
        assertEquals("'34' > '34'", getELText(_structuredDocument,1851));
        assertEquals("'34' gt '34'", getELText(_structuredDocument,1893));
        assertEquals("-5 > 2", getELText(_structuredDocument,1936));
        assertEquals("-5 gt 2", getELText(_structuredDocument,1973));
        assertEquals("2 > -5", getELText(_structuredDocument,2011));
        assertEquals("2 gt -5", getELText(_structuredDocument,2048));
        assertEquals("-5 > -5", getELText(_structuredDocument,2086));
        assertEquals("-5 gt -5", getELText(_structuredDocument,2124));
        assertEquals("myBean.integerProperty > null", getELText(_structuredDocument,2163));
        assertEquals("null gt myBean.integerProperty", getELText(_structuredDocument,2223));

        assertEquals("5 > true", getELText(_structuredDocument,2307));
        assertEquals("5 gt true", getELText(_structuredDocument,2346));
        assertEquals("myBean.integerProperty > myBean.booleanProperty", getELText(_structuredDocument,2386));
        assertEquals("myBean.integerProperty gt myBean.booleanProperty", getELText(_structuredDocument,2464));
        assertEquals("myBean.stringArrayProperty > myBean.booleanProperty", getELText(_structuredDocument,2543));
        assertEquals("myBean.stringArrayProperty gt myBean.booleanProperty", getELText(_structuredDocument,2625));
        assertEquals("myBean.integerProperty > true ", getELText(_structuredDocument,2711));
        assertEquals("myBean.integerProperty gt true ", getELText(_structuredDocument,2775));
        assertEquals("myBean.booleanProperty > true", getELText(_structuredDocument,2840));
        assertEquals("myBean.booleanProperty gt true", getELText(_structuredDocument,2903));
        assertEquals("true > false", getELText(_structuredDocument,2965));
        assertEquals("true gt false", getELText(_structuredDocument,3009));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(852, Signature.SIG_BOOLEAN);
        assertNoError(913, Signature.SIG_BOOLEAN);
        assertNoError(975, Signature.SIG_BOOLEAN);
        assertNoError(1035, Signature.SIG_BOOLEAN);
        assertNoError(1096, Signature.SIG_BOOLEAN);
        assertNoError(1159, Signature.SIG_BOOLEAN);
        assertNoError(1224, Signature.SIG_BOOLEAN);
        assertNoError(1311, Signature.SIG_BOOLEAN);
        assertNoError(1399, Signature.SIG_BOOLEAN);
        assertNoError(1460, Signature.SIG_BOOLEAN);
        assertNoError(1519, Signature.SIG_BOOLEAN);
        assertNoError(1583, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1695, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1731, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1768, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1809, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1851, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1893, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1936, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1973, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2011, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2048, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2086, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2124, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2163, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2223, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(2307, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2346, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2386, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2464, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2543, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2625, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2711, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2775, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2840, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2903, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2965, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3009, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);
    }
}
