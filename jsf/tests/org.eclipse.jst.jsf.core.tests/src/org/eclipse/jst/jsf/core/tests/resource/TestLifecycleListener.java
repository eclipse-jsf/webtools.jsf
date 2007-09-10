package org.eclipse.jst.jsf.core.tests.resource;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestLifecycleListener extends TestCase 
{
    private WebProjectTestEnvironment _webProjectTestEnv;
    private IResource                 _res;
    
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

        _res = _webProjectTestEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
                , "/testfiles/jsps/testdata1.jsp.data", "/testdata1.jsp");
    }

    private void testInaccessibleCondition(IResource res, Runnable runnable, ReasonType reason)
    {
        assertTrue(res.isAccessible());

        LifecycleListener testListener = new LifecycleListener(res);
        MockListener mockListener = new MockListener();
        testListener.addListener(mockListener);

        runnable.run();

        mockListener.assertAcceptedResourceInaccessible(res, reason);
        assertFalse(res.isAccessible());

        testListener.removeListener(mockListener);
        testListener.dispose();
    }

    public void testProjectClosedForProject() throws Exception
    {
        Runnable runnable = new Runnable()
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
        Runnable runnable = new Runnable()
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
        testInaccessibleCondition(_res, runnable,  ReasonType.RESOURCE_PROJECT_CLOSED);
    }

    public void testProjectDeletedForProject() throws Exception
    {
        Runnable runnable = new Runnable()
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
        Runnable runnable = new Runnable()
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
        testInaccessibleCondition(_res, runnable,  ReasonType.RESOURCE_PROJECT_DELETED);
    }

    public void testResourceDeleted() throws Exception
    {
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                // now delete the resource
                try {
                    _res.delete(true, null);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        testInaccessibleCondition(_res, runnable,  ReasonType.RESOURCE_DELETED);
    }

    public void testDisposeAfterEvent() throws Exception
    {
        LifecycleListener testListener = new LifecycleListener(_res);
        MockListenerThatDoesDispose mockListener = new MockListenerThatDoesDispose();
        testListener.addListener(mockListener);

        _res.delete(true, null);
        
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

    private class MockListener implements IResourceLifecycleListener
    {
        private List<ResourceLifecycleEvent>   acceptedEvents = 
            new ArrayList<ResourceLifecycleEvent>();

        public EventResult acceptEvent(ResourceLifecycleEvent event) 
        {
            acceptedEvents.add(event);
            return new EventResult();
        }

        public void assertAcceptedResourceInaccessible(final IResource res, final ReasonType  reason)
        {
            for (ResourceLifecycleEvent event : acceptedEvents)
            {
                if (event.getEventType() == EventType.RESOURCE_INACCESSIBLE
                        && event.getAffectedResource() == res)
                {
                    if (reason == event.getReasonType())
                    {
                        return;
                    }
                    else
                    {
                        System.out.printf("Expected event found with different result: %s instead of %s", event.getReasonType().toString(), reason.toString());
                    }
                }
            }

            // if we get to here then we have failed to find the expected
            // event
            fail("Expected to find RESOURCE_INACCESSIBLE event for resource: "+res.toString());
        }
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
            EventResult result = new EventResult();
            result.setDisposeAfterEvent(true);
            return result;
        }
    }
}
