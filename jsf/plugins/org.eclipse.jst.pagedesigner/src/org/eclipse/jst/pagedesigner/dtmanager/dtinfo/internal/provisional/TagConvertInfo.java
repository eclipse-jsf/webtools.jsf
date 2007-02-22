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
 * A representation of the model object '<em><b>Tag Convert Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo#getClassname <em>Classname</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfoPackage#getTagConvertInfo()
 * @model
 * @generated
 */
public interface TagConvertInfo extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfoPackage#getTagConvertInfo_Operations()
	 * @model type="org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation" containment="true"
	 *        extendedMetaData="kind='element' name='operation'"
	 * @generated
	 */
	EList getOperations();

	/**
	 * Returns the value of the '<em><b>Classname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classname</em>' attribute.
	 * @see #setClassname(String)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfoPackage#getTagConvertInfo_Classname()
	 * @model
	 * @generated
	 */
	String getClassname();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo#getClassname <em>Classname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classname</em>' attribute.
	 * @see #getClassname()
	 * @generated
	 */
	void setClassname(String value);

} // TagConvertInfo
