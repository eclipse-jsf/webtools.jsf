/*******************************************************************************
 * Copyright (c) 2002, 2006, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Jens Lukowski/Innoopract - initial renaming/restructuring
 * 	 Gerry Kessler/Oracle - code borrowed and repurposed for JSF subproject
 *
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;


/**
 * Registry of standard metadata files
 */
public final class StandardMetaDataFileRegistry {
	private Map/*<String, List<IMetaDataSourceModelProvider>>*/ mdFilesMap 	= new HashMap/*<String, List<IMetaDataSourceModelProvider>>*/(1);
	private List/*<IMetaDataSourceModelProvider>*/ EMPTY_LIST 	= new ArrayList/*<IMetaDataSourceModelProvider>*/(0);

	private static StandardMetaDataFileRegistry reg;
	
	/**
	 * @return the singleton instance of the registry
	 */
	public static StandardMetaDataFileRegistry getInstance() {
		if (reg == null){
			reg = new StandardMetaDataFileRegistry();
		}
		return reg;
	}
	
	private StandardMetaDataFileRegistry() {
		new StandardMetaDataFileRegistryReader(this).readRegistry();
	}

	/**
	 * @param uri as String
	 * @return list of standard metadata sources as <code>IMetaDataSourceModelProvider</code>s.  
	 * Returns empty list of no standard metadata files are registered for the given uri.
	 */
	public synchronized List/*<IMetaDataSourceModelProvider>*/ getStandardMetaDataModelProviders(String uri) {
		List/*<IMetaDataSourceModelProvider>*/ theList = (List)mdFilesMap.get(uri);
		return theList != null ? theList : EMPTY_LIST ;
	}
		
	/**
	 * For use by registry reader only
	 * @param uri
	 * @param fileInfo 
	 */
	public synchronized void addStandardMetaDataFileInfo(String uri, IStandardMetaDataSourceInfo fileInfo) {
		List/*<IMetaDataSourceModelProvider>*/ providers = (List) mdFilesMap.get(uri);
		if (providers == null) {
			providers = new ArrayList/*<IStandardMetaDataSourceInfo>*/();
			mdFilesMap.put(uri, providers);
		}
		providers.add(new StandardMetaDataFilesProvider(fileInfo));
	}	
	
/**
 * Internal implementation of IMetaDataSourceModelProvider for "standard" metadata sources
 *
 */
class StandardMetaDataFilesProvider implements IMetaDataSourceModelProvider {
	
	private org.eclipse.jst.jsf.common.metadata.internal.IStandardMetaDataSourceInfo info;
	private Object model;
	private IMetaDataLocator locator;
	private StandardMetaDataSourceFileLocator fileLocator = null;
	
	/**
	 * Constructor
	 * @param info
	 */
	StandardMetaDataFilesProvider(IStandardMetaDataSourceInfo info){
		this.info = info;
	}
	
	private StandardMetaDataSourceFileLocator getFileLocator(){
		if (fileLocator == null){		
			if (info.getLocatorClassname() == null){
				fileLocator = new PluginRelativeStandardMetaDataSourceFileLocator();
			}	
			else {		
				Class klass = JSFCommonPlugin.loadClass(info.getLocatorClassname(), info.getBundleId());
				try {
					fileLocator = (StandardMetaDataSourceFileLocator)klass.newInstance();
				} catch (InstantiationException e) {
					JSFCommonPlugin.log(IStatus.ERROR, "InstantiationException: StandardMetaDataFilesProvider.getFileLocator()", e); //$NON-NLS-1$
				} catch (IllegalAccessException e) {
					JSFCommonPlugin.log(IStatus.ERROR, "IllegalAccessException: StandardMetaDataFilesProvider.getFileLocator()", e);				 //$NON-NLS-1$
				}			
			}
			if (fileLocator != null)
				fileLocator.setFileInfo(info);
		}
		return fileLocator;
	}
	
	private InputStream getInputStream() throws IOException {				
		if (getFileLocator() != null){
			return	getFileLocator().getInputStream();			
		}
		return null;
		
	}
	
