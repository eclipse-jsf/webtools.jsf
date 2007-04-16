/**
 * <copyright>
 * </copyright>
 *
 * $Id: IListTypeDescriptorImpl.java,v 1.2 2007/04/16 19:53:58 itrimble Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.IListTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IList Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IListTypeDescriptorImpl#getListSource <em>List Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IListTypeDescriptorImpl extends ITypeDescriptorImpl implements IListTypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * The default value of the '{@link #getListSource() <em>List Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getListSource()
     * @generated
     * @ordered
     */
    protected static final EList LIST_SOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getListSource() <em>List Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getListSource()
     * @generated
     * @ordered
     */
    protected EList listSource = LIST_SOURCE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IListTypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.ILIST_TYPE_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getListSource() {
        return listSource;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setListSource(EList newListSource) {
        EList oldListSource = listSource;
        listSource = newListSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.ILIST_TYPE_DESCRIPTOR__LIST_SOURCE, oldListSource, listSource));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SymbolPackage.ILIST_TYPE_DESCRIPTOR__LIST_SOURCE:
                return getListSource();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SymbolPackage.ILIST_TYPE_DESCRIPTOR__LIST_SOURCE:
                setListSource((EList)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case SymbolPackage.ILIST_TYPE_DESCRIPTOR__LIST_SOURCE:
                setListSource(LIST_SOURCE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SymbolPackage.ILIST_TYPE_DESCRIPTOR__LIST_SOURCE:
                return LIST_SOURCE_EDEFAULT == null ? listSource != null : !LIST_SOURCE_EDEFAULT.equals(listSource);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (listSource: ");
        result.append(listSource);
        result.append(')');
        return result.toString();
    }

    /* 
     * @generated NOT
     */
    public EList getInterfaceTypeSignatures() {
        return ECollections.EMPTY_ELIST;
    }

    public EList getMethods() 
    {
        // TODO: should this return the methods on  a List?
        return ECollections.EMPTY_ELIST;   
    }

    public EList getProperties() 
    {
        // TODO: a list has no properties as such.  What to do here?
        return ECollections.EMPTY_ELIST;
    }

    /** 
     * @generated NOT
     */
    public EList getSuperTypeSignatures() {
        return ECollections.EMPTY_ELIST;
    }

    /**
     * @generated NOT
     */
    public String getTypeSignature() 
    {
        return TypeConstants.TYPE_LIST;
    }

} //IListTypeDescriptorImpl
