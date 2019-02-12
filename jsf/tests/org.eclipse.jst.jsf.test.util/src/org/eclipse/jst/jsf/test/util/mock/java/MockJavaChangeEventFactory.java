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
package org.eclipse.jst.jsf.test.util.mock.java;

import static junit.framework.Assert.assertNotNull;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

public class MockJavaChangeEventFactory
{
    private final MockJDTWorkspaceContext _jdtContext;

    public MockJavaChangeEventFactory(final MockJDTWorkspaceContext jdtContext)
    {
        _jdtContext = jdtContext;
    }

    public ElementChangedEvent createSimpleJarAdded(final IProject project,
            final IPackageFragmentRoot packageRoots)
    {
        final IJavaElementDelta delta = createSimpleJarAddedDelta(project,
                packageRoots, IJavaElementDelta.F_ADDED_TO_CLASSPATH);
        return new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE);
    }

    public ElementChangedEvent createSimpleJarRemoved(
            final IProject project, final IPackageFragmentRoot packageRoots)
    {
        final IJavaElementDelta delta = createSimpleJarAddedDelta(project,
                packageRoots, IJavaElementDelta.F_REMOVED_FROM_CLASSPATH);
        return new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE);
    }

    public IJavaElementDelta createSimpleJarAddedDelta(final IProject project,
            final IPackageFragmentRoot packageRoots, final int flags)
    {
        final IJavaProject owningProject = _jdtContext
                .createJavaProject(project);
        assertNotNull(owningProject);
        final MockJavaElementDelta delta = new MockJavaElementDelta(packageRoots);
        delta.changed(flags);
        return delta;
    }
}
