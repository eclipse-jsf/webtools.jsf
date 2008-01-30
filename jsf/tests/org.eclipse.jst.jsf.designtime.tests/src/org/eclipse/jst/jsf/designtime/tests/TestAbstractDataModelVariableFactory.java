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

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.symbols.DataModelVariableTestFacade;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestAbstractDataModelVariableFactory extends TestCase 
{
    private JSFFacetedTestEnvironment   _jsfFacetedTestEnvironment;
    private JDTTestEnvironment          _jdtTestEnvironment;
    

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestAbstractDataModelVariableFactory_"+getName());
        projectTestEnvironment.createProject(false);

        _jsfFacetedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFacetedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        
        try
        {
            final IProject project = _jdtTestEnvironment.getJavaProject().getProject();
            project.close(null);
            project.delete(true, null);
        }
        catch (CoreException ce)
        {
            ce.printStackTrace(System.err);
        }
    }

    public final void testCreateArraySymbol() 
    {
        // test array of int.  Should yield a scalar symbol for int
        createAndVerifyArraySymbol("intArray", Signature.SIG_INT, 1, Signature.SIG_INT, 0);
        // test array of String.  Should yield a string
        createAndVerifyArraySymbol("stringArray", TypeConstants.TYPE_STRING, 1, TypeConstants.TYPE_STRING, 0);
        // test an array of array of strings.  Should yield an array of Strings
        createAndVerifyArraySymbol("stringArrayArray", TypeConstants.TYPE_STRING, 2, TypeConstants.TYPE_STRING, 1);
    }

    private ISymbol createAndVerifyArraySymbol(final String name, final String typeSignature, final int arrayCount, final String expectedResultSignature, final int expectedArrayCount)
    {
        final DataModelVariableTestFacade testFacade = new DataModelVariableTestFacade();
        
        final ISymbol symbol = testFacade.testCreateArraySymbol(name, Signature.createArraySignature(typeSignature, arrayCount), _jdtTestEnvironment.getJavaProject());
        assertTrue(symbol instanceof IInstanceSymbol);
        final IInstanceSymbol instanceSymbol = (IInstanceSymbol) symbol;
        assertTrue(instanceSymbol.getTypeDescriptor() instanceof IJavaTypeDescriptor2);
        final IJavaTypeDescriptor2 typeDesc = (IJavaTypeDescriptor2) instanceSymbol.getTypeDescriptor();
        assertEquals(name, symbol.getName());
        assertEquals(Signature.createArraySignature(expectedResultSignature,expectedArrayCount),typeDesc.getTypeSignature());
        assertEquals(expectedArrayCount, typeDesc.getArrayCount());
       
        return symbol;
    }
    

    public final void testCreateFromList() 
    {
        final String[]  stringArgType = new String[] {TypeConstants.TYPE_STRING};
        final String[]  listOfStringArgType = 
            new String[] {"Ljava.util.List<Ljava.lang.String;>;"};
        
        final DataModelVariableTestFacade testFacade = new DataModelVariableTestFacade();

        // test non-parameterized list
        ValueType valueType = new ValueType(TypeConstants.TYPE_LIST, new String[0], new String[0], new String[0],false, IAssignable.ASSIGNMENT_TYPE_RHS);
        ISymbol symbol = testFacade.testCreateFromList("rawList", valueType, _jdtTestEnvironment.getJavaProject());
        {
            // should create the same as passing directly to default
            final ISymbol compareSymbol = testFacade.createDefaultSymbol("rawListDefault");
            assertTrue(symbol instanceof IComponentSymbol);
            assertTrue(compareSymbol instanceof IComponentSymbol);
         
            final IComponentSymbol s1 = (IComponentSymbol) symbol;
            final IComponentSymbol s2 = (IComponentSymbol) compareSymbol;
            assertSame(s1, s2);
        }
        
        // test list of strings
        valueType = new ValueType(TypeConstants.TYPE_LIST, stringArgType, new String[0], new String[0], false, IAssignable.ASSIGNMENT_TYPE_RHS);
        symbol = testFacade.testCreateFromList("string", valueType, _jdtTestEnvironment.getJavaProject());
        assertTrue(symbol instanceof IComponentSymbol);
        assertEquals(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, ((IComponentSymbol)symbol).getRuntimeSource());
        assertEquals(TypeConstants.TYPE_STRING, ((IComponentSymbol)symbol).getTypeDescriptor().getTypeSignature());
        assertEquals(0, ((IComponentSymbol)symbol).getTypeDescriptor().getTypeParameterSignatures().size());
        
        // test list of list of strings
        valueType = new ValueType(TypeConstants.TYPE_LIST, listOfStringArgType, new String[0], new String[0], false, IAssignable.ASSIGNMENT_TYPE_RHS);
        symbol = testFacade.testCreateFromList("listOfString", valueType, _jdtTestEnvironment.getJavaProject());
        assertTrue(symbol instanceof IComponentSymbol);
        assertEquals(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, ((IComponentSymbol)symbol).getRuntimeSource());
        assertEquals(TypeConstants.TYPE_LIST, ((IComponentSymbol)symbol).getTypeDescriptor().getTypeSignature());
        assertEquals(1, ((IComponentSymbol)symbol).getTypeDescriptor().getTypeParameterSignatures().size());
        assertEquals("Ljava.lang.String;", ((IComponentSymbol)symbol).getTypeDescriptor().getTypeParameterSignatures().get(0));
        // next level: get the nested string in the list
        valueType = IObjectSymbolBasedValueType.getInstance(symbol);
        symbol = testFacade.testCreateFromList("subListOfString", valueType, _jdtTestEnvironment.getJavaProject());
        assertTrue(symbol instanceof IComponentSymbol);
        assertEquals(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, ((IComponentSymbol)symbol).getRuntimeSource());
        assertEquals(TypeConstants.TYPE_STRING, ((IComponentSymbol)symbol).getTypeDescriptor().getTypeSignature());
        assertEquals(0, ((IComponentSymbol)symbol).getTypeDescriptor().getTypeParameterSignatures().size());
    }
    
    private void assertSame(final IComponentSymbol s1, final IComponentSymbol s2)
    {
        // ensure we don't pass the same ref twice by accident
        assertFalse(s1 == s2);
        assertEquals(s1.getRuntimeSource(), s2.getRuntimeSource());
        assertEquals(s1.getDetailedDescription(), s2.getDetailedDescription());
        assertEquals(s1.getTypeDescriptor().getTypeSignature(), s2.getTypeDescriptor().getTypeSignature());
    }
    
    public final void testCreateScalarSymbol() 
    {
        createAndVerifyScalar("String", TypeConstants.TYPE_STRING);
        createAndVerifyScalar("List", TypeConstants.TYPE_LIST);
        createAndVerifyScalar("ListOfString", "Ljava.util.List<Ljava.lang.String;>;");
        createAndVerifyScalar("ArrayOfInteger", Signature.createArraySignature(TypeConstants.TYPE_BOXED_INTEGER, 1));
    }
    
    private void createAndVerifyScalar(final String name, final String signature)
    {
        final DataModelVariableTestFacade testFacade = new DataModelVariableTestFacade();
        final ISymbol symbol =  testFacade.testCreateScalarSymbol("", signature, _jdtTestEnvironment.getJavaProject());
        assertTrue(symbol instanceof IComponentSymbol);
        final IComponentSymbol compSymbol = (IComponentSymbol) symbol;
        assertEquals(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, ((IComponentSymbol)symbol).getRuntimeSource());
        assertEquals(Signature.getTypeErasure(signature), compSymbol.getTypeDescriptor().getTypeSignature());
        
        final String[]  typeArgs = Signature.getTypeArguments(signature);
        for (int i = 0; i < typeArgs.length; i++)
        {
            assertEquals(typeArgs[i], compSymbol.getTypeDescriptor().getTypeParameterSignatures().get(i));
        }
    }

    public final void testCreateDefaultSymbol() 
    {
        final DataModelVariableTestFacade testFacade = new DataModelVariableTestFacade();
        final ISymbol defaultSymbol = testFacade.createDefaultSymbol("foo");
        assertNotNull(defaultSymbol);
        assertEquals("foo", defaultSymbol.getName());
        assertTrue(defaultSymbol instanceof  IComponentSymbol);
        final IComponentSymbol symbol = (IComponentSymbol) defaultSymbol;
        assert(symbol.getTypeDescriptor() instanceof IBoundedMapTypeDescriptor);
        assertEquals(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, symbol.getRuntimeSource());
        assertNotNull(symbol.getDetailedDescription());
    }
    
    public final void testCreateFromType()
    {
        final DataModelVariableTestFacade testFacade = new DataModelVariableTestFacade();
        // test each of known branches against the construction method that's expected to be called
        
        // an array of something should yield the same thing as createArraySymbol
        {
            final String arraySig = "[Ljava.lang.String;";
            final ValueType valueType = new ValueType(arraySig, new String[0], new String[0], new String[0], false, IAssignable.ASSIGNMENT_TYPE_RHS);
            final ISymbol array1 = testFacade.createFromType("array", valueType, _jdtTestEnvironment.getJavaProject()); 
            final ISymbol array2 = testFacade.testCreateArraySymbol("array2", arraySig, _jdtTestEnvironment.getJavaProject());
            assertSame((IComponentSymbol)array1, (IComponentSymbol)array2);
        }
        
        // if it's a list should be the same as createFromList
        {
            final String listSig = TypeConstants.TYPE_LIST;
            final ValueType valueType = new ValueType(listSig, new String[0], new String[0], new String[0], false, IAssignable.ASSIGNMENT_TYPE_RHS);
            final ISymbol list1 = testFacade.createFromType("list", valueType, _jdtTestEnvironment.getJavaProject()); 
            final ISymbol list2 = testFacade.testCreateFromList("list2", valueType, _jdtTestEnvironment.getJavaProject());
            assertSame((IComponentSymbol)list1, (IComponentSymbol)list2);
        }
        
        // if it's a DataModel, should be the same as default
        {
            final String dataModelSig = TypeConstants.TYPE_DATA_MODEL;
            final ValueType valueType = new ValueType(dataModelSig, new String[0], new String[0], new String[0], false, IAssignable.ASSIGNMENT_TYPE_RHS);
            final ISymbol dataModel1 = testFacade.createFromType("dataModel", valueType, _jdtTestEnvironment.getJavaProject()); 
            final ISymbol dataModel2 = testFacade.createDefaultSymbol("dataModel2");
            assertSame((IComponentSymbol)dataModel1, (IComponentSymbol)dataModel2);
        }
        
        // if it's just some non-special object, like a String, then should be same a scalar
        {
            final String stringSig = TypeConstants.TYPE_STRING;
            final ValueType valueType = new ValueType(stringSig, new String[0], new String[0], new String[0], false, IAssignable.ASSIGNMENT_TYPE_RHS);
            final ISymbol scalar1 = testFacade.createFromType("scalar1", valueType, _jdtTestEnvironment.getJavaProject()); 
            final ISymbol scalar2 = testFacade.testCreateScalarSymbol("scalar2", stringSig, _jdtTestEnvironment.getJavaProject());
            assertSame((IComponentSymbol)scalar1, (IComponentSymbol)scalar2);
        }
    }
}
