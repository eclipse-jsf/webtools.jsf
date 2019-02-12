/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
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
 * $Id: FaceletTaglib_1_0Factory.java,v 1.1 2010/03/18 06:24:39 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package
 * @generated
 */
public interface FaceletTaglib_1_0Factory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    FaceletTaglib_1_0Factory eINSTANCE = org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl.FaceletTaglib_1_0FactoryImpl.init();

    /**
     * Returns a new object of class '<em>Facelet Library Class Tag Lib</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Facelet Library Class Tag Lib</em>'.
     * @generated
     */
    FaceletLibraryClassTagLib createFaceletLibraryClassTagLib();

    /**
     * Returns a new object of class '<em>Facelet XML Defn Taglib</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Facelet XML Defn Taglib</em>'.
     * @generated
     */
    FaceletXMLDefnTaglib createFaceletXMLDefnTaglib();

    /**
     * Returns a new object of class '<em>Facelet Taglib Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Facelet Taglib Defn</em>'.
     * @generated
     */
    FaceletTaglibDefn createFaceletTaglibDefn();

    /**
     * Returns a new object of class '<em>Component Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Component Tag Defn</em>'.
     * @generated
     */
    ComponentTagDefn createComponentTagDefn();

    /**
     * Returns a new object of class '<em>Validator Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Validator Tag Defn</em>'.
     * @generated
     */
    ValidatorTagDefn createValidatorTagDefn();

    /**
     * Returns a new object of class '<em>Converter Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Converter Tag Defn</em>'.
     * @generated
     */
    ConverterTagDefn createConverterTagDefn();

    /**
     * Returns a new object of class '<em>Handler Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Handler Tag Defn</em>'.
     * @generated
     */
    HandlerTagDefn createHandlerTagDefn();

    /**
     * Returns a new object of class '<em>Source Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Source Tag Defn</em>'.
     * @generated
     */
    SourceTagDefn createSourceTagDefn();

    /**
     * Returns a new object of class '<em>Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Tag Defn</em>'.
     * @generated
     */
    TagDefn createTagDefn();

    /**
     * Returns a new object of class '<em>Function Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Defn</em>'.
     * @generated
     */
    FunctionDefn createFunctionDefn();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FaceletTaglib_1_0Package getFaceletTaglib_1_0Package();

} //FaceletTaglib_1_0Factory
