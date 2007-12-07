/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.meta.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.meta.ICMRegistry;
import org.eclipse.jst.pagedesigner.meta.IElementDescriptor;
import org.osgi.framework.Bundle;
import org.xml.sax.SAXException;

/**
 * XXX: temp implementation. In the future, will need add more things to allow
 * other plugins to contribute things.
 * 
 * @author mengbo
 * @deprecated
 */
public class CMRegistry implements ICMRegistry {
	static Logger _log = PDPlugin.getLogger(CMRegistry.class);

	private Map _htmlMap = new HashMap();

	private Map _jspMap = new HashMap();

	private List _contributedRegistries = new ArrayList();

	private static CMRegistry _instance = null;

	/**
	 * @return the singleton instance
	 */
	public static CMRegistry getInstance() {
		if (_instance == null) {
			_instance = new CMRegistry();
		}
		return _instance;
	}

	/**
	 * 
	 */
	private CMRegistry() {
		ProgressMonitorDialog progress = new ProgressMonitorDialog(null);
		try {
			progress.run(true, false, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException {
					try {
						monitor
								.beginTask(
										PDPlugin
												.getResourceString("CMRegistry.ReadConfigration"),
										IProgressMonitor.UNKNOWN);
						monitor
								.subTask(PDPlugin
										.getResourceString("CMRegistry.HTMLConfigration"));
						loadCM("configs/cm/html.xml", _htmlMap);
						monitor
								.subTask(PDPlugin
										.getResourceString("CMRegistry.JSPConfigration"));
						loadCM("configs/cm/jsp.xml", _jspMap);
						monitor
								.subTask(PDPlugin
										.getResourceString("CMRegistry.OtherConfigration"));
						readExtensions();
					} finally {
						monitor.done();
					}
				}
			});
		} catch (InvocationTargetException e) {
			// ignore
		} catch (InterruptedException e) {
			// ignore
		}
	}

	/**
	 * 
	 */
	private void readExtensions() {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(PDPlugin.getPluginId(),
						IJMTConstants.EXTENSION_POINT_CMREGISTRY);
		IConfigurationElement[] eles = extensionPoint
				.getConfigurationElements();

		for (int i = 0; i < eles.length; i++) {
			if (eles[i].getName().equals("registry")) {
				String uri = eles[i].getAttribute("uri");
				if (uri == null || uri.length() == 0) {
					// no uri, skip
					continue;
				}
				String configFile = eles[i].getAttribute("configFile");
				if (configFile != null && configFile.length() > 0) {
					String bundleName = eles[i].getDeclaringExtension()
							.getContributor().getName();
					try {
						Bundle bundle = Platform.getBundle(bundleName);
						URL cmFileUrl = bundle.getEntry(configFile);
						SimpleCMRegistry reg = new SimpleCMRegistry(uri,
								cmFileUrl);
						_contributedRegistries.add(reg);
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
							_contributedRegistries.add(obj);
						}
					} catch (CoreException e) {
					    PDPlugin.getLogger(getClass()).error(new Throwable(e));
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.ICMRegistry#getSupportedURI()
	 */
	public String getSupportedURI() {
		return null;
	}

	private void loadCM(String fileName, Map map) {
		try {
			URL url = PDPlugin.getDefault().getBundle().getEntry(fileName);
			ElementDescReader reader = new ElementDescReader(url);
			reader.readElements(map);
		} catch (Exception e) {
			_log.error("Error loading " + fileName + ": " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.ICMRegistry#getElementDescriptor(java.lang.String,
	 *      java.lang.String)
	 */
	public IElementDescriptor getElementDescriptor(String uri, String tagname) {
		if (uri == null) {
			uri = ITLDConstants.URI_HTML;
		}
		if (ITLDConstants.URI_HTML.equals(uri)) {
			IElementDescriptor desc = getHTMLElementDescriptor(tagname);
			if (desc != null) {
				return desc;
			}
		}
		if (ITLDConstants.URI_JSP.equals(uri)) {
			return getJSPElementDescriptor(tagname);
		}
		for (int i = 0, size = _contributedRegistries.size(); i < size; i++) {
			ICMRegistry reg = (ICMRegistry) _contributedRegistries.get(i);
			if (uri.equals(reg.getSupportedURI())) {
				IElementDescriptor ret = reg.getElementDescriptor(uri, tagname);
				if (ret != null) {
					return ret;
				}
			}
		}

		return null;
	}

	private IElementDescriptor getHTMLElementDescriptor(String tagname) {
		return (IElementDescriptor) _htmlMap.get(tagname.toLowerCase());
	}

	private IElementDescriptor getJSPElementDescriptor(String tagname) {
		return (IElementDescriptor) _jspMap.get(tagname.toLowerCase());
	}
	
	public List<ICMRegistry> getRegistries() {
		List<ICMRegistry> ret = new ArrayList<ICMRegistry>(_contributedRegistries);
		ret.add(this);
		return ret;
	}
	
	/**
	 * call to free up memory used
	 */
	public void unloadRegistry(){
		_instance = null;
	}
}
