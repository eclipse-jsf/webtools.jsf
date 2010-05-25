package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceFragment;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceFragment.Type;
import org.eclipse.jst.jsf.designtime.internal.resources.IWorkspaceJSFResourceFragment;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener.JSFResourceChangedEvent;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener.JSFResourceChangedEvent.CHANGE_TYPE;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceContainer;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceFragmentIdentifier;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifier;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory.InvalidIdentifierException;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResourceContainer;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResourceLocator;
import org.eclipse.jst.jsf.test.util.junit4.BugRegressionTest;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContainer;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
import org.eclipse.jst.jsf.test.util.mock.MockResource;
import org.eclipse.jst.jsf.test.util.mock.MockResourceChangeEventFactory;
import org.eclipse.jst.jsf.test.util.mock.MockVirtualComponentQuery;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestWorkspaceBasedResourceLocator
{
    private WorkspaceJSFResourceLocator _locator;
    private MockWorkspaceContext _context;
    private MockProject _project;
    private MockContainer _webContentFolder;
    private IFolder _resourceRoot;
    private MockResourceChangeEventFactory _eventFactory;
    private ResourceIdentifierFactory _resourceIdFactory;
    private ChangeTester<JSFResourceChangedEvent, CHANGE_TYPE> _changeTester;

    @SuppressWarnings(
    { "unchecked" })
    @Before
    public void setUp() throws Exception
    {
        _context = new MockWorkspaceContext();
        final File file = new File("./testdata/TestProject.zip");
        assertTrue(file.exists());
        final ZipFile zipFile = new ZipFile(file);
        _project = _context.loadProject(
                new Path("TestProjectTaglibDescriptor"), zipFile, "Test2/");
        _project.loadAllMembers();
        _webContentFolder = (MockContainer) _context.getResource(_project
                .getFullPath().append("/WebContent"));
        assertNotNull(_webContentFolder);
        _resourceRoot = _webContentFolder.getFolder(new Path("resources"));
        _locator = new WorkspaceJSFResourceLocator(
                Collections.EMPTY_LIST,
                new CopyOnWriteArrayList<ILocatorChangeListener>(),
                new MockVirtualComponentQuery(_project.getFolder("/WebContent")),
                new ContentTypeResolver(new MockContentTypeManager()), _context
                        .getWorkspace());
        _eventFactory = new MockResourceChangeEventFactory(_context);
        _resourceIdFactory = new ResourceIdentifierFactory();
        _changeTester = new ChangeTester<JSFResourceChangedEvent, CHANGE_TYPE>(
                _context, _eventFactory, _resourceRoot)
        {
            private JSFResourceChangeListener _listener;

            @Override
            protected void installListener()
            {
                _events.clear();
                _listener = new JSFResourceChangeListener()
                {
                    @Override
                    public void changed(final JSFResourceChangedEvent event)
                    {
                        _events.add(event);
                    }
                };
                _locator.addListener(_listener);
            }

            @Override
            protected boolean isChangeType(final JSFResourceChangedEvent event,
                    final CHANGE_TYPE type)
            {
                return event.getChangeType() == type;
            }

            @Override
            protected void removeListener()
            {
                _locator.removeListener(_listener);
            }
        };
    }

    @Test
    public void testLocate()
    {
        _locator.start(_project);
        // we can pass null here since our jar provider doesn't care about
        // projects.
        final List<IJSFResourceFragment> foundResources = _locator
                .locate(_project);
        assertEquals(3, foundResources.size());
        final Set<String> foundResourceIds = new HashSet<String>();
        for (final IJSFResourceFragment res : foundResources)
        {
            foundResourceIds.add(res.getId().toString());
        }
        // contains a directory for mylib
        assertTrue(foundResourceIds.contains("mylib333"));
        assertTrue(foundResourceIds.contains("mylib333/tag1.xhtml"));
    }

    @Test
    public void testResourceChangeContents_File()
            throws InvalidIdentifierException
    {
        _locator.start(_project);
        final IJSFResourceFragment changeThis = findById(_locator,
                "mylib333/tag1.xhtml");
        _changeTester.fireResourceFileContentsChange("mylib333/tag1.xhtml");
        _changeTester.assertNumEvents(1);
        final JSFResourceChangedEvent event = _changeTester.getEvent(0);
        assertEquals(CHANGE_TYPE.CHANGED, event.getChangeType());
        assertEquals(changeThis, event.getOldValue());
        assertEquals(changeThis, event.getNewValue());
    }

    @Test
    public void testRemoveChange_File() throws InvalidIdentifierException
    {
        _locator.start(_project);
        final IJSFResourceFragment changeThis = findById(_locator,
                "mylib333/tag1.xhtml");
        assertEquals(3, _locator.locate(_project).size());
        _changeTester.fireResourceFileDelete("mylib333/tag1.xhtml");
        _changeTester.assertNumEvents(1);
        final JSFResourceChangedEvent event = _changeTester.getEvent(0);
        assertEquals(CHANGE_TYPE.REMOVED, event.getChangeType());
        assertEquals(changeThis, event.getOldValue());
        assertEquals(null, event.getNewValue());
        assertEquals(2, _locator.locate(_project).size());
    }

    @Test
    public void testAddChange_File_ExistingLib()
    {
        _locator.start(_project);
        assertEquals(3, _locator.locate(_project).size());
        _changeTester.fireResourceFileAdd("mylib333/tag2.xhtml");
        _changeTester.assertNumEvents(1);
        final JSFResourceChangedEvent event = _changeTester.getEvent(0);
        assertEquals(CHANGE_TYPE.ADDED, event.getChangeType());
        assertNull(event.getOldValue());
        assertNotNull(event.getNewValue());
        assertEquals(4, _locator.locate(_project).size());
    }

    @Test
    public void testAddChange_Folder_NewLib()
    {
        _locator.start(_project);
        assertEquals(3, _locator.locate(_project).size());
        _changeTester.fireResourceFolderAdd("mylib999");
        _changeTester.assertNumEvents(1);
        final JSFResourceChangedEvent event = _changeTester.getEvent(0);
        assertEquals(CHANGE_TYPE.ADDED, event.getChangeType());
        assertNull(event.getOldValue());
        assertNotNull(event.getNewValue());
        assertEquals(Type.CONTAINER, event.getNewValue().getType());
        final JSFResourceContainer newValue = (JSFResourceContainer) event
                .getNewValue();
        assertTrue(newValue instanceof WorkspaceJSFResourceContainer);
        final IResource resource = ((WorkspaceJSFResourceContainer) newValue)
                .getResource();
        assertEquals(IResource.FOLDER, resource.getType());
        assertEquals("mylib999", resource.getName());
        assertEquals(Type.CONTAINER, newValue.getType());
        assertEquals(4, _locator.locate(_project).size());
    }

    @Test
    public void testMoveChange_Folder_Rename()
    {
        _locator.start(_project);
        assertEquals(3, _locator.locate(_project).size());
        _changeTester.fireResourceFolderRename("mylib333", "mylib1111");
        _changeTester.assertNumEvents(3);
        {
            final JSFResourceChangedEvent event = _changeTester
                    .getSingleEvent(CHANGE_TYPE.ADDED);
            assertEquals(CHANGE_TYPE.ADDED, event.getChangeType());
            assertNull(event.getOldValue());
            assertNotNull(event.getNewValue());
            assertEquals(Type.CONTAINER, event.getNewValue().getType());
            final JSFResourceContainer newValue = (JSFResourceContainer) event
                    .getNewValue();
            assertTrue(newValue instanceof WorkspaceJSFResourceContainer);
            final IResource resource = ((WorkspaceJSFResourceContainer) newValue)
                    .getResource();
            assertEquals(IResource.FOLDER, resource.getType());
            assertEquals("mylib1111", resource.getName());
            assertEquals(Type.CONTAINER, newValue.getType());
        }
        {
            final List<JSFResourceChangedEvent> events = _changeTester
                    .getEvent(CHANGE_TYPE.REMOVED);
            assertEquals(2, events.size());
            JSFResourceChangedEvent event = events.get(0);
            {
                assertNotNull(event.getOldValue());
                assertNull(event.getNewValue());
                assertEquals(Type.CONTAINER, event.getOldValue().getType());
                final JSFResourceContainer oldValue = (JSFResourceContainer) event
                        .getOldValue();
                assertTrue(oldValue instanceof WorkspaceJSFResourceContainer);
                final IResource resource = ((WorkspaceJSFResourceContainer) oldValue)
                        .getResource();
                assertEquals(IResource.FOLDER, resource.getType());
                assertEquals("mylib333", resource.getName());
                assertEquals(Type.CONTAINER, oldValue.getType());
            }
            event = events.get(1);
            {
                assertNotNull(event.getOldValue());
                assertNull(event.getNewValue());
                assertEquals(Type.RESOURCE, event.getOldValue().getType());
                final IWorkspaceJSFResourceFragment oldValue = (IWorkspaceJSFResourceFragment) event
                        .getOldValue();
                final IResource resource = (oldValue)
                        .getResource();
                assertEquals(IResource.FILE, resource.getType());
                assertEquals("tag1.xhtml", resource.getName());
            }
        }
        assertEquals(3, _locator.locate(_project).size());
    }

    @Test
    public void testMoveChange_File_Rename()
    {
        _locator.start(_project);
        assertEquals(3, _locator.locate(_project).size());
        _changeTester.fireResourceFileRename("mylib333/tag1.xhtml",
                "mylib333/tag2.xhtml");
        _changeTester.assertNumEvents(2);
        {
            final JSFResourceChangedEvent event = _changeTester
                    .getSingleEvent(CHANGE_TYPE.ADDED);
            assertEquals(CHANGE_TYPE.ADDED, event.getChangeType());
            assertNull(event.getOldValue());
            assertNotNull(event.getNewValue());
            assertEquals(Type.RESOURCE, event.getNewValue().getType());
            final JSFResource newValue = (JSFResource) event.getNewValue();
            assertTrue(newValue instanceof WorkspaceJSFResource);
            final IResource resource = ((WorkspaceJSFResource) newValue)
                    .getResource();
            assertEquals(IResource.FILE, resource.getType());
            assertEquals("tag2.xhtml", resource.getName());
            assertEquals("mylib333/tag2.xhtml", newValue.getId().toString());
            assertEquals(Type.RESOURCE, newValue.getType());
        }
        {
            final JSFResourceChangedEvent event = _changeTester
                    .getSingleEvent(CHANGE_TYPE.REMOVED);
            assertEquals(CHANGE_TYPE.REMOVED, event.getChangeType());
            assertNotNull(event.getOldValue());
            assertNull(event.getNewValue());
            assertEquals(Type.RESOURCE, event.getOldValue().getType());
            final JSFResource oldValue = (JSFResource) event.getOldValue();
            assertTrue(oldValue instanceof WorkspaceJSFResource);
            final IResource resource = ((WorkspaceJSFResource) oldValue)
                    .getResource();
            assertEquals(IResource.FILE, resource.getType());
            assertEquals("tag1.xhtml", resource.getName());
            assertEquals("mylib333/tag1.xhtml", oldValue.getId().toString());
        }
        assertEquals(3, _locator.locate(_project).size());
    }

    @Test
    @BugRegressionTest(bugNumber = 312358)
    public void testResourceRootDoesntExist()
    {
        ((MockResource) _resourceRoot).setExists(false);
        _locator.start(_project);
        // the workspace listener should get added event though the resource
        // root doesn't yet exist.
        assertEquals(1, _context.getWorkspace().getListeners().size());
        // simulate adding the folder
        ((MockResource) _resourceRoot).setExists(true);
        _changeTester.fireResourceFolderAdd("");
        _changeTester.assertNumEvents(1);
        // simulate adding a sub-folder
        _changeTester.fireResourceFolderAdd("ezcomp");
        _changeTester.assertNumEvents(1);
        final List<JSFResourceChangedEvent> events = _changeTester
                .getEvent(CHANGE_TYPE.ADDED);
        assertEquals(1, events.size());
        final JSFResourceChangedEvent event = events.get(0);
        final ResourceFragmentIdentifier id = event.getNewValue().getId();
        assertTrue(event.getNewValue().getType() == Type.CONTAINER);
        assertEquals("ezcomp", id.getLibraryName());
    }

    @Test
    @BugRegressionTest(bugNumber = 312358)
    public void testRemoveResourceRootAndAdd()
    {
        _locator.start(_project);
        assertEquals(1, _context.getWorkspace().getListeners().size());
        // delete the folder
        _changeTester.fireResourceFolderDelete("");
        // should get an event for root, lib333 and lib333/tag1.xhtml
        _changeTester.assertNumEvents(3);
        // should still be listening event after root is removed in case
        // it gets added back
        assertEquals(1, _context.getWorkspace().getListeners().size());
        // now add back the folder
        _changeTester.fireResourceFolderAdd("");
        _changeTester.assertNumEvents(1);
        {
            final JSFResourceChangedEvent event = _changeTester
                    .getSingleEvent(CHANGE_TYPE.ADDED);
            assertEquals("", event.getNewValue().getId().toString());
        }
        // now add a child to the resource root and assert we discover it.
        _changeTester.fireResourceFolderAdd("ezcomp");
        _changeTester.assertNumEvents(1);
        {
            final JSFResourceChangedEvent event = _changeTester
                    .getSingleEvent(CHANGE_TYPE.ADDED);
            assertEquals("ezcomp", event.getNewValue().getId().toString());
        }
    }

    @Test
    @BugRegressionTest(bugNumber = 312358)
    public void testAddNoneResourceFolderToWebContent()
    {
        _locator.start(_project);
        assertEquals(1, _context.getWorkspace().getListeners().size());
        // delete the folder
        _changeTester.fireResourceFolderAdd("../foobar");
        _changeTester.assertNumEvents(0);
    }

    @Test
    @BugRegressionTest(bugNumber = 312358)
    public void testRemoveRecursiveFolder()
    {
        _locator.start(_project);
        assertEquals(1, _context.getWorkspace().getListeners().size());
        // delete the folder
        _changeTester.fireResourceFileDeleteRecusive("");
        final int expectedEvents = 3;
        _changeTester.assertNumEvents(expectedEvents);
        final List<JSFResourceChangedEvent> events = _changeTester
                .getEvent(CHANGE_TYPE.REMOVED);
        assertEquals(expectedEvents, events.size());
        final Set<String> ids = new HashSet<String>();
        for (final JSFResourceChangedEvent event : events)
        {
            ids.add(event.getOldValue().getId().toString());
        }
        assertTrue(ids.contains(""));
        assertTrue(ids.contains("mylib333"));
        assertTrue(ids.contains("mylib333/tag1.xhtml"));
        assertEquals(expectedEvents, ids.size());
    }

    @Test
    @BugRegressionTest(bugNumber = 314145)
    public void testRenameFileInRootFolder()
    {
        _locator.start(_project);
        assertEquals(1, _context.getWorkspace().getListeners().size());
        _changeTester.fireResourceFileRename("../t11.jsp", "../t11.jspx");
        // the workspace resource locator shouldn't care if something outside
        // the resources sub-dir changes.
        _changeTester.assertNumEvents(0);
    }

    private IJSFResourceFragment findById(
            final WorkspaceJSFResourceLocator locator, final String id)
            throws InvalidIdentifierException
    {
        final ResourceIdentifier resId = _resourceIdFactory
                .createLibraryResource(id);
        assertNotNull(resId);
        final List<IJSFResourceFragment> located = _locator.locate(_project);
        for (final IJSFResourceFragment res : located)
        {
            if (res.getId().equals(resId))
            {
                return res;
            }
        }
        return null;
    }
}
