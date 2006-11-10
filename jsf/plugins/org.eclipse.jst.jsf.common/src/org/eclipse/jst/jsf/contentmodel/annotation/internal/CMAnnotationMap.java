/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Jens Lukowski/Innoopract - initial renaming/restructuring
 *   Oracle - code borrowed and repurposed for JSF subproject
 *
 *******************************************************************************/
package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationPropertyValue;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationSourceFileLocator;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationSourceFileInfo;
import org.eclipse.osgi.util.NLS;


/**
 * Internal data model of content model annotations.   
 * The content model is assumed to be xml-based (elements and attributes).
 * 
 * Map of annotations for content model elements keyed by tag element name.
 * Attribute annotations are stored in a map inside the element annotation.
 * 
 *  @see <code>org.eclipse.jst.jsf.contentmodel.annotation.internal.CMElementAnnotation</code>
 *  @see <code>org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAttributeAnnotation</code>
 * 
 */
public class CMAnnotationMap {
	//map of tag-element annotations keyed by tag name
	protected Map cmNodeToAnnotationMap = new Hashtable();
	protected boolean isCaseSensitive = true;
	protected ICMAnnotationSourceFileInfo fileInfo;
	private ResourceBundle resourceBundle;
	private CMAnnotationSourceFileLocator locator;

