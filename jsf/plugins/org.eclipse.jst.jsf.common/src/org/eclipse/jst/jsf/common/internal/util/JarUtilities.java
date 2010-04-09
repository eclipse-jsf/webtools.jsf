package org.eclipse.jst.jsf.common.internal.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author cbateman
 *
 */
public class JarUtilities
{
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
}
