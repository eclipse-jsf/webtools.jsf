package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
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

    private IndexHeader                     _header;
    private List<ProjectIndex>              _projectIndices;

    public MasterIndex(final String repositoryPath)
    {
        _repositoryPath = repositoryPath;
    }

    public void create(final File file, final SerializableVersion version)
            throws IOException
    {
        _header = new IndexHeader(version);
        _projectIndices = new ArrayList<ProjectIndex>();

        save(file);
    }

    public void save(final File file) throws IOException
    {
        FileOutputStream out = null;

        try
        {
            out = new FileOutputStream(file);
            final ObjectOutputStream objStream = new ObjectOutputStream(out);
            objStream.writeObject(this);
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    public void load(final File file, final SerializableVersion expectedVersion)
            throws IOException, ClassNotFoundException
    {
        FileInputStream in = null;

        try
        {
            in = new FileInputStream(file);
            final ObjectInputStream objStream = new ObjectInputStream(in);
            MasterIndex index = (MasterIndex) objStream.readObject();
            _header = index._header;
            _projectIndices = index._projectIndices;
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
        }
    }

    public synchronized ProjectIndex getProjectIndex(final IProject project)
    {
        // check if the project already exists
        for (final ProjectIndex index : _projectIndices)
        {
            if (index.getProjectName().equals(project.getName()))
            {
                return index;
            }
        }

        final ProjectIndex index = new ProjectIndex(0, project.getName(),
                _repositoryPath);
        index.create();
        _projectIndices.add(index);
        return index;
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
         * 
         */
        private static final long         serialVersionUID = 40851054201507727L;

        private final SerializableVersion _version;
        private long                      _lastModifiedStamp;

        public IndexHeader(final SerializableVersion version)
        {
            _version = version;
            _lastModifiedStamp = 0;
        }

        protected final Version getVersion()
        {
            return _version;
        }

        protected final synchronized long modified()
        {
            return _lastModifiedStamp++;
        }

        protected final synchronized long getLastModifiedStamp()
        {
            return _lastModifiedStamp;
        }
    }

    static class SerializableVersion extends Version implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 5973410328814890568L;

        public SerializableVersion(final int major, final int minor,
                final int micro)
        {
            super(major, minor, micro);
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

        public synchronized void create()
        {
            final int uniqueCode = _projectName.hashCode()
                    ^ ((int) Math.random() * Integer.MAX_VALUE);
            final String fileName = String.format("Project_%s_%x.idx",
                    _projectName, Integer.valueOf(uniqueCode));
            _file = new File(new Path(_relativePath).append(fileName)
                    .toOSString());
        }

        public synchronized Map<String, Namespace> getNamespaces()
                throws IOException, ClassNotFoundException
        {
            FileInputStream in = null;

            try
            {
                in = new FileInputStream(_file);
                final ObjectInputStream objStream = new ObjectInputStream(in);
                final Map<String, Namespace> namespaces = (Map<String, Namespace>) objStream
                        .readObject();
                return namespaces;
            }
            finally
            {
                if (in != null)
                {
                    in.close();
                }
            }
        }

        public synchronized void save(final Map<String, Namespace> namespaces) throws IOException
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
