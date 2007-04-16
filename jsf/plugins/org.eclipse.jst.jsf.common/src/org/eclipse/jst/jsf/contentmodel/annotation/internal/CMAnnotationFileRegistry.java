/*******************************************************************************
 * Copyright (c) 2002, 2006 IBM Corporation and others.
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
package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.contentmodel.annotation.ICMAnnotationSourceFileInfo;
import org.eclipse.osgi.util.NLS;

/**
 * This internal class is used to associate one or more annotation files with a grammar
 * file mapped by a key (uri).
 * 
 * Using the CMAnnotationFileRegistryReader, extenders of <code>org.eclipse.jst.jsf.contentmodel.annotations.annotationFiles</code> are loaded
 * into the annotationFilesMap keyed by uri.   There can be multiple annotationFiles mapped to the same uri.
 * 
 * This registry should only be accessed by clients using the <code>org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper</code>.
 * Only when the system attempts to access an annotation for a content model, will the file be parsed and cached.
 * 
 * When queried, CMAnnotationPropertyValue objects are returned for each named property found in each annotation file for a particular uri.
 * 
 * also see org.eclipse.jst.jsf.contentmodel.annotations.annotationFiles extension-point
 * @see CMAnnotationFileRegistryReader
 * @see CMAnnotationMap
 * @see org.eclipse.jst.jsf.contentmodel.annotation.ICMAnnotationSourceFileInfo
 * 
 * @deprecated see common.metadata package
 */
public final class CMAnnotationFileRegistry {
	//all annotation files registered using "org.eclipse.jst.jsf.contentmodel.annotations.annotationFiles" ext-pt. and keyed by uri.  
	private Map annotationFilesMap 	= new HashMap(1);
	//will return list of CMAnnotationMap (parsed annotation files) objects keyed by uri
	private Map parsedFilesMap 		= new HashMap(1);

	private static CMAnnotationFileRegistry reg;
	
	private static final boolean DISABLED = true;
	
	public static CMAnnotationFileRegistry getInstance() {
		if (reg == null){
			reg = new CMAnnotationFileRegistry();
		}
		return reg;
		
	}
	
	//constructor reads the annotationFile registry and populates the annotationFilesMap
	private CMAnnotationFileRegistry() {
		if (!(DISABLED))
			new CMAnnotationFileRegistryReader(this).readRegistry();
	}
	
	private synchronized List getAnnotationFilesInfos(String uri) {
		List theList = (List) annotationFilesMap.get(uri);
		return theList != null ? theList : new ArrayList();
	}
		
	/**
	 * For use by registry reader only
	 * @param uri
	 * @param fileInfo 
	 */
	public synchronized void addAnnotationFileInfo(String uri, ICMAnnotationSourceFileInfo fileInfo) {
		List fileInfos = (List) annotationFilesMap.get(uri);
		if (fileInfos == null) {
			fileInfos = new ArrayList();
			annotationFilesMap.put(uri, fileInfos);
		}
		fileInfos.add(fileInfo);
	}	
	
	private synchronized void addParsedFileToMap(String uri, CMAnnotationMap map) {
		List maps = (List) parsedFilesMap.get(uri);
		if (maps == null) {
			maps = new ArrayList();
			parsedFilesMap.put(uri, maps);
		}
		maps.add(map);
	}

	/**
	 * Return list of CMAnnotationMaps for a given uri.  
	 * By calling this method, unparsed annotation files for the given 
	 * uri will be parsed.
	 * 
	 * @param uri
	 * @return List of CMAnnotationMaps
	 */
	public synchronized List getAnnotationMaps(String uri) {
		if (DISABLED){
			JSFCommonPlugin.log(IStatus.ERROR, "Attempted metadata access using CMAnnotationFiles for uri: "+uri+".   Use org.eclipse.jst.jsf.common.metadata, instead." );
			return Collections.EMPTY_LIST;
		}
		
		List list = (List)parsedFilesMap.get(uri);
		if (list == null){
			loadAnnotationsForGrammar(uri);
			list = (List)parsedFilesMap.get(uri);
		}
		return list;
	}
	

	/**
	 * Does this bundle define an annotationFile extension for the given uri.
	 * 
	 * @param bundleId
	 * @param uri
	 * @return boolean
	 */
	public boolean hasAnnotations(String bundleId, String uri) {
//		if (DISABLED){
//			
//		}
		List maps = (List)annotationFilesMap.get(uri);
		if (maps == null || maps.size() ==0)
			return false;
		
		for (int i=0;i<maps.size();i++){
			if (((ICMAnnotationSourceFileInfo)maps.get(i)).getBundleId().equals(bundleId))
				return true;
		}
		return false;
	}
	/**
	 * Are there any annotationFile extensions defined for the given uri.
	 * @param uri
	 * @return boolean
	 */
	public boolean hasAnnotations(String uri){
		if (DISABLED){
			JSFCommonPlugin.log(IStatus.ERROR, "Attempted metadata access using CMAnnotationFiles for uri: "+uri+".   Use org.eclipse.jst.jsf.common.metadata, instead." );
			return false;
		}
		if (annotationFilesMap.get(uri) != null)
			return true;
		return false;
	}

	private boolean areAnnotationsParsed(String uri) {
		if (hasAnnotations(uri)){
			if (parsedFilesMap.get(uri) != null)
				return true;
		}
		return false;
	}
	
	private void loadAnnotationsForGrammar(String uri) {
		if (areAnnotationsParsed(uri))
			return;
		
		if (DISABLED)
			return;
		
		List annotationFiles = getAnnotationFilesInfos(uri);
		for (Iterator i = annotationFiles.iterator(); i.hasNext();) {
			try {					
				CMAnnotationFileInfo annotationFileInfo = (CMAnnotationFileInfo) i.next();
				CMAnnotationMap map = new CMAnnotationMap(annotationFileInfo);
				CMAnnotationFileParserHelper parserHelper = new CMAnnotationFileParserHelper();
				parserHelper.parse(map);
				addParsedFileToMap(uri, map);
			}
			catch (Exception e) {
                JSFCommonPlugin.log(e, NLS.bind(Messages.CMAnnotationFileRegistry_load_error, new String[]{uri}));				
			}
		}		
	}

}
