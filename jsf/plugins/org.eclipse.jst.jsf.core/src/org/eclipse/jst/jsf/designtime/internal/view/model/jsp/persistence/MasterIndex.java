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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.osgi.framework.Version;

/**
 * The definitive index of what projects are indexed for JSP tag registry
 * persistence.
 * 
 * @author cbateman
 * 
 */
class MasterIndex implements Serializable
{
    public final static SerializableVersion CURRENT_VERSION  = new SerializableVersion(
                                                                     1, 0, 0);

    /**
     * 
     */
    private static final long               serialVersionUID = -2725662604972649316L;

    private final transient String          _repositoryPath;
    private final transient File            _storageFile;

    private IndexHeader                     _header;
    private List<ProjectIndex>              _projectIndices;

    public MasterIndex(final File storageFile, final String repositoryPath)
    {
        _storageFile = storageFile;
        _repositoryPath = repositoryPath;
    }

    public synchronized void create(final SerializableVersion version)
            throws IOException
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions.log("MasterIndex.create, version=:" //$NON-NLS-1$
                    + version.toString());
        }

        _header = new IndexHeader(version);
        _projectIndices = new ArrayList<ProjectIndex>();

        save(_storageFile);
    }

    public synchronized void save(final File file) throws IOException
    {
        FileOutputStream out = null;

        try
        {
            if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
            {
                JSFCoreTraceOptions.log("Trying to save master index file: " //$NON-NLS-1$
                        + file.getAbsolutePath());
            }

            out = new FileOutputStream(file);
            final ObjectOutputStream objStream = new ObjectOutputStream(out);
            objStream.writeObject(this);

            if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
            {
                JSFCoreTraceOptions
                        .log("Master index file written successfully: " //$NON-NLS-1$
                                + file.getAbsolutePath());
            }
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    public synchronized void load(final SerializableVersion expectedVersion)
            throws IOException, ClassNotFoundException
    {
        FileInputStream in = null;

        try
        {
            if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
            {
                JSFCoreTraceOptions.log("Trying to load master index file: " //$NON-NLS-1$
                        + _storageFile.getAbsolutePath());
            }

            in = new FileInputStream(_storageFile);
            final ObjectInputStream objStream = new ObjectInputStream(in);
            MasterIndex index = (MasterIndex) objStream.readObject();
            _header = index._header;
            _projectIndices = index._projectIndices;

            if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
            {
                JSFCoreTraceOptions
                        .log("Loaded master index file successfully:" //$NON-NLS-1$
                                + _storageFile.getAbsolutePath());
                JSFCoreTraceOptions.log("Initial contents: "); //$NON-NLS-1$
                System.out.println(index.toString());
            }
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
        }
    }

    public synchronized String toString()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append(String.format("Header: %s\n\n", _header.toString())); //$NON-NLS-1$

        for (final ProjectIndex projIndex : _projectIndices)
        {
            buffer.append(String.format("\t%s\n", projIndex.toString())); //$NON-NLS-1$
        }

        return buffer.toString();
    }

    public synchronized ProjectIndex getProjectIndex(final IProject project)
    {
        ProjectIndex index = findIndex(project);

        if (index != null)
        {
            return index;
        }

        // otherwise, create.
        index = new ProjectIndex(0, project.getName(), _repositoryPath);
        index.create();
        _projectIndices.add(index);
        try
        {
            save(_storageFile);
        }
        catch (IOException ioe)
        {
            JSFCorePlugin.log(ioe,
                    "Failed to save master index.  Project Index for " //$NON-NLS-1$
                            + project.toString() + " may not be saved"); //$NON-NLS-1$
        }
        return index;
    }

    public synchronized void removeProjectIndex(final IProject project) throws IOException
    {
        final ProjectIndex index = findIndex(project);
        if (index != null)
        {
            _projectIndices.remove(index);
            if (!index.remove())
            {
                throw new IOException("Failed to remove index file"); //$NON-NLS-1$
            }
        }
    }

    private ProjectIndex findIndex(final IProject project)
    {
        // check if the project already exists
        for (final ProjectIndex index : _projectIndices)
        {
            if (index.getProjectName().equals(project.getName()))
            {
                return index;
            }
        }
        return null;
    }

    private void writeObject(final java.io.ObjectOutputStream out)
            throws IOException
    {
        out.writeObject(_header);
        out.writeObject(_projectIndices);
    }

    private void readObject(final java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException
    {
        _header = (IndexHeader) in.readObject();
        _projectIndices = (List<ProjectIndex>) in.readObject();
    }

    private class IndexHeader implements Serializable
    {
        /**
         * serializable
         */
        private static final long         serialVersionUID = 40851054201507727L;

        private final SerializableVersion _version;
//        private long                      _lastModifiedStamp;

        public IndexHeader(final SerializableVersion version)
        {
            _version = version;
//            _lastModifiedStamp = 0;
        }

//        protected final Version getVersion()
//        {
//            return _version.getVersion();
//        }
//
//        protected final synchronized long modified()
//        {
//            return _lastModifiedStamp++;
//        }
//
//        protected final synchronized long getLastModifiedStamp()
//        {
//            return _lastModifiedStamp;
//        }

        public String toString()
        {
            return "Version: " + _version.toString(); //$NON-NLS-1$
        }
    }

    static class SerializableVersion implements Serializable
    {
        private Version           _version;
        /**
         * 
         */
        private static final long serialVersionUID = 5973410328814890568L;

        public SerializableVersion(final int major, final int minor,
                final int micro)
        {
            _version = new Version(major, minor, micro);
        }

        public SerializableVersion(final String versionString)
        {
            _version = new Version(versionString);
        }

        public Version getVersion()
        {
            return _version;
        }

        public String toString()
        {
            return _version.toString();
        }

        private void writeObject(ObjectOutputStream out) throws IOException
        {
            out.writeObject(_version.toString());
        }

        private void readObject(ObjectInputStream in) throws IOException,
                ClassNotFoundException
        {
            final String versionString = (String) in.readObject();
            _version = new Version(versionString);
        }
    }

    static class ProjectIndex implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 2864798175910805380L;

        private final String      _projectName;
        private final String      _relativePath;
        private final long        _lastModStampOnProject;
        private File              _file;

        /**
         * @param lastModStampOnProject
         * @param projectName
         * @param relativePath
         */
        public ProjectIndex(final long lastModStampOnProject,
                final String projectName, final String relativePath)
        {
            super();
            _lastModStampOnProject = lastModStampOnProject;
            _projectName = projectName;
            _relativePath = relativePath;
        }

        public boolean remove()
        {
            return _file.delete();
        }

        public synchronized void create()
        {
            final long uniqueCode = Math
                    .round((Math.random() * Integer.MAX_VALUE));
            final String fileName = String.format("Project_%s_%x.idx", //$NON-NLS-1$
                    _projectName, Long.valueOf(uniqueCode));
            _file = new File(new Path(_relativePath).append(fileName)
                    .toOSString());
        }

        public synchronized Map<String, SerializableTLDNamespace> getNamespaces()
                throws IOException, ClassNotFoundException
        {
            FileInputStream in = null;

            try
            {
                in = new FileInputStream(_file);
                final ObjectInputStream objStream = new ObjectInputStream(in);
                final Map<String, SerializableTLDNamespace> namespaces = (Map<String, SerializableTLDNamespace>) objStream
                        .readObject();
                return namespaces;
            }
            catch (FileNotFoundException nfe)
            {
                return new HashMap<String, SerializableTLDNamespace>();
            }
            finally
            {
                if (in != null)
                {
                    in.close();
                }
            }
        }

        public synchronized void save(
                final Map<String, SerializableTLDNamespace> namespaces)
                throws IOException
        {
            FileOutputStream out = null;

            try
            {
                out = new FileOutputStream(_file);
                final ObjectOutputStream objStream = new ObjectOutputStream(out);
                objStream.writeObject(namespaces);
            }
            finally
            {
                if (out != null)
                {
                    out.close();
                }
            }
        }

        public String toString()
        {
            return "project= " + _projectName + ", relativePath=" //$NON-NLS-1$ //$NON-NLS-2$
                    + _relativePath + ", lastModified=" //$NON-NLS-1$
                    + _lastModStampOnProject + ", saveFile=" + _file; //$NON-NLS-1$
        }

        // public synchronized void save()
        // {
        // if (_file != null)
        // {
        // doSave();
        // }
        // throw new NullPointerException("_file is null");
        // }
        //
        // private void doSave() throws IOException
        // {
        // FileOutputStream out = null;
        //            
        // try
        // {
        // out = new FileOutputStream(_file);
        // final ObjectOutputStream objStream = new ObjectOutputStream(out);
        // objStream.writeObject(this);
        // }
        // finally
        // {
        // if (out != null)
        // {
        // out.close();
        // }
        // }
        // }

        protected final String getProjectName()
        {
            return _projectName;
        }

        protected final String getRelativePath()
        {
            return _relativePath;
        }

        protected final long getLastModStampOnProject()
        {
            return _lastModStampOnProject;
        }
    }
}
