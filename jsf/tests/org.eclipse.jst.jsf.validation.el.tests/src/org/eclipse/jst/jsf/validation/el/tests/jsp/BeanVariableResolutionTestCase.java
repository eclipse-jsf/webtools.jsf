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

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean", getELText(_structuredDocument,523));
        assertEquals("myBeanSubClass", getELText(_structuredDocument,562));
        assertEquals("mapBean", getELText(_structuredDocument,609));
        assertEquals("mapBean1", getELText(_structuredDocument,649));
        assertEquals("hiddenBean", getELText(_structuredDocument,690));
        assertEquals("myBean_none", getELText(_structuredDocument,733));
        assertEquals("myBeanSettable", getELText(_structuredDocument,777));

        assertEquals("myBean1", getELText(_structuredDocument,851));
        assertEquals("someOtherBeanName", getELText(_structuredDocument,891));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(523, "Lbeans.MyBean;");
        assertNoError(562, "Lbeans.MyBeanSubClass;");
        assertNoError(609, "Lbeans.MapBean;");
        assertNoError(649, "Lbeans.MapBean;");
        assertNoError(690, "Lbeans.MyBean;");
        assertNoError(733, "Lbeans.MyBean;");
        assertNoError(777, "Lbeans.MyBeanSettable;");
    }

    @Override
    public void testWarningExprs()
    {
        List<IMessage> list = assertSemanticWarning(851, null, 1);
        assertContainsProblem(list,DiagnosticFactory.VARIABLE_NOT_FOUND_ID);

        list = assertSemanticWarning(891, null, 1);
        assertContainsProblem(list,DiagnosticFactory.VARIABLE_NOT_FOUND_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no error cases
    }

}
