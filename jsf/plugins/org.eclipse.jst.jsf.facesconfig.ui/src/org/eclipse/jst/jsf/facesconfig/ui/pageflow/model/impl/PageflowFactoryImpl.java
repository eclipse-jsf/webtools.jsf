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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class PageflowFactoryImpl extends EFactoryImpl implements
		PageflowFactory {
	/**
	 * Creates and instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case PageflowPackage.PAGEFLOW:
			return createPageflow();
		case PageflowPackage.PF_LINK:
			return createPFLink();
		case PageflowPackage.PF_PAGE:
			return createPFPage();
		case PageflowPackage.PF_LINK_BENDPOINT:
			return createPFLinkBendpoint();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName()
					+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pageflow createPageflow() {
		PageflowImpl pageflow = new PageflowImpl();
		return pageflow;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PFLink createPFLink() {
		PFLinkImpl pfLink = new PFLinkImpl();
		return pfLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PFPage createPFPage() {
		PFPageImpl pfPage = new PFPageImpl();
		return pfPage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PFLinkBendpoint createPFLinkBendpoint() {
		PFLinkBendpointImpl pfLinkBendpoint = new PFLinkBendpointImpl();
		return pfLinkBendpoint;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowPackage getPageflowPackage() {
		return (PageflowPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	public static PageflowPackage getPackage() {
		return PageflowPackage.eINSTANCE;
	}

}
// PageflowFactoryImpl
