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
package org.eclipse.jst.jsf.context.symbol.tests;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IBoundedListTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;

/**
 * 
 * @author cbateman
 *
 */
public class TestTypeCoercion extends ModelBaseTestCase 
{
    private final static String     packageName = "com.test";
    private IBeanInstanceSymbol     _testMapBeanSymbol;
    private IBeanInstanceSymbol     _testListBeanSymbol;
    private IBeanInstanceSymbol      _testBeanWithGenericProperties;
    private Map<String, IPropertySymbol>  _genericProperties;

    // used as the int argument to List.get(int)
    private final static ValueType  LIST_GETTER_ARG = new ValueType(Signature.SIG_INT, ValueType.ASSIGNMENT_TYPE_RHS);

    // used as a String argument to List.get(String)
    private final static ValueType  MAP_GETTER_ARG_STRING =   new ValueType(TypeConstants.TYPE_STRING, ValueType.ASSIGNMENT_TYPE_RHS);

    protected void setUp() throws Exception 
    {
        super.setUp();

        _testMapBeanSymbol =
            setupBeanProperty(ContextSymbolTestPlugin.getDefault().getBundle(),
                    "/testdata/TestBeanMap.java.data", packageName, 
                    "TestBeanMap", new HashMap());
        
        _testListBeanSymbol =
            setupBeanProperty(ContextSymbolTestPlugin.getDefault().getBundle(),
                    "/testdata/MyListBean.java.data", packageName, 
                    "MyListBean", new HashMap());
        
        _genericProperties = new HashMap<String, IPropertySymbol>();
        
        _testBeanWithGenericProperties =
            setupBeanProperty(ContextSymbolTestPlugin.getDefault().getBundle(),
                    "/testdata/TestBeanWithGenericProperties.java.data", packageName, 
                    "TestBeanWithGenericProperties",_genericProperties);
    }

    /**
     * Test Sanity of setup
     */
    public void testSanity()
    {
        assertNotNull(_testMapBeanSymbol);
        assertNotNull(_testListBeanSymbol);
        assertNotNull(_testBeanWithGenericProperties);
    }
    
    /**
     * Test the basic semantics of a bean that implements Map
     */
    public void testBasicMapBean()
    {
        assertTrue(_testMapBeanSymbol.getTypeDescriptor().instanceOf(TypeConstants.TYPE_MAP));
        assertTrue(_testMapBeanSymbol.supportsCoercion(TypeConstants.TYPE_MAP));
        final ITypeDescriptor typeDesc = 
            _testMapBeanSymbol.coerce(TypeConstants.TYPE_MAP);
        assertNotNull(typeDesc);
        assertTrue(typeDesc.instanceOf(TypeConstants.TYPE_MAP));
        assertTrue(typeDesc instanceof IBoundedMapTypeDescriptor);
        assertTrue(((IBoundedMapTypeDescriptor)typeDesc).isUnboundedForType(TypeConstants.TYPE_MAP));
        final String testSymbolName = "chelsea";
        ISymbol symbol = 
            ((IBoundedMapTypeDescriptor)typeDesc).
                getUnboundedProperty(testSymbolName, TypeConstants.TYPE_MAP);
        
        assertEquals(testSymbolName, symbol.getName());
    }
    
    /**
     * Test basic bean coercion to a list
     */
    public void testBasicListBean()
    {
        assertTrue(_testListBeanSymbol.getTypeDescriptor().instanceOf(TypeConstants.TYPE_LIST));
        assertTrue(_testListBeanSymbol.supportsCoercion(TypeConstants.TYPE_LIST));
        final ITypeDescriptor typeDesc = 
            _testListBeanSymbol.coerce(TypeConstants.TYPE_LIST);
        assertNotNull(typeDesc);
        assertTrue(typeDesc.instanceOf(TypeConstants.TYPE_LIST));
        assertTrue(typeDesc instanceof IBoundedListTypeDescriptor);
        
        // a list can only be indexed numerically (i.e. get(int))
        assertTrue(((IBoundedListTypeDescriptor)typeDesc).isUnboundedForType(TypeConstants.TYPE_BOXED_INTEGER));
        final Integer symbolKey = Integer.valueOf(0);
        ISymbol symbol = 
            ((IBoundedListTypeDescriptor)typeDesc).
                getUnboundedProperty(symbolKey, TypeConstants.TYPE_BOXED_INTEGER);
        
        assertEquals(symbolKey.toString(), symbol.getName());

    }
    
