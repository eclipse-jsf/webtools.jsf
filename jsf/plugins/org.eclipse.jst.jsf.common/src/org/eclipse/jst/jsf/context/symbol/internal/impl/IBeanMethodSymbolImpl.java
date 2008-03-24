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
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBean Method Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl#getSignature <em>Signature</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IBeanMethodSymbolImpl extends EObjectImpl implements IBeanMethodSymbol {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

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
     * The default value of the '{@link #getSignature() <em>Signature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSignature()
     * @generated
     * @ordered
     */
    protected static final String SIGNATURE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSignature() <em>Signature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSignature()
     * @generated
     * @ordered
     */
    protected String signature = SIGNATURE_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwner() <em>Owner</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOwner()
     * @generated
     * @ordered
     */
	protected IJavaTypeDescriptor2 owner = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected IBeanMethodSymbolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the static eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SymbolPackage.Literals.IBEAN_METHOD_SYMBOL;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the name of the method symbol 
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
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IBEAN_METHOD_SYMBOL__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * @return the signature in JVM/JDT form 
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSignature() {
        return signature;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newSignature 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSignature(String newSignature) {
        String oldSignature = signature;
        signature = newSignature;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IBEAN_METHOD_SYMBOL__SIGNATURE, oldSignature, signature));
    }

    /**
     * <!-- begin-user-doc -->
     * @return the java type descriptor that owns this symbol
	 * <!-- end-user-doc -->
     * @generated
     */
	public IJavaTypeDescriptor2 getOwner() {
        if (owner != null && owner.eIsProxy()) {
            InternalEObject oldOwner = (InternalEObject)owner;
            owner = (IJavaTypeDescriptor2)eResolveProxy(oldOwner);
            if (owner != oldOwner) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SymbolPackage.IBEAN_METHOD_SYMBOL__OWNER, oldOwner, owner));
            }
        }
        return owner;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the java type descriptor that derives this symbol 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IJavaTypeDescriptor2 basicGetOwner() {
        return owner;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newOwner 
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOwner(IJavaTypeDescriptor2 newOwner) {
        IJavaTypeDescriptor2 oldOwner = owner;
        owner = newOwner;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IBEAN_METHOD_SYMBOL__OWNER, oldOwner, owner));
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @param resolve 
     * @param coreType 
     * @return the feature object for featureID 
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SymbolPackage.IBEAN_METHOD_SYMBOL__NAME:
                return getName();
            case SymbolPackage.IBEAN_METHOD_SYMBOL__SIGNATURE:
                return getSignature();
            case SymbolPackage.IBEAN_METHOD_SYMBOL__OWNER:
                if (resolve) return getOwner();
                return basicGetOwner();
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
            case SymbolPackage.IBEAN_METHOD_SYMBOL__NAME:
                setName((String)newValue);
                return;
            case SymbolPackage.IBEAN_METHOD_SYMBOL__SIGNATURE:
                setSignature((String)newValue);
                return;
            case SymbolPackage.IBEAN_METHOD_SYMBOL__OWNER:
                setOwner((IJavaTypeDescriptor2)newValue);
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
            case SymbolPackage.IBEAN_METHOD_SYMBOL__NAME:
                setName(NAME_EDEFAULT);
                return;
            case SymbolPackage.IBEAN_METHOD_SYMBOL__SIGNATURE:
                setSignature(SIGNATURE_EDEFAULT);
                return;
            case SymbolPackage.IBEAN_METHOD_SYMBOL__OWNER:
                setOwner((IJavaTypeDescriptor2)null);
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
            case SymbolPackage.IBEAN_METHOD_SYMBOL__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case SymbolPackage.IBEAN_METHOD_SYMBOL__SIGNATURE:
                return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
            case SymbolPackage.IBEAN_METHOD_SYMBOL__OWNER:
                return owner != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * @param derivedFeatureID 
     * @param baseClass 
     * @return the feature id 
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == ISymbol.class) {
            switch (derivedFeatureID) {
                case SymbolPackage.IBEAN_METHOD_SYMBOL__NAME: return SymbolPackage.ISYMBOL__NAME;
                default: return -1;
            }
        }
        if (baseClass == IMethodSymbol.class) {
            switch (derivedFeatureID) {
                case SymbolPackage.IBEAN_METHOD_SYMBOL__SIGNATURE: return SymbolPackage.IMETHOD_SYMBOL__SIGNATURE;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * @param baseFeatureID 
     * @param baseClass 
     * @return the feature id 
     * <!-- end-user-doc -->
     * @generated
     */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
        if (baseClass == ISymbol.class) {
            switch (baseFeatureID) {
                case SymbolPackage.ISYMBOL__NAME: return SymbolPackage.IBEAN_METHOD_SYMBOL__NAME;
                default: return -1;
            }
        }
        if (baseClass == IMethodSymbol.class) {
            switch (baseFeatureID) {
                case SymbolPackage.IMETHOD_SYMBOL__SIGNATURE: return SymbolPackage.IBEAN_METHOD_SYMBOL__SIGNATURE;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the default string representation 
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", signature: "); //$NON-NLS-1$
        result.append(signature);
        result.append(')');
        return result.toString();
    }

	public String getDetailedDescription() {
	    StringBuffer descBuffer = new StringBuffer("<p><b>Signature:</b> ");
        descBuffer.append(Signature.toString(signature, getName(), null, false, true)).append("</p>");
        IMethod method = JavaUtil.findCorrespondingMethod(this);
        if (method != null) {
            String javadoc = JavaUtil.getMethodJavadoc(method);
            if (javadoc != null) {
                descBuffer.append("<p>").append(javadoc).append("</p>");
            }
        }
        return descBuffer.toString();
    }
    
	public void setDetailedDescription(String detailedDescription) {
		throw new UnsupportedOperationException("Detailed Description is derived on property symbols"); //$NON-NLS-1$
	}


} //IBeanMethodSymbolImpl
