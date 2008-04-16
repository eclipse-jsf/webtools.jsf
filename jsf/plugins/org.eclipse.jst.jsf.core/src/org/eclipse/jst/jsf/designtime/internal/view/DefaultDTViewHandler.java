package org.eclipse.jst.jsf.designtime.internal.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.internal.JSPUtil;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ImmutableLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessAdvisor;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessEvent;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessListener;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.VersionStamp;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessEvent.ChangeType;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.w3c.dom.Node;

/**
 * A default implementation of the design time view handler meant to parallel
 * the default runtime ViewHandler required by the JSF spec.
 * 
 */
public class DefaultDTViewHandler extends AbstractDTViewHandler
{
    private final MyLifecycleManager _lifecycleManager = new MyLifecycleManager();

    @Override
    public String calculateLocale(final DTFacesContext context)
            throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IResource getActionDefinition(final DTFacesContext context,
            final String viewId) throws ViewHandlerException
    {
        // TODO: this seems like a bit of a cope out...
        return context.adaptContextObject();
    }

    @Override
    public IPath getActionURL(final DTFacesContext context,
            final IResource resource, final IPath requestPath)
            throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPath getRelativeActionPath(final DTFacesContext context,
            final String relativeToViewId, final String uri)
            throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IViewDefnAdapterFactory getViewMetadataAdapterFactory(
            final DTFacesContext context) throws ViewHandlerException
    {
        final IResource res = context.adaptContextObject();

        if (res instanceof IFile)
        {
            return new DefaultViewDefnAdapterFactory(this);
        }

        return null;
    }

    @Override
    protected VersionStamp createVersionStamp(
            final DTFacesContext facesContext, final String viewId)
    {
        return new TimeBasedVersionStamp();
    }

    static class DefaultViewDefnAdapterFactory extends
            AbstractViewDefnAdapterFactory
    {
        private final DefaultDTViewHandler _myViewHandler;

        DefaultViewDefnAdapterFactory(final DefaultDTViewHandler viewHandler)
        {
            _myViewHandler = viewHandler;
        }

        @Override
        public IViewDefnAdapter<Node, IDocument> createAdapter(
                final DTFacesContext context, final String viewId)
        {
            try
            {
                final IResource res = _myViewHandler.getActionDefinition(
                        context, viewId);

                if (res instanceof IFile)
                {
                    final IFile srcFile = (IFile) res;
                    final ITagRegistry registry = findTagRegistry(srcFile);
                    if (JSPUtil.isJSPContentType(srcFile) && registry != null)
                    {
                        // if we have a jsp file, then return the default
                        // adapter
                        return new JSPViewDefnAdapter(registry);
                    }
                }
            }
            catch (final ViewHandlerException vhe)
            {
                JSFCorePlugin.log(vhe, "While acquiring view adapter");
            }

            // not found or failed
            return null;
        }
    }

    @Override
    protected DTUIViewRoot internalCreateView(
            final DTFacesContext facesContext, final String viewId)
    {
        IViewDefnAdapterFactory factory;
        try
        {
            factory = getViewMetadataAdapterFactory(facesContext);
            if (factory != null)
            {
                final IViewDefnAdapter<?, ?> adapter = factory.createAdapter(
                        facesContext, viewId);
                if (adapter instanceof XMLViewDefnAdapter)
                {
                    final IResource res = facesContext.adaptContextObject();
                    final DTUIViewRoot root = newView(facesContext, viewId);
                    final XMLComponentTreeConstructionStrategy constructionStrategy = createTreeConstructionStrategy(
                            (XMLViewDefnAdapter) adapter, res.getProject());

                    constructionStrategy
                            .createComponentTree(facesContext, root);
                    return root;
                }
            }
        }
        catch (final ViewHandlerException e)
        {
            JSFCorePlugin.log(e, "While acquiring view defn adapter factory");
            // fall-through
        }

        return null;
    }

    /**
     * By default, returns DefaultDTUIViewRoot.
     * 
     * @param facesContext
     * @param viewId
     * @return a new instance of a view. Override to change the implementation
     *         or configuration of DTUIViewRoot that is used by
     *         internalCreateView.
     */
    protected DTUIViewRoot newView(final DTFacesContext facesContext,
            final String viewId)
    {
        return new DefaultDTUIViewRoot();
    }

    @Override
    protected void registerView(DTUIViewRoot viewRoot,
            DTFacesContext facesContext, String viewId)
    {
        final IResource res = facesContext.adaptContextObject();
        _lifecycleManager.addViewInfo(viewId, res);
    }

    /**
     * Sub-classes may override to provide a different strategy.
     * 
     * @param adapter
     * @param project
     * @return the construction strategy used to create this view's component
     *         tree
     */
    protected XMLComponentTreeConstructionStrategy createTreeConstructionStrategy(
            final XMLViewDefnAdapter adapter, final IProject project)
    {
        return new XMLComponentTreeConstructionStrategy(adapter, project);
    }

    @Override
    public boolean supportsViewDefinition(final IFile file)
    {
        // currently only JSP content type is supported
        return (JSPUtil.isJSPContentType(file));
    }

    @Override
    protected StalenessAdvisor createStalenessAdvisor(
            final DTUIViewRoot viewRoot, final DTFacesContext facesContext,
            final String viewId)
    {
        final IResource res = facesContext.adaptContextObject();
        if (res != null)
        {
            return new ResourceModStampStalenessAdvisor(viewRoot, res);
        }
        return new NullStalenessAdvisor();
    }

