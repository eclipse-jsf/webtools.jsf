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
 * 
 * @author cbateman
 * 
 */
public abstract class JSFResourceFragment implements IJSFResourceFragment
{
    private final ResourceFragmentIdentifier _id;
    private final Type _type;

    /**
     * @param id
     * @param type
     */
    public JSFResourceFragment(final ResourceFragmentIdentifier id,
            final Type type)
    {
        _id = id;
        _type = type;
    }

    public ResourceFragmentIdentifier getId()
    {
        return _id;
    }

    public final Type getType()
    {
        return _type;
    }

    public abstract boolean isAccessible();
    
    public String toString()
    {
        String toString = _id != null ? _id.toString() : "?"; //$NON-NLS-1$
        return String.format("%s[%s]", toString, _type.toString()); //$NON-NLS-1$
    }
}
