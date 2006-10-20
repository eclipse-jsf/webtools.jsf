package org.eclipse.jst.jsf.validation.el.tests.jsp;


import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.types.TypeConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test suite for testing validation of arithmetic add
 * 
 * @author cbateman
 *
 */
public class ArithmeticAddTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception 
    {
        _srcFileName = "/testdata/jsps/arithmeticAdd.jsp.data";
        _destFileName = "/arithmeticAdd.jsp";

        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.integerProperty  + 3", getELText(_structuredDocument,900));
        assertEquals("myBean.stringProperty + 3", getELText(_structuredDocument,961));
        assertEquals("myBean.integerProperty + myBean.integerProperty", getELText(_structuredDocument,1020));
        assertEquals("myBean.bigIntegerProperty + 4", getELText(_structuredDocument,1101));
        assertEquals("myBean.bigIntegerProperty + 5.5", getELText(_structuredDocument,1164));
        assertEquals("myBean.bigDoubleProperty + 5.5", getELText(_structuredDocument,1229));
        assertEquals("myBean.doubleProperty + 5", getELText(_structuredDocument,1293));

        assertEquals("5 + 3", getELText(_structuredDocument,1381));
        assertEquals("5.5 + 4", getELText(_structuredDocument,1417));
        assertEquals("5.5 + null", getELText(_structuredDocument,1455));
        assertEquals("'5' + '4'", getELText(_structuredDocument,1499));
        assertEquals("null + null", getELText(_structuredDocument,1542));
        assertEquals("5.5 + 3.5", getELText(_structuredDocument,1588));

        assertEquals("5 + true", getELText(_structuredDocument,1651));
        assertEquals("myBean.integerProperty + myBean.booleanProperty", getELText(_structuredDocument,1690));
        assertEquals("myBean.stringArrayProperty + myBean.booleanProperty", getELText(_structuredDocument,1768));
        assertEquals("myBean.integerProperty + true ", getELText(_structuredDocument,1853));
        assertEquals("'a' + 'b'", getELText(_structuredDocument,1917));
        assertEquals("myBean.bigIntegerProperty + true", getELText(_structuredDocument,1960));
    }
    
    public void testNoErrorExprs()
    {
        assertNoError(900, Signature.SIG_LONG);
        assertNoError(961, Signature.SIG_LONG);
        assertNoError(1020, Signature.SIG_LONG);
        assertNoError(1101, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1164, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1229, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1293, Signature.SIG_DOUBLE);
    }

    public void testWarningExprs()
    {
        List list = assertSemanticWarning(1381, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1417, Signature.SIG_DOUBLE,  1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1455, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1499, Signature.SIG_LONG,  1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1542, Signature.SIG_LONG,  1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);
        
        list = assertSemanticWarning(1588, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }
    
    public void testErrorExprs()
    {
        List list = assertSemanticError(1651, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(1690, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1768, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
       
        list = assertSemanticError(1853, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(1917, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID);

        list = assertSemanticError(1960, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}