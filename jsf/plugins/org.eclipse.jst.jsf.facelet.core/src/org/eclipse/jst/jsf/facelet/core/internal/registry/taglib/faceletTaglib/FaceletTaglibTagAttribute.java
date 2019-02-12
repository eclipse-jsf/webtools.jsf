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
 * $Id: FaceletTaglibTagAttribute.java,v 1.1 2010/03/18 06:24:37 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib Tag Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *                 The attribute element defines an attribute for the nesting
 *                 tag. The attribute element may have several subelements
 *                 defining:
 * 
 *                 description a description of the attribute
 * 
 *                 name the name of the attribute
 * 
 *                 required whether the attribute is required or
 *                 optional
 * 
 *                 type the type of the attribute
 * 
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getNameElement <em>Name Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getRequiredElement <em>Required Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getTypeElement <em>Type Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignatureElement <em>Method Signature Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignature <em>Method Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute()
 * @model extendedMetaData="name='facelet-taglib-tag-attributeType' kind='elementOnly'"
 * @generated
 */
public interface FaceletTaglibTagAttribute extends UserVisibleTaglibObject
{
    /**
     * Returns the value of the '<em><b>Name Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name Element</em>' containment reference.
     * @see #setNameElement(FaceletTaglibCanonicalName)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_NameElement()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
     * @generated
     */
    FaceletTaglibCanonicalName getNameElement();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getNameElement <em>Name Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name Element</em>' containment reference.
     * @see #getNameElement()
     * @generated
     */
    void setNameElement(FaceletTaglibCanonicalName value);

    /**
     * Returns the value of the '<em><b>Required Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * 
     *                         Defines if the nesting attribute is required or
     *                         optional.
     * 
     *                         If not present then the default is "false", i.e
     *                         the attribute is optional.
     * 
     *                     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Required Element</em>' containment reference.
     * @see #setRequiredElement(GenericBoolean)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_RequiredElement()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='required' namespace='##targetNamespace'"
     * @generated
     */
    GenericBoolean getRequiredElement();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getRequiredElement <em>Required Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Required Element</em>' containment reference.
     * @see #getRequiredElement()
     * @generated
     */
    void setRequiredElement(GenericBoolean value);

    /**
     * Returns the value of the '<em><b>Type Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * 
     *                             Defines the Java type of the attributes
     *                             value. If this element is omitted, the
     *                             expected type is assumed to be
     *                             "java.lang.Object".
     * 
     *                         
     * <!-- end-model-doc -->
     * @return the value of the '<em>Type Element</em>' containment reference.
     * @see #setTypeElement(FullyQualifiedClass)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_TypeElement()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
     * @generated
     */
    FullyQualifiedClass getTypeElement();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getTypeElement <em>Type Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Element</em>' containment reference.
     * @see #getTypeElement()
     * @generated
     */
    void setTypeElement(FullyQualifiedClass value);

    /**
     * Returns the value of the '<em><b>Method Signature Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * 
     *                             Defines the method signature for a MethodExpression-
     *                             enabled attribute.
     * 
     *                         
     * <!-- end-model-doc -->
     * @return the value of the '<em>Method Signature Element</em>' containment reference.
     * @see #setMethodSignatureElement(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_MethodSignatureElement()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='method-signature' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getMethodSignatureElement();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignatureElement <em>Method Signature Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Method Signature Element</em>' containment reference.
     * @see #getMethodSignatureElement()
     * @generated
     */
    void setMethodSignatureElement(IdentifiableStringValue value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_Name()
     * @model transient="true" volatile="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Required</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Required</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Required</em>' attribute.
     * @see #setRequired(boolean)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_Required()
     * @model transient="true" volatile="true"
     * @generated
     */
    boolean isRequired();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#isRequired <em>Required</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Required</em>' attribute.
     * @see #isRequired()
     * @generated
     */
    void setRequired(boolean value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_Type()
     * @model transient="true" volatile="true"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

    /**
     * Returns the value of the '<em><b>Method Signature</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Method Signature</em>' attribute.
     * @see #setMethodSignature(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_MethodSignature()
     * @model transient="true" volatile="true"
     * @generated
     */
    String getMethodSignature();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignature <em>Method Signature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Method Signature</em>' attribute.
     * @see #getMethodSignature()
     * @generated
     */
    void setMethodSignature(String value);

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
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagAttribute_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // FaceletTaglibTagAttribute
