/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
