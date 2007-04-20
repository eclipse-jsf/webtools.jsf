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
        assertEquals("myBean.doubleProperty > 5", getELText(_structuredDocument,1467));
        assertEquals("5 gt myBean.bigIntegerProperty", getELText(_structuredDocument,1526));
        assertEquals("myBean.bigDoubleProperty > myBean.bigIntegerProperty", getELText(_structuredDocument,1590));

        assertEquals("5 >= 3", getELText(_structuredDocument,1705));
        assertEquals("5 ge 3", getELText(_structuredDocument,1742));
        assertEquals("'4' >= '34'", getELText(_structuredDocument,1779));
        assertEquals("'4' ge '34'", getELText(_structuredDocument,1821));
        assertEquals("'34' >= '34'", getELText(_structuredDocument,1863));
        assertEquals("'34' ge '34'", getELText(_structuredDocument,1906));
        assertEquals("-5 >= 2", getELText(_structuredDocument,1949));
        assertEquals("-5 ge 2", getELText(_structuredDocument,1987));
        assertEquals("2 >= -5", getELText(_structuredDocument,2025));
        assertEquals("2 ge -5", getELText(_structuredDocument,2063));
        assertEquals("-5 >= -5", getELText(_structuredDocument,2101));
        assertEquals("-5 ge -5", getELText(_structuredDocument,2140));
        assertEquals("myBean.integerProperty > null", getELText(_structuredDocument,2179));
        assertEquals("null gt myBean.integerProperty", getELText(_structuredDocument,2239));

        assertEquals("5 >= true", getELText(_structuredDocument,2323));
        assertEquals("5 ge true", getELText(_structuredDocument,2363));
        assertEquals("myBean.integerProperty >= myBean.booleanProperty", getELText(_structuredDocument,2403));
        assertEquals("myBean.integerProperty ge myBean.booleanProperty", getELText(_structuredDocument,2482));
        assertEquals("myBean.stringArrayProperty >= myBean.booleanProperty", getELText(_structuredDocument,2561));
        assertEquals("myBean.stringArrayProperty ge myBean.booleanProperty", getELText(_structuredDocument,2644));
        assertEquals("myBean.integerProperty >= true ", getELText(_structuredDocument,2730));
        assertEquals("myBean.integerProperty ge true ", getELText(_structuredDocument,2795));
        assertEquals("myBean.booleanProperty >= true", getELText(_structuredDocument,2860));
        assertEquals("myBean.booleanProperty ge true", getELText(_structuredDocument,2924));
        assertEquals("true >= false", getELText(_structuredDocument,2986));
        assertEquals("true ge false", getELText(_structuredDocument,3031));    
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
        assertNoError(1526, Signature.SIG_BOOLEAN);
        assertNoError(1590, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1705, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1742, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1779, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1821, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1863, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1906, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1949, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1987, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2025, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2063, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2101, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2140, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2179, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2239, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(2323, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2363, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2403, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2482, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2561, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2644, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2730, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2795, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2860, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2924, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2986, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3031, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);
    }
}
