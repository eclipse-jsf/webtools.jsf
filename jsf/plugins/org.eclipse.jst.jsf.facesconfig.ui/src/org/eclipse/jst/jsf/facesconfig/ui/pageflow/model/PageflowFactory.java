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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage
 * @generated
 */
public interface PageflowFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	PageflowFactory eINSTANCE = new org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowFactoryImpl();

	/**
	 * Returns a new object of class '<em>Pageflow</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Pageflow</em>'.
	 * @generated
	 */
	Pageflow createPageflow();

	/**
	 * Returns a new object of class '<em>PF Link</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>PF Link</em>'.
	 * @generated
	 */
	PFLink createPFLink();

	/**
	 * Returns a new object of class '<em>PF Page</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>PF Page</em>'.
	 * @generated
	 */
	PFPage createPFPage();

	/**
	 * Returns a new object of class '<em>PF Link Bendpoint</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>PF Link Bendpoint</em>'.
	 * @generated
	 */
	PFLinkBendpoint createPFLinkBendpoint();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	PageflowPackage getPageflowPackage();

}
// PageflowFactory
