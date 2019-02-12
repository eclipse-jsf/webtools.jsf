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
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceTracker;

class TaglibFileTracker extends ResourceTracker<IFile>
{
    private String _uri;
    private TaglibResourceManager _manager;
    private final ILibraryChangeHandler _handler;

    public TaglibFileTracker(final IFile file, final TaglibResourceManager manager,
            final ILibraryChangeHandler handler)
    {
        super(file);
        _manager = manager;
        _manager.addListener(this);
        _handler = handler;
    }

    public String getUri()
    {
        return _uri;
    }

    public final void setUri(final String uri)
    {
        _uri = uri;
    }

    @Override
    public void dispose()
    {
        _manager.removeListener(this);
        _manager = null;
    }

    @Override
    protected void fireResourceInAccessible(final IResource resource, final ReasonType reasonType)
    {
        // removed resources kick a remove event
        _handler.removed(_uri, getResource());
    }

    @Override
    protected void fireResourceChanged(final IResource resource, final ReasonType reasonType)
    {
        // changed resources kick a change event
        _handler.changed(_uri, getResource());
    }

    @Override
    protected void fireResourceAdded(final IResource affectedResource, final ReasonType reasonType)
    {
        // added resources kick an add event.
        _handler.added(getResource());
    }
}