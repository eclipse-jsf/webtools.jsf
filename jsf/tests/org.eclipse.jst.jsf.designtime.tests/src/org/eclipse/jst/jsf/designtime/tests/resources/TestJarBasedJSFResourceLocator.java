package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.common.internal.resource.DefaultJarLocator;
import org.eclipse.jst.jsf.common.internal.resource.IJarLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceFragment;
import org.eclipse.jst.jsf.designtime.internal.resources.JarBasedJSFResourceLocator;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.eclipse.jst.jsf.test.util.mock.java.MockJDTWorkspaceContext;
import org.eclipse.jst.jsf.test.util.mock.java.MockJavaCoreMediator;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestJarBasedJSFResourceLocator
{
    private IJarLocator _jarProvider;
    private JarBasedJSFResourceLocator _locator;
    private MockJDTWorkspaceContext _jdtContext;
    private MockWorkspaceContext _wsContext;
    private MockProject _project;
//    private MockFile _jarIFile;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp()
    {
        _wsContext = new MockWorkspaceContext();
        _project = _wsContext.createProject("TestProject");
        File jarFile = new File("./testdata/jsfResources.jar");
        assertTrue(jarFile.exists());
        _jdtContext = new MockJDTWorkspaceContext(_wsContext);
        _jdtContext.createCPELibraryInProject(_project, new Path(
                "/WebContent/WEB-INF/lib/jsfResources.jar"), jarFile);
        _jarProvider = new DefaultJarLocator(new MockJavaCoreMediator(
                _jdtContext));
        _locator = new JarBasedJSFResourceLocator(Collections.EMPTY_LIST,
                new CopyOnWriteArrayList<ILocatorChangeListener>(),
                _jarProvider, new ContentTypeResolver(
                        new MockContentTypeManager()));
    }

    @Test
    public void testLocate()
    {
        
        _locator.start(_project);
        // we can pass null here since our jar provider doesn't care about projects.
        final List<IJSFResourceFragment> foundResources = _locator.locate(_project);
        assertEquals(3, foundResources.size());
        final Set<String> foundResourceIds = new HashSet<String>();
        for (final IJSFResourceFragment res : foundResources)
        {
            foundResourceIds.add(res.getId().toString());
        }
        // contains a directory for mylib
        assertTrue(foundResourceIds.contains("mylib"));
        assertTrue(foundResourceIds.contains("mylib/tag1.xhtml"));
        // @BugRegressionTest(bugNumber = 318478)
        assertTrue(foundResourceIds.contains("mylib/invalidDir"));
    }
    
//    @Test
//    @BugRegressionTest(bugNumber = 318478)
//    public void testAddInvalidResource()
//    {
//        _locator.start(_project);
//        assertEquals(1, _context.getWorkspace().getListeners().size());
//        _changeTester.fireResourceFolderAdd("mylib");
//        // the mylib should register a library add
//        _changeTester.assertNumEvents(1);
//        _changeTester.fireResourceFolderAdd("mylib/invalidDir");
//        // the dir is potentially valid as a fragment
//        _changeTester.assertNumEvents(1);
//        // the workspace resource locator shouldn't care if something with
//        // an invalid id is added
//        _changeTester.fireResourceFileAdd("mylib/invalidDir/file.css");
//        _changeTester.assertNumEvents(0);
//    }

}
