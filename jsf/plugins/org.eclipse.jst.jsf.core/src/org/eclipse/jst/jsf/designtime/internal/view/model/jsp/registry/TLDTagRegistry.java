package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.internal.RunOnCompletionPattern;
import org.eclipse.jst.jsf.common.internal.policy.IdentifierOrderedIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.CompositeTagResolvingStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.DefaultJSPTagResolver;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDNamespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TagIntrospectingStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.UnresolvedJSPTagResolvingStrategy;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibIndexDelta;
import org.eclipse.jst.jsp.core.taglib.ITaglibIndexListener;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;

/**
 * Registry of all tld-defined tags for a particular project classpath
 * 
 * @author cbateman
 * 
 */
public final class TLDTagRegistry implements ITagRegistry
{
    // STATIC
    private final static Map<IProject, TLDTagRegistry> INSTANCES;
    private final static ILock                         CRITICAL_SECTION;
    private static TagIndexListener                    TAG_INDEX_LISTENER;
    private static LifecycleListener                   LIFECYCLE_LISTENER;

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

                // if tag index listener does exist, add it
                if (TAG_INDEX_LISTENER == null)
                {
                    TAG_INDEX_LISTENER = new TagIndexListener();
                    TaglibIndex.addTaglibIndexListener(TAG_INDEX_LISTENER);
                }
            }

            return instance;
        }
        finally
        {
            CRITICAL_SECTION.release();

            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
            {
                if (instance != null)
                {
                    JSFCoreTraceOptions
                            .log("TLDTagRegistry: Acquired instance "
                                    + instance.toString());
                }
                JSFCoreTraceOptions.log("TLDTagRegistry: already existed="
                        + alreadyExisted);
            }
        }
    }

    private static boolean doesRegistryExist(final IProject project)
    {
        CRITICAL_SECTION.acquire();
        try
        {
            return INSTANCES.get(project) != null;
        }
        finally
        {
            CRITICAL_SECTION.release();
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
            final TLDTagRegistry processor = INSTANCES.get(project);

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
                if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
                {
                    JSFCoreTraceOptions
                            .log("TLDTagRegistry: No instances left.  Disposing static values.");
                }

                // if we no longer have any resources being tracked,
                // then dispose the lifecycle listener
                LIFECYCLE_LISTENER.dispose();
                LIFECYCLE_LISTENER = null;
                if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
                {
                    JSFCoreTraceOptions
                            .log("TLDTagRegistry: Disposing lifecycle listener "
                                    + project.toString());
                }

                // no point in listening for events on taglibs if no outstanding
                // registries
                // if tag index listener does exist, add it
                if (TAG_INDEX_LISTENER != null)
                {
                    TaglibIndex.removeTaglibIndexListener(TAG_INDEX_LISTENER);
                    TAG_INDEX_LISTENER = null;
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
    private final IProject                                             _project;
    private final Map<String, TLDNamespace>                            _nsResolved;
    private final CompositeTagResolvingStrategy<TLDElementDeclaration> _resolver;
    private final LifecycleListener                                    _lifecycleListener;
    private boolean                                                    _hasBeenInitialized = false;
    private final IResourceLifecycleListener                           _resListener;
    private final AtomicBoolean                                        _isDisposed         = new AtomicBoolean(
                                                                                                   false);
    private final ConcurrentLinkedQueue<LibraryOperation>              _changeOperations   = new ConcurrentLinkedQueue<LibraryOperation>();
    private final Job                                                  _changeJob;
    private final List<ITagRegistryListener>                           _listeners;

    private TLDTagRegistry(final IProject project,
            final LifecycleListener lifecycleListener)
    {
        _project = project;
        _nsResolved = new HashMap<String, TLDNamespace>();
        _resolver = new CompositeTagResolvingStrategy<TLDElementDeclaration>();

        // add the strategies
        _resolver.addStrategy(new TagIntrospectingStrategy(_project));
        _resolver.addStrategy(new DefaultJSPTagResolver(_project));
        // makes sure that a tag element will always be created for any
        // given tag definition even if other methods fail
        _resolver.addStrategy(new UnresolvedJSPTagResolvingStrategy());

        // strategy ordering
        final List<String> strategyOrdering = new ArrayList<String>();
        strategyOrdering.add(DefaultJSPTagResolver.ID);
        strategyOrdering.add(TagIntrospectingStrategy.ID);
        strategyOrdering.add(UnresolvedJSPTagResolvingStrategy.ID);

        final IdentifierOrderedIteratorPolicy<String> policy = new IdentifierOrderedIteratorPolicy<String>(
                strategyOrdering);
        _resolver.setOrderingPolicy(policy);

        // install lifecycle listener
        _lifecycleListener = lifecycleListener;
        _resListener = new IResourceLifecycleListener()
        {
            public EventResult acceptEvent(final ResourceLifecycleEvent event)
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
        _listeners = new CopyOnWriteArrayList<ITagRegistryListener>();
        _changeJob = new ChangeJob(project.getName());
    }

    private void dispose()
    {
        // latch on the isDisposed flag so this block can only ever
        // execute once
        if (!_isDisposed.compareAndSet(false, true))
        {
            fireEvent(new TagRegistryChangeEvent(this,
                    TagRegistryChangeEvent.EventType.REGISTRY_DISPOSED));
            _lifecycleListener.removeListener(_resListener);
            _nsResolved.clear();
            _changeOperations.clear();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.ITagRegistry#isDisposed()
     */
    public final boolean isDisposed()
    {
        return _isDisposed.get();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.ITagRegistry#refresh()
     */
    public void refresh(final Runnable runAfter)
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.refresh: start");
        }

        final Job refreshJob = new Job("Refreshing JSP tag registry for "
                + _project.getName())
        {
            @Override
            protected IStatus run(final IProgressMonitor monitor)
            {
                synchronized (TLDTagRegistry.this)
                {
                    if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
                    {
                        JSFCoreTraceOptions
                                .log("TLDTagRegistry.refresh: start");
                    }

                    final List<Namespace> namespaces = new ArrayList(
                            _nsResolved.values());

                    _nsResolved.clear();

                    fireEvent(new TagRegistryChangeEvent(TLDTagRegistry.this,
                            TagRegistryChangeEvent.EventType.REMOVED_NAMESPACE,
                            namespaces));
                    initialize();

                    return Status.OK_STATUS;
                }
            }
        };

        final RunOnCompletionPattern runPattern = new RunOnCompletionPattern(
                refreshJob, runAfter);
        runPattern.run();

        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.refresh: finished");
        }
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
        final List<Namespace> affectedObjects = new ArrayList<Namespace>();
        for (final ITaglibRecord tldrec : tldrecs)
        {
            // defer the event
            final Namespace ns = initialize(tldrec, false);

            if (ns != null)
            {
                affectedObjects.add(ns);
            }
        }

        _hasBeenInitialized = true;

        if (affectedObjects.size() > 0)
        {
            fireEvent(new TagRegistryChangeEvent(this,
                    TagRegistryChangeEvent.EventType.ADDED_NAMESPACE,
                    affectedObjects));
        }

        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.initialize: finished");
        }
    }

    private TLDNamespace initialize(final ITaglibRecord tagRecord,
            final boolean fireEvent)
    {
        final CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
        final TLDDocument doc = (TLDDocument) factory
                .createCMDocument(tagRecord);
        if (doc != null)
        {
            final TLDNamespace ns = new TLDNamespace(doc, _resolver);
            _nsResolved.put(doc.getUri(), ns);

            if (!fireEvent)
            {
                fireEvent(new TagRegistryChangeEvent(this,
                        TagRegistryChangeEvent.EventType.ADDED_NAMESPACE,
                        Collections.singletonList(ns)));
            }

            return ns;
        }

        // no new namespace
        return null;
    }

    private void remove(final ITaglibRecord tagRecord)
    {
        final CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
        final TLDDocument doc = (TLDDocument) factory
                .createCMDocument(tagRecord);

        final TLDNamespace ns = _nsResolved.remove(doc.getUri());

        if (ns != null)
        {
            fireEvent(new TagRegistryChangeEvent(this,
                    TagRegistryChangeEvent.EventType.REMOVED_NAMESPACE,
                    Collections.singletonList(ns)));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.ITagRegistry#getAllTagLibraries()
     */
    public final synchronized Collection<? extends Namespace> getAllTagLibraries()
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.getAllTagLibraries: start");
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.ITagRegistry#getTagLibrary(java.lang.String)
     */
    public final synchronized Namespace getTagLibrary(final String uri)
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions.log("TLDTagRegistry.getTagLibrary: start uri="
                    + uri);
        }

        if (!_hasBeenInitialized)
        {
            initialize();
        }

        final Namespace ns = _nsResolved.get(uri);

        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY)
        {
            JSFCoreTraceOptions
                    .log("TLDTagRegistry.getTagLibrary: finished, result="
                            + ns.toString());
        }
        return ns;
    }

    @Override
    public String toString()
    {
        return String
                .format(
                        "TLDRegistry for project %s, isDisposed=%s, hasBeenInitialized=%s, numberOfNamespace=%d",
                        _project.toString(), Boolean.valueOf(isDisposed()),
                        Boolean.valueOf(_hasBeenInitialized), Integer
                                .valueOf(_nsResolved.size()));
    }

    public void addListener(final ITagRegistryListener listener)
    {
        if (!_listeners.contains(listener))
        {
            _listeners.add(listener);
        }
    }

    public void removeListener(final ITagRegistryListener listener)
    {
        _listeners.remove(listener);
    }

    /**
     * @param event
     */
    private void fireEvent(final TagRegistryChangeEvent event)
    {
        for (final ITagRegistryListener listener : _listeners)
        {
            try
            {
                listener.registryChanged(event);
            }
            catch (final Exception e)
            {
                JSFCorePlugin.log(new Exception(e),
                        "During change event notification");
            }
        }
    }

    private void addLibraryOperation(final LibraryOperation operation)
    {
        _changeOperations.add(operation);
        _changeJob.schedule();
    }

    private LibraryOperation createAddOperation(final ITaglibRecord changeRecord)
    {
        return new AddTagLibrary(changeRecord);
    }

    private LibraryOperation createRemoveOperation(
            final ITaglibRecord changeRecord)
    {
        return new RemoveTagLibrary(changeRecord);
    }

    private LibraryOperation createChangeOperation(
            final ITaglibRecord changeRecord)
    {
        if (changeRecord == null)
        {
            throw new IllegalArgumentException();
        }
        return new ChangeTagLibrary(changeRecord);
    }

    private class ChangeJob extends Job
    {
        private int _rescheduleTime = -1;

        public ChangeJob(final String projectName)
        {
            super("Update job for project " + projectName);
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor)
        {
            synchronized (TLDTagRegistry.this)
            {
                _rescheduleTime = -1;

                LibraryOperation operation = null;
                final MultiStatus multiStatus = new MultiStatus(
                        JSFCorePlugin.PLUGIN_ID, 0, "Result of change job",
                        new Throwable());
                while ((operation = _changeOperations.poll()) != null)
                {
                    _rescheduleTime = 10000; // ms

                    operation.run();
                    multiStatus.add(operation.getResult());
                }

                if (_rescheduleTime >= 0 && !monitor.isCanceled())
                {
                    // if any operations were found on this run, reschedule
                    // to run again in 10seconds based on the assumption that
                    // events may be coming in bursts
                    schedule(_rescheduleTime);
                }

                return multiStatus;
            }
        }
    }

    private abstract class LibraryOperation implements Runnable
    {
        protected final ITaglibRecord _changeRecord;
        private IStatus               _result;

        protected LibraryOperation(final ITaglibRecord changeRecord)
        {
            _changeRecord = changeRecord;
        }

        public final void run()
        {
            try
            {
                _result = doRun();
            }
            catch (final Exception e)
            {
                _result = new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID,
                        "Problem during run", e);
            }
        }

        public IStatus getResult()
        {
            return _result;
        }

        protected abstract IStatus doRun();
    }

    private class AddTagLibrary extends LibraryOperation
    {
        public AddTagLibrary(final ITaglibRecord newRecord)
        {
            super(newRecord);
        }

        @Override
        protected IStatus doRun()
        {
            synchronized (TLDTagRegistry.this)
            {
                // fire change event if applicable
                initialize(_changeRecord, true);
                return Status.OK_STATUS;
            }
        }
    }

    private class RemoveTagLibrary extends LibraryOperation
    {
        protected RemoveTagLibrary(final ITaglibRecord changeRecord)
        {
            super(changeRecord);
        }

        @Override
        protected IStatus doRun()
        {
            remove(_changeRecord);
            return Status.OK_STATUS;

        }

    }

    private class ChangeTagLibrary extends LibraryOperation
    {

        protected ChangeTagLibrary(final ITaglibRecord changeRecord)
        {
            super(changeRecord);
        }

        @Override
        protected IStatus doRun()
        {
            IStatus result = null;

            synchronized (TLDTagRegistry.this)
            {
                new RemoveTagLibrary(_changeRecord).doRun();

                if (result.getSeverity() != IStatus.ERROR
                        && result.getSeverity() != IStatus.CANCEL)
                {
                    result = new AddTagLibrary(_changeRecord).doRun();
                }
            }

            return result;
        }

    }

    private static class TagIndexListener implements ITaglibIndexListener
    {
        public void indexChanged(final ITaglibIndexDelta delta)
        {
            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
            {
                JSFCoreTraceOptions.log("TagIndexListener.indexChanged: start");
            }

            visitDelta(delta);

            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
            {
                JSFCoreTraceOptions
                        .log("TagIndexListener.indexChanged: finish");
            }
        }

        private void visitDelta(final ITaglibIndexDelta delta)
        {
            final IProject project = delta.getProject();

            // if a registry hasn't been created for this project yet,
            // then don't worry about tag library change events on it.
            if (doesRegistryExist(project))
            {
                final TLDTagRegistry registry = TLDTagRegistry
                        .getRegistry(project);

                if (!registry.isDisposed()
                        && delta.getTaglibRecord() != null)
                {
                    switch (delta.getKind())
                    {
                        case ITaglibIndexDelta.ADDED:
                        {
                            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
                            {
                                JSFCoreTraceOptions.log(String.format(
                                        "Processing add change: project=%s",
                                        project.getName()));
                            }

                            registry
                                    .addLibraryOperation(registry
                                            .createAddOperation(delta
                                                    .getTaglibRecord()));
                        }
                        break;
                        case ITaglibIndexDelta.REMOVED:
                        {
                            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
                            {
                                JSFCoreTraceOptions
                                        .log(String
                                                .format(
                                                        "Processing remove change: project=%s, tagrecord=%s",
                                                        project.getName()));
                            }
                            registry.addLibraryOperation(registry
                                    .createRemoveOperation(delta
                                            .getTaglibRecord()));
                        }
                        break;
                        case ITaglibIndexDelta.CHANGED:
                        {
                            if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
                            {
                                JSFCoreTraceOptions
                                        .log(String
                                                .format(
                                                        "Processing change: project=%s, tagrecord=%s",
                                                        project.getName()));
                            }
                            registry.addLibraryOperation(registry
                                    .createChangeOperation(delta
                                            .getTaglibRecord()));
                        }
                        break;
                    }
                }
            }

            // visit children
            for (final ITaglibIndexDelta child : delta.getAffectedChildren())
            {
                visitDelta(child);
            }
        }
    }
}
