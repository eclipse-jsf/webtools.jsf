/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.ClassNotFoundException;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;
import org.eclipse.emf.ecore.xmi.IllegalValueException;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.util.MetadataResourceImpl;

/**
 * Singleton that produces and loads standard metadata models.  
 * All models are loaded into the same ResourceSet.
 * <p>
 * All metadata extension models must be registered with org.eclipse.emf.ecore.generated_package extension-point.
 * No other mechanism is provided for model uri resolution.
 * <p>
 * Debug tracing for model loading is available: <code>org.eclipse.jst.jsf.common/debug/metadataload=true</code>
 * <p>
 * When the /debug/metadataload trace flag is set, and in case extension models are known not to be available, 
 * and metadata is referencing those models, error logging can be suppressed by launching with the following properties set:<br>
 * &nbsp;&nbsp;&nbsp;metadata.package.ignores<br>
 * &nbsp;&nbsp;&nbsp;metadata.classname.ignores
 *  <p>
 *  eg. Usage for when WPE is not present<p>
 *  <code>
 	-Dmetadata.package.ignores=http://org.eclipse.jsf.pagedesigner/dtinfo.ecore,<br>http://org.eclipse.jsf.pagedesigner/QuickEditTabSections.ecore<br>
 	-Dmetadata.classname.ignores=DTInfo,QuickEditTabSections<br>
 *  </code>
 * <p>
 * see {@link Model}
 */
public class StandardModelFactory {
	private static StandardModelFactory INSTANCE;
	static boolean DEBUG_MD_LOAD = false;
	static boolean DEBUG_MD_GET = false;
	private ExtendedMetaData extendedMetaData;
	private ResourceSet resourceSet;

	
	/**
	 * @return singleton instance of the metadata model factory
	 */
	public synchronized static StandardModelFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new StandardModelFactory();
			INSTANCE.init();	
			
			if (JSFCommonPlugin.getPlugin().isDebugging()){
				DEBUG_MD_LOAD = Boolean.valueOf(Platform.getDebugOption(JSFCommonPlugin.PLUGIN_ID+"/debug/metadataload")).booleanValue();//$NON-NLS-1$
				DEBUG_MD_GET  = Boolean.valueOf(Platform.getDebugOption(JSFCommonPlugin.PLUGIN_ID+"/debug/metadataget")).booleanValue();//$NON-NLS-1$
			}
		}
		return INSTANCE;
	}
	
	private void init() {
		resourceSet = new ResourceSetImpl();
		
	    extendedMetaData = new BasicExtendedMetaData(resourceSet.getPackageRegistry());
		
		// Register the appropriate resource factory to handle all file extensions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
			 new XMLResourceFactoryImpl());
		
		//relying on the org.eclipse.emf.ecore.generated_package ext-pt to register traits
	}

	private StandardModelFactory() {		
		super();
	}
	
//	/**
//	 * Factory method that probably belongs somewhere else!
//	 * @param key
//	 * @param strategy
//	 * @return an empty MetaDataModel
//	 * @deprecated
//	 */
//	public MetaDataModel createModel(ModelKeyDescriptor key, IDomainLoadingStrategy strategy){
//		return new MetaDataModel(key, strategy);
//	}
	
	/**
	 * @param context
	 * @param strategy
	 * @return MetaDataModel
	 */
	public MetaDataModel createModel(final IMetaDataModelContext context, final IDomainLoadingStrategy strategy) {
		return new MetaDataModel(context, strategy);
	}
