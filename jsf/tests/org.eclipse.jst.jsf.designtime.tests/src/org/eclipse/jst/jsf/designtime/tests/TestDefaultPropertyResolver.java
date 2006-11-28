package org.eclipse.jst.jsf.designtime.tests;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.provisional.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.provisional.el.DefaultDTPropertyResolver;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Unit tests for the default property resolver
 * @author cbateman
 *
 */
public class TestDefaultPropertyResolver extends TestCase 
{
    private JDTTestEnvironment              _jdtTestEnvironment;
    private JSFFacetedTestEnvironment       _jsfFactedTestEnvironment;

    private IType                           _testBean1Type;
    private IType _testMapBean1Type;
    private IType _testBean2Type;
    private IType _testBean3Type;
    private IType _testBeanWithMapProp;
    
    private final static String     SRC_FOLDER_NAME = "src";
    private final static String     PACKAGE_NAME = "com.test";
    private final static String     TESTBEAN1_NAME = "TestBean1";
    private final static String     TESTBEAN2_NAME = "TestBean2";
    private final static String     TESTBEAN3_NAME = "TestBean3";
    private final static String     MAPBEAN_NAME = "MapBean";
    private final static String     BEANWITHMAPPROP_NAME = "BeanWithMapProp";  
    
    
    protected void setUp() throws Exception 
    {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDefaultBeanSymbolSourceProvider_"+getName());
        projectTestEnvironment.createProject();
//        _facesConfigFile = (IFile) projectTestEnvironment.
//            loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle(),
//                                  "/testdata/faces-config.xml.data", 
//                                  "/WEB-INF/faces-config.xml");

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize();
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);

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

    }
    
    /**
     * Test precondition sanity
     */
    public void testSanity()
    {
        JDTBeanIntrospector  beanIntrospector = new JDTBeanIntrospector(_testBean1Type);
        Map props = beanIntrospector.getProperties();
        assertEquals(2, props.size());
        assertTrue(props.containsKey("stringProp1"));
        assertTrue(props.containsKey("booleanIsProp1"));
        
        beanIntrospector = new JDTBeanIntrospector(_testMapBean1Type);
        props = beanIntrospector.getProperties();
        // has 1 as a bean: isEmpty -> empty property
        assertEquals(1, props.size());

        beanIntrospector = new JDTBeanIntrospector(_testBean2Type);
        props = beanIntrospector.getProperties();
        // two props: one TestBean3
        assertEquals(1, props.size());
        
        beanIntrospector = new JDTBeanIntrospector(_testBean3Type);
        props = beanIntrospector.getProperties();
        // one props: one of type TestBean2
        assertEquals(1, props.size());
        
        beanIntrospector = new JDTBeanIntrospector(_testBeanWithMapProp);
        props = beanIntrospector.getProperties();
        // one props: one of type Map
        assertEquals(1, props.size());
    }
    
    /**
     * Test a basic simple bean symbol to see if we resolve properties correctly
     */
    public void testBeanInstanceSymbol()
    {
        IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myBean");
        IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testBean1Type);
        symbol.setTypeDescriptor(typeDesc);
        
        DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        ISymbol[]  properties = propResolver.getAllProperties(symbol);
        assertEquals(2, properties.length);
        Map  checkProps = new HashMap();
        for (int i = 0; i < properties.length; i++)
        {
            checkProps.put(properties[i].getName(), properties[i]);
        }
        
        assertTrue(checkProps.containsKey("stringProp1"));
        assertTrue(checkProps.containsKey("booleanIsProp1"));
        
        {
            ISymbol stringProp1 = propResolver.getProperty(symbol, "stringProp1");
            assertNotNull(stringProp1);
            assertTrue(stringProp1 instanceof IPropertySymbol);
            assertTrue(((IPropertySymbol)stringProp1).getTypeDescriptor().getTypeSignature().equals(TypeConstants.TYPE_STRING));
            IPropertySymbol mapStringProp1 = (IPropertySymbol) checkProps.get("stringProp1");
            assertEquals(((IPropertySymbol)stringProp1).getTypeDescriptor().getTypeSignature(), 
                            mapStringProp1.getTypeDescriptor().getTypeSignature());
        }
        
        {
            ISymbol booleanIsProp = propResolver.getProperty(symbol, "booleanIsProp1");
            assertNotNull(booleanIsProp);
            assertTrue(booleanIsProp instanceof IPropertySymbol);
            assertTrue(((IPropertySymbol)booleanIsProp).getTypeDescriptor().getTypeSignature().equals(Signature.SIG_BOOLEAN));
            IPropertySymbol mapBooleanProp = (IPropertySymbol) checkProps.get("booleanIsProp1");
            assertEquals(((IPropertySymbol)booleanIsProp).getTypeDescriptor().getTypeSignature(), 
                         mapBooleanProp.getTypeDescriptor().getTypeSignature());
        }
    }
    
    /**
     * Test an unconstrained Map bean
     */
    public void testMapBeanInstanceSymbol()
    {
        IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName("myMapBean");
        IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        typeDesc.setType(_testMapBean1Type);
        symbol.setTypeDescriptor(typeDesc);
        
        DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        ISymbol[]  properties = propResolver.getAllProperties(symbol);
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
            ISymbol someProperty1 = propResolver.getProperty(symbol, unconstrainedPropName);
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
        final Map mapSource = new HashMap();
        mapSource.put("prop1", "propValue1");
        mapSource.put("prop2", "propValue2");
        mapSource.put("dotted.property", "dottedPropertyValue");
        typeDesc.setMapSource(mapSource);
        symbol.setTypeDescriptor(typeDesc);
        
        DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        ISymbol[]  properties = propResolver.getAllProperties(symbol);

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
            for (int i = 0; i < properties.length; i++)
            {
                // make sure the property's name matches a value in map source
                // for the dotted one, we make an exception
                assertTrue(mapSource.containsKey(properties[i].getName())
                            || (properties[i].getName().startsWith("dotted")));
                // should be a property symbol
                assertTrue(properties[i] instanceof IPropertySymbol);
                final IPropertySymbol propSymbol = (IPropertySymbol) properties[i];
                
                // get the symbol by name
                ISymbol  symbolById = propResolver.getProperty(symbol, propSymbol.getName());
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
        ISymbol symbolById = propResolver.getProperty(symbol, "dotted");
        assertNotNull(symbolById);
        assertTrue(((IPropertySymbol)symbolById).isIntermediate());
        ISymbol dottedProp = propResolver.getProperty(symbolById, "property");
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
            assertEquals(1, properties.length);
            
            // check props
            final Map  gotProps  = new HashMap();
            
            for (int i = 0; i < properties.length; i++)
            {
                IPropertySymbol propSymbol = (IPropertySymbol) properties[i];
                assertEquals(propSymbol.getTypeDescriptor().getTypeSignature(),
                        ((IObjectSymbol)propResolver.getProperty(symbol,propSymbol.getName()))
                                                    .getTypeDescriptor().getTypeSignature());
                gotProps.put(propSymbol.getName(), propSymbol);
            }
            
            assertTrue(gotProps.containsKey("myBean3"));
        }
        // next run recursively on myBean3 10 props deep
        ISymbol curBase = symbol;
        // note, when i is even, the property is myBean3 on TestBean2
        // when i is odd, the property is myBean2 on TestBean3
        for (int i = 0; i < 10; i++)
        {
            final ISymbol[] properties = propResolver.getAllProperties(curBase);
            assertEquals(1, properties.length);
            final IPropertySymbol  propSymbol = (IPropertySymbol) properties[0];

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
        
        DefaultDTPropertyResolver propResolver = new DefaultDTPropertyResolver();
        ISymbol[]  properties = propResolver.getAllProperties(symbol);

        // should be just one property
        assertEquals(1, properties.length);
        assertEquals(properties[0].getName(), "mapProp");
        assertEquals(properties[0].getName(),
                     propResolver.getProperty(symbol, "mapProp").getName());
        
        IPropertySymbol  mapProp = (IPropertySymbol) propResolver.getProperty(symbol, "mapProp");
        assertEquals("Lcom.test.MapBean;", mapProp.getTypeDescriptor().getTypeSignature());
        
        properties = propResolver.getAllProperties(mapProp);
        assertEquals(0, properties.length);
        
        IPropertySymbol unboundedProp = (IPropertySymbol) propResolver.getProperty(mapProp, "foo");
        assertEquals(TypeConstants.TYPE_JAVAOBJECT, unboundedProp.getTypeDescriptor().getTypeSignature());
    }
}
