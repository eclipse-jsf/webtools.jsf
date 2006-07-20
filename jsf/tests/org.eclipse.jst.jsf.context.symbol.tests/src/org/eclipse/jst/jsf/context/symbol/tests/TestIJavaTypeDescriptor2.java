package org.eclipse.jst.jsf.context.symbol.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JavaCodeResource;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;
import org.osgi.framework.Bundle;

/**
 * Test the IJavaTypeDescriptor
 * 
 * @author cbateman
 *
 */
public class TestIJavaTypeDescriptor2 extends TestCase 
{
    private JDTTestEnvironment      _jdtTestEnvironment;
    private Map                     _beanProperties;
    private Map                     _beanSubclassProperties;
    private Map                     _beanMethods;
    private Map                     _beanMethodsSubclass;     
    
    private IBeanInstanceSymbol     _testBean1Symbol;
    private IBeanInstanceSymbol     _testBean1SubclassSymbol;
    private IBeanInstanceSymbol     _testBean2Symbol;
    private IBeanInstanceSymbol     _testBean2SubclassSymbol;
    
    private final static String srcFolderName = "src";
    private final static String packageName1 = "com.test";
    private final static String testBeanName1 = "TestBean1";
    private final static String testBeanSubclass1 = "TestBean1Subclass";
    private final static String testBeanName2 = "TestBean2";
    private final static String testBean2Subclass = "TestBean2Subclass";

    private final static String overloadedMethodName = "overloadedMethod";
    
    protected void setUp() throws Exception 
    {
        super.setUp();
        final ProjectTestEnvironment  projectTestEnvironment = 
            new ProjectTestEnvironment("TestJDTBeanIntrospectorProject");
        projectTestEnvironment.createProject();
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);

        // load ITestBean2 first due to later dependencies
        loadSourceClass(ContextSymbolTestPlugin.getDefault().getBundle(), "/testdata/ITestBean2.java.data", "ITestBean2");
        assertNotNull(_jdtTestEnvironment.getJavaProject().findType(packageName1+"."+"ITestBean2"));
        
        // load another bean first since others have a dependency on on it
        loadSourceClass(TestsPlugin.getDefault().getBundle(), "/testfiles/AnotherBean.java.data", "AnotherBean");
        assertNotNull(_jdtTestEnvironment.getJavaProject().findType(packageName1+"."+"AnotherBean"));
        
        _beanProperties = new HashMap();
        _testBean1Symbol =
            setupBeanProperty("/testfiles/TestBean1.java.data", testBeanName1, _beanProperties);
        
        _beanSubclassProperties = new HashMap();
        _testBean1SubclassSymbol =
            setupBeanProperty("/testfiles/TestBean1Subclass.java.data", testBeanSubclass1, _beanSubclassProperties);

        _beanMethods = new HashMap();
        _testBean2Symbol = 
            setupBeanMethods("/testdata/TestBean2.java.data", testBeanName2, _beanMethods);

