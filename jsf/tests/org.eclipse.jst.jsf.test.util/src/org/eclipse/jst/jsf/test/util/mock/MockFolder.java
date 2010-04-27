package org.eclipse.jst.jsf.test.util.mock;

import java.net.URI;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class MockFolder extends MockContainer implements IFolder
{

    public MockFolder(IPath path, IMockResourceFactory resFactory)
    {
        super(IResource.FOLDER, path, resFactory);
    }

    public void create(boolean force, boolean local, IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void create(int updateFlags, boolean local, IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void createLink(IPath localLocation, int updateFlags,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void createLink(URI location, int updateFlags,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void delete(boolean force, boolean keepHistory,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IFolder getFolder(String name)
    {
        return super.getFolder(new Path(name));
    }

    public void move(IPath destination, boolean force, boolean keepHistory,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

}
