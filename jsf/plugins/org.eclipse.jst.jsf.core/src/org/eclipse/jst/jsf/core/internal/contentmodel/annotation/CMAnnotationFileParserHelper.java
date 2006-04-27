/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.core.internal.contentmodel.annotation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.provisional.contentmodel.annotation.CMAnnotationSourceFileLocator;
import org.eclipse.jst.jsf.core.internal.provisional.contentmodel.annotation.ICMAnnotationFileParser;
import org.osgi.framework.Bundle;

public class CMAnnotationFileParserHelper {
	private CMAnnotationFileInfo fileInfo;
	private static final String DEFAULT_PARSER_NAME = "org.eclipse.jst.jsf.core.internal.contentmodel.annotation.CMAnnotationFileParser";
	private static final String DEFAULT_LOCATOR_NAME = "org.eclipse.jst.jsf.core.internal.contentmodel.annotation.CMPluginRelativeSourceFileLocator";
	
	public CMAnnotationFileParserHelper(CMAnnotationFileInfo fileInfo) {
		super();
		this.fileInfo = fileInfo;
	}
	
	public void parse(CMAnnotationMap map){
		ICMAnnotationFileParser parser = getParser();
		ICMAnnotationAdvisor advisor = new CMAnnotationAdvisor(map, fileInfo);
		CMAnnotationSourceFileLocator locator = getLocator();
		map.setLocator(locator);
		try {
			parser.parse(advisor, locator);
		} catch (Exception e) {
			JSFCorePlugin.log(e, "Unable to parse: " + fileInfo.getAnnotationFileLocation() + " with " + fileInfo.getAnnotationFileLocatorClassname());
		}
	}

	private ICMAnnotationFileParser getParser() {
		String parserClassName = fileInfo.getParserClassName();
		Object obj = null;
		if (parserClassName != null && !parserClassName.equals("")){
			obj = getObjectForClassname(fileInfo.getBundleId(), parserClassName);
		}
		else {//return default			
			obj = getObjectForClassname(JSFCorePlugin.PLUGIN_ID, DEFAULT_PARSER_NAME );
		}
		if (obj != null && obj instanceof ICMAnnotationFileParser)
			return (ICMAnnotationFileParser)obj;
		
		return null;
	}
	
	private CMAnnotationSourceFileLocator getLocator() {
		String locatorClassName = fileInfo.getAnnotationFileLocatorClassname();
		Object obj = null;
		if (locatorClassName != null && !locatorClassName.equals("")){
			obj = getObjectForClassname(fileInfo.getBundleId(), locatorClassName);
		}
		else {//return default			
			obj = getObjectForClassname(JSFCorePlugin.PLUGIN_ID, DEFAULT_LOCATOR_NAME);
		}
		if (obj != null && obj instanceof CMAnnotationSourceFileLocator){
			CMAnnotationSourceFileLocator locator = (CMAnnotationSourceFileLocator)obj;
			((CMAnnotationSourceFileLocator) locator).setFileInfo(fileInfo);
			return locator;
		}
		return null;
	}
	
	private static Object getObjectForClassname(String bundleId, String providerClassName){
		try {
			Bundle bundle =Platform.getBundle(bundleId);
			if (bundle == null){
				JSFCorePlugin.log(IStatus.ERROR, getMsg( bundleId, providerClassName, "Unable to find bundleId: "));
				return null;
			}
			Class klass = bundle.loadClass(providerClassName);
			if (klass != null){
				return klass.newInstance();					
			}
			return null;
		} catch (ClassNotFoundException e) {
			JSFCorePlugin.log(e, getMsg(bundleId, providerClassName, "ClassNotFoundException: "));
		} catch (InstantiationException e) {
			JSFCorePlugin.log(e, getMsg(bundleId, providerClassName,"InstantiationException: "));
		} catch (IllegalAccessException e) {
			JSFCorePlugin.log(e, getMsg(bundleId, providerClassName,"IllegalAccessException: "));
		}
		return null;
	}

	private static String getMsg(String bundleId, String providerClassName, String prefix) {
		StringBuffer buf = new StringBuffer(prefix);
		buf.append(providerClassName).append(" in ").append(bundleId);
		return buf.toString();
	}

}
