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
package org.eclipse.jst.jsf.context.symbol;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedJavaTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage
 * @generated
 */
public interface SymbolFactory extends EFactory {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String copyright = "Copyright 2006 Oracle";

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    SymbolFactory eINSTANCE = org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>IBean Instance Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IBean Instance Symbol</em>'.
	 * @generated
	 */
    IBeanInstanceSymbol createIBeanInstanceSymbol();

	/**
	 * Returns a new object of class '<em>IBean Property Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IBean Property Symbol</em>'.
	 * @generated
	 */
    IBeanPropertySymbol createIBeanPropertySymbol();

	/**
	 * Returns a new object of class '<em>IInstance Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IInstance Symbol</em>'.
	 * @generated
	 */
    IInstanceSymbol createIInstanceSymbol();

	/**
	 * Returns a new object of class '<em>IJava Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IJava Symbol</em>'.
	 * @generated
	 */
    IJavaSymbol createIJavaSymbol();

	/**
	 * Returns a new object of class '<em>IJava Type Descriptor2</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IJava Type Descriptor2</em>'.
	 * @generated
	 */
    IJavaTypeDescriptor2 createIJavaTypeDescriptor2();

	/**
	 * Returns a new object of class '<em>IBean Method Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IBean Method Symbol</em>'.
	 * @generated
	 */
    IBeanMethodSymbol createIBeanMethodSymbol();

	/**
	 * Returns a new object of class '<em>IComponent Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IComponent Symbol</em>'.
	 * @generated
	 */
    IComponentSymbol createIComponentSymbol();

	/**
	 * Returns a new object of class '<em>IProperty Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IProperty Symbol</em>'.
	 * @generated
	 */
    IPropertySymbol createIPropertySymbol();

	/**
	 * Returns a new object of class '<em>IMap Type Descriptor</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IMap Type Descriptor</em>'.
	 * @generated
	 */
    IMapTypeDescriptor createIMapTypeDescriptor();

	/**
	 * Returns a new object of class '<em>IMethod Symbol</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IMethod Symbol</em>'.
	 * @generated
	 */
    IMethodSymbol createIMethodSymbol();

	/**
	 * Returns a new object of class '<em>IBounded Map Type Descriptor</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IBounded Map Type Descriptor</em>'.
	 * @generated
	 */
    IBoundedMapTypeDescriptor createIBoundedMapTypeDescriptor();

	/**
	 * Returns a new object of class '<em>IBounded Java Type Descriptor</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>IBounded Java Type Descriptor</em>'.
	 * @generated
	 */
    IBoundedJavaTypeDescriptor createIBoundedJavaTypeDescriptor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    SymbolPackage getSymbolPackage();

} //SymbolFactory
