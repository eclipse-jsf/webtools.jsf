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
package org.eclipse.jst.jsf.test.util.mock;

import java.util.EventObject;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;

public class MockResourceChangeEvent extends EventObject implements IResourceChangeEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 6425297945375951902L;
    private final MockResourceDelta _delta;
    private final IResource _resource;
    private final int _type;

    /**
     * @param resource
     * @param type
     * @param delta
     */
    public MockResourceChangeEvent(final IResource resource, final int type, final MockResourceDelta delta)
    {
        super(resource != null ? resource.getWorkspace() : new Object());
        _delta = delta;
        _resource = resource;
        _type = type;
    }
    public MockResourceChangeEvent(final int type, final MockResourceDelta delta)
    {
        this(null, type, delta);
    }

    public IMarkerDelta[] findMarkerDeltas(final String type, final boolean includeSubtypes)
    {
        throw new UnsupportedOperationException();
    }

    public int getBuildKind()
    {
        // we currently don't support build events
        return 0;
    }

    public IResourceDelta getDelta()
    {
        return _delta;
    }

    public IResource getResource()
    {
        return _resource;
    }

    public int getType()
    {
        return _type;
    }

}
