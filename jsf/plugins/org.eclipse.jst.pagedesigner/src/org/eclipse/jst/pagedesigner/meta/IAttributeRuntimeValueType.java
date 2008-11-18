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
	static final String prefix1 = "org.eclipse.jst.jsf.core.attributevalues."; //$NON-NLS-1$
	/**
	 * 
	 */
	static final String prefix2 = "org.eclipse.jst.pagedesigner.attributevalues."; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String BASE = prefix1+"BaseType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String CSSSTYLE = prefix1+"CSSStyleType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CSSCLASS = prefix1+"CSSClassType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CSSID = prefix1+"CSSIdType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String STRING = prefix1+"StringType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String BOOLEAN = prefix1+"BooleanType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String RELATIVEPATH = prefix1+"RelativePathType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String WEBPATH = prefix1+"WebPathType"; //FIXME //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String COLOR = prefix1+"ColorType"; //$NON-NLS-1$

//	public static final String NAMED_BOOLEAN = prefix1+"NAMED-BOOLEAN";
	
	/**
	 * 
	 */
	public static final String LONG = prefix1+"LongType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String INTEGER = prefix1+"IntegerType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String DOUBLE = prefix1+"DoubleType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String METHODBINDING = prefix1+"MethodBindingType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String JAVACLASS = prefix1+"JavaClassType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String SCRIPT = prefix1+"ScriptType"; //$NON-NLS-1$

//	public static final String PROPERTYBINDING = prefix1+"PROPERTYBINDING";
	
	/**
	 * 
	 */
	public static final String VALUE = prefix1+"ValueType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String VALUEBINDING = prefix1+"ValueBindingType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TIMEZONE = prefix1+"TimeZoneType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String ACTION = prefix1+"ActionType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CLASSPATH_RESOURCE = prefix1+"CLASSPATH_RESOURCE"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CURRENCYCODE = prefix1+"CurrencyCodeType"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String LINK = prefix1+"LinkType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String LOCALE = prefix1+"LocaleType"; //$NON-NLS-1$

//	public static final String MULTICHOICE = prefix1+"MULTICHOICE";
	
	/**
	 * 
	 */
	public static final String FACESCONFIGIDENTIFIER = prefix1+"FacesConfigIdentifierType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String FACESCONFIGVALIDATORID = prefix1+"FacesConfigValidatorIDType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String FACESCONFIGCONVERTERID = prefix1+"FacesConfigConverterIDType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String COMPONENTBINDING = prefix1+"ComponentBindingType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String COMPONENTID = prefix1+"ComponentIDType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String LENGTH = prefix1+"LengthType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String RESOURCEBUNDLE = prefix1+"ResourceBundleType"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String LANGUAGECODE = prefix1+"LanguageCodeType"; //$NON-NLS-1$
}
