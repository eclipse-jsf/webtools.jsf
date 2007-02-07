/**
 * <copyright>
 * </copyright>
 *
 * $Id: Entity.java,v 1.2 2007/02/07 00:03:49 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.provisional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IEntityVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getChildEntities <em>Child Entities</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getTraits <em>Traits</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getIncludeGroups <em>Include Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity()
 * @model extendedMetaData="kind='element' name='entity'"
 * @generated
 */
public interface Entity extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Child Entities</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Entities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Entities</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_ChildEntities()
	 * @model type="org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity" containment="true"
	 *        extendedMetaData="kind='element' name='entity'"
	 * @generated
	 */
	EList getChildEntities();

	/**
	 * Returns the value of the '<em><b>Traits</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traits</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traits</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_Traits()
	 * @model type="org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait" containment="true"
	 *        extendedMetaData="kind='element' name='trait'"
	 * @generated
	 */
	EList getTraits();

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
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Include Groups</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.internal.provisional.IncludeEntityGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include Groups</em>' reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_IncludeGroups()
	 * @model type="org.eclipse.jst.jsf.common.metadata.internal.provisional.IncludeEntityGroup"
	 *        extendedMetaData="kind='element' name='include-entity-group'"
	 * @generated
	 */
	EList getIncludeGroups();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="org.eclipse.jst.jsf.common.metadata.internal.provisional.IEntityVisitor"
	 * @generated
	 */
	void accept(IEntityVisitor visitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Model getModel();

} // Entity
