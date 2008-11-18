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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 * @deprecated
 */
public class JSFLibraryRegistryPackageImpl extends EPackageImpl implements JSFLibraryRegistryPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jsfLibraryRegistryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jsfLibraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiveFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pluginProvidedJSFLibraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum jsfVersionEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JSFLibraryRegistryPackageImpl() {
		super(eNS_URI, JSFLibraryRegistryFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * @return the JSF library registry package
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JSFLibraryRegistryPackage init() {
		if (isInited) return (JSFLibraryRegistryPackage)EPackage.Registry.INSTANCE.getEPackage(JSFLibraryRegistryPackage.eNS_URI);

		// Obtain or create and register package
		JSFLibraryRegistryPackageImpl theJSFLibraryRegistryPackage = (JSFLibraryRegistryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof JSFLibraryRegistryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new JSFLibraryRegistryPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theJSFLibraryRegistryPackage.createPackageContents();

		// Initialize created meta-data
		theJSFLibraryRegistryPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJSFLibraryRegistryPackage.freeze();

		return theJSFLibraryRegistryPackage;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eclass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJSFLibraryRegistry() {
		return jsfLibraryRegistryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSFLibraryRegistry_DefaultImplementationID() {
		return (EAttribute)jsfLibraryRegistryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the ereference
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJSFLibraryRegistry_JSFLibraries() {
		return (EReference)jsfLibraryRegistryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the ereference 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJSFLibraryRegistry_PluginProvidedJSFLibraries() {
		return (EReference)jsfLibraryRegistryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eclass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJSFLibrary() {
		return jsfLibraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSFLibrary_ID() {
		return (EAttribute)jsfLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSFLibrary_Name() {
		return (EAttribute)jsfLibraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSFLibrary_JSFVersion() {
		return (EAttribute)jsfLibraryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSFLibrary_Deployed() {
		return (EAttribute)jsfLibraryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJSFLibrary_Implementation() {
		return (EAttribute)jsfLibraryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the ereference 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJSFLibrary_ArchiveFiles() {
		return (EReference)jsfLibraryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eclass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchiveFile() {
		return archiveFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiveFile_SourceLocation() {
		return (EAttribute)archiveFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiveFile_RelativeToWorkspace() {
		return (EAttribute)archiveFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiveFile_RelativeDestLocation() {
		return (EAttribute)archiveFileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the ereference 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiveFile_JSFLibrary() {
		return (EReference)archiveFileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eclass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPluginProvidedJSFLibrary() {
		return pluginProvidedJSFLibraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eattribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPluginProvidedJSFLibrary_PluginID() {
		return (EAttribute)pluginProvidedJSFLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPluginProvidedJSFLibrary_Label() {
		return (EAttribute)pluginProvidedJSFLibraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the eenum 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getJSFVersion() {
		return jsfVersionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the jsf library registry factory
     * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFLibraryRegistryFactory getJSFLibraryRegistryFactory() {
		return (JSFLibraryRegistryFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		jsfLibraryRegistryEClass = createEClass(JSF_LIBRARY_REGISTRY);
		createEAttribute(jsfLibraryRegistryEClass, JSF_LIBRARY_REGISTRY__DEFAULT_IMPLEMENTATION_ID);
		createEReference(jsfLibraryRegistryEClass, JSF_LIBRARY_REGISTRY__JSF_LIBRARIES);
		createEReference(jsfLibraryRegistryEClass, JSF_LIBRARY_REGISTRY__PLUGIN_PROVIDED_JSF_LIBRARIES);

		jsfLibraryEClass = createEClass(JSF_LIBRARY);
		createEAttribute(jsfLibraryEClass, JSF_LIBRARY__ID);
		createEAttribute(jsfLibraryEClass, JSF_LIBRARY__NAME);
		createEAttribute(jsfLibraryEClass, JSF_LIBRARY__JSF_VERSION);
		createEAttribute(jsfLibraryEClass, JSF_LIBRARY__DEPLOYED);
		createEAttribute(jsfLibraryEClass, JSF_LIBRARY__IMPLEMENTATION);
		createEReference(jsfLibraryEClass, JSF_LIBRARY__ARCHIVE_FILES);

		pluginProvidedJSFLibraryEClass = createEClass(PLUGIN_PROVIDED_JSF_LIBRARY);
		createEAttribute(pluginProvidedJSFLibraryEClass, PLUGIN_PROVIDED_JSF_LIBRARY__PLUGIN_ID);
		createEAttribute(pluginProvidedJSFLibraryEClass, PLUGIN_PROVIDED_JSF_LIBRARY__LABEL);

		archiveFileEClass = createEClass(ARCHIVE_FILE);
		createEAttribute(archiveFileEClass, ARCHIVE_FILE__RELATIVE_TO_WORKSPACE);
		createEAttribute(archiveFileEClass, ARCHIVE_FILE__SOURCE_LOCATION);
		createEAttribute(archiveFileEClass, ARCHIVE_FILE__RELATIVE_DEST_LOCATION);
		createEReference(archiveFileEClass, ARCHIVE_FILE__JSF_LIBRARY);

		// Create enums
		jsfVersionEEnum = createEEnum(JSF_VERSION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes
		pluginProvidedJSFLibraryEClass.getESuperTypes().add(this.getJSFLibrary());

		// Initialize classes and features; add operations and parameters
		initEClass(jsfLibraryRegistryEClass, JSFLibraryRegistry.class, "JSFLibraryRegistry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getJSFLibraryRegistry_DefaultImplementationID(), ecorePackage.getEString(), "DefaultImplementationID", "", 0, 1, JSFLibraryRegistry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getJSFLibraryRegistry_JSFLibraries(), this.getJSFLibrary(), null, "JSFLibraries", null, 0, -1, JSFLibraryRegistry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getJSFLibraryRegistry_PluginProvidedJSFLibraries(), this.getPluginProvidedJSFLibrary(), null, "PluginProvidedJSFLibraries", null, 0, -1, JSFLibraryRegistry.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		EOperation op = addEOperation(jsfLibraryRegistryEClass, this.getJSFLibrary(), "getJSFLibraryByID", 1, 1); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "ID", 1, 1); //$NON-NLS-1$

		op = addEOperation(jsfLibraryRegistryEClass, ecorePackage.getEEList(), "getJSFLibrariesByName", 1, 1); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1); //$NON-NLS-1$

		addEOperation(jsfLibraryRegistryEClass, ecorePackage.getEEList(), "getImplJSFLibraries", 1, 1); //$NON-NLS-1$

		addEOperation(jsfLibraryRegistryEClass, ecorePackage.getEEList(), "getNonImplJSFLibraries", 1, 1); //$NON-NLS-1$

		addEOperation(jsfLibraryRegistryEClass, ecorePackage.getEEList(), "getAllJSFLibraries", 1, 1); //$NON-NLS-1$

		op = addEOperation(jsfLibraryRegistryEClass, ecorePackage.getEBoolean(), "addJSFLibrary", 1, 1); //$NON-NLS-1$
		addEParameter(op, this.getJSFLibrary(), "library", 1, 1); //$NON-NLS-1$

		op = addEOperation(jsfLibraryRegistryEClass, ecorePackage.getEBoolean(), "removeJSFLibrary", 1, 1); //$NON-NLS-1$
		addEParameter(op, this.getJSFLibrary(), "library", 1, 1); //$NON-NLS-1$

		addEOperation(jsfLibraryRegistryEClass, this.getJSFLibrary(), "getDefaultImplementation", 1, 1); //$NON-NLS-1$

		op = addEOperation(jsfLibraryRegistryEClass, null, "setDefaultImplementation"); //$NON-NLS-1$
		addEParameter(op, this.getJSFLibrary(), "implementation", 1, 1); //$NON-NLS-1$

		initEClass(jsfLibraryEClass, JSFLibrary.class, "JSFLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getJSFLibrary_ID(), ecorePackage.getEString(), "ID", "", 0, 1, JSFLibrary.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getJSFLibrary_Name(), ecorePackage.getEString(), "Name", null, 1, 1, JSFLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getJSFLibrary_JSFVersion(), this.getJSFVersion(), "JSFVersion", "UNKNOWN", 1, 1, JSFLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getJSFLibrary_Deployed(), ecorePackage.getEBoolean(), "Deployed", "true", 1, 1, JSFLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getJSFLibrary_Implementation(), ecorePackage.getEBoolean(), "Implementation", "false", 1, 1, JSFLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getJSFLibrary_ArchiveFiles(), this.getArchiveFile(), this.getArchiveFile_JSFLibrary(), "ArchiveFiles", null, 0, -1, JSFLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(jsfLibraryEClass, ecorePackage.getEBoolean(), "containsArchiveFile", 1, 1); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "fullPath", 1, 1); //$NON-NLS-1$

		addEOperation(jsfLibraryEClass, this.getJSFLibrary(), "getWorkingCopy", 1, 1); //$NON-NLS-1$

		op = addEOperation(jsfLibraryEClass, null, "updateValues"); //$NON-NLS-1$
		addEParameter(op, this.getJSFLibrary(), "otherLibrary", 1, 1); //$NON-NLS-1$

		op = addEOperation(jsfLibraryEClass, ecorePackage.getEBoolean(), "copyTo", 1, 1); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "baseDestLocation", 1, 1); //$NON-NLS-1$

		addEOperation(jsfLibraryEClass, ecorePackage.getEString(), "getLabel", 1, 1); //$NON-NLS-1$

		initEClass(pluginProvidedJSFLibraryEClass, PluginProvidedJSFLibrary.class, "PluginProvidedJSFLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPluginProvidedJSFLibrary_PluginID(), ecorePackage.getEString(), "pluginID", null, 1, 1, PluginProvidedJSFLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPluginProvidedJSFLibrary_Label(), ecorePackage.getEString(), "Label", null, 1, 1, PluginProvidedJSFLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(archiveFileEClass, ArchiveFile.class, "ArchiveFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getArchiveFile_RelativeToWorkspace(), ecorePackage.getEBoolean(), "RelativeToWorkspace", "true", 1, 1, ArchiveFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getArchiveFile_SourceLocation(), ecorePackage.getEString(), "SourceLocation", null, 1, 1, ArchiveFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getArchiveFile_RelativeDestLocation(), ecorePackage.getEString(), "RelativeDestLocation", null, 1, 1, ArchiveFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getArchiveFile_JSFLibrary(), this.getJSFLibrary(), this.getJSFLibrary_ArchiveFiles(), "JSFLibrary", null, 1, 1, ArchiveFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(archiveFileEClass, ecorePackage.getEString(), "getName", 1, 1); //$NON-NLS-1$

		addEOperation(archiveFileEClass, ecorePackage.getEString(), "getPath", 1, 1); //$NON-NLS-1$

		addEOperation(archiveFileEClass, ecorePackage.getEBoolean(), "exists", 1, 1); //$NON-NLS-1$

		op = addEOperation(archiveFileEClass, ecorePackage.getEBoolean(), "equals", 1, 1); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEJavaObject(), "object", 1, 1); //$NON-NLS-1$

		addEOperation(archiveFileEClass, ecorePackage.getEInt(), "hashCode", 1, 1); //$NON-NLS-1$

		op = addEOperation(archiveFileEClass, ecorePackage.getEBoolean(), "copyTo", 1, 1); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "baseDestLocation", 1, 1); //$NON-NLS-1$

		addEOperation(archiveFileEClass, ecorePackage.getEString(), "getResolvedSourceLocation", 1, 1); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(jsfVersionEEnum, JSFVersion.class, "JSFVersion"); //$NON-NLS-1$
		addEEnumLiteral(jsfVersionEEnum, JSFVersion.UNKNOWN_LITERAL);
		addEEnumLiteral(jsfVersionEEnum, JSFVersion.V1_1_LITERAL);
		addEEnumLiteral(jsfVersionEEnum, JSFVersion.V1_2_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //JSFLibraryRegistryPackageImpl
