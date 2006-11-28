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
package org.eclipse.jst.jsf.context.symbol.internal.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.*;

import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor;


/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage
 * @generated
 */
public class SymbolSwitch {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static SymbolPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SymbolSwitch() {
        if (modelPackage == null) {
            modelPackage = SymbolPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * @param theEObject 
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * @param theEClass 
     * @param theEObject 
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch((EClass)eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * @param classifierID 
     * @param theEObject 
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case SymbolPackage.IBEAN_INSTANCE_SYMBOL: {
                IBeanInstanceSymbol iBeanInstanceSymbol = (IBeanInstanceSymbol)theEObject;
                Object result = caseIBeanInstanceSymbol(iBeanInstanceSymbol);
                if (result == null) result = caseIInstanceSymbol(iBeanInstanceSymbol);
                if (result == null) result = caseIDescribedInDetail(iBeanInstanceSymbol);
                if (result == null) result = caseIObjectSymbol(iBeanInstanceSymbol);
                if (result == null) result = caseISymbol(iBeanInstanceSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IBEAN_PROPERTY_SYMBOL: {
                IBeanPropertySymbol iBeanPropertySymbol = (IBeanPropertySymbol)theEObject;
                Object result = caseIBeanPropertySymbol(iBeanPropertySymbol);
                if (result == null) result = caseIPropertySymbol(iBeanPropertySymbol);
                if (result == null) result = caseIDescribedInDetail(iBeanPropertySymbol);
                if (result == null) result = caseIObjectSymbol(iBeanPropertySymbol);
                if (result == null) result = caseISymbol(iBeanPropertySymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IINSTANCE_SYMBOL: {
                IInstanceSymbol iInstanceSymbol = (IInstanceSymbol)theEObject;
                Object result = caseIInstanceSymbol(iInstanceSymbol);
                if (result == null) result = caseIObjectSymbol(iInstanceSymbol);
                if (result == null) result = caseISymbol(iInstanceSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IJAVA_SYMBOL: {
                IJavaSymbol iJavaSymbol = (IJavaSymbol)theEObject;
                Object result = caseIJavaSymbol(iJavaSymbol);
                if (result == null) result = caseISymbol(iJavaSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.ISYMBOL: {
                ISymbol iSymbol = (ISymbol)theEObject;
                Object result = caseISymbol(iSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.ITYPE_DESCRIPTOR: {
                ITypeDescriptor iTypeDescriptor = (ITypeDescriptor)theEObject;
                Object result = caseITypeDescriptor(iTypeDescriptor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IDESCRIBED_IN_DETAIL: {
                IDescribedInDetail iDescribedInDetail = (IDescribedInDetail)theEObject;
                Object result = caseIDescribedInDetail(iDescribedInDetail);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IJAVA_TYPE_DESCRIPTOR2: {
                IJavaTypeDescriptor2 iJavaTypeDescriptor2 = (IJavaTypeDescriptor2)theEObject;
                Object result = caseIJavaTypeDescriptor2(iJavaTypeDescriptor2);
                if (result == null) result = caseITypeDescriptor(iJavaTypeDescriptor2);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IBEAN_METHOD_SYMBOL: {
                IBeanMethodSymbol iBeanMethodSymbol = (IBeanMethodSymbol)theEObject;
                Object result = caseIBeanMethodSymbol(iBeanMethodSymbol);
                if (result == null) result = caseIDescribedInDetail(iBeanMethodSymbol);
                if (result == null) result = caseIMethodSymbol(iBeanMethodSymbol);
                if (result == null) result = caseISymbol(iBeanMethodSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.ICOMPONENT_SYMBOL: {
                IComponentSymbol iComponentSymbol = (IComponentSymbol)theEObject;
                Object result = caseIComponentSymbol(iComponentSymbol);
                if (result == null) result = caseIInstanceSymbol(iComponentSymbol);
                if (result == null) result = caseIDescribedInDetail(iComponentSymbol);
                if (result == null) result = caseIObjectSymbol(iComponentSymbol);
                if (result == null) result = caseISymbol(iComponentSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IPROPERTY_SYMBOL: {
                IPropertySymbol iPropertySymbol = (IPropertySymbol)theEObject;
                Object result = caseIPropertySymbol(iPropertySymbol);
                if (result == null) result = caseIObjectSymbol(iPropertySymbol);
                if (result == null) result = caseISymbol(iPropertySymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IMAP_TYPE_DESCRIPTOR: {
                IMapTypeDescriptor iMapTypeDescriptor = (IMapTypeDescriptor)theEObject;
                Object result = caseIMapTypeDescriptor(iMapTypeDescriptor);
                if (result == null) result = caseITypeDescriptor(iMapTypeDescriptor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IMETHOD_SYMBOL: {
                IMethodSymbol iMethodSymbol = (IMethodSymbol)theEObject;
                Object result = caseIMethodSymbol(iMethodSymbol);
                if (result == null) result = caseISymbol(iMethodSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IOBJECT_SYMBOL: {
                IObjectSymbol iObjectSymbol = (IObjectSymbol)theEObject;
                Object result = caseIObjectSymbol(iObjectSymbol);
                if (result == null) result = caseISymbol(iObjectSymbol);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IBOUNDED_TYPE_DESCRIPTOR: {
                IBoundedTypeDescriptor iBoundedTypeDescriptor = (IBoundedTypeDescriptor)theEObject;
                Object result = caseIBoundedTypeDescriptor(iBoundedTypeDescriptor);
                if (result == null) result = caseITypeDescriptor(iBoundedTypeDescriptor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IBOUNDED_MAP_TYPE_DESCRIPTOR: {
                IBoundedMapTypeDescriptor iBoundedMapTypeDescriptor = (IBoundedMapTypeDescriptor)theEObject;
                Object result = caseIBoundedMapTypeDescriptor(iBoundedMapTypeDescriptor);
                if (result == null) result = caseIMapTypeDescriptor(iBoundedMapTypeDescriptor);
                if (result == null) result = caseIBoundedTypeDescriptor(iBoundedMapTypeDescriptor);
                if (result == null) result = caseITypeDescriptor(iBoundedMapTypeDescriptor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SymbolPackage.IBOUNDED_JAVA_TYPE_DESCRIPTOR: {
                IBoundedJavaTypeDescriptor iBoundedJavaTypeDescriptor = (IBoundedJavaTypeDescriptor)theEObject;
                Object result = caseIBoundedJavaTypeDescriptor(iBoundedJavaTypeDescriptor);
                if (result == null) result = caseIJavaTypeDescriptor2(iBoundedJavaTypeDescriptor);
                if (result == null) result = caseIBoundedTypeDescriptor(iBoundedJavaTypeDescriptor);
                if (result == null) result = caseITypeDescriptor(iBoundedJavaTypeDescriptor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IBean Instance Symbol</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IBean Instance Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIBeanInstanceSymbol(IBeanInstanceSymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IBean Property Symbol</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IBean Property Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIBeanPropertySymbol(IBeanPropertySymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IInstance Symbol</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IInstance Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIInstanceSymbol(IInstanceSymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IJava Symbol</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IJava Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIJavaSymbol(IJavaSymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>ISymbol</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>ISymbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseISymbol(ISymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IType Descriptor</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IType Descriptor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseITypeDescriptor(ITypeDescriptor object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IDescribed In Detail</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IDescribed In Detail</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIDescribedInDetail(IDescribedInDetail object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IJava Type Descriptor2</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IJava Type Descriptor2</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIJavaTypeDescriptor2(IJavaTypeDescriptor2 object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IBean Method Symbol</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IBean Method Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIBeanMethodSymbol(IBeanMethodSymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IComponent Symbol</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IComponent Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIComponentSymbol(IComponentSymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IProperty Symbol</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IProperty Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIPropertySymbol(IPropertySymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IMap Type Descriptor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IMap Type Descriptor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIMapTypeDescriptor(IMapTypeDescriptor object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IMethod Symbol</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IMethod Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIMethodSymbol(IMethodSymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IObject Symbol</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IObject Symbol</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIObjectSymbol(IObjectSymbol object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IBounded Type Descriptor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IBounded Type Descriptor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIBoundedTypeDescriptor(IBoundedTypeDescriptor object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IBounded Map Type Descriptor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IBounded Map Type Descriptor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIBoundedMapTypeDescriptor(IBoundedMapTypeDescriptor object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IBounded Java Type Descriptor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IBounded Java Type Descriptor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIBoundedJavaTypeDescriptor(IBoundedJavaTypeDescriptor object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	public Object defaultCase(EObject object) {
        return null;
    }

} //SymbolSwitch

