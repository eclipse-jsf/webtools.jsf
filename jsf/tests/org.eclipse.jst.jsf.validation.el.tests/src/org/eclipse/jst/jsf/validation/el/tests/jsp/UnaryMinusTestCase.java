package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for unary minus
 * 
 * @author cbateman
 */
public class UnaryMinusTestCase extends SingleJSPTestCase 
{
    public UnaryMinusTestCase()
    {
        super("/testdata/jsps/unaryMinus.jsp.data", "/unaryMinus.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("-myBean.integerProperty", getELText(_structuredDocument,852));
        assertEquals("-myBean.doubleProperty", getELText(_structuredDocument,906));
        assertEquals("-myBean.bigDoubleProperty", getELText(_structuredDocument,959));
        assertEquals("-myBean.bigIntegerProperty", getELText(_structuredDocument,1015));

        assertEquals("-myBean.stringProperty", getELText(_structuredDocument,1102));
        assertEquals("-5", getELText(_structuredDocument,1158));
        assertEquals("-5.5", getELText(_structuredDocument,1194));
        assertEquals("-'5'", getELText(_structuredDocument,1232));
        assertEquals("-'5.5'", getELText(_structuredDocument,1270));
        assertEquals("-null", getELText(_structuredDocument,1310));

        assertEquals("-false", getELText(_structuredDocument,1370));
        assertEquals("-true", getELText(_structuredDocument,1407));
        assertEquals("-myBean.booleanProperty", getELText(_structuredDocument,1443));
        assertEquals("-myBean.collectionProperty", getELText(_structuredDocument,1497));
        assertEquals("-myBean.mapProperty", getELText(_structuredDocument,1554));
        assertEquals("-myBean.stringArrayProperty", getELText(_structuredDocument,1604));
        assertEquals("-'notANumber'", getELText(_structuredDocument,1662));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(852, Signature.SIG_INT);
        assertNoError(906, Signature.SIG_DOUBLE);
        assertNoError(959, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1015, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1158, Signature.SIG_LONG);
        assertNoError(1194, Signature.SIG_DOUBLE);
        assertNoError(1232, Signature.SIG_LONG);
        assertNoError(1270, Signature.SIG_DOUBLE);

    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1102, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        list = assertSemanticWarning(1310, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO_ID);
    }
    
    public void testErrorExprs() 
    {
        List list = assertSemanticError(1370, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1407, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1443, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1497, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1554, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1604, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1662, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }

}
