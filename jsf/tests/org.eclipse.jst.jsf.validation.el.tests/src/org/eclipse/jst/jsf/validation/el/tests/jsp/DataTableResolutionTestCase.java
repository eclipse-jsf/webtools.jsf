package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

public class DataTableResolutionTestCase extends SingleJSPTestCase 
{
    public DataTableResolutionTestCase() 
    {
        super("/testdata/jsps/dataTableResolution.jsp.data", "/dataTableResolution.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("foo.x", getELText(_structuredDocument,906));
        assertEquals("row1.name", getELText(_structuredDocument,1069));
        assertEquals("row3.stringProperty", getELText(_structuredDocument,1227));
        assertEquals("row4.anyField", getELText(_structuredDocument,1408));
        assertEquals("row2WrongVar.x", getELText(_structuredDocument,1606));
        assertEquals("row2.wrongMember", getELText(_structuredDocument,1745));    }

    public void testNoErrorExprs()
    {
        assertNoError(906, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1069, TypeConstants.TYPE_STRING);
        assertNoError(1227, TypeConstants.TYPE_STRING);
        assertNoError(1408, TypeConstants.TYPE_JAVAOBJECT);
    }

    public void testWarningExprs() 
    {
        List problems = assertSemanticWarning(1606, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.VARIABLE_NOT_FOUND_ID);
        
        problems = assertSemanticWarning(1745, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }
    
    public void testErrorExprs() {
        // no errors
    }
}
