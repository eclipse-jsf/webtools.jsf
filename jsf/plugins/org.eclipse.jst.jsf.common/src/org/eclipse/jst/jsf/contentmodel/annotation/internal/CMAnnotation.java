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
 *   Gerry Kessler/Oracle - code borrowed and repurposed for JSF subproject
 * 
 * 
 *******************************************************************************/
package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Content model annotation.   A property with a set of values.
 * @deprecated see common.metadata package
 */
public abstract class CMAnnotation {
	private String _name;	
	private Hashtable props = new Hashtable(3);

	/**
	 * @param name
	 */
	public CMAnnotation(String name) {
		setName(name.trim());
	}

	private void setName(String name) {
		_name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param propertyName
	 * @param propertyValue
	 */
	public void setProperty(String propertyName, String propertyValue) {
		CMAnnotationProperty prop = getAnnotationProperty(propertyName);
		if (prop == null){
			prop = new CMAnnotationProperty(propertyName, propertyValue);
			props.put(propertyName, prop);
		}
		else {
			prop.setValue(propertyValue);
		}		
	}

	/**
	 * @param propertyName
	 * @return the annotation property
	 */
	public CMAnnotationProperty getAnnotationProperty(String propertyName){
		if (props.containsKey(propertyName))
			return (CMAnnotationProperty)props.get(propertyName);
		return null;
	}
	
	class CMAnnotationProperty {
		private String propName;
		private List propValues = new ArrayList(2);
		
		/**
		 * @param propertyName
		 */
		public CMAnnotationProperty(String propertyName){
			propName = propertyName;
		}

		/**
		 * @param propertyName
		 * @param propertyValue
		 */
		public CMAnnotationProperty(String propertyName, String propertyValue){
			propName = propertyName;
			propValues.add(propertyValue);
		}
		
		/**
		 * @param propertyValue
		 */
		public void setValue(String propertyValue) {
			propValues.add(propertyValue);
		}
		
		/**
		 * @return the property name
		 */
		public String getPropertyName(){
			return propName;
		}
		
		/**
		 * @return the property value
		 */
		public List getPropertyValues(){
			return propValues;
		}
	}
}
