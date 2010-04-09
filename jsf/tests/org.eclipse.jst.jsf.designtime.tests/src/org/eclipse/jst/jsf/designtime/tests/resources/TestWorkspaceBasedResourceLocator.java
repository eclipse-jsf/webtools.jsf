package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResourceLocator;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
import org.eclipse.jst.jsf.test.util.mock.MockResource;
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
    private MockResource _webContentFolder;
//    private MockResourceChangeEventFactory _eventFactory;

    @SuppressWarnings("unchecked")
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
        _webContentFolder = _context.getResource(_project.getFullPath().append(
                "/WebContent"));
        assertNotNull(_webContentFolder);
//        _eventFactory = new MockResourceChangeEventFactory();
        _locator = new WorkspaceJSFResourceLocator(Collections.EMPTY_LIST, 
                new CopyOnWriteArrayList<ILocatorChangeListener>(), 
                new MockVirtualComponentQuery(_project.getFolder("/WebContent")),
                new ContentTypeResolver(new MockContentTypeManager()));
    }

    @Test
    public void testLocate()
    {
        _locator.start(_project);
        // we can pass null here since our jar provider doesn't care about
        // projects.
        final List<JSFResource> foundResources = _locator.locate(_project);
        assertEquals(2, foundResources.size());
        final Set<String> foundResourceIds = new HashSet<String>();
        for (final JSFResource res : foundResources)
        {
            foundResourceIds.add(res.getId().toString());
        }
        // contains a directory for mylib
        assertTrue(foundResourceIds.contains("mylib333"));
        assertTrue(foundResourceIds.contains("mylib333/tag1.xhtml"));
    }
}
