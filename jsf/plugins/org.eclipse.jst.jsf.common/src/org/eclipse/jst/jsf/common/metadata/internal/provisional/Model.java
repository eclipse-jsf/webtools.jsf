/**
 * <copyright>
 * </copyright>
 *
 * $Id: Model.java,v 1.1 2007/01/15 23:26:14 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.provisional;

import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Model#getSourceModelProvider <em>Source Model Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getModel()
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
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getModel_SourceModelProvider()
	 * @model unique="false" dataType="org.eclipse.jst.jsf.common.metadata.internal.provisional.IMetaDataSourceModelProvider" transient="true" volatile="true"
	 * @generated
	 */
	IMetaDataSourceModelProvider getSourceModelProvider();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Model#getSourceModelProvider <em>Source Model Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Model Provider</em>' attribute.
	 * @see #getSourceModelProvider()
	 * @generated
	 */
	void setSourceModelProvider(IMetaDataSourceModelProvider value);

} // Model
