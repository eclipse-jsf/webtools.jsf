/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.facet;

/**
 * Indicates the type of change that should be made with the data in a
 * FacetChangeModel.
 * 
 * @author cbateman
 * 
 */
public enum ChangeActionType
{
    /**
     * Change is to add indicated values
     */
    ADD,

    /**
     * Change is to remove indicated values
     */
    REMOVE
}
