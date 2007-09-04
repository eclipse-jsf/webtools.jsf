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

    protected void setUp() throws Exception 
    {
        super.setUp();
    }
    
    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("empty myBean.collectionProperty", getELText(_structuredDocument,852));
        assertEquals("empty myBean.mapProperty", getELText(_structuredDocument,917));
        assertEquals("empty myBean.stringArrayProperty", getELText(_structuredDocument,975));
        assertEquals("empty myBean.stringProperty", getELText(_structuredDocument,1041));
        assertEquals("myBean.stringProperty", getELText(_structuredDocument,1102));
        assertEquals("empty myBean.listProperty", getELText(_structuredDocument,1157));
        assertEquals("empty mapBean", getELText(_structuredDocument,1213));
        
        assertEquals("empty 5", getELText(_structuredDocument,1283));
        assertEquals("empty myBean.integerProperty", getELText(_structuredDocument,1321));
        assertEquals("empty false", getELText(_structuredDocument,1380));
        assertEquals("empty myBean.booleanProperty", getELText(_structuredDocument,1422));
        assertEquals("empty ''", getELText(_structuredDocument,1481));
        assertEquals("empty 'notEmpty'", getELText(_structuredDocument,1523));
        assertEquals("empty null", getELText(_structuredDocument,1574));
        assertEquals("empty 456", getELText(_structuredDocument,1618));
    }

    public void testNoErrorExprs() 
    {
        assertNoError(852, Signature.SIG_BOOLEAN);
        assertNoError(917, Signature.SIG_BOOLEAN);
        assertNoError(975, Signature.SIG_BOOLEAN);
        assertNoError(1041, Signature.SIG_BOOLEAN);
//        assertNoError(1102, Signature.SIG_BOOLEAN);
        assertNoError(1157, Signature.SIG_BOOLEAN);
        assertNoError(1213, Signature.SIG_BOOLEAN);

    }

    public void testWarningExprs() 
    {
        List<IMessage> list = assertSemanticWarning(1283, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1321, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1380, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1422, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);

        list = assertSemanticWarning(1481, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1523, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1574, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID);

        list = assertSemanticWarning(1618, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID);
    }
    
    public void testErrorExprs() 
    {
        // no error cases
    }
}
