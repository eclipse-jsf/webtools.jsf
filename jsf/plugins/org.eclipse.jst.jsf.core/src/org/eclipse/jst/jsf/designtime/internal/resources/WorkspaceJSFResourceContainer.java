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

import org.eclipse.core.resources.IContainer;

/**
 * A concrete JSFResourceContainer that maps to a container object in the
 * workspace.
 * 
 * @author cbateman
 *
 */
public class WorkspaceJSFResourceContainer extends JSFResourceContainer implements IWorkspaceJSFResourceFragment
{

    private final IContainer _container;

    /**
     * @param id
     * @param container
     */
    public WorkspaceJSFResourceContainer(final ResourceFragmentIdentifier id,
            final IContainer container)
    {
        super(id);
        _container = container;
    }

    @Override
    public boolean isAccessible()
    {
        return _container.isAccessible();
    }

    /**
     * @return the container resource
     */
    public IContainer getResource()
    {
        return _container;
    }

}
