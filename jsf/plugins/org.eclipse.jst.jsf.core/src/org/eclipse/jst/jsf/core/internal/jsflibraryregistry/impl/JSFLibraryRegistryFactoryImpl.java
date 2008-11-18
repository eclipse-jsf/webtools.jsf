/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 * @deprecated
 */
public class JSFLibraryRegistryFactoryImpl extends EFactoryImpl implements JSFLibraryRegistryFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * @return the initialized factory 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JSFLibraryRegistryFactory init() {
		try {
			JSFLibraryRegistryFactory theJSFLibraryRegistryFactory = (JSFLibraryRegistryFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/webtools/jsf/schema/jsflibraryregistry.xsd");  //$NON-NLS-1$
			if (theJSFLibraryRegistryFactory != null) {
				return theJSFLibraryRegistryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JSFLibraryRegistryFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFLibraryRegistryFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param eClass 
	 * @return the static eclass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY: return createJSFLibraryRegistry();
			case JSFLibraryRegistryPackage.JSF_LIBRARY: return createJSFLibrary();
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY: return createPluginProvidedJSFLibrary();
			case JSFLibraryRegistryPackage.ARCHIVE_FILE: return createArchiveFile();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param eDataType 
	 * @param initialValue 
	 * @return an instance of eDataType for initialValue
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case JSFLibraryRegistryPackage.JSF_VERSION:
				return createJSFVersionFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param eDataType 
	 * @param instanceValue 
	 * @return the string created from instanceValue from eDataType 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case JSFLibraryRegistryPackage.JSF_VERSION:
				return convertJSFVersionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the jsf library registry
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFLibraryRegistry createJSFLibraryRegistry() {
		JSFLibraryRegistryImpl jsfLibraryRegistry = new JSFLibraryRegistryImpl();
		return jsfLibraryRegistry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the jsf library 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFLibrary createJSFLibrary() {
		JSFLibraryImpl jsfLibrary = new JSFLibraryImpl();
		return jsfLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the archive file 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiveFile createArchiveFile() {
		ArchiveFileImpl archiveFile = new ArchiveFileImpl();
		return archiveFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the plugin provided JSF library 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PluginProvidedJSFLibrary createPluginProvidedJSFLibrary() {
		PluginProvidedJSFLibraryImpl pluginProvidedJSFLibrary = new PluginProvidedJSFLibraryImpl();
		return pluginProvidedJSFLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return the jsfVersion the dataType 
     * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFVersion createJSFVersionFromString(EDataType eDataType, String initialValue) {
		JSFVersion result = JSFVersion.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return the string version of the data type 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJSFVersionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the registry package 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFLibraryRegistryPackage getJSFLibraryRegistryPackage() {
		return (JSFLibraryRegistryPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the package 
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static JSFLibraryRegistryPackage getPackage() {
		return JSFLibraryRegistryPackage.eINSTANCE;
	}

} //JSFLibraryRegistryFactoryImpl
