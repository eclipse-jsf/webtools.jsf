/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagCreationInfo.java,v 1.1 2007/02/28 05:04:45 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Creation Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationInfo#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationInfo#getTemplate <em>Template</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationInfo()
 * @model
 * @generated
 */
public interface TagCreationInfo extends EObject {
	public static final String TRAIT_ID = "tag-create";
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' attribute.
	 * @see #setTemplate(Object)
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationInfo_Template()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType"
	 * @generated
	 */
	Object getTemplate();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationInfo#getTemplate <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' attribute.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(Object value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationInfo_Attributes()
	 * @model type="org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute" containment="true"
	 *        extendedMetaData="kind='element' name='attribute'"
	 * @generated
	 */
	EList getAttributes();

} // TagCreationInfo
