/*******************************************************************************
 * Copyright (c) 2006, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
*     Reto Weiss/Axon Ivy    - Cache resolved types 
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.core.tests.util;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

import junit.framework.TestCase;

/**
 * Tester fot he JDTBeanIntrospector class
 * 
 * @author cbateman
 * 
 */
public class TestJDTBeanIntrospector extends TestCase
{
    // TODO: test the getAllMethods. also ensure that we get coverage of the
    // case
    // where a type has no supers
    private JDTTestEnvironment           _jdtTestEnvironment;
    private IType                        _testBean1Type;
    private IType                        _testBeanSubclassType;
    private IType                        _testBeanGenericType;
    private IType                        _testInterface;
    private Map<String, JDTBeanProperty> _properties;
    private Map<String, JDTBeanProperty> _subClassProperties;
    private Map<String, JDTBeanProperty> _genericTypeProperties;
    private Map<String, JDTBeanProperty> _interfaceProperties;

    private final static String          srcFolderName         = "src";
    private final static String          packageName1          = "com.test";
    private final static String          testBeanName1         = "TestBean1";
    private final static String          testBeanSubclassName1 =
        "TestBean1Subclass";
    private final static String          testAnotherBeanName   = "AnotherBean";
    private final static String          testBeanGenericName   =
        "TestBeanGeneric";
    private final static String          testBeanInterface = "IBeanInterface";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true,
                "www-proxy.us.oracle.com", "80");

        final WebProjectTestEnvironment projectTestEnvironment =
            new WebProjectTestEnvironment("TestJDTBeanIntrospector_"
                    + getClass().getName() + "_" + getName());
        projectTestEnvironment.createProject(true);

        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
        "/testfiles/TestBean1.java.data");
        String code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testBeanName1, code);

        _testBean1Type =
            _jdtTestEnvironment.getJavaProject().findType(
                    packageName1 + "." + testBeanName1);
        assertNotNull(_testBean1Type);

        // load TestBean1Subclass
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
        "/testfiles/TestBean1Subclass.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testBeanSubclassName1, code);

        _testBeanSubclassType =
            _jdtTestEnvironment.getJavaProject().findType(
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

        _testBeanGenericType =
            _jdtTestEnvironment.getJavaProject().findType(
                    packageName1 + "." + testBeanGenericName);
        assertNotNull(_testBeanGenericType);

        // load IBeanInterface
        codeRes = new TestFileResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(),
                "/testfiles/IBeanInterface.java.data");
        code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1,
                testBeanInterface, code);

        _testInterface = _jdtTestEnvironment.getJavaProject().findType(
                packageName1 + "." + testBeanInterface);
        assertNotNull(_testInterface);
        assertTrue(_testInterface.exists());
        
        // introspect after classes loaded to ensure all dependencies
        // are in the project
        JDTBeanIntrospector beanIntrospector = JDTBeanIntrospector.forType(_testBean1Type);
        _properties = beanIntrospector.getProperties();

        beanIntrospector = JDTBeanIntrospector.forType(_testBeanSubclassType);
        _subClassProperties = beanIntrospector.getProperties();

        beanIntrospector = JDTBeanIntrospector.forType(_testBeanGenericType);
        _genericTypeProperties = beanIntrospector.getProperties();

        beanIntrospector = JDTBeanIntrospector.forType(_testInterface);
        _interfaceProperties = beanIntrospector.getProperties();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        final IProject project = _jdtTestEnvironment.getJavaProject().getProject();

        try
        {
            project.close(null);
            project.delete(true, null);
        }
        catch (final CoreException ce)
        {
            ce.printStackTrace(System.err);
        }
    }

    /**
     * Basic high-level sanity check on the generate properties map
     */
    public void testMapSanity()
    {
        final int NUM_PROPS = 15;

        checkMapSanity(_properties, NUM_PROPS);
        // sub class an locally defined property in addition to what
        // is inherited
        checkMapSanity(_subClassProperties, NUM_PROPS + 1);

        checkMapSanity(_genericTypeProperties, 6);
    }

    private void checkMapSanity(final Map<String, JDTBeanProperty> properties,
            final int numProps)
    {
        assertEquals("Check extra or missing properties", numProps, properties
                .size());
        assertNull("Empty string is invalid property name", properties.get(""));
        assertNull("Null is not a valid property name", properties.get(null));
    }

    /**
     * 
     */
    public void testStringProp1()
    {
        testStringProp1(_properties);
    }

    /**
     * Test the inherited stringProp1 property
     */
    public void testStringProp1SubClass()
    {
        testStringProp1(_subClassProperties);
    }

    private void testStringProp1(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("stringProp1");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String", "Ljava.lang.String;",
                property.getTypeSignature());
        assertNotNull("IType should resolve", property.getType());
    }

    /**
     * 
     */
    public void testBooleanIsProp1()
    {
        testBooleanIsProp1(_properties);
    }

    /**
     * Test inherited property
     */
    public void testBooleanIsProp1SubClass()
    {
        testBooleanIsProp1(_subClassProperties);
    }

    private void testBooleanIsProp1(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("booleanIsProp1");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", Signature.SIG_BOOLEAN,
                property.getTypeSignature());
        assertNull("IType won't resolve", property.getType());
    }

    /**
     * 
     */
    public void testBooleanIsProp2()
    {
        testBooleanIsProp2(_properties);
    }

    /**
     * Test inherited property
     */
    public void testBooleanIsProp2SubClass()
    {
        testBooleanIsProp2(_subClassProperties);
    }

    private void testBooleanIsProp2(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("booleanIsProp2");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", Signature.SIG_BOOLEAN,
                property.getTypeSignature());
        assertNull("IType won't resolve", property.getType());
        assertEquals("Make sure the is getter is chosen", "isBooleanIsProp2",
                property.getGetter().getElementName());
    }

    /**
     * 
     */
    public void testNotBooleanIsProp1()
    {
        testNotBooleanIsProp1(_properties);
    }

    /**
     * test inherited
     */
    public void testNotBooleanIsProp1SubClass()
    {
        testNotBooleanIsProp1(_subClassProperties);
    }

    /**
     * 
     */
    private void testNotBooleanIsProp1(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("notBooleanIsProp1");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", Signature.SIG_BOOLEAN,
                property.getTypeSignature());
        assertNull("IType won't resolve", property.getType());
        assertEquals("Make sure the get getter is chosen",
                "getNotBooleanIsProp1", property.getGetter().getElementName());
    }

    /**
     * test property
     */
    public void testStringProperty2()
    {
        testStringProperty2(_properties);
    }

    /**
     * test inherited
     */
    public void testStringProperty2SubClass()
    {
        testStringProperty2(_subClassProperties);
    }

    /**
     * 
     */
    private void testStringProperty2(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("stringProperty2");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a String", "Ljava.lang.String;",
                property.getTypeSignature());
        assertNotNull("IType should resolve", property.getType());
    }

    /**
     * 
     */
    public void testReadonlyStringProperty()
    {
        testReadonlyStringProperty(_properties);
    }

    /**
     * inherited
     */
    public void testReadonlyStringPropertySubClass()
    {
        testReadonlyStringProperty(_subClassProperties);
    }

    /**
     * 
     */
    private void testReadonlyStringProperty(
            final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("readonlyStringProperty");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a String", "Ljava.lang.String;",
                property.getTypeSignature());
        assertNotNull("IType should resolve", property.getType());
    }

    /**
     * test property
     */
    public void testReadonlyBooleanProperty()
    {
        testReadonlyBooleanProperty(_properties);
    }

    /**
     * test inherited property
     */
    public void testReadonlyBooleanPropertySubClass()
    {
        testReadonlyBooleanProperty(_subClassProperties);
    }

    /**
     * 
     */
    private void testReadonlyBooleanProperty(
            final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("readonlyBooleanProperty");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a boolean", Signature.SIG_BOOLEAN,
                property.getTypeSignature());
        assertNull("IType won't resolve", property.getType());
    }

    /**
     * 
     */
    public void testWriteonlyStringProperty()
    {
        testWriteonlyStringProperty(_properties);
    }

    /**
     * test inherited
     */
    public void testWriteonlyStringPropertySubClass()
    {
        testWriteonlyStringProperty(_subClassProperties);
    }

    /**
     * 
     */
    private void testWriteonlyStringProperty(
            final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("writeonlyStringProperty");
        assertNotNull(property);

        assertFalse("No getter for this property", property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String", "Ljava.lang.String;",
                property.getTypeSignature());
        assertNotNull("IType should resolve", property.getType());
    }

    /**
     * test property
     */
    public void testStringArrayProperty()
    {
        testStringArrayProperty(_properties);
    }

    /**
     * test inherited
     */
    public void testStringArrayPropertySubClass()
    {
        testStringArrayProperty(_subClassProperties);
    }

    /**
     * 
     */
    private void testStringArrayProperty(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("stringArrayProperty");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String[]", "[Ljava.lang.String;",
                property.getTypeSignature());
        assertNotNull("Should resolve the base type", property.getType());
        assertEquals(1, property.getArrayCount());
    }

    /**
     * 
     */
    public void testCollectionProperty()
    {
        testCollectionProperty(_properties);
    }

    /**
     * test inherited
     */
    public void testCollectionPropertySubClass()
    {
        testCollectionProperty(_subClassProperties);
    }

    /**
     * 
     */
    private void testCollectionProperty(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("collectionProperty");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a Collection",
                "Ljava.util.Collection;", property.getTypeSignature());
        assertNotNull("Should have a type", property.getType());
    }

    /**
     * 
     */
    public void testMapProperty()
    {
        testMapProperty(_properties);
    }

    /**
     * 
     */
    public void testMapPropertySubClass()
    {
        testMapProperty(_subClassProperties);
    }

    /**
     * 
     */
    private void testMapProperty(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("mapProperty");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a Map", "Ljava.util.Map;", property
                .getTypeSignature());
        assertNotNull("Should have a type", property.getType());
    }

    /**
     * test a locally defined bean type
     */
    public void testAnotherBean()
    {
        testAnotherBean(_properties);
    }

    /**
     * test inherited
     */
    public void testAnotherBeanSubClass()
    {
        testAnotherBean(_subClassProperties);
    }

    private void testAnotherBean(final Map<String, JDTBeanProperty> properties)
    {
        final JDTBeanProperty property = properties.get("anotherBean");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for com.test.AnotherBean",
                "Lcom.test.AnotherBean;", property.getTypeSignature());
        assertNotNull("Should have a type", property.getType());
    }

    /**
     * Test a property that's in the sub-class but not the parent
     */
    public void testSubclassOnly()
    {
        final String inheritedPropertyName = "locallyDefinedProperty";

        // ensure we didn't some how put an inherited property into the
        // parent
        assertNull(_properties.get(inheritedPropertyName));
        final JDTBeanProperty property =
            _subClassProperties.get(inheritedPropertyName);
        assertNotNull(property);

        assertTrue(property.isReadable());
        // no setter
        assertFalse(property.isWritable());
        assertEquals("Signature must be for a String", "Ljava.lang.String;",
                property.getTypeSignature());
        assertNotNull("Should have a type", property.getType());

    }

    public void testGenericListOfStringProperty() throws Exception
    {
        final JDTBeanProperty property = _genericTypeProperties.get("listOfStrings");
        assertEquals(TypeConstants.TYPE_LIST, property.getTypeSignature());

        assertEquals(1, property.getTypeParameterSignatures().size());
        assertEquals(TypeConstants.TYPE_STRING, property
                .getTypeParameterSignatures().get(0));
    }

    public void testGenericArrayListOfStringProperty() throws Exception
    {
        final JDTBeanProperty property =
            _genericTypeProperties.get("arrayListOfStrings");
        assertEquals("Ljava.util.ArrayList;", property.getTypeSignature());

        assertEquals(1, property.getTypeParameterSignatures().size());
        assertEquals(TypeConstants.TYPE_STRING, property
                .getTypeParameterSignatures().get(0));
    }

    public void testGenericListOfListOfStringProperty() throws Exception
    {
        final JDTBeanProperty property =
            _genericTypeProperties.get("listOfListOfStrings");
        assertEquals("Ljava.util.List;", property.getTypeSignature());

        assertEquals(1, property.getTypeParameterSignatures().size());
        assertEquals("Ljava.util.List<Ljava.lang.String;>;", property
                .getTypeParameterSignatures().get(0));
    }

    public void testGenericMapOfString_StringProperty() throws Exception
    {
        final JDTBeanProperty property =
            _genericTypeProperties.get("mapOfString_String");
        assertEquals("Ljava.util.Map;", property.getTypeSignature());

        assertEquals(2, property.getTypeParameterSignatures().size());
        assertEquals("Ljava.lang.String;", property
                .getTypeParameterSignatures().get(0));
        assertEquals("Ljava.lang.String;", property
                .getTypeParameterSignatures().get(1));
    }

    // test regression of https://bugs.eclipse.org/bugs/show_bug.cgi?id=197506
    public void testUnboundedProperty_List() throws Exception
    {
        final JDTBeanProperty property = _genericTypeProperties.get("unboundedList");
        assertEquals("Ljava.util.List;", property.getTypeSignature());

        assertEquals(1, property.getTypeParameterSignatures().size());
        assertEquals("Ljava.lang.Object;", property
                .getTypeParameterSignatures().get(0));
    }
    
    public void testPropertyInheritedFromInterface() throws Exception
    {
        final JDTBeanProperty property = _interfaceProperties.get("inheritedThroughInterface");
        assertNotNull(property);

        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be int", Signature.SIG_INT,
                property.getTypeSignature());
        assertNull("IType won't resolve", property.getType());
    }
}
