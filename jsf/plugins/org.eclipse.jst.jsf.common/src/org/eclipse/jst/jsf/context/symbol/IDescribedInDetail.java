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
 * Tags a symbol that has a detailed, user-readable description
 * @author cbateman
 *
 */
public interface IDescribedInDetail {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->s
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

	/**
	 * @return the detailed description
	 * @model
	 */
	String  getDetailedDescription();
	
	/**
	 * @param detailedDescription
	 * @model
	 */
	void setDetailedDescription(String detailedDescription);
}
