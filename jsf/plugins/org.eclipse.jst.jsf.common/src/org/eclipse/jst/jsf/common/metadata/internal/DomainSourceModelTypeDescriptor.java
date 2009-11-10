/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * Implementation of a {@link IDomainSourceModelType} descriptor.   
 * Responsible for producing instances of {@link IDomainSourceModelType}.  
 * Also responsible for creating the {@link IMetaDataTranslator} descriptors from the 
 * <code>com.eclipse.jst.jsf.common.domainSourceModelTypeTranslators</code> ext-pt
 */
public class DomainSourceModelTypeDescriptor {
	private static final String TRANSLATORS_EXTENSION_POINT_ID = "domainSourceModelTypeTranslators";
	private static final String STANDARD_FILE_NULL_TRANSLATOR = "org.eclipse.jst.jsf.common.metadata.internal.StandardMetaDataFilesTranslator";
	private String domain = "DEFAULT";
	private String domainSourceModelTypeId;
	private String locatorClassName = "org.eclipse.jst.jsf.common.metadata.internal.StandardMetaDataLocator";
	private Set translatorDescriptors;
	private String bundleId = JSFCommonPlugin.PLUGIN_ID;
	private int ordinal;
	private IDomainSourceModelType _instance;
	
	/**
	 * Constructor
	 * @param domain
	 * @param domainSourceModelTypeId
	 * @param locatorClassName
	 * @param bundleId
	 * @param ordinal
	 */
	public DomainSourceModelTypeDescriptor(String domain, String domainSourceModelTypeId, String locatorClassName, String bundleId, int ordinal){
		this.domain = domain;
		this.locatorClassName = locatorClassName;
		this.domainSourceModelTypeId = domainSourceModelTypeId;
		this.bundleId = bundleId;
		this.ordinal = ordinal;
		init();
	}

	/**
	 * Default model type descriptor that will load only standard metadata files
	 */
	public DomainSourceModelTypeDescriptor(){
//		getTranslatorDescriptors();
		//createTranslatorInstances() will add the standard null translator 
	}
	
	private synchronized void init() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint point = extensionRegistry.getExtensionPoint(JSFCommonPlugin.PLUGIN_ID, TRANSLATORS_EXTENSION_POINT_ID );
		if (point != null) {
			IConfigurationElement[] elements = point.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				IConfigurationElement element = elements[i];
				String srcHdlrId = element.getAttribute("domainSourceModelTypeId");
				if (srcHdlrId.equals(domainSourceModelTypeId))
					addTranslatorDescriptor(element);
			}
		}
	}
	
	private void addTranslatorDescriptor(IConfigurationElement element) {
		String translator = element.getAttribute("translatorClass");
		DomainSourceModelTranslatorDescriptor d = new DomainSourceModelTranslatorDescriptor(translator, element.getContributor().getName());
		getTranslatorDescriptors().add(d);
	}

	private Set getTranslatorDescriptors(){
		if (translatorDescriptors == null){
			translatorDescriptors = new HashSet();
		}
		return translatorDescriptors;
	}

	/**
	 * @return domain
	 */
	public String getDomain() { 
		return domain;
	}
	 
	/**
	 * @return singleton instance of the {@link IDomainSourceModelType}
	 */
	public IDomainSourceModelType getInstance(){
		if (_instance == null){
			_instance = newInstance();
		}
		return _instance;
	}
	/**
	 * @return new instance of {@link IDomainSourceModelType} 
	 */
	private IDomainSourceModelType newInstance(){		

		return new DomainSourceModelTypeImpl();
	}
	
	/**
	 * Internal class implementing {@link IDomainSourceModelType}
	 */
	class DomainSourceModelTypeImpl implements IDomainSourceModelType{

		private Set translators;
		private IMetaDataLocator locator;

		DomainSourceModelTypeImpl(){
            // restrict construction to package scope
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jst.jsf.common.metadata.internal.IDomainSourceModelType#getDomain()
		 */
		public String getDomain() {
			return domain;
		}
		
		/**
		 * @return value of ordinal defined by the ext-pt used for ordering source types for a domain
		 */
		public int getOrdinal(){
			return ordinal;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jst.jsf.common.metadata.internal.IDomainSourceModelType#getLocator()
		 */
		public IMetaDataLocator getLocator() {
			if (locator == null){
				locator = MetaDataLocatorFactory.getInstance().getLocator(locatorClassName, bundleId);
			}
								
			return locator;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jst.jsf.common.metadata.internal.IDomainSourceModelType#getTranslators()
		 */
		public Set getTranslators() {
			if (translators == null){				
				translators = createTranslatorInstances();
			}
			return translators;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString(){
			StringBuffer buf = new StringBuffer("DomainSourceModelTypeImpl");
			buf.append("(domain = ");
			buf.append(getDomain());
			buf.append(", locator = ");
			buf.append(getLocator());
			buf.append(")");
			return buf.toString();
		}
		
		private Set createTranslatorInstances() {
			translators = new HashSet/*<IMetaDataTranslator>*/();
			if (getTranslatorDescriptors().size() == 0){// for TagLibDomain, we are adding null translator via extension (as of 7/16/07)
				//would get here if a domain and source type was defined without a domain translator.  Should not happen, but 
				//add Null Translator for now....
				//we could/should raise exception.  
				//Developers should add a STANDARD_FILE_NULL_TRANSLATOR if using standard metadata format.
				Class klass = JSFCommonPlugin.loadClass(STANDARD_FILE_NULL_TRANSLATOR, JSFCommonPlugin.PLUGIN_ID);
				try {
					translators.add(klass.newInstance());
					return translators;
				} catch (InstantiationException e) {
                    // TODO: other error handling?
					JSFCommonPlugin.log(e, "Error in createTranslatorInstances(STANDARD_FILE_NULL_TRANSLATOR)");
				} catch (IllegalAccessException e) {
                    // TODO: other error handling?
                    JSFCommonPlugin.log(e, "Error in createTranslatorInstances(STANDARD_FILE_NULL_TRANSLATOR)");
				}

			}
			
			Iterator/*<DomainSourceModelTranslatorDescriptor>*/it = getTranslatorDescriptors().iterator();
			while (it.hasNext()){
				DomainSourceModelTranslatorDescriptor d = (DomainSourceModelTranslatorDescriptor)it.next();
				Class klass = JSFCommonPlugin.loadClass(d.getTranslator(), d.getBundleId());
				try {
					translators.add(klass.newInstance());
				} catch (InstantiationException e) {
                    // TODO: other error handling?
                    JSFCommonPlugin.log(e, "Error in createTranslatorInstances");
				} catch (IllegalAccessException e) {
                    // TODO: other error handling?
                    JSFCommonPlugin.log(e, "Error in createTranslatorInstances");
				}
			}
			return translators;
		}

	}
	
	/**
	 * Internal class implementing a descriptor for DomainSourceModelTranslators
	 */
	static class DomainSourceModelTranslatorDescriptor {

		private String _translator;
		private String _bundleId;

		/**
		 * Constructor
		 * @param translator
		 * @param bundleId
		 */
		public DomainSourceModelTranslatorDescriptor(String translator, String bundleId) {
			this._translator = translator;
			this._bundleId = bundleId;
		}
		
		String getTranslator(){
			return _translator;
		}
		
		String getBundleId(){
			return _bundleId;
		}
		
	}

}
