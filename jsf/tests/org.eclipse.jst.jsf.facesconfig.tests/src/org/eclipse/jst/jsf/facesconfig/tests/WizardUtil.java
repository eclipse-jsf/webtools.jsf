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
package org.eclipse.jst.jsf.facesconfig.tests;

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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Utility class for launching JSF-related wizard operations. 
 *
 * @author spaxton
 */
public class WizardUtil {
	private static boolean projectCreated = false;
	public static final String FACESCONFIG_PROJECT_NAME = "FacesConfigUnitTest";
	private static final String FACESCONFIG_EAR_PROJECT_NAME = FACESCONFIG_PROJECT_NAME + "EAR";

	public static boolean isProjectCreated() {
		return projectCreated;
	}
	
	public static void createProject() {
		if(!isProjectCreated()) {
			// first delete the projects of these names, if present
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IResource oldWebProj = workspace.getRoot().getProject(FACESCONFIG_PROJECT_NAME);
			IResource oldEarProj = workspace.getRoot().getProject(FACESCONFIG_EAR_PROJECT_NAME);

			try {			
				workspace.delete(new IResource[] { oldWebProj, oldEarProj }, true, null);
			} catch (CoreException ce) {
				ce.printStackTrace();
			}
	
			
			try {
				IProject project = createWebProject(FACESCONFIG_PROJECT_NAME);
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
	 */
	public static void recreateProject() {
		setProjectDirtied();
		createProject();
	}

	/**
	 * Should call createProject first. 
	 */
	public static IProject getTestProject() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		return workspace.getRoot().getProject(FACESCONFIG_PROJECT_NAME);
	}

	private static void checkAndAddFacesConfig(IProject project) {
		IContainer container = ComponentCore.createComponent(project).getRootFolder().getFolder("/WEB-INF").getUnderlyingFolder();
		IFile facesConfigFile  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config.xml"))); //$NON-NLS-1$
		IFile facesConfigFile1  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config1.xml"))); //$NON-NLS-1$
		IFile facesConfigFile2  = ResourcesPlugin.getWorkspace().getRoot().getFile(container.getFullPath().append(new Path("faces-config2.xml"))); //$NON-NLS-1$
		
		if(facesConfigFile.exists()) {
			return;
		} else {
			// there is no faces-config, so add our starting template
			try {
				String sourcePath = Platform.asLocalURL(Platform.find(Platform.getBundle("org.eclipse.jst.jsf.facesconfig.tests"), new Path("/template/faces-config.xml"))).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
				File f = new File(sourcePath);
				FileInputStream sourceStream = new FileInputStream(f);
				facesConfigFile.create(sourceStream, true, null);
				sourceStream.close();

				String sourcePath1 = Platform.asLocalURL(Platform.find(Platform.getBundle("org.eclipse.jst.jsf.facesconfig.tests"), new Path("/template/faces-config1.xml"))).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
				File f1 = new File(sourcePath1);
				FileInputStream sourceStream1 = new FileInputStream(f1);
				facesConfigFile1.create(sourceStream1, true, null);
				sourceStream1.close();

				String sourcePath2 = Platform.asLocalURL(Platform.find(Platform.getBundle("org.eclipse.jst.jsf.facesconfig.tests"), new Path("/template/faces-config2.xml"))).getPath().toString(); //$NON-NLS-1$ //$NON-NLS-2$	
				File f2 = new File(sourcePath2);
				FileInputStream sourceStream2 = new FileInputStream(f2);
				facesConfigFile2.create(sourceStream2, true, null);
				sourceStream2.close();
			} catch (IOException ioe) {
			} catch (CoreException ce) {
			}

		}
		
	}
}
