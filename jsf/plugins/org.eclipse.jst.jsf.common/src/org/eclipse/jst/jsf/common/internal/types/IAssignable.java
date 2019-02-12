/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
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

package org.eclipse.jst.jsf.common.internal.types;

/**
 * Defines the lhs/rhs rules for a type
 * @author cbateman
 *
 */
public interface IAssignable 
{
    /**
     * Type is none: it cannot be assigned to.  method binding.
     */
    public static int  ASSIGNMENT_TYPE_NONE = 0x0;
    /**
     * Type is lhs: it can be assigned to
     */
    public static int  ASSIGNMENT_TYPE_LHS = 0x1;
    
    /**
     * Type is rhs: it can be assigned from
     */
    public static int  ASSIGNMENT_TYPE_RHS = 0x2;
    
    /**
     * @return the assigment mask
     */
    public int  getAssignability();
    
    /**
     * @return true if ASSIGNMENT_TYPE_LHS is set in getAssignability
     */
    public boolean isLHS();
    /**
     * @return true if ASSIGNMENT_TYPE_RHS is set in getAssignability
     */
    public boolean isRHS();
}
