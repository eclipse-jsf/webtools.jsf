/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.common.project.facet.JavaProjectFacetCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action;
import org.eclipse.wst.common.project.facet.core.internal.FacetedProjectNature;
import org.osgi.framework.Bundle;

/**
 * Test environment for dynamic web projects
 * @author cbateman
 *
 */
public class WebProjectTestEnvironment extends ProjectTestEnvironment {

    private final IProjectFacetVersion  _javaFacetVersion;
    private final IProjectFacetVersion  _webFacetVersion;
    /**
     * @param projectName
     */
    public WebProjectTestEnvironment(String projectName) {
        this(projectName, JavaFacetUtils.JAVA_50, ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.4"));
    }

    /**
     * @param projectName
     * @param javaVersion
     * @param webVersion
     */
    public WebProjectTestEnvironment(String projectName, IProjectFacetVersion javaVersion, IProjectFacetVersion webVersion)
    {
        super(projectName);
        _javaFacetVersion = javaVersion;
        _webFacetVersion = webVersion;
    }
    
    /**
     * NOTE: behaviour override compared to super.  This method doesn't 
     * delete existing
     * 
     * Create the web project. If this method completes successfully
     * without throwing RuntimeExceptions, this test environment will
     * point to an IProject with Java and Web facets set as configured
     * in the constructor.
     * 
     * NOTE: create behaviour is significantly altered by the ignoreExisting
     * flag
     * 
     * @param ignoreProjectExists -- only has impact if _projectName already
     * exists in the workspace.  In this case, if set to true, then createProject
     * will return without error if the project exists and is properly facted, but
     * throw a RuntimeException if faceting doesn't match what is expected.
     * 
     * If set to false and the project exists, a runtime exception will be thrown
     *
     */
    @Override
    public void createProject(boolean ignoreProjectExists) 
    {
        boolean  doCreate = true;
        
        try 
        {
            _project = ResourcesPlugin.getWorkspace().getRoot().getProject(_projectName);
            
            if (_project.isAccessible())
            {
                if (ignoreProjectExists)
                {
                    if (hasFacets(_project))
                    {
                       doCreate = false;
                    }
                }
                else
                {
                    throw new RuntimeException("Project was not expected to exist but does: "+_project.getName());
                }
            }
            
            if (doCreate)
            {
                 _project = createWebProject(_projectName);
            }
            _projectCreated = true;
        } catch (Exception t) {
            throw new RuntimeException(t);
        }
    }
    
    private boolean hasFacets(IProject project) throws CoreException
    {
        boolean  javaInstalled = false;
        boolean  webInstalled = false;
        
        IFacetedProject facetedProject = ProjectFacetsManager.create(project);
        
        IProjectFacetVersion version = 
            facetedProject.getInstalledVersion(_javaFacetVersion.getProjectFacet());
        
        if (version != null)
        {
            if (version.compareTo(_javaFacetVersion) == 0)
            {
                javaInstalled = true;
            }
            else
            {
                throw new RuntimeException("Wrong Java version already installed. Has: "+version.getVersionString()+" but was expecting: "+_javaFacetVersion.getVersionString());
            }
        }
        
        version = facetedProject.getInstalledVersion(_webFacetVersion.getProjectFacet());
        
        if (version != null)
        {
            if (version.compareTo(_webFacetVersion) == 0)
            {
                webInstalled = true;
            }
            else
            {
                throw new RuntimeException("Wrong web version already installed. Has: "+version.getVersionString()+" but was expecting: "+_javaFacetVersion.getVersionString());
            }
        }
        
        if (javaInstalled && webInstalled)
        {
            return true;
        }
        
        return false;
    }

    /**
     * @param projectName
     * @return the web project
     * @throws Exception
     */
    private IProject createWebProject(String projectName) throws Exception 
    {
        IProject project =  ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

        if(!isProjectCreated()) 
        {
            if (!project.exists())
            {
                project.create(null);
                project.open(null);
                ProjectUtilities.addNatureToProject(project, FacetedProjectNature.NATURE_ID);
            }
            
            Set<Action> actions = new HashSet<Action>();
            IDataModel dataModel = DataModelFactory.createDataModel(new JavaProjectFacetCreationDataModelProvider());
            dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
            actions.add(new IFacetedProject.Action(Action.Type.INSTALL,_javaFacetVersion,null));
//            dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_ACTION_MAP, actions);
//            dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
//            dataModel.dispose();
            
            dataModel = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
            dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
            actions.add(new IFacetedProject.Action(Action.Type.INSTALL,_webFacetVersion,null));

            IFacetedProject facetedProject = ProjectFacetsManager.create(project);
            facetedProject.modify(actions, null);
        }
        
        return project;
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
     * Creates or overwrites destFileName with the contents of srcFileName in bundle
     * 
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
        
        if (!file.exists())
        {            
            file.create(new ByteArrayInputStream(resource.toBytes()), true, null);
        }
        else
        {
            file.setContents(new ByteArrayInputStream(resource.toBytes()), true, true, null);
        }
        
        return file;
    }

}
