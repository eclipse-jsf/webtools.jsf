package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.types.TypeConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

public class MethodBindingTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/methodBinding.jsp.data";
        _destFileName = "/methodBinding.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("myBean.getStringProperty", getELText(_structuredDocument,849));
        assertEquals("myBean.getIntegerProperty", getELText(_structuredDocument,910));
        assertEquals("myBean.getBooleanProperty", getELText(_structuredDocument,972));
        assertEquals("myBean.getDoubleProperty", getELText(_structuredDocument,1034));
        assertEquals("myBean.getMapProperty", getELText(_structuredDocument,1095));
        assertEquals("myBean.getStringArrayProperty", getELText(_structuredDocument,1153));
        assertEquals("myBean.getCollectionProperty", getELText(_structuredDocument,1219));
        assertEquals("myBean.getListProperty", getELText(_structuredDocument,1284));
        assertEquals("myBean.getComparableProperty", getELText(_structuredDocument,1343));
        assertEquals("myBean.getBigIntegerProperty", getELText(_structuredDocument,1408));
        assertEquals("myBean.getBigDoubleProperty", getELText(_structuredDocument,1473));
        assertEquals("myBean.recursiveCall", getELText(_structuredDocument,1537));
        assertEquals("myBean.getWritableStringProperty", getELText(_structuredDocument,1594));
        assertEquals("myBean.setWritableStringProperty", getELText(_structuredDocument,1663));
        assertEquals("myBean.validate", getELText(_structuredDocument,1732));
        assertEquals("myBean.validate2", getELText(_structuredDocument,1784));
        assertEquals("myBean.getSelf", getELText(_structuredDocument,1837));
        assertEquals("myBean.isIsStyleBooleanProperty", getELText(_structuredDocument,1888));
        assertEquals("myBeanSubClass.getStringProperty", getELText(_structuredDocument,1958));
        assertEquals("myBeanSubClass.getIntegerProperty", getELText(_structuredDocument,2027));
        assertEquals("myBeanSubClass.getBooleanProperty", getELText(_structuredDocument,2097));
        assertEquals("myBeanSubClass.getDoubleProperty", getELText(_structuredDocument,2167));
        assertEquals("myBeanSubClass.getMapProperty", getELText(_structuredDocument,2236));
        assertEquals("myBeanSubClass.getStringArrayProperty", getELText(_structuredDocument,2302));
        assertEquals("myBeanSubClass.getCollectionProperty", getELText(_structuredDocument,2376));
        assertEquals("myBeanSubClass.getListProperty", getELText(_structuredDocument,2449));
        assertEquals("myBeanSubClass.getComparableProperty", getELText(_structuredDocument,2516));
        assertEquals("myBeanSubClass.getBigIntegerProperty", getELText(_structuredDocument,2589));
        assertEquals("myBeanSubClass.getBigDoubleProperty", getELText(_structuredDocument,2662));
        assertEquals("myBeanSubClass.recursiveCall", getELText(_structuredDocument,2734));
        assertEquals("myBeanSubClass.getWritableStringProperty", getELText(_structuredDocument,2799));
        assertEquals("myBeanSubClass.setWritableStringProperty", getELText(_structuredDocument,2876));
        assertEquals("myBeanSubClass.validate", getELText(_structuredDocument,2953));
        assertEquals("myBeanSubClass.validate2", getELText(_structuredDocument,3013));
        assertEquals("myBeanSubClass.getSelf", getELText(_structuredDocument,3074));
        assertEquals("myBeanSubClass.isIsStyleBooleanProperty", getELText(_structuredDocument,3133));
        assertEquals("myBeanSubClass.getSubClassStringProperty", getELText(_structuredDocument,3209));

        assertEquals("-myBean.validate", getELText(_structuredDocument,3338));
        assertEquals("myBean.getIntegerProperty + myBean.getDoubleProperty", getELText(_structuredDocument,3387));
    }
 
    public void testNoErrorExprs() {
        final String[] noStrings = new  String[0];
        assertNoError(849, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(910, Signature.createMethodSignature(noStrings, Signature.SIG_INT));
        assertNoError(972, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));
        assertNoError(1034, Signature.createMethodSignature(noStrings, Signature.SIG_DOUBLE));
        assertNoError(1095, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_MAP));
        assertNoError(1153, Signature.createMethodSignature(noStrings, Signature.createArraySignature(TypeConstants.TYPE_STRING,1)));
        assertNoError(1219, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COLLECTION));
        assertNoError(1284, Signature.createMethodSignature(noStrings, "Ljava.util.List;"));
        assertNoError(1343, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COMPARABLE));
        assertNoError(1408, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_INTEGER));
        assertNoError(1473, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_DOUBLE));
        assertNoError(1537, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));
        assertNoError(1594, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(1663, Signature.createMethodSignature(new String[]{TypeConstants.TYPE_STRING}, Signature.SIG_VOID));
        assertNoError(1732, "(QFacesContext;QUIComponent;Ljava.lang.Object;)V");
        assertNoError(1784, "(QFacesContext;QUIComponent;[Ljava.lang.Object;)V");
        assertNoError(1837, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));        
        assertNoError(1888, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));        

        assertNoError(1958, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(2027, Signature.createMethodSignature(noStrings, Signature.SIG_INT));
        assertNoError(2097, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));
        assertNoError(2167, Signature.createMethodSignature(noStrings, Signature.SIG_DOUBLE));
        assertNoError(2236, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_MAP));
        assertNoError(2302, Signature.createMethodSignature(noStrings, Signature.createArraySignature(TypeConstants.TYPE_STRING,1)));
        assertNoError(2376, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COLLECTION));
        assertNoError(2449, Signature.createMethodSignature(noStrings, "Ljava.util.List;"));
        assertNoError(2516, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COMPARABLE));
        assertNoError(2589, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_INTEGER));
        assertNoError(2662, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_DOUBLE));
        assertNoError(2734, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));
        assertNoError(2799, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(2876, Signature.createMethodSignature(new String[]{TypeConstants.TYPE_STRING}, Signature.SIG_VOID));
        assertNoError(2953, "(QFacesContext;QUIComponent;Ljava.lang.Object;)V");
        assertNoError(3013, "(QFacesContext;QUIComponent;[Ljava.lang.Object;)V");
        assertNoError(3074, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));        
        assertNoError(3133, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));        
        assertNoError(3209, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));        
    }

    public void testWarningExprs() 
    {
        // no warning cases
    }

    public void testErrorExprs() 
    {
        List list = assertSemanticError(3338, null, 1);
        assertContainsProblem(list, 0);

        list = assertSemanticError(3387, null, 2);
        assertContainsProblem(list, 0);
    }

}
