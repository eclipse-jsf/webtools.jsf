package org.eclipse.jst.jsf.context.symbol.tests;

import java.util.HashMap;

import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor;

/**
 * 
 * @author cbateman
 *
 */
public class TestTypeCoercion extends ModelBaseTestCase 
{
    private final static String     packageName = "com.test";
    private IBeanInstanceSymbol     _testMapBeanSymbol;
    
    protected void setUp() throws Exception 
    {
        super.setUp();

        _testMapBeanSymbol =
            setupBeanProperty(ContextSymbolTestPlugin.getDefault().getBundle(),
                    "/testdata/TestBeanMap.java.data", packageName, 
                    "TestBeanMap", new HashMap());
    }

    /**
     * Test Sanity of setup
     */
    public void testSanity()
    {
        assertNotNull(_testMapBeanSymbol);
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
}
