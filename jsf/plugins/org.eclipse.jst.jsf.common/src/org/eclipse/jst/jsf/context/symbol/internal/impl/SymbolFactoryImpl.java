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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.*;

import org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SymbolFactoryImpl extends EFactoryImpl implements SymbolFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * @return the SymbolFactory 
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SymbolFactory init() {
        try {
            SymbolFactory theSymbolFactory = (SymbolFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/jst/jsf/context/symbol.ecore"); 
            if (theSymbolFactory != null) {
                return theSymbolFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SymbolFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SymbolFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @param eClass 
     * @return the model instace for the model class 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case SymbolPackage.IBEAN_INSTANCE_SYMBOL: return createIBeanInstanceSymbol();
            case SymbolPackage.IBEAN_PROPERTY_SYMBOL: return createIBeanPropertySymbol();
            case SymbolPackage.IINSTANCE_SYMBOL: return createIInstanceSymbol();
            case SymbolPackage.IJAVA_SYMBOL: return createIJavaSymbol();
            case SymbolPackage.IJAVA_TYPE_DESCRIPTOR2: return createIJavaTypeDescriptor2();
            case SymbolPackage.IBEAN_METHOD_SYMBOL: return createIBeanMethodSymbol();
            case SymbolPackage.ICOMPONENT_SYMBOL: return createIComponentSymbol();
            case SymbolPackage.IPROPERTY_SYMBOL: return createIPropertySymbol();
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR: return createIMapTypeDescriptor();
            case SymbolPackage.IMETHOD_SYMBOL: return createIMethodSymbol();
            case SymbolPackage.IBOUNDED_MAP_TYPE_DESCRIPTOR: return createIBoundedMapTypeDescriptor();
            case SymbolPackage.IBOUNDED_JAVA_TYPE_DESCRIPTOR: return createIBoundedJavaTypeDescriptor();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return an object constructed from the initialValue string 
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case SymbolPackage.ERUNTIME_SOURCE:
                return createERuntimeSourceFromString(eDataType, initialValue);
            case SymbolPackage.ITYPE:
                return createITypeFromString(eDataType, initialValue);
            case SymbolPackage.IJAVA_ELEMENT:
                return createIJavaElementFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return the string equivelent of eDataType for the instance 
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case SymbolPackage.ERUNTIME_SOURCE:
                return convertERuntimeSourceToString(eDataType, instanceValue);
            case SymbolPackage.ITYPE:
                return convertITypeToString(eDataType, instanceValue);
            case SymbolPackage.IJAVA_ELEMENT:
                return convertIJavaElementToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * @return a new bean instance symbol 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IBeanInstanceSymbol createIBeanInstanceSymbol() {
        IBeanInstanceSymbolImpl iBeanInstanceSymbol = new IBeanInstanceSymbolImpl();
        return iBeanInstanceSymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * @return a new bean property symbol 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IBeanPropertySymbol createIBeanPropertySymbol() {
        IBeanPropertySymbolImpl iBeanPropertySymbol = new IBeanPropertySymbolImpl();
        return iBeanPropertySymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IInstanceSymbol createIInstanceSymbol() {
        IInstanceSymbolImpl iInstanceSymbol = new IInstanceSymbolImpl();
        return iInstanceSymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * @return a new java symbol 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IJavaSymbol createIJavaSymbol() {
        IJavaSymbolImpl iJavaSymbol = new IJavaSymbolImpl();
        return iJavaSymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * @return a new java type descriptor 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IJavaTypeDescriptor2 createIJavaTypeDescriptor2() {
        IJavaTypeDescriptor2Impl iJavaTypeDescriptor2 = new IJavaTypeDescriptor2Impl();
        return iJavaTypeDescriptor2;
    }

    /**
     * <!-- begin-user-doc -->
     * @return a new bean method symbol 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IBeanMethodSymbol createIBeanMethodSymbol() {
        IBeanMethodSymbolImpl iBeanMethodSymbol = new IBeanMethodSymbolImpl();
        return iBeanMethodSymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * @return a new component derived symbol 
     * <!-- end-user-doc -->
     * @generated
     */
    public IComponentSymbol createIComponentSymbol() {
        IComponentSymbolImpl iComponentSymbol = new IComponentSymbolImpl();
        return iComponentSymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * @return a new property symbol 
     * <!-- end-user-doc -->
     * @generated
     */
    public IPropertySymbol createIPropertySymbol() {
        IPropertySymbolImpl iPropertySymbol = new IPropertySymbolImpl();
        return iPropertySymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * @return create map type descriptor 
     * <!-- end-user-doc -->
     * @generated
     */
    public IMapTypeDescriptor createIMapTypeDescriptor() {
        IMapTypeDescriptorImpl iMapTypeDescriptor = new IMapTypeDescriptorImpl();
        return iMapTypeDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the method symbol 
     * <!-- end-user-doc -->
     * @generated
     */
    public IMethodSymbol createIMethodSymbol() {
        IMethodSymbolImpl iMethodSymbol = new IMethodSymbolImpl();
        return iMethodSymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IBoundedMapTypeDescriptor createIBoundedMapTypeDescriptor() {
        IBoundedMapTypeDescriptorImpl iBoundedMapTypeDescriptor = new IBoundedMapTypeDescriptorImpl();
        return iBoundedMapTypeDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IBoundedJavaTypeDescriptor createIBoundedJavaTypeDescriptor() {
        IBoundedJavaTypeDescriptorImpl iBoundedJavaTypeDescriptor = new IBoundedJavaTypeDescriptorImpl();
        return iBoundedJavaTypeDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return 
     * <!-- end-user-doc -->
     * @generated
     */
    public ERuntimeSource createERuntimeSourceFromString(EDataType eDataType, String initialValue) {
        ERuntimeSource result = ERuntimeSource.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return the converted runtime source 
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertERuntimeSourceToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
	 * @param eDataType
	 * @param initialValue
	 * @return 
	 * <!-- end-user-doc -->
     * @generated
     */
	public IType createITypeFromString(EDataType eDataType, String initialValue) {
        return (IType)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * @param eDataType 
	 * @param instanceValue 
	 * @return the converted string
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertITypeToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * @param eDataType 
	 * @param initialValue 
	 * @return return the java element
	 * <!-- end-user-doc -->
     * @generated
     */
	public IJavaElement createIJavaElementFromString(EDataType eDataType, String initialValue) {
        return (IJavaElement)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * @param eDataType 
	 * @param instanceValue 
	 * @return the converted string 
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertIJavaElementToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the symbol package 
	 * <!-- end-user-doc -->
     * @generated
     */
	public SymbolPackage getSymbolPackage() {
        return (SymbolPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
	 * @return get the symbol package
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	public static SymbolPackage getPackage() {
        return SymbolPackage.eINSTANCE;
    }

} //SymbolFactoryImpl

