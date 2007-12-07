/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.internal.metadata;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.metadata.internal.AbstractMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.meta.ICMRegistry;
import org.eclipse.jst.pagedesigner.meta.internal.SimpleCMRegistry;
import org.osgi.framework.Bundle;
import org.xml.sax.SAXException;

/**
 * Locates Sybase CM config metadata files. 
 * 
 * Supplied to ease migration to JSF Common meta data by allowing current MD into JSF common md format.
 * 
 * @deprecated this will be disappearing along with all of the Sybase meta data 
 * and supporting classes before Ganymede release
 *
 */
public class SybaseCMConfigMetaDataLocator extends AbstractMetaDataLocator {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator#locateMetaDataModelProviders(java.lang.String)
	 */
	public List locateMetaDataModelProviders(String uri) {
//		if (1==1)
//			return null; //temp... turn off translation!
		
		List <ICMRegistry> registries = findCMRegistries(uri);
		if (! registries.isEmpty()) {
			List <IMetaDataSourceModelProvider> providers = new ArrayList<IMetaDataSourceModelProvider>(registries.size());
			for (ICMRegistry reg : registries) {
				if (reg.getSupportedURI().equals(uri)) {
					providers.add(createMetaDataSourceProvider(reg));
				}
			}
			return providers;
		}
		return null;
	}

	private List<ICMRegistry> findCMRegistries(String uri) {
		List<ICMRegistry> contributedRegistries = new ArrayList<ICMRegistry>();
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(PDPlugin.getPluginId(),
						IJMTConstants.EXTENSION_POINT_CMREGISTRY);
		IConfigurationElement[] eles = extensionPoint
				.getConfigurationElements();

		for (int i = 0; i < eles.length; i++) {
			if (eles[i].getName().equals("registry")) {
				String cmUri = eles[i].getAttribute("uri");
				if (cmUri != null && cmUri.equals(uri)) {
					String configFile = eles[i].getAttribute("configFile");
					if (configFile != null && configFile.length() > 0) {
						String bundleName = eles[i].getDeclaringExtension()
								.getContributor().getName();
						try {
							Bundle bundle = Platform.getBundle(bundleName);
							URL cmFileUrl = bundle.getEntry(configFile);
							SimpleCMRegistry reg = new SimpleCMRegistry(uri,
									cmFileUrl);
							contributedRegistries.add(reg);
						} catch (ParserConfigurationException e) {
			                PDPlugin.getLogger(getClass()).error(new Throwable(e));
			            } catch (FactoryConfigurationError e) {
			                PDPlugin.getLogger(getClass()).error(new Throwable(e));                    
			            } catch (SAXException e) {
			                PDPlugin.getLogger(getClass()).error(new Throwable(e));
			            } catch (IOException e) {
			                PDPlugin.getLogger(getClass()).error(new Throwable(e));
			            }
					}
					String className = eles[i].getAttribute("class");
					if (className != null && className.length() > 0) {
						try {
							Object obj = eles[i].createExecutableExtension("class");
							
							if (obj instanceof ICMRegistry) {
								contributedRegistries.add((ICMRegistry)obj);
							}
						} catch (CoreException e) {
						    PDPlugin.getLogger(getClass()).error(new Throwable(e));
						}
					}
				}
			}
		}
		return contributedRegistries;
	}
	
	private IMetaDataSourceModelProvider createMetaDataSourceProvider(ICMRegistry reg) {		
		IMetaDataSourceModelProvider provider = new SybaseCMConfigMetadataProvider(reg);
		provider.setLocator(this);
		return provider;
	}

	/* 
	 * Does nothing.
	 */
	public void startLocating() {
		//do nothing
	}

	/* 
	 * Does nothing.
	 */
	public void stopLocating() {
		//do nothing
	}

	/**
	 *
	 */
	public class SybaseCMConfigMetadataProvider implements IMetaDataSourceModelProvider {
		private IMetaDataLocator _locator;
		private ICMRegistry _reg;
		/**
		 * Construct from ICMRegsistry
		 * @param reg
		 */
		public SybaseCMConfigMetadataProvider(ICMRegistry reg) {
			_reg = reg;
		}

		public Object getSourceModel() {
			return _reg;
		}
		
		public IMetaDataLocator getLocator() {
			return _locator;
		}

		public void setLocator(IMetaDataLocator locator) {
			_locator = locator;
		}
		
		public Object getAdapter(Class adapter) {
			// TODO Auto-generated method stub
			return null;
		}
	}	
}

