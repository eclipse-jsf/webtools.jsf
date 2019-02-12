/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.symbol;


/** 
 * Tags a symbol that has a detailed, user-readable description
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public interface IDescribedInDetail {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->s
     * @generated
     */
    String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

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
