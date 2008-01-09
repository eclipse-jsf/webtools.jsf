package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Test cases for logical not (not, !)
 * 
 * @author cbateman
 */
public class LogicalNotTestCase extends SingleJSPTestCase {

    public LogicalNotTestCase()
    {
        super("/testdata/jsps/logicalNOT.jsp.data", "/logicalNOT.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("!myBean.booleanProperty", getELText(_structuredDocument,829));
        assertEquals("not myBean.booleanProperty", getELText(_structuredDocument,885));
        assertEquals("!myBean.stringProperty", getELText(_structuredDocument,941));
        assertEquals("not myBean.stringProperty", getELText(_structuredDocument,993));

        assertEquals("!true", getELText(_structuredDocument,1075));
        assertEquals("not true", getELText(_structuredDocument,1113));
        assertEquals("!false", getELText(_structuredDocument,1154));
        assertEquals("not false", getELText(_structuredDocument,1193));
        assertEquals("! 'true'", getELText(_structuredDocument,1235));
        assertEquals("not 'true'", getELText(_structuredDocument,1276));
        assertEquals("! 'notTrue'", getELText(_structuredDocument,1319));
        assertEquals("not 'notTrue'", getELText(_structuredDocument,1363));

        assertEquals("!5", getELText(_structuredDocument,1430));
        assertEquals("not 5", getELText(_structuredDocument,1462));
        assertEquals("!myBean.integerProperty", getELText(_structuredDocument,1497));
        assertEquals("not myBean.integerProperty", getELText(_structuredDocument,1550));
        assertEquals("!myBean.collectionProperty", getELText(_structuredDocument,1606));
        assertEquals("not myBean.collectionProperty", getELText(_structuredDocument,1662));
        assertEquals("!myBean.stringArrayProperty", getELText(_structuredDocument,1721));
        assertEquals("not myBean.stringArrayProperty", getELText(_structuredDocument,1778));
        assertEquals("!myBean.mapProperty", getELText(_structuredDocument,1838));
        assertEquals("not myBean.mapProperty", getELText(_structuredDocument,1887));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(829, Signature.SIG_BOOLEAN);
        assertNoError(885, Signature.SIG_BOOLEAN);
        assertNoError(941, Signature.SIG_BOOLEAN);
        assertNoError(993, Signature.SIG_BOOLEAN);
    }

    @Override
    public void testWarningExprs()
    {
        List<IMessage> list = assertSemanticWarning(1075, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1113, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1154, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1193, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1235, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1276, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1319, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1363, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<IMessage> list = assertSemanticError(1430, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1462, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1497, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1550, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1606, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1662, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1721, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1778, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1838, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(1887, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }

}
