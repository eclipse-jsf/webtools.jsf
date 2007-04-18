/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.common.metadata;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trait</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Trait#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Trait#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Trait#getSourceModelProvider <em>Source Model Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getTrait()
 * @model extendedMetaData="kind='element' name='trait'"
 * @generated
 */
public interface Trait extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

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
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getTrait_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.Trait#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(EObject)
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getTrait_Value()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	EObject getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.Trait#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EObject value);

	/**
	 * Returns the value of the '<em><b>Source Model Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Model Provider</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Model Provider</em>' attribute.
	 * @see #setSourceModelProvider(IMetaDataSourceModelProvider)
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getTrait_SourceModelProvider()
	 * @model dataType="org.eclipse.jst.jsf.common.metadata.IMetaDataSourceModelProvider"
	 * @generated
	 */
	IMetaDataSourceModelProvider getSourceModelProvider();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.Trait#getSourceModelProvider <em>Source Model Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Model Provider</em>' attribute.
	 * @see #getSourceModelProvider()
	 * @generated
	 */
	void setSourceModelProvider(IMetaDataSourceModelProvider value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='visitor.visit(this);'"
	 * @generated
	 */
	void accept(ITraitVisitor visitor);

} // Trait
