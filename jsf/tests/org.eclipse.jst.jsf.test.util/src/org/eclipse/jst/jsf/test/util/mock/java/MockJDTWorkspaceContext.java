package org.eclipse.jst.jsf.test.util.mock.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jst.jsf.test.util.mock.MockFile;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;

public class MockJDTWorkspaceContext
{
    private final MockWorkspaceContext _wsContext;
    private final Map<MockProject, List<IPackageFragmentRoot>> _cpEntriesByProject;
    private final CopyOnWriteArrayList<IElementChangedListener> _listeners = new CopyOnWriteArrayList<IElementChangedListener>();

    public MockJDTWorkspaceContext(final MockWorkspaceContext wsContext)
    {
        _wsContext = wsContext;
        _cpEntriesByProject = new HashMap<MockProject, List<IPackageFragmentRoot>>();
    }

    @SuppressWarnings("unchecked")
    public MockJavaProject createJavaProject(final IProject project)
    {
        List<IPackageFragmentRoot> cpEntries = _cpEntriesByProject.get(project);
        return new MockJavaProject(project,
                cpEntries != null ? Collections.unmodifiableList(cpEntries)
                        : Collections.EMPTY_LIST);
    }

    /**
     * Initializes this context with mock cp entry information for the provider
     * project based on the contents of the project (i.e. jar files).
     * 
     * @param project
     * @throws CoreException
     */
    public void loadCPEntriesFromProject(final IProject project)
            throws CoreException
    {
        project.accept(new IResourceVisitor()
        {
            public boolean visit(final IResource resource) throws CoreException
            {
                if (resource.getType() == IResource.FILE
                        && "jar".equals(resource.getFullPath()
                                .getFileExtension()))
                {
                    createAndAddPackageFragmentRoot((MockProject) project,
                            ((MockFile) resource).getLocation());
                }
                return true;
            }
        });
    }

    /**
     * @param project
     * @param projectRelativePath
     * @param file
     * @return a new mock classpath entry. This entry will automatically be
     *         added to project as a file and to any MockJavaProject created
     *         through this context for project.
     */
    public IPackageFragmentRoot createCPELibraryInProject(
            final MockProject project, final IPath projectRelativePath,
            final File file)
    {
        _wsContext.attachFile(project, projectRelativePath, file);
        return createAndAddPackageFragmentRoot(project,
                new Path(file.getAbsolutePath()));
    }

    private IPackageFragmentRoot createAndAddPackageFragmentRoot(
            final MockProject project, final IPath absPathToRoot)
    {
        final IJavaProject javaProject = createJavaProject(project);
        final IPackageFragmentRoot fragRoot = new MockPackageFragmentRoot(
                javaProject, absPathToRoot);
        addCPEntry(project, fragRoot);
        return fragRoot;
    }

    private void addCPEntry(final MockProject project,
            final IPackageFragmentRoot packageRoot)
    {
        List<IPackageFragmentRoot> entriesForProject = _cpEntriesByProject
                .get(project);
        if (entriesForProject == null)
        {
            entriesForProject = new ArrayList<IPackageFragmentRoot>();
            _cpEntriesByProject.put(project, entriesForProject);
        }
        entriesForProject.add(packageRoot);
    }

    public void addElementChangedListener(IElementChangedListener listener)
    {
        _listeners.addIfAbsent(listener);
    }

    public void removeElementChangedListener(IElementChangedListener listener)
    {
        _listeners.remove(listener);
    }

    public void fireElementChangedEvent(final ElementChangedEvent event)
    {
        for (final IElementChangedListener listener : _listeners)
        {
            listener.elementChanged(event);
        }
    }

    public Collection<IElementChangedListener> getListeners()
    {
        return Collections.unmodifiableList(_listeners);
    }
}
