/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IInstance Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#getTypeDescriptor <em>Type Descriptor</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#isReadable <em>Readable</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#isWritable <em>Writable</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#isTypeResolved <em>Type Resolved</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#getRuntimeSource <em>Runtime Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IInstanceSymbolImpl extends EObjectImpl implements IInstanceSymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";  //$NON-NLS-1$

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getTypeDescriptor() <em>Type Descriptor</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTypeDescriptor()
     * @generated
     * @ordered
     */
	protected ITypeDescriptor typeDescriptor = null;

    /**
     * The default value of the '{@link #isReadable() <em>Readable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isReadable()
     * @generated NOT
     * @ordered
     */
    protected static final boolean READABLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isReadable() <em>Readable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isReadable()
     * @generated
     * @ordered
     */
    protected boolean readable = READABLE_EDEFAULT;

    /**
     * The default value of the '{@link #isWritable() <em>Writable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isWritable()
     * @generated
     * @ordered
     */
    protected static final boolean WRITABLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isWritable() <em>Writable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isWritable()
     * @generated
     * @ordered
     */
    protected boolean writable = WRITABLE_EDEFAULT;

    /**
     * The default value of the '{@link #isTypeResolved() <em>Type Resolved</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isTypeResolved()
     * @generated
     * @ordered
     */
	protected static final boolean TYPE_RESOLVED_EDEFAULT = false;

    /**
     * The default value of the '{@link #getRuntimeSource() <em>Runtime Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRuntimeSource()
     * @generated
     * @ordered
     */
    protected static final ERuntimeSource RUNTIME_SOURCE_EDEFAULT = ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL;

    /**
     * The cached value of the '{@link #getRuntimeSource() <em>Runtime Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRuntimeSource()
     * @generated
     * @ordered
     */
    protected ERuntimeSource runtimeSource = RUNTIME_SOURCE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected IInstanceSymbolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the static class 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SymbolPackage.Literals.IINSTANCE_SYMBOL;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the symbol name
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newName 
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IINSTANCE_SYMBOL__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * @return the type descriptor for this instance 
	 * <!-- end-user-doc -->
     * @generated
     */
	public ITypeDescriptor getTypeDescriptor() {
        if (typeDescriptor != null && typeDescriptor.eIsProxy()) {
            InternalEObject oldTypeDescriptor = (InternalEObject)typeDescriptor;
            typeDescriptor = (ITypeDescriptor)eResolveProxy(oldTypeDescriptor);
            if (typeDescriptor != oldTypeDescriptor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SymbolPackage.IINSTANCE_SYMBOL__TYPE_DESCRIPTOR, oldTypeDescriptor, typeDescriptor));
            }
        }
        return typeDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
	 * @return the instance type
	 * <!-- end-user-doc -->
     * @generated
     */
	public ITypeDescriptor basicGetTypeDescriptor() {
        return typeDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newTypeDescriptor 
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTypeDescriptor(ITypeDescriptor newTypeDescriptor) {
        ITypeDescriptor oldTypeDescriptor = typeDescriptor;
        typeDescriptor = newTypeDescriptor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IINSTANCE_SYMBOL__TYPE_DESCRIPTOR, oldTypeDescriptor, typeDescriptor));
    }

    /**
     * <!-- begin-user-doc -->
     * @return true if instance symbol is readable 
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isReadable() {
        return readable;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newReadable 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReadable(boolean newReadable) {
        boolean oldReadable = readable;
        readable = newReadable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IINSTANCE_SYMBOL__READABLE, oldReadable, readable));
    }

    /**
     * <!-- begin-user-doc -->
     * @return true if instance is writable.  
     * 
     * Normally, instances should not be 
     * considered writable, since the VariableResolver has no setter, however
     * this is included to allow for unforeseen cases that may require an 
     * instance symbol to appear to be writable 
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isWritable() {
        return writable;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newWritable 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWritable(boolean newWritable) {
        boolean oldWritable = writable;
        writable = newWritable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IINSTANCE_SYMBOL__WRITABLE, oldWritable, writable));
    }

	/**
	 * <!-- begin-user-doc -->
     * @return true if the type descriptor has been resolved for this symbol 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isTypeResolved() 
	{
		return getTypeDescriptor() != null;
	}

    /**
     * <!-- begin-user-doc -->
     * @return return the symbol's runtime source 
     * <!-- end-user-doc -->
     * @generated
     */
    public ERuntimeSource getRuntimeSource() {
        return runtimeSource;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newRuntimeSource 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRuntimeSource(ERuntimeSource newRuntimeSource) {
        ERuntimeSource oldRuntimeSource = runtimeSource;
        runtimeSource = newRuntimeSource == null ? RUNTIME_SOURCE_EDEFAULT : newRuntimeSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IINSTANCE_SYMBOL__RUNTIME_SOURCE, oldRuntimeSource, runtimeSource));
    }

    /**
     * @generated NOT
     */
    public boolean supportsCoercion(String typeSignature) 
    {
        // first check that our type descriptor even thinks it is
        // an instanceof this type
        if (getTypeDescriptor().instanceOf(typeSignature))
        {
            // for java bean instances, we need to minimally support
            // Map, List and Array to conform to the basic spec
            // for JSF EL
            if (TypeConstants.TYPE_MAP.equals(typeSignature))
            {
                return true;
            }
            else if (TypeConstants.TYPE_LIST.equals(typeSignature)
                       /*TODO: || isArray*/)
            {
                return true;
            }
        }
        
        // otherwise, not supported
        return false;
    }


    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ITypeDescriptor coerce(String typeSignature) {
        // TODO:
        return getTypeDescriptor();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ISymbol call(String methodName, EList methodArguments, String symbolName) {
        return Util.call(methodName,methodArguments,symbolName,getTypeDescriptor());
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @param resolve 
     * @param coreType 
     * @return the value for the featureID 
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SymbolPackage.IINSTANCE_SYMBOL__NAME:
                return getName();
            case SymbolPackage.IINSTANCE_SYMBOL__TYPE_DESCRIPTOR:
                if (resolve) return getTypeDescriptor();
                return basicGetTypeDescriptor();
            case SymbolPackage.IINSTANCE_SYMBOL__READABLE:
                return isReadable() ? Boolean.TRUE : Boolean.FALSE;
            case SymbolPackage.IINSTANCE_SYMBOL__WRITABLE:
                return isWritable() ? Boolean.TRUE : Boolean.FALSE;
            case SymbolPackage.IINSTANCE_SYMBOL__TYPE_RESOLVED:
                return isTypeResolved() ? Boolean.TRUE : Boolean.FALSE;
            case SymbolPackage.IINSTANCE_SYMBOL__RUNTIME_SOURCE:
                return getRuntimeSource();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @param newValue 
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SymbolPackage.IINSTANCE_SYMBOL__NAME:
                setName((String)newValue);
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__TYPE_DESCRIPTOR:
                setTypeDescriptor((ITypeDescriptor)newValue);
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__READABLE:
                setReadable(((Boolean)newValue).booleanValue());
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__WRITABLE:
                setWritable(((Boolean)newValue).booleanValue());
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__RUNTIME_SOURCE:
                setRuntimeSource((ERuntimeSource)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
            case SymbolPackage.IINSTANCE_SYMBOL__NAME:
                setName(NAME_EDEFAULT);
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__TYPE_DESCRIPTOR:
                setTypeDescriptor((ITypeDescriptor)null);
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__READABLE:
                setReadable(READABLE_EDEFAULT);
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__WRITABLE:
                setWritable(WRITABLE_EDEFAULT);
                return;
            case SymbolPackage.IINSTANCE_SYMBOL__RUNTIME_SOURCE:
                setRuntimeSource(RUNTIME_SOURCE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @return true if the feature has been set 
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SymbolPackage.IINSTANCE_SYMBOL__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case SymbolPackage.IINSTANCE_SYMBOL__TYPE_DESCRIPTOR:
                return typeDescriptor != null;
            case SymbolPackage.IINSTANCE_SYMBOL__READABLE:
                return readable != READABLE_EDEFAULT;
            case SymbolPackage.IINSTANCE_SYMBOL__WRITABLE:
                return writable != WRITABLE_EDEFAULT;
            case SymbolPackage.IINSTANCE_SYMBOL__TYPE_RESOLVED:
                return isTypeResolved() != TYPE_RESOLVED_EDEFAULT;
            case SymbolPackage.IINSTANCE_SYMBOL__RUNTIME_SOURCE:
                return runtimeSource != RUNTIME_SOURCE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the string representation 
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");  //$NON-NLS-1$
        result.append(name);
        result.append(", readable: "); //$NON-NLS-1$
        result.append(readable);
        result.append(", writable: "); //$NON-NLS-1$
        result.append(writable);
        result.append(", runtimeSource: "); //$NON-NLS-1$
        result.append(runtimeSource);
        result.append(')');
        return result.toString();
    }

} //IInstanceSymbolImpl
