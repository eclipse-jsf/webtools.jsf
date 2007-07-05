package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for logical equals (eq, ==)
 * 
 * @author cbateman
 */
public class LogicalEqualsTestCase extends SingleJSPTestCase 
{
    public LogicalEqualsTestCase() 
    {
        super("/testdata/jsps/logicalEquals.jsp.data", "/logicalEquals.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("myBean.stringProperty == '3'", getELText(_structuredDocument,852));
        assertEquals("myBean.stringProperty eq '3'", getELText(_structuredDocument,914));
        assertEquals("myBean.integerProperty == 3", getELText(_structuredDocument,976));
        assertEquals("myBean.integerProperty eq 3", getELText(_structuredDocument,1037));
        assertEquals("myBean.booleanProperty == true", getELText(_structuredDocument,1098));
        assertEquals("myBean.booleanProperty eq true", getELText(_structuredDocument,1162));
        assertEquals("myBean.integerProperty == '4' ", getELText(_structuredDocument,1226));
        assertEquals("myBean.integerProperty eq '4' ", getELText(_structuredDocument,1290));
        assertEquals("myBean.bigDoubleProperty == 4.5", getELText(_structuredDocument,1354));
        assertEquals("myBean.doubleProperty eq 67", getELText(_structuredDocument,1419));
        assertEquals("myBean.bigIntegerProperty == 500", getELText(_structuredDocument,1480));
        assertEquals("myBean.stringArrayProperty == '3'", getELText(_structuredDocument,1546));
        assertEquals("myBean.stringArrayProperty eq myBean.listProperty", getELText(_structuredDocument,1613));
        assertEquals("myBean.coins == 'dime'", getELText(_structuredDocument,1692));
        assertEquals("myBean.coins eq 'dime'", getELText(_structuredDocument,1745));
        assertEquals("myBean.colors == 'red'", getELText(_structuredDocument,1798));
        assertEquals("myBean.colors eq 'red'", getELText(_structuredDocument,1850));
        assertEquals("myBean.coins == myBean.stringProperty", getELText(_structuredDocument,1902));
        assertEquals("myBean.coins eq myBean.stringProperty", getELText(_structuredDocument,1969));
        assertEquals("myBean.rawEnum == 'red'", getELText(_structuredDocument,2036));
        assertEquals("myBean.coinEnum == myBean.coins", getELText(_structuredDocument,2089));

        assertEquals("5 == 3", getELText(_structuredDocument,2175));
        assertEquals("5 eq 3", getELText(_structuredDocument,2212));
        assertEquals("true == false", getELText(_structuredDocument,2249));
        assertEquals("true eq false", getELText(_structuredDocument,2293));
        assertEquals("'4' == '34'", getELText(_structuredDocument,2337));
        assertEquals("'4' eq '34'", getELText(_structuredDocument,2379));
        assertEquals("'34' == '34'", getELText(_structuredDocument,2421));
        assertEquals("'34' eq '34'", getELText(_structuredDocument,2464));
        assertEquals("myBean.integerProperty == null", getELText(_structuredDocument,2507));
        assertEquals("null eq myBean.integerProperty", getELText(_structuredDocument,2568));
        assertEquals("5.4 == 4.3", getELText(_structuredDocument,2629));
        assertEquals("true == true", getELText(_structuredDocument,2670));
        assertEquals("myBean.coins == 'notAValue'", getELText(_structuredDocument,2713));
        assertEquals("myBean.coins eq 'notAValue'", getELText(_structuredDocument,2771));
        assertEquals("myBean.coins == 'notAValue' && myBean.coins == 'dime'", getELText(_structuredDocument,2829));
        assertEquals("myBean.coins eq 'notAValue' && myBean.coins eq 'dime'", getELText(_structuredDocument,2913));
        assertEquals("myBean.coins == myBean.colors", getELText(_structuredDocument,2997));
        assertEquals("myBean.coins == myBean.stringArrayProperty", getELText(_structuredDocument,3057));
        assertEquals("'blah' == myBean.coins", getELText(_structuredDocument,3130));
        assertEquals("myBean.coins eq 'blah'", getELText(_structuredDocument,3183));

        assertEquals("5 == true", getELText(_structuredDocument,3257));
        assertEquals("5 eq true", getELText(_structuredDocument,3297));
        assertEquals("myBean.integerProperty == myBean.booleanProperty", getELText(_structuredDocument,3337));
        assertEquals("myBean.integerProperty eq myBean.booleanProperty", getELText(_structuredDocument,3416));
        assertEquals("myBean.stringArrayProperty == myBean.booleanProperty", getELText(_structuredDocument,3495));
        assertEquals("myBean.booleanProperty eq myBean.stringArrayProperty", getELText(_structuredDocument,3578));
        assertEquals("myBean.integerProperty == true ", getELText(_structuredDocument,3664));
        assertEquals("myBean.integerProperty eq true ", getELText(_structuredDocument,3729));
        assertEquals("false == myBean.integerProperty", getELText(_structuredDocument,3794));    
    }

    public void testNoErrorExprs() 
    {
        assertNoError(852, Signature.SIG_BOOLEAN);
        assertNoError(914, Signature.SIG_BOOLEAN);
        assertNoError(976, Signature.SIG_BOOLEAN);
        assertNoError(1037, Signature.SIG_BOOLEAN);
        assertNoError(1098, Signature.SIG_BOOLEAN);
        assertNoError(1162, Signature.SIG_BOOLEAN);
        assertNoError(1226, Signature.SIG_BOOLEAN);
        assertNoError(1290, Signature.SIG_BOOLEAN);
        assertNoError(1354, Signature.SIG_BOOLEAN);
        assertNoError(1419, Signature.SIG_BOOLEAN);
        assertNoError(1480, Signature.SIG_BOOLEAN);
        assertNoError(1546, Signature.SIG_BOOLEAN);
        assertNoError(1613, Signature.SIG_BOOLEAN);
        assertNoError(1692, Signature.SIG_BOOLEAN);
        assertNoError(1745, Signature.SIG_BOOLEAN);
        assertNoError(1798, Signature.SIG_BOOLEAN);
        assertNoError(1850, Signature.SIG_BOOLEAN);
        assertNoError(1902, Signature.SIG_BOOLEAN);
        assertNoError(1969, Signature.SIG_BOOLEAN);
        assertNoError(2036, Signature.SIG_BOOLEAN);
        assertNoError(2089, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(2175, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(2212, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2249, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2293, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2337, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2379, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2421, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2464, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2507, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2568, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2629, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2670, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2713, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);

        list = assertSemanticWarning(2771, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);

        list = assertSemanticWarning(2829, Signature.SIG_BOOLEAN, 2);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(2913, Signature.SIG_BOOLEAN, 2);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(2997, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);

        list = assertSemanticWarning(3057, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);
        
        list = assertSemanticWarning(3130, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);

        list = assertSemanticWarning(3183, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);
    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(3257, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3297, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3337, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3416, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3495, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(3578, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(3664, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3729, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(3794, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