//
//	/**
//	 * Factory method that probably belongs somewhere else!
//	 * @param modelContext 
//	 * @return a ModelKeyDescriptor for the context
//	 * @deprecated
//	 */
//	public ModelKeyDescriptor createModelKeyDescriptor(final ITaglibDomainMetaDataModelContext modelContext) {
//		return new ModelKeyDescriptor(modelContext.getProject(), modelContext.getDomainID(), modelContext.getURI());
//	}
	
	/**
	 * @param inputStream
	 * @param provider
	 * @param uri 
	 * @return the root of the standard model from the resource as an EList
	 * @throws IOException
	 */
	public EList loadStandardFileResource(final InputStream inputStream,
            final IMetaDataSourceModelProvider provider,
            final org.eclipse.emf.common.util.URI uri) throws IOException
    {
        final XMLResource res = new MetadataResourceImpl(provider);
        
        debug(String.format(
                ">>> Loading standard meta-data file for uri %s", uri), DEBUG_MD_LOAD); //$NON-NLS-1$
        
        res.setURI(uri);
        resourceSet.getResources().add(res);
        setLoadOptions(res);
        res.load(inputStream, null);
        if (DEBUG_MD_LOAD)
        {
            reportErrors(res);
        }
        final EList root = res.getContents();
        return root;
    }

	private void reportErrors(Resource res) {
		EList<Resource.Diagnostic> errs = res.getErrors();
		if (! errs.isEmpty()){
			for (Iterator<Resource.Diagnostic> it= errs.iterator();it.hasNext();){
				StandardModelErrorMessageFactory.logErrorMessage(it.next());
			}
		}
	}
	
	/**
	 * Sets default load options for the resource
	 * @param resource 
	 */
	protected void setLoadOptions(XMLResource resource) {
		Map options = resource.getDefaultLoadOptions();
//		options.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, true);
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		options.put(XMLResource.OPTION_RESOURCE_HANDLER, resource);
		options.put(XMLResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.FALSE);//turning this off so that res.getErrors() has values to check!  bizarre that I should need to do this.
//		options.put(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE, Boolean.TRUE);
		
//		if (DEBUG_MD_LOAD)
//		{
//		    System.out.println("Using load options: "+options);
//		}
	}


	static class StandardModelErrorMessageFactory {
		private static List<String> _missingPackageURIs;
		private static List<String> _missingClassnames;
		
		/**
		 * Simply logs all messages against JSFCommonPlugin, for now.
		 * @param diagnostic
		 */
		public static void logErrorMessage(Resource.Diagnostic diagnostic) {	
			//should be XMIException
			if (diagnostic instanceof XMIException) {
				XMIException ex = (XMIException)diagnostic;				
				String msg = createMessage(ex);
				if (msg != null)
					JSFCommonPlugin.log(IStatus.ERROR, msg);
			}
			else {
				JSFCommonPlugin.log(IStatus.ERROR, diagnostic.toString());//do better???
			}
		}

		private static String createMessage(XMIException ex) {
			
			StringBuffer buf = new StringBuffer("Metadata Load Error: ") //$NON-NLS-1$
				.append(ex.getClass().getSimpleName()).append(": "); //$NON-NLS-1$
			
			if (ex instanceof PackageNotFoundException) {				
				if (shouldIgnore(ex)) 
					return null;
				
				buf.append(((PackageNotFoundException)ex).uri());			
			} 
			else if (ex instanceof ClassNotFoundException) {
				if (shouldIgnore(ex)) 
					return null;
				
				buf.append(((ClassNotFoundException)ex).getName());
			}
			else if (ex instanceof FeatureNotFoundException)
				buf.append(((FeatureNotFoundException)ex).getName());
			else if (ex instanceof IllegalValueException)
				buf.append(((IllegalValueException)ex).getValue().toString());
			else if (ex instanceof UnresolvedReferenceException)
				buf.append(((UnresolvedReferenceException)ex).getReference());	
			else
				buf.append(ex.getMessage());
			
			buf.append(" in ").append(ex.getLocation()).append(": Line = ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(ex.getLine()).append(": Column = ").append(ex.getColumn()); //$NON-NLS-1$
			return buf.toString();
		}

		private static boolean shouldIgnore(XMIException ex) {
			if (ex instanceof PackageNotFoundException) {
				String uri = ((PackageNotFoundException)ex).uri();
				return getMissingPackageURIs().contains(uri);
			}
			else if (ex instanceof ClassNotFoundException) {
				String className = ((ClassNotFoundException)ex).getName();
				return getMissingClassnames().contains(className);
			}
			return false;
		}

		private static List<String> getMissingPackageURIs() {
			if (_missingPackageURIs == null) {
				_missingPackageURIs = buildList("metadata.package.ignores"); //$NON-NLS-1$

			}
			return _missingPackageURIs;
		}
		
		private static List<String> getMissingClassnames() {
			if (_missingClassnames == null) {
				_missingClassnames = buildList("metadata.classname.ignores"); //$NON-NLS-1$
			}
			return _missingClassnames;
		}

		private static List<String> buildList(String propertyName) {
			List<String> ret = new ArrayList<String>();
			String ignoreSet = System.getProperty(propertyName);
			if (ignoreSet == null )//try env
				ignoreSet = System.getenv(propertyName);
			
			if (ignoreSet != null && !(ignoreSet.equals(""))){ //$NON-NLS-1$
				StringTokenizer st = new StringTokenizer(ignoreSet, ","); //$NON-NLS-1$
				while(st.hasMoreTokens()){
					String uri = st.nextToken();
					if (!(uri.equals(""))) //$NON-NLS-1$
							ret.add(uri);
				}
			}

			return ret;
		}
		 
	}
	
	/**
	 * Debug output.  The parenthesis shows thread id.
	 * @param msg
	 * @param debugFlag
	 */
	public static void debug(String msg, boolean debugFlag) {
		if (debugFlag)
			System.out.println(msg + "["+Thread.currentThread().getId()+"]"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	
}
