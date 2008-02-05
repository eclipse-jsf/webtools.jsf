/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Oracle -- adapted WizardUtil class for EMF faces config model testing
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.lang.reflect.InvocationTargetException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;

/**
 * Utility class for launching JSF-related wizard operations.
 * 
 * @author spaxton, cbateman
 */
public class ProjectTestEnvironment
{
    /**
     * flag indicating if the project test env has been created
     */
    protected boolean      _projectCreated = false;

    /**
     * the project name
     */
    protected final String _projectName;
    /**
     * the project
     */
    protected IProject     _project;

    protected static final IOverwriteQuery OVERWRITE_ALL_QUERY =
        new IOverwriteQuery()
        {
            public String queryOverwrite(
                    String pathString)
            {
                return IOverwriteQuery.ALL;
            }
        };

    /**
      * @param projectName
      */
    public ProjectTestEnvironment(final String projectName)
    {
        _projectName = projectName;
    }

    /**
     * @return true if the project is in a valid created state
     */
    public boolean isProjectCreated()
    {
        return _projectCreated;
    }

    /**
     * @param ignoreProjectExists --
     *            only has impact if _projectName already exists in the
     *            workspace. In this case, if set to true, then createProject
     *            will return without error if the project exists (it will be
     *            deleted and recreated)
     * 
     * If set to false and the project exists, a runtime exception will be
     * thrown
     * 
     * @return true if project is created
     */
    public boolean createProject(boolean ignoreProjectExists)
    {
        IProject project =
                           ResourcesPlugin.getWorkspace().getRoot().getProject(
                                   _projectName);

        if (project.isAccessible() && !ignoreProjectExists)
        {
            throw new RuntimeException(
                    "Project was not expected to exist but does: "
                            + project.getName());
        }

        if (!isProjectCreated())
        {
            // first delete the projects of these names, if present
            deleteProject();

            if (!project.exists())
            {
                try
                {
                    final IProgressMonitor monitor = new NullProgressMonitor();
                    project.create(monitor);
                    project.open(monitor);
                    _project = project;
                    _projectCreated = true;
                    return true;
                }
                catch (CoreException ce)
                {
                    ce.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * Creates the new projecct from the specified zip file.  createProject
     * is first called to create and open the empty project.  The projectZip
     * is then used to populate the contents of the project.
     * 
     * @param projectZip
     * @param ignoreProjectExists
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    public final void createFromZip(final ZipFile projectZip,
            final boolean ignoreProjectExists) throws InvocationTargetException, InterruptedException
    {
    	// TODO: assert that the faceting in the zip matches what was set in the constructor.
        createProject(ignoreProjectExists);
        ZipFileStructureProvider zipFileStructureProvider =
            new ZipFileStructureProvider(
                    projectZip)
        {

            @Override
            public String getFullPath(Object element)
            {
                final String fullName = super.getFullPath(element);
                IPath asPath = new Path(fullName);
                
                if (asPath.segmentCount() > 0)
                {
                    boolean pathIsAbsolute = asPath.isAbsolute();
                    String newFirstSegment = (pathIsAbsolute ? "/" : "") + _projectName;
                    asPath = new Path(newFirstSegment+"/"+asPath.removeFirstSegments(1).toString());
                }
                return asPath.toString();
            }

            @Override
            public String getLabel(Object element)
            {
                if (element.equals(getRoot())) {
                    return ((ZipEntry) element).getName();
                }

                return new Path(getFullPath(element)).lastSegment();
            }
        };

        final ImportOperation op =
                             new ImportOperation(_project
                                     .getProjectRelativePath(),
                                     zipFileStructureProvider.getRoot(),
                                     zipFileStructureProvider,
                                     OVERWRITE_ALL_QUERY);
        op.setCreateContainerStructure(true);
        op.run(null);
    }

    /**
     * Delete project
     */
    public void deleteProject()
    {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject oldWebProj = workspace.getRoot().getProject(_projectName);

        try
        {
            if (oldWebProj != null && oldWebProj.isAccessible())
            {
                workspace.delete(new IResource[]
                { oldWebProj }, true, null);
            }
        }
        catch (CoreException ce)
        {
            ce.printStackTrace();
        }
    }

    /**
     * Clears the _projectCreated flag, allowing createProject to be called to
     * reset the web project back to it's initial state
     */
    public void setProjectDirtied()
    {
        _projectCreated = false;
    }

    /**
     * Forces recreation of the test project - to be used after unit tests that
     * dirty the project state.
     */
    public void recreateProject()
    {
        setProjectDirtied();
        createProject(true);
    }

    /**
     * Should call createProject first.
     * 
     * @return the IProject
     */
    public IProject getTestProject()
    {
        if (_projectCreated)
        {
            return _project;
        }

        return null;
    }
}
