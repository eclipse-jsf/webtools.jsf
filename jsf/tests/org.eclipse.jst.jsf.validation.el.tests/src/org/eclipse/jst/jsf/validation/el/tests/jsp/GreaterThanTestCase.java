package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Test cases for greater than (gt, >)
 * 
 * @author cbateman
 */
public class GreaterThanTestCase extends SingleJSPTestCase
{
    public GreaterThanTestCase()
    {
        super("/testdata/jsps/greaterThan.jsp.data", "/greaterThan.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        super.testSanity();

        assertEquals("myBean.stringProperty > '3'", getELText(_structuredDocument,828));
        assertEquals("myBean.stringProperty gt '3'", getELText(_structuredDocument,888));
        assertEquals("myBean.integerProperty > 3", getELText(_structuredDocument,949));
        assertEquals("myBean.integerProperty gt 3", getELText(_structuredDocument,1008));
        assertEquals("myBean.integerProperty > '4' ", getELText(_structuredDocument,1068));
        assertEquals("myBean.integerProperty gt '4' ", getELText(_structuredDocument,1130));
        assertEquals("myBean.comparableProperty > myBean.collectionProperty", getELText(_structuredDocument,1194));
        assertEquals("myBean.comparableProperty gt myBean.collectionProperty", getELText(_structuredDocument,1280));
        assertEquals("myBean.integerProperty > -3", getELText(_structuredDocument,1367));
        assertEquals("myBean.doubleProperty > 5", getELText(_structuredDocument,1427));
        assertEquals("5 gt myBean.bigIntegerProperty", getELText(_structuredDocument,1485));
        assertEquals("myBean.bigDoubleProperty > myBean.bigIntegerProperty", getELText(_structuredDocument,1548));
        assertEquals("myBean.coins > 'quarter'", getELText(_structuredDocument,1633));
        assertEquals("myBean.coins gt 'quarter'", getELText(_structuredDocument,1690));
        assertEquals("myBean.rawEnum > 'quarter'", getELText(_structuredDocument,1748));
        assertEquals("myBean.coinEnum gt 'quarter'", getELText(_structuredDocument,1807));
        assertEquals("myBean.rawEnum > myBean.coins", getELText(_structuredDocument,1864));
        assertEquals("myBean.coinEnum > myBean.colors", getELText(_structuredDocument,1922));

        assertEquals("5 > 3", getELText(_structuredDocument,2010));
        assertEquals("5 gt 3", getELText(_structuredDocument,2045));
        assertEquals("'4' > '34'", getELText(_structuredDocument,2081));
        assertEquals("'4' gt '34'", getELText(_structuredDocument,2121));
        assertEquals("'34' > '34'", getELText(_structuredDocument,2162));
        assertEquals("'34' gt '34'", getELText(_structuredDocument,2203));
        assertEquals("-5 > 2", getELText(_structuredDocument,2245));
        assertEquals("-5 gt 2", getELText(_structuredDocument,2281));
        assertEquals("2 > -5", getELText(_structuredDocument,2318));
        assertEquals("2 gt -5", getELText(_structuredDocument,2354));
        assertEquals("-5 > -5", getELText(_structuredDocument,2391));
        assertEquals("-5 gt -5", getELText(_structuredDocument,2428));
        assertEquals("myBean.integerProperty > null", getELText(_structuredDocument,2466));
        assertEquals("null gt myBean.integerProperty", getELText(_structuredDocument,2525));

        assertEquals("5 > true", getELText(_structuredDocument,2606));
        assertEquals("5 gt true", getELText(_structuredDocument,2644));
        assertEquals("myBean.integerProperty > myBean.booleanProperty", getELText(_structuredDocument,2683));
        assertEquals("myBean.integerProperty gt myBean.booleanProperty", getELText(_structuredDocument,2760));
        assertEquals("myBean.stringArrayProperty > myBean.booleanProperty", getELText(_structuredDocument,2838));
        assertEquals("myBean.stringArrayProperty gt myBean.booleanProperty", getELText(_structuredDocument,2919));
        assertEquals("myBean.integerProperty > true ", getELText(_structuredDocument,3004));
        assertEquals("myBean.integerProperty gt true ", getELText(_structuredDocument,3067));
        assertEquals("myBean.booleanProperty > true", getELText(_structuredDocument,3131));
        assertEquals("myBean.booleanProperty gt true", getELText(_structuredDocument,3193));
        assertEquals("true > false", getELText(_structuredDocument,3254));
        assertEquals("true gt false", getELText(_structuredDocument,3297));
        assertEquals("true > false", getELText(_structuredDocument,3341));
        assertEquals("myBean.coins > myBean.colors", getELText(_structuredDocument,3383));
        assertEquals("myBean.coins gt myBean.colors", getELText(_structuredDocument,3441));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_BOOLEAN);
        assertNoError(888, Signature.SIG_BOOLEAN);
        assertNoError(949, Signature.SIG_BOOLEAN);
        assertNoError(1008, Signature.SIG_BOOLEAN);
        assertNoError(1068, Signature.SIG_BOOLEAN);
        assertNoError(1130, Signature.SIG_BOOLEAN);
        assertNoError(1194, Signature.SIG_BOOLEAN);
        assertNoError(1280, Signature.SIG_BOOLEAN);
        assertNoError(1367, Signature.SIG_BOOLEAN);
        assertNoError(1427, Signature.SIG_BOOLEAN);
        assertNoError(1485, Signature.SIG_BOOLEAN);
        assertNoError(1548, Signature.SIG_BOOLEAN);
        assertNoError(1633, Signature.SIG_BOOLEAN);
        assertNoError(1690, Signature.SIG_BOOLEAN);
        assertNoError(1748, Signature.SIG_BOOLEAN);
        assertNoError(1807, Signature.SIG_BOOLEAN);
        assertNoError(1864, Signature.SIG_BOOLEAN);
        assertNoError(1922, Signature.SIG_BOOLEAN);
    }

    @Override
    public void testWarningExprs()
    {
        List<IMessage> list = assertSemanticWarning(2010, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2045, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2081, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2121, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2162, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2203, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2245, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2281, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2318, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2354, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2391, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2428, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2466, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2525, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<IMessage> list = assertSemanticError(2606, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2644, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2683, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2760, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2838, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2919, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3004, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3067, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3131, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3193, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3254, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3297, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3341, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3383, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);

        list = assertSemanticError(3441, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);
    }
}
