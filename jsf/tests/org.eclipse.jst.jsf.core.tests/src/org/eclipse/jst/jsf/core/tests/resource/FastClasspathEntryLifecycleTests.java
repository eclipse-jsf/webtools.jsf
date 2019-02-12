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
package org.eclipse.jst.jsf.core.tests.resource;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jst.jsf.common.internal.resource.ClasspathEntryLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.IClasspathLifecycleListener.ClasspathLifecycleEvent;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.IWorkspaceContext;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.eclipse.jst.jsf.test.util.mock.java.MockJDTWorkspaceContext;
import org.eclipse.jst.jsf.test.util.mock.java.MockJavaChangeEventFactory;
import org.eclipse.jst.jsf.test.util.mock.java.MockJavaCoreMediator;
import org.eclipse.jst.jsf.test.util.mock.java.MockJavaProject;
import org.eclipse.jst.jsf.test.util.mock.java.MockPackageFragmentRoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class FastClasspathEntryLifecycleTests
{
    private IWorkspaceContext _wsContext;
    private MockJavaChangeEventFactory _factory;
    private IProject _project;
    private MockJDTWorkspaceContext _jdtContext;
    private MockJavaProject _javaProject;

    @Before
    public void setUp() throws Exception
    {
        _wsContext = new MockWorkspaceContext();
        _project = _wsContext.createProject("SomeTestProject");
        _jdtContext = new MockJDTWorkspaceContext(_wsContext);
        _javaProject = _jdtContext.createJavaProject(_project);
        _factory = new MockJavaChangeEventFactory(_jdtContext);
    }

    @Test
    public void testAddJarTo()
    {
        final ClasspathEntryLifecycleListener listener = new ClasspathEntryLifecycleListener(
                _project, new MockJavaCoreMediator(_jdtContext));
        ClasspathTestListener testListener = new ClasspathTestListener(
                _jdtContext, listener);
        IPackageFragmentRoot fragRoot = new MockPackageFragmentRoot(
                _javaProject, new Path(
                        "/WebContent/WEB-INF/lib/my.jar"));
        ElementChangedEvent event = _factory.createSimpleJarAdded(_project,
                fragRoot);
        testListener.fireAndExpect(event, event.getDelta().getElement(),
                ClasspathLifecycleEvent.Type.ADDED);
        listener.dispose();
    }

    @Test
    public void testRemoveJarFrom()
    {
        final ClasspathEntryLifecycleListener listener = new ClasspathEntryLifecycleListener(
                _project, new MockJavaCoreMediator(_jdtContext));
        ClasspathTestListener testListener = new ClasspathTestListener(
                _jdtContext, listener);
        IPackageFragmentRoot fragRoot = new MockPackageFragmentRoot(
                _javaProject, new Path(
                        "/WebContent/WEB-INF/lib/my.jar"));
        ElementChangedEvent event = _factory.createSimpleJarRemoved(_project,
                fragRoot);
        testListener.fireAndExpect(event, event.getDelta().getElement(),
                ClasspathLifecycleEvent.Type.REMOVED);
        listener.dispose();
    }
}
