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
package org.eclipse.jst.jsf.designtime.internal.resources;

/**
 * An object that backs a fragment of a JSF resource. A fragment may be a full
 * fledged JSF resource or a may be another interesting related object such as
 * the library folder that holds a resource.
 * 
 * @author cbateman
 * 
 */
public interface IJSFResourceFragment
{

    /**
     * @return true if the fragment is accessible
     */
    public abstract boolean isAccessible();

    /**
     * @return the type of this fragment.
     */
    public abstract Type getType();

    /**
     * @return the id of the fragment
     */
    public ResourceFragmentIdentifier getId();

    /**
     * The type of a fragment
     * 
     */
    public enum Type
    {
        /**
         * A fragment of this type is a full-fledged JSF Resource. A fragment of
         * this type can always be cast to IJSFResource.
         */
        RESOURCE,
        /**
         * A fragment of this type is a container for actual RESOURCE's or other
         * fragments (or both). A fragment of this type can always be cast to
         * IJSFResourceContainer.
         */
        CONTAINER;
    }

}
