package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for less than (<, lt)
 * 
 * @author cbateman
 */
public class LessThanTestCase extends SingleJSPTestCase 
{
    public LessThanTestCase() 
    {
        super("/testdata/jsps/lessThan.jsp.data", "/lessThan.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception 
    {
        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.stringProperty < '3'", getELText(_structuredDocument,852));
        assertEquals("myBean.stringProperty lt '3'", getELText(_structuredDocument,913));
        assertEquals("myBean.integerProperty < 3", getELText(_structuredDocument,975));
        assertEquals("myBean.integerProperty lt 3", getELText(_structuredDocument,1035));
        assertEquals("myBean.integerProperty < '4' ", getELText(_structuredDocument,1096));
        assertEquals("myBean.integerProperty lt '4' ", getELText(_structuredDocument,1159));
        assertEquals("myBean.comparableProperty < myBean.collectionProperty", getELText(_structuredDocument,1224));
        assertEquals("myBean.comparableProperty lt myBean.collectionProperty", getELText(_structuredDocument,1311));
        assertEquals("myBean.integerProperty < -3", getELText(_structuredDocument,1399));
        assertEquals("myBean.doubleProperty < 5", getELText(_structuredDocument,1460));
        assertEquals("5 lt myBean.bigIntegerProperty", getELText(_structuredDocument,1519));
        assertEquals("myBean.bigDoubleProperty < myBean.bigIntegerProperty", getELText(_structuredDocument,1583));
        assertEquals("myBean.coins < 'quarter'", getELText(_structuredDocument,1669));
        assertEquals("myBean.coins lt 'quarter'", getELText(_structuredDocument,1727));
        assertEquals("myBean.rawEnum < 'quarter'", getELText(_structuredDocument,1786));
        assertEquals("myBean.coinEnum lt 'quarter'", getELText(_structuredDocument,1846));
        assertEquals("myBean.rawEnum < myBean.coins", getELText(_structuredDocument,1904));
        assertEquals("myBean.coinEnum < myBean.colors", getELText(_structuredDocument,1963));

        assertEquals("5 < 3", getELText(_structuredDocument,2054));
        assertEquals("5 lt 3", getELText(_structuredDocument,2090));
        assertEquals("'4' < '34'", getELText(_structuredDocument,2127));
        assertEquals("'4' lt '34'", getELText(_structuredDocument,2168));
        assertEquals("'34' < '34'", getELText(_structuredDocument,2210));
        assertEquals("'34' lt '34'", getELText(_structuredDocument,2252));
        assertEquals("-5 < 2", getELText(_structuredDocument,2295));
        assertEquals("-5 lt 2", getELText(_structuredDocument,2332));
        assertEquals("2 < -5", getELText(_structuredDocument,2370));
        assertEquals("2 lt -5", getELText(_structuredDocument,2407));
        assertEquals("-5 < -5", getELText(_structuredDocument,2445));
        assertEquals("-5 lt -5", getELText(_structuredDocument,2483));
        assertEquals("myBean.integerProperty < null", getELText(_structuredDocument,2522));
        assertEquals("null lt myBean.integerProperty", getELText(_structuredDocument,2582));

        assertEquals("5 < true", getELText(_structuredDocument,2666));
        assertEquals("5 lt true", getELText(_structuredDocument,2705));
        assertEquals("myBean.integerProperty < myBean.booleanProperty", getELText(_structuredDocument,2745));
        assertEquals("myBean.integerProperty lt myBean.booleanProperty", getELText(_structuredDocument,2823));
        assertEquals("myBean.stringArrayProperty < myBean.booleanProperty", getELText(_structuredDocument,2902));
        assertEquals("myBean.stringArrayProperty lt myBean.booleanProperty", getELText(_structuredDocument,2984));
        assertEquals("myBean.integerProperty < true ", getELText(_structuredDocument,3070));
        assertEquals("myBean.integerProperty lt true ", getELText(_structuredDocument,3134));
        assertEquals("myBean.booleanProperty < true", getELText(_structuredDocument,3199));
        assertEquals("myBean.booleanProperty lt true", getELText(_structuredDocument,3262));
        assertEquals("true < false", getELText(_structuredDocument,3324));
        assertEquals("true < false", getELText(_structuredDocument,3413));
        assertEquals("myBean.coins < myBean.colors", getELText(_structuredDocument,3456));
        assertEquals("myBean.coins lt myBean.colors", getELText(_structuredDocument,3515));
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
        assertNoError(1669, Signature.SIG_BOOLEAN);
        assertNoError(1727, Signature.SIG_BOOLEAN);
        assertNoError(1786, Signature.SIG_BOOLEAN);
        assertNoError(1846, Signature.SIG_BOOLEAN);
        assertNoError(1904, Signature.SIG_BOOLEAN);
        assertNoError(1963, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(2054, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2090, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2127, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2168, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2210, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2252, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2295, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2332, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2370, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2407, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2445, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2483, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2522, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2582, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(2666, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2705, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2745, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2823, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2902, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2984, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3070, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3134, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3199, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3262, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3324, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3368, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);
        
        list = assertSemanticError(3413, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3456, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);

        list = assertSemanticError(3515, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);
    }
}
