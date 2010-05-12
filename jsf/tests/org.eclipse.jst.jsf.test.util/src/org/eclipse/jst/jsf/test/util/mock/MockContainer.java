package org.eclipse.jst.jsf.test.util.mock;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.FileInfoMatcherDescription;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class MockContainer extends MockResource implements IContainer
{
    private final IMockResourceFactory _resFactory;

    public MockContainer(int type, IPath path, IMockResourceFactory resFactory)
    {
        super(type, path);
        _resFactory = resFactory;
    }

    public void loadAllMembers() throws Exception 
    {
        getResFactory().forceLoad((MockProject) this.getProject());
    }
    
    @Override
    public void dispose() throws Exception
    {
        try
        {
            getResFactory().dispose();
        } finally
        {
            super.dispose();
        }
    }


    public boolean exists(IPath path)
    {
        throw new UnsupportedOperationException();
    }

    public IResource findMember(String name)
    {
        throw new UnsupportedOperationException();
    }

    public IResource findMember(String name, boolean includePhantoms)
    {
        throw new UnsupportedOperationException();
    }

    public IResource findMember(IPath path)
    {
        throw new UnsupportedOperationException();
    }

    public IResource findMember(IPath path, boolean includePhantoms)
    {
        throw new UnsupportedOperationException();
    }

    public String getDefaultCharset() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public String getDefaultCharset(boolean checkImplicit) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IFile getFile(IPath path)
    {
        try
        {
            return (IFile) getResFactory().createFile(this, path);
        } catch (Exception e)
        {
            throw new AssertionFailedError(e.getLocalizedMessage());
        }
    }

    public IFile getFile(String path)
    {
        return getFile(new Path(path));
    }

    public IFolder getFolder(IPath path)
    {
        try
        {
            return getResFactory().createFolder(this, path);
        } catch (Exception e)
        {
            throw new AssertionFailedError(e.getLocalizedMessage());
        }
    }

    
    @Override
    protected void visitMembers(IResourceVisitor visitor, int depth,
            int memberFlags) throws CoreException
    {
         for (final IResource res : members(memberFlags))
         {
             res.accept(visitor, depth, memberFlags);
         }
    }

    public IResource[] members() throws CoreException
    {
        return members(IResource.NONE);
    }

    public IResource[] members(boolean includePhantoms) throws CoreException
    {
        return members(includePhantoms ? INCLUDE_PHANTOMS : IResource.NONE);
    }

    public IResource[] members(int memberFlags) throws CoreException
    {
        // TODO: ignore member flags for now
        return getResFactory().getCurrentMembers(this).toArray(new IResource[0]);
    }

    public IFile[] findDeletedMembersWithHistory(int depth,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void setDefaultCharset(String charset) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void setDefaultCharset(String charset, IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IResourceFilterDescription createFilter(int type,
            FileInfoMatcherDescription matcherDescription, int updateFlags,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IResourceFilterDescription[] getFilters() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IMockResourceFactory getResFactory()
    {
        return _resFactory;
    }
}
