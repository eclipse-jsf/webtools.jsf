package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

/**
 * Test cases for assignability test on variable and properties
 * 
 * @author cbateman
 */
public class AssignabilityTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/assignability.jsp.data";
        _destFileName = "/assignability.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("myBean.integerProperty + 3", getELText(_structuredDocument,906));
        assertEquals("myBean.writableStringProperty", getELText(_structuredDocument,965));
        assertEquals("myBean.stringProperty", getELText(_structuredDocument,1025));
        assertEquals("bundle.bundleProp2", getELText(_structuredDocument,1076));
        assertEquals("requestScope.myBeanSubClass", getELText(_structuredDocument,1127));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(906, Signature.SIG_LONG, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(965, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(1025, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1076, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1127, "Lbeans.MyBeanSubClass;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        // TODO: this will fail when the jsf-impl/api jars are injected since then we will
        // have fully resolved (L) signatures instead of (Q) unresolved ones.
        assertNoError(1187, "(QFacesContext;QUIComponent;Ljava.lang.Object;)V", IAssignable.ASSIGNMENT_TYPE_NONE);
    }

    public void testWarningExprs()
    {
        // no warnings
    }
    
    public void testErrorExprs() 
    {
        // no errors
    }
}
