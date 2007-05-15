package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for greater than or equal (ge, >=)
 * 
 * @author cbateman
 *
 */
public class GreaterThanEqTestCase extends SingleJSPTestCase {

    public GreaterThanEqTestCase()
    {
        super("/testdata/jsps/greaterThanEq.jsp.data", "/greaterThanEq.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception 
    {
        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.stringProperty >= '3'", getELText(_structuredDocument,852));
        assertEquals("myBean.stringProperty ge '3'", getELText(_structuredDocument,914));
        assertEquals("myBean.integerProperty >= 3", getELText(_structuredDocument,976));
        assertEquals("myBean.integerProperty ge 3", getELText(_structuredDocument,1037));
        assertEquals("myBean.integerProperty >= '4' ", getELText(_structuredDocument,1098));
        assertEquals("myBean.integerProperty ge '4' ", getELText(_structuredDocument,1162));
        assertEquals("myBean.comparableProperty >= myBean.collectionProperty", getELText(_structuredDocument,1227));
        assertEquals("myBean.comparableProperty ge myBean.collectionProperty", getELText(_structuredDocument,1315));
        assertEquals("myBean.integerProperty >= -3", getELText(_structuredDocument,1405));
        assertEquals("myBean.doubleProperty >= 5", getELText(_structuredDocument,1467));
        assertEquals("5 ge myBean.bigIntegerProperty", getELText(_structuredDocument,1527));
        assertEquals("myBean.bigDoubleProperty >= myBean.bigIntegerProperty", getELText(_structuredDocument,1591));
        assertEquals("myBean.coins >= 'quarter'", getELText(_structuredDocument,1678));
        assertEquals("myBean.coins ge 'quarter'", getELText(_structuredDocument,1737));
        assertEquals("myBean.rawEnum >= 'quarter'", getELText(_structuredDocument,1796));
        assertEquals("myBean.coinEnum ge 'quarter'", getELText(_structuredDocument,1857));
        assertEquals("myBean.rawEnum >= myBean.coins", getELText(_structuredDocument,1915));
        assertEquals("myBean.coinEnum >= myBean.colors", getELText(_structuredDocument,1975));

        assertEquals("5 >= 3", getELText(_structuredDocument,2070));
        assertEquals("5 ge 3", getELText(_structuredDocument,2107));
        assertEquals("'4' >= '34'", getELText(_structuredDocument,2144));
        assertEquals("'4' ge '34'", getELText(_structuredDocument,2186));
        assertEquals("'34' >= '34'", getELText(_structuredDocument,2228));
        assertEquals("'34' ge '34'", getELText(_structuredDocument,2271));
        assertEquals("-5 >= 2", getELText(_structuredDocument,2314));
        assertEquals("-5 ge 2", getELText(_structuredDocument,2352));
        assertEquals("2 >= -5", getELText(_structuredDocument,2390));
        assertEquals("2 ge -5", getELText(_structuredDocument,2428));
        assertEquals("-5 >= -5", getELText(_structuredDocument,2466));
        assertEquals("-5 ge -5", getELText(_structuredDocument,2505));
        assertEquals("myBean.integerProperty >= null", getELText(_structuredDocument,2544));
        assertEquals("null ge myBean.integerProperty", getELText(_structuredDocument,2605));

        assertEquals("5 >= true", getELText(_structuredDocument,2689));
        assertEquals("5 ge true", getELText(_structuredDocument,2729));
        assertEquals("myBean.integerProperty >= myBean.booleanProperty", getELText(_structuredDocument,2769));
        assertEquals("myBean.integerProperty ge myBean.booleanProperty", getELText(_structuredDocument,2848));
        assertEquals("myBean.stringArrayProperty >= myBean.booleanProperty", getELText(_structuredDocument,2927));
        assertEquals("myBean.stringArrayProperty ge myBean.booleanProperty", getELText(_structuredDocument,3010));
        assertEquals("myBean.integerProperty >= true ", getELText(_structuredDocument,3096));
        assertEquals("myBean.integerProperty ge true ", getELText(_structuredDocument,3161));
        assertEquals("myBean.booleanProperty >= true", getELText(_structuredDocument,3226));
        assertEquals("myBean.booleanProperty ge true", getELText(_structuredDocument,3290));
        assertEquals("true >= false", getELText(_structuredDocument,3352));
        assertEquals("true ge false", getELText(_structuredDocument,3397));
        assertEquals("myBean.coins >= myBean.colors", getELText(_structuredDocument,3441));
        assertEquals("myBean.coins ge myBean.colors", getELText(_structuredDocument,3501));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(852, Signature.SIG_BOOLEAN);
        assertNoError(914, Signature.SIG_BOOLEAN);
        assertNoError(976, Signature.SIG_BOOLEAN);
        assertNoError(1037, Signature.SIG_BOOLEAN);
        assertNoError(1098, Signature.SIG_BOOLEAN);
        assertNoError(1162, Signature.SIG_BOOLEAN);
        assertNoError(1227, Signature.SIG_BOOLEAN);
        assertNoError(1315, Signature.SIG_BOOLEAN);
        assertNoError(1405, Signature.SIG_BOOLEAN);
        assertNoError(1467, Signature.SIG_BOOLEAN);
        assertNoError(1527, Signature.SIG_BOOLEAN);
        assertNoError(1591, Signature.SIG_BOOLEAN);
        assertNoError(1678, Signature.SIG_BOOLEAN);
        assertNoError(1737, Signature.SIG_BOOLEAN);
        assertNoError(1796, Signature.SIG_BOOLEAN);
        assertNoError(1857, Signature.SIG_BOOLEAN);
        assertNoError(1915, Signature.SIG_BOOLEAN);
        assertNoError(1975, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(2070, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2107, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2144, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2186, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2228, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2271, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2314, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2352, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2390, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2428, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2466, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2505, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2544, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2605, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(2689, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2729, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2769, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2848, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2927, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3010, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3096, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3161, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3226, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3290, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3352, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3397, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3441, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);

        list = assertSemanticError(3501, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);
    }
}
