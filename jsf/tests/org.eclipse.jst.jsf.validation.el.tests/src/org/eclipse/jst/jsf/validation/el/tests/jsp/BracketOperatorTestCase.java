package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Main test cases for the bracket operator -- x['y']
 * @author cbateman
 *
 */
public class BracketOperatorTestCase extends SingleJSPTestCase 
{
    public BracketOperatorTestCase() 
    {
        super("/testdata/jsps/bracketOperator.jsp.data", "/bracketOperator.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
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
        assertEquals("myBean.stringArrayProperty[0]", getELText(_structuredDocument,1620));
        assertEquals("myBean.stringArrayProperty[myBean.integerProperty]", getELText(_structuredDocument,1680));
        assertEquals("myBean.stringArrayProperty['0']", getELText(_structuredDocument,1761));
        assertEquals("myBean.intArrayProperty[1]", getELText(_structuredDocument,1823));
        assertEquals("myBean.intArrayProperty[1] > 0", getELText(_structuredDocument,1880));
        assertEquals("empty myBean.arrayOfArrayOfStringProperty", getELText(_structuredDocument,1941));
        assertEquals("empty myBean.arrayOfArrayOfStringProperty[1]", getELText(_structuredDocument,2013));
        assertEquals("myBean.arrayOfArrayOfStringProperty[0][1]", getELText(_structuredDocument,2088));
        assertEquals("myBean.arrayOfArrayOfStringProperty[myBean.intArrayProperty[0]][myBean.intArrayProperty[1]]", getELText(_structuredDocument,2160));
        
        assertEquals("beanWithListProperties.listProperty[0]", getELText(_structuredDocument,2284));
        assertEquals("beanWithListProperties.listProperty[myBean.integerProperty]", getELText(_structuredDocument,2353));
        assertEquals("beanWithListProperties.listProperty['0']", getELText(_structuredDocument,2443));
        assertEquals("beanWithListProperties.listProperty['0'].someValue", getELText(_structuredDocument,2514));
        assertEquals("beanWithListProperties.arrayListProperty[0]", getELText(_structuredDocument,2595));
        assertEquals("beanWithListProperties.arrayListProperty[myBean.integerProperty]", getELText(_structuredDocument,2669));
        assertEquals("beanWithListProperties.arrayListProperty['0']", getELText(_structuredDocument,2764));
        assertEquals("beanWithListProperties.arrayListProperty['0'].someValue", getELText(_structuredDocument,2840));
        assertEquals("listBean[0]", getELText(_structuredDocument,2930));
        assertEquals("listBean[myBean.integerProperty]", getELText(_structuredDocument,2972));
        assertEquals("listBean['0']", getELText(_structuredDocument,3035));
        assertEquals("listBean['0'].someValue", getELText(_structuredDocument,3079));

        assertEquals("bundle['y']", getELText(_structuredDocument,3160));
        assertEquals("bundle['x.z']", getELText(_structuredDocument,3205));
        assertEquals("beanWithMapProperties['actionProperty.foo']", getELText(_structuredDocument,3252));
        assertEquals("beanWithMapProperties['treeMap.foo']", getELText(_structuredDocument,3329));
        assertEquals("beanWithMapProperties['mapProperty.foo']", getELText(_structuredDocument,3399));
        assertEquals("beanWithMapProperties['mapProperty.foo.x']", getELText(_structuredDocument,3473));
        assertEquals("beanWithMapProperties['treeMap.foo.x']", getELText(_structuredDocument,3549));
        assertEquals("myBean.stringArrayProperty[myBean.stringProperty]", getELText(_structuredDocument,3621));
        assertEquals("myBean.stringArrayProperty[-1]", getELText(_structuredDocument,3703));
        assertEquals("beanWithListProperties.listProperty.someProperty", getELText(_structuredDocument,3766));
        assertEquals("beanWithListProperties.arrayListProperty.someProperty", getELText(_structuredDocument,3845));
        assertEquals("beanWithListProperties.arrayListProperty[-1]", getELText(_structuredDocument,3929));
        assertEquals("listBean[-1]", getELText(_structuredDocument,4004));
        assertEquals("myBean.stringArrayProperty['a']", getELText(_structuredDocument,4249));
        assertEquals("beanWithListProperties.arrayListProperty['a']", getELText(_structuredDocument,4311));
        assertEquals("beanWithListProperties.arrayListProperty[true]", getELText(_structuredDocument,4387));
        assertEquals("listBean['a']", getELText(_structuredDocument,4464));
        assertEquals("listBean[true]", getELText(_structuredDocument,4508));    }
    
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
        
        assertNoError(1620, TypeConstants.TYPE_STRING);
        assertNoError(1680, TypeConstants.TYPE_STRING);
        assertNoError(1761, TypeConstants.TYPE_STRING);
        
        assertNoError(1823, Signature.SIG_INT);
        assertNoError(1880, Signature.SIG_BOOLEAN);
        assertNoError(1941, Signature.SIG_BOOLEAN);
        assertNoError(2013, Signature.SIG_BOOLEAN);

        assertNoError(2088, TypeConstants.TYPE_STRING);
        assertNoError(2160, TypeConstants.TYPE_STRING);


        assertNoError(2284, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2353, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2443, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2514, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2595, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2669, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2764, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2840, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2930, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2972, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3035, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3079, TypeConstants.TYPE_JAVAOBJECT);
    }

    public void testWarningExprs() 
    {

        List list = assertSemanticWarning(3160, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(3205, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(3252, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3329, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3399, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3473, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3549, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3621, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        list = assertSemanticWarning(3703, null, 1);
        assertContainsProblem(list, DiagnosticFactory.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS_ID);

        list = assertSemanticWarning(3766, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3845, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3929, null, 1);
        assertContainsProblem(list, DiagnosticFactory.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS_ID);

        list = assertSemanticWarning(4004, null, 1);
        assertContainsProblem(list, DiagnosticFactory.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS_ID);
    }
    
    public void testErrorExprs() 
    {        
        List list = assertSemanticError(4249, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(4311, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(4387, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(4464, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(4508, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
