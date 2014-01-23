package org.eclipse.jst.jsf.test.util.mock;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class MockWorkspace implements IWorkspace
{
    private final CopyOnWriteArrayList<IResourceChangeListener> _changeListeners = new CopyOnWriteArrayList<IResourceChangeListener>();
    private final MockWorkspaceRoot _root;

    public MockWorkspace(final MockWorkspaceRoot root)
    {
        _root = root;
    }
    public void dispose()
    {
        _changeListeners.clear();
    }

    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class adapter)
    {
        throw new UnsupportedOperationException();
    }

    public void addResourceChangeListener(final IResourceChangeListener listener)
    {
        _changeListeners.addIfAbsent(listener);
    }

    public void addResourceChangeListener(final IResourceChangeListener listener,
            final int eventMask)
    {
        // TODO: ignore masks for now
        _changeListeners.addIfAbsent(listener);
    }

    protected void fireResourceChangeEvent(final IResourceChangeEvent event)
    {
        for (final IResourceChangeListener listener : _changeListeners)
        {
            listener.resourceChanged(event);
        }
    }

    public List<IResourceChangeListener> getListeners()
    {
        return Collections.unmodifiableList(_changeListeners);
    }
    
    public ISavedState addSaveParticipant(final Plugin plugin,
            final ISaveParticipant participant) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public ISavedState addSaveParticipant(final String pluginId,
            final ISaveParticipant participant) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void build(final int kind, final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

	public void build(IBuildConfiguration[] buildConfigs, int kind,
			boolean buildReferences, IProgressMonitor monitor)
			throws CoreException
	{
        throw new UnsupportedOperationException();
	}

	public IBuildConfiguration newBuildConfig(String projectName,
			String configName)
	{
		return null;
	}

    public void checkpoint(final boolean build)
    {
        throw new UnsupportedOperationException();

    }

    public IProject[][] computePrerequisiteOrder(final IProject[] projects)
    {
        throw new UnsupportedOperationException();

    }

    public ProjectOrder computeProjectOrder(final IProject[] projects)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus copy(final IResource[] resources, final IPath destination,
            final boolean force, final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IStatus copy(final IResource[] resources, final IPath destination,
            final int updateFlags, final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IStatus delete(final IResource[] resources, final boolean force,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IStatus delete(final IResource[] resources, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void deleteMarkers(final IMarker[] markers) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void forgetSavedTree(final String pluginId)
    {
        throw new UnsupportedOperationException();

    }

    public IFilterMatcherDescriptor[] getFilterMatcherDescriptors()
    {
        throw new UnsupportedOperationException();

    }

    public IFilterMatcherDescriptor getFilterMatcherDescriptor(
            final String filterMatcherId)
    {
        throw new UnsupportedOperationException();

    }

    public IProjectNatureDescriptor[] getNatureDescriptors()
    {
        throw new UnsupportedOperationException();

    }

    public IProjectNatureDescriptor getNatureDescriptor(final String natureId)
    {
        throw new UnsupportedOperationException();

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getDanglingReferences()
    {
        throw new UnsupportedOperationException();

    }

    public IWorkspaceDescription getDescription()
    {
        throw new UnsupportedOperationException();

    }

    public IWorkspaceRoot getRoot()
    {
        return _root;
    }

    public IResourceRuleFactory getRuleFactory()
    {
        throw new UnsupportedOperationException();

    }

    public ISynchronizer getSynchronizer()
    {
        throw new UnsupportedOperationException();

    }

    public boolean isAutoBuilding()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isTreeLocked()
    {
        throw new UnsupportedOperationException();
    }

    public IProjectDescription loadProjectDescription(
            final IPath projectDescriptionFile) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IProjectDescription loadProjectDescription(
            final InputStream projectDescriptionFile) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IStatus move(final IResource[] resources, final IPath destination,
            final boolean force, final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IStatus move(final IResource[] resources, final IPath destination,
            final int updateFlags, final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IProjectDescription newProjectDescription(final String projectName)
    {
        throw new UnsupportedOperationException();

    }

    public void removeResourceChangeListener(final IResourceChangeListener listener)
    {
        _changeListeners.remove(listener);
    }

    public void removeSaveParticipant(final Plugin plugin)
    {
        throw new UnsupportedOperationException();

    }

    public void removeSaveParticipant(final String pluginId)
    {
        throw new UnsupportedOperationException();

    }

    public void run(final IWorkspaceRunnable action, final ISchedulingRule rule, final int flags,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void run(final IWorkspaceRunnable action, final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IStatus save(final boolean full, final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setDescription(final IWorkspaceDescription description)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public String[] sortNatureSet(final String[] natureIds)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateEdit(final IFile[] files, final Object context)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateFiltered(final IResource resource)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateLinkLocation(final IResource resource, final IPath location)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateLinkLocationURI(final IResource resource, final URI location)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateName(final String segment, final int typeMask)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateNatureSet(final String[] natureIds)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validatePath(final String path, final int typeMask)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateProjectLocation(final IProject project, final IPath location)
    {
        throw new UnsupportedOperationException();

    }

    public IStatus validateProjectLocationURI(final IProject project, final URI location)
    {
        throw new UnsupportedOperationException();

    }

    public IPathVariableManager getPathVariableManager()
    {
        throw new UnsupportedOperationException();

    }

}
