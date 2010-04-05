/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.resource;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.ITestTracker;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.PerfTracker;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class LifecycleStressTest extends TestCase
{
    private final static int           NUM_PROJECTS = 50;
    private final static int           NUM_FILES    = 100;

    private Map<IProject, List<IFile>> _resources;
    private LifecycleListener          _listener;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _resources = new HashMap<IProject, List<IFile>>();
        _listener = new LifecycleListener(ResourcesPlugin.getWorkspace());

        for (int i = 0; i < NUM_PROJECTS; i++)
        {
            final WebProjectTestEnvironment webProjectTestEnv =
                new WebProjectTestEnvironment(getProjectName(getName(), i));
            webProjectTestEnv.createProject(false);
            assertNotNull(webProjectTestEnv);
            assertNotNull(webProjectTestEnv.getTestProject());
            assertTrue(webProjectTestEnv.getTestProject().isAccessible());

            final List<IFile> list = new ArrayList<IFile>();

            for (int j = 0; j < NUM_FILES; j++)
            {
                final IFile file =
                    (IFile) webProjectTestEnv.loadResourceInWebRoot(
                            TestsPlugin.getDefault().getBundle(),
                            "/testfiles/jsps/testdata1.jsp.data",
                            "/testdata_" + j + ".jsp");

                list.add(file);
                _listener.addResource(file);
            }
            _resources.put(webProjectTestEnv.getTestProject(), list);
        }
    }

    @Override
    protected void tearDown() throws Exception
    {
        for (final IProject project : _resources.keySet())
        {
            try
            {
                project.close(null);
                project.delete(true, null);
            }
            catch (final CoreException ce)
            {
                ce.printStackTrace(System.err);
            }
        }

        _resources.clear();
    }

    public void testChanges() throws Exception
    {
        final int NUM_ITERATIONS = 1000;
        final int[] projectIdx = new int[NUM_ITERATIONS];
        final int[] fileIdx = new int[NUM_ITERATIONS];

        final Random random = new Random();

        for (int i = 0; i < NUM_ITERATIONS; i++)
        {
            projectIdx[i] = random.nextInt(NUM_PROJECTS);
            fileIdx[i] = random.nextInt(NUM_FILES);
        }

        traceIntArray(System.out, "Project sequence: ", projectIdx);
        traceIntArray(System.out, "File sequence: ", fileIdx);

        final LifecycleTestTracker tracker = new LifecycleTestTracker(NUM_ITERATIONS);
        _listener.setTestTracker(tracker);

        for (int i = 0; i < NUM_ITERATIONS; i++)
        {
            final IProject project =
                ResourcesPlugin.getWorkspace().getRoot().getProject(
                        getProjectName(getName(), projectIdx[i]));
            final List<IFile> fileList = _resources.get(project);
            final MockListener listener = new MockListener();
            _listener.addListener(listener);

            // simulates a content change
            fileList.get(fileIdx[i]).touch(null);

            listener.assertAcceptedEvent(fileList.get(fileIdx[i]),
                    EventType.RESOURCE_CHANGED,
                    ReasonType.RESOURCE_CHANGED_CONTENTS);
            _listener.removeListener(listener);
        }

        tracker.report(System.out);
    }

    private String getProjectName(final String testName, final int idx)
    {
        return getClass().getName() + "_" + testName + idx;
    }

    private void traceIntArray(final PrintStream stream, final String prefix,
            final int[] intArray)
    {
        stream.print(prefix);

        for (int i = 0; i < intArray.length - 1; i++)
        {
            stream.print(intArray[i] + ",");
        }

        stream.print(intArray[intArray.length - 1]);
        stream.print("\n");
    }

    private static class LifecycleTestTracker implements ITestTracker
    {
        private final PerfTracker _resourceChangedTimes;
        // private final PerfTracker _findMemberTimes;

        private long              _lastResourceChangedId;
        // private long _lastFindMemberChangedId;
        private long              _curResourceChangedTime;

        // private long _curFindMemberChangedTime;

        public LifecycleTestTracker(final int numberOfDataPoints)
        {
            _resourceChangedTimes =
                new PerfTracker("ResourceChanged", numberOfDataPoints + 100);
            // _findMemberTimes =
            // new PerfTracker("findMember",
            // numberOfDataPoints*NUM_FILES*NUM_PROJECTS+100);
        }

        public void fireEvent(final Event event, final long seqId,
                final String eventLabel)
        {
            // get the current as quickly as possible
            final long curTime = System.nanoTime();

            switch (event)
            {
                case START_TRACKING:
                    if ("trackMethod_resourceChanged".equals(eventLabel))
                    {
                        _curResourceChangedTime = curTime;
                        _lastResourceChangedId = seqId;
                    }
                    else if ("testFindMember".equals(eventLabel))
                    {
                        // _curFindMemberChangedTime = curTime;
                        // _lastFindMemberChangedId = seqId;
                    }
                    break;

                case STOP_TRACKING:
                    if ("trackMethod_resourceChanged".equals(eventLabel))
                    {
                        assertEquals(_lastResourceChangedId, seqId);
                        _resourceChangedTimes.recordTime(curTime
                                - _curResourceChangedTime);
                    }
                    else if ("testFindMember".equals(eventLabel))
                    {
                        // assertEquals(_lastFindMemberChangedId, seqId);
                        // _findMemberTimes.recordTime(curTime -
                        // _curFindMemberChangedTime);
                    }
                    break;
            }
        }

        public void report(final PrintStream stream)
        {
            _resourceChangedTimes.printReport(System.out);
            // _findMemberTimes.printReport(System.out);
        }
    }
}
