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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.osgi.framework.Bundle;

/**
 * Utility class for launching JSF-related wizard operations. 
 *
 * @author spaxton, cbateman
 */
public class ProjectTestEnvironment {
	private boolean projectCreated = false;
	private final String  _projectName;
	private IProject		_project;

	
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
		return projectCreated;
	}
	
	/**
	 * Construct the basic web project
	 */
	public void createProject() 
    {
		if(!isProjectCreated()) 
        {
			// first delete the projects of these names, if present
            deleteProject();
			try 
            {
				_project = createWebProject(_projectName);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		
			projectCreated = true;
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
	 * @param projectName
	 * @return the web project
	 * @throws Exception
	 */
	private IProject createWebProject(String projectName) throws Exception {
		if(!isProjectCreated()) {
			IDataModel dataModel = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
			dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
			dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
            dataModel.dispose();
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}

	/**
	 * Clears the projectCreated flag, allowing createProject to be called to reset
	 * the web project back to it's initial state
	 */
	public void setProjectDirtied() {
		projectCreated = false;
	}

	/**
	 * Forces recreation of the test project - to be used after unit tests
	 * that dirty the project state.  
	 */
	public void recreateProject() {
		setProjectDirtied();
		createProject();
	}

	/**
	 * Should call createProject first. 
	 * @return the IProject
	 */
	public IProject getTestProject() 
	{
		if (projectCreated)
		{
			return _project;
		}
		
		return null;
	}

    /**
     * @param create 
     * @param force 
     * @return the web root container for the project
     */
    public IVirtualContainer getWebRoot(boolean create, boolean force)
    {
        IVirtualContainer webRoot =
            ComponentCore.createComponent(getTestProject()).getRootFolder();
        
        if (!webRoot.exists() && create)
        {
            try
            {
                webRoot.create(force ? IVirtualResource.FORCE : 0, new NullProgressMonitor());
            }
            catch (CoreException ce)
            {
                Activator.log("Error creating web root", ce);
                ce.printStackTrace();
            }
        }
       
        return webRoot;
    }
    
       
    /**
     * @param bundle
     * @param srcFileName
     * @param destDirName
     * @param destFileName
     * @return the IResource for the  newly loaded resource
     * @throws IOException 
     * @throws CoreException 
     */
    public IResource loadResourceInWebRoot(Bundle bundle, String srcFileName, String destFileName) throws IOException, CoreException
    {
        final TestFileResource resource = new TestFileResource();
        resource.load(bundle, srcFileName);
        
        IFile file = getWebRoot(true, true).getFile(new Path(destFileName)).getUnderlyingFile();
        file.create(new ByteArrayInputStream(resource.toBytes()), true, null);
        
        return file;
    }
    
//	public void checkAndAddFacesConfig() {
//		final IProject project = getTestProject();
//		IContainer container = ComponentCore.createComponent(project).getRootFolder().getFolder("/WEB-INF").getUnderlyingFolder();
//		IFile facesConfigFile  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config.xml"))); //$NON-NLS-1$
//		IFile facesConfigFile1  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config1.xml"))); //$NON-NLS-1$
//		IFile facesConfigFile2  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config2.xml"))); //$NON-NLS-1$
//		
//		if(facesConfigFile.exists()) {
//			return;
//		} else {
//			// there is no faces-config, so add our starting template
//			try {
//				String sourcePath = Platform.asLocalURL(Platform.find(Platform.getBundle("org.eclipse.jst.jsf.facesconfig.tests"), new Path("/template/faces-config.xml"))).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
//				File f = new File(sourcePath);
//				FileInputStream sourceStream = new FileInputStream(f);
//				facesConfigFile.create(sourceStream, true, null);
//				sourceStream.close();
//
//				String sourcePath1 = Platform.asLocalURL(Platform.find(Platform.getBundle("org.eclipse.jst.jsf.facesconfig.tests"), new Path("/template/faces-config1.xml"))).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
//				File f1 = new File(sourcePath1);
//				FileInputStream sourceStream1 = new FileInputStream(f1);
//				facesConfigFile1.create(sourceStream1, true, null);
//				sourceStream1.close();
//
//				String sourcePath2 = Platform.asLocalURL(Platform.find(Platform.getBundle("org.eclipse.jst.jsf.facesconfig.tests"), new Path("/template/faces-config2.xml"))).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
//				File f2 = new File(sourcePath2);
//				FileInputStream sourceStream2 = new FileInputStream(f2);
//				facesConfigFile2.create(sourceStream2, true, null);
//				sourceStream2.close();
//			} catch (IOException ioe) {
//			} catch (CoreException ce) {
//			}
//
//		}
//		
//	}
}
