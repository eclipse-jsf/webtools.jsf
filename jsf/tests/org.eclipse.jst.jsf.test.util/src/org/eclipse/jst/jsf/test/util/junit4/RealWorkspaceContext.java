package org.eclipse.jst.jsf.test.util.junit4;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.internal.events.BuildManager;
import org.eclipse.core.internal.events.ILifecycleListener;
import org.eclipse.core.internal.events.NotificationManager;
import org.eclipse.core.internal.localstore.FileSystemResourceManager;
import org.eclipse.core.internal.properties.IPropertyManager;
import org.eclipse.core.internal.refresh.RefreshManager;
import org.eclipse.core.internal.resources.AliasManager;
import org.eclipse.core.internal.resources.CharsetManager;
import org.eclipse.core.internal.resources.ContentDescriptionManager;
import org.eclipse.core.internal.resources.LocalMetaArea;
import org.eclipse.core.internal.resources.MarkerManager;
import org.eclipse.core.internal.resources.NatureManager;
import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.internal.resources.ResourceInfo;
import org.eclipse.core.internal.resources.SaveManager;
import org.eclipse.core.internal.resources.WorkManager;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.internal.resources.WorkspaceDescription;
import org.eclipse.core.internal.watson.ElementTree;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFilterMatcherDescriptor;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNatureDescriptor;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ISavedState;
import org.eclipse.core.resources.ISynchronizer;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceLock;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jst.jsf.test.util.Activator;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestUtil;
import org.eclipse.jst.jsf.test.util.mock.AbstractWorkspaceContextWithEvents;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

@SuppressWarnings("deprecation")
public class RealWorkspaceContext extends AbstractWorkspaceContextWithEvents
{
    private DecoratedWorkspace _workspace;
    private final CopyOnWriteArrayList<IResourceChangeListener> _listeners;
    private final Workspace _actualWorkspace;

    public RealWorkspaceContext()
    {
        _listeners = new CopyOnWriteArrayList<IResourceChangeListener>();
        _actualWorkspace = (Workspace) ResourcesPlugin.getWorkspace();
        _workspace = new DecoratedWorkspace(this, _actualWorkspace, _listeners);
    }

    @Override
    protected void doInit() throws Exception
    {
        setWorkspace(_workspace);
        setWorkspace(_actualWorkspace.getRoot(), _workspace);
    }

    private static void setWorkspace(final IResource root,
            final IWorkspace workspace) throws Exception
    {
        final Class<Resource> rootClass = Resource.class;
        final Field declaredField = rootClass.getDeclaredField("workspace");
        declaredField.setAccessible(true);
        declaredField.set(root, workspace);
    }

    private void setWorkspace(final IWorkspace workspace) throws Exception
    {
        final Class<ResourcesPlugin> resPlugin = ResourcesPlugin.class;
        final Field declaredField = resPlugin.getDeclaredField("workspace");
        declaredField.setAccessible(true);
        declaredField.set(null, workspace);
    }

    @Override
    public void doDispose() throws Exception
    {
        setWorkspace(_actualWorkspace.getRoot(), _actualWorkspace);
        setWorkspace(_workspace._ws);
        _workspace = null;
    }

    public IWorkspace getWorkspace()
    {
        assertInitialized();
        return _workspace;
    }

    public IResource getResource(final IPath path)
    {
        assertInitialized();
        final IWorkspaceRoot root = _workspace.getRoot();
        final IResource res = root.findMember(path, false);
        if (res != null && res.exists())
        {
            return res;
        }
        return null;
    }

    public IFile getFile(final IPath path)
    {
        return (IFile) getResource(path);
    }

    public IProject getProject(final IPath path)
    {
        return (IProject) getResource(path);
    }

    public IProject createProject(final String baseId)
    {
        assertInitialized();
        int suffix = 0;
        String projectName = null;
        do
        {
            projectName = baseId + "_" + suffix++;
        } while ((_workspace.getRoot().getProject(projectName).exists()));
        assertNotNull(projectName);
        return createProject2(projectName);
    }

    private IProject createProject2(final String projectName)
    {
        assertInitialized();
        final ProjectTestEnvironment testEnv = new ProjectTestEnvironment(
                projectName);
        assertTrue(testEnv.createProject(false));
        return testEnv.getTestProject();
    }

    public IProject createProject(final IPath path)
    {
        return createProject2(path.toString());
    }

