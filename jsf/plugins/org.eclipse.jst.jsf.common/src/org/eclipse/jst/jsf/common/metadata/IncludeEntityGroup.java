/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.common.metadata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Include Entity Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getModelUri <em>Model Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getIncludeEntityGroup()
 * @model
 * @generated
 */
public interface IncludeEntityGroup extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

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
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getIncludeEntityGroup_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Model Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Uri</em>' attribute.
	 * @see #setModelUri(String)
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getIncludeEntityGroup_ModelUri()
	 * @model extendedMetaData="name='uri'"
	 * @generated
	 */
	String getModelUri();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getModelUri <em>Model Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Uri</em>' attribute.
	 * @see #getModelUri()
	 * @generated
	 */
	void setModelUri(String value);

} // IncludeEntityGroup