    /**
     * Measures the staleness of a view by comparing the modification stamp on
     * the resource at construction with the current value when isStale is
     * called.
     * 
     * @author cbateman
     * 
     */
    protected final class ResourceModStampStalenessAdvisor extends
            StalenessAdvisor implements Serializable
    {
        /**
         * version id
         */
        private static final long                                 serialVersionUID = -4982206388722638735L;
        private final long                                        _modificationStamp;

        private transient final IResource                         _res;

        /**
         * @param viewRoot
         * @param file
         */
        public ResourceModStampStalenessAdvisor(final DTUIViewRoot viewRoot,
                final IResource file)
        {
            _res = file;
            _modificationStamp = file.getModificationStamp();
        }

        @Override
        public boolean isStale()
        {
            final long curStamp = _res.getModificationStamp();
            return curStamp != _modificationStamp;
        }

        @Override
        public void addListener(final StalenessListener listener)
        {
            _lifecycleManager.addListener(_res, listener);
        }

        @Override
        public void removeListener(final StalenessListener listener)
        {
            _lifecycleManager.removeListener(_res, listener);
        }

        @Override
        public boolean isAccessible()
        {
            return _res.isAccessible();
        }
    }

    @Override
    public final void setLifecycleListener(ImmutableLifecycleListener listener)
    {
        _lifecycleManager.update(listener);
    }

    @Override
    protected final void doDispose()
    {
        _lifecycleManager.dispose();
    }

    private class MyLifecycleManager implements IResourceLifecycleListener
    {
        private ImmutableLifecycleListener     _listener;
        private final Map<IResource, ViewInfo> _stalenessListeners;

        public MyLifecycleManager()
        {
            _stalenessListeners = new HashMap<IResource, ViewInfo>();
        }

        public void addListener(IResource res, StalenessListener listener)
        {
            ViewInfo viewInfo = getViewInfo(res);
            viewInfo.getListeners().addIfAbsent(listener);
        }

        public void removeListener(IResource res, StalenessListener listener)
        {
            ViewInfo viewInfo = getViewInfo(res);
            viewInfo.getListeners().remove(listener);
        }

        public EventResult acceptEvent(final ResourceLifecycleEvent event)
        {
            switch (event.getEventType())
            {
                case RESOURCE_CHANGED:
                {
                    return handleContentChangeEvent(event);
                }

                case RESOURCE_INACCESSIBLE:
                {
                    return handleInaccessibleChanageEvent(event);
                }

                default:
                    // do nothing with other types
            }

            return EventResult.getDefaultEventResult();
        }

        private EventResult handleContentChangeEvent(
                final ResourceLifecycleEvent event)
        {
            if (event.getReasonType() != ReasonType.RESOURCE_CHANGED_CONTENTS)
            {
                return EventResult.getDefaultEventResult();
            }

            final IResource res = event.getAffectedResource();
            List<StalenessListener> stalenessListeners = getListeners(res);

            if (stalenessListeners != null)
            {
                for (final StalenessListener listener : stalenessListeners)
                {
                    listener.stalenessChanged(new StalenessEvent(
                            ChangeType.VIEW_DEFN_CHANGED));
                }
            }
            return EventResult.getDefaultEventResult();
        }

        private EventResult handleInaccessibleChanageEvent(
                final ResourceLifecycleEvent event)
        {
            final IResource res = event.getAffectedResource();
            final ReasonType reasonType = event.getReasonType();
            ChangeType changeType = null;
            if (reasonType == ReasonType.RESOURCE_PROJECT_CLOSED)
            {
                changeType = ChangeType.VIEW_DEFN_PROJECT_CLOSED;
            }
            else if (reasonType == ReasonType.RESOURCE_DELETED)
            {
                changeType = ChangeType.VIEW_DEFN_DELETED;
            }
            else
            {
                return EventResult.getDefaultEventResult();
            }

            final List<StalenessListener>  listeners = getListeners(res);
            
            if (listeners != null)
            {
                for (final StalenessListener listener : listeners)
                {
                    listener.stalenessChanged(new StalenessEvent(
                            changeType));
                }
            }
            return EventResult.getDefaultEventResult();
        }

        private List<StalenessListener> getListeners(final IResource res)
        {
            List<StalenessListener> stalenessListeners = null;

            synchronized (this)
            {
                final ViewInfo viewInfo = _stalenessListeners.get(res);

                if (viewInfo != null)
                {
                    stalenessListeners = viewInfo.getListeners();
                }
            }
            return stalenessListeners;
        }

        public synchronized void addViewInfo(final String viewId,
                final IResource res)
        {
            ViewInfo viewInfo = _stalenessListeners.get(viewId);

            if (viewInfo == null)
            {
                viewInfo = new ViewInfo(viewId);
                _stalenessListeners.put(res, viewInfo);
            }
        }

        public synchronized ViewInfo getViewInfo(final IResource res)
        {
            return _stalenessListeners.get(res);
        }

        public synchronized void dispose()
        {
            // updating with null effectively deregisters any existing listener
            // and doesn't register a new one.
            update(null);
        }

        public synchronized void update(
                final ImmutableLifecycleListener listener)
        {
            if (listener == _listener)
            {
                return;
            }

            final ImmutableLifecycleListener oldListener = _listener;

            if (oldListener != null)
            {
                oldListener.removeListener(this);
            }

            _listener = listener;

            if (_listener != null)
            {
                _listener.addListener(this);
            }
        }
    }

    private static class ViewInfo
    {
        private final CopyOnWriteArrayList<StalenessListener> _listeners;
        private final String                  _viewId;

        /**
         * @param listeners
         * @param res
         */
        private ViewInfo(final String viewId)
        {
            super();
            _listeners = new CopyOnWriteArrayList<StalenessListener>();
            _viewId = viewId;
        }

        protected final CopyOnWriteArrayList<StalenessListener> getListeners()
        {
            return _listeners;
        }

        protected final String getViewId()
        {
            return _viewId;
        }
    }
}
