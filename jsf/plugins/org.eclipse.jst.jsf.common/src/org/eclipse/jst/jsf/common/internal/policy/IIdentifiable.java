/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.policy;

/**
 * An object with an unique identity
 * @author cbateman
 * @param <IDTYPE> 
 *
 */
public interface IIdentifiable<IDTYPE>
{
    /**
     * @return the unique identifier
     */
    IDTYPE getId();
    
    /**
     * @return a human-readable name for this identifier.
     */
    String getDisplayName();
}
