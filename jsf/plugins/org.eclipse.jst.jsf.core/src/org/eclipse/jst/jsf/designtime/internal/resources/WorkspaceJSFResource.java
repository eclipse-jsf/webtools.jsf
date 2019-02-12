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

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;

/**
 * A JSF resource that corresponds to an object in the workspace.
 * @author cbateman
 *
 */
public class WorkspaceJSFResource extends JSFResource implements IWorkspaceJSFResourceFragment
{
    private final IResource _res;

    /**
     * @param id
     * @param res
     * @param contentTypeResolver 
     */
    public WorkspaceJSFResource(final ResourceIdentifier id, final IResource res, final ContentTypeResolver contentTypeResolver)
    {
        super(id, contentTypeResolver);
        _res = res;
    }

    public final IResource getResource()
    {
        return _res;
    }

    
    public final boolean isAccessible()
    {
        return _res.isAccessible();
    }
}
