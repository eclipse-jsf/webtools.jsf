/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

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
        assertEquals("myBean.getStringProperty", ELAssert.getELText(_structuredDocument,825));
        assertEquals("myBean.getIntegerProperty", ELAssert.getELText(_structuredDocument,885));
        assertEquals("myBean.getBooleanProperty", ELAssert.getELText(_structuredDocument,946));
        assertEquals("myBean.getDoubleProperty", ELAssert.getELText(_structuredDocument,1007));
        assertEquals("myBean.getMapProperty", ELAssert.getELText(_structuredDocument,1067));
        assertEquals("myBean.getStringArrayProperty", ELAssert.getELText(_structuredDocument,1124));
        assertEquals("myBean.getCollectionProperty", ELAssert.getELText(_structuredDocument,1189));
        assertEquals("myBean.getListProperty", ELAssert.getELText(_structuredDocument,1253));
        assertEquals("myBean.getComparableProperty", ELAssert.getELText(_structuredDocument,1311));
        assertEquals("myBean.getBigIntegerProperty", ELAssert.getELText(_structuredDocument,1375));
        assertEquals("myBean.getBigDoubleProperty", ELAssert.getELText(_structuredDocument,1439));
        assertEquals("myBean.recursiveCall", ELAssert.getELText(_structuredDocument,1502));
        assertEquals("myBean.getWritableStringProperty", ELAssert.getELText(_structuredDocument,1558));
        assertEquals("myBean.setWritableStringProperty", ELAssert.getELText(_structuredDocument,1626));
        assertEquals("myBean.validate", ELAssert.getELText(_structuredDocument,1694));
        assertEquals("myBean.validate2", ELAssert.getELText(_structuredDocument,1745));
        assertEquals("myBean.getSelf", ELAssert.getELText(_structuredDocument,1797));
        assertEquals("myBean.isIsStyleBooleanProperty", ELAssert.getELText(_structuredDocument,1847));
        assertEquals("myBeanSubClass.getStringProperty", ELAssert.getELText(_structuredDocument,1915));
        assertEquals("myBeanSubClass.getIntegerProperty", ELAssert.getELText(_structuredDocument,1983));
        assertEquals("myBeanSubClass.getBooleanProperty", ELAssert.getELText(_structuredDocument,2052));
        assertEquals("myBeanSubClass.getDoubleProperty", ELAssert.getELText(_structuredDocument,2121));
        assertEquals("myBeanSubClass.getMapProperty", ELAssert.getELText(_structuredDocument,2189));
        assertEquals("myBeanSubClass.getStringArrayProperty", ELAssert.getELText(_structuredDocument,2254));
        assertEquals("myBeanSubClass.getCollectionProperty", ELAssert.getELText(_structuredDocument,2327));
        assertEquals("myBeanSubClass.getListProperty", ELAssert.getELText(_structuredDocument,2399));
        assertEquals("myBeanSubClass.getComparableProperty", ELAssert.getELText(_structuredDocument,2465));
        assertEquals("myBeanSubClass.getBigIntegerProperty", ELAssert.getELText(_structuredDocument,2537));
        assertEquals("myBeanSubClass.getBigDoubleProperty", ELAssert.getELText(_structuredDocument,2609));
        assertEquals("myBeanSubClass.recursiveCall", ELAssert.getELText(_structuredDocument,2680));
        assertEquals("myBeanSubClass.getWritableStringProperty", ELAssert.getELText(_structuredDocument,2744));
        assertEquals("myBeanSubClass.setWritableStringProperty", ELAssert.getELText(_structuredDocument,2820));
        assertEquals("myBeanSubClass.validate", ELAssert.getELText(_structuredDocument,2896));
        assertEquals("myBeanSubClass.validate2", ELAssert.getELText(_structuredDocument,2955));
        assertEquals("myBeanSubClass.getSelf", ELAssert.getELText(_structuredDocument,3015));
        assertEquals("myBeanSubClass.isIsStyleBooleanProperty", ELAssert.getELText(_structuredDocument,3073));
        assertEquals("myBeanSubClass.getSubClassStringProperty", ELAssert.getELText(_structuredDocument,3148));

        assertEquals("-myBean.validate", ELAssert.getELText(_structuredDocument,3272));
        assertEquals("myBean.getIntegerProperty + myBean.getDoubleProperty", ELAssert.getELText(_structuredDocument,3320));
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
        List<ReportedProblem> list = assertSemanticError(3272, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING_ID);

        list = assertSemanticError(3320, null, 2);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING_ID);
    }

}
