package org.eclipse.jst.jsf.designtime.internal.resources;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher;
import org.eclipse.jst.jsf.common.internal.finder.acceptor.JarEntryMatchingAcceptor;
import org.eclipse.jst.jsf.common.internal.finder.matcher.TaglibJarEntryFinder;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.common.internal.resource.IJarProvider;
import org.eclipse.jst.jsf.common.internal.util.JarUtilities;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * A JSF resource locator that finds resources in jars.
 * 
 * @author cbateman
 * 
 */
public class JarBasedJSFResourceLocator extends AbstractJSFResourceLocator
{
    private final IJarProvider _provider;
    private final ContentTypeResolver _contentTypeResolver;
    private static final Pattern _resourcePathPattern = Pattern
            .compile("META-INF/resources/(.*)"); //$NON-NLS-1$
    private static final TaglibJarEntryFinder _resourceFinder = new TaglibJarEntryFinder(
            _resourcePathPattern);

    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param mutableListenerList
     * @param provider
     * @param contentTypeResolver
     */
    public JarBasedJSFResourceLocator(
            final String id,
            final String displayName,
            final List<JSFResource> noResultValue,
            final CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList,
            final IJarProvider provider,
            final ContentTypeResolver contentTypeResolver)
    {
        super(id, displayName, noResultValue, mutableListenerList);
        _provider = provider;
        _contentTypeResolver = contentTypeResolver;
    }

    /**
     * @param noResultValue
     * @param mutableListenerList
     * @param provider
     * @param contentTypeResolver
     */
    public JarBasedJSFResourceLocator(
            final List<JSFResource> noResultValue,
            final CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList,
            final IJarProvider provider,
            final ContentTypeResolver contentTypeResolver)
    {
        this(
                "", "", noResultValue, mutableListenerList, provider, contentTypeResolver); //$NON-NLS-1$//$NON-NLS-2$
    }

    @Override
    protected List<JSFResource> doLocate(final IProject project)
    {
        final List<JSFResource> resourcesFound = new ArrayList<JSFResource>();

        final Collection<? extends JarFile> jars = _provider.getJars(project);

        for (final JarFile jarFile : jars)
        {
            resourcesFound.addAll(processJar(jarFile));
        }

        return resourcesFound;
    }

    /**
     * @param entry
     * @param defaultDtdStream
     * @throws Exception
     */
    private List<JSFResource> processJar(final JarFile jarFile)
    {
        final List<JSFResource> tagLibsFound = new ArrayList<JSFResource>();

        try
        {
            if (jarFile != null)
            {
                final JarEntryMatchingAcceptor acceptor = new JarEntryMatchingAcceptor();
                final VisitorMatcher<JarFile, JarEntry, String> matcher = new VisitorMatcher<JarFile, JarEntry, String>(
                        "", "", acceptor, Collections.singletonList(_resourceFinder)); //$NON-NLS-1$//$NON-NLS-2$
                final Collection<? extends JarEntry> matchingEntries = matcher
                        .find(jarFile);
                for (final JarEntry jarEntry : matchingEntries)
                {
                    try
                    {
                        final Matcher patternMatcher = _resourcePathPattern
                                .matcher(jarEntry.getName());
                        if (patternMatcher.matches())
                        {
                            final String group = patternMatcher.group(1);
                            if (group != null && group.trim().length() > 0)
                            {

                                final ResourceIdentifier libRes = new ResourceIdentifierFactory()
                                        .createLibraryResource(group);
                                final URL jarUrl = JarUtilities.INSTANCE
                                        .createJarUrl(jarFile);
                                tagLibsFound.add(new JarBasedJSFResource(
                                        libRes, jarUrl, _contentTypeResolver));
                            }
                        }
                    } catch (final Exception e)
                    {
                        JSFCorePlugin.log(
                                "Error initializing facelet registry entry", //$NON-NLS-1$
                                e);
                    }
                }
            }
        } catch (final Exception e)
        {
            JSFCorePlugin.log(e,
                    "While locating jar based facelet tag libraries"); //$NON-NLS-1$
        } finally
        {
            if (jarFile != null)
            {
                try
                {
                    jarFile.close();
                } catch (final IOException ioe)
                {
                    JSFCorePlugin.log("Error closing jar file", ioe); //$NON-NLS-1$
                }
            }
        }
        return tagLibsFound;
    }

}
