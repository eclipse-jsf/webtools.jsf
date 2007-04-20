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

        assertEquals("5 == 3", getELText(_structuredDocument,1717));
        assertEquals("5 eq 3", getELText(_structuredDocument,1754));
        assertEquals("true == false", getELText(_structuredDocument,1791));
        assertEquals("true eq false", getELText(_structuredDocument,1835));
        assertEquals("'4' == '34'", getELText(_structuredDocument,1879));
        assertEquals("'4' eq '34'", getELText(_structuredDocument,1921));
        assertEquals("'34' == '34'", getELText(_structuredDocument,1963));
        assertEquals("'34' eq '34'", getELText(_structuredDocument,2006));
        assertEquals("myBean.integerProperty == null", getELText(_structuredDocument,2049));
        assertEquals("null eq myBean.integerProperty", getELText(_structuredDocument,2110));
        assertEquals("5.4 == 4.3", getELText(_structuredDocument,2171));
        assertEquals("true == true", getELText(_structuredDocument,2212));

        assertEquals("5 == true", getELText(_structuredDocument,2276));
        assertEquals("5 eq true", getELText(_structuredDocument,2316));
        assertEquals("myBean.integerProperty == myBean.booleanProperty", getELText(_structuredDocument,2356));
        assertEquals("myBean.integerProperty eq myBean.booleanProperty", getELText(_structuredDocument,2435));
        assertEquals("myBean.stringArrayProperty == myBean.booleanProperty", getELText(_structuredDocument,2514));
        assertEquals("myBean.booleanProperty eq myBean.stringArrayProperty", getELText(_structuredDocument,2597));
        assertEquals("myBean.integerProperty == true ", getELText(_structuredDocument,2683));
        assertEquals("myBean.integerProperty eq true ", getELText(_structuredDocument,2748));
        assertEquals("false == myBean.integerProperty", getELText(_structuredDocument,2813));  
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
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1717, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1754, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1791, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1835, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1879, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1921, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1963, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2006, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2049, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2110, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2171, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2212, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(2276, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2316, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2356, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2435, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2514, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(2597, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(2683, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2748, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(2813, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
