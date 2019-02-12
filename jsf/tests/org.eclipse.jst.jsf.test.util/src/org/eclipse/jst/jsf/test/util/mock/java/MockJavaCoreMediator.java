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

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.jsf.common.internal.resource.ClasspathEntryLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.JavaCoreMediator;
import org.eclipse.jst.jsf.test.util.mock.MockProject;

public class MockJavaCoreMediator extends JavaCoreMediator
{
    private final MockJDTWorkspaceContext _jdtContext;

    public MockJavaCoreMediator(final MockJDTWorkspaceContext jdtContext)
    {
        _jdtContext = jdtContext;
    }

    @Override
    public IJavaProject create(final IProject project)
    {
        return _jdtContext.createJavaProject((MockProject) project);
    }

    @Override
    public void addElementChangedListener(IElementChangedListener listener)
    {
        _jdtContext.addElementChangedListener(listener);
    }

    @Override
    public void removeElementChangeListener(
            ClasspathEntryLifecycleListener listener)
    {
        _jdtContext.removeElementChangedListener(listener);
    }
}
