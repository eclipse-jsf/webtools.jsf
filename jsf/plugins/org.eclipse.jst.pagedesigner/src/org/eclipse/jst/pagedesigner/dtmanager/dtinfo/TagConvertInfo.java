/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Convert Info</b></em>'.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagConvertInfo()
 * @model
 * @generated
 */
public interface TagConvertInfo extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagConvertInfo_Operations()
	 * @model type="org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation" containment="true"
	 *        extendedMetaData="kind='element' name='operation'"
	 * @generated
	 */
	EList getOperations();

} // TagConvertInfo
