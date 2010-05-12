package org.eclipse.jst.jsf.test.util.mock;

import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class MockWorkspaceRoot extends MockContainer implements IWorkspaceRoot
{
    private MockWorkspaceContext _wsContext;

    public MockWorkspaceRoot()
    {
        super(IResource.ROOT, new Path(""), null);
    }

    public void setContext(MockWorkspaceContext context)
    {
        _wsContext = context;
    }

    public void delete(final boolean deleteContent, final boolean force,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer[] findContainersForLocation(final IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer[] findContainersForLocationURI(final URI location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer[] findContainersForLocationURI(final URI location,
            final int memberFlags)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile[] findFilesForLocation(final IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile[] findFilesForLocationURI(final URI location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile[] findFilesForLocationURI(final URI location, final int memberFlags)
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer getContainerForLocation(final IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile getFileForLocation(final IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IProject getProject(final String name)
    {
        return _wsContext.getProject(new Path(name));
    }

    public IProject[] getProjects()
    {
        throw new UnsupportedOperationException();
        
    }

    public IProject[] getProjects(final int memberFlags)
    {
        throw new UnsupportedOperationException();
        
    }

    @Override
    public IProject getProject()
    {
        return null;
    }

    @Override
    public IContainer getParent()
    {
        return null;
    }

    @Override
    public IFile getFile(IPath path)
    {
        if (path == null || path.segmentCount() == 0)
        {
            throw new IllegalArgumentException();
        }
        IProject project = getProject(path.segment(0));
        if (project != null)
        {
            return project.getFile(path.removeFirstSegments(1));
        }
        return null;
    }
}
