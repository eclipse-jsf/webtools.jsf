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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin Provided JSF Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary#getPluginID <em>Plugin ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getPluginProvidedJSFLibrary()
 * @model
 * @generated
 */
public interface PluginProvidedJSFLibrary extends JSFLibrary{
	/**
	 * Separator between plugin id and JSF Library name
	 */
	public static final String ID_SEPARATOR = "$$";
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Plugin ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin ID</em>' attribute.
	 * @see #setPluginID(String)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getPluginProvidedJSFLibrary_PluginID()
	 * @model required="true"
	 * @generated
	 */
	String getPluginID();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary#getPluginID <em>Plugin ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin ID</em>' attribute.
	 * @see #getPluginID()
	 * @generated
	 */
	void setPluginID(String value);

} // PluginProvidedJSFLibrary
