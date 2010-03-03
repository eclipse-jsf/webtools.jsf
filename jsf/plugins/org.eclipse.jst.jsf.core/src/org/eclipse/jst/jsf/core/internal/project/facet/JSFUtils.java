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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * 
 */
@SuppressWarnings("deprecation")
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
	 */
	public static final String PP_JSF_IMPLEMENTATION_LIBRARIES = "jsf.implementation.libraries"; //$NON-NLS-1$
	/**
	 * the key for component libraries in persistent properties
	 */
	public static final String PP_JSF_COMPONENT_LIBRARIES = "jsf.component.libraries"; //$NON-NLS-1$
	/**
	 * the key for implementation type in persistent properties
	 */
	public static final String PP_JSF_IMPLEMENTATION_TYPE = "jsf.implementation.type"; //$NON-NLS-1$

	private static final String DEFAULT_DEFAULT_MAPPING_SUFFIX = "jsp"; //$NON-NLS-1$
	
	private final JSFVersion  _version;
    private final IModelProvider _modelProvider;
	
	/**
	 * @param version
	 * @param modelProvider 
	 */
	protected JSFUtils(final JSFVersion version, final IModelProvider modelProvider)
	{
	    _version = version;
	    _modelProvider = modelProvider;
	}
	
	/**
	 * @return the jsf version that this instance is for.
	 */
	public final JSFVersion getVersion()
    {
        return _version;
    }

	/**
	 * @param config
	 * @return servlet display name to use from wizard data model
	 */
	protected final String getDisplayName(IDataModel config) {
		String displayName = config.getStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME);
		if (displayName == null || displayName.trim().length() == 0)
			displayName = JSF_DEFAULT_SERVLET_NAME;
		return displayName.trim();
	}
	
	/**
	 * @param config
	 * @return servlet display name to use from wizard data model
	 */
	protected final String getServletClassname(IDataModel config) {
		String className = config.getStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME);
		if (className == null || className.trim().equals("")) //$NON-NLS-1$
			className = JSF_SERVLET_CLASS;
		return className.trim();
	}

	/**
	 * @return IModelProvider
	 */
	public final IModelProvider getModelProvider() {
		Object webAppObj = _modelProvider.getModelObject();
		if (webAppObj == null)
		{
			return null;
		}
		return _modelProvider;
	}

   /**
     * @param configPath
     */
    public final void createConfigFile(IPath configPath)
    {
        FileOutputStream os = null;
//        final String QUOTE = new String(new char[] { '"' });
        try {
            IPath dirPath = configPath.removeLastSegments(1);
            dirPath.toFile().mkdirs();
            File file = configPath.toFile();
            file.createNewFile();
            os = new FileOutputStream(file);
            printConfigFile(os);
        } catch (FileNotFoundException e) {
            JSFCorePlugin.log(IStatus.ERROR, Messages.JSFUtils_ErrorCreatingConfigFile, e);
        } catch (IOException e) {
            JSFCorePlugin.log(IStatus.ERROR, Messages.JSFUtils_ErrorCreatingConfigFile, e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    JSFCorePlugin.log(IStatus.ERROR, Messages.JSFUtils_ErrorClosingConfigFile, e);
                }
            }
        }
    }
    
    /**
     * @param out
     */
    protected final void printConfigFile(final OutputStream out)
    {
        PrintWriter pw = null;
        try
        {
            pw = new PrintWriter(out);
            doVersionSpecificConfigFile(pw);
        }
        finally
        {
            if (pw != null)
            {
                pw.close();
            }
        }
    }
    
    /**
     * @param pw
     */
    protected abstract void doVersionSpecificConfigFile(final PrintWriter pw);
    
    
    /**
     * @param webAppObj 
     * @return true if this going into a JavaEE (1.5 and later) or a J2EE (1.4 and earlier)
     * configured application.
     */
    public boolean isJavaEE(final Object webAppObj)
    {
      if (webAppObj instanceof org.eclipse.jst.javaee.web.WebApp)
      {
          org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp)webAppObj;
          return WebAppVersionType.VALUES.contains(webApp.getVersion());
      }
      return false;
    }

    /**
     * @param config
     * @return list of URL patterns from the datamodel
     */
    protected final List<String> getServletMappings(final IDataModel config) {
        final List<String> mappings = new ArrayList<String>();
        final String[] patterns = (String[])config.getProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS);
        if (patterns != null)
        {
            for (final String pattern : patterns)
            {
                mappings.add(pattern);
            }
        }
        return mappings;
    }
    /**
     * Does an update of the web application's config file.
     * 
     * @param webApp must be WebApp of the appropriate type.
     * @param config
     * @throws ClassCastException if webApp is not the appropriate type.
     */
    public abstract void updateWebApp(Object webApp, IDataModel config);


    /**
     * Called on a facet uninstall to remove JSF related changes.
     * @param webApp
     */
    public abstract void rollbackWebApp(Object webApp);


    /**
     * @param webAppObj
     * @param resource
     * @param existingURL
     * @return the modified url path for the (possibly) jsf resource.
     */
    public abstract IPath getFileUrlPath(Object webAppObj, IResource resource,
            IPath existingURL);
    
    /**
     * @param fileExtension
     * @return true if the file extension is deemed to be for a JSF.
     */
    protected boolean isValidKnownExtension(String fileExtension) {
        if (fileExtension != null &&
                (   fileExtension.equalsIgnoreCase(DEFAULT_DEFAULT_MAPPING_SUFFIX) ||  
                fileExtension.equalsIgnoreCase("jspx") ||  //$NON-NLS-1$
                fileExtension.equalsIgnoreCase("jsf") || //$NON-NLS-1$
                fileExtension.equalsIgnoreCase("xhtml"))) //$NON-NLS-1$
            return true;

        return false;
    }

    
    /**
     * @param resource
     * @return true if the resource is deemed to be a JSF page.
     */
    protected boolean isJSFPage(IResource resource) {
        // currently always return true.
        // need to find quick way of determining whether this is a JSF JSP Page
        return true;
    }
    
    /**
     * @return the default value for the default mapping suffix
     */
    protected String getDefaultDefaultSuffix()
    {
        return DEFAULT_DEFAULT_MAPPING_SUFFIX;
    }

    /**
     * @param name 
     * @param value 
     * @return the 
     */
    protected final String calculateSuffix(final String name, final String value)
    {
        if (name != null
                && JSF_DEFAULT_SUFFIX_CONTEXT_PARAM.equals(name
                        .trim()))
        {
            return normalizeSuffix(value != null ? value.trim() : null);
        }
        return null;
    }

    /**
     * @param defSuffix
     * @return the suffix value with any leading dot removed
     */
    protected final String normalizeSuffix(String defSuffix)
    {
        if (defSuffix != null && defSuffix.startsWith(".")) //$NON-NLS-1$
        {
            defSuffix = defSuffix.substring(1);
        }
        return defSuffix;
    }

    /**
     * Holds all the obsolete JSF Library stuff.  This will go away post-Helios.
     * @author cbateman
     *
     */
    public static class JSFLibraryHandler
    {
        /**
         * Construct an array that hold paths for all JARs in a JSF library. 
         * 
         * @param jsfLib
         * @param logMissingJar true to log an error for each invalid JAR.
         * @return elements
         */
        public final IPath[] getJARPathforJSFLib(JSFLibrary jsfLib, boolean logMissingJar) {        
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
        
        private int numberofValidJar(EList archiveFiles) {
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
        
        private void logErroronMissingJAR(JSFLibrary jsfLib, ArchiveFile ar) {
            String msg = NLS.bind(Messages.JSFUtils_MissingJAR, 
                            ar.getName(),
                            jsfLib.getLabel());
            JSFCorePlugin.log(IStatus.ERROR, msg);
        }
        
        /**
         * Construct an array that hold paths for all JARs in a JSF library. 
         * However, archive files that no longer exist are filtered out.  
         * 
         * @param jsfLib
         * @param logMissingJar true to log an error for each invalid JAR.
         * @return elements
         */
        public final IPath[] getJARPathforJSFLibwFilterMissingJars(JSFLibrary jsfLib, boolean logMissingJar) {
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

    }
}
