package org.eclipse.jst.jsf.test.util.mock;

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

public class MockVirtualFile implements IVirtualFile
{
    private IFile _realFile;

    public MockVirtualFile(IFile realFile)
    {
        _realFile = realFile;
    }

    public void createLink(IPath aProjectRelativeLocation, int updateFlags,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void removeLink(IPath aProjectRelativeLocation, int updateFlags,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void delete(int updateFlags, IProgressMonitor monitor)
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
        return getUnderlyingFile();
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

    public void setResourceType(String aResourceType)
    {
        throw new UnsupportedOperationException();

    }

    public boolean contains(ISchedulingRule rule)
    {
        throw new UnsupportedOperationException();

    }

    public boolean isConflicting(ISchedulingRule rule)
    {
        throw new UnsupportedOperationException();

    }

    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter)
    {
        throw new UnsupportedOperationException();

    }

    public IFile getUnderlyingFile()
    {
        return _realFile;
    }

    public IFile[] getUnderlyingFiles()
    {
        throw new UnsupportedOperationException();

    }

}
