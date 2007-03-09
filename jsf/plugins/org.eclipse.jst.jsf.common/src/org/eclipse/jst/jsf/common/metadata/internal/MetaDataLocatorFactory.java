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
	
	public IMetaDataLocator getLocator(String locatorClassName, String bundleId){
		String key = getKey(locatorClassName, bundleId);
		IMetaDataLocator locator = (IMetaDataLocator)getLocators().get(key);
		if (locator == null){
			Class klass = JSFCommonPlugin.loadClass(locatorClassName, bundleId);
			try {
				locator = (IMetaDataLocator)klass.newInstance();
				if (locator != null)
					getLocators().put(key, locator);
			} catch (InstantiationException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "Could not instantiate IMetaDataLocator: "+key, e);
			} catch (IllegalAccessException e) {
				JSFCommonPlugin.log(IStatus.ERROR, "IllegalAccessException while creating IMetaDataLocator: "+key, e);
			}
		}
		return locator;
	}

	private String getKey(String locatorClassName, String bundleId) {
		StringBuffer buf = new StringBuffer(bundleId);
		buf.append(":");
		buf.append(locatorClassName);
		return buf.toString();
	}
	
	public void dispose(){
		for (Iterator it=getLocators().values().iterator();it.hasNext();){
			IMetaDataLocator locator = (IMetaDataLocator)it.next();
			locator.stopLocating();			
		}
		getLocators().clear();
	}
}
