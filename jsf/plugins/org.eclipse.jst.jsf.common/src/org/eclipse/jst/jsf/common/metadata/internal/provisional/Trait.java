/**
 * <copyright>
 * </copyright>
 *
 * $Id: Trait.java,v 1.1 2007/01/15 23:26:14 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.provisional;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.ITraitVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trait</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getSourceModel <em>Source Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getTrait()
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
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getTrait_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' reference.
	 * @see #setValue(EObject)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getTrait_Value()
	 * @model
	 * @generated
	 */
	EObject getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getValue <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EObject value);

	/**
	 * Returns the value of the '<em><b>Source Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Model</em>' reference.
	 * @see #isSetSourceModel()
	 * @see #unsetSourceModel()
	 * @see #setSourceModel(Model)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getTrait_SourceModel()
	 * @model unsettable="true" required="true" transient="true"
	 * @generated
	 */
	Model getSourceModel();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getSourceModel <em>Source Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Model</em>' reference.
	 * @see #isSetSourceModel()
	 * @see #unsetSourceModel()
	 * @see #getSourceModel()
	 * @generated
	 */
	void setSourceModel(Model value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getSourceModel <em>Source Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSourceModel()
	 * @see #getSourceModel()
	 * @see #setSourceModel(Model)
	 * @generated
	 */
	void unsetSourceModel();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getSourceModel <em>Source Model</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Source Model</em>' reference is set.
	 * @see #unsetSourceModel()
	 * @see #getSourceModel()
	 * @see #setSourceModel(Model)
	 * @generated
	 */
	boolean isSetSourceModel();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="org.eclipse.jst.jsf.common.metadata.internal.provisional.ITraitVisitor"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='visitor.visit(this);'"
	 * @generated
	 */
	void accept(ITraitVisitor visitor);

} // Trait
