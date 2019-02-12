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
 * A representation of the model object '<em><b>Renderer Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getComponentFamily <em>Component Family</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererType <em>Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererClass <em>Renderer Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getFacet <em>Facet</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererExtension <em>Renderer Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType()
 * @model extendedMetaData="name='renderer_._type' kind='elementOnly'"
 * @generated
 */
public interface RendererType extends EObject {
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_Description()
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_DisplayName()
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_Icon()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
	 *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getIcon();

    /**
	 * Returns the value of the '<em><b>Component Family</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Family</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Family</em>' containment reference.
	 * @see #setComponentFamily(ComponentFamilyType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_ComponentFamily()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='component-family' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentFamilyType getComponentFamily();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getComponentFamily <em>Component Family</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Family</em>' containment reference.
	 * @see #getComponentFamily()
	 * @generated
	 */
	void setComponentFamily(ComponentFamilyType value);

    /**
	 * Returns the value of the '<em><b>Renderer Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Renderer Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Renderer Type</em>' containment reference.
	 * @see #setRendererType(RendererTypeType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_RendererType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='renderer-type' namespace='##targetNamespace'"
	 * @generated
	 */
	RendererTypeType getRendererType();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererType <em>Renderer Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer Type</em>' containment reference.
	 * @see #getRendererType()
	 * @generated
	 */
	void setRendererType(RendererTypeType value);

    /**
	 * Returns the value of the '<em><b>Renderer Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Renderer Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Renderer Class</em>' containment reference.
	 * @see #setRendererClass(RendererClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_RendererClass()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='renderer-class' namespace='##targetNamespace'"
	 * @generated
	 */
	RendererClassType getRendererClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererClass <em>Renderer Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer Class</em>' containment reference.
	 * @see #getRendererClass()
	 * @generated
	 */
	void setRendererClass(RendererClassType value);

    /**
	 * Returns the value of the '<em><b>Facet</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.FacetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Facet</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Facet</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_Facet()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.FacetType" containment="true"
	 *        extendedMetaData="kind='element' name='facet' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getFacet();

    /**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_Attribute()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.AttributeType" containment="true"
	 *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getAttribute();

    /**
	 * Returns the value of the '<em><b>Renderer Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.RendererExtensionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Renderer Extension</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Renderer Extension</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_RendererExtension()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.RendererExtensionType" containment="true"
	 *        extendedMetaData="kind='element' name='renderer-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getRendererExtension();

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRendererType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // RendererType
