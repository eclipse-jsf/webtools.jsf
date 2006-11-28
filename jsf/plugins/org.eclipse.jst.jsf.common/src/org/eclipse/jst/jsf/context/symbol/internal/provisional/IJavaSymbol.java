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

package org.eclipse.jst.jsf.context.symbol.internal.provisional;

import org.eclipse.jdt.core.IJavaElement;

/**
 * Represents a symbol that has meaning within Java's context.  This may
 * be the name of a type, an instance, method etc.
 * 
 * @author cbateman
 * @model
 */
public interface IJavaSymbol extends ISymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

	/**
	 * A IJavaElement may not exist for a symbol if it is synthetic at
	 * design time but will be bound to a Java symbol at runtime.  An
	 * example is a managed bean instance in JSF.  JDT can provide no
	 * design-time meta-data for the symbol because it won't have 
	 * a Java representation until the containing JSP is compiled.
	 * 
	 * @return JDT's java element for this symbol or null if one doesn't
	 * exist.
	 * @model
	 */
	IJavaElement  getJavaElement();
    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol#getJavaElement <em>Java Element</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Java Element</em>' attribute.
     * @see #getJavaElement()
     * @generated
     */
	void setJavaElement(IJavaElement value);

}
