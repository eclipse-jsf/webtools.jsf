package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarFile;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.common.internal.resource.IJarProvider;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.JarBasedJSFResourceLocator;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.eclipse.jst.jsf.test.util.mock.MockJarProvider;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestJarBasedJSFResourceLocator
{
    private IJarProvider _jarProvider;
    private JarBasedJSFResourceLocator _locator;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp()
    {
        final JarFile jar = MockJarProvider.getJar("./testdata/jsfResources.jar");
        _jarProvider = new MockJarProvider(jar);
        _locator = new JarBasedJSFResourceLocator(Collections.EMPTY_LIST, new CopyOnWriteArrayList<ILocatorChangeListener>(), _jarProvider, new ContentTypeResolver(new MockContentTypeManager()));
    }

    @Test
    public void testLocate()
    {
        
        _locator.start(new MockProject(new Path("foo"), null));
        // we can pass null here since our jar provider doesn't care about projects.
        final List<JSFResource> foundResources = _locator.locate(null);
        assertEquals(2, foundResources.size());
        final Set<String> foundResourceIds = new HashSet<String>();
        for (final JSFResource res : foundResources)
        {
            foundResourceIds.add(res.getId().toString());
        }
        // contains a directory for mylib
        assertTrue(foundResourceIds.contains("mylib"));
        assertTrue(foundResourceIds.contains("mylib/tag1.xhtml"));
    }
}
