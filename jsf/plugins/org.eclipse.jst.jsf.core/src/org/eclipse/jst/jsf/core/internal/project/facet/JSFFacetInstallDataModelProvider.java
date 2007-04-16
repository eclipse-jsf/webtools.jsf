/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IProjectCreationPropertiesNew;

/**
 * Provides a data model used by the JSF facet install.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFFacetInstallDataModelProvider extends
		FacetInstallDataModelProvider implements
		IJSFFacetInstallDataModelProperties {

	private String 	errorMessage;
	private IPath 	webContentPath;
	
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(IMPLEMENTATION);
		names.add(DEPLOY_IMPLEMENTATION);
		names.add(CONFIG_PATH);
		names.add(SERVLET_NAME);
		names.add(SERVLET_CLASSNAME);
		names.add(SERVLET_URL_PATTERNS);
		names.add(WEBCONTENT_DIR);
		
		names.add(IMPLEMENTATION_LIBRARIES);
		names.add(COMPONENT_LIBRARIES);
		names.add(DEFAULT_IMPLEMENTATION_LIBRARY);
		
		return names;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(IMPLEMENTATION)) {
			if (JSFCorePlugin.getDefault().getJSFLibraryRegistry() == null)
				return null;
			return getDefaultImplementationLibrary();//JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();
		} else if (propertyName.equals(DEPLOY_IMPLEMENTATION)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(CONFIG_PATH)) {
			return JSFUtils.JSF_DEFAULT_CONFIG_PATH; //$NON-NLS-1$;
		} else if (propertyName.equals(SERVLET_NAME)) {
			return JSFUtils.JSF_DEFAULT_SERVLET_NAME; //$NON-NLS-1$
		} else if (propertyName.equals(SERVLET_CLASSNAME)) {
			return JSFUtils.JSF_SERVLET_CLASS;	
		} else if (propertyName.equals(SERVLET_URL_PATTERNS)) {
			return new String[] { "*.faces" }; //$NON-NLS-1$
		} else if (propertyName.equals(FACET_ID)) {
			return IJSFCoreConstants.JSF_CORE_FACET_ID;
		} else if (propertyName.equals(WEBCONTENT_DIR)){
			return "WebContent";  //not sure I need this
		} else if (propertyName.equals(COMPONENT_LIBRARIES)) {
			return new JSFLibraryReference[0];
		} else if (propertyName.equals(IMPLEMENTATION_LIBRARIES)) {
			return getDefaultJSFImplementationLibraries();
		} else if (propertyName.equals(DEFAULT_IMPLEMENTATION_LIBRARY)) {
			return getDefaultImplementationLibrary();
		}
		return super.getDefaultProperty(propertyName);
	}
	public IStatus validate(String name) {
		errorMessage = null;
		if (name.equals(IMPLEMENTATION)) {//&& getBooleanProperty(DEPLOY_IMPLEMENTATION)
			List libs = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getJSFLibrariesByName(getStringPropertyWithDefaultStripped(IMPLEMENTATION_LIBRARIES));
			JSFLibrary lib = ! libs.isEmpty() ? (JSFLibrary)libs.get(0) : null; 
			IStatus status = validateImpl(lib);
			if (!OK_STATUS.equals(status))
			{
				return status;
			}
            return validateClasspath();
		} else if (name.equals(CONFIG_PATH)) {
			return validateConfigLocation(getStringProperty(CONFIG_PATH));
		} else if (name.equals(SERVLET_NAME)) {			
			return validateServletName(getStringProperty(SERVLET_NAME));
		}
		else if (name.equals(COMPONENT_LIBRARIES)) {
			return validateClasspath();
		}
		return super.validate(name);
	}
	
	private IStatus createErrorStatus(String msg) {		
		return new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, msg);
	}
	
	private String getStringPropertyWithDefaultStripped(String propertyName) {
		String val = getStringProperty(propertyName);
		if (val.endsWith(JSFLibraryRegistry.DEFAULT_IMPL_LABEL)){
			val = val.substring(0, val.indexOf(JSFLibraryRegistry.DEFAULT_IMPL_LABEL)).trim();
			setProperty(propertyName, val);
		}		
		return val;
	}

	private IStatus validateServletName(String servletName) {
		if (servletName == null || servletName.trim().length() == 0) {
			errorMessage = Messages.JSFFacetInstallDataModelProvider_ValidateServletName;
			return createErrorStatus(errorMessage);				
		}
		
		return OK_STATUS;
	}

	private IStatus validateImpl(JSFLibrary impl) {
		if (impl == null) {
			errorMessage = Messages.JSFFacetInstallDataModelProvider_ValidateJSFImpl; 
		}
		if (errorMessage != null) {
			return createErrorStatus(errorMessage);
		}
		return OK_STATUS;
	}
	
	private IStatus validateConfigLocation(String text) {
		if (text == null || text.trim().equals("")) { //$NON-NLS-1$
			errorMessage = Messages.JSFFacetInstallDataModelProvider_ValidateConfigFileEmpty;
			return createErrorStatus(errorMessage);
		}
		text = text.trim();
		
		if (getProjectPath() == null) //this circumstance occurs on page init
			return OK_STATUS;
		
		IPath fullPath = getProjectPath().append(text);
		IPath passedPath = new Path(text);
		if (!fullPath.isValidPath(text)){
			errorMessage = Messages.JSFFacetInstallDataModelProvider_ValidateConfigFilePath;
			return createErrorStatus(errorMessage);
		}
		
		//FIXME:  check for valid file path also [passedPath.toFile().isFile()] 
		if (text.toLowerCase().lastIndexOf(".xml") != text.length() - 4) { //$NON-NLS-1$
			errorMessage = Messages.JSFFacetInstallDataModelProvider_ValidateConfigFileXML;
			return createErrorStatus(errorMessage);
		}
		
		if (text.lastIndexOf("\\") >= 0){ //$NON-NLS-1$
			errorMessage = Messages.JSFFacetInstallDataModelProvider_ValidateConfigFileSlashes;
			return createErrorStatus(errorMessage);
		} 
//		if (1 == 1){
//			//FIXME!!!!
//			//until i can figure out how to get WebContent dir from the WebApp config,
//			//skip validation here
//			return OK_STATUS;
//		}
		if (passedPath.getDevice() != null) {
			errorMessage = NLS.bind(
					Messages.JSFFacetInstallDataModelProvider_ValidateConfigFileRelative1,
					getWebContentFolder().removeFirstSegments(getWebContentFolder().segmentCount() - 1).toString());
			return createErrorStatus(errorMessage);
		}

		IPath setPath = getWebContentFolder().append(passedPath);
		if (!getWebContentFolder().isPrefixOf(setPath)) {
			errorMessage = NLS.bind(
					Messages.JSFFacetInstallDataModelProvider_ValidateConfigFileRelative2,
					getWebContentFolder().removeFirstSegments(getWebContentFolder().segmentCount() - 1).toString());
			return createErrorStatus(errorMessage);
		}

		return OK_STATUS;
	}
	
	private IStatus validateClasspath(){
		Set jars = new HashSet();
		if (doesProjectExist()){
			//validate actual classpath by loading jars from cp
			try {
				IClasspathEntry[] entries = getJavaProject().getResolvedClasspath(true);
				for (int i=0;i<entries.length;i++){
					IClasspathEntry entry = entries[i];
					if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY){
						jars.add(entry.getPath().makeAbsolute().toString());
					}
//					else if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER){
//						IClasspathContainer cont = (IClasspathContainer)entry;
//						for (int j=0;j<cont.getClasspathEntries().length;j++){
//							jars.add(cont.getClasspathEntries()[j].getPath().makeAbsolute().toString());
//						}
//					}
				}
			} catch (JavaModelException e) {
			    // FIXME: what should we do in this case?
			    JSFCorePlugin.log(e, "Error searching class path");
			}			
		}
		else {//as we do not have a javaProject yet, all we can do is validate that there is no duplicate jars (absolute path)
	
		}
		
		IStatus status = null;
		JSFLibraryReference ref =  ((JSFLibraryReference)getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION));
		if (ref != null){
			status = checkForDupeArchiveFiles(jars, ((JSFLibraryReference)getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION)).getLibrary());
			if (!OK_STATUS.equals(status)){
				return status;
			}
		} else {
			return createErrorStatus("JSF Implementation library must be specified.");
		}
		
		JSFLibraryReference[] compLibs = (JSFLibraryReference[]) getProperty(IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES);
		if (compLibs != null){
			for (int i=0;i<compLibs.length;i++){
				JSFLibrary lib = compLibs[i].getLibrary();
				 status = checkForDupeArchiveFiles(jars, lib);
					if (!OK_STATUS.equals(status)){
						return status;
					}
			}		
		}
		return OK_STATUS;
	}

	private IJavaProject getJavaProject() {
		IProject proj = getProject();
		if (proj != null)
			return JavaCore.create(proj); 
		return null;
	}

	private IProject getProject(){
		String projName = (String)getProperty(FACET_PROJECT_NAME);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return project;
	}
	private boolean doesProjectExist() {
		IProject project = getProject();
		return (project != null) && project.exists();
	}

	private IStatus checkForDupeArchiveFiles(Set jars,
			JSFLibrary selectedJSFLibImplementation) {
		if (selectedJSFLibImplementation == null)
			return OK_STATUS;
		
		for (Iterator it=selectedJSFLibImplementation.getArchiveFiles().iterator();it.hasNext();){
			ArchiveFile jar = (ArchiveFile)it.next();
			if (jars.contains(jar.getResolvedSourceLocation())){
				return createErrorStatus("Duplicated jar on classpath: "+jar.getResolvedSourceLocation());				
			}
            jars.add(jar.getSourceLocation());
		}
		return OK_STATUS;
	}
	
	private IPath getProjectPath() {
		String projName = (String)getProperty(FACET_PROJECT_NAME);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		if (project.exists()){
			return project.getLocation();
		} 
		
		IDataModel projModel = (IDataModel)getProperty(MASTER_PROJECT_DM);
		if (projModel.getBooleanProperty(IProjectCreationPropertiesNew.USE_DEFAULT_LOCATION)){
			return new Path(projModel.getStringProperty(IProjectCreationPropertiesNew.PROJECT_LOCATION)).append(projName);
		}
		
		return new Path(projModel.getStringProperty(IProjectCreationPropertiesNew.USER_DEFINED_LOCATION)).append(projName);
	}

	private IPath getWebContentFolder() {
		//One can get here 2 ways:
		//if web app exists and user is adding a facet, or
		// if creating a new web app.
		if (webContentPath != null)
			return webContentPath;
		
		WebArtifactEdit webApp = null;
		try {
			String projName = model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME);
			IProject proj = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projName);
			webApp = JSFUtils.getWebArtifactEditForRead(proj);
			if (webApp != null) {
				webContentPath = JSFUtils.getWebArtifactEditForRead(proj)
					.getDeploymentDescriptorPath().removeLastSegments(2);
				return webContentPath;
			}
			webContentPath = new Path(getStringProperty(WEBCONTENT_DIR));
			return webContentPath;
		} finally {
			if (webApp != null) {
				webApp.dispose();
			}
		}

	}
	
	private List getDefaultJSFImplementationLibraries() {
		List list = new ArrayList();
		if (JSFCorePlugin.getDefault().getJSFLibraryRegistry() != null) {
			JSFLibrary jsfLib = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();
			if (jsfLib != null){
				JSFLibraryReference prjJSFLib = new JSFLibraryReference(jsfLib, true, true);
				list.add(prjJSFLib);
			}
		}
		return list;
	}	
	
	private JSFLibraryReference getDefaultImplementationLibrary() {		
		if (JSFCorePlugin.getDefault().getJSFLibraryRegistry() != null) {
			JSFLibrary jsfLib = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();
			return new JSFLibraryReference(jsfLib, true, true);	
		}
		return null;	
	}	
	
}
