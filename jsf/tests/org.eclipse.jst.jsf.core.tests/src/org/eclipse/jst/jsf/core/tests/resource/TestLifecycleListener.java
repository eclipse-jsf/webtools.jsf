/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.resource;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestLifecycleListener extends TestCase 
{
    private WebProjectTestEnvironment _webProjectTestEnv;
    private IResource                 _res1;
    private IResource                 _res2;

    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);

        _webProjectTestEnv = new WebProjectTestEnvironment(
                getClass().getName()+"_" + getName());
        _webProjectTestEnv.createProject(false);
        assertNotNull(_webProjectTestEnv);
        assertNotNull(_webProjectTestEnv.getTestProject());
        assertTrue(_webProjectTestEnv.getTestProject().isAccessible());

        _res1 = _webProjectTestEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
                , "/testfiles/jsps/testdata1.jsp.data", "/testdata1.jsp");
        _res2 = _webProjectTestEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
                , "/testfiles/jsps/testdata1.jsp.data", "/testdata2.jsp");
    }

    private void testInaccessibleCondition(IResource res, Runnable runnable, ReasonType reason)
    {
        assertTrue(res.isAccessible());

        LifecycleListener testListener = new LifecycleListener(res, ResourcesPlugin.getWorkspace());
        MockListener mockListener = new MockListener();
        testListener.addListener(mockListener);

        runnable.run();

        mockListener.assertAcceptedResourceInaccessible(res, reason);
        assertFalse(res.isAccessible());

        testListener.removeListener(mockListener);
        testListener.dispose();
    }
    
    private void testInaccessibleConditionMultiple(List<IResource> resources, Runnable runnable, List<ReasonType> reasons)
    {
        assertEquals(resources.size(), reasons.size());

        for (IResource res : resources)
        {
            assertTrue(res.isAccessible());
        }

        LifecycleListener testListener = new LifecycleListener(resources, ResourcesPlugin.getWorkspace());
        MockListener mockListener = new MockListener();
        testListener.addListener(mockListener);

        runnable.run();

        for (int i = 0; i < resources.size(); i++)
        {
            final IResource res = resources.get(i);
            final ReasonType reason = reasons.get(i);
            mockListener.assertAcceptedResourceInaccessible(res,reason);
            assertFalse(res.isAccessible());
        }

        testListener.removeListener(mockListener);
        testListener.dispose();
    }

    public void testProjectClosedForProject() throws Exception
    {
        final Runnable runnable = new Runnable()
        {
            public void run() 
            {
                // now close the project
                try {
                    _webProjectTestEnv.getTestProject().close(null);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }
            }

        };
        testInaccessibleCondition(_webProjectTestEnv.getTestProject(), runnable,  ReasonType.RESOURCE_PROJECT_CLOSED);
    }

    public void testProjectClosedForContainedResource() throws Exception
    {
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                // now close the project
                try {
                    _webProjectTestEnv.getTestProject().close(null);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        testInaccessibleCondition(_res1, runnable,  ReasonType.RESOURCE_PROJECT_CLOSED);
    }

    public void testProjectDeletedForProject() throws Exception
    {
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                // now delete the project
                try {
                    _webProjectTestEnv.getTestProject().delete(true, null);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        testInaccessibleCondition(_webProjectTestEnv.getTestProject(), runnable,  ReasonType.RESOURCE_DELETED);
    }

    public void testProjectDeletedForContainedResource() throws Exception
    {
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                // now delete the project
                try 
                {
                    _webProjectTestEnv.getTestProject().delete(true, null);
                } catch (CoreException e) 
                {
                    throw new RuntimeException(e);
                }
            }
        };
        testInaccessibleCondition(_res1, runnable,  ReasonType.RESOURCE_PROJECT_DELETED);
    }

    public void testResourceDeleted() throws Exception
    {
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                // now delete the resource
                try {
                    _res1.delete(true, null);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        testInaccessibleCondition(_res1, runnable,  ReasonType.RESOURCE_DELETED);
    }

    public void testMultipleResourcesDeleted() throws Exception
    {
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                // now delete the resource
                try {
                    _res1.delete(true, null);
                    _res2.delete(true, null);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        
        List<IResource> resources = new ArrayList<IResource>();
        resources.add(_res1);
        resources.add(_res2);
        List<ReasonType> reasons = new ArrayList<ReasonType>(); 
        reasons.add(ReasonType.RESOURCE_DELETED);
        reasons.add(ReasonType.RESOURCE_DELETED);
        testInaccessibleConditionMultiple(resources, runnable, reasons);
    }

    public void testMultipleResourcesProjectClosed() throws Exception
    {
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                // now delete the resource
                try {
                   _res1.getProject().close(null);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        
        List<IResource> resources = new ArrayList<IResource>();
        resources.add(_res1);
        resources.add(_res2);
        List<ReasonType> reasons = new ArrayList<ReasonType>(); 
        reasons.add(ReasonType.RESOURCE_PROJECT_CLOSED);
        reasons.add(ReasonType.RESOURCE_PROJECT_CLOSED);
        testInaccessibleConditionMultiple(resources, runnable, reasons);
    }

    public void testAddResource() throws Exception
    {
        assertTrue(_res1.isAccessible());
        assertTrue(_res2.isAccessible());

        LifecycleListener testListener = new LifecycleListener(_res1, ResourcesPlugin.getWorkspace());
        MockListener mockListener = new MockListener();
        testListener.addListener(mockListener);
        testListener.addResource(_res2);

        _res1.getProject().close(null);

        mockListener.assertAcceptedResourceInaccessible(_res1, ReasonType.RESOURCE_PROJECT_CLOSED);
        mockListener.assertAcceptedResourceInaccessible(_res2, ReasonType.RESOURCE_PROJECT_CLOSED);
        assertFalse(_res1.isAccessible());
        assertFalse(_res2.isAccessible());

        testListener.removeListener(mockListener);
        testListener.dispose();
    }
    
    public void testAddRemoveResource() throws Exception
    {
        assertTrue(_res1.isAccessible());
        assertTrue(_res2.isAccessible());

        LifecycleListener testListener = new LifecycleListener(_res1, ResourcesPlugin.getWorkspace());
        MockListener mockListener = new MockListener();
        testListener.addListener(mockListener);
        testListener.addResource(_res2);
        testListener.removeResource(_res1);
        
        _res1.getProject().close(null);

        // we removed res1, so should find an event for it
        mockListener.assertNoAcceptedResourceInaccessible(_res1, ReasonType.RESOURCE_PROJECT_CLOSED);
        mockListener.assertAcceptedResourceInaccessible(_res2, ReasonType.RESOURCE_PROJECT_CLOSED);
        assertFalse(_res1.isAccessible());
        assertFalse(_res2.isAccessible());

        testListener.removeListener(mockListener);
        testListener.dispose();
    }

    public void testRemoveListenerResource() throws Exception
    {
        assertTrue(_res1.isAccessible());
        assertTrue(_res2.isAccessible());

        LifecycleListener testListener = new LifecycleListener(_res1, ResourcesPlugin.getWorkspace());
        testListener.addResource(_res2);
        MockListener mockListener = new MockListener();
        MockListener mockListener2 = new MockListener();
        
        testListener.addListener(mockListener);
        testListener.addListener(mockListener2);
        _res1.delete(true, null);

        // both listeners should get the event on res1
        mockListener.assertAcceptedResourceInaccessible(_res1, ReasonType.RESOURCE_DELETED);
        mockListener2.assertAcceptedResourceInaccessible(_res1, ReasonType.RESOURCE_DELETED);
        assertFalse(_res1.isAccessible());

        // remove the listener for mock2
        testListener.removeListener(mockListener2);

        _res2.delete(true, null);

        // the first mockListener should get it
        mockListener.assertAcceptedResourceInaccessible(_res2, ReasonType.RESOURCE_DELETED);
        // the second one was removed before the delete
        // so it should not have accepted an event
        mockListener2.assertNoAcceptedResourceInaccessible(_res2, ReasonType.RESOURCE_DELETED);
        assertFalse(_res2.isAccessible());

        testListener.removeListener(mockListener);
        testListener.dispose();
    }
    
    public void testResourceChangedEvent() throws Exception
    {
        assertTrue(_res1.isAccessible());
        assertTrue(_res2.isAccessible());

        LifecycleListener testListener = new LifecycleListener(_res1, ResourcesPlugin.getWorkspace());
        testListener.addResource(_res2);
        MockListener mockListener = new MockListener();
        testListener.addListener(mockListener);
        
        // simulate a content change
        _res1.touch(null);

        mockListener.assertAcceptedEvent(_res1, EventType.RESOURCE_CHANGED, ReasonType.RESOURCE_CHANGED_CONTENTS);

        testListener.removeListener(mockListener);
        testListener.dispose();
    }
    
    public void testDisposeAfterEvent() throws Exception
    {
        LifecycleListener testListener = new LifecycleListener(_res1, ResourcesPlugin.getWorkspace());
        MockListenerThatDoesDispose mockListener = new MockListenerThatDoesDispose();
        testListener.addListener(mockListener);

        _res1.delete(true, null);
        
        assertTrue(testListener.isDisposed());
        
        boolean caughtAssertion = false;
        try
        {
            testListener.addListener(mockListener);
        }
        catch (IllegalStateException ae)
        {
            caughtAssertion = true;
        }
        
        assertTrue(caughtAssertion);
    }

    /**
     * A mock object that tests the disposeAfterEvent flag.
     * @author cbateman
     *
     */
    private class MockListenerThatDoesDispose implements IResourceLifecycleListener
    {
        public EventResult acceptEvent(ResourceLifecycleEvent event) 
        {
            EventResult result = EventResult.getDisposeAfterEventResult();
            return result;
        }
    }
}
