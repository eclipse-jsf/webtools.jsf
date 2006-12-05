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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IMap Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl#getMapSource <em>Map Source</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl#isImmutable <em>Immutable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IMapTypeDescriptorImpl extends ITypeDescriptorImpl implements IMapTypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * The default value of the '{@link #getMapSource() <em>Map Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMapSource()
     * @generated
     * @ordered
     */
    protected static final Map MAP_SOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMapSource() <em>Map Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMapSource()
     * @generated
     * @ordered
     */
    protected Map mapSource = MAP_SOURCE_EDEFAULT;

    /**
     * The default value of the '{@link #isImmutable() <em>Immutable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isImmutable()
     * @generated
     * @ordered
     */
    protected static final boolean IMMUTABLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isImmutable() <em>Immutable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isImmutable()
     * @generated
     * @ordered
     */
    protected boolean immutable = IMMUTABLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IMapTypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the static eClass 
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.IMAP_TYPE_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the map source 
     * <!-- end-user-doc -->
     * @generated
     */
    public Map getMapSource() {
        return mapSource;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newMapSource 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMapSource(Map newMapSource) {
        Map oldMapSource = mapSource;
        mapSource = newMapSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IMAP_TYPE_DESCRIPTOR__MAP_SOURCE, oldMapSource, mapSource));
    }

    /**
     * <!-- begin-user-doc -->
     * @return true if this map is immutable as defined in the java.util.Map
     * interface. 
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isImmutable() {
        return immutable;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newImmutable 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImmutable(boolean newImmutable) {
        boolean oldImmutable = immutable;
        immutable = newImmutable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IMAP_TYPE_DESCRIPTOR__IMMUTABLE, oldImmutable, immutable));
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getTypeSignature()
     * @generated NOT
     */
    public String getTypeSignature() 
    {
        // if the delegate has been set, use it
        if (eIsSet(SymbolPackage.IMAP_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE))
        {
            return getTypeSignatureDelegate();
        }
        
        // otherwise use Map
        return "Ljava.util.Map;";
    }

    public EList getInterfaceTypeSignatures() {
        return ECollections.EMPTY_ELIST;
    }

    public EList getSuperTypeSignatures() {
        return ECollections.EMPTY_ELIST;
    }

    /**
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getProperties()
     */
    public EList getProperties() 
    {
        final BasicEList  list = new BasicEList();
        final Map source = getMapSource();
        final Set keys = source.keySet();
        final Map segmentMap = processSegments(keys, source);
        list.addAll(segmentMap.values());
        return list;
    }
    
    public EList getMethods() 
    {
        // TODO: should this return the methods on  a Map?
        return ECollections.EMPTY_ELIST;
    }
    
    /**
     * @generated  NOT
     */
    public IObjectSymbol getArrayElement() {
		return null;
	}

    /**
     * @generated  NOT
     */
	public boolean isArray() {
		// a map is never an array
		return false;
	}

	private Map processSegments(final Set  keys, final Map source)
    {
        final Map segmentMap = new HashMap();
        
        for (final Iterator it = keys.iterator(); it.hasNext();)
        {
            final String key = (String) it.next();
            final String segments[] = key.split("\\.");
            
            IPropertySymbol  property = 
                (IPropertySymbol) segmentMap.get(segments[0]);
            
            if (property == null)
            {
                final Object propValue = source.get(key);
                property = SymbolFactory.eINSTANCE.createIPropertySymbol();
                property.setName(segments[0]);
                ITypeDescriptor typeDesc = null;
                
                // TODO: need wrapper object to rationalize
                if (propValue != null)
                {
                    if (propValue instanceof IType)
                    {
                        typeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
                        ((IJavaTypeDescriptor2)typeDesc).setType((IType)propValue);
                    }
                    else if (propValue instanceof IInstanceSymbol)
                    {
                        typeDesc = ((IInstanceSymbol)propValue).getTypeDescriptor();
                    }
                    else if (propValue instanceof IPropertySymbol)
                    {
                        typeDesc = ((IPropertySymbol)propValue).getTypeDescriptor();
                    }
                    else
                    {
                        String className = propValue.getClass().getName();
                        String typeSignature = Signature.createTypeSignature(className, true);
                        typeDesc = SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
                        ((IMapTypeDescriptor)typeDesc).setMapSource(new HashMap());
                        ((IMapTypeDescriptor)typeDesc).setTypeSignatureDelegate(typeSignature);
                        // inherit this descriptor's mutability
                        ((IMapTypeDescriptor)typeDesc).setImmutable(isImmutable());
                        property.setIntermediate(true);  // set the property as intermediate until we find out different
                    }
                    
                    property.setTypeDescriptor(typeDesc);
                    property.setReadable(true);
                    // is only writable if map is not immutable
                    property.setWritable(!isImmutable());
                }
                
                segmentMap.put(segments[0], property);
            }
            
            final ITypeDescriptor typeDesc = property.getTypeDescriptor();            
            
            if (typeDesc instanceof IMapTypeDescriptor)
            {
                if (segments.length == 1)
                {
                    // TODO: not always allowed ((IMapTypeDescriptor)typeDesc).getMapSource().put(null, source.get(key));
                    // property is more than simply intermediate
                    property.setIntermediate(false);
                }
                else
                {
                    ((IMapTypeDescriptor)typeDesc).getMapSource().
                        put(key.substring(key.indexOf('.')+1), source.get(key));
                }
            }
        }
        
        return segmentMap;
    }

    
    
    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @param resolve 
     * @param coreType 
     * @return the object for the feature id 
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__MAP_SOURCE:
                return getMapSource();
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__IMMUTABLE:
                return isImmutable() ? Boolean.TRUE : Boolean.FALSE;
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
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__MAP_SOURCE:
                setMapSource((Map)newValue);
                return;
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__IMMUTABLE:
                setImmutable(((Boolean)newValue).booleanValue());
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
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__MAP_SOURCE:
                setMapSource(MAP_SOURCE_EDEFAULT);
                return;
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__IMMUTABLE:
                setImmutable(IMMUTABLE_EDEFAULT);
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
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__MAP_SOURCE:
                return MAP_SOURCE_EDEFAULT == null ? mapSource != null : !MAP_SOURCE_EDEFAULT.equals(mapSource);
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR__IMMUTABLE:
                return immutable != IMMUTABLE_EDEFAULT;
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
        result.append(" (mapSource: ");
        result.append(mapSource);
        result.append(", immutable: ");
        result.append(immutable);
        result.append(')');
        return result.toString();
    }

} //IMapTypeDescriptorImpl
