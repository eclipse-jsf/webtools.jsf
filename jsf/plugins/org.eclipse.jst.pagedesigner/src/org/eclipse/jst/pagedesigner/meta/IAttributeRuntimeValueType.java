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
package org.eclipse.jst.pagedesigner.meta;

/**
 * Enumeration of core attribute-value-runtime-types.
 * Not to be implemented by clients.
 */
public interface IAttributeRuntimeValueType {
	// shared for html and jsf
	/**
	 * 
	 */
	static final String prefix1 = "org.eclipse.jst.jsf.core.attributevalues.";
	/**
	 * 
	 */
	static final String prefix2 = "org.eclipse.jst.pagedesigner.attributevalues.";
	
	/**
	 * 
	 */
	public static final String BASE = prefix1+"BaseType";
	
	/**
	 * 
	 */
	public static final String CSSSTYLE = prefix2+"CSSStyleType";

	/**
	 * 
	 */
	public static final String CSSCLASS = prefix2+"CSSClassType";

	/**
	 * 
	 */
	public static final String CSSID = prefix2+"CSSIdType";

	/**
	 * 
	 */
	public static final String STRING = prefix1+"StringType";

	/**
	 * 
	 */
	public static final String BOOLEAN = prefix1+"BooleanType";

	/**
	 * 
	 */
	public static final String RELATIVEPATH = prefix1+"RelativePathType";

	/**
	 * 
	 */
	public static final String WEBPATH = prefix1+"WebPathType"; //FIXME

	/**
	 * 
	 */
	public static final String COLOR = prefix1+"ColorType";

//	public static final String NAMED_BOOLEAN = prefix1+"NAMED-BOOLEAN";
	
	/**
	 * 
	 */
	public static final String LONG = prefix1+"LongType";
	
	/**
	 * 
	 */
	public static final String INTEGER = prefix1+"IntegerType";
	
	/**
	 * 
	 */
	public static final String DOUBLE = prefix1+"DoubleType";

	/**
	 * 
	 */
	public static final String METHODBINDING = prefix1+"MethodBindingType";

	/**
	 * 
	 */
	public static final String JAVACLASS = prefix1+"JavaClassType";

	/**
	 * 
	 */
	public static final String SCRIPT = prefix1+"ScriptType";

//	public static final String PROPERTYBINDING = prefix1+"PROPERTYBINDING";
	
	/**
	 * 
	 */
	public static final String VALUE = prefix1+"ValueType";
	
	/**
	 * 
	 */
	public static final String VALUEBINDING = prefix1+"ValueBindingType";

	/**
	 * 
	 */
	public static final String TIMEZONE = prefix1+"TimeZoneType";
	
	/**
	 * 
	 */
	public static final String ACTION = prefix1+"ActionType";

	/**
	 * 
	 */
	public static final String CLASSPATH_RESOURCE = prefix1+"CLASSPATH_RESOURCE";

	/**
	 * 
	 */
	public static final String CURRENCYCODE = prefix1+"CurrencyCodeType";

	/**
	 * 
	 */
	public static final String LINK = prefix1+"LinkType";
	
	/**
	 * 
	 */
	public static final String LOCALE = prefix1+"LocaleType";

//	public static final String MULTICHOICE = prefix1+"MULTICHOICE";
	
	/**
	 * 
	 */
	public static final String FACESCONFIGIDENTIFIER = prefix1+"FacesConfigIdentifierType";
	
	/**
	 * 
	 */
	public static final String FACESCONFIGVALIDATORID = prefix1+"FacesConfigValidatorIDType";
	
	/**
	 * 
	 */
	public static final String FACESCONFIGCONVERTERID = prefix1+"FacesConfigConverterIDType";
	
	/**
	 * 
	 */
	public static final String COMPONENTBINDING = prefix1+"ComponentBindingType";
	
	/**
	 * 
	 */
	public static final String COMPONENTID = prefix1+"ComponentIDType";
	
	/**
	 * 
	 */
	public static final String LENGTH = prefix1+"LengthType";
	
	/**
	 * 
	 */
	public static final String RESOURCEBUNDLE = prefix1+"ResourceBundleType";
	
	/**
	 * 
	 */
	public static final String LANGUAGECODE = prefix1+"LanguageCodeType";
}
