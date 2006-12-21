/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.osgi.framework.Bundle;

/**
 * Utility class for launching JSF-related wizard operations. 
 *
 * @author spaxton
 */
public class WizardUtil {
	private static boolean projectCreated = false;
	private static final String FACESCONFIG_PROJECT_NAME = "FacesConfigUnitTest";
	private static final String FACESCONFIG_EAR_PROJECT_NAME = FACESCONFIG_PROJECT_NAME + "EAR";

	public static boolean isProjectCreated() {
		return projectCreated;
	}
	
	public static void createProject(String nameSuffix) {
		if(!isProjectCreated()) {
			// first delete the projects of these names, if present
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IResource oldWebProj = workspace.getRoot().getProject(createProjectName(nameSuffix));
			IResource oldEarProj = workspace.getRoot().getProject(createEarProjectName(nameSuffix));

			try {			
				workspace.delete(new IResource[] { oldWebProj, oldEarProj }, true, null);
			} catch (CoreException ce) {
				ce.printStackTrace();
			}
	
			
			try {
				IProject project = createWebProject(createProjectName(nameSuffix));
				checkAndAddFacesConfig(project);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		
			projectCreated = true;
		}
	}

	protected static IProject createWebProject(String projectName) throws Exception {
		if(!isProjectCreated()) {
			IDataModel dataModel = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
			dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
			dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}

	public static void setProjectDirtied() {
		projectCreated = false;
	}

	/**
	 * Forces recreation of the test project - to be used after unit tests
	 * that dirty the project state.  
	 * @param nameSuffix TODO
	 */
	public static void recreateProject(String nameSuffix) {
		setProjectDirtied();
		createProject(nameSuffix);
	}

	/**
	 * Should call createProject first. 
	 * @return the test project
	 * @param nameSuffix TODO
	 */
	public static IProject getTestProject(String nameSuffix) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		return workspace.getRoot().getProject(createProjectName(nameSuffix));
	}

    private static String createProjectName(final String nameSuffix)
    {
        // TODO: do we need separate projects for each test?
        return FACESCONFIG_PROJECT_NAME;//+"_"+nameSuffix;        
    }
    
    private static String createEarProjectName(final String nameSuffix)
    {
        // TODO: do we need separate projects for each test?
        return FACESCONFIG_EAR_PROJECT_NAME;//+"_"+nameSuffix;
    }
    
	private static void checkAndAddFacesConfig(IProject project) {
		IContainer container = ComponentCore.createComponent(project).getRootFolder().getFolder("/WEB-INF").getUnderlyingFolder();
		IFile facesConfigFile  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config.xml"))); //$NON-NLS-1$
		IFile facesConfigFile1  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config1.xml"))); //$NON-NLS-1$
		IFile facesConfigFile2  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config2.xml"))); //$NON-NLS-1$
		IFile facesConfigFileExtData = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config-ext-data1.xml"))); //$NON-NLS-1$
        

        IFile facesConfig_1_2File = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config_1_2.xml"))); //$NON-NLS-1$ 

        if(facesConfigFile.exists()) {
			return;
		}
        
        final IPath facesConfigPath = new Path("/template/faces-config.xml");
        final IPath facesConfig1Path = new Path("/template/faces-config1.xml");
        final IPath facesConfig2Path = new Path("/template/faces-config2.xml");
        final IPath facesConfigExtData1Path = new Path("/template/faces-config-ext-data1.xml");
        final IPath facesConfig_1_2_Path = new Path("/template/faces-config_1_2.xml");
        
        final Bundle  myBundle = Platform.getBundle("org.eclipse.jst.jsf.facesconfig.tests");
        // there is no faces-config, so add our starting template
        try {
            
            {
            	final String sourcePath = FileLocator.toFileURL(FileLocator.find(myBundle, facesConfigPath,null)).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
            	final File f = new File(sourcePath);
            	final FileInputStream sourceStream = new FileInputStream(f);
            	facesConfigFile.create(sourceStream, true, null);
            	sourceStream.close();
            }
            {
            	final String sourcePath1 = FileLocator.toFileURL(FileLocator.find(myBundle,facesConfig1Path,null)).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
            	final File f1 = new File(sourcePath1);
            	final FileInputStream sourceStream1 = new FileInputStream(f1);
            	facesConfigFile1.create(sourceStream1, true, null);
            	sourceStream1.close();
            }
            {
            	final String sourcePath2 = FileLocator.toFileURL(FileLocator.find(myBundle, facesConfig2Path,null)).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
            	final File f2 = new File(sourcePath2);
            	final FileInputStream sourceStream2 = new FileInputStream(f2);
            	facesConfigFile2.create(sourceStream2, true, null);
            	sourceStream2.close();
            }

            {
                final String sourcePathExtData1 = FileLocator.toFileURL(FileLocator.find(myBundle, facesConfigExtData1Path,null)).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$   
                final File fext = new File(sourcePathExtData1);
                final FileInputStream sourceStreamExt1 = new FileInputStream(fext);
                facesConfigFileExtData.create(sourceStreamExt1, true, null);
                sourceStreamExt1.close();
            }
            
            {
                final String sourcePathFacesConfig_1_2 = 
                    FileLocator.toFileURL(FileLocator.find(myBundle, facesConfig_1_2_Path,null)).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$   
                final File fext = new File(sourcePathFacesConfig_1_2);
                final FileInputStream sourceStreamExt1 = new FileInputStream(fext);
                facesConfig_1_2File.create(sourceStreamExt1, true, null);
                sourceStreamExt1.close();
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        } catch (CoreException ce) {
            ce.printStackTrace(System.err);
        }
	}
}
