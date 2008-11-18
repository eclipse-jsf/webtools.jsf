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

import java.util.HashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.IBoundedListTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBean Property Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanPropertySymbolImpl#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IBeanPropertySymbolImpl extends IPropertySymbolImpl implements IBeanPropertySymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("hiding")
	public static final String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$


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
	protected IBeanPropertySymbolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the static class 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SymbolPackage.Literals.IBEAN_PROPERTY_SYMBOL;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the owner's type descriptor 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IJavaTypeDescriptor2 getOwner() {
        if (owner != null && owner.eIsProxy()) {
            InternalEObject oldOwner = (InternalEObject)owner;
            owner = (IJavaTypeDescriptor2)eResolveProxy(oldOwner);
            if (owner != oldOwner) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SymbolPackage.IBEAN_PROPERTY_SYMBOL__OWNER, oldOwner, owner));
            }
        }
        return owner;
    }

    /**
     * <!-- begin-user-doc -->
	 * @return the java type descriptor for this instance
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
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IBEAN_PROPERTY_SYMBOL__OWNER, oldOwner, owner));
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
            case SymbolPackage.IBEAN_PROPERTY_SYMBOL__OWNER:
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
            case SymbolPackage.IBEAN_PROPERTY_SYMBOL__OWNER:
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
            case SymbolPackage.IBEAN_PROPERTY_SYMBOL__OWNER:
                setOwner((IJavaTypeDescriptor2)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @return true if the feature is set 
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SymbolPackage.IBEAN_PROPERTY_SYMBOL__OWNER:
                return owner != null;
        }
        return super.eIsSet(featureID);
    }

	public String getDetailedDescription() 
	{
		return createDetailedDescription();
	}

	public void setDetailedDescription(String detailedDescription) 
	{
		throw new UnsupportedOperationException("Detailed Description is derived on property symbols"); //$NON-NLS-1$
	}

    /**
     * @generated NOT
     */
    public ITypeDescriptor coerce(final String typeSignature) 
    {
        // ensure that we have an erased version
        final String erasedTypeSignature = Signature.getTypeErasure(typeSignature);
        
        if (supportsCoercion(typeSignature))
        {
            if (TypeConstants.TYPE_MAP.equals(erasedTypeSignature))
            {
                IBoundedMapTypeDescriptor mapDesc =  
                    SymbolFactory.eINSTANCE.createIBoundedMapTypeDescriptor();
                // bean maps are generally writable
                mapDesc.setImmutable(true);
                mapDesc.setMapSource(new HashMap());  // give it an empty map
                return mapDesc;
            }
            else if (TypeConstants.TYPE_LIST.equals(erasedTypeSignature))
            {
                IBoundedListTypeDescriptor listDesc =  
                    SymbolFactory.eINSTANCE.createIBoundedListTypeDescriptor();
                listDesc.setJdtContext(deriveBestJdtContext());
                 
                // bean maps are generally writable
                return listDesc;
            }
        }
        
        return null;

    }

    /**
     * @return the derived jdt element
     * @generated NOT
     */
    private IJavaElement deriveBestJdtContext()
    {
        IJavaElement contextElement = null;
        if (getTypeDescriptor() instanceof IJavaTypeDescriptor2)
        {
            contextElement = 
                ((IJavaTypeDescriptor2)getTypeDescriptor()).getType();
        }
        
        if (contextElement == null)
        {
            contextElement = getTypeDescriptor().getJdtContext();
        }

        return contextElement;
    }
    
    /** (non-Javadoc)
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl#call(java.lang.String, org.eclipse.emf.common.util.EList, java.lang.String)
     * 
     * @generated NOT
     */
    public ISymbol call(String methodName, EList methodArguments,
            String symbolName) 
    {
        return Util.call(methodName, methodArguments, symbolName, getTypeDescriptor());
    }


//    private List convertArgsToSignatures(List methodArgs)
//    {
//        List args = new ArrayList();
//        
//        for (final Iterator it = methodArgs.iterator(); it.hasNext();)
//        {
//            Object arg = it.next();
//            String className = arg.getClass().getName();
//            String resolvedName = Signature.createTypeSignature(className, true);
//            args.add(resolvedName);
//        }
//        
//        return args;
//    }

    /**
     * @generated NOT
     */
    public boolean supportsCoercion(String typeSignature) {
        // first check that our type descriptor even thinks it is
        // an instanceof this type
        if (getTypeDescriptor().instanceOf(typeSignature))
        {
            // for java bean instances, we need to minimally support
            // Map, List and Array to conform to the basic spec
            // for JSF EL
            if (TypeConstants.TYPE_MAP.equals(Signature.getTypeErasure(typeSignature)))
            {
                return true;
            }
            else if (TypeConstants.TYPE_LIST.equals(Signature.getTypeErasure(typeSignature)))
            {
                return true;
            }
        }
        
        // otherwise, not supported
        return false;
    }
    
    /**
     * @return the detail description
     * @generated NOT
     */
    private String createDetailedDescription()
    {
        StringBuffer additionalInfo = new StringBuffer("<p><b>"); //$NON-NLS-1$
        ITypeDescriptor typeDescriptor_ = getTypeDescriptor();
        if (typeDescriptor_ != null) {
            additionalInfo.append(Messages.getString("IBeanPropertySymbolImpl.Type")); //$NON-NLS-1$
            additionalInfo.append("</b>"); //$NON-NLS-1$
            additionalInfo.append(Signature.toString(typeDescriptor_.getTypeSignature()));
        }
        additionalInfo.append("</p>"); //$NON-NLS-1$
        additionalInfo.append("<p><b>"); //$NON-NLS-1$
        
        additionalInfo.append(Messages.getString("IBeanPropertySymbolImpl.Access"));  //$NON-NLS-1$
        additionalInfo.append("</b>"); //$NON-NLS-1$
        if (isReadable() && isWritable()) {
            additionalInfo.append(Messages.getString("IBeanPropertySymbolImpl.read_write")); //$NON-NLS-1$
        } else if (isReadable()) { 
            additionalInfo.append(Messages.getString("IBeanPropertySymbolImpl.readonly")); //$NON-NLS-1$
        } else if (isWritable()) { 
            additionalInfo.append(Messages.getString("IBeanPropertySymbolImpl.writeonly")); //$NON-NLS-1$
        } else {
            additionalInfo.append(Messages.getString("IBeanPropertySymbolImpl.none")); //$NON-NLS-1$
        }
        additionalInfo.append("</p>"); //$NON-NLS-1$
        JDTBeanProperty property = JavaUtil.findCorrespondingJDTProperty(this);
        if (property != null) {
            String javaDoc = null;
            if (property.getGetter() != null) {
                javaDoc = JavaUtil.getMethodJavadoc(property.getGetter());
            }
            if (javaDoc == null && property.getSetter() != null) {
                javaDoc = JavaUtil.getMethodJavadoc(property.getSetter());
            }
            if (javaDoc != null) {
                additionalInfo.append("<p>").append(javaDoc).append("</p>"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

        return additionalInfo.toString();
    }
    
} //IBeanPropertySymbolImpl

