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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

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
	protected boolean          _projectCreated = false;
    
	/**
	 * the project name
	 */
	protected final String     _projectName;
	/**
	 * the project
	 */
	protected IProject		   _project;

	
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
	public boolean isProjectCreated() {
		return _projectCreated;
	}
	
	/**
     * @param ignoreProjectExists -- only has impact if _projectName already
     * exists in the workspace.  In this case, if set to true, then createProject
     * will return without error if the project exists (it will be deleted and recreated)
     * 
     * If set to false and the project exists, a runtime exception will be thrown
	 */
	public void createProject(boolean ignoreProjectExists) 
    {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(_projectName);
        
        if (project.isAccessible() && !ignoreProjectExists)
        {
            throw new RuntimeException("Project was not expected to exist but does: "+project.getName());
        }
        
		if(!isProjectCreated()) 
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
                }
                catch (CoreException ce)
                {
                    ce.printStackTrace();
                }
            }
		}
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
                workspace.delete(new IResource[] { oldWebProj }, true, null);
            }
        }
        catch (CoreException ce) 
        {
            ce.printStackTrace();
        }
    }
    

	/**
	 * Clears the _projectCreated flag, allowing createProject to be called to reset
	 * the web project back to it's initial state
	 */
	public void setProjectDirtied() {
		_projectCreated = false;
	}

	/**
	 * Forces recreation of the test project - to be used after unit tests
	 * that dirty the project state.  
	 */
	public void recreateProject() {
		setProjectDirtied();
		createProject(true);
	}

	/**
	 * Should call createProject first. 
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
