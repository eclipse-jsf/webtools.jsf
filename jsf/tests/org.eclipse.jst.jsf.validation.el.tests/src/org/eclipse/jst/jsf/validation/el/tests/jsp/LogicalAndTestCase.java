package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for logical AND (and, &&)
 * 
 * @author cbateman
 */
public class LogicalAndTestCase extends SingleJSPTestCase
{

    public LogicalAndTestCase() {
        super("/testdata/jsps/logicalAND.jsp.data", "/logicalAND.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean.booleanProperty && myBean.booleanProperty", getELText(_structuredDocument,828));
        assertEquals("myBean.booleanProperty and myBean.booleanProperty", getELText(_structuredDocument,909));
        assertEquals("myBean.booleanProperty && true ", getELText(_structuredDocument,988));
        assertEquals("myBean.booleanProperty and true ", getELText(_structuredDocument,1049));
        assertEquals("'true' && myBean.booleanProperty", getELText(_structuredDocument,1114));
        assertEquals("'true' and myBean.booleanProperty", getELText(_structuredDocument,1180));

        assertEquals("false && myBean.booleanProperty", getELText(_structuredDocument,1269));
        assertEquals("false and myBean.booleanProperty", getELText(_structuredDocument,1333));
        assertEquals("null && myBean.booleanProperty ", getELText(_structuredDocument,1398));
        assertEquals("null and myBean.booleanProperty ", getELText(_structuredDocument,1462));
        assertEquals("'notTrue' && myBean.booleanProperty", getELText(_structuredDocument,1527));
        assertEquals("myBean.booleanProperty && false", getELText(_structuredDocument,1598));
        assertEquals("myBean.booleanProperty and false", getELText(_structuredDocument,1662));
        assertEquals("myBean.booleanProperty && null ", getELText(_structuredDocument,1727));
        assertEquals("myBean.booleanProperty and null", getELText(_structuredDocument,1791));
        assertEquals("myBean.booleanProperty && 'notTrue'", getELText(_structuredDocument,1855));
        assertEquals("true && false", getELText(_structuredDocument,1922));
        assertEquals("null && true", getELText(_structuredDocument,1965));

        assertEquals("myBean.integerProperty && true", getELText(_structuredDocument,2037));
        assertEquals("true && myBean.integerProperty", getELText(_structuredDocument,2100));
        assertEquals("4 && true", getELText(_structuredDocument,2163));
    }



    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_BOOLEAN);
        assertNoError(909, Signature.SIG_BOOLEAN);
        assertNoError(988, Signature.SIG_BOOLEAN);
        assertNoError(1049, Signature.SIG_BOOLEAN);
        assertNoError(1114, Signature.SIG_BOOLEAN);
        assertNoError(1180, Signature.SIG_BOOLEAN);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1269, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1333, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1398, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1462, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1527, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1598, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1662, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1727, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1791, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1855, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1922, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1965, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(2037, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(2100, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(2163, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }
}
