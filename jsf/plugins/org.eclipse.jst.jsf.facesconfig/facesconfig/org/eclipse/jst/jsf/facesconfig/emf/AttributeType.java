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
 * A representation of the model object '<em><b>Attribute Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeName <em>Attribute Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeClass <em>Attribute Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getSuggestedValue <em>Suggested Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeExtension <em>Attribute Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType()
 * @model extendedMetaData="name='attribute_._type' kind='elementOnly'"
 * @generated
 */
public interface AttributeType extends EObject {
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_Description()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.DescriptionType" containment="true" resolveProxies="false"
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_DisplayName()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType" containment="true" resolveProxies="false"
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_Icon()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getIcon();

	/**
	 * Returns the value of the '<em><b>Attribute Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Name</em>' containment reference.
	 * @see #setAttributeName(AttributeNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_AttributeName()
	 * @model containment="true" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='attribute-name' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributeNameType getAttributeName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeName <em>Attribute Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Name</em>' containment reference.
	 * @see #getAttributeName()
	 * @generated
	 */
	void setAttributeName(AttributeNameType value);

	/**
	 * Returns the value of the '<em><b>Attribute Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Class</em>' containment reference.
	 * @see #setAttributeClass(AttributeClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_AttributeClass()
	 * @model containment="true" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='attribute-class' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributeClassType getAttributeClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeClass <em>Attribute Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Class</em>' containment reference.
	 * @see #getAttributeClass()
	 * @generated
	 */
	void setAttributeClass(AttributeClassType value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' containment reference.
	 * @see #setDefaultValue(DefaultValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_DefaultValue()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='default-value' namespace='##targetNamespace'"
	 * @generated
	 */
	DefaultValueType getDefaultValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDefaultValue <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' containment reference.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(DefaultValueType value);

	/**
	 * Returns the value of the '<em><b>Suggested Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suggested Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suggested Value</em>' containment reference.
	 * @see #setSuggestedValue(SuggestedValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_SuggestedValue()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='suggested-value' namespace='##targetNamespace'"
	 * @generated
	 */
	SuggestedValueType getSuggestedValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getSuggestedValue <em>Suggested Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suggested Value</em>' containment reference.
	 * @see #getSuggestedValue()
	 * @generated
	 */
	void setSuggestedValue(SuggestedValueType value);

	/**
	 * Returns the value of the '<em><b>Attribute Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Extension</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Extension</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_AttributeExtension()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='attribute-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getAttributeExtension();

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAttributeType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // AttributeType
