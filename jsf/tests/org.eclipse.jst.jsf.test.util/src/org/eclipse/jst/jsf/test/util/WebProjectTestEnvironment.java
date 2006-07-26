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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
 * Test environment for dynamic web projects
 * @author cbateman
 *
 */
public class WebProjectTestEnvironment extends ProjectTestEnvironment {

    /**
     * @param projectName
     */
    public WebProjectTestEnvironment(String projectName) {
        super(projectName);
    }

    
    public void createProject() 
    {
        try 
        {
            _project = createWebProject(_projectName);
            _projectCreated = true;
        } catch (Throwable t) {
            t.printStackTrace();
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
