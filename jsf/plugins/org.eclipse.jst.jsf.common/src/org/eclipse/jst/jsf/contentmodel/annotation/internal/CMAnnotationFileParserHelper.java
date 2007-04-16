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

package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.contentmodel.annotation.CMAnnotationSourceFileLocator;
import org.eclipse.jst.jsf.contentmodel.annotation.ICMAnnotationFileParser;
import org.eclipse.jst.jsf.contentmodel.annotation.ICMAnnotationSourceFileInfo;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * Helper class used by the annotations registry to parse and poplulate the annotation maps using
 * the specified parsed, and source file locator from the extension.
 * 
 * @author Gerry Kessler - Oracle
 * @deprecated see common.metadata package
 */
public class CMAnnotationFileParserHelper {
	private static final String DEFAULT_PARSER_NAME = "org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAnnotationFileParser"; //$NON-NLS-1$
	private static final String DEFAULT_LOCATOR_NAME = "org.eclipse.jst.jsf.contentmodel.annotation.internal.CMPluginRelativeSourceFileLocator"; //$NON-NLS-1$
	
	public CMAnnotationFileParserHelper() {
		super();
	}
	
	public void parse(CMAnnotationMap map){
		ICMAnnotationFileParser parser = getParser(map.getFileInfo());
		ICMAnnotationAdvisor advisor = new CMAnnotationAdvisor(map);
		CMAnnotationSourceFileLocator locator = getLocator(map.getFileInfo());
		map.setLocator(locator);
		try {
			parser.parse(advisor, locator);
		} catch (Exception e) {
			JSFCommonPlugin.log(e, NLS.bind(Messages.CMAnnotationFileParserHelper_unable_to_parse , new String[]{map.getFileInfo().getAnnotationFileLocation(), map.getFileInfo().getAnnotationFileLocatorClassname()}));
		}
	}

	private ICMAnnotationFileParser getParser(ICMAnnotationSourceFileInfo fileInfo) {
		String parserClassName = fileInfo.getParserClassName();
		Object obj = null;
		if (parserClassName != null && !parserClassName.equals("")){ //$NON-NLS-1$
			obj = getObjectForClassname(fileInfo.getBundleId(), parserClassName);
		}
		else {//return default			
			obj = getObjectForClassname(JSFCommonPlugin.PLUGIN_ID, DEFAULT_PARSER_NAME );
		}
		if (obj != null && obj instanceof ICMAnnotationFileParser)
			return (ICMAnnotationFileParser)obj;
		
		return null;
	}
	
	private CMAnnotationSourceFileLocator getLocator(ICMAnnotationSourceFileInfo fileInfo) {
		String locatorClassName = fileInfo.getAnnotationFileLocatorClassname();
		Object obj = null;
		if (locatorClassName != null && !locatorClassName.equals("")){ //$NON-NLS-1$
			obj = getObjectForClassname(fileInfo.getBundleId(), locatorClassName);
		}
		else {//return default			
			obj = getObjectForClassname(JSFCommonPlugin.PLUGIN_ID, DEFAULT_LOCATOR_NAME);
		}
		if (obj != null && obj instanceof CMAnnotationSourceFileLocator){
			CMAnnotationSourceFileLocator locator = (CMAnnotationSourceFileLocator)obj;
			locator.setFileInfo(fileInfo);
			return locator;
		}
		return null;
	}
	
	private static Object getObjectForClassname(String bundleId, String providerClassName){
		try {
			Bundle bundle =Platform.getBundle(bundleId);
			if (bundle == null){
				JSFCommonPlugin.log(IStatus.ERROR, NLS.bind(Messages.CMAnnotationFileParserHelper_unable_to_find_bundleid, new String[]{bundleId, providerClassName}));
				return null;
			}
			Class klass = bundle.loadClass(providerClassName);
			if (klass != null){
				return klass.newInstance();					
			}
			return null;
		} catch (ClassNotFoundException e) {
			JSFCommonPlugin.log(e, NLS.bind(Messages.CMAnnotationFileParserHelper_class_not_found, new String[]{bundleId, providerClassName}));
		} catch (InstantiationException e) {
			JSFCommonPlugin.log(e, NLS.bind(Messages.CMAnnotationFileParserHelper_instantiation_exception, new String[]{bundleId, providerClassName}));
		} catch (IllegalAccessException e) {
			JSFCommonPlugin.log(e, NLS.bind(Messages.CMAnnotationFileParserHelper_illegal_access_exception, new String[]{bundleId, providerClassName}));
		}
		return null;
	}

}
