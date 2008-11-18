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
 * A representation of the model object '<em><b>Converter Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterForClass <em>Converter For Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterClass <em>Converter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterExtension <em>Converter Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType()
 * @model extendedMetaData="name='converter_._type' kind='elementOnly'"
 * @generated
 */
public interface ConverterType extends EObject {
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_Description()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_DisplayName()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_Icon()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
     *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
     * @generated
     */
	EList getIcon();

    /**
     * Returns the value of the '<em><b>Converter Id</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Converter Id</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Converter Id</em>' containment reference.
     * @see #setConverterId(ConverterIdType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_ConverterId()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='converter-id' namespace='##targetNamespace'"
     * @generated
     */
	ConverterIdType getConverterId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterId <em>Converter Id</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Converter Id</em>' containment reference.
     * @see #getConverterId()
     * @generated
     */
	void setConverterId(ConverterIdType value);

    /**
     * Returns the value of the '<em><b>Converter For Class</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Converter For Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Converter For Class</em>' containment reference.
     * @see #setConverterForClass(ConverterForClassType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_ConverterForClass()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='converter-for-class' namespace='##targetNamespace'"
     * @generated
     */
	ConverterForClassType getConverterForClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterForClass <em>Converter For Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Converter For Class</em>' containment reference.
     * @see #getConverterForClass()
     * @generated
     */
	void setConverterForClass(ConverterForClassType value);

    /**
     * Returns the value of the '<em><b>Converter Class</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Converter Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Converter Class</em>' containment reference.
     * @see #setConverterClass(ConverterClassType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_ConverterClass()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='converter-class' namespace='##targetNamespace'"
     * @generated
     */
	ConverterClassType getConverterClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterClass <em>Converter Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Converter Class</em>' containment reference.
     * @see #getConverterClass()
     * @generated
     */
	void setConverterClass(ConverterClassType value);

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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_Attribute()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.AttributeType" containment="true"
     *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace'"
     * @generated
     */
	EList getAttribute();

    /**
     * Returns the value of the '<em><b>Property</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Property</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_Property()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.PropertyType" containment="true"
     *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
     * @generated
     */
	EList getProperty();

    /**
     * Returns the value of the '<em><b>Converter Extension</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ConverterExtensionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Converter Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Converter Extension</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_ConverterExtension()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ConverterExtensionType" containment="true"
     *        extendedMetaData="kind='element' name='converter-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList getConverterExtension();

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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getConverterType_Id()
     * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

} // ConverterType
