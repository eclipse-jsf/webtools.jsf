package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Test cases for bean variable resolution
 * 
 * @author cbateman
 *
 */
public class BeanVariableResolutionTestCase extends SingleJSPTestCase 
{
    public BeanVariableResolutionTestCase() {
        super("/testdata/jsps/beanVariableResolution.jsp.data", "/beanVariableResolution.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("myBean", getELText(_structuredDocument,541));
        assertEquals("myBeanSubClass", getELText(_structuredDocument,581));
        assertEquals("mapBean", getELText(_structuredDocument,629));
        assertEquals("mapBean1", getELText(_structuredDocument,670));
        assertEquals("hiddenBean", getELText(_structuredDocument,712));
        assertEquals("myBean_none", getELText(_structuredDocument,756));
        assertEquals("myBeanSettable", getELText(_structuredDocument,801));

        assertEquals("myBean1", getELText(_structuredDocument,878));
        assertEquals("someOtherBeanName", getELText(_structuredDocument,919));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(541, "Lbeans.MyBean;");
        assertNoError(581, "Lbeans.MyBeanSubClass;");
        assertNoError(629, "Lbeans.MapBean;");
        assertNoError(670, "Lbeans.MapBean;");
        assertNoError(712, "Lbeans.MyBean;");
        assertNoError(756, "Lbeans.MyBean;");
        assertNoError(801, "Lbeans.MyBeanSettable;");
    }

    public void testWarningExprs() 
    {
        List<IMessage> list = assertSemanticWarning(878, null, 1);
        assertContainsProblem(list,DiagnosticFactory.VARIABLE_NOT_FOUND_ID);
        
        list = assertSemanticWarning(919, null, 1);
        assertContainsProblem(list,DiagnosticFactory.VARIABLE_NOT_FOUND_ID);
    }

    public void testErrorExprs() 
    {
        // no error cases
    }

}
