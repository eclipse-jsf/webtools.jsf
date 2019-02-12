/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.context.symbol.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jface.text.Document;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.text.edits.TextEdit;


/**
 * Tests the java type descriptor's ability to react correctly to changes
 * in its underlying type.  This is very important when type and symbol caching
 * are enabled.
 * 
 * @author cbateman
 *
 */
public class TestIJavaTypeDescriptor2_ChangeStability extends ModelBaseTestCase 
{
    private IBeanInstanceSymbol     _testBean1Symbol;
    private IBeanInstanceSymbol     _testBean2Symbol;

    private Map<String, IPropertySymbol>               _beanProperties;
    private Map<String, IBeanMethodSymbol>             _bean1Methods;
    private Map<String, IBeanMethodSymbol>             _bean2Methods;
    private Map<String, IPropertySymbol>               _bean2Properties;

    private final static String packageName1 = "com.test";
    private final static String testBeanName1 = "TestBean1";
    private final static String testBeanName2 = "TestBean2";

    protected void setUp() throws Exception 
    {
        super.setUp();

        // load ITestBean2 first due to later dependencies
        loadSourceClass(ContextSymbolTestPlugin.getDefault().getBundle(), "/testdata/ITestBean2.java.data", packageName1, "ITestBean2");

        // load another bean first since others have a dependency on on it
        loadSourceClass(TestsPlugin.getDefault().getBundle(), "/testfiles/AnotherBean.java.data", packageName1, "AnotherBean");
        assertNotNull(_jdtTestEnvironment.getJavaProject().findType(packageName1+"."+"AnotherBean"));
        
        _beanProperties = new HashMap<String, IPropertySymbol>();
        _testBean1Symbol =
            setupBeanProperty(TestsPlugin.getDefault().getBundle(), 
                              "/testfiles/TestBean1.java.data", packageName1, 
                              testBeanName1, _beanProperties);
        _bean1Methods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(_bean1Methods, _testBean1Symbol);

        _bean2Methods = new HashMap<String, IBeanMethodSymbol>();
        _testBean2Symbol = 
            setupBeanMethods("/testdata/TestBean2.java.data", testBeanName2, _bean2Methods);
        _bean2Properties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean2Symbol, _bean2Properties);
    }

    
    private IBeanInstanceSymbol setupBeanMethods(String fileName, String beanClassName, Map<String, IBeanMethodSymbol> methods) throws Exception
    {
        loadSourceClass(ContextSymbolTestPlugin.getDefault().getBundle(), fileName, packageName1, beanClassName);

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
        populateMethodMap(methods, bean);
        return bean;
    }
    
    protected void tearDown() throws Exception 
    {
        super.tearDown();
        _jdtTestEnvironment.getProjectEnvironment().getTestProject().delete(true, null);
    }

    private final static String NON_BEAN_METHOD_CONTENTS = "public String myActionSource(){return \"foo\";}";
    private final static String NON_BEAN_METHOD_SIG = Signature.createMethodSignature(new String[0], TypeConstants.TYPE_STRING);
    /**
     * Check the affect of adding a non-bean (not a getter/setter match) on
     * the IJavaTypeDescriptor2
     */
    public void testAddNonBeanMethod() throws Exception
    {
        // pre-cond => basic setup
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        IMethod newMethod = type.createMethod(NON_BEAN_METHOD_CONTENTS, null, true, null);
        assertNotNull(newMethod);
        assertEquals(NON_BEAN_METHOD_SIG, TypeUtil.resolveMethodSignature(type, newMethod.getSignature()));
        assertTrue(newMethod.exists());
        
        // post-cond: bean has new method 
        Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(newMethods,_testBean1Symbol);
        assertEquals(_bean1Methods.size()+1, newMethods.size());
        // should be the same if we remove the new method
        assertNotNull(newMethods.remove("myActionSource"));
        compareMethodMaps(_bean1Methods, newMethods);
        
        // post-cond: properties are unchanged
        Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean1Symbol, newProperties);
        compareSymbolMaps(_beanProperties, newProperties);
    }
    
    public void testRemoveNonBeanMethod() throws Exception
    {
        // pre-cond => basic setup, delete a non-bean method
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        IMethod method = type.getMethod("get", new String[0]);
        assertNotNull(method);
        method.delete(true, null);
        assertFalse(method.exists());
        
        // post-cond: bean has one less method 
        Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(newMethods,_testBean1Symbol);
        assertEquals(_bean1Methods.size()-1, newMethods.size());
        assertTrue(newMethods.get("get") == null);
        // should be the same if we remove the  method from expected
        assertNotNull(_bean1Methods.remove("get"));
        compareMethodMaps(_bean1Methods, newMethods);
        
        // post-cond: properties are unchanged
        Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean1Symbol, newProperties);
        compareSymbolMaps(_beanProperties, newProperties);
    }
    
    private final static String     BEAN_PROPERTY_NAME = "anAddedStringProperty";
    private final static String     BEAN_METHOD_NAME = "getAnAddedStringProperty";
    private final static String     BEAN_METHOD_CONTENTS = 
        "public String "+BEAN_METHOD_NAME+"() {return \"foo\";}";
    private final static String     BEAN_METHOD_SIG = 
        Signature.createMethodSignature(new String[0], TypeConstants.TYPE_STRING);
    
    public void testAddBeanMethod() throws Exception
    {
        // pre-cond => basic setup
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        IMethod newMethod = type.createMethod(BEAN_METHOD_CONTENTS, null, true, null);
        assertNotNull(newMethod);
        assertTrue(newMethod.exists());
        assertEquals(BEAN_METHOD_SIG, TypeUtil.resolveMethodSignature(type, newMethod.getSignature()));
        
        {
            // post-cond: bean has new method 
            Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
            populateMethodMap(newMethods,_testBean1Symbol);
            assertEquals(_bean1Methods.size()+1, newMethods.size());
            // should be the same if we remove the new method
            assertNotNull(newMethods.remove(BEAN_METHOD_NAME));
            compareMethodMaps(_bean1Methods, newMethods);
        }
        
        {
            // post-cond: bean has a new property
            Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
            populatePropertyMap(_testBean1Symbol, newProperties);
            assertEquals(_beanProperties.size()+1, newProperties.size());
            // should be the same if we remove the new property
            IPropertySymbol prop = newProperties.remove(BEAN_PROPERTY_NAME);
            assertEquals(TypeConstants.TYPE_STRING, prop.getTypeDescriptor().getTypeSignature());
            compareSymbolMaps(_beanProperties, newProperties);
        }
    }
    
    private final static String  EXISTING_BEAN_PROPERTY_TO_REMOVE = "readonlyStringProperty";
    private final static String  EXISTING_BEAN_METHOD = "getReadonlyStringProperty";
    private final static String  EXISTING_BEAN_METHOD_SIG = BEAN_METHOD_SIG;

    public void testRemoveBeanMethod() throws Exception
    {
        // pre-cond => basic setup, delete a non-bean method
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        IMethod method = type.getMethod(EXISTING_BEAN_METHOD, new String[0]);
        assertNotNull(method);
        assertEquals(EXISTING_BEAN_METHOD_SIG, TypeUtil.resolveMethodSignature(type, method.getSignature()));
        assertTrue(method.exists());
        method.delete(true, null);
        assertFalse(method.exists());
        
        // post-cond: bean has one less method 
        Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(newMethods,_testBean1Symbol);
        assertEquals(_bean1Methods.size()-1, newMethods.size());
        assertTrue(newMethods.get(EXISTING_BEAN_METHOD) == null);
        // should be the same if we remove the  method from expected
        assertNotNull(_bean1Methods.remove(EXISTING_BEAN_METHOD));
        compareMethodMaps(_bean1Methods, newMethods);
        
        // post-cond: bean has one less property
        Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean1Symbol, newProperties);
        assertEquals(_beanProperties.size()-1, newProperties.size());
        // should be the same if we remove the same property from the expected
        IPropertySymbol prop = _beanProperties.remove(EXISTING_BEAN_PROPERTY_TO_REMOVE);
        assertEquals(TypeConstants.TYPE_STRING, prop.getTypeDescriptor().getTypeSignature());
        compareSymbolMaps(_beanProperties, newProperties);
    }

    // NOTE: it is ADifferentReadonlyPropertyName with a capital "A" because it is followed
    // by a capital letter (D), which by bean rules means don't lower-cap the property name
    private final static String  NEW_READONLY_BEAN_PROP_NAME = "ADifferentReadonlyPropertyName";
    private final static String  NEW_READONLY_BEAN_METHOD_NAME = "getADifferentReadonlyPropertyName";

    public void testRenameBeanProperty() throws Exception
    {
        // pre-cond => basic setup, delete a non-bean method
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        IMethod method = type.getMethod(EXISTING_BEAN_METHOD, new String[0]);
        assertNotNull(method);
        assertEquals(EXISTING_BEAN_METHOD_SIG, TypeUtil.resolveMethodSignature(type, method.getSignature()));
        assertTrue(method.exists());
        method.rename(NEW_READONLY_BEAN_METHOD_NAME, false, null);
        method = type.getMethod(NEW_READONLY_BEAN_METHOD_NAME, new String[0]);
        assertTrue(method.exists());
        
        // post-cond: bean has same number of methods
        Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(newMethods,_testBean1Symbol);
        assertEquals(_bean1Methods.size(), newMethods.size());
        assertTrue(newMethods.get(EXISTING_BEAN_METHOD) == null);
        // should be the same if we remove existing and add new one from newMethods
        assertNotNull(_bean1Methods.remove(EXISTING_BEAN_METHOD));
        IBeanMethodSymbol changedMethod = newMethods.get(NEW_READONLY_BEAN_METHOD_NAME);
        assertEquals(BEAN_METHOD_SIG, changedMethod.getSignature());
        _bean1Methods.put(NEW_READONLY_BEAN_METHOD_NAME, changedMethod);
        compareMethodMaps(_bean1Methods, newMethods);
        
        // post-cond: bean has same number of properties
        Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean1Symbol, newProperties);
        assertEquals(_beanProperties.size(), newProperties.size());
        // should be the same if we replace with the new property
        IPropertySymbol prop = newProperties.get(NEW_READONLY_BEAN_PROP_NAME);
        assertNotNull(prop);
        assertEquals(TypeConstants.TYPE_STRING, prop.getTypeDescriptor().getTypeSignature());
        assertNotNull(_beanProperties.remove(EXISTING_BEAN_PROPERTY_TO_REMOVE));
        _beanProperties.put(prop.getName(), prop);
        compareSymbolMaps(_beanProperties, newProperties);
    }
    
    private static final String  READONLY_BEAN_REPLACEMENT_METHOD =
        "public Integer "+EXISTING_BEAN_METHOD+"(){return new Integer(4);}";
    private static final String  READONLY_BEAN_REPLACEMENT_SIG =
        Signature.createMethodSignature(new String[0], TypeConstants.TYPE_BOXED_INTEGER);

    public void testChangeTypeOfGetter() throws Exception
    {
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        IMethod method = type.getMethod(EXISTING_BEAN_METHOD, new String[0]);
        assertNotNull(method);
        assertTrue(method.exists());
        
        // remove old, add new
        method.delete(true, null);
        assertFalse(method.exists());
        type.createMethod(READONLY_BEAN_REPLACEMENT_METHOD, null, true, null);
        assertTrue(method.exists());
        
        // post-cond: bean has the same number of methods
        // but, we need to replace the changed one in _bean1Methods
        // for the maps to equal
        Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(newMethods,_testBean1Symbol);
        assertEquals(_bean1Methods.size(), newMethods.size());
        assertNotNull(newMethods.get(EXISTING_BEAN_METHOD));
        IBeanMethodSymbol methodSymbol = _bean1Methods.remove(EXISTING_BEAN_METHOD);
        assertNotNull(methodSymbol);
        assertEquals(EXISTING_BEAN_METHOD_SIG, methodSymbol.getSignature());
        // should be the same if we swap the new method into the old map
        methodSymbol = newMethods.get(EXISTING_BEAN_METHOD);
        assertNotNull(methodSymbol);
        assertEquals(READONLY_BEAN_REPLACEMENT_SIG, methodSymbol.getSignature());
        _bean1Methods.put(EXISTING_BEAN_METHOD, methodSymbol);
        compareMethodMaps(_bean1Methods, newMethods);
        
        // post-cond: bean's type signature has changed
        // post-cond: same number of props
        Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean1Symbol, newProperties);
        assertEquals(_beanProperties.size(), newProperties.size());
        // should be the same if we replace the same property from the expected
        IPropertySymbol prop = _beanProperties.remove(EXISTING_BEAN_PROPERTY_TO_REMOVE);
        assertEquals(TypeConstants.TYPE_STRING, prop.getTypeDescriptor().getTypeSignature());
        prop = newProperties.get(EXISTING_BEAN_PROPERTY_TO_REMOVE);
        assertEquals(TypeConstants.TYPE_BOXED_INTEGER, prop.getTypeDescriptor().getTypeSignature());
        _beanProperties.put(EXISTING_BEAN_PROPERTY_TO_REMOVE, prop);
        compareSymbolMaps(_beanProperties, newProperties);
    }
    
    private final static String  WRITEONLY_BEAN_PROPERTY_NAME =
        "writeonlyStringProperty";
    private final static String  WRITEONLY_BEAN_REPLACEMENT_METHOD_NAME =
        "setWriteonlyStringProperty";
    private final static String  WRITEONLY_BEAN_REPLACMENT_CONTENTS =
        "public void "+WRITEONLY_BEAN_REPLACEMENT_METHOD_NAME
            +"(Integer newValue){}";
    private final static String  EXISTING_BEAN_SETTER_METHOD_SIG =
        Signature.createMethodSignature(new String[] {TypeConstants.TYPE_STRING}, Signature.SIG_VOID);
    private final static String  REPLACEMENT_BEAN_SETTER_METHOD_SIG = 
        Signature.createMethodSignature(new String[] {TypeConstants.TYPE_BOXED_INTEGER}, Signature.SIG_VOID);

    public void testChangeTypeOfSetter() throws Exception
    {
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        IMethod method = type.getMethod(WRITEONLY_BEAN_REPLACEMENT_METHOD_NAME, new String[]{"QString;"});
        assertNotNull(method);
        assertTrue(method.exists());

        // remove old, add new
        method.delete(true, null);
        assertFalse(method.exists());
        method = type.createMethod(WRITEONLY_BEAN_REPLACMENT_CONTENTS, null, true, null);
        assertTrue(method.exists());

        // post-cond: bean has the same number of methods
        // but, we need to replace the changed one in _bean1Methods
        // for the maps to equal
        Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(newMethods,_testBean1Symbol);
        assertEquals(_bean1Methods.size(), newMethods.size());
        assertNotNull(newMethods.get(WRITEONLY_BEAN_REPLACEMENT_METHOD_NAME));
        IBeanMethodSymbol methodSymbol =
            _bean1Methods.remove(WRITEONLY_BEAN_REPLACEMENT_METHOD_NAME);
        assertNotNull(methodSymbol);
        assertEquals(EXISTING_BEAN_SETTER_METHOD_SIG, methodSymbol.getSignature());
        // should be the same if we swap the new method into the old map
        methodSymbol = newMethods.get(WRITEONLY_BEAN_REPLACEMENT_METHOD_NAME);
        assertNotNull(methodSymbol);
        assertEquals(REPLACEMENT_BEAN_SETTER_METHOD_SIG, methodSymbol.getSignature());
        _bean1Methods.put(WRITEONLY_BEAN_REPLACEMENT_METHOD_NAME, methodSymbol);
        compareMethodMaps(_bean1Methods, newMethods);
        
        // post-cond: bean's type signature has changed
        // post-cond: same number of props
        Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean1Symbol, newProperties);
        assertEquals(_beanProperties.size(), newProperties.size());
        // should be the same if we replace the same property from the expected
        IPropertySymbol prop = _beanProperties.remove(WRITEONLY_BEAN_PROPERTY_NAME);
        assertEquals(TypeConstants.TYPE_STRING, prop.getTypeDescriptor().getTypeSignature());
        prop = newProperties.get(WRITEONLY_BEAN_PROPERTY_NAME);
        assertEquals(TypeConstants.TYPE_BOXED_INTEGER, prop.getTypeDescriptor().getTypeSignature());
        _beanProperties.put(WRITEONLY_BEAN_PROPERTY_NAME, prop);
        compareSymbolMaps(_beanProperties, newProperties);
    }
    
    public void testAddSuperType() throws Exception
    {
        IType type = _testBean1Symbol.getJavaTypeDescriptor().getType();
        changeSuperType(type, _testBean2Symbol.getJavaTypeDescriptor().getType());
        
        // post-cond: bean has the same number of methods
        // but, we need to replace the changed one in _bean1Methods
        // for the maps to equal
        Map<String, IBeanMethodSymbol> newMethods = new HashMap<String, IBeanMethodSymbol>();
        populateMethodMap(newMethods,_testBean1Symbol);
        Map<String, IBeanMethodSymbol> mergedMethodMap = new HashMap<String, IBeanMethodSymbol>();
        mergedMethodMap.putAll(_bean1Methods);
        mergedMethodMap.putAll(_bean2Methods);

        assertEquals(mergedMethodMap.size(), newMethods.size());
        compareMethodMaps(mergedMethodMap, newMethods);
        
        // post-cond: bean's type signature has changed
        // post-cond: same number of props
        Map<String, IPropertySymbol> newProperties = new HashMap<String, IPropertySymbol>();
        populatePropertyMap(_testBean1Symbol, newProperties);
        // should be the same if we merge the two expected maps
        Map<String, IPropertySymbol> mergedSymbolMap = new HashMap<String, IPropertySymbol>();
        mergedSymbolMap.putAll(_beanProperties);
        mergedSymbolMap.putAll(_bean2Properties);
        // bean 2 has a single bean property
        assertEquals(mergedSymbolMap.size(), newProperties.size());
        compareSymbolMaps(mergedSymbolMap, newProperties);
    }
    
    private void populateMethodMap(Map<String, IBeanMethodSymbol> methods, IBeanInstanceSymbol bean)
    {
        List methodList = bean.getMethods();
        for(final Iterator it = methodList.iterator(); it.hasNext();)
        {
            final IBeanMethodSymbol  method = (IBeanMethodSymbol) it.next();
            methods.put(method.getName(), method);
        }
    }
    
    private void compareSymbolMaps(Map<String, ? extends IObjectSymbol> expected, Map<String, ? extends IObjectSymbol> actual)
    {
        assertEquals(expected.size(), actual.size());
        
        for (String name : actual.keySet())
        {
            ITypeDescriptor expectedTypeDesc = expected.get(name).getTypeDescriptor();
            ITypeDescriptor actualTypeDesc = actual.get(name).getTypeDescriptor();
            
            assertTypeDescriptorsSame(expectedTypeDesc, actualTypeDesc);
        }
    }
    
    private void compareMethodMaps(Map<String, ? extends IMethodSymbol> expected, Map<String, ? extends IMethodSymbol> actual)
    {
        assertEquals(expected.size(), actual.size());
        
        for (String name : actual.keySet())
        {
            assertEquals(expected.get(name).getSignature(), actual.get(name).getSignature());
        }
    }
    
    private void assertTypeDescriptorsSame(ITypeDescriptor expectedTypeDesc, ITypeDescriptor actualTypeDesc)
    {
        assertEquals(expectedTypeDesc.getTypeSignature()
                , actualTypeDesc.getTypeSignature());
        if (expectedTypeDesc.getArrayElement() != null)
        {
            assertNotNull(actualTypeDesc.getArrayElement());
            assertTypeDescriptorsSame(expectedTypeDesc.getArrayElement().getTypeDescriptor()
                    , actualTypeDesc.getArrayElement().getTypeDescriptor());
        }
        else
        {
            assertNull(actualTypeDesc.getArrayElement());
        }
        
        assertEquals(expectedTypeDesc.getInterfaceTypeSignatures()
                , actualTypeDesc.getInterfaceTypeSignatures());
        assertEquals(expectedTypeDesc.getSuperTypeSignatures()
                , actualTypeDesc.getSuperTypeSignatures());
        assertEquals(expectedTypeDesc.getTypeParameterSignatures()
                , actualTypeDesc.getTypeParameterSignatures());
    }
    
    private void changeSuperType(IType type, IType newSuperType) throws Exception
    {
        ICompilationUnit cu = type.getCompilationUnit().getWorkingCopy(null);
        CompilationUnit astRoot = createASTAndStartRecording(cu);

        // modify the AST
        TypeDeclaration typeDeclaration = (TypeDeclaration)astRoot.types().get(0);
        AST ast = typeDeclaration.getAST();
        Name superName = ast.newName(newSuperType.getFullyQualifiedName());
        Type superType = ast.newSimpleType(superName);
        typeDeclaration.setSuperclassType(superType);

        commitAST(cu, astRoot);
    }
    
    private CompilationUnit createASTAndStartRecording(ICompilationUnit cu) throws Exception
    {
        // creation of DOM/AST from a ICompilationUnit
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(cu);
        // start record of the modifications
        CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
        astRoot.recordModifications();
        return astRoot;
    }
    
    private void commitAST(ICompilationUnit cu, CompilationUnit astRoot) throws Exception
    {
        String source = cu.getBuffer().getContents();
        Document document= new Document(source);

        // computation of the text edits
        TextEdit edits = astRoot.rewrite(document, cu.getJavaProject().getOptions(true));

        // computation of the new source code
        edits.apply(document);
        String newSource = document.get();

        // update of the compilation unit
        cu.getBuffer().setContents(newSource);
        cu.commitWorkingCopy(true, null);
    }
}
