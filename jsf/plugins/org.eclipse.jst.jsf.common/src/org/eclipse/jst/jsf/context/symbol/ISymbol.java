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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * An entity that represents a meaningful named value in some context. 
 * Typically these represent programming language variables, properties and methods.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 * @author cbateman
 * @model
 */
public interface ISymbol extends EObject {
    /**
     * Singleton for an empty array of ISymbols
     */
    final ISymbol[]         EMPTY_SYMBOL_ARRAY = new ISymbol[0];
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

	/**
	 * @return the symbol's name
	 * @model
	 */
	String getName();
    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.ISymbol#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

}
