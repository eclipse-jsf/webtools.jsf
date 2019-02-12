/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
 * A representation of the model object '<em><b>Render Kit Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitId <em>Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitClass <em>Render Kit Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderer <em>Renderer</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getClientBehaviorRenderer <em>Client Behavior Renderer</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitExtension <em>Render Kit Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType()
 * @model extendedMetaData="name='render-kit_._type' kind='elementOnly'"
 * @generated
 */
public interface RenderKitType extends EObject {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_Description()
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_DisplayName()
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_Icon()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
	 *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getIcon();

    /**
	 * Returns the value of the '<em><b>Render Kit Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Render Kit Id</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Render Kit Id</em>' containment reference.
	 * @see #setRenderKitId(RenderKitIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_RenderKitId()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='render-kit-id' namespace='##targetNamespace'"
	 * @generated
	 */
	RenderKitIdType getRenderKitId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitId <em>Render Kit Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Render Kit Id</em>' containment reference.
	 * @see #getRenderKitId()
	 * @generated
	 */
	void setRenderKitId(RenderKitIdType value);

    /**
	 * Returns the value of the '<em><b>Render Kit Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Render Kit Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Render Kit Class</em>' containment reference.
	 * @see #setRenderKitClass(RenderKitClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_RenderKitClass()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='render-kit-class' namespace='##targetNamespace'"
	 * @generated
	 */
	RenderKitClassType getRenderKitClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitClass <em>Render Kit Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Render Kit Class</em>' containment reference.
	 * @see #getRenderKitClass()
	 * @generated
	 */
	void setRenderKitClass(RenderKitClassType value);

    /**
	 * Returns the value of the '<em><b>Renderer</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.RendererType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Renderer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Renderer</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_Renderer()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.RendererType" containment="true"
	 *        extendedMetaData="kind='element' name='renderer' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getRenderer();

    /**
	 * Returns the value of the '<em><b>Client Behavior Renderer</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client Behavior Renderer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Client Behavior Renderer</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_ClientBehaviorRenderer()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType" containment="true"
	 *        extendedMetaData="kind='element' name='client-behavior-renderer' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getClientBehaviorRenderer();

				/**
	 * Returns the value of the '<em><b>Render Kit Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitExtensionType}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Render Kit Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Render Kit Extension</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_RenderKitExtension()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.RenderKitExtensionType" containment="true"
	 *        extendedMetaData="kind='element' name='render-kit-extension' namespace='##targetNamespace'"
	 * @generated
	 */
    EList getRenderKitExtension();

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRenderKitType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // RenderKitType
