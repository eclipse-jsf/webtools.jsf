package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.types.TypeConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Main test cases for the bracket operator -- x['y']
 * @author cbateman
 *
 */
public class BracketOperatorTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/bracketOperator.jsp.data";
        _destFileName = "/bracketOperator.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("bundle['x.y']", getELText(_structuredDocument,841));
        assertEquals("beanWithMapProperties['integerProperty']", getELText(_structuredDocument,888));
        assertEquals("bundle['x']", getELText(_structuredDocument,967));
        assertEquals("beanWithMapProperties.treeMap", getELText(_structuredDocument,1020));
        assertEquals("beanWithMapProperties.treeMap.foo", getELText(_structuredDocument,1090));
        assertEquals("beanWithMapProperties['treeMap'].foo", getELText(_structuredDocument,1157));
        assertEquals("beanWithMapProperties['treeMap']['foo']", getELText(_structuredDocument,1228));
        assertEquals("beanWithMapProperties.treeMap['foo']", getELText(_structuredDocument,1301));
        assertEquals("mapBean['foo']", getELText(_structuredDocument,1380));
        assertEquals("mapBean['foo.x']", getELText(_structuredDocument,1428));
        assertEquals("mapBean['getIgnoredIntProperty']", getELText(_structuredDocument,1478));
        assertEquals("beanWithMapProperties['actionProperty']", getELText(_structuredDocument,1548));
        
        
        assertEquals("bundle['y']", getELText(_structuredDocument,1645));
        assertEquals("bundle['x.z']", getELText(_structuredDocument,1690));
        assertEquals("beanWithMapProperties['actionProperty.foo']", getELText(_structuredDocument,1737));
        assertEquals("beanWithMapProperties['treeMap.foo']", getELText(_structuredDocument,1814));
        assertEquals("beanWithMapProperties['mapProperty.foo']", getELText(_structuredDocument,1884));
        assertEquals("beanWithMapProperties['mapProperty.foo.x']", getELText(_structuredDocument,1958));
        assertEquals("beanWithMapProperties['treeMap.foo.x']", getELText(_structuredDocument,2034));    
    }
    

    public void testNoErrorExprs() 
    {
        assertNoError(841, TypeConstants.TYPE_STRING);
        assertNoError(888, Signature.SIG_INT);
//        assertNoError(967, TypeConstants.TYPE_STRING);
        assertNoError(1020, "Ljava.util.TreeMap;");
        assertNoError(1090, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1157, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1228, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1301, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1380, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1428, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1478, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1548, Signature.createMethodSignature(new String[0], TypeConstants.TYPE_STRING));
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1645, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(1690, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(1737, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(1814, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(1884, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(1958, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(2034, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }
    
    public void testErrorExprs() 
    {
        // no error expressions
    }

}
