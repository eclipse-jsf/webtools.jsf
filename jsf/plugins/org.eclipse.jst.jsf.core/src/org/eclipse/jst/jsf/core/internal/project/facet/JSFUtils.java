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

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * 
 */
public abstract class JSFUtils {
	/**
	 * The default name for the Faces servlet
	 */
	public static final String JSF_DEFAULT_SERVLET_NAME = "Faces Servlet"; //$NON-NLS-1$
	/**
	 * The default name of the Faces servlet class
	 */
	public static final String JSF_SERVLET_CLASS = "javax.faces.webapp.FacesServlet"; //$NON-NLS-1$
	/**
	 * The name of the context parameter used for JSF configuration files
	 */
	public static final String JSF_CONFIG_CONTEXT_PARAM = "javax.faces.CONFIG_FILES"; //$NON-NLS-1$
	
	/**
	 * The name of the context parameter used for defining the default JSP file extension
	 */
	public static final String JSF_DEFAULT_SUFFIX_CONTEXT_PARAM = "javax.faces.DEFAULT_SUFFIX"; //$NON-NLS-1$
	
	/**
	 * The path to the default application configuration file
	 */
	public static final String JSF_DEFAULT_CONFIG_PATH = "/WEB-INF/faces-config.xml";  //$NON-NLS-1$

	/**
	 * Default URL mapping to faces servlet
	 */
	public static final String JSF_DEFAULT_URL_MAPPING = "/faces/*"; //$NON-NLS-1$

	/**
	 * the key for implementation libraries in persistent properties
     * TODO: should encapsulate the property somewhere and hide the constant
	 */
	public static final String PP_JSF_IMPLEMENTATION_LIBRARIES = "jsf.implementation.libraries"; //$NON-NLS-1$
	/**
	 * the key for component libraries in persistent properties
     * TODO: should encapsulate the property somewhere and hide the constant
	 */
	public static final String PP_JSF_COMPONENT_LIBRARIES = "jsf.component.libraries"; //$NON-NLS-1$
	/**
	 * the key for implementation type in persistent properties
     * TODO: should encapsulate the property somewhere and hide the constant
	 */
	public static final String PP_JSF_IMPLEMENTATION_TYPE = "jsf.implementation.type"; //$NON-NLS-1$
	/**
	 * Construct an array that hold paths for all JARs in a JSF library. 
	 * However, archive files that no longer exist are filtered out.  
	 * 
	 * @param jsfLib
	 * @param logMissingJar true to log an error for each invalid JAR.
	 * @return elements
	 */
	public static IPath[] getJARPathforJSFLibwFilterMissingJars(JSFLibrary jsfLib, boolean logMissingJar) {
		EList archiveFiles = jsfLib.getArchiveFiles();
		int numJars = numberofValidJar(archiveFiles);
		IPath[] elements = new IPath[numJars];
		ArchiveFile ar = null;
		int idxValidJar = 0;
		for (int i= 0; i < archiveFiles.size(); i++) {
			ar = (ArchiveFile)archiveFiles.get(i); 
			if ( !ar.exists() ) {
				if (logMissingJar) {
					logErroronMissingJAR(jsfLib, ar);
				}
			} else {
				elements[idxValidJar] = new Path(((ArchiveFile)archiveFiles.get(i)).getResolvedSourceLocation()).makeAbsolute();
				idxValidJar++;
			}
		}
		return elements;		
	}
	
	/**
	 * Construct an array that hold paths for all JARs in a JSF library. 
	 * 
	 * @param jsfLib
	 * @param logMissingJar true to log an error for each invalid JAR.
	 * @return elements
	 */
	public static IPath[] getJARPathforJSFLib(JSFLibrary jsfLib, boolean logMissingJar) {		
		EList archiveFiles = jsfLib.getArchiveFiles();
		int numJars = archiveFiles.size();
		IPath[] elements = new IPath[numJars];
		ArchiveFile ar = null;
		for (int i= 0; i < numJars; i++) {
			ar = (ArchiveFile)archiveFiles.get(i); 
			if ( !ar.exists() && logMissingJar ) {
				logErroronMissingJAR(jsfLib, ar);
			}
			elements[i] = new Path(((ArchiveFile)archiveFiles.get(i)).getResolvedSourceLocation()).makeAbsolute();
		}
		return elements;
	}	
	
	private static int numberofValidJar(EList archiveFiles) {
		int total = 0;
		final Iterator it = archiveFiles.iterator();
		ArchiveFile ar = null;
		while(it.hasNext()) {
			ar = (ArchiveFile) it.next();
			if (ar.exists()) {
				total++;
			}
		}
		return total;
	}
	
	private static void logErroronMissingJAR(JSFLibrary jsfLib, ArchiveFile ar) {
		String msg = NLS.bind(Messages.JSFUtils_MissingJAR, 
						ar.getName(),
						jsfLib.getLabel());
		JSFCorePlugin.log(IStatus.ERROR, msg);
	}
	

