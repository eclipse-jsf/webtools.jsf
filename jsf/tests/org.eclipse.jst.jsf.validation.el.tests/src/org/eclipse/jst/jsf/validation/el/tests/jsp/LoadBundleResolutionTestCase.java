package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.types.TypeConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

public class LoadBundleResolutionTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/loadBundleResolution.jsp.data";
        _destFileName = "/loadBundleResolution.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("bundle.bundleProp2", getELText(_structuredDocument,972));
        assertEquals("bundle.bundleProp1 && myBean.stringProperty", getELText(_structuredDocument,1024));
        assertEquals("empty bundle", getELText(_structuredDocument,1101));
        assertEquals("empty bundle.bundleProp2", getELText(_structuredDocument,1147));
        assertEquals("bundle.bundleProp2 + 5", getELText(_structuredDocument,1205));
        assertEquals("bundleProp2", getELText(_structuredDocument,1258));
        assertEquals("bundle.x.y", getELText(_structuredDocument,1300));

        assertEquals("-bundle.bundleProp1", getELText(_structuredDocument,1368));
        assertEquals("bundle.bundleProp3", getELText(_structuredDocument,1421));
        assertEquals("msg", getELText(_structuredDocument,1473));
        assertEquals("bundle.x", getELText(_structuredDocument,1510));
    }

    public void testNoErrorExprs() 
    {
        assertNoError(972, TypeConstants.TYPE_STRING);
        assertNoError(1024, Signature.SIG_BOOLEAN);
        assertNoError(1101, Signature.SIG_BOOLEAN);
        assertNoError(1147, Signature.SIG_BOOLEAN);
        assertNoError(1205, Signature.SIG_LONG);
        //assertNoError(1258, TypeConstants.TYPE_STRING);
        assertNoError(1300, TypeConstants.TYPE_STRING);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1368, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED);

        list = assertSemanticWarning(1421, null, 1);
        assertContainsProblem(list, 0);

        list = assertSemanticWarning(1473, null, 1);
        assertContainsProblem(list, 0);
        
        list = assertSemanticWarning(1510, null, 1);
        assertContainsProblem(list, 0);
    }
    
    public void testErrorExprs() 
    {
        // no error
    }
}
