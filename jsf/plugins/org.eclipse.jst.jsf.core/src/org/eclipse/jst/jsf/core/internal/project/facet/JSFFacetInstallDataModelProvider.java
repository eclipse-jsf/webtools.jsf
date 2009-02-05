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
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action;

/**
 * Provides a data model used by the JSF facet install.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFFacetInstallDataModelProvider extends
		FacetInstallDataModelProvider implements
		IJSFFacetInstallDataModelProperties {

	private String 	errorMessage;
	
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(IMPLEMENTATION_TYPE_PROPERTY_NAME);
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
		if (propertyName.equals(IMPLEMENTATION_TYPE_PROPERTY_NAME)){
			return IMPLEMENTATION_TYPE.UNKNOWN;
		}
		else if (propertyName.equals(IMPLEMENTATION)) {
			if (JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry() == null)
				return null;
			return getDefaultImplementationLibrary();//JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();
		} else if (propertyName.equals(DEPLOY_IMPLEMENTATION)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(CONFIG_PATH)) {
			return JSFUtils.JSF_DEFAULT_CONFIG_PATH; 
		} else if (propertyName.equals(SERVLET_NAME)) {
			return JSFUtils.JSF_DEFAULT_SERVLET_NAME;
		} else if (propertyName.equals(SERVLET_CLASSNAME)) {
			return JSFUtils.JSF_SERVLET_CLASS;	
		} else if (propertyName.equals(SERVLET_URL_PATTERNS)) {
			return new String[] {JSFUtils.JSF_DEFAULT_URL_MAPPING };
		} else if (propertyName.equals(FACET_ID)) {
			return IJSFCoreConstants.JSF_CORE_FACET_ID;
		} else if (propertyName.equals(WEBCONTENT_DIR)){
			return "WebContent";  //not sure I need this //$NON-NLS-1$
		} else if (propertyName.equals(COMPONENT_LIBRARIES)) {
			return new JSFLibraryInternalReference[0];
		} else if (propertyName.equals(IMPLEMENTATION_LIBRARIES)) {
			return getDefaultJSFImplementationLibraries();
		} else if (propertyName.equals(DEFAULT_IMPLEMENTATION_LIBRARY)) {
			return getDefaultImplementationLibrary();
		}
		return super.getDefaultProperty(propertyName);
	}
	
	public IStatus validate(String name) {
		errorMessage = null;
		if (name.equals(IMPLEMENTATION_TYPE_PROPERTY_NAME)) {
			if (getProperty(IMPLEMENTATION_TYPE_PROPERTY_NAME) == IMPLEMENTATION_TYPE.UNKNOWN) {
				return createErrorStatus(Messages.JSFFacetInstallDataModelProvider_INITIAL_VALIDATION_IMPL_TYPE);
			}
		}
		else if (name.equals(IMPLEMENTATION)) {
			if (getProperty(IMPLEMENTATION_TYPE_PROPERTY_NAME) == IMPLEMENTATION_TYPE.USER_SPECIFIED) {
				JSFLibraryInternalReference lib = (JSFLibraryInternalReference)getProperty(IMPLEMENTATION);
				IStatus status = validateImpl(lib.getLibrary());
				if (!OK_STATUS.equals(status))
					return status;
					
	            return validateClasspath();
			}
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

		if (passedPath.getDevice() != null) {
			errorMessage = NLS.bind(
					Messages.JSFFacetInstallDataModelProvider_ValidateConfigFileRelative1,
					getWebContentFolderName());
			return createErrorStatus(errorMessage);
		}
		IPath webContentFolder = getWebContentFolder();
		IPath setPath = webContentFolder.append(passedPath);
		if (!getWebContentFolder().isPrefixOf(setPath)) {
			errorMessage = NLS.bind(
					Messages.JSFFacetInstallDataModelProvider_ValidateConfigFileRelative2,
					getWebContentFolderName());
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
				}
			} catch (JavaModelException e) {
			    // FIXME: what should we do in this case?
			    JSFCorePlugin.log(e, "Error searching class path"); //$NON-NLS-1$
			}			
		}
		//else as we do not have a javaProject yet, all we can do is validate that there is no duplicate jars (absolute path)
		
		IStatus status = null;
		
		JSFLibraryInternalReference ref = null;
		if (getProperty(IMPLEMENTATION_TYPE_PROPERTY_NAME) == IMPLEMENTATION_TYPE.USER_SPECIFIED) {
			ref = ((JSFLibraryInternalReference)getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION));
			if (ref != null){
				status = checkForDupeArchiveFiles(jars, ((JSFLibraryInternalReference)getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION)).getLibrary());
				if (!OK_STATUS.equals(status)){
					return status;
				}
			} else {
				return createErrorStatus(Messages.JSFFacetInstallDataModelProvider_ClientImplValidationMsg);
			}
		}

		JSFLibraryInternalReference[] compLibs = (JSFLibraryInternalReference[]) getProperty(IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES);
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
		if (projName == null || "".equals(projName))
			return null;
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return project;
	}
	
	private boolean doesProjectExist() {
		IProject project = getProject();
		return (project != null) && project.exists();
	}

	private IStatus checkForDupeArchiveFiles(Set jars,
			JSFLibrary aJSFLib) {
		if (aJSFLib == null)
			return OK_STATUS;
		
		for (Iterator it=aJSFLib.getArchiveFiles().iterator();it.hasNext();){
			ArchiveFile jar = (ArchiveFile)it.next();
			if (jars.contains(jar.getResolvedSourceLocation())){
				return createErrorStatus(NLS.bind(Messages.JSFFacetInstallDataModelProvider_DupeJarValidation,jar.getResolvedSourceLocation()));				
			}
            jars.add(jar.getResolvedSourceLocation());
		}
		return OK_STATUS;
	}
	
	private IPath getProjectPath() {		
		IProject project = getProject();
		if (project == null)
			return null;
		else if (project.exists())
			return project.getLocation();
		
		String projName = (String)getProperty(FACET_PROJECT_NAME);
		IFacetedProjectWorkingCopy projModel = (IFacetedProjectWorkingCopy)getProperty(FACETED_PROJECT_WORKING_COPY );
		
		if (projModel.getProjectLocation() != null)
			return projModel.getProjectLocation().append(projName);

		return ResourcesPlugin.getWorkspace().getRoot().getRawLocation().append(projName);
		
	}

	private IPath getWebContentFolder() {
		//One can get here 2 ways:
		//if web app exists and user is adding a facet, or
		// if creating a new web app.

		IPath webContentPath = null;
		String projName = model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME);
		IProject proj = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projName);
		
		String webFolder = getWebContentFolderName();
		if (proj.exists()) {
			
			webContentPath = ComponentCore.createComponent(proj).getRootFolder()
						.getUnderlyingFolder().getRawLocation();
		}
		else {			

			if (webFolder == null){
				//we got problems... should not happen
				return proj.getFullPath();
			}
			webContentPath = proj.getFullPath().append(webFolder);

		}
		return webContentPath;
	}
	
	private String getWebContentFolderName() {
		String projName = (String)getProperty(FACET_PROJECT_NAME);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		if (project.exists()){
			IPath webContentPath = ComponentCore.createComponent(project).getRootFolder()
			.getUnderlyingFolder().getProjectRelativePath();

			return webContentPath.toString();
		}
		
		IFacetedProjectWorkingCopy projWC = (IFacetedProjectWorkingCopy)getProperty(FACETED_PROJECT_WORKING_COPY);
		Set<Action> pfas = projWC.getProjectFacetActions();
		for (Action action : pfas){
			if (action.getProjectFacetVersion().getProjectFacet().getId().equals("jst.web")){
				IDataModel webFacet = (IDataModel) action.getConfig();
				return webFacet.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER );
			}
		}
		
		//should not get here.   
		return null;
	}

	private List getDefaultJSFImplementationLibraries() {
		List list = new ArrayList();
		if (JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry() != null) {
			JSFLibrary jsfLib = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getDefaultImplementation();
			if (jsfLib != null){
				JSFLibraryInternalReference prjJSFLib = new JSFLibraryInternalReference(jsfLib, true, true);
				list.add(prjJSFLib);
			}
		}
		return list;
	}	
	
	private JSFLibraryInternalReference getDefaultImplementationLibrary() {		
		if (JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry() != null) {
			JSFLibrary jsfLib = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getDefaultImplementation();
			return new JSFLibraryInternalReference(jsfLib, true, true);	
		}
		return null;	
	}	
	
}
