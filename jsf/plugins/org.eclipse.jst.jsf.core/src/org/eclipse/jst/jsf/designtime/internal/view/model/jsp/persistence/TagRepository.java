/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence.MasterIndex.ProjectIndex;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence.MasterIndex.SerializableVersion;

class TagRepository
{
    private final static Object       GLOBAL_MASTER_INDEX_LOCK = new Object();
    private static MasterIndex        GLOBAL_MASTER_INDEX;
    private final static String       PATH_TO_REPOSITORY       = "/.jsptagregistry"; //$NON-NLS-1$
    private final static String       MASTER_INDEX_FILE_NAME   = "tagRegistryMasterIndex.idx"; //$NON-NLS-1$

    private final IProject            _project;
    //private Map<String, SerializableTLDNamespace> _namespaces;

    public TagRepository(final IProject project)
    {
        _project = project;
    }

    public Map<String, SerializableTLDNamespace> load() throws IOException,
            ClassNotFoundException
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions.log("Loading tag repository for: " //$NON-NLS-1$
                    + _project.toString());
        }

        final MasterIndex index = getOrCreateMasterIndex();

        final ProjectIndex projIndex = index.getProjectIndex(_project);
        final Map<String, SerializableTLDNamespace> namespaces = projIndex.getNamespaces();

        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions.log("Contents of repo for: " //$NON-NLS-1$
                    + _project.toString());
            
            for (final Map.Entry<String, SerializableTLDNamespace> nsEntry : namespaces.entrySet())
            {
                JSFCoreTraceOptions.log("\tNamespace: "+nsEntry.getKey()); //$NON-NLS-1$
                
                SerializableTLDNamespace ns = nsEntry.getValue();
                for (final ITagElement element : ns.getViewElements())
                {
                    JSFCoreTraceOptions.log("\t\tTag: "+element.toString()); //$NON-NLS-1$
                }
            }
        }

        return namespaces;
    }

    public void save(Map<String, SerializableTLDNamespace> namespaces) throws IOException,
            ClassNotFoundException
    {
        final MasterIndex index = getOrCreateMasterIndex();
        final ProjectIndex projectIndex = index.getProjectIndex(_project);
        projectIndex.save(namespaces);
    }

    public void clearAll() throws IOException, ClassNotFoundException
    {
        final MasterIndex index = getOrCreateMasterIndex();
        index.removeProjectIndex(_project);
    }

    private MasterIndex getOrCreateMasterIndex() throws IOException,
            ClassNotFoundException
    {
        final IPath wkspacePath = JSFCorePlugin.getDefault().getStateLocation();
        final IPath repoPath = wkspacePath.append(PATH_TO_REPOSITORY);
        final File repoDir = new File(repoPath.toOSString());

        synchronized (GLOBAL_MASTER_INDEX_LOCK)
        {
            if (GLOBAL_MASTER_INDEX != null)
            {
                if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
                {
                    JSFCoreTraceOptions
                            .log("GLOBAL_MASTER_INDEX already exists for: " //$NON-NLS-1$
                                    + _project.toString());
                }
                return GLOBAL_MASTER_INDEX;
            }

            if (!repoDir.exists())
            {
                createNewRepo(repoDir);
            }

            final File masterIndexFile = new File(new Path(repoDir
                    .getAbsolutePath()).append(MASTER_INDEX_FILE_NAME)
                    .toOSString());
            final MasterIndex index = new MasterIndex(masterIndexFile, repoDir.getAbsolutePath());

            if (!masterIndexFile.exists())
            {
                if (!masterIndexFile.createNewFile())
                {
                    throw new IOException(
                            "Could not create new master index file: " //$NON-NLS-1$
                                    + masterIndexFile.toString());
                }
                if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
                {
                    JSFCoreTraceOptions.log("Creating master index file for " //$NON-NLS-1$
                            + _project.toString());
                }
                index.create(new SerializableVersion(1, 0, 0));
            }
            else
            {
                if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
                {
                    JSFCoreTraceOptions.log("Loading master index file for " //$NON-NLS-1$
                            + _project.toString());
                }
                index.load(new SerializableVersion(1, 0, 0));
            }

            GLOBAL_MASTER_INDEX = index;
            return index;
        }
    }

    private static void createNewRepo(final File repoDir) throws IOException
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions.log("Creating repo directory: " //$NON-NLS-1$
                    + repoDir.getAbsolutePath());
        }

        if (!repoDir.mkdir())
        {
            throw new IOException("Failed to create repo directory: " //$NON-NLS-1$
                    + repoDir.getAbsolutePath());
        }
    }
}