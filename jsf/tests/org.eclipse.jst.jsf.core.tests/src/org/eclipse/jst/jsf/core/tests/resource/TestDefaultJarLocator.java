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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jst.jsf.common.internal.resource.ClasspathJarFile;
import org.eclipse.jst.jsf.common.internal.resource.DefaultJarLocator;
import org.eclipse.jst.jsf.common.internal.resource.IJarLocator.JarChangeEvent;
import org.eclipse.jst.jsf.common.internal.resource.IJarLocator.JarChangeEvent.Type;
import org.eclipse.jst.jsf.common.internal.resource.IJarLocator.JarChangeListener;
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
public class TestDefaultJarLocator
{
    private IWorkspaceContext _wsContext;
    private IProject _project;
    private MockJavaChangeEventFactory _factory;
    private MockJDTWorkspaceContext _jdtContext;
    private MockJavaProject _javaProject;

    @Before
    public void setUp() throws Exception
    {
        _wsContext = new MockWorkspaceContext();
        _project = _wsContext.createProject("SomeTestProject");
        _jdtContext = new MockJDTWorkspaceContext(_wsContext);
        _factory = new MockJavaChangeEventFactory(_jdtContext);
        _jdtContext.createCPELibraryInProject(_project, new Path(
                "/WEB-INF/WebContent/lib/nocareContents.jar"), new File(
                "./testfiles/faces-all-bogus.jar"));
        _javaProject = _jdtContext.createJavaProject(_project);
    }

    @Test
    public void testStart() throws Exception
    {
        final DefaultJarLocator jarLocator = new DefaultJarLocator(
                new MockJavaCoreMediator(_jdtContext));
        jarLocator.start(_project);
    }

    @Test
    public void testLocate()
    {
        final DefaultJarLocator jarLocator = new DefaultJarLocator(
                new MockJavaCoreMediator(_jdtContext));
        jarLocator.start(_project);
        Collection<? extends ClasspathJarFile> locate = jarLocator.locate(_project);
        assertEquals(1, locate.size());
        Collection<? extends ClasspathJarFile> jars = jarLocator.getJars(_project);
        assertEquals(1, locate.size());
        assertEquals(locate, jars);
    }

    @Test
    public void testAddJar()
    {
        final DefaultJarLocator jarLocator = new DefaultJarLocator(
                new MockJavaCoreMediator(_jdtContext));
        jarLocator.start(_project);
        final JarChangeEvent[] trappedEvents = new JarChangeEvent[1];
        jarLocator.addListener(new JarChangeListener()
        {
            @Override
            public void changed(final JarChangeEvent event)
            {
                trappedEvents[0] = event;
            }
        });
        IPackageFragmentRoot fragRoot = new MockPackageFragmentRoot(
                _javaProject, new Path(
                        "/WebContent/WEB-INF/lib/my.jar"));
        final ElementChangedEvent event = _factory.createSimpleJarAdded(
                _project, fragRoot);
        _jdtContext.fireElementChangedEvent(event);
        assertNotNull(trappedEvents[0]);
        assertEquals(Type.JAR_ADDED, trappedEvents[0].getType());
        assertEquals("/WebContent/WEB-INF/lib/my.jar", trappedEvents[0]
                .getJar().getPath().toString());
    }

    @Test
    public void testRemoveJar()
    {
        final DefaultJarLocator jarLocator = new DefaultJarLocator(
                new MockJavaCoreMediator(_jdtContext));
        jarLocator.start(_project);
        final JarChangeEvent[] trappedEvents = new JarChangeEvent[1];
        jarLocator.addListener(new JarChangeListener()
        {
            @Override
            public void changed(final JarChangeEvent event)
            {
                trappedEvents[0] = event;
            }
        });
        IPackageFragmentRoot fragRoot = new MockPackageFragmentRoot(
                _javaProject, new Path(
                        "/WebContent/WEB-INF/lib/my.jar"));
        final ElementChangedEvent event = _factory.createSimpleJarRemoved(
                _project, fragRoot);
        _jdtContext.fireElementChangedEvent(event);
        assertNotNull(trappedEvents[0]);
        assertEquals(Type.JAR_REMOVED, trappedEvents[0].getType());
        assertEquals("/WebContent/WEB-INF/lib/my.jar", trappedEvents[0]
                .getJar().getPath().toString());
    }

    @Test(expected = IllegalStateException.class)
    public void testLocateWithoutStart()
    {
        final DefaultJarLocator jarLocator = new DefaultJarLocator(
                new MockJavaCoreMediator(_jdtContext));
        jarLocator.locate(_project);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetJarsWithoutStart()
    {
        final DefaultJarLocator jarLocator = new DefaultJarLocator(
                new MockJavaCoreMediator(_jdtContext));
        jarLocator.getJars(_project);
    }
}
