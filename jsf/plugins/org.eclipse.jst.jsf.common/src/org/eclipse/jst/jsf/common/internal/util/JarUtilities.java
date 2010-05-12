package org.eclipse.jst.jsf.common.internal.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author cbateman
 *
 */
public class JarUtilities
{
    private static final String JAR_PREFIX = "jar:"; //$NON-NLS-1$
    private static final String JAR_FILE_PREFIX = JAR_PREFIX + "file:"; //$NON-NLS-1$
    /**
     * A common instance.
     */
    public static final JarUtilities INSTANCE = new JarUtilities();
    /**
     * @param jarFile
     * @return the URL for the jarFile
     * @throws MalformedURLException
     */
    public URL createJarUrl(final JarFile jarFile) throws MalformedURLException
    {
        return createJarUrl(jarFile, ""); //$NON-NLS-1$
    }
    
    /**
     * @param jarFile
     * @param jarEntry
     * @return the URL for the jarEntry inside jarFile.
     * @throws MalformedURLException
     */
    public URL createJarUrl(final JarFile jarFile, final JarEntry jarEntry) throws MalformedURLException
    {
        return createJarUrl(jarFile, jarEntry != null ? jarEntry.getName() : ""); //$NON-NLS-1$
    }
    
    /**
     * @param jarFile
     * @param jarEntryName
     * @return the URL for the jarEntryName inside jarFile.
     * @throws MalformedURLException 
     */
    public URL createJarUrl(final JarFile jarFile, final String jarEntryName) throws MalformedURLException
    {
        final String fixedJarFileName = jarFile.getName().replace("\\", "/"); //$NON-NLS-1$ //$NON-NLS-2$
        return createJarUrl(fixedJarFileName, jarEntryName != null ? jarEntryName : ""); //$NON-NLS-1$
    }
    
    /**
     * @param filePath
     * @param jarEntryName
     * @return the URL for the jarEntryName in the file at filePath
     * @throws MalformedURLException
     */
    public URL createJarUrl(final String filePath, final String jarEntryName) throws MalformedURLException
    {
        final String urlString = String.format("jar:file:///%s!/%s", //$NON-NLS-1$
                filePath, jarEntryName); 
        return new URL(urlString); 
    }
    
    /**
     * @param url
     * @return a file for the URL if url is in the local file system (must conform to jar:file:// uri).
     * or null.
     */
    public File getFile(final URL url)
    {
        String string = url.toString();
        if (string != null && string.startsWith(JAR_FILE_PREFIX))
        {
            string = string.substring(JAR_PREFIX.length());
            try
            {
                return new File(URI.create(string));
            } catch (IllegalArgumentException e)
            {
                // fallthorough and return null if the file can't do anything
                // with the string.
            }
        }
        return null;
    }
}
