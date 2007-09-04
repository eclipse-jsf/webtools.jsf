package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * Test suite for arithimetic divide (/, div)
 * @author cbateman
 *
 */
public class ArithmeticDivideTestCase extends SingleJSPTestCase 
{
    public ArithmeticDivideTestCase() 
    {
        super("/testdata/jsps/arithmeticDivide.jsp.data", "/WEB-INF/arithmeticDivide.jsp", JSFVersion.V1_1, FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception 
    {
        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.integerProperty / 3", getELText(_structuredDocument,852));
        assertEquals("myBean.integerProperty div 3", getELText(_structuredDocument,912));
        assertEquals("myBean.stringProperty / 3", getELText(_structuredDocument,974));
        assertEquals("myBean.stringProperty div 3", getELText(_structuredDocument,1033));
        assertEquals("myBean.integerProperty / myBean.integerProperty", getELText(_structuredDocument,1094));
        assertEquals("myBean.integerProperty div myBean.integerProperty", getELText(_structuredDocument,1175));
        assertEquals("myBean.bigIntegerProperty / 4", getELText(_structuredDocument,1258));
        assertEquals("myBean.bigIntegerProperty / 5.5", getELText(_structuredDocument,1321));
        assertEquals("myBean.bigDoubleProperty / 5.5", getELText(_structuredDocument,1386));
        assertEquals("myBean.doubleProperty / 5", getELText(_structuredDocument,1450));

        assertEquals("5 / 3", getELText(_structuredDocument,1542));
        assertEquals("5 div 3", getELText(_structuredDocument,1578));
        assertEquals("5.5 / 4", getELText(_structuredDocument,1616));
        assertEquals("5.5 div 4", getELText(_structuredDocument,1654));
        assertEquals("'5' / '4'", getELText(_structuredDocument,1697));
        assertEquals("'5' div '4'", getELText(_structuredDocument,1740));
        assertEquals("null / null", getELText(_structuredDocument,1785));
        assertEquals("null div null", getELText(_structuredDocument,1830));
        assertEquals("5.5 / 3.5", getELText(_structuredDocument,1877));

        assertEquals("5 / true", getELText(_structuredDocument,1945));
        assertEquals("5 div true", getELText(_structuredDocument,1984));
        assertEquals("myBean.integerProperty / myBean.booleanProperty", getELText(_structuredDocument,2025));
        assertEquals("myBean.integerProperty div myBean.booleanProperty", getELText(_structuredDocument,2103));
        assertEquals("myBean.stringArrayProperty / myBean.booleanProperty", getELText(_structuredDocument,2183));
        assertEquals("myBean.integerProperty div myBean.booleanProperty", getELText(_structuredDocument,2265));
        assertEquals("myBean.integerProperty / true ", getELText(_structuredDocument,2348));
        assertEquals("myBean.integerProperty div true ", getELText(_structuredDocument,2412));
        assertEquals("'a' / 'b'", getELText(_structuredDocument,2478));
        assertEquals("'a' div 'b'", getELText(_structuredDocument,2521));
        assertEquals("5.5 / null", getELText(_structuredDocument,2563));
        assertEquals("5.5 div null", getELText(_structuredDocument,2604));
        assertEquals("5/0", getELText(_structuredDocument,2647));
        assertEquals("5 div 0", getELText(_structuredDocument,2681));
        assertEquals("myBean.bigIntegerProperty + true", getELText(_structuredDocument,2719));
        assertEquals("myBean.bigDoubleProperty / null", getELText(_structuredDocument,2782));
        assertEquals("myBean.bigDoubleProperty div true", getELText(_structuredDocument,2844));
    }
    
    public void testNoErrorExprs()
    {
        assertNoError(852, Signature.SIG_DOUBLE);
        assertNoError(912, Signature.SIG_DOUBLE);
        assertNoError(974, Signature.SIG_DOUBLE);
        assertNoError(1033, Signature.SIG_DOUBLE);
        assertNoError(1094, Signature.SIG_DOUBLE);
        assertNoError(1175, Signature.SIG_DOUBLE);
        assertNoError(1258, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1321, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1386, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1450, Signature.SIG_DOUBLE);
    }
    
    public void testWarningExprs()
    {
        List<IMessage> list = assertSemanticWarning(1542, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1578, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1616, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1654, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1697, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1740, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1785, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);

        list = assertSemanticWarning(1830, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);
        
        list = assertSemanticWarning(1877, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }
    
    public void testErrorExprs()
    {
        List<IMessage> list = assertSemanticError(1945, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1984, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2025, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2103, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2183, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2265, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2348, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2412, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2478, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2521, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2563, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2604, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2647, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2681, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);
        
        list = assertSemanticError(2719, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2782, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2844, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
