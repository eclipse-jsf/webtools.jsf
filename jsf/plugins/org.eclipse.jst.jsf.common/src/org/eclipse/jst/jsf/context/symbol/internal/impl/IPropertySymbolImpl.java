/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IProperty Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl#getTypeDescriptor <em>Type Descriptor</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl#isReadable <em>Readable</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl#isWritable <em>Writable</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl#isIntermediate <em>Intermediate</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IPropertySymbolImpl extends EObjectImpl implements IPropertySymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

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
     * Set to true if this property symbol is readable
     */
    protected boolean  isReadable = READABLE_EDEFAULT;
    
    /**
     * Set to true if this property symbol is writable
     */
    protected boolean  isWritable = WRITABLE_EDEFAULT;
    
    /**
     * The default value of the '{@link #isReadable() <em>Readable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isReadable()
     * @generated
     * @ordered
     */
    protected static final boolean READABLE_EDEFAULT = false;

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
     * The default value of the '{@link #isIntermediate() <em>Intermediate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIntermediate()
     * @generated
     * @ordered
     */
    protected static final boolean INTERMEDIATE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIntermediate() <em>Intermediate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIntermediate()
     * @generated
     * @ordered
     */
    protected boolean intermediate = INTERMEDIATE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IPropertySymbolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the static eClass 
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.IPROPERTY_SYMBOL;
    }

    /**
     * <!-- begin-user-doc -->
     * @return return the property name
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newName the new name of the property 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IPROPERTY_SYMBOL__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * @return the property's type descriptor ?? can be null ?? 
     * <!-- end-user-doc -->
     * @generated
     */
    public ITypeDescriptor getTypeDescriptor() {
        if (typeDescriptor != null && typeDescriptor.eIsProxy()) {
            InternalEObject oldTypeDescriptor = (InternalEObject)typeDescriptor;
            typeDescriptor = (ITypeDescriptor)eResolveProxy(oldTypeDescriptor);
            if (typeDescriptor != oldTypeDescriptor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SymbolPackage.IPROPERTY_SYMBOL__TYPE_DESCRIPTOR, oldTypeDescriptor, typeDescriptor));
            }
        }
        return typeDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the type descriptor 
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
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IPROPERTY_SYMBOL__TYPE_DESCRIPTOR, oldTypeDescriptor, typeDescriptor));
    }

    /**
     * <!-- begin-user-doc -->
     * @return true if property is readable 
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
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IPROPERTY_SYMBOL__READABLE, oldReadable, readable));
    }

    /**
     * <!-- begin-user-doc -->
     * @return true if property is writable 
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
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IPROPERTY_SYMBOL__WRITABLE, oldWritable, writable));
    }

    /**
     * <!-- begin-user-doc -->
     * @return true if this property is intermediate and has no value of it's own
     * besides as a path to it's own properties.  This is used to support the 
     * situation in JSF where a map-based property can have dots in it (i.e. 
     * a loadBundle key my.property) and so creates a (discouraged) ambiguous 
     * situation where a property like #{x.y.z) really corresponds to a key
     * "y.z" in a property file pointed to by "x" instead of there being an
     * actual y property on x that has value in and of itself (in this case
     * "#{x.y}" is invalid even though "#{x.y.z}" is valid). 
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIntermediate() {
        return intermediate;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newIntermediate 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntermediate(boolean newIntermediate) {
        boolean oldIntermediate = intermediate;
        intermediate = newIntermediate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IPROPERTY_SYMBOL__INTERMEDIATE, oldIntermediate, intermediate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean supportsCoercion(String typeSignature) {
        // TODO: for now, not supported on generic properties
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ITypeDescriptor coerce(String typeSignature) 
    {
        // TODO: for now, no generic property type coercion
        return getTypeDescriptor();
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @param resolve 
     * @param coreType 
     * @return the eObject for the featureID 
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SymbolPackage.IPROPERTY_SYMBOL__NAME:
                return getName();
            case SymbolPackage.IPROPERTY_SYMBOL__TYPE_DESCRIPTOR:
                if (resolve) return getTypeDescriptor();
                return basicGetTypeDescriptor();
            case SymbolPackage.IPROPERTY_SYMBOL__READABLE:
                return isReadable() ? Boolean.TRUE : Boolean.FALSE;
            case SymbolPackage.IPROPERTY_SYMBOL__WRITABLE:
                return isWritable() ? Boolean.TRUE : Boolean.FALSE;
            case SymbolPackage.IPROPERTY_SYMBOL__INTERMEDIATE:
                return isIntermediate() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID -- set the feature 
     * @param newValue 
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SymbolPackage.IPROPERTY_SYMBOL__NAME:
                setName((String)newValue);
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__TYPE_DESCRIPTOR:
                setTypeDescriptor((ITypeDescriptor)newValue);
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__READABLE:
                setReadable(((Boolean)newValue).booleanValue());
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__WRITABLE:
                setWritable(((Boolean)newValue).booleanValue());
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__INTERMEDIATE:
                setIntermediate(((Boolean)newValue).booleanValue());
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID -- unset the feature 
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case SymbolPackage.IPROPERTY_SYMBOL__NAME:
                setName(NAME_EDEFAULT);
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__TYPE_DESCRIPTOR:
                setTypeDescriptor((ITypeDescriptor)null);
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__READABLE:
                setReadable(READABLE_EDEFAULT);
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__WRITABLE:
                setWritable(WRITABLE_EDEFAULT);
                return;
            case SymbolPackage.IPROPERTY_SYMBOL__INTERMEDIATE:
                setIntermediate(INTERMEDIATE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @return true if is set 
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SymbolPackage.IPROPERTY_SYMBOL__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case SymbolPackage.IPROPERTY_SYMBOL__TYPE_DESCRIPTOR:
                return typeDescriptor != null;
            case SymbolPackage.IPROPERTY_SYMBOL__READABLE:
                return readable != READABLE_EDEFAULT;
            case SymbolPackage.IPROPERTY_SYMBOL__WRITABLE:
                return writable != WRITABLE_EDEFAULT;
            case SymbolPackage.IPROPERTY_SYMBOL__INTERMEDIATE:
                return intermediate != INTERMEDIATE_EDEFAULT;
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
        result.append(" (name: ");
        result.append(name);
        result.append(", readable: ");
        result.append(readable);
        result.append(", writable: ");
        result.append(writable);
        result.append(", intermediate: ");
        result.append(intermediate);
        result.append(')');
        return result.toString();
    }

} //IPropertySymbolImpl
