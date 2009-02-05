/**
 * <copyright>
 * </copyright>
 *
 * $Id: JSFLibraryRegistryXMLProcessor.java,v 1.2 2007/04/04 18:50:17 cbateman Exp $
 */
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JSFLibraryRegistryXMLProcessor extends XMLProcessor {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation";


	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFLibraryRegistryXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		JSFLibraryRegistryPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the JSFLibraryRegistryResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * @return Map of registrations 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new JSFLibraryRegistryResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new JSFLibraryRegistryResourceFactoryImpl());
		}
		return registrations;
	}

} //JSFLibraryRegistryXMLProcessor
