/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: JSFLibraryRegistryXMLProcessor.java,v 1.4 2008/11/18 22:23:53 gkessler Exp $
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
 * @deprecated
 */
public class JSFLibraryRegistryXMLProcessor extends XMLProcessor {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$


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
