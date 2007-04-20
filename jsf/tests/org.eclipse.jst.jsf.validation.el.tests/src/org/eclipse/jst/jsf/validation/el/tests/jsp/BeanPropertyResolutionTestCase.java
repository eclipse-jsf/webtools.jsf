package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for bean property resolution
 * 
 * @author cbateman
 */
public class BeanPropertyResolutionTestCase extends SingleJSPTestCase 
{
    public BeanPropertyResolutionTestCase() {
        super("/testdata/jsps/beanPropertyResolution.jsp.data", "/beanPropertyResolution.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
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
        assertEquals("myBeanSubClass.stringProperty", getELText(_structuredDocument,1324));
        assertEquals("myBeanSubClass.integerProperty", getELText(_structuredDocument,1387));
        assertEquals("myBeanSubClass.booleanProperty", getELText(_structuredDocument,1451));
        assertEquals("myBeanSubClass.doubleProperty", getELText(_structuredDocument,1515));
        assertEquals("myBeanSubClass.mapProperty", getELText(_structuredDocument,1578));
        assertEquals("myBeanSubClass.stringArrayProperty", getELText(_structuredDocument,1638));
        assertEquals("myBeanSubClass.collectionProperty", getELText(_structuredDocument,1706));
        assertEquals("myBeanSubClass.listProperty", getELText(_structuredDocument,1773));
        assertEquals("myBeanSubClass.comparableProperty", getELText(_structuredDocument,1834));
        assertEquals("myBeanSubClass.bigIntegerProperty", getELText(_structuredDocument,1901));
        assertEquals("myBeanSubClass.bigDoubleProperty", getELText(_structuredDocument,1968));
        assertEquals("myBeanSubClass.writableStringProperty", getELText(_structuredDocument,2034));
        assertEquals("myBeanSubClass.isStyleBooleanProperty", getELText(_structuredDocument,2105));
        assertEquals("myBeanSubClass.subClassStringProperty", getELText(_structuredDocument,2176));
        assertEquals("myBeanSubClass.stringProperty", getELText(_structuredDocument,2278));
        assertEquals("myBeanSettable.integerProperty", getELText(_structuredDocument,2341));
        assertEquals("myBeanSettable.booleanProperty", getELText(_structuredDocument,2405));
        assertEquals("myBeanSettable.doubleProperty", getELText(_structuredDocument,2469));
        assertEquals("myBeanSettable.mapProperty", getELText(_structuredDocument,2532));
        assertEquals("myBeanSettable.stringArrayProperty", getELText(_structuredDocument,2592));
        assertEquals("myBeanSettable.collectionProperty", getELText(_structuredDocument,2660));
        assertEquals("myBeanSettable.listProperty", getELText(_structuredDocument,2727));
        assertEquals("myBeanSettable.comparableProperty", getELText(_structuredDocument,2788));
        assertEquals("myBeanSettable.bigIntegerProperty", getELText(_structuredDocument,2855));
        assertEquals("myBeanSettable.bigDoubleProperty", getELText(_structuredDocument,2922));
        assertEquals("myBeanSettable.writableStringProperty", getELText(_structuredDocument,2988));
        assertEquals("myBeanSettable.isStyleBooleanProperty", getELText(_structuredDocument,3059));

        assertEquals("myBean.subClassStringProperty", getELText(_structuredDocument,3159));
        assertEquals("myBeanSubClass.notAMember", getELText(_structuredDocument,3222));
        assertEquals("myBeanSettable.alsoNotAMember", getELText(_structuredDocument,3281));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(541, TypeConstants.TYPE_STRING);
        assertNoError(596, Signature.SIG_INT);
        assertNoError(652, Signature.SIG_BOOLEAN);
        assertNoError(708, Signature.SIG_DOUBLE);
        assertNoError(763, TypeConstants.TYPE_MAP);
        assertNoError(815, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1));
        assertNoError(875, TypeConstants.TYPE_COLLECTION);
        assertNoError(934, "Ljava.util.List;");
        assertNoError(987, TypeConstants.TYPE_COMPARABLE);
        assertNoError(1046, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1105, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1163, TypeConstants.TYPE_STRING);
        assertNoError(1226, Signature.SIG_BOOLEAN);

        assertNoError(1324, TypeConstants.TYPE_STRING);
        assertNoError(1387, Signature.SIG_INT);
        assertNoError(1451, Signature.SIG_BOOLEAN);
        assertNoError(1515, Signature.SIG_DOUBLE);
        assertNoError(1578, TypeConstants.TYPE_MAP);
        assertNoError(1638, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1));
        assertNoError(1706, TypeConstants.TYPE_COLLECTION);
        assertNoError(1773, "Ljava.util.List;");
        assertNoError(1834, TypeConstants.TYPE_COMPARABLE);
        assertNoError(1901, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1968, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(2034, TypeConstants.TYPE_STRING);
        assertNoError(2105, Signature.SIG_BOOLEAN);
        assertNoError(2176, TypeConstants.TYPE_STRING);
        assertNoError(2278, TypeConstants.TYPE_STRING);

        assertNoError(2341, Signature.SIG_INT);
        assertNoError(2405, Signature.SIG_BOOLEAN);
        assertNoError(2469, Signature.SIG_DOUBLE);
        assertNoError(2532, TypeConstants.TYPE_MAP);
        assertNoError(2592, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1));
        assertNoError(2660, TypeConstants.TYPE_COLLECTION);
        assertNoError(2727, "Ljava.util.List;");
        assertNoError(2788, TypeConstants.TYPE_COMPARABLE);
        assertNoError(2855, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(2922, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(2988, TypeConstants.TYPE_STRING);
        assertNoError(3059, Signature.SIG_BOOLEAN);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(3159,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3222,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(3281,null,1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    public void testErrorExprs() 
    {
        // no error cases
    }
}
