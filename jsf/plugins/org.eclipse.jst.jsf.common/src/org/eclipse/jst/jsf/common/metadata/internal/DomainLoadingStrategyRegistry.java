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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;


/**
 * Registry of strategies used to load domains of metadata.
 * Uses the <code>org.eclipse.jst.jsf.common.domainLoadingStrategies</code> ext-pt to load.  
 * 
 * see <code>org.eclipse.jst.jsf.common.domainLoadingStrategies</code> ext-pt 
 */
public class DomainLoadingStrategyRegistry{
	private static DomainLoadingStrategyRegistry INSTANCE;
	
	private HashMap <String, DomainLoadingStrategyDescriptorImpl> domainLoadingStrategyDescriptors;
	
	private static final String EXTENSION_POINT_ID = "domainLoadingStrategies"; //$NON-NLS-1$

	private DomainLoadingStrategyRegistry(){
		init();
	}
	
	/**
	 * @return singleton instance of the DomainLoadingStrategyRegistry
	 */
	public synchronized static DomainLoadingStrategyRegistry getInstance() {
		if (INSTANCE == null){
			INSTANCE = new DomainLoadingStrategyRegistry();
		}
		return INSTANCE;
	}
	
	/**
	 * Loads registry with descriptors from the domainLoadingStrategies ext-pt.    
	 */
	synchronized final void  init(){
		final IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		final IExtensionPoint point = extensionRegistry.getExtensionPoint(JSFCommonPlugin.PLUGIN_ID, EXTENSION_POINT_ID );
		if (point != null) {
			IConfigurationElement[] elements = point.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				IConfigurationElement element = elements[i];
				DomainLoadingStrategyDescriptorImpl dls = new DomainLoadingStrategyDescriptorImpl(element);
				addDomainLoadingStrategyDescriptor(dls);
			}
		}
	}

	/**
	 * Add domain loading strategy descriptor for a domain to the registry domains
	 * @param strategy
	 */
	protected void addDomainLoadingStrategyDescriptor(final DomainLoadingStrategyDescriptorImpl strategy){
		if (getDescriptors().containsKey(strategy.getDomain())) {
			if (!strategy.getBundleId().equals(JSFCommonPlugin.PLUGIN_ID)) {
				getDescriptors().put(strategy.getDomain(), strategy);
			}
		} else {
			getDescriptors().put(strategy.getDomain(), strategy);
		}
	}

	/**
	 * @param domain
	 * @return an instance of an <code>IDomainLoadingStrategy</code> for the given domain
	 */
	public IDomainLoadingStrategy getLoadingStrategy(final String domain){
		final DomainLoadingStrategyDescriptorImpl strategy = getDescriptors().get(domain);
		if (strategy == null){
			return createDefaultLoadingStrategy();
		}
        return createLoadingStrategy(domain);
	}

	
	/**
	 * @return strategy that will only use standard metadata files
	 */
	private IDomainLoadingStrategy createDefaultLoadingStrategy() {
		return new DomainLoadingStrategy(null);
	}

	private IDomainLoadingStrategy createLoadingStrategy(final String domain){
		return getDescriptors().get(domain).newInstance();			
	}
	
	private Map <String, DomainLoadingStrategyDescriptorImpl> getDescriptors(){
		if (domainLoadingStrategyDescriptors == null){
			domainLoadingStrategyDescriptors = new HashMap<String, DomainLoadingStrategyDescriptorImpl>();			
		}
		return domainLoadingStrategyDescriptors;
	}

	/**
	 * Implementation of a DomainLoadingStrategy descriptor that is responsible for creating instances of the IDomainLoadingStrategy
	 */
	private static class DomainLoadingStrategyDescriptorImpl {
		String domain;
		String loadingStrategyClassName;
		String bundleId;
		Class strategy;
		IConfigurationElement element;
		
		DomainLoadingStrategyDescriptorImpl(IConfigurationElement element){
			this.element = element;
			this.init();
		}

		private void init() {
			domain = element.getAttribute("domainId"); //$NON-NLS-1$
			bundleId = element.getContributor().getName();
			loadingStrategyClassName = element.getAttribute("domainLoadingStrategy");	//$NON-NLS-1$
		}

		/**
		 * @return domain id
		 */
		public String getDomain() {	
			return domain;
		}

		/**
		 * @return bundle ID
		 */
		public String getBundleId() {
			return bundleId;
		}

		/**
		 * @return new instance of IDomainLoadingStrategy
		 */
		public IDomainLoadingStrategy newInstance(){
			try {
				Class[] parameterTypes = new Class[]{String.class};
				Object[] initargs = new Object[]{domain};
				Object loader = this.getLoadingStrategy().getConstructor(parameterTypes).newInstance(initargs);
				if (loader instanceof IDomainLoadingStrategy)
					return (IDomainLoadingStrategy)loader;
			} catch (InstantiationException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "Unable to instantiate IDomainLoadingStrategy for: "+ domain,e); //$NON-NLS-1$
			} catch (IllegalAccessException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "IllegalAccessException during creation of IDomainLoadingStrategy for: "+ domain,e); //$NON-NLS-1$
			} catch (IllegalArgumentException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "IllegalArgumentException during creation of IDomainLoadingStrategy for: "+ domain,e); //$NON-NLS-1$
			} catch (SecurityException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "SecurityException during creation of IDomainLoadingStrategy for: "+ domain,e); //$NON-NLS-1$
			} catch (InvocationTargetException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "InvocationTargetException during creation of IDomainLoadingStrategy for: "+ domain,e); //$NON-NLS-1$
			} catch (NoSuchMethodException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "NoSuchMethodException during creation of IDomainLoadingStrategy for: "+ domain,e); //$NON-NLS-1$
			}
			return null;
		}
		
		private Class getLoadingStrategy() {		
			if (strategy == null){
				strategy = JSFCommonPlugin.loadClass(loadingStrategyClassName, bundleId);
			}
			return strategy;
		}
		
	}

}