	private URI getMDFileURI(){
		try {
			return URI.createURI(getFileLocator().getURL().toURI().toString());
		} catch (URISyntaxException e) {
			JSFCommonPlugin.log(IStatus.ERROR, "Metadata File Load Error: "+getFileLocator().getFileInfo().toString()+": URISyntaxException: "+e.getMessage());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider#getSourceModel()
	 */
	public Object getSourceModel() {
		if (model != null)
			return model;
		
		InputStream inputStream = null;
		try {
			URI uri = getMDFileURI();
			inputStream = getInputStream();
			if (inputStream != null){
				EList contents = StandardModelFactory.getInstance().loadStandardFileResource(inputStream, this, uri);
				//check to see if this is a Model
				if (contents != null && !contents.isEmpty() &&
						contents.get(0) instanceof Model){				
					model = contents.get(0);
					((Model)model).setSourceModelProvider(this);
				}
			}
		} catch (FileNotFoundException e){
			JSFCommonPlugin.log(IStatus.ERROR, e.getLocalizedMessage());
		} catch (IOException e) {
			JSFCommonPlugin.log(IStatus.ERROR,"IOException(1): StandardMetaDataFilesProvider.getSourceModel():"+getModelName(), e); //$NON-NLS-1$			
		} finally {
			if (inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					JSFCommonPlugin.log( IStatus.ERROR,"IOException (2): StandardMetaDataFilesProvider.getSourceModel():"+getModelName(), e); //$NON-NLS-1$
				}
			}
		}
		return model;
	}

	private String getModelName() {
		return info.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider#getLocator()
	 */
	public IMetaDataLocator getLocator() {
		return locator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider#setLocator(org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator)
	 */
	public void setLocator(IMetaDataLocator locator) {
		this.locator = locator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider#getResourceBundle()
	 */
	private ResourceBundle internalGetResourceBundle() {
		if (getFileLocator() != null){
			try {
				return fileLocator.getResourceBundle();
			} catch (MissingResourceException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "InternalGetResourceBundle1", e);
			} catch (IOException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "InternalGetResourceBundle2", e);			
			}
		}
		return null;
	}

	public Object getAdapter(Class klass) {
		final StandardMetaDataFilesProvider mdp = this;
		if (klass == IImageDescriptorProvider.class){			
			return new IImageDescriptorProvider(){
				String imageBase;
				public ImageDescriptor getImageDescriptor(String imagePath) {
					imagePath = appendImageBase(imagePath);
					String bundleID = mdp.getFileLocator().getFileInfo().getBundleId();
					URL url = FileLocator.find(Platform.getBundle(bundleID), new Path(imagePath), null);
					return ImageDescriptor.createFromURL(url);
				}
				private String appendImageBase(String imagePath) {
					return getImageBase() + imagePath;
				}
				
				private String getImageBase(){
					if (imageBase == null){
						Model aModel = (Model)getSourceModel();
						Trait t = TaglibDomainMetaDataQueryHelper.getTrait(aModel, "images-base-path"); //$NON-NLS-1$
						if (t == null){
							imageBase = "";		 //$NON-NLS-1$
						} else {
							imageBase = TraitValueHelper.getValueAsString(t);
							if (imageBase != null && imageBase.length() > 0){
								imageBase = imageBase +"/"; //$NON-NLS-1$
							}
						}
					}
					return imageBase;
				}
				
			};
		
		} else if (klass == IResourceBundleProvider.class) {
			return new IResourceBundleProvider(){

				public ResourceBundle getResourceBundle() {
					return mdp.internalGetResourceBundle();
				}
				
			};
			
		} else if (klass == IClassLoaderProvider.class){
			return new IClassLoaderProvider(){

				public Class loadClass(String className) {
					String bundleID = mdp.getFileLocator().getFileInfo().getBundleId();
					try {
						return Platform.getBundle(bundleID).loadClass(className);
					} catch (ClassNotFoundException e) {
						return null;
					}
				}
				
			};
		}
		return null;
	}

}
}
