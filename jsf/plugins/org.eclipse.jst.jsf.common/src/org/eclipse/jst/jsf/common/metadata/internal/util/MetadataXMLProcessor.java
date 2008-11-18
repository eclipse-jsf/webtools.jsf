/**
 * <copyright>
 * </copyright>
 *
 * $Id: MetadataXMLProcessor.java,v 1.4 2008/11/18 22:24:36 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;


/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MetadataXMLProcessor extends XMLProcessor {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$


	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		MetadataPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the MetadataResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new MetadataResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new MetadataResourceFactoryImpl());
		}
		return registrations;
	}

} //MetadataXMLProcessor
