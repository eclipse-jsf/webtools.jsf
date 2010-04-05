package org.eclipse.jst.jsf.test.util.mock;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * A test context that can be used to construct a number of MockResources that
 * are associated with a mocked workspace/project hierarchy
 * 
 * @author cbateman
 * 
 */
public class MockWorkspaceContext
{
    private final MockWorkspace _ws;

    private final Map<IPath, MockResource> _ownedResources;

    public MockWorkspaceContext(final MockWorkspace ws)
    {
        _ws = ws;
        _ownedResources = new HashMap<IPath, MockResource>();
    }

    public MockWorkspaceContext()
    {
        this(new MockWorkspace(new MockWorkspaceRoot()));
    }

    public void fireWorkspaceEvent(final IResourceChangeEvent event)
    {
        _ws.fireResourceChangeEvent(event);
    }

    public void dispose() throws Exception
    {
        for (final Map.Entry<IPath, MockResource> entry : _ownedResources
                .entrySet())
        {
            entry.getValue().dispose();
        }
        _ownedResources.clear();
        _ws.dispose();
    }

    public MockWorkspace getWorkspace()
    {
        return _ws;
    }

    public MockResource getResource(final IPath path)
    {
        return _ownedResources.get(path);
    }

    public MockProject getProject(final IPath path)
    {
        return (MockProject) _ownedResources.get(path);
    }

    public MockProject createProject(final IPath path, final boolean replace)
    {
        final MockProject project = new MockProject(path,
                new MyMockResourceFactory());
        attachProject(project, replace);
        return project;
    }

    /**
     * @return a mock project with a generated name that is guaranteed not to
     *         conflict with any that already exist in this context.
     */
    public MockProject createProject(final String baseId)
    {
        int i = 0;

        while (_ownedResources.get(generateName(baseId, i)) != null)
        {
            // keep looping until we get TestProject_i that doesn't exist
            i++;
        }

        return createProject(generateName(baseId, i), false);
    }

    private Path generateName(final String baseId, final int i)
    {
        return new Path(baseId + "_TestProject_" + i);
    }

    public MockProject createProject(final IPath path)
    {
        return createProject(path, false);
    }

    public void attachProject(final MockProject project, final boolean replace)
    {
        checkExists(project.getFullPath(), replace);
        project.setWorkspace(_ws);
        _ownedResources.put(project.getFullPath(), project);
    }

    public MockProject loadProject(final IPath path, final ZipFile zip)
            throws Exception
    {
        return loadProject(path, zip, "");
    }

    public MockProject loadProject(final IPath path, final ZipFile zip,
            final String pathIntoZip) throws Exception
    {
        checkExists(path, false);
        final MockProject project = new MockProject(path,
                new MyMockResourceFactory(zip, pathIntoZip));
        attachProject(project, false);
        return project;
    }

    private MockResource checkExists(final IPath path, final boolean replace)
    {
        final MockResource resource = _ownedResources.get(path);

        if (resource != null && !replace)
        {
            throw new IllegalArgumentException(path.toString()
                    + " already exists");
        }
        return resource;
    }

    private class MyMockResourceFactory implements IMockResourceFactory
    {
        private ZipFile _zip;
        private final String _pathIntoZip;

        public MyMockResourceFactory()
        {
            // do nothing.
            _pathIntoZip = "";
        }

        public MyMockResourceFactory(final ZipFile zip, final String pathIntoZip)
                throws Exception
        {
            _zip = zip;
            _pathIntoZip = pathIntoZip;
        }

        public MockFile createFile(final MockContainer container,
                final IPath path) throws CoreException, IOException
        {
            final IPath newFileFullPath = container.getFullPath().append(path);
            MockResource resource = checkExists(newFileFullPath, true);
            if (resource == null)
            {

                resource = new MockFile(newFileFullPath);
                ((MockResource) resource).setWorkspace(_ws);
                ((MockResource) resource).setProject(container.getProject());
                if (_zip != null)
                {
                    final ZipEntry entry = _zip.getEntry(_pathIntoZip
                            + path.toString());

                    if (entry != null)
                    {
                        final InputStream inputStream = _zip
                                .getInputStream(entry);
                        if (inputStream != null)
                        {
                            ((MockFile) resource).setContents(inputStream,
                                    false, true, new NullProgressMonitor());
                        }
                    }

                    ensurePathToNewResource(container, path);
                }
                _ownedResources.put(newFileFullPath, resource);
            }
            return (MockFile) resource;
        }

        public MockFolder createFolder(final MockContainer container,
                final IPath path)
        {
            final IPath newFileFullPath = container.getFullPath().append(path);
            MockResource resource = checkExists(newFileFullPath, true);
            if (resource == null)
            {

                resource = new MockFolder(newFileFullPath, this);
                ((MockResource) resource).setWorkspace(_ws);
                ((MockResource) resource).setProject(container.getProject());
                ensurePathToNewResource(container, path);
                _ownedResources.put(newFileFullPath, resource);
            }
            return (MockFolder) resource;
        }

        protected void ensurePathToNewResource(final MockContainer container,
                final IPath path)
        {
            // add any intervening MockContainers for the folders
            // under container where the file lives
            IPath leadingPath = path.removeLastSegments(1);
            IPath curPath = container.getFullPath();
            while (leadingPath.segmentCount() > 0)
            {
                final String nextSegment = leadingPath.segments()[0];
                curPath = curPath.append(nextSegment);
                leadingPath = leadingPath.removeFirstSegments(1);
                MockResource newContainer = checkExists(curPath, true);
                if (newContainer == null)
                {
                    newFolder(container, curPath);
                }
            }
        }

        protected void newFolder(MockContainer container, IPath curPath)
        {
            MockResource newContainer;
            newContainer = new MockFolder(curPath, this);
            newContainer.setWorkspace(_ws);
            newContainer.setProject(container.getProject());
            _ownedResources.put(curPath, newContainer);
        }

        public void dispose() throws Exception
        {
            if (_zip != null)
            {
                _zip.close();
            }
        }

        public List<MockResource> getCurrentMembers(
                final MockContainer container)
        {
            final List<MockResource> members = new ArrayList<MockResource>();
            final IPath containerPath = container.getFullPath();
            for (final IPath path : _ownedResources.keySet())
            {
                // path is a member of container if container's path is
                // a prefix of path and path has only one extra segment
                if (containerPath.isPrefixOf(path)
                        && path.segmentCount() == containerPath.segmentCount() + 1)
                {
                    members.add(_ownedResources.get(path));
                }
            }
            return members;
        }

        public void forceLoad(final MockProject project) throws Exception
        {
            final Enumeration<? extends ZipEntry> entries = _zip.entries();
            while (entries.hasMoreElements())
            {
                final ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                final int removeIdx = name.indexOf(_pathIntoZip);
                if (removeIdx > -1)
                {
                    name = name.substring(_pathIntoZip.length());
                    if (entry.isDirectory())
                    {
                        newFolder(project, new Path(name));
                    } else
                    {
                        createFile(project, new Path(name));
                    }
                }
            }
        }
    }
}