        _beanMethodsSubclass = new HashMap();
        _testBean2SubclassSymbol = 
            setupBeanMethods("/testdata/TestBean2Subclass.java.data", testBean2Subclass, _beanMethodsSubclass);
    }
    

    private IBeanInstanceSymbol setupBeanProperty(String fileName, String beanClassName, Map properties) throws Exception
    {
        loadSourceClass(TestsPlugin.getDefault().getBundle(), fileName, beanClassName);
        
        final IType testBean1Type = 
            _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+beanClassName);
        assertNotNull(testBean1Type);
        
        final IJavaTypeDescriptor2 testBeanDescriptor = 
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        testBeanDescriptor.setType(testBean1Type);
        
        IBeanInstanceSymbol  bean = 
            SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        bean.setTypeDescriptor(testBeanDescriptor);
        bean.setName(beanClassName);
        List propertyList = bean.getProperties();
        for(final Iterator it = propertyList.iterator(); it.hasNext();)
        {
            final IBeanPropertySymbol  property = 
                (IBeanPropertySymbol) it.next();
            properties.put(property.getName(), property);
        }
        
        return bean;
    }
    
    private void loadSourceClass(final Bundle bundle, final String fileName, final String beanClassName) throws Exception
    {
        JavaCodeResource codeRes = new JavaCodeResource();
        codeRes.load(bundle, fileName);
        String code = codeRes.getCode();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, beanClassName, code);
    }
    
    private IBeanInstanceSymbol setupBeanMethods(String fileName, String beanClassName, Map methods) throws Exception
    {
        loadSourceClass(ContextSymbolTestPlugin.getDefault().getBundle(), fileName, beanClassName);

        final IType testBean1Type = 
            _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+beanClassName);
        assertNotNull(testBean1Type);
        
        final IJavaTypeDescriptor2 testBeanDescriptor = 
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        testBeanDescriptor.setType(testBean1Type);
        
        IBeanInstanceSymbol  bean = 
            SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        bean.setTypeDescriptor(testBeanDescriptor);
        bean.setName(beanClassName);
        List methodList = bean.getMethods();
        for(final Iterator it = methodList.iterator(); it.hasNext();)
        {
            final IBeanMethodSymbol  method = (IBeanMethodSymbol) it.next();

            // exclude the overloaded methods as these are handled separately
            if (!overloadedMethodName.equals(method.getName()))
            {
                methods.put(method.getName(), method);
            }
        }
        
        return bean;
    }
    
    /**
     * Basic high-level sanity check on the generate properties map
     */
    public void testMapSanity()
    {
        assertEquals("Check extra or missing properties",_beanProperties.size(), 12);
        assertEquals("Check extra or missing properties",_beanSubclassProperties.size(), 13);
        assertEquals("Check extra or missing methods",_beanMethods.size(), 5);
        assertEquals("Check extra or missing methods",_beanMethodsSubclass.size(), 6);
    }
    
    /**
     * 
     */
    public void testStringProp1()
    {
        testStringProp1(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testStringProp1SubClass()
    {
        testStringProp1(_beanSubclassProperties);
    }
    
    /**
     * @param properties
     */
    private void testStringProp1(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("stringProp1");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String", 
                        "Ljava.lang.String;", property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * test property
     */
    public void testBooleanIsProp1()
    {
        testBooleanIsProp1(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testBooleanIsProp1SubClass()
    {
        testBooleanIsProp1(_beanSubclassProperties);
    }

    
    /**
     * @param properties
     */
    private void testBooleanIsProp1(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("booleanIsProp1");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * 
     */
    public void testBooleanIsProp2()
    {
        testBooleanIsProp2(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testBooleanIsProp2SubClass()
    {
        testBooleanIsProp2(_beanSubclassProperties);
    }
    
    /**
     * @param properties
     */
    private void testBooleanIsProp2(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("booleanIsProp2");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * 
     */
    public void testNotBooleanIsProp1()
    {
        testNotBooleanIsProp1(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testNotBooleanIsProp1SubClass()
    {
        testNotBooleanIsProp1(_beanSubclassProperties);
    }

    /**
     * 
     */
    private void testNotBooleanIsProp1(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("notBooleanIsProp1");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * 
     */
    public void testStringProperty2()
    {
        testStringProperty2(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testStringProperty2SubClass()
    {
        testStringProperty2(_beanSubclassProperties);
    }
    
    /**
     * 
     */
    private void testStringProperty2(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("stringProperty2");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a String", 
                        "Ljava.lang.String;", property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * 
     */
    public void testReadonlyStringProperty()
    {
        testReadonlyStringProperty(_beanProperties);
    }
    
    /**
     * test inhertied
     */
    public void testReadonlyStringPropertySubClass()
    {
        testReadonlyStringProperty(_beanSubclassProperties);
    }
    
    
    /**
     * 
     */
    private void testReadonlyStringProperty(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("readonlyStringProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a String", 
                        "Ljava.lang.String;", property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * 
     */
    public void testReadonlyBooleanProperty()
    {
        testReadonlyBooleanProperty(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testReadonlyBooleanPropertySubClass()
    {
        testReadonlyBooleanProperty(_beanSubclassProperties);
    }
    
    /**
     * 
     */
    private void testReadonlyBooleanProperty(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("readonlyBooleanProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertFalse("No setter for this property", property.isWritable());
        assertEquals("Signature must be for a boolean", 
                        Signature.SIG_BOOLEAN, property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * 
     */
    public void testStringArrayProperty()
    {
        testStringArrayProperty(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testStringArrayPropertySubClass()
    {
        testStringArrayProperty(_beanSubclassProperties);
    }
    
    /**
     * 
     */
    private void testStringArrayProperty(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("stringArrayProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String[]", 
                "[Ljava.lang.String;", property.getTypeDescriptor().getTypeSignature());
    }

    /**
     * 
     */
    public void testCollectionProperty()
    {
        testCollectionProperty(_beanProperties);
    }
    
    /**
     * test inhertited
     */
    public void testCollectionPropertySubClass()
    {
        testCollectionProperty(_beanSubclassProperties);
    }
    
    /**
     * 
     */
    private void testCollectionProperty(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("collectionProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a Collection", 
                "Ljava.util.Collection;", property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * 
     */
    public void testMapProperty()
    {
        testMapProperty(_beanProperties);
    }

    /**
     * test inherited
     */
    public void testMapPropertySubClass()
    {
        testMapProperty(_beanProperties);
    }

    /**
     * 
     */
    private void testMapProperty(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("mapProperty");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a Map", 
                "Ljava.util.Map;", property.getTypeDescriptor().getTypeSignature());
    }

    /**
     * 
     */
    public void testWriteonlyStringProperty()
    {
        testWriteonlyStringProperty(_beanProperties);
    }
    
    /**
     * inherited
     */
    public void testWriteonlyStringPropertySubClass()
    {
        testWriteonlyStringProperty(_beanSubclassProperties);
    }
    
    /**
     * 
     */
    private void testWriteonlyStringProperty(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("writeonlyStringProperty");
        assertNotNull(property);
        
        assertFalse("No getter for this property", property.isReadable());
        assertTrue(property.isWritable());
        assertEquals("Signature must be for a String", 
                "Ljava.lang.String;", property.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * test a locally defined bean type
     */
    public void testAnotherBean()
    {
        testAnotherBean(_beanProperties);
    }
    
    /**
     * test inherited
     */
    public void testAnotherBeanSubClass()
    {
        testAnotherBean(_beanSubclassProperties);
    }

    private void testAnotherBean(Map properties)
    {
        IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("anotherBean");
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        assertTrue("No setter for this property",property.isWritable());
        assertEquals("Signature must be for a com.test.AnotherBean", 
                "Lcom.test.AnotherBean;", property.getTypeDescriptor().getTypeSignature());
    }

    /**
     * Test bean.anotherBean.property for TestBean1
     */
    public void testPropertyOfProperty()
    {
        testPropertyOfProperty(_beanProperties);
    }
    
    /**
     * Test in inherited anotherBean property
     */
    public void testPropertyOfPropertyInSubclass()
    {
        testPropertyOfProperty(_beanSubclassProperties);
    }
    
    private void testPropertyOfProperty(Map properties)
    {
        final IBeanPropertySymbol  property = 
            (IBeanPropertySymbol) properties.get("anotherBean");
        assertNotNull(property);

        boolean foundNestedProperty = false;
        
        final List anotherBeanProps =
            property.getTypeDescriptor().getProperties();
        assertTrue("The nested properties has properties", anotherBeanProps.size()>0);

        SEARCH_FOR_NESTED_PROPERTY:for 
            (final Iterator it = anotherBeanProps.iterator(); it.hasNext();)
        {
            final IBeanPropertySymbol nestedProp = (IBeanPropertySymbol) it.next();
            
            // looking for a property of AnotherBean called property
            if ("property".equals(nestedProp.getName()))
            {
                foundNestedProperty = true;
                break SEARCH_FOR_NESTED_PROPERTY;
            }
        }
        
        assertTrue(foundNestedProperty);
    }
    
    /**
     * Test a property that's in the sub-class but not the parent
     */
    public void testSubclassOnly()
    {
        final String inheritedPropertyName = "locallyDefinedProperty";
        
        // ensure we didn't some how put an inherited property into the
        // parent
        assertNull(_beanProperties.get(inheritedPropertyName));
        IBeanPropertySymbol property = (IBeanPropertySymbol) _beanSubclassProperties.get(inheritedPropertyName);
        assertNotNull(property);
        
        assertTrue(property.isReadable());
        // no setter
        assertFalse(property.isWritable());
        assertEquals("Signature must be for a String", 
                "Ljava.lang.String;", property.getTypeDescriptor().getTypeSignature());
    }
    
/* ------ Method signature testing -----------------------*/
    
    /**
     * test no arg boolean method
     */
    public void testABooleanMethodWithNoArgs()
    {
        testABooleanMethodWithNoArgs(_beanMethods);
    }
    
    /**
     * test inherited no arg boolean method
     */
    public void testABooleanMethodWithNoArgsSubclass()
    {
        testABooleanMethodWithNoArgs(_beanMethodsSubclass);
    }
    
    
    /**
     * 
     */
    private void testABooleanMethodWithNoArgs(Map properties)
    {
        IBeanMethodSymbol  method = 
            (IBeanMethodSymbol) properties.get("aBooleanMethodWithNoArgs");
        
        assertEquals("()"+Signature.SIG_BOOLEAN,
                        method.getSignature());
    }
    
    /**
     * test no args string method
     */
    public void testAStringMethodWithNoArgs()
    {
        testAStringMethodWithNoArgs(_beanMethods);
    }
    
    /**
     * test inherited no args string method
     */
    public void testAStringMethodWithNoArgsSubclass()
    {
        testAStringMethodWithNoArgs(_beanMethodsSubclass);
    }
    
    /**
     * 
     */
    private void testAStringMethodWithNoArgs(Map properties)
    {
        IBeanMethodSymbol  method = 
            (IBeanMethodSymbol) properties.get("aStringMethodWithNoArgs");
        
        assertEquals("()Ljava.lang.String;",
                        method.getSignature());
    }
    
    /**
     * test is accessor method
     */
    public void testIsAnIsAccessor()
    {
        testIsAnIsAccessor(_beanMethods);
    }
    
    /**
     * test inherited
     */
    public void testIsAnIsAccessorSubclass()
    {
        testIsAnIsAccessor(_beanMethodsSubclass);
    }
    
    /**
     * 
     */
    private void testIsAnIsAccessor(Map properties)
    {
        IBeanMethodSymbol  method = 
            (IBeanMethodSymbol) properties.get("isAnIsAccessor");
        
        assertEquals("()Z",
                        method.getSignature());
    }
    
    /**
     * 
     */
    public void testAIntegerMethodThatTakesAString()
    {
        testAIntegerMethodThatTakesAString(_beanMethods);
    }
    
    /**
     * inherited
     */
    public void testAIntegerMethodThatTakesAStringSubclass()
    {
        testAIntegerMethodThatTakesAString(_beanMethodsSubclass);
    }
    
    /**
     * 
     */
    private void testAIntegerMethodThatTakesAString(Map properties)
    {
        IBeanMethodSymbol  method = 
            (IBeanMethodSymbol) properties.get("aIntegerMethodThatTakesAString");
        
        assertEquals("(Ljava.lang.String;)"+Signature.SIG_INT,
                        method.getSignature());
    }
    
    /**
     * 
     */
    public void testAIntegerMethodThatTakesAStringAndLong()
    {
        testAIntegerMethodThatTakesAStringAndLong(_beanMethods);
    }
    
    /**
     * inherited
     */
    public void testAIntegerMethodThatTakesAStringAndLongSubclass()
    {
        testAIntegerMethodThatTakesAStringAndLong(_beanMethodsSubclass);
    }
    
    /**
     * @param arg1
     * @param arg2
     */
    private void testAIntegerMethodThatTakesAStringAndLong(Map properties)
    {
        IBeanMethodSymbol  method = 
            (IBeanMethodSymbol) properties.get("aIntegerMethodThatTakesAStringAndLong");
        
        assertEquals("(Ljava.lang.String;Ljava.lang.Long;)"+Signature.SIG_INT,
                        method.getSignature());
    }

    /**
     * test that the overloaded methods are both there
     */
    public void testOverloadedMethod()
    {
        testOverloadedMethod(_testBean2Symbol);
    }
    
    /**
     * test the inherited
     */
    public void testOverloadedMethodSubclass()
    {
        testOverloadedMethod(_testBean2SubclassSymbol);
    }
    
    private void testOverloadedMethod(IBeanInstanceSymbol bean)
    {
        final String intMethodSignature =
            "("+Signature.SIG_INT+")"+Signature.SIG_VOID;
        final String stringMethodSignature =
            "(Ljava.lang.String;)V";
        
        boolean   foundIntOverload = false;
        boolean   foundStringOverload = false;
        
        for (final Iterator it = bean.getTypeDescriptor().getMethods().iterator();
                it.hasNext();)
        {
            final IBeanMethodSymbol method = (IBeanMethodSymbol) it.next();
            if (overloadedMethodName.equals(method.getName()))
            {
                final String typeSignature = method.getSignature();
                
                if (intMethodSignature.equals(typeSignature))
                {
                    foundIntOverload = true;
                }
                else if (stringMethodSignature.equals(typeSignature))
                {
                    foundStringOverload = true;
                }
            }
        }
        
        assertTrue(foundIntOverload && foundStringOverload);
    }
    
    /**
     * Test method signature that is only in the sub-class, not in the parent
     */
    public void testMethodSubclassOnly()
    {
        assertNull(_beanMethods.get("validate"));
        
        IBeanMethodSymbol  method = 
            (IBeanMethodSymbol) _beanMethodsSubclass.get("validate");
        assertNotNull(method);
        assertEquals("(Lcom.test.TestBean2;)"+Signature.SIG_VOID,
                        method.getSignature());
    }

/* ------ test acquisition of super-classes and interfaces -----------------------*/
    /**
     * Test correct super class acquistion
     */
    public void testSuperClassAcquisition()
    {
        // TestBean1
        List superSigs =
            _testBean1Symbol.getTypeDescriptor().getSuperTypeSignatures();
        
        // should only have java.lang.Object as a super
        assertEquals(superSigs.size(), 1);
        assertEquals("Ljava.lang.Object;", (String)superSigs.get(0));
        
        // TestBean1Subclass
        superSigs = 
            _testBean1SubclassSymbol.getTypeDescriptor().getSuperTypeSignatures();
        
        // should have Object and TestBean1 as parents
        assertEquals(superSigs.size(), 2);
        assertTrue(superSigs.contains("Ljava.lang.Object;"));
        assertTrue(superSigs.contains("Lcom.test.TestBean1;"));
        
        // TestBean2
        superSigs =
            _testBean2Symbol.getTypeDescriptor().getSuperTypeSignatures();
        
        // should only have java.lang.Object as a super
        assertEquals(superSigs.size(), 1);
        assertEquals("Ljava.lang.Object;", (String)superSigs.get(0));

        // TestBean2Subclass
        superSigs =
            _testBean2SubclassSymbol.getTypeDescriptor().getSuperTypeSignatures();
        // should have Object and TestBean2 as parents
        assertEquals(superSigs.size(), 2);
        assertTrue(superSigs.contains("Ljava.lang.Object;"));
        assertTrue(superSigs.contains("Lcom.test.TestBean2;"));

    }
    
    /**
     * Test the acquisition of the interface implemented on TestBean2 and its subclass
     */
    public void testInterfaceAcquisition()
    {
        // TestBean2
        List superSigs =
            _testBean2Symbol.getTypeDescriptor().getInterfaceTypeSignatures();
        
        // should only have ITestBean2
        assertEquals(superSigs.size(), 1);
        assertEquals("Lcom.test.ITestBean2;", (String)superSigs.get(0));

        // TestBean2Subclass
        superSigs =
            _testBean2SubclassSymbol.getTypeDescriptor().getInterfaceTypeSignatures();
        // should only have ITestBean2
        assertEquals(superSigs.size(), 1);
        assertEquals("Lcom.test.ITestBean2;", (String)superSigs.get(0));
    }
    
// coverage of EObject generated methods
    
    /**
     * Cover isSet
     */
    public void testIsESet()
    {
        IType testBean1Type = null;
        
        try
        {
            testBean1Type =
                _jdtTestEnvironment.getJavaProject().
                    findType(packageName1+"."+testBeanName1);
        }
        catch (JavaModelException jme)
        {
            fail(jme.getLocalizedMessage());
        }
        
        assertNotNull(testBean1Type);

        IJavaTypeDescriptor2  desc =
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        
        // there are methods and one property on TestBean2
        assertFalse(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanProperties()));
        assertFalse(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanMethods()));
        assertFalse(desc.eIsSet(SymbolPackage.eINSTANCE.getIBeanInstanceSymbol_JavaTypeDescriptor()));
        
        desc.eSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanProperties(), 
                  _testBean2Symbol.getJavaTypeDescriptor().getBeanProperties());
        desc.eSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanMethods(), 
                  _testBean2Symbol.getJavaTypeDescriptor().getBeanMethods());
        desc.eSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_Type(),
                  testBean1Type);
        
        assertFalse(((List)desc.eGet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanProperties())).size() 
                   == 0);
        assertFalse(((List)desc.eGet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanMethods())).size()
                   == 0);
        assertEquals(desc.eGet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_Type()),
                testBean1Type);
       
        assertTrue(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanProperties()));
        assertTrue(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanMethods()));
        assertTrue(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_Type()));
        
        desc.eUnset(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanProperties());
        desc.eUnset(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanMethods());
        desc.eUnset(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_Type());
        
        // there are methods and one property on TestBean2
        assertFalse(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanProperties()));
        assertFalse(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_BeanMethods()));
        assertFalse(desc.eIsSet(SymbolPackage.eINSTANCE.getIJavaTypeDescriptor2_Type()));
    }
    
    /**
     * Cover the toString
     */
    public void testToString()
    {
        _testBean2Symbol.getTypeDescriptor().toString();
    }
}
