package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Test suite for arithmetic remainder (mod, %)
 * @author cbateman
 *
 */
public class ArithmeticModuloTestCase extends SingleJSPTestCase 
{
    public ArithmeticModuloTestCase() 
    {
        super("/testdata/jsps/arithmeticModulo.jsp.data", "/WEB-INF/arithmeticModulo.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception 
    {
        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.integerProperty % 3", getELText(_structuredDocument,852));
        assertEquals("myBean.integerProperty mod 3", getELText(_structuredDocument,912));
        assertEquals("myBean.stringProperty % 3", getELText(_structuredDocument,974));
        assertEquals("myBean.stringProperty mod 3", getELText(_structuredDocument,1033));
        assertEquals("myBean.integerProperty % myBean.integerProperty", getELText(_structuredDocument,1094));
        assertEquals("myBean.integerProperty mod myBean.integerProperty", getELText(_structuredDocument,1175));
        assertEquals("myBean.bigIntegerProperty mod 4", getELText(_structuredDocument,1258));
        assertEquals("myBean.doubleProperty mod 4", getELText(_structuredDocument,1323));
        assertEquals("myBean.doubleProperty mod 5.43", getELText(_structuredDocument,1383));

        assertEquals("5 % 3", getELText(_structuredDocument,1472));
        assertEquals("5 mod 3", getELText(_structuredDocument,1508));
        assertEquals("5.5 % 4 ", getELText(_structuredDocument,1546));
        assertEquals("5.5 mod 4 ", getELText(_structuredDocument,1585));
        assertEquals("'5' % '4'", getELText(_structuredDocument,1629));
        assertEquals("'5' mod '4'", getELText(_structuredDocument,1672));
        assertEquals("null % null", getELText(_structuredDocument,1717));
        assertEquals("null mod null", getELText(_structuredDocument,1762));

        assertEquals("5 % true", getELText(_structuredDocument,1829));
        assertEquals("5 mod true", getELText(_structuredDocument,1868));
        assertEquals("myBean.stringArrayProperty % myBean.booleanProperty", getELText(_structuredDocument,1909));
        assertEquals("myBean.stringArrayProperty mod myBean.booleanProperty", getELText(_structuredDocument,1991));
        assertEquals("myBean.integerProperty % true ", getELText(_structuredDocument,2078));
        assertEquals("myBean.integerProperty mod true ", getELText(_structuredDocument,2142));
        assertEquals("'a' % 'b'", getELText(_structuredDocument,2208));
        assertEquals("'a' mod 'b'", getELText(_structuredDocument,2251));
        assertEquals("5.5 % null", getELText(_structuredDocument,2293));
        assertEquals("5.5 mod null", getELText(_structuredDocument,2334));
        assertEquals("5%0", getELText(_structuredDocument,2377));
        assertEquals("5 mod 0", getELText(_structuredDocument,2411));
        assertEquals("myBean.integerProperty % myBean.booleanProperty", getELText(_structuredDocument,2449));
        assertEquals("myBean.integerProperty mod myBean.booleanProperty", getELText(_structuredDocument,2527));
        assertEquals("myBean.bigIntegerProperty % myBean.booleanProperty", getELText(_structuredDocument,2607));
    }
    
    public void testNoErrorExprs()
    {
        assertNoError(852,Signature.SIG_LONG);
        assertNoError(912, Signature.SIG_LONG);
        assertNoError(974, Signature.SIG_LONG);
        assertNoError(1033, Signature.SIG_LONG);
        assertNoError(1094, Signature.SIG_LONG);
        assertNoError(1175, Signature.SIG_LONG);
        assertNoError(1258, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1323, Signature.SIG_DOUBLE);
        assertNoError(1383, Signature.SIG_DOUBLE);

    }
    
    public void testWarningExprs()
    {
        List<IMessage> list = assertSemanticWarning(1472, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1508, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1546, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1585, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1629, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1672, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1717, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);
        
        list = assertSemanticWarning(1762, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);
        
    }

    public void testErrorExprs()
    {
        List<IMessage> list = assertSemanticError(1829, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(1868, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(1909, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1991, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2078, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2142, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2208, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2251, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2293, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2334, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2377, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2411, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);
        
        list = assertSemanticError(2449, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2527, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2607, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2688, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2752, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
