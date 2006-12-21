/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Bundle Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getBaseName <em>Base Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getVar <em>Var</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getResourceBundleType()
 * @model extendedMetaData="name='resourceBundle_._type' kind='elementOnly'"
 * @generated
 */
public interface ResourceBundleType extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * Returns the value of the '<em><b>Description</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.DescriptionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getResourceBundleType_Description()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.DescriptionType" containment="true"
     *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
     * @generated
     */
    EList getDescription();

    /**
     * Returns the value of the '<em><b>Display Name</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Name</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getResourceBundleType_DisplayName()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType" containment="true"
     *        extendedMetaData="kind='element' name='display-name' namespace='##targetNamespace'"
     * @generated
     */
    EList getDisplayName();

    /**
     * Returns the value of the '<em><b>Icon</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.IconType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Icon</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Icon</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getResourceBundleType_Icon()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
     *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
     * @generated
     */
    EList getIcon();

    /**
     * Returns the value of the '<em><b>Base Name</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Base Name</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Base Name</em>' reference.
     * @see #setBaseName(BaseNameType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getResourceBundleType_BaseName()
     * @model extendedMetaData="kind='element' name='base-name' namespace='##targetNamespace'"
     * @generated
     */
    BaseNameType getBaseName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getBaseName <em>Base Name</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Base Name</em>' reference.
     * @see #getBaseName()
     * @generated
     */
    void setBaseName(BaseNameType value);

    /**
     * Returns the value of the '<em><b>Var</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Var</em>' reference.
     * @see #setVar(VarType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getResourceBundleType_Var()
     * @model extendedMetaData="kind='element' name='var' namespace='##targetNamespace'"
     * @generated
     */
    VarType getVar();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getVar <em>Var</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Var</em>' reference.
     * @see #getVar()
     * @generated
     */
    void setVar(VarType value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getResourceBundleType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // ResourceBundleType
