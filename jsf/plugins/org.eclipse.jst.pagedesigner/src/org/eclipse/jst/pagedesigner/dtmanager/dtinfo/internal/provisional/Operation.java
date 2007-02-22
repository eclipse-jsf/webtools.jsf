/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfoPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends EObject {
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
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfoPackage#getOperation_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfoPackage#getOperation_Parameters()
	 * @model type="org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Parameter" containment="true"
	 *        extendedMetaData="kind='element' name='parameter'"
	 * @generated
	 */
	EList getParameters();

} // Operation
