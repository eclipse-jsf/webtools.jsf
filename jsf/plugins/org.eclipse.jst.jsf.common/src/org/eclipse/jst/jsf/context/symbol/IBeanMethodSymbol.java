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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IBean Method Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBeanMethodSymbol()
 * @model
 * @generated
 */
public interface IBeanMethodSymbol extends IDescribedInDetail, IMethodSymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";   //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Owner</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Owner</em>' reference.
     * @see #setOwner(IJavaTypeDescriptor2)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBeanMethodSymbol_Owner()
     * @model
     * @generated
     */
	IJavaTypeDescriptor2 getOwner();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol#getOwner <em>Owner</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Owner</em>' reference.
     * @see #getOwner()
     * @generated
     */
	void setOwner(IJavaTypeDescriptor2 value);

} // IBeanMethodSymbol
