package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Test cases for bean property resolution
 * 
 * @author cbateman
 */
public class BeanPropertyResolutionTestCase extends SingleJSPTestCase 
{
    public BeanPropertyResolutionTestCase() {
        super("/testdata/jsps/beanPropertyResolution.jsp.data", "/beanPropertyResolution.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    /**
     * Allow sub-classing for sensitivity analysis (i.e. different dest file extensions)
     * 
     * @param srcFile
     * @param destFile
     */
    protected BeanPropertyResolutionTestCase(final String srcFile, final String destFile)
    {
        super(srcFile,destFile, JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("myBean.stringProperty", getELText(_structuredDocument,541));
        assertEquals("myBean.integerProperty", getELText(_structuredDocument,596));
        assertEquals("myBean.booleanProperty", getELText(_structuredDocument,652));
        assertEquals("myBean.doubleProperty", getELText(_structuredDocument,708));
        assertEquals("myBean.mapProperty", getELText(_structuredDocument,763));
        assertEquals("myBean.stringArrayProperty", getELText(_structuredDocument,815));
        assertEquals("myBean.collectionProperty", getELText(_structuredDocument,875));
        assertEquals("myBean.listProperty", getELText(_structuredDocument,934));
        assertEquals("myBean.comparableProperty", getELText(_structuredDocument,987));
        assertEquals("myBean.bigIntegerProperty", getELText(_structuredDocument,1046));
        assertEquals("myBean.bigDoubleProperty", getELText(_structuredDocument,1105));
        assertEquals("myBean.writableStringProperty", getELText(_structuredDocument,1163));
        assertEquals("myBean.isStyleBooleanProperty", getELText(_structuredDocument,1226));
        assertEquals("myBean.colors", getELText(_structuredDocument,1289));
        assertEquals("myBean.coins", getELText(_structuredDocument,1336));
        
        assertEquals("myBeanSubClass.stringProperty", getELText(_structuredDocument,1417));
        assertEquals("myBeanSubClass.integerProperty", getELText(_structuredDocument,1480));
        assertEquals("myBeanSubClass.booleanProperty", getELText(_structuredDocument,1544));
        assertEquals("myBeanSubClass.doubleProperty", getELText(_structuredDocument,1608));
        assertEquals("myBeanSubClass.mapProperty", getELText(_structuredDocument,1671));
        assertEquals("myBeanSubClass.stringArrayProperty", getELText(_structuredDocument,1731));
        assertEquals("myBeanSubClass.collectionProperty", getELText(_structuredDocument,1799));
        assertEquals("myBeanSubClass.listProperty", getELText(_structuredDocument,1866));
        assertEquals("myBeanSubClass.comparableProperty", getELText(_structuredDocument,1927));
        assertEquals("myBeanSubClass.bigIntegerProperty", getELText(_structuredDocument,1994));
        assertEquals("myBeanSubClass.bigDoubleProperty", getELText(_structuredDocument,2061));
        assertEquals("myBeanSubClass.writableStringProperty", getELText(_structuredDocument,2127));
        assertEquals("myBeanSubClass.isStyleBooleanProperty", getELText(_structuredDocument,2198));
        assertEquals("myBeanSubClass.subClassStringProperty", getELText(_structuredDocument,2269));
        assertEquals("myBeanSubClass.colors", getELText(_structuredDocument,2340));
        assertEquals("myBeanSubClass.coins", getELText(_structuredDocument,2395));

        assertEquals("myBeanSubClass.stringProperty", getELText(_structuredDocument,2478));
        assertEquals("myBeanSettable.integerProperty", getELText(_structuredDocument,2541));
        assertEquals("myBeanSettable.booleanProperty", getELText(_structuredDocument,2605));
        assertEquals("myBeanSettable.doubleProperty", getELText(_structuredDocument,2669));
        assertEquals("myBeanSettable.mapProperty", getELText(_structuredDocument,2732));
        assertEquals("myBeanSettable.stringArrayProperty", getELText(_structuredDocument,2792));
        assertEquals("myBeanSettable.collectionProperty", getELText(_structuredDocument,2860));
        assertEquals("myBeanSettable.listProperty", getELText(_structuredDocument,2927));
        assertEquals("myBeanSettable.comparableProperty", getELText(_structuredDocument,2988));
        assertEquals("myBeanSettable.bigIntegerProperty", getELText(_structuredDocument,3055));
        assertEquals("myBeanSettable.bigDoubleProperty", getELText(_structuredDocument,3122));
        assertEquals("myBeanSettable.writableStringProperty", getELText(_structuredDocument,3188));
        assertEquals("myBeanSettable.isStyleBooleanProperty", getELText(_structuredDocument,3259));
        assertEquals("myBeanSettable.colors", getELText(_structuredDocument,3330));
        assertEquals("myBeanSettable.coins", getELText(_structuredDocument,3385));
        
        assertEquals("myBean.subClassStringProperty", getELText(_structuredDocument,3468));
        assertEquals("myBeanSubClass.notAMember", getELText(_structuredDocument,3531));
        assertEquals("myBeanSettable.alsoNotAMember", getELText(_structuredDocument,3590));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(541, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(596, Signature.SIG_INT, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(652, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(708, Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(763, TypeConstants.TYPE_MAP, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(815, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1), IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(875, TypeConstants.TYPE_COLLECTION, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(934, "Ljava.util.List;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(987, TypeConstants.TYPE_COMPARABLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1046, TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1105, TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1163, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(1226, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1289, "Lbeans.MyEnum2;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1336, "Lbeans.MyEnum1;", IAssignable.ASSIGNMENT_TYPE_RHS);

        assertNoError(1417, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1480, Signature.SIG_INT, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1544, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1608, Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1671, TypeConstants.TYPE_MAP, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1731, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1), IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1799, TypeConstants.TYPE_COLLECTION, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1866, "Ljava.util.List;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1927, TypeConstants.TYPE_COMPARABLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1994, TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2061, TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2127, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2198, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2269, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2340, "Lbeans.MyEnum2;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2395, "Lbeans.MyEnum1;", IAssignable.ASSIGNMENT_TYPE_RHS);
        
        assertNoError(2478, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2541, Signature.SIG_INT, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2605, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2669, Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2732, TypeConstants.TYPE_MAP, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2792, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1), IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2860, TypeConstants.TYPE_COLLECTION, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2927, "Ljava.util.List;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2988, TypeConstants.TYPE_COMPARABLE, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3055, TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3122, TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3188, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3259, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3330, "Lbeans.MyEnum2;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3385, "Lbeans.MyEnum1;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
    }

    public void testWarningExprs() 
    {
        List<IMessage> list = assertSemanticWarning(3468,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3531,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(3590,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    public void testErrorExprs() 
    {
        // no error cases
    }
}
