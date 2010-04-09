package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.common.internal.util.JarUtilities;
import org.eclipse.jst.jsf.designtime.internal.resources.JarBasedJSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifier;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory.InvalidIdentifierException;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestJarBasedJSFResource
{
    private JarFile _jarFile;
    private ZipEntry _entry;
    private ContentTypeResolver _contentTypeResolver;

    @Before
    public void setUp() throws IOException
    {
        final File file = new File("./testdata/jsfResources.jar");
        assertTrue(file.exists());
        _jarFile = new JarFile(file.getAbsoluteFile());
        _entry = _jarFile.getEntry("META-INF/resources/mylib/tag1.xhtml");
        assertNotNull(_entry);
        _contentTypeResolver = new ContentTypeResolver(
                new MockContentTypeManager());
    }

    @Test
    public void testJarBasedJSFResource() throws MalformedURLException,
            InvalidIdentifierException
    {
        final URL url = JarUtilities.INSTANCE.createJarUrl(_jarFile,
                (JarEntry) _entry);
        final ResourceIdentifier id = new ResourceIdentifierFactory()
                .createLibraryResource("mylib/tag1.xhtml");

        final JarBasedJSFResource resource = new JarBasedJSFResource(id, url,
                _contentTypeResolver);
        assertSame(id, resource.getId());
        assertSame(url, resource.getJarURL());
        assertTrue(resource.isAccessible());
    }

    @Test
    public void testJarBasedJSFResource_BadUrl() throws MalformedURLException,
            InvalidIdentifierException
    {
        final URL url = JarUtilities.INSTANCE.createJarUrl("/SomeBadPath", "");
        final ResourceIdentifier id = new ResourceIdentifierFactory()
                .createLibraryResource("mylib/tag1.xhtml");

        final JarBasedJSFResource resource = new JarBasedJSFResource(id, url,
                _contentTypeResolver);
        assertSame(id, resource.getId());
        assertSame(url, resource.getJarURL());
        // the url is bad, so we won't get anything.
        assertFalse(resource.isAccessible());
    }

    @Test
    public void testJarBasedJSFResource_GoodUrlBadEntry()
            throws MalformedURLException, InvalidIdentifierException
    {
        final URL url = JarUtilities.INSTANCE.createJarUrl(_jarFile,
                (JarEntry) _entry);
        final ResourceIdentifier id = new ResourceIdentifierFactory()
                .createLibraryResource("mylib/notATag1.xhtml");

        final JarBasedJSFResource resource = new JarBasedJSFResource(id, url,
                _contentTypeResolver);
        assertSame(id, resource.getId());
        assertSame(url, resource.getJarURL());
        // the url is good, but there is no entry for notATag1,
        // so we won't get anything.
        assertFalse(resource.isAccessible());
    }

    @Test
    public void testJarEntryURL() throws IOException, URISyntaxException
    {
        final URL url = JarUtilities.INSTANCE.createJarUrl(_jarFile,
                (JarEntry) _entry);
        assertNotNull(url);
        final URLConnection jarConnection = url.openConnection();
        assertTrue(jarConnection instanceof JarURLConnection);
        final JarEntry jarEntry = ((JarURLConnection) jarConnection)
                .getJarEntry();
        assertNotNull(jarEntry);
        assertEquals(_entry.getName(), jarEntry.getName());
        assertEquals(_entry.isDirectory(), jarEntry.isDirectory());
    }
}
