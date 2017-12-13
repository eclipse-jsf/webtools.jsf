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
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
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

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean.stringProperty", ELAssert.getELText(_structuredDocument,523));
        assertEquals("myBean.integerProperty", ELAssert.getELText(_structuredDocument,577));
        assertEquals("myBean.booleanProperty", ELAssert.getELText(_structuredDocument,632));
        assertEquals("myBean.doubleProperty", ELAssert.getELText(_structuredDocument,687));
        assertEquals("myBean.mapProperty", ELAssert.getELText(_structuredDocument,741));
        assertEquals("myBean.stringArrayProperty", ELAssert.getELText(_structuredDocument,792));
        assertEquals("myBean.collectionProperty", ELAssert.getELText(_structuredDocument,851));
        assertEquals("myBean.listProperty", ELAssert.getELText(_structuredDocument,909));
        assertEquals("myBean.comparableProperty", ELAssert.getELText(_structuredDocument,961));
        assertEquals("myBean.bigIntegerProperty", ELAssert.getELText(_structuredDocument,1019));
        assertEquals("myBean.bigDoubleProperty", ELAssert.getELText(_structuredDocument,1077));
        assertEquals("myBean.writableStringProperty", ELAssert.getELText(_structuredDocument,1134));
        assertEquals("myBean.isStyleBooleanProperty", ELAssert.getELText(_structuredDocument,1196));
        assertEquals("myBean.colors", ELAssert.getELText(_structuredDocument,1258));
        assertEquals("myBean.coins", ELAssert.getELText(_structuredDocument,1304));

        assertEquals("myBeanSubClass.stringProperty", ELAssert.getELText(_structuredDocument,1382));
        assertEquals("myBeanSubClass.integerProperty", ELAssert.getELText(_structuredDocument,1444));
        assertEquals("myBeanSubClass.booleanProperty", ELAssert.getELText(_structuredDocument,1507));
        assertEquals("myBeanSubClass.doubleProperty", ELAssert.getELText(_structuredDocument,1570));
        assertEquals("myBeanSubClass.mapProperty", ELAssert.getELText(_structuredDocument,1632));
        assertEquals("myBeanSubClass.stringArrayProperty", ELAssert.getELText(_structuredDocument,1691));
        assertEquals("myBeanSubClass.collectionProperty", ELAssert.getELText(_structuredDocument,1758));
        assertEquals("myBeanSubClass.listProperty", ELAssert.getELText(_structuredDocument,1824));
        assertEquals("myBeanSubClass.comparableProperty", ELAssert.getELText(_structuredDocument,1884));
        assertEquals("myBeanSubClass.bigIntegerProperty", ELAssert.getELText(_structuredDocument,1950));
        assertEquals("myBeanSubClass.bigDoubleProperty", ELAssert.getELText(_structuredDocument,2016));
        assertEquals("myBeanSubClass.writableStringProperty", ELAssert.getELText(_structuredDocument,2081));
        assertEquals("myBeanSubClass.isStyleBooleanProperty", ELAssert.getELText(_structuredDocument,2151));
        assertEquals("myBeanSubClass.subClassStringProperty", ELAssert.getELText(_structuredDocument,2221));
        assertEquals("myBeanSubClass.colors", ELAssert.getELText(_structuredDocument,2291));
        assertEquals("myBeanSubClass.coins", ELAssert.getELText(_structuredDocument,2345));

        assertEquals("myBeanSubClass.stringProperty", ELAssert.getELText(_structuredDocument,2425));
        assertEquals("myBeanSettable.integerProperty", ELAssert.getELText(_structuredDocument,2487));
        assertEquals("myBeanSettable.booleanProperty", ELAssert.getELText(_structuredDocument,2550));
        assertEquals("myBeanSettable.doubleProperty", ELAssert.getELText(_structuredDocument,2613));
        assertEquals("myBeanSettable.mapProperty", ELAssert.getELText(_structuredDocument,2675));
        assertEquals("myBeanSettable.stringArrayProperty", ELAssert.getELText(_structuredDocument,2734));
        assertEquals("myBeanSettable.collectionProperty", ELAssert.getELText(_structuredDocument,2801));
        assertEquals("myBeanSettable.listProperty", ELAssert.getELText(_structuredDocument,2867));
        assertEquals("myBeanSettable.comparableProperty", ELAssert.getELText(_structuredDocument,2927));
        assertEquals("myBeanSettable.bigIntegerProperty", ELAssert.getELText(_structuredDocument,2993));
        assertEquals("myBeanSettable.bigDoubleProperty", ELAssert.getELText(_structuredDocument,3059));
        assertEquals("myBeanSettable.writableStringProperty", ELAssert.getELText(_structuredDocument,3124));
        assertEquals("myBeanSettable.isStyleBooleanProperty", ELAssert.getELText(_structuredDocument,3194));
        assertEquals("myBeanSettable.colors", ELAssert.getELText(_structuredDocument,3264));
        assertEquals("myBeanSettable.coins", ELAssert.getELText(_structuredDocument,3318));

        assertEquals("myBean.subClassStringProperty", ELAssert.getELText(_structuredDocument,3398));
        assertEquals("myBeanSubClass.notAMember", ELAssert.getELText(_structuredDocument,3460));
        assertEquals("myBeanSettable.alsoNotAMember", ELAssert.getELText(_structuredDocument,3518));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(523, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(577, Signature.SIG_INT, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(632, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(687, Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(741, TypeConstants.TYPE_MAP, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(792, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1), IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(851, TypeConstants.TYPE_COLLECTION, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(909, "Ljava.util.List;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(961, TypeConstants.TYPE_COMPARABLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1019, TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1077, TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1134, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(1196, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1258, "Lbeans.MyEnum2;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1304, "Lbeans.MyEnum1;", IAssignable.ASSIGNMENT_TYPE_RHS);

        assertNoError(1382, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1444, Signature.SIG_INT, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1507, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1570, Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1632, TypeConstants.TYPE_MAP, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1691, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1), IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1758, TypeConstants.TYPE_COLLECTION, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1824, "Ljava.util.List;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1884, TypeConstants.TYPE_COMPARABLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1950, TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2016, TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2081, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2151, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2221, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2291, "Lbeans.MyEnum2;", IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2345, "Lbeans.MyEnum1;", IAssignable.ASSIGNMENT_TYPE_RHS);

        assertNoError(2425, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(2487, Signature.SIG_INT, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2550, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2613, Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2675, TypeConstants.TYPE_MAP, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2734, Signature.createArraySignature(TypeConstants.TYPE_STRING, 1), IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2801, TypeConstants.TYPE_COLLECTION, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2867, "Ljava.util.List;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2927, TypeConstants.TYPE_COMPARABLE, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(2993, TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3059, TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3124, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3194, Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3264, "Lbeans.MyEnum2;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(3318, "Lbeans.MyEnum1;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(3398,null,1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3460,null,1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3518,null,1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no error cases
    }
}
