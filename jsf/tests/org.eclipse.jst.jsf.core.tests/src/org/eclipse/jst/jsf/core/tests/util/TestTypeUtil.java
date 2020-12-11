/*******************************************************************************
 * Copyright (c) 2007, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Reto Weiss/Axon Ivy    - Cache resolved types    
 * 
 ********************************************************************************/
package org.eclipse.jst.jsf.core.tests.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.util.JDTTypeResolver;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

import junit.framework.TestCase;

public class TestTypeUtil extends TestCase
{
  private static final IType[] EMPTY_SUPER_TYPES = new IType[0];
  
    private JDTTestEnvironment  _jdtTestEnvironment;
    private IType               _testBean1Type;
    private IType               _testBeanSubclassType;
    private IType               _testBeanGenericType;
    private IType               _testEnum1Type;
    private IType               _binaryType;

    // private Map<String, JDTBeanProperty> _properties;
    // private Map<String, JDTBeanProperty> _subClassProperties;
    // private Map<String, JDTBeanProperty> _genericTypeProperties;

    private final static String srcFolderName         = "src";
    private final static String packageName1          = "com.test";
    private final static String testBeanName1         = "TestBean1";
    private final static String testBeanSubclassName1 = "TestBean1Subclass";
    private final static String testAnotherBeanName   = "AnotherBean";
    private final static String testBeanGenericName   = "TestBeanGeneric";
    private final static String testEnumName1         = "TestEnum1";

