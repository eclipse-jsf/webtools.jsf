/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.tests.util;

import java.io.File;
import java.io.FilenameFilter;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;

public class JSFCoreUtilHelper {

	public static JSFLibrary constructJSFLib(
			String id, 
			String name,			
			String[] archivefiles, 
			boolean bImpl) {
		
		ArchiveFile archiveFile = null;	
		String testData;		
		String pathTestFiles = TestsPlugin.getInstallLocation().getPath() + "testfiles/";
		JSFLibrary jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
		jsfLib.setID(id);
		jsfLib.setName(name);	
		jsfLib.setImplementation(bImpl);
		
		for (int i = 0; i < archivefiles.length; i++) {
			testData = pathTestFiles + archivefiles[i];						
			archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
			archiveFile.setRelativeToWorkspace(false);
			archiveFile.setSourceLocation(testData);			
			archiveFile.setJSFLibrary(jsfLib);			
		}
		
		return jsfLib;
	}
	
	/**
	 * Create a Dynamic Web application with given name using default operation.
	 * If project with given name already exists, then it returns that project.
	 *   
	 * @param aProjectName Project name.
	 * @return IProject instance.
	 * @throws Exception on error.
	 */
	public static IProject createWebProject(String aProjectName) throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (!project.exists()) {
			IDataModel dataModel = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
			dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, aProjectName);
			dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}
	
	/**
	 * If the JSF Library registry is empty, it will seed it with an Impl and non-Impl library
	 */
	public static void createJSFLibraryRegistry(){
		if (JSFCorePlugin.getDefault().getJSFLibraryRegistry().getAllJSFLibraries().size() < 2){
			//create Impl 
			JSFLibrary lib = constructJSFLib("AN-IMPL-LIB", "/testfiles/JSFLib/", true, false);
			JSFCorePlugin.getDefault().getJSFLibraryRegistry().addJSFLibrary(lib);
			//create non-Impl.   Uses same jars but declares it to be non implementation
			lib = constructJSFLib("A-NON-IMPL-LIB", "/testfiles/JSFLib/", false, false);
			JSFCorePlugin.getDefault().getJSFLibraryRegistry().addJSFLibrary(lib);
			//create plugin impl
			lib = constructJSFLib("AN-IMPL-LIB", "/testfiles/JSFLib/", true, true);
			JSFCorePlugin.getDefault().getJSFLibraryRegistry().addJSFLibrary(lib);
			//create plugin-non impl
			
		}
	}

	/**
	 * Convenience method to get new JSFLibraryRegistry instance from factory.
	 * Note that the returned instance is not associated with a resource and so
	 * cannot be loaded or saved unless a resource is created and the returned
	 * instance is set in the resource contents.
	 * 
	 * @return JSFLibraryRegistry instance.
	 */
	public static JSFLibraryRegistry getNewJSFLibraryRegistry() {
		return JSFLibraryRegistryFactory.eINSTANCE.createJSFLibraryRegistry();
	}

	/**
	 * Convenience method to get JSFLibraryRegistry instance from JSFCorePlugin
	 * (which will cause the JSFCorePlugin.loadJSFLibraryRegistry() and
	 * JSFCorePlugin.loadJSFLibraryExtensions() methods to be called and will
	 * subsequently allow JSFCorePlugin.saveJSFLibraryRegistry() to be called,
	 * if desired).
	 * 
	 * @return JSFLibraryRegistry instance from JSFCorePlugin.
	 */
	public static JSFLibraryRegistry getJSFLibraryRegistryFromJSFCorePlugin() {
		return JSFCorePlugin.getDefault().getJSFLibraryRegistry(); 
	}
	
	/**
	 * Creates a JSF Library from all the jars and zips found at the relative path from this plugin
	 * 
	 * @param name
	 * @param relPathToArchives
	 * @param isImpl
	 * @return JSFLibrary
	 */
	public static JSFLibrary constructJSFLib(
			String name,			
			String relPathToArchives, 
			boolean isImpl,
			boolean isFakedPlugginLib) {		
		
		JSFLibrary jsfLib = null;
		if (isFakedPlugginLib){
			jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createPluginProvidedJSFLibrary();
			jsfLib.setID("fakePluginLib_"+name);
		}
		else
			jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
		
		jsfLib.setName(name);	
		jsfLib.setImplementation(isImpl);
		
		File pathTestFiles = new File(TestsPlugin.getInstallLocation().getPath() 
								+ File.separator + relPathToArchives);
		FilenameFilter jars = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if (name.length() >=5){
					String lastChars = name.toLowerCase().substring(name.length() - 4);
					if (lastChars.equals(".jar") || lastChars.equals(".zip"))
						return true;
				}
				return false;
			}			
		};
		
		String[] fileNames = pathTestFiles.list(jars);
		for(int i=0;i < fileNames.length ;i++){
			String fileName = pathTestFiles.getAbsolutePath().concat(File.separator).concat(fileNames[i]);
			ArchiveFile archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
			archiveFile.setRelativeToWorkspace(false);
			archiveFile.setSourceLocation(fileName);			
			archiveFile.setJSFLibrary(jsfLib);		
		}

		return jsfLib;
	}

	/**
	 * Creates a JSF Library from all the jars and zips found at the relative
	 * path from this plugin.
	 * 
	 * @param ID
	 * @param name
	 * @param relPathToArchives
	 * @param isImpl
	 * @return JSFLibrary
	 */
	public static JSFLibrary constructJSFLib(
			String ID,
			String name,			
			String relPathToArchives, 
			boolean isImpl,
			boolean isFakedPlugginLib) {

		JSFLibrary jsfLib = constructJSFLib(name, relPathToArchives, isImpl, isFakedPlugginLib);
		jsfLib.setID(ID);
		return jsfLib;
	}

}
