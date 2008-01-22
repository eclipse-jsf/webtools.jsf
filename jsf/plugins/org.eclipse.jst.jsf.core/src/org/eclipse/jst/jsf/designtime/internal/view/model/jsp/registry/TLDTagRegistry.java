package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.CompositeTagResolvingStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.DefaultJSPTagResolver;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IdentifierOrderedIteratorPolicy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDNamespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TagIntrospectingStrategy;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;

/**
 * Registry of all tld-defined tags for a particular project classpath
 * 
 * @author cbateman
 * 
 */
public final class TLDTagRegistry
{
    // STATIC
    private final static Map<IProject, TLDTagRegistry> INSTANCES;
    private final static ILock CRITICAL_SECTION;
    private static LifecycleListener LIFECYCLE_LISTENER;

    static
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions
                    .log("TLDTagRegistry: Static initialization TLDTagRegistry");
        }
        INSTANCES = new HashMap<IProject, TLDTagRegistry>();
        CRITICAL_SECTION = Job.getJobManager().newLock();
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions
                    .log("TLDTagRegistry: Done static initialization TLDTagRegistry");
        }
    }

    /**
     * TODO: pass in a ProgressMonitor since this may be long running if the
     * registry is being requested and lazily initialized.
     * 
     * @param project
     * @return the TLD registry for the project's classpath
     */
    public static TLDTagRegistry getRegistry(final IProject project)
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry: Getting registry for "
                    + project.toString());
        }

        TLDTagRegistry instance = null;
        boolean alreadyExisted = true;

        CRITICAL_SECTION.acquire();
        try
        {
            instance = INSTANCES.get(project);

            if (instance == null)
            {
                alreadyExisted = false;
                if (LIFECYCLE_LISTENER == null)
                {
                    LIFECYCLE_LISTENER = new LifecycleListener(project);
                }
                else
                {
                    LIFECYCLE_LISTENER.addResource(project);
                }

                instance = new TLDTagRegistry(project, LIFECYCLE_LISTENER);
                INSTANCES.put(project, instance);
            }

            return instance;
        }
        finally
        {
            CRITICAL_SECTION.release();

            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
            {
                JSFCoreTraceOptions.log("TLDTagRegistry: Acquired instance "
                        + instance.toString());
                JSFCoreTraceOptions.log("TLDTagRegistry: already existed="
                        + alreadyExisted);
            }
        }
    }

    private static void dispose(final IProject project)
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry: Disposing for project "
                    + project.toString());
        }

        CRITICAL_SECTION.acquire();
        try
        {
            TLDTagRegistry processor = INSTANCES.get(project);

            if (processor != null)
            {
                INSTANCES.remove(project);

                if (!processor.isDisposed())
                {
                    processor.dispose();
                    LIFECYCLE_LISTENER.removeResource(project);
                }

            }

            if (INSTANCES.size() == 0)
            {
                // if we no longer have any resources being tracked,
                // then dispose the lifecycle listener
                LIFECYCLE_LISTENER.dispose();
                if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
                {
                    JSFCoreTraceOptions
                            .log("TLDTagRegistry: Disposing lifecycle listener "
                                    + project.toString());
                }
            }
        }
        finally
        {
            CRITICAL_SECTION.release();
            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
            {
                JSFCoreTraceOptions
                        .log("TLDTagRegistry: Done disposing registry for "
                                + project.toString());
            }
        }
    }

    // INSTANCE
    private final IProject _project;
    private final Map<String, TLDNamespace> _nsResolved;
    private final CompositeTagResolvingStrategy<TLDElementDeclaration> _resolver;
    private final LifecycleListener _lifecycleListener;
    private boolean _hasBeenInitialized = false;
    private IResourceLifecycleListener _resListener;
    private final AtomicBoolean _isDisposed = new AtomicBoolean(false);

    private TLDTagRegistry(final IProject project,
            final LifecycleListener lifecycleListener)
    {
        _project = project;
        _nsResolved = new HashMap<String, TLDNamespace>();
        _resolver = new CompositeTagResolvingStrategy<TLDElementDeclaration>();

        // add the strategies
        _resolver.addStrategy(new TagIntrospectingStrategy(_project));
        _resolver.addStrategy(new DefaultJSPTagResolver(_project));

        // strategy ordering
        final List<String> strategyOrdering = new ArrayList<String>();
        strategyOrdering.add(DefaultJSPTagResolver.ID);
        strategyOrdering.add(TagIntrospectingStrategy.ID);

        final IdentifierOrderedIteratorPolicy<String> policy = new IdentifierOrderedIteratorPolicy<String>(
                strategyOrdering);
        _resolver.setOrderingPolicy(policy);

        // install lifecycle listener
        _lifecycleListener = lifecycleListener;
        _resListener = new IResourceLifecycleListener()
        {
            public EventResult acceptEvent(ResourceLifecycleEvent event)
            {
                final EventResult result = EventResult.getDefaultEventResult();

                // not interested
                if (!_project.equals(event.getAffectedResource()))
                {
                    return result;
                }

                if (event.getEventType() == EventType.RESOURCE_INACCESSIBLE)
                {
                    dispose(_project);
                }

                return result;
            }
        };

        _lifecycleListener.addListener(_resListener);
    }

    private void dispose()
    {
        // latch on the isDisposed flag so this block can only ever
        // execute once
        if (!_isDisposed.compareAndSet(false, true))
        {
            _lifecycleListener.removeListener(_resListener);
            _nsResolved.clear();
        }
    }

    /**
     * @return true if this registry is disposed. It is an error to use a
     *         disposed registry and behaviour of such a registry is not
     *         guaranteed.
     */
    public final boolean isDisposed()
    {
        return _isDisposed.get();
    }

    /**
     * This method may cause long-running operations to be executed. If runAfter
     * is non-null, any long running operations will schedule asynchronously on
     * a separate thread and on successful completion, runAfter will be executed
     * (no assumption should be made about what thread it is run on).
     * 
     * 
     * @return true if the refresh succeeds, false if an exception caused it to
     *         fail
     */
    public synchronized boolean refresh(/* final Runnable runAfter */)
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.refresh: start");
        }

        _nsResolved.clear();

        final boolean success = true;
        initialize();

        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions
                    .log("TLDTagRegistry.refresh: finished, success=" + success);
        }

        return success;
    }

    /**
     * @throws JavaModelException
     * @throws CoreException
     * @throws IOException
     */
    private void initialize()
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.initialize: start");
        }

        final ITaglibRecord[] tldrecs = TaglibIndex
                .getAvailableTaglibRecords(_project.getFullPath());
        final CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
        for (final ITaglibRecord tldrec : tldrecs)
        {
            final TLDDocument doc = (TLDDocument) factory
                    .createCMDocument(tldrec);
            if (doc != null)
            {
                _nsResolved.put(doc.getUri(), new TLDNamespace(doc, _resolver));
            }
        }

        _hasBeenInitialized = true;

        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.initialize: finished");
        }
    }

    /**
     * @return all tag libraries for a project tag registry
     */
    public final synchronized Collection<? extends Namespace> getAllTagLibraries()
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions
                    .log("TLDTagRegistry.getAllTagLibraries: start");
        }
        
        if (!_hasBeenInitialized)
        {
            initialize();
        }
        final Set<TLDNamespace> allTagLibraries = new HashSet<TLDNamespace>();
        allTagLibraries.addAll(_nsResolved.values());
        
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions
                    .log("TLDTagRegistry.getAllTagLibraries: finished");
        }
        return allTagLibraries;
    }

    public String toString()
    {
        return String
                .format(
                        "TLDRegistry for project %s, isDisposed=%s, hasBeenInitialized=%s, numberOfNamespace=%d",
                        _project.toString(), Boolean.valueOf(isDisposed()),
                        Boolean.valueOf(_hasBeenInitialized), Integer
                                .valueOf(_nsResolved.size()));
    }
}
