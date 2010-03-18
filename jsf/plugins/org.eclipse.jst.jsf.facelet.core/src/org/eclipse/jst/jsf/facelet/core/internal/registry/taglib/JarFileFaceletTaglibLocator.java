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
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.internal.strategy.SimpleStrategyComposite;
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

    private static final String DISPLAYNAME = Messages.JarFileFaceletTaglibLocator_0;
    private static final String ID = JarFileFaceletTaglibLocator.class
            .getCanonicalName();
    private final TagRecordFactory _factory;
    private Map<String, IFaceletTagRecord> _records;
    private final SimpleStrategyComposite<JarEntry, JarEntry, JarEntry, String, TaglibFinder<JarEntry, JarEntry>> _finder;

    /**
     * @param factory
     */
    public JarFileFaceletTaglibLocator(final TagRecordFactory factory)
    {
        super(ID, DISPLAYNAME);
        _factory = factory;
        _records = new HashMap<String, IFaceletTagRecord>();

        List<TaglibFinder<JarEntry, JarEntry>> finders = new ArrayList<TaglibFinder<JarEntry, JarEntry>>();
        finders.add(_taglibMetaInfFinder);
        finders.add(_taglibGlassfishFinder);

        _finder = new SimpleStrategyComposite<JarEntry, JarEntry, JarEntry, String, TaglibFinder<JarEntry, JarEntry>>(
                finders);

    }

    @Override
    public Map<String, ? extends IFaceletTagRecord> doLocate(
            final IProject project)
    {
        try
        {
            return findInJars(project);
        } catch (JavaModelException e)
        {
            FaceletCorePlugin
                    .log(
                            "Couldn't locate jar file taglibs: " + project.getProject(), e); //$NON-NLS-1$
        }
        return Collections.EMPTY_MAP;
    }

    private Map<String, ? extends IFaceletTagRecord> findInJars(
            final IProject project) throws JavaModelException
    {
        final IJavaProject javaProject = JavaCore.create(project);

        final IClasspathEntry[] entries = javaProject
                .getResolvedClasspath(true);

        final List<FaceletTaglib> tagLibsFound = new ArrayList<FaceletTaglib>();
        for (final IClasspathEntry entry : entries)
        {

            switch (entry.getEntryKind())
            {
            // this entry describes a source root in its project
            case IClasspathEntry.CPE_SOURCE:

                break;
            // - this entry describes a folder or JAR containing
            // binaries
            case IClasspathEntry.CPE_LIBRARY:
            {
                tagLibsFound.addAll(processJar(entry));
            }
                break;
            // - this entry describes another project
            case IClasspathEntry.CPE_PROJECT:
                // {
                // final IPath pathToProject = entry.getPath();
                // IWorkspace wkspace = ResourcesPlugin.getWorkspace();
                // IResource res =
                // wkspace.getRoot().findMember(pathToProject);
                // if (res instanceof IProject)
                // {
                // tagLibsFound.addAll();
                // }
                // }
                break;
            // - this entry describes a project or library indirectly
            // via a
            // classpath variable in the first segment of the path *
            case IClasspathEntry.CPE_VARIABLE:
                break;
            // - this entry describes set of entries referenced
            // indirectly
            // via a classpath container
            case IClasspathEntry.CPE_CONTAINER:
                break;
            }
        }

        for (final FaceletTaglib tag : tagLibsFound)
        {
            IFaceletTagRecord record = _factory.createRecords(tag);
            if (record != null)
            {
                _records.put(record.getURI(), record);
            }
        }

        return _records;
    }

    /**
     * TODO: Merge into JSFAppConfigUtils.
     * 
     * @param entry
     * @return
     */
    private static JarFile getJarFileFromCPE(final IClasspathEntry entry)
            throws IOException
    {
        if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY)
        {
            IPath libraryPath = entry.getPath();
            if (libraryPath.getFileExtension() != null
                    && libraryPath.getFileExtension().length() > 0)
            {
                final IWorkspaceRoot workspaceRoot = ResourcesPlugin
                        .getWorkspace().getRoot();
                if (libraryPath.getDevice() == null
                        && workspaceRoot.getProject(libraryPath.segment(0))
                                .exists())
                {
                    libraryPath = workspaceRoot.getFile(libraryPath)
                            .getLocation();
                }
                final String libraryPathString = libraryPath.toString();
                return new JarFile(libraryPathString);
            }
        }
        return null;
    }

    /**
     * @param entry
     * @param defaultDtdStream
     */
    private List<FaceletTaglib> processJar(final IClasspathEntry entry)
    {
        JarFile jarFile = null;
        final List<FaceletTaglib> tagLibsFound = new ArrayList<FaceletTaglib>();

        try
        {
            jarFile = getJarFileFromCPE(entry);

            if (jarFile != null)
            {
                final Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements())
                {
                    JarEntry jarEntry = jarEntries.nextElement();

                    jarEntry = _finder.perform(jarEntry);

                    if (jarEntry != null && jarEntry != _finder.getNoResult())
                    {
                        //                    if ((name.startsWith("META-INF/") //$NON-NLS-1$
                        //                            && name.endsWith(".taglib.xml")) //$NON-NLS-1$
                        //                            || (name.startsWith("com/sun/faces/metadata/taglib/") //$NON-NLS-1$ //ludo GlassFish v3
                        //                            && name.endsWith(".taglib.xml"))) //$NON-NLS-1$
                        {
                            InputStream is = null;
                            try
                            {
                                is = jarFile.getInputStream(jarEntry);
                                TagModelLoader loader = new TagModelLoader(jarEntry.getName());
                                loader.loadFromInputStream(is);
                                FaceletTaglib tagLib = loader.getTaglib();

                                if (tagLib != null)
                                {
                                    tagLibsFound.add(tagLib);
                                }
                            } catch (final Exception e)
                            {
                                FaceletCorePlugin
                                        .log(
                                                "Error initializing facelet registry entry", //$NON-NLS-1$
                                                e);
                            }
                            finally
                            {
                                if (is != null)
                                {
                                    // is.close();
                                }
                            }
                        }
                    }
                }
            }
        } catch (final IOException e)
        {
            FaceletCorePlugin.log(
                    "Error opening classpath jar file: " + entry, e); //$NON-NLS-1$
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
