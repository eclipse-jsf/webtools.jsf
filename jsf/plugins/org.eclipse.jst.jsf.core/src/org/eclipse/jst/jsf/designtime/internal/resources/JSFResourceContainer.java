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
 * A container for JSFResource's.  This is a fragment in that it's identifier
 * may not point to a valid JSFResource but rather a portion of that nonetheless
 * maps to some interesting underlying contain object such as an IFolder.
 * 
 * @author cbateman
 *
 */
public abstract class JSFResourceContainer extends JSFResourceFragment implements IJSFResourceContainer
{

    /**
     * @param id
     */
    public JSFResourceContainer(ResourceFragmentIdentifier id)
    {
        super(id, Type.CONTAINER);
    }

    @Override
    public abstract boolean isAccessible();

}
