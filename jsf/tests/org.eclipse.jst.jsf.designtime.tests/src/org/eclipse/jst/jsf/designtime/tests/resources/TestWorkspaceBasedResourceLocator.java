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
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener.JSFResourceChangedEvent;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener.JSFResourceChangedEvent.CHANGE_TYPE;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceContainer;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifier;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory.InvalidIdentifierException;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResourceContainer;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResourceLocator;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContainer;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
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
            @Override
            protected void installListener()
            {
                _events.clear();
                _locator.addListener(new JSFResourceChangeListener()
                {
                    @Override
                    public void changed(final JSFResourceChangedEvent event)
                    {
                        _events.add(event);
                    }
                });
            }

            @Override
            protected boolean isChangeType(final JSFResourceChangedEvent event,
                    final CHANGE_TYPE type)
            {
                return event.getChangeType() == type;
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
        _changeTester.assertNumEvents(2);
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
            final JSFResourceChangedEvent event = _changeTester
                    .getSingleEvent(CHANGE_TYPE.REMOVED);
            assertEquals(CHANGE_TYPE.REMOVED, event.getChangeType());
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
        assertEquals(3, _locator.locate(_project).size());
    }

    @Test
    public void testMoveChange_File_Rename()
    {
        _locator.start(_project);
        assertEquals(3, _locator.locate(_project).size());
        _changeTester.fireResourceFileRename("mylib333/tag1.xhtml", "mylib333/tag2.xhtml");
        _changeTester.assertNumEvents(2);
        {
            final JSFResourceChangedEvent event = _changeTester
                    .getSingleEvent(CHANGE_TYPE.ADDED);
            assertEquals(CHANGE_TYPE.ADDED, event.getChangeType());
            assertNull(event.getOldValue());
            assertNotNull(event.getNewValue());
            assertEquals(Type.RESOURCE, event.getNewValue().getType());
            final JSFResource newValue = (JSFResource) event
                    .getNewValue();
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
            final JSFResource oldValue = (JSFResource) event
                    .getOldValue();
            assertTrue(oldValue instanceof WorkspaceJSFResource);
            final IResource resource = ((WorkspaceJSFResource) oldValue)
                    .getResource();
            assertEquals(IResource.FILE, resource.getType());
            assertEquals("tag1.xhtml", resource.getName());
            assertEquals("mylib333/tag1.xhtml", oldValue.getId().toString());
        }
        assertEquals(3, _locator.locate(_project).size());
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
