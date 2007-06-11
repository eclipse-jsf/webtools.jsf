/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.common.metadata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor;
import org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Model#getEntityGroups <em>Entity Groups</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Model#getSourceModelProvider <em>Source Model Provider</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Model#getCurrentModelContext <em>Current Model Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getModel()
 * @model extendedMetaData="kind='element' name='metadatamodel'"
 * @generated
 */
public interface Model extends Entity {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

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
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getModel_SourceModelProvider()
	 * @model unique="false" dataType="org.eclipse.jst.jsf.common.metadata.IMetaDataSourceModelProvider" transient="true" volatile="true"
	 * @generated
	 */
	IMetaDataSourceModelProvider getSourceModelProvider();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.Model#getSourceModelProvider <em>Source Model Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Model Provider</em>' attribute.
	 * @see #getSourceModelProvider()
	 * @generated
	 */
	void setSourceModelProvider(IMetaDataSourceModelProvider value);

	/**
	 * Returns the value of the '<em><b>Current Model Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current Model Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Model Context</em>' attribute.
	 * @see #setCurrentModelContext(ModelKeyDescriptor)
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getModel_CurrentModelContext()
	 * @model dataType="org.eclipse.jst.jsf.common.metadata.ModelContext" transient="true" volatile="true"
	 * @generated
	 */
	ModelKeyDescriptor getCurrentModelContext();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.Model#getCurrentModelContext <em>Current Model Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Model Context</em>' attribute.
	 * @see #getCurrentModelContext()
	 * @generated
	 */
	void setCurrentModelContext(ModelKeyDescriptor value);

	/**
	 * Returns the value of the '<em><b>Entity Groups</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.EntityGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Groups</em>' reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getModel_EntityGroups()
	 * @model type="org.eclipse.jst.jsf.common.metadata.EntityGroup"
	 *        extendedMetaData="kind='element' name='entityGroup'"
	 * @generated
	 */
	EList getEntityGroups();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param groupId 
	 * @return EntityGroup
	 * @model
	 * @generated
	 */
	EntityGroup findIncludeGroup(String groupId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="org.eclipse.jst.jsf.common.metadata.IEntityVisitor"
	 * @generated
	 */
	void accept(IEntityVisitor visitor);

} // Model