    public void testListGetCall()
    {
        assertTrue(_testListBeanSymbol.getTypeDescriptor().instanceOf(TypeConstants.TYPE_LIST));
        assertTrue(_testListBeanSymbol.supportsCoercion(TypeConstants.TYPE_LIST));
        
        EList<ValueType> valueTypes = new BasicEList<ValueType>();
        valueTypes.add(LIST_GETTER_ARG);
        ISymbol symbol = _testListBeanSymbol.call("get", valueTypes, "listBean[0]");
        assertNotNull(symbol);
        assertTrue(symbol instanceof IPropertySymbol);
        assertEquals(TypeConstants.TYPE_JAVAOBJECT, ((IPropertySymbol)symbol).getTypeDescriptor().getTypeSignature());
    }
    
    public void testMapGetCall()
    {
        assertTrue(_testMapBeanSymbol.getTypeDescriptor().instanceOf(TypeConstants.TYPE_MAP));
        assertTrue(_testMapBeanSymbol.supportsCoercion(TypeConstants.TYPE_MAP));

        EList<ValueType> valueTypes = new BasicEList<ValueType>();
        valueTypes.add(MAP_GETTER_ARG_STRING);
        ISymbol symbol = _testMapBeanSymbol.call("get", valueTypes, "foo");
        assertNotNull(symbol);
        assertTrue(symbol instanceof IPropertySymbol);
        assertEquals(TypeConstants.TYPE_JAVAOBJECT, ((IPropertySymbol)symbol).getTypeDescriptor().getTypeSignature());
        
    }
    
    public void testListGetCallWithSimpleTypeArguments()
    {
        final EList<ValueType> valueTypes = new BasicEList<ValueType>();
        valueTypes.add(LIST_GETTER_ARG);

        final IPropertySymbol propSymbol = 
            _genericProperties.get("listOfStrings");
        assertNotNull(propSymbol);
        assertTrue(propSymbol.supportsCoercion(TypeConstants.TYPE_LIST));
        assertFalse(propSymbol.supportsCoercion(TypeConstants.TYPE_MAP));
        assertEquals(TypeConstants.TYPE_LIST, propSymbol.getTypeDescriptor().getTypeSignature());
        assertEquals(1, propSymbol.getTypeDescriptor().getTypeParameterSignatures().size());
        assertEquals(TypeConstants.TYPE_STRING, propSymbol.getTypeDescriptor().getTypeParameterSignatures().get(0));
        
        ISymbol symbol = propSymbol.call("get", valueTypes, "listBean[0]");
        assertNotNull(symbol);
        assertTrue(symbol instanceof IPropertySymbol);
        assertEquals(TypeConstants.TYPE_STRING, ((IPropertySymbol)symbol).getTypeDescriptor().getTypeSignature());
    }
    
    public void testMapGetCallWithSimpleTypeArguments()
    {
        final EList<ValueType> valueTypes = new BasicEList<ValueType>();
        valueTypes.add(MAP_GETTER_ARG_STRING);

        final IPropertySymbol propSymbol = 
            _genericProperties.get("mapOfStringsKeyedByString");
        assertNotNull(propSymbol);
        assertTrue(propSymbol.supportsCoercion(TypeConstants.TYPE_MAP));
        assertFalse(propSymbol.supportsCoercion(TypeConstants.TYPE_LIST));
        assertEquals(TypeConstants.TYPE_MAP, propSymbol.getTypeDescriptor().getTypeSignature());
        assertEquals(2, propSymbol.getTypeDescriptor().getTypeParameterSignatures().size());
        assertEquals(TypeConstants.TYPE_STRING, propSymbol.getTypeDescriptor().getTypeParameterSignatures().get(0));
        assertEquals(TypeConstants.TYPE_STRING, propSymbol.getTypeDescriptor().getTypeParameterSignatures().get(1));
        
        ISymbol symbol = propSymbol.call("get", valueTypes, "mapBean['someKey']");
        assertNotNull(symbol);
        assertTrue(symbol instanceof IPropertySymbol);
        assertEquals(TypeConstants.TYPE_STRING, ((IPropertySymbol)symbol).getTypeDescriptor().getTypeSignature());

    }
}
