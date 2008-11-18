/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * Creates instances of IMetaDataLocators and caches them so that there is only one instance of a particular locator
 * when client requests one. 
 */
public class MetaDataLocatorFactory {
	private static MetaDataLocatorFactory INSTANCE = null;
	
	/**
	 * @return singleton instance of the MetaDataLocatorFactory
	 */
	public synchronized static MetaDataLocatorFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new MetaDataLocatorFactory();
		}
		return INSTANCE;
	}

	private HashMap _locators;

	private Map getLocators() {
		if (_locators == null){
			_locators = new HashMap();
		}
		return _locators;
	}
	
	/**
	 * @param locatorClassName
	 * @param bundleId
	 * @return IMetaDataLocator
	 */
	public IMetaDataLocator getLocator(String locatorClassName, String bundleId){
		String key = getKey(locatorClassName, bundleId);
		IMetaDataLocator locator = (IMetaDataLocator)getLocators().get(key);
		if (locator == null){
			Class klass = JSFCommonPlugin.loadClass(locatorClassName, bundleId);
			try {
				locator = (IMetaDataLocator)klass.newInstance();
				if (locator != null) {
					getLocators().put(key, locator);
					locator.startLocating();
				}
			} catch (InstantiationException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "Could not instantiate IMetaDataLocator: "+key, e); //$NON-NLS-1$
			} catch (IllegalAccessException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "IllegalAccessException while creating IMetaDataLocator: "+key, e); //$NON-NLS-1$
			}
		}
		return locator;
	}

	private String getKey(String locatorClassName, String bundleId) {
		StringBuffer buf = new StringBuffer(bundleId);
		buf.append(":"); //$NON-NLS-1$
		buf.append(locatorClassName);
		return buf.toString();
	}
	
	/**
	 * Stops and disposes all locators
	 */
	public void dispose(){
		for (Iterator it=getLocators().values().iterator();it.hasNext();){
			IMetaDataLocator locator = (IMetaDataLocator)it.next();
			locator.stopLocating();			
		}
		getLocators().clear();
	}
}
