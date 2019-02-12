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
 * $Id: FaceletTaglib.java,v 1.1 2010/03/18 06:24:29 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *                 The top level XML element in a facelet tag library XML file.
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getLibraryClass <em>Library Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getCompositeLibraryName <em>Composite Library Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getTag <em>Tag</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getFunction <em>Function</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getTaglibExtension <em>Taglib Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespaceUri <em>Namespace Uri</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getShortName <em>Short Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib()
 * @model extendedMetaData="name='facelet-taglibType' kind='elementOnly'"
 * @generated
 */
public interface FaceletTaglib extends UserVisibleTaglibObject
{
    /**
	 * Returns the value of the '<em><b>Library Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Library Class</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Library Class</em>' containment reference.
	 * @see #setLibraryClass(FullyQualifiedClass)
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_LibraryClass()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='library-class' namespace='##targetNamespace'"
	 * @generated
	 */
    FullyQualifiedClass getLibraryClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getLibraryClass <em>Library Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Library Class</em>' containment reference.
	 * @see #getLibraryClass()
	 * @generated
	 */
    void setLibraryClass(FullyQualifiedClass value);

    /**
	 * Returns the value of the '<em><b>Namespace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespace</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace</em>' containment reference.
	 * @see #setNamespace(IdentifiableStringValue)
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_Namespace()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='namespace' namespace='##targetNamespace'"
	 * @generated
	 */
    IdentifiableStringValue getNamespace();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespace <em>Namespace</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace</em>' containment reference.
	 * @see #getNamespace()
	 * @generated
	 */
    void setNamespace(IdentifiableStringValue value);

    /**
	 * Returns the value of the '<em><b>Composite Library Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Composite Library Name</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Library Name</em>' containment reference.
	 * @see #setCompositeLibraryName(FullyQualifiedClass)
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_CompositeLibraryName()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='composite-library-name' namespace='##targetNamespace'"
	 * @generated
	 */
    FullyQualifiedClass getCompositeLibraryName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getCompositeLibraryName <em>Composite Library Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Composite Library Name</em>' containment reference.
	 * @see #getCompositeLibraryName()
	 * @generated
	 */
    void setCompositeLibraryName(FullyQualifiedClass value);

    /**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:6'"
	 * @generated
	 */
    FeatureMap getGroup();

    /**
	 * Returns the value of the '<em><b>Tag</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_Tag()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='tag' namespace='##targetNamespace' group='#group:6'"
	 * @generated
	 */
    EList<FaceletTaglibTag> getTag();

    /**
	 * Returns the value of the '<em><b>Function</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_Function()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='function' namespace='##targetNamespace' group='#group:6'"
	 * @generated
	 */
    EList<FaceletTaglibFunction> getFunction();

    /**
	 * Returns the value of the '<em><b>Taglib Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Taglib Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Taglib Extension</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_TaglibExtension()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='taglib-extension' namespace='##targetNamespace'"
	 * @generated
	 */
    EList<FaceletTaglibExtension> getTaglibExtension();

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
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
    String getId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
    void setId(String value);

    /**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(FaceletTaglibVersion)
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_Version()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
    FaceletTaglibVersion getVersion();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @generated
	 */
    void setVersion(FaceletTaglibVersion value);

    /**
	 * Unsets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(FaceletTaglibVersion)
	 * @generated
	 */
    void unsetVersion();

    /**
	 * Returns whether the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getVersion <em>Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(FaceletTaglibVersion)
	 * @generated
	 */
    boolean isSetVersion();

    /**
	 * Returns the value of the '<em><b>Namespace Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespace Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace Uri</em>' attribute.
	 * @see #setNamespaceUri(String)
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_NamespaceUri()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token" transient="true" volatile="true"
	 * @generated
	 */
    String getNamespaceUri();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespaceUri <em>Namespace Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace Uri</em>' attribute.
	 * @see #getNamespaceUri()
	 * @generated
	 */
    void setNamespaceUri(String value);

				/**
	 * Returns the value of the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Name</em>' attribute.
	 * @see #setShortName(String)
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglib_ShortName()
	 * @model extendedMetaData="kind='element' name='short-name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getShortName();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getShortName <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Name</em>' attribute.
	 * @see #getShortName()
	 * @generated
	 */
	void setShortName(String value);

} // FaceletTaglib
