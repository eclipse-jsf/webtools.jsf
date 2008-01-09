package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Test cases for method bindings
 * 
 * @author cbateman
 */
public class MethodBindingTestCase extends SingleJSPTestCase
{
    public MethodBindingTestCase()
    {
        super("/testdata/jsps/methodBinding.jsp.data", "/methodBinding.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean.getStringProperty", getELText(_structuredDocument,825));
        assertEquals("myBean.getIntegerProperty", getELText(_structuredDocument,885));
        assertEquals("myBean.getBooleanProperty", getELText(_structuredDocument,946));
        assertEquals("myBean.getDoubleProperty", getELText(_structuredDocument,1007));
        assertEquals("myBean.getMapProperty", getELText(_structuredDocument,1067));
        assertEquals("myBean.getStringArrayProperty", getELText(_structuredDocument,1124));
        assertEquals("myBean.getCollectionProperty", getELText(_structuredDocument,1189));
        assertEquals("myBean.getListProperty", getELText(_structuredDocument,1253));
        assertEquals("myBean.getComparableProperty", getELText(_structuredDocument,1311));
        assertEquals("myBean.getBigIntegerProperty", getELText(_structuredDocument,1375));
        assertEquals("myBean.getBigDoubleProperty", getELText(_structuredDocument,1439));
        assertEquals("myBean.recursiveCall", getELText(_structuredDocument,1502));
        assertEquals("myBean.getWritableStringProperty", getELText(_structuredDocument,1558));
        assertEquals("myBean.setWritableStringProperty", getELText(_structuredDocument,1626));
        assertEquals("myBean.validate", getELText(_structuredDocument,1694));
        assertEquals("myBean.validate2", getELText(_structuredDocument,1745));
        assertEquals("myBean.getSelf", getELText(_structuredDocument,1797));
        assertEquals("myBean.isIsStyleBooleanProperty", getELText(_structuredDocument,1847));
        assertEquals("myBeanSubClass.getStringProperty", getELText(_structuredDocument,1915));
        assertEquals("myBeanSubClass.getIntegerProperty", getELText(_structuredDocument,1983));
        assertEquals("myBeanSubClass.getBooleanProperty", getELText(_structuredDocument,2052));
        assertEquals("myBeanSubClass.getDoubleProperty", getELText(_structuredDocument,2121));
        assertEquals("myBeanSubClass.getMapProperty", getELText(_structuredDocument,2189));
        assertEquals("myBeanSubClass.getStringArrayProperty", getELText(_structuredDocument,2254));
        assertEquals("myBeanSubClass.getCollectionProperty", getELText(_structuredDocument,2327));
        assertEquals("myBeanSubClass.getListProperty", getELText(_structuredDocument,2399));
        assertEquals("myBeanSubClass.getComparableProperty", getELText(_structuredDocument,2465));
        assertEquals("myBeanSubClass.getBigIntegerProperty", getELText(_structuredDocument,2537));
        assertEquals("myBeanSubClass.getBigDoubleProperty", getELText(_structuredDocument,2609));
        assertEquals("myBeanSubClass.recursiveCall", getELText(_structuredDocument,2680));
        assertEquals("myBeanSubClass.getWritableStringProperty", getELText(_structuredDocument,2744));
        assertEquals("myBeanSubClass.setWritableStringProperty", getELText(_structuredDocument,2820));
        assertEquals("myBeanSubClass.validate", getELText(_structuredDocument,2896));
        assertEquals("myBeanSubClass.validate2", getELText(_structuredDocument,2955));
        assertEquals("myBeanSubClass.getSelf", getELText(_structuredDocument,3015));
        assertEquals("myBeanSubClass.isIsStyleBooleanProperty", getELText(_structuredDocument,3073));
        assertEquals("myBeanSubClass.getSubClassStringProperty", getELText(_structuredDocument,3148));

        assertEquals("-myBean.validate", getELText(_structuredDocument,3272));
        assertEquals("myBean.getIntegerProperty + myBean.getDoubleProperty", getELText(_structuredDocument,3320));
    }

    @Override
    public void testNoErrorExprs() {
        final String[] noStrings = new  String[0];
        assertNoError(825, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(885, Signature.createMethodSignature(noStrings, Signature.SIG_INT));
        assertNoError(946, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));
        assertNoError(1007, Signature.createMethodSignature(noStrings, Signature.SIG_DOUBLE));
        assertNoError(1067, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_MAP));
        assertNoError(1124, Signature.createMethodSignature(noStrings, Signature.createArraySignature(TypeConstants.TYPE_STRING,1)));
        assertNoError(1189, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COLLECTION));
        assertNoError(1253, Signature.createMethodSignature(noStrings, "Ljava.util.List;"));
        assertNoError(1311, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COMPARABLE));
        assertNoError(1375, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_INTEGER));
        assertNoError(1439, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_DOUBLE));
        assertNoError(1502, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));
        assertNoError(1558, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(1626, Signature.createMethodSignature(new String[]{TypeConstants.TYPE_STRING}, Signature.SIG_VOID));
        assertNoError(1694, "(QFacesContext;QUIComponent;Ljava.lang.Object;)V");
        assertNoError(1745, "(QFacesContext;QUIComponent;[Ljava.lang.Object;)V");
        assertNoError(1797, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));
        assertNoError(1847, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));

        assertNoError(1915, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(1983, Signature.createMethodSignature(noStrings, Signature.SIG_INT));
        assertNoError(2052, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));
        assertNoError(2121, Signature.createMethodSignature(noStrings, Signature.SIG_DOUBLE));
        assertNoError(2189, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_MAP));
        assertNoError(2254, Signature.createMethodSignature(noStrings, Signature.createArraySignature(TypeConstants.TYPE_STRING,1)));
        assertNoError(2327, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COLLECTION));
        assertNoError(2399, Signature.createMethodSignature(noStrings, "Ljava.util.List;"));
        assertNoError(2465, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_COMPARABLE));
        assertNoError(2537, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_INTEGER));
        assertNoError(2609, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_BIG_DOUBLE));
        assertNoError(2680, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));
        assertNoError(2744, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
        assertNoError(2820, Signature.createMethodSignature(new String[]{TypeConstants.TYPE_STRING}, Signature.SIG_VOID));
        assertNoError(2896, "(QFacesContext;QUIComponent;Ljava.lang.Object;)V");
        assertNoError(2955, "(QFacesContext;QUIComponent;[Ljava.lang.Object;)V");
        assertNoError(3015, Signature.createMethodSignature(noStrings, "Lbeans.MyBean;"));
        assertNoError(3073, Signature.createMethodSignature(noStrings, Signature.SIG_BOOLEAN));
        assertNoError(3148, Signature.createMethodSignature(noStrings, TypeConstants.TYPE_STRING));
    }

    @Override
    public void testWarningExprs()
    {
        // no warning cases
    }

    @Override
    public void testErrorExprs()
    {
        List<IMessage> list = assertSemanticError(3272, null, 1);
        assertContainsProblem(list, DiagnosticFactory.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING_ID);

        list = assertSemanticError(3320, null, 2);
        assertContainsProblem(list, DiagnosticFactory.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING_ID);
    }

}
