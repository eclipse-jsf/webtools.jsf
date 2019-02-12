/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.io.Serializable;
import java.util.Collection;

/**
 * Abstracts an xml namespace used to define a set of view tags
 * 
 */
public abstract class Namespace implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 6386464282396970948L;

    /**
     * @return the namespace uri. MUST NOT BE NULL
     */
    public abstract String getNSUri();

    /**
     * This call may be long running.  Call isInitialized to determine if all 
     * information is already cached, or if calling this method is likely
     * to take a long time.
     * 
     * @return the view elements in this namespace. May be empty but MUST NOT BE
     *         NULL.
     */
    public abstract Collection<? extends ITagElement> getViewElements();
    
    /**
     * This method should be light weight if possible.  Unlike calling
     * getViewElements().isEmpty, this call should not require that all
     * view element children of this namespace be analyzed and constructed in
     * order to succeed.
     * 
     * @return true if this name space has view element children
     */
    public abstract boolean hasViewElements();

    /**
     * If calculating all view elements is expensive, a Namespace implementation
     * may defer calculation until a specific element is requested.  A caller of
     * getViewElements (such as a UI that shows all tags), may wish to know if
     * the entire namespace has been initialized before calling it, since it may
     * be long running.
     * 
     * @return true if the namespace's elements have been fully initialized.  If this
     * method returns false, care should be taken in calling getViewElements() and
     * getViewElement() since they may be very long running.
     */
    public abstract boolean isInitialized();
    
    /**
     * @param name
     * @return the tag element called 'name' or null if none.
     */
    public abstract ITagElement getViewElement(final String name);

    /**
     * @return a user readable display name for this namespace or null if none.
     */
    public abstract String getDisplayName();

    public String toString()
    {
        return getNSUri();
    }
}
