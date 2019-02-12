/*******************************************************************************
 * Copyright (c) 2006, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: IBoundedMapTypeDescriptorImpl.java,v 1.7 2007/10/01 04:29:44 cbateman Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.IBoundedJavaTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBounded Map Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IBoundedMapTypeDescriptorImpl extends IMapTypeDescriptorImpl implements IBoundedMapTypeDescriptor {
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
    protected IBoundedMapTypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.IBOUNDED_MAP_TYPE_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isUnboundedForType(String typeSignature) {
        // TODO: for now, return true if the type is a resolved object
        // need to add support for template checking (Java5) and
        // decide what to do with unresolved (Q) type signatures
        return typeSignature != null
                && typeSignature.startsWith(Character.toString(Signature.C_RESOLVED));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ISymbol getUnboundedProperty(Object name, String typeSignature) {
        ISymbol  retValue = null;
        
        if (isUnboundedForType(typeSignature))
        {
            // first see if we have it in our map source
            retValue = getFromMap(name.toString());
            
            if (retValue == null)
            {
                IPropertySymbol  propSymbol = SymbolFactory.eINSTANCE.createIPropertySymbol();
                // TODO: there is a possible problem here for non-string keyed maps
                propSymbol.setName(name.toString());
                propSymbol.setReadable(true);
                IBoundedJavaTypeDescriptor typeDesc = 
                    SymbolFactory.eINSTANCE.createIBoundedJavaTypeDescriptor();
                
                typeDesc.setTypeSignatureDelegate(TypeConstants.TYPE_JAVAOBJECT);
                propSymbol.setTypeDescriptor(typeDesc);
                retValue = propSymbol;
            }
        }

        return retValue;

    }

    /**
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#calculateSyntheticCall(java.lang.String, org.eclipse.emf.common.util.EList, java.lang.String)
     * @generated NOT
     */
    public ISymbol calculateSyntheticCall(String methodName, EList methodArgs,
            String symbolName) {
        if ("get".equals(methodName)  //$NON-NLS-1$
                && methodArgs.size() == 1)
        {
            return getUnboundedProperty(symbolName, ((ValueType) methodArgs.get(0)).getSignature());
        }
            
        // default is return null
        return null;
    }


    /**
     * @generated NOT
     */
    private ISymbol getFromMap(final String name)
    {
        for (final Iterator it = getProperties().iterator(); it.hasNext();)
        {
            ISymbol  symbol = (ISymbol) it.next();
            
            if (symbol.getName().equals(name))
            {
                return symbol;
            }
        }
        
        return null;
    }

} //IBoundedMapTypeDescriptorImpl