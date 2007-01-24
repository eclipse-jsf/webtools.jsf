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

package org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAnnotationFileRegistry;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAnnotationMap;

/**
 * Class meant for all public access to the design-time meta-data (CMAnnotations) values.
 * 
 * <li>Annotation files are registered against a uri (eg. http://java.sun.com/jsf/core) 
 * using the extension-point <code>org.eclipse.jst.jsf.contentmodel.annotations.annotationFiles</code>.</li>
 * <li>Any number of annotation files can be associated with the uri and against any element or attribute.</li>
 * <li>It is recommended that a single plugin should avoid registering meta-data for the same attribute or element 
 * from different annotationFiles.</li>
 * <li>A CMAnnotation allows for a collection of property values for a named property.</li>  
 * <li>Querying the registry using this helper class can return lists of <code>CMAnnotationPropertyValue</code> 
 * which allows the caller to make the determination of whether the property value is of interest or not.</li> 
 * <li>The caller can also query the registry for specific plugin supplied annotation meta-data.</li>
 * 
 * <li>-- include link to doc--</li>
 * 
 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.CMElementAnnotation
 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAttributeAnnotation
 * @see CMAnnotationPropertyValue
 * @see org.eclipse.jst.jsf.contentmodel.annotations.annotationFiles extension-point
 * 
 * @deprecated see common.metadata package
 */
public class CMAnnotationHelper {
	private static List EMPTY_LIST = new ArrayList(0);
	
	/**
	 * Returns a list of CMAnnotationPropertyValue objects for a given property name and uri/tag/tag attribute combo.
	 * A list is being returned because it is possible that there are multiple annotation files with the same 
	 * named property.  Caller can decide what to do.
	 * 
	 * Will return empty if annotation is not found or meta-data property name not present.
	 * 
	 * @param uri
	 * @param cmElementName
	 * @param cmAttrName
	 * @param meta_prop_name
	 * @return list of CMAnnotationPropertyValue objects
	 */
	public static List getCMAttributeProperties(String uri, String cmElementName, String cmAttrName, String meta_prop_name) {
		return getCMAttributeProperties(null, uri, cmElementName, cmAttrName, meta_prop_name);
	}
	

