/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsflibraryregistry.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryregistry.PluginProvidedJSFLibraryArchiveFilesDelegate;
import org.eclipse.osgi.util.NLS;

/**
 * Helper class used to create plugin-rovided JSF Libraries from the
 * <code>org.eclipse.jst.jsf.core.pluginProvidedJsfLibraries</code> extension-point.
 * <br>
 * 
 * @author Gerry Kessler - Oracle
 * @deprecated
 */
public final class PluginProvidedJSFLibraryCreationHelper2 {
	private PluginProvidedJSFLibrary newLib;
	private IConfigurationElement config_element;
	private String relativeDestLocation = "WEB-INF/lib"; //$NON-NLS-1$
	
	/**
	 * Key of the 'name' attribute of the extension point.
	 */
	public final static String NAME 		= "name"; //$NON-NLS-1$
	/**
	 * Key of the 'isImplementation' attribute of the extension point.
	 */
	public final static String IS_IMPL 		= "isImplementation"; //$NON-NLS-1$
	/**
	 * Key of the 'maxVersionSupported' attribute of the extension point.
	 */
	public final static String VERSION 		= "maxVersionSupported"; //$NON-NLS-1$
	/**
	 * Key of the 'archiveFilesDelegate' attribute of the extension point.
	 */
	public final static String DELEGATE 	= "archiveFilesDelegate"; //$NON-NLS-1$
	/**
	 * Key of the 'label' attribute of the extension point.
	 */
	public final static String LABEL 		= "label"; //$NON-NLS-1$
	
	/**
	 * Creates an instance with the specified IConfigurationElement instance.
	 * 
	 * @param jsfLibrary IConfigurationElement instance
	 */
	public PluginProvidedJSFLibraryCreationHelper2 (IConfigurationElement jsfLibrary){
		this.config_element = jsfLibrary;
	}
	
	/**
	 * Add a jar file to the library
	 * @param pluginRootRelativePath
	 */
	public void addArchiveFile(String pluginRootRelativePath) {
		ArchiveFile jar = createArchiveFile(pluginRootRelativePath);
		if (!newLib.containsArchiveFile(jar.getSourceLocation()))
			newLib.getArchiveFiles().add(jar);
	}
	
	/**
	 * Creates a new PluginProvidedJSFLibrary from the JSFLibrary extension point.
	 * 
	 * @return PluginProvidedJSFLibrary instance.
	 */
	public JSFLibrary create(){
		newLib = JSFLibraryRegistryFactory.eINSTANCE.createPluginProvidedJSFLibrary();
		newLib.setPluginID(getPluginID());
		newLib.setName(config_element.getAttribute(NAME));
		String label = config_element.getAttribute(LABEL);
		if (label != null && label.length() > 0){
			newLib.setLabel(label);
		}
		newLib.setImplementation(config_element.getAttribute(IS_IMPL).equals("true") ? true : false); //$NON-NLS-1$		
		newLib.setJSFVersion(JSFVersion.getJSFVersion(config_element.getAttribute(VERSION)));
		
		try {
			addArchives();			
			return newLib;
		} catch (Exception e) {
			JSFCorePlugin.log(
					e,
					NLS.bind(
							Messages.PluginProvidedJSFLibraryCreationHelper_ErrorCreating,
							newLib.getName()));
		}
		return null;
	}

	/**
	 * Adds ArchiveFile instances to the specified JSFLibrary instance.
	 * 
	 * @param newLib JSFLibrary instance
	 * @throws CoreException on core failure.
	 */
	private void addArchives() throws CoreException {
		PluginProvidedJSFLibraryArchiveFilesDelegate jarCol = null;

		jarCol = (PluginProvidedJSFLibraryArchiveFilesDelegate)config_element.createExecutableExtension(DELEGATE);
		if (jarCol != null){
			jarCol.setCreationHelper(this);
			jarCol.getArchiveFiles();
		}
	}
	/**
	 * Returns ArchiveFile where the location is set relative to the plugin.   
	 * As long as the ArchiveFile is on the local machine somewhere, it should
	 * be locatable.
	 *  
	 * @param relativePathFileName Relative location of the ArchiveFile
	 * @return ArchiveFile instance.
	 */
	private ArchiveFile createArchiveFile(String pluginRootRelativePath){
		ArchiveFile file = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
		file.setRelativeToWorkspace(false);
		file.setSourceLocation(pluginRootRelativePath);
		file.setRelativeDestLocation(relativeDestLocation);
		return file;
	}

	/**
	 * Returns the plugin's ID.
	 * 
	 * @return The plugin's ID
	 */
	private String getPluginID() {
		return config_element.getDeclaringExtension().getContributor().getName();
	}

}