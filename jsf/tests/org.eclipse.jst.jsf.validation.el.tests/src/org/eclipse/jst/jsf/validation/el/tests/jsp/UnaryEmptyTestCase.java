package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Test cases for unary empty
 * 
 * @author cbateman
 */
public class UnaryEmptyTestCase extends SingleJSPTestCase
{
    public UnaryEmptyTestCase()
    {
        super("/testdata/jsps/emptyOperator.jsp.data", "/emptyOperator.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
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

        assertEquals("empty myBean.collectionProperty", getELText(_structuredDocument,828));
        assertEquals("empty myBean.mapProperty", getELText(_structuredDocument,892));
        assertEquals("empty myBean.stringArrayProperty", getELText(_structuredDocument,949));
        assertEquals("empty myBean.stringProperty", getELText(_structuredDocument,1014));
        assertEquals("myBean.stringProperty", getELText(_structuredDocument,1074));
        assertEquals("empty myBean.listProperty", getELText(_structuredDocument,1128));
        assertEquals("empty mapBean", getELText(_structuredDocument,1183));

        assertEquals("empty 5", getELText(_structuredDocument,1250));
        assertEquals("empty myBean.integerProperty", getELText(_structuredDocument,1287));
        assertEquals("empty false", getELText(_structuredDocument,1345));
        assertEquals("empty myBean.booleanProperty", getELText(_structuredDocument,1386));
        assertEquals("empty ''", getELText(_structuredDocument,1444));
        assertEquals("empty 'notEmpty'", getELText(_structuredDocument,1485));
        assertEquals("empty null", getELText(_structuredDocument,1535));
        assertEquals("empty 456", getELText(_structuredDocument,1578));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_BOOLEAN);
        assertNoError(892, Signature.SIG_BOOLEAN);
        assertNoError(949, Signature.SIG_BOOLEAN);
        assertNoError(1014, Signature.SIG_BOOLEAN);
        //        assertNoError(1074, Signature.SIG_BOOLEAN);
        assertNoError(1128, Signature.SIG_BOOLEAN);
        assertNoError(1183, Signature.SIG_BOOLEAN);

    }

    @Override
    public void testWarningExprs()
    {
        List<IMessage> list = assertSemanticWarning(1250, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1287, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1345, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1386, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1444, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1485, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1535, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1578, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no error cases
    }
}
