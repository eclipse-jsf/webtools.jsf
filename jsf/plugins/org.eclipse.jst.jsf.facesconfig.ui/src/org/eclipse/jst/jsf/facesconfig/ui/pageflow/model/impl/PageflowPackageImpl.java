/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class PageflowPackageImpl extends EPackageImpl implements
		PageflowPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pageflowEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pageflowNodeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pfLinkEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pageflowElementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pfPageEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pfLinkBendpointEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PageflowPackageImpl() {
		super(eNS_URI, PageflowFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends. Simple dependencies are
	 * satisfied by calling this method on all dependent packages before doing
	 * anything else. This method drives initialization for interdependent
	 * packages directly, in parallel with this package, itself.
	 * <p>
	 * Of this package and its interdependencies, all packages which have not
	 * yet been registered by their URI values are first created and registered.
	 * The packages are then initialized in two steps: meta-model objects for
	 * all of the packages are created before any are initialized, since one
	 * package's meta-model objects may refer to those of another.
	 * <p>
	 * Invocation of this method will not affect any packages that have already
	 * been initialized. 
	 * 
	 * <!-- begin-user-doc --> 
 	 * @return the package 
	 * <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PageflowPackage init() {
		if (isInited)
			return (PageflowPackage) EPackage.Registry.INSTANCE
					.get(PageflowPackage.eNS_URI);

		// Obtain or create and register package
		PageflowPackageImpl thePageflowPackage = (PageflowPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof PageflowPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI)
				: new PageflowPackageImpl());

		isInited = true;

		// Create package meta-data objects
		thePageflowPackage.createPackageContents();

		// Initialize created meta-data
		thePageflowPackage.initializePackageContents();

		return thePageflowPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPageflow() {
		return pageflowEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPageflow_Nodes() {
		return (EReference) pageflowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPageflow_Links() {
		return (EReference) pageflowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflow_Configfile() {
		return (EAttribute) pageflowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPageflowNode() {
		return pageflowNodeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPageflowNode_Pageflow() {
		return (EReference) pageflowNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPageflowNode_Outlinks() {
		return (EReference) pageflowNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPageflowNode_Inlinks() {
		return (EReference) pageflowNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPFLink() {
		return pfLinkEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPFLink_Pageflow() {
		return (EReference) pfLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPFLink_Target() {
		return (EReference) pfLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPFLink_Source() {
		return (EReference) pfLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLink_Outcome() {
		return (EAttribute) pfLinkEClass.getEStructuralFeatures().get(3);
	}

		/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLink_Redirect() {
		return (EAttribute) pfLinkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPFLink_BendPoints() {
		return (EReference) pfLinkEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> 
	 * @return the small icon eattribute 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLink_Smallicon() {
		return (EAttribute) pfLinkEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> 
	 * @return the large icon attribute 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPFLink_Largeicon() {
		return (EAttribute) pfLinkEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLink_Fromaction() {
		return (EAttribute) pfLinkEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> 
	 * @return the reference link eattribute 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_ReferenceLink() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPageflowElement() {
		return pageflowElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_Name() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_Comment() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_X() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_Y() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_Width() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_Height() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPageflowElement_Id() {
		return (EAttribute) pageflowElementEClass.getEStructuralFeatures().get(
				6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPFPage() {
		return pfPageEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFPage_Path() {
		return (EAttribute) pfPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFPage_Smallicon() {
		return (EAttribute) pfPageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFPage_Largeicon() {
		return (EAttribute) pfPageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPFLinkBendpoint() {
		return pfLinkBendpointEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLinkBendpoint_D1Width() {
		return (EAttribute) pfLinkBendpointEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLinkBendpoint_D1Height() {
		return (EAttribute) pfLinkBendpointEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLinkBendpoint_D2Width() {
		return (EAttribute) pfLinkBendpointEClass.getEStructuralFeatures().get(
				2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLinkBendpoint_D2Height() {
		return (EAttribute) pfLinkBendpointEClass.getEStructuralFeatures().get(
				3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPFLinkBendpoint_Weight() {
		return (EAttribute) pfLinkBendpointEClass.getEStructuralFeatures().get(
				4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPFLinkBendpoint_Link() {
		return (EReference) pfLinkBendpointEClass.getEStructuralFeatures().get(
				5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowFactory getPageflowFactory() {
		return (PageflowFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		pageflowEClass = createEClass(PAGEFLOW);
		createEReference(pageflowEClass, PAGEFLOW__NODES);
		createEReference(pageflowEClass, PAGEFLOW__LINKS);
		createEAttribute(pageflowEClass, PAGEFLOW__CONFIGFILE);

		pageflowNodeEClass = createEClass(PAGEFLOW_NODE);
		createEReference(pageflowNodeEClass, PAGEFLOW_NODE__PAGEFLOW);
		createEReference(pageflowNodeEClass, PAGEFLOW_NODE__OUTLINKS);
		createEReference(pageflowNodeEClass, PAGEFLOW_NODE__INLINKS);

		pfLinkEClass = createEClass(PF_LINK);
		createEReference(pfLinkEClass, PF_LINK__PAGEFLOW);
		createEReference(pfLinkEClass, PF_LINK__TARGET);
		createEReference(pfLinkEClass, PF_LINK__SOURCE);
		createEAttribute(pfLinkEClass, PF_LINK__OUTCOME);
		createEAttribute(pfLinkEClass, PF_LINK__REDIRECT);
		createEReference(pfLinkEClass, PF_LINK__BEND_POINTS);
		createEAttribute(pfLinkEClass, PF_LINK__SMALLICON);
		createEAttribute(pfLinkEClass, PF_LINK__LARGEICON);
		createEAttribute(pfLinkEClass, PF_LINK__FROMACTION);

		pageflowElementEClass = createEClass(PAGEFLOW_ELEMENT);
		createEAttribute(pageflowElementEClass, PAGEFLOW_ELEMENT__NAME);
		createEAttribute(pageflowElementEClass, PAGEFLOW_ELEMENT__COMMENT);
		createEAttribute(pageflowElementEClass, PAGEFLOW_ELEMENT__X);
		createEAttribute(pageflowElementEClass, PAGEFLOW_ELEMENT__Y);
		createEAttribute(pageflowElementEClass, PAGEFLOW_ELEMENT__WIDTH);
		createEAttribute(pageflowElementEClass, PAGEFLOW_ELEMENT__HEIGHT);
		createEAttribute(pageflowElementEClass, PAGEFLOW_ELEMENT__ID);
		createEAttribute(pageflowElementEClass,
				PAGEFLOW_ELEMENT__REFERENCE_LINK);

		pfPageEClass = createEClass(PF_PAGE);
		createEAttribute(pfPageEClass, PF_PAGE__PATH);
		createEAttribute(pfPageEClass, PF_PAGE__SMALLICON);
		createEAttribute(pfPageEClass, PF_PAGE__LARGEICON);

		pfLinkBendpointEClass = createEClass(PF_LINK_BENDPOINT);
		createEAttribute(pfLinkBendpointEClass, PF_LINK_BENDPOINT__D1_WIDTH);
		createEAttribute(pfLinkBendpointEClass, PF_LINK_BENDPOINT__D1_HEIGHT);
		createEAttribute(pfLinkBendpointEClass, PF_LINK_BENDPOINT__D2_WIDTH);
		createEAttribute(pfLinkBendpointEClass, PF_LINK_BENDPOINT__D2_HEIGHT);
		createEAttribute(pfLinkBendpointEClass, PF_LINK_BENDPOINT__WEIGHT);
		createEReference(pfLinkBendpointEClass, PF_LINK_BENDPOINT__LINK);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes
		pageflowEClass.getESuperTypes().add(this.getPageflowElement());
		pageflowNodeEClass.getESuperTypes().add(this.getPageflowElement());
		pfLinkEClass.getESuperTypes().add(this.getPageflowElement());
		pfPageEClass.getESuperTypes().add(this.getPageflowNode());

		// Initialize classes and features; add operations and parameters
		initEClass(pageflowEClass, Pageflow.class, "Pageflow", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPageflow_Nodes(), this.getPageflowNode(), this
				.getPageflowNode_Pageflow(), "nodes", null, 0, -1,
				Pageflow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPageflow_Links(), this.getPFLink(), this
				.getPFLink_Pageflow(), "links", null, 0, -1, Pageflow.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getPageflow_Configfile(), ecorePackage.getEString(),
				"configfile", null, 0, 1, Pageflow.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(pageflowNodeEClass, PageflowNode.class, "PageflowNode",
				IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPageflowNode_Pageflow(), this.getPageflow(), this
				.getPageflow_Nodes(), "pageflow", null, 1, 1,
				PageflowNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPageflowNode_Outlinks(), this.getPFLink(), this
				.getPFLink_Source(), "outlinks", null, 0, -1,
				PageflowNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPageflowNode_Inlinks(), this.getPFLink(), this
				.getPFLink_Target(), "inlinks", null, 0, -1,
				PageflowNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(pfLinkEClass, PageflowLink.class, "PFLink", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPFLink_Pageflow(), this.getPageflow(), this
				.getPageflow_Links(), "pageflow", null, 1, 1,
				PageflowLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPFLink_Target(), this.getPageflowNode(), this
				.getPageflowNode_Inlinks(), "target", null, 1, 1,
				PageflowLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPFLink_Source(), this.getPageflowNode(), this
				.getPageflowNode_Outlinks(), "source", null, 1, 1,
				PageflowLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLink_Outcome(), ecorePackage.getEString(),
				"outcome", null, 0, 1, PageflowLink.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLink_Redirect(), ecorePackage.getEBoolean(),
				"redirect", null, 0, 1, PageflowLink.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPFLink_BendPoints(), this.getPFLinkBendpoint(), this
				.getPFLinkBendpoint_Link(), "bendPoints", null, 0, -1,
				PageflowLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLink_Smallicon(), ecorePackage.getEString(),
				"smallicon", null, 0, 1, PageflowLink.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLink_Largeicon(), ecorePackage.getEString(),
				"largeicon", null, 0, 1, PageflowLink.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLink_Fromaction(), ecorePackage.getEString(),
				"fromaction", null, 0, 1, PageflowLink.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(pageflowElementEClass, PageflowElement.class,
				"PageflowElement", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPageflowElement_Name(), ecorePackage.getEString(),
				"name", "unnamed", 0, 1, PageflowElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageflowElement_Comment(), ecorePackage.getEString(),
				"comment", null, 0, 1, PageflowElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageflowElement_X(), ecorePackage.getEInt(), "x",
				"0", 0, 1, PageflowElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getPageflowElement_Y(), ecorePackage.getEInt(), "y",
				"0", 0, 1, PageflowElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getPageflowElement_Width(), ecorePackage.getEInt(),
				"width", "-1", 0, 1, PageflowElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageflowElement_Height(), ecorePackage.getEInt(),
				"height", "-1", 0, 1, PageflowElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageflowElement_Id(), ecorePackage.getEString(),
				"id", null, 1, 1, PageflowElement.class, !IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageflowElement_ReferenceLink(), ecorePackage
				.getEString(), "referenceLink", "", 0, 1,
				PageflowElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(pfPageEClass, PageflowPage.class, "PFPage", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPFPage_Path(), ecorePackage.getEString(), "path",
				null, 0, 1, PageflowPage.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getPFPage_Smallicon(), ecorePackage.getEString(),
				"smallicon", null, 0, 1, PageflowPage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFPage_Largeicon(), ecorePackage.getEString(),
				"largeicon", null, 0, 1, PageflowPage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(pfLinkBendpointEClass, PageflowLinkBendpoint.class,
				"PFLinkBendpoint", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPFLinkBendpoint_D1Width(), ecorePackage.getEInt(),
				"d1Width", null, 0, 1, PageflowLinkBendpoint.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLinkBendpoint_D1Height(), ecorePackage.getEInt(),
				"d1Height", null, 0, 1, PageflowLinkBendpoint.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLinkBendpoint_D2Width(), ecorePackage.getEInt(),
				"d2Width", null, 0, 1, PageflowLinkBendpoint.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLinkBendpoint_D2Height(), ecorePackage.getEInt(),
				"d2Height", null, 0, 1, PageflowLinkBendpoint.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPFLinkBendpoint_Weight(), ecorePackage.getEFloat(),
				"weight", "0.5", 0, 1, PageflowLinkBendpoint.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPFLinkBendpoint_Link(), this.getPFLink(), this
				.getPFLink_BendPoints(), "link", null, 1, 1,
				PageflowLinkBendpoint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

}
// PageflowPackageImpl
