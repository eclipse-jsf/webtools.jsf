package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.jst.jsf.test.util.mock.MockFile;
import org.eclipse.jst.jsf.test.util.mock.MockResourceChangeEventFactory;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;

public abstract class ChangeTester<EVENTTYPE, CHANGETYPE>
{
    protected final List<EVENTTYPE> _events = new ArrayList<EVENTTYPE>();
    private final MockWorkspaceContext _context;
    private final MockResourceChangeEventFactory _eventFactory;
    private final IFolder _resourceRoot;

    /**
     * @param context
     * @param factory
     * @param resourceRoot
     */
    public ChangeTester(final MockWorkspaceContext context,
            final MockResourceChangeEventFactory factory,
            final IFolder resourceRoot)
    {
        _context = context;
        _eventFactory = factory;
        _resourceRoot = resourceRoot;
    }

    public void fireResourceFileContentsChange(final String resourceId)
    {
        installListener();
        final IFile file = getFile(resourceId);
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFileChange((MockFile) file, true);
        _context.fireWorkspaceEvent(event);
    }

    public void fireResourceFileAdd(final String resourceId)
    {
        installListener();
        final IFile file = _resourceRoot.getFile(resourceId);
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFileAdded((MockFile) file);
        _context.fireWorkspaceEvent(event);
    }

    private IFile getFile(final String resourceId)
    {
        final IFile file = _resourceRoot.getFile(resourceId);
        assertNotNull(file);
        assertTrue(file.exists());
        return file;
    }

    private IFolder getFolder(final String folderName)
    {
        final IFolder folder = _resourceRoot.getFolder(folderName);
        assertNotNull(folder);
        assertTrue(folder.exists());
        return folder;
    }

    protected abstract void installListener();

    protected abstract boolean isChangeType(final EVENTTYPE event,
            final CHANGETYPE type);

    public void fireResourceFileDelete(final String resourceId)
    {
        installListener();
        final IFile file = getFile(resourceId);
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFileRemove((MockFile) file);
        _context.fireWorkspaceEvent(event);

    }

    public void fireResourceFolderAdd(final String folderName)
    {
        installListener();
        final IFolder folder = getFolder(folderName);
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFolderAdded(folder);
        _context.fireWorkspaceEvent(event);
    }

    public void fireResourceFolderDelete(final String folderName)
    {
        installListener();
        final IFolder folder = getFolder(folderName);
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFolderDeleted(folder);
        _context.fireWorkspaceEvent(event);
    }

    public void fireResourceFolderRename(final String folderName,
            final String newFolderName)
    {
        installListener();
        final IFolder folder = getFolder(folderName);
        final IFolder newFolder = getFolder(newFolderName);
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFolderRename(folder, newFolder);
        _context.fireWorkspaceEvent(event);
    }

    public void fireResourceFileRename(String fileName, String newFileName)
    {
        installListener();
        final IFile folder = getFile(fileName);
        final IFile newFolder = getFile(newFileName);
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFileRename(folder, newFolder);
        _context.fireWorkspaceEvent(event);
    }
    public EVENTTYPE getEvent(final int eventNum)
    {
        return _events.get(eventNum);
    }

    public EVENTTYPE getSingleEvent(final CHANGETYPE type)
    {
        List<EVENTTYPE> events = getEvent(type);
        if (events.isEmpty())
        {
            throw new AssertionError();
        } else if (events.size() > 1)
        {
            throw new AssertionError();
        }
        return events.get(0);
    }

    public List<EVENTTYPE> getEvent(final CHANGETYPE type)
    {
        List<EVENTTYPE> foundEvents = new ArrayList<EVENTTYPE>();
        for (final EVENTTYPE event : _events)
        {
            if (isChangeType(event, type))
            {
                foundEvents.add(event);
            }
        }
        return foundEvents;
    }

    public void assertNumEvents(final int numEvents)
    {
        assertEquals(numEvents, _events.size());
    }


}