    // private final static String testEnumName2 = "TestEnum2";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true,
                "www-proxy.us.oracle.com", "80");

        final WebProjectTestEnvironment projectTestEnvironment = new WebProjectTestEnvironment(
                "TestTypeUtil" + getClass().getName() + "_" + getName());
        projectTestEnvironment.createProject(true);

        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
                "/testfiles/TestBean1.java.data");
        String code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testBeanName1, code);

        _testBean1Type = _jdtTestEnvironment.getJavaProject().findType(
                packageName1 + "." + testBeanName1);
        assertNotNull(_testBean1Type);

        // load TestBean1Subclass
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
                "/testfiles/TestBean1Subclass.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testBeanSubclassName1, code);

        _testBeanSubclassType = _jdtTestEnvironment.getJavaProject().findType(
                packageName1 + "." + testBeanSubclassName1);
        assertNotNull(_testBeanSubclassType);
        // sanity to make sure we don't somehow accidently get the same class
        assertNotSame(_testBean1Type, _testBeanSubclassType);

        // load anotherBean
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
                "/testfiles/AnotherBean.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testAnotherBeanName, code);

        assertNotNull(_jdtTestEnvironment.getJavaProject().findType(
                packageName1 + "." + testAnotherBeanName));

        // load TestBeanGeneric
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
                "/testfiles/TestBeanGeneric.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testBeanGenericName, code);

        _testBeanGenericType = _jdtTestEnvironment.getJavaProject().findType(
                packageName1 + "." + testBeanGenericName);
        assertNotNull(_testBeanGenericType);

        // load TestEnum1
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
                "/testfiles/TestEnum1.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testEnumName1, code);

        _testEnum1Type = _jdtTestEnvironment.getJavaProject().findType(
                packageName1 + "." + testEnumName1);
        assertNotNull(_testEnum1Type);
        assertTrue(_testEnum1Type.isEnum());

        final IClasspathEntry entry = _jdtTestEnvironment.addJarClasspathEntry(
                TestsPlugin.getDefault().getBundle(),
                "/testfiles/signatures.jar");
        assertNotNull(entry);

        _binaryType = _jdtTestEnvironment.getJavaProject().findType(
                packageName1 + "." + "BinaryType");
        assertNotNull(_binaryType);
        // 7 methods plus 1 implicit constructor
        assertEquals(7+1, _binaryType.getMethods().length);

        // introspect after classes loaded to ensure all dependencies
        // are in the project
        // JDTBeanIntrospector beanIntrospector =
        // new JDTBeanIntrospector(_testBean1Type);
        //
        // _properties = beanIntrospector.getProperties();
        //
        // beanIntrospector =
        // new JDTBeanIntrospector(_testBeanSubclassType);
        //
        // _subClassProperties = beanIntrospector.getProperties();

        // beanIntrospector =
        // new JDTBeanIntrospector(_testBeanGenericType);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * only simple tests required, mainly to assert that the eraseTypeParameters
     * is defaulted to true, since the method should be fully equivalent to
     * resolveTypeSignature(type, signature, true)
     */
    // public void testResolveTypeITypeString() {
    // fail("nbla");
    // }
    public void testResolveTypeSignatureITypeString()
    {
        // this one should be the same regardless of type erasure
        assertEquals(TypeConstants.TYPE_STRING, 
                resolver(_testBean1Type).resolveEraseTypeParams("QString;"));
        assertEquals(TypeConstants.TYPE_STRING, 
                resolver(_testBean1Type).resolveKeepTypeParams("QString;"));

        // no parameters are provided here, so we should see the same result
        assertEquals(TypeConstants.TYPE_COLLECTION, 
                resolver(_testBean1Type).resolveEraseTypeParams("QCollection;"));
        assertEquals(TypeConstants.TYPE_COLLECTION, 
                resolver(_testBean1Type).resolveKeepTypeParams("QCollection;"));
        assertEquals(TypeConstants.TYPE_MAP, 
                resolver(_testBean1Type).resolveEraseTypeParams("QMap;"));
        assertEquals(TypeConstants.TYPE_MAP, 
                resolver(_testBean1Type).resolveKeepTypeParams("QMap;"));

        // in this case, the provided signature has type erasure, so the answer
        // will different depending on typeErasure flag
        final String typeSigWithErasure = resolver(_testBean1Type)
                .resolveEraseTypeParams("QMap<QString;QString;>;");
        assertEquals(TypeConstants.TYPE_MAP, typeSigWithErasure);
        final String typeSigNoErasure = resolver(_testBean1Type)
                .resolveKeepTypeParams("QMap<QString;QString;>;");
        assertEquals("Ljava.util.Map<Ljava.lang.String;Ljava.lang.String;>;",
                typeSigNoErasure);

        // test resolution of type paramaters
        final IType mapType = TypeUtil.resolveType(_jdtTestEnvironment
                .getJavaProject(), "Ljava.util.Map;");
        assertNotNull(mapType);
        assertEquals(TypeConstants.TYPE_JAVAOBJECT, 
                resolver(mapType).resolveKeepTypeParams("TV;"));

        // unfound signature
        assertEquals("QSomeNotRealClass;", 
                resolver(_testBean1Type).resolveKeepTypeParams("QSomeNotRealClass;"));

        // arrays
        assertEquals("[I", resolver(_testBean1Type).resolveEraseTypeParams("[I"));

        assertEquals("[Ljava.lang.String;", 
                resolver(_testBean1Type).resolveKeepTypeParams("[QString;"));

        assertEquals("[Ljava.util.Map;", 
                resolver(_testBean1Type).resolveKeepTypeParams("[QMap;"));

        assertEquals("[Ljava.util.Collection;", 
                resolver(_testBean1Type).resolveKeepTypeParams("[QCollection;"));

        // array of arrays
        assertEquals("[[[Ljava.lang.String;", 
                resolver(_testBean1Type).resolveKeepTypeParams("[[[QString;"));

        // cover cases where wildcards and/or capture are used. All should be
        // equivalent to the case for
        // the same signature without wildcards and capture

        // with type erasure
        runWildcardAndCapture(typeSigWithErasure, true);
        // and without type erasure
        runWildcardAndCapture(typeSigNoErasure, false);
    }

    private void runWildcardAndCapture(final String expected,
            final boolean typeErasure)
    {
        // extends
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<+QString;+QString;>;", typeErasure));
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<+QString;QString;>;", typeErasure));
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<QString;+QString;>;", typeErasure));
        // super
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<-QString;-QString;>;", typeErasure));
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<-QString;QString;>;", typeErasure));
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<QString;-QString;>;", typeErasure));
        // star
        // this one is distinct because * are converted to Object
        final String expected1 = "Ljava.util.Map"
                + (typeErasure ? ";"
                        : "<Ljava.lang.Object;Ljava.lang.Object;>;");
        assertEquals(expected1, resolveTypeSignature(_testBean1Type,
                "QMap<**>;", typeErasure));
        final String expected2 = "Ljava.util.Map"
                + (typeErasure ? ";"
                        : "<Ljava.lang.Object;Ljava.lang.String;>;");
        assertEquals(expected2, resolveTypeSignature(_testBean1Type,
                "QMap<*QString;>;", typeErasure));
        final String expected3 = "Ljava.util.Map"
                + (typeErasure ? ";"
                        : "<Ljava.lang.String;Ljava.lang.Object;>;");
        assertEquals(expected3, resolveTypeSignature(_testBean1Type,
                "QMap<QString;*>;", typeErasure));
        // capture extends
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<!+QString;!+QString;>;", typeErasure));
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<!+QString;QString;>;", typeErasure));
        assertEquals(expected, resolveTypeSignature(_testBean1Type,
                "QMap<QString;!+QString;>;", typeErasure));

        assertEquals("Ljava.lang.String;", resolveTypeSignature(
                _testBean1Type, "!+QString;", typeErasure));

        // test regression on
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=197506
        assertEquals("Ljava.lang.Object;", resolveTypeSignature(
                _testBean1Type, "*", typeErasure));
    }

    public void testCanNeverBeEqual()
    {
        // one of the arguments is the enum base
        assertFalse(TypeUtil.canNeverBeEqual(TypeConstants.TYPE_ENUM_BASE,
                "Lcom.test.SomeEnum;"));
        assertFalse(TypeUtil.canNeverBeEqual("Lcom.test.SomeEnum;",
                TypeConstants.TYPE_ENUM_BASE));

        assertTrue(TypeUtil.canNeverBeEqual("Lcom.test.SomeEnum1;",
                "Lcom.test.SomeEnum2;"));
        assertFalse(TypeUtil.canNeverBeEqual("Lcom.test.SomeEnum1;",
                "Lcom.test.SomeEnum1;"));
    }

    public void testIsEnumsCompareCompatible()
    {
        // one of the arguments is the enum base
        assertTrue(TypeUtil.isEnumsCompareCompatible(
                TypeConstants.TYPE_ENUM_BASE, "Lcom.test.SomeEnum;"));
        assertTrue(TypeUtil.isEnumsCompareCompatible("Lcom.test.SomeEnum;",
                TypeConstants.TYPE_ENUM_BASE));

        assertFalse(TypeUtil.isEnumsCompareCompatible("Lcom.test.SomeEnum1;",
                "Lcom.test.SomeEnum2;"));
        assertTrue(TypeUtil.isEnumsCompareCompatible("Lcom.test.SomeEnum1;",
                "Lcom.test.SomeEnum1;"));
    }

    // public void testResolveTypeSignatureITypeStringBoolean() {
    // fail("Not yet implemented");
    // }
    //
    public void testGetSignature()
    {
        assertEquals("Lcom.test.TestBean1;", TypeUtil
                .getSignature(_testBean1Type));
    }

    public void testResolveMethodSignature() throws Exception
    {
        assertEquals("()Ljava.lang.String;", 
                resolver(_testBean1Type).resolveMethodEraseTypeParams("()QString;"));
        assertEquals("(Ljava.lang.String;)V", 
                resolver(_testBean1Type).resolveMethodEraseTypeParams("(QString;)V"));
        assertEquals("(Ljava.lang.String;Z)V", 
                resolver(_testBean1Type).resolveMethodEraseTypeParams("(QString;Z)V"));

        IMethod method = _testBean1Type.getMethod("getStringProp1", null);
        assertEquals("()Ljava.lang.String;", 
                resolver(_testBean1Type).resolveMethodEraseTypeParams(method.getSignature()));

        method = _testBean1Type.getMethod("setStringProperty2", new String[]
        { "I" });
        assertEquals("(I)V", 
                resolver(_testBean1Type).resolveMethodEraseTypeParams(method.getSignature()));

        // test binary methods
        // the framework returns method signatures using "/" instead of "."
        // separators when the IType is BinaryType

        // sanity: _binaryType must a JDT BinaryType impl for this test to
        // be valid
        assertTrue(_binaryType.isBinary());
        assertEquals("()Ljava.lang.String;", resolver(_testBean1Type).resolveMethodEraseTypeParams(
                _binaryType.getMethod("getStringProperty", null)
                        .getSignature()));

        assertEquals("(Ljava.lang.String;)V", resolver(_testBean1Type).resolveMethodEraseTypeParams(
                _binaryType.getMethod("setStringProperty",
                        new String[]
                        { "Ljava.lang.String;" }).getSignature()));

        assertEquals("()I", resolver(_testBean1Type).resolveMethodEraseTypeParams(
                _binaryType.getMethod("getIntegerProperty", null)
                        .getSignature()));

        assertEquals("(I)V", resolver(_testBean1Type).resolveMethodEraseTypeParams(
                _binaryType.getMethod("setIntegerProperty", new String[]
                { "I" }).getSignature()));

        assertEquals("()Lcom.test.BinaryPropertyAndMethodType;", 
                resolver(_testBean1Type).resolveMethodEraseTypeParams(_binaryType.getMethod(
                        "getUserDefined", null).getSignature()));

        assertEquals("(Lcom.test.BinaryPropertyAndMethodType;)V", 
                resolver(_testBean1Type).resolveMethodEraseTypeParams(_binaryType.getMethod(
                        "setUserDefined", new String[]
                        { "Lcom.test.BinaryPropertyAndMethodType;" })
                        .getSignature()));

        assertEquals(
                "(Ljava.lang.String;Lcom.test.BinaryPropertyAndMethodType;I)I",
                resolver(_testBean1Type).resolveMethodEraseTypeParams(
                                _binaryType
                                        .getMethod(
                                                "methodWithMultipleArgs",
                                                new String[]
                                                {
                                                        "Ljava.lang.String;",
                                                        "Lcom.test.BinaryPropertyAndMethodType;",
                                                        "I" }).getSignature()));
    }

    // public void testGetFullyQualifiedName() {
    // fail("Not yet implemented");
    // }
    //
    public void testResolveTypeIJavaProjectString()
    {
        assertNotNull(TypeUtil.resolveType(
                _jdtTestEnvironment.getJavaProject(), "Ljava.lang.String;"));
        assertEquals(_testBean1Type, TypeUtil.resolveType(_jdtTestEnvironment
                .getJavaProject(), "Lcom.test.TestBean1;"));
    }

    public void testMatchTypeParameterToArgument() throws Exception
    {
        final IType mapType = TypeUtil.resolveType(_jdtTestEnvironment
                .getJavaProject(), "Ljava.util.Map;");
        assertNotNull(mapType);

        final List<String> args = new ArrayList<String>();
        args.add("Ljava.lang.String;");
        args.add("Lcom.test.TestBean1;");

        // TestBean1 is the "V" in Map<K,V>
        assertEquals("Lcom.test.TestBean1;", TypeUtil
                .matchTypeParameterToArgument(mapType, "TV;", args));

        // there is no "Q" type arg
        assertNull(TypeUtil.matchTypeParameterToArgument(mapType, "TQ;", args));
        // there is no "garbonzo" type arg
        assertNull(TypeUtil.matchTypeParameterToArgument(mapType, "Tgarbonzo;",
                args));
    }

    public void testIsEnumMember() throws Exception
    {
        assertTrue(TypeUtil.isEnumMember(_testEnum1Type, "red"));
        assertTrue(TypeUtil.isEnumMember(_testEnum1Type, "blue"));
        assertTrue(TypeUtil.isEnumMember(_testEnum1Type, "green"));
        assertTrue(TypeUtil.isEnumMember(_testEnum1Type, "yellow"));
        assertTrue(TypeUtil.isEnumMember(_testEnum1Type, "purple"));
        assertTrue(TypeUtil.isEnumMember(_testEnum1Type, "orange"));

        assertFalse(TypeUtil.isEnumMember(_testEnum1Type, "mauve"));
        assertFalse(TypeUtil.isEnumMember(_testEnum1Type, "pink"));

        // test the enum base type.. all things may be members of it
        final IType type = _jdtTestEnvironment.getJavaProject().findType(
                "java.lang.Enum");
        assertNotNull(type);
        // assertTrue(type.isEnum());
        assertTrue(TypeUtil.isEnumMember(type, "red"));
        assertTrue(TypeUtil.isEnumMember(type, "pink"));
        assertTrue(TypeUtil.isEnumMember(type, "anything"));
        assertTrue(TypeUtil.isEnumMember(type, "deadbeef"));

    }
        
    String resolveTypeSignature(IType type, String typeSignature, boolean erased)
    {
      if (erased)
      {
        return new JDTTypeResolver(type, EMPTY_SUPER_TYPES).resolveEraseTypeParams(typeSignature);
      }
      else
      {
        return new JDTTypeResolver(type, EMPTY_SUPER_TYPES).resolveKeepTypeParams(typeSignature);
      }
    }
    
    JDTTypeResolver resolver(IType type)
    {
      return new JDTTypeResolver(type, EMPTY_SUPER_TYPES);
    }
}