    public IProject loadProject(final IPath path,
            final ZipFileLoader zipFileLoader) throws Exception
    {
        assertInitialized();
        assertFalse(_workspace.getRoot().getProject(path.toString()).exists());
        return TestUtil.createProjectFromZip(zipFileLoader.getFile(),
                path.toString());
    }

    public IProject loadProject(final IPath path,
            final Bundle bundle, String pathToZip) throws Exception {
        assertInitialized();
        assertFalse(_workspace.getRoot().getProject(path.toString()).exists());
        return TestUtil.createProjectFromZip(bundle, path.toString(),
                pathToZip);
    }

    public IFile attachFile(final IProject project,
            final IPath projectRelativePath, final File file) throws Exception
    {
        assertInitialized();
        final ByteArrayOutputStream stream = JSFTestUtil.loadFromFile(file);
        return TestUtil.createFile(project, projectRelativePath.toString(),
                stream.toString());
    }

    public void fireWorkspaceEvent(final IResourceChangeEvent event)
    {
        assertInitialized();
        for (final IResourceChangeListener listener : _listeners)
        {
            SafeRunner.run(new ISafeRunnable()
            {
                public void run() throws Exception
                {
                    listener.resourceChanged(event);
                }

                public void handleException(final Throwable exception)
                {
                    Activator.log("While processing mock resource event",
                            exception);
                }
            });
        }
    }

    public List<IResourceChangeListener> getListeners()
    {
        assertInitialized();
        return Collections.unmodifiableList(_listeners);
    }
    
    public List<IResourceChangeListener> getListeners(final List<Class<? extends IResourceChangeListener>>  includeListeners)
    {
        List<IResourceChangeListener> listeners = new ArrayList<IResourceChangeListener>();
        for (final IResourceChangeListener listener : getListeners())
        {
            SEARCH_CLASSES: for (final Class<? extends IResourceChangeListener> clazz : includeListeners)
            {
                if (clazz.isAssignableFrom(listener.getClass()))
                {
                    listeners.add(listener);
                    break SEARCH_CLASSES;
                }
            }
        }
        return listeners;
    }

    @Override
    protected void assertInitialized()
    {
        super.assertInitialized();
    }

    public void ensureAllMembers(final IProject project) throws Exception
    {
        // do nothing; the real workspace always is "loaded".
    }

    private static class DecoratedWorkspace extends Workspace
    {
        private final Workspace _ws;
        private final List<IResourceChangeListener> _listeners;
        private final RealWorkspaceContext _wsContext;

        /**
         * @param ws
         * @param listeners
         */
        public DecoratedWorkspace(final RealWorkspaceContext wsContext,
                final Workspace ws,
                final List<IResourceChangeListener> listeners)
        {
            _wsContext = wsContext;
            _ws = ws;
            _listeners = listeners;
        }

        @Override
        protected void flushBuildOrder()
        {
            invokeOnWorkspace("flushBuildOrder", new Class<?>[0], new Object[0]);
        }

