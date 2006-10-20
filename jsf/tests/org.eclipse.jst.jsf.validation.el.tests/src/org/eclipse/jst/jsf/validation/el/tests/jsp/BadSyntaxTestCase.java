package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

public class BadSyntaxTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/badSyntax.jsp.data";
        _destFileName = "/badSyntax.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals(" ", getELText(_structuredDocument,870));
        assertEquals("myBean.integerProperty + ", getELText(_structuredDocument,902));
        assertEquals("&& myBean.booleanProperty", getELText(_structuredDocument,958));
        assertEquals("&!", getELText(_structuredDocument,1014));
        assertEquals("f?x", getELText(_structuredDocument,1047));
    }


    public void testNoErrorExprs() {
        // no non-error cases
    }


    public void testWarningExprs() 
    {
        List list = assertSyntaxWarning(870,1);
        assertContainsProblem(list, 0);
        
        list = assertSyntaxWarning(902,1);
        assertContainsProblem(list, 0);
        
        list = assertSyntaxWarning(958,1);
        assertContainsProblem(list, 0);

        list = assertSyntaxWarning(1014,1);
        assertContainsProblem(list, 0);

        list = assertSyntaxWarning(1047,1);
        assertContainsProblem(list, 0);
    }

    public void testErrorExprs() 
    {
        // no error cases
    }

}
