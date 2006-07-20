package org.eclipse.jst.jsf.core.tests.util;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.provisional.util.JDTBeanProperty;
import org.eclipse.jst.jsf.core.internal.provisional.util.JDTBeanPropertyWorkingCopy;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JavaCodeResource;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;

/**
 * Tests basic operations on the jdt bean property
 * 
 * @author cbateman
 *
 */
public class TestJDTBeanPropertyWorkingCopy extends TestCase {
	private JDTTestEnvironment  _jdtTestEnvironment;
	private IType               _testBean1Type;
    
    private final static String srcFolderName = "src";
    private final static String packageName1 = "com.test";
    private final static String testBeanName1 = "TestBean1";
    
	protected void setUp() throws Exception {
		super.setUp();
		final ProjectTestEnvironment  projectTestEnvironment = 
			new ProjectTestEnvironment("TestJDTBeanPropertyWorkingCopyProject");
		projectTestEnvironment.createProject();
		
		_jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
        
        // load TestBean1
        JavaCodeResource codeRes = new JavaCodeResource();
        codeRes.load(TestsPlugin.getDefault().getBundle(), "/testfiles/TestBean1.java.data");
        String code = codeRes.getCode();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName1, testBeanName1, code);
        
        _testBean1Type = _jdtTestEnvironment.getJavaProject().findType(packageName1+"."+testBeanName1);
        assertNotNull(_testBean1Type);
	}
	
    /**
     * Test simple dt bean introspection
     */
	public void testSimpleBeanProperty()
    {
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleGetter = _testBean1Type.getMethod("getStringProp1", new String[0]);
        IMethod simpleSetter = _testBean1Type.getMethod("setStringProp1", new String[] {"QString;"});
        assertNotNull(simpleGetter);
        assertNotNull(simpleSetter);
        
        workingCopy.addSetter(simpleSetter);
        workingCopy.setGetter(simpleGetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);
        
        // we provided both a get and set, so should be readable and writable
        assertTrue(beanProperty.isReadable());
        assertTrue(beanProperty.isWritable());
        
        // the getter and setter methods should match the ones we gave
        assertTrue(beanProperty.getGetter() == simpleGetter);
        assertTrue(beanProperty.getSetter() == simpleSetter);
        
        // the type should resolve to a String
        assertTrue("Ljava.lang.String;".equals(beanProperty.getTypeSignature()));
        
        // should be able to resolve the IType
        assertNotNull(beanProperty.getType());
    }
    
    /**
     * Test a simple dt bean based on a boolean "is" getter
     */
    public void testSimpleIsBeanProperty()
    {
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleGetter = _testBean1Type.getMethod("isBooleanIsProp1", new String[0]);
        IMethod simpleSetter = _testBean1Type.getMethod("setBooleanIsProp1", new String[] {Signature.SIG_BOOLEAN});
        assertNotNull(simpleGetter);
        assertNotNull(simpleSetter);

        workingCopy.addSetter(simpleSetter);
        workingCopy.setGetter(simpleGetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);
        
        // we provided both a get and set, so should be readable and writable
        assertTrue(beanProperty.isReadable());
        assertTrue(beanProperty.isWritable());
        
        // the getter and setter methods should match the ones we gave
        assertTrue(beanProperty.getGetter() == simpleGetter);
        assertTrue(beanProperty.getSetter() == simpleSetter);
        
        // the type should resolve to a String
        assertTrue(Signature.SIG_BOOLEAN.equals(beanProperty.getTypeSignature()));
        
        // should be a boolean so no IType
        assertNull(beanProperty.getType());
    }
    
    /**
     * Assert that where there is both and "is" and "get" accessor for a boolean
     * property, the "is" accessor is always the one that is used
     */
    public void testIsAccessorTakesPrecedence()
    {
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleIsGetter = _testBean1Type.getMethod("isBooleanIsProp2", new String[0]);
        IMethod simpleGetter = _testBean1Type.getMethod("getBooleanIsProp2", new String[0]);
        IMethod simpleSetter = _testBean1Type.getMethod("setBooleanIsProp2", new String[] {Signature.SIG_BOOLEAN});
        assertNotNull(simpleGetter);
        assertNotNull(simpleSetter);
        assertNotNull(simpleIsGetter);
        
        workingCopy.setIsGetter(simpleIsGetter);
        workingCopy.addSetter(simpleSetter);
        workingCopy.setGetter(simpleGetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);
        
        // we provided both a get and set, so should be readable and writable
        assertTrue(beanProperty.isReadable());
        assertTrue(beanProperty.isWritable());
        
        // the getter and setter methods should match the ones we gave
        // MOST IMPORTANT is that the is getter is selected
        assertTrue(beanProperty.getGetter() == simpleIsGetter);
        assertTrue(beanProperty.getSetter() == simpleSetter);
        
        // the type should resolve to a String
        assertTrue(Signature.SIG_BOOLEAN.equals(beanProperty.getTypeSignature()));
        
        // should be a boolean so no IType
        assertNull(beanProperty.getType());
    }
    
    // TODO: this test belongs in the bean introspector tests
