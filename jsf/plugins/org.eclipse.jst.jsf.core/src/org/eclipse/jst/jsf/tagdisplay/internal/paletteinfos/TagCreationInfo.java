/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: TagCreationInfo.java,v 1.2 2008/11/18 22:23:54 gkessler Exp $
 */
package org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos;

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
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo#getTemplate <em>Template</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getTagCreationInfo()
 * @model
 * @generated
 */
public interface TagCreationInfo extends EObject {
	/**
	 * the name of the trait id
	 */
	public static final String TRAIT_ID = "tag-create"; //$NON-NLS-1$
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getTagCreationInfo_Attributes()
	 * @model type="org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationAttribute" containment="true"
	 *        extendedMetaData="kind='element' name='attribute'"
	 * @generated
	 */
	EList getAttributes();

	/**
	 * Returns the value of the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' attribute.
	 * @see #setTemplate(Object)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getTagCreationInfo_Template()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType"
	 * @generated
	 */
	Object getTemplate();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo#getTemplate <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' attribute.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(Object value);

} // TagCreationInfo
