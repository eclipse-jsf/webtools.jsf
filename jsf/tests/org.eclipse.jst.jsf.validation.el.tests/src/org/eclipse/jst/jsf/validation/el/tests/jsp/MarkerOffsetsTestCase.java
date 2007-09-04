package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Used to verify that marker offset/lengths are calculated correctly
 * for each type error message.  This test case should aim for coverage
 * of all diagnostic creation for EL.  Note that we can't currently get coverage
 * on ternaries (parser bug), empty bracket or missing bracket because these things
 * are validated outside the EL validator.
 * 
 * @author cbateman
 *
 */
public class MarkerOffsetsTestCase extends SingleJSPTestCase 
{
    public MarkerOffsetsTestCase() {
        super("/testdata/jsps/markerOffsets.jsp.data", "/markerOffsets.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("5 + 3", getELText(_structuredDocument,905));
        assertEquals("null / null", getELText(_structuredDocument,941));
        assertEquals("myBean.integerProperty == null", getELText(_structuredDocument,983));
        assertEquals("false && myBean.booleanProperty", getELText(_structuredDocument,1043));
        assertEquals("myBean.booleanProperty && false", getELText(_structuredDocument,1104));
        assertEquals("!false", getELText(_structuredDocument,1165));
        assertEquals("empty 5", getELText(_structuredDocument,1202));
        assertEquals("-null", getELText(_structuredDocument,1240));
        assertEquals("-myBean.stringProperty", getELText(_structuredDocument,1276));
        assertEquals("myBean.integerProperty - (5 + 3)", getELText(_structuredDocument,1412));
        assertEquals("myBean.booleanProperty && myBean.integerProperty + 5 == null", getELText(_structuredDocument,1475));
        assertEquals("notABean.stringProperty", getELText(_structuredDocument,1609));
        assertEquals("myBean.notAProperty", getELText(_structuredDocument,1663));
        assertEquals("myBean.integerProperty + 5 + myBean.notAProperty", getELText(_structuredDocument,1713));
        assertEquals("myBean.integerProperty++", getELText(_structuredDocument,1814));
        assertEquals("myBean.", getELText(_structuredDocument,1869));
        assertEquals(" ", getELText(_structuredDocument,1907));
        
        assertEquals("myBean.integerProperty / 0", getELText(_structuredDocument,2028));
        assertEquals("myBean.integerProperty + myBean.booleanProperty", getELText(_structuredDocument,2085));
        assertEquals("myBean.integerProperty && myBean.booleanProperty", getELText(_structuredDocument,2163));
        assertEquals("myBean.booleanProperty >= myBean.collectionProperty", getELText(_structuredDocument,2242));
        assertEquals("5 + 'noNumberConversion'", getELText(_structuredDocument,2324));
        assertEquals("-true", getELText(_structuredDocument,2379));
        assertEquals("!5", getELText(_structuredDocument,2415));
        assertEquals("myBean.doubleProperty + myBean.getIntegerProperty", getELText(_structuredDocument,2477));
    }

    public void testNoErrorExprs() 
    {
        // marker offset tests are only meaningful for validation warnings and errors
        // since we are testing the length and offset values for their corresponding markers
    }
    public void testWarningExprs() 
    {
        List<IMessage> list = assertSemanticWarning(905, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID, 905, 5);

        list = assertSemanticWarning(941, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID, 941, 11);
        
        list = assertSemanticWarning(983, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID, 983, 30);

        list = assertSemanticWarning(1043, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID, 1043, 31);

        list = assertSemanticWarning(1104, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID, 1104, 31);

        list = assertSemanticWarning(1165, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID, 1165, 6);

        list = assertSemanticWarning(1202, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID, 1202, 7);

        list = assertSemanticWarning(1240, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO_ID, 1240, 5);

        list = assertSemanticWarning(1412, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID, 1438, 5);

        list = assertSemanticWarning(1475, null, 2);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID, 1501, 34);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID, 1475, 60);

        list = assertSemanticWarning(1609, null, 1);
        assertContainsProblem(list, DiagnosticFactory.VARIABLE_NOT_FOUND_ID, 1609, 8);

        list = assertSemanticWarning(1663, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID, 1670, 12);

        list = assertSemanticWarning(1713, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID, 1749, 12);

        list = assertSyntaxWarning(1814, 1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID, 1837, 1);

        list = assertSemanticWarning(1869, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID, 1875, 1);

        list = assertSyntaxWarning(1907, 1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID, 1907, 1);
    }
    
    public void testErrorExprs() 
    {
        List<IMessage> list = assertSemanticError(2028, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID, 2028, 26);

        list = assertSemanticError(2085, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID, 2085, 47);

        list = assertSemanticError(2163, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID, 2163, 48);

        list = assertSemanticError(2242, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID, 2242, 51);

        list = assertSemanticError(2324, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID, 2324, 24);

        list = assertSemanticError(2379, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID, 2379, 5);

        list = assertSemanticError(2415, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID, 2415, 2);

        list = assertSemanticError(2477, null, 1);
        assertContainsProblem(list, DiagnosticFactory.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING_ID, 2501, 25);
    }

}