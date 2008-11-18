/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage
 * @generated
 * @deprecated
 */
public interface JSFLibraryRegistryFactory extends EFactory{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JSFLibraryRegistryFactory eINSTANCE = org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>JSF Library Registry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>JSF Library Registry</em>'.
	 * @generated
	 */
	JSFLibraryRegistry createJSFLibraryRegistry();

	/**
	 * Returns a new object of class '<em>JSF Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>JSF Library</em>'.
	 * @generated
	 */
	JSFLibrary createJSFLibrary();

	/**
	 * Returns a new object of class '<em>Archive File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Archive File</em>'.
	 * @generated
	 */
	ArchiveFile createArchiveFile();

	/**
	 * Returns a new object of class '<em>Plugin Provided JSF Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Plugin Provided JSF Library</em>'.
	 * @generated
	 */
	PluginProvidedJSFLibrary createPluginProvidedJSFLibrary();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JSFLibraryRegistryPackage getJSFLibraryRegistryPackage();

} //JSFLibraryRegistryFactory
