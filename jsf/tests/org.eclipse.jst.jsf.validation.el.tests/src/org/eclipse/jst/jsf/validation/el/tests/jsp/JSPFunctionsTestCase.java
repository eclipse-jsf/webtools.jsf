package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for functions
 * 
 * @author cbateman
 */
public class JSPFunctionsTestCase extends SingleJSPTestCase 
{
    public JSPFunctionsTestCase() {
        super("/testdata/jsps/jspFunctions.jsp.data", "/jspFunctions.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("f:someFunc(6) > 8", getELText(_structuredDocument,967));
        assertEquals("f:someFunc(6)", getELText(_structuredDocument,1018));
        assertEquals("f:someFunc(true)", getELText(_structuredDocument,1065));
        assertEquals("someFunc(6) > 8", getELText(_structuredDocument,1260));
        assertEquals("someFunc(6)", getELText(_structuredDocument,1309));
        assertEquals("someFunc(true)", getELText(_structuredDocument,1354));
    }

    public void testNoErrorExprs() 
    {
        assertNoError(967, null);
        assertNoError(1018, null);
        assertNoError(1065, null);
    }

    public void testWarningExprs() 
    {
        // note: this will change to non-error when functions are properly supported
        // by the parser
        List list = assertSyntaxWarning(1260, 1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);

        list = assertSyntaxWarning(1309, 1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);
        
        list = assertSyntaxWarning(1354, 1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);
    }
    
    public void testErrorExprs() 
    {
        // no error cases
    }

}
