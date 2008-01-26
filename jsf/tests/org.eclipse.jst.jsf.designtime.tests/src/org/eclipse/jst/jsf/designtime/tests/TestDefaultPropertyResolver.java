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
package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.el.DefaultDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleMapSourceFactory;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Unit tests for the default property resolver
 * @author cbateman
 *
 */
public class TestDefaultPropertyResolver extends TestCase 
{
    private static final int NUM_PROPERTIES_TEST_BEAN_1 = 6;  // includes java.lang.Object.getClass()
    private JDTTestEnvironment              _jdtTestEnvironment;
    private JSFFacetedTestEnvironment       _jsfFactedTestEnvironment;

    private IType                           _testBean1Type;
    private IType                           _testMapBean1Type;
    private IType                           _testBean2Type;
    private IType                           _testBean3Type;
    private IType                           _testBeanWithMapProp;
    private IType                           _testListBeanType;
    private IType                           _testBeanWithListPropType;
    private IType                           _testBeanWithGenericProperties;
    
    private final static String     SRC_FOLDER_NAME = "src";
    private final static String     PACKAGE_NAME = "com.test";
    private final static String     TESTBEAN1_NAME = "TestBean1";
    private final static String     TESTBEAN2_NAME = "TestBean2";
    private final static String     TESTBEAN3_NAME = "TestBean3";
    private final static String     MAPBEAN_NAME = "MapBean";
    private final static String     BEANWITHMAPPROP_NAME = "BeanWithMapProp";  
    private final static String     LISTBEAN_NAME = "ListBean";
    private final static String     BEANWITHLISTPROP_NAME = "BeanWithListProp";
    private final static String     BEANWITHGENERICPROP_NAME = "TestBeanWithGenericProperties";
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDefaultPropertyResolver_"+getName());
        projectTestEnvironment.createProject(false);

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        _jdtTestEnvironment.addResourceFile(SRC_FOLDER_NAME
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");
        
        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/TestBean1.java.data", TESTBEAN1_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBean1Type = 
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+TESTBEAN1_NAME);
        assertNotNull(_testBean1Type);
        
        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/MapBean.java.data", MAPBEAN_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testMapBean1Type = 
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+MAPBEAN_NAME);
        assertNotNull(_testMapBean1Type);
        
        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/TestBean2.java.data", TESTBEAN2_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBean2Type = 
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+TESTBEAN2_NAME);
        assertNotNull(_testBean2Type);
        
        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/TestBean3.java.data", TESTBEAN3_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBean3Type = 
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+TESTBEAN3_NAME);
        assertNotNull(_testBean3Type);
        
        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/BeanWithMapProp.java.data", BEANWITHMAPPROP_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBeanWithMapProp = 
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+BEANWITHMAPPROP_NAME);
        assertNotNull(_testBeanWithMapProp);

        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/ListBean.java.data", LISTBEAN_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testListBeanType =
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+LISTBEAN_NAME);
        assertNotNull(_testListBeanType);
        
        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/BeanWithListProp.java.data", BEANWITHLISTPROP_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBeanWithListPropType =
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+BEANWITHLISTPROP_NAME);
        assertNotNull(_testBeanWithListPropType);
        
        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/TestBeanWithGenericProperties.java.data", BEANWITHGENERICPROP_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBeanWithGenericProperties = 
            _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+BEANWITHGENERICPROP_NAME);
        assertNotNull(_testBeanWithGenericProperties);
    }
    
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        
        _jdtTestEnvironment.getJavaProject().getProject().delete(true, null);
    }



    /**
     * Test precondition sanity
     */
    public void testSanity()
    {
        JDTBeanIntrospector  beanIntrospector = new JDTBeanIntrospector(_testBean1Type);
        Map<String, JDTBeanProperty> props = beanIntrospector.getProperties();
        assertEquals(NUM_PROPERTIES_TEST_BEAN_1, props.size());
        assertTrue(props.containsKey("stringProp1"));
        assertTrue(props.containsKey("booleanIsProp1"));
        
        beanIntrospector = new JDTBeanIntrospector(_testMapBean1Type);
        props = beanIntrospector.getProperties();
        // has 2 as a bean: isEmpty -> empty property + class
        assertEquals(2, props.size());

        beanIntrospector = new JDTBeanIntrospector(_testBean2Type);
        props = beanIntrospector.getProperties();
        // two props: myBean3, class
        assertEquals(2, props.size());
        
        beanIntrospector = new JDTBeanIntrospector(_testBean3Type);
        props = beanIntrospector.getProperties();
        // two props: one of type TestBean2 + class
        assertEquals(2, props.size());
        
        beanIntrospector = new JDTBeanIntrospector(_testBeanWithMapProp);
        props = beanIntrospector.getProperties();
        // two props: one of type Map + class
        assertEquals(2, props.size());
        
        beanIntrospector = new JDTBeanIntrospector(_testListBeanType);
        props = beanIntrospector.getProperties();
        // includes isEmpty and class
        assertEquals(3, props.size());
        
        beanIntrospector = new JDTBeanIntrospector(_testBeanWithListPropType);
        props = beanIntrospector.getProperties();
        assertEquals(2, props.size());
        
        beanIntrospector = new JDTBeanIntrospector(_testBeanWithGenericProperties);
        props = beanIntrospector.getProperties();
        assertEquals(3, props.size());
    }
    
    /**
     * Test a basic simple bean symbol to see if we resolve properties correctly
     */
    public void testBeanInstanceSymbol()
    {
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myBean");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testBean1Type);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        final ISymbol[]  properties = propResolver.getAllProperties(symbol);
        assertEquals(NUM_PROPERTIES_TEST_BEAN_1, properties.length);
        final Map<String, ISymbol>  checkProps = new HashMap<String, ISymbol>();
        for (final ISymbol propSymbol : properties)
        {
            checkProps.put(propSymbol.getName(), propSymbol);
        }
        
        assertTrue(checkProps.containsKey("stringProp1"));
        assertTrue(checkProps.containsKey("booleanIsProp1"));
        
        {
            final ISymbol stringProp1 = propResolver.getProperty(symbol, "stringProp1");
            assertNotNull(stringProp1);
            assertTrue(stringProp1 instanceof IPropertySymbol);
            assertTrue(((IPropertySymbol)stringProp1).getTypeDescriptor().getTypeSignature().equals(TypeConstants.TYPE_STRING));
            final IPropertySymbol mapStringProp1 = (IPropertySymbol) checkProps.get("stringProp1");
            assertEquals(((IPropertySymbol)stringProp1).getTypeDescriptor().getTypeSignature(), 
                            mapStringProp1.getTypeDescriptor().getTypeSignature());
        }
        
        {
            final ISymbol booleanIsProp = propResolver.getProperty(symbol, "booleanIsProp1");
            assertNotNull(booleanIsProp);
            assertTrue(booleanIsProp instanceof IPropertySymbol);
            assertTrue(((IPropertySymbol)booleanIsProp).getTypeDescriptor().getTypeSignature().equals(Signature.SIG_BOOLEAN));
            final IPropertySymbol mapBooleanProp = (IPropertySymbol) checkProps.get("booleanIsProp1");
            assertEquals(((IPropertySymbol)booleanIsProp).getTypeDescriptor().getTypeSignature(), 
                         mapBooleanProp.getTypeDescriptor().getTypeSignature());
        }
    }
    
    /**
     * Test an unconstrained Map bean
     */
    public void testMapBeanInstanceSymbol()
    {
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myMapBean");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testMapBean1Type);
        symbol.setTypeDescriptor(typeDesc);

        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        final ISymbol[]  properties = propResolver.getAllProperties(symbol);
        // there no design-time identifiable properties
        // note that this is different that what JDTBeanIntrospector returns
        // since the default property resolver first coerces to a Map
        // so there are no propeties
        assertEquals(0, properties.length);

        // but being that it's an unbounded Map, it may have  any number
        // of arbitrary "unconstrained" properties
        for (int i = 0; i < 25; i++)
        {
            final String unconstrainedPropName = "someName"+i*7+"withNonSequentialNumber"+i*11;
            final ISymbol someProperty1 = propResolver.getProperty(symbol, unconstrainedPropName);
            assertNotNull(someProperty1);
            assertEquals(unconstrainedPropName, someProperty1.getName());
            assertTrue(someProperty1 instanceof IPropertySymbol);
            // completely unconstrained properties should come back as java object
            assertEquals(TypeConstants.TYPE_JAVAOBJECT, ((IPropertySymbol)someProperty1).getTypeDescriptor().getTypeSignature());
        }
    }

    /**
     * Test a component with map source
     */
    public void testComponentBeanInstanceSymbol()
    {
        final IComponentSymbol symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName("myComponentSymbol");
        final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
        final Map<String, String> mapSource = new HashMap<String, String>();
        mapSource.put("prop1", "propValue1");
        mapSource.put("prop2", "propValue2");
        mapSource.put("dotted.property", "dottedPropertyValue");
        typeDesc.setMapSource(mapSource);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        final ISymbol[]  properties = propResolver.getAllProperties(symbol);

        // should have three properties
        assertEquals(3, properties.length);
        
        // loop 10 times through the inner loop assertions to 
        // verify that getAllProperties and getProperties are 
        // idempotent
        for (int j = 0; j < 10; j++)
        {
            // should have properties from mapSource above
            // these should all be equivalent to those returned by getProperty
            // since the object is a map first and foremost (e.g. not a bean cast to a Map)
            for (final ISymbol propertie : properties)
            {
                // make sure the property's name matches a value in map source
                // for the dotted one, we make an exception
                assertTrue(mapSource.containsKey(propertie.getName())
                            || (propertie.getName().startsWith("dotted")));
                // should be a property symbol
                assertTrue(propertie instanceof IPropertySymbol);
                final IPropertySymbol propSymbol = (IPropertySymbol) propertie;
                
                // get the symbol by name
                final ISymbol  symbolById = propResolver.getProperty(symbol, propSymbol.getName());
                // should have the same name whether in getAll or get
                assertEquals(propSymbol.getName(), symbolById.getName());
                // we don't have an absolute "equals" for symbols, but can at least
                // verify type equivalence
                assertEquals(propSymbol.getTypeDescriptor().getTypeSignature(),
                                ((IObjectSymbol)symbolById).getTypeDescriptor().getTypeSignature());
            }
        }
        
        // verify that the dotted property is intermediate and that 
        // dotted.property is there
        final ISymbol symbolById = propResolver.getProperty(symbol, "dotted");
        assertNotNull(symbolById);
        assertTrue(((IPropertySymbol)symbolById).isIntermediate());
        final ISymbol dottedProp = propResolver.getProperty(symbolById, "property");
        assertTrue(dottedProp instanceof IPropertySymbol);
        assertEquals(TypeConstants.TYPE_STRING, ((IPropertySymbol)dottedProp).getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * Tests a property of property for a bean instance
     * i.e. var.prop1.prop2
     */
    public void testBeanPropertyOnBeanProperty()
    {
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myBean2");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testBean2Type);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        {
            final ISymbol[]  properties = propResolver.getAllProperties(symbol);
            assertEquals(2, properties.length);  // should have defined property plus class
            
            // check props
            final Map<String, IPropertySymbol>  gotProps  = new HashMap<String, IPropertySymbol>();
            
            for (final ISymbol propertie : properties)
            {
                final IPropertySymbol propSymbol = (IPropertySymbol) propertie;
                assertEquals(propSymbol.getTypeDescriptor().getTypeSignature(),
                        ((IObjectSymbol)propResolver.getProperty(symbol,propSymbol.getName()))
                                                    .getTypeDescriptor().getTypeSignature());
                gotProps.put(propSymbol.getName(), propSymbol);
            }
            
            assertTrue(gotProps.containsKey("myBean3"));
            assertTrue(gotProps.containsKey("class"));
        }
        // next run recursively on myBean3 10 props deep
        ISymbol curBase = symbol;
        // note, when i is even, the property is myBean3 on TestBean2
        // when i is odd, the property is myBean2 on TestBean3
        for (int i = 0; i < 10; i++)
        {
            final ISymbol[] properties = propResolver.getAllProperties(curBase);
            assertEquals(2, properties.length); // has class property as well as expected  in it
            final IPropertySymbol  propSymbol = (IPropertySymbol) findSymbol(i % 2 == 0 ? "myBean3" : "myBean2", properties);

            // i is even
            if (i % 2 == 0)
            {
                assertEquals("myBean3", propSymbol.getName());
                assertEquals("Lcom.test.TestBean3;", propSymbol.getTypeDescriptor().getTypeSignature());
                curBase = propSymbol;
            }
            // i is odd
            else
            {
                assertEquals("myBean2", propSymbol.getName());
                assertEquals("Lcom.test.TestBean2;", propSymbol.getTypeDescriptor().getTypeSignature());
                curBase = propSymbol;
            }
            curBase = propSymbol;
        }
    }
    
    /**
     * Test the case where a bean property is of type Map
     */
    public void testBeanWithMapProperty()
    {
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("BeanWithMapProp");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testBeanWithMapProp);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        ISymbol[]  properties = propResolver.getAllProperties(symbol);

        // should be two properties: mapProp and class
        assertEquals(2, properties.length);
        assertNotNull(findSymbol("mapProp",properties));
        assertEquals(findSymbol("mapProp",properties).getName(),
                     propResolver.getProperty(symbol, "mapProp").getName());
        
        final IPropertySymbol  mapProp = (IPropertySymbol) propResolver.getProperty(symbol, "mapProp");
        assertEquals("Lcom.test.MapBean;", mapProp.getTypeDescriptor().getTypeSignature());
        
        properties = propResolver.getAllProperties(mapProp);
        assertEquals(0, properties.length);
        
        final IPropertySymbol unboundedProp = (IPropertySymbol) propResolver.getProperty(mapProp, "foo");
        assertEquals(TypeConstants.TYPE_JAVAOBJECT, unboundedProp.getTypeDescriptor().getTypeSignature());
    }
    
    /**
     * Verify that bean properties of type array are resolved properly
     * using the getProperty(base, offset) method.
     */
    public void testBeanWithArrayProperty()
    {
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("BeanWithArrayProp");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testBean1Type);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();

        // test array of strings property
        {
            final ISymbol arrayOfStringsProperty = 
                propResolver.getProperty(symbol, "stringArrayProperty");
            
            assertTrue(arrayOfStringsProperty instanceof IPropertySymbol);
            final IPropertySymbol arrayOfStringsPropertySymbol = 
                (IPropertySymbol) arrayOfStringsProperty;
            assertEquals(Signature.createArraySignature(TypeConstants.TYPE_STRING, 1), arrayOfStringsPropertySymbol.getTypeDescriptor().getTypeSignature());
            assertTrue(arrayOfStringsPropertySymbol.getTypeDescriptor().isArray());
            assertEquals(TypeConstants.TYPE_STRING, arrayOfStringsPropertySymbol.getTypeDescriptor().getArrayElement().getTypeDescriptor().getTypeSignature());
    
            // now the real property resolver test
            // get an array element
            final ISymbol  arrayElement = propResolver.getProperty(arrayOfStringsPropertySymbol, 0);
            assertTrue(arrayElement instanceof IObjectSymbol);
            final IObjectSymbol arrayElementSymbol = (IObjectSymbol) arrayElement;
            assertNotNull(((IJavaTypeDescriptor2)arrayElementSymbol.getTypeDescriptor()).getType());
            assertEquals(TypeConstants.TYPE_STRING, arrayElementSymbol.getTypeDescriptor().getTypeSignature());
        }
        
        // test array of int property
        {
            final ISymbol arrayOfIntProperty = 
                propResolver.getProperty(symbol, "intArrayProperty");
            
            assertTrue(arrayOfIntProperty instanceof IPropertySymbol);
            final IPropertySymbol arrayOfIntPropertySymbol = 
                (IPropertySymbol) arrayOfIntProperty;
            assertEquals(Signature.createArraySignature(Signature.SIG_INT, 1), arrayOfIntPropertySymbol.getTypeDescriptor().getTypeSignature());
            assertTrue(arrayOfIntPropertySymbol.getTypeDescriptor().isArray());
            assertEquals(Signature.SIG_INT, arrayOfIntPropertySymbol.getTypeDescriptor().getArrayElement().getTypeDescriptor().getTypeSignature());
    
            // now the real property resolver test
            // get an array element
            final ISymbol  arrayElement = propResolver.getProperty(arrayOfIntPropertySymbol, 0);
            assertTrue(arrayElement instanceof IObjectSymbol);
            final IObjectSymbol arrayElementSymbol = (IObjectSymbol) arrayElement;
            // type will be null since int has no corresponding IType (not an object)
            assertNull(((IJavaTypeDescriptor2)arrayElementSymbol.getTypeDescriptor()).getType());
            assertEquals(Signature.SIG_INT, arrayElementSymbol.getTypeDescriptor().getTypeSignature());
        }
        
        // test array of array of strings
        {
            final ISymbol arrayOfIntProperty = 
                propResolver.getProperty(symbol, "arrayOfArrayOfStringProperty");
            
            assertTrue(arrayOfIntProperty instanceof IPropertySymbol);
            final IPropertySymbol arrayOfIntPropertySymbol = 
                (IPropertySymbol) arrayOfIntProperty;
            assertEquals(Signature.createArraySignature(TypeConstants.TYPE_STRING, 2),
                         arrayOfIntPropertySymbol.getTypeDescriptor().getTypeSignature());
            assertTrue(arrayOfIntPropertySymbol.getTypeDescriptor().isArray());
            assertEquals(
                         Signature.createArraySignature(TypeConstants.TYPE_STRING,1), 
                         arrayOfIntPropertySymbol.getTypeDescriptor().getArrayElement().getTypeDescriptor().getTypeSignature()
                        );
    
            // now the real property resolver test
            // get an array element
            final ISymbol  arrayElement = propResolver.getProperty(arrayOfIntPropertySymbol, 0);
            assertTrue(arrayElement instanceof IObjectSymbol);
            final IObjectSymbol arrayElementSymbol = (IObjectSymbol) arrayElement;
            assertNotNull(((IJavaTypeDescriptor2)arrayElementSymbol.getTypeDescriptor()).getType());
            assertEquals(
                            Signature.createArraySignature(TypeConstants.TYPE_STRING,1), 
                            arrayElementSymbol.getTypeDescriptor().getTypeSignature()
                        );
            
            // the elements of the array are arrays themselves
            assertTrue(arrayElementSymbol.getTypeDescriptor().isArray());
            final ISymbol arrayElementElement =
                propResolver.getProperty(arrayElementSymbol, 0);
            assertTrue(arrayElementElement instanceof IObjectSymbol);
            final IObjectSymbol arrayElementElementSymbol = 
                (IObjectSymbol) arrayElementElement;
            assertNotNull(((IJavaTypeDescriptor2)arrayElementElementSymbol.getTypeDescriptor()).getType());
            assertEquals(TypeConstants.TYPE_STRING, arrayElementElementSymbol.getTypeDescriptor().getTypeSignature());
        }
    }
    
    /**
     * Test an unconstrained Map bean
     */
    public void testListBeanInstanceSymbol()
    {
        final IBeanInstanceSymbol symbol = 
            SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myListBean");
        final IJavaTypeDescriptor2 typeDesc = 
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testListBeanType);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = 
            new DefaultDTPropertyResolver();
        final ISymbol[]  properties = propResolver.getAllProperties(symbol);

        // should have no properties since a list won't be treated like
        // anything but a list
        assertEquals(0, properties.length);

        // but being that it's an unbounded List, it may have  any number
        // of arbitrary "unconstrained" properties a different indices
        for (int i = 0; i < 25; i++)
        {
            final ISymbol someProperty1 = propResolver.getProperty(symbol, i);
            assertNotNull(someProperty1);
            assertTrue(someProperty1 instanceof IPropertySymbol);
            // completely unconstrained properties should come back as java object
            assertEquals(TypeConstants.TYPE_JAVAOBJECT, 
                    ((IPropertySymbol)someProperty1).getTypeDescriptor().getTypeSignature());
        }
    }
    
    /**
     * Test a property on a bean that is a list
     */
    public void testBeanWithListProperty()
    {
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("beanWithListProp");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testBeanWithListPropType);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        ISymbol[]  properties = propResolver.getAllProperties(symbol);

        // should be just one property plus Object.class
        assertEquals(2, properties.length);
        assertNotNull(findSymbol("listProp", properties));
        assertEquals(findSymbol("listProp", properties).getName(),
                propResolver.getProperty(symbol, "listProp").getName());
        
        final IPropertySymbol  listProp = (IPropertySymbol) propResolver.getProperty(symbol, "listProp");
        assertEquals("Lcom.test.ListBean;", listProp.getTypeDescriptor().getTypeSignature());
        
        properties = propResolver.getAllProperties(listProp);
        
        // has isEmpty and one bean props
        assertEquals(0, properties.length);
        
        final IPropertySymbol unboundedProp = (IPropertySymbol) propResolver.getProperty(listProp, 0);
        assertEquals(TypeConstants.TYPE_JAVAOBJECT, unboundedProp.getTypeDescriptor().getTypeSignature());
        
        // list base symbols do not have non-numeric keys
        assertNull(propResolver.getProperty(listProp, "anyKey"));
    }
    
    public void testGenericListProperty()
    {
        final IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("beanWithListProp");
        final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testBeanWithGenericProperties);
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        ISymbol[]  properties = propResolver.getAllProperties(symbol);

        // should be just one property plus Object.class
        assertEquals(3, properties.length);
        assertNotNull(findSymbol("listOfStrings", properties));
        assertEquals(findSymbol("listOfStrings", properties).getName(),
                propResolver.getProperty(symbol, "listOfStrings").getName());
        
        final IPropertySymbol  listProp = (IPropertySymbol) propResolver.getProperty(symbol, "listOfStrings");
        assertEquals(TypeConstants.TYPE_LIST, listProp.getTypeDescriptor().getTypeSignature());
        
        properties = propResolver.getAllProperties(listProp);
        
        // has isEmpty and one bean props
        assertEquals(0, properties.length);
        
        final IPropertySymbol unboundedProp = (IPropertySymbol) propResolver.getProperty(listProp, 0);
        assertEquals(TypeConstants.TYPE_STRING, unboundedProp.getTypeDescriptor().getTypeSignature());
    }
    
    public void testBoundedTypeDescriptor()
    {
    	final IComponentSymbol symbol = 
    		SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName("componentSymbol");
        final IBoundedMapTypeDescriptor typeDesc = 
        	SymbolFactory.eINSTANCE.createIBoundedMapTypeDescriptor();
        typeDesc.setMapSource(new HashMap<String, String>());
        symbol.setTypeDescriptor(typeDesc);
        
        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        final ISymbol propSymbol = propResolver.getProperty(symbol, "anyProp");
        
        assertNotNull(propSymbol);
        assertTrue(propSymbol instanceof IPropertySymbol);
        assertEquals(TypeConstants.TYPE_JAVAOBJECT, 
        	((IPropertySymbol)propSymbol).getTypeDescriptor().getTypeSignature());
    }

    public void testDottedPropertyNames() throws Exception
    {
    	checkDottedBundleNames();
    	// TODO: should add coverage for dotted, non-bundles...
    }
    
    @SuppressWarnings("unchecked")
    private void checkDottedBundleNames() throws Exception
    {
        final Map map = 
        	ResourceBundleMapSourceFactory.getResourceBundleMapSource
        		(_jdtTestEnvironment.getProjectEnvironment().getTestProject()
        				, "bundles.bundle1");
        assertNotNull(map);
        assertEquals(3, map.size());

        final IMapTypeDescriptor typeDesc = 
            SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
        typeDesc.setMapSource(map);
        final IComponentSymbol symbol = 
            SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName("dottedMapSource");
        symbol.setTypeDescriptor(typeDesc);

        final DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        {
	        ISymbol oneDot = propResolver.getProperty(symbol, "one");
	        assertNotNull(oneDot);
	        assertTrue(oneDot instanceof IPropertySymbol);
	        assertTrue(((IPropertySymbol)oneDot).isIntermediate());

	        oneDot = propResolver.getProperty(symbol, "one.dot");
	        assertNotNull(oneDot);
	        assertTrue(oneDot instanceof IPropertySymbol);
	        assertFalse(((IPropertySymbol)oneDot).isIntermediate());
        }

        {
        	ISymbol twoDots = propResolver.getProperty(symbol, "two");
            assertNotNull(twoDots);
            assertTrue(twoDots instanceof IPropertySymbol);
	        assertTrue(((IPropertySymbol)twoDots).isIntermediate());

        	twoDots = propResolver.getProperty(symbol, "two.dot");
            assertNotNull(twoDots);
            assertTrue(twoDots instanceof IPropertySymbol);
	        assertTrue(((IPropertySymbol)twoDots).isIntermediate());

        	twoDots = propResolver.getProperty(symbol, "two.dot.property");
            assertNotNull(twoDots);
            assertTrue(twoDots instanceof IPropertySymbol);
	        assertFalse(((IPropertySymbol)twoDots).isIntermediate());

        }
    }
    
    private ISymbol findSymbol(final String name, final ISymbol[] symbols)
    {
        for (final ISymbol symbol : symbols)
        {
            if (symbol.getName().equals(name))
            {
                return symbol;
            }
        }
        
        return null;
     }
}
