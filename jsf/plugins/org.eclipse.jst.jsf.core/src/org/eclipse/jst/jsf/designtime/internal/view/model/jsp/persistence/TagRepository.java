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
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence.MasterIndex.ProjectIndex;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence.MasterIndex.SerializableVersion;

class TagRepository
{
    private final static Object GLOBAL_MASTER_INDEX_LOCK = new Object();
    private static MasterIndex  GLOBAL_MASTER_INDEX;
    private final static String PATH_TO_REPOSITORY       = "/.jsptagregistry";
    private final static String MASTER_INDEX_FILE_NAME   = "tagRegistryMasterIndex.idx";

    private final IProject              _project;
    private Map<String, Namespace>      _namespaces;

    public TagRepository(final IProject project)
    {
        _project = project;
    }

    public Map<String, Namespace> load() throws IOException, ClassNotFoundException
    {
        final IPath wkspacePath = JSFCorePlugin.getDefault().getStateLocation();
        final IPath repoPath = wkspacePath.append(PATH_TO_REPOSITORY);
        final File repoDir = new File(repoPath.toOSString());

        final MasterIndex index = getOrCreateMasterIndex(repoDir);

        final ProjectIndex projIndex = index.getProjectIndex(_project);
        _namespaces = projIndex.getNamespaces();
        return _namespaces;
    }

    public void save(Map<String, Namespace> namespaces)
    {
        // TODO Auto-generated method stub
        
    }

    private static MasterIndex getOrCreateMasterIndex(final File repoDir)
            throws IOException, ClassNotFoundException
    {
        synchronized (GLOBAL_MASTER_INDEX_LOCK)
        {
            if (GLOBAL_MASTER_INDEX != null)
            {
                return GLOBAL_MASTER_INDEX;
            }

            if (!repoDir.exists())
            {
                createNewRepo(repoDir);
            }

            final File masterIndexFile = new File(new Path(repoDir
                    .getAbsolutePath()).append(MASTER_INDEX_FILE_NAME)
                    .toOSString());
            final MasterIndex index = new MasterIndex(repoDir.getAbsolutePath());

            if (!masterIndexFile.exists())
            {
                if (!masterIndexFile.createNewFile())
                {
                    throw new IOException(
                            "Could not create new master index file: "
                                    + masterIndexFile.toString());
                }
                index.create(masterIndexFile, new SerializableVersion(1, 0, 0));
            }
            else
            {
                index.load(masterIndexFile, new SerializableVersion(1, 0, 0));
            }

            GLOBAL_MASTER_INDEX = index;
            return index;
        }
    }

    private static void createNewRepo(final File repoDir) throws IOException
    {
        if (!repoDir.mkdir())
        {
            throw new IOException("Failed to create repo directory: "
                    + repoDir.getAbsolutePath());
        }
    }
}