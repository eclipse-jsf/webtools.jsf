package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for syntax error testing
 * 
 * @author cbateman
 *
 */
public class BadSyntaxTestCase extends SingleJSPTestCase
{
    public BadSyntaxTestCase() {
        super("/testdata/jsps/badSyntax.jsp.data", "/badSyntax.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals(" ", getELText(_structuredDocument,870));
        assertEquals("myBean.integerProperty + ", getELText(_structuredDocument,902));
        assertEquals("&& myBean.booleanProperty", getELText(_structuredDocument,958));
        assertEquals("&!", getELText(_structuredDocument,1014));
        assertEquals("f?x", getELText(_structuredDocument,1047));
    }


    @Override
    public void testNoErrorExprs() {
        // no non-error cases
    }


    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSyntaxWarning(870,1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);

        list = assertSyntaxWarning(902,1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);

        list = assertSyntaxWarning(958,1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);

        list = assertSyntaxWarning(1014,1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);

        list = assertSyntaxWarning(1047,1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no error cases
    }

}