	/**
	 * Constructor
	 * @param ICMAnnotationSourceFileInfo
	 */
	public CMAnnotationMap(ICMAnnotationSourceFileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	/**
	 * Set whether or not the model should be queried in a case sensitive manner
	 * 
	 * @param boolean
	 */
	public void setCaseSensitive(boolean isCaseSensitive) {
		this.isCaseSensitive = isCaseSensitive;
	}

	/**
	 * Set the ICMAnnotationSourceFileLocator used to find and load the annotation file
	 * @param locator
	 */
	public void setLocator(CMAnnotationSourceFileLocator locator){
		this.locator = locator;
	}
	
	/**
	 * Return  the annotation for the specified element name.
	 * Will be null if no annotation is found.
	 * 
	 * @param elementName
	 * @return CMElementAnnotation
	 */
	public CMElementAnnotation getElementAnnotation(String elementName) {
		CMElementAnnotation elem = null;
		if (cmNodeToAnnotationMap.containsKey(elementName)){
			List annos = (List)cmNodeToAnnotationMap.get(elementName);
			Iterator it = annos.iterator();
			while(it.hasNext()){
				elem = (CMElementAnnotation)it.next();
				if (elem.getName() == elementName)
					break;
			}
		}		
		return elem;
	}
	
	/**
	 * Annotation advisor will call this method when adding an annotation to a map.
	 * 
	 * @param annotation
	 */
	public void addCMElementAnnotation(CMElementAnnotation annotation) {
		addAnnotationForCMNodeName(annotation.getName(), annotation);
	}

	private void addAnnotationForCMNodeName(String cmNodeName, CMElementAnnotation annotation) {
		List list = (List) cmNodeToAnnotationMap.get(cmNodeName);
		if (list == null) {
			list = new Vector();
			cmNodeToAnnotationMap.put(cmNodeName, list);
		}
		list.add(annotation);
	}

	/**
	 * Returns list of NLS fixed String values for a given property name of an element.
	 * 
	 * @param elementName
	 * @param propertyName
	 * @return List of Strings - can be null
	 */
	public List getCMElementPropertyValues(String elementName, String propertyName) {
		List result = null;
		String key = getStringValueForCaseSensitivity(elementName);
		String propName = getStringValueForCaseSensitivity(propertyName);
		List annotationList = (List) cmNodeToAnnotationMap.get(key);
		if (annotationList != null) {
			for (Iterator i = annotationList.iterator(); i.hasNext();) {
				CMAnnotation annotation = (CMAnnotation) i.next();
				if (annotation.getAnnotationProperty(propName)!= null) {
					result = getNLSPropertyValues(annotation, propName);
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns the first NLS fixed String value for a given property name of an element.
	 * Caller should use <code>getCMElementPropertyValues</code> if multiple values can be returned for a
	 * property name.
	 * 
	 * @param elementName
	 * @param propertyName
	 * @return String - can be null
	 */
	public String getCMElementProperty(String elementName, String propertyName) {
		String result = null;
		List vals = getCMElementPropertyValues(elementName, propertyName);
		if (vals != null){
			if (vals.get(0) != null)
				result = vals.get(0).toString();
		}
		return result;
	}
	
	/**
	 * Returns list of NLS fixed String values for a given property name of an attribute of a particular element.
	 * 
	 * @param elementName
	 * @param propertyName
	 * @return List of Strings - can be null
	 */
	public List getCMAttributePropertyValues(String elementName, String attributeName, String propertyName) {
		List result = null;
		String key = getStringValueForCaseSensitivity(elementName);
		String attrName = getStringValueForCaseSensitivity(attributeName);
		String propName = getStringValueForCaseSensitivity(propertyName);		
		List annotationListForElement = (List) cmNodeToAnnotationMap.get(key);
		if (annotationListForElement != null) {
			CMAttributeAnnotation annotationForAttr = getCMAttributeAnnotation(annotationListForElement, attrName);
			if (annotationForAttr != null){
				if (annotationForAttr.getAnnotationProperty(propName) != null){
					List propVals = getNLSPropertyValues(annotationForAttr, propName);
					if (propVals != null)
						result = propVals;
				}
			}
		}
		return result;
	}
		
	/**
	 * Gets the first property value for the specified element and attribute.
	 * 
	 * @param elementName
	 * @param attributeName
	 * @param propertyName
	 * @return String - can be null
	 */
	public String getCMAttributeProperty(String elementName, String attributeName, String propertyName) {
		String result = null;
		List propVals = getCMAttributePropertyValues(elementName, attributeName, propertyName);
		if (propVals != null){
			result = (String)propVals.get(0);
		}
		return result;
	}

	/**
	 * Return a <code>CMAnnotationPropertyValue</code> object for the given element, attribute, and property name.
	 * 
	 * @param elementName
	 * @param attributeName
	 * @param propertyName
	 * @return CMAnnotationPropertyValue - can be null
	 */
	public CMAnnotationPropertyValue getCMAttributePropertyValue(String elementName, String attributeName, String propertyName) {
		CMAnnotationPropertyValue result = null;
		List val = getCMAttributePropertyValues(elementName, attributeName, propertyName);
		if (val != null){
			result = new CMAnnotationPropertyValue(fileInfo, val);
		}
		return result;
	}
	
	/**
	 * Return a <code>CMAnnotationPropertyValue</code> object for the given element and property name.
	 * 
	 * @param elementName
	 * @param propertyName
	 * @return CMAnnotationPropertyValue - can be null
	 */
	public CMAnnotationPropertyValue getCMElementPropertyValue(String elementName, String propertyName) {
		CMAnnotationPropertyValue result = null;
		List val = getCMElementPropertyValues(elementName, propertyName);
		if (val != null){
			result = new CMAnnotationPropertyValue( fileInfo, val);
		}
		return result;
	}
	
	private CMAttributeAnnotation getCMAttributeAnnotation(List annotationListForElement, String attributeName) {
		for (int i=0;i < annotationListForElement.size();i++){
			CMElementAnnotation annotationForElement = (CMElementAnnotation)annotationListForElement.get(i);
			if (annotationForElement != null){
				Map attributeAnnotations = annotationForElement.getAttributeAnnotations();
				if (attributeAnnotations != null){
					CMAttributeAnnotation attributeAnnotation = (CMAttributeAnnotation)attributeAnnotations.get(attributeName);
					if (attributeAnnotation != null){
						return attributeAnnotation;
					}
				}
			}
		}
		return null ;
	}

	private List getNLSPropertyValues(CMAnnotation annotation, String propertyName){
		List result =  annotation.getAnnotationProperty(propertyName).getPropertyValues();
		for (int i=0;i<result.size();i++){
			String val = (String)result.get(i);
			if (val.startsWith("%") && !val.startsWith("%%")){ //$NON-NLS-1$ //$NON-NLS-2$
				val = getNLSPropertyValue(val);
				result.set(i, val);
				//also update property annotation to avoid needing to go thru bundle next time
				annotation.getAnnotationProperty(propertyName).getPropertyValues().set(i, val);
			}
		}
		return result;
	}

	//will return null if there is an IOException with ResourceBundle
	private String getNLSPropertyValue(String val){
		String NOT_FOUND = Messages.CMAnnotationMap_key_not_found;
		try{
			ResourceBundle resourceBundle_ = getResourceBundle();		
			if (resourceBundle_ != null){
				String replVal = resourceBundle_.getString(val.substring(1));
				return replVal;
			}
			//return original string
			return val; 
		} catch (IOException e) {
			JSFCommonPlugin.log(e, NLS.bind(Messages.CMAnnotationMap_IOException, new String[]{val}));
			return null;
		} catch (MissingResourceException e){
			//fall thru
			JSFCommonPlugin.log(e,  NLS.bind(Messages.CMAnnotationMap_MissingResource_exception, new String[]{val}));
		}
		return val.substring(1) + NOT_FOUND;
	}

	/**
	 * Return the ICMAnnotationSourceFileInfo used to create this CMAnnotationMap
	 * @return ICMAnnotationSourceFileInfo
	 */
	public ICMAnnotationSourceFileInfo getFileInfo(){
		return fileInfo;
	}
	
	/**
	 * @param String
	 * @return string value used for case sensitive or insensitive queries.
	 */
	public String getStringValueForCaseSensitivity(String val){
		return isCaseSensitive ? val : val.toLowerCase();
	}

	private ResourceBundle getResourceBundle() throws IOException{		
		if (resourceBundle == null){		
			resourceBundle = locator.getResourceBundle();
		}
		return resourceBundle;
	}
}
