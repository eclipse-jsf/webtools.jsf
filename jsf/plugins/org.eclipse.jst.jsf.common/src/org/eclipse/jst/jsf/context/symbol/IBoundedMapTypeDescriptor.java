/*******************************************************************************
 * Copyright (c) 2006, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.context.symbol;


/**
 * <!-- begin-user-doc -->
 * Describes a IMapTypeDescriptor for which the possible keyed values are
 * unbounded for some set of possible value types.  For example, a managed
 * bean that implements Map, is unconstrained for all values in Java 1.4 and
 * for whatever the template value type is in Java5.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBoundedMapTypeDescriptor()
 * @model
 * @generated
 */
public interface IBoundedMapTypeDescriptor extends IMapTypeDescriptor, IBoundedTypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

} // IBoundedMapTypeDescriptor