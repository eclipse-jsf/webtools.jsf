/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.io.Serializable;

/**
 * Information about a tags attribute
 * @author cbateman
 *
 */
public interface ITagAttribute extends Serializable
{
    /**
     * @return the name of the attribute.  In XML this is the "local" name of
     * the attribute: that is, it's name without any namespace qualification.
     */
    String getName();
    
    /**
     * @return the unique namespace of the attribute or null if it is always the
     * same as it's owner (the typical case in JSF).
     */
    String getTargetNamespace();
    
    /**
     * @return a long-form piece of text, intended for end-user consumption,
     * that describes the attribute
     */
    String getDescription();
    
    /**
     * @return a short, user visible label text for the attribute.
     */
    String getDisplayName();
    
    /**
     * @return true if the attribute is required to be populated on the 
     * element.
     */
    boolean isRequired();
}
