/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.ListOfValues;
import org.eclipse.osgi.util.NLS;

/**
 * Experimental/prototype class to assist with decoding trait values.
 * This could/should be made API at some point after "hardening". (https://bugs.eclipse.org/bugs/show_bug.cgi?id=192052) 
 */
public class TraitValueHelper {
	private static final String KEY_NOT_FOUND = Messages.Key_not_found;
	/**
	 * @param trait
	 * @return EClass of trait value
	 */
	public static EClass getValueType(Trait trait){
		if (trait == null)
			return null;
		if (trait.getValue() != null)
			return trait.getValue().eClass();
		return null;
	}
	
	/**
	 * @param trait
	 * @return value of trait as String Object.  
	 */
	public static Object getValue(Trait trait){
		if (trait == null)
			return null;
		if (trait.getValue() == null)
			return null;
		
		if (trait.getValue() instanceof SimpleAnyType){
			return ((SimpleAnyType)trait.getValue()).getRawValue();
		}
		else if (trait.getValue() instanceof AnyType){
			AnyType any = (AnyType)trait.getValue();
			FeatureMap map = any.getMixed();			
			return getTextValueFromFeatureMap(map);
		}
		else if ( trait.getValue().eIsProxy() && trait.getValue() instanceof BasicEObjectImpl){
			BasicEObjectImpl o = (BasicEObjectImpl)trait.getValue();
			return o.eProxyURI().toString();
		}
		return trait.getValue();
	}
	
	private static String getTextValueFromFeatureMap(FeatureMap map) {
		for (Iterator it=map.iterator();it.hasNext();){
			FeatureMap.Entry entry = (FeatureMap.Entry)it.next();
			if (entry.getEStructuralFeature().getName().equals("text"))		
				return (String)entry.getValue();
		}
		return null;
	}

	/**
	 * @param trait
	 * @return value of trait as String.  If externalized, will resolve from resource bundle.
	 */
	public static String getValueAsString(Trait trait){
		Object val = getValue(trait);
		if (val != null && val instanceof String){			
			String result = getNLSValue(trait, (String)val);
			return result;
		}
		return null;
	}
	
	/**
	 * 
	 * @param trait whose value a {@link ListOfValues} or is a single string
	 * @return List of Strings.  If externalized, will resolve from resource bundle 
	 * using getNLSValue(Trait trait, String rawValue)
	 */
	public synchronized static List getValueAsListOfStrings(Trait trait){
		//PROTO ONLY!!! Need to make WAY more robust!
		List ret = new ArrayList();
		if (trait.getValue() instanceof ListOfValues) {
			for(Iterator it=trait.getValue().eContents().iterator();it.hasNext();){
				Object o = it.next();				
				if (o instanceof SimpleAnyType){
					SimpleAnyType sat = (SimpleAnyType)o;
					String rawValue = getTextValueFromFeatureMap(sat.getMixed());
					String nlsValue = getNLSValue(trait, rawValue);
					
					ret.add(nlsValue);
				}	
			}
		} 
		else {
			//may be single value
			String o = getValueAsString(trait);
			if (o != null)
				ret.add(o);
		}
		return ret;
	}
	
	/**
	 * Looks for '%' (and not '%%') at beginning of rawValue.   If found, looks to the
	 * traits sourceModelProvider for resource bundle to resolve the key after 
	 * stripping the '%' sign.
	 * @param trait
	 * @param rawValue of string in from metadata
	 * @return the NLS Value or rawValue if it cannot be located
	 */
	public static String getNLSValue(Trait trait, String rawValue) {
		String result = rawValue;
		if (rawValue.startsWith("%") && !rawValue.startsWith("%%")){ 
			String key = rawValue.substring(1);
			result = getNLSPropertyValue(trait, key);	
			if (rawValue == null){
				result = rawValue;
			}
		}
		return result == null ? null : result.trim();
	}

	//will return null if there is an IOException with ResourceBundle
	private static String getNLSPropertyValue(Trait trait, String key){
		try{			
			IMetaDataSourceModelProvider provider = trait.getSourceModelProvider();
			IResourceBundleProvider resourceBundleProvider = (IResourceBundleProvider)provider.getAdapter(IResourceBundleProvider.class);		
			if (resourceBundleProvider != null){
				ResourceBundle resourceBundle_ = resourceBundleProvider.getResourceBundle();				
				if (resourceBundle_ != null){
					String replVal = resourceBundle_.getString(key);
					return replVal;
				}				
			}
			//return original string 
			return key; 

		} catch (MissingResourceException e){
			//fall thru
			JSFCommonPlugin.log(e,  NLS.bind(Messages.MissingResource_exception, new String[]{key}));
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return key + KEY_NOT_FOUND;
	}

	/**
	 * Will take get the value as a String and attempt to coerce to boolean.
	 * Will return 'false' if coercion fails, or value was null.
	 * @param trait
	 * @return true or false 
	 */
	public static boolean getValueAsBoolean(Trait trait) {
		String val = getValueAsString(trait);
		if (val == null)
			return false;
		
		return Boolean.valueOf(val).booleanValue();
	}
	
//	/**
//	 * @param trait
//	 * @return name of class or primitive datatype that the value is defined as.   
//	 * For SimpleAnyTypes, the getInstanceType().getInstanceClassName() is returned.
//	 * For AnyType, java.lang.String is always returned.
//	 * For all others, the class.getName() is returned.
//	 */
//	public static String getValueInstanceClassName(Trait trait) {String.class.getName();
//		if (trait.getValue() instanceof SimpleAnyType) {
//			return ((SimpleAnyType)trait.getValue()).getInstanceType().getInstanceClassName();
//		}
//		else if (trait.getValue() instanceof AnyType) {
//			return String.class.getName();
//		}
//		else {
//			return trait.getValue().getClass().getName();
//		}
//		
//	}

}