	/**
	 * Returns a list of CMAnnotationPropertyValue objects for a given bundleId, property name and uri/tag/tag attribute combo.
	 * A list is being returned because it is possible that there are multiple annotation files with the same 
	 * named property.  Caller can decide what to do.
	 * 
	 * Use this method if you are not looking for a property value from a specific bundle.  Usually the caller.
	 * 
	 * Will return empty if annotation is not found or meta-data property name not present.
	 * 
	 * @param bundleId
	 * @param uri
	 * @param cmElementName
	 * @param cmAttrName
	 * @param meta_prop_name
	 * @return list of CMAnnotationPropertyValue objects
	 */
	public static List getCMAttributeProperties(String bundleId, String uri, String cmElementName, String cmAttrName, String meta_prop_name) {
		if (!CMAnnotationFileRegistry.getInstance().hasAnnotations(uri))			
			return EMPTY_LIST;
		
		List list = new ArrayList();
		List maps = CMAnnotationFileRegistry.getInstance().getAnnotationMaps(uri);
		if (maps != null){
			Iterator it = maps.iterator();
			while (it.hasNext()){
				CMAnnotationMap map = (CMAnnotationMap)it.next();
				if (bundleId == null || map.getFileInfo().getBundleId().equals(bundleId)){
					CMAnnotationPropertyValue propVal =  map.getCMAttributePropertyValue(cmElementName, cmAttrName, meta_prop_name);
					if (propVal != null){
						list.add(propVal);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * Convenience method to return the first string value for a given bundle, uri, element, attr, and meta_prop_name.
	 * Caller needs to be aware that this could be a multi-value property.  If so, <code>getCMAttributePropertyValues</code>
	 * should probably be called.
	 * 
	 * Caller must be sure that only one annotation meta-data file is associated with this uri from the specified bundle.  
	 * If there are multiple annotations, only the first bundle's values will be returned.
	 * 
	 * Will return null if annotation is not found or meta-data property name not present.
	 * 
	 * @param bundleId
	 * @param uri
	 * @param cmElementName
	 * @param cmAttrName
	 * @param meta_prop_name
	 * @return property value as String
	 */
	public static String getCMAttributePropertyValue(String bundleId, String uri, String cmElementName, String cmAttrName, String meta_prop_name){
		List vals = getCMAttributeProperties(bundleId, uri, cmElementName, cmAttrName, meta_prop_name);
		if (vals != null && !vals.isEmpty()){
			return((CMAnnotationPropertyValue)vals.get(0)).getPropertyValue();
		}
		return null;
	}
	
	/**
	 * Convenience method to return the list of property values for a given bundle, uri, element, attr, and meta_prop_name.
	 * 
	 * Caller must be sure that only one annotation meta-data file is associated with this uri from the specified bundle.  
	 * If there are multiple annotations, only the first bundle's values will be returned.
	 * 
	 * Will return empty list if annotation is not found or meta-data property name not present.
	 * 
	 * @param bundleId
	 * @param uri
	 * @param cmElementName
	 * @param cmAttrName
	 * @param meta_prop_name
	 * @return property values as List
	 */
	public static List getCMAttributePropertyValues(String bundleId, String uri, String cmElementName, String cmAttrName, String meta_prop_name){
		List vals = getCMAttributeProperties(bundleId, uri, cmElementName, cmAttrName, meta_prop_name);
		if (!vals.isEmpty()){
			return((CMAnnotationPropertyValue)vals.get(0)).getPropertyValues();
		}
		return EMPTY_LIST;
	}
	/**
	 * Returns a list of CMAnnotationPropertyValue objects for a given property name and uri/tag element combo.
	 * A list is being returned because it is possible that there are multiple annotation files with the same 
	 * named property.  Caller can decide what to do.
	 * 
	 * Will return empty list if annotation is not found or meta-data property name not present.
	 * 
	 * @param uri
	 * @param cmElementName
	 * @param cmAttrName
	 * @param meta_prop_name
	 * @return list of CMAnnotationPropertyValue objects
	 */
	public static List getCMElementProperties(String uri, String cmElementName, String meta_prop_name) {
		return getCMElementProperties(null, uri, cmElementName, meta_prop_name);
	}

	
	/**
	 * Returns a list of CMAnnotationPropertyValue objects for a given bundle, property name and uri/tag element combo.
	 * A list is being returned because it is possible that there are multiple annotation files with the same 
	 * named property.  Caller can decide what to do.
	 * 
	 * Use this method if you are not looking for a property value from a specific bundle.  Usually the caller.
	 * 
	 * Will return empty list if annotation is not found or meta-data property name not present.
	 * 
	 * @param bundleId
	 * @param uri
	 * @param cmElementName
	 * @param cmAttrName
	 * @param meta_prop_name
	 * @return list of CMAnnotationPropertyValue objects
	 */
	public static List getCMElementProperties(String bundleId, String uri, String cmElementName, String meta_prop_name) {
		if (!CMAnnotationFileRegistry.getInstance().hasAnnotations(uri))			
			return EMPTY_LIST;
		
		List list = new ArrayList();
		List maps = CMAnnotationFileRegistry.getInstance().getAnnotationMaps(uri);
		if (maps != null){
			Iterator it = maps.iterator();
			while (it.hasNext()){
				CMAnnotationMap map = (CMAnnotationMap)it.next();
				if (bundleId == null || map.getFileInfo().getBundleId().equals(bundleId)){
					CMAnnotationPropertyValue propVal =  map.getCMElementPropertyValue(cmElementName, meta_prop_name);
					if (propVal != null){
						list.add(propVal);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * Convenience method to return the first string value for a given bundle, uri, element, and meta_prop_name.
	 * Caller needs to be aware that this could be a multi-value property.  If so, <code>getCMAttributePropertyValues</code>
	 * should probably be called.
	 * 
	 * Caller must be sure that only one annotation meta-data file is associated with this uri from the specified bundle.  
	 * If there are multiple annotations, only the first bundle's values will be returned.
	 * 
	 * Will return null if annotation is not found or meta-data property name not present.
	 * 
	 * @param bundleId
	 * @param uri
	 * @param cmElementName
	 * @param meta_prop_name
	 * @return property value as String
	 */
	public static String getCMElementPropertyValue(String bundleId, String uri, String cmElementName, String meta_prop_name){
		List vals = getCMElementProperties(bundleId, uri, cmElementName, meta_prop_name);
		if (vals != null && !vals.isEmpty()){
			return((CMAnnotationPropertyValue)vals.get(0)).getPropertyValue();
		}
		return null;
	}
	
	/**
	 * Convenience method to return the list of property values for a given bundle, uri, element, and meta_prop_name.
	 * 
	 * Caller must be sure that only one annotation meta-data file is associated with this uri from the specified bundle.  
	 * If there are multiple annotations, only the first bundle's values will be returned.
	 * 
	 * Will return empty list if annotation is not found or meta-data property name not present.
	 * 
	 * @param bundleId
	 * @param uri
	 * @param cmElementName
	 * @param meta_prop_name
	 * @return List of property values as Strings 
	 */
	public static List getCMElementPropertyValues(String bundleId, String uri, String cmElementName, String meta_prop_name){
		List vals = getCMElementProperties(bundleId, uri, cmElementName, meta_prop_name);
		if (vals != null && !vals.isEmpty()){
			return((CMAnnotationPropertyValue)vals.get(0)).getPropertyValues();
		}
		return null;
	}
	
	/**
	 * Return whether or not there are annotation files for a given uri and bundleId.
	 * @param bundleId
	 * @param uri
	 * @return boolean
	 */
	public static boolean hasAnnotations(String bundleId, String uri) {
		return CMAnnotationFileRegistry.getInstance().hasAnnotations(bundleId, uri);
	}
	
	/**
	 * Return whether or not there are annotation files for a given uri.
	 * @param uri
	 * @return boolean 
	 */
	public static boolean hasAnnotations(String uri) {
		return CMAnnotationFileRegistry.getInstance().hasAnnotations(uri);
	}
}
