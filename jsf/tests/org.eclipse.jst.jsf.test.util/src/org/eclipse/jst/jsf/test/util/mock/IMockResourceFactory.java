package org.eclipse.jst.jsf.test.util.mock;

import java.util.List;

import org.eclipse.core.runtime.IPath;

public interface IMockResourceFactory
{
    /**
     * @param path the path, relative to container where the file should
     * be created.
     * @return the mock file for the path.  If the file already exists
     * for this path, then this will be returned. If a resource already exists
     * for this path but it is not a file then a ClassCastException is thrown.
     */
    MockResource createFile(final MockContainer container, final IPath path) throws Exception;
    
    /**
     * @param container
     * @return all of the resources this factory currently knows about for the 
     * container.  In other words, all of the paths within container for 
     * which create* methods have been successfully called.
     */
    List<MockResource>  getCurrentMembers(final MockContainer container);

    /**
     * Cause any "existent" resources that have not yet be created from test
     * source (i.e. from a project zip) to be loaded so that they will be
     * returned by getCurrentMembers().
     * 
     * @throws Exception
     */
    void forceLoad(final MockProject project) throws Exception;
    /**
     * Signal that the factory should release any resources it is holding.
     * @throws Exception 
     */
    void dispose() throws Exception;

    public abstract MockFolder createFolder(final MockContainer container, final IPath path);
}