//    /**
//     * The is accessor should not be used if it is not a boolean return type
//     */
//    public void testIsAccessorNotUsedIfNotBoolean()
//    {
//        JDTBeanPropertyWorkingCopy workingCopy = 
//            new JDTBeanPropertyWorkingCopy(_testBean1Type);
//        
//        IMethod simpleIsGetter = _testBean1Type.getMethod("isNotBooleanIsProp1", new String[0]);
//        IMethod simpleGetter = _testBean1Type.getMethod("getNotBooleanIsProp1", new String[0]);
//        IMethod simpleSetter = _testBean1Type.getMethod("setNotBooleanIsProp1", new String[] {Signature.SIG_BOOLEAN});
//        
//        workingCopy.setIsGetter(simpleIsGetter);
//        workingCopy.addSetter(simpleSetter);
//        workingCopy.setGetter(simpleGetter);
//        
//        JDTBeanProperty beanProperty = workingCopy.toValueObjects();
//        assertNotNull(beanProperty);
//        
//        // we provided both a get and set, so should be readable and writable
//        assertTrue(beanProperty.isReadable());
//        assertTrue(beanProperty.isWritable());
//        
//        // the getter and setter methods should match the ones we gave
//        // MOST IMPORTANT is that the is getter is NOT selected
//        assertTrue(beanProperty.getGetter() == simpleGetter);
//        assertTrue(beanProperty.getSetter() == simpleSetter);
//        
//        // the type should resolve to a String
//        assertTrue(Signature.SIG_BOOLEAN.equals(beanProperty.getTypeSignature()));
//        
//        // should be a boolean so no IType
//        assertNull(beanProperty.getType());
//
//    }
    
    /**
     * A setter should be ignored if it doesn't match the getter's return
     * type
     */
    public void testDoNotUseSetterIfDoesNotMatchGetterType()
    {
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleGetter = _testBean1Type.getMethod("getStringProperty2", new String[0]);
        IMethod simpleSetter = _testBean1Type.getMethod("setStringProperty2", new String[] {Signature.SIG_INT});
        assertNotNull(simpleGetter);
        assertNotNull(simpleSetter);

        workingCopy.addSetter(simpleSetter);
        workingCopy.setGetter(simpleGetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);
        
        // valid getter so readable
        assertTrue(beanProperty.isReadable());
        // setter should be ignored so not writable
        assertFalse(beanProperty.isWritable());
        
        // getter should match
        assertTrue(beanProperty.getGetter() == simpleGetter);
        
        // setter should be null since the one given is ignored
        assertNull(beanProperty.getSetter());
        
        // the type should resolve to a String
        assertTrue("Ljava.lang.String;".equals(beanProperty.getTypeSignature()));
        
        // should be an IType for a String
        assertNotNull(beanProperty.getType());
    }
    
    /**
     * test read-only bean properties
     */
    public void testReadonlyBeanProperty()
    {
    // readonly get
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleGetter = _testBean1Type.getMethod("getReadonlyStringProperty", new String[0]);
        assertNotNull(simpleGetter);
        
        workingCopy.addSetter(null);
        workingCopy.setGetter(simpleGetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);
        
        // valid getter so readable
        assertTrue(beanProperty.isReadable());
        // setter is null so should no be writable
        assertTrue(!beanProperty.isWritable());
        
        // getter should match
        assertTrue(beanProperty.getGetter() == simpleGetter);
        
        // setter should be null
        assertNull(beanProperty.getSetter());
        
        // the type should resolve to a String
        assertTrue("Ljava.lang.String;".equals(beanProperty.getTypeSignature()));
        
        // should be an IType for a String
        assertNotNull(beanProperty.getType());
        
        workingCopy =
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
    // readonly is getter
        IMethod isGetter = _testBean1Type.getMethod("isReadonlyBooleanProperty", new String[0]);
        assertNotNull(isGetter);
        
        workingCopy.addSetter(null);
        workingCopy.setGetter(isGetter);
        
        beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);
        
        // valid getter so readable
        assertTrue(beanProperty.isReadable());
        // setter null so not writable
        assertFalse(beanProperty.isWritable());
        
        // getter should match the isGetter
        assertTrue(beanProperty.getGetter() == isGetter);
        
        // setter should be null
        assertNull(beanProperty.getSetter());
        
        // the type should resolve to a String
        assertTrue(Signature.SIG_BOOLEAN.equals(beanProperty.getTypeSignature()));
        
        // should not be an IType for a boolean
        assertNull(beanProperty.getType());
    }
    
    /**
     * Test a simple write-only bean
     */
    public void testWriteonlyBeanProperty()
    {
        // readonly get
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleSetter = _testBean1Type.getMethod("setWriteonlyStringProperty", new String[]{"QString;"});
        assertNotNull(simpleSetter);
        
        workingCopy.addSetter(simpleSetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);

        // not readable
        assertFalse(beanProperty.isReadable());
        // writable
        assertTrue(beanProperty.isWritable());
        
        // setter should match
        assertTrue(beanProperty.getSetter() == simpleSetter);
        
        // getter should be null
        assertNull(beanProperty.getGetter());
        
        // the type should resolve to a String
        assertTrue("Ljava.lang.String;".equals(beanProperty.getTypeSignature()));
        
        // should be an IType for a String
        assertNotNull(beanProperty.getType());
    }
    
    /**
     * 
     */
    public void testStringArrayProperty()
    {
        // readonly get
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleGetter = _testBean1Type.getMethod("getStringArrayProperty", new String[0]);
        IMethod simpleSetter = _testBean1Type.getMethod("setStringArrayProperty", new String[]{"[QString;"});
        assertNotNull(simpleGetter);
        assertNotNull(simpleSetter);
        
        workingCopy.setGetter(simpleGetter);
        workingCopy.addSetter(simpleSetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);

        // readable
        assertTrue(beanProperty.isReadable());
        // writable
        assertTrue(beanProperty.isWritable());
        
        // getter/setter should match
        assertTrue(beanProperty.getGetter() == simpleGetter);
        assertTrue(beanProperty.getSetter() == simpleSetter);
        
        // the type should resolve to a String[]
        assertTrue("[Ljava.lang.String;".equals(beanProperty.getTypeSignature()));
        
        // no IType for arrays
        assertNull(beanProperty.getType());
    }
    
    /**
     * 
     */
    public void testCollectionProperty()
    {
        // readonly get
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleGetter = _testBean1Type.getMethod("getCollectionProperty", new String[0]);
        IMethod simpleSetter = _testBean1Type.getMethod("setCollectionProperty", new String[]{"QCollection;"});
        assertNotNull(simpleGetter);
        assertNotNull(simpleSetter);
        
        workingCopy.setGetter(simpleGetter);
        workingCopy.addSetter(simpleSetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);

        // readable
        assertTrue(beanProperty.isReadable());
        // writable
        assertTrue(beanProperty.isWritable());
        
        // getter/setter should match
        assertTrue(beanProperty.getGetter() == simpleGetter);
        assertTrue(beanProperty.getSetter() == simpleSetter);
        
        // the type should resolve to java.util.Collection
        assertTrue("Ljava.util.Collection;".equals(beanProperty.getTypeSignature()));
        
        // should resolve a type
        assertNotNull(beanProperty.getType());
    }
    
    /**
     * 
     */
    public void testMapProperty()
    {
        // readonly get
        JDTBeanPropertyWorkingCopy workingCopy = 
            new JDTBeanPropertyWorkingCopy(_testBean1Type);
        
        IMethod simpleGetter = _testBean1Type.getMethod("getMapProperty", new String[0]);
        IMethod simpleSetter = _testBean1Type.getMethod("setMapProperty", new String[]{"QMap;"});
        assertNotNull(simpleGetter);
        assertNotNull(simpleSetter);
        
        workingCopy.setGetter(simpleGetter);
        workingCopy.addSetter(simpleSetter);
        
        JDTBeanProperty beanProperty = workingCopy.toValueObject();
        assertNotNull(beanProperty);

        // readable
        assertTrue(beanProperty.isReadable());
        // writable
        assertTrue(beanProperty.isWritable());
        
        // getter/setter should match
        assertTrue(beanProperty.getGetter() == simpleGetter);
        assertTrue(beanProperty.getSetter() == simpleSetter);
        
        // the type should resolve to java.util.Map
        assertTrue("Ljava.util.Map;".equals(beanProperty.getTypeSignature()));
        
        // should resolve a type
        assertNotNull(beanProperty.getType());
    }

}
