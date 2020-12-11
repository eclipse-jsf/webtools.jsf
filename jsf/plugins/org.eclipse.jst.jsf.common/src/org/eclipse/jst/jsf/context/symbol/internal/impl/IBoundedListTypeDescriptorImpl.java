/*******************************************************************************
 * Copyright (c) 2006, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation     - initial API and implementation
 *     Reto Weiss/Axon Ivy - Cache JDTBeanIntrospector
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: IBoundedListTypeDescriptorImpl.java,v 1.6 2007/10/01 04:29:44 cbateman Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.util.JDTBeanMethod;
import org.eclipse.jst.jsf.context.symbol.IBoundedListTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBounded List Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IBoundedListTypeDescriptorImpl extends IListTypeDescriptorImpl implements IBoundedListTypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("hiding")
	public static final String copyright = "Copyright 2006 Oracle";  //$NON-NLS-1$

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IBoundedListTypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.IBOUNDED_LIST_TYPE_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isUnboundedForType(String typeSignature) 
    {
        // type signature must be a boxed integer
        // TODO: at this level, do we need to deal with coercion to
        // other integer types?  list.get() takes an integer...
        return typeSignature != null && TypeConstants.TYPE_BOXED_INTEGER.equals(typeSignature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ISymbol getUnboundedProperty(Object name, String typeSignature) {
        ISymbol  retValue = null;

        if (isUnboundedForType(typeSignature)
                && name instanceof Number)
        {
            // get integer value
            int offset = ((Number)name).intValue();

            // first see if we have it in our map source
            // TODO: retValue = getFromMap(name.toString());

            if (retValue == null)
            {
                IPropertySymbol  propSymbol = SymbolFactory.eINSTANCE.createIPropertySymbol();
                // TODO: there is a possible problem here for non-string keyed maps
                propSymbol.setName(name.toString());
                propSymbol.setReadable(true);
                propSymbol.setTypeDescriptor(getBoundsTypeDescriptor(offset));
                retValue = propSymbol;
            }
        }

        return retValue;
    }

    /**
     * <!-- begin-user-doc -->
     * @param methodName 
     * @param methodArguments 
     * @param symbolName 
     * @return a symbol representing the return value of the method 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ISymbol call(String methodName, EList methodArguments, String symbolName)
    {
        ISymbol result = null;
        final IType type = resolveType(getTypeSignature());
        
        if (type != null)
        {
            final JDTBeanIntrospector introspector = JDTBeanIntrospector.forType(type);
            
            final JDTBeanMethod callMethod = 
                matchMethod(methodName, methodArguments, introspector.getMethods());
            
            if (callMethod != null)
            {
                // resolve the method's return type; don't erase parameters
                final String retTypeSignature = callMethod.getResolvedReturnTypeUnerased();
                final IPropertySymbol  propSymbol = 
                    SymbolFactory.eINSTANCE.createIPropertySymbol();

                // TODO: there is a possible problem here for non-string keyed maps
                propSymbol.setName(symbolName);
                propSymbol.setReadable(true);
                IJavaTypeDescriptor2 typeDesc = 
                    SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
                
                typeDesc.setArrayCount(Signature.getArrayCount(retTypeSignature));
                
                // may be null
                typeDesc.setType(resolveType(retTypeSignature));
                typeDesc.setTypeSignatureDelegate(retTypeSignature);
                propSymbol.setTypeDescriptor(typeDesc);
                result = propSymbol;
            }
        }
        
        return result;
    }
    
    private JDTBeanMethod matchMethod(String name, List methodArguments, JDTBeanMethod[] allMethods)
    {
        final List argSigs = convertArgsToSignatures(methodArguments);
        
        for (int i = 0; i < allMethods.length; i++)
        {
            final JDTBeanMethod method = allMethods[i];
            
            // check for names and argument count match
            if (method.getUnresolvedParameterTypesUnerased().length == argSigs.size()
                    && method.getElementName().equals(name))
            {
                String[] paramSigs = method.getUnresolvedParameterTypesUnerased(); // RWEI seems not be correct to get unresolved types here
                // need to verify argument matches
                boolean isMatched = true;
                CHECK_ARGUMENTS: for (int j = 0; j < paramSigs.length; j++)
                {
                    if (!paramSigs[j].equals(argSigs.get(j)))
                    {
                        // not a match
                        isMatched = false;
                        break CHECK_ARGUMENTS;
                    }
                }
                
                if (isMatched)
                {
                    return method;
                }
            }
        }

        return null;
    }

    private List convertArgsToSignatures(List methodArgs)
    {
        List args = new ArrayList();
        
        for (final Iterator it = methodArgs.iterator(); it.hasNext();)
        {
            Object arg = it.next();
            String className = arg.getClass().getName();
            String resolvedName = Signature.createTypeSignature(className, true);
            args.add(resolvedName);
        }
        
        return args;
    }
    
    /**
     * @return the ITypeDescriptor for this List's element type (bound type).
     * Defaults to java.lang.Object if no bounds or can't resolve bounds
     * 
     * @generated NOT 
     */
    private ITypeDescriptor getBoundsTypeDescriptor(int offset)
    {
        IJavaTypeDescriptor2  typeDesc = null;
        
        List typeParameters = getTypeParameterSignatures();
        
        // if no bounds at all, then default to bounded java object
        if (typeParameters.size() == 0)
        {
            typeDesc = 
                SymbolFactory.eINSTANCE.createIBoundedJavaTypeDescriptor();
            
            typeDesc.setTypeSignatureDelegate(TypeConstants.TYPE_JAVAOBJECT);
        }
        else
        {
            // TODO: there should only be exactly one on a list...
            final String elementType = (String) typeParameters.get(0);
            
            typeDesc = 
                SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
            
            typeDesc.setArrayCount(Signature.getArrayCount(elementType));
            
            // may be null
            typeDesc.setType(resolveType(elementType));
            typeDesc.setTypeSignatureDelegate(elementType);
        }

        return typeDesc;
    }
} //IBoundedListTypeDescriptorImpl