        private void invokeOnWorkspace(final String name,
                final Class<?>[] classes, final Object[] values)
        {
            try
            {
                final Class<Workspace> workspaceClass = Workspace.class;
                final Method declaredMethod = workspaceClass.getDeclaredMethod(
                        name, classes);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(_ws, values);
            } catch (final SecurityException e)
            {
                throw new RuntimeException(e);
            } catch (final NoSuchMethodException e)
            {
                throw new RuntimeException(e);
            } catch (final IllegalArgumentException e)
            {
                throw new RuntimeException(e);
            } catch (final IllegalAccessException e)
            {
                throw new RuntimeException(e);
            } catch (final InvocationTargetException e)
            {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void addLifecycleListener(final ILifecycleListener listener)
        {
            _ws.addLifecycleListener(listener);
        }

        @Override
        public void addResourceChangeListener(
                final IResourceChangeListener listener)
        {
            addResourceChangeListener(listener, IResourceChangeEvent.PRE_CLOSE
                    | IResourceChangeEvent.PRE_DELETE
                    | IResourceChangeEvent.POST_CHANGE);
        }

        @Override
        public void addResourceChangeListener(
                final IResourceChangeListener listener, final int eventMask)
        {
            _wsContext.assertInitialized();
            if (Thread.currentThread() == Display.getDefault().getThread())
            {
                _listeners.add(listener);
            }
            _ws.addResourceChangeListener(listener, eventMask);
        }

        @Override
        public ISavedState addSaveParticipant(final Plugin plugin,
                final ISaveParticipant participant) throws CoreException
        {
            return _ws.addSaveParticipant(plugin, participant);
        }

        @Override
        public ISavedState addSaveParticipant(final String pluginId,
                final ISaveParticipant participant) throws CoreException
        {
            return _ws.addSaveParticipant(pluginId, participant);
        }

        @Override
        public void beginOperation(final boolean createNewTree)
                throws CoreException
        {
            _ws.beginOperation(createNewTree);
        }

        @Override
        public void broadcastBuildEvent(final Object source, final int type,
                final int buildTrigger)
        {
            _ws.broadcastBuildEvent(source, type, buildTrigger);
        }

        @Override
        public void broadcastPostChange()
        {
            _ws.broadcastPostChange();
        }

        @Override
        public void build(final int trigger, final IProgressMonitor monitor)
                throws CoreException
        {
            _ws.build(trigger, monitor);
        }

        @Override
        public void checkpoint(final boolean build)
        {
            _ws.checkpoint(build);
        }

        @Override
        public void close(final IProgressMonitor monitor) throws CoreException
        {
            _ws.close(monitor);
        }

        @Override
        public IProject[][] computePrerequisiteOrder(final IProject[] targets)
        {
            return _ws.computePrerequisiteOrder(targets);
        }

        @Override
        public ProjectOrder computeProjectOrder(final IProject[] projects)
        {
            return _ws.computeProjectOrder(projects);
        }

        @Override
        public IStatus copy(final IResource[] resources,
                final IPath destination, final boolean force,
                final IProgressMonitor monitor) throws CoreException
        {
            return _ws.copy(resources, destination, force, monitor);
        }

        @Override
        public IStatus copy(final IResource[] resources,
                final IPath destination, final int updateFlags,
                final IProgressMonitor monitor) throws CoreException
        {
            return _ws.copy(resources, destination, updateFlags, monitor);
        }

        @Override
        public int countResources(final IPath root, final int depth,
                final boolean phantom)
        {
            return _ws.countResources(root, depth, phantom);
        }

        @Override
        public ResourceInfo createResource(final IResource resource,
                final boolean phantom) throws CoreException
        {
            return _ws.createResource(resource, phantom);
        }

        @Override
        public ResourceInfo createResource(final IResource resource,
                final int updateFlags) throws CoreException
        {
            return _ws.createResource(resource, updateFlags);
        }

        @Override
        public ResourceInfo createResource(final IResource resource,
                final ResourceInfo info, final boolean phantom,
                final boolean overwrite, final boolean keepSyncInfo)
                throws CoreException
        {
            return _ws.createResource(resource, info, phantom, overwrite,
                    keepSyncInfo);
        }

        @Override
        public IStatus delete(final IResource[] resources, final boolean force,
                final IProgressMonitor monitor) throws CoreException
        {
            return _ws.delete(resources, force, monitor);
        }

        @Override
        public IStatus delete(final IResource[] resources,
                final int updateFlags, final IProgressMonitor monitor)
                throws CoreException
        {
            return _ws.delete(resources, updateFlags, monitor);
        }

        @Override
        public void deleteMarkers(final IMarker[] markers) throws CoreException
        {
            _ws.deleteMarkers(markers);
        }

        @Override
        public void endOperation(final ISchedulingRule rule,
                final boolean build, final IProgressMonitor monitor)
                throws CoreException
        {
            _ws.endOperation(rule, build, monitor);
        }

//        @Override
//        public boolean equals(final Object obj)
//        {
//            return _ws.equals(obj);
//        }

        @Override
        public Object getAdapter(
                @SuppressWarnings("rawtypes") final Class adapter)
        {
            return _ws.getAdapter(adapter);
        }

        @Override
        public void forgetSavedTree(final String pluginId)
        {
            _ws.forgetSavedTree(pluginId);
        }

        @Override
        public AliasManager getAliasManager()
        {
            return _ws.getAliasManager();
        }

        @Override
        public BuildManager getBuildManager()
        {
            return _ws.getBuildManager();
        }

        @Override
        public IBuildConfiguration[] getBuildOrder()
        {
            return _ws.getBuildOrder();
        }

        @Override
        public CharsetManager getCharsetManager()
        {
            return _ws.getCharsetManager();
        }

        @Override
        public ContentDescriptionManager getContentDescriptionManager()
        {
            return _ws.getContentDescriptionManager();
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Map getDanglingReferences()
        {
            return _ws.getDanglingReferences();
        }

        @Override
        public IWorkspaceDescription getDescription()
        {
            return _ws.getDescription();
        }

        @Override
        public ElementTree getElementTree()
        {
            return _ws.getElementTree();
        }

        @Override
        public FileSystemResourceManager getFileSystemManager()
        {
            return _ws.getFileSystemManager();
        }

        @Override
        public MarkerManager getMarkerManager()
        {
            return _ws.getMarkerManager();
        }

        @Override
        public LocalMetaArea getMetaArea()
        {
            return _ws.getMetaArea();
        }

        @Override
        public IFilterMatcherDescriptor getFilterMatcherDescriptor(
                final String filterMAtcherId)
        {
            return _ws.getFilterMatcherDescriptor(filterMAtcherId);
        }

        @Override
        public IFilterMatcherDescriptor[] getFilterMatcherDescriptors()
        {
            return _ws.getFilterMatcherDescriptors();
        }

        @Override
        public IProjectNatureDescriptor getNatureDescriptor(
                final String natureId)
        {
            return _ws.getNatureDescriptor(natureId);
        }

        @Override
        public IProjectNatureDescriptor[] getNatureDescriptors()
        {
            return _ws.getNatureDescriptors();
        }

        @Override
        public NatureManager getNatureManager()
        {
            return _ws.getNatureManager();
        }

        @Override
        public NotificationManager getNotificationManager()
        {
            return _ws.getNotificationManager();
        }

        @Override
        public IPathVariableManager getPathVariableManager()
        {
            return _ws.getPathVariableManager();
        }

        @Override
        public IPropertyManager getPropertyManager()
        {
            return _ws.getPropertyManager();
        }

        @Override
        public RefreshManager getRefreshManager()
        {
            return _ws.getRefreshManager();
        }

        @Override
        public ResourceInfo getResourceInfo(final IPath path,
                final boolean phantom, final boolean mutable)
        {
            return _ws.getResourceInfo(path, phantom, mutable);
        }

        @Override
        public IWorkspaceRoot getRoot()
        {
            return _ws.getRoot();
        }

        @Override
        public IResourceRuleFactory getRuleFactory()
        {
            return _ws.getRuleFactory();
        }

        @Override
        public SaveManager getSaveManager()
        {
            return _ws.getSaveManager();
        }

        @Override
        public ISynchronizer getSynchronizer()
        {
            return _ws.getSynchronizer();
        }

        @Override
        public WorkManager getWorkManager() throws CoreException
        {
            return _ws.getWorkManager();
        }

        @Override
        public int hashCode()
        {
            return _ws.hashCode();
        }

        @Override
        public WorkspaceDescription internalGetDescription()
        {
            return _ws.internalGetDescription();
        }

        @Override
        public boolean isAutoBuilding()
        {
            return _ws.isAutoBuilding();
        }

        @Override
        public boolean isOpen()
        {
            return _ws.isOpen();
        }

        @Override
        public boolean isTreeLocked()
        {
            return _ws.isTreeLocked();
        }

        @Override
        public IProjectDescription loadProjectDescription(
                final InputStream stream) throws CoreException
        {
            return _ws.loadProjectDescription(stream);
        }

        @Override
        public IProjectDescription loadProjectDescription(final IPath path)
                throws CoreException
        {
            return _ws.loadProjectDescription(path);
        }

        @Override
        public IStatus move(final IResource[] resources,
                final IPath destination, final boolean force,
                final IProgressMonitor monitor) throws CoreException
        {
            return _ws.move(resources, destination, force, monitor);
        }

        @Override
        public IStatus move(final IResource[] resources,
                final IPath destination, final int updateFlags,
                final IProgressMonitor monitor) throws CoreException
        {
            return _ws.move(resources, destination, updateFlags, monitor);
        }

        @Override
        public IProjectDescription newProjectDescription(
                final String projectName)
        {
            return _ws.newProjectDescription(projectName);
        }

        @Override
        public Resource newResource(final IPath path, final int type)
        {
            Resource res = _ws.newResource(path, type);
            try
            {
                setWorkspace(res, this);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
            return res;
        }

        @Override
        public ElementTree newWorkingTree()
        {
            return _ws.newWorkingTree();
        }

        @Override
        public IStatus open(final IProgressMonitor monitor)
                throws CoreException
        {
            return _ws.open(monitor);
        }

        @Override
        public void prepareOperation(final ISchedulingRule rule,
                final IProgressMonitor monitor) throws CoreException
        {
            _ws.prepareOperation(rule, monitor);
        }

        @Override
        public void removeResourceChangeListener(
                final IResourceChangeListener listener)
        {
            _wsContext.assertInitialized();
            if (Thread.currentThread() == Display.getDefault().getThread())
            {
                _listeners.remove(listener);
            }
            _ws.removeResourceChangeListener(listener);
        }

        @Override
        public void removeSaveParticipant(final Plugin plugin)
        {
            _ws.removeSaveParticipant(plugin);
        }

        @Override
        public void removeSaveParticipant(final String pluginId)
        {
            _ws.removeSaveParticipant(pluginId);
        }

        @Override
        public void run(final IWorkspaceRunnable action,
                final IProgressMonitor monitor) throws CoreException
        {
            _ws.run(action, monitor);
        }

        @Override
        public void run(final IWorkspaceRunnable action,
                final ISchedulingRule rule, final int options,
                final IProgressMonitor monitor) throws CoreException
        {
            _ws.run(action, rule, options, monitor);
        }

        @Override
        public IStatus save(final boolean full, final IProgressMonitor monitor)
                throws CoreException
        {
            return _ws.save(full, monitor);
        }

        @Override
        public IStatus save(final boolean full,
                final boolean keepConsistencyWhenCanceled,
                final IProgressMonitor monitor) throws CoreException
        {
            return _ws.save(full, keepConsistencyWhenCanceled, monitor);
        }

        @Override
        public void setCrashed(final boolean value)
        {
            _ws.setCrashed(value);
        }

        @Override
        public void setDescription(final IWorkspaceDescription value)
        {
            _ws.setDescription(value);
        }

        @Override
        public void setTreeLocked(final boolean locked)
        {
            _ws.setTreeLocked(locked);
        }

        @Override
        public void setWorkspaceLock(final WorkspaceLock lock)
        {
            _ws.setWorkspaceLock(lock);
        }

        @Override
        public String[] sortNatureSet(final String[] natureIds)
        {
            return _ws.sortNatureSet(natureIds);
        }

        @Override
        public String toDebugString()
        {
            return _ws.toDebugString();
        }

        @Override
        public String toString()
        {
            return _ws.toString();
        }

        @Override
        public URI transferVariableDefinition(final IResource source,
                final IResource dest, final URI sourceURI) throws CoreException
        {
            return _ws.transferVariableDefinition(source, dest, sourceURI);
        }

        @Override
        public void updateModificationStamp(final ResourceInfo info)
        {
            info.incrementModificationStamp();
        }

        @Override
        public IStatus validateEdit(final IFile[] files, final Object context)
        {
            return _ws.validateEdit(files, context);
        }

        @Override
        public IStatus validateLinkLocation(final IResource resource,
                final IPath unresolvedLocation)
        {
            return _ws.validateLinkLocation(resource, unresolvedLocation);
        }

        @Override
        public IStatus validateLinkLocationURI(final IResource resource,
                final URI unresolvedLocation)
        {
            return _ws.validateLinkLocationURI(resource, unresolvedLocation);
        }

        @Override
        public IStatus validateName(final String segment, final int type)
        {
            return _ws.validateName(segment, type);
        }

        @Override
        public IStatus validateNatureSet(final String[] natureIds)
        {
            return _ws.validateNatureSet(natureIds);
        }

        @Override
        public IStatus validatePath(final String path, final int type)
        {
            return _ws.validatePath(path, type);
        }

        @Override
        public IStatus validateProjectLocation(final IProject context,
                final IPath location)
        {
            return _ws.validateProjectLocation(context, location);
        }

        @Override
        public IStatus validateProjectLocationURI(final IProject project,
                final URI location)
        {
            return _ws.validateProjectLocationURI(project, location);
        }

        @Override
        public IStatus validateFiltered(final IResource resource)
        {
            return _ws.validateFiltered(resource);
        }
    }
}
