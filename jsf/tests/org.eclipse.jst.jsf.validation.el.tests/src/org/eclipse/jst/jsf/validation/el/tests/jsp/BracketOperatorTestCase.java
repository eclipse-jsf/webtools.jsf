/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
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
        super("/testdata/jsps/bracketOperator.jsp.data", "/bracketOperator.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        super.testSanity();

        assertEquals("bundle['x.y']", getELText(_structuredDocument,816));
        assertEquals("beanWithMapProperties['integerProperty']", getELText(_structuredDocument,862));
        assertEquals("bundle['x']", getELText(_structuredDocument,940));
        assertEquals("beanWithMapProperties.treeMap", getELText(_structuredDocument,991));
        assertEquals("beanWithMapProperties.treeMap.foo", getELText(_structuredDocument,1059));
        assertEquals("beanWithMapProperties['treeMap'].foo", getELText(_structuredDocument,1125));
        assertEquals("beanWithMapProperties['treeMap']['foo']", getELText(_structuredDocument,1195));
        assertEquals("beanWithMapProperties.treeMap['foo']", getELText(_structuredDocument,1267));
        assertEquals("mapBean['foo']", getELText(_structuredDocument,1343));
        assertEquals("mapBean['foo.x']", getELText(_structuredDocument,1390));
        assertEquals("mapBean['getIgnoredIntProperty']", getELText(_structuredDocument,1439));
        assertEquals("beanWithMapProperties['actionProperty']", getELText(_structuredDocument,1508));
        assertEquals("myBean.stringArrayProperty[0]", getELText(_structuredDocument,1578));
        assertEquals("myBean.stringArrayProperty[myBean.integerProperty]", getELText(_structuredDocument,1637));
        assertEquals("myBean.stringArrayProperty['0']", getELText(_structuredDocument,1717));
        assertEquals("myBean.intArrayProperty[1]", getELText(_structuredDocument,1778));
        assertEquals("myBean.intArrayProperty[1] > 0", getELText(_structuredDocument,1834));
        assertEquals("empty myBean.arrayOfArrayOfStringProperty", getELText(_structuredDocument,1894));
        assertEquals("empty myBean.arrayOfArrayOfStringProperty[1]", getELText(_structuredDocument,1965));
        assertEquals("myBean.arrayOfArrayOfStringProperty[0][1]", getELText(_structuredDocument,2039));
        assertEquals("myBean.arrayOfArrayOfStringProperty[myBean.intArrayProperty[0]][myBean.intArrayProperty[1]]", getELText(_structuredDocument,2110));
        assertEquals("beanWithListProperties.listProperty[0]", getELText(_structuredDocument,2232));
        assertEquals("beanWithListProperties.listProperty[myBean.integerProperty]", getELText(_structuredDocument,2300));
        assertEquals("beanWithListProperties.listProperty['0']", getELText(_structuredDocument,2389));
        assertEquals("beanWithListProperties.listProperty['0'].someValue", getELText(_structuredDocument,2459));
        assertEquals("beanWithListProperties.arrayListProperty[0]", getELText(_structuredDocument,2539));
        assertEquals("beanWithListProperties.arrayListProperty[myBean.integerProperty]", getELText(_structuredDocument,2612));
        assertEquals("beanWithListProperties.arrayListProperty['0']", getELText(_structuredDocument,2706));
        assertEquals("beanWithListProperties.arrayListProperty['0'].someValue", getELText(_structuredDocument,2781));
        assertEquals("listBean[0]", getELText(_structuredDocument,2869));
        assertEquals("listBean[myBean.integerProperty]", getELText(_structuredDocument,2910));
        assertEquals("listBean['0']", getELText(_structuredDocument,2972));
        assertEquals("listBean['0'].someValue", getELText(_structuredDocument,3015));

        assertEquals("bundle['y']", getELText(_structuredDocument,3093));
        assertEquals("bundle['x.z']", getELText(_structuredDocument,3137));
        assertEquals("beanWithMapProperties['actionProperty.foo']", getELText(_structuredDocument,3183));
        assertEquals("beanWithMapProperties['treeMap.foo']", getELText(_structuredDocument,3259));
        assertEquals("beanWithMapProperties['mapProperty.foo']", getELText(_structuredDocument,3328));
        assertEquals("beanWithMapProperties['mapProperty.foo.x']", getELText(_structuredDocument,3401));
        assertEquals("beanWithMapProperties['treeMap.foo.x']", getELText(_structuredDocument,3476));
        assertEquals("myBean.stringArrayProperty[myBean.stringProperty]", getELText(_structuredDocument,3547));
        assertEquals("myBean.stringArrayProperty[-1]", getELText(_structuredDocument,3628));
        assertEquals("beanWithListProperties.listProperty.someProperty", getELText(_structuredDocument,3689));
        assertEquals("beanWithListProperties.arrayListProperty.someProperty", getELText(_structuredDocument,3767));
        assertEquals("beanWithListProperties.arrayListProperty[-1]", getELText(_structuredDocument,3850));
        assertEquals("listBean[-1]", getELText(_structuredDocument,3924));
        assertEquals("bundle['y']", getELText(_structuredDocument,3969));
        assertEquals("bundle[null]", getELText(_structuredDocument,4013));
        assertEquals("listBean.stringProperty", getELText(_structuredDocument,4121));

        assertEquals("listBean['stringProperty']", getELText(_structuredDocument,4175));
        assertEquals("myBean.stringArrayProperty['a']", getELText(_structuredDocument,4256));
        assertEquals("beanWithListProperties.arrayListProperty['a']", getELText(_structuredDocument,4317));
        assertEquals("beanWithListProperties.arrayListProperty[true]", getELText(_structuredDocument,4392));
        assertEquals("listBean['a']", getELText(_structuredDocument,4468));
        assertEquals("listBean[true]", getELText(_structuredDocument,4511));
    }


    @Override
    public void testNoErrorExprs()
    {
        assertNoError(816, TypeConstants.TYPE_STRING);
        assertNoError(862, Signature.SIG_INT);
        //        assertNoError(967, TypeConstants.TYPE_STRING);
        assertNoError(991, "Ljava.util.TreeMap;");
        assertNoError(1059, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1125, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1195, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1267, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1343, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1390, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1439, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1508, Signature.createMethodSignature(new String[0], TypeConstants.TYPE_STRING));

        assertNoError(1578, TypeConstants.TYPE_STRING);
        assertNoError(1637, TypeConstants.TYPE_STRING);
        assertNoError(1717, TypeConstants.TYPE_STRING);

        assertNoError(1778, Signature.SIG_INT);
        assertNoError(1834, Signature.SIG_BOOLEAN);
        assertNoError(1894, Signature.SIG_BOOLEAN);
        assertNoError(1965, Signature.SIG_BOOLEAN);

        assertNoError(2039, TypeConstants.TYPE_STRING);
        assertNoError(2110, TypeConstants.TYPE_STRING);


        assertNoError(2232, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2300, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2389, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2459, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2539, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2612, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2706, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2781, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2869, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2910, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(2972, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3015, TypeConstants.TYPE_JAVAOBJECT);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(3093, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3137, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3183, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3259, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3328, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3401, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3476, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3547, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        list = assertSemanticWarning(3628, null, 1);
        assertContainsProblem(list, DiagnosticFactory.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS_ID);

        list = assertSemanticWarning(3689, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3767, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(3850, null, 1);
        assertContainsProblem(list, DiagnosticFactory.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS_ID);

        list = assertSemanticWarning(3924, null, 1);
        assertContainsProblem(list, DiagnosticFactory.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS_ID);

        list = assertSemanticWarning(3969, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(4013, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_DOT_WITH_VALUEB_NULL_ID);

        // the dot notation member is not found because base is a list
        list = assertSemanticWarning(4121, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    @Override
    public void testErrorExprs()
    {
        assertEquals("listBean['stringProperty']", getELText(_structuredDocument,4175));
        assertEquals("myBean.stringArrayProperty['a']", getELText(_structuredDocument,4256));
        assertEquals("beanWithListProperties.arrayListProperty['a']", getELText(_structuredDocument,4317));
        assertEquals("beanWithListProperties.arrayListProperty[true]", getELText(_structuredDocument,4392));
        assertEquals("listBean['a']", getELText(_structuredDocument,4468));
        assertEquals("listBean[true]", getELText(_structuredDocument,4511));

        List<ReportedProblem> list = assertSemanticError(4175, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(4256, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(4317, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(4392, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(4468, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(4511, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