	/**
	 * @param config
	 * @return servlet display name to use from wizard data model
	 */
	protected static String getDisplayName(IDataModel config) {
		String displayName = config.getStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME);
		if (displayName == null || displayName.trim().equals("")) //$NON-NLS-1$
			displayName = JSF_DEFAULT_SERVLET_NAME;
		return displayName.trim();
	}
	
	/**
	 * @param config
	 * @return servlet display name to use from wizard data model
	 */
	protected static String getServletClassname(IDataModel config) {
		String className = config.getStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME);
		if (className == null || className.trim().equals("")) //$NON-NLS-1$
			className = JSF_SERVLET_CLASS;
		return className.trim();
	}
	
	/**
	 * Servlet 2.3_SRV.11.2: a string that begins with a "/" and ends
	 * with "/*" is a prefix mapping
	 * 
	 * @param mapping
	 * @return true if the mapping string represents a prefix mapping
	 */
	public static boolean isPrefixMapping(final String mapping)
	{
	    if (mapping == null || mapping.length() < 4)
	    {
	        return false;
	    }
	    
	    return mapping.charAt(0) == '/' && mapping.endsWith("/*");
	}
	
	/**
	 * Servlet 2.3_SRV.11.2: a string that begins with "*." 
	 * is an extension mapping
     * 
	 * @param mapping
	 * @return true if mapping is an extension mapping
	 */
	public static boolean isExtensionMapping(final String mapping)
	{
	    if (mapping == null)
	    {
	        return false;
	    }
	    
	    return mapping.startsWith("*.");
	}
	
	/**
	 * Search the list of servlet-mappings for the first extension and prefix mappings.  The contents
	 * of mappings is assumed to be all url-pattern's.  
	 * 
	 * If prefExtMapping is not null, it is an extension mapping and
     * it is in mappings, then it is returned.  Otherwise, the first extension
     * mapping in mappings is returned.  Returns null if mappings does not
     * contain an extension mapping.  The same algorithm holds for prefPrefixMapping and
     * corresponding prefix mapping.
     * 
     * See isExtensionMapping and isPrefixMapping for more information on url patterns.
	 * 
	 * @param mappings
	 * @param prefExtMapping
	 * @param prefPrefixMapping
	 * @return the result
	 */
	public static MappingSearchResult searchServletMappings(
			final List<String> mappings, String prefExtMapping,
			String prefPrefixMapping) {
		String firstExtFound = null;
		String firstPrefixFound = null;
		boolean foundExtMapping = false;
		boolean foundPrefixMapping = false;

		// if the caller has no preferredMapping, then
		// set it to something guaranteed to be non-null
		// and which is guaranteed not to match anything
		// that pass isExtensionMapping
		if (prefExtMapping == null) {
			prefExtMapping = "NOTANEXTENSIONMAPPING";
		}

		// similarly, guarantee that if the caller has no
		// preferred prefix mapping, that we set a non-null
		// comp mapping
		if (prefPrefixMapping == null) {
			prefPrefixMapping = "NOTAPREFIXMAPPING";
		}

		SEARCH_LOOP: for (String mapping : mappings) {
			if (isExtensionMapping(mapping)) {
				// can assum that mapping is non-null since
				// it is an ext mapping
				if (prefExtMapping.equals(mapping.trim())) {
					firstExtFound = prefExtMapping;
					continue;
				}

				if (firstExtFound == null) {
					firstExtFound = mapping.trim();
				}
			} else if (isPrefixMapping(mapping)) {
				if (prefPrefixMapping.equals(mapping.trim())) {
					firstPrefixFound = prefPrefixMapping;
					continue;
				}

				if (firstPrefixFound == null) {
					firstPrefixFound = mapping.trim();
				}
			}

			if (foundExtMapping && foundPrefixMapping) {
				break SEARCH_LOOP;
			}
		}

		return new MappingSearchResult(firstExtFound, firstPrefixFound);
	}
	
    /**
	 * The result of a servlet mapping search
	 * 
	 */
	public static class MappingSearchResult {
		private final String _extensionMapping; // may be null;
		private final String _prefixMapping; // may be null

		MappingSearchResult(final String extensionMapping,
				final String prefixMapping) {
			_extensionMapping = extensionMapping;
			_prefixMapping = prefixMapping;
		}

		/**
		 * @return true if the search yielded a valid result
		 */
		public boolean isResult() {
			return _extensionMapping != null || _prefixMapping != null;
		}

		/**
		 * @return the first extension mapping matching search criteria or null
		 * if none
		 */
		public final String getExtensionMapping() {
			return _extensionMapping;
		}

		/**
		 * @return the first prefix mapping matching search criteria or null
		 * if none
		 */
		public final String getPrefixMapping() {
			return _prefixMapping;
		}
    }
}
