package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IJSFTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.TagRegistryChangeEvent;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.TagRegistryChangeEvent.EventType;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDTagRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Structured content provider for tag libraries.
 * 
 * @author cbateman
 * 
 */
public class TaglibContentProvider implements IStructuredContentProvider,
        ITreeContentProvider, ITagRegistry.ITagRegistryListener
{
    private final static Object[] NO_CHILDREN  = new Object[0];
    private IProject              _curInput;
    private ITagRegistry          _curTagRegistry;
    private Viewer                _curViewer;
    private final AtomicLong      _changeStamp = new AtomicLong(0);
    private Map<String, Object>          _rootNamespaces;

    public Object[] getElements(final Object inputElement)
    {
        if (inputElement instanceof IProject)
        {
            return _rootNamespaces.values().toArray();
        }

        return NO_CHILDREN;
    }

    public void dispose()
    {
        // nothing to do
    }

    public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
    {
        // update our change stamp to invalid outstanding update tasks
        _changeStamp.incrementAndGet();
        _curViewer = viewer;

        if (oldInput instanceof IProject)
        {
            if (_curTagRegistry != null)
            {
                _curTagRegistry.removeListener(this);
            }
        }

        if (newInput instanceof IProject)
        {
            _curTagRegistry = TLDTagRegistry.getRegistry((IProject) newInput);
            _curTagRegistry.addListener(this);
            _curInput = (IProject) newInput;

            _rootNamespaces = Collections
                    .synchronizedMap(new HashMap<String, Object>());
            _rootNamespaces.put("not a uri", new TreePlaceholder("Calculating...", null));

            new UpdateNamespacesListJob(_curInput, _changeStamp.get())
                    .schedule();
        }
        else
        {
            _curInput = null;
            _rootNamespaces = Collections.EMPTY_MAP;
        }
    }

    public Object[] getChildren(final Object parentElement)
    {
        if (parentElement instanceof IProject)
        {
            return _rootNamespaces.values().toArray();
        }
        else if (parentElement instanceof Namespace)
        {
            final Namespace ns = (Namespace) parentElement;

            // this could be very expensive if not initialized
            if (ns.isInitialized())
            {
                return ((Namespace) parentElement).getViewElements().toArray();
            }

            // fire up a job that ensures the namespace is initialized
            // and then fires refresh again on this element
            final Job updateNamespaceJob = new Job("Updating namespace")
            {
                @Override
                protected IStatus run(final IProgressMonitor monitor)
                {
                    ns.getViewElements();
                    PlatformUI.getWorkbench().getDisplay().asyncExec(
                            new Runnable()
                            {
                                public void run()
                                {
                                    // avoid infinite recursion
                                    if (ns.isInitialized())
                                    {
                                        TaglibContentProvider.this
                                                .viewerRefresh(ns);
                                    }
                                    else
                                    {
                                        MessageDialog
                                                .openError(
                                                        Display
                                                                .getCurrent()
                                                                .getActiveShell(),
                                                        "Error updating namespace",
                                                        "There was a problem initializing the namespace");
                                    }
                                }
                            });
                    return Status.OK_STATUS;
                }
            };

            updateNamespaceJob.schedule();

            return new Object[]
            { new TreePlaceholder("Calculating tags, please wait...", null) };
        }
        else if (parentElement instanceof IJSFTagElement)
        {
            return new Object[]
            { ((IJSFTagElement) parentElement).toString() };
        }

        return NO_CHILDREN;
    }

    public Object getParent(final Object element)
    {
        // no support for parent traversal right now
        return null;
    }

    public boolean hasChildren(final Object element)
    {
        // finding all children of a namespace can be expensive
        if (element instanceof Namespace)
        {
            return ((Namespace) element).hasViewElements();
        }
        return getChildren(element).length > 0;
    }

    public void registryChanged(final TagRegistryChangeEvent changeEvent)
    {
        if (_curViewer != null)
        {
            _curViewer.getControl().getDisplay().asyncExec(
                    new RegistryChangeTask(changeEvent.getType(), changeEvent
                            .getAffectedObjects(), _changeStamp.get()));
        }
    }

    private final class RegistryChangeTask implements Runnable
    {
        private final EventType                 _eventType;
        private final long                      _timestamp;
        private final List<? extends Namespace> _affectedObjects;

        RegistryChangeTask(final TagRegistryChangeEvent.EventType eventType,
                final List<? extends Namespace> affectedObjects,
                final long timestamp)
        {
            _eventType = eventType;
            _timestamp = timestamp;
            _affectedObjects = affectedObjects;
        }

        public void run()
        {
            // if changes have been made since this task was queued, then abort
            // since we don't know if our data is still valid
            if (_timestamp != TaglibContentProvider.this._changeStamp.get())
            {
                return;
            }

            switch (_eventType)
            {
                case ADDED_NAMESPACE:
                case CHANGED_NAMESPACE:
                {
                    for (final Namespace ns : _affectedObjects)
                    {
                        _rootNamespaces.put(ns.getNSUri(), ns);
                    }

                    viewerRefresh(_curInput);
                }
                break;

                case REMOVED_NAMESPACE:
                {
                    for (final Namespace ns : _affectedObjects)
                    {
                        _rootNamespaces.remove(ns.getNSUri());
                    }
                    viewerRefresh(_curInput);
                }
                break;

                case REGISTRY_DISPOSED:
                {
                    _curTagRegistry.removeListener(TaglibContentProvider.this);
                    // nullify the input since the current project is no longer
                    // valid
                    _curViewer.setInput(null);
                }
            }
        }
    }

    private void viewerRefresh(final Object parentElement)
    {
        if (_curViewer instanceof StructuredViewer)
        {
            final StructuredViewer viewer = (StructuredViewer) _curViewer;
            viewer.refresh(parentElement);
        }
        else
        {
            _curViewer.refresh();
        }
    }

    private class UpdateNamespacesListJob extends Job
    {

        private final long     _timestamp;
        private final IProject _project;

        public UpdateNamespacesListJob(final IProject project,
                final long timestamp)
        {
            super("Updating available namespaces for project "
                    + project.getName());
            _project = project;
            _timestamp = timestamp;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor)
        {
            if (!_project.isAccessible())
            {
                return new Status(IStatus.CANCEL, "", "");
            }

            final Collection<? extends Namespace> libs = TLDTagRegistry
                    .getRegistry(_project).getAllTagLibraries();
            _rootNamespaces.clear();
            
            for (Namespace ns : libs)
            {
                _rootNamespaces.put(ns.getNSUri(), ns);
            }
            
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    // only bother if the provider hasn't changed asynchronously
                    if (_timestamp == TaglibContentProvider.this._changeStamp
                            .get())
                    {
                        viewerRefresh(_curInput);
                    }
                }
            });

            return Status.OK_STATUS;
        }
    }

    /**
     * Takes the place of a real tree model object while the real object is
     * being retrieved.
     * 
     */
    public static class TreePlaceholder
    {
        private final String _text;
        private final Image  _image;

        TreePlaceholder(final String text, final Image image)
        {
            _text = text;
            _image = image;
        }

        /**
         * @return the placeholder text or null if none
         */
        public String getText()
        {
            return _text;
        }

        /**
         * @return the image or null if none
         */
        public Image getImage()
        {
            return _image;
        }

    }
}
