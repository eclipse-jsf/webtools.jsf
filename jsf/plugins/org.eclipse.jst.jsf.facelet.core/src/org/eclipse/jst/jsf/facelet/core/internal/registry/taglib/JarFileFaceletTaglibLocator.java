/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.AlwaysMatcher;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.IMatcher;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher;
import org.eclipse.jst.jsf.common.internal.finder.acceptor.JarEntryMatchingAcceptor;
import org.eclipse.jst.jsf.common.internal.finder.matcher.TaglibJarEntryFinder;
import org.eclipse.jst.jsf.common.internal.resource.DefaultJarProvider;
import org.eclipse.jst.jsf.common.internal.resource.IJarProvider;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;

/**
 * A locator that finds Facelet taglibs in jars on the classpath
 * 
 * @author cbateman
 * 
 */
public class JarFileFaceletTaglibLocator extends AbstractFaceletTaglibLocator
{
    /**
     * Default taglib finder that looks in meta-inf
     */
    public static final TaglibJarEntryFinder _taglibMetaInfFinder = new TaglibJarEntryFinder(
            Pattern.compile("META-INF/.*\\.taglib\\.xml")); //$NON-NLS-1$
    /**
     * Default finder that looks in the glassfish package.
     */
    public static final TaglibJarEntryFinder _taglibGlassfishFinder = new TaglibJarEntryFinder(
            Pattern.compile("com/sun/faces/metadata/taglib/.*\\.taglib\\.xml")); //$NON-NLS-1$

    private static final List<IMatcher> MATCHERS;

    static
    {
        final List<IMatcher> matchers = new ArrayList<IMatcher>();
        matchers.add(_taglibGlassfishFinder);
        matchers.add(_taglibMetaInfFinder);
        MATCHERS = Collections.unmodifiableList(matchers);
    }
    private static final String DISPLAYNAME = Messages.JarFileFaceletTaglibLocator_0;
    private static final String ID = JarFileFaceletTaglibLocator.class
            .getCanonicalName();
    private final TagRecordFactory _factory;
    private final Map<String, IFaceletTagRecord> _records;
    private final IJarProvider _provider;

    /**
     * @param factory
     */
    public JarFileFaceletTaglibLocator(final TagRecordFactory factory)
    {
        this(factory, new DefaultJarProvider(Collections
                .singletonList(new AlwaysMatcher())));
    }

    /**
     * @param factory
     * @param jarProvider
     */
    public JarFileFaceletTaglibLocator(final TagRecordFactory factory,
            final IJarProvider jarProvider)
    {
        super(ID, DISPLAYNAME);
        _factory = factory;
        _records = new HashMap<String, IFaceletTagRecord>();
        _provider = jarProvider;
    }

    @Override
    public Map<String, ? extends IFaceletTagRecord> doLocate(
            final IProject project)
    {
        final List<FaceletTaglib> tagLibsFound = new ArrayList<FaceletTaglib>();

        final Collection<? extends JarFile> jars = _provider.getJars(project);

        for (final JarFile jarFile : jars)
        {
            tagLibsFound.addAll(processJar(jarFile));
        }

        for (final FaceletTaglib tag : tagLibsFound)
        {
            final IFaceletTagRecord record = _factory.createRecords(tag);
            if (record != null)
            {
                _records.put(record.getURI(), record);
            }
        }

        return _records;
    }

    /**
     * @param entry
     * @param defaultDtdStream
     * @throws Exception
     */
    private List<FaceletTaglib> processJar(final JarFile jarFile)
    {
        final List<FaceletTaglib> tagLibsFound = new ArrayList<FaceletTaglib>();

        try
        {
            if (jarFile != null)
            {
                final JarEntryMatchingAcceptor acceptor = new JarEntryMatchingAcceptor();
                final VisitorMatcher<JarFile, JarEntry, String> matcher = new VisitorMatcher<JarFile, JarEntry, String>(
                        "", "", acceptor, MATCHERS); //$NON-NLS-1$//$NON-NLS-2$
                final Collection<? extends JarEntry> matchingEntries = matcher
                        .find(jarFile);
                for (final JarEntry jarEntry : matchingEntries)
                {
                    InputStream is = null;
                    try
                    {
                        is = jarFile.getInputStream(jarEntry);
                        final TagModelLoader loader = new TagModelLoader(
                                jarEntry.getName());
                        loader.loadFromInputStream(is);
                        final FaceletTaglib tagLib = loader.getTaglib();

                        if (tagLib != null)
                        {
                            tagLibsFound.add(tagLib);
                        }
                    } catch (final Exception e)
                    {
                        FaceletCorePlugin.log(
                                "Error initializing facelet registry entry", //$NON-NLS-1$
                                e);
                    } finally
                    {
                        if (is != null)
                        {
                            // is.close();
                        }
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
                    FaceletCorePlugin.log("Error closing jar file", ioe); //$NON-NLS-1$
                }
            }
        }
        return tagLibsFound;
    }
}
