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
 * A representation of the model object '<em><b>Factory Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getApplicationFactory <em>Application Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getExceptionHandlerFactory <em>Exception Handler Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getExternalContextFactory <em>External Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getFacesContextFactory <em>Faces Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getPartialViewContextFactory <em>Partial View Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getLifecycleFactory <em>Lifecycle Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getViewDeclarationLanguageFactory <em>View Declaration Language Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getTagHandlerDelegateFactory <em>Tag Handler Delegate Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getRenderKitFactory <em>Render Kit Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getVisitContextFactory <em>Visit Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getFactoryExtension <em>Factory Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType()
 * @model extendedMetaData="name='factory_._type' kind='elementOnly'"
 * @generated
 */
public interface FactoryType extends EObject {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * Returns the value of the '<em><b>Application Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Application Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_ApplicationFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='application-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getApplicationFactory();

    /**
	 * Returns the value of the '<em><b>Exception Handler Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handler Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handler Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_ExceptionHandlerFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='exception-handler-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getExceptionHandlerFactory();

				/**
	 * Returns the value of the '<em><b>External Context Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Context Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Context Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_ExternalContextFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='external-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getExternalContextFactory();

				/**
	 * Returns the value of the '<em><b>Faces Context Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Faces Context Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Faces Context Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_FacesContextFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='faces-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getFacesContextFactory();

    /**
	 * Returns the value of the '<em><b>Partial View Context Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partial View Context Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partial View Context Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_PartialViewContextFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='partial-view-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getPartialViewContextFactory();

				/**
	 * Returns the value of the '<em><b>Lifecycle Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lifecycle Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lifecycle Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_LifecycleFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='lifecycle-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getLifecycleFactory();

    /**
	 * Returns the value of the '<em><b>View Declaration Language Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Declaration Language Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Declaration Language Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_ViewDeclarationLanguageFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='view-declaration-language-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getViewDeclarationLanguageFactory();

				/**
	 * Returns the value of the '<em><b>Tag Handler Delegate Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Handler Delegate Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Handler Delegate Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_TagHandlerDelegateFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='tag-handler-delegate-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getTagHandlerDelegateFactory();

				/**
	 * Returns the value of the '<em><b>Render Kit Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Render Kit Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Render Kit Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_RenderKitFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='render-kit-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getRenderKitFactory();

    /**
	 * Returns the value of the '<em><b>Visit Context Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visit Context Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visit Context Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_VisitContextFactory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='visit-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getVisitContextFactory();

				/**
	 * Returns the value of the '<em><b>Factory Extension</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.FactoryExtensionType}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Factory Extension</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Factory Extension</em>' reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_FactoryExtension()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.FactoryExtensionType"
	 *        extendedMetaData="kind='element' name='factory-type' namespace='##targetNamespace'"
	 * @generated
	 */
    EList getFactoryExtension();

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFactoryType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // FactoryType
