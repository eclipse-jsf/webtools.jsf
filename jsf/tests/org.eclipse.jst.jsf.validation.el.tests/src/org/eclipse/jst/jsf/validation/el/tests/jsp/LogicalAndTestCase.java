package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

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

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("myBean.booleanProperty && myBean.booleanProperty", getELText(_structuredDocument,852));
        assertEquals("myBean.booleanProperty and myBean.booleanProperty", getELText(_structuredDocument,934));
        assertEquals("myBean.booleanProperty && true ", getELText(_structuredDocument,1014));
        assertEquals("myBean.booleanProperty and true ", getELText(_structuredDocument,1076));
        assertEquals("'true' && myBean.booleanProperty", getELText(_structuredDocument,1142));
        assertEquals("'true' and myBean.booleanProperty", getELText(_structuredDocument,1209));

        assertEquals("false && myBean.booleanProperty", getELText(_structuredDocument,1301));
        assertEquals("false and myBean.booleanProperty", getELText(_structuredDocument,1366));
        assertEquals("null && myBean.booleanProperty ", getELText(_structuredDocument,1432));
        assertEquals("null and myBean.booleanProperty ", getELText(_structuredDocument,1497));
        assertEquals("'notTrue' && myBean.booleanProperty", getELText(_structuredDocument,1563));
        assertEquals("myBean.booleanProperty && false", getELText(_structuredDocument,1636));
        assertEquals("myBean.booleanProperty and false", getELText(_structuredDocument,1701));
        assertEquals("myBean.booleanProperty && null ", getELText(_structuredDocument,1767));
        assertEquals("myBean.booleanProperty and null", getELText(_structuredDocument,1832));
        assertEquals("myBean.booleanProperty && 'notTrue'", getELText(_structuredDocument,1897));
        assertEquals("true && false", getELText(_structuredDocument,1965));
        assertEquals("null && true", getELText(_structuredDocument,2009));
        
        assertEquals("myBean.integerProperty && true", getELText(_structuredDocument,2084));
        assertEquals("true && myBean.integerProperty", getELText(_structuredDocument,2148));
        assertEquals("4 && true", getELText(_structuredDocument,2212));
    }
    


    public void testNoErrorExprs() 
    {
        assertNoError(852, Signature.SIG_BOOLEAN);
        assertNoError(934, Signature.SIG_BOOLEAN);
        assertNoError(1014, Signature.SIG_BOOLEAN);
        assertNoError(1076, Signature.SIG_BOOLEAN);
        assertNoError(1142, Signature.SIG_BOOLEAN);
        assertNoError(1209, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List<IMessage> list = assertSemanticWarning(1301, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1366, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1432, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1497, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1563, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1636, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1701, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1767, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1832, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1897, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1965, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2009, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);
    }

    public void testErrorExprs() 
    {
        List<IMessage> list = assertSemanticError(2084, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(2148, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(2212, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }
}
