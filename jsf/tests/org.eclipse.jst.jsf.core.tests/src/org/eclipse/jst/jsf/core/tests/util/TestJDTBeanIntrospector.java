package org.eclipse.jst.jsf.core.tests.util;

import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.provisional.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.core.internal.provisional.util.JDTBeanProperty;
import org.eclipse.jst.jsf.core.internal.provisional.util.JDTBeanPropertyWorkingCopy;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JavaCodeResource;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;

/**
 * Tester fot he JDTBeanIntrospector class
 * @author cbateman
 *
 */
public class TestJDTBeanIntrospector extends TestCase 
{
    private JDTTestEnvironment  _jdtTestEnvironment;
    private IType               _testBean1Type;
    private IType               _testBeanSubclassType;
    private Map                 _properties;
    private Map                 _subClassProperties;

    private final static String srcFolderName = "src";
    private final static String packageName1 = "com.test";
    private final static String testBeanName1 = "TestBean1";
    private final static String testBeanSubclassName1 = "TestBean1Subclass";
    private final static String testAnotherBeanName = "AnotherBean";

    
    protected void setUp() throws Exception {
        super.setUp();
        final ProjectTestEnvironment  projectTestEnvironment = 
            new ProjectTestEnvironment("TestJDTBeanIntrospectorProject");
        projectTestEnvironment.createProject();
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
        JavaCodeResource codeRes = new JavaCodeResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/TestBean1.java.data");
        String code = codeRes.getCode();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testBeanName1, code);
        
        _testBean1Type = _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testBeanName1);
        assertNotNull(_testBean1Type);
        
        // load TestBean1Subclass
        codeRes = new JavaCodeResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/TestBean1Subclass.java.data");
        code = codeRes.getCode();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testBeanSubclassName1, code);

        _testBeanSubclassType = _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testBeanSubclassName1);
        assertNotNull(_testBeanSubclassType);
        // sanity to make sure we don't somehow accidently get the same class
        assertNotSame(_testBean1Type, _testBeanSubclassType);
        
        // load anotherBean
        codeRes = new JavaCodeResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/AnotherBean.java.data");
        code = codeRes.getCode();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testAnotherBeanName, code);
        
        assertNotNull(_jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testAnotherBeanName));
        
        // introspect after classes loaded to ensure all dependencies
        // are in the project
        JDTBeanIntrospector  beanIntrospector = 
            new JDTBeanIntrospector(_testBean1Type);
        
        _properties = beanIntrospector.getProperties();
        
        beanIntrospector = 
            new JDTBeanIntrospector(_testBeanSubclassType);
        
        _subClassProperties = beanIntrospector.getProperties();
    }

    /**
     * Basic high-level sanity check on the generate properties map
     */
    public void testMapSanity()
    {
        checkMapSanity(_properties, 12);
        // sub class an locally defined property in addition to what
        // is inherited
        checkMapSanity(_subClassProperties, 13);
    }

    private void checkMapSanity(Map properties, int numProps)
    {
        assertEquals("Check extra or missing properties",properties.size(), numProps);
        assertNull("Empty string is invalid property name", properties.get(""));
        assertNull("Null is not a valid property name", properties.get(null));
        
        // ensure type correctness of all values
        for (final Iterator it = properties.values().iterator(); it.hasNext();)
        {
            Object value = it.next();
            assertTrue(value instanceof JDTBeanProperty);
            // no working copies should slip their way in
            assertFalse(value instanceof JDTBeanPropertyWorkingCopy);
        }
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
    
    private void testStringProp1(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("stringProp1");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String", 
                        "Ljava.lang.String;", property.getTypeSignature());
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
    
    private void testBooleanIsProp1(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("booleanIsProp1");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeSignature());
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
    
    private void testBooleanIsProp2(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("booleanIsProp2");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeSignature());
        assertNull("IType won't resolve", property.getType());
        assertEquals("Make sure the is getter is chosen", "isBooleanIsProp2", property.getGetter().getElementName());
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
    private void testNotBooleanIsProp1(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("notBooleanIsProp1");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeSignature());
        assertNull("IType won't resolve", property.getType());
        assertEquals("Make sure the get getter is chosen", "getNotBooleanIsProp1", property.getGetter().getElementName());
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
    private void testStringProperty2(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("stringProperty2");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a String", 
                        "Ljava.lang.String;", property.getTypeSignature());
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
    private void testReadonlyStringProperty(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("readonlyStringProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a String", 
                        "Ljava.lang.String;", property.getTypeSignature());
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
    private void testReadonlyBooleanProperty(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("readonlyBooleanProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeSignature());
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
    private void testWriteonlyStringProperty(Map properties)
    {
        JDTBeanProperty  property = (JDTBeanProperty) properties.get("writeonlyStringProperty");
        assertNotNull(property);
        
        assertFalse("No getter for this property", property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String", 
                "Ljava.lang.String;", property.getTypeSignature());
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
    private void testStringArrayProperty(Map properties)
    {
        JDTBeanProperty property = (JDTBeanProperty) properties.get("stringArrayProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String[]", 
                "[Ljava.lang.String;", property.getTypeSignature());
        assertNull("No IType for arrays", property.getType());
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
    private void testCollectionProperty(Map properties)
    {
        JDTBeanProperty property = (JDTBeanProperty) properties.get("collectionProperty");
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
    private void testMapProperty(Map properties)
    {
        JDTBeanProperty property = (JDTBeanProperty) properties.get("mapProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a Map", 
                "Ljava.util.Map;", property.getTypeSignature());
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

    private void testAnotherBean(Map properties)
    {
        JDTBeanProperty property = (JDTBeanProperty) properties.get("anotherBean");
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
        JDTBeanProperty property = (JDTBeanProperty) _subClassProperties.get(inheritedPropertyName);
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        // no setter
        assertFalse(property.isWritable());
        assertEquals("Signature must be for a String", 
                "Ljava.lang.String;", property.getTypeSignature());
        assertNotNull("Should have a type", property.getType());

    }
}
