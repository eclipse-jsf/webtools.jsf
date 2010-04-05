package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public class MockVirtualFolder implements IVirtualFolder
{
    private final IContainer _realFolder;

    public MockVirtualFolder(final IContainer realFolder)
    {
        _realFolder = realFolder;
    }

    public void create(final int updateFlags, final IProgressMonitor aMonitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public boolean exists(final IPath path)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualResource findMember(final String name)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualResource findMember(final String name, final int searchFlags)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualResource findMember(final IPath path)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualResource findMember(final IPath path, final int searchFlags)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualFile getFile(final IPath path)
    {
        IFile file2 = _realFolder.getFile(path);
        if (file2 != null)
        {
            return new MockVirtualFile(file2);
        }
        return null;
    }

    public IVirtualFolder getFolder(final IPath path)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualFile getFile(final String name)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualFolder getFolder(final String name)
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualResource[] members() throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualResource[] members(final int memberFlags)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualResource[] getResources(final String aResourceType)
    {
        throw new UnsupportedOperationException();

    }

    public void createLink(final IPath aProjectRelativeLocation,
            final int updateFlags, final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void removeLink(final IPath aProjectRelativeLocation,
            final int updateFlags, final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void delete(final int updateFlags, final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public boolean exists()
    {
        throw new UnsupportedOperationException();

    }

    public String getFileExtension()
    {
        throw new UnsupportedOperationException();

    }

    public IPath getWorkspaceRelativePath()
    {
        throw new UnsupportedOperationException();

    }

    public IPath getProjectRelativePath()
    {
        throw new UnsupportedOperationException();

    }

    public IPath getRuntimePath()
    {
        throw new UnsupportedOperationException();

    }

    public String getName()
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualComponent getComponent()
    {
        throw new UnsupportedOperationException();

    }

    public IVirtualContainer getParent()
    {
        throw new UnsupportedOperationException();

    }

    public IProject getProject()
    {
        throw new UnsupportedOperationException();

    }

    public int getType()
    {
        throw new UnsupportedOperationException();

    }

    public IResource getUnderlyingResource()
    {
        throw new UnsupportedOperationException();

    }

    public IResource[] getUnderlyingResources()
    {
        throw new UnsupportedOperationException();

    }

    public boolean isAccessible()
    {
        throw new UnsupportedOperationException();

    }

    public String getResourceType()
    {
        throw new UnsupportedOperationException();

    }

    public void setResourceType(final String aResourceType)
    {
        throw new UnsupportedOperationException();

    }

    public boolean contains(final ISchedulingRule rule)
    {
        throw new UnsupportedOperationException();

    }

    public boolean isConflicting(final ISchedulingRule rule)
    {
        throw new UnsupportedOperationException();

    }

    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class adapter)
    {
        throw new UnsupportedOperationException();

    }

    public IContainer getUnderlyingFolder()
    {
        return _realFolder;
    }

    public IContainer[] getUnderlyingFolders()
    {
        throw new UnsupportedOperationException();

    }

}
