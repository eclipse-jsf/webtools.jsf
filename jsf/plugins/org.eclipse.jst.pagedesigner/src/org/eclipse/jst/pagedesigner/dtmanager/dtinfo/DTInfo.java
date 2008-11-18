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
 * A representation of the model object '<em><b>DT Info</b></em>'.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo#getTagConvertInfo <em>Tag Convert Info</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo#getTagDecorateInfos <em>Tag Decorate Infos</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getDTInfo()
 * @model
 * @generated
 */
public interface DTInfo extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Tag Convert Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Convert Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Convert Info</em>' containment reference.
	 * @see #setTagConvertInfo(TagConvertInfo)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getDTInfo_TagConvertInfo()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='tag-convert-info'"
	 * @generated
	 */
	TagConvertInfo getTagConvertInfo();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo#getTagConvertInfo <em>Tag Convert Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Convert Info</em>' containment reference.
	 * @see #getTagConvertInfo()
	 * @generated
	 */
	void setTagConvertInfo(TagConvertInfo value);

	/**
	 * Returns the value of the '<em><b>Tag Decorate Infos</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Decorate Infos</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Decorate Infos</em>' containment reference list.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getDTInfo_TagDecorateInfos()
	 * @model type="org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagDecorateInfo" containment="true"
	 *        extendedMetaData="kind='element' name='tag-decorate-info'"
	 * @generated
	 */
	EList getTagDecorateInfos();

} // DTInfo
