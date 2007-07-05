/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.core.tests.util;

import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestTypeUtil extends TestCase 
{
    private JDTTestEnvironment                      _jdtTestEnvironment;
    private IType                                   _testBean1Type;
    private IType                                   _testBeanSubclassType;
    private IType                                   _testBeanGenericType;
    private Map<String, JDTBeanProperty>            _properties;
    private Map<String, JDTBeanProperty>            _subClassProperties;
    private Map<String, JDTBeanProperty>            _genericTypeProperties;

    private final static String srcFolderName = "src";
    private final static String packageName1 = "com.test";
    private final static String testBeanName1 = "TestBean1";
    private final static String testBeanSubclassName1 = "TestBean1Subclass";
    private final static String testAnotherBeanName = "AnotherBean";
    private final static String testBeanGenericName = "TestBeanGeneric";

    protected void setUp() throws Exception {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
        
        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestJDTBeanIntrospectorProject");
        projectTestEnvironment.createProject(true);
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/TestBean1.java.data");
        String code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testBeanName1, code);
        
        _testBean1Type = _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testBeanName1);
        assertNotNull(_testBean1Type);
        
        // load TestBean1Subclass
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/TestBean1Subclass.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testBeanSubclassName1, code);

        _testBeanSubclassType = _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testBeanSubclassName1);
        assertNotNull(_testBeanSubclassType);
        // sanity to make sure we don't somehow accidently get the same class
        assertNotSame(_testBean1Type, _testBeanSubclassType);
        
        // load anotherBean
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/AnotherBean.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testAnotherBeanName, code);
        
        assertNotNull(_jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testAnotherBeanName));
        
        // load TestBeanGeneric
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/TestBeanGeneric.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testBeanGenericName, code);

        _testBeanGenericType = _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testBeanGenericName); 
        assertNotNull(_testBeanGenericType);
        
        // introspect after classes loaded to ensure all dependencies
        // are in the project
        JDTBeanIntrospector  beanIntrospector = 
            new JDTBeanIntrospector(_testBean1Type);
        
        _properties = beanIntrospector.getProperties();
        
        beanIntrospector = 
            new JDTBeanIntrospector(_testBeanSubclassType);
        
        _subClassProperties = beanIntrospector.getProperties();
        
        beanIntrospector = 
            new JDTBeanIntrospector(_testBeanGenericType);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * only simple tests required, mainly to assert that the
     * eraseTypeParameters is defaulted to true, since the method
     * should be fully equivalent to resolveTypeSignature(type, signature, true)
     */
//    public void testResolveTypeITypeString() {
//        fail("nbla");
//    }

    public void testResolveTypeSignatureITypeString() 
    {
        // this one should be the same regardless of type erasure
        assertEquals(TypeConstants.TYPE_STRING, TypeUtil.resolveTypeSignature(_testBean1Type, "QString;", true));
        assertEquals(TypeConstants.TYPE_STRING, TypeUtil.resolveTypeSignature(_testBean1Type, "QString;", false));

        // no parameters are provided here, so we should see the same result
        assertEquals(TypeConstants.TYPE_COLLECTION, TypeUtil.resolveTypeSignature(_testBean1Type, "QCollection;", true));
        assertEquals(TypeConstants.TYPE_COLLECTION, TypeUtil.resolveTypeSignature(_testBean1Type, "QCollection;", false));
        assertEquals(TypeConstants.TYPE_MAP, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap;", true));
        assertEquals(TypeConstants.TYPE_MAP, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap;", false));
        
        // in this case, the provided signature has type erasure, so the answer will different depending on typeErasure flag
        final String typeSigWithErasure = TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<QString;QString;>;", true);        
        assertEquals(TypeConstants.TYPE_MAP, typeSigWithErasure);
        final String typeSigNoErasure = TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<QString;QString;>;", false);
        assertEquals("Ljava.util.Map<Ljava.lang.String;Ljava.lang.String;>;", typeSigNoErasure);
        
        // cover cases where wildcards and/or capture are used.  All should be equivalent to the case for
        // the same signature without wildcards and capture
        
        // with type erasure
        runWildcardAndCapture(typeSigWithErasure, true);
        // and without type erasure
        runWildcardAndCapture(typeSigNoErasure, false);
    }

    private void runWildcardAndCapture(final String expected, boolean typeErasure)
    {
        // extends
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<+QString;+QString;>;", typeErasure));
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<+QString;QString;>;", typeErasure));
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<QString;+QString;>;", typeErasure));
            //super
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<-QString;-QString;>;", typeErasure));
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<-QString;QString;>;", typeErasure));
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<QString;-QString;>;", typeErasure));
            // star
        // this one is distinct because * are converted to Object
        final String expected1 = "Ljava.util.Map" + (typeErasure ? ";" : "<Ljava.lang.Object;Ljava.lang.Object;>;"); 
        assertEquals(expected1, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<**>;", typeErasure));
        final String expected2 = "Ljava.util.Map" + (typeErasure ? ";" : "<Ljava.lang.Object;Ljava.lang.String;>;");
        assertEquals(expected2, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<*QString;>;", typeErasure));
        final String expected3 = "Ljava.util.Map" + (typeErasure ? ";" : "<Ljava.lang.String;Ljava.lang.Object;>;"); 
        assertEquals(expected3, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<QString;*>;", typeErasure));
            // capture extends
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<!+QString;!+QString;>;", typeErasure));
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<!+QString;QString;>;", typeErasure));
        assertEquals(expected, TypeUtil.resolveTypeSignature(_testBean1Type, "QMap<QString;!+QString;>;", typeErasure));
    }
    
//    public void testResolveTypeSignatureITypeStringBoolean() {
//        fail("Not yet implemented");
//    }
//
//    public void testGetSignature() {
//        fail("Not yet implemented");
//    }
//
//    public void testResolveMethodSignature() {
//        fail("Not yet implemented");
//    }
//
//    public void testGetFullyQualifiedName() {
//        fail("Not yet implemented");
//    }
//
//    public void testResolveTypeIJavaProjectString() {
//        fail("Not yet implemented");
//    }
//
//    public void testMatchTypeParameterToArgument() {
//        fail("Not yet implemented");
//    }

}
