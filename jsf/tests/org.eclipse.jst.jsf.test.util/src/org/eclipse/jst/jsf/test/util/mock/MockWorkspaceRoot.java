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

    public MockWorkspaceRoot()
    {
        super(IResource.ROOT, new Path(""), null);
    }

    public void delete(boolean deleteContent, boolean force,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer[] findContainersForLocation(IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer[] findContainersForLocationURI(URI location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer[] findContainersForLocationURI(URI location,
            int memberFlags)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile[] findFilesForLocation(IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile[] findFilesForLocationURI(URI location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile[] findFilesForLocationURI(URI location, int memberFlags)
    {
        throw new UnsupportedOperationException();
        
    }

    public IContainer getContainerForLocation(IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IFile getFileForLocation(IPath location)
    {
        throw new UnsupportedOperationException();
        
    }

    public IProject getProject(String name)
    {
        throw new UnsupportedOperationException();
        
    }

    public IProject[] getProjects()
    {
        throw new UnsupportedOperationException();
        
    }

    public IProject[] getProjects(int memberFlags)
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
